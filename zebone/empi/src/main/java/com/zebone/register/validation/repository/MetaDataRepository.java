package com.zebone.register.validation.repository;



import com.zebone.register.validation.domain.DocConf;
import com.zebone.register.validation.domain.DocMapping;
import com.zebone.register.validation.domain.FieldConf;
import com.zebone.register.validation.domain.FieldTableColumn;

/**
 * 元数据Repository
 * @author 陈阵
 * @date 2013-7-30 上午9:12:42
 */
public interface MetaDataRepository {

	/**
	 * 根据文档主键获取文档配置信息
	 * 
	 * @param id
	 * @author 陈阵
	 * @date 2013-7-30 上午9:13:26
	 */
	public DocConf getDocConfigById(String id);

	/**
	 * 根据xpath和docId获取元数据主键
	 * 
	 * @param xpath
	 * @param docId
	 * @return
	 * @author 陈阵
	 * @date 2013-7-31 上午9:35:15
	 */
	public DocMapping getDocMappingByXpathAndDocId(String xpath, String docId);

	
	/**
	 * 根据主键获取元数据
	 * 
	 * @param id
	 * @return
	 * @author 陈阵
	 * @date 2013-7-31 上午10:16:28
	 */
	public FieldConf getFieldConfById(String id);
	
	
	/**
	 * 根据元数据标识符号获取 元数据
	 * @param fieldId
	 * @return
	 * @author 陈阵 
	 * @date 2013-7-31 下午1:22:16
	 */
	public FieldConf getFieldConfByFieldId(String fieldId);
	
	
	/**
	 *  根据文档类型编号和文档版本号获取文档信息
	 * @param docType
	 * @param docCode
	 * @return
	 * @author 陈阵 
	 * @date 2013-8-18 上午8:56:26
	 */
	public DocConf getDocConfigByDocTypeCodeAndDocVersion(String docTypeCode,String docVersion);
	
	
    
	/**
	 *  根据元数据标识获取文档映射信息 
	 * @param docId
	 * @param fieldId
	 * @return
	 * @author 陈阵 
	 * @date 2013-8-20 上午9:04:36
	 */
	public DocMapping getDocMappingByFieldId(String docId,String fieldId);
	
	
	
	/**
	 * 根据列主键id获取文档数据映射的表、列 
	 * @param columnId
	 * @return
	 * @author 陈阵 
	 * @date 2013-8-27 下午1:29:51
	 */
	public FieldTableColumn getFieldTableColumn(String columnId);

}
