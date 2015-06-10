package com.zebone.empi.dao;

import java.util.List;
import java.util.Map;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.empi.vo.ResidentInfo;

/**
 * 居民信息数据库操作映射接口
 * @author YinCm
 * @version 2013-7-31 下午10:15:20
 */
@Mapper
public interface ResidentInfoMapper {

	/**
	 * 通过empi号删除居民信息记录
	 * @param empi
	 * @return
	 */
    int deleteResidentInfoByEmpi(String empi);

    /**
     * 插入居民信息记录
     * @param record
     * @return
     */
    int insertResidentInfo(ResidentInfo record);

    /**
     * 按empi号查询一条居民记录
     * @param empi
     * @return
     */
    ResidentInfo selectResidentInfoByEmpi(String empi);
    
    /**
     * 查找所有居民信息记录
     * @param residentInfo
     * @return
     */
    List<ResidentInfo> selectResidentInfo(ResidentInfo residentInfo);

    /**
     * 按照empi号更新居民信息记录
     * @param record
     * @return
     */
    int updateResidentInfoByEmpi(ResidentInfo record);

    /**
     * 根据empi号更新居民等级
     * @param oMap
     * @return
     */
    int updateStarLevel(Map oMap);


}