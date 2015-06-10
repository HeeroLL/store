package com.zebone.dnode.engine.clean;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;

import com.zebone.dnode.engine.clean.exception.CleanException;
import com.zebone.dnode.engine.clean.exception.ErrorUtil;

/**
 * 数据转化解析器
 * @author cz
 *
 */
public class DataConvertParse {

	private static Map<String,Class<? extends DataConvert>> classMap = new HashMap<String,Class<? extends DataConvert>>();
	private static Map<String,Class<?>[]> constructorMap = new HashMap<String,Class<?>[]>();

	
    private static final String idx = "_";
	
	static{
		classMap.put("S", SConvert.class);
		classMap.put("DATE", DateConvert.class);
		constructorMap.put("S", new Class<?>[]{int.class});
	}
	
	
    public DataConvert parse(String regx) throws CleanException{
    	DataConvert dc = null;
    	if(regx.indexOf(idx) != -1){ 
    		String[] regs = regx.split(idx);
    		if(regs.length >= 1){
    			String[] args = Arrays.copyOfRange(regs, 1, regs.length);
    			if(regs[0] != null){
    				Object[] oargs = new Object[args.length];
    				String key = regs[0].toUpperCase();
    				Class<? extends DataConvert> clz = classMap.get(key);
    				Class<?>[] ctcons = constructorMap.get(key);
    				if(ctcons != null && ctcons.length > 0){
        				oargs = ConvertArgs(args,ctcons);
    				}
        			try {
						dc = ConstructorUtils.invokeConstructor(clz, oargs);
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						throw new CleanException(ErrorUtil.DATA_CONVERT_ERROR, e);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						throw new CleanException(ErrorUtil.DATA_CONVERT_ERROR, e);
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						throw new CleanException(ErrorUtil.DATA_CONVERT_ERROR, e);
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						throw new CleanException(ErrorUtil.DATA_CONVERT_ERROR, e);
					}
    			} 			
    		}
    	}else{
			Class<? extends DataConvert> clz = classMap.get(regx);
			try {
				dc = ConstructorUtils.invokeConstructor(clz);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				throw new CleanException(ErrorUtil.DATA_CONVERT_ERROR, e);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				throw new CleanException(ErrorUtil.DATA_CONVERT_ERROR, e);
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				throw new CleanException(ErrorUtil.DATA_CONVERT_ERROR, e);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				throw new CleanException(ErrorUtil.DATA_CONVERT_ERROR, e);
			}
    	}
    	return dc;
    	
    }
    
	private Object[] ConvertArgs(String[] args, Class<?>[] cls) {
		Object[] nObject = new Object[args.length];
		for (int i = 0; i < cls.length; i++) {
			String className = ClassUtils.getShortClassName(cls[i]);
			if(ClassName.INT.equals(className)){
				nObject[i] = Integer.parseInt(args[i]);
			}else{
				nObject[i] = new String(args[i]);
			}
		}
		return nObject;
	}

    
	private static class ClassName{
		public static final String INT = "int";
	}

}
