package com.zebone.btp.cache;

import java.io.IOException;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 对方法进行缓存控制的切面.对所关注的方法的返回结果进行缓存管理.
 * 
 * @author 宋俊杰
 * 
 */
public class MethodCacheAspect {
	private static final Logger logger = Logger.getLogger(MethodCacheAspect.class);
	private Cache cache;
	private static ObjectMapper objectMapper = new ObjectMapper();

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public Object findFromCache(ProceedingJoinPoint joinPoint) throws Throwable {
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		String cacheKey = getCacheKey(className, methodName, arguments);
		logger.debug("方法 " + className + "." + methodName + " 被缓存");
		logger.debug("从缓存[" + cache.getName() + "]中查找对象(cacheKey:" + cacheKey + ")");
		Element element = cache.get(cacheKey);
		if (null == element) {
			logger.debug("没有从缓存中找到对象 , 将方法的返回结果放到缓存中!");
			Object returnObject = joinPoint.proceed();
			element = new Element(cacheKey, returnObject);
			cache.put(element);
		}
		return element.getValue();
	}

	/**
	 * 刷新缓存。当调用某个service的“写”方法时，就会刷新这个service中的所有方法的缓存。
	 * 关于“写”方法有哪些，在ApplicationContext-cache 中配置。
	 * @param joinPoint
	 */
	@SuppressWarnings("unchecked")
	public void flushCache(JoinPoint joinPoint) {
		String className = joinPoint.getTarget().getClass().getName();
		List<String> list = cache.getKeys();
		for (String key : list) {
			if (key.startsWith(className)) {
				cache.remove(key);
				logger.debug("清楚缓存 Key=" + key);
			}
		}
	}

	/**
	 * 根据类的名字（包括包名）、方法名字、和参数生成cacheKey。 类名.方法名.参数json字符串...
	 * 
	 * @param className
	 * @param methodName
	 * @param arguments
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private String getCacheKey(String className, String methodName, Object[] arguments) throws JsonGenerationException,
			JsonMappingException, IOException {
		StringBuilder sb = new StringBuilder();
		sb.append(className).append(".").append(methodName);
		for (Object o : arguments) {
			// 将参数转换成json字符串。
			String json = objectMapper.writeValueAsString(o);
			sb.append(".").append(json);
		}
		return sb.toString();
	}
}
