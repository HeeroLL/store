package com.zebone.register.validation;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zebone.register.validation.domain.CheckConfig;
import com.zebone.register.validation.enu.ResultType;
import com.zebone.register.validation.service.CheckService;

/**
 * 校验入口
 * 
 * @author 林彬
 * @date 2013-8-16 上午10:21:17
 */
@Service("documentValidation")
public class DocumentValidation {

	private static final Log logger = LogFactory.getLog(DocumentValidation.class);

	/** 校验服务 **/
	@Resource
	private CheckService checkService;


	public String execute(String docxml,String ip){
		String xmlData = null;
		if (StringUtils.isNotEmpty(xmlData)) {
			CheckConfig checkConfig = new CheckConfig();
			checkConfig.setIsRepeat("0");
			checkConfig.setIsSelect("0");
			checkConfig.setIsBusinessFormat("0");
			checkConfig.setIsValue("0");
			checkConfig.setIsDataFormat("0");
			checkConfig.setIsOnly("0");
			checkConfig.setIsJurisd("0");
			return checkService.check(checkConfig, xmlData, ip);
		} else{
			String result = ResultType.XML_ERROR.getErrorCode()+"|"+ResultType.XML_ERROR.getErrorMsg()+"|文档不能为空！";
			return result;
		}

	}


}
