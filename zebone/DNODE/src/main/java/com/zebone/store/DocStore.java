/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * 蔡祥龙              New             Aug 6, 2013      文档存储服务接口
 */
package com.zebone.store;

import javax.jws.WebParam;
import javax.jws.WebService;
@WebService
public interface DocStore {
	/**
	 * @author caixl
	 * @date Aug 6, 2013
	 * @description TODO 文档存储注册方法
	 * @param xml 参数
	 * @return String  返回是否注册成功标识
	 */
	String ProviderAndRegistryDocumentSet_b(@WebParam(name="xml")String xml);
}
