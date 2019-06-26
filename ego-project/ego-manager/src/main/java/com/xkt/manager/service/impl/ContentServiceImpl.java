package com.xkt.manager.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xkt.base.mapper.ContentMapper;
import com.xkt.base.pojo.Content;
import com.xkt.base.vo.EUDataGridResult;
import com.xkt.base.vo.EgoResult;
import com.xkt.manager.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentMapper mapper;

	@Override
	public EUDataGridResult listByCatIdAndPage(Long categoryId, int page, int rows) {

		EUDataGridResult result = new EUDataGridResult();

		RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);

		EntityWrapper<Content> wrapper = new EntityWrapper<>();
		wrapper.eq("category_id", categoryId);

		List<Content> contents = mapper.selectPage(rowBounds, wrapper);
		Integer count = mapper.selectCount(wrapper);

		result.setRows(contents);
		result.setTotal(count);

		return result;
	}

	@Override
	public EgoResult deleteByIds(Integer[] ids) {

		mapper.deleteBatchIds(Arrays.asList(ids));

		// 删除商品后需要清空掉缓存里面的数据
		//需要完成：httpClientUtils.doGet("http://localhost:8081/rest/cache/clear/cid");
		//cluster.hdel(String key,String... field)
		return EgoResult.ok();
	}

	@Override
	public EgoResult save(Content content) {

		content.setCreated(new Date());
		content.setUpdated(content.getCreated());

		mapper.insert(content);

		return EgoResult.ok();
	}

}
