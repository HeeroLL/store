package com.zebone.dnode;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zebone.dnode.engine.analyze.DocumentAnalyzeJobStarter;
import com.zebone.dnode.engine.auditlog.AuditlogJobStarter;
import com.zebone.dnode.engine.empi.EmpiNoticeJobStarter;
import com.zebone.dnode.engine.empi.EmpiPushJobStarter;
import com.zebone.dnode.engine.notice.NoticeJobStarter;
import com.zebone.dnode.engine.preprocess.PreprocessJobStarter;
import com.zebone.dnode.engine.register.RegisterTaskInit;
import com.zebone.dnode.engine.store.StorageTaskInit;
import com.zebone.dnode.engine.validation.ValidationTaskInit;

/**
 * 启动入口
 * 
 * @author songjunjie
 * @version 2013-8-17 上午09:39:23
 */
public class Main {
    
    private static ClassPathXmlApplicationContext ctx;

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        if (args == null || args.length == 0) {
            System.out.println("请输入执行参数");
            System.out.println("格式   java -Xmx800m -jar dnode.jar store register validation analyze preprocess empinotice");
            System.exit(0);
        }
        boolean initStore = false;
        boolean initValidation = false;
        boolean initRegister = false;
        boolean initAnalyze = false;
        boolean initPreprocess = false;
        boolean initEmpinotice = false;
        boolean initEmpiPush = false;
        boolean initNotice = false;
        boolean initAuditlog = false;
        
        String[] springXmlFile = new String[args.length];
        for (int i = 0; i < springXmlFile.length; i++) {
            if ("validation".equals(args[i])) {
                springXmlFile[i] = "classpath:/com/zebone/dnode/engine/validation/config/ApplicationContext.xml";
                initValidation = true;
            } else if ("store".equals(args[i])) {
                springXmlFile[i] = "classpath:com/zebone/dnode/engine/store/config/ApplicationContext.xml";
                initStore = true;
            } else if ("register".equals(args[i])) {
                springXmlFile[i] = "classpath:com/zebone/dnode/engine/register/config/ApplicationContext.xml";
                initRegister = true;
            } else if ("analyze".equals(args[i])) {
                springXmlFile[i] = "classpath:com/zebone/dnode/engine/analyze/config/ApplicationContext.xml";
                initAnalyze = true;
            } else if ("preprocess".equals(args[i])) {
                springXmlFile[i] = "classpath:com/zebone/dnode/engine/preprocess/config/ApplicationContext.xml";
                initPreprocess = true;
            } else if ("empinotice".equals(args[i])) {
                springXmlFile[i] = "classpath:com/zebone/dnode/engine/empi/config/ApplicationContext.xml";
                initEmpinotice = true;
            } else if ("empipush".equals(args[i])) {
                springXmlFile[i] = "classpath:com/zebone/dnode/engine/empi/config/ApplicationContext.xml";
                initEmpiPush = true;
            } else if ("notice".equals(args[i])) {
                springXmlFile[i] = "classpath:com/zebone/dnode/engine/notice/config/ApplicationContext.xml";
                initNotice = true;
            } else if ("auditlog".equals(args[i])) {
                springXmlFile[i] = "classpath:com/zebone/dnode/engine/auditlog/config/ApplicationContext.xml";
                initAuditlog = true;
            }
        }
        try {
            ctx = new ClassPathXmlApplicationContext(springXmlFile);
            if (initValidation) {
                ValidationTaskInit validationTaskInit = ctx.getBean("validationTaskInit", ValidationTaskInit.class);
                validationTaskInit.init();
            }
            
            if (initStore) {
                StorageTaskInit storageTaskInit = ctx.getBean("storageTaskInit", StorageTaskInit.class);
                storageTaskInit.init();
                
            }
            
            if (initRegister) {
                RegisterTaskInit registerTaskInit = ctx.getBean("registerTaskInit", RegisterTaskInit.class);
                registerTaskInit.init();
            }
            
            if (initAnalyze) {
                DocumentAnalyzeJobStarter documentAnalyzeJobStarter =
                    ctx.getBean("documentAnalyzeJobStarter", DocumentAnalyzeJobStarter.class);
                documentAnalyzeJobStarter.startJob();
            }
            
            if (initPreprocess) {
                PreprocessJobStarter preprocessJobStarter =
                    ctx.getBean("preprocessJobStarter", PreprocessJobStarter.class);
                preprocessJobStarter.startJob();
            }
            
            if (initEmpinotice) {
                EmpiNoticeJobStarter empiNoticeJobStarter =
                    ctx.getBean("empiNoticeJobStarter", EmpiNoticeJobStarter.class);
                empiNoticeJobStarter.startJob();
            }
            
            if (initEmpiPush) {
                EmpiPushJobStarter empiPushJobStarter = ctx.getBean("empiPushJobStarter", EmpiPushJobStarter.class);
                empiPushJobStarter.startJob();
            }
            
            if (initNotice) {
                NoticeJobStarter noticeJobStarter = ctx.getBean("noticeJobStarter", NoticeJobStarter.class);
                noticeJobStarter.startJob();
            }
            
            if (initAuditlog) {
                AuditlogJobStarter auditlogJobStarter = ctx.getBean("auditlogJobStarter", AuditlogJobStarter.class);
                auditlogJobStarter.startJob();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
