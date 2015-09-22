/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * caixl              New             Mar 6, 2013		数据字典控制层
 */
package com.zebone.dip.dictionary.controller;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.core.util.ChineseToPinYin;
import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.core.web.BaseController;
import com.zebone.dip.dictionary.service.DictService;
import com.zebone.dip.dictionary.vo.PDictType;
import com.zebone.dip.dictionary.vo.PDictValue;
import com.zebone.dip.dictionary.vo.PDictionary;
import com.zebone.dip.dictionary.vo.PDictionaryType;
import com.zebone.dip.ds.service.DsObjService;
import com.zebone.dip.ds.vo.DsObj;
import com.zebone.util.JsonUtil;
import com.zebone.util.UUIDUtil;
@Controller
@Scope(value="prototype")
public class DictController extends BaseController {
	@Resource
	private DictService dictService;
	@Resource
	private DsObjService dsObjService;
	
	/*****************************************标准数据字典start**************************************************/
	/**
	 * @author caixl
	 * @date Mar 6, 2013
	 * @description TODO 标准数据字典的注册查询页面
	 * @return String
	 */
	@RequestMapping("dict/dictStandardIndex")
	public String dictStandardIndex(){
		return "dip/dict/dict_standard_index";
	}
	
	/**
	 * @author caixl
	 * @date Mar 6, 2013
	 * @description TODO 标准数据字典类型树
	 * @return String
	 */
	@RequestMapping("dict/dictStandardTree")
	public String dictStandardTree(HttpServletRequest request){
		List<PDictionaryType> list = dictService.getPDictStandardInfo();
		PDictionaryType pd = new PDictionaryType();
		pd.setTypeId("1");
		pd.setTypeName("标准字典类型");
		list.add(pd);
		String str = JsonUtil.writeValueAsString(list);
		request.setAttribute("list", str);
		return "dip/dict/dict_standard_tree";
	}
	
	@RequestMapping("dict/dictTypeMain")
	public String dictTypeMain(){
		return "dip/dict/dict_type_main";
	}
	
	@RequestMapping("dict/dictTypeStandardList")
	public @ResponseBody JsonGrid dictTypeStandardList(PDictionaryType pDictionary,Pagination<PDictionaryType> page){
		page = dictService.getDictTypeStandardList(pDictionary,page);
		JsonGrid jGrid = new JsonGrid("success",page.getTotalCount(),page.getData());
		return jGrid;
	}
	/**
	 * @author Administrator
	 * @date Mar 7, 2013
	 * @description TODO
	 * @param request
	 * @return PDictionaryType
	 */
	@RequestMapping("dict/loadDictTypeStandardInfo")
	public @ResponseBody PDictionaryType loadDictTypeStandardInfo(HttpServletRequest request){
		String id = request.getParameter("id");
		PDictionaryType pdt = dictService.getDictTypeStandardById(id);
		return pdt;
	}
	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 保存数据字典类型相关信息
	 * @return Map<String,Object>
	 */
	@RequestMapping("dict/saveDictTypeStandardInfo")
	public @ResponseBody Map<String, Object> saveDictTypeStandardInfo(PDictionaryType pdt){
		int result = 0;
		String msg = "";
		boolean bool = false;
		if(StringUtils.isEmpty(pdt.getTypeId())){
			pdt.setIsDeleted("0");
			pdt.setParentId("1");
			pdt.setTypeId(UUIDUtil.getUuid());
			result = dictService.saveDictTypeStardInfo(pdt);
			if(result>0){
				msg = "保存成功";
				bool = true;
			}else{
				msg = "保存失败";
			}
		}else{
			result = dictService.updateDictTypeStardInfo(pdt);
			if(result>0){
				msg = "更新成功";
				bool = true;
			}else{
				msg = "更新失败";
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", bool);
		map.put("msg", msg);
		map.put("id", pdt.getTypeId());
		return map;
	}
	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 删除标准数据字典类型的信息
	 * @param request
	 * @return Map<String,Object>
	 */
	@RequestMapping("dict/removeDictTypeStandardByIds")
	public @ResponseBody Map<String, Object> removeDictTypeStandardByIds(HttpServletRequest request){
		String id = request.getParameter("id");
		int result = dictService.removeDictTypeStandardByIds(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if(result == 1){
			map.put("success", true);
		}else if(result == 2){
			map.put("success", false);
			map.put("msg", "该数据字典类型有数据字典数据，不能删除");
		}else{
			map.put("success", false);
		}
		return map;
	}
	
	@RequestMapping("dict/dictTypeStandardValidate")
	public @ResponseBody Map<String,Object> dictTypeStandardValidate(PDictionaryType pdt){
		boolean check = dictService.selectDictTypeStandard(pdt);
		Map<String,Object> map = new HashMap<String,Object>();
		if(check){
			map.put("success", true);
		}else{
			map.put("success", false);
		}
		return map;
	}
	/**
	 * @author caixl
	 * @date Mar 7, 2013
	 * @description TODO 标准数据字典页面
	 * @param request
	 * @return String
	 */
	@RequestMapping("dict/dictMain")
	public String dictMain(HttpServletRequest request){
		String typeId = request.getParameter("typeId");
		String typeName = request.getParameter("typeName");
		request.setAttribute("typeId", typeId);
		request.setAttribute("typeName", typeName);
		return "dip/dict/dict_main";
	}
	
	@RequestMapping("dict/dictStandardList")
	public @ResponseBody JsonGrid dictStandardList(PDictionary pDictionary,Pagination<PDictionary> page){
		page = dictService.getDictStandardList(pDictionary,page);
		JsonGrid jGrid = new JsonGrid("success",page.getTotalCount(),page.getData());
		return jGrid;
	}
	
	@RequestMapping("dict/saveDictStandardInfo")
	public @ResponseBody Map<String, Object> saveDictStandardInfo(PDictionary pDictionary){
		int result = 0;
		String msg = "";
		boolean bool = false;
		String[] py = ChineseToPinYin.chineseToPinyinAndShort(pDictionary.getDictName()).split(",");
		if(py.length>1){
			pDictionary.setNameJianpin(py[1]);
		}else{
			pDictionary.setNameJianpin(py[0]);
		}
		pDictionary.setNamePinyin(py[0]);
		
		if(StringUtils.isEmpty(pDictionary.getDictId())){
			pDictionary.setIsDeleted("0");
			pDictionary.setDictId(UUIDUtil.getUuid());
			result = dictService.saveDictStardInfo(pDictionary);
			if(result>0){
				msg = "保存成功";
				bool = true;
			}else{
				msg = "保存失败";
			}
		}else{
			result = dictService.updateDictStardInfo(pDictionary);
			if(result>0){
				msg = "更新成功";
				bool = true;
			}else{
				msg = "更新失败";
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", bool);
		map.put("msg", msg);
		map.put("dictId", pDictionary.getDictId());
		return map;
	}
	
	@RequestMapping("dict/dictStandardOrder")
	public @ResponseBody Map<String, Object> dictStandardOrder(PDictionary pDictionary){
		boolean bool = false;
		if(pDictionary.getOrderNo() == null){
			dictService.orderDictionaryByTypeId(pDictionary.getDicttypeId());
			bool = true;
		}else{
			//isDeleted 临时作为向上或向下移动的标志，1：向上；2：向下
			bool = dictService.dictionaryOrder(pDictionary);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", bool);
		return map;
	}
	
	@RequestMapping("dict/dictStandardLoad")
	public @ResponseBody PDictionary dictStandardLoad(HttpServletRequest request){
		String id = request.getParameter("id");
		PDictionary pd = dictService.getDictStandardById(id);
		return pd;
	}
	
	@RequestMapping("dict/removeDictStandardByIds")
	public @ResponseBody Map<String, Object> removeDictStandardByIds(HttpServletRequest request){
		int result = 0;
		String id = request.getParameter("id");
		result = dictService.removeDictStandardByIds(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", result > 0 ? true : false);
		return map;
	}
	/*****************************************标准数据字典end**************************************************/
	/*****************************************各数据源数据字典start**************************************************/
	@RequestMapping("dict/dictDsIndex")
	public String dictDsIndex(){
		return "dip/dict/dict_ds_index";
	}
	@RequestMapping("dict/dictDsTree")
	public String dictDsTree(HttpServletRequest request){
		List<PDictType> list = dictService.getDictTypeDsInfo();
		String str = JsonUtil.writeValueAsString(list);
		String lylx = request.getParameter("lylx");
		request.setAttribute("list", str);
		request.setAttribute("lylx", lylx);
		return "dip/dict/dict_ds_tree";
	}
	
	@RequestMapping("dict/dictTypeDsMain")
	public String dictTypeDsMain(HttpServletRequest request){
		String dsId = request.getParameter("dsId");
		String dsName = request.getParameter("dsName");
		request.setAttribute("dsName", dsName);
		request.setAttribute("dsId", dsId);
		List<DsObj> list = dsObjService.findDsObjAll();
		request.setAttribute("list", list);
		return "dip/dict/dict_type_ds_main";
	}
	
	@RequestMapping("dict/dictTypeDsList")
	public @ResponseBody JsonGrid dictTypeDsList(PDictType pDictType,Pagination<PDictType> page){
		page = dictService.getDictTypeDsList(pDictType,page);
		JsonGrid jGrid = new JsonGrid("success",page.getTotalCount(),page.getData());
		return jGrid;
	}
	
	@RequestMapping("dict/saveDictTypeDsInfo")
	public @ResponseBody Map<String, Object> saveDictTypeDsInfo(PDictType pDictType,HttpServletRequest request){
		int result = 0;
		String msg = "";
		boolean bool = false;
		String dsName = request.getParameter("dsName");
		pDictType.setDictTypeName(dsName+":"+pDictType.getTypeName());
		if(StringUtils.isEmpty(pDictType.getTypeId())){
			pDictType.setTypeId(UUIDUtil.getUuid());
			pDictType.setIsDeleted("0");
			result = dictService.saveDictTypeDsInfo(pDictType);
			if(result>0){
				msg = "保存成功";
				bool = true;
			}else{
				msg = "保存失败";
			}
		}else{
			result = dictService.updateDictTypeDsInfo(pDictType);
			if(result>0){
				msg = "更新成功";
				bool = true;
			}else{
				msg = "更新失败";
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", bool);
		map.put("msg", msg);
		map.put("typeId", pDictType.getTypeId());
		return map;
	}
	
	@RequestMapping("dict/loadDictTypeDsInfo")
	public @ResponseBody PDictType loadDictTypeDsInfo(HttpServletRequest request){
		String id = request.getParameter("id");
		PDictType pDictType = dictService.getDictTypeDsById(id);
		return pDictType;
	}
	
	@RequestMapping("dict/removeDictTypeDsByIds")
	public @ResponseBody Map<String, Object> removeDictTypeDsByIds(HttpServletRequest request){
		String id = request.getParameter("id");
		int result = dictService.removeDictTypeDsByIds(id);
		Map<String, Object> map = new HashMap<String, Object>();
		if(result == 1){
			map.put("success", true);
		}else if(result == 2){
			map.put("success", false);
			map.put("msg", "该数据字典类型有数据字典数据，不能删除");
		}else{
			map.put("success", false);
		}
		return map;
	}
	
	@RequestMapping("dict/dictDsMain")
	public String dictDsMain(HttpServletRequest request){
		String typeId = request.getParameter("typeId");
		String typeName = request.getParameter("typeName");
		request.setAttribute("typeName", typeName);
		request.setAttribute("typeId", typeId);
		return "dip/dict/dict_ds_main";
	}
	/**
	 * @author caixl
	 * @date Mar 9, 2013
	 * @description TODO 各数据源字典列表查询
	 * @param pDictValue
	 * @param page
	 * @return JsonGrid
	 */
	@RequestMapping("dict/dictDsList")
	public @ResponseBody JsonGrid dictDsList(PDictValue pDictValue,Pagination<PDictValue> page){
		page = dictService.getDictDsList(pDictValue,page);
		JsonGrid jGrid = new JsonGrid("success",page.getTotalCount(),page.getData());
		return jGrid;
	}
	
	@RequestMapping("dict/dictDsLoad")
	public @ResponseBody PDictValue dictDsLoad(HttpServletRequest request){
		String id = request.getParameter("id");
		PDictValue pDictValue = dictService.getDictDsById(id);
		return pDictValue;
	}
	
	@RequestMapping("dict/saveDictDsInfo")
	public @ResponseBody Map<String, Object> saveDictDsInfo(PDictValue pDictValue){
		int result = 0;
		String msg = "";
		boolean bool = false;
		if(StringUtils.isEmpty(pDictValue.getValueId())){
			pDictValue.setValueId(UUIDUtil.getUuid());
			pDictValue.setIsDeleted("0");
			result = dictService.saveDictDsInfo(pDictValue);
			if(result>0){
				msg = "保存成功";
				bool = true;
			}else{
				msg = "保存失败";
			}
		}else{
			result = dictService.updateDictDsInfo(pDictValue);
			if(result>0){
				msg = "更新成功";
				bool = true;
			}else{
				msg = "更新失败";
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", bool);
		map.put("msg", msg);
		map.put("valueId", pDictValue.getValueId());
		return map;
	}
	
	@RequestMapping("dict/removeDictDsByIds")
	public @ResponseBody Map<String, Object> removeDictDsByIds(HttpServletRequest request){
		int result = 0;
		String id = request.getParameter("id");
		result = dictService.removeDictDsByIds(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", result > 0 ? true : false);
		return map;
	}
	/*****************************************各数据源数据字典end**************************************************/
	/*****************************************数据字典匹配start**************************************************/
	@RequestMapping("dict/dictMatchIndex")
	public String dictMatchIndex(){
		return "dip/dict/dict_match_index";
	}
	
	@RequestMapping("dict/dictMatchMain")
	public String dictMatchMain(HttpServletRequest request){
		String typeId = request.getParameter("typeId");
		String typeName = request.getParameter("typeName");
		System.out.println(typeId);
		String str = "";
		if(StringUtils.isEmpty(typeId)){
			str = "1";
		}
		List<PDictValue> list = dictService.getDictDsByDictTypeId(typeId);
		List<PDictionary> list1 = null;
		String dicttypeId = "";
		if(list != null && list.size()>0){
			PDictValue pdv = list.get(0);
			if(!StringUtils.isEmpty(pdv.getDictId())){
				list1 = dictService.getDictStandardByIds(pdv.getDictId());
				dicttypeId = list1.get(0).getDicttypeId();
			}
		}
		List<PDictionaryType> pdtList = dictService.getPDictStandardInfo();
		request.setAttribute("str", str);
		request.setAttribute("typeId", typeId);
		request.setAttribute("typeName", typeName);
		request.setAttribute("list", list);
		request.setAttribute("pdtList", pdtList);
		request.setAttribute("list1", list1);
		request.setAttribute("dicttypeId", dicttypeId);
		return "dip/dict/dict_match_main";
	}
	
	@RequestMapping("dict/getDictStandardById")
	public @ResponseBody Map<String, Object> getDictStandardById(HttpServletRequest request){
		String dictId = request.getParameter("dictId");
		List<PDictionary> list = dictService.getDictStandardByDictId(dictId);
		Map<String, Object> map = new HashMap<String, Object>();
		if(list!=null && list.size()>0){
			map.put("success", true);
			map.put("data", list);
		}else{
			map.put("success", false);
		}
		return map;
	}
	
	@RequestMapping("dict/saveDictMatchInfo")
	public @ResponseBody Map<String, Object> saveDictMatchInfo(HttpServletRequest request){
		String dictData = request.getParameter("dictData");
		int result = dictService.saveDictMatchInfo(dictData);
		Map<String, Object> map = new HashMap<String, Object>();
		if(result>0){
			map.put("success", true);
		}else{
			map.put("success", false);
		}
		return map;
	}
	/*****************************************数据字典匹配end**************************************************/
	
	/***************************************导入数据字典start*************************************************/
	@Test
	public void exportDictInfo(){
		Workbook wwb = null;
	    try {
	    	//wwb = Workbook.getWorkbook(new File("e:\\导入数据\\WS364数据元CV代码表.xls"));
	    	//wwb = Workbook.getWorkbook(new File("e:\\导入数据\\WS363枚举型数据元的枚举值.xls"));
	    	wwb = Workbook.getWorkbook(new File("e:\\导入数据\\国际及国内标准代码表的编码值.xls"));
	    	Sheet sheet = wwb.getSheet(0);
	    	int n = sheet.getRows();
	    	
	    	List<PDictionaryType> pdtList = new ArrayList<PDictionaryType>();
	    	List<PDictionary> pdList = new ArrayList<PDictionary>(n);
	    	int a = 0,k=1;
	    	String id = "";
	    	for(int i=0; i<n; i++){
	    		Cell[] cell = sheet.getRow(i);
	    		String dictType = cell[0].getContents();
	    		
	    		if(!StringUtils.isEmpty(dictType)){
	    			id = UUIDUtil.getUuid();
	    			PDictionaryType pdt= new PDictionaryType();
		    		pdt.setIsDeleted("0");
		    		pdt.setParentId("1");
		    		pdt.setTypeId(id);
		    		pdt.setTypeCode(dictType.split("\n")[0]);
		    		pdt.setTypeName(dictType.split("\n")[1]);
		    		pdt.setRemark(dictType.split("\n")[1]);
		    		pdtList.add(pdt);
		    		k=1;
	    			a++;
	    		}
	    		PDictionary pd = new PDictionary();
	    		pd.setDictCode(cell[1].getContents());
	    		pd.setDictId(UUIDUtil.getUuid());
	    		System.out.println(cell[1].getContents()+"\t"+cell[2].getContents());
	    		pd.setDictName(cell[2].getContents());
	    		pd.setDicttypeId(id);
	    		pd.setIsDeleted("0");
	    		String[] str = ChineseToPinYin.chineseToPinyinAndShort(cell[2].getContents()).split(",");
	    		if(str.length>1){
	    			pd.setNameJianpin(str[1]);
	    		}else{
	    			pd.setNameJianpin(str[0]);
	    		}
	    		pd.setNamePinyin(str[0]);
	    		pd.setOrderNo((short)k);
	    		pd.setRemark(cell[3].getContents());
	    		pdList.add(pd);
	    		k++;
	    	}
	    	wwb.close();
	    	importDictInfo(pdtList,pdList);
	    	System.out.println(a);
	    } catch (Exception e) {
	    	System.out.println("导入失败......");
	    	e.printStackTrace();
	    }
	}
	
	/**
	 * @author caixl
	 * @date Mar 26, 2013
	 * @description TODO
	 * @param pdtList
	 * @param pdList void
	 */
	private void importDictInfo(List<PDictionaryType> pdtList,
			List<PDictionary> pdList) {
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.6:1521:ZB", "centre_test", "centre_test");
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("INSERT INTO B_DICTIONARY_TYPE VALUES (?,?,?,?,?,?)");
			ps1 = conn.prepareStatement("INSERT INTO B_DICTIONARY VALUES (?,?,?,?,?,?,?,?,?)");
			for(PDictionaryType pdt:pdtList){
				ps.setString(1, pdt.getTypeId());
				ps.setString(2, pdt.getTypeName());
				ps.setString(3, pdt.getTypeCode());
				ps.setString(4, pdt.getRemark());
				ps.setShort(5, (short)0);
				ps.setString(6, pdt.getParentId());
				ps.addBatch();
			}
			ps.executeBatch();
			ps.close();
			pdtList = null;int s = 1;
			for(PDictionary pd : pdList){
				ps1.setString(1, pd.getDictId());
				ps1.setString(2, pd.getDictName());
				ps1.setString(3, pd.getDictCode());
				ps1.setString(4, pd.getNamePinyin());
				ps1.setString(5, pd.getNameJianpin());
				ps1.setString(6, pd.getRemark());
				ps1.setString(7, pd.getDicttypeId());
				ps1.setShort(8, (short)0);
				ps1.setShort(9, pd.getOrderNo());
				ps1.addBatch();
				s++;
				if(s==500){
					s=1;
					ps1.executeBatch();
				}
			}
			ps1.executeBatch();
			ps1.close();
			conn.commit();
			conn.close();
		}catch(Exception e){
			System.out.println("插入数据失败......");
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null) ps.close();
				if(ps1!=null) ps1.close();
				if(conn!=null) conn.close();
			}catch(Exception e){}
		}
	}
	
	/***************************************导入数据字典end*************************************************/
}
