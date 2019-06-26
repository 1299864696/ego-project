package com.xkt.base.pojo;

import java.util.List;

/**
 * 自定义导航栏菜单pojo
 * 
 * @author lzx
 *
 */
public class Menu {

	private List<?> data;//一级目录

	public Menu() {
		super();
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

}
