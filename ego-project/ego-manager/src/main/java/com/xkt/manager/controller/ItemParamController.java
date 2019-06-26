package com.xkt.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xkt.base.vo.EUDataGridResult;
import com.xkt.base.vo.EgoResult;
import com.xkt.manager.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService service;

	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult listAndPage(Integer page, Integer rows) {

		EUDataGridResult result = service.listAndPage(page, rows);

		return result;

	}

	@RequestMapping("/query/itemcatid/{catId}")
	@ResponseBody
	public EgoResult getByCatId(@PathVariable("catId") Long catId) {

		EgoResult result = service.getByCatId(catId);

		return result;
	}

	@RequestMapping("/save/{catId}")
	public EgoResult save(@PathVariable("catId") Long catId, String paramData) {

		EgoResult result = service.save(catId, paramData);

		return result;
	}

}
