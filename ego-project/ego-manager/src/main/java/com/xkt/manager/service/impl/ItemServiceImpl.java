package com.xkt.manager.service.impl;

import java.util.Date;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.xkt.base.mapper.ItemDescMapper;
import com.xkt.base.mapper.ItemMapper;
import com.xkt.base.mapper.ItemParamItemMapper;
import com.xkt.base.pojo.Item;
import com.xkt.base.pojo.ItemDesc;
import com.xkt.base.pojo.ItemParamItem;
import com.xkt.base.utils.IDUtils;
import com.xkt.base.utils.JsonUtils;
import com.xkt.base.vo.EUDataGridResult;
import com.xkt.base.vo.EgoResult;
import com.xkt.base.vo.SearchItem;
import com.xkt.manager.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper mapper;

	@Autowired
	private ItemParamItemMapper itemParamItemMapper;

	@Autowired
	private ItemDescMapper itemDescMapper;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Value("${MQ_ITEM_QUEUE_NAME}")
	private String destination;

	@Override
	public Item selectById(Long id) {

		Item item = mapper.selectById(id);

		return item;
	}

	@Override
	public EgoResult save(Item item, String desc, String itemParams, String categoryName) {

		long itemId = IDUtils.genItemId();
		// 保存商品信息
		item.setId(itemId);
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		item.setStatus((byte) 1);
		mapper.insert(item);

		/*
		 * 将增加得商品信息同步(写入队列中)到索引库 （1）将商品转化成searchItem类型 （2）通过匿名内部类，创建Message消息对象
		 */
		SearchItem temp = new SearchItem();
		temp.setId(item.getId());
		temp.setImage(item.getImage());
		temp.setPrice(item.getPrice());
		temp.setSellPoint(item.getSellPoint());
		temp.setTitle(item.getTitle());
		temp.setCategoryName(categoryName);

		jmsTemplate.send(destination, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {

				// 使用Map类型保存消息
				MapMessage mapMessage = session.createMapMessage();
				// key用来标记当前是在添加商品
				// value是存储商品的信息
				mapMessage.setString("key", "add");
				mapMessage.setString("value", JsonUtils.objectToJson(temp));
				return mapMessage;
			}
		});

		// 保存商品描述信息
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(item.getCreated());
		itemDesc.setUpdated(itemDesc.getCreated());
		itemDescMapper.insert(itemDesc);

		// 保存规格参数
		ItemParamItem pItem = new ItemParamItem();
		pItem.setItemId(itemId);
		pItem.setParamData(itemParams);
		pItem.setCreated(item.getCreated());
		pItem.setUpdated(item.getUpdated());

		itemParamItemMapper.insert(pItem);

		return EgoResult.ok();
	}

	@Override
	public EUDataGridResult listAndPage(Integer page, Integer rows) {
		
		EUDataGridResult result = new EUDataGridResult();
		
		List<Item> list = mapper.listAndPage((page-1)*rows, rows);
		result.setRows(list);
		
		//获取商品的总数量
		Integer count = mapper.selectCount(null);
		result.setTotal(count);
		
		return result;
	}

}
