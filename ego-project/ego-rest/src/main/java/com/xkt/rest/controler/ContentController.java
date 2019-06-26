package com.xkt.rest.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xkt.base.vo.EgoResult;
import com.xkt.rest.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService service;

	@RequestMapping("/category/{cid}")
	@ResponseBody
	public EgoResult getContentByCatId(@PathVariable("cid")Long catId) {

		EgoResult result = service.getContentByCatId(catId);

		return result;
	}
}
