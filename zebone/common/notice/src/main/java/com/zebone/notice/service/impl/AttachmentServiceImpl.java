package com.zebone.notice.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zebone.notice.dao.AttachmentMapper;
import com.zebone.notice.service.AttachmentService;
import com.zebone.notice.vo.Attachment;
import com.zebone.util.PropertyConfigurer;
import com.zebone.util.UUIDUtil;

/**
 * 
 * 附件操作业务接口实现类
 * 
 * @author lilin
 * @version [版本号, 2015年5月14日]
 */
@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {
    /**
     * log4j
     */
    private static final Logger log = Logger.getLogger(AttachmentServiceImpl.class);
    
    /**
     * 注入文件根目录路径
     */
    @Value("${fileupload.rootPath}")
    private String rootPath;
    
    /**
     * 注入文件上传最大大小
     */
    @Value("${fileupload.limitsize}")
    private long limitsize;
    
    /**
     * 读取properties文件配置类
     */
    @Resource
    private PropertyConfigurer propertyConfigurer;
    
    /**
     * 附件信息操作Mapper
     */
    @Resource
    private AttachmentMapper attachmentMapper;
    
    /**
     * 保存附件
     * 
     * @param attachment 附件信息
     * @param file 附件
     */
    @Override
    public void saveAttachment(Attachment attachment, MultipartFile file) {
        if (file == null || file.getOriginalFilename() == null) {
            log.error("Upload file is null.");
            throw new RuntimeException("uploadfile.null");
        }
        
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1);
        attachment.setAttachmentName(file.getOriginalFilename());
        attachment.setAttachmentImage(propertyConfigurer.getProperty("file." + suffix.toLowerCase()));
        // 校验文件格式
        if (StringUtils.isEmpty(attachment.getAttachmentImage())) {
            log.error("File format error. File format is " + suffix);
            throw new RuntimeException("file.format.error");
        }
        attachment.setAttachmentSize(file.getSize());
        
        if (StringUtils.isEmpty(attachment.getnId())) {
            attachment.setnId(UUIDUtil.getUuid());
        } else {
            // 计算文件总大小不能超过配置
            Long totalSize = attachmentMapper.getTotalSizeByNId(attachment.getnId());
            if (totalSize == null) {
                totalSize = 0L;
            }
            totalSize += file.getSize();
            if (totalSize > limitsize) {
                log.error("File size is out of range. The total size is " + totalSize);
                throw new RuntimeException("file.maxsize.outofrange");
            }
        }
        
        attachment.setaId(UUIDUtil.getUuid()); // 生成主键
        attachment.setAttachmentPath(rootPath + File.separator + attachment.getnId() + File.separator);
        attachment.setCreateTime(new Date());
        // 保存元数据
        attachmentMapper.saveAttachment(attachment);
        
        // 保存实体文件
        File attachmentFile = new File(getFileName(attachment));
        attachmentFile.mkdirs();
        try {
            file.transferTo(attachmentFile);
        } catch (IOException e) {
            log.error("Transfer file failed.", e);
            throw new RuntimeException("transfer.file.failed", e);
        }
        
    }
    
    /**
     * 重命名附件名
     * 
     * @param attachment 附件信息
     */
    @Override
    public void updateName(Attachment attachment) {
        Attachment attachmentDb = attachmentMapper.getAttachmentById(attachment.getaId());
        
        if (attachmentDb == null) {
            log.error("file.not.exists");
            throw new RuntimeException("file.not.exists");
        }
        
        int index = attachmentDb.getAttachmentName().lastIndexOf('.');
        String suffix = attachmentDb.getAttachmentName().substring(index);
        attachment.setAttachmentName(attachment.getAttachmentName() + suffix);
        
        // 更新元数据
        updateAttachment(attachment);
        
        // 文件重命名
        File file = new File(getFileName(attachmentDb));
        if (file != null && file.exists()) {
            attachment.setAttachmentPath(attachmentDb.getAttachmentPath());
            attachment.setAttachmentSize(attachmentDb.getAttachmentSize());
            attachment.setOrderNumber(attachmentDb.getOrderNumber());
            // 更新实体文件
            file.renameTo(new File(getFileName(attachment)));
        }
    }
    
    /**
     * 更新附件
     * 
     * @param attachment 附件信息
     */
    @Override
    public void updateAttachment(Attachment attachment) {
        
        attachmentMapper.updateAttachment(attachment);
    }
    
    /**
     * 根据主键删除附件
     * 
     * @param aId 主键
     */
    @Override
    public void deleteAttachment(String aId) {
        Attachment attachment = attachmentMapper.getAttachmentById(aId);
        if (attachment == null) {
            return;
        }
        // 删除元数据
        attachmentMapper.deleteAttachment(aId);
        // 删除实体文件
        File file = new File(getFileName(attachment));
        if (file.exists()) {
            file.delete();
        }
    }
    
    /**
     * 根据外键批量删除附件信息
     * 
     * @param nId 外键
     */
    @Override
    public void deleteAllAttachment(String nId) {
        // 批量删除元数据信息
        attachmentMapper.deleteAllAttachment(nId);
        // 批量删除实体文件
        File file = new File(rootPath + File.separator + nId);
        if (!file.exists()) {
            return;
        }
        try {
            FileUtils.deleteDirectory(file);
        } catch (IOException e) {
            log.error("Delete file failed.", e);
            throw new RuntimeException("delete.file.failed", e);
        }
    }
    
    /**
     * 根据通知公告主键获取所有附件信息
     * 
     * @param nId 通知公告主键
     * @return 附件信息列表
     */
    @Override
    public List<Attachment> getAttachmentList(String nId) {
        return attachmentMapper.findAttachmentByNID(nId);
    }
    
    /**
     * 根据主键下载附件
     * 
     * @param aId 主键
     * @param response 响应对象
     * @throws IOException
     */
    @Override
    public void downloadAttachment(String aId, HttpServletResponse response)
        throws IOException {
        Attachment attachment = attachmentMapper.getAttachmentById(aId);
        if (attachment == null) {
            return;
        }
        File file = new File(getFileName(attachment));
        
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/x-msdownload;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment; filename="
            + new String(attachment.getAttachmentName().getBytes("GBK"), "iso8859-1"));
        response.getOutputStream().write(FileUtils.readFileToByteArray(file));
    }
    
    private String getFileName(Attachment attachment) {
        return attachment.getAttachmentPath() + attachment.getOrderNumber() + '_' + attachment.getAttachmentName();
    }
}
