package com.zebone.register;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 文档注册查询接口
 * 蔡祥龙
 * 2013-08-24
 */
@WebService
public interface DocRegisterQuery {
	/**
	 * @author caixl
	 * @date Aug 24, 2013
	 * @description TODO 调阅数据信息
	 * @param xml 调阅参数
	 * @return String 调阅返回值
	 */
	String DocumentRegistry_RegistryStroedQuery(@WebParam(name="xml")String xml);
}
