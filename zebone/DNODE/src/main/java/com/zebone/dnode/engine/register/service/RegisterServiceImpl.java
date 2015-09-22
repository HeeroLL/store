package com.zebone.dnode.engine.register.service;

/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * 蔡祥龙              New             Aug 14, 2013     文档注册引擎实现类
 */

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zebone.dnode.engine.register.dao.DocStorageMapper;
import com.zebone.dnode.engine.register.vo.DocStorage;
import com.zebone.register.DocRegister;
@Service("registerService")
public class RegisterServiceImpl implements RegisterService {
	
	private static final Log logger = LogFactory.getLog(RegisterServiceImpl.class);

	@Value("${url.docRegister}")
	private String docRegisterUrl;
	
	@Resource
	private DocStorageMapper docStorageMapper;
	
	/**
	 * @author 蔡祥龙
	 * @date Aug 14, 2013
	 * @description TODO 文档注册服务
	 */
	@Override
	public void register(String analyzeThreads, String threadNo) {
		int threads = Integer.parseInt(analyzeThreads);
        int no = Integer.parseInt(threadNo);
		//获取待注册文档
		List<DocStorage> list = docStorageMapper.getDocStorageList(threads,no);
		if(list!=null && list.size()>0){
			List<DocStorage> docStorages = new ArrayList<DocStorage>();
			for(int i = 0;i < list.size(); i++){
				DocStorage docStorage = list.get(i);
				JaxWsProxyFactoryBean jw = new JaxWsProxyFactoryBean();
				jw.setAddress(docRegisterUrl);
				jw.setServiceClass(DocRegister.class);
				DocRegister dr = (DocRegister)jw.create();
				
				String xml = dr.DocumentRegistry_RegistryDocumentSet_b(docStorage.getDocXml(), docStorage.getDocRegisterState());
				
				Document document = null;
				try{
					document = DocumentHelper.parseText(xml);
					Element root = document.getRootElement();
					List els = root.elements();
					if(els != null && els.size() >0){
						Element el1 = (Element)els.get(0);
						Element el2 = (Element)els.get(1);
						if("1".equals(el1.getTextTrim())){
							docStorage.setEmpiId(el2.getTextTrim());
							if("0".equals(docStorage.getDocRegisterState())){
								docStorage.setDocRegisterState("1");
							}else{
								docStorage.setDocRegisterState("3");
							}
							docStorages.add(docStorage);
						}else{
                            docStorage.setDocRegisterState("9"); //9代表文档注册失败
							docStorages.add(docStorage);
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(docStorages!=null && docStorages.size()>0){
				for(DocStorage ds:docStorages){
					docStorageMapper.updateDocRegisterState(ds);
				}
			}
		}
	}

	@Override
	public void register(String docOrgCode) {
		// TODO Auto-generated method stub
		//获取待注册文档
		List<DocStorage> list = docStorageMapper.getDocStorageListByDocOrgCode(docOrgCode);
		if(list!=null && list.size()>0){
			List<DocStorage> docStorages = new ArrayList<DocStorage>();
			for(int i = 0;i < list.size(); i++){
				DocStorage docStorage = list.get(i);
				JaxWsProxyFactoryBean jw = new JaxWsProxyFactoryBean();
				jw.setAddress(docRegisterUrl);
				jw.setServiceClass(DocRegister.class);
				DocRegister dr = (DocRegister)jw.create();
				
				String xml = dr.DocumentRegistry_RegistryDocumentSet_b(docStorage.getDocXml(), docStorage.getDocRegisterState());
				
				Document document = null;
				try{
					document = DocumentHelper.parseText(xml);
					Element root = document.getRootElement();
					List els = root.elements();
					if(els != null && els.size() >0){
						Element el1 = (Element)els.get(0);
						Element el2 = (Element)els.get(1);
						if("1".equals(el1.getTextTrim())){
							docStorage.setEmpiId(el2.getTextTrim());
							if("0".equals(docStorage.getDocRegisterState())){
								docStorage.setDocRegisterState("1");
							}else{
								docStorage.setDocRegisterState("3");
							}
							docStorages.add(docStorage);
						}else{
                            docStorage.setDocRegisterState("9"); //9代表文档注册失败
							docStorages.add(docStorage);
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(docStorages!=null && docStorages.size()>0){
				for(DocStorage ds:docStorages){
					docStorageMapper.updateDocRegisterState(ds);
				}
			}
		}
	}
}
