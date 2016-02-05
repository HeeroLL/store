package com.isell.service.common.po;

/**
 * 上传文件参数类
 *
 */
public class UploadParam {
	
	/**
     * 接入编码
     */
    private String accessCode;
    
    /**
     * 验证码
     */
    private String authCode;
	
	/**
	 * 上传名称
	 */
	private String uploadName;
	
	/**
	 * 上传类型 11：商品图片；12：注册会员；13：退款凭证；14：开店；15：酷店logo；16：保存分享   2：excel
	 */
	private String uploadType;
	
	/**
	 * 组装参数
	 */
	private String id;

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getUploadName() {
		return uploadName;
	}

	public void setUploadName(String uploadName) {
		this.uploadName = uploadName;
	}

	public String getUploadType() {
		return uploadType;
	}

	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
