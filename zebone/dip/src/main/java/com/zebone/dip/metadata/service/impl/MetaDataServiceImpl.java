/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Jun 25, 2013		元数据管理service层实现
 */
package com.zebone.dip.metadata.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zebone.btp.app.dict.pojo.DictionaryType;
import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.dictionary.dao.DipDictionaryTypeMapper;
import com.zebone.dip.metadata.dao.DataSetConfMapper;
import com.zebone.dip.metadata.dao.DatasetMappingMapper;
import com.zebone.dip.metadata.dao.FieldConfMapper;
import com.zebone.dip.metadata.service.MetaDataService;
import com.zebone.dip.metadata.vo.DataSetConf;
import com.zebone.dip.metadata.vo.DatasetMapping;
import com.zebone.dip.metadata.vo.FieldConf;
@Service("metaDataService")
public class MetaDataServiceImpl implements MetaDataService {

	@Resource
	private FieldConfMapper fieldConfMapper;
	
	@Resource
	private DataSetConfMapper dataSetConfMapper;
	
	@Resource
	private DatasetMappingMapper datasetMappingMapper;
	
	@Resource
	private DipDictionaryTypeMapper dipDictionaryTypeMapper;
	
	/**
	 * @author caixl
	 * @date Jun 25, 2013
	 * @description TODO 元数据列表
	 * @param page
	 * @param fieldConf 查询条件
	 * @return Pagination<FieldConf>
	 */
	@Override
	public Pagination<FieldConf> metadataPage(Pagination<FieldConf> page,FieldConf fieldConf) {
		List<FieldConf> list = fieldConfMapper.metadataList(page.getRowBounds(),fieldConf);
		int totalCount = fieldConfMapper.metadataTotalCount(fieldConf);
		page.setData(list);
		page.setTotalCount(totalCount);
		return page;
	}

	/**
	 * @author caixl
	 * @date Jun 26, 2013
	 * @description TODO 保存元数据信息
	 * @param fieldConf
	 * @return int
	 */
	@Override
	public int saveMetadata(FieldConf fieldConf) {
		int result = fieldConfMapper.insertSelective(fieldConf);
		if(result > 0) return 1;
		return 0;
	}

	/**
	 * @author caixl
	 * @date Jun 26, 2013
	 * @description TODO 更新元数据信息
	 * @param fieldConf
	 * @return int
	 */
	@Override
	public int updateMetadata(FieldConf fieldConf) {
		int result = fieldConfMapper.updateByPrimaryKey(fieldConf);
		if(result > 0) return 1;
		return 0;
	}

	/**
	 * @author caixl
	 * @date Jun 26, 2013
	 * @description TODO 根据元数据标识获取元数据信息
	 * @param id 元数据标识
	 * @return FieldConf
	 */
	@Override
	public FieldConf getMetadataINfoById(String id) {
		return fieldConfMapper.selectByPrimaryKey(id);
	}

	/**
	 * @author caixl
	 * @date Jun 26, 2013
	 * @description TODO 逻辑删除元数据相关信息
	 * @param ids 元数据标识数组
	 * @return int 删除返回标识
	 */
	@Override
	public int removeMetadataByIds(String[] ids) {
		int doc = 0,dset = 0;
		List<String> list = new ArrayList<String>();
		for(int i = 0;i < ids.length ;i++){
			int count = 0;
			int count1 = 0;
			count = fieldConfMapper.metadataExistsDataSet(ids[i]);
			count1 = fieldConfMapper.metadataExistsDocMap(ids[i]);
			if(count > 0) {
				dset = 1;
				return 2;
			}
			if(count1 > 0) {
				doc = 1;
				return 2;
			}
			if(count < 1 && count1 <1){
				list.add(ids[i]);
			}
		}
		if(list.size() < 1){
			if(doc == 1){
				if(dset == 1){
					return 4;
				}else{
					return 3;
				}
			}else{
				if(dset == 1){
					return 2;
				}
			}
		}else{
			dset = fieldConfMapper.deleteMetadataByIds(list);
			if(dset > 0) return 1;
		}
		return 0;
	}

	/**
	 * @author caixl
	 * @date Jun 26, 2013
	 * @description TODO 获取数据集列表信息
	 * @param page
	 * @param dataSetConf 查询条件
	 * @return Pagination<DataSetConf>
	 */
	@Override
	public Pagination<DataSetConf> datasetPage(Pagination<DataSetConf> page,
			DataSetConf dataSetConf) {
		List<DataSetConf> list = dataSetConfMapper.datasetList(page.getRowBounds(),dataSetConf);
		int totalCount = dataSetConfMapper.datasetTotalCount(dataSetConf);
		page.setData(list);
		page.setTotalCount(totalCount);
		return page;
	}

	/**
	 * @author caixl
	 * @date Jun 27, 2013
	 * @description TODO 获取数据集的相关信息
	 * @param id
	 * @return DataSetConf
	 */
	@Override
	public DataSetConf getDataSetConfById(String id) {
		return dataSetConfMapper.selectByPrimaryKey(id);
	}

	/**
	 * @author caixl
	 * @date Jun 27, 2013
	 * @description TODO 保存数据集相关信息
	 * @param dataSetConf
	 * @return int
	 */
	@Override
	public int saveDataSetConf(DataSetConf dataSetConf) {
		int result = dataSetConfMapper.insertSelective(dataSetConf);
		if(result > 0) return 1;
		return 0;
	}

	/**
	 * @author caixl
	 * @date Jun 27, 2013
	 * @description TODO 更新数据集相关信息
	 * @param dataSetConf
	 * @return int
	 */
	@Override
	public int updateDataSetConf(DataSetConf dataSetConf) {
		int result = dataSetConfMapper.updateByPrimaryKey(dataSetConf);
		if(result > 0) return 1;
		return 0;
	}

	/**
	 * @author caixl
	 * @date Jun 27, 2013
	 * @description TODO 获取数据元列表信息
	 * @param f
	 * @return List<FieldConf>
	 */
	@Override
	public List<FieldConf> getMetadatasByQuery(FieldConf f) {
		return fieldConfMapper.getMetadatasByQuery(f);
	}

	/**
	 * @author caixl
	 * @date Jun 28, 2013
	 * @description TODO 保存 数据集 与 元数据的关系
	 * @param datasetMapping
	 * @return int
	 */
	@Override
	public int addMetadataInfo(DatasetMapping datasetMapping) {
		int res = datasetMappingMapper.insert(datasetMapping);
		if(res>0) return 1;
		return 0;
	}

	/**
	 * @author caixl
	 * @date Jun 28, 2013
	 * @description TODO 解除数据元与数据集的关系
	 * @param fieldId
	 * @param datasetId
	 * @return int
	 */
	@Override
	public int revokeMetadata(String[] fieldId, String datasetId) {
		for(String id : fieldId){
			DatasetMapping dd = new DatasetMapping();
			dd.setDatasetId(datasetId);
			dd.setFieldId(id);
			int result = datasetMappingMapper.revokeMetadata(dd);
			if(result < 1) return 0;
		}
		return 1;
	}

	/**
	 * @author caixl
	 * @date Jun 28, 2013
	 * @description TODO 查询某个数据集下的数据元
	 * @param page
	 * @param id
	 * @return Pagination<FieldConf>
	 */
	@Override
	public Pagination<FieldConf> datasetMetadataPage(
			Pagination<FieldConf> page, FieldConf fieldConf) {
		List<FieldConf> list = fieldConfMapper.datasetMetadataList(page.getRowBounds(),fieldConf);
		int totalCount = fieldConfMapper.datasetMetadataCount(fieldConf);
		page.setData(list);
		page.setTotalCount(totalCount);
		return page;
	}

	/**
	 * @author caixl
	 * @date Jun 28, 2013
	 * @description TODO 删除数据集相关信息
	 * @param ids
	 * @return int
	 */
	@Override
	public int removeDatasetInfo(String[] ids) {
		List<String> list = new ArrayList<String>();
		for(String id : ids){
			int n = datasetMappingMapper.getCountByDatasetId(id);
			if(n > 0){
				return 2;
			}else{
				list.add(id);
			}
		}
		if(list!=null && list.size()>0){
			int result = dataSetConfMapper.removeDatasetByIds(list);
			if(result > 0) return 1;
		}
		return 0;
	}

	/**
	 * @author caixl
	 * @date Jul 12, 2013
	 * @description TODO 判断是否存在 元数据编码
	 * @param fieldCode
	 * @param id
	 * @return int
	 */
	@Override
	public int isExistsFieldCode(String fieldCode,String id) {
		return fieldConfMapper.isExistsFieldCode(fieldCode,id);
	}

	/**
	 * @author caixl
	 * @date Jul 12, 2013
	 * @description TODO 判断是否存在 元数据标识符
	 * @param fieldId
	 * @param id
	 * @return int
	 */
	@Override
	public int isExistsFieldId(String fieldId,String id) {
		return fieldConfMapper.isExistsFieldId(fieldId,id);
	}

	/**
	 * @author caixl
	 * @date Jul 12, 2013
	 * @description TODO 获取匹配的值域信息
	 * @param id
	 * @param name
	 * @return List<Dictionary>
	 */
	@Override
	public List<DictionaryType> getListInfoById(String id, String name) {
		List<DictionaryType> list = null;
		if("4".equals(id)){
			list = dipDictionaryTypeMapper.getDictInfoByName(name);
		}else if("5".equals(id)){
			list = fieldConfMapper.getDictInfo2ByName(name);
		}
		return list;
	}

	/**
	 * @author caixl
	 * @date Jul 24, 2013
	 * @description TODO 元数据是否被使用
	 * @param id
	 * @return int
	 */
	@Override
	public int metadataIsUsingById(String id) {
		int count = 0;
		count = fieldConfMapper.metadataExistsDataSet(id);
		if(count > 0) return 1;
		count = fieldConfMapper.metadataExistsDocMap(id);
		if(count > 0) return 1;
		return 0;
	}

	/**
	 * @author caixl
	 * @date Jul 26, 2013
	 * @description TODO 验证是否存在相同版本和同名的数据集
	 * @param dataSetConf
	 * @return int
	 */
	@Override
	public int existsDatasetInfo(DataSetConf dataSetConf) {
		int count = dataSetConfMapper.existsDatasetInfo(dataSetConf);
		if(count > 0) return 1;
		return 0;
	}

	/**
	 * @author caixl
	 * @date Jul 26, 2013
	 * @description TODO 验证数据集中是否存在该元数据别名
	 * @param datasetMapping
	 * @return int
	 */
	@Override
	public int datasetExistsYsjbm(DatasetMapping datasetMapping) {
		int count = datasetMappingMapper.datasetExistsYsjbm(datasetMapping);
		if(count > 0) return 1;
		return 0;
	}

	/**
	 * 获取数据集相关信息集合
	 * @param name
	 * @return 
	 * List<DataSetConf>
	 */
	@Override
	public List<DataSetConf> getListByDatasetName(String name) {
		List<DataSetConf> list = dataSetConfMapper.getListByName(name);
		return list;
	}

}
