package com.xkt.base.vo;

import java.util.List;

import com.xkt.base.pojo.Order;
import com.xkt.base.pojo.OrderItem;
import com.xkt.base.pojo.OrderShipping;

/**
 * 自定义orderDetail详情类型，继承order类即可拥有order类的全部属性，从而可一次能够表单中接收与订单有关的字段
 * 
 * @author Administrator
 *
 */
public class OrderDetail extends Order {

	// springmvc绑定集合类型时，不能直接绑定，必须将集合定义到一个javaBean中
	private List<OrderItem> orderItems;

	private OrderShipping orderShipping;

	public OrderDetail() {
		super();
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderShipping getOrderShipping() {
		return orderShipping;
	}

	public void setOrderShipping(OrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}

}
