package com.xkt.sso.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xkt.base.mapper.UserMapper;
import com.xkt.base.pojo.User;
import com.xkt.base.utils.JsonUtils;
import com.xkt.base.vo.EgoResult;
import com.xkt.sso.service.UserService;

import redis.clients.jedis.JedisCluster;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	@Value("${Ego_TOKEN}")
	private String Ego_TOKEN;

	@Autowired
	private JedisCluster jedisCluster;

	@Override
	public EgoResult check(String param, Integer type) {
		Map<String, Object> map = new HashMap<>();

		if (param != null) {
			if (1 == type) {
				map.put("username", param);
			} else if (2 == type) {
				map.put("phone", param);
			} else if (3 == type) {
				map.put("email", param);
			} else {
				return EgoResult.build(400, "待校验的数据类型有误");
			}
		} else {
			return EgoResult.build(400, "待校验的数据不能为空");
		}

		List<User> list = mapper.selectByMap(map);

		if (null != list && list.size() > 0) {
			return EgoResult.ok(false);
		}
		return EgoResult.ok(true);
	}

	@Override
	public EgoResult register(User user) {

		try {
			user.setCreated(new Date());
			user.setUpdated(user.getCreated());
			mapper.insert(user);

			return EgoResult.ok();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return EgoResult.build(400, "注册失败. 请校验数据后请再提交数据", null);
		}

	}

	@Override
	public EgoResult login(String username, String password, HttpServletResponse response) {

		// 1.查询用户信息
		EntityWrapper<User> wrapper = new EntityWrapper<>();

		wrapper.eq("username", username);
		wrapper.eq("password", password);

		List<User> list = mapper.selectList(wrapper);
		if (null != list && list.size() > 0) {
			/*
			 * 2. 根据登录用户，生成token将身份信息放入到cookie中
			 *
			 * token本质：用一个字符串，用来标记当前用户的登录，不允许包含任何用户信息
			 */
			String token = UUID.randomUUID().toString();
			Cookie cookie = new Cookie(Ego_TOKEN, token);
			// 没域名cookie.setDomain("localhost");
			cookie.setPath("/");

			response.addCookie(cookie);

			/*
			 * 3.将用户信息与token关联起来 token作为key，登录用户作为value写入到redis中，让token与用户产生关联
			 * 使用Redis能用hash尽量用hash，但hash不能单独对某一个field设置过期 这里用户登录要设置过期时间，默认定位30min
			 */
			User user = list.get(0);
			user.setPassword("");// 将存储在redis中的密码设置为空

			jedisCluster.set("EGO_USER:" + token, JsonUtils.objectToJson(user));
			jedisCluster.expire("EGO_USER:" + token, 1800);// 设置登录用户时间为30min

			return EgoResult.ok(token);
		} else {
			return EgoResult.build(400, "用户民或者密码错误，请重新输入");
		}

	}

	@Override
	public EgoResult check(String token) {
		if (StringUtils.isNoneBlank(token)) {
			String jsonData = jedisCluster.get("EGO_USER:" + token);
			if (StringUtils.isNoneBlank(jsonData)) {

				User user = JsonUtils.jsonToPojo(jsonData, User.class);

				// 重置时间
				jedisCluster.expire("EGO_USER" + token, 1800);
				return EgoResult.ok(user);
			}
		}
		return EgoResult.build(400, "当前用户未登录或登录过期");
	}
}
