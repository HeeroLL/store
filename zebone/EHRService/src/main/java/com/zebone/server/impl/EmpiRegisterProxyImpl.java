package com.zebone.server.impl;

import com.zebone.empi.service.EmpiService;
import com.zebone.server.EmpiRegisterProxy;
import javax.annotation.Resource;


/**
 * 文档注册代理类。
 * @author songjunjie
 * @version 2013-8-21 下午03:29:47
 */
public class EmpiRegisterProxyImpl implements EmpiRegisterProxy {
	
	
	@Resource
	private EmpiService empiService;


	/**
	 * @see com.zebone.server.EmpiRegisterProxy#empiRegister(java.lang.String)
	 */
	@Override
	public String empiRegister(String registerXml) {
		try {
			String resultInfo = empiService.registerOrUpdate(registerXml);
//			String[] info = resultInfo.split("|");
			return resultInfo;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

    @Override
    public String searchEmpiInfo(String searchXml) {
        try {
            String resultInfo = empiService.searchEmpiInfo(searchXml);
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
