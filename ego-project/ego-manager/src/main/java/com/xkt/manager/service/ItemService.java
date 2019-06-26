package com.xkt.manager.service;

import com.xkt.base.pojo.Item;
import com.xkt.base.vo.EUDataGridResult;
import com.xkt.base.vo.EgoResult;

public interface ItemService {

	/**
	 * 根据iD查询商品
	 * 
	 * @param id
	 * @return
	 */
	Item selectById(Long id);

	/**
	 * 保存商品
	 * @param item
	 * @param desc
	 * @param itemParams
	 * @return
	 */
	EgoResult save(Item item, String desc, String itemParams,String categoryName);

	/**
	 *查询商品类别，并分页
	 * @param page 页码
	 * @param rows 容量
	 * @return
	 */
	EUDataGridResult listAndPage(Integer page, Integer rows); }
