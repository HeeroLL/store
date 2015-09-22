package com.zebone.dip.log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.log.dao.DocLogMapper;
import com.zebone.dip.log.service.DocViewLogService;
import com.zebone.dip.log.service.EmpiLogService;
import com.zebone.dip.log.vo.EmpiResult;
import com.zebone.dip.log.vo.EmpiSearchObj;
import com.zebone.dip.log.vo.ParmInfo;

/**
 * empi controller
 * 
 * @author ycm
 * 
 */
@Controller
public class EmpiLogController {

	@Resource
	private DocViewLogService docViewService;

	@Resource
	private DocLogMapper docLogMapper;

	@Resource
	EmpiLogService empiLogService;

	/**
	 * 获取机构与其编码的hash表
	 * 
	 * @param session
	 */
	private Map<String, String> getOrgInfoMapFromSession(HttpSession session) {
		if (session.getAttribute("orgInfoMap") == null) {
			List<ParmInfo> orgList = docLogMapper.getAllOrganInfo();
			Map<String, String> orgInfoMap = new HashMap<String, String>();

			for (ParmInfo pi : orgList) {
				orgInfoMap.put(pi.getCode(), pi.getName());
			}

			session.setAttribute("orgInfoMap", orgInfoMap);
			return orgInfoMap;
		} else {
			@SuppressWarnings("unchecked")
			Map<String, String> map = (Map<String, String>) session
					.getAttribute("orgInfoMap");
			return map;
		}
	}

	/**
	 * 获取卡类型标志与其编码的hash表
	 * 
	 * @param session
	 */
	private Map<String, String> getCardTypeMapFromSession(HttpSession session) {
		if (session.getAttribute("cardTypeMap") == null) {
			List<ParmInfo> cardTypeList = docViewService.getCardInfo();
			Map<String, String> cardTypeMap = new HashMap<String, String>();

			for (ParmInfo pi : cardTypeList) {
				cardTypeMap.put(pi.getCode(), pi.getName());
			}

			session.setAttribute("cardTypeMap", cardTypeMap);
			
			return cardTypeMap;
		} else {
			@SuppressWarnings("unchecked")
			Map<String, String> map = (Map<String, String>) session
					.getAttribute("cardTypeMap");
			return map;
		}

	}

	/**
	 * 注册更新页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "log/logEmpiModify")
	public String searchAddAndUpdateLogIndex(ModelMap map,	HttpServletRequest request) {
		List<ParmInfo> cardTypeList = docViewService.getCardInfo();
		map.put("cardTypeList", cardTypeList);
		if (request.getSession().getAttribute("cardTypeMap") == null) {
			Map<String, String> cardTypeMap = new HashMap<String, String>();

			for (ParmInfo pi : cardTypeList) {
				cardTypeMap.put(pi.getCode(), pi.getName());
			}

			request.getSession().setAttribute("cardTypeMap", cardTypeMap);
		}

		return "dip/log/log_empi_modify";
	}

	/**
	 * 注册更新查询结果
	 * 
	 * @param p
	 * @param empiSearchObj
	 * @return
	 */
	@RequestMapping(value = "log/empiUpdateoradd", method = RequestMethod.POST)
	@ResponseBody
	public JsonGrid searchAddUpdateLog(Pagination<EmpiResult> p, EmpiSearchObj empiSearchObj, HttpServletRequest request) {
		if (empiSearchObj.getStatus() == null
				|| empiSearchObj.getStatus().trim().equals("")) {
			empiSearchObj.setStatus("0");
		}

		p = empiLogService.searchAddUpdateLog(empiSearchObj, p);
		convertCodeToName(p.getData(),request.getSession());
		JsonGrid jGrid = new JsonGrid("true", p.getTotalCount(), p.getData());
		return jGrid;
	}

	/**
	 * 查询页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "log/logEmpiQuery")
	public String searchQueryLog(ModelMap map, HttpServletRequest request) {
		List<ParmInfo> cardTypeList = docViewService.getCardInfo();
		map.put("cardTypeList", cardTypeList);
		if (request.getSession().getAttribute("cardTypeMap") == null) {
			Map<String, String> cardTypeMap = new HashMap<String, String>();

			for (ParmInfo pi : cardTypeList) {
				cardTypeMap.put(pi.getCode(), pi.getName());
			}
			request.getSession().setAttribute("cardTypeMap", cardTypeMap);
		}
		return "dip/log/log_empi_query";
	}

	/**
	 * 查询结果
	 * 
	 * @param p
	 * @param empiSearchObj
	 * @return
	 */
	@RequestMapping(value = "log/empiSearch", method = RequestMethod.POST)
	@ResponseBody
	public JsonGrid searchQueryLog(Pagination<EmpiResult> p, EmpiSearchObj empiSearchObj, HttpServletRequest request) {
		p = empiLogService.searchQueryLog(empiSearchObj, p);
		convertCodeToName(p.getData(),request.getSession());
		JsonGrid jGrid = new JsonGrid("true", p.getTotalCount(), p.getData());
		return jGrid;
	}

	/**
	 * 将EmpiResult中的，机构编码和类型标志编码转换成名称
	 * 
	 * @param list
	 */
	private void convertCodeToName(List<EmpiResult> list, HttpSession session) {
		Map<String, String> cardTypeMap = getCardTypeMapFromSession(session);
		Map<String, String> orgInfoMap = getOrgInfoMapFromSession(session);
		for(EmpiResult er : list){
			//机构编码
			if(er.getOrgCode()!=null){
				er.setOrgName(orgInfoMap.get(er.getOrgCode()));
			}
			if(er.getOrgName()==null || er.getOrgName().trim().equals("")){
				er.setOrgName("-----");
			}
			//卡类型标识
			if(er.getCardType()!=null){
				er.setCardType(cardTypeMap.get(er.getCardType()));
			}
			if(er.getCardType()==null || er.getCardType().trim().equals("")){
				er.setCardType("-----");
			}
			
			
		}
	}

	
	
	
}
