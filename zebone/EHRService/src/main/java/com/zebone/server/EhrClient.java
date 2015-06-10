/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             May 8, 2013		
 */
package com.zebone.server;

import javax.jws.WebService;

@WebService
public interface EhrClient {
	/**
	 * @author caixl
	 * @date May 8, 2013
	 * @description TODO 数据采集标准接口方法
	 * @param xml 上文档数据
	 * @return ResultSet
	 */
	ResultSet EHRSyncTransport(String xml);
}
