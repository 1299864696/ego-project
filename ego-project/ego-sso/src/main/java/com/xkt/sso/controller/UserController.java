package com.xkt.sso.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xkt.base.pojo.User;
import com.xkt.base.utils.JsonUtils;
import com.xkt.base.vo.EgoResult;
import com.xkt.sso.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;

	@RequestMapping("/showLogin")
	public String showLogin() {

		return "login";
	}

	@RequestMapping("/showRegister")
	public String showRegister() {

		return "register";
	}

	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object check(@PathVariable("param") String param, @PathVariable("type") Integer type, String callback) {

		EgoResult result = service.check(param, type);

		if (StringUtils.isNoneBlank(callback)) {

			String jsonData = JsonUtils.objectToJson(result);

			String jsData = callback + "(" + jsonData + ")";

			return jsData;
		} else {

			return result;
		}

	}

	@RequestMapping("/register")
	@ResponseBody
	public EgoResult register(User user) {

		EgoResult result = service.register(user);

		return result;
	}

	@RequestMapping("/login")
	@ResponseBody
	public EgoResult login(String username, String password, HttpServletResponse response) {

		EgoResult result = service.login(username, password, response);

		return result;
	}

	@RequestMapping("/token/{token}")
	@ResponseBody
	public Object ckeck(@PathVariable("token") String token, String callback) {

		EgoResult result = service.check(token);

		if (StringUtils.isNoneBlank(callback)) {

			String jsonData = JsonUtils.objectToJson(result);
			String jsData = callback + "(" + jsonData + ")";

			return jsData;
		}

		return result;
	}

}
