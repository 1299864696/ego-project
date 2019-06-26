package com.xkt.manager.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xkt.base.mapper.ItemParamMapper;
import com.xkt.base.pojo.ItemParam;
import com.xkt.base.vo.EUDataGridResult;
import com.xkt.base.vo.EgoResult;
import com.xkt.manager.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private ItemParamMapper mapper;

	@Override
	public EUDataGridResult listAndPage(Integer page, Integer rows) {

		List<Map<String, Object>> list = mapper.listAndPage((page - 1) * rows, rows);

		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);

		Integer count = mapper.selectCount(null);
		result.setTotal(count);
		return result;
	}

	@Override
	public EgoResult getByCatId(Long catId) {

		EntityWrapper<ItemParam> wrapper = new EntityWrapper<>();
		wrapper.eq("item_cat_id", catId);

		List<ItemParam> list = mapper.selectList(wrapper);

		if (null != list && list.size() > 0) {
			return EgoResult.ok(list.get(0));
		} else {
			return EgoResult.build(404, "未添加该商品");
		}

	}

	@Override
	public EgoResult save(Long catId, String paramData) {

		ItemParam itemParam = new ItemParam();

		itemParam.setItemCatId(catId);
		itemParam.setParamData(paramData);
		itemParam.setCreated(new Date());
		itemParam.setUpdated(itemParam.getCreated());

		mapper.insert(itemParam);

		return EgoResult.ok();

	}

}
