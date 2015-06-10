package com.zebone.dip.empi.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.shiro.SecurityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.security.UserDetails;
import com.zebone.dip.dictionary.util.DictionaryConvert;
import com.zebone.dip.empi.dao.EmpiInfoMapper;
import com.zebone.dip.empi.dao.EmpiLogInfoMapper;
import com.zebone.dip.empi.dao.ResidentCardMapper;
import com.zebone.dip.empi.dao.UnbindApplyMapper;
import com.zebone.dip.empi.dao.UpdateApplyMapper;
import com.zebone.dip.empi.service.EmpiManageService;
import com.zebone.dip.empi.vo.ApplyUnbindResult;
import com.zebone.dip.empi.vo.ApplyUpdateReq;
import com.zebone.dip.empi.vo.ApplyUpdateRes;
import com.zebone.dip.empi.vo.EmpiCount;
import com.zebone.dip.empi.vo.EmpiInfo;
import com.zebone.dip.empi.vo.EmpiLogInfo;
import com.zebone.dip.empi.vo.EmpiUnbindApply;
import com.zebone.dip.empi.vo.ResidentCard;
import com.zebone.dip.empi.vo.ResidentUpdateApply;
import com.zebone.dip.empi.vo.UnbindApply;
import com.zebone.dip.empi.vo.UnbindApplyParameter;
import com.zebone.dip.empi.vo.UnbindAuditRequest;
import com.zebone.dip.empi.vo.UpdateApply;
import com.zebone.dip.empi.vo.UpdateAuditRequest;
import com.zebone.dip.log.dao.DocLogMapper;
import com.zebone.dip.log.dao.EmpiLogMapper;
import com.zebone.empi.service.AuditService;
import com.zebone.empi.service.EmpiService;

/**
 * 主索引管理服务实现类
 *
 * @author 杨英
 * @version 2014-7-15 下午3:06
 */
@Service("empiManageService")
public class EmpiManageServiceImpl implements EmpiManageService {
	
    private static final Log log = LogFactory.getLog(EmpiManageServiceImpl.class);
    
 
   
    @Resource
    private EmpiInfoMapper empiInfoMapper;

    @Resource
    private ResidentCardMapper residentCardMapper;

    @Resource
    private EmpiLogInfoMapper empiLogInfoMapper;

    @Resource
    private DocLogMapper docLogMapper;

    @Resource
    private UpdateApplyMapper updateApplyMapper;

    @Resource
    private UnbindApplyMapper unbindApplyMapper;
    
    @Resource
    private EmpiLogMapper empiLogMapper;

    private JaxWsProxyFactoryBean jw = new JaxWsProxyFactoryBean();

    @Value("${url.empiAudit}")
    private String empiAuditUrl;
    
    @Value("${url.empiAuditApply}")
    private String empiAuditApply;
    


    @Override
    public Pagination<EmpiInfo> empiInfoPage(Pagination<EmpiInfo> page, ResidentCard residentCard) {
        List<EmpiInfo> list = empiInfoMapper.empiInfoList(page.getRowBounds(), residentCard);
        int totalCount = empiInfoMapper.empiInfoTotalCount(residentCard);
        page.setData(list);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public Pagination<EmpiLogInfo> operateList(Pagination<EmpiLogInfo> page, EmpiInfo empiInfo) {
        if (empiInfo != null) {
            List<EmpiLogInfo> list = empiLogInfoMapper.operateList(page.getRowBounds(), empiInfo.getEmpi());
            int totalCount = empiLogInfoMapper.operateTotalCount(empiInfo.getEmpi());
            page.setData(list);
            page.setTotalCount(totalCount);
        }
        return page;
    }

    @Override
    public EmpiInfo getEmpiDetails(String empiNo) {
        EmpiInfo empiInfo = empiInfoMapper.getEmpiDetail(empiNo);
        if(empiInfo!=null){
            Map<String, Map<String, String>> typeMap = DictionaryConvert.getDictionary();
            //将字典编码替换成字典值
            String sex = empiInfo.getSex();
            empiInfo.setSex(typeMap.get("sex").containsKey(sex) ? typeMap.get("sex").get(sex) : "");
            String nation = empiInfo.getNation();
            empiInfo.setNation(typeMap.get("nation").containsKey(nation) ? typeMap.get("nation").get(nation) : "");
            String nationality = empiInfo.getNationality();
            empiInfo.setNationality(typeMap.get("nationality").containsKey(nationality) ? typeMap.get("nationality").get(nationality) :"");
            String marital = empiInfo.getMaritalStatus();
            empiInfo.setMaritalStatus(typeMap.get("matrimony").containsKey(marital) ? typeMap.get("matrimony").get(marital) :"");
            String level = empiInfo.getStarLevel();
            empiInfo.setStarLevel(typeMap.get("starLevel").containsKey(level) ? typeMap.get("starLevel").get(level) : "");
            Date birthday = empiInfo.getBirthday();
            if(birthday!=null){
                empiInfo.setStrBirthday(new SimpleDateFormat("yyyy-MM-dd").format(birthday));
            }
        }
        return empiInfo;
    }

    @Override
    public List<ResidentCard> getCardList(String empiNo) {
        List<ResidentCard> cardList = residentCardMapper.getCardList(empiNo);
        if (cardList != null) {
            for (ResidentCard residentCard : cardList) {
                Map<String, Map<String, String>> typeMap = DictionaryConvert.getDictionary();
                String cardType = residentCard.getCardType();
                residentCard.setCardType(typeMap.get("cardType").containsKey(cardType) ? typeMap.get("cardType").get(cardType) : "");
            }
        }
        return cardList;
    }

    @Override
    public Pagination<EmpiCount> empiCountList(Pagination<EmpiCount> page, EmpiCount empiCount) {
        List<EmpiCount> data = empiLogInfoMapper.getEmpiCountList(empiCount, page.getRowBounds());
        int totalCount = empiLogInfoMapper.getEmpiCountListNum(empiCount);
        if (data != null && data.size() > 0) {
            for (EmpiCount count : data) {
                //根据机构编码获取机构名称
                count.setOrgName(docLogMapper.getOrgName(count.getOrgCode()));
            }
        }
        page.setData(data);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public Pagination<UpdateApply> updateApplyList(Pagination<UpdateApply> page, UpdateApply updateApply) {
        List<UpdateApply> data = updateApplyMapper.getUpdateApplyList(updateApply, page.getRowBounds());
        int totalCount = updateApplyMapper.getUpdateApplyCount(updateApply);
        if (data != null && data.size() > 0) {
            for (UpdateApply apply : data) {
                //根据机构编码获取机构名称
                apply.setOrgName(docLogMapper.getOrgName(apply.getOrgCode()));
            }
        }
        page.setData(data);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public UpdateApply getUpdateApplyInfo(String empiNo) {
        UpdateApply updateApply = updateApplyMapper.getUpdateApplyInfo(empiNo);
        if(updateApply!=null){
            Map<String, Map<String, String>> typeMap = DictionaryConvert.getDictionary();
            //将字典编码替换成字典值
            String sex = updateApply.getSex();
            updateApply.setSex(typeMap.get("sex").containsKey(sex) ? typeMap.get("sex").get(sex) : "");
            String nation = updateApply.getNation();
            updateApply.setNation(typeMap.get("nation").containsKey(nation) ? typeMap.get("nation").get(nation) : "");
            Date birthday = updateApply.getBirthday();
            if(birthday!=null){
                updateApply.setStrBirthday(new SimpleDateFormat("yyyy-MM-dd").format(birthday));
            }
        }
        return updateApply;
    }

    @Override
    public int auditRealInfoApply(UpdateApply updateApply, String auditResult) {
        int result = 0;
        String auditParam = null;
        XStream xs = new XStream(new DomDriver("UTF-8", new NoNameCoder()));
        UpdateAuditRequest updateAuditRequest = new UpdateAuditRequest();
        //从数据库中获取实名信息更新申请记录详情
        UpdateApply updateApplyRecord = updateApplyMapper.getUpdateApplyInfo(updateApply.getEmpi());
        try {
            if (updateApplyRecord != null) {
                Date birthday = updateApplyRecord.getBirthday();
                if (birthday != null) {
                    updateApplyRecord.setStrBirthday(new SimpleDateFormat("yyyyMMdd").format(birthday));
                }
                byte[] photo = updateApplyRecord.getPhoto();
                if (photo != null && photo.length > 0) {
                    updateApplyRecord.setStrPhoto(new String(photo));
                }
                BeanUtils.copyProperties(updateAuditRequest, updateApplyRecord);
                updateAuditRequest.setAuditDesc(updateApply.getAuditDesc());
                updateAuditRequest.setAuditResult(auditResult);
                xs.processAnnotations(UpdateAuditRequest.class);
                auditParam = xs.toXML(updateAuditRequest);

                jw.setAddress(empiAuditUrl);
                jw.setServiceClass(AuditService.class);
                AuditService auditService = (AuditService) jw.create();
                String returnInfo = auditService.auditForUpdate(auditParam);

                Document doc = DocumentHelper.parseText(returnInfo);
                Element root = doc.getRootElement();
                Element el1 = root.element("isSuccess");
                String returnFlag = el1.getTextTrim();
                if (returnFlag != null && "1".equals(returnFlag)) {
                    result = 1;     //允许或拒绝更新成功
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Pagination<UnbindApply> unbindApplyList(Pagination<UnbindApply> page, UnbindApply unbindApply) {
        List<UnbindApply> data = unbindApplyMapper.getUnbindApplyList(unbindApply, page.getRowBounds());
        int totalCount = unbindApplyMapper.getUnbindApplyCount(unbindApply);
        if (data != null && data.size() > 0) {
            Map<String, Map<String, String>> typeMap = DictionaryConvert.getDictionary();
            for (UnbindApply unbindRec : data) {
                //根据机构编码获取机构名称
                unbindRec.setOrgName(docLogMapper.getOrgName(unbindRec.getOrgCode()));
                String secCardType = unbindRec.getSecCardType();
                unbindRec.setSecCardType(typeMap.get("cardType").containsKey(secCardType) ? typeMap.get("cardType").get(secCardType) : "");
                String firstCardType = unbindRec.getFirstCardType();
                unbindRec.setFirstCardType(typeMap.get("cardType").containsKey(firstCardType) ? typeMap.get("cardType").get(firstCardType) :"");
            }
        }
        page.setData(data);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public UnbindApply getUnbindApplyInfo(String id) {
        UnbindApply unbindApply = unbindApplyMapper.getUnbindApplyInfo(id);
        if(unbindApply!=null){
            Map<String, Map<String, String>> typeMap = DictionaryConvert.getDictionary();
            //将字典编码替换成字典值
            String sex = unbindApply.getSex();
            unbindApply.setSex(typeMap.get("sex").containsKey(sex) ? typeMap.get("sex").get(sex) : "");
            String nation = unbindApply.getNation();
            unbindApply.setNation(typeMap.get("nation").containsKey(nation) ? typeMap.get("nation").get(nation) : "");
            String secCardType = unbindApply.getSecCardType();
            unbindApply.setSecCardType(typeMap.get("cardType").containsKey(secCardType) ? typeMap.get("cardType").get(secCardType) : "");
            String firstCardType = unbindApply.getFirstCardType();
            unbindApply.setFirstCardType(typeMap.get("cardType").containsKey(firstCardType) ? typeMap.get("cardType").get(firstCardType) : "");
            Date birthday = unbindApply.getBirthday();
            if(birthday!=null){
                unbindApply.setStrBirthday(new SimpleDateFormat("yyyy-MM-dd").format(birthday));
            }
        }
        return unbindApply;
    }

    @Override
    public int auditUnbindApply(UnbindApply unbindApply, String auditResult) {
        int result = 0;
        String auditParam = null;
        XStream xs = new XStream(new DomDriver("UTF-8", new NoNameCoder()));
        UnbindAuditRequest unbindAuditRequest = new UnbindAuditRequest();
        //从数据库中获取解绑申请记录详情
        UnbindApply unbindApplyRecord = unbindApplyMapper.getUnbindApplyInfo(unbindApply.getId());
        try {
            if (unbindApplyRecord != null) {
                Date birthday = unbindApplyRecord.getBirthday();
                if (birthday != null) {
                    unbindApplyRecord.setStrBirthday(new SimpleDateFormat("yyyyMMdd").format(birthday));
                }
                byte[] photo = unbindApplyRecord.getPhoto();
                if (photo != null && photo.length > 0) {
                    unbindApplyRecord.setStrPhoto(new String(photo));
                }
                BeanUtils.copyProperties(unbindAuditRequest, unbindApplyRecord);
                unbindAuditRequest.setAuditDesc(unbindApply.getAuditDesc());
                unbindAuditRequest.setAuditResult(auditResult);
                xs.processAnnotations(UnbindAuditRequest.class);
                auditParam = xs.toXML(unbindAuditRequest);
                jw.setAddress(empiAuditUrl);
                jw.setServiceClass(AuditService.class);
                AuditService auditService = (AuditService) jw.create();
                String returnInfo = auditService.auditForunBind(auditParam);

                Document doc = DocumentHelper.parseText(returnInfo);
                Element root = doc.getRootElement();
                Element el1 = root.element("isSuccess");
                String returnFlag = el1.getTextTrim();
                if (returnFlag != null && "1".equals(returnFlag)) {
                    result = 1;     //允许或拒绝解绑成功
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

	@Override
	public Map<String, Object> saveUpdateApply(
			ResidentUpdateApply residentUpdateApply) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
        boolean success = true;
        String msg = null;
		try {
			UserDetails userDetails = (UserDetails) SecurityUtils.getSubject().getPrincipal();
			
			String orgCode= userDetails.getLevelCode();
			String cardNo = residentUpdateApply.getCardNo();
			String name = residentUpdateApply.getName();
			
			ResidentCard residentCard = residentCardMapper.getCardByNoAndType(cardNo, "1");
			if(residentCard == null){
				success = false;
				msg = "主索引不存在";
			}else{
				String empi = residentCard.getEmpi();
				EmpiInfo empiInfo = empiInfoMapper.getEmpiDetail(empi);
				
				
				XStream xs = new XStream(new DomDriver("UTF-8", new NoNameCoder()));
				xs.processAnnotations(ApplyUpdateReq.class);
				
				ApplyUpdateReq applyUpdateReq = new ApplyUpdateReq();
				applyUpdateReq.setCardNo(cardNo);
				//applyUpdateReq.setOrgCode(orgCode);
				applyUpdateReq.setOrgCode("123456789");
				applyUpdateReq.setName(name);
				applyUpdateReq.setSex(empiInfo.getSex());
				applyUpdateReq.setNation(empiInfo.getNation());
				applyUpdateReq.setPermanentAddress(empiInfo.getPermanentAddress());
				
				SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
		        String birthday = formatDate.format(empiInfo.getBirthday());
				applyUpdateReq.setStrBirthday(birthday);
				
				if(empiInfo.getPhoto() != null){
					applyUpdateReq.setStrPhoto(new String(empiInfo.getPhoto()));
				}
				
				String queryXml = xs.toXML(applyUpdateReq);
				
				JaxWsProxyFactoryBean jw1 = new JaxWsProxyFactoryBean();
				jw1.setAddress(empiAuditApply);
				jw1.setServiceClass(EmpiService.class);
				EmpiService empiService = (EmpiService) jw1.create();
				String queryResultXml = empiService.applyForUpdate(queryXml);
				
				xs.processAnnotations(ApplyUpdateRes.class);
				ApplyUpdateRes applyUpdateRes = (ApplyUpdateRes)xs.fromXML(queryResultXml);
				String isSuccess = applyUpdateRes.getSuccess();
				if("2".equals(isSuccess)){
					 success = false;
					 msg = applyUpdateRes.getErrorMsg();
				}
				result.put("empi", empi);
				result.put("cardno", cardNo);
			}
		} catch (Exception e) {
		    success = false;
		    msg="系统错误";
			log.error(e.getMessage(), e);
		}finally{
			result.put("success", success);
			result.put("msg", msg);
		}
		return result;
	}

	@Override
	public Map<String, Object> saveUnbindApply(UnbindApplyParameter unbindApplyParameter) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
        boolean success = true;
        String msg = null;
		try {
			ResidentCard unbindCard = residentCardMapper.getCardByNoAndType(unbindApplyParameter.getUnbindCardNo(), unbindApplyParameter.getUnbindCardType());
			if(unbindCard == null){
				success = false;
				msg = "解绑二级卡号不存在";
				result.put("success", success);
				result.put("msg", msg);
				return result;
			}
			
			ResidentCard rebindCard = residentCardMapper.getCardByNoAndType(unbindApplyParameter.getBindCardNo(), unbindApplyParameter.getBindCardType());
			if(rebindCard == null){
				success = false;
				msg = "重新绑定的一级卡号不存在";
				result.put("success", success);
				result.put("msg", msg);
				return result;
			}
			
			EmpiInfo empiInfo = empiInfoMapper.getEmpiDetail(rebindCard.getEmpi());
			
			EmpiUnbindApply empiUnbindApply = new EmpiUnbindApply();
			empiUnbindApply.setName(empiInfo.getName());
			empiUnbindApply.setSex(empiInfo.getSex());
			empiUnbindApply.setNation(empiInfo.getNation());
			
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
	        String birthday = formatDate.format(empiInfo.getBirthday());
	        empiUnbindApply.setBirthday(birthday);
	        empiUnbindApply.setPermanentAddress(empiInfo.getPermanentAddress());
			
			if(empiInfo.getPhoto() != null){
				empiUnbindApply.setPhoto(new String(empiInfo.getPhoto()));
			}
			
			UserDetails userDetails = (UserDetails) SecurityUtils.getSubject().getPrincipal();
			String orgCode= userDetails.getLevelCode();
			//empiUnbindApply.setOrgCode(orgCode);
			empiUnbindApply.setOrgCode("123456789");
			empiUnbindApply.setFirstCardNo(rebindCard.getCardNo());
			empiUnbindApply.setFirstCardType(rebindCard.getCardType());
			empiUnbindApply.setSecCardNo(unbindCard.getCardNo());
			empiUnbindApply.setSecCardType(unbindCard.getCardType());
			empiUnbindApply.setSecCardOrg(unbindCard.getCardOrg());
			
			XStream xs = new XStream(new DomDriver("UTF-8", new NoNameCoder()));
			xs.processAnnotations(EmpiUnbindApply.class);
			String queryXml = xs.toXML(empiUnbindApply);
			
			
			JaxWsProxyFactoryBean jw1 = new JaxWsProxyFactoryBean();
			jw1.setAddress(empiAuditApply);
			jw1.setServiceClass(EmpiService.class);
			EmpiService empiService = (EmpiService) jw1.create();
			String applyForUnbindResult = empiService.applyForUnbind(queryXml);
			xs.processAnnotations(ApplyUnbindResult.class);
			ApplyUnbindResult applyUnbindResult = (ApplyUnbindResult)xs.fromXML(applyForUnbindResult);
			String isSuccess = applyUnbindResult.getSuccess();
			result.put("id", applyUnbindResult.getUnbindApplyId());
			result.put("empi", unbindCard.getEmpi());
			if("2".equals(isSuccess)){
				 success = false;
				 msg = applyUnbindResult.getErrorMsg();
			}
		}catch (Exception e) {
		    success = false;
		    msg="系统错误";
			log.error(e.getMessage(), e);
		}finally{
			result.put("success", success);
			result.put("msg", msg);
		}
		return result;
	
	}

}
