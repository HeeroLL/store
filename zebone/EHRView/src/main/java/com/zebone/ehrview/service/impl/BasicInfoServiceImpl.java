package com.zebone.ehrview.service.impl;

import com.zebone.register.DocRegisterQuery;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zebone.ehrview.service.BasicInfoService;
import com.zebone.empi.service.EmpiService;

@Service
public class BasicInfoServiceImpl implements BasicInfoService {

    @Value("#{settings['docRegisterQueryUrl']}")
    private String docRegisterQueryUrl;

    @Value("#{settings['empiServiceUrl']}")
    private String empiServiceUrl;

    private JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

	@Override
	public String getEmpiBasicInfo(String cardXml) {
//		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress(empiServiceUrl);
		factory.setServiceClass(EmpiService.class);
		EmpiService echoService = (EmpiService)factory.create();

		String str="";
		try {
			str = echoService.searchEmpiInfo(cardXml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

    @Override
    public String getHealthRecordInfo(String xmlStr) {
        String str = "";
//        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setAddress(docRegisterQueryUrl);
        factory.setServiceClass(DocRegisterQuery.class);
        DocRegisterQuery docRegisterQuery = (DocRegisterQuery) factory.create();
        str = docRegisterQuery.DocumentRegistry_RegistryStroedQuery(xmlStr);
        return str;
    }

}
