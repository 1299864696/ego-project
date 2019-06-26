package com.xkt.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xkt.base.mapper.ItemCatMapper;
import com.xkt.base.pojo.ItemCat;
import com.xkt.base.pojo.Menu;
import com.xkt.base.pojo.MenuNode;
import com.xkt.base.utils.JsonUtils;
import com.xkt.rest.service.ItemCatService;

import redis.clients.jedis.JedisCluster;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private ItemCatMapper mapper;

	@Autowired
	private JedisCluster cluster;

	@Value("MENU_KEY")
	private String MENU_KEY;
	

	@Override
	public Menu initMenu() {

		Menu menu = new Menu();

		List nodes = getNodesByParentId(0L);
		menu.setData(nodes);

		return menu;
	}

	private List getNodesByParentId(long parentId) {
		
		List<ItemCat> selectList;
		
		try {
			selectList = new ArrayList<>();
			String jsonData = cluster.hget(MENU_KEY, parentId + "");
			if (null != jsonData && !"".equals(jsonData)) {
				selectList = JsonUtils.jsonToList(jsonData, ItemCat.class);
			} else {

				EntityWrapper<ItemCat> wrapper = new EntityWrapper<>();
				wrapper.eq("parent_id", parentId);
				selectList = mapper.selectList(wrapper);

				cluster.hset(MENU_KEY, parentId + "", JsonUtils.objectToJson(selectList));
			} 
		} catch (Exception e) {
			e.printStackTrace();
			//连接集群异常直接查询数据库
			EntityWrapper<ItemCat> wrapper = new EntityWrapper<>();
			wrapper.eq("parent_id", parentId);
			selectList = mapper.selectList(wrapper);
		}
		List nodes = new ArrayList<>();

		MenuNode node = null;
		for (ItemCat itemCat : selectList) {
			if (1 == itemCat.getIsParent()) {
				node = new MenuNode();
				node.setU("/products/" + itemCat.getId() + ".html");// u : "/products/1.html"
				node.setN("<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");// n : "<a

				node.setI(getNodesByParentId(itemCat.getId()));

				nodes.add(node);
			} else {
				nodes.add("/products/" + itemCat.getId() + ".html|" + itemCat.getName());// [3] :
																							// "/products/6.html|多媒体图书"
			}
		}

		return nodes;
	}

}
