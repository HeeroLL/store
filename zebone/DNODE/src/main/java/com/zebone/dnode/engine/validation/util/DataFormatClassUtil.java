package com.zebone.dnode.engine.validation.util;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.reflect.ConstructorUtils;

import com.zebone.dnode.engine.validation.component.ADataFormatValidation;
import com.zebone.dnode.engine.validation.component.ANDataFormatValidation;
import com.zebone.dnode.engine.validation.component.D8DataFormatValidation;
import com.zebone.dnode.engine.validation.component.DT15DataFormatValidation;
import com.zebone.dnode.engine.validation.component.DataFormatValidation;
import com.zebone.dnode.engine.validation.component.NDataFormatValidation;
import com.zebone.dnode.engine.validation.component.T6DataFormatValidation;
import com.zebone.dnode.engine.validation.component.TFDataFormatValidation;

/**
 * 数据格式校验util类
 * @author 陈阵 
 * @date 2013-8-12 上午9:52:51
 */
public class DataFormatClassUtil {
	
	private static Map<String,Class<? extends DataFormatValidation>> dataFormatValidationClassMap = new HashMap<String,Class<? extends DataFormatValidation>>();

		
	static{
		dataFormatValidationClassMap.put("A", ADataFormatValidation.class);
		dataFormatValidationClassMap.put("AN", ANDataFormatValidation.class);
		dataFormatValidationClassMap.put("N", NDataFormatValidation.class);
		dataFormatValidationClassMap.put("D8", D8DataFormatValidation.class);
		dataFormatValidationClassMap.put("T6", T6DataFormatValidation.class);
		dataFormatValidationClassMap.put("DT15", DT15DataFormatValidation.class);
		dataFormatValidationClassMap.put("T/F", TFDataFormatValidation.class);
	}
	
	/**
	 * 反射获取数据格式校验类
	 * @param dataFormatValue
	 * @return
	 * @author 陈阵 
	 * @date 2013-8-12 上午10:22:52
	 */
    public static DataFormatValidation getDataFormatValidation(String dataFormatValue) {
    	DataFormatValidation dataFormatValidationClass = null;
    	Class<? extends DataFormatValidation> dataFormatValidation = null;
    	if("DT15".equals(dataFormatValue)){
    		dataFormatValidation = dataFormatValidationClassMap.get("DT15");
    	}else if("T6".equals(dataFormatValue)){
    		dataFormatValidation = dataFormatValidationClassMap.get("T6");
    	}else if("D8".equals(dataFormatValue)){
    		dataFormatValidation = dataFormatValidationClassMap.get("D8");
    	}else if("N".equals(dataFormatValue.substring(0,1))){
    		  dataFormatValidation = dataFormatValidationClassMap.get("N");
    	}else if("AN".equals(dataFormatValue.substring(0,2))){
    		dataFormatValidation = dataFormatValidationClassMap.get("AN");
    	}else if("A".equals(dataFormatValue.substring(0,1))){
    		dataFormatValidation = dataFormatValidationClassMap.get("A");
    	}else if("T/F".equals(dataFormatValue)){
    		dataFormatValidation = dataFormatValidationClassMap.get("T/F");
    	}
    	
    	try {
    		if(dataFormatValidation != null){
    			dataFormatValidationClass = ConstructorUtils.invokeConstructor(dataFormatValidation);
    		}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataFormatValidationClass;
   	
    }
    

}

