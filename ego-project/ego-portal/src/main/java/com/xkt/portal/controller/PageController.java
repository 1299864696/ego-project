package com.xkt.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xkt.portal.service.ADService;

@Controller
public class PageController {

	@Autowired
	private ADService service;

	@RequestMapping("/index")
	public String showIndex(ModelMap map) {

		String ads = service.getAD();
		map.addAttribute("ads", ads);

		return "index";
	}

}
