package com.zebone.dip.md.service;

import java.util.List;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.md.vo.MDContentObject;
import com.zebone.dip.md.vo.MasterData;
import com.zebone.dip.md.vo.MasterDataField;
import com.zebone.dip.md.vo.MasterDataQuery;
import com.zebone.dip.md.vo.NameCode;

/**
 * 主数据管理服务
 * @author songjunjie
 *
 */
public interface MasterDataService {
	
	/**
	 * 根据主数据类别ID，此类别下的所有主数据。
	 * @param typeId 类别ID
	 * @return 主数据列表
	 */
	public List getMasterDataByTypeId(String typeId);
	
	/**
	 * 根据主数据代码，得到主数据中的数据信息。
	 * @param code 主数据代码
	 * @return
	 */
	public Pagination getDataByCode(String code);

    /**
     * 根据主数据id，得到字段信息。
     * @param masterDataId
     * @return  字段列表
     */
    public List<MasterDataField> getFieldByMasterDataId (String masterDataId);

    /**
     * 根据条件获取主数据内容列表信息
     * @param masterDataQuery
     * @return Pagination<MDContentObject>
     */
    Pagination<MDContentObject> mtDetailPage(Pagination<MDContentObject> page, MasterDataQuery masterDataQuery);

    /**
     * 根据ID获取主数据具体表中的数据信息。
     * @param masterDataQuery
     * @return  MDContentObject 公用
     */
    MDContentObject getMtDetailsById(MasterDataQuery masterDataQuery);

    /**
     * 保存内容信息到具体的主数据表中。
     * @param masterDataQuery
     * @return
     */
    int saveMDContentInfo(MasterDataQuery masterDataQuery);

    /**
     * 根据ids，批量删除主数据。
     * @param masterDataQuery
     * @return
     */
    int removeMDContentInfo(MasterDataQuery masterDataQuery);

    /**
     * 根据id，获取主数据。
     * @param id 主数据ID主键
     * @return
     */
    MasterData getMasterDataById (String id);
    
    /**
     * 根据主数据编码查询出主数据信息
     * @param code
     * @return
     * @author songjunjie
     */
    public MasterData getMasterDataByCode(String code);

    /**
     * 获取主数据具体表中MD_NAME字段的信息列表
     * @param  masterDataQuery
     * @return 字符串列表
     */
    List<NameCode> getMDNameLic (MasterDataQuery masterDataQuery);

    /**
     * 获取所有的主数据类型
     * @return 主数据列表
     */
    public List getMasterDataType();
	
}
