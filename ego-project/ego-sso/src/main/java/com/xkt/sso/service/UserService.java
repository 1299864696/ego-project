package com.xkt.sso.service;

import javax.servlet.http.HttpServletResponse;

import com.xkt.base.pojo.User;
import com.xkt.base.vo.EgoResult;

public interface UserService {

	/**
	 * 根据不同参数校验数据
	 * 
	 * @param param
	 * @param type
	 * @return
	 */
	EgoResult check(String param, Integer type);

	/**
	 * 用户注册
	 * 
	 * @param username
	 * @param password
	 * @param phone
	 * @param email
	 * @return
	 */
	EgoResult register(User user);

	/**
	 * 用户登录操作
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	EgoResult login(String username, String password, HttpServletResponse response);

	/**
	 * 校验用户是否登陆
	 *  token为用户的身份令牌
	 * @param token
	 * @return
	 */
	EgoResult check(String token);

}