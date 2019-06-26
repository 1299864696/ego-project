package com.xkt.search.listener;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xkt.base.utils.JsonUtils;
import com.xkt.base.vo.SearchItem;

@Component
public class ItemListener implements MessageListener {

	@Autowired
	private SolrServer server;

	@Override
	public void onMessage(Message message) {

		if (null != message) {
			System.out.println("监听到消息");

			if (message instanceof MapMessage) {
				MapMessage mapMessage = (MapMessage) message;
				try {
					// 获取key的值
					String flag = mapMessage.getString("key");
					if ("add".equals(flag)) {
						String jsonItem = mapMessage.getString("value");
						SearchItem item = JsonUtils.jsonToPojo(jsonItem, SearchItem.class);

						// 将新增的商品加入索引库
						SolrInputDocument doc = new SolrInputDocument();
						doc.addField("id", item.getId());
						doc.addField("item_title", item.getTitle());
						doc.addField("item_category_name", item.getCategoryName());
						doc.addField("item_price", item.getPrice());
						doc.addField("item_sell_point", item.getSellPoint());
						doc.addField("item_image", item.getImage());

						server.add(doc);
						server.commit();
					} else if ("delete".equals(flag)) {
						// 删除商品,传入id即可
						// server.deleteById(id);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

}
