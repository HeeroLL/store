package com.zebone.empi.delete;

import com.zebone.empi.delete.service.DeleteMsgInitService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 杨英
 * @version 2014-7-15 上午8:20
 */
public class DeleteMsgRun {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext cpxa = new ClassPathXmlApplicationContext("classpath:DeApplicationContext.xml");
        DeleteMsgInitService deleteMsgInitService = cpxa.getBean("deleteMsgInitService", DeleteMsgInitService.class);
        deleteMsgInitService.init();
    }
}
