package com.zebone.dip.compare.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.compare.dao.DictTypeOrgMapper;
import com.zebone.dip.compare.vo.DictInfo;
import com.zebone.dip.compare.vo.DictTypeOrg;
import com.zebone.util.DateUtil;
import com.zebone.util.UUIDUtil;

/**
 * 数据字典对照业务实现层
 * @author: caixl
 * @date： 日期：Jan 2, 2014
 * @version 1.0
 */
@Service("dictCompareService")
public class DictCompareServiceImpl implements DictCompareService {
	@Resource
	private DictTypeOrgMapper dictTypeOrgMapper;

	/**
	 * 获取机构下的字典类型的信息
	 * @param levelCode
	 * @return 
	 * List<DictTypeOrg>
	 */
	@Override
	public List<DictTypeOrg> getListByOrg(String levelCode) {
		
		return dictTypeOrgMapper.getListByCodeAndName(levelCode,"");
	}

	/**
	 * 获取机构下的字典类型信息
	 * @param levelCode 机构
	 * @param typeName 类型名称
	 * @return 
	 * List<DictTypeOrg>
	 */
	@Override
	public List<DictTypeOrg> getDictTypeByName(String levelCode, String typeName) {
		return dictTypeOrgMapper.getListByCodeAndName(levelCode,typeName);
	}

	/**
	 * 获取字典对照相关信息列表
	 * @param page
	 * @param dictInfo
	 * @return 
	 * Pagination<DictInfo>
	 */
	@Override
	public Pagination<DictInfo> getDictPage(Pagination<DictInfo> page,DictInfo dictInfo) {
		List<DictInfo> list = dictTypeOrgMapper.getDictList(page.getRowBounds(),dictInfo);
		int totalCount = dictTypeOrgMapper.getTotalCount(dictInfo);
		page.setData(list);
		page.setTotalCount(totalCount);
		return page;
	}

	/**
	 * 获取值域信息跟据名称或类型
	 * @param dictInfo
	 * @return 
	 * List<DictInfo>
	 */
	@Override
	public List<DictInfo> getDictListByName(DictInfo dictInfo) {
		
		return dictTypeOrgMapper.getDictByName(dictInfo);
	}

	/**
	 * 获取字典的详细对照信息
	 * @param orgDictId 字典类型标识
	 * @return 
	 * DictInfo
	 */
	@Override
	public DictInfo getDictInfoByOrgDictId(String orgDictId) {
		DictInfo dictInfo = dictTypeOrgMapper.getDictByOrgDictId(orgDictId);
		return dictInfo;
	}

	/**
	 * 自动匹配标准字典信息
	 * @param dictInfo
	 * @return 
	 * List<DictInfo>
	 */
	@Override
	public List<DictInfo> getDictListByNameAndTypeId(DictInfo dictInfo) {
		//List<String> typeIds = dictTypeOrgMapper.getIdsByOrgTypeId(dictInfo.getOrgDictTypeId());
		return dictTypeOrgMapper.getDictInfoByName(dictInfo);
	}

	/**
	 * 自动匹配标准字典类型数据
	 * @param name
	 * @return 
	 * List<DictTypeOrg>
	 */
	@Override
	public List<DictTypeOrg> getListByName(String name) {
		return dictTypeOrgMapper.getListByName(name);
	}

	/**
	 * 保存对照信息
	 * @param dictInfo
	 * @return 
	 * int
	 */
	@Override
	public int saveCompareInfo(DictInfo dictInfo) {
		if(StringUtils.isBlank(dictInfo.getId())){
			if(StringUtils.isBlank(dictInfo.getOrgDictTypeId())){
				String typeId = dictTypeOrgMapper.getCountTypeByInfo(dictInfo);
				if(StringUtils.isBlank(typeId)){
					dictInfo.setOrgDictTypeId(UUIDUtil.getUuid());
					dictTypeOrgMapper.saveDictType(dictInfo);
					String id = UUIDUtil.getUuid();
					dictInfo.setOrgDictId(id);
					dictTypeOrgMapper.saveDictInfo(dictInfo);
					dictInfo.setId(UUIDUtil.getUuid());
					dictInfo.setUserTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
					dictTypeOrgMapper.saveCompareInfo(dictInfo);
					return 3;
				}else{
					dictInfo.setOrgDictTypeId(typeId);
					int n= dictTypeOrgMapper.getCountByInfo(dictInfo);
					if(n>0){
						return 2;
					}
					String id = UUIDUtil.getUuid();
					dictInfo.setOrgDictId(id);
					dictTypeOrgMapper.saveDictInfo(dictInfo);
					dictInfo.setId(UUIDUtil.getUuid());
					dictInfo.setUserTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
					dictTypeOrgMapper.saveCompareInfo(dictInfo);
				}
			}else{
				int n= dictTypeOrgMapper.getCountByInfo(dictInfo);
				if(n>0){
					return 2;
				}
				String id = UUIDUtil.getUuid();
				dictInfo.setOrgDictId(id);
				dictTypeOrgMapper.saveDictInfo(dictInfo);
				dictInfo.setId(UUIDUtil.getUuid());
				dictInfo.setUserTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
				dictTypeOrgMapper.saveCompareInfo(dictInfo);
			}
		}else{
			dictTypeOrgMapper.updateCompareInfo(dictInfo);
		}
		return 1;
	}

	/**
	 * 删除对照字典数据
	 * @param id 
	 * void
	 */
	@Override
	public void removeCompare(String id) {
		String[] ids = id.split(";");
		for(int i=0;i<ids.length;i++){
			String[] data = ids[i].split(",");
			dictTypeOrgMapper.deleteOrgDictById(data[1]);
			dictTypeOrgMapper.deleteCompareById(data[0]);
		}
	}
}
