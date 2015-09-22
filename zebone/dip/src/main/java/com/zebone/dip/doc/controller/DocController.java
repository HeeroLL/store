package com.zebone.dip.doc.controller;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.zebone.dip.dictionary.service.DipDictionaryService;
import com.zebone.dip.dictionary.vo.DipDictionary;
import com.zebone.dip.doc.service.DocService;
import com.zebone.dip.doc.vo.DocFieldInfo;
import com.zebone.dip.doc.vo.DocXml;
import com.zebone.dip.doc.vo.ErrorInfo;
import com.zebone.dip.doc.vo.MasterDataValue;
import com.zebone.dip.doc.vo.TreeWithValueInfo;
import com.zebone.dip.md.vo.NameCode;
import com.zebone.dip.metadata.service.DocumentService;
import com.zebone.dip.metadata.vo.DocConf;
import com.zebone.dip.metadata.vo.DocMapping;
import com.zebone.dip.metadata.vo.DocTreeInfo;
import com.zebone.dip.metadata.vo.FieldConf;
import com.zebone.dip.metadata.vo.TreeInfo;
import com.zebone.dip.tool.docoperation.GenerateDocument;
import com.zebone.dip.tool.vo.DocInfo;
import com.zebone.util.JsonUtil;


@Controller
public class DocController {

    @Resource
    private DocService docService;
    @Resource
    private DipDictionaryService dipDictionaryService;
    @Resource
    private DocumentService documentService;

    @RequestMapping("doc/docDownload")
    public String downloadPage(ModelMap map) {
        List<NameCode> type = dipDictionaryService.getTypeByCode("EXV00.00.026");
        map.put("docTypeCode", type);
        return "dip/doc/doc_download";
    }

    @RequestMapping("downloadDocOrg/index")
    public String docIndex(ModelMap map) {

        return "dip/doc/index";
    }

    /**
     * 获取页面左侧的树状列表
     *
     * @return
     */
    @RequestMapping("doc/docTree")
    public String docTreePage() {
        return "dip/doc/doc_tree";
    }


    /**
     * 示例文档页面左侧文档结构树
     *
     * @return
     */
    @RequestMapping("doc/docTypeTree")
    public String docExampleIndexPage(ModelMap map) {
        List<DocConf> list = docService.getDocInfoLic();
        String docTypes = JsonUtil.writeValueAsString(list);
        map.put("docTypes", docTypes);
        return "dip/doc/doc_typeTree";
    }

    @RequestMapping("doc/getFieldInfo")//获取某个节点的相关信息
    @ResponseBody
    public Map<String, Object> getFieldInfo(String fieldCode) {
        FieldConf fieldConf = docService.getFieldInfo(fieldCode);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fieldInfo", fieldConf);
        return map;
    }

    @RequestMapping("doc/getDirectoryRandom")//随机获取字典项
    @ResponseBody
    public Map<String, Object> getDirectoryRandom(String dicttype_id) {
        DipDictionary dipDictionary = docService.getDirectoryRandom(dicttype_id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("directoryInfo", dipDictionary);
        return map;
    }

    @RequestMapping("doc/docExampleIndex")
    public String docIndex() {
        return "dip/doc/doc_example_index";
    }

    public static void Copy(Object source, Object dest) throws Exception {  //复制属性至另一个相似的类上
        //获取属性
        BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(), java.lang.Object.class);
        PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();

        BeanInfo destBean = Introspector.getBeanInfo(dest.getClass(), java.lang.Object.class);
        PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();

        try {
            for (int i = 0; i < sourceProperty.length; i++) {

                for (int j = 0; j < destProperty.length; j++) {

                    if (sourceProperty[i].getName().equals(destProperty[j].getName())) {
                        //调用source的getter方法和dest的setter方法
                        destProperty[j].getWriteMethod().invoke(dest, sourceProperty[i].getReadMethod().invoke(source));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("属性复制失败:" + e.getMessage());
        }
    }


    @RequestMapping("doc/downloadXml")//xml文档下载
    public void downloadXml(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id, @RequestParam("name") String name, ModelMap map) throws DocumentException, IOException {
        try {
            String xml = docService.getDocXmlInfo(id);
            Document doc = DocumentHelper.parseText(xml);
            name += ".xml";
            String fileName = "";
            if(request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
                fileName = URLEncoder.encode(name,"UTF-8");
            }else{
                fileName = new String(name.getBytes("UTF-8"), "ISO-8859-1");
            }
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            PrintWriter out = response.getWriter();
            out.write(xml);
            out.flush();
            out.close();
        } finally {


        }
    }

    @RequestMapping("doc/downloadTestXml")//xml实例文档下载
    public void downloadTestXml(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id, @RequestParam("name") String name, ModelMap map) throws DocumentException, IOException {
        try {
            String xml = docService.getXmlTestInfo(id);

            if (xml == null || "".equals(xml)) {
                xml = docService.getDocXmlInfo(id);
                name += ".xml";
            } else {
                name += "_测试.xml";
            }
            Document doc = DocumentHelper.parseText(xml);

            String fileName = "";
            if(request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
                fileName = URLEncoder.encode(name,"UTF-8");
            }else{
                fileName = new String(name.getBytes("UTF-8"), "ISO-8859-1");
            }
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            PrintWriter out = response.getWriter();
            out.write(xml);
            out.flush();
            out.close();
        } finally {


        }
    }


    private DocInfo changeToDocInfo(DocXml docConf) {
        DocInfo docInfo = new DocInfo();
        docInfo.setDocName(docConf.getDocName());
        docInfo.setDocTypeCode(docConf.getDocTypeCode());
        docInfo.setDocVersion(docConf.getDocVersion());
        docInfo.setDocXML(docConf.getDocXml());
        docInfo.setId(docConf.getId());
        return docInfo;
    }

    @RequestMapping("doc/downloadDoc")//下载doc文档
    public void downloadDoc(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id, @RequestParam("name") String name, ModelMap map) throws DocumentException, IOException {
        try {
            DocXml docConf = docService.getDocWordInfo(id);
            DocInfo di = this.changeToDocInfo(docConf);
            //String filepathString = Thread.currentThread().getContextClassLoader().getResource("").toString().replace("%20", " ").substring(6) + "src1.docx";
            
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
            ServletContext servletContext = webApplicationContext.getServletContext();  
            String filepathString = servletContext.getRealPath("/")+"/WEB-INF/classes/com/zebone/dip/tool/docoperation/"+"src1.docx";

            //String filepathString = "f:/src1.docx";
            //新建文档
            XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(filepathString));
            // 存储字典值域代码（id）
            List<String> fieldValueList = new ArrayList<String>();
            // 填补表，在fieldValueList中获得
            GenerateDocument.fillDocInfoTable(document, fieldValueList, di);
            // 添加各个字典表
            GenerateDocument.addDictionaryTables(document, fieldValueList);
            //添加最后文档
            GenerateDocument.addXMLAndTitleDocInfo(document, di);
            name += ".docx";
            String fileName = "";
            if(request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
                fileName = URLEncoder.encode(name,"UTF-8");
            }else{
                fileName = new String(name.getBytes("UTF-8"), "ISO-8859-1");
            }
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            document.write(response.getOutputStream());
        } finally {
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }

    }


    @RequestMapping("doc/listDirectoryValueByName")//根据名称获取字典信息
    @ResponseBody
    public Map<String, Object> listDirectoryValueByName(@RequestParam("dicttypeid") String dicttypeid, @RequestParam("query") String name) {
        DipDictionary dipDictionary = new DipDictionary();
        dipDictionary.setDict_name(name.split("_")[0]);
        dipDictionary.setDicttype_id(dicttypeid);
        List<DipDictionary> objs = docService.listDirectoryValueByName(dipDictionary);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", objs);
        map.put("success", true);
        int total = 0;
        if (objs != null && objs.size() > 0) {
            total = objs.size();
        }
        map.put("query", name);
        map.put("total", total);
        return map;
    }

    @RequestMapping("doc/listDirectoryValueByDocId")//根据名称获取字典信息
    @ResponseBody
    public Map<String, Object> listDirectoryValueByDocId(@RequestParam("docid") String docid) {

        DipDictionary objs = docService.listDirectoryValueByDocId(docid);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", objs);
        map.put("success", true);

        return map;
    }


    @RequestMapping("doc/listMasterDateByName")//根据名称获取主数据信息
    @ResponseBody
    public Map<String, Object> listMasterDateByName(@RequestParam("masterdataname") String masterdataname, @RequestParam("query") String name) throws UnsupportedEncodingException {
        String test = new String(masterdataname.getBytes("ISO-8859-1"), "UTF-8");
        List<MasterDataValue> objs = docService.listMasterDateByName(masterdataname, name);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", objs);
        map.put("success", true);
        int total = 0;
        if (objs != null && objs.size() > 0) {
            total = objs.size();
        }
        map.put("query", name);
        map.put("total", total);
        return map;
    }

    //doc/saveXmlInfo
    @RequestMapping("doc/saveXmlInfo")//保存测试xml文档信息
    @ResponseBody
    public Map<String, Object> saveXmlInfo(HttpServletRequest request, HttpServletResponse response, String json, String xmlinfo) throws DocumentException, IOException {

        String[] array = json.split(";");
        String docid = array[0].split(":")[1].replace("\"", "");
        String xml = docService.getDocXmlInfo(docid);
        Document doc = DocumentHelper.parseText(xml);

        Element element = (Element) doc.selectSingleNode("/ClinicalDocument");
        String name = element.attributeValue("name");


        xmlinfo = array[1].substring(array[1].indexOf("[") + 1, array[1].lastIndexOf("]"));
        String[] xmlinfonode = xmlinfo.split(" ");
        //List<DocFieldInfo> listDocFieldInfo = new ArrayList<DocFieldInfo>();
        for (String node : xmlinfonode)//解析节点信息
        {
            node = node.substring(1, node.length() - 1);
            if (node.split(",").length < 2) {
                String nodename = node.split(":")[0].replace("\"", "");
                String nodeid = nodename.split("_")[1];
                String nodevalue = node.split(":")[1].replace("\"", "");
                DocMapping docMapping = docService.getDocMappingInfo(nodeid);
//				DocFieldInfo docFieldInfo = new DocFieldInfo();
//				docFieldInfo.setId(nodeid);
//				docFieldInfo.setXpath(docMapping.getXpath());
//				docFieldInfo.setCode(nodevalue);
//				docFieldInfo.setDisPlayname(nodevalue);
//				docFieldInfo.setFieldId(docMapping.getFieldId());
                Element em = (Element) doc.selectSingleNode(docMapping.getXpath() + "/value");
                em.attribute("code").setValue(nodevalue);
                em.attribute("displayName").setValue(nodevalue);
                //	listDocFieldInfo.add(docFieldInfo);
            } else {
                String code = node.split(",")[0];
                String displayname = node.split(",")[1];
                String nodename = code.split(":")[0].replace("\"", "");
                String nodeid = nodename.split("_")[1];
                String displaynamevalue = code.split(":")[1].replace("\"", "");
                String codevalue = displayname.split(":")[1].replace("\"", "");
                DocMapping docMapping = docService.getDocMappingInfo(nodeid);
//				DocFieldInfo docFieldInfo = new DocFieldInfo();
//				docFieldInfo.setId(nodeid);
//				docFieldInfo.setXpath(docMapping.getXpath());
//				docFieldInfo.setCode(codevalue);
//				docFieldInfo.setDisPlayname(displaynamevalue);
//				docFieldInfo.setFieldId(docMapping.getFieldId());
//				listDocFieldInfo.add(docFieldInfo);
                Element em = (Element) doc.selectSingleNode(docMapping.getXpath() + "/value");
                em.attribute("code").setValue(codevalue);
                em.attribute("displayName").setValue(displaynamevalue);
            }
        }
        //	ErrorInfo errorInfo=validateFieldInfo(listDocFieldInfo);

        name += ".xml";
        xml = doc.asXML();
        DocXml docXml = new DocXml();
        docXml.setId(docid);
        docXml.setXmlSample(xml);
        Map<String, Object> map = new HashMap<String, Object>();
//		if(errorInfo!=null)
//		{
//			map.put("success", "false");
//			map.put("message", errorInfo.getXpath()+":"+errorInfo.getErrorType()+":"+errorInfo.getErrorMessage());
//		}
//		else
//		{
//			map.put("success", "true");
//		}


        boolean isSuccess = docService.updateDocSampleXml(docXml);
        if (isSuccess) {
            map.put("success", "true");
        } else {
            map.put("success", "false");
        }

//		try
//		{
//		String fileName = new String(name.getBytes("UTF-8"),"ISO-8859-1");
//		response.reset();
//		response.setCharacterEncoding("UTF-8");
//		response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
//		xml=doc.asXML();
//		PrintWriter  out= response.getWriter();
//		out.write(xml);
//		out.flush();
//		out.close();
//		}
//		finally
//		{
//			
//		}

//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("success", true);
//		return map;
        return map;
    }

    private ErrorInfo validateFieldInfo(List<DocFieldInfo> listDocFieldInfo) {
        ErrorInfo errorInfo = new ErrorInfo();
        for (DocFieldInfo docFieldInfo : listDocFieldInfo) {
            FieldConf fieldConf = docService.getFieldInfoByid(docFieldInfo.getFieldId());
            String fieldFormat = fieldConf.getFieldFormat();
            String[] fieldFormatArray = fieldFormat.split("\\.\\.");
            if (fieldFormatArray[0].equals(fieldFormat)) {
                String temp = fieldFormat.replaceAll("[^A-Za-z]", "");
                if (fieldFormat.replaceAll("[^A-Za-z]", "").equals("AN") || fieldFormat.replaceAll("[^A-Za-z]", "").equals("A")) {
                    String length = fieldFormat.replaceAll("[^\\d]", "");
                    if (docFieldInfo.getCode().length() != Integer.parseInt(length)) {
                        errorInfo.setErrorType(fieldFormat);
                        errorInfo.setXpath(docFieldInfo.getXpath());
                        errorInfo.setErrorMessage(docFieldInfo.getCode());
                        return errorInfo;
                    }
                } else if (fieldFormat.replaceAll("[^A-Za-z]", "").equals("N")) {
                    String[] numberTemp = fieldFormatArray[1].split(",");
                    if (numberTemp[0].equals(fieldFormatArray[1])) {
                        if (docFieldInfo.getCode().length() != Integer.parseInt(numberTemp[0])) {
                            errorInfo.setErrorType(fieldFormat);
                            errorInfo.setXpath(docFieldInfo.getXpath());
                            errorInfo.setErrorMessage(docFieldInfo.getCode());
                            return errorInfo;
                        }
                    } else {
                        if (docFieldInfo.getCode().length() != Integer.parseInt(numberTemp[0]) + Integer.parseInt(numberTemp[1]) + 1) {
                            errorInfo.setErrorType(fieldFormat);
                            errorInfo.setXpath(docFieldInfo.getXpath());
                            errorInfo.setErrorMessage(docFieldInfo.getCode());
                            return errorInfo;
                        }
                    }
                }
            } else {
                if (fieldFormat.replaceAll("[^A-Za-z]", "").equals("AN") || fieldFormat.replaceAll("[^A-Za-z]", "").equals("A")) {
                    String max = fieldFormatArray[1];
                    String min = fieldFormatArray[0].replaceAll("[^\\d]", "");
                    if (min.equals("")) {
                        min = "0";
                    }
                    if (!(docFieldInfo.getCode().length() < Integer.parseInt(max) && docFieldInfo.getCode().length() > Integer.parseInt(min))) {
                        errorInfo.setErrorType(fieldFormat);
                        errorInfo.setXpath(docFieldInfo.getXpath());
                        errorInfo.setErrorMessage(docFieldInfo.getCode());
                        return errorInfo;
                    }
                } else if (fieldFormat.replaceAll("[^A-Za-z]", "").equals("N")) {
                    String[] numberTemp = fieldFormatArray[1].split(",");
                    String min = fieldFormatArray[0].replaceAll("[^\\d]", "");
                    if (min.equals("")) {
                        min = "0";
                    }
                    if (numberTemp[0].equals(fieldFormatArray[1])) {
                        if (!(docFieldInfo.getCode().length() <= Integer.parseInt(numberTemp[0]) && docFieldInfo.getCode().length() > Integer.parseInt(min))) {
                            errorInfo.setErrorType(fieldFormat);
                            errorInfo.setXpath(docFieldInfo.getXpath());
                            errorInfo.setErrorMessage(docFieldInfo.getCode());
                            return errorInfo;
                        }
                    } else {
                        if (!(docFieldInfo.getCode().length() <= Integer.parseInt(numberTemp[0]) + Integer.parseInt(numberTemp[1]) + 1 && docFieldInfo.getCode().length() > Integer.parseInt(min))) {
                            errorInfo.setErrorType(fieldFormat);
                            errorInfo.setXpath(docFieldInfo.getXpath());
                            errorInfo.setErrorMessage(docFieldInfo.getCode());
                            return errorInfo;
                        }
                    }
                }
            }


        }


        return null;
    }

    /**
     * 加载文档结构
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("doc/docStructure")//获取文档结构信息
    public String docStructure(@RequestParam("docId") String docId, @RequestParam("docName") String docName, ModelMap map) throws Exception {
        DocTreeInfo doctree = documentService.getInfoById(docId);
        List<TreeWithValueInfo> list = new ArrayList<TreeWithValueInfo>();
        String xml = docService.getXmlTestInfo(docId);
        Document doc;
        String str;
        if (xml == null || "".equals(xml))//当无测试文档时下载空文档
        {
            for (TreeInfo info : doctree.getTreeInfos()) {
                if ("ClinicalDocument".equals(info.getFieldId())) {
                    info.setName(docName);
                }
            }
            str = JsonUtil.writeValueAsString(doctree.getTreeInfos());
        } else {
            doc = DocumentHelper.parseText(xml);//解析测试文档
            for (TreeInfo info : doctree.getTreeInfos()) {
                TreeWithValueInfo treeWithValueInfo = new TreeWithValueInfo();
                Copy(info, treeWithValueInfo);

                if ("ClinicalDocument".equals(treeWithValueInfo.getFieldId())) {
                    treeWithValueInfo.setName(docName);
                }
                if (treeWithValueInfo.getIsFeild().endsWith("1")) {
                    Element element = (Element) doc.selectSingleNode(treeWithValueInfo.getXpath() + "/value");
                    String code = element.attributeValue("code");
                    String displayName = element.attributeValue("displayName");
                    treeWithValueInfo.setCode(code);
                    treeWithValueInfo.setDisplayName(displayName);
                }
                list.add(treeWithValueInfo);

            }

            str = JsonUtil.writeValueAsString(list);

        }

        map.put("docName", docName);
        map.put("docId", doctree.getDocId());
        map.put("docStruInfo", str);
        return "dip/doc/doc_stru";
    }


}
