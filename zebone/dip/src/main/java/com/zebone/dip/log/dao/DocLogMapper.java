package com.zebone.dip.log.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.log.vo.DocLog;
import com.zebone.dip.log.vo.ParmInfo;
@Mapper
public interface DocLogMapper {

    int deleteByPrimaryKey(String id);

    int insert(DocLog record);

    int insertSelective(DocLog record);

    DocLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DocLog record);

    int updateByPrimaryKey(DocLog record);

	/**
	 * 方法描述: 获取文档类型数据
	 * @author caixl
	 * @date Sep 10, 2013
	 * @return 
	 * List<ParmInfo>
	 */
	List<ParmInfo> getDictInfo();

	/**
	 * 方法描述: 获取符合条件的医疗机构信息
	 * @author caixl
	 * @date Sep 10, 2013
	 * @param name
	 * @param rowBounds
	 * @return 
	 * List<ParmInfo>
	 */
	List<ParmInfo> getOrganInfo(@Param("name")String name, RowBounds rowBounds);

	/**
	 * 方法描述: 获取医疗机构信息
	 * @author 殷昌明
	 * @date Sep 10, 2013
	 * @param name
	 * @param rowBounds
	 * @return 
	 * List<ParmInfo>
	 */
	List<ParmInfo> getAllOrganInfo();
	
	
	/**
	 * 方法描述: 获取符合条件的医疗机构的总数母
	 * @author caixl
	 * @date Sep 10, 2013
	 * @param name
	 * @return 
	 * int
	 */
	int getOrganCount(@Param("name")String name);

	/**
	 * 方法描述: 获取文档日志列表
	 * @author caixl
	 * @date Sep 10, 2013
	 * @param docLog 相关文档日志的信息
	 * @param rowBounds
	 * @return 
	 * List<DocLog>
	 */
	List<DocLog> getDocLogList(DocLog docLog, RowBounds rowBounds);

	/**
	 * 方法描述: 获取文档日志总数
	 * @author caixl
	 * @date Sep 10, 2013
	 * @param docLog 相关文档日志的信息
	 * @return 
	 * int
	 */
	int getDocLogCount(DocLog docLog);

    /**
     * 方法描述: 根据医疗机构编码获取机构名称
     * @param orgCode 医疗机构编码
     * @return
     */
    String getOrgName(String orgCode);

    /**
     * 方法描述: 根据医生编码获取医生名称
     * @param doctorCode 医生编码
     * @return
     */
    String getDoctorName(String doctorCode);

    /**
     * 方法描述: 获取标识信息列表
     * @return
     */
    List<ParmInfo> getCardInfo();
}