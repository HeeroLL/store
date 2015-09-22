/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Aug 24, 2013     文档数据调阅实现类
 */
package com.zebone.store;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.zebone.follow.dao.DmFollowupMapper;
import com.zebone.follow.dao.EhrMainMapper;
import com.zebone.follow.dao.ElderfuMapper;
import com.zebone.follow.dao.HbpFollowupMapper;
import com.zebone.follow.dao.HcMapper;
import com.zebone.follow.vo.DataPreprocess;
import com.zebone.follow.vo.DmFollowup;
import com.zebone.follow.vo.Elderfu;
import com.zebone.follow.vo.HbpFollowup;
import com.zebone.follow.vo.Hc;
import com.zebone.store.dao.DocStorageMapper;
import com.zebone.store.vo.DocStorage;
import org.springframework.stereotype.Service;

@Service("DocQueryImpl")
public class DocQueryImpl implements DocQuery {

	@Resource
	private DocStorageMapper docStorageMapper;
	@Resource
	private DmFollowupMapper dmFollowupMapper;
	@Resource
	private ElderfuMapper elderfuMapper;
	@Resource
	private HbpFollowupMapper hbpFollowupMapper;
	@Resource
	private HcMapper hcMapper;
	@Resource 
	private EhrMainMapper ehrMainMapper;
	
	private static final Log logger = LogFactory.getLog(DocQueryImpl.class);
	
	/**
	 * @author caixl
	 * @date Aug 24, 2013
	 * @description TODO 查询文档数据
	 * @param xml <param><flag>#flag</flag><code>#code</code><empiId>#empiId</empiId></param> 
	 * flag 1、查询文档，code为文档编号
	 * flag 2、查询文档，code为文档类型编号
	 * flag 3、查询列表，code为文档类型编号
	 * @return String 返回调阅数据
	 */
	@Override
	public String DocumentRepository_RetrieveDocumentSet(String xml) {
		Document doc = null;
		String flag = "";
		String code = "";
		String empiId = "";
		try{
			doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			Element el1 = root.element("flag");
			Element el2 = root.element("code");
			Element el3 = root.element("empiId");
			
			flag = el1.getTextTrim();
			code = el2.getTextTrim();
			empiId = el3.getTextTrim();
		}catch (Exception e) {
			logger.error("传入参数格式不正确", e);
		}
		
		if("1".equals(flag)){  //根据文档编号调阅
			DocStorage docStorage = docStorageMapper.getDocStorageByDocNo(code);
			if(docStorage!= null){
				return docStorage.getDocXml();
			}
		}else if("2".equals(flag)){
			DocStorage docStorage = docStorageMapper.getDocStorageByDocTypeCode(code,empiId);
			if(docStorage!= null){
				return docStorage.getDocXml();
			}
		}else if("3".equals(flag)){
			if("B04.01.02.00".equals(code)){//高血压随访记录
				List<HbpFollowup> list = hbpFollowupMapper.getHbpByEmpiId(empiId);
				if(list!=null && list.size()>0){
					StringBuilder sb = new StringBuilder("<result><entity>");
					for(int i=0;i<list.size();i++){
						sb.append("<item>").append(list.get(i).getCardNo()).append("</item>");
					}
					sb.append("</entity></result>");
					return sb.toString();
				}
			}else if("B04.02.02.00".equals(code)){//糖尿病随访记录
				List<DmFollowup> list = dmFollowupMapper.getDmByEmpiId(empiId);
				if(list!=null && list.size()>0){
					StringBuilder sb = new StringBuilder("<result><entity>");
					for(int i=0;i<list.size();i++){
						sb.append("<item>").append(list.get(i).getCardNo()).append("</item>");
					}
					sb.append("</entity></result>");
					return sb.toString();
				}
			}else if("B04.04.01.00".equals(code)){//老年人保健随访
				List<Elderfu> list = elderfuMapper.getElderByEmpiId(empiId);
				if(list!=null && list.size()>0){
					StringBuilder sb = new StringBuilder("<result><entity>");
					for(int i=0;i<list.size();i++){
						sb.append("<item>").append(list.get(i).getCardNo()).append("</item>");
					}
					sb.append("</entity></result>");
					return sb.toString();
				}
			}else if("C00.04.01.00".equals(code)){//成人健康体检
				List<Hc> list = hcMapper.getHcByEmpiId(empiId);
				if(list!=null && list.size()>0){
					StringBuilder sb = new StringBuilder("<result><entity>");
					for(int i=0;i<list.size();i++){
						sb.append("<item>").append(list.get(i).getCardNo()).append("</item>");
					}
					sb.append("</entity></result>");
					return sb.toString();
				}
			}
			
		}else if("4".equals(flag)){ //首页数据
			List<DataPreprocess> list = ehrMainMapper.getDataBlock(empiId,code);
			if(list!=null && list.size()>0){
				StringBuilder sb = new StringBuilder("<result>");
				for(int i=0;i<list.size();i++){
					sb.append(list.get(i).getTempXml());
				}
				sb.append("</result>");
				return sb.toString();
			}
		}
		return "";
	}

}
