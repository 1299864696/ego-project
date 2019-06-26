package com.xkt.manager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xkt.base.mapper.ContentCategoryMapper;
import com.xkt.base.pojo.ContentCategory;
import com.xkt.base.vo.EUTreeNode;
import com.xkt.base.vo.EgoResult;
import com.xkt.manager.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private ContentCategoryMapper mapper;

	@Override
	public List<EUTreeNode> getByParentId(Long ParentId) {
		List<EUTreeNode> nodes = new ArrayList<>();

		EntityWrapper<ContentCategory> wrapper = new EntityWrapper<>();
		wrapper.eq("parent_id", ParentId);
		List<ContentCategory> list = mapper.selectList(wrapper);

		EUTreeNode node = null;

		for (ContentCategory c : list) {
			node = new EUTreeNode();

			node.setId(c.getId());
			node.setText(c.getName());
			node.setState(1 == c.getIsParent() ? "closed" : "open");
			nodes.add(node);
		}

		return nodes;
	}

	@SuppressWarnings("static-access")
	@Override
	public EgoResult save(Long parentId, String name) {

		ContentCategory contentCategory = new ContentCategory();
		contentCategory.setIsParent((byte) 0);
		contentCategory.setName(name);
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setStatus(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(contentCategory.getCreated());

		mapper.insert(contentCategory);

		// 在一个节点下增长了一个子目录，判断这个结点是否为父目录，若不是则这个结点要变为父目录

		ContentCategory parent = mapper.selectById(parentId);
		if (parent.getIsParent() == 0) {

			parent.setIsParent((byte) 1);
			mapper.updateById(parent);
		}

		EgoResult result = new EgoResult();

		return result.ok(contentCategory);
	}

}
