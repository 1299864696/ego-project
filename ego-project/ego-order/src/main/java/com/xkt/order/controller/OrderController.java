package com.xkt.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xkt.base.utils.JsonUtils;
import com.xkt.base.vo.OrderDetail;
import com.xkt.order.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService service;

	/**
	 * SpringMVC绑定json到pojo上，需要使用@RequestBody
	 * 
	 */
	@RequestMapping("/create")
	@ResponseBody
	public String save(String order) {

		OrderDetail orderDetail = JsonUtils.jsonToPojo(order, OrderDetail.class);
		String orderId = service.save(orderDetail, orderDetail.getOrderItems(), orderDetail.getOrderShipping());

		return orderId;
	}

}
