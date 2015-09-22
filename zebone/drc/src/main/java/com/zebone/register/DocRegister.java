/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * 蔡祥龙              New             Aug 7, 2013      文档注册服务接口
 */
package com.zebone.register;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface DocRegister {
	/**
	 * @author caixl
	 * @date Aug 8, 2013
	 * @description TODO 文档注册
	 * @param xml
	 * @param docRegisterState
	 * @return String 返回注册标识和主索引标识
	 */
	String DocumentRegistry_RegistryDocumentSet_b (@WebParam(name="xml")String xml,@WebParam(name="docRegState")String docRegisterState);
}
