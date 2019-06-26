package com.xkt.search.service;

import java.util.List;

import org.apache.solr.common.SolrInputDocument;

import com.xkt.base.vo.EgoResult;
import com.xkt.base.vo.SearchItem;
import com.xkt.base.vo.SearchResult;

public interface SearchService {

	/**
	 * 采集数据
	 * 
	 * @return
	 */
	List<SearchItem> gathData();

	/**
	 * 将采集的数据转换成文档
	 * 
	 * @param items
	 * @return
	 */
	List<SolrInputDocument> getDocuments(List<SearchItem> items);

	/**
	 * 将文档写入索引库
	 * @param docs
	 * @return
	 */
	EgoResult addDocuments(List<SolrInputDocument> docs);
	
	/**
	 * 根据搜索条件搜索商品
	 * @param keyword
	 * @param categoryName
	 * @param price
	 * @param page
	 * @param sort
	 * @return
	 */
	SearchResult doSearch(String keyword,String categoryName,String price,int page,Integer sort);;

}
