package com.zebone.register.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.register.vo.EmpiInfo;

@Mapper
public interface EmpiInfoMapper {

	/**
	 * @author caixl
	 * @date Aug 9, 2013
	 * @description TODO 保存主索引注册信息
	 * @param empiInfo
	 * @return int
	 */
	int insert(EmpiInfo empiInfo);

	/**
	 * @author caixl
	 * @date Aug 9, 2013
	 * @description TODO 更新主索引信息
	 * @param empiInfo
	 * @return int
	 */
	int update(EmpiInfo empiInfo);

	/**
	 * @author caixl
	 * @date Aug 24, 2013
	 * @description TODO 获取卫生服务活动列表
	 * @param empiId
	 * @return List<EmpiInfo>
	 */
	List<EmpiInfo> getEmpiInfoByEmpiId(@Param("empiId")String empiId);

	/**
	 * @author caixl
	 * @date Aug 25, 2013
	 * @description TODO 获取健康疾病和问题列表
	 * @param empiId
	 * @return List<EmpiInfo>
	 */
	List<EmpiInfo> getIcdByEmpiId(@Param("empiId")String empiId);

	/**
	 * @author caixl
	 * @date Aug 25, 2013
	 * @description TODO 获取健康疾病和问题列表
	 * @param string
	 * @param empiId
	 * @return List<EmpiInfo>
	 */
	List<EmpiInfo> getEmpiInfoByIcdAndEmpiId(@Param("icdCode")String string, @Param("empiId")String empiId);

	/**
	 * @author Administrator
	 * @date Aug 25, 2013
	 * @description TODO 获取不同年龄段的健康活动列表
	 * @param startDate
	 * @param endDate
	 * @param empiId
	 * @return List<EmpiInfo>
	 */
	List<EmpiInfo> getEmpiInfoByAge(@Param("startDate")String startDate, @Param("endDate")String endDate,
			@Param("empiId")String empiId);

    /**
     * 获取调阅列表数据
     * @param oMap
     * @return
     */
    List<String> getFollowUpList (Map<String,String> oMap);

    /**
     * 根据父文档编号获取其关联文档编号
     * @param oMap
     * @return
     */
    String getDocNoByParentNo (Map<String,String> oMap);
    
}