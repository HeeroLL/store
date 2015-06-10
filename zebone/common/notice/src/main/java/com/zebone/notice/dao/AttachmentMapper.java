package com.zebone.notice.dao;

import java.util.List;

import com.zebone.btp.core.mybatis.Mapper;
import com.zebone.notice.vo.Attachment;

/**
 * 
 * 附件Mapper
 * 
 * @author lilin
 * @version [版本号, 2015年5月13日]
 */
@Mapper
public interface AttachmentMapper {
    /**
     * 根据主键查询附件信息
     *
     * @param aId 主键
     * @return 附件信息
     */
    Attachment getAttachmentById(String aId);
    
    /**
     * 根据外键查询所有附件
     * 
     * @param nId 外键
     * @return 附件列表
     */
    List<Attachment> findAttachmentByNID(String nId);
    
    /**
     * 批量保存附件
     *
     * @param attachments 附件信息数组
     * @return 保存成功的条数
     */
    int saveAttachment(Attachment... attachments);
    
    /**
     * 更新附件
     *
     * @param attachment 附件信息
     */
    void updateAttachment(Attachment attachment);
    
    /**
     * 根据主键删除附件信息
     *
     * @param aId 主键
     * @return 删除成功的条数
     */
    int deleteAttachment(String aId);
    
    /**
     * 根据外键批量删除附件信息
     *
     * @param nId 外键
     * @return 删除成功的条数
     */
    int deleteAllAttachment(String nId);
    
    /**
     * 根据外键查询一批文件的总大小
     *
     * @param nId 外键
     * @return 文件总大小
     */
    Long getTotalSizeByNId(String nId);
}
