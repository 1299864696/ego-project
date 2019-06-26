package com.xkt.rest.service;

import com.xkt.base.pojo.Item;

public interface ItemService {

	/**
	 * 通过ID查询商品
	 * @param id
	 * @return
	 */
	Item getById(Long id);
}
