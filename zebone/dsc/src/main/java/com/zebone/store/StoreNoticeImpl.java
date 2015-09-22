package com.zebone.store;

import com.zebone.store.dao.DocStorageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 存储通知实现
 *
 * @author 杨英
 * @version 2013-10-23 上午09:12
 */

@Service("StoreNoticeImpl")
public class StoreNoticeImpl implements StoreNotice {

    @Resource
    private DocStorageMapper docStorageMapper;

    @Override
    public int DocumentRepository_RegisterStateUpdate(String docNo) {
        int result = 0;
        if (docNo != null && !"".equals(docNo)) {
            result = docStorageMapper.updateRegisterStateByDocNo(docNo);
        }
        return result;
    }
}
