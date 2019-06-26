package com.xkt.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.xkt.base.vo.ItemCart;
import com.xkt.base.vo.OrderDetail;

public interface OrderService {

	/**
	 * 从购物车获取商品
	 * @param request
	 * @return
	 */
	List<ItemCart> getItemFormCart(HttpServletRequest request);
	
	/**
	 * 保存订单
	 * @param order
	 * @return
	 */
	String save(OrderDetail order);

}
