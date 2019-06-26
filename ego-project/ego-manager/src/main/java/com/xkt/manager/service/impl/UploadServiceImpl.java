package com.xkt.manager.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xkt.base.utils.FtpUtil;
import com.xkt.base.vo.UploadResult;
import com.xkt.manager.service.UploadService;
@Service
public class UploadServiceImpl implements UploadService {

	@Value("${FTP_HOST}")
	private String host;

	@Value("${FTP_PORT}")
	private Integer port;

	@Value("${FTP_USER}")
	private String username;

	@Value("${FTP_PASSWD}")
	private String password;

	@Value("${FTP_BASE_URL}")
	private String basePath;
	
	@Value("${PICTURE_BASE_URL}")
	private String PICTURE_BASE_URL;

	@Override
	public UploadResult upload(MultipartFile file) {

		UploadResult result = new UploadResult();
		try {
			// 上传需求：按日期划分图片的目录， 2019/04/27/1.jpg
			String filePath = "/" + new SimpleDateFormat("yyyy").format(new Date()) + "/"
					+ new SimpleDateFormat("MM").format(new Date()) + "/"
					+ new SimpleDateFormat("dd").format(new Date());

			// 获取文件后缀，即格式
			String originalFilename = file.getOriginalFilename();

			String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
			String remoteFileName = UUID.randomUUID().toString() + fileType;

			boolean upload = FtpUtil.upload(host, port, username, password, basePath, filePath, remoteFileName,
					file.getInputStream());
			
			if (upload) {
				result.setError(0);
				result.setUrl(PICTURE_BASE_URL+filePath+"/"+remoteFileName);
			}else {
				result.setError(1);
				result.setMessage("上传失败，请稍后再试");
			}
		} catch (IOException e) {
			result.setError(1);
			result.setMessage(e.getMessage());
			
		}
		return result;
	}

}
