package com.xkt.base.vo;

import java.util.List;

/**
 * 自定义搜索返回值类型
 * @author Administrator
 *
 */
public class SearchResult {

	private Long recordCount;
	private List<SearchItem> itemList;
	private Integer totalPages;
	private Integer curPage;

	public SearchResult() {
		super();
	}

	public Long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}

	public List<SearchItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

}
