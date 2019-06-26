package com.xkt.portal.service;

import com.xkt.base.pojo.Item;

public interface ItemService {
	
	/**
	 * 根据ID查询商品信息
	 * @param id
	 * @return
	 */
	Item getById(Long id);

}
