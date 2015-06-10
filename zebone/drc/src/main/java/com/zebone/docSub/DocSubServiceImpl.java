package com.zebone.docSub;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.zebone.docSub.dao.DocStorageMapper;
import com.zebone.docSub.dao.DocSubMapper;
import com.zebone.docSub.vo.DocSubMessage;
import com.zebone.docSub.vo.DocSubRequest;
import com.zebone.docSub.vo.DocSubResult;

/**
 * 文档订阅服务实现类
 *
 * @author 杨英
 * @version 2014-8-12 上午10:25
 */
@Service("DocSubServiceImpl")
public class DocSubServiceImpl implements DocSubService {
    private static final Log logger = LogFactory.getLog(DocSubServiceImpl.class);
    
    @Value("${brokerURL}")
    private String brokerURL;

    @Resource
    private DocSubMapper docSubMapper;

    @Resource
    private DocStorageMapper docStorageMapper;
    
    
    @Value("${wmq.host}")
	private String wmqHost;
	
	@Value("${wmq.port}")
	private int wmqPort;
	
	@Value("${wmq.channel}")
	private String wmqChannel;
	
	@Value("${wmq.queuemanager}")
	private String wmqQueueManager;
	
	@Value("${wmq.transporttype}")
	private String wmqTransportType;


    @Override
    public String docSub(String xml) {
    	XStream xs = new XStream(new DomDriver("UTF-8", new NoNameCoder()));
        String returnInfo;
        boolean isSuccess = true;
        DocSubResult docSubResult = new DocSubResult();
        List<DocSubMessage> docSubMsgList;
        try {
        	
            xs.processAnnotations(DocSubRequest.class);
            DocSubRequest docSubRequest = (DocSubRequest) xs.fromXML(xml);
            if (docSubRequest != null && docSubRequest.getDocOrg() != null && !"".equals(docSubRequest.getDocOrg())
                    && docSubRequest.getDocType() != null && !"".equals(docSubRequest.getDocType())) {
                String subOrg = docSubRequest.getSubOrg();
                String docOrg = docSubRequest.getDocOrg();    //文档上传机构列表
                String docType = docSubRequest.getDocType();  //文档类型列表
                String[] docOrgLic = docOrg.split(",");
                String[] docTypeLic = docType.split(",");
                Map<String,Object> oMap = new HashMap<String, Object>();
                oMap.put("docOrgLic", new ArrayList<String>(Arrays.asList(docOrgLic)));
                oMap.put("docTypeLic",new ArrayList<String>(Arrays.asList(docTypeLic)));
                if(docSubRequest.getStartDt()!=null && !"".equals(docSubRequest.getStartDt())){
                    oMap.put("startDt",docSubRequest.getStartDt());
                }
                if(docSubRequest.getEndDt()!=null && !"".equals(docSubRequest.getEndDt())){
                    oMap.put("endDt",docSubRequest.getEndDt());
                }
                docSubMsgList = docSubMapper.getSubMessage(oMap);
                if (docSubMsgList != null && docSubMsgList.size() > 0) {
                    xs.processAnnotations(DocSubMessage.class);
                    for (DocSubMessage docSubMessage : docSubMsgList) {
                        String docXml = docStorageMapper.getDocContentByDocNo(docSubMessage.getDocNo());
                        if (docXml != null && docXml.length() > 0) {
                            docSubMessage.setDocXml(docXml);
                            String subXml = xs.toXML(docSubMessage);
                            sendMessage(subXml, subOrg);
                        }
                    }
                }
            } else {
                isSuccess = false;
                docSubResult.setErrorCode("1");
                docSubResult.setErrorMsg("xml参数格式错误");
            }
        } catch (Exception e) {
            isSuccess = false;
            docSubResult.setErrorCode("2");
            docSubResult.setErrorMsg("系统出现异常");
            logger.error(e.getMessage(), e);
        } finally {
            if (isSuccess) {
                docSubResult.setSuccess("1");
            } else {
                docSubResult.setSuccess("2");
            }
            xs.processAnnotations(DocSubResult.class);
            returnInfo = xs.toXML(docSubResult);
        }
        return returnInfo;
    }

    /**
     * 信息推送
     *
     * @param subXml
     */
    public void sendMessage(String subXml, String subOrg) {
        if (subXml == null || subXml.equals("")) {
            throw new RuntimeException("不能发送空信息！");
        }
        /*
        MQQueue mqQueue = null;
        MQQueueManager qmgr = null;
        try {
				MQEnvironment.CCSID = 1381; // default 819
				MQEnvironment.channel = wmqChannel;
				MQEnvironment.hostname = wmqHost;
				MQEnvironment.port = wmqPort;
				MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY,MQC.TRANSPORT_MQSERIES);

				MQPutMessageOptions pmo = new MQPutMessageOptions();
				qmgr = new MQQueueManager(wmqQueueManager);
				mqQueue = qmgr.accessQueue(subOrg, MQC.MQOO_OUTPUT
						+ MQC.MQOO_FAIL_IF_QUIESCING + MQC.MQOO_BIND_NOT_FIXED);

				MQMessage msg = new MQMessage();

				msg.format = MQC.MQFMT_STRING;
	    		msg.persistence = MQC.MQPER_PERSISTENT;
	    		msg.messageId = MQC.MQMI_NONE;
	    		msg.correlationId = MQC.MQMI_NONE;
	    		msg.encoding = 546; // default 273
	    		msg.characterSet = 1381; // default 819
	    		
				msg.clearMessage();
				msg.writeString(subXml);
				mqQueue.put(msg, pmo);
			
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
        	try {
				mqQueue.close();
	    		qmgr.disconnect();		
			} catch (MQException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(), e);
				throw new RuntimeException(e);
			}
        }
        */
    }
}
