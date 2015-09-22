package com.zebone.dip.empi.controller;

import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.dip.dictionary.service.DipDictionaryService;
import com.zebone.dip.empi.service.EmpiManageService;
import com.zebone.dip.empi.vo.*;
import com.zebone.dip.log.service.LogService;
import com.zebone.dip.log.vo.ParmInfo;
import com.zebone.dip.md.vo.NameCode;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主索引管理
 * @author 杨英
 * @version 2014-7-14 上午9:28
 */
@Controller
public class EmpiManagerController {

    @Resource
    private DipDictionaryService dipDictionaryService;

    @Resource
    private EmpiManageService empiManageService;

    @Resource
    private LogService logService;

    @RequestMapping("empiManage/index")
    public String empiManageIndex(ModelMap map) {
        return "dip/empi/empiManage_index";
    }

    /**
     * 获取页面左侧的树状列表
     * @return
     */
    @RequestMapping("empiManage/tree")
    public String empiManageTreePage() {
        return "dip/empi/empiManage_tree";
    }

    /**
     * 获取主索引信息查询页面
     * @return
     */
    @RequestMapping("empiManage/query")
    public String empiManageQueryPg(ModelMap map) {
        List<NameCode> type = dipDictionaryService.getTypeByCode("EXV00.00.001");
        map.put("typeList", type);
        return "dip/empi/empiManage_query";
    }

    /**
     * 获取主索引信息列表
     * @return
     */
    @RequestMapping("empiManage/empiInfoList")
    @ResponseBody
    public JsonGrid empiInfoList(ResidentCard residentCard, Pagination<EmpiInfo> page) {
        Pagination<EmpiInfo> p = empiManageService.empiInfoPage(page, residentCard);
        JsonGrid jGrid = new JsonGrid("true", p.getTotalCount(), p.getData());
        return jGrid;
    }

    /**
     * 获取操作明细列表
     * @return
     */
    @RequestMapping("empiManage/operateList")
    public @ResponseBody JsonGrid operateList(EmpiInfo empiInfo,
                                                 Pagination<EmpiLogInfo> page) {
        Pagination<EmpiLogInfo> p = empiManageService.operateList(page, empiInfo);
        JsonGrid jGrid = new JsonGrid("true", p.getTotalCount(), p.getData());
        return jGrid;
    }

    /**
     * 获取主索引详细信息
     * @return
     */
    @RequestMapping("empiManage/empiDetals")
    public String empiDetails (HttpServletRequest request, HttpServletResponse response){
        String empiNo = request.getParameter("empi");
        EmpiInfo empiInfo = empiManageService.getEmpiDetails(empiNo);
        if(empiInfo!=null){
            byte[] photo = empiInfo.getPhoto();
            if(photo!=null && photo.length>0){
                empiInfo.setStrPhoto(new String(photo));
            }
        }
        request.setAttribute("empiInfo", empiInfo);
        return "dip/empi/empiManage_detail";
    }

    /**
     * 获取标识信息列表
     *
     * @return
     */
    @RequestMapping("empiManage/cardList")
    public @ResponseBody JsonGrid cardList(String empi) {
        List<ResidentCard> list = empiManageService.getCardList(empi);
        JsonGrid jGrid = new JsonGrid("true", list.size(), list);
        return jGrid;
    }

    /**
     * 获取主索引信息统计页面
     * @return
     */
    @RequestMapping("empiManage/count")
    public String empiManageCountPg(ModelMap map) {
        List<NameCode> type = dipDictionaryService.getTypeByCode("EXV00.00.001");
        map.put("typeList", type);
        return "dip/empi/empiManage_count";
    }

    /**
     * 获取主索引统计信息列表
     * @return
     */
    @RequestMapping("empiManage/countInfoList")
    public @ResponseBody JsonGrid empiCountList(EmpiCount empiCount,
                                              Pagination<EmpiCount> page) {
        Pagination<EmpiCount> p = empiManageService.empiCountList(page, empiCount);
        JsonGrid jGrid = new JsonGrid("true", p.getTotalCount(), p.getData());
        return jGrid;
    }

    /**
     * 获取实名信息更新页面
     * @return
     */
    @RequestMapping("empiManage/realApplyInfo")
    public String realApplyInfo(ModelMap map) {
        List<NameCode> statusList = dipDictionaryService.getTypeByCode("EXV00.00.070");
        map.put("statusList", statusList);
        return "dip/empi/empiManage_realApply";
    }

    /**
     * 获取EMPI解绑页面
     * @return
     */
    @RequestMapping("empiManage/unbindApplyInfo")
    public String unbindApplyInfo(ModelMap map) {
        List<NameCode> statusList = dipDictionaryService.getTypeByCode("EXV00.00.070");
        map.put("statusList", statusList);//状态列表
        //卡类型列表
        List<NameCode> type = dipDictionaryService.getTypeByCode("EXV00.00.001");
        for(int i=0; i<type.size(); i++){  //移除卡类型列表中的一级标识
            String code = type.get(i).getCode();
            if("1".equals(code) || "2".equals(code) || "3".equals(code)){
                type.remove(i);
            }
        }
        map.put("typeList", type);
        return "dip/empi/empiManage_unbindApply";
    }

    /**
     * 获取实名信息更新申请列表
     * @return
     */
    @RequestMapping("empiManage/updateApplyList")
    public @ResponseBody JsonGrid updateApplyList(UpdateApply updateApply,
                                                Pagination<UpdateApply> page) {
        Pagination<UpdateApply> p = empiManageService.updateApplyList(page, updateApply);
        JsonGrid jGrid = new JsonGrid("true", p.getTotalCount(), p.getData());
        return jGrid;
    }

    /**
     * 获取主索引解绑申请列表
     * @return
     */
    @RequestMapping("empiManage/unbindApplyList")
    public @ResponseBody JsonGrid unbindApplyList(UnbindApply unbindApply,
                                                  Pagination<UnbindApply> page) {
        Pagination<UnbindApply> p = empiManageService.unbindApplyList(page, unbindApply);
        JsonGrid jGrid = new JsonGrid("true", p.getTotalCount(), p.getData());
        return jGrid;
    }

    /**
     * 获取实名信息更新审核详情页面
     * @return
     */
    @RequestMapping("empiManage/updateAuditDetails")
    public String auditDetails (HttpServletRequest request, HttpServletResponse response){
        String empiNo = request.getParameter("empi");
        EmpiInfo empiInfo = empiManageService.getEmpiDetails(empiNo);
        if(empiInfo!=null){
            byte[] photo = empiInfo.getPhoto();
            if(photo!=null && photo.length>0){
                empiInfo.setStrPhoto(new String(photo));
            }
        }
        //获取申请更新的实名信息
        UpdateApply updateApply = empiManageService.getUpdateApplyInfo(empiNo);
        if(updateApply!=null){
            byte[] photo = updateApply.getPhoto();
            if(photo!=null && photo.length>0){
                updateApply.setStrPhoto(new String(photo));
            }
        }
        request.setAttribute("empiInfo", empiInfo);
        request.setAttribute("updateApply",updateApply);
        return "dip/empi/empiManage_auditDetails";
    }

    /**
     * 获取EMPI解绑审核详情页面
     * @return
     */
    @RequestMapping("empiManage/unbindAuditDetails")
    public String unbindAuditDetails (HttpServletRequest request, HttpServletResponse response){
        String empiNo = request.getParameter("empi");
        String id = request.getParameter("id");
        EmpiInfo empiInfo = empiManageService.getEmpiDetails(empiNo);
        if(empiInfo!=null){
            byte[] photo = empiInfo.getPhoto();
            if(photo!=null && photo.length>0){
                empiInfo.setStrPhoto(new String(photo));
            }
        }
        //获取二级标识原本绑定的一级标识信息
        ResidentCard residentCard = null;
        List<ResidentCard> list = empiManageService.getCardList(empiNo);
        for(ResidentCard rec : list){
            if("1".equals(rec.getCardLevel())) {
                residentCard = rec;
                break;
            }
        }
        //获取需要重新绑定的新的一级标识的信息
        UnbindApply unbindApply = empiManageService.getUnbindApplyInfo(id);
        if(unbindApply!=null){
            byte[] photo = unbindApply.getPhoto();
            if(photo!=null && photo.length>0){
                unbindApply.setStrPhoto(new String(photo));
            }
        }
        request.setAttribute("empiInfo", empiInfo);
        request.setAttribute("oldFirstCard",residentCard);
        request.setAttribute("unbindApply",unbindApply);
        return "dip/empi/empiManage_unbindAudit";
    }

    /**
     * 允许更新申请
     * @return
     */
    @RequestMapping("empiManage/permitUpdateApply")
    public @ResponseBody Map<String, Object> permitUpdateApply(UpdateApply updateApply) {
        int result = 0;
        String msg = "";
        boolean bool = false;
        result = empiManageService.auditRealInfoApply(updateApply,"1"); //1 代表允许申请
        if (result > 0) {
            msg = "更新成功";
            bool = true;
        } else {
            msg = "更新失败";
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", bool);
        map.put("msg", msg);
        return map;
    }

    /**
     * 拒绝更新申请
     * @return
     */
    @RequestMapping("empiManage/refuseUpdateApply")
    public @ResponseBody Map<String, Object> refuseUpdateApply(UpdateApply updateApply) {
        int result = 0;
        String msg = "";
        boolean bool = false;
        result = empiManageService.auditRealInfoApply(updateApply,"2");  //2 代表拒绝申请
        if (result > 0) {
            msg = "申请已拒绝";
            bool = true;
        } else {
            msg = "申请拒绝失败";
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", bool);
        map.put("msg", msg);
        return map;
    }

    /**
     * 允许EMPI解绑申请
     * @return
     */
    @RequestMapping("empiManage/permitUnbindApply")
    public @ResponseBody Map<String, Object> permitUnbindApply(UnbindApply unbindApply) {
        int result = 0;
        String msg = "";
        boolean bool = false;
        result = empiManageService.auditUnbindApply(unbindApply,"1"); //1 代表允许解绑
        if (result > 0) {
            msg = "解绑成功";
            bool = true;
        } else {
            msg = "解绑失败";
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", bool);
        map.put("msg", msg);
        return map;
    }

    /**
     * 拒绝EMPI解绑申请
     * @return
     */
    @RequestMapping("empiManage/refuseUnbindApply")
    public @ResponseBody Map<String, Object> refuseUnbindApply(UnbindApply unbindApply) {
        int result = 0;
        String msg = "";
        boolean bool = false;
        result = empiManageService.auditUnbindApply(unbindApply,"2");  //2 代表拒绝解绑
        if (result > 0) {
            msg = "解绑已拒绝";
            bool = true;
        } else {
            msg = "解绑拒绝失败";
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", bool);
        map.put("msg", msg);
        return map;
    }
    
    
    /**
     * 实名信息修改申请
     * @param map
     * @return
     * @author 陈阵 
     * @date 2014-10-10 上午10:02:18
     */
    @RequestMapping("empiManage/addRealApply")
    public String addRealApply() {
        return "dip/empi/empiManage_addRealApply";
    }
    
    
    /**
     * 实名信息修改申请
     * @param map
     * @return
     * @author 陈阵 
     * @date 2014-10-10 上午10:02:18
     */
    @RequestMapping("empiManage/realApplyAdd")
    @ResponseBody
    public Map<String, Object> realApplyAdd(ResidentUpdateApply residentUpdateApply) {
    	Map<String,Object> result = new HashMap<String, Object>();
    	if(StringUtils.isEmpty(residentUpdateApply.getCardNo()) || 
    			StringUtils.isEmpty(residentUpdateApply.getName())){
    		result.put("success", false);
    	}else{
    		result = empiManageService.saveUpdateApply(residentUpdateApply);
    	}
        return result;
    }
    
    
    /**
     * 就诊卡解绑
     * @return
     * @author 陈阵 
     * @date 2014-10-14 下午1:10:00
     */
    @RequestMapping("empiManage/addUnbindApply")
    public String addUnbindApply() {
        return "dip/empi/empiManage_addUnbindApply";
    }
    
    
    /**
     * 卡解绑
     * @param residentUpdateApply
     * @return
     * @author 陈阵 
     * @date 2014-10-14 下午2:13:16
     */
    @RequestMapping("empiManage/unbindApplyAdd")
    @ResponseBody
    public Map<String, Object> unbindApplyAdd(UnbindApplyParameter unbindApplyParameter) {
    	Map<String,Object> result = new HashMap<String, Object>();
    	if(StringUtils.isEmpty(unbindApplyParameter.getBindCardNo()) || 
    			StringUtils.isEmpty(unbindApplyParameter.getBindCardType()) ||
    			  StringUtils.isEmpty(unbindApplyParameter.getUnbindCardNo()) ||
    			  	StringUtils.isEmpty(unbindApplyParameter.getUnbindCardType())){
    		result.put("success", false);
    	}else{
    		result = empiManageService.saveUnbindApply(unbindApplyParameter);
    	}
        return result;
    }
    
    
    


}
