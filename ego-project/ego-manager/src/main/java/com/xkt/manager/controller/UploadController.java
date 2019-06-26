package com.xkt.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xkt.base.vo.UploadResult;
import com.xkt.manager.service.UploadService;

@Controller
public class UploadController {

	@Autowired
	private UploadService service;

	@RequestMapping("/pic/upload")
	@ResponseBody
	public UploadResult fResult(MultipartFile uploadFile) {

		UploadResult result = service.upload(uploadFile);

		return result;
	}

}
