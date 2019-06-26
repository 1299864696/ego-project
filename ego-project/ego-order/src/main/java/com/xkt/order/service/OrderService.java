package com.xkt.order.service;

import java.util.List;

import com.xkt.base.pojo.Order;
import com.xkt.base.pojo.OrderItem;
import com.xkt.base.pojo.OrderShipping;

public interface OrderService {

	/**
	 * 保存订单
	 * @param order
	 * @param orderItems
	 * @param orderShipping
	 * @return
	 */
	String save(Order order,List<OrderItem> orderItems,OrderShipping orderShipping);
}
