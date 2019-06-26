package com.xkt.portal.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xkt.base.pojo.User;
import com.xkt.base.vo.ItemCart;
import com.xkt.base.vo.OrderDetail;
import com.xkt.portal.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService service;

	@RequestMapping("/order-cart")
	public String getItemFormCart(HttpServletRequest request) {

		// User loginUser = (User)request.getAttribute("loginUser");
		// request.setAttribute("loginUser", loginUser);
		List<ItemCart> cart = service.getItemFormCart(request);

		request.setAttribute("cartList", cart);

		return "order-cart";

	}

	@RequestMapping("/create")
	public String save(OrderDetail order, HttpServletRequest request) {

		User user = (User) request.getAttribute("loginUser");
		if (user != null) {

			order.setUserId(user.getId() + "");
		}

		String orderId = service.save(order);

		if (StringUtils.isNoneBlank(orderId)) {

			request.setAttribute("orderId", orderId);
			request.setAttribute("payment", order.getPayment());

			// 预计三天后的时间
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/E");
			calendar.add(Calendar.DATE, 3);

			request.setAttribute("date", df.format(calendar.getTime()));

			return "success";
		} else {
			request.setAttribute("message", "订单提交失败，请稍后再试，或者联系客服");
			return "/error/exception";
		}
	}

}
