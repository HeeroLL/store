/**
 * ==================   =============   ============   =================================
 * Author               OperateType     Date           Description
 * ==================   =============   ============   =================================
 * lilin                New             2012-12-11     审计日志队列
 */
package com.zebone.btp.log.thread;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.zebone.btp.log.dao.LogMapper;
import com.zebone.btp.log.pojo.AuditLogInfo;
import com.zebone.btp.log.pojo.AuditLogInfoExt;

/**
 *
 * 审计日志队列<br>
 * 该类实现生产者消费者模式，每当需要记录日志，新建生产者实例对象，把日志放入队列中<br>
 * 通过一个已经实例化好的消费者实例真正去记录日志
 *
 * @author lilin
 * @version [版本号, 2012-12-11]
 */
public class LogQueue
{
    /**
     * log4j日志
     */
    private static final Logger log = Logger.getLogger(LogQueue.class);

    /**
     * 阻塞性队列，内部实现线程同步
     */
    private LinkedBlockingQueue<AuditLogInfo> queue;

    /**
     * 操作数据库的mapper对象
     */
    @Autowired
    private LogMapper mapper;

    /**
     * 实例化的时候即启动消费者线程
     */
    public LogQueue()
    {

    }

    /**
     * 把日志存放到队列中
     *
     * @param auditLogInfo 日志对象
     */
    public void putLog(AuditLogInfo auditLogInfo)
    {
        // 启生产者线程，存放日志对象
        new Producer(auditLogInfo).start();
    }

    /**
     * 初始化消费者线程
     */
    public void init()
    {
        new Consumer().start();
    }

    /**
     * 生产者线程，负责存放日志对象
     */
    class Producer extends Thread
    {
        /**
         * 需要存放的日志对象
         */
        private AuditLogInfo auditLogInfo;

        /**
         * 默认构造方法
         *
         * @param auditLogInfo 日志对象
         */
        Producer(AuditLogInfo auditLogInfo)
        {
            this.auditLogInfo = auditLogInfo;
        }

        public void run()
        {
            try
            {
                queue.put(auditLogInfo);
            }
            catch (InterruptedException e)
            {
                log.error("Producer_log Thread Interrupted.", e);
            }
        }
    }

    /**
     * 消费者线程，负责记录日志
     */
    class Consumer extends Thread
    {
        public void run()
        {
            while (true)
            {
                try
                {
                    AuditLogInfo auditLogInfo = queue.take();

                    // 保存主表
                    mapper.saveLog(auditLogInfo);

                    if (!CollectionUtils.isEmpty(auditLogInfo.getAuditLogInfoExtList()))
                    {
                        for (AuditLogInfoExt auditLogInfoExt : auditLogInfo.getAuditLogInfoExtList())
                        {
                            // 保存从表
                            mapper.saveLogExt(auditLogInfoExt);
                        }
                    }
                }
                catch (InterruptedException e)
                {
                    log.error("Consumer_log Thread Interrupted.", e);
                }
                // 捕获所有异常确保线程的有效性
                catch (Exception e)
                {
                    log.error(e);
                }
            }
        }
    }

    public void setQueue(LinkedBlockingQueue<AuditLogInfo> queue)
    {
        this.queue = queue;
    }

}
