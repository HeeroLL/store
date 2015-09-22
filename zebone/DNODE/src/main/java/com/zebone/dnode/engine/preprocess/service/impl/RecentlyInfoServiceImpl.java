package com.zebone.dnode.engine.preprocess.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zebone.dnode.engine.preprocess.PreprocessUtil;
import com.zebone.dnode.engine.preprocess.dao.ParseLogMapper;
import com.zebone.dnode.engine.preprocess.dao.PreprocessDataMapper;
import com.zebone.dnode.engine.preprocess.dao.ServiceInfoMapper;
import com.zebone.dnode.engine.preprocess.service.RecentlyInfoService;
import com.zebone.dnode.engine.preprocess.vo.CommonForMedication;
import com.zebone.dnode.engine.preprocess.vo.ExamInfo;
import com.zebone.dnode.engine.preprocess.vo.FollowUpInfo;
import com.zebone.dnode.engine.preprocess.vo.InHospitalInfo;
import com.zebone.dnode.engine.preprocess.vo.InspectInfo;
import com.zebone.dnode.engine.preprocess.vo.MedicationInfo;
import com.zebone.dnode.engine.preprocess.vo.OutPatientInfo;
import com.zebone.dnode.engine.preprocess.vo.ParseLogInfo;
import com.zebone.dnode.engine.preprocess.vo.PreprocessData;
import com.zebone.dnode.engine.preprocess.vo.PreviousHisInfo;
import com.zebone.util.DateUtil;
import com.zebone.util.UUIDUtil;

/**
 * 预处理近期信息服务,如近期检查,近期检验等
 *
 * @author 杨英
 * @version 2013-12-16 上午08:55
 */
@Service("recentlyInfoService")
public class RecentlyInfoServiceImpl implements RecentlyInfoService {

    private static final Log log = LogFactory.getLog(RecentlyInfoServiceImpl.class);

    @Resource
    private ParseLogMapper parseLogMapper;

    @Resource
    private PreprocessDataMapper preprocessDataMapper;

    @Resource
    private ServiceInfoMapper serviceInfoMapper;

    /**
     * 预处理近期检查信息
     */
    @Override
    public void preprocessExamInfo() {
        //与检查相关的业务表信息
        List<String> tableNames = new ArrayList<String>();
        tableNames.add("TBL_IMAGE_EXAM");
        preprocessInfo(tableNames,"013");
    }

    /**
     * 预处理近期检验信息
     */
    @Override
    public void preprocessInspectInfo() {
        //与检验相关的业务表信息
        List<String> tableNames = new ArrayList<String>();
        tableNames.add("TBL_INSEPECT_REPORT");
        preprocessInfo(tableNames,"014");
    }

    /**
     * 预处理近期门诊信息
     */
    @Override
    public void preprocessOutPatientInfo() {
        //与门诊相关的业务表信息
        List<String> tableNames = new ArrayList<String>();
        tableNames.add("TBL_OUTPATIENT_DIAG_DETAIL");
        tableNames.add("TBL_OUTPATIENT_DIAG");
        preprocessInfo(tableNames,"016");
    }

    /**
     * 预处理近期用药信息
     */
    @Override
    public void preprocessMedicationInfo() {
        //与用药相关的业务表信息
        List<String> tableNames = new ArrayList<String>();
        //门诊处方
        tableNames.add("TBL_OUTPATIENT_RECIPE");
        tableNames.add("TBL_OUTPATIENT_RECIPE_DETAIL");
        //住院医嘱
        tableNames.add("TBL_INHOSPITAL_ORDER");
        tableNames.add("TBL_INHOSPITAL_ORDER_DRUG");
        //高血压随访用药
        tableNames.add("TBL_HBPFU_MEDICATIONS");
        //糖尿病随访用药
        tableNames.add("TBL_DMFU_MEDICATIONS");
        //老年人保健随访用药
        tableNames.add("TBL_ELDERFU_MEDICATIONS");
        preprocessInfo(tableNames,"015");
    }

    /**
     * 预处理近期住院信息
     */
    @Override
    public void preprocessInHospitalInfo() {
        //与住院相关的业务表信息
        List<String> tableNames = new ArrayList<String>();
        tableNames.add("TBL_INHOSPITAL_REG");
        tableNames.add("TBL_INHOSPITAL_REG_SYMPTOM");
        preprocessInfo(tableNames,"017");
    }

    /**
     * 预处理近期随访信息
     */
    @Override
    public void preprocessFollowUpInfo() {
        //与随访相关的业务表信息
        List<String> tableNames = new ArrayList<String>();
        tableNames.add("TBL_HBP_FOLLOWUP");
        tableNames.add("TBL_DM_FOLLOWUP");
        tableNames.add("TBL_ELDERFU");
        preprocessInfo(tableNames,"018");
    }

    /**
     * 预处理疾病史信息
     */
    @Override
    public void preprocessDiseasesInfo() {
        //与疾病史相关的业务表信息
        List<String> tableNames = new ArrayList<String>();
        tableNames.add("TBL_PSON_DISEASES_HT");
        preprocessInfo(tableNames, "005");
    }

    /**
     * 预处理手术史信息
     */
    @Override
    public void preprocessOperationInfo() {
        //与手术史相关的业务表信息
        List<String> tableNames = new ArrayList<String>();
        tableNames.add("TBL_PSON_OPERATION_HT");
        preprocessInfo(tableNames, "006");
    }

    /**
     * 预处理外伤史信息
     */
    @Override
    public void preprocessRtumHisInfo() {
        //与外伤史相关的业务表信息
        List<String> tableNames = new ArrayList<String>();
        tableNames.add("TBL_PSON_RTUM_HT");
        preprocessInfo(tableNames, "007");
    }

    /**
     * 预处理输血史信息
     */
    @Override
    public void preprocessTransfusionInfo() {
        //与输血史相关的业务表信息
        List<String> tableNames = new ArrayList<String>();
        tableNames.add("TBL_PSON_TRANSFUSION_HT");
        preprocessInfo(tableNames, "008");
    }


    /**
     * 预处理近期列表信息
     */
    private void preprocessInfo(List<String> tableNames, String dataCode) {
        //获取指定的未处理信息列表
        List<ParseLogInfo> parseInfoLic = parseLogMapper.getUndressedInfo(tableNames);

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
                        if ("013".equals(dataCode)) {
                            tempXml = getXmlExamInfo(empiNoAndDocNoLic[0], empiNoAndDocNoLic[1]);
                        } else if ("014".equals(dataCode)) {
                            tempXml = getXmlInspectInfo(empiNoAndDocNoLic[0], empiNoAndDocNoLic[1]);
                        } else if ("015".equals(dataCode)){
                            tempXml = getXmlMedicationInfo(empiNoAndDocNoLic[0]);
                        } else if ("016".equals(dataCode)) {
                            tempXml = getXmlOutpatientInfo(empiNoAndDocNoLic[0], empiNoAndDocNoLic[1]);
                        } else if ("017".equals(dataCode)) {
                            tempXml = getXmlInHospitalInfo(empiNoAndDocNoLic[0], empiNoAndDocNoLic[1]);
                        } else if ("018".equals(dataCode)) {
                            tempXml = getXmlFollowUpInfo(empiNoAndDocNoLic[0], empiNoAndDocNoLic[1]);
                        } else if ("005".equals(dataCode)) {
                            tempXml = getXmlDiseasesInfo(empiNoAndDocNoLic[0], empiNoAndDocNoLic[1]);
                        } else if ("006".equals(dataCode)) {
                            tempXml = getXmlOperationInfo(empiNoAndDocNoLic[0], empiNoAndDocNoLic[1]);
                        } else if ("007".equals(dataCode)) {
                            tempXml = getXmlRtumHisInfo(empiNoAndDocNoLic[0], empiNoAndDocNoLic[1]);
                        } else if ("008".equals(dataCode)) {
                            tempXml = getXmlTransfusionInfo(empiNoAndDocNoLic[0], empiNoAndDocNoLic[1]);
                        }
                        preprocessData.setTempXml(tempXml);
                        //判断该业务块在数据库中是否已经存在
                        String id1 = preprocessDataMapper.getIdByEmpiAndDataCd(preprocessData);
                        if (id1 != null && !"".equals(id1)) {
                            preprocessData.setId(id1);
                        } else {
                            preprocessData.setId(UUIDUtil.getUuid());
                        }
                        if (!StringUtils.isEmpty(preprocessData.getTempXml())) {
                            preprocessDataList.add(preprocessData);
                        }
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
     * 把近期检查信息加工成xml格式数据
     *
     * @param empiNo
     * @param docNo
     */
    private String getXmlExamInfo(String empiNo, String docNo) {
        StringBuffer sbExamInfo = new StringBuffer();
        //用于传递参数
        Map<String,String> oMap = new HashMap<String, String>();
        oMap.put("empiNo",empiNo);
        oMap.put("docNo", docNo);
        List<ExamInfo> examInfoLic = serviceInfoMapper.getExamInfo(oMap);
        if(examInfoLic!=null && examInfoLic.size()>0){
            sbExamInfo.append("<entity code=\"013\">");
            for(int i=0; i<examInfoLic.size(); i++){
                ExamInfo examInfo = examInfoLic.get(i);
                String rptTime = examInfo.getRptTime();
                if (rptTime != null && rptTime.length() >= 7) {
                    rptTime = rptTime.substring(0, 8);
                }
                sbExamInfo.append("<item code=\"JCRQ"+(i+1)+"\">"+rptTime+"</item>");
                sbExamInfo.append("<item code=\"JCMC"+(i+1)+"\">"+examInfo.getExamName()+"</item>");
                sbExamInfo.append("<item code=\"JCJG"+(i+1)+"\">"+examInfo.getImageFinding()+"</item>");
                sbExamInfo.append("<item code=\"JCYY"+(i+1)+"\">"+examInfo.getServiceOrg()+"</item>");
            }
            sbExamInfo.append("</entity>");
        }
        return sbExamInfo.toString();
    }

    /**
     * 把近期检验信息加工成xml格式数据
     *
     * @param empiNo
     * @param docNo
     */
    private String getXmlInspectInfo(String empiNo, String docNo) {
        StringBuffer sbInspectInfo = new StringBuffer();
        //用于传递参数
        Map<String,String> oMap = new HashMap<String, String>();
        oMap.put("empiNo",empiNo);
        oMap.put("docNo", docNo);
        List<InspectInfo> inspectInfoLic = serviceInfoMapper.getInspectInfo(oMap);
        if(inspectInfoLic!=null && inspectInfoLic.size()>0){
            sbInspectInfo.append("<entity code=\"014\">");
            for(int i=0; i<inspectInfoLic.size(); i++){
                InspectInfo inspectInfo = inspectInfoLic.get(i);
                String inspectDate = inspectInfo.getInspectDate();
                if (inspectDate != null && inspectDate.length() >= 7) {
                    inspectDate = inspectDate.substring(0, 8);
                }
                sbInspectInfo.append("<item code=\"JYRQ"+(i+1)+"\">"+inspectDate+"</item>");
                sbInspectInfo.append("<item code=\"JYMC"+(i+1)+"\">"+inspectInfo.getInspectName()+"</item>");
                sbInspectInfo.append("<item code=\"JYJG"+(i+1)+"\">"+inspectInfo.getInspectResult()+"</item>");
                sbInspectInfo.append("<item code=\"JYYY"+(i+1)+"\">"+inspectInfo.getServiceOrg()+"</item>");
            }
            sbInspectInfo.append("</entity>");
        }
        return sbInspectInfo.toString();
    }

    /**
     * 把近期门诊信息加工成xml格式数据
     *
     * @param empiNo
     * @param docNo
     */
    private String getXmlOutpatientInfo(String empiNo, String docNo) {
        StringBuffer sbOutPatientInfo = new StringBuffer();
        //用于传递参数
        Map<String,String> oMap = new HashMap<String, String>();
        oMap.put("empiNo",empiNo);
        oMap.put("docNo", docNo);
        List<OutPatientInfo> outPatientInfoLic = serviceInfoMapper.getOutPatientInfo(oMap);
        if(outPatientInfoLic!=null && outPatientInfoLic.size()>0){
            sbOutPatientInfo.append("<entity code=\"016\">");
            for(int i=0; i<outPatientInfoLic.size(); i++){
                OutPatientInfo outPatientInfo = outPatientInfoLic.get(i);
                String outPatientTime = outPatientInfo.getOutPatientTime();
                if (outPatientTime != null && outPatientTime.length() >= 7) {
                    outPatientTime = outPatientTime.substring(0, 8);
                }
                sbOutPatientInfo.append("<item code=\"MZRQ"+(i+1)+"\">"+outPatientTime+"</item>");
                sbOutPatientInfo.append("<item code=\"MZZD"+(i+1)+"\">"+outPatientInfo.getDiseaseDiag()+"</item>");
                sbOutPatientInfo.append("<item code=\"MZYY"+(i+1)+"\">"+outPatientInfo.getServiceOrg()+"</item>");
                sbOutPatientInfo.append("<item code=\"MZKS"+(i+1)+"\">"+outPatientInfo.getClinicDept()+"</item>");
            }
            sbOutPatientInfo.append("</entity>");
        }
        return sbOutPatientInfo.toString();
    }

    /**
     * 把用药信息加工成xml格式数据
     *
     * @param empiNo
     */
    private String getXmlMedicationInfo(String empiNo) {
        StringBuffer sbMedicationInfo = new StringBuffer();
        //用于传递参数
        Map<String,String> oMap = new HashMap<String, String>();
        oMap.put("empiNo",empiNo);

        //用药信息列表
        List<MedicationInfo> medicationInfoLic = new ArrayList<MedicationInfo>();

        CommonForMedication outPatientInfo = serviceInfoMapper.getLatestOutPatientInfo(empiNo);
        CommonForMedication inHospitalInfo = serviceInfoMapper.getLatestInHospitalInfo(empiNo);
        if ((outPatientInfo != null && !StringUtils.isEmpty(outPatientInfo.getLatestDt()))
                && (inHospitalInfo != null && !StringUtils.isEmpty(inHospitalInfo.getLatestDt()))) {
            Date outPatientDt = DateUtil.parseDate(outPatientInfo.getLatestDt(), "yyyymmdd");
            Date inHospitalDt = DateUtil.parseDate(inHospitalInfo.getLatestDt(), "yyyymmdd");
            if (outPatientDt.after(inHospitalDt)) { //门诊日期大于住院日期
                oMap.put("singleNo", outPatientInfo.getSingleNo()); //singleNo 为门(急)诊号
                medicationInfoLic = serviceInfoMapper.getOutPatientMedication(oMap);
            } else {
                oMap.put("singleNo", inHospitalInfo.getSingleNo());//singleNo 为住院号
                medicationInfoLic = serviceInfoMapper.getInHospitalMedication(oMap);
            }
        } else if ((outPatientInfo!=null && !StringUtils.isEmpty(outPatientInfo.getLatestDt()))
                && (inHospitalInfo == null || StringUtils.isEmpty(inHospitalInfo.getLatestDt()))) { //只有门诊日期,没有住院日期
            oMap.put("singleNo", outPatientInfo.getSingleNo()); //singleNo 为门(急)诊号
            medicationInfoLic = serviceInfoMapper.getOutPatientMedication(oMap);
        } else if ((inHospitalInfo!=null && !StringUtils.isEmpty(inHospitalInfo.getLatestDt()))
                && (outPatientInfo == null || StringUtils.isEmpty(outPatientInfo.getLatestDt()))) { //只有住院日期,没有门诊日期
            oMap.put("singleNo", inHospitalInfo.getSingleNo());//singleNo 为住院号
            medicationInfoLic = serviceInfoMapper.getInHospitalMedication(oMap);
        }
        //高血压随访用药
        List<MedicationInfo> hbpMedLic = serviceInfoMapper.getHbpMedication(empiNo);
        if (hbpMedLic != null && hbpMedLic.size() > 0) {
            medicationInfoLic.addAll(hbpMedLic);
        }
        //糖尿病随访用药
        List<MedicationInfo> dmMedLic = serviceInfoMapper.getDmMedication(empiNo);
        if (dmMedLic != null && dmMedLic.size() > 0) {
            medicationInfoLic.addAll(dmMedLic);
        }
        //老年人保健随访用药
        List<MedicationInfo> elderMedLic = serviceInfoMapper.getElderMedication(empiNo);
        if (elderMedLic != null && elderMedLic.size() > 0) {
            medicationInfoLic.addAll(elderMedLic);
        }

        if(medicationInfoLic!=null && medicationInfoLic.size()>0){
            sbMedicationInfo.append("<entity code=\"015\">");
            for(int i=0; i<medicationInfoLic.size(); i++){
                MedicationInfo medicationInfo = medicationInfoLic.get(i);
                String medicationDt = medicationInfo.getDate();
                if (medicationDt != null && medicationDt.length() >= 7) {
                    medicationDt = medicationDt.substring(0, 8);
                }
                sbMedicationInfo.append("<item code=\"YYFL"+(i+1)+"\">"+medicationInfo.getCategory()+"</item>");
                sbMedicationInfo.append("<item code=\"YYRQ"+(i+1)+"\">"+medicationDt+"</item>");
                sbMedicationInfo.append("<item code=\"YWMC"+(i+1)+"\">"+medicationInfo.getDrugName()+"</item>");
                sbMedicationInfo.append("<item code=\"YYYF"+(i+1)+"\">"+medicationInfo.getDrugUsage()+"</item>");
                sbMedicationInfo.append("<item code=\"YYTJ"+(i+1)+"\">"+medicationInfo.getDrugOpPath()+"</item>");
            }
            sbMedicationInfo.append("</entity>");
        }
        return sbMedicationInfo.toString();
    }

    /**
     * 把近期住院信息加工成xml格式数据
     *
     * @param empiNo
     * @param docNo
     */
    private String getXmlInHospitalInfo(String empiNo, String docNo) {
        StringBuffer sbInHospitalInfo = new StringBuffer();
        //用于传递参数
        Map<String,String> oMap = new HashMap<String, String>();
        oMap.put("empiNo",empiNo);
        oMap.put("docNo", docNo);
        List<InHospitalInfo> inHospitalInfoLic = serviceInfoMapper.getInHospitalInfo(oMap);
        if(inHospitalInfoLic!=null && inHospitalInfoLic.size()>0){
            sbInHospitalInfo.append("<entity code=\"017\">");
            for(int i=0; i<inHospitalInfoLic.size(); i++){
                InHospitalInfo inHospitalInfo = inHospitalInfoLic.get(i);
                sbInHospitalInfo.append("<item code=\"ZYRQ"+(i+1)+"\">"+inHospitalInfo.getInHosTime()+"</item>");
                sbInHospitalInfo.append("<item code=\"ZYZD"+(i+1)+"\">"+inHospitalInfo.getInHosDiag()+"</item>");
                sbInHospitalInfo.append("<item code=\"ZYYY"+(i+1)+"\">"+inHospitalInfo.getServiceOrg()+"</item>");
                sbInHospitalInfo.append("<item code=\"ZYKS"+(i+1)+"\">"+inHospitalInfo.getInHosDept()+"</item>");
            }
            sbInHospitalInfo.append("</entity>");
        }
        return sbInHospitalInfo.toString();
    }

    /**
     * 把近期随访信息加工成xml格式数据
     *
     * @param empiNo
     * @param docNo
     */
    private String getXmlFollowUpInfo(String empiNo, String docNo) {
        StringBuffer sbFollowUpInfo = new StringBuffer();
        //用于传递参数
        Map<String,String> oMap = new HashMap<String, String>();
        oMap.put("empiNo",empiNo);
        oMap.put("docNo", docNo);
        List<FollowUpInfo> followUpInfoLic = serviceInfoMapper.getFollowUpInfo(oMap);
        if(followUpInfoLic!=null && followUpInfoLic.size()>0){
            sbFollowUpInfo.append("<entity code=\"018\">");
            for(int i=0; i<followUpInfoLic.size() && i<3; i++){   //按随访日期倒叙排列,取前三条记录
                FollowUpInfo followUpInfo = followUpInfoLic.get(i);
                sbFollowUpInfo.append("<item code=\"SFRQ"+(i+1)+"\">"+followUpInfo.getFuVisitDate()+"</item>");
                sbFollowUpInfo.append("<item code=\"SFYY"+(i+1)+"\">"+followUpInfo.getServiceOrg()+"</item>");
                sbFollowUpInfo.append("<item code=\"SFYS"+(i+1)+"\">"+followUpInfo.getFuVisitDoctor()+"</item>");
            }
            sbFollowUpInfo.append("</entity>");
        }
        return sbFollowUpInfo.toString();
    }

    /**
     * 把疾病史信息加工成xml格式数据
     *
     * @param empiNo
     * @param docNo
     */
    private String getXmlDiseasesInfo(String empiNo, String docNo) {
        StringBuffer sbDiseasesInfo = new StringBuffer();
        //用于传递参数
        Map<String,String> oMap = new HashMap<String, String>();
        oMap.put("empiNo",empiNo);
        oMap.put("docNo", docNo);
        List<PreviousHisInfo> diseasesInfoLic = serviceInfoMapper.getDiseasesInfo(oMap);
        if(diseasesInfoLic!=null && diseasesInfoLic.size()>0){
            sbDiseasesInfo.append("<entity code=\"005\">");
            for(int i=0; i<diseasesInfoLic.size(); i++){
                PreviousHisInfo diseasesInfo = diseasesInfoLic.get(i);
                String diseaseTime = diseasesInfo.getTime();
                if (diseaseTime != null && diseaseTime.length() >= 7) {
                    diseaseTime = diseaseTime.substring(0, 8);
                }
                sbDiseasesInfo.append("<item code=\"JBSRQ"+(i+1)+"\">"+diseaseTime+"</item>");
                sbDiseasesInfo.append("<item code=\"JBSMC"+(i+1)+"\">"+diseasesInfo.getName()+"</item>");
            }
            sbDiseasesInfo.append("</entity>");
        }
        return sbDiseasesInfo.toString();
    }

    /**
     * 把手术史信息加工成xml格式数据
     *
     * @param empiNo
     * @param docNo
     */
    private String getXmlOperationInfo(String empiNo, String docNo) {
        StringBuffer sbOperationInfo = new StringBuffer();
        //用于传递参数
        Map<String,String> oMap = new HashMap<String, String>();
        oMap.put("empiNo",empiNo);
        oMap.put("docNo", docNo);
        List<PreviousHisInfo> operationInfoLic = serviceInfoMapper.getOperationInfo(oMap);
        if(operationInfoLic!=null && operationInfoLic.size()>0){
            sbOperationInfo.append("<entity code=\"006\">");
            for(int i=0; i<operationInfoLic.size(); i++){
                PreviousHisInfo operationInfo = operationInfoLic.get(i);
                String operationTime = operationInfo.getTime();
                if (operationTime != null && operationTime.length() >= 7) {
                    operationTime = operationTime.substring(0, 8);
                }
                sbOperationInfo.append("<item code=\"SSRQ" +(i+1)+ "\">" + operationTime + "</item>");
                sbOperationInfo.append("<item code=\"SSMC"+(i+1)+"\">"+operationInfo.getName()+"</item>");
            }
            sbOperationInfo.append("</entity>");
        }
        return sbOperationInfo.toString();
    }

    /**
     * 把外伤史信息加工成xml格式数据
     *
     * @param empiNo
     * @param docNo
     */
    private String getXmlRtumHisInfo(String empiNo, String docNo) {
        StringBuffer sbRtumHisInfo = new StringBuffer();
        //用于传递参数
        Map<String,String> oMap = new HashMap<String, String>();
        oMap.put("empiNo",empiNo);
        oMap.put("docNo", docNo);
        List<PreviousHisInfo> RtumHisInfoLic = serviceInfoMapper.getRtumHisInfo(oMap);
        if(RtumHisInfoLic!=null && RtumHisInfoLic.size()>0){
            sbRtumHisInfo.append("<entity code=\"007\">");
            for(int i=0; i<RtumHisInfoLic.size(); i++){
                PreviousHisInfo rtumHisInfo = RtumHisInfoLic.get(i);
                String rtumHisTime = rtumHisInfo.getTime();
                if (rtumHisTime != null && rtumHisTime.length() >= 7) {
                    rtumHisTime = rtumHisTime.substring(0, 8);
                }
                sbRtumHisInfo.append("<item code=\"WSRQ"+(i+1)+"\">"+rtumHisTime+"</item>");
                sbRtumHisInfo.append("<item code=\"WSMC"+(i+1)+"\">"+rtumHisInfo.getName()+"</item>");
            }
            sbRtumHisInfo.append("</entity>");
        }
        return sbRtumHisInfo.toString();
    }

    /**
     * 把输血史信息加工成xml格式数据
     *
     * @param empiNo
     * @param docNo
     */
    private String getXmlTransfusionInfo(String empiNo, String docNo) {
        StringBuffer sbTransfusionInfo = new StringBuffer();
        //用于传递参数
        Map<String,String> oMap = new HashMap<String, String>();
        oMap.put("empiNo",empiNo);
        oMap.put("docNo", docNo);
        List<PreviousHisInfo> TransfusionInfoLic = serviceInfoMapper.getTransfusionInfo(oMap);
        if(TransfusionInfoLic!=null && TransfusionInfoLic.size()>0){
            sbTransfusionInfo.append("<entity code=\"008\">");
            for(int i=0; i<TransfusionInfoLic.size(); i++){
                PreviousHisInfo transfusionInfo = TransfusionInfoLic.get(i);
                String transfusionTime = transfusionInfo.getTime();
                if (transfusionTime != null && transfusionTime.length() >= 7) {
                    transfusionTime = transfusionTime.substring(0, 8);
                }
                sbTransfusionInfo.append("<item code=\"SXRQ"+(i+1)+"\">"+transfusionTime+"</item>");
                sbTransfusionInfo.append("<item code=\"SXMC"+(i+1)+"\">"+transfusionInfo.getName()+"</item>");
            }
            sbTransfusionInfo.append("</entity>");
        }
        return sbTransfusionInfo.toString();
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
}
