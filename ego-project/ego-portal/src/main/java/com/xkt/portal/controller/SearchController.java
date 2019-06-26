package com.xkt.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xkt.base.vo.SearchResult;
import com.xkt.portal.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService service;

	@RequestMapping("/search")
	public String doSearch(String q, @RequestParam(defaultValue = "1") Integer page, ModelMap map) {
		SearchResult result = service.doSearch(q, page);

		if (null != result) {

			map.addAttribute("totalPages", result.getTotalPages());
			map.addAttribute("query", q);
			map.addAttribute("itemList", result.getItemList());
			map.addAttribute("page", page);

			return "search";
		} else {

			// 统一异常处理：当请求异常时，将异常信息输出到一个公共的异常处理页面
			map.addAttribute("message", "服务器正在抵御外星人入侵，请稍后再试！");

			return "error/exception";
		}
	}
}
