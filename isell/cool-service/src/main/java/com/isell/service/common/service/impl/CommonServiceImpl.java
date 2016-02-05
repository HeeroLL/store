package com.isell.service.common.service.impl;

import java.io.File;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.isell.core.config.BisConfig;
import com.isell.core.util.FileUploadUtil;
import com.isell.core.util.Record;
import com.isell.service.common.po.UploadParam;
import com.isell.service.common.service.CommonService;

/**
 * 公共服务实现类
 *
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService {
	
	 /**
     * 配置信息
     */
    @Resource
    private BisConfig config;

	/**
	 * 上传文件
	 * 
	 * @param uploadFile 上传文件
	 * @param param 上传文件参数
	 * @return 文件保存地址
	 */
	@Override
	public Record uploadFile(File uploadFile, UploadParam param) {
		Record record = new Record();
		boolean success = false;
		boolean flag = true;
		long imgMaxsize = config.getImgMaxSize();
		String imgLocal = config.getImgLocal();		
		//String fileName = System.currentTimeMillis() + uploadFile.getAbsolutePath().substring(uploadFile.getAbsolutePath().lastIndexOf("."));
		String fileName = uploadFile.getName();
		String dirPath = "'";		
		String uploadType = param.getUploadType();
		if(StringUtils.isNotEmpty(uploadType)){
			//判断组合参数id，uploadType为12、13时，必填会员主键；为14、15、16时，必填酷店主键
			String id = param.getId();
			if(StringUtils.isEmpty(id) && ("12".equals(uploadType) || "13".equals(uploadType) || "14".equals(uploadType) || "15".equals(uploadType) || "16".equals(uploadType))){
				record.set("msg", "参数错误，id不能为空");
				flag = false;
			}else{
				if("11".equals(param.getUploadType())){ //商品图片
					dirPath = "/cool/";
				}else if ("12".equals(param.getUploadType())){ //注册会员
					dirPath = "/member/" + id + '/';
				}else if ("13".equals(param.getUploadType())){ //退款凭证
					dirPath = "/member/returngm/" + id + '/';
				}else if ("14".equals(param.getUploadType())){ //开店
					dirPath = "/shop/" + id + "/";
				}else if ("15".equals(param.getUploadType())){ //酷店logo
					dirPath = "/shop/" + id + "/logo/";
				}else if ("16".equals(param.getUploadType())){ //保存分享
					dirPath = "/shop/" + id  + "/";
				}else if ("2".equals(param.getUploadType())){ //excel
					dirPath = "/orderExcel/"; 
				}else{
					record.set("msg", "文件类型不正确");
					flag = false;
				}
			}					
			if(flag){
				String result = "";
				if("2".equals(param.getUploadType())){
					result = FileUploadUtil.uploadExcel(uploadFile, dirPath, fileName, imgMaxsize, imgLocal);
				}else{
					result = FileUploadUtil.uploadImage(uploadFile, dirPath, fileName, imgMaxsize, imgLocal);
				}
				if("true".equals(result)){
					record.set("filePath", dirPath+fileName	);
					success = true;
				}else{
					record.set("msg", result);
				}			
			}	
		}else{
			record.set("msg", "参数错误，上传类型不能为空");
		}
			
		record.set("success", success);
		return record;
	}

}
