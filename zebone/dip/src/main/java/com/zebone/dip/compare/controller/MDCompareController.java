package com.zebone.dip.compare.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.app.mho.vo.Mho;
import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.security.UserDetails;
import com.zebone.dip.compare.service.MDCompareService;
import com.zebone.dip.compare.vo.MDstdInfo;
import com.zebone.dip.compare.vo.MainInfo;
import com.zebone.util.DateUtil;
import com.zebone.util.UUIDUtil;

/**
 * 类描述：
 * @author: caixl
 * @date： 日期：Jan 9, 2014
 * @version 1.0
 */
@Controller
public class MDCompareController {
	
	private static final Log log = LogFactory.getLog(MDCompareController.class);
	
	@Resource
	private MDCompareService mdCompareService;
	
	@RequestMapping("compare/getMainInfoListById")
	@ResponseBody
	public Map<String, Object> getMainInfoListById(@RequestParam("tableName")String tableName,@RequestParam("query") String name){
		MainInfo mainInfo = new MainInfo();
		mainInfo.setName(name);
		mainInfo.setTableName(tableName);
		List<MainInfo> list = mdCompareService.getOrgMainInfoByName(mainInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		map.put("success", true);
		int total =0;
		if(list != null && list.size() > 0){
			total = list.size();
		}
		map.put("query", name);
		map.put("total", total);
		return map;
	}
	
	@RequestMapping("compare/maindataPage")
	@ResponseBody
	public JsonGrid maindataPage(Pagination<MainInfo> page,MainInfo mainInfo){
		if(!StringUtils.isBlank(mainInfo.getTableName())){
			mainInfo.setStdTableName(mainInfo.getTableName().substring(0, mainInfo.getTableName().length()-2));
		}
        //获取用户信息
        UserDetails userDetails = (UserDetails) SecurityUtils.getSubject().getPrincipal();
        List<Mho> mhos = userDetails.getMhoList();
        String levelCode = "";
        if (mhos != null && mhos.size() > 0) {
            levelCode = mhos.get(0).getLevelCode();
        }
        mainInfo.setOrgCode(levelCode);
        mainInfo.setUserCode(userDetails.getPersonnelId());
        Pagination<MainInfo> p = mdCompareService.getListByInfo(page,mainInfo);
		JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
		return jGrid;
	}
	
	@RequestMapping("compare/mainInfoEdit")
	public String mainInfoEdit(ModelMap modelMap,MainInfo mainInfo){
		MainInfo info = new MainInfo();
		if(!StringUtils.isBlank(mainInfo.getId())){
			info = mdCompareService.getInfoById(mainInfo);
		}
		info.setTableName(mainInfo.getTableName());
		modelMap.put("info", info);
		ClassPathResource resource = new ClassPathResource("tablefield.properties");
		Properties p = new Properties();
		try {
			p.load(resource.getInputStream());
			String key = mainInfo.getTableName().substring(0, mainInfo.getTableName().length()-2);
			String str = (String)p.get(key);
			modelMap.put("cols", str);
		} catch (IOException e) {
			log.error("", e);
		}
		return "dip/compare/maindata/mainInfoEdit";
	}
	
	@RequestMapping("compare/maindatastdPage")
	@ResponseBody
	public JsonGrid maindatastdPage(Pagination<MDstdInfo> page,MainInfo mainInfo){
		if(!StringUtils.isBlank(mainInfo.getTableName())){
			mainInfo.setStdTableName(mainInfo.getTableName().substring(0, mainInfo.getTableName().length()-2));
		}
		Pagination<MDstdInfo> p = mdCompareService.getStdListByInfo(page,mainInfo);
		JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
		return jGrid;
	}
	
	@RequestMapping("compare/getMdstdByName")
	@ResponseBody
	public Map<String, Object> getMdstdByName(@RequestParam("tableName")String tableName,@RequestParam("query") String stdName){
		MainInfo mainInfo = new MainInfo();
		mainInfo.setStdName(stdName);
		mainInfo.setStdTableName(tableName.substring(0, tableName.length()-2));
		List<MainInfo> list = mdCompareService.getMainListByName(mainInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		map.put("success", true);
		int total =0;
		if(list != null && list.size() > 0){
			total = list.size();
		}
		map.put("query", stdName);
		map.put("total", total);
		return map;
	}
	
	@RequestMapping("compare/saveMdCompareInfo")
	@ResponseBody
	public Map<String, Object> saveMdCompareInfo(MainInfo mainInfo){
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		List<Mho> mhos = userDetails.getMhoList();
		String levelCode = "";
		if(mhos!=null && mhos.size()>0){
			levelCode = mhos.get(0).getLevelCode();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			if(StringUtils.isBlank(mainInfo.getId())){
				mainInfo.setId(UUIDUtil.getUuid());
				mainInfo.setOrgCode(levelCode);
				mainInfo.setUserCode(userDetails.getPersonnelId());
				mainInfo.setCreateTime(DateUtil.getCurrentDate("yyyyMMddHHmmss"));
				int result = mdCompareService.saveMdCompare(mainInfo);
				if(result == 2){
					map.put("success", false);
					map.put("msg", "该机构主数据已经存在，不能再做新增操作");
				}else{
					map.put("success", true);
				}
			}else{
				mdCompareService.updateMdCompare(mainInfo);
				map.put("success", true);
			}
		}catch (Exception e) {
			map.put("success", false);
			log.error("", e);
		}
		return map;
	}
	
	@RequestMapping("compare/removeMdCompare")
	@ResponseBody
	public Map<String, Object> removeMdCompare(MainInfo mainInfo){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			mdCompareService.removeMdCompare(mainInfo);
			map.put("success", true);
		}catch (Exception e) {
			map.put("success", false);
		}
		return map;
	}
}
