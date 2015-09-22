package com.zebone.dip.compare.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.app.mho.vo.Mho;
import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.security.UserDetails;
import com.zebone.dip.compare.service.DictCompareService;
import com.zebone.dip.compare.vo.DictInfo;
import com.zebone.dip.compare.vo.DictTypeOrg;
import com.zebone.util.JsonUtil;

/**
 * 数据字典对照控制层
 * @author: caixl
 * @date： 日期：Dec 30, 2013
 * @version 1.0
 */
@Controller
public class DictCompareController {
	private static final Log log = LogFactory.getLog(DictCompareController.class);
	
	@Resource
	private DictCompareService dictCompareService;
	
	@RequestMapping("compare/dictImpIndex")
	public String DictImpIndex(){
		return "dip/compare/dictionary/dict_imp_index";
	}
	
	@RequestMapping("compare/dictManaIndex")
	public String dictManaIndex(ModelMap map){
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		List<Mho> mhos = userDetails.getMhoList();
		String levelCode = "";
		if(mhos!=null && mhos.size()>0){
			levelCode = mhos.get(0).getLevelCode();
		}
		List<DictTypeOrg> list = dictCompareService.getListByOrg(levelCode);
		String types = JsonUtil.writeValueAsString(list);
		map.put("list", types);
		return "dip/compare/dictionary/dict_mana_index";
	}
	
	@RequestMapping("compare/getDictTypeByName")
	@ResponseBody
	public Map<String,Object> getDictTypeByName(@RequestParam("typeName")String typeName){
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		List<Mho> mhos = userDetails.getMhoList();
		String levelCode = "";
		if(mhos!=null && mhos.size()>0){
			levelCode = mhos.get(0).getLevelCode();
		}
		List<DictTypeOrg> list = dictCompareService.getDictTypeByName(levelCode,typeName);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("data", list);
		return map;
	}
	
	@RequestMapping("compare/getDictComparePage")
	@ResponseBody
	public JsonGrid getDictComparePage(Pagination<DictInfo> page,DictInfo dictInfo){
        //获取用户信息
        UserDetails userDetails = (UserDetails) SecurityUtils.getSubject().getPrincipal();
        List<Mho> mhos = userDetails.getMhoList();
        String levelCode = "";
        if (mhos != null && mhos.size() > 0) {
            levelCode = mhos.get(0).getLevelCode();
        }
        dictInfo.setOrgCode(levelCode);
        dictInfo.setUserCode(userDetails.getPersonnelId());
		Pagination<DictInfo> p = dictCompareService.getDictPage(page,dictInfo);
		JsonGrid jGrid = new JsonGrid("true",p.getTotalCount(),p.getData());
		return jGrid;
	}
	
	@RequestMapping("compare/getDictByTypeId")
	@ResponseBody
	public Map<String, Object> getDictByTypeId(@RequestParam("orgDictTypeId")String orgDictTypeId,@RequestParam("query") String orgDict){
		DictInfo dictInfo = new DictInfo();
		dictInfo.setOrgDict(orgDict);
		dictInfo.setOrgDictTypeId(orgDictTypeId);
		List<DictInfo> list = dictCompareService.getDictListByName(dictInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		map.put("success", true);
		int total =0;
		if(list != null && list.size() > 0){
			total = list.size();
		}
		map.put("query", orgDict);
		map.put("total", total);
		return map;
	}
	
	@RequestMapping("compare/getDictInfoByOrgDictId")
	@ResponseBody
	public Map<String, Object> getDictInfoByOrgDictId(@RequestParam("orgDictId")String orgDictId){
		Map<String, Object> map = new HashMap<String, Object>();
		DictInfo dictInfo = dictCompareService.getDictInfoByOrgDictId(orgDictId);
		map.put("data", dictInfo);
		return map;
	}
	@RequestMapping("compare/getDictByOrgTypeId")
	@ResponseBody
	public Map<String, Object> getDictByOrgTypeId(@RequestParam("orgDictTypeId")String orgDictTypeId,@RequestParam("query") String dict){
		DictInfo dictInfo = new DictInfo();
		dictInfo.setDict(dict);
		dictInfo.setDictTypeId(orgDictTypeId);
		List<DictInfo> list = dictCompareService.getDictListByNameAndTypeId(dictInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		map.put("success", true);
		int total =0;
		if(list != null && list.size() > 0){
			total = list.size();
		}
		map.put("query", dict);
		map.put("total", total);
		return map;
	}
	@RequestMapping("compare/getDictTypeInfoByName")
	@ResponseBody
	public Map<String, Object> getDictTypeInfoByName(@RequestParam("query") String name){
		List<DictTypeOrg> list = dictCompareService.getListByName(name);
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
	@RequestMapping("compare/saveCompareInfo")
	@ResponseBody
	public Map<String, Object> saveCompareInfo(DictInfo dictInfo){
		UserDetails userDetails = (UserDetails)SecurityUtils.getSubject().getPrincipal();
		List<Mho> mhos = userDetails.getMhoList();
		if(mhos!=null && mhos.size()>0){
			dictInfo.setOrgCode(mhos.get(0).getLevelCode());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		int result = 0;
		dictInfo.setUserCode(userDetails.getPersonnelId());
		try{
			result = dictCompareService.saveCompareInfo(dictInfo);
			
			if(result == 1){
				map.put("success", true);
			}else if(result == 2){
				map.put("success", false);
				map.put("msg", "该字典编码已经存在，不可对照");
			}else if(result == 3){
				map.put("success", true);
				map.put("typeId", dictInfo.getOrgDictTypeId());
			}
		}catch (Exception e) {
			map.put("success", false);
			log.error("", e);
		}
		
		return map;
	}
	
	@RequestMapping("compare/removeCompareInfo")
	@ResponseBody
	public Map<String, Object> removeCompareInfo(@RequestParam("id")String id){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			dictCompareService.removeCompare(id);
			map.put("success", true);
		}catch (Exception e) {
			log.error("", e);
			map.put("success", false);
		}
		return map;
	}
}
