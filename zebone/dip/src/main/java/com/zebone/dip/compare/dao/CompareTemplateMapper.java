package com.zebone.dip.compare.dao;

import java.util.List;

import com.zebone.btp.core.mybatis.Mapper;

/**
 * 日志操作数据库映射接口
 * @author YinCm
 * @version 2013-7-31 下午10:15:20
 */
@Mapper
public interface CompareTemplateMapper {

    /**
     * 医疗机构信息
     * @return 按模板中顺序排列，用引号内"**--**" 分隔，如：洛阳镇东序社区卫生服务站**--**10114104**--**10114000
     */
    List<String> searchBizMedicalOrgan();
    
    /**
     * 医疗机构人员信息
     * @return 按模板中顺序排列，用引号内"**--**" 分隔，如：洛阳镇东序社区卫生服务站**--**10114104**--**10114000
     */
    List<String> searchBizMedicalOrganStaff();
    
    /**
     * 行政区划信息
     * @return 按模板中顺序排列，用引号内"**--**" 分隔，如：洛阳镇东序社区卫生服务站**--**10114104**--**10114000
     */
    List<String> searchBizAdministrativeDivision();
    
    /**
     * 诊疗项目信息
     * @return 按模板中顺序排列，用引号内"**--**" 分隔，如：洛阳镇东序社区卫生服务站**--**10114104**--**10114000
     */
    List<String> searchBizDiagnoseTreatmentItem();
    
    /**
     * 药品信息
     * @return 按模板中顺序排列，用引号内"**--**" 分隔，如：洛阳镇东序社区卫生服务站**--**10114104**--**10114000
     */
    List<String> searchBizDrugInfo();
    
    /**
     * 字典信息
     * @return 按模板中顺序排列，用引号内"**--**" 分隔，如：洛阳镇东序社区卫生服务站**--**10114104**--**10114000
     */
    List<String> searchDictionaryInfo();
    
}