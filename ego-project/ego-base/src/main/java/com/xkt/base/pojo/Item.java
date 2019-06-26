package com.xkt.base.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName
public class Item {

	@TableId(value = "id", type = IdType.INPUT)
	private Long id; // bigint(20) NOT NULL COMMENT '商品id，同时也是商品编号',

	@TableField(value = "title") // 当pojo的属性名称和表的字段一致时，可以省略@TableField
	private String title; // varchar(100) NOT NULL COMMENT '商品标题',

	private String sellPoint; // varchar(500) DEFAULT NULL COMMENT '商品卖点',
	private Long price; // bigint(20) NOT NULL COMMENT '商品价格，单位为：分',
	private int num; // int(10) NOT NULL COMMENT '库存数量',
	private String barcode; // varchar(30) DEFAULT NULL COMMENT '商品条形码',
	private String image; // varchar(500) DEFAULT NULL COMMENT '商品图片',
	private Long cid; // bigint(10) NOT NULL COMMENT '所属类目，叶子类目',
	private byte status; // tinyint(4) NOT NULL DEFAULT '1' COMMENT '商品状态，1-正常，2-下架，3-删除',
	private Date created; // datetime NOT NULL COMMENT '创建时间',
	private Date updated; // datetime NOT NULL COMMENT '更新时间',

	@TableField(exist = false) // mybatis-plus中，对于类的属性，如果数据库没有对应字段，必须使用exit=false来标记
	private String images[];

	public String[] getImages() {
		if (null != image) {
			images = image.split(",");
		}
		return images;
	}

	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}
