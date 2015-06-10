package com.zebone.empi.delete.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 删除已推送的主索引信息服务实现类
 * @author 杨英
 * @version 2014-7-15 上午8:29
 */
@Service("deleteMsgService")
public class DeleteMsgServiceImpl implements DeleteMsgService {

    @Resource(name="jdbcTemplateCli")
    private JdbcTemplate jdbcTemplate;

    @Override
    public void deleteMsg() {
        jdbcTemplate.update("DELETE FROM EMPI_PULL_MSG WHERE PULL_STATUS = '1'");
    }
}
