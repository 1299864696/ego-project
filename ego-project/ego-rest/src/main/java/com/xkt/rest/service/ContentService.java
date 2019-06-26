package com.xkt.rest.service;

import com.xkt.base.vo.EgoResult;

public interface ContentService {
	
	/**
	 * 根据内容分类id查询分类列表
	 * @param catId
	 * @return
	 */
	EgoResult getContentByCatId(Long catId);

}
