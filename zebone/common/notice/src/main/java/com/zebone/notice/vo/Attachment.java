package com.zebone.notice.vo;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.zebone.util.CharacterUtil;

/**
 * 
 * 附件信息
 * 
 * @author lilin
 * @version [版本号, 2015年5月13日]
 */
public class Attachment {
    
    /**
     * 定义文件名显示的总长度
     */
    private static final int TOTAL_LENGTH = 16;
    
    /**
     * 主键ID
     */
    private String aId;
    
    /**
     * 外键ID
     */
    private String nId;
    
    /**
     * 附件名称
     */
    private String attachmentName;
    
    /**
     * 附件大小
     */
    private Long attachmentSize;
    
    /**
     * 附件图标名
     */
    private String attachmentImage;
    
    /**
     * 附件路径
     */
    private String attachmentPath;
    
    /**
     * 排序号
     */
    private Integer orderNumber;
    
    /**
     * 上传时间
     */
    private Date createTime;
    
    /**
     * 重写toString方法
     * 
     * @return String
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
    /**
     * 获取显示的文件大小
     *
     * @return 文件大小
     */
    public String getViewSize() {
        if (attachmentSize == null) {
            return null;
        }
            
        String size;
        double resultSize = attachmentSize;
        // KB
        if (resultSize / 1000 > 1) {
            BigDecimal bg = new BigDecimal(resultSize / 1000);
            resultSize = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            // MB
            if (resultSize / 1000 > 1) {
                bg = new BigDecimal(resultSize / 1000);
                resultSize = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                size = resultSize + "MB";
            } else {
                size = resultSize + "KB";
            }
        } else {
            size = resultSize + "B";
        }
        return size;
    }
    
    /**
     * 获取显示的名称
     *
     * @return 显示的名称
     */
    public String getViewName() {
        if (attachmentName == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        int index = attachmentName.lastIndexOf('.');
        
        String prefixName = attachmentName.substring(0, index);
        String suffix = attachmentName.substring(index);
        
        int strLength = suffix.length() + 2; // 加两个.的长度
        // 遍历文件名每一个字节
        for (int i = 0; i < prefixName.length(); i++) {
            char c = prefixName.charAt(i);
            
            if (CharacterUtil.isDoublet(c)) { // 双字节则加2
                if (strLength + 2 > TOTAL_LENGTH) {
                    strLength++;
                    builder.append('.');
                    break;
                }
                strLength += 2;
            } else { // 单字节则加1
                strLength++;
            }
            builder.append(c);
            if (strLength >= TOTAL_LENGTH) {
                break;
            }
        }
        if (strLength >= TOTAL_LENGTH) {
            builder.append("..");
        }
        builder.append(suffix);
        
        // 还是不够总长就拼空格
        while (strLength < TOTAL_LENGTH) {
            builder.append('　');
            strLength++;
            strLength++;
        }
        
        return builder.toString(); 
    }
    
    /**
     * 附件名称
     */
    public String getAttachmentName() {
        return this.attachmentName;
    }
    
    /**
     * 附件名称
     */
    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
    
    public String getAttachmentImage() {
        return attachmentImage;
    }
    
    public void setAttachmentImage(String attachmentImage) {
        this.attachmentImage = attachmentImage;
    }
    
    public Integer getOrderNumber() {
        return orderNumber;
    }
    
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    public String getAttachmentPath() {
        return attachmentPath;
    }
    
    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }
    
    public Long getAttachmentSize() {
        return attachmentSize;
    }
    
    public void setAttachmentSize(Long attachmentSize) {
        this.attachmentSize = attachmentSize;
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public String getnId() {
        return nId;
    }

    public void setnId(String nId) {
        this.nId = nId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}