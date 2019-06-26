package com.xkt.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xkt.base.utils.HttpClientUtils;
import com.xkt.base.utils.JsonUtils;
import com.xkt.base.vo.SearchResult;
import com.xkt.portal.service.SearchService;
@Service
public class SearchServiceImpl implements SearchService {

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;

	@Value("${SEARCH_DOSEARCH}")
	private String SEARCH_DO_SEARCH;

	@Override
	public SearchResult doSearch(String q, Integer page) {

		SearchResult searchResult = new SearchResult();

		Map<String, String> map = new HashMap<>();
		map.put("keyword", q);
		map.put("page", page.toString());

		String jsonData = HttpClientUtils.doGet(SEARCH_BASE_URL + SEARCH_DO_SEARCH, map);

		if (StringUtils.isNoneBlank(jsonData)) {

			searchResult = JsonUtils.jsonToPojo(jsonData, SearchResult.class);
		}
		return searchResult;
	}

}
