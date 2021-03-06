package com.zebone.dip.ws.resource.dao;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.ws.resource.model.PResourceLog;
import com.zebone.dip.ws.resource.model.PResourceLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PResourceLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_LOG
     *
     * @mbggenerated Fri Apr 11 14:27:48 CST 2014
     */
    int countByExample(PResourceLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_LOG
     *
     * @mbggenerated Fri Apr 11 14:27:48 CST 2014
     */
    int deleteByExample(PResourceLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_LOG
     *
     * @mbggenerated Fri Apr 11 14:27:48 CST 2014
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_LOG
     *
     * @mbggenerated Fri Apr 11 14:27:48 CST 2014
     */
    int insert(PResourceLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_LOG
     *
     * @mbggenerated Fri Apr 11 14:27:48 CST 2014
     */
    int insertSelective(PResourceLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_LOG
     *
     * @mbggenerated Fri Apr 11 14:27:48 CST 2014
     */
    List<PResourceLog> selectByExampleWithBLOBs(PResourceLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_LOG
     *
     * @mbggenerated Fri Apr 11 14:27:48 CST 2014
     */
    List<PResourceLog> selectByExample(PResourceLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_LOG
     *
     * @mbggenerated Fri Apr 11 14:27:48 CST 2014
     */
    PResourceLog selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_LOG
     *
     * @mbggenerated Fri Apr 11 14:27:48 CST 2014
     */
    int updateByExampleSelective(@Param("record") PResourceLog record, @Param("example") PResourceLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_LOG
     *
     * @mbggenerated Fri Apr 11 14:27:48 CST 2014
     */
    int updateByExampleWithBLOBs(@Param("record") PResourceLog record, @Param("example") PResourceLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_LOG
     *
     * @mbggenerated Fri Apr 11 14:27:48 CST 2014
     */
    int updateByExample(@Param("record") PResourceLog record, @Param("example") PResourceLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_LOG
     *
     * @mbggenerated Fri Apr 11 14:27:48 CST 2014
     */
    int updateByPrimaryKeySelective(PResourceLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_LOG
     *
     * @mbggenerated Fri Apr 11 14:27:48 CST 2014
     */
    int updateByPrimaryKeyWithBLOBs(PResourceLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_LOG
     *
     * @mbggenerated Fri Apr 11 14:27:48 CST 2014
     */
    int updateByPrimaryKey(PResourceLog record);
}