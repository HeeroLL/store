/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2012-12-5      记录审计日志的切面类
 */
package com.zebone.btp.log.aop;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import com.zebone.btp.log.LogConstant;
import com.zebone.btp.log.LogContext;
import com.zebone.btp.log.pojo.ErrorLog;
import com.zebone.btp.log.pojo.LogConfigInfo;
import com.zebone.btp.log.service.BtpLog;
import com.zebone.btp.log.service.ErrorLogService;
import com.zebone.util.ReflectUtil;
import com.zebone.util.UUIDUtil;

/**
 * 记录审计日志的切面类<br>
 * 该类需要重构，先实现功能
 * 
 * @author lilin
 * @version [版本号, 2012-12-5]
 */
public class LogAspect {
	
	private static final Log log = LogFactory.getLog(LogAspect.class);
	/**
	 * 日志配置初始化信息对象
	 */
	private LogContext logContext;
	
	/**
	 * 错误日志服务类
	 */
	private ErrorLogService errorLogService; 

	/**
	 * BTP日志接口
	 */
	private BtpLog btpLog;

	/**
	 * 记录审计日志
	 * 
	 * @param joinPoint
	 *            切入点，存放代理对象的相关信息
	 * @return obj
	 * @throws Throwable
	 *             Throwable
	 */
	public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
		// 获得参数
		Object[] args = joinPoint.getArgs();
		// 获得目标对象的类名
		String className = joinPoint.getTarget().getClass().getName();
		// 获得所执行的方法名
		String methodName = joinPoint.getSignature().getName();

		String key = className + '.' + methodName;

		// 获取配置信息
		LogConfigInfo logConfigInfo = logContext.getLogConfigInfo(key);

		// 为空则表示没有配置日志，则直接调用方法返回
		if (logConfigInfo == null) {
			return joinPoint.proceed(args);
		}

		// 无参的方法
		if (args == null || args.length == 0) {
			// 记录审计日志
			btpLog.log(key);
			return joinPoint.proceed();
		}

		// 取得需要记录的参数信息
		Object param = args[logConfigInfo.getParamIndex()];
		String serviceId = null;
		// 修改的情况先要取旧值
		Object oldValue = null;
		// 获取旧值
		if (StringUtils.isNotEmpty(logConfigInfo.getServiceId())
				&& StringUtils.isNotEmpty(logConfigInfo.getUpdateBeanId())
				&& StringUtils.isNotEmpty(logConfigInfo.getUpdateMethod())) {
			// 反射获取serviceId
			serviceId = (String) ReflectUtil.getFieldValue(param, logConfigInfo
					.getServiceId());

			// 获取spring的bean
			Object bean = logContext.getBean(logConfigInfo.getUpdateBeanId());
			// 反射获取oldValue，根据主键查询旧值
			oldValue = ReflectUtil.invokeMethod(
					logConfigInfo.getUpdateMethod(), bean, serviceId);
		}

		// 直接执行代理对象的方法
		Object result = joinPoint.proceed(args);

		// 删除的情况直接把参数记为serviceId
		if (LogConstant.LOG_OPFLAG_DELETE.equals(logConfigInfo.getOpFlag())) {
			serviceId = ArrayUtils.toString(param);
		} else if (StringUtils.isNotEmpty(logConfigInfo.getServiceId())) {
			// 反射获取serviceId
			serviceId = (String) ReflectUtil.getFieldValue(param, logConfigInfo
					.getServiceId());
		}

		// 记录审计日志
		btpLog.log(key, serviceId, param, oldValue);

		return result;
	}

	/**
	 * 拦截异常信息。对异常信息进行记录。
	 * @param jp
	 * @param ex
	 * @author 宋俊杰
	 * @date 2012-12-20
	 */
	public void interceptException(JoinPoint jp, Throwable ex) {

		String className = jp.getTarget().getClass().getName();
		String methodName = jp.getSignature().getName();
		int flag = className.indexOf("$");
		if (flag < 0) {
			String fullPath = className+methodName;
			log.info("业务处理时发生了异常:[" + fullPath + "]");
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			//将错误信息写入到StringWriter中。
			ex.printStackTrace(pw);
			String errorInfo = sw.toString();
			ErrorLog errorLog = new ErrorLog();
			String id = UUIDUtil.getUuid();
			errorLog.setErrorLogId(id);
			errorLog.setClassName(className);
			errorLog.setMethodName(methodName);
			errorLog.setCreateTime(new Date());
			errorLog.setErrorInfo(errorInfo);
			try {
				this.errorLogService.saveInThread(errorLog);
			} catch (Exception e) {
				log.error("",e);
			}
		}
	}

	public void setLogContext(LogContext logContext) {
		this.logContext = logContext;
	}

	public void setBtpLog(BtpLog btpLog) {
		this.btpLog = btpLog;
	}
	
	public void setErrorLogService(ErrorLogService errorLogService){
		this.errorLogService = errorLogService;
	}
}
