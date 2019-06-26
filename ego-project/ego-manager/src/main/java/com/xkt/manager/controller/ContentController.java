package com.xkt.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xkt.base.pojo.Content;
import com.xkt.base.vo.EUDataGridResult;
import com.xkt.base.vo.EgoResult;
import com.xkt.manager.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService service;

	@RequestMapping("/query/list")
	@ResponseBody
	public EUDataGridResult listByCatIdAndPage(Long categoryId, int page, int rows) {

		EUDataGridResult result = service.listByCatIdAndPage(categoryId, page, rows);

		return result;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public EgoResult deleteByIds(Integer[] ids) {

		EgoResult result = service.deleteByIds(ids);

		return result;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public EgoResult save(Content content) {
		
		EgoResult result = service.save(content);
		
		return result;
	}

}
