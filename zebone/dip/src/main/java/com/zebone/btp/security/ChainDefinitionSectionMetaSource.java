package com.zebone.btp.security;

import java.util.Map;

import org.apache.shiro.config.Ini;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;

/**
 * 负责初始化 shiro的权限设置。从数据库中装载权限列表。
 * @author 宋俊杰
 *
 */
public class ChainDefinitionSectionMetaSource implements FactoryBean<Map<String,String>>{
	    
	    private String filterChainDefinitions;
	    
	    /**
	     * 默认premission字符串
	     */
	    public static final String PREMISSION_STRING="roles[\"{0}\"]";
	    
	    //TODO 需要从数据库里面得到安全配置信息
	    public Map<String,String> getObject() throws BeansException {
	        
	        //获取所有Resource
//	        List list = resourceDao.getAll();
	        
	        Ini ini = new Ini();
	        //加载默认的url
	        ini.load(filterChainDefinitions);
	        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
	        //循环Resource的url,逐个添加到section中。section就是filterChainDefinitionMap,
	        //里面的键就是链接URL,值就是存在什么条件才能访问该链接
//	        for (Iterator it = list.iterator(); it.hasNext();) {
//	            
//	            Resource resource = it.next();
//	            //如果不为空值添加到section中
//	            if(StringUtils.isNotEmpty(resource.getValue()) && StringUtils.isNotEmpty(resource.getPermission())) {
//	                section.put(resource.getValue(),  MessageFormat.format(PREMISSION_STRING,resource.getPermission()));
//	            }
//	            
//	        }
	        section.put("/file/test/**", "roles[admin]");
	        for(Map.Entry<String, String> entry : section.entrySet()){
	        	System.out.println("================="+entry.getKey());
	        }
	        
	        return section;
	    }
	    
	    /**
	     * 通过filterChainDefinitions对默认的url过滤定义
	     * 
	     * @param filterChainDefinitions 默认的url过滤定义
	     */
	    public void setFilterChainDefinitions(String filterChainDefinitions) {
	        this.filterChainDefinitions = filterChainDefinitions;
	    }


	    @Override
	    public boolean isSingleton() {
	        return false;
	    }

		@Override
		public Class<?> getObjectType() {
			return this.getClass();
		}
}
