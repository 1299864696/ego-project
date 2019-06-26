package com.xkt.manager.service;

import java.util.List;

import com.xkt.base.vo.EUTreeNode;

public interface ItemCatService {
	
	/**
	  * 根据父目录的ID,获取子目录，并将结果转换为异步树的结点结构
	 * @param parent_id
	 * @return
	 */
	List<EUTreeNode> getNodeByParentId(Long parent_id);

}
