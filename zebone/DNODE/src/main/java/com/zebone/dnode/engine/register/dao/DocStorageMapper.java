package com.zebone.dnode.engine.register.dao;

/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * 蔡祥龙                 New             Aug 14, 2013  注册引擎数据访问层
 */

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.core.mybatis.DcMapper;
import com.zebone.dnode.engine.register.vo.DocStorage;

@DcMapper
public interface DocStorageMapper {

	/**
	 * @author caixl
	 * @date Aug 14, 2013
	 * @description TODO 获取代注册的存储信息
	 * @return List<DocStorage>
	 */
	List<DocStorage> getDocStorageList(@Param("threadCount")int threadCount, @Param("threadNo")int threadNo);
	
	
	/**
	 * 获取代注册的存储信息
	 * @param docOrgCode  机构编码
	 * @return
	 * @author 陈阵 
	 * @date 2014-1-2 上午9:47:45
	 */
	List<DocStorage> getDocStorageListByDocOrgCode(@Param("docOrgCode") String docOrgCode);

	/**
	 * @author caixl
	 * @date Aug 15, 2013
	 * @description TODO 更新注册状态
	 * @param list
	 * @return int
	 */
	int updateDocRegisterState(DocStorage list);

}
