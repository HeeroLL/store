package com.zebone.btp.sso.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zebone.btp.core.util.JsonGrid;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.sso.pojo.SsoAccountInfo;
import com.zebone.btp.sso.pojo.SysAccountInfo;
import com.zebone.btp.sso.pojo.SysRegInfo;
import com.zebone.btp.sso.service.SsoAccountService;
import com.zebone.btp.sso.service.SysAccountService;
import com.zebone.btp.sso.service.SysRegService;

/**
 * 注册系统
 *
 * @author charmyin
 * @version 1.0
 *
 */
@Controller
@RequestMapping("sso")
public class SsoController
{

    @Autowired
    private SysRegService sysRegService;

    @Autowired
    private SsoAccountService ssoAccountService;

    @Autowired
    private SysAccountService sysAccountService;

    /**
     * 导向系统注册页面
     *
     * @return
     */
    @RequestMapping("/goSysReg")
    public String goSysReg()
    {
        return "sso/sysreg_index";
    }

    /**
     * 分页查询注册的子系统
     *
     * @param sysRegInfo 查询条件集合对象
     * @param page 分页对象
     * @return json格式
     */
    @RequestMapping("/sysRegQueryPage")
    @ResponseBody
    public JsonGrid sysRegQueryPage(SysRegInfo sysRegInfo, Pagination<SysRegInfo> page)
    {
        page = sysRegService.searchSysRegInfo(page.getRowBounds(), sysRegInfo);

        JsonGrid jsonGrid = new JsonGrid("success", page.getTotalCount(), page.getData());

        return jsonGrid;
    }

    /**
     * 按注册系统id查找注册系统信息
     *
     * @param reg_id
     * @return 返回注册系统信息
     */
    @RequestMapping("/getSysRegById")
    @ResponseBody
    public SysRegInfo getSysRegById(@RequestParam("regId")
    String regId)
    {
        SysRegInfo sri = sysRegService.findSysRegInfoById(regId);
        return sri;
    }

    /**
     * 删除注册系统
     *
     * @param reg_id
     * @return
     */
    @RequestMapping("/removeSysReg")
    @ResponseBody
    public Map<String, Object> removeSysReg(String regId)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try
        {
            // 判断参数是否为空
            if (regId != null && !regId.trim().isEmpty())
            {
                String[] ids = regId.split(",");
                for (int i = 0; i < ids.length; i++)
                {
                    sysRegService.deleteSysRegInfo(ids[i]);
                }
                resultMap.put("msg", "success");
            }
            else
            {
                resultMap.put("msg", "未传要删除的id值");
            }
        }
        catch (Exception e)
        {
            resultMap.put("msg", "删除过程中出错~");
        }
        return resultMap;
    }

    /**
     * 保存子系统注册信息
     *
     * @param sysRegInfo
     * @return
     */
    @RequestMapping("/saveSysReg")
    @ResponseBody
    public Map<String, Object> saveSysReg(SysRegInfo sysRegInfo)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        String sys_id = sysRegInfo.getRegId();
        if (sys_id != null && !sys_id.equals("null") && !sys_id.trim().isEmpty())
        {
            sysRegService.updateSysRegInfo(sysRegInfo);
        }
        else
        {
            sysRegInfo.setRegId(UUID.randomUUID().toString().replace("-", ""));
            sysRegService.saveSysRegInfo(sysRegInfo);
        }
        map.put("msg", "success");
        return map;
    }

    /**
     * 导向用户管理界面
     *
     * @return
     */
    @RequestMapping("/goAccountInfo")
    public String goAccount()
    {
        return "sso/users_index";
    }

    /**
     * 返回json的格式
     *
     * @param ssoAccountInfo
     * @param page
     * @return
     */
    @RequestMapping("/queryAccount")
    @ResponseBody
    public JsonGrid queryAccount(SsoAccountInfo ssoAccountInfo, Pagination<SsoAccountInfo> page)
    {
        page = ssoAccountService.searchSsoAccountInfo(page.getRowBounds(), ssoAccountInfo);
        JsonGrid jg = new JsonGrid("success", page.getTotalCount(), page.getData());
        return jg;
    }

    /**
     * 按照帐号ID查找用户信息
     *
     * @param accountId
     * @return 返回Json格式的用户信息
     */
    @RequestMapping("/getAccountInfoById")
    @ResponseBody
    public SsoAccountInfo getAccountById(@RequestParam("accountId")
    String accountId)
    {
        SsoAccountInfo ssoAccountInfo = ssoAccountService.findSsoAccountInfoById(accountId);
        return ssoAccountInfo;
    }

    /**
     * 保存用户信息
     *
     * @param ssoAccountInfo
     * @return
     */
    @RequestMapping("/saveAccountInfo")
    @ResponseBody
    public Map<String, String> saveAccount(SsoAccountInfo ssoAccountInfo)
    {
        String accountId = ssoAccountInfo.getAccountId();
        Map<String, String> resultMap = new HashMap<String, String>();
        // 根据id是否为空来判断插入还是更新
        try
        {
            // 密码MD5加密
            ssoAccountInfo.setPassword(new Md5Hash(ssoAccountInfo.getPassword()).toHex());
            if (accountId != null && !accountId.trim().isEmpty() && !accountId.equals("null"))
            {
                ssoAccountService.updateSsoAccountInfo(ssoAccountInfo);
            }
            else
            {
                ssoAccountInfo.setAccountId(UUID.randomUUID().toString().replace("-", ""));
                ssoAccountService.saveSsoAccountInfo(ssoAccountInfo);
            }
            resultMap.put("msg", "success");
        }
        catch (Exception e)
        {
            resultMap.put("msg", "fail");
        }

        return resultMap;
    }

    /**
     * 按帐号ID删除帐号信息
     *
     * @param accountId
     * @return
     */
    @RequestMapping("/removeAccountByIds")
    @ResponseBody
    public Map<String, String> removeAccountByIds(@RequestParam("accountId")
    String accountId)
    {
        Map<String, String> resultMap = new HashMap<String, String>();
        try
        {
            // 判断参数是否为空
            if (accountId != null && !accountId.trim().isEmpty())
            {
                String[] ids = accountId.split(",");
                for (int i = 0; i < ids.length; i++)
                {
                    ssoAccountService.deleteSsoAccountInfo(ids[i]);
                }
                resultMap.put("msg", "success");
            }
            else
            {
                resultMap.put("msg", "未传要删除的id值");
            }
        }
        catch (Exception e)
        {
            resultMap.put("msg", "删除过程中出错~");
        }
        return resultMap;
    }

    /**
     * 导向系统注册页面
     *
     * @return
     */
    @RequestMapping("/goSysAccnt")
    public String goSysAccnt()
    {
        return "sso/sysaccnt_index";
    }

    /**
     * 分页查询注册的子系统
     *
     * @param sysRegInfo 查询条件集合对象
     * @param page 分页对象
     * @return json格式
     */
    @RequestMapping("/sysAccntQueryPage")
    @ResponseBody
    public JsonGrid sysAccntQueryPage(SysAccountInfo sysAccountInfo, Pagination<SysAccountInfo> page)
    {
        page = sysAccountService.searchSysAccountInfo(page.getRowBounds(), sysAccountInfo);

        JsonGrid jsonGrid = new JsonGrid("success", page.getTotalCount(), page.getData());

        return jsonGrid;
    }

    /**
     * 按注册系统id查找注册系统信息
     *
     * @param reg_id
     * @return 返回注册系统信息
     */
    @RequestMapping("/getSysAccntById")
    @ResponseBody
    public SysAccountInfo getSysAccntById(@RequestParam("accountId")
    String accountId)
    {
        SysAccountInfo sri = sysAccountService.findSysAccountInfoById(accountId);
        return sri;
    }

    /**
     * 删除注册系统
     *
     * @param reg_id
     * @return
     */
    @RequestMapping("/removeSysAccnt")
    @ResponseBody
    public Map<String, Object> removeSysAccnt(String accountId)
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try
        {
            // 判断参数是否为空
            if (accountId != null && !accountId.trim().isEmpty())
            {
                String[] ids = accountId.split(",");
                for (int i = 0; i < ids.length; i++)
                {
                    sysAccountService.deleteSysAccountInfo(ids[i]);
                }
                resultMap.put("msg", "success");
            }
            else
            {
                resultMap.put("msg", "未传要删除的id值");
            }
        }
        catch (Exception e)
        {
            resultMap.put("msg", "删除过程中出错~");
        }
        return resultMap;
    }

    /**
     * 保存子系统注册信息
     *
     * @param sysRegInfo
     * @return
     */
    @RequestMapping("/saveSysAccnt")
    @ResponseBody
    public Map<String, Object> saveSysAccnt(SysAccountInfo sysAccountInfo)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        String sysId = sysAccountInfo.getAccountId();

        // 密码MD5加密
        sysAccountInfo.setPassword(new Md5Hash(sysAccountInfo.getPassword()).toHex());
        if (sysId != null && !sysId.equals("null") && !sysId.trim().isEmpty())
        {
            sysAccountService.updateSysAccountInfo(sysAccountInfo);
        }
        else
        {
            sysAccountInfo.setAccountId(UUID.randomUUID().toString().replace("-", ""));
            sysAccountService.saveSysAccountInfo(sysAccountInfo);
        }
        map.put("msg", "success");
        return map;
    }

}
