package com.xkt.base.utils;

import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FtpUtil {

	/**
	 * 文件上传
	 * 
	 * @param host           ftp主机地址
	 * @param port           端口
	 * @param username       ftp用户名
	 * @param password       ftp用户密码
	 * @param basePath       基础路径 /home/ftpuser/ego/images
	 * @param filePath       文件的路径，按日期区分 /2019/04/26
	 * @param remoteFileName 上传之后，文件的名称
	 * @param local          待上传文件的流对象
	 * @return
	 */
	public static boolean upload(String host, Integer port, String username, String password, String basePath,
			String filePath, String remoteFileName, InputStream local) {

		// 1、创建ftp客户端
		FTPClient client = new FTPClient();
		try {

			// 2、连接服务端
			client.connect(host, port);

			// 3、登陆，认证身份
			boolean flag = client.login(username, password);

			if (flag) {

				/*
				 * 切换上传的目录。
				 * 
				 * 如果这个目录不存在，则切换失败
				 */
				boolean flag2 = client.changeWorkingDirectory(basePath + filePath);
				if (!flag2) {
					/*
					 * 如果目录不存在，则创建目录
					 * 
					 * 注意事项：如果有多级目录的时候，必须一层一层目录去创建
					 */
					String tempPath = basePath;

					String[] paths = filePath.split("/");

					for (String path : paths) {
						if (null != path && !"".equals(path)) {
							tempPath = tempPath + "/" + path;

							// 创建当前目录 tempPath= /home/ftpuser/ego/images/2019/04/26
							/*
							 * 切换到目录不成功，则说明该目录不存在，要创建这个目录
							 * 
							 * 如果切换成功，则继续循环，创建下一层目录
							 */
							if (!client.changeWorkingDirectory(tempPath)) {

								// 如果创建成功，则切换到该目录
								if (client.makeDirectory(tempPath)) {
									if (!client.changeWorkingDirectory(tempPath)) {
										return false;
									}

								} else {
									return false;
								}

							} else {
								continue;
							}

						} else {
							continue;
						}

					}
				}

				// 5、指定上传为被动上传 因为：很多的客户端禁止主动模式
				client.enterLocalPassiveMode();

				// 6、指定上传方式为二进制，即使用字节流
				client.setFileType(FTP.BINARY_FILE_TYPE);

				// 7、上传
				boolean result = client.storeFile(remoteFileName, local);

				return result;

			} else {

				return flag;
			}

		} catch (Exception e) {
			e.printStackTrace();

			return false;

		} finally {

			try {
				if (client.logout()) {
					client.disconnect();
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
	}

}
