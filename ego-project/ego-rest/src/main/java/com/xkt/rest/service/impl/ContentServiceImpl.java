package com.xkt.rest.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xkt.base.mapper.ContentMapper;
import com.xkt.base.pojo.Content;
import com.xkt.base.utils.JsonUtils;
import com.xkt.base.vo.EgoResult;
import com.xkt.rest.service.ContentService;

import redis.clients.jedis.JedisCluster;

@Service
public class ContentServiceImpl implements ContentService {

	/**
	 * 缓存逻辑： （1）优先查找缓存，如果缓存中有数据，则直接返回结果，不需要查数据库。
	 * 
	 * （2）如果缓存中没有数据，则查询数据库，将数据放入缓存中。
	 * 
	 * （3）如果连接缓存异常，则查询数据库。
	 * 
	 * 
	 * 我们给首页的内容添加缓存的时候需要考虑的点 （1）选用哪种数据结构类型
	 * 
	 * 我们考虑使用redis的时候，能使用hash尽量使用hash。
	 * 
	 * 那我们缓存内容的时候，完全可以使用hash，格式 EGO_CONTENT:{categoryId:内容列表}
	 * 
	 * 
	 * （2）数据用什么格式存、取 我们使用hash的时候，只能存String字符串，所以需要将被缓存的对象，转成json格式。 取出来之后，再转成pojo
	 * 
	 */

	@Autowired
	private ContentMapper mapper;

	@Autowired
	private JedisCluster cluster;

	@Value("EGO_CONTENT")
	private String EGO_CONTENT;

	@Override
	public EgoResult getContentByCatId(Long catId) {

		try {
			String jsonData = cluster.hget(EGO_CONTENT, catId + "");
			if (null != jsonData && !"".equals(jsonData)) {

				List<Content> contents = JsonUtils.jsonToList(jsonData, Content.class);

				return EgoResult.ok(contents);
			} else {

				Map<String, Object> map = new HashMap<>();
				map.put("category_id", catId);

				List<Content> list = mapper.selectByMap(map);

				cluster.hset(EGO_CONTENT, catId + "", JsonUtils.objectToJson(list));

				return EgoResult.ok(list);
			}
		} catch (Exception e) {
			e.printStackTrace();

			Map<String, Object> map = new HashMap<>();
			map.put("category_id", catId);

			List<Content> list = mapper.selectByMap(map);

			return EgoResult.ok(list);
		}

	}

}
