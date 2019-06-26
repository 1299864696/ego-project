package com.xkt.base.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
@TableName(value="tb_item_param_item")
public class ItemParamItem {
	
	@TableId
	private Long id;// BIGINT(20) NOT NULL AUTO_INCREMENT,
	private Long itemId;// BIGINT(20) NULL DEFAULT NULL COMMENT '商品ID',
	private String paramData;// TEXT NULL COMMENT '参数数据，格式为json格式',
	private Date created;// DATETIME NULL DEFAULT NULL,
	private Date updated;// DATETIME NULL DEFAULT NULL,

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getParamData() {
		return paramData;
	}

	public void setParamData(String paramData) {
		this.paramData = paramData;
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

	public ItemParamItem() {
		super();
	}

}
