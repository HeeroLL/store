package com.zebone.dip.dictionary.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.core.util.ChineseToPinYin;
import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.dictionary.service.DipDictionaryService;
import com.zebone.dip.dictionary.vo.DipDictionary;
import com.zebone.dip.dictionary.vo.DipDictionaryType;
import com.zebone.util.Encodes;
import com.zebone.util.EncodingTool;
import com.zebone.util.UUIDUtil;
/**
 * 字典Controller
 * @author YinCm
 * @version 2013-7-18 上午10:10:20
 */
@Controller
public class DipDictController {

	@Resource(name="dipDictionaryService")
	private DipDictionaryService dds;
	
	
	@RequestMapping("dip/dict/index")
	public String dictIndex(){
		return "dip/dict/dict_type";
	}
	
	
	/**
	 * 通过type_name和parent_id获取类型编码与版本号信息
	 * @param ddt
	 * @return
	 */
	@RequestMapping(value="dip/dict/getDictVersionInfo", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> getDictVersionInfo(DipDictionaryType ddt){
		String type_code;
		Map<String, String> map = new HashMap<String, String>();
		if(ddt.getParent_id().equals("1")){
			type_code = ddt.getType_name();
			if(type_code!=null && !type_code.trim().equals("")){
				String[] jianpin = ChineseToPinYin.chineseToPinyinAndShort(type_code).toUpperCase().split(",");
				if(jianpin.length>1){
					ddt.setType_code(jianpin[1]);
					map.put("type_code", jianpin[1]);
				}else{
					ddt.setType_code(jianpin[0]);
					map.put("type_code", jianpin[0]);
				}
			}else{
				map.put("type_code", "");
			}
		}else{
			//添加类型编号
			DipDictionaryType ddt_code_list = new DipDictionaryType();
			ddt_code_list.setParent_id(ddt.getParent_id());
			ddt_code_list.setType_name(ddt.getType_name());
			List<String> code_list = dds.getTypeCodeListByParentIdAndTypeName(ddt_code_list);
			if(code_list.size()==0){
				type_code = dds.getDipDictionaryTypeById(ddt.getParent_id()).getType_code();
				int countTypeByParent = dds.countDipDictionaryByParentId(ddt.getParent_id())+1;  
				if(countTypeByParent<10){
					type_code=type_code+"000"+countTypeByParent;
				}else if(countTypeByParent<100){
					type_code=type_code+"00"+countTypeByParent;
				}else if(countTypeByParent<1000){
					type_code=type_code+"0"+countTypeByParent;
				}else{
					type_code=type_code+countTypeByParent;
				}
			}else{
				int index = code_list.get(0).lastIndexOf("_");
				type_code = code_list.get(0).substring(0, index);
			}
			
			
			//ddt.setType_code(type_code);
			//map.put("type_code", type_code);
			
			
			String type_name = ddt.getType_name();
			//添加版本号
			if(type_name!=null && !type_name.trim().equals("")){
				int countExist = dds.countTypeByParentIdAndName(ddt);
				String version = "";
				if(countExist<10){
					version = "1."+countExist;
				}else{
					version = version+(countExist+10);
					String fist = version.substring(1);
					String second = version.substring(1, 2);
					version = fist+"."+second;
				}
				map.put("version", version);
				ddt.setVersion(version);
				ddt.setType_code(type_code+"_"+version);
				map.put("type_code", ddt.getType_code());
			}else{
				map.put("version", "");
			}
		}
		return map;
	}
	
	/**
	 * 添加一个字典类型
	 * @param ddt
	 * @return
	 */
	@RequestMapping(value="dip/dict/saveType", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addDict(DipDictionaryType ddt){
		boolean isSuccess;
		Map<String,String> map = new HashMap<String, String>();
		if(ddt.getType_id()!=null && ddt.getType_id().trim()!=""){
            if(!ddt.getType_code().contains("_") && ddt.getVersion()!=null && !"".equals(ddt.getVersion())){
                ddt.setType_code(ddt.getType_code()+"_"+ddt.getVersion());
            }
			String[] ids = new String[]{ddt.getType_id().trim()};
			if(!this.dds.checkDicTypeChildrenBeforeDelete(ids)){
				map.put("success", "cannot");
				return map;
			}
			isSuccess = dds.updateDipDictionaryType(ddt);
			
		}else{
			ddt.setType_id(UUIDUtil.getUuid());
			String type_code;
			if(ddt.getParent_id().equals("1")){
				type_code = ddt.getType_name();
				String[] jianpin = ChineseToPinYin.chineseToPinyinAndShort(type_code).toUpperCase().split(",");
				if(jianpin.length>1){
					ddt.setType_code(jianpin[1]);
				}else{
					ddt.setType_code(jianpin[0]);
				}
			}else{
				DipDictionaryType ddt_code_list = new DipDictionaryType();
				ddt_code_list.setParent_id(ddt.getParent_id());
				ddt_code_list.setType_name(ddt.getType_name());
				List<String> code_list = dds.getTypeCodeListByParentIdAndTypeName(ddt_code_list);
				if(code_list.size()==0){
					//添加类型编号
					type_code = dds.getDipDictionaryTypeById(ddt.getParent_id()).getType_code();
					int countTypeByParent = dds.countDipDictionaryByParentId(ddt.getParent_id())+1;  
					if(countTypeByParent<10){
						type_code=type_code+"000"+countTypeByParent;
					}else if(countTypeByParent<100){
						type_code=type_code+"00"+countTypeByParent;
					}else if(countTypeByParent<1000){
						type_code=type_code+"0"+countTypeByParent;
					}else{
						type_code=type_code+countTypeByParent;
					}
				}else{
					int index = code_list.get(0).lastIndexOf("_");
					type_code = code_list.get(0).substring(0, index);
				}
				
				
				
				//添加版本号
				int countExist = dds.countTypeByParentIdAndName(ddt);
				String version = "";
				if(countExist<10){
					version = "1."+countExist;
				}else{
					version = version+(countExist+10);
					String fist = version.substring(1);
					String second = version.substring(1, 2);
					version = fist+"."+second;
				}
				ddt.setVersion(version);
				ddt.setType_code(type_code+"_"+version);
			}	
			
			isSuccess = dds.addDipDictionaryType(ddt);
		}
		
		
		if(isSuccess){
			map.put("success", "true");
		}else{
			map.put("success", "false");
		}
		return map;
	}
	
	/**
	 * 查寻数据字典，父级id为精确查询，其余为模糊查询
	 * @param parent_id
	 * @param page
	 * @return
	 */
	@RequestMapping(value="dip/dict/searchType", method=RequestMethod.POST)
	public @ResponseBody JsonGrid searchDipDictType(Pagination<DipDictionaryType> page, DipDictionaryType ddt){
        if (ddt.getType_code() != null && !"".equals(ddt.getType_code())) {
            String typeCode = Encodes.urlDecode(ddt.getType_code());
            ddt.setType_code(EncodingTool.escapeStr(typeCode));
        }
        if (ddt.getType_name() != null && !"".equals(ddt.getType_name())) {
            String typeName = Encodes.urlDecode(ddt.getType_name());
            ddt.setType_name(EncodingTool.escapeStr(typeName));
        }
        page = dds.searchDipDictionaryType(ddt, page);
		for(DipDictionaryType ddtt : page.getData()){
			if(ddtt.getParent_id().equals("1")){
				ddtt.setVersion("--------");
			}
		}
		JsonGrid jGrid = new JsonGrid("success",page.getTotalCount(),page.getData());
		return jGrid;
		 
	}
	
	/**
	 * 获取所有字典类型
	 * @return
	 */
	@RequestMapping("dip/dict/allDictType")
	@ResponseBody
	public List<DipDictionaryType> getAllDictType(){
		//List<DipDictionaryType> list = dds.getAllDipDictionaryType();
		List<DipDictionaryType> list = dds.getLevel1DictType();
//		for(DipDictionaryType ddt : list){
//			if(!ddt.getParent_id().equals("1")){
//				ddt.setType_name(ddt.getType_name()+"("+ddt.getVersion()+")");
//			}else{
//				ddt.setVersion("--------");
//			}
//		}
		//设定根结点
//		DipDictionaryType ddt = new DipDictionaryType();
//		ddt.setType_id("1");
//		ddt.setParent_id("0");
//		ddt.setType_name("字典类别");
//		list.add(ddt);
		
		return list;
	}
	/**
	 * 通过type_id获取字典类型
	 * @param type_id
	 * @return
	 */
	@RequestMapping("dip/dict/getDictTypeById")
	@ResponseBody
	public Map<String, Object> getDipDictionaryTypeById(String type_id){
		DipDictionaryType ddt = dds.getDipDictionaryTypeById(type_id);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("ddt", ddt);
		return map;
	}
	
	/**
	 * 删除字典类型
	 */
	@RequestMapping("dip/dict/removeTypeByIds")
	@ResponseBody
	public Map<String, Boolean> removeDipDictType(String id){
		String[] ids=null;
		if(id!=null &&id.trim()!=null){
			ids=id.split(",");
		}
		boolean success = dds.deleteDipDictionaryTypeByIds(ids);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("success", success);
		return map;
	}
	
	
	//////////字典CRUD
	
	/**
	 * 按照type_id获取对应的子节点
	 */
	@RequestMapping("dip/dict/searchDipDict")
	public @ResponseBody JsonGrid searchDipDict(Pagination<DipDictionary> page, DipDictionary dd){
        if (dd.getDict_code() != null && !"".equals(dd.getDict_code())) {
            String dictCode = Encodes.urlDecode(dd.getDict_code());
            dd.setDict_code(EncodingTool.escapeStr(dictCode));
        }
        if (dd.getDict_name() != null && !"".equals(dd.getDict_name())) {
            String dictName = Encodes.urlDecode(dd.getDict_name());
            dd.setDict_name(EncodingTool.escapeStr(dictName));
        }
        page = dds.searchDipDictionary(dd, page);
		JsonGrid jGrid = new JsonGrid("success",page.getTotalCount(),page.getData());
		return jGrid;
	}
	
	/**
	 * 添加字典
	 */
	/**
	 * @param dd
	 * @return
	 */
	@RequestMapping("dip/dict/saveDipDict")
	public @ResponseBody Map<String,Boolean> addDipDict(DipDictionary dd){
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean success ;
		if(dd.getDict_id()==null || dd.getDict_id().trim().equals("")){
			dd.setDict_id(UUIDUtil.getUuid());
			success = dds.addDipDictionary(dd);
		}else{
			success = dds.updateDipDictionary(dd);
		}
		map.put("success", success);
		return map;
	}
	
	/**
	 * 按照ids删除字典值域
	 */
	@RequestMapping("dip/dict/delDipDictByIds")
	public @ResponseBody Map<String, Boolean> deleteDipDictByIds(String id){
		String[] ids = null;
		boolean success;
		if(id==null || id.trim().equals("")){
			success = false;
		}else{
			ids = id.split(",");
			success = dds.deleteDipDictionaryByIds(ids);
		}
		
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("success", success);
		return map;
	}
	
	/**
	 * 按照dict_id获取dict
	 */
	@RequestMapping("dip/dict/getDipDictById")
	public @ResponseBody DipDictionary getDipDictById(String id){
		DipDictionary dd = dds.findDipDictionaryById(id);
		return dd;
	}

    /**
     * 根据ID判断该字典类型是否存在子类型或者值域
     * @param id
     */
    @RequestMapping("dip/dict/checkDictById")
    @ResponseBody
    public Map<String, Boolean> checkDictById(String id) {
        String[] ids = new String[]{id.trim()};
        boolean success = !(dds.checkDicTypeChildrenBeforeDelete(ids));
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("success", success);
        return map;
    }

}
