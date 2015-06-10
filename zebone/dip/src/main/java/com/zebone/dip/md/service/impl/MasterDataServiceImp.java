package com.zebone.dip.md.service.impl;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.md.MasterDataUtil;
import com.zebone.dip.md.dao.MasterDataFieldMapper;
import com.zebone.dip.md.dao.MasterDataMapper;
import com.zebone.dip.md.service.MasterDataService;
import com.zebone.dip.md.vo.MDContentObject;
import com.zebone.dip.md.vo.MasterData;
import com.zebone.dip.md.vo.MasterDataField;
import com.zebone.dip.md.vo.MasterDataQuery;
import com.zebone.dip.md.vo.NameCode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * 主数据管理service实现类
 * @author Yang Ying
 * @version 2013-7-4 下午2:10
 */

@Service("masterDataService")
public class MasterDataServiceImp implements MasterDataService{

    @Resource
    private MasterDataMapper masterDataMapper;

    @Resource
    private MasterDataFieldMapper masterDataFieldMapper;

    @Override
    public List getMasterDataByTypeId(String typeId) {
        return masterDataMapper.getMasterDataByTypeId(typeId);
    }

    @Override
    public Pagination getDataByCode(String code) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<MasterDataField> getFieldByMasterDataId(String masterDataId) {
        return masterDataFieldMapper.getFieldByMasterDataId(masterDataId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Pagination<MDContentObject> mtDetailPage(Pagination<MDContentObject> page, MasterDataQuery masterDataQuery) {
        // 查询结果
        List<HashMap<String, Object>> tempList = masterDataFieldMapper
                .getDetailObject(page.getRowBounds(), masterDataQuery);

        // 封装到对应的Page对象
        page.setData(MasterDataUtil.process(tempList, masterDataQuery.getDbFields()));
        page.setTotalCount(masterDataFieldMapper.getDetailObjectTotal(masterDataQuery));
        return page;
    }

    @Override
    public MDContentObject getMtDetailsById(MasterDataQuery masterDataQuery) {
        //根据ID得到查询结果
        List<HashMap<String, Object>> tempList = masterDataFieldMapper.getMtDetailsById(masterDataQuery);

        //得到对应的MDContentObject对象
        return MasterDataUtil.processObject(masterDataQuery.getDbFields(), tempList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int saveMDContentInfo(MasterDataQuery masterDataQuery) {
        int d = 0;

        //判断主数据编码是否已经存在,如果已经存在，不允许再进行保存
        MasterDataQuery masterDataQuery1 = new MasterDataQuery();
        masterDataQuery1.setDbName(masterDataQuery.getDbName());
        String[] dbFieldV = new String[0];
        List<String> valueList = masterDataQuery.getValueList();
        masterDataQuery1.setPrimaryKeyValue(valueList.get(0));
        String dbFields = masterDataQuery.getDbFields();
        if(dbFields!=null) dbFieldV = dbFields.split(",");
        for(int i=0; i<dbFieldV.length; i++){
            if("MD_CODE".equalsIgnoreCase(dbFieldV[i])){
                //dbFieldV中并不存在ID_字段，而valueList中存在ID_值，两者位数相差1
                masterDataQuery1.setFieldValue(valueList.get(i+1));
            }
        }
        int count = masterDataFieldMapper.countMDInfo(masterDataQuery1);
        if (count > 0) {
            return -1;
        } else {
            // 通过新增 删除达到更新的目的,此处需要物理删除
            if (StringUtils.isNotEmpty(masterDataQuery.getPrimaryKeyValue())) {
                d = masterDataFieldMapper.physicalDeleteMDContent(masterDataQuery);
            }

            // 新增
            int a = masterDataFieldMapper.saveMDContentInfo(masterDataQuery);
            return a + d;
        }
    }

    @Override
    public int removeMDContentInfo(MasterDataQuery masterDataQuery) {

        // 将ID字符传转化为list
        String[] mdId = masterDataQuery.getId().split(",");
        for (String id : mdId) {
            masterDataQuery.addId(id.trim());
        }
        int count = masterDataFieldMapper.removeMDContentInfo(masterDataQuery);
        if(count > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public MasterData getMasterDataById(String id) {
        return masterDataMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 根据主数据编码查询出主数据信息
     * @param code
     * @return
     * @author songjunjie
     */
    public MasterData getMasterDataByCode(String code) {
        return masterDataMapper.getByCode(code);
    }

    @Override
    public List<NameCode> getMDNameLic(MasterDataQuery masterDataQuery) {
        return masterDataFieldMapper.getMDNameLic(masterDataQuery);
    }

    @Override
    public List getMasterDataType() {
        return masterDataMapper.getAllMasterData();
    }
}
