package com.xkt.portal.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xkt.base.pojo.Item;
import com.xkt.base.utils.HttpClientUtils;
import com.xkt.base.utils.JsonUtils;
import com.xkt.portal.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;

	@Override
	public Item getById(Long id) {
		
		//http://localhost:8081/rest/item/155801444435607
		String jsonData = HttpClientUtils.doGet(REST_BASE_URL + "/item/" + id);

		if (StringUtils.isNoneBlank(jsonData)) {
			Item item = JsonUtils.jsonToPojo(jsonData, Item.class);
			return item;
		} else {
			return null;
		}

	}

}
