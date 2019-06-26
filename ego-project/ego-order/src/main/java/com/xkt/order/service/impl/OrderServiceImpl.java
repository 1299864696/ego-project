package com.xkt.order.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xkt.base.mapper.OrderItemMapper;
import com.xkt.base.mapper.OrderMapper;
import com.xkt.base.mapper.OrderShippingMapper;
import com.xkt.base.pojo.Order;
import com.xkt.base.pojo.OrderItem;
import com.xkt.base.pojo.OrderShipping;
import com.xkt.base.utils.IDUtils;
import com.xkt.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderItemMapper orderItemMapper;

	@Autowired
	private OrderShippingMapper orderShippingMapper;

	@Override
	public String save(Order order, List<OrderItem> orderItems, OrderShipping orderShipping) {

		// 1.保存订单
		long orderId = IDUtils.genItemId();

		order.setOrderId(orderId + "");
		order.setStatus("1");
		order.setCreateTime(new Date());
		order.setUpdateTime(order.getCreateTime());
		orderMapper.insert(order);

		// 2.保存订单商品（一个订单中存在多个商品）
		for (OrderItem orderItem : orderItems) {

			orderItem.setId(IDUtils.genItemId() + "");
			orderItem.setItemId(orderId + "");

			orderItemMapper.insert(orderItem);
		}

		// 3.保存收件人信息
		orderShipping.setOrderId(orderId + "");
		orderShipping.setCreated(order.getCreateTime());
		orderShipping.setUpdated(order.getUpdateTime());

		orderShippingMapper.insert(orderShipping);

		return orderId + "";
	}

}
