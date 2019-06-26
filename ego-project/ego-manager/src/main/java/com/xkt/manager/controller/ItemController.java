package com.xkt.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.xkt.base.pojo.Item;
import com.xkt.base.vo.EUDataGridResult;
import com.xkt.base.vo.EgoResult;
import com.xkt.manager.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService service;

	@ResponseBody
	@RequestMapping("/{itemId}")
	public Item selectById(@PathVariable("itemId") Long id) {

		Item item = service.selectById(id);

		return item;
	}

	@RequestMapping("/save")
	@ResponseBody
	public EgoResult save(Item item, String desc, String itemParams, String categoryName) {

		EgoResult result = service.save(item, desc, itemParams, categoryName);

		return result;
	}

	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult list(int page, int rows) {

		EUDataGridResult result = service.listAndPage(page, rows);

		return result;
	}

}
