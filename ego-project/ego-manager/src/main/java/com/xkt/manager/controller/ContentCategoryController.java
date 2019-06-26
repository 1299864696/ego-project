package com.xkt.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xkt.base.vo.EUTreeNode;
import com.xkt.base.vo.EgoResult;
import com.xkt.manager.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService service;

	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getByParentId(@RequestParam(defaultValue = "0", value = "id") Long ParentId) {

		List<EUTreeNode> list = service.getByParentId(ParentId);

		return list;

	}

	@RequestMapping("/create")
	@ResponseBody
	public EgoResult save(Long parentId, String name) {

		EgoResult result = service.save(parentId, name);

		return result;

	}

}
