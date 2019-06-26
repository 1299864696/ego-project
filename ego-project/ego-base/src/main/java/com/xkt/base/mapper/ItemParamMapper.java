package com.xkt.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xkt.base.pojo.ItemParam;

public interface ItemParamMapper extends BaseMapper<ItemParam> {

	@Select("SELECT p.id,p.item_cat_id AS itemCatId,c.name AS ItemCatName,p.param_data AS paramData,p.created,p.updated "
			+ "FROM tb_item_param p LEFT JOIN tb_item_cat c ON p.id=c.name limit #{start},#{size}")
	List<Map<String, Object>> listAndPage(@Param("start")Integer start,@Param("size")Integer size);

}
