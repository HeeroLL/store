package com.zebone.dip.compare.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.core.util.JsonGrid;
import com.zebone.dip.compare.service.CompareCommonService;
import com.zebone.dip.compare.service.ExcelManageService;
import com.zebone.dip.compare.vo.Module;

/**
 *  
 * @author YinCM
 *
 */
@Controller
public class CompareIndexController {

	@Resource
	private CompareCommonService compareCommonService;
	@Resource
	private ExcelManageService excelManageService;
	
	/**
	 * 首页地址
	 * @return
	 */
	@RequestMapping("compare/index")
	public String compareIndex(){
		return "dip/compare/index";
	}
	
	/**
	 * 模板载入地址
	 * @return
	 */
	@RequestMapping("compare/loadModuleindex")
	public String compareLoadModuleIndex(){
		return "dip/compare/moduleLoadIndex";
	}
	
	/**
	 * 模板列表
	 * @return
	 */
	@RequestMapping("compare/modules")
	@ResponseBody
	public JsonGrid getModules(){
		Module mod1 = new Module("医疗机构信息", "WJD01.001", "1");
		Module mod2 = new Module("医疗机构人员信息", "WJD01.003", "2");
		Module mod3 = new Module("行政区划信息", "WJD01.004", "3");
		Module mod4 = new Module("诊疗项目信息", "WJD01.006", "4");
		Module mod5 = new Module("药品信息", "WJD01.008", "5");
		Module mod6 = new Module("字典", "WJD01.00x", "6");
		List<Module> moduleList = new ArrayList<Module>();
		moduleList.add(mod1);
		moduleList.add(mod2);
		moduleList.add(mod3);
		moduleList.add(mod4);
		moduleList.add(mod5);
		moduleList.add(mod6);
		JsonGrid jGrid = new JsonGrid("success",10,moduleList);
		return jGrid;
	}
	
	/**
	 * 通过提交的模板类型编码，生成excel文档
	 * 医疗机构信息   : 1
	 * 医疗机构人员信息   : 2
	 * 行政区划信息  :3
	 * 诊疗项目信息  : 4
	 * 药品信息 : 5
	 * 字典 : 6
	 * @param typeId
	 * @return
	 */
/*	@RequestMapping("compare/getModuleTemplateByTypeId")
	@ResponseBody
	public String getModuleTemplate(String typeId){
		List<String> list = compareCommonService.generateModuleTemplate(typeId);
		return "生成excel成功~"+typeId+"--共有:"+list.size();
	}
	*/
	@RequestMapping(value="compare/getModuleTemplateByTypeId")
    public void getModuleTemplate(HttpSession session,HttpServletResponse response, String typeId) throws Exception{
        try{
            String filePathToBeServed = excelManageService.createModuleExcel(typeId);//complete file name with path;
            File fileToDownload = new File(filePathToBeServed);
            InputStream inputStream = new FileInputStream(fileToDownload);
            String name= filePathToBeServed.substring(filePathToBeServed.lastIndexOf("/")+1,filePathToBeServed.lastIndexOf("-")+1);
            name="机构"+name+"数据填报.xlsx";
            System.out.println(name);
            
            response.setContentType("application/force-download");
            
            String fileName = URLEncoder.encode(name,"UTF-8");
            if(fileName.length()>150)//解决IE 6.0 bug
                  fileName=new String(name.getBytes("GBK"),"ISO-8859-1");
            response.setHeader( "Content-Disposition", "attachment;filename="  + fileName);
            //response.setHeader("Content-Disposition", "attachment; filename="+new String(name.getBytes("utf-8"),"iso-8859-1"));
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Length",new Long(fileToDownload.length()).toString());
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();

        }catch(Exception e){
            e.printStackTrace();
        }

    }
	
}
