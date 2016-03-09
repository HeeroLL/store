package com.isell.ei.idcard.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.isell.core.util.JsonUtil;
import com.isell.core.util.Record;
import com.isell.ei.idcard.bean.IdcardCheckInfo;
import com.isell.ei.idcard.service.IdcardService;

/**
 * 身份证验证接口实现类
 * 
 * @author wangpeng
 * @version [版本号, 2016年3月3日]
 */
@Service("idcardService")
public class IdcardServiceImpl  implements IdcardService{
	
	 /**
     * log
     */
    private static final Logger log = Logger.getLogger(IdcardServiceImpl.class);

	/**
	 * 获取身份证信息
	 * 
	 * @param idCard
	 * @return
	 */
	@Override
	public Record getIdcardInfo(String idCard) {
		Record record = new Record();
		Boolean success = false;
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();

        try {
            URL url = new URL(API_STROE_HTTP_URL + "?" + "id=" + idCard);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("apikey",  API_STROE_API_KEY);
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
            IdcardCheckInfo info = JsonUtil.readValue(result, IdcardCheckInfo.class);    
            System.out.println(result);
            record.set("sex", info.getRetData().getSex());
            record.set("birthday", info.getRetData().getBirthday());
            record.set("address", info.getRetData().getAddress());
            success = true;
        } catch (Exception e) {
            //e.printStackTrace();
        	log.debug("输入的身份证信息不合法！");
        }
        
        if(!success){
        	record.set("msg", "输入的身份证信息不合法！");
        }
        record.set("success", success);
		return record;
	}

}
