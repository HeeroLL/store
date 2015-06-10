/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * wangpeng             New             2012-11-27     字典管理controller
 */
package com.zebone.btp.app.dict.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.app.dict.pojo.Dictionary;
import com.zebone.btp.app.dict.pojo.DictionaryType;
import com.zebone.btp.app.dict.service.DictionaryService;
import com.zebone.btp.app.dict.service.DictionaryTypeService;
import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.core.web.BaseController;
import com.zebone.util.JsonUtil;

/**
 * 字典管理controller
 * @date 2012-11-27
 * @author wangpeng
 *
 */
@Controller
@Scope(value="prototype")
public class DictionaryController extends BaseController {
	private static final Log log = LogFactory.getLog(DictionaryController.class);
	@Resource
	private DictionaryService dictionaryService;
	
	@Resource
	private DictionaryTypeService dictionaryTypeService;
	
	/**
	 * 字典管理入口页面
	 * @date 2012-11-27
	 * @return
	 */
	@RequestMapping("btp/dict/index")
	public 	String dictionaryIndex(){
		return "btp/dict/dictionary_index";
	}
	
	/**
	 * 字典类型树跳转页面
	 * @date 2012-11-27
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("btp/dict/dictTypeTree")
	public String dictionaryTypeTree(HttpServletRequest request,HttpServletResponse response){
		List<DictionaryType> typeList = dictionaryTypeService.getAllDictionaryType();
		request.setAttribute("typeList", JsonUtil.writeValueAsString(typeList));
		return "btp/dict/dictionaryType_tree";
	}
	

	//********************************************  字典 start  ******************************************/
	/**
	 * 字典主界面
	 * @date 2012-11-28
	 * @return
	 */
	@RequestMapping("btp/dict/dict")
	public String dictionaryMain(HttpServletRequest request,HttpServletResponse response){
		String dicttypeId = request.getParameter("dicttypeId");
		request.setAttribute("dicttypeId", dicttypeId);
		if(StringUtils.isNotEmpty(dicttypeId)){
			request.setAttribute("typeName", dictionaryTypeService.getDictionaryTypeById(dicttypeId).getTypeName());
		}	
		return "btp/dict/dictionary";
	}	
	
	/**
	 * 字典列表显示（不分页）
	 * @date 2012-11-28
	 * @param page
	 * @param dictionary
	 * @return
	 */
	@RequestMapping("btp/dict/dictPage")
	public @ResponseBody JsonGrid dictionaryPage(Pagination<Dictionary> page,Dictionary dictionary, HttpServletRequest request){
		String dicttypeId = request.getParameter("dicttypeId");
		dictionary.setDicttypeId(dicttypeId);
		page = dictionaryService.getDictionaryPage(page, dictionary);
		JsonGrid jGrid = new JsonGrid("success",page.getTotalCount(),page.getData());
		return jGrid;
	}
	
	/**
	 * 删除字典信息
	 * @date 2012-11-29
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("btp/dict/dictRemove")
	public @ResponseBody Map<String,Object> dictionaryRemove(HttpServletRequest request,HttpServletResponse response){
		String dictIds = request.getParameter("id");
		String[] dictId = dictIds.split(",");
		boolean bool = dictionaryService.deleteDictionary(dictId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", bool);
		return map;
	}
	
	/**
	 * 保存字典信息
	 * @date 2012-11-29
	 * @param dictionary
	 * @return
	 */
	@RequestMapping("btp/dict/dictSave")
	public @ResponseBody Map<String,Object> dictionarySave(Dictionary dictionary){
		boolean bool = false;
		if(StringUtils.isNotEmpty(dictionary.getDictId())){  //更新
			dictionaryService.updateDictionary(dictionary);
			bool = true;
		}else{
			dictionaryService.insertDictionary(dictionary);
			bool = true;
		}
		//boolean bool = dictionaryService.saveDictionary(dictionary);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", bool);
		return map;
	}
	
	/**
	 * 加载字典信息
	 * @date 2012-11-29
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("btp/dict/dictLoad")
	public @ResponseBody Dictionary dictionaryLoad(HttpServletRequest request,HttpServletResponse response){
		String dictId = request.getParameter("id");
		Dictionary dictionary = dictionaryService.getDictionaryById(dictId);
		return dictionary;
	}
	
	/**
	 * 字典排序
	 * @date 2012-11-30
	 * @param dictionary
	 * @return
	 */
	@RequestMapping("btp/dict/dictOrder")
	public @ResponseBody Map<String,Object> dictionaryOrder(Dictionary dictionary){
		boolean bool = false;		
		if(dictionary.getOrderNo() == null){
			dictionaryService.orderDictionaryByTypeId(dictionary.getDicttypeId());
			bool = true;
		}else{
			//isDeleted 临时作为向上或向下移动的标志，1：向上；2：向下
			bool = dictionaryService.dictionaryOrder(dictionary);
		}		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", bool);
		return map;
	}
	
	//********************************************  字典 end  ******************************************/
	
	
	//********************************************  字典类型 start  ******************************************/
	/**
	 * 字典类型主界面
	 * @date 2012-11-28
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("btp/dict/dictType")
	public String dictionaryTypeMain(HttpServletRequest request,HttpServletResponse response){
		String parentId = request.getParameter("parentId");
		request.setAttribute("parentId", parentId);
		if(StringUtils.isNotEmpty(parentId)){
			request.setAttribute("pTypeName", dictionaryTypeService.getDictionaryTypeById(parentId).getTypeName());
		}		
		List<DictionaryType> dType = dictionaryTypeService.getDictionaryTypeNoPid();
		request.setAttribute("dType", dType);
		return "btp/dict/dictionaryType";
	}
	
	/**
	 * 字典类型列表显示（分页）
	 * @date 2012-11-29
	 * @param page
	 * @param dictionaryType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("btp/dict/dictTypePage")
	public @ResponseBody JsonGrid dictionaryTypePage(Pagination<DictionaryType> page,DictionaryType dictionaryType, HttpServletRequest request){
		String parentId = request.getParameter("parentId");
		if(StringUtils.isNotEmpty(parentId)){
			dictionaryType.setParentId(parentId);
		}
		page = dictionaryTypeService.getDictionaryTypePage(page, dictionaryType);
		JsonGrid jGrid = new JsonGrid("success",page.getTotalCount(),page.getData());
		return jGrid;
	}
	
	/**
	 * 删除字典类型信息
	 * @date 2012-11-29
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("btp/dict/dictTypeRemove")
	public @ResponseBody Map<String,Object> dictionaryTypeRemove(HttpServletRequest request,HttpServletResponse response){
		String typeIds = request.getParameter("id");
		String[] typeId = typeIds.split(",");
		boolean bool = dictionaryTypeService.deleteDictionaryType(typeId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", bool);
		return map;
	}
	
	/**
	 * 保存字典类型信息
	 * @date 2012-11-29
	 * @param dictionaryType
	 * @return
	 */
	@RequestMapping("btp/dict/dictTypeSave")
	public @ResponseBody Map<String,Object> dictionaryTypeSave(DictionaryType dictionaryType){
		//boolean bool = dictionaryTypeService.saveDictionaryType(dictionaryType);
		boolean bool = false;
		if(StringUtils.isNotEmpty(dictionaryType.getTypeId())){ //更新
			dictionaryTypeService.updateDictionaryType(dictionaryType);		
			bool = true;
		}else{
			dictionaryTypeService.insertDictionaryType(dictionaryType);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", bool);
		return map;
	}
	
	/**
	 * 加载字典类型信息
	 * @date 2012-11-29
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("btp/dict/dictTypeLoad")
	public @ResponseBody DictionaryType  dictionaryTypeLoad(HttpServletRequest request,HttpServletResponse response){
		String typeId = request.getParameter("id");
		DictionaryType dictionaryType  = dictionaryTypeService.getDictionaryTypeById(typeId);
		return dictionaryType;
	}
	
	/**
	 * 
	 * 判断字典类型是否存在
	 * @date 2012-11-30
	 * @param request
	 * @param response void
	 */
	@RequestMapping("btp/dict/dictTypeValidate")
	public @ResponseBody Map<String,Object> validateDictionaryType(DictionaryType dictionaryType){
		boolean check = dictionaryTypeService.selectDictionaryType(dictionaryType);
		Map<String,Object> map = new HashMap<String,Object>();
		if(check){
			map.put("success", true);
		}else{
			map.put("success", false);
		}
		return map;
	}
	
	//********************************************  字典类型 end  ******************************************/
	
	
	//********************************************  控件 start  ******************************************/
	
	/**
	 * 根据字典类型代码返回字典信息(json格式)。 
	 * 注意：@RequestMapping中定义的访问路径没有安装规范中定义的规则(模块/子模块/操作),
	 * 因为此访问路径不需要进行授权控制。
	 * @param dictType
	 * @param selectId
	 * @return
	 * @author 宋俊杰
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("dict/getDictByType")
	@ResponseBody
	public Map getDictByType(@RequestParam("dictType") String dictType , @RequestParam("selectId")String selectId){
		List dictList = new ArrayList();
		List<Dictionary> list = this.dictionaryService.getDictionaryByTypeCode(dictType);
		for(Dictionary dict : list){
			Map dictMap = new HashMap();
			dictMap.put("id", dict.getDictId());
			dictMap.put("name", dict.getDictName());
			dictMap.put("code", dict.getDictCode());
			dictMap.put("jianpin", dict.getNameJianpin());
			dictMap.put("pinyin", dict.getNamePinyin());
			dictList.add(dictMap);
		}
		Map map = new HashMap();
		map.put("selectId", selectId);
		map.put("dictList",dictList);
		if(log.isDebugEnabled()){
			log.debug(JsonUtil.writeValueAsString(map));
		}
		
		return map;
	}
	
	/**
	 * 根据字典类型代码返回字典简要信息,只包含字典代码和字典名字。
	 * 返回的类型为 javascript类型。相当于动态生成一段js代码。前端的grid控件根据js代码中
	 * 的字典列表渲染单元格中字典。生成的js代码的格式为：
	 * dictType_{字典类型代码} = {字典代码：字典值,......};
	 * 注意：@RequestMapping中定义的访问路径没有安装规范中定义的规则(模块/子模块/操作),
	 * 因为此访问路径不需要进行授权控制。
	 * @param dictType 如果需要多个字典，字典类型代码用','号分隔
	 * @return
	 * @author 宋俊杰
	 */
	@RequestMapping("dict/getPlainDictByType")
	public void getPlainDictByType(HttpServletResponse response , @RequestParam("dictType") String dictTypeList){
		if(StringUtils.isEmpty(dictTypeList)){
			return;
		}
		StringBuilder sb = new StringBuilder();
		String[] dictTypeArray = dictTypeList.split(",");
		for(String dictType : dictTypeArray){
			List<Dictionary> list = this.dictionaryService.getDictionaryByTypeCode(dictType);
			//将字典类型代码中的'.'和'-' 都替换成'_'。 js的变量名字不能够含有这2个字符，其他字符暂时不处理.
//			声明字典类型代码的时候可能用不到其他字符
			dictType = dictType.replaceAll("[\\.|-]", "_");
			sb.append("var dictType_").append(dictType).append(" = ");
			if(list==null || list.size()==0){
				log.error("未找到字典类型编码为"+dictType+"的字典值");
				sb.append("{}");//没有找到字典值，返回给前台一个空对象
				continue;
			}
			Map<String,String> dictMap = new HashMap<String,String>();
			for(Dictionary dict : list){
				String code = dict.getDictCode();
				String name = dict.getDictName();
				dictMap.put(code,name);
			}
			String json = JsonUtil.writeValueAsString(dictMap);
			sb.append(json).append(";\n");
		}
		//加上这句，在IE下会报错。
		response.setContentType("text/javascript");
		response.setCharacterEncoding("utf8");
		log.debug(sb.toString());
		this.wirte(sb.toString(), response);
	}
	//********************************************  控件 end  ******************************************/
	
}
