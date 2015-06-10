package com.zebone.store.dao;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.store.vo.DocStorage;

/**
 * 文档存储数据接口层
 */
@Mapper
public interface DocStorageMapper {

	/**
	 * @author caixl
	 * @date Aug 6, 2013
	 * @description TODO 存储文档信息
	 * @param docStorage
	 * @return int 返回注册成功标识
	 */
	int insert(DocStorage docStorage);

	/**
	 * @author caixl
	 * @date Aug 6, 2013
	 * @description TODO 满足要求的文档个数
	 * @param docNo 文档编号
	 * @return int
	 */
	int docStorageCount(@Param("docNo")String docNo);

	/**
	 * @author caixl
	 * @date Aug 6, 2013
	 * @description TODO 根据文档编码获取文档
	 * @param docNo
	 * @return DocStorage
	 */
	DocStorage getDocStorageByDocNo(@Param("docNo")String docNo);

	/**
	 * @author caixl
	 * @date Aug 6, 2013
	 * @description TODO 更新文档信息
	 * @param docStorage
	 * @return int
	 */
	int update(DocStorage docStorage);

	/**
	 * @author caixl
	 * @date Aug 24, 2013
	 * @description TODO 根据文档类型编码获取文档
	 * @param code
	 * @return DocStorage
	 */
	DocStorage getDocStorageByDocTypeCode(@Param("docTypeCode")String code,@Param("empiId")String empiId);

    /**
     * 根据文档编号更新文档注册状态
     * @param docNo
     * @return int
     */
    int updateRegisterStateByDocNo(String docNo);
	
}