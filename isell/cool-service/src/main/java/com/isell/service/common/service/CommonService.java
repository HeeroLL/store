package com.isell.service.common.service;

import java.io.File;

import com.isell.core.util.Record;
import com.isell.service.common.po.UploadParam;

/**
 * 公共服务接口
 *
 */
public interface CommonService {
	
	/**
	 * 上传文件
	 * 
	 * @param uploadFile 上传文件
	 * @param param 文件上传参数
	 * @return 文件保存地址
	 */
	Record uploadFile(File uploadFile, UploadParam param);

}
