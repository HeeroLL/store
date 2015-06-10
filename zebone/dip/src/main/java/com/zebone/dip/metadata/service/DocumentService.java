/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Jun 29, 2013		文档管理业务接口层
 */
package com.zebone.dip.metadata.service;

import java.util.List;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.metadata.vo.DataSetConf;
import com.zebone.dip.metadata.vo.DocConf;
import com.zebone.dip.metadata.vo.DocTreeInfo;
import com.zebone.dip.metadata.vo.FieldConf;

public interface DocumentService {

	/**
	 * @author caixl
	 * @date Jun 29, 2013
	 * @description TODO 文档列表信息查询
	 * @param page
	 * @param docConf 
	 * @return Pagination<DocConf>
	 */
	Pagination<DocConf> docvInfoPage(Pagination<DocConf> page, DocConf docConf);

	/**
	 * @author caixl
	 * @date Jul 1, 2013
	 * @description TODO 保存文档基本信息
	 * @param docConf
	 * @return int
	 */
	int saveDocvInfo(DocConf docConf);

	/**
	 * @author caixl
	 * @date Jul 1, 2013
	 * @description TODO 更新文档基本信息
	 * @param docConf
	 * @return int
	 */
	int updateDocvInfo(DocConf docConf);

	/**
	 * @author caixl
	 * @date Jul 1, 2013
	 * @description TODO 删除相关文档信息
	 * @param ids
	 * @return int
	 */
	int removeDocvInfo(String[] ids);

	/**
	 * @author caixl
	 * @date Jul 1, 2013
	 * @description TODO 加载文档基础信息
	 * @param id
	 * @return DocConf
	 */
	DocConf loadDocvInfoById(String id);

	/**
	 * @author caixl
	 * @date Jul 2, 2013
	 * @description TODO 根据文档id，获取一个文档的层级结构的对象
	 * @param id
	 * @return DocTreeInfo
	 */
	DocTreeInfo getInfoById(String id);

	/**
	 * @author caixl
	 * @date Jul 2, 2013
	 * @description TODO 获取数据集名称信息
	 * @return List<String>
	 */
	List<String> getDatasetNames();

	/**
	 * @author caixl
	 * @param dataSetConf 
	 * @param page 
	 * @date Jul 2, 2013
	 * @description TODO 获取某元数据及下的元数据列表信息
	 * @return Pagination<FieldConf>
	 */
	Pagination<FieldConf> metadataListBydataset(Pagination<FieldConf> page, FieldConf dataSetConf);

	/**
	 * @author caixl
	 * @date Jul 3, 2013
	 * @description TODO 将文档与数据源的绑定存入库中
	 * @param docTreeInfo
	 * @return int
	 */
	int saveDocMapping(DocTreeInfo docTreeInfo);

	/**
	 * @author caixl
	 * @date Jul 4, 2013
	 * @description TODO 判断文档是否绑定元数据
	 * @param id
	 * @return int
	 */
	int isExistXMLById(String id);

	/**
	 * @author caixl
	 * @date Jul 30, 2013
	 * @description TODO 验证文档是否重复
	 * @param docConf
	 * @return int
	 */
	int validateDocInfo(DocConf docConf);

    /**
     * 根据文档ID判断该文档是否存在数据映射关系
     * @param id
     * @return
     */
    int isDocMapping(String id);
    
    
    /**
     * 更新文档中的xsd字段
     * @param docConf
     * @return
     * @author 陈阵 
     * @date 2013-8-17 上午10:54:11
     */
    int updateDocConfByIdSelective(DocConf docConf);
	
}
