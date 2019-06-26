package com.xkt.portal.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xkt.base.utils.HttpClientUtils;
import com.xkt.base.utils.JsonUtils;
import com.xkt.base.vo.ItemCart;
import com.xkt.base.vo.OrderDetail;
import com.xkt.portal.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Value("${EGO_CART}")
	private String EGO_CART;

	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;

	@Override
	public List<ItemCart> getItemFormCart(HttpServletRequest request) {
		List<ItemCart> cart = getCart(request);
		return cart;
	}

	private List<ItemCart> getCart(HttpServletRequest request) {
		List<ItemCart> cart = null;
		/*
		 * 在所有的cookies中判断，有没有购物车的cookie,如果有则从cookie中取出，若没有则创建
		 */
		boolean flag = true;

		Cookie[] cookies = request.getCookies();
		if (null != cookies && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				// 根据cookie的名称来判断，我们给购物车的cookie取个常量名
				if (EGO_CART.equals(name)) {
					String jsonData = cookie.getValue();
					try {
						cart = JsonUtils.jsonToList(URLDecoder.decode(jsonData, "utf-8"), ItemCart.class);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						flag = false;
						break;
					}
				}

			}
		}

		// 如果cookie没有添加过购物车，则直接创建一个购物车，即new一个list集合
		if (flag) {
			cart = new ArrayList<>();
		}

		return cart;
	}

	@Override
	public String save(OrderDetail order) {

		Map<String, String> params = new HashMap<>();
		params.put("order", JsonUtils.objectToJson(order));

		String orderId = HttpClientUtils.doGet(ORDER_BASE_URL + "/create", params);

		return orderId;
	}

}
