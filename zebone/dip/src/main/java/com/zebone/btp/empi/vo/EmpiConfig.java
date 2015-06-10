package com.zebone.btp.empi.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 系统配置对象 对应EMPI系统配置表EMPI_CONFIG
 * 
 * @author ouyangxin 2013-1-21
 */
public class EmpiConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 导入EMPI存在时是否更新基本信息 0 否（default）1 是 */
	private String updateEmpiFlag;

	/** 上传文件路径 默认路径为根目录下/upload/import */
	private String uploadPath;

	/** EMPI类型: ET1身份证（default）;ET2护照; ET3军官证 */
	private String empiType;

	/** 更新EMPI时是否更新对应的卡card信息0 否 1 是（default） */
	private String updateCardFlag;

	public String getUpdateEmpiFlag() {
		return updateEmpiFlag;
	}

	public void setUpdateEmpiFlag(String updateEmpiFlag) {
		this.updateEmpiFlag = updateEmpiFlag;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getEmpiType() {
		return empiType;
	}

	public void setEmpiType(String empiType) {
		this.empiType = empiType;
	}

	public String getUpdateCardFlag() {
		return updateCardFlag;
	}

	public void setUpdateCardFlag(String updateCardFlag) {
		this.updateCardFlag = updateCardFlag;
	}

	/**
	 * 重写toString方法
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
