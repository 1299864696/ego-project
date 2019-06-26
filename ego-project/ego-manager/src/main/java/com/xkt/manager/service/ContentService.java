package com.xkt.manager.service;

import com.xkt.base.pojo.Content;
import com.xkt.base.vo.EUDataGridResult;
import com.xkt.base.vo.EgoResult;

public interface ContentService {

	/**
	 * 通过内容分类Id找出所属类别下的商品
	 * 
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	EUDataGridResult listByCatIdAndPage(Long categoryId, int page, int rows);
	
	/**
	 * 通过商品ID批量删除商品
	 * @param ids
	 * @return
	 */
	EgoResult deleteByIds(Integer[] ids);
	
	/**
	 * 添加商品保存信息
	 * @param content
	 * @return
	 */
	EgoResult save(Content content);

}
