package com.zebone.dip.ws.resource.dao;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.dip.ws.resource.model.PResourceUserLog;
import com.zebone.dip.ws.resource.model.PResourceUserLogExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PResourceUserLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_USER_LOG
     *
     * @mbggenerated Mon Mar 31 08:42:14 CST 2014
     */
    int countByExample(PResourceUserLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_USER_LOG
     *
     * @mbggenerated Mon Mar 31 08:42:14 CST 2014
     */
    int deleteByExample(PResourceUserLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_USER_LOG
     *
     * @mbggenerated Mon Mar 31 08:42:14 CST 2014
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_USER_LOG
     *
     * @mbggenerated Mon Mar 31 08:42:14 CST 2014
     */
    int insert(PResourceUserLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_USER_LOG
     *
     * @mbggenerated Mon Mar 31 08:42:14 CST 2014
     */
    int insertSelective(PResourceUserLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_USER_LOG
     *
     * @mbggenerated Mon Mar 31 08:42:14 CST 2014
     */
    List<PResourceUserLog> selectByExample(PResourceUserLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_USER_LOG
     *
     * @mbggenerated Mon Mar 31 08:42:14 CST 2014
     */
    PResourceUserLog selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_USER_LOG
     *
     * @mbggenerated Mon Mar 31 08:42:14 CST 2014
     */
    int updateByExampleSelective(@Param("record") PResourceUserLog record, @Param("example") PResourceUserLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_USER_LOG
     *
     * @mbggenerated Mon Mar 31 08:42:14 CST 2014
     */
    int updateByExample(@Param("record") PResourceUserLog record, @Param("example") PResourceUserLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_USER_LOG
     *
     * @mbggenerated Mon Mar 31 08:42:14 CST 2014
     */
    int updateByPrimaryKeySelective(PResourceUserLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table P_RESOURCE_USER_LOG
     *
     * @mbggenerated Mon Mar 31 08:42:14 CST 2014
     */
    int updateByPrimaryKey(PResourceUserLog record);
}