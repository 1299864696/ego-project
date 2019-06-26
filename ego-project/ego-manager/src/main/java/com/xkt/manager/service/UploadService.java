package com.xkt.manager.service;

import org.springframework.web.multipart.MultipartFile;

import com.xkt.base.vo.UploadResult;

public interface UploadService {

	/**
	 * 文件上传
	 * @param file 接受从页面发送的文件
	 * @return
	 */
	UploadResult upload(MultipartFile file);

}
