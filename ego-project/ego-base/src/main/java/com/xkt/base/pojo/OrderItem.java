package com.xkt.base.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("tb_order_item")
public class OrderItem {

	@TableId(type = IdType.INPUT)
	private String id;// VARCHAR(20) NOT NULL COLLATE 'utf8_bin',
	private String itemId;// VARCHAR(50) NOT NULL COMMENT '商品id' COLLATE 'utf8_bin',
	private String orderId;// VARCHAR(50) NOT NULL COMMENT '订单id' COLLATE 'utf8_bin',
	private Integer num;// INT(10) NULL DEFAULT NULL COMMENT '商品购买数量',
	private String title;// VARCHAR(200) NULL DEFAULT NULL COMMENT '商品标题' COLLATE 'utf8_bin',
	private Long price;// BIGINT(50) NULL DEFAULT NULL COMMENT '商品单价',
	private Long totalFee;// BIGINT(50) NULL DEFAULT NULL COMMENT '商品总金额',
	private String picPath;// VARCHAR(200) NULL DEFAULT NULL COMMENT '商品图片地址' COLLATE 'utf8_bin'

	public OrderItem() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Long totalFee) {
		this.totalFee = totalFee;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

}
