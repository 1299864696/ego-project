package com.xkt.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xkt.base.pojo.Content;
import com.xkt.base.utils.HttpClientUtils;
import com.xkt.base.utils.JsonUtils;
import com.xkt.base.vo.ADItem;
import com.xkt.base.vo.EgoResult;
import com.xkt.portal.service.ADService;
@Service
public class ADServiceImpl implements ADService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;

	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;

	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	public String getAD() {

		List<ADItem> ADItems = new ArrayList<>();

		// 用HTTPClient调用远程接口
		HttpClientUtils httpClient = new HttpClientUtils();
		// 远程请求服务层接口
		String jsonData = httpClient.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
		// 如果响应数据不为空
		if (null != jsonData && !"".equals(jsonData)) {
			EgoResult egoResult = EgoResult.formatToList(jsonData, Content.class);

			if (200 == egoResult.getStatus()) {

				List<Content> contentList = (List<Content>) egoResult.getData();

				ADItem adItem = null;
				for (Content content : contentList) {
					adItem = new ADItem();
					adItem.setHref(content.getUrl());
					adItem.setSrc(content.getPic());
					adItem.setSrcB(content.getPic2());
					adItem.setAlt(content.getTitleDesc());
					ADItems.add(adItem);
				}
			}

		}

		return JsonUtils.objectToJson(ADItems);
	}

}
