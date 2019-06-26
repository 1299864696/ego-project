package com.xkt.base.test;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 * 测试文件上传
 * 
 * @author lzx
 *
 */
public class TestFtp {

	public static void main(String[] args) {

		try {
			// 1.创建ftp客户端
			FTPClient client = new FTPClient();
			// 2.连接服务器
			client.connect("192.168.109.3", 21);
			// 3.登录，认证身份
			boolean flag = client.login("ftpuser", "ftpuser");

			if (flag) {

				/*
				 * 4.指定图片上传的目录，默认路径在ftpuser的家目录下
				 * 
				 * 确保ftpuser用户有这个目录下的写权限
				 * 
				 * 在Linux上用ftpuser这个用户，去创建这个目录
				 * 
				 */
				client.changeWorkingDirectory("/home/ftpuser/ego/images");

				// 5.指定上传为被动上传，因为：很多的客户端禁止主动模式
				client.enterLocalPassiveMode();

				// 6.指定文件上传的方式为二进制,即使用字节流
				client.setFileType(FTP.BINARY_FILE_TYPE);

				// 7.上传
				File pic = new File("F:/图片/3.jpg");
				boolean result = client.storeFile(System.currentTimeMillis() + ".jpg", new FileInputStream(pic));

				if (result) {
					System.out.println("上传成功");
				} else {
					System.out.println("上传失败");
				}
			}

			client.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
