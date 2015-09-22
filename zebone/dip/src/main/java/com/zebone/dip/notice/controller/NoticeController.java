package com.zebone.dip.notice.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zebone.btp.core.mybatis.page.PageConfig;
import com.zebone.btp.core.mybatis.page.PageInfo;
import com.zebone.dip.medicalstaff.service.MedicalStaffService;
import com.zebone.notice.service.AttachmentService;
import com.zebone.notice.service.PublicNoticeService;
import com.zebone.notice.vo.Attachment;
import com.zebone.notice.vo.PublicNotice;

/**
 * 
 * 通知、公告模块控制层
 * 
 * @author lilin
 * @version [版本号, 2015年5月11日]
 */
@Controller
@RequestMapping("notice")
public class NoticeController {
    
    /**
     * 公告服务接口
     */
    @Resource
    private PublicNoticeService publicNoticeService;
    
    /**
     * 附件操作业务接口
     */
    @Resource
    private AttachmentService attachmentService;
    
    /**
     * 医疗人员业务接口
     */
    @Resource
    private MedicalStaffService medicalStaffService;
    
    /************************************************消息管理begin***************************************************************/
    
    /**
     * 辅助功能-消息管理主页
     * 
     * @return URL
     */
    @RequestMapping("/noticeMain")
    public String noticeMain() {
        return "dip/notice/noticeMain";
    }
    
    /**
     * 公告信息管理主页
     * 
     * @return URL
     */
    @RequestMapping("/publicNoticeIndex")
    public String publicNoticeIndex() {
        return "dip/notice/publicNoticeIndex";
    }
    
    /**
     * 公告编辑界面
     * 
     * @param nId 公告主键
     * @param map 返回值
     * @return URL
     */
    @RequestMapping("/publicNoticeEdit")
    public String publicNoticeEdit(String nId, ModelMap map) {
        if (!StringUtils.isEmpty(nId)) {
            map.put("publicNotice", publicNoticeService.getPublicNoticeById(nId));
            map.put("attachmentList", attachmentService.getAttachmentList(nId));
        }
        
        return "dip/notice/publicNoticeEdit";
    }
    
    /**
     * 公告查看界面
     * 
     * @param nId 公告主键
     * @param map 返回值
     * @return URL
     */
    @RequestMapping("/publicNoticeView")
    public String publicNoticeView(String nId, ModelMap map) {
        if (!StringUtils.isEmpty(nId)) {
            PublicNotice publicNotice = publicNoticeService.getPublicNoticeById(nId);
            // 设置医疗人员姓名
            if (publicNotice != null) {
                publicNotice.setPublishPersonName(medicalStaffService.getMedicalStaffNameByid(publicNotice.getPublishPersonId()));
            }
            map.put("publicNotice", publicNotice);
            map.put("attachmentList", attachmentService.getAttachmentList(nId));
        }
        
        return "dip/notice/publicNoticeView";
    }
    
    /**
     * 获取公告表格数据
     * 
     * @param publicNotice 查询条件
     * @param page 分页参数
     * @return pageInfo
     */
    @RequestMapping("/getPublicNoticeDataGrid")
    @ResponseBody
    public PageInfo<PublicNotice> getPublicNoticeDataGrid(PublicNotice publicNotice, PageConfig page) {
        PageInfo<PublicNotice> noticePage = publicNoticeService.findPublicNoticeList(page, publicNotice);
        if (noticePage != null && noticePage.getRows() != null) {
            for (PublicNotice notice : noticePage.getRows()) {
                notice.setPublishPersonName(medicalStaffService.getMedicalStaffNameByid(notice.getPublishPersonId()));
            }
        }
        return noticePage;
    }
    
    /**
     * 保存或更新公告信息
     * 
     * @param publicNotice 公告信息
     * @return 结果
     */
    @RequestMapping("/saveOrUpdatePublicNotice")
    @ResponseBody
    public Map<String, Object> saveOrUpdatePublicNotice(PublicNotice publicNotice) {
        // TODO: 还需设置当前发布人的机构码和人员ID
        publicNotice.setPublishPersonId("admin");
        publicNotice.setPublishOrg("卫生局");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        publicNoticeService.saveOrUpdatePublicNotice(publicNotice);
        resultMap.put("success", true);
        return resultMap;
    }
    
    /**
     * 删除公告
     * 
     * @param nId 主键ID
     * @return 结果
     */
    @RequestMapping("/deletePublicNotice")
    @ResponseBody
    public Map<String, Object> deletePublicNotice(String nId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        publicNoticeService.deletePublicNotice(nId);
        resultMap.put("success", true);
        return resultMap;
    }
    
    /**
     * 上传附件
     * 
     * @param request 请求对象
     * @param attachment 附件
     * @return 结果
     */
    @RequestMapping("/uploadAttachment")
    @ResponseBody
    public Map<String, Object> uploadAttachment(HttpServletRequest request, Attachment attachment) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        MultipartFile file = multipartRequest.getFile("uploadFile");
        
        attachmentService.saveAttachment(attachment, file);
        resultMap.put("success", true);
        resultMap.put("attachment", attachment);
        return resultMap;
    }
    
    /**
     * 重命名附件
     * 
     * @param attachment 附件信息
     * @return 结果
     */
    @RequestMapping("/renameAttachment")
    @ResponseBody
    public Map<String, Object> renameAttachment(Attachment attachment) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        attachmentService.updateName(attachment);
        resultMap.put("success", true);
        resultMap.put("attachment", attachment);
        return resultMap;
    }
    
    /**
     * 下载附件
     * 
     * @param response 响应对象
     * @param aId 主键
     * @return result
     * @throws IOException IOException
     */
    @RequestMapping("/downloadAttachment")
    public void downloadAttachment(HttpServletResponse response, String aId)
        throws IOException {
        attachmentService.downloadAttachment(aId, response);
    }
    
    /**
     * 删除附件
     * 
     * @param aId 附件id
     * @return 结果
     */
    @RequestMapping("/deleteAttachment")
    @ResponseBody
    public Map<String, Object> deleteAttachment(String aId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        attachmentService.deleteAttachment(aId);
        resultMap.put("success", true);
        return resultMap;
    }
    
    /************************************************消息管理end***************************************************************/
    /************************************************消息查看begin***************************************************************/
    /**
     * 辅助功能-消息查看主页
     * 
     * @return URL
     */
    @RequestMapping("/noticeViewMain")
    public String noticeViewMain() {
        return "dip/notice/noticeViewMain";
    }
    
    /**
     * 公告信息查看主页
     * 
     * @return URL
     */
    @RequestMapping("/publicNoticeViewIndex")
    public String publicNoticeViewIndex() {
        return "dip/notice/publicNoticeViewIndex";
    }
    /************************************************消息查看end***************************************************************/
}
