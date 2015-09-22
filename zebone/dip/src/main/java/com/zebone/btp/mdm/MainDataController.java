package com.zebone.btp.mdm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.Constant;
import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.core.web.BaseController;
import com.zebone.btp.mdm.service.MainDataService;
import com.zebone.btp.mdm.vo.DBQuery;
import com.zebone.btp.mdm.vo.MDObject;
import com.zebone.btp.mdm.vo.MainDataTypeVO;
import com.zebone.util.JsonUtil;
import com.zebone.util.UUIDUtil;

/**
 * 
 * @author ouyangxin 主数据管理
 */
@Controller
@RequestMapping("maindata")
public class MainDataController extends BaseController {

	@Autowired
	private MainDataService mainDataService;

	/**
	 * 主数据管理 首页
	 */
	@RequestMapping("index")
	public String mainDataIndex(HttpServletRequest reqs,
			HttpServletResponse resp) {

		return "maindata/index";
	}

	/**
	 * 主数据类型 tree
	 */
	@RequestMapping("btp-maindata-tree")
	public String mainDataTree(HttpServletRequest reqs, HttpServletResponse resp) {

		MainDataTypeVO vo = new MainDataTypeVO();
		List<MainDataTypeVO> dataTree = mainDataService.queryMDTList(vo);
		reqs.setAttribute("mainDataTree",JsonUtil.writeValueAsString(dataTree));
		return "maindata/maindataTree";
	}

	/**
	 * 主数据类型 管理页面
	 */
	@RequestMapping("btp-maindata-type")
	public String mainDataType(HttpServletRequest reqs, HttpServletResponse resp) {
		
		//设置类型list
		List<MainDataTypeVO> typeList = mainDataService.queryTypeList();
		reqs.setAttribute("typeList", typeList);
		
		String mdtType  = StringUtils.isEmpty(reqs.getParameter("mdtType"))?Constant.MDT_TYPE:reqs.getParameter("mdtType");
		String mdtName  = reqs.getParameter("mdtName");
		
		//为空设置默认值
		reqs.setAttribute("mdtType", mdtType);
		reqs.setAttribute("mdtName", StringUtils.isEmpty(mdtName)?Constant.MDT_NAME:mdtName);
		reqs.setAttribute("mdtId", reqs.getParameter("mdtId"));
		
		return "maindata/mainDataType";
	}

	/**
	 * 主数据类型 分页列表
	 */
	@RequestMapping("btp-maindata-type-list")
	public @ResponseBody
	JsonGrid mdtQueryPage(Pagination<MainDataTypeVO> page,
			@ModelAttribute MainDataTypeVO mainDataType) {
		page = mainDataService.mdtQueryPage(page, mainDataType);
		JsonGrid jGrid = new JsonGrid("success", page.getTotalCount(), page
				.getData());
		return jGrid;
	}

	/**
	 * 主数据 管理界面
	 */
	@RequestMapping("btp-maindata")
	public String mainData(HttpServletRequest reqs, HttpServletResponse resp) {

		// 根据Code显示主数据否则显示主数据类型管理页面
		String code = reqs.getParameter("code");
		String resultURI = "";
		if (StringUtils.isEmpty(code)) {
			resultURI = "";
		} else {
			MainDataTypeVO mdt = mainDataService.findMDTByCode(code);
			reqs.setAttribute("mdt", mdt);
			resultURI = "maindata/maindata";
		}

		return resultURI;
	}

	/**
	 * 主数据 列表
	 */
	@RequestMapping("btp-mdm-list")
	public @ResponseBody
	JsonGrid mainDataList(Pagination<MDObject> page, DBQuery dbQuery) {

		page = mainDataService.mtDetailPage(page, dbQuery);
		JsonGrid jGrid = new JsonGrid("success", page.getTotalCount(), page
				.getData());
		return jGrid;
	}

	/**
	 * 
	 * MD删除 支持批量删除
	 * 
	 * @param mdIds
	 * @return
	 */
	@RequestMapping("btp-mdm-remove")
	@ResponseBody
	public Map<String, Object> removeMDBatch(@ModelAttribute
	DBQuery dbQuery, @RequestParam("id")
	String ids) {
		dbQuery.setId(ids);
		boolean resultFlag = mainDataService.removeMDBatch(dbQuery);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", resultFlag);
		return map;
	}

	/**
	 * 
	 * 根据ID加载数据
	 * 
	 * @param dbQuery
	 * @return
	 */
	@RequestMapping("btp-mdm-load")
	public @ResponseBody
	MDObject loadMD(@ModelAttribute
	DBQuery dbQuery) {

		return mainDataService.loadMD(dbQuery);
	}

	/**
	 * 
	 * MD 新增 删除
	 * 
	 * @param reqs
	 * @param dbQuery
	 * @return
	 */
	@RequestMapping("btp-mdm-operate")
	@ResponseBody
	public Map<String, Object> operateMDData(HttpServletRequest reqs,
			@ModelAttribute
			DBQuery dbQuery) {

		// 处理传值
		String[] arr = dbQuery.getCorres().split(",");
		String value = "";
		for (String temp : arr) {
			value = reqs.getParameter(temp.trim());
			if (value.equals(dbQuery.getPrimaryKeyValue())) {
				value = UUIDUtil.getUuid();
			}
			dbQuery.addValue(value);
		}

		boolean bool = mainDataService.operateMDData(dbQuery);

		Map<String, Object> map = new HashMap<String, Object>();
		String message = bool ? "保存成功！" : "保存失败！";
		map.put("bool", bool);
		map.put("msg", message);
		return map;

	};

	/**
	 * 
	 * 新增类型数据MDT
	 * @param reqs
	 * @param mdt
	 * @return
	 */
	@RequestMapping("btp-mdtype-operate")
	@ResponseBody
	public Map<String, Object> operateMDTypeData(HttpServletRequest reqs,
			@ModelAttribute MainDataTypeVO mdt) {
		
		boolean bool = false;
		
		//处理数据
		MainDataTypeVO vo = mdt;
		
		if(null != vo)
		{
			// mdtcode有值则为更新 否则 新增
			if(StringUtils.isNotEmpty(vo.getMdtCode()))
			{
				bool = mainDataService.updateMDTData(vo);
			}
			else
			{
				//type值是根类型则新增type自身信息 否则根据type值新建表信息
				if(Constant.MDT_TYPE.equals(vo.getMdtType()))
				{
					bool =  mainDataService.operateMDType(vo);
				}else
				{
					bool =  mainDataService.operateMDTData(vo);
				}
			}
		}
		//封装返回结果
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bool", bool);
		String message = bool ? "保存成功！" : "保存失败！";
		map.put("msg", message);
		return map;
	};	
	
	/**
	 * 根据ID加载类型数据
	 * @param id
	 * @return
	 */
	@RequestMapping("btp-mdt-load")
	public @ResponseBody
	MainDataTypeVO loadMD(@RequestParam("code")
	String code) {

		return mainDataService.findMDTByCode(code);
	}
	
	@RequestMapping("btp-mdmtype-remove")
	@ResponseBody
	public Map<String, Object> removeMDTypeBatch(@ModelAttribute
	DBQuery dbQuery, @RequestParam("id")
	String ids) {
		dbQuery.setId(ids);
		boolean resultFlag = mainDataService.removeMDType(dbQuery);
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("success", resultFlag);
		return map;
	}
}
