package com.xkt.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xkt.base.vo.EgoResult;
import com.xkt.base.vo.SearchResult;
import com.xkt.search.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;

	@RequestMapping("/import/all")
	@ResponseBody
	public EgoResult createIndex() {

		EgoResult result = searchService.addDocuments(searchService.getDocuments(searchService.gathData()));

		return result;

	}   
	
	@RequestMapping(value="/doSearch",method=RequestMethod.GET)
	@ResponseBody
	public SearchResult doSearch(String keyword, String categoryName, String price, @RequestParam(defaultValue="1")Integer page, Integer sort) {
		
		SearchResult result = searchService.doSearch(keyword, categoryName, price, page, sort);
		
		return result;
	}
}
