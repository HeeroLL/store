package com.zebone.ehrview.controller;

import com.zebone.core.web.BaseController;
import com.zebone.ehrview.service.BasicInfoService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;


/**
 * 个人基本信息管理
 *
 * @author 杨英
 * @version 2013-8-16 上午08:20
 */
@Controller
public class BasicInfoController extends BaseController {

    @Resource
    BasicInfoService basicInfoService;

    /**
     * 展示健康档案的个人基本信息
     * @param oMap
     * @param request
     * @param empiId
     * @param dataCode 文档类型
     * @return
     */
    @RequestMapping("basicInfo")
    public String showBasicInfo(ModelMap oMap, HttpServletRequest request, @RequestParam("empiId") String empiId,
                                @RequestParam("dataCode") String dataCode, @RequestParam("searchInfo") String searchInfo,
                                @RequestParam("patientInfo") String patientInfo) {
        String path = request.getSession().getServletContext().getRealPath("");
        String xmlPar = "<param><empi_id>"+empiId+"</empi_id><data_code>A_"+dataCode+"</data_code><doc_no></doc_no>" +
                "<search_info>"+searchInfo+"</search_info><patient_info>"+patientInfo+"</patient_info></param>";
        String xslFileName = path + "/WEB-INF/views/ehrview/basic_info.xsl";
        String htmlValue = getHtmlValue(xmlPar, xslFileName);
        oMap.put("htmlValue", htmlValue);
        return "ehrview/basic_info";
    }

    /**
     * 展示成人健康体检信息
     * @param oMap
     * @param request
     * @param empiId
     * @param dataCode 文档类型
     * @param docNo  文档编号
     * @return
     */
    @RequestMapping("healthCheck")
    public String showHealthCheckInfo(ModelMap oMap, HttpServletRequest request, @RequestParam("empiId") String empiId,
                                      @RequestParam("dataCode") String dataCode, @RequestParam("docNo") String docNo,
                                      @RequestParam("searchInfo") String searchInfo,
                                      @RequestParam("patientInfo") String patientInfo) {
        String path = request.getSession().getServletContext().getRealPath("");
        String xmlPar = "<param><empi_id>"+empiId+"</empi_id><data_code>A_"+dataCode+"</data_code><doc_no>"+docNo+"</doc_no>" +
                "<search_info>"+searchInfo+"</search_info><patient_info>"+patientInfo+"</patient_info></param>";
        String xslFileName = path + "/WEB-INF/views/ehrview/health_check.xsl";
        String htmlValue = getHtmlValue(xmlPar, xslFileName);
        oMap.put("htmlValue", htmlValue);
        oMap.put("empiId",empiId);
        oMap.put("searchInfo",searchInfo);
        oMap.put("patientInfo",patientInfo);
        return "ehrview/health_check";
    }

    /**
     * 展示高血压专档信息
     * @param oMap
     * @param request
     * @return
     */
    @RequestMapping("hypertensionRep")
    public String showHypertensionRepInfo(ModelMap oMap, HttpServletRequest request,  @RequestParam("empiId") String empiId,
                                          @RequestParam("dataCode") String dataCode,
                                          @RequestParam("searchInfo") String searchInfo,
                                          @RequestParam("patientInfo") String patientInfo) {
        String path = request.getSession().getServletContext().getRealPath("");
        String xmlPar = "<param><empi_id>"+empiId+"</empi_id><data_code>A_"+dataCode+"</data_code><doc_no></doc_no>" +
                "<search_info>"+searchInfo+"</search_info><patient_info>"+patientInfo+"</patient_info></param>";
        String xslFileName = path + "/WEB-INF/views/ehrview/hypertension_rep.xsl";
        String htmlValue = getHtmlValue(xmlPar, xslFileName);
        oMap.put("empiId", empiId);
        oMap.put("htmlValue", htmlValue);
        return "ehrview/hypertension_rep";
    }

    /**
     * 展示高血压随访详情
     * @param oMap
     * @param request
     * @return
     */
    @RequestMapping("hypertensionDetails")
    public String showHypertensionDetails(ModelMap oMap, HttpServletRequest request, @RequestParam("empiId") String empiId,
                                          @RequestParam("dataCode") String dataCode, @RequestParam("docNo") String docNo,
                                          @RequestParam("searchInfo") String searchInfo,
                                          @RequestParam("patientInfo") String patientInfo) {
        String path = request.getSession().getServletContext().getRealPath("");
        String xmlPar = "<param><empi_id>"+empiId+"</empi_id><data_code>A_"+dataCode+"</data_code><doc_no>"+docNo+"</doc_no>" +
                "<search_info>"+searchInfo+"</search_info><patient_info>"+patientInfo+"</patient_info></param>";
        String xslFileName = path + "/WEB-INF/views/ehrview/hypertension_details.xsl";
        String htmlValue = getHtmlValue(xmlPar, xslFileName);
        oMap.put("htmlValue", htmlValue);
        oMap.put("empiId", empiId);
        //高血压专档的文档类型编码
        oMap.put("dataCode", "B04.01.01.00");
        oMap.put("searchInfo",searchInfo);
        oMap.put("patientInfo",patientInfo);
        return "ehrview/hypertension_details";
    }

    /**
     * 展示糖尿病专档信息
     * @param oMap
     * @param request
     * @return
     */
    @RequestMapping("diabetesRep")
    public String showDiabetesRepInfo(ModelMap oMap, HttpServletRequest request, @RequestParam("empiId") String empiId,
                                      @RequestParam("dataCode") String dataCode,@RequestParam("searchInfo") String searchInfo,
                                      @RequestParam("patientInfo") String patientInfo) {
        String path = request.getSession().getServletContext().getRealPath("");
        String xmlPar = "<param><empi_id>"+empiId+"</empi_id><data_code>A_"+dataCode+"</data_code><doc_no></doc_no>" +
                "<search_info>"+searchInfo+"</search_info><patient_info>"+patientInfo+"</patient_info></param>";
        String xslFileName = path + "/WEB-INF/views/ehrview/diabetes_rep.xsl";
        String htmlValue = getHtmlValue(xmlPar, xslFileName);
        oMap.put("htmlValue", htmlValue);
        oMap.put("empiId", empiId);
        return "ehrview/diabetes_rep";
    }

    /**
     * 展示糖尿病随访详情
     * @param oMap
     * @param request
     * @return
     */
    @RequestMapping("diabetesDetails")
    public String showDiabetesDetails(ModelMap oMap, HttpServletRequest request,@RequestParam("empiId") String empiId,
                                      @RequestParam("dataCode") String dataCode, @RequestParam("docNo") String docNo,
                                      @RequestParam("searchInfo") String searchInfo,
                                      @RequestParam("patientInfo") String patientInfo) {
        String path = request.getSession().getServletContext().getRealPath("");
        String xmlPar = "<param><empi_id>"+empiId+"</empi_id><data_code>A_"+dataCode+"</data_code><doc_no>"+docNo+"</doc_no>" +
                "<search_info>"+searchInfo+"</search_info><patient_info>"+patientInfo+"</patient_info></param>";
        String xslFileName = path + "/WEB-INF/views/ehrview/diabetes_details.xsl";
        String htmlValue = getHtmlValue(xmlPar, xslFileName);
        oMap.put("htmlValue", htmlValue);
        oMap.put("empiId", empiId);
        //糖尿病专档的文档类型编码
        oMap.put("dataCode", "B04.02.01.00");
        oMap.put("searchInfo",searchInfo);
        oMap.put("patientInfo",patientInfo);
        return "ehrview/diabetes_details";
    }

    /**
     * 展示老年人保健随访详情
     * @param oMap
     * @param request
     * @return
     */
    @RequestMapping("healthCareDetails")
    public String showHealthCareDetails(ModelMap oMap, HttpServletRequest request, @RequestParam("empiId") String empiId,
                                        @RequestParam("dataCode") String dataCode, @RequestParam("docNo") String docNo,
                                        @RequestParam("searchInfo") String searchInfo,
                                        @RequestParam("patientInfo") String patientInfo) {
        String path = request.getSession().getServletContext().getRealPath("");
        String xmlPar = "<param><empi_id>"+empiId+"</empi_id><data_code>A_"+dataCode+"</data_code><doc_no>"+docNo+"</doc_no>" +
                "<search_info>"+searchInfo+"</search_info><patient_info>"+patientInfo+"</patient_info></param>";
        String xslFileName = path + "/WEB-INF/views/ehrview/healthCare_details.xsl";
        String htmlValue = getHtmlValue(xmlPar, xslFileName);
        oMap.put("htmlValue", htmlValue);
        oMap.put("empiId", empiId);
        oMap.put("searchInfo",searchInfo);
        oMap.put("patientInfo",patientInfo);
        return "ehrview/healthCare_details";
    }

    /**
     * 展示健康基本信息详情
     *
     * @param oMap
     * @param request
     * @return
     */
    @RequestMapping("healthInfoDetails")
    public String showHealthInfoDetails(ModelMap oMap, HttpServletRequest request, @RequestParam("empiId") String empiId,
                                        @RequestParam("dataCode") String dataCode) {
        String path = request.getSession().getServletContext().getRealPath("");
        String xmlPar = "<param><empi_id>"+empiId+"</empi_id><data_code>A_"+dataCode+"</data_code><doc_no></doc_no></param>";
        String xslFileName = path + "/WEB-INF/views/ehrview/basic_info.xsl";
        String htmlValue = getHtmlValue(xmlPar, xslFileName);
        oMap.put("htmlValue", htmlValue);
        oMap.put("healthInfo", "true");
        return "ehrview/basic_info";
    }

    /**
     * 展示门诊页面
     * @param oMap
     * @param request
     * @param empiId
     * @param dataCode 文档类型
     * @return
     */
    @RequestMapping("outPatient")
    public String showOutPatientPage(ModelMap oMap, HttpServletRequest request, @RequestParam("empiId") String empiId,
                                       @RequestParam("dataCode") String dataCode, @RequestParam("docNo") String docNo,
                                       @RequestParam("searchInfo") String searchInfo,
                                       @RequestParam("patientInfo") String patientInfo) {
        oMap.put("empiId",empiId);
        oMap.put("dataCode",dataCode);
        oMap.put("docNo",docNo);
        oMap.put("searchInfo",searchInfo);
        oMap.put("patientInfo",patientInfo);
        return "medicalService/outPatient";
    }

    /**
     * 展示门诊详细信息
     * @param oMap
     * @param request
     * @param empiId
     * @param dataCode 文档类型
     * @return
     */
    @RequestMapping("outPatientDetail")
    public String showOutPatientDetail(ModelMap oMap, HttpServletRequest request, @RequestParam("empiId") String empiId,
                                       @RequestParam("dataCode") String dataCode, @RequestParam("docNo") String docNo,
                                       @RequestParam("searchInfo") String searchInfo,
                                       @RequestParam("patientInfo") String patientInfo) {
        String path = request.getSession().getServletContext().getRealPath("");
        String xslFileName = path + "/WEB-INF/views/medicalService/outPatient_diag.xsl";
        if("C00.01.03.00".equals(dataCode)){ //门诊处方
            xslFileName = path + "/WEB-INF/views/medicalService/outPatient_recipe.xsl";
        }else if("C00.02.05.00".equals(dataCode)){ //手术记录
            xslFileName = path + "/WEB-INF/views/medicalService/operation_note.xsl";
        }else if("C00.03.01.00".equals(dataCode)){ //影像检查
            xslFileName = path + "/WEB-INF/views/medicalService/image_exam.xsl";
        }else if("C00.03.02.01".equals(dataCode)){ //实验室检验
            xslFileName = path + "/WEB-INF/views/medicalService/inspection_report.xsl";
        }
        String xmlPar = "<param><empi_id>" + empiId + "</empi_id><data_code>A_" + dataCode + "</data_code><doc_no>"+docNo+"</doc_no>" +
                "<search_info>" + searchInfo + "</search_info><patient_info>" + patientInfo + "</patient_info></param>";
        String htmlValue = getHtmlValue(xmlPar, xslFileName);
        oMap.put("htmlValue", htmlValue);
        return "medicalService/outPatient_details";
    }

    /**
     * 展示健康和疾病问题列表详情
     * @param oMap
     * @param request
     * @return
     */
    @RequestMapping("healthProblemDetails")
    public String showHealthProDetails(ModelMap oMap, HttpServletRequest request, @RequestParam("empiId") String empiId,
                                        @RequestParam("dataCode") String dataCode, @RequestParam("docNo") String docNo,
                                        @RequestParam("searchInfo") String searchInfo,
                                        @RequestParam("patientInfo") String patientInfo) {
        if("A00.01.01.00".equals(dataCode)){
            return showBasicInfo(oMap,request,empiId,dataCode,searchInfo,patientInfo);
        }else if("B04.01.01.00".equals(dataCode)){
            return showHypertensionRepInfo(oMap,request,empiId,dataCode,searchInfo,patientInfo);
        }else if("B04.01.02.00".equals(dataCode)){
            return showHypertensionDetails(oMap,request,empiId,dataCode,docNo,searchInfo,patientInfo);
        }else if("B04.02.01.00".equals(dataCode)){
            return showDiabetesRepInfo(oMap,request,empiId,dataCode,searchInfo,patientInfo);
        }else if("B04.02.02.00".equals(dataCode)){
            return showDiabetesDetails(oMap,request,empiId,dataCode,docNo,searchInfo,patientInfo);
        }else if("B04.04.01.00".equals(dataCode)){
            return showHealthCareDetails(oMap,request,empiId,dataCode,docNo,searchInfo,patientInfo);
        }else if("C00.04.01.00".equals(dataCode)){
            return showHealthCheckInfo(oMap,request,empiId,dataCode,docNo,searchInfo,patientInfo);
        }else if("C00.01.02.00".equals(dataCode)){
            return showOutPatientPage(oMap,request,empiId,dataCode,docNo,searchInfo,patientInfo);
        }
        return null;
    }
    /**
     * 根据xsl模板文件将WS接口返回的xml转成html
     * @param xmlPar 固定格式的xml参数
     * @param xslFileName xls模板文件
     * @return
     */
    public String getHtmlValue(String xmlPar, String xslFileName) {
        String htmlValue = "";
        //通过WS接口获取xml数据
        String result = basicInfoService.getHealthRecordInfo(xmlPar);
        try {
            if (result != null && !"".equals(result)) {
                Document document = DocumentHelper.parseText(result);
                TransformerFactory factory = TransformerFactory.newInstance();
                StreamSource xsl = new StreamSource(new FileInputStream(xslFileName));
                Transformer transformer = factory.newTransformer(xsl);

                DocumentSource docSource = new DocumentSource(document);
                StringWriter writer = new StringWriter();
                StreamResult docResult = new StreamResult(writer);
                transformer.transform(docSource, docResult);
                htmlValue = writer.toString();
                writer.flush();
                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return htmlValue;
    }

    /**
     * 根据xsl模板文件与xml文件返回html
     * @param xmlFileName
     * @param xslFileName xls模板文件
     * @return
     */
    public String getHtmlValue1(String xmlFileName, String xslFileName) {
        String htmlValue = "";
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new File(xmlFileName));
            TransformerFactory factory = TransformerFactory.newInstance();
            StreamSource xsl = new StreamSource(new FileInputStream(xslFileName));
            Transformer transformer = factory.newTransformer(xsl);
            DocumentSource docSource = new DocumentSource(document);
            StringWriter writer = new StringWriter();
            StreamResult docResult = new StreamResult(writer);
            transformer.transform(docSource, docResult);
            htmlValue = writer.toString();
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return htmlValue;
    }
}
