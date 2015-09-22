package com.zebone.check;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.zebone.check.CheckConfig.Check;
import com.zebone.check.CheckConfig.Rule;
import com.zebone.check.component.DictionaryCheck;
import com.zebone.check.component.MasterDataCheck;
import com.zebone.util.SpringAppContext;

public abstract class Vcheck {
	
	
	private static Map<String,MethodInfo> checkClassMap = new HashMap<String,MethodInfo>() ;
	
	static{
		//主数据
		List<Class<? extends Object>> masterDataParaClass = new ArrayList<Class<? extends Object>>();
		masterDataParaClass.add(Connection.class);
		masterDataParaClass.add(String.class);
		masterDataParaClass.add(String.class);
		checkClassMap.put("masterdata", new MethodInfo(new MasterDataCheck(),"checkMasterData",masterDataParaClass));	
	
		//字典
		List<Class<? extends Object>> dicParaClass = new ArrayList<Class<? extends Object>>();
		dicParaClass.add(Connection.class);
		dicParaClass.add(String.class);
		dicParaClass.add(String.class);
		checkClassMap.put("dic", new MethodInfo(new DictionaryCheck(), "checkDictionary", dicParaClass));
		
		//字典多选
		List<Class<? extends Object>> dicListParaClass = new ArrayList<Class<? extends Object>>();
		dicListParaClass.add(Connection.class);
		dicListParaClass.add(String.class);
		dicListParaClass.add(String.class);
		checkClassMap.put("dicList", new MethodInfo(new DictionaryCheck(), "checkDictionaryList", dicListParaClass));
		
	}
	
	public <T> String check(CheckConfig checkConfig,T checkObjcet) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, SQLException{
		StringBuilder result = new StringBuilder();
		List<Check> checkList = checkConfig.getCheckList();
		String path = checkConfig.getPath();
		if(path!=null&&path!=""){
			path+=".";
		}else{
			path="";
		}
		if(checkList!=null && checkList.size() >0){
			for(Check check : checkList){
				String field = path+check.getField();
				String fieldName = check.getFieldName();
//				String checkValue = BeanUtils.getProperty(checkObjcet, field);
				Object[] values = getValue(checkObjcet, field);
				if(values!=null&&values.length>0){
					for(Object objVal : values){
						boolean bool =false;
						if(!(objVal instanceof Short)){
							String temp = (String)objVal;
							//字符串值域校验，如果为null值，不进行校验
							if(temp!=null&&temp!=""){
								bool =true;
							}
						}else{
							Short ss = (Short)objVal;
							objVal = Short.toString(ss);
							bool =true;
						}
						if(bool){
							Object checkValue = objVal;
							for(Rule rule : check.getRuleList()){
							      CheckResult checkResult = null;
							      String type = rule.getType();
							      String[] types = type.split(":");
							      MethodInfo mi = checkClassMap.get(types[0]);
							      if(types.length == 1){
							    	  Object[] paraObject = new Object[2];
							    	  DataSource ds = SpringAppContext.getApplicationContext().getBean("dataSource",DataSource.class);
						  			  Connection con = ds.getConnection();
						  			  paraObject[0] = con;
						  			  paraObject[1] = checkValue;
							    	  checkResult = (CheckResult)MethodUtils.invokeMethod(mi.getObj(), mi.getMethod(),paraObject);
							    	  releaseConnection(con,ds);
							      }else{
							    	List<Class<? extends Object>> paraClass = mi.getParaClass();
							    	Object[] paraObject = new Object[paraClass.size()];
							    	Pattern pattern = Pattern.compile("\\[(.+)\\]",Pattern.CASE_INSENSITIVE);
							  		Matcher matcher = pattern.matcher(types[1]);
							  		while(matcher.find()){
							  			String[] para = matcher.group(1).split(",");
							  			int i=0;
							  			for(;i<para.length;i++){
							  			    Object obj =	paraClass.get(i);
							  			    if(obj.equals(Connection.class)){
							  			    	if(para[i].equals("dataSource")){
							  			    		//当前数据源
							  			    		DataSource ds = SpringAppContext.getApplicationContext().getBean("dataSource",DataSource.class);
									  				Connection con = ds.getConnection();
									  				paraObject[i] = con;
							  			    	}else{
							  			    		//自定义，配置数据源
							  			    	}
							  			     }
							  			    if(obj.equals(String.class)){
							  			    	Pattern pattern2 = Pattern.compile("\\{(.+)\\}");
							  			    	Matcher matcher2 = pattern2.matcher(para[i]);
							  			    	if(matcher2.find()){
							  			    		String str  = matcher2.group(1);
							  			    		Object[] val = getValue(checkObjcet, str);
							  			    		paraObject[i] = val[0];
							  			    	}else{
							  			    		paraObject[i] = para[i];
							  			    	}
							  			    }
							  			}
							  			paraObject[i] = checkValue;
							  		}
							  		checkResult = (CheckResult)MethodUtils.invokeMethod(mi.getObj(), mi.getMethod(),paraObject);
							  		DataSource ds = SpringAppContext.getApplicationContext().getBean("dataSource",DataSource.class);
							  		releaseConnection((Connection)paraObject[0],ds);
							      }
							      if(!checkResult.isSuccess()){
							    	  result.append(fieldName).append(" ").append(checkResult.getErrorMessage()).append("\r\n"); 
							      }
							}
						}
					}
				}
			}
		}	
		return result.toString();
	}
	
	/**
	 * 释放数据库链接
	 * @param con
	 * @param ds
	 */
	private void releaseConnection(Connection con,DataSource ds){
		DataSourceUtils.releaseConnection(con, ds);
	}
	
	public Object[] getValue(Object obj,String value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        String[] propertys = value.split("\\.",2);
        if(propertys.length == 1){
        	return new Object[]{PropertyUtils.getProperty(obj, propertys[0])};
        }else{
        	if(obj!=null){
        		PropertyDescriptor pd = PropertyUtils.getPropertyDescriptor(obj,propertys[0]);
            	Class<? extends Object> clz = pd.getPropertyType();
         		if(clz.isAssignableFrom(List.class)){
         			List<Object> resultObj = new ArrayList<Object>();
         			List<Object> objList = (List<Object>)PropertyUtils.getProperty(obj, propertys[0]);
         			for(Object lObj : objList){
         			   Object[] returnObj =	getValue(lObj,propertys[1]);
         			   for(Object tempObj : returnObj){
         				  resultObj.add(tempObj);
         			   }
//         			   resultObj.add(Arrays.asList(returnObj));
         			}
         			return resultObj.toArray();
         		}else{
         			Object nObj = PropertyUtils.getProperty(obj, propertys[0]);
         			return getValue(nObj,propertys[1]);
         		}
        	}else{
        		return null;
        	}
        }
	}
	
	public void setCheckClassMap(Map<String,MethodInfo> map){
		checkClassMap.putAll(map);
	}
	
	@SuppressWarnings("unused")
    public static class MethodInfo{
    
		private Object obj;
    	
    	private String method;
    	
        private List<Class<? extends Object>> paraClass;
        
		public MethodInfo(Object obj, String method) {
			super();
			this.obj = obj;
			this.method = method;
		}
        
		public MethodInfo(Object obj, String method,
				List<Class<? extends Object>> paraClass) {
			super();
			this.obj = obj;
			this.method = method;
			this.paraClass = paraClass;
		}

		public Object getObj() {
			return obj;
		}

		public void setObj(Object obj) {
			this.obj = obj;
		}

		public String getMethod() {
			return method;
		}

		public void setMethod(String method) {
			this.method = method;
		}

		public List<Class<? extends Object>> getParaClass() {
			return paraClass;
		}

		public void setParaClass(List<Class<? extends Object>> paraClass) {
			this.paraClass = paraClass;
		}
		
	     

    }
    

}
