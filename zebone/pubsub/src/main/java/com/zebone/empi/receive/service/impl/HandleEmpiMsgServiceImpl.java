package com.zebone.empi.receive.service.impl;

import com.zebone.empi.receive.service.HandleEmpiMsgService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.zebone.util.UUIDUtil;

/**
 * 处理主索引推送信息实现类
 * @author 杨英
 * @version 2014-7-14 下午2:16
 */
@Service("handleEmpiMsgService")
public class HandleEmpiMsgServiceImpl implements HandleEmpiMsgService{

    @Resource(name="jdbcTemplateCli")
    private JdbcTemplate jdbcTemplate;

    @Override
    public void handleEmpiMsg(String msg) {
        EmpiMsgObj empiMsgObj = new EmpiMsgObj();
        empiMsgObj.setId(UUIDUtil.getUuid());
        empiMsgObj.setPullContent(msg);
        jdbcTemplate.update("INSERT INTO EMPI_PULL_MSG(ID_, PULL_CONTENT) VALUES(?,?)",new EmpiMsgObjPSS(empiMsgObj));
    }


    private static class EmpiMsgObj{
        private String id;
        private String pullContent;
        private String queueName;

        private String getId() {
            return id;
        }

        private void setId(String id) {
            this.id = id;
        }

        private String getPullContent() {
            return pullContent;
        }

        private void setPullContent(String pullContent) {
            this.pullContent = pullContent;
        }

        private String getQueueName() {
            return queueName;
        }

        private void setQueueName(String queueName) {
            this.queueName = queueName;
        }
    }

    private final class EmpiMsgObjPSS implements PreparedStatementSetter {

        private EmpiMsgObj empiMsgObj;

        public EmpiMsgObjPSS(EmpiMsgObj empiMsgObj) {
            super();
            this.empiMsgObj = empiMsgObj;
        }

        @Override
        public void setValues(PreparedStatement paramPreparedStatement) throws SQLException {
            paramPreparedStatement.setString(1,empiMsgObj.getId());
            paramPreparedStatement.setString(2,empiMsgObj.getPullContent());
        }
    }

}
