package com.zebone.notice.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.zebone.notice.vo.Attachment;

/**
 * 
 * 附件操作业务接口
 * 
 * @author lilin
 * @version [版本号, 2015年5月14日]
 */
public interface AttachmentService {
    /**
     * 保存附件
     * 
     * @param attachment 附件信息
     * @param file 附件
     */
    void saveAttachment(Attachment attachment, MultipartFile file);
    
    /**
     * 重命名附件名
     * 
     * @param attachment 附件信息
     */
    
    void updateName(Attachment attachment);
    
    /**
     * 更新附件
     * 
     * @param attachment 附件信息
     */
    void updateAttachment(Attachment attachment);
    
    /**
     * 根据主键删除附件
     * 
     * @param aId 主键
     */
    void deleteAttachment(String aId);
    
    /**
     * 根据外键批量删除附件信息
     * 
     * @param nId 外键
     */
    void deleteAllAttachment(String nId);
    
    /**
     * 根据通知公告主键获取所有附件信息
     * 
     * @param nId 通知公告主键
     * @return 附件信息列表
     */
    List<Attachment> getAttachmentList(String nId);
    
    /**
     * 根据主键下载附件
     * 
     * @param aId 主键
     * @param response 响应对象
     * @throws IOException
     */
    void downloadAttachment(String aId, HttpServletResponse response)
        throws IOException;
}
