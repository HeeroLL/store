package com.zebone.register.validation.dao;

import org.apache.ibatis.annotations.Param;

import com.zebone.btp.core.mybatis.DipMapper;
import com.zebone.register.validation.domain.DocConf;
import com.zebone.register.validation.domain.DocMapping;
import com.zebone.register.validation.domain.FieldConf;
import com.zebone.register.validation.domain.FieldTableColumn;

/**
 * 元数据mapper
 * @author 陈阵 
 * @date 2013-7-29 上午9:24:00
 */
@DipMapper
public interface MetaDataMapper {
	
	/**
	 * 根据文档主键获取文档配置信息
	 * @return
	 * @author 陈阵 
	 * @date 2013-7-29 上午10:02:03
	 */
	public DocConf getDocConfigById(@Param("id") String id);
	
	
	/**
	 * 根据xpath和docId获取元数据主键
	 * @param xpath
	 * @param docId
	 * @return
	 * @author 陈阵 
	 * @date 2013-7-31 上午9:32:11
	 */
    public DocMapping getDocMappingByXpathAndDocId(@Param("xpath") String xpath,@Param("docId") String docId);

    
    /**
     * 根据主键获取元数据 
     * @param id
     * @return
     * @author 陈阵 
     * @date 2013-7-31 上午10:16:28
     */
    public FieldConf getFieldConfById(@Param("id") String id);
    
    
    
    /**
     * 根据元数据标识符号获取 元数据
     * @param fieldId
     * @return
     * @author 陈阵 
     * @date 2013-7-31 下午1:21:22
     */
    public FieldConf getFieldConfByFieldId(@Param("fieldId") String fieldId);
    
    
    /**
     *  根据文档类型编号和文档版本号获取文档信息
     * @param docTypeCode
     * @param docVersion
     * @return
     * @author 陈阵 
     * @date 2013-8-18 上午9:00:16
     */
    public DocConf getDocConfigByDocTypeCodeAndDocVersion(@Param("docTypeCode") String docTypeCode, @Param("docVersion") String docVersion);
    
    
    
    /**
     * 根据元数据标识获取文档映射信息
     * @param fieldId
     * @return
     * @author 陈阵 
     * @date 2013-8-20 上午8:58:23
     */
    public DocMapping getDocMappingByFieldId(@Param("docId") String docId,  @Param("fieldId") String fieldId);
    
    
    
    /**
     * 根据列主键id获取文档数据映射的表、列
     * @param columnId
     * @return
     * @author 陈阵 
     * @date 2013-8-27 下午1:27:01
     */
    public FieldTableColumn getFieldTableColumn(@Param("id") String columnId);
}
