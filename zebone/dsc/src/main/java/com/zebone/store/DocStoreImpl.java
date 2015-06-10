/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * 蔡祥龙              New             Aug 6, 2013       文档存储服务实现类
 */
package com.zebone.store;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.zebone.btp.core.util.DateUtil;
import com.zebone.btp.core.util.Identities;
import com.zebone.dip.log.service.DocExtInfoService;
import com.zebone.dip.log.service.LogServiceClient;
import com.zebone.dip.log.vo.Error;
import com.zebone.dip.log.vo.DocExtendInfo;
import com.zebone.dip.log.vo.Syslog;
import com.zebone.store.dao.DocStorageLogMapper;
import com.zebone.store.dao.DocStorageMapper;
import com.zebone.store.util.XmlParser;
import com.zebone.store.vo.DocStorage;
import com.zebone.store.vo.DocStorageLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("DocStoreImpl")
public class DocStoreImpl implements DocStore {

	@Resource
	private DocStorageMapper docStorageMapper;
	@Resource
	private DocStorageLogMapper docStorageLogMapper;
	@Resource
	private LogServiceClient logServiceClient;

    //文档头中文档活动类信息
    @Value("${doc_no}")
    private   String DOC_NO ;
    @Value("${doc_type}")
    private   String DOC_TYPE ;
    @Value("${doc_version}")
    private   String DOC_VERSION ;

    //生成文档信息
    @Value("${doc_org}")
    private   String DOC_ORG;
    @Value("${operate_type}")
    private   String OPERATE_TYPE ;
	
	/**
	 * @author caixl
	 * @date Aug 6, 2013
	 * @description TODO 文档存储注册方法
	 * @param xml 参数
	 * @return String  返回是否存储成功标识 1：成功 0：不成功
	 */
	@Override
	public String ProviderAndRegistryDocumentSet_b(String xml) {
		String res = "0";
		if(!StringUtils.isEmpty(xml)){
			
			Syslog syslog = new Syslog();
			DocExtendInfo ro = DocExtInfoService.getExtInfo(xml);
			if(ro!=null){
				syslog.setLogID(ro.getUuid());
			}
			syslog.setCategory("1");
			syslog.setType(Syslog.TYPE_STORAGE);
			
			DocStorage docStorage = getDocStoragebyXML(xml);//解析xml文档获取相应的信息
			
			if(docStorage != null){//存储文档
				docStorage.setDocXml(xml);
				try{
					if("1".equals(docStorage.getDocOperState())){//插入文档
						docStorage.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
						docStorage.setId(Identities.uuid());
						int result = docStorageMapper.docStorageCount(docStorage.getDocNo());
						if(result > 0){
							syslog.setStatus("0");
							Error error = new Error();
							error.setCode("S_001");
							error.setDesc("新增失败。文档重复，档案编号["+docStorage.getDocNo()+"] 已经存在");
							syslog.setError(error);
							syslog.setDocNo(docStorage.getDocNo());
							logServiceClient.saveLog(syslog);
						}else{
							result = docStorageMapper.insert(docStorage);
							if(result > 0){
								DocStorageLog docStorageLog = new DocStorageLog();
								docStorageLog.setCreateTime(docStorage.getCreateTime());
								docStorageLog.setDocNo(docStorage.getDocNo());
								docStorageLog.setDocOperState(docStorage.getDocOperState());
								docStorageLog.setDocOrg(docStorage.getDocOrg());
								docStorageLog.setDocVersion(docStorage.getDocVersion());
								docStorageLog.setDocRegisterState(docStorage.getDocRegisterState());
								docStorageLog.setDocTypeCode(docStorage.getDocTypeCode());
								docStorageLog.setDocXml(docStorage.getDocXml());
								docStorageLog.setEmpiId(docStorage.getEmpiId());
								docStorageLog.setId(Identities.uuid());
								docStorageLog.setParentId(docStorage.getId());
								result = docStorageLogMapper.insert(docStorageLog);
								if(result > 0) {
									syslog.setStatus("1");
									logServiceClient.saveLog(syslog);
									
									res = "1";
								}
							}
						}
					}else if("2".equals(docStorage.getDocOperState())){//更新文档
						DocStorage ds = docStorageMapper.getDocStorageByDocNo(docStorage.getDocNo());
						
						if(ds == null) {
							syslog.setStatus("0");
							Error error = new Error();
							error.setCode("S_002");
							error.setDesc("文档更新失败。文档编号["+docStorage.getDocNo()+"]在存储库中不存在");
							syslog.setError(error);
							logServiceClient.saveLog(syslog);
							
							return res;
						}
						
						if("0".equals(ds.getDocRegisterState())){
							docStorage.setDocRegisterState("0");
						}else{
							docStorage.setDocRegisterState("2");
						}
						if("0".equals(ds.getDocParseState())){
							docStorage.setDocParseState("0");
						}else{
							docStorage.setDocParseState("2");
						}
						int result = docStorageMapper.update(docStorage);
						if(result > 0){
							DocStorageLog docStorageLog = new DocStorageLog();
							docStorageLog.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
							docStorageLog.setDocNo(docStorage.getDocNo());
							docStorageLog.setDocOperState(docStorage.getDocOperState());
							docStorageLog.setDocOrg(docStorage.getDocOrg());
							docStorageLog.setDocRegisterState(docStorage.getDocRegisterState());
							docStorageLog.setDocTypeCode(docStorage.getDocTypeCode());
							docStorageLog.setDocVersion(docStorage.getDocVersion());
							docStorageLog.setDocXml(docStorage.getDocXml());
							docStorageLog.setEmpiId(ds.getEmpiId());
							docStorageLog.setId(Identities.uuid());
							docStorageLog.setParentId(ds.getId());
							result = docStorageLogMapper.insert(docStorageLog);
							if(result > 0) {
								syslog.setStatus("1");
								logServiceClient.saveLog(syslog);
								
								res = "1";
							}
						}
					}
				}catch(Exception e){
					syslog.setStatus("0");
					Error error = new Error();
					error.setCode("S_999");
					error.setDesc("文档数据存储失败");
					syslog.setError(error);
					syslog.setDocNo(docStorage.getDocNo());
					logServiceClient.saveLog(syslog);
					
					e.printStackTrace();
					return res;
				}
			}else{
				syslog.setStatus("0");
				Error error = new Error();
				error.setCode("S_999");
				error.setDesc("文档解析失败");
				syslog.setError(error);
				
				logServiceClient.saveLog(syslog);
			}
		}
		return res;
	}

	//将xml转换为注册信息对象
	public DocStorage getDocStoragebyXML(String xml){
		DocStorage doc = null;
		XmlParser xp = new XmlParser();
		Map<String, String> map = xp.getDocStorageByXML(xml);
		
		if(map!=null && !map.isEmpty()){
			doc = new DocStorage();
			doc.setDocOperState(map.get(OPERATE_TYPE));
			doc.setDocOrg(map.get(DOC_ORG));
			doc.setDocTypeCode(map.get(DOC_TYPE));
			doc.setDocNo(map.get(DOC_NO));
			doc.setDocVersion(map.get(DOC_VERSION));
		}
		return doc;
	}
}
