package com.zebone.ehrview.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zebone.ehrview.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.ehrview.service.BasicInfoService;
import com.zebone.ehrview.service.HomeService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Resource
	public BasicInfoService basicInfoService;
	
	@Resource
	public HomeService homeService;

	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("index")
	public String home(HttpServletRequest request, Model model){
		//首先查看session中是否存在，存在，则赋值，不存在则从ws中获取
		Object obj = request.getSession().getAttribute("basicInfoMap");
		ResidentInfo ri = null;
		if(obj==null || ((ResidentInfo)obj).getInfoMap()==null){
            model.addAttribute("errorMsg", "用户信息已过期，请重新登录");
            return "loginError";
//			ri = homeService.getPatientBasicInfo("","","");
//			request.getSession().setAttribute("basicInfoMap",ri);
		}else{
			ri = (ResidentInfo)obj;
            int patientAge = getPatientAge(ri.getInfoMap().get("birthday"));
            ri.getInfoMap().put("age",patientAge+"");
		}
		model.addAllAttributes(ri.getInfoMap());

        //searchInfo与patientInfo用于文档调阅日志的记录
        model.addAttribute("searchInfo", ri.getSearchInfo());
        model.addAttribute("patientInfo", ri.getPatientInfo());

        String[] patientInfos = new String[3];
        if(ri.getPatientInfo()!=null) patientInfos = ri.getPatientInfo().split(",");
        model.addAttribute("patientCardType",getCardTypeDesc(patientInfos[0]));
        model.addAttribute("patientCardNo",patientInfos[1]);

		//卫生服务活动菜单列表
		List<ServiceMenuItem> smiL = homeService.getServiceMenuItems(ri.getInfoMap().get("empi"));
		model.addAttribute("menuList",smiL);
		//健康疾病列表
		List<HealthMenuItem> healthMenuItemList = homeService.getHealthMenuItems(ri.getInfoMap().get("empi"));
		model.addAttribute("healthMenuItemList",healthMenuItemList);
		//生命阶段列表
		return "indexNew";
	}
	
	/**
	 * empi详细
	 * @return
	 */
	@RequestMapping("empiDetail")
	public String empiDetail(HttpServletRequest request, Model model){
		//首先查看session中是否存在，存在，则赋值，不存在则从ws中获取
		Object obj = request.getSession().getAttribute("basicInfoMap");
		ResidentInfo ri = null;
		if(obj==null || ((ResidentInfo)obj).getInfoMap()==null){
            model.addAttribute("errorMsg", "用户信息已过期，请重新登录");
            return "loginError";
//			ri = homeService.getPatientBasicInfo("","","");
//			request.getSession().setAttribute("basicInfoMap",ri);
		}else{
			ri = (ResidentInfo)obj;
		}
		//获取empi基本信息和card信息，赋予model，前端采用jstl表达式展现
	    //ResidentInfo ri = homeService.getPatientBasicInfo("","","");
		Map<String, String> basicInfoMap = ri.getInfoMap();
		List<ResidentCard> cardList = ri.getCardList();
		model.addAllAttributes(basicInfoMap);
		model.addAttribute("cardList",cardList);
		return "empiDetail";
	}
	
	/**
	 * 首页信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("healthInfoPage")
	public String healthInfoPage(HttpServletRequest request, Model model, @RequestParam(value="empiId") String empiId){
        //获取首页5块信息
		Map<String, String> homeInfoMap = homeService.getHealthHomeInfo(empiId, "001,002,003,004,005");
		model.addAllAttributes(homeInfoMap);
		model.addAttribute("empiId",empiId);
		return "basic";
	}

    /**
     * 新健康档案首页信息
     * @param model
     * @return
     */
    @RequestMapping("homePage")
    public String getHomeInfo(Model model, @RequestParam("empiId") String empiId,
                              @RequestParam("birthday") String birthday, @RequestParam("role") String role){
        int patientAge = getPatientAge(birthday);

        //不同的居民与不同的角色对应不同的业务块
        String businessBlock = "";
        Map<String, String> homeInfoMap = new HashMap<String, String>();
        if(patientAge<=6){ //儿童居民
            if("R01".equals(role)){ //"R01"表示医疗医生角色,"R02"表示公卫医生角色
                businessBlock = "child1";
                homeInfoMap = homeService.getHealthHomeInfo(empiId, "004,013,014,015,016,017,019,020");
            }else if("R02".equals(role)){
                businessBlock = "child2";
                homeInfoMap = homeService.getHealthHomeInfo(empiId, "004,012,013,014,015,016,017,018,019,020");
            }
        }else if(patientAge>=60){ //老年居民
            if("R01".equals(role)){
                businessBlock = "older1";
                homeInfoMap = homeService.getHealthHomeInfo
                        (empiId, "004,005,006,007,008,009,010,011,013,014,015,016,017");
            }else if("R02".equals(role)){
                businessBlock = "older2";
                homeInfoMap = homeService.getHealthHomeInfo
                        (empiId, "004,005,006,007,008,009,010,011,012,013,014,015,016,017,018");
            }
        }else{ //其他居民
            if("R01".equals(role)){
                businessBlock = "other1";
                homeInfoMap = homeService.getHealthHomeInfo
                        (empiId, "004,005,006,007,008,011,013,014,015,016,017");
            }else if("R02".equals(role)){
                businessBlock = "other2";
                homeInfoMap = homeService.getHealthHomeInfo
                        (empiId, "004,005,006,007,008,011,012,013,014,015,016,017,018");
            }
        }
        //近期用药信息的列表大小不定,用此来存储所有用药信息,前端遍历显示
//        List<MedicationInfo> medicationLic = new ArrayList<MedicationInfo>();
//        List<String> medicationKeyLic = new ArrayList<String>();
//        for(Map.Entry<String, String> entry : homeInfoMap.entrySet()){
//            String strKey = entry.getKey();
//            if((strKey.indexOf("YYFL"))>-1 ){ //"YYFL"代表用药分类
//                medicationKeyLic.add(strKey);
//            }
//        }
//        for(int i=0; i<medicationKeyLic.size(); i++){
//            MedicationInfo medicationInfo = new MedicationInfo();
//            medicationInfo.setCategory(homeInfoMap.get("YYFL"+(i+1)));
//            medicationInfo.setDate(homeInfoMap.get("YYRQ"+(i+1)));
//            medicationInfo.setDrugName(homeInfoMap.get("YWMC"+(i+1)));
//            medicationInfo.setDrugUsage(homeInfoMap.get("YYYF"+(i+1)));
//            medicationInfo.setDrugOpPath(homeInfoMap.get("YYTJ"+(i+1)));
//            medicationLic.add(medicationInfo);
//        }
        model.addAllAttributes(homeInfoMap);
        model.addAttribute("empiId",empiId);
        model.addAttribute("businessBlock",businessBlock);
//        model.addAttribute("medicationLic",medicationLic);
        return "basic";
    }
	
	/**
	 * 个人基本信息
	 * @param request
	 * @return
	 */
	@RequestMapping("basicInfo")
	@ResponseBody
	public Map<String,String> patientBasicInfo(HttpServletRequest request){
		Map<String,String> map = new HashMap();
	    map = homeService.getPatientBasicInfo("","","","").getInfoMap();
		return map;
	}

    /**
     * 健康档案浏览登录
     * @param request
     * @return
     */
    @RequestMapping("login")
    public String login(HttpServletRequest request, Model model, @RequestParam("orgCode") String orgCode,
                              @RequestParam("doctorCode") String doctorCode, @RequestParam("cardType") String cardType,
                              @RequestParam("cardNo") String cardNo, @RequestParam("patientName") String patientName,
                              @RequestParam("role") String role) {
        String errorMsg = "";
        ResidentInfo residentInfo = homeService.getPatientBasicInfo(patientName, cardNo, cardType,orgCode);
        if (residentInfo == null || residentInfo.getCardList().size() == 0) {
            errorMsg = "该患者未注册或姓名未匹配";
        } else {
            residentInfo.setSearchInfo(orgCode+","+doctorCode);
            residentInfo.setPatientInfo(cardType+","+cardNo+","+patientName);
            request.getSession().setAttribute("basicInfoMap", residentInfo);
            model.addAttribute("role", role);   //角色信息
        }
        if(errorMsg.length() > 0){
            model.addAttribute("errorMsg", errorMsg);
            return "loginError";
        }else{
            return home(request,model);
        }
    }

    public BasicInfoService getBasicInfoService() {
		return basicInfoService;
	}

	public void setBasicInfoService(BasicInfoService basicInfoService) {
		this.basicInfoService = basicInfoService;
	}

	public HomeService getHomeService() {
		return homeService;
	}

	public void setHomeService(HomeService homeService) {
		this.homeService = homeService;
	}

    /**
     * 根据出生日期获取年龄
     * @param birthday
     * @return
     */
    public int getPatientAge(String birthday) {
        if (birthday != null && !"".equals(birthday)) {
            DateFormat dateFormat = new SimpleDateFormat("yyyymmdd");
            Date birthDay = null;
            try {
                birthDay = dateFormat.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal = Calendar.getInstance();

            if (cal.before(birthDay)) {
                throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
            }

            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH) + 1;
            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

            cal.setTime(birthDay);
            int yearBirth = cal.get(Calendar.YEAR);
            int monthBirth = cal.get(Calendar.MONTH);
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

            int age = yearNow - yearBirth;

            if (monthNow <= monthBirth) {
                if (monthNow == monthBirth) {
                    if (dayOfMonthNow < dayOfMonthBirth) {
                        age--;
                    }
                } else {
                    age--;
                }
            }
            return age;
        } else {
            return 0;
        }
    }

    /**
     * 根据卡类型代码获取卡类型描述
     * @param cardTypeCode
     * @return
     */
    private String getCardTypeDesc(String cardTypeCode) {
        String typeDesc = "身份证";
        if ("2".equals(cardTypeCode)) {
            typeDesc = "军官证";
        } else if ("3".equals(cardTypeCode)) {
            typeDesc = "护照";
        } else if ("4".equals(cardTypeCode)) {
            typeDesc = "社保卡";
        } else if ("5".equals(cardTypeCode)) {
            typeDesc = "医保卡";
        } else if ("6".equals(cardTypeCode)) {
            typeDesc = "市民卡";
        } else if ("7".equals(cardTypeCode)) {
            typeDesc = "健康卡";
        } else if ("8".equals(cardTypeCode)) {
            typeDesc = "农合号";
        } else if ("9".equals(cardTypeCode)) {
            typeDesc = "院内主索引";
        } else if ("10".equals(cardTypeCode)) {
            typeDesc = "院内就诊卡";
        }
        return typeDesc;
    }

}
