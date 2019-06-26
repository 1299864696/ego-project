package com.xkt.manager.service;

import com.xkt.base.vo.EUDataGridResult;
import com.xkt.base.vo.EgoResult;

public interface ItemParamService {
	
	/**
	 * 查询规格参数列表，并分页
	 * @param page
	 * @param rows
	 * @return
	 */
	EUDataGridResult listAndPage(Integer page,Integer rows);
	
	/**
	 * 根据商品分类id查询规格模板
	 * @param CatId
	 * @return
	 */
	EgoResult getByCatId(Long catId);
	
	/**
	 * 保存规格参数模板
	 * @param catId
	 * @param paramDate
	 * @return
	 */
	EgoResult save(Long catId,String paramData);

}
