package com.zebone.empi.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.zebone.empi.vo.EmpiMatchLog;
import com.zebone.empi.vo.ResidentCard;
import com.zebone.empi.vo.ResidentInfo;

/**
 * 映射类，用于通过field的name操作类字段
 * @author YinCm
 * @version 2013-7-31 下午10:10:20
 */
public class ObjectReflect {
	
	/**
	 * 将ResidentInfo转换为EmpiMatchLog
	 * @param ri
	 * @return
	 */
	public static EmpiMatchLog convertResidentInfoToEmpiMatchLog(ResidentInfo ri){
		
		EmpiMatchLog eml = new EmpiMatchLog();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.format(date);
		eml.setMatchTime(sdf.format(date));
		eml.setName(ri.getName());		
		eml.setSystemCode(ri.getSystem_code());
		
		List<ResidentCard> cardLic = ri.getCardList();
		if (cardLic != null && cardLic.size() > 0) {
			ResidentCard card = cardLic.get(0);
			eml.setCardNo(card.getCardNo());
			eml.setCardOrg(card.getCardOrg());
			eml.setCardType(card.getCardType());
		}
		
		return eml;
	}
	
	/**
	 * 用newObj中的值覆盖origin中值为"null"或为空的属性
	 * @param origin
	 * @param newObj
	 */
	public static void updateEmpty(Object origin, Object newObj){
		Field[] fields = origin.getClass().getDeclaredFields();
		try{
			for(int i=0; i<fields.length; i++){
				//Get the value of origin
				Object object = runGetter(fields[i], origin);
				if(object==null || object.toString().equals("")){
					Object value = runGetter(fields[i], newObj);
					if(value==null || value.toString().equals("")){
						continue;
					}
					System.out.println(origin+"---"+value);
					runSetter(fields[i], origin, value);
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 将父类实例中的变量值赋给子类实例
	 * @param parent
	 * @param child
	 */
	public static void copyPrarentToChild(Object parent, Object child){
		Field[] fields = parent.getClass().getDeclaredFields();
		try{
			for(int i=0; i<fields.length; i++){
				Object object = runGetter(fields[i], parent);
				runSetter(fields[i], child, object);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取o中的field属性值
	 * @param field
	 * @param the object you want get the field from.
	 * @return
	 */
	public static Object runGetter(Field field, Object o)
	{
	    for (Method method : o.getClass().getMethods())
	    {
	        if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3)))
	        {
	            if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase()))
	            {
	                try
	                {
	                    return method.invoke(o);
	                }
	                catch (IllegalAccessException e)
	                {
	                    System.out.println("Could not determine method: " + method.getName());
	                }
	                catch (InvocationTargetException e)
	                {
	                	System.out.println("Could not determine method: " + method.getName());
	                }
	            }
	        }
	    }
	    return null;
	}
	
	/**
	 *将value赋值给o中的field属性
	 * @param field
	 * @param The object you want get the field from.
	 * @param The value set to the field
	 * @return
	 */
	public static Object runSetter(Field field, Object o, Object value)
	{
	    for (Method method : o.getClass().getMethods())
	    {
	        if ((method.getName().startsWith("set")) && (method.getName().length() == (field.getName().length() + 3)))
	        {
	            if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase()))
	            {
	                try
	                {
	                    return method.invoke(o,value);
	                }
	                catch (IllegalAccessException e)
	                {
	                    System.out.println("Could not determine method: " + method.getName());
	                }
	                catch (InvocationTargetException e)
	                {
	                	System.out.println("Could not determine method: " + method.getName());
	                }

	            }
	        }
	    }
	    return null;
	}
}
