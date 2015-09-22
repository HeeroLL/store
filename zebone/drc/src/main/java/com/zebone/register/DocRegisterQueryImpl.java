/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Aug 24, 2013     文档数据调阅服务
 */
package com.zebone.register;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zebone.btp.core.util.DateUtil;
import com.zebone.btp.core.util.Identities;
import com.zebone.register.dao.DocViewLogMapper;
import com.zebone.register.dao.StoreServiceMapper;
import com.zebone.register.vo.DocViewLog;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.dom4j.*;
import org.springframework.beans.factory.annotation.Value;

import com.zebone.register.dao.EmpiInfoMapper;
import com.zebone.register.vo.EmpiInfo;
import com.zebone.store.DocQuery;
import org.springframework.stereotype.Service;

@Service("DocRegisterQueryImpl")
public class DocRegisterQueryImpl implements DocRegisterQuery {

	@Resource
	private EmpiInfoMapper empiInfoMapper;

    @Resource
    private StoreServiceMapper storeServiceMapper;

    @Resource
    private DocViewLogMapper docViewLogMapper;

    /**
     * 文档唯一标识的xpath路径。
     */
    @Value("${doc_no_xpath}")
    private   String DOC_NO_XPATH ;

//	@Value("${url.docQuery}")
//	private String docQueryUrl;
	
	private static final Log logger = LogFactory.getLog(DocRegisterQueryImpl.class);
	
	/**
	 * @author caixl
	 * @date Aug 24, 2013
	 * @description TODO 调阅数据信息
	 * @param xml 调阅参数
	 * @return String 调阅返回值
	 */
	@Override
	public String DocumentRegistry_RegistryStroedQuery(String xml) {
		//解析参数
		Document doc = null;
		String empiId = "";
		String dataCode = "";
		String docNo = "";
        String searchInfo = "";
        String patientInfo = "";
		
		//String result = "";
		try {
			doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			Element el1 = root.element("empi_id");
			Element el2 = root.element("data_code");
			Element el3 = root.element("doc_no");
            Element el4 = root.element("search_info");
            Element el5 = root.element("patient_info");
			empiId = el1.getTextTrim();
			dataCode = el2.getTextTrim();
			docNo = el3.getTextTrim();
            if (el4 != null) searchInfo = el4.getTextTrim();
            if (el5 != null) patientInfo = el5.getTextTrim();

        } catch (DocumentException e) {
			logger.error("参数格式不对", e);
			e.printStackTrace();
			return "";
		}
		
		if(StringUtils.isEmpty(empiId)){
			logger.error("没有上传个人标识");
			return "";
		}
		
		if(StringUtils.isEmpty(dataCode)){
			logger.error("没有上传调用模块");
			return "";
		}
		
		JaxWsProxyFactoryBean jw = new JaxWsProxyFactoryBean();
        Map<String,String> oMap = new HashMap<String, String>();
        oMap.put("serviceType","2"); //"2"代表存储系统查询服务
        oMap.put("systemCode", "A"); // "A"代表存储系统A
        String docQueryUrl = storeServiceMapper.getWebUrl(oMap);
		jw.setAddress(docQueryUrl);
		jw.setServiceClass(DocQuery.class);
		DocQuery docQuery = (DocQuery)jw.create();
		
		String[] modes = dataCode.split("_");
		if("A".equals(modes[0])){//卫生服务活动信息
			if(modes.length==1){//获取卫生服务活动列表
				List<EmpiInfo> list = empiInfoMapper.getEmpiInfoByEmpiId(empiId);
				if(list != null && list.size()>0){
					StringBuilder result = new StringBuilder("<result><entity>");
					for(int i=0;i<list.size();i++){
						EmpiInfo empiInfo = list.get(i);
						result.append("<item code=\"").append(empiInfo.getMainId()).append("\">")
								.append(empiInfo.getHealthCode()).append("</item>");
					}
					result.append("</entity></result>");
					return result.toString();
				}else{
					return "";
				}
			}else if(modes.length == 2){//调阅文档数据
                DocViewLog docViewLog = new DocViewLog(); //文档调阅日志对象
                docViewLog.setDocType(modes[1]);
                docViewLog.setId(Identities.uuid());
                docViewLog.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
                if(!StringUtils.isEmpty(searchInfo)){ //searchInfo中包含机构编码和医生编码
                    String[] searchInfoLic = searchInfo.split(",");
                    docViewLog.setOrgCode(searchInfoLic[0]);
                    docViewLog.setDoctorCode(searchInfoLic[1]);
                }
                if(!StringUtils.isEmpty(patientInfo)){ //patientInfo中包含患者卡类型,卡号和姓名
                    String[] patientInfoLic = patientInfo.split(",");
                    docViewLog.setCardType(patientInfoLic[0]);
                    docViewLog.setCardNo(patientInfoLic[1]);
                    docViewLog.setName(patientInfoLic[2]);
                }
				String param =  "<param><flag>#flag</flag><code>#code</code><empiId>#empiId</empiId></param>";
				if(StringUtils.isEmpty(docNo)){
					param = param.replaceAll("#flag", "2");
					param = param.replaceAll("#code", modes[1]);
					param = param.replaceAll("#empiId", empiId);
				}else{
                    if ("C00.01.03.00".equals(modes[1]) || "C00.02.05.00".equals(modes[1])
                            || "C00.03.01.00".equals(modes[1]) || "C00.03.02.01".equals(modes[1])) {
                        //根据参数中就诊信息的文档编号，获取其他门诊信息的文档编号，如"门诊处方","手术记录"
                        Map<String,String> map = new HashMap<String, String>();
                        map.put("empiId",empiId);
                        map.put("dataCode",modes[1]);
                        map.put("parentDocNo",docNo);
                        docNo = empiInfoMapper.getDocNoByParentNo(map);
                        if(docNo == null) docNo = "";
                    }
					param = param.replaceAll("#flag", "1");
					param = param.replaceAll("#code", docNo);
                    docViewLog.setDocNo(docNo);
				}
				
				String data = docQuery.DocumentRepository_RetrieveDocumentSet(param);

                if (StringUtils.isEmpty(data)) {
                    docViewLog.setViewState("0"); //表示调阅未成功
                } else {
                    docViewLog.setViewState("1"); //表示调阅成功

                    //如果调阅参数中文档编号为空,从返回的文档数据中获取文档编号用于调阅日志
                    if (StringUtils.isEmpty(docNo)) {
                        try {
                            Document doc1 = DocumentHelper.parseText(data);
                            Node node = doc1.selectSingleNode(DOC_NO_XPATH);
                            if (node != null) {
                                docViewLog.setDocNo(((Element) node).attributeValue("code"));
                            }
                        } catch (DocumentException e) {
                            logger.error("文档数据解析失败", e);
                        }
                    }
                }

                try {
                    docViewLogMapper.insert(docViewLog);//插入文档调阅日志
                }catch (Exception e){
                    logger.error("文档调阅日志保存失败", e);
                }

				return data;
			}else if(modes.length == 3){//调阅列表数据,从文档注册头中获取
//				String param = "<param><flag>#flag</flag><code>#code</code><empiId>#empiId</empiId></param>";
//				param = param.replaceAll("#flag", "3");
//				param = param.replaceAll("#code", modes[2]);
//				param = param.replaceAll("#empiId", empiId);
				
//				String data = docQuery.DocumentRepository_RetrieveDocumentSet(param); +
                Map<String,String> paramMap = new HashMap<String, String>();
                paramMap.put("docTypeCode",modes[2]);
                paramMap.put("empiId",empiId);
                String data = "";
                List<String> list = empiInfoMapper.getFollowUpList(paramMap);
                if(list!=null && list.size()>0){
                    StringBuilder sb = new StringBuilder("<result><entity>");
                    for(int i=0;i<list.size();i++){
                        sb.append("<item>").append(list.get(i)).append("</item>");
                    }
                    sb.append("</entity></result>");
                    data =  sb.toString();
                }
				return data;
			}
		}else if("B".equals(modes[0])){//健康和疾病问题
			if(modes.length==1){//获取健康和疾病问题列表
			 	List<EmpiInfo> list = empiInfoMapper.getIcdByEmpiId(empiId);
			 	if(list!=null && list.size()>0){
			 		StringBuilder sb = new StringBuilder("<result><entity>");
			 		for(int i=0;i<list.size();i++){
						EmpiInfo empiInfo = list.get(i);
						sb.append("<item code=\"").append(empiInfo.getIcdCode()).append("\">")
								.append(empiInfo.getCardNo()).append("</item>");
					}
			 		sb.append("</entity></result>");
			 		return sb.toString();
			 	}
			}else if(modes.length == 3){
				List<EmpiInfo> list = empiInfoMapper.getEmpiInfoByIcdAndEmpiId(modes[2],empiId);
				if(list!=null && list.size()>0){
					StringBuilder sb = new StringBuilder("<result><entity>");
			 		for(int i=0;i<list.size();i++){
						EmpiInfo empiInfo = list.get(i);
						sb.append("<item>").append(empiInfo.getCardNo()).append("</item>");
					}
			 		sb.append("</entity></result>");
			 		return sb.toString();
				}
			}
		}else if("C".equals(modes[0])){//生命阶段
			if(modes.length == 3){
				String startDate = modes[2].substring(0, 8)+"000000";
				String endDate = modes[2].substring(8)+"235959";
				List<EmpiInfo> list = empiInfoMapper.getEmpiInfoByAge(startDate,endDate,empiId);
				if(list!=null && list.size()>0){
					StringBuilder sb = new StringBuilder("<result><entity>");
			 		for(int i=0;i<list.size();i++){
						EmpiInfo empiInfo = list.get(i);
						sb.append("<item>").append(empiInfo.getCardNo()).append("</item>");
					}
			 		sb.append("</entity></result>");
			 		return sb.toString();
				}
			}
		}else if("M".equals(modes[0])){//首页
			if(modes.length == 2){
				String[] strs = modes[1].split(",");
				StringBuilder str = new StringBuilder();
				for(String s : strs){
					str.append("'").append(s).append("',");
				}
				if(str.length()>1){
					str.deleteCharAt(str.length() -1);
				}
				String param =  "<param><flag>4</flag><code>"+str.toString()+"</code><empiId>"+empiId+"</empiId></param>";
				
				String data = docQuery.DocumentRepository_RetrieveDocumentSet(param);
				return data;
			}
		}
		
		return "";
	}

}
