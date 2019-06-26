package com.xkt.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xkt.base.mapper.ItemMapper;
import com.xkt.base.pojo.Item;
import com.xkt.rest.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper mapper;

	@Override
	public Item getById(Long id) {
		
		Item item = mapper.selectById(id);
		
		return item;
	}

	
}
