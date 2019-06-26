package com.xkt.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xkt.base.mapper.ItemCatMapper;
import com.xkt.base.pojo.ItemCat;
import com.xkt.base.vo.EUTreeNode;
import com.xkt.manager.service.ItemCatService;
@Service
public class ItemCatServiveImpl implements ItemCatService {

	@Autowired
	private ItemCatMapper mapper;

	@Override
	public List<EUTreeNode> getNodeByParentId(Long parent_id) {

		// 根据parent_id查询出类目是父目录的结果
		EntityWrapper<ItemCat> wrapper = new EntityWrapper<>();
		wrapper.eq("parent_id", parent_id);
		List<ItemCat> list = mapper.selectList(wrapper);

		// 装入List<EUTreeNode>对象中
		List<EUTreeNode> nodes = new ArrayList<>();

		EUTreeNode node = null;
		for (ItemCat itemCat : list) {
			node = new EUTreeNode();

			// 该类目是否为父类目，1为true，0为false'
			// open表示叶子结点，closed表示目录
			if (0 == itemCat.getIsParent()) {
				node.setState("open");
			} else {
				node.setState("closed");
			}
			node.setId(itemCat.getId());
			node.setText(itemCat.getName());
			
			nodes.add(node);
		}

		return nodes;
	}

}
