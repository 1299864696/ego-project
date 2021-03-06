package com.xkt.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xkt.base.vo.EUTreeNode;
import com.xkt.manager.service.ItemCatService;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService catService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getByParentId(@RequestParam(name="id",defaultValue="0")Long parentId){
		
		List<EUTreeNode> nodes = catService.getNodeByParentId(parentId);
		
		return  nodes;
	}


}
