package com.zebone.notice.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import com.zebone.btp.core.mybatis.page.PageConfig;
import com.zebone.btp.core.mybatis.page.PageInfo;
import com.zebone.notice.service.AttachmentService;
import com.zebone.notice.service.PublicNoticeService;
import com.zebone.notice.dao.PublicNoticeMapper;
import com.zebone.notice.vo.PublicNotice;
import com.zebone.util.GeneralDef;
import com.zebone.util.UUIDUtil;

/**
 * 
 * 公告服务接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年5月11日]
 */
@Service("publicNoticeService")
public class PublicNoticeServiceImpl implements PublicNoticeService {
    
    /**
     * 注入publicNoticeMapper
     */
    @Resource
    private PublicNoticeMapper publicNoticeMapper;
    
    /**
     * 附件操作业务接口
     */
    @Resource
    private AttachmentService attachmentService;
    
    /**
     * 保存或修改公告信息
     * 
     * @param publicNotice 公告信息
     */
    @Override
    public void saveOrUpdatePublicNotice(PublicNotice publicNotice) {
        publicNotice.setPublishTime(new Date());
        // 计算失效日期
        if (publicNotice.getEffectiveTime() != null) {
            Date expiryDate = DateUtils.addMonths(publicNotice.getPublishTime(), publicNotice.getEffectiveTime());
            publicNotice.setExpiryDate(DateFormatUtils.format(expiryDate, "yyyyMMdd"));
        }
        if (!StringUtils.isEmpty(publicNotice.getnId())) {
            // 从数据库查询是否存在数据
            if (publicNoticeMapper.getPublicNoticeById(publicNotice.getnId()) != null) {
                publicNoticeMapper.updatePublicNotice(publicNotice);
                return;
            }
        } else {
            // 生成主键
            publicNotice.setnId(UUIDUtil.getUuid());
        }
        publicNotice.setDelFlag(GeneralDef.SIGN_0);
        publicNoticeMapper.savePublicNotice(publicNotice);
    }
    
    /**
     * 删除公告信息
     * 
     * @param nId 公告id
     */
    @Override
    public void deletePublicNotice(String nId) {
        publicNoticeMapper.deletePublicNotice(nId);
        // 批量删除附件及文件
        attachmentService.deleteAllAttachment(nId);
    }
    
    /**
     * 查询公告信息
     * 
     * @param page 分页信息
     * @param publicNotice 查询条件
     * @return 分页后的公告信息
     */
    @Override
    public PageInfo<PublicNotice> findPublicNoticeList(PageConfig page, PublicNotice publicNotice) {
        PageInfo<PublicNotice> pageInfo = new PageInfo<PublicNotice>(page.getPage(), page.getRows());
        pageInfo.setRows(publicNoticeMapper.findPublicNoticeList(pageInfo.getRowBounds(), publicNotice));
        pageInfo.setTotal(publicNoticeMapper.getTotalCount(publicNotice));
        
        return pageInfo;
    }
    
    /**
     * 根据主键查询公告信息
     * 
     * @param nId 公告主键
     * @return 公告信息
     */
    @Override
    public PublicNotice getPublicNoticeById(String nId) {
        return publicNoticeMapper.getPublicNoticeById(nId);
    }
}
