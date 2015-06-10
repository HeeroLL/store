/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Jun 25, 2013		元数据管理业务层接口
 */
package com.zebone.dip.metadata.service;

import java.util.List;

import com.zebone.btp.app.dict.pojo.DictionaryType;
import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.metadata.vo.DataSetConf;
import com.zebone.dip.metadata.vo.DatasetMapping;
import com.zebone.dip.metadata.vo.FieldConf;

public interface MetaDataService {

	/**
	 * @author caixl
	 * @date Jun 25, 2013
	 * @description TODO 元数据列表
	 * @param page
	 * @param fieldConf 查询条件
	 * @return Pagination<FieldConf>
	 */
	Pagination<FieldConf> metadataPage(Pagination<FieldConf> page,FieldConf fieldConf);

	/**
	 * @author caixl
	 * @date Jun 26, 2013
	 * @description TODO 保存元数据信息
	 * @param fieldConf
	 * @return int
	 */
	int saveMetadata(FieldConf fieldConf);

	/**
	 * @author caixl
	 * @date Jun 26, 2013
	 * @description TODO 更新元数据信息
	 * @param fieldConf
	 * @return int
	 */
	int updateMetadata(FieldConf fieldConf);

	/**
	 * @author caixl
	 * @date Jun 26, 2013
	 * @description TODO 根据元数据标识获取元数据信息
	 * @param id 元数据标识
	 * @return FieldConf
	 */
	FieldConf getMetadataINfoById(String id);

	/**
	 * @author caixl
	 * @date Jun 26, 2013
	 * @description TODO 逻辑删除元数据相关信息
	 * @param ids 元数据标识数组
	 * @return int 删除返回标识
	 */
	int removeMetadataByIds(String[] ids);

	/**
	 * @author caixl
	 * @date Jun 26, 2013
	 * @description TODO 获取数据集列表信息
	 * @param page
	 * @param dataSetConf 查询条件
	 * @return Pagination<DataSetConf>
	 */
	Pagination<DataSetConf> datasetPage(Pagination<DataSetConf> page,
			DataSetConf dataSetConf);

	/**
	 * @author caixl
	 * @date Jun 27, 2013
	 * @description TODO 获取数据集的相关信息
	 * @param id
	 * @return DataSetConf
	 */
	DataSetConf getDataSetConfById(String id);

	/**
	 * @author caixl
	 * @date Jun 27, 2013
	 * @description TODO 保存数据集相关信息
	 * @param dataSetConf
	 * @return int
	 */
	int saveDataSetConf(DataSetConf dataSetConf);

	/**
	 * @author caixl
	 * @date Jun 27, 2013
	 * @description TODO 更新数据集相关信息
	 * @param dataSetConf
	 * @return int
	 */
	int updateDataSetConf(DataSetConf dataSetConf);

	/**
	 * @author caixl
	 * @date Jun 27, 2013
	 * @description TODO 获取数据元列表信息
	 * @param f
	 * @return List<FieldConf>
	 */
	List<FieldConf> getMetadatasByQuery(FieldConf f);

	/**
	 * @author caixl
	 * @date Jun 28, 2013
	 * @description TODO 保存 数据集 与 元数据的关系
	 * @param datasetMapping
	 * @return int
	 */
	int addMetadataInfo(DatasetMapping datasetMapping);

	/**
	 * @author caixl
	 * @date Jun 28, 2013
	 * @description TODO 解除数据元与数据集的关系
	 * @param fieldId
	 * @param datasetId
	 * @return int
	 */
	int revokeMetadata(String[] fieldId, String datasetId);

	/**
	 * @author caixl
	 * @date Jun 28, 2013
	 * @description TODO 查询某个数据集下的数据元
	 * @param page
	 * @param fieldConf
	 * @return Pagination<FieldConf>
	 */
	Pagination<FieldConf> datasetMetadataPage(Pagination<FieldConf> page,
			FieldConf fieldConf);

	/**
	 * @author caixl
	 * @date Jun 28, 2013
	 * @description TODO 删除数据集相关信息
	 * @param split
	 * @return int
	 */
	int removeDatasetInfo(String[] split);

	/**
	 * @author caixl
	 * @date Jul 12, 2013
	 * @description TODO 判断是否存在 元数据编码
	 * @param fieldCode
	 * @param id
	 * @return int
	 */
	int isExistsFieldCode(String fieldCode,String id);

	/**
	 * @author caixl
	 * @date Jul 12, 2013
	 * @description TODO 判断是否存在 元数据标识符
	 * @param fieldId
	 * @param id
	 * @return int
	 */
	int isExistsFieldId(String fieldId,String id);

	/**
	 * @author caixl
	 * @date Jul 12, 2013
	 * @description TODO 获取匹配的值域信息
	 * @param id
	 * @param name
	 * @return List<DictionaryType>
	 */
	List<DictionaryType> getListInfoById(String id, String name);

	/**
	 * @author caixl
	 * @date Jul 24, 2013
	 * @description TODO 元数据是否被使用
	 * @param id
	 * @return int
	 */
	int metadataIsUsingById(String id);

	/**
	 * @author caixl
	 * @date Jul 26, 2013
	 * @description TODO 验证是否存在相同版本和同名的数据集
	 * @param dataSetConf
	 * @return int
	 */
	int existsDatasetInfo(DataSetConf dataSetConf);

	/**
	 * @author caixl
	 * @date Jul 26, 2013
	 * @description TODO 验证数据集中是否存在该元数据别名
	 * @param datasetMapping
	 * @return int
	 */
	int datasetExistsYsjbm(DatasetMapping datasetMapping);

	/**
	 * 获取数据集相关信息集合
	 * @param name
	 * @return 
	 * List<DataSetConf>
	 */
	List<DataSetConf> getListByDatasetName(String name);

}
