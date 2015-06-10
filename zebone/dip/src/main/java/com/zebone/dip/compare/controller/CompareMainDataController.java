package com.zebone.dip.compare.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zebone.dip.compare.service.ExcelManageService;
import com.zebone.dip.compare.utils.ExcelFileUploadValidator;
import com.zebone.dip.compare.vo.FileUpload;
import com.zebone.dip.compare.vo.MainInfo;
import com.zebone.dip.compare.vo.UploadDataVO;

/**
 * 主数据对照模块controller
 * 
 * @author YinCM
 * 
 */
@Controller
public class CompareMainDataController {

	@Resource 
	private ExcelFileUploadValidator excelFileUploadValidator;
	@Resource
	private ExcelManageService excelManageService;
	/**
	 * 模板载入地址
	 * 
	 * @return
	 */
	@RequestMapping("compare/maindata/index")
	public String compareLoadModuleIndex() {
		return "dip/compare/maindata/mainDataLoadIndex";
	}

	/**
	 * 主数据对照功能
	 * 
	 * @return
	 */
	@RequestMapping("compare/maindata/manageIndex")
	public String compareMaindataManageIndex(MainInfo info,ModelMap map) {
		String tableName = "";
		if(!StringUtils.isBlank(info.getTableName())){
			tableName = info.getTableName();
		}
		map.put("tableName", tableName);
		return "dip/compare/maindata/mainDataManageIndex";
	}

	/**
	 * 文件上传
	 * 
	 * @return
	 */
	
	public Map<String, String> fileUpload() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("hello.jpg", "hello.jpg");
		map.put("successCount", "1200");
		map.put("failCount", "12");
		map.put("detailDownload", "aqqq.xslt");
		return map;
	}

	@RequestMapping(value="compare/maindata/uploadFile", method=RequestMethod.POST)
	@ResponseBody
	public void fileUploaded(MultipartHttpServletRequest request, HttpServletResponse response, @ModelAttribute("uploadedFile") FileUpload fileUpload,
			BindingResult result) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		MultipartFile file = fileUpload.getFile();
		excelFileUploadValidator.validate(fileUpload, result);
		String fileName = file.getOriginalFilename();
		//System.out.println("fileUpload.getFileType()"+fileUpload.getFileType());
		
		
		//上传文件保存路径
		String destPath="";
		try {
			inputStream = file.getInputStream();
			//保存文件
			destPath = this.getClass().getClassLoader().getResource("").getPath();
			Date date = new Date();
			String strDate = new Long(date.getTime()).toString();
			destPath = destPath.substring(0, destPath.lastIndexOf("/WEB-INF/classes/"))+"/dipCompare/uploadDataFile/"+fileName+strDate+".xlsx";
			destPath = destPath.replaceAll("%20", " ");
			System.out.println("destPath---"+destPath);
			
			File newFile = new File(destPath);
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		UploadDataVO uploadDataVO = null;
		//如果是字典类型，则进字典转换
		Map<String, String> map = new HashMap<String, String>();
		try{
			if(fileUpload.getFileType().trim().equals("6")){
				uploadDataVO = excelManageService.loadDictExcelToDBAndGenerateResultFile(fileUpload, destPath);
			}else{
				uploadDataVO = excelManageService.loadExcelToDBAndGenerateResultFile(fileUpload, destPath);
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("success", "false");
		}
		
		if(uploadDataVO!=null){
			map.put("success", "true");
		}else{
			map.put("success", "false");
		}
		
		//String destPathError = this.getClass().getClassLoader().getResource("").getPath();
		//destPathError = destPathError.substring(0, destPathError.lastIndexOf("/WEB-INF/classes/"))+"/dipCompare/errorExcelFile/"+errorFileName+".xlsx";
		map.put("filePath", uploadDataVO.getFileName());
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    String resultStr = "{\"success\":\""+map.get("success")+"\", \"filePath\":"+"\""+uploadDataVO.getFileName()+"\", \"correctCount\":"+"\""+new Integer(uploadDataVO.getCorrectCount()).toString()+"\", \"failCount\":"+"\""+new Integer(uploadDataVO.getFailCount()).toString()+"\", \"successCount\":"+"\""+new Integer(uploadDataVO.getSuccessCount()).toString()+"\", \"fileNameWrong\":"+"\""+new Boolean(uploadDataVO.isFileNameWrong()).toString()+"\"}";
	    try {
			response.getWriter().write(resultStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("compare/maindata/fileUpload")
	public void errorDetailUpload(HttpServletRequest request, HttpServletResponse response){
		String name = request.getParameter("name");
		if(!StringUtils.isBlank(name)){
			String path = this.getClass().getClassLoader().getResource("").getPath();
			path = path.substring(0, path.lastIndexOf("/WEB-INF/classes/"));
			path = path.replaceAll("%20", " ");
			File file = new File(path+"/dipCompare/errorExcelFile/"+name+".xlsx");
			
			try {
				// 通知浏览器以下载的方式打开
				response.setHeader("content-type", "application/octet-stream");
				response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "utf-8"));
				
				// 创建和文件相关的输入流
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));

		        // 获得response的输出流
		        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());

		        // 流的对拷
		        for (int data; (data = in.read()) != -1;)
		            out.write(data);
		        in.close();
		        out.close();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
