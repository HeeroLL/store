package com.zebone.register;

import com.zebone.register.dao.RegisterTempMapper;
import com.zebone.register.vo.RegisterTemp;
import com.zebone.store.StoreNotice;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebParam;
import java.util.HashMap;
import java.util.Map;

/**
 * 注册通知实现
 *
 * @author 杨英
 * @version 2013-10-23 上午08:56
 */
@Service("RegisterNoticeImpl")
public class RegisterNoticeImpl implements RegisterNotice {

    @Resource
    private RegisterTempMapper registerTempMapper;

    private static final Log logger = LogFactory.getLog(RegisterNoticeImpl.class);
    
    
    private StoreNotice storeNotice = null;

    @Override
    public String DocumentRegistryNotice(@WebParam(name = "xml") String xml) {
        //解析参数
        Document doc = null;
        String cardNo = "";
        String cardType = "";
        String name = "";
        String result = "0";

        try {
            doc = DocumentHelper.parseText(xml);
            Element root = doc.getRootElement();
            Element el1 = root.element("card_no");
            Element el2 = root.element("card_type");
            Element el3 = root.element("name");
            cardNo = el1.getTextTrim();
            cardType = el2.getTextTrim();
            name = el3.getTextTrim();

        } catch (DocumentException e) {
            logger.error("参数格式不对", e);
            e.printStackTrace();
            return result;
        }

        if (StringUtils.isEmpty(cardNo)) {
            logger.error("没有上传卡号");
            return result;
        }
        if (StringUtils.isEmpty(cardType)) {
            logger.error("没有上传卡类型");
            return result;
        }
        if (StringUtils.isEmpty(name)) {
            logger.error("没有上传姓名");
            return result;
        }

        Map<String,String> oMap = new HashMap<String, String>();
        oMap.put("cardNo", cardNo);
        oMap.put("cardType", cardType);
        oMap.put("name", name);
        RegisterTemp registerTemp = registerTempMapper.getRegisterTempInfo(oMap);
        if (registerTemp != null) {
        	if(storeNotice == null){
        		   JaxWsProxyFactoryBean jw = new JaxWsProxyFactoryBean();
                   jw.setAddress(registerTemp.getWebUrl());
                   jw.setServiceClass(StoreNotice.class);
                   storeNotice = (StoreNotice) jw.create();
        	}
            int changeResult = storeNotice.DocumentRepository_RegisterStateUpdate(registerTemp.getDocNo());
            if(changeResult > 0){
                result = "1";
            }
        }
        return result;
    }
}
