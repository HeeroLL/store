package com.zebone.dnode.engine.store.service;

/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * 蔡祥龙              New             Aug 13, 2013     文档存储引擎实现类
 */

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zebone.dnode.engine.store.dao.CheckResultMapper;
import com.zebone.dnode.engine.store.vo.CheckResult;
import com.zebone.store.DocStore;
@Service("documentStoreService")
public class DocumentStoreServiceImpl  implements DocumentStoreService{
	
	private static final Log log = LogFactory.getLog(DocumentStoreServiceImpl.class);

	@Value("${url.docStore}")
	private String docStoreUrl;
	
	@Resource
	private CheckResultMapper checkResultMapper;
	
	/**
	 * @author caixl
	 * @date Aug 13, 2013
	 * @description TODO 存储相关信息
	 */
	@Override
	public void store(String analyzeThreads, String threadNo) {
		//获取带存储文档
		int threads = Integer.parseInt(analyzeThreads);
        int no = Integer.parseInt(threadNo);
		List<CheckResult> list = checkResultMapper.getCheckResultList(threads,no);
		if(list!=null && list.size()>0){
			List<String> ids = new ArrayList<String>();
			List<String> fids = new ArrayList<String>();
			for(int i = 0;i<list.size();i++){
				JaxWsProxyFactoryBean jw = new JaxWsProxyFactoryBean();
				jw.setAddress(docStoreUrl);
				jw.setServiceClass(DocStore.class);
				DocStore ds = (DocStore)jw.create();
				
				String result = ds.ProviderAndRegistryDocumentSet_b(list.get(i).getDocXml());
				if("1".equals(result)){
					ids.add(list.get(i).getId());
				}else{
					fids.add(list.get(i).getId());
				}
			}
			if(ids!=null && ids.size()>0){
				checkResultMapper.updateStorageFlag(ids);
			}
			if(fids !=null && fids.size()>0){
				checkResultMapper.updateStorageFFlag(fids);
			}
		}
	}

	@Override
	public void store(String orgCode) {
		// TODO Auto-generated method stub
		// 获取带存储文档
		List<CheckResult> list = checkResultMapper.getCheckResultListByOrgCode(orgCode);
		if (list != null && list.size() > 0) {
			List<String> ids = new ArrayList<String>();
			List<String> fids = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				JaxWsProxyFactoryBean jw = new JaxWsProxyFactoryBean();
				jw.setAddress(docStoreUrl);
				jw.setServiceClass(DocStore.class);
				DocStore ds = (DocStore) jw.create();

				String result = ds.ProviderAndRegistryDocumentSet_b(list.get(i)
						.getDocXml());
				if ("1".equals(result)) {
					ids.add(list.get(i).getId());
				} else {
					fids.add(list.get(i).getId());
				}
			}
			if (ids != null && ids.size() > 0) {
				checkResultMapper.updateStorageFlag(ids);
			}
			if (fids != null && fids.size() > 0) {
				checkResultMapper.updateStorageFFlag(fids);
			}
		}
	}
	
}
