package com.zebone.btp.core.log;

import org.apache.log4j.Level;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

/**
 * log4j在写日志到数据库的时候，对于mybatis的输出日志进行过滤。在debug模式下，
 * 只需要输出sql语句，参数还有连接数据库的信息
 * @author songjunjie
 * @version 2013-8-27 下午04:13:48
 */
public class DBLogFilter extends Filter {

	@Override
	public int decide(LoggingEvent event) {
		if(event.getLevel() != Level.DEBUG){
			return Filter.ACCEPT;
		}
		
		if(event.getLocationInformation().getClassName().equals("org.apache.ibatis.logging.slf4j.Slf4jImpl")){
			String msg = event.getRenderedMessage();
			if(msg!=null && msg.indexOf("==>  Preparing")>-1){
				return Filter.ACCEPT;
			}
			if(msg!=null && msg.indexOf("==> Parameters")>-1){
				return Filter.ACCEPT;
			}
			if(msg!=null && msg.indexOf("ooo Using Connection")>-1){
				return Filter.ACCEPT;
			}
		}
		return Filter.DENY;
	}

}
