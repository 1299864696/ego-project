package com.xkt.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xkt.base.vo.ItemCart;

/**
 * @author Administrator
 *
 */
public interface ItemCartService {

	/**
	 * 添加商品到购物车
	 * 
	 * 购物车的本质就是List集合，可以转为json数据存到cookie中
	 * 
	 * @param id
	 * @param num
	 * @param request
	 * @param response
	 * @return
	 */
	List<ItemCart> add(Long id, Integer num, HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 修改购物车商品数量
	 * @param id
	 * @param num
	 * @param request
	 * @param response
	 * @return
	 */
	List<ItemCart> update(Long id, Integer num, HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 点击我的购物车展示购物信息
	 * @param request
	 * @return
	 */
	List<ItemCart> show(HttpServletRequest request);
	
	/**
	 * 根据与ID删除商品
	 * @param id
	 * @param num
	 * @param request
	 * @param response
	 * @return
	 */
	List<ItemCart> delete(Long id, HttpServletRequest request, HttpServletResponse response);

}
