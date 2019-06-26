package com.xkt.manager.service;

import java.util.List;

import com.xkt.base.vo.EUTreeNode;
import com.xkt.base.vo.EgoResult;

public interface ContentCategoryService {

	/**
	 * 根据父类目id查询子类目
	 * @param ParentID
	 * @return
	 */
	List<EUTreeNode> getByParentId(Long ParentID);
	
	/**
	 * 新增子类目
	 * @return
	 */
	EgoResult save(Long parentId,String name);
}
