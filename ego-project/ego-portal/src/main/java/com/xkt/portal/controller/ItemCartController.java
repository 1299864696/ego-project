package com.xkt.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xkt.base.vo.ItemCart;
import com.xkt.portal.service.ItemCartService;

@Controller
@RequestMapping("/cart")
public class ItemCartController {

	@Autowired
	private ItemCartService service;

	@RequestMapping("/add/{num}/{itemid}")
	public String add(@PathVariable("itemid") Long id, @PathVariable("num") Integer num, HttpServletRequest request,
			HttpServletResponse response) {

		List<ItemCart> cart = service.add(id, num, request, response);

		request.setAttribute("cartList", cart);

		return "cart";

	}

	@RequestMapping("/update/num/{id}/{num}")
	public String update(@PathVariable("id") Long id, @PathVariable("num") Integer num, HttpServletRequest request,
			HttpServletResponse response) {

		List<ItemCart> cart = service.update(id, num, request, response);

		request.setAttribute("cartList", cart);

		return "cart";
	}

	@RequestMapping("/cart")
	List<ItemCart> show(HttpServletRequest request) {

		List<ItemCart> cart = service.show(request);

		return cart;
	}

	@RequestMapping("/delete/{id}")
	public String update(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {

		List<ItemCart> cart = service.delete(id, request, response);

		request.setAttribute("cartList", cart);

		return "cart";
	}

}
