package com.xkt.base.vo;

/**
 * 
 * kindeditor文件上传返回值类型
 * 
 * @author lzx
 *
 */
public class UploadResult {

	private int error; // 表示 1 表示失败，0表示成功

	private String url; // 上传成功时，文件的访问地址

	private String message; // 上传失败时，错误信息

	public UploadResult() {
		super();
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
