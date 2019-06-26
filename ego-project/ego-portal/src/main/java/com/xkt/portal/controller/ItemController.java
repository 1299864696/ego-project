package com.xkt.portal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xkt.base.pojo.Item;
import com.xkt.portal.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService service;

	@RequestMapping("/{itemId}")
	public String getById(@PathVariable("itemId") Long id, HttpServletRequest request) {

		Item item = service.getById(id);

		if (null != item) {
			request.setAttribute("item", item);
			return "item";
		} else {
			request.setAttribute("message", "外星人把服务器抢走了，地球卫士正在修复!");
			return "error/exception";
		}
	}
}
