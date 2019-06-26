package com.xkt.base.vo;

/**
 * 自定义异步树节点结构
 * @author lzx
 *
 */
public class EUTreeNode {

	private long id;

	private String text;

	private String state;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
