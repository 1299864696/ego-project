package com.xkt.portal.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xkt.base.pojo.Item;
import com.xkt.base.utils.HttpClientUtils;
import com.xkt.base.utils.JsonUtils;
import com.xkt.base.vo.ItemCart;
import com.xkt.portal.service.ItemCartService;

@Service
public class ItemCartServiceImpl implements ItemCartService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;

	@Value("${EGO_CART}")
	private String EGO_CART;

	@Override
	public List<ItemCart> add(Long id, Integer num, HttpServletRequest request, HttpServletResponse response) {
		/*
		 * 1、获取购物车 本质：List集合，转json格式 (1)、优先从cookie中获取 (2)、如果cookie中没有，则创建一个购物车
		 * 
		 * 2、添加商品到购物车中。 （1）如果购物车中已经存在该商品，则修改该商品的数量即可。
		 * 
		 * （2）如果购物车没有该商品，则直接添加
		 * 
		 * 3、将更新后的购物车重写写入cookie中
		 * 
		 * 
		 * 问题1：cookie只能保存String，购物车如何保存？ 答：转成json
		 * 
		 * 问题2：cookie不支持中文，怎么解决？ 答：（1）将中文写入cookie的时候，对中文进行编码设置
		 * （2）从cookie中取出中文时，对中文进行解码设置。
		 */
		List<ItemCart> cart = getCart(request);

		/*
		 * 2.将商品添加到购物车 先查询购物车中是否有该商品， 若有则增加数量，没有则创建
		 */
		boolean flag = true;
		for (ItemCart itemCart : cart) {
			String itemId = itemCart.getId();
			// 购物车已有该商品
			if (StringUtils.isNoneBlank(itemId)) {
				if (itemId.equals(id)) {
					itemCart.setNum(itemCart.getNum() + num);// 存在商品，添加数量即可
					flag = false;
					break;
				}
			}
		}

		// 购物车中没有该商品
		if (flag) {
			// 查询出该商品有关信息封装在itemCart再加入到list中
			ItemCart itemCart = new ItemCart();

			String jsonData = HttpClientUtils.doGet(REST_BASE_URL + "/item/" + id);
			Item item = JsonUtils.jsonToPojo(jsonData, Item.class);

			itemCart.setId(item.getId().toString());
			itemCart.setImage(item.getImage());
			itemCart.setNum(num);
			itemCart.setPrice(item.getPrice());
			itemCart.setTitle(item.getTitle());

			cart.add(itemCart);
		}

		// 3.将更新后的商品写入cookie中
		try {
			Cookie cookie = new Cookie(EGO_CART, URLEncoder.encode(JsonUtils.objectToJson(cart), "utf-8"));
			// 暴露cookie，所有人可以访问
			// 这种方式不安全，我们需要通过domain属性来设置一个域名保护
			cookie.setDomain("localhost");
			cookie.setPath("/");
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		/*
		 * 域名保护 setDomain 首先要明确的一个点是：cookie是不安全的。
		 * 
		 * 我们设置域名的目的，就是保护cookie不被非法访问。
		 * 
		 * 域名规则： 一级域名： www.ego.com .ego.com 二级域名： image.ego.com manager.ego.com
		 * 
		 * 设置域名保护的时候，只需要将cookie的域名设置为一级域名，那么该域名下所有的子系统就都可以访问 比如通用的格式： .ego.com ego.com
		 * 
		 */

		return cart;
	}

	/**
	 * 获取购物车
	 * 
	 * @param request
	 * @return
	 */
	private List<ItemCart> getCart(HttpServletRequest request) {

		List<ItemCart> cart = null;
		/*
		 * 在所有的cookies中判断，有没有购物车的cookie,如果有则从cookie中取出，若没有则创建
		 */
		boolean flag = true;

		Cookie[] cookies = request.getCookies();
		if (null != cookies && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				// 根据cookie的名称来判断，我们给购物车的cookie取个常量名
				if (EGO_CART.equals(name)) {
					String jsonData = cookie.getValue();
					try {
						cart = JsonUtils.jsonToList(URLDecoder.decode(jsonData, "utf-8"), ItemCart.class);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						flag = false;
						break;
					}
				}

			}
		}

		// 如果cookie没有添加过购物车，则直接创建一个购物车，即new一个list集合
		if (flag) {
			cart = new ArrayList<>();
		}

		return cart;
	}

	@Override
	public List<ItemCart> update(Long id, Integer num, HttpServletRequest request, HttpServletResponse response) {
		//1.获取购物车
		List<ItemCart> cart = getCart(request);
		//2.更新数量
		for (ItemCart item : cart) {
			if (item.getId().equals(id)) {
				item.setNum(num);
				break;
			}
		}
		
		//3.将更新后的购物车重新放到cookie中
		try {
			Cookie cookie = new Cookie(EGO_CART,URLEncoder.encode(JsonUtils.objectToJson(cart), "utf-8"));
			
			cookie.setDomain("localhost");
			cookie.setPath("/");
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return cart;

	}

	@Override
	public List<ItemCart> show(HttpServletRequest request) {
		
		List<ItemCart> cart = getCart(request);
		
		return cart;
	}

	@Override
	public List<ItemCart> delete(Long id, HttpServletRequest request, HttpServletResponse response) {
		//1.获取购物车
		List<ItemCart> cart = getCart(request);
		//2.删除商品
		for (ItemCart item : cart) {
			if (item.getId().equals(id)) {
				cart.remove(item);
				break;
			}
		}
		
		//3.将更新后的购物车重新放到cookie中
		try {
			Cookie cookie = new Cookie(EGO_CART,URLEncoder.encode(JsonUtils.objectToJson(cart), "utf-8"));
			
			cookie.setDomain("localhost");
			cookie.setPath("/");
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return cart;
	}

}
