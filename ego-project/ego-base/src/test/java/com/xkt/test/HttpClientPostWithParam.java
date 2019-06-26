package com.xkt.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 测试HttpClientPostWithParam
 * 
 * @author lzx
 *
 */
public class HttpClientPostWithParam {

	public static void main(String[] args) {

		doPostWithParam();

	}

	private static void doPostWithParam() {

		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;

		try {
			// 1、第一步：创建HttpClient客户端
			httpclient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();

			// 2、第二步：创建post请求方式，如果有参数，需要通过UrlEncodedFormEntity来模拟form表单

			// 设置2个post参数，一个是scope、一个是q
			List<NameValuePair> parameters = new ArrayList<>();
			parameters.add(new BasicNameValuePair("scope", "project"));
			parameters.add(new BasicNameValuePair("q", "mysql"));
			// 构造一个form表单式的实体
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
			HttpPost post = new HttpPost("http://www.oschina.net/search");
			// 将请求实体设置到httpPost对象中
			post.setEntity(formEntity);

			// 有一些网站（接口）设置了反爬虫机制，禁止httpclient这种工具去请求接口代码
			// 设置请求头，伪装浏览器发送请求,有的网站机制比较健全，可能会不成功
			post.addHeader("user-agent",
					"Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");

			// 3、第三步：执行请求

			response = httpclient.execute(post);

			// 4、第四步：解析结果
			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println(statusCode);
			if (200 == statusCode) {
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

			if (null != httpclient) {
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
