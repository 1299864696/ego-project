package com.xkt.base.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("tb_content_category")
public class ContentCategory {

	@TableId
	private Long id;// BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
	private Long parentId;// BIGINT(20) NULL DEFAULT NULL COMMENT '父类目ID=0时，代表的是一级的类目',
	private String name;// VARCHAR(50) NULL DEFAULT NULL COMMENT '分类名称',
	private int status;// INT(1) NULL DEFAULT '1' COMMENT '状态。可选值:1(正常),2(删除)',
	private int sortOrder;// INT(4) NULL DEFAULT NULL COMMENT
							// '排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
	private byte isParent;// TINYINT(1) NULL DEFAULT '1' COMMENT '该类目是否为父类目，1为true，0为false',
	private Date created;// DATETIME NULL DEFAULT NULL COMMENT '创建时间',
	private Date updated;// DATETIME NULL DEFAULT NULL COMMENT '创建时间',

	public ContentCategory() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public byte getIsParent() {
		return isParent;
	}

	public void setIsParent(byte isParent) {
		this.isParent = isParent;
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
