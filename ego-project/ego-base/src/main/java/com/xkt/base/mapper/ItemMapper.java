package com.xkt.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xkt.base.pojo.Item;
import com.xkt.base.vo.SearchItem;

public interface ItemMapper extends BaseMapper<Item>{

	@Select("select t.id,t.title,t.sell_point as sellPoint,t.image,t.price,c.name as categoryName "
			+ "from tb_item t left join tb_content_category c on t.id=c.id")
	List<SearchItem> gathData();
	
	@Select("SELECT t.id AS id,t.title,t.sell_point AS sellPoint,t.price,t.num,t.barcode,t.image,t.cid,t.`status`,t.created, t.updated,tc.name\" \r\n" + 
			"    +\" FROM tb_item t left join tb_item_cat tc on t.cid = tc.id limit #{start},#{rows}")
	List<Item> listAndPage(@Param(value="start")Integer start ,@Param(value="rows")Integer rows);
}
