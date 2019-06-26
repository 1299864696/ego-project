package com.xkt.search.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xkt.base.mapper.ItemMapper;
import com.xkt.base.vo.EgoResult;
import com.xkt.base.vo.SearchItem;
import com.xkt.base.vo.SearchResult;
import com.xkt.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private ItemMapper mapper;

	@Autowired
	private HttpSolrServer solrServer;

	@Value("${solr.pageSize}")
	private Integer pageSize;

	@Override
	public List<SearchItem> gathData() {

		List<SearchItem> list = mapper.gathData();

		return list;
	}

	@Override
	public List<SolrInputDocument> getDocuments(List<SearchItem> items) {

		List<SolrInputDocument> docs = new ArrayList<>();

		SolrInputDocument doc = null;
		for (SearchItem item : items) {

			// solr的域，必须先定义后使用
			doc = new SolrInputDocument();
			doc.addField("id", item.getId());
			doc.addField("item_title", item.getTitle());
			doc.addField("item_sell_point", item.getSellPoint());
			doc.addField("item_price", item.getPrice());
			doc.addField("item_image", item.getImage());
			doc.addField("item_category_name", item.getCategoryName());

			docs.add(doc);
		}
		return docs;
	}

	@Override
	public EgoResult addDocuments(List<SolrInputDocument> docs) {

		try {
			solrServer.add(docs);
			solrServer.commit();
			return EgoResult.ok();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return EgoResult.build(400, "创建索引库失败");
		}

	}

	@Override
	public SearchResult doSearch(String keyword, String categoryName, String price, int page, Integer sort) {

		SearchResult result = new SearchResult();
		// 1.创建查询对象
		SolrQuery query = getSolrQuery(keyword, categoryName, price, page, sort);

		try {
			// 2.执行搜索
			QueryResponse response = solrServer.query(query);

			// 3.解析查询结果
			if (0 == response.getStatus()) {

				SolrDocumentList documentList = response.getResults();

				// 获取商品总数量
				long numFound = documentList.getNumFound();
				result.setRecordCount(numFound);
				// 获取高亮设置之后的的高亮域的值
				Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

				// 获取商品列表
				List<SearchItem> items = new ArrayList<>();
				SearchItem item = null;
				for (SolrDocument doc : documentList) {
					item = new SearchItem();
					String id = (String) doc.get("id");

					// 由于开启了高亮设置，所有获取商品名称的时候，优先从高亮域中获取
					String name = "";

					boolean flag = true;
					if (null != highlighting && highlighting.size() > 0) {
						Map<String, List<String>> map = highlighting.get(id);
						if (null != map && map.size() > 0) {
							List<String> list = map.get("item_title");
							if (null != list && list.size() > 0) {
								name = list.get(0);
								flag = false;
							}
						}
					}
					// 如果从高亮域中没有获取到商品的名称，则从文档中获取
					if (flag) {

						name = (String) doc.get("item_title");
					}
					String sellPoint = (String) doc.get("item_sell_point");
					long itemPrice = (long) doc.get("item_price");
					String image = (String) doc.get("item_image");
					String catName = (String) doc.get("item_category_name");

					item.setId(Long.valueOf(id));
					item.setCategoryName(catName);
					item.setImage(image);
					item.setPrice(itemPrice);
					item.setSellPoint(sellPoint);
					item.setTitle(name);

					items.add(item);
				}

				result.setItemList(items);

				result.setCurPage(page);

				result.setTotalPages((int) Math.ceil(result.getRecordCount() * 1.0 / pageSize));

			} else {
				return result;
			}

		} catch (

		SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据条件构建SolrQuery查询对象
	 * 
	 * @param keyword
	 * @param categoryName
	 * @param price
	 * @param page
	 * @param sort
	 * @return
	 */
	private SolrQuery getSolrQuery(String keyword, String categoryName, String price, int page, Integer sort) {

		SolrQuery query = new SolrQuery();

		// 设置查询关键字
		/*
		 * if (null==keyword&&"".equals(keyword)) { }
		 */
		if (StringUtils.isBlank(keyword)) {
			query.add("q", "*:*");
		} else {
			query.setQuery("item_title:" + keyword);
			// 设置高亮
			query.setHighlight(true);
			query.setHighlightSimplePre("<font style='color:red'>");
			query.setHighlightSimplePost("</font>");

			query.addHighlightField("item_title");
		}

		// 判断是否有商品类别过滤
		if (null != categoryName && !"".equals(categoryName)) {
			query.addFilterQuery("item_category_name:" + categoryName);
		}

		// 判断是否需要价格过滤
		if (StringUtils.isNotBlank(price)) {
			String[] split = price.split("-");

			// 两个数
			if (split.length > 1) {
				query.addFilterQuery("item_price:[" + split[0] + " TO" + split[1] + "]");
			} else {
				query.addFilterQuery("item_price:[" + split[0] + " TO *]");
			}
		}

		// 设置分页
		query.set("start", (page - 1) * pageSize);
		query.set("rows", pageSize);

		// 判断是否按照价格排序
		if (null != sort) {
			if (0 == sort) {
				query.set("sort", "item_price asc");
			} else {
				query.set("sort", "item_price desc");
			}
		}
		return query;
	}

}
