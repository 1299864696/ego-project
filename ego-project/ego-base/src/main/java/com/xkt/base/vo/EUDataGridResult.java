package com.xkt.base.vo;

import java.util.List;

/**
 * 自定义easyui-datagrid插件返回值类型
 * @author lzx
 *
 */
public class EUDataGridResult {

	private long total;
	
	private List<?> rows;

	public EUDataGridResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
