package com.xkt.base.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

public class User {

	@TableId(type=IdType.AUTO)
	private Long id;// BIGINT(20) NOT NULL AUTO_INCREMENT,
	private String username;// VARCHAR(50) NOT NULL COMMENT '用户名',
	private String password;// VARCHAR(32) NOT NULL COMMENT '密码，加密存储',
	private String phone;// VARCHAR(20) NULL DEFAULT NULL COMMENT '注册手机号',
	private String email;// VARCHAR(50) NULL DEFAULT NULL COMMENT '注册邮箱',
	private Date created;// DATETIME NOT NULL,
	private Date updated;// DATETIME NOT NULL,

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
