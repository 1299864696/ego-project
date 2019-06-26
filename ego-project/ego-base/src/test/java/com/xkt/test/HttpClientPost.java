package com.xkt.test;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;

/**
 * 测试HttpClientPost
 * 
 * @author lzx
 *
 */
public class HttpClientPost {

	public static void main(String[] args) {

		doPost();

	}

	private static void doPost() {

		// 1.创建Httpclient对象
		CloseableHttpClient httpclient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy())
				.build();

		// 2.创建http POST请求
		HttpPost httpPost = new HttpPost("http://www.oschina.net");

		CloseableHttpResponse response = null;

		/**
		 * HTTP/1.1 403 Forbidden 原因： 有些网站，设置了反爬虫机制 解决的办法： 设置请求头，伪装浏览器
		 */
		httpPost.addHeader("user-agent",
				"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");

		try {
			// 3.执行请求
			response = httpclient.execute(httpPost);
			System.out.println(response.getStatusLine());
			// 4.解析结果

			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				String content = EntityUtils.toString(response.getEntity(), "UTF-8");
				System.out.println(content);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
