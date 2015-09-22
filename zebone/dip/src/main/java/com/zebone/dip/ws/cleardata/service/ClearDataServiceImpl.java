package com.zebone.dip.ws.cleardata.service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.zebone.dip.ws.cleardata.dao.ClearDcDataMapper;
import com.zebone.dip.ws.cleardata.dao.ClearDipDataMapper;
import com.zebone.dip.ws.cleardata.dao.ClearEmpiDataMapper;
import com.zebone.dip.ws.cleardata.dao.ClearWrcDataMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 清理文档相关数据服务实现类
 * @author 杨英
 * @version 2014-7-10 上午09:15
 */
@WebService(endpointInterface = "com.zebone.dip.ws.cleardata.service.ClearDataService")
@Service("clearDataService")
public class ClearDataServiceImpl implements ClearDataService {

    private static final Log log = LogFactory.getLog(ClearDataServiceImpl.class);

    private XStream xs = new XStream(new DomDriver());

    @Resource
    private ClearDcDataMapper  clearDcDataMapper;

    @Resource
    private ClearWrcDataMapper clearWrcDataMapper;

    @Resource
    private ClearEmpiDataMapper clearEmpiDataMapper;

    @Resource
    private ClearDipDataMapper clearDipDataMapper;


    @Override
    public String clearData(@WebParam(name = "requestPara") String requestParam) {
        xs.processAnnotations(Request.class);
        Request request = (Request) xs.fromXML(requestParam);
        Response response = new Response();

        Map<String,String> oMap = new HashMap<String,String>();
        oMap.put("cardNo",request.getCardNo());
        oMap.put("cardType",request.getCardType());
        String empiNo = clearEmpiDataMapper.getEmpiNo(oMap);

        if (empiNo != null && empiNo.length() > 0) {
            try {
                deleteEmpiData(empiNo); //清除主索引数据
                deleteWrcData(empiNo,request.getCardNo());
                deleteDcData(empiNo);
                deleteDipData(empiNo);
                response.setSuccess("1"); //数据清除成功
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }else{
            response.setSuccess("2");
            response.setErrorCode("001");
            response.setErrorMsg("主索引不存在");
        }

        xs.processAnnotations(Response.class);
        return xs.toXML(response);

    }

    @XStreamAlias("request")
    public static class Request {

        private String cardType;
        private String cardNo;

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }
    }

    @XStreamAlias("response")
    public static class Response {

        private String isSuccess;
        private String errorCode;
        private String errorMsg;

        public String getSuccess() {
            return isSuccess;
        }

        public void setSuccess(String success) {
            isSuccess = success;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }
    }

    public void deleteEmpiData(String empiNo){
        clearEmpiDataMapper.deleteLogData(empiNo);
        clearEmpiDataMapper.deleteCardData(empiNo);
        clearEmpiDataMapper.deleteEmpiInfo(empiNo);
    }

    public void deleteDcData(String empiNo){
        Map<String,String> oMap = new HashMap<String, String>();
        oMap.put("empiNo",empiNo);
        List<String> tableNameLic = clearDipDataMapper.getTableNameLic(empiNo);
        List<String> docNoLic = clearDipDataMapper.getDocNoLic(empiNo);
        if(tableNameLic!=null && tableNameLic.size()>0){
            for(String tableName : tableNameLic){
                oMap.put("tableName", tableName);
                clearDcDataMapper.deleteParseInfo(oMap);
            }
        }
        if(docNoLic!=null && docNoLic.size()>0){
            for(String docNo : docNoLic){
                clearDcDataMapper.deleteDocLogInfo(docNo);
                clearDcDataMapper.deleteDocInfo(docNo);
            }
        }
    }

    public void deleteWrcData(String empiNo, String cardNo){
        clearWrcDataMapper.deleteRegEmpiInfo(cardNo);
        clearWrcDataMapper.deleteRegLogInfo(empiNo);
        clearWrcDataMapper.deleteRegMainInfo(empiNo);
    }

    public void deleteDipData(String empiNo){
        clearDipDataMapper.deleteParseTableInfo(empiNo);
    }
}
