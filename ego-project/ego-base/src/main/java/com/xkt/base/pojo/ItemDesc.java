package com.xkt.base.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("tb_item_desc")
public class ItemDesc {

	@TableId(value="item_id",type = IdType.INPUT)
	private Long itemId;// BIGINT(20) NOT NULL COMMENT '商品ID',
	private String itemDesc;// TEXT NULL COMMENT '商品描述',
	private Date created;// DATETIME NULL DEFAULT NULL COMMENT '创建时间',
	private Date updated;// DATETIME NULL DEFAULT NULL COMMENT '更新时间',

	public ItemDesc() {
		super();
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
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
