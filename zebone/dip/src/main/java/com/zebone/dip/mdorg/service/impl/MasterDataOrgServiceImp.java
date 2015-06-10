package com.zebone.dip.mdorg.service.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.dictionary.dao.DipDictionaryTypeMapper;
import com.zebone.dip.dictionary.vo.DipDictionaryType;
import com.zebone.dip.md.vo.MasterData;
import com.zebone.dip.md.vo.MasterDataField;
import com.zebone.dip.mdorg.dao.MasterDataFieldStruMapper;
import com.zebone.dip.mdorg.dao.MasterDataStruMapper;
import com.zebone.dip.mdorg.service.MasterDataOrgService;
import com.zebone.util.Encodes;
import com.zebone.util.UUIDUtil;


/**
 * 主数据结构管理service实现类
 * @author lin fengjian
 * @version 2014-4-25 下午2:10
 */

@Service("masterDataStruService")
public class MasterDataOrgServiceImp implements MasterDataOrgService{
	@Resource
	private MasterDataStruMapper masterDataStruMapper;

	@Resource
	public MasterDataFieldStruMapper masterDataFieldStruMapper;

    @Resource
    private DipDictionaryTypeMapper dipDictionaryTypeMapper;

	public Pagination<MasterData> MasterDataStruList(Pagination<MasterData> page,MasterData masterData)
	{
		if (masterData.getName() != null && !"".equals(masterData.getName())) {
			String name = Encodes.urlDecode(masterData.getName());
			masterData.setName(name);
		}
		if (masterData.getComment() != null && !"".equals(masterData.getComment())) {
			String comment = Encodes.urlDecode(masterData.getComment());
			masterData.setComment(comment);
		}
		List<MasterData> list = masterDataStruMapper.masterDataStruList(page.getRowBounds(), masterData);
        int totalCount = masterDataStruMapper.masterDataStruTotalCount(masterData);
        page.setData(list);
        page.setTotalCount(totalCount);
        return page;
		
	}
	@Override
	public Pagination<MasterDataField> masterDataFieldPage(
			Pagination<MasterDataField> page, MasterDataField masterDataField) {
		List<MasterDataField> list = masterDataFieldStruMapper.masterDataFieldList(page.getRowBounds(),masterDataField);
		int totalCount = masterDataFieldStruMapper.masterDataFieldTotalCount(masterDataField);
		page.setData(list);
		page.setTotalCount(totalCount);
		return page;
	}
	@Override
	public MasterData getmasterDataStruInfoById(String id) {
		
		return masterDataStruMapper.getmasterDataStruInfoById(id);
	}
	@Override
    public int saveMasterDataStruInfo(MasterData masterData) {
        int countMDName = masterDataStruMapper.countMasterDataName(masterData);
        int countMDTableName = masterDataStruMapper.countMDTableName(masterData);
        int countMDCode = masterDataStruMapper.countMDCode(masterData);
        if (countMDTableName > 0) { //说明该表名已经存在，不能再保存相同的表名
            return -1;
        } else if (countMDName > 0) { //说明该主数据名已经存在
            return -2;
        } else if (countMDCode > 0) { //说明该主数据编码已经存在
            return -3;
        } else {
            int result = masterDataStruMapper.saveMasterDataStruInfo(masterData);
            if (result > 0) { //MD_CODE,MD_NAME字段是每个主数据表结构都需要添加的
                MasterDataField masterDataField = new MasterDataField();
                masterDataField.setMasterDataId(masterData.getId());
                masterDataField.setFieldType("varchar");
                masterDataField.setNullable("0");
                masterDataField.setDisplayable("Y");
                masterDataField.setId(UUIDUtil.getUuid());
                masterDataField.setFieldName("MD_CODE");
                masterDataField.setDisplayName("编码");
                masterDataField.setComment("编码");
                masterDataField.setFieldLength("25");
                for (int i = 1; i <= 2; i++) {
                    if (i == 2) {
                        masterDataField.setId(UUIDUtil.getUuid());
                        masterDataField.setFieldName("MD_NAME");
                        masterDataField.setDisplayName("名称");
                        masterDataField.setComment("名称");
                        masterDataField.setFieldLength("100");
                    }
                    masterDataFieldStruMapper.saveMasterDataStruFieldInfo(masterDataField);
                }

            }
            return result;
        }
    }

    @Override
    public int updateMasterDataStruInfo(MasterData masterData) {
        int countMDName = masterDataStruMapper.countMasterDataName(masterData);
        int countMDTableName = masterDataStruMapper.countMDTableName(masterData);
        int countMDCode = masterDataStruMapper.countMDCode(masterData);
        if (countMDTableName > 0) {  //说明该表名已经存在，不能再保存相同的表名
            return -1;
        } else if (countMDName > 0) { //说明该主数据名已经存在
            return -2;
        } else if (countMDCode > 0) { //说明该主数据编码已经存在
            return -3;
        } else {
            return masterDataStruMapper.updateMasterDataStruInfo(masterData);
        }
	}

	public int removeMasterDataStruByIds(String[] ids) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < ids.length; i++) {
            String mdTableName = masterDataStruMapper.getMDTableName(ids[i]);
            int count = masterDataStruMapper.countMDTable(mdTableName);   //判断主数据表是否已经创建
            if (count < 1) {
                list.add(ids[i]);
            }
        }
        if (list.size() < 1) {
            return 2;
        } else {
            int count1 = masterDataStruMapper.deleteMasterDataStruByIds(list);
            if (count1 > 0) {
                masterDataFieldStruMapper.deleteMDFieldByMDIds(list);
                return 1;
            }
        }
        return 0;
    }
	@Override
	public MasterDataField getMasterDataStruFieldInfoById(String id) {
		
		return masterDataFieldStruMapper.selectByPrimaryKey(id);
	}
	@Override
	public int saveMasterDataStruFieldInfo(MasterDataField masterDataField) {
		int columnNameCount = masterDataFieldStruMapper.fieldNameCount(masterDataField);
        if(columnNameCount > 0){  //说明该字段名已经存在，不能再保存相同的字段
            return -1;
        } else {
            return masterDataFieldStruMapper.saveMasterDataStruFieldInfo(masterDataField);
        }
	}
	@Override
	public int updateMasterDataStruFieldInfo(MasterDataField masterDataField) {
		int columnNameCount = masterDataFieldStruMapper.fieldNameCount(masterDataField);
        if (columnNameCount > 0) {  //说明该字段名已经存在，不能再保存相同的字段
            return -1;
        } else {
            return masterDataFieldStruMapper.updateMasterDataStruFieldInfo(masterDataField);
        }
	}
	@Override
	public int removeMasterDataStruFieldByIds(String[] ids) {
		List<String> list = new ArrayList<String>(Arrays.asList(ids));
        if (list.size() < 1) {
            return 2;
        } else {
            int count1 = masterDataFieldStruMapper.deleteMasterDataStruFieldByIds(list);
            if (count1 > 0) return 1;
        }
        return 0;
	}

    @Override
    public int isMDTableExist(String mdTableName) {
        return masterDataStruMapper.countMDTable(mdTableName);
    }

    @Override
    public List<DipDictionaryType> getMatchInfo(String fieldType, String name) {
        List<DipDictionaryType> list = null;
        if("dt".equals(fieldType)){   //字典型
            list = dipDictionaryTypeMapper.getMatchDictInfo(name);
        }else if("md".equals(fieldType)){//主数据型
            list = dipDictionaryTypeMapper.getMatchMDInfo(name);
        }
        return list;
    }

    @Override
    public String generateSql(String masterDataId, String tableName) {
        String sql = "CREATE TABLE " + tableName + " (ID_ VARCHAR2(32) NOT NULL PRIMARY KEY, " +
                "MD_CODE VARCHAR2(25), MD_NAME NVARCHAR2(100), TIMESTAMP TIMESTAMP(6), IS_DELETED NUMBER(1) DEFAULT 0";
        List<MasterDataField> masterDataFieldLic = masterDataFieldStruMapper.getMDFieldLic(masterDataId);
        if (masterDataFieldLic != null && masterDataFieldLic.size() > 0) {
            for (MasterDataField masterDataField : masterDataFieldLic) {
                String fieldName = masterDataField.getFieldName();
                if (!"MD_NAME".equalsIgnoreCase(fieldName) && !"MD_CODE".equalsIgnoreCase(fieldName)
                        && !"TIMESTAMP".equalsIgnoreCase(fieldName) && !"IS_DELETED".equalsIgnoreCase(fieldName)) {
                    if ("number".equalsIgnoreCase(masterDataField.getFieldType())) {
                        sql = sql + ", " + fieldName + " NUMBER(" + masterDataField.getFieldLength() + ")";
                    } else if ("datatime".equalsIgnoreCase(masterDataField.getFieldType())) {
                        sql = sql + ", " + fieldName + " DATE";
                    } else {
                        sql = sql + ", " + fieldName + " NVARCHAR2(" + masterDataField.getFieldLength() + ")";
                    }
                }
            }
        }
        sql = sql + " )";
        return sql;
    }


}
