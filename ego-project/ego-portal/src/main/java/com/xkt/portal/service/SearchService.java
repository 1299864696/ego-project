package com.xkt.portal.service;

import com.xkt.base.vo.SearchResult;

public interface SearchService {

	/**
	 * 执行搜索
	 * @param q
	 * @param page
	 * @return
	 */
	SearchResult doSearch(String q,Integer page);
}
