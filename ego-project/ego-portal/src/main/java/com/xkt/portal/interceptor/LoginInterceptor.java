package com.xkt.portal.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xkt.base.pojo.User;
import com.xkt.base.utils.HttpClientUtils;
import com.xkt.base.vo.EgoResult;

public class LoginInterceptor implements HandlerInterceptor {

	@Value("${Ego_TOKEN}")
	private String Ego_TOKEN;

	@Value("${SSO_BASE_URL}")
	private String SSO_BASE_URL;

	/*
	 * 进入controller之前执行
	 * 
	 * 常用来做同意的身份认证
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

		boolean flag = true;
		// 1.从cookie中获取token
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (Ego_TOKEN.equals(cookie.getName())) {
					String token = cookie.getValue();

					// 根据token到sso系统中获取用户信息
					String jsonData = HttpClientUtils.doGet(SSO_BASE_URL + "/token/" + token);
					if (StringUtils.isNoneBlank(jsonData)) {
						EgoResult result = EgoResult.formatToPojo(jsonData, User.class);

						if (200 == result.getStatus()) {
							User loginUser = (User) result.getData();
							request.setAttribute("loginUser", loginUser);
							flag = false;
							break;
						}

					}
				}
			}
		}
		if (flag) {
			/*
			 * flag为true,表示没有登录，要跳转到登录页面
			 * 
			 * 不能请求转发，请求转发只能在同一个项目中
			 * 
			 */
			response.sendRedirect(SSO_BASE_URL + "/showLogin");
			return false;
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
