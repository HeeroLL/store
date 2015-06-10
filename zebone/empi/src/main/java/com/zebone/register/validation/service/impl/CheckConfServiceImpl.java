package com.zebone.register.validation.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zebone.register.validation.dao.CheckConfMapper;
import com.zebone.register.validation.domain.CheckConf;
import com.zebone.register.validation.service.CheckConfService;
import com.zebone.util.UUIDUtil;



/**
 * 类描述：校验接口业务实现层
 * @author: caixl
 * @date： 日期：Sep 3, 2013
 * @version 1.0
 */
@Service("checkConfService")
public class CheckConfServiceImpl implements CheckConfService{

	@Resource
	private CheckConfMapper checkConfMapper;
	
	/**
	 * 方法描述: 获取所有文档校验开关的相关的信息
	 * @author caixl
	 * @date Sep 3, 2013
	 * @return 
	 * List<CheckConf>
	 */
	@Override
	public List<CheckConf> getCheckConfList() {
		
		return checkConfMapper.getCheckConfAll();
	}

	/**
	 * 方法描述: 校验开关的相关数据保存
	 * @author caixl
	 * @date Sep 3, 2013
	 * @param data 
	 * @return 
	 * int
	 */
	@Override
	public int checkoutSave(String data) {
		int result = 0;
		String[] datas = data.split(";");
		List<CheckConf> list = new ArrayList<CheckConf>(datas.length);
		for(String str : datas){
			String[] strs = str.split(",",7);
			CheckConf checkConf = new CheckConf();
			checkConf.setDocId(strs[0]);
			checkConf.setId(UUIDUtil.getUuid());
			checkConf.setIsSelect(strs[1]);
			checkConf.setIsRepeat(strs[2]);
			checkConf.setIsDataFormat(strs[3]);
			checkConf.setIsBusiFormat(strs[4]);
			checkConf.setIsOnly(strs[5]);
			checkConf.setIsValue(strs[6]);
			
			list.add(checkConf);
		}
		checkConfMapper.delete();
		
		result = checkConfMapper.saveBatch(list);
		return result;
	}

}
