package com.zebone.dip.ws.resource.check;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zebone.check.Vcheck;
import com.zebone.dip.ws.resource.check.component.DeptCheck;
import com.zebone.dip.ws.resource.check.component.UserCheck;

public class ResourceVcheck extends Vcheck{
	
	public ResourceVcheck(){
		Map<String,MethodInfo> map = new HashMap<String,MethodInfo>() ;
		//人员
		List<Class<? extends Object>> userParaClass = new ArrayList<Class<? extends Object>>();
		userParaClass.add(Connection.class);
		userParaClass.add(String.class);
		userParaClass.add(String.class);
		map.put("user", new MethodInfo(new UserCheck(), "checkUserData", userParaClass));
		
		//科室
		List<Class<? extends Object>> deptParaClass = new ArrayList<Class<? extends Object>>();
		deptParaClass.add(Connection.class);
		deptParaClass.add(String.class);
		deptParaClass.add(String.class);
		map.put("dept", new MethodInfo(new DeptCheck(), "checkDeptData", deptParaClass));
		super.setCheckClassMap(map);
	}    

}
