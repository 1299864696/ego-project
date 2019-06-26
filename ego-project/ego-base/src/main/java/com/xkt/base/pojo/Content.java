package com.xkt.base.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
@TableName
public class Content {
	
	@TableId
	private Long id;// BIGINT(20) NOT NULL AUTO_INCREMENT,
	private Long categoryId;// BIGINT(20) NOT NULL COMMENT '内容类目ID',
	private String title;// VARCHAR(200) NULL DEFAULT NULL COMMENT '内容标题',
	private String subTitle;// VARCHAR(100) NULL DEFAULT NULL COMMENT '子标题',
	private String titleDesc;// VARCHAR(500) NULL DEFAULT NULL COMMENT '标题描述',
	private String url;// VARCHAR(500) NULL DEFAULT NULL COMMENT '链接',
	private String pic;// VARCHAR(300) NULL DEFAULT NULL COMMENT '图片绝对路径',
	private String pic2;// VARCHAR(300) NULL DEFAULT NULL COMMENT '图片2',
	private String content;// TEXT NULL COMMENT '内容',
	private Date created;// DATETIME NULL DEFAULT NULL,
	private Date updated;// DATETIME NULL DEFAULT NULL,

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getTitleDesc() {
		return titleDesc;
	}

	public void setTitleDesc(String titleDesc) {
		this.titleDesc = titleDesc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Content() {
		super();
	}
}
