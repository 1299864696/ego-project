package com.xkt.rest.controler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class OrderController {
	
	@RequestMapping(value="/order/list",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	public String order() {

		return "商品订购成功";
	}

}
