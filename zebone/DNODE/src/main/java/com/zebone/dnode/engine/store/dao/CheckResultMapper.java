package com.zebone.dnode.engine.store.dao;

/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * Administrator              New             Aug 13, 2013
 */

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.core.mybatis.DipMapper;
import com.zebone.dnode.engine.store.vo.CheckResult;

@DipMapper
public interface CheckResultMapper {

	/**
	 * @author caixl
	 * @date Aug 13, 2013
	 * @description TODO 获取待存储数据信息
	 * @param threadCount
	 * @param threadNo
	 * @return List<CheckResult>
	 */
	public List<CheckResult> getCheckResultList(@Param("threadCount")int threadCount, @Param("threadNo")int threadNo);

	/**
	 * @author caixl
	 * @date Aug 14, 2013
	 * @description TODO 更新存储成功状态信息
	 * @param list
	 * @return int
	 */
	public int updateStorageFlag(List<String> list);

	/**
	 * 方法描述: 更新存储失败状态信息
	 * @author caixl
	 * @date Sep 24, 2013
	 * @param list 
	 * void
	 */
	public void updateStorageFFlag(List<String> list);
	
	
    /**
     * 根据机构编码获取待存储数据信息
     * @param docOrgCode
     * @return
     * @author 陈阵 
     * @date 2014-1-2 上午9:05:29
     */
	public List<CheckResult> getCheckResultListByOrgCode(@Param("docOrgCode") String docOrgCode);
	
}
