package com.xkt.test;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 测试HttpClient
 * 
 * @author lzx
 *
 */
public class HttpClientGet {

	public static void main(String[] args) {

		doGet();

	}

	private static void doGet() {

		CloseableHttpClient httpClient = null;

		CloseableHttpResponse response = null;

		try {
			// 1.创建客户端
			httpClient = HttpClients.createDefault();

			// 2.创建get请求
			HttpGet get = new HttpGet("http://www.baidu.com");

			// 3.执行请求
			response = httpClient.execute(get);

			// 4.解析结果
			// 判断返回状态是否为200
			int code = response.getStatusLine().getStatusCode();

			if (200 == code) {

				HttpEntity entity = response.getEntity();

				String str = EntityUtils.toString(entity, "utf-8");

				System.out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != response) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
