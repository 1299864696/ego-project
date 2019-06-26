package com.xkt.rest.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xkt.base.pojo.Menu;
import com.xkt.base.utils.JsonUtils;
import com.xkt.rest.service.ItemCatService;

@RestController
public class ItemCatController {

	@Autowired
	private ItemCatService service;

	/**
	 * 支持jsonp异步请求
	 * 
	 * @return
	 */
	@RequestMapping(value = "/item/all", produces = "application/json;charset=utf-8")
	public Object initMenu(String callback) {

		Menu menu = service.initMenu();

		//当callback不为空的时候，说明是一个jsonp得跨越请求，需要用callback伪装js
		if (null != callback && !"".equals(callback)) {

			String jsonMenu = JsonUtils.objectToJson(menu);

			return callback + "(" + jsonMenu + ")";
		} else {
			return menu;
		}
	}

}
