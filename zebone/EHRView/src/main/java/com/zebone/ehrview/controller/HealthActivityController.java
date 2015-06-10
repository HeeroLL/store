package com.zebone.ehrview.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zebone.ehrview.service.HealthActivityService;
import com.zebone.ehrview.vo.AdultHealthExam;
import com.zebone.ehrview.vo.CommonListItem;
import com.zebone.ehrview.vo.Hypertension;
import com.zebone.ehrview.vo.OldersFollowup;

/**
 * 卫生服务活动相关controller
 * @author YinCM
 *
 */
@Controller
public class HealthActivityController {
	

/*	@RequestMapping("healthServiceActivity")
	public String hypertentionDoc(Model model){
		model.addAttribute("name", "charmyin");
		return "HealthServiceActivity/healthServiceActivity";
	}*/
	@Resource
	HealthActivityService healthActivityService;
	
	/**
	 * 高血压随访列表
	 * @param model
	 * @return
	 */
	@RequestMapping("hypertentionFollowup")
	public String hypertentionFollowup(Model model, @RequestParam(value="empiId", required=false) String empiId){
		if(empiId==null){
			empiId = "";
			return "HealthServiceActivity/empty";
		}
		List<Hypertension> hypertensionList = healthActivityService.getHypertensionFollowupList(empiId);
		model.addAttribute(hypertensionList);
		model.addAttribute("empiId", empiId);
		model.addAttribute("dataCode", "B04.01.02.00");
        //高血压专档的文档类型编码
        model.addAttribute("dataCode1","B04.01.01.00");
		return "HealthServiceActivity/hypertentionFollowup";
	}
	
	 
	/**
	 * 老年人保健随访列表
	 * @param model
	 * @return
	 */
	@RequestMapping("oldersFollowup")
	public String oldersFollowup(Model model, @RequestParam(value="empiId", required=false) String empiId){
		if(empiId==null){
			empiId = "";
			return "HealthServiceActivity/empty";
		}
		List<OldersFollowup> oldersFollowupList = healthActivityService.getOldersFollowupList(empiId);
		model.addAttribute("empiId", empiId);
		model.addAttribute("dataCode", "B04.04.01.00");
		model.addAttribute("oldersFollowupList",oldersFollowupList);
		return "HealthServiceActivity/oldersFollowup";
	}
	
	/**
	 * 成人健康体检列表
	 * @param model
	 * @return
	 */
	@RequestMapping("adultsHealthExam")
	public String adultsHealthExam(Model model, @RequestParam(value="empiId", required=false) String empiId){
		if(empiId==null){
			empiId = "";
			return "HealthServiceActivity/empty";
		}
		List<AdultHealthExam> adultsHealthExamList = healthActivityService.getAdultHealthExamList(empiId);
		model.addAttribute("adultsHealthExamList",adultsHealthExamList);
		model.addAttribute("empiId", empiId);
		model.addAttribute("dataCode", "C00.04.01.00");
		return "HealthServiceActivity/adultsHealthExam";
	}
	
	/**
	 * 糖尿病随访列表
	 * @param model
	 * @return
	 */
	@RequestMapping("diabetesFollowup")
	public String diabetesFollowup(Model model, @RequestParam(value="empiId", required=false) String empiId){
		if(empiId==null){
			empiId = "";
			return "HealthServiceActivity/empty";
		}
		List<Hypertension> diabetesList = healthActivityService.getDiabetesFollowupList(empiId);
		model.addAttribute(diabetesList);
		model.addAttribute("empiId", empiId);
		model.addAttribute("dataCode", "B04.02.02.00");
        //糖尿病专档的文档类型编码
        model.addAttribute("dataCode1", "B04.02.01.00");
		return "HealthServiceActivity/diabetesFollowup";
	}

	
	/**
	 * 成人健康体检列表
	 * @param model
	 * @return
	 */
	@RequestMapping("healthActivityCommonList")
	public String healthActivityCommonList(Model model, @RequestParam(value="empiId", required=false) String empiId,
                                           @RequestParam(value="dataCode", required=true) String dataCode,
                                           @RequestParam(value = "searchInfo", required = false) String searchInfo,
                                           @RequestParam(value = "patientInfo", required = false) String patientInfo){
		if(empiId==null){
			empiId = "";
			return "HealthServiceActivity/empty";
		}
		
		HashMap<String, Integer> code_type_hash = new HashMap<String,Integer>();
		code_type_hash.put("B04.01.02.00", 1);
		code_type_hash.put("B04.02.02.00", 2);
		code_type_hash.put("B04.04.01.00", 3);
		code_type_hash.put("C00.04.01.00", 4);
		code_type_hash.put("C00.01.02.00", 5);
		code_type_hash.put("C00.02.01.00", 6);
		code_type_hash.put("C00.04.04.00", 9);
		code_type_hash.put("B01.03.01.00", 101);
		code_type_hash.put("B01.03.02.00", 102);
		code_type_hash.put("B01.03.03.00", 103);
		code_type_hash.put("B02.02.01.00", 111);
		code_type_hash.put("B02.02.02.00", 112);
		
		/**
		 * 1:高血压随访;2:糖尿病随访;3:老年人保健随访;4:成人健康体检;9：一般体检；10：儿童随访（101:1岁内；102：1-2岁；103:3-6岁）； 11：妇女随访（111：第一次产前随访；112：2-5次产前随访）
		 */
		int type = code_type_hash.get(dataCode);
		model.addAttribute("doctortitle", "随访医生");
		model.addAttribute("departmenttitle", "随访机构");
		model.addAttribute("timetitle", "随访日期");
        model.addAttribute("serviceName","疾病管理-->高血压报卡");
		switch(type){
			case 1:
				model.addAttribute("empiId", empiId);
				model.addAttribute("title", "高血压随访");
				model.addAttribute("url", "hypertensionDetails.zb");
				model.addAttribute("dataCode", "B04.01.02.00");
				break;
			case 2:
				model.addAttribute("empiId", empiId);
				model.addAttribute("title", "糖尿病随访");
				model.addAttribute("url", "diabetesDetails.zb");
				model.addAttribute("dataCode", "B04.02.02.00");
                model.addAttribute("serviceName","疾病管理-->糖尿病报卡");
				break;
			case 3:
				model.addAttribute("empiId", empiId);
				model.addAttribute("title", "老年人保健随访");
				model.addAttribute("url", "healthCareDetails.zb");
				model.addAttribute("dataCode", "B04.04.01.00");
                model.addAttribute("serviceName","疾病管理-->老年人健康管理");
				break;
			case 4:
				model.addAttribute("empiId", empiId);
                model.addAttribute("serviceName","个人基本信息");
				model.addAttribute("title", "成人健康体检");
				model.addAttribute("departmenttitle", "服务机构");
				model.addAttribute("doctortitle", "责任医生");
				model.addAttribute("timetitle", "体检日期");
				model.addAttribute("url", "healthCheck.zb");
				model.addAttribute("dataCode", "C00.04.01.00");
				break;
			case 5:
				model.addAttribute("empiId", empiId);
                model.addAttribute("serviceName","医疗服务");
				model.addAttribute("title", "门诊");
				model.addAttribute("timetitle", "就诊时间");
				model.addAttribute("departmenttitle", "服务机构");
				model.addAttribute("doctortitle", "医生");
                model.addAttribute("clinicDept","就诊科室");
				model.addAttribute("url", "outPatient.zb");
				model.addAttribute("dataCode", "C00.01.02.00");
				break;
			case 6:
				model.addAttribute("empiId", empiId);
                model.addAttribute("serviceName","医疗服务");
				model.addAttribute("title", "住院");
				model.addAttribute("timetitle", "入院时间");
				model.addAttribute("doctortitle", "医生");
				model.addAttribute("departmenttitle", "服务机构");
                model.addAttribute("clinicDept","入院科室");
				model.addAttribute("url", "aaa.zb");
				model.addAttribute("dataCode", "C00.02.01.00");
				break;
			case 9:
				model.addAttribute("empiId", empiId);
                model.addAttribute("serviceName","医疗服务");
				model.addAttribute("title", "一般体检");
				model.addAttribute("departmenttitle", "服务机构");
				model.addAttribute("timetitle", "体检日期");
				model.addAttribute("doctortitle", "责任医生");
				model.addAttribute("url", "aaa.zb");
				model.addAttribute("dataCode", "C00.04.04.00");
				break;
			case 101:
				model.addAttribute("empiId", empiId);
				model.addAttribute("title", "儿童随访(1岁内)");
				model.addAttribute("departmenttitle", "随访机构");
				model.addAttribute("url", "aaa.zb");
				model.addAttribute("dataCode", "B01.03.01.00");
				break;
			case 102:
				model.addAttribute("empiId", empiId);
				model.addAttribute("title", "儿童随访(1-2岁)");
				model.addAttribute("departmenttitle", "随访机构");
				model.addAttribute("url", "aaa.zb");
				model.addAttribute("dataCode", "B01.03.02.00");
				break;
			case 103:
				model.addAttribute("empiId", empiId);
				model.addAttribute("title", "儿童随访(3-6岁)");
				model.addAttribute("departmenttitle", "随访机构");
				model.addAttribute("url", "aaa.zb");
				model.addAttribute("dataCode", "B01.03.03.00");
				break;
			case 111:
				model.addAttribute("empiId", empiId);
				model.addAttribute("title", "第1次产前随访");
				model.addAttribute("departmenttitle", "随访机构");
				model.addAttribute("url", "aaa.zb");
				model.addAttribute("dataCode", "B02.02.01.00");
				break;
			case 112:
				model.addAttribute("empiId", empiId);
				model.addAttribute("title", "第2-5次产前随访");
				model.addAttribute("departmenttitle", "随访机构");
				model.addAttribute("url", "aaa.zb");
				model.addAttribute("dataCode", "B02.02.02.00");
				break;
			default:
				return "HealthServiceActivity/empty";
		}
		List<CommonListItem> commonList = healthActivityService.getCommonList(empiId, dataCode);
		model.addAttribute("commonList",commonList);
        model.addAttribute("searchInfo",searchInfo);
        model.addAttribute("patientInfo",patientInfo);
		return "HealthServiceActivity/commonList";
	}
	
	
	

	public HealthActivityService getHealthActivityService() {
		return healthActivityService;
	}


	public void setHealthActivityService(HealthActivityService healthActivityService) {
		this.healthActivityService = healthActivityService;
	}
	
}
