package com.isell.ps.app;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isell.bis.sys.BisException;
import com.isell.cache.service.JVMCacheService;
import com.isell.core.auth.bean.RequestParameter;
import com.isell.core.config.BisConfig;
import com.isell.core.util.Coder;
import com.isell.core.util.Identities;
import com.isell.core.util.JsonData;
import com.isell.core.web.FileUtil;
import com.isell.service.common.po.UploadParam;
import com.isell.service.common.service.CommonService;


/**
 * app公共服务统一网关入口
 * 
 * @author lilin
 * @version [版本号, 2015年11月10日]
 */
@Controller
@RequestMapping("app")
public class AppGatewayController {
    
    /**
     * JVM缓存服务接口
     */
    @Resource
    private JVMCacheService jvmCacheService;
    
    /**
     *公共服务接口
     */
    @Resource
    private CommonService commonService;
    
    /**
     * 配置信息
     */
    @Resource
    private BisConfig config;
    
    // private static final String token="037ce836aeb911e58acb00e04c8cea44";
    /**
     * 统一网关入口负责校验请求合法性，和转发合法请求到指定控制器
     *
     * @param request 请求
     * @param service 接口服务名
     * @param param 参数
     * @return 处理结果
     */
    @RequestMapping("gateway")
    public String gateway(HttpServletRequest request, String service, RequestParameter param) {
        if (service == null) {
            throw new RuntimeException("exception.access.service.null");
        }
      
        String accessCode = param.getAccessCode();
        if (accessCode == null || param.getAuthCode() == null) {
            throw new RuntimeException("exception.access.param-invalidate");
        }
        // 取token信息
        String token = jvmCacheService.get("token_" + accessCode);
        String  token2 = null;
        if(config.getAccessSysMap().get(accessCode)!=null){
        	token2 = config.getAccessSysMap().get(accessCode).getPrivateKey();
        }
        if (StringUtils.isEmpty(token)&&StringUtils.isEmpty(token2)) {
            throw new BisException("-1", "exception.access.token-invalidate"); // -1：token失效
        }
        // 对json参数+私钥的字符串进行MD5加密生成校验码
        String authCode = Coder.encodeMd5(param.getJsonObj() + token);
        String authCode2 = Coder.encodeMd5(param.getJsonObj() + token2);
        if (!authCode.equals(param.getAuthCode())&&!authCode2.equals(param.getAuthCode())) {
            throw new RuntimeException("exception.access.authcode-wrong");
        }      
        
        return "forward:../" + service.replace("_", "/");//默认为forward模式   
    }
    /**
     * app 2.0入口
     * @param request
     * @param service
     * @param param
     * @return
     */
    @RequestMapping("iservice")
    public String iservice(HttpServletRequest request, String service, RequestParameter param)
    {
    	 if (service == null) {
             throw new RuntimeException("exception.access.service.null");
         }
         String accessCode = param.getAccessCode();
         if (accessCode == null || param.getAuthCode() == null) {
             throw new RuntimeException("exception.access.param-invalidate");
         }
         // 对json参数+私钥的字符串进行MD5加密生成校验码
        /* String authCode = Coder.encodeMd5(param.getJsonObj() + token);
         if (!authCode.equals(param.getAuthCode())) {
             throw new RuntimeException("exception.access.authcode-wrong");
         } */       
         
         return "forward:../" + service.replace("_", "/");//默认为forward模式 
    }
    
    
    /**
     * app获取token接口
     *
     * @param accessCode 接入码
     * @return token
     */
    @ResponseBody
    @RequestMapping("getToken")
    public JsonData getToken(String accessCode) {
        JsonData jsonData = new JsonData();
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", Identities.uuid());
        jsonData.setData(map);
        // 将token放入缓存
        jvmCacheService.set("token_" + accessCode, map.get("token"), JVMCacheService.DAY);
        
        return jsonData;
    }
    
    /**
     *  文件上传接口	
     *
     * @param request 请求
     * @param param 参数
     * 
     * @return 上传文件保存路径
     */
    @RequestMapping("uploadFile")
    public JsonData uploadFile(HttpServletRequest request) {
    	//String accessCode = param.getAccessCode();
    	/*
        if (accessCode == null || param.getAuthCode() == null) {
            throw new RuntimeException("exception.access.param-invalidate");
        }
        // 取token信息
        String token = jvmCacheService.get("token_" + accessCode); 
        if (StringUtils.isEmpty(token)) {
            throw new BisException("-1", "exception.access.token-invalidate"); // -1：token失效
        }
//        // 对json参数+私钥的字符串进行MD5加密生成校验码
//        String authCode = Coder.encodeMd5(param.getJsonObj() + token); 
//        if (!authCode.equals(param.getAuthCode())) {
//            throw new RuntimeException("exception.access.authcode-wrong");
//        }       
        */
    	
    	JsonData jsonData = new JsonData();
    	boolean success = false;
    	
    	String path = "";
    	UploadParam param = new UploadParam();
		try {			
	    	File file = new File(config.getImgLocal()+"/temp/");
	        FileUtil.createDirectory(file);
	        ServletFileUpload fu = FileUtil.initFileUpload(file);
			
	        List<FileItem> fileItems = fu.parseRequest(request);
	        @SuppressWarnings("rawtypes")
			Iterator iter = fileItems.iterator();	        
	        while (iter.hasNext()) {
	        	FileItem item = (FileItem)iter.next();
	        	if (item.isFormField()) {//判断该表单项是否是普通类型
	        		String key = item.getFieldName();
	        		if("uploadType".equals(key)){
	        			try {
							String value = item.getString("utf-8");
							System.out.println("key==" + key + "; value==" + value);
							param.setUploadType(value);
						} catch (UnsupportedEncodingException e) {
							jsonData.setMsg("上传类型错误");
							e.printStackTrace();
						}
	        		}else if("id".equals(key)){
	        			try {
							String value = item.getString("utf-8");
							System.out.println("key==" + key + "; value==" + value);
							if(StringUtils.isNotEmpty(value)){
								param.setId(value);
							}
						} catch (UnsupportedEncodingException e) {
							jsonData.setMsg("组合参数错误");
							e.printStackTrace();
						}
	        		}
	        	}else{ //否则该表单项是file 类型
	        		String name = item.getName();
	                String filename = item.getName();
	                name = name.substring(0, name.lastIndexOf("."));
	                if (("".equalsIgnoreCase(name)) || (item.getSize() <= 100L)){
	                	continue;
	                }else{
	                    path = System.currentTimeMillis() + (int)(Math.random() * 1000.0D) + FileUtil.getImageSuffix(filename);
	                    File fNew = new File(config.getImgLocal()+"/temp/", path);
	                    item.write(fNew);
	                    
	                    //业务处理，放到哪个目录下，并且获取路径
	                    Map<String, Object> resultMap = commonService.uploadFile(fNew, param).getColumns();
	                    jsonData.setSuccess(resultMap.get("success") == null ? false : (Boolean)resultMap.get("success"));
	                    resultMap.remove("success");
	                    if (resultMap.get("msg") != null) {
	                        jsonData.setMsg((String)resultMap.get("msg"));
	                        resultMap.remove("msg");	                        
	                    }
	                    jsonData.setData(resultMap);
	                    //删除临时文件
	                    if(fNew.exists()){
	                    	fNew.delete();	   
	                    }	                                     
	                    success = true;
	                }
	        	}
	        }
		} catch (FileUploadException e) {
			jsonData.setMsg("不支持的编码");
		} catch (IOException e) {
			jsonData.setMsg("文件上传异常");
	    } catch (Exception e) {
	    	jsonData.setMsg("文件上传失败");
        }        
		 jsonData.setSuccess(success);
        return jsonData;
    }
}
