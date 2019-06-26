package com.xkt.rest.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xkt.base.pojo.Item;
import com.xkt.rest.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService service;

	@RequestMapping("/{itemId}")
	@ResponseBody
	public Item getById(@PathVariable("itemId") Long id) {

		Item item = service.getById(id);

		return item;
	}
}
