package com.zebone.dip.log.service;

import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.log.dao.DocLogMapper;
import com.zebone.dip.log.dao.DocViewLogMapper;
import com.zebone.dip.log.vo.DocViewLog;
import com.zebone.dip.log.vo.ParmInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文档调阅日志实现类
 *
 * @author 杨英
 * @version 2013-11-21 上午10:15
 */
@Service("docViewLogService")
public class DocViewLogServiceImpl implements DocViewLogService {

    @Resource
    private DocViewLogMapper docViewLogMapper;

    @Resource
    private DocLogMapper docLogMapper;

    @Override
    public Pagination<DocViewLog> getDocViewLogPage(DocViewLog docViewLog, Pagination<DocViewLog> page) {
        List<DocViewLog> data = docViewLogMapper.getDocViewLogList(docViewLog, page.getRowBounds());
        int totalCount = docViewLogMapper.getDocViewLogCount(docViewLog);
        Map<String, String> docInfoMap = getDocMapInfo();
        if (data != null && data.size() > 0) {
            for (DocViewLog viewLog : data) {
                //把结果集中的文档类型编码替换成文档名称
                viewLog.setDocType(docInfoMap.get(viewLog.getDocType()));
                //医生编码替换成医生名称
                viewLog.setDoctorCode(docLogMapper.getDoctorName(viewLog.getDoctorCode()));
                //机构编码替换成机构名称
                viewLog.setOrgCode(docLogMapper.getOrgName(viewLog.getOrgCode()));
                //格式化日期
                viewLog.setCreateTime(formatDate(viewLog.getCreateTime()));
                if("1".equals(viewLog.getViewState())){
                    viewLog.setViewState("成功");
                }else{
                    viewLog.setViewState("失败");
                }
            }
        }
        page.setData(data);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public List<ParmInfo> getCardInfo() {
        return docLogMapper.getCardInfo();
    }

    /**
     * 获取文档类型键值对信息,key为docTypeCode,value为docName
     * @return
     */
    public Map<String, String> getDocMapInfo() {
        Map<String, String> oMap = new HashMap<String, String>();
        List<ParmInfo> paramInfoLic = docLogMapper.getDictInfo();
        if (paramInfoLic != null) {
            for (ParmInfo parmInfo : paramInfoLic) {
                oMap.put(parmInfo.getCode(), parmInfo.getName());
            }
        }
        return oMap;
    }

    public String formatDate(String date) {
        String str = "";
        if (date != null && date.length() == 14) {
            str = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8)
                    + " " + date.substring(8, 10) + ":" + date.substring(10, 12) + ":" + date.substring(12, 14);
        }
        return str;
    }
}
