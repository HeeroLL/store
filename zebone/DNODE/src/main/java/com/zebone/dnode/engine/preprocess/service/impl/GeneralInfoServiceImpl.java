package com.zebone.dnode.engine.preprocess.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zebone.dnode.engine.preprocess.PreprocessUtil;
import com.zebone.dnode.engine.preprocess.dao.ParseLogMapper;
import com.zebone.dnode.engine.preprocess.dao.PreprocessDataMapper;
import com.zebone.dnode.engine.preprocess.dao.ServiceInfoMapper;
import com.zebone.dnode.engine.preprocess.service.GeneralInfoService;
import com.zebone.dnode.engine.preprocess.vo.CompositeInfo;
import com.zebone.dnode.engine.preprocess.vo.DmCurveInfo;
import com.zebone.dnode.engine.preprocess.vo.HbpCurveInfo;
import com.zebone.dnode.engine.preprocess.vo.LifeStyle;
import com.zebone.dnode.engine.preprocess.vo.ManageDoctorInfo;
import com.zebone.dnode.engine.preprocess.vo.ParseLogInfo;
import com.zebone.dnode.engine.preprocess.vo.PreprocessData;
import com.zebone.util.DateUtil;
import com.zebone.util.UUIDUtil;

/**
 * 预处理综合信息服务,如生活习惯,综合情况等
 *
 * @author 杨英
 * @version 2013-12-17 下午03:02
 */
@Service("generalInfoService")
public class GeneralInfoServiceImpl implements GeneralInfoService{

    private static final Log log = LogFactory.getLog(GeneralInfoServiceImpl.class);

    @Resource
    private ParseLogMapper parseLogMapper;

    @Resource
    private PreprocessDataMapper preprocessDataMapper;

    @Resource
    private ServiceInfoMapper serviceInfoMapper;

    @Override
    public void preprocessLifeStyleInfo() {
        //与生活习惯相关的业务表信息
        List<String> tableNames = new ArrayList<String>();
        tableNames.add("TBL_HC_LIFESTYLE");
        tableNames.add("TBL_HC_DRINKTYPE");
        preprocessInfo(tableNames,"011");
    }

    @Override
    public void preprocessHbpCurveInfo() {
        //与高血压相关的业务表信息
        List<String> tableNames = new ArrayList<String>();
        tableNames.add("TBL_HBPFU_SIGN");
        tableNames.add("TBL_HBP_FOLLOWUP");
        preprocessInfo(tableNames,"009");
    }

    @Override
    public void preprocessDmCurveInfo() {
        //与糖尿病相关的业务表信息
        List<String> tableNames = new ArrayList<String>();
        tableNames.add("TBL_DM_FOLLOWUP");
        preprocessInfo(tableNames,"010");
    }

    @Override
    public void preprocessManageDoctorInfo() {
        //与管理医生相关的业务表信息
        List<String> tableNames = new ArrayList<String>();
        tableNames.add("TBL_PSON_HEALTH_RECORD");
        preprocessInfo(tableNames,"012");
    }

    @Override
    public void preprocessCompositeInfo() {
        //与综合情况相关的业务表信息
        List<String> tableNames = new ArrayList<String>();
        tableNames.add("TBL_PSON_GENETIC_HT");
        preprocessInfo(tableNames,"004");
    }

    /**
     * 预处理指定业务块信息
     */
    private void preprocessInfo(List<String> tableNames, String dataCode) {
        //获取与某业务块相关的未处理信息列表,如改列表为空,则说明该业务块的信息不需重新加工
        List<ParseLogInfo> parseInfoLic = parseLogMapper.getUndressedInfo(tableNames);

        //高血压与糖尿病曲线数据每月进行加工，不管日志变化表中有无未处理的数据
        if("009".equals(dataCode) || "010".equals(dataCode)){
            parseInfoLic = parseLogMapper.getParseLogInfoForCurve(tableNames);
        }

        //需要重新加工的数据列表
        List<PreprocessData> preprocessDataList = new ArrayList<PreprocessData>();

        //业务文档变化日志信息的主键列表
        List<String> ids = new ArrayList<String>();

        //存储EMPI ID信息,若相同的empi存在两份同类型的未加工文档数据，过滤后取最新的一份
        Map<String, String> empiIdMap = new HashMap<String, String>();

        //获取未加工数据的map信息,key为empiNo和docNo,值为id
        Map<String, String> oMap = PreprocessUtil.getUndressedIdMapInfo(parseInfoLic);

        for (Map.Entry<String, String> entry : oMap.entrySet()) {
            PreprocessData preprocessData = new PreprocessData();

            String[] empiNoAndDocNoLic = new String[2];
            String empiNoAndDocNo = entry.getKey();
            if (empiNoAndDocNo != null) empiNoAndDocNoLic = empiNoAndDocNo.split("&");

            String curTime = DateUtil.getCurrentDate("yyyyMMdd");
            preprocessData.setEmpiNo(empiNoAndDocNoLic[0]);
            preprocessData.setDataCode(dataCode);
            preprocessData.setCreateTime(curTime);

            String id = entry.getValue();
            if (id != null && !"".equals(id)) {
                String[] idList = id.split(",");

                if (idList != null && idList.length > 0) {
                    for (int i = 0; i < idList.length; i++) {
                        ids.add(idList[i]);
                    }
                    if (empiIdMap.get(empiNoAndDocNoLic[0]) == null) {
                        String tempXml = "";
                        if ("004".equals(dataCode)) {
                            tempXml = getXmlCompositeInfo(empiNoAndDocNoLic[0], empiNoAndDocNoLic[1]);
                        } else if ("009".equals(dataCode)) {
                            tempXml = getXmlHbpCurveInfo(empiNoAndDocNoLic[0], empiNoAndDocNoLic[1]);
                        } else if ("010".equals(dataCode)) {
                            tempXml = getXmlDmCurveInfo(empiNoAndDocNoLic[0], empiNoAndDocNoLic[1]);
                        } else if ("011".equals(dataCode)) {
                            tempXml = getXmlLifeStyleInfo(empiNoAndDocNoLic[0], empiNoAndDocNoLic[1]);
                        } else if ("012".equals(dataCode)) {
                            tempXml = getXmlManageDoctorInfo(empiNoAndDocNoLic[0], empiNoAndDocNoLic[1]);
                        }
                        preprocessData.setTempXml(tempXml);
                        //判断该业务块在数据库中是否已经存在
                        String id1 = preprocessDataMapper.getIdByEmpiAndDataCd(preprocessData);
                        if (id1 != null && !"".equals(id1)) {
                            preprocessData.setId(id1);
                        } else {
                            preprocessData.setId(UUIDUtil.getUuid());
                        }
                        preprocessDataList.add(preprocessData);
                        empiIdMap.put(empiNoAndDocNoLic[0], empiNoAndDocNoLic[0]);
                    }
                }
            }
        }
        try {
            updatePreprocessData(preprocessDataList);
            updateParseDataFlag(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 把生活习惯信息加工成xml格式数据
     *
     * @param empiNo
     * @param docNo
     */
    private String getXmlLifeStyleInfo(String empiNo, String docNo) {
        StringBuffer sbLifeStyleInfo = new StringBuffer();
        //用于传递参数
        Map<String, String> oMap = new HashMap<String, String>();
        oMap.put("empiNo", empiNo);
        oMap.put("docNo", docNo);
        LifeStyle lifeStyle = serviceInfoMapper.getLifeStyle(oMap);
        sbLifeStyleInfo.append("<entity code=\"011\"><item code=\"YDPL\">");
        sbLifeStyleInfo.append(strTransfer(lifeStyle.getSportFreq()));
        sbLifeStyleInfo.append("</item><item code=\"YSXG\">");
        sbLifeStyleInfo.append(strTransfer(lifeStyle.getDietCustom()));
        sbLifeStyleInfo.append("</item><item code=\"XYZK\">");
        sbLifeStyleInfo.append(strTransfer(lifeStyle.getSmokeStatus()));
        sbLifeStyleInfo.append("</item><item code=\"YJZL\">");
        sbLifeStyleInfo.append(formatInfo(serviceInfoMapper.getDrinkType(oMap)));
        sbLifeStyleInfo.append("</item><item code=\"ZYMS\">");
        sbLifeStyleInfo.append(strTransfer(lifeStyle.getOccHazardousDesc()));
        sbLifeStyleInfo.append("</item><item code=\"ZYSC\">");
        sbLifeStyleInfo.append(strTransfer(lifeStyle.getOccHazardousSt()));
        sbLifeStyleInfo.append("</item></entity>");
        return sbLifeStyleInfo.toString();
    }

    /**
     * 把随访血压值加工成xml格式数据
     *
     * @param empiNo
     * @param docNo
     */
    private String getXmlHbpCurveInfo(String empiNo, String docNo) {
        StringBuffer sbHbpCurveInfo = new StringBuffer();
        //用于传递参数
        Map<String, String> oMap = new HashMap<String, String>();
        oMap.put("empiNo", empiNo);
        oMap.put("docNo", docNo);
        List<HbpCurveInfo> hbpCurveInfoLic = serviceInfoMapper.getHbpCurveInfo(oMap);
        //高血压曲线值Map,键为"随访日期(yyyyMM)",值为"收缩压-舒张压"
        Map<String, String> resultMap = new HashMap<String, String>();
        if (hbpCurveInfoLic != null && hbpCurveInfoLic.size() > 0) {
            for (int i = 0; i < hbpCurveInfoLic.size(); i++) {
                HbpCurveInfo hbpCurveInfo = hbpCurveInfoLic.get(i);
                String strKey = hbpCurveInfo.getVisitDate().substring(0,6);
                if (resultMap.get(strKey) == null) { //如某个月份有多个值，取最新的
                    resultMap.put(strKey, hbpCurveInfo.getSbp() + "-" + hbpCurveInfo.getDbp());
                }
            }
        }
        sbHbpCurveInfo.append("<entity code=\"009\">");
        for (int i = -11; i <= 0; i++) {
            String reduceMonth = DateUtil.getReduceMonth("yyyyMM", i); //当前日期减去若干月后的时间
            //高血压曲线横坐标值(月份),始:当前月份往前11个月 止:当前月份 共:12个月份值
            sbHbpCurveInfo.append("<item code=\"GXYHZB" + (i + 12) + "\">" + reduceMonth.substring(4, 6) + "</item>");
            String hbpValue = resultMap.get(reduceMonth);
            if (hbpValue != null && !"".equals(hbpValue)) {
                String[] hbpValues = hbpValue.split("-");
                sbHbpCurveInfo.append("<item code=\"SSY" + (i + 12) + "\">" + hbpValues[0] + "</item>");
                sbHbpCurveInfo.append("<item code=\"SZY" + (i + 12) + "\">" + hbpValues[1] + "</item>");
            }
        }
        sbHbpCurveInfo.append("</entity>");
        return sbHbpCurveInfo.toString();
    }

    /**
     * 把随访血糖值加工成xml格式数据
     *
     * @param empiNo
     * @param docNo
     */
    private String getXmlDmCurveInfo(String empiNo, String docNo) {
        StringBuffer sbDmCurveInfo = new StringBuffer();
        //用于传递参数
        Map<String, String> oMap = new HashMap<String, String>();
        oMap.put("empiNo", empiNo);
        oMap.put("docNo", docNo);
        List<DmCurveInfo> dmCurveInfoLic = serviceInfoMapper.getDmCurveInfo(oMap);
        //糖尿病曲线值Map,键为"随访日期(yyyyMM)",值为"空腹血糖值"
        Map<String, String> resultMap = new HashMap<String, String>();
        if (dmCurveInfoLic != null && dmCurveInfoLic.size() > 0) {
            for (int i = 0; i < dmCurveInfoLic.size(); i++) {
                DmCurveInfo dmCurveInfo = dmCurveInfoLic.get(i);
                String strKey = dmCurveInfo.getVisitDate().substring(0,6);
                if (resultMap.get(strKey) == null) { //如某个月份有多个值,取最新的
                    resultMap.put(strKey, dmCurveInfo.getFbg());
                }
            }
        }
        sbDmCurveInfo.append("<entity code=\"010\">");
        for (int i = -11; i <= 0; i++) {
        	String reduceMonth = DateUtil.getReduceMonth("yyyyMM", i); //当前日期减去若干月后的时间
        	//糖尿病曲线横坐标值(月份),始:当前月份往前11个月 止:当前月份 共:12个月份值
            sbDmCurveInfo.append("<item code=\"XTHZB" + (i + 12) + "\">" + reduceMonth.substring(4, 6) + "</item>");
            String fbg = resultMap.get(reduceMonth);
            if (fbg != null && !"".equals(fbg)) {
                sbDmCurveInfo.append("<item code=\"XT" + (i + 12) + "\">" + fbg + "</item>");
            }
        }
        sbDmCurveInfo.append("</entity>");
        return sbDmCurveInfo.toString();
    }

    /**
     * 把管理医生信息加工成xml格式数据
     *
     * @param empiNo
     * @param docNo
     */
    private String getXmlManageDoctorInfo(String empiNo, String docNo) {
        StringBuffer sbManageDoctorInfo = new StringBuffer();
        //用于传递参数
        Map<String,String> oMap = new HashMap<String, String>();
        oMap.put("empiNo",empiNo);
        oMap.put("docNo", docNo);
        ManageDoctorInfo manageDoctorInfo = serviceInfoMapper.getManageDoctorInfo(oMap);
        sbManageDoctorInfo.append("<entity code=\"012\"><item code=\"YSXM\">");
        sbManageDoctorInfo.append(manageDoctorInfo.getDoctorName());
        sbManageDoctorInfo.append("</item><item code=\"LXDH\">");
        sbManageDoctorInfo.append(manageDoctorInfo.getContactTel());
        sbManageDoctorInfo.append("</item><item code=\"SSJJ\">");
        sbManageDoctorInfo.append(manageDoctorInfo.getOrg());
        sbManageDoctorInfo.append("</item><item code=\"FWRQ\">");
        sbManageDoctorInfo.append(formatStr(manageDoctorInfo.getPreServiceDate()));
        sbManageDoctorInfo.append("</item></entity>");
        return sbManageDoctorInfo.toString();
    }

    /**
     * 把综合情况信息加工成xml格式数据
     *
     * @param empiNo
     * @param docNo
     */
    private String getXmlCompositeInfo(String empiNo, String docNo) {
        StringBuffer sbCompositeInfo = new StringBuffer();
        //用于传递参数
        Map<String,String> oMap = new HashMap<String, String>();
        oMap.put("empiNo",empiNo);
        oMap.put("docNo", docNo);
        CompositeInfo compositeInfo = serviceInfoMapper.getCompositeInfo(oMap);
        sbCompositeInfo.append("<entity code=\"004\"><item code=\"ABOXX\">");
        sbCompositeInfo.append(compositeInfo.getABOType());
        sbCompositeInfo.append("</item><item code=\"RHXX\">");
        sbCompositeInfo.append(compositeInfo.getRHType());
        sbCompositeInfo.append("</item><item code=\"YCS\">");
        sbCompositeInfo.append(compositeInfo.getGeneticHis());
        sbCompositeInfo.append("</item><item code=\"YWGMS\">");
        sbCompositeInfo.append(formatInfo(serviceInfoMapper.getAllergyInfo(oMap)));
        sbCompositeInfo.append("</item><item code=\"JZS\">");
        sbCompositeInfo.append(formatInfo(serviceInfoMapper.getFamilyInfo(oMap)));
        sbCompositeInfo.append("</item><item code=\"CJLX\">");
        sbCompositeInfo.append(formatInfo(serviceInfoMapper.getDisabilityInfo(oMap)));
        sbCompositeInfo.append("</item><item code=\"BMI\">");
        sbCompositeInfo.append(formatStr(serviceInfoMapper.getBMIValue(empiNo)));
        sbCompositeInfo.append("</item></entity>");
        return sbCompositeInfo.toString();
    }

    /**
     * 通过删除,新增来更新数据
     *
     * @param preprocessDtList
     */
    @Transactional(value = "transactionManager_dc", rollbackFor = Throwable.class)
    public void updatePreprocessData(List<PreprocessData> preprocessDtList) throws Exception {
        try {
            if (preprocessDtList != null && preprocessDtList.size() > 0) {
                preprocessDataMapper.deletePreprocessData(preprocessDtList);
                preprocessDataMapper.insertPreprocessData(preprocessDtList);
            }
        } catch (Exception e) {
            String errorDesc = "更新表TMP_DATA_PREPROCESS的信息时出错";
            log.error(errorDesc, e);
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    /**
     * 根据id列表把已加工的数据状态更新为"1"
     *
     * @param ids
     */
    public void updateParseDataFlag(List<String> ids) {
        for (String id : ids) {
            try {
                parseLogMapper.updateDataFlag(id);
            } catch (Exception e) {
                String errorDesc = "更新表P_PARSE_TABLE_LOG中的数据加工标志出错,ID=" + id;
                log.error(errorDesc, e);
            }
        }
    }

    private String formatInfo(List<String> strLic) {
        if (strLic == null || strLic.size() == 0) {
            return "无";
        } else {
            String returnVal = "";
            for (String str : strLic) {
                if (!"".equals(returnVal)) {
                    returnVal = returnVal + ",";
                }
                returnVal = returnVal + strTransfer(str);
            }
            return returnVal;
        }
    }

    private String strTransfer(String str) {
        if (str != null && !"".equals(str)) {
            return str.replace("<", "&lt;");
        } else {
            return "";
        }
    }

    private String formatStr(String str) {
        if (str == null) {
            return "";
        } else {
            return str;
        }
    }
}
