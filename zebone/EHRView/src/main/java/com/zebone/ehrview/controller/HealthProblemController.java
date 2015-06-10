package com.zebone.ehrview.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zebone.ehrview.service.HealthProblemService;
import com.zebone.ehrview.service.HomeService;
import com.zebone.ehrview.vo.HealthMenuItem;
import com.zebone.ehrview.vo.HealthProblem;
import com.zebone.ehrview.vo.ResidentInfo;

/**
 * 健康和疾病问题controller
 * @author YinCM
 *
 */
@Controller
public class HealthProblemController {
	
	@Resource
	HealthProblemService healthProblemService;
	@Resource
	HomeService homeService;
	/**
	 * 健康与疾病问题列表
	 * @param model
	 * @return
	 */
	@RequestMapping("healthProblemList")
	public String healthProblemList(HttpServletRequest request, Model model, @RequestParam(value="empiId", required=false) String empiId, String dataCode,
                                    String searchInfo, String patientInfo ){
		if(empiId==null){
			empiId = "";
			return "HealthProblem/empty";
		}
		//为了在前端显示当前grid所属菜单名称，将code与名称的键值对存入session，如果存在则从session获取，不存在则直接获取
		Object healthMenuMap = request.getSession().getAttribute("healthMenuMap");
		Map<String, String> codeNameMap = new HashMap<String, String>();
		if(healthMenuMap==null){
			List<HealthMenuItem> healthMenuItemList = homeService.getHealthMenuItems(empiId);
			for(HealthMenuItem hmi : healthMenuItemList){
				codeNameMap.put(hmi.getCode(), hmi.getName());
			}
			request.getSession().setAttribute("healthMenuMap",codeNameMap);
		}else{
			codeNameMap = (Map<String,String>)healthMenuMap;
		}
		
		//首先查看session中是否存在，存在，则赋值，不存在则从ws中获取
		Object obj = request.getSession().getAttribute("basicInfoMap");
		ResidentInfo ri = null;
		if(obj==null || ((ResidentInfo)obj).getInfoMap()==null){
            String[] patientInfoLic = patientInfo.split(","); //patientInfo中包含卡类型,卡号，患者姓名
            String[] searchInfoLic = searchInfo.split(",");  //searchInfo中包含机构编码，医生编码
            ri = homeService.getPatientBasicInfo(patientInfoLic[2], patientInfoLic[1], patientInfoLic[0],searchInfoLic[0]);
		}else{
			ri = (ResidentInfo)obj;
        }
        String patientAge = ri.getInfoMap().get("birthday");
        model.addAttribute("patientage", patientAge);

        List<HealthProblem> healthProblemList = healthProblemService.getHealthProblemList(empiId, dataCode);
		model.addAttribute("healthProblemList",healthProblemList);
		model.addAttribute("empiId", empiId);
		model.addAttribute("dataCode", dataCode);
		model.addAttribute("healthName", codeNameMap.get(dataCode));
        model.addAttribute("searchInfo", searchInfo);
        model.addAttribute("patientInfo", patientInfo);
		return "HealthProblem/healthProblemList";
	}

	public HealthProblemService getHealthProblemService() {
		return healthProblemService;
	}

	public void setHealthProblemService(HealthProblemService healthProblemService) {
		this.healthProblemService = healthProblemService;
	}

	public HomeService getHomeService() {
		return homeService;
	}

	public void setHomeService(HomeService homeService) {
		this.homeService = homeService;
	}
	
}