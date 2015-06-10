package com.zebone.btp.empi.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.zebone.btp.Constant;
import com.zebone.btp.empi.EmpiUtil;
import com.zebone.btp.empi.mapper.EmpiCardMapper;
import com.zebone.btp.empi.mapper.EmpiConfigMapper;
import com.zebone.btp.empi.mapper.EmpiErrorMapper;
import com.zebone.btp.empi.mapper.EmpiUserMapper;
import com.zebone.btp.empi.utils.ProcessExcel;
import com.zebone.btp.empi.utils.ProcessFileUtils;
import com.zebone.btp.empi.vo.EmpiCard;
import com.zebone.btp.empi.vo.EmpiConfig;
import com.zebone.btp.empi.vo.EmpiError;
import com.zebone.btp.empi.vo.EmpiUser;
import com.zebone.btp.empi.vo.ImportData;
import com.zebone.util.UUIDUtil;

@Service("empiDataService")
public class EmpiDataServiceImpl implements EmpiDataService {
    
    private static Logger log = Logger.getLogger(EmpiDataServiceImpl.class);
    
    @Autowired
    private EmpiUserMapper empiUserMapper;
    
    @Autowired
    private EmpiCardMapper empiCardMapper;
    
    @Autowired
    private EmpiConfigMapper empiConfigMapper;
    
    @Autowired
    private EmpiErrorMapper empiErrorMapper;
    
    @Override
    public boolean proImportEmpiData(MultipartFile file, ImportData data) {
        
        boolean flag = false;
        try {
            
            // 获取配置信息
            EmpiConfig ec = empiConfigMapper.getEmpiConfig();
            
            if (null != ec) {
                
                // 获取临时上传文件目录
                String fileName = processUploadPath(file);
                
                data.setEmpiType(ec.getEmpiType());// 获取配置的EMPI类型
                data.setAbsolutePath(fileName);// 带路径的文件名
                
                // 根据类型 返回对应的处理模板
                ProcessFileUtils pfu = checkFileType(data);
                
                if (null != pfu) {
                    // 解析文件 获取结果集
                    List<EmpiUser> userList = pfu.readFromFile(data);
                    
                    // 入库
                    flag = addEmpiData(userList, ec);
                }
            }
            
        }
        catch (IOException ioe) {
            log.error(ioe, ioe);
        }
        catch (Exception e) {
            log.error(e, e);
        }
        
        return flag;
    }
    
    /**
     * 处理路径
     * 
     * @throws Exception
     */
    private String processUploadPath(MultipartFile file)
        throws IOException {
        
        // 创建目录 相对系统应用
        String path = System.getProperty("zebone") + "upload\\import\\";
        File temp = new File(path);
        if (!temp.isDirectory()) {
            temp.mkdirs();
        }
        
        // 移动文件
        String fileName = path + file.getOriginalFilename();
        if (temp.exists()) {
            temp = new File(fileName);
            file.transferTo(temp);
        }
        
        return fileName;
    }
    
    /**
     * 
     * 将userList入库 根据不同的更新原则处理
     * 
     * @param userList
     * @return
     */
    @Override
    public boolean addEmpiData(List<EmpiUser> userList, EmpiConfig ec) {
        
        boolean flag = false;
        
        if (!CollectionUtils.isEmpty(userList)) {
            
            // 处理EmpiUser对象
            for (EmpiUser u : userList) {
                
                int exist = empiUserMapper.getEmpiUserByEmpi(u.getEmpiId());
                
                try {
                    // 新增用户 若用户已经存在 则查看配置是否更新基本信息
                    if (exist > 0) {
                        if (Constant.UPDATE_EMPI_FLAG.equals(ec.getUpdateEmpiFlag())) {
                            
                            empiUserMapper.updateEmpiUser(u);
                        }
                        // 记录错误表：证件已经注册
                        addEmpiError(null, u, null, Constant.ERR_CODE_ICN_EXISTS);
                        
                    }
                    else {
                        
                        if (EmpiUtil.icnValidate(u.getEmpiId())) {
                            empiUserMapper.addEmpiUser(u);
                        }
                        else {
                            // 记录错误表： 证件格式错误
                            addEmpiError(null, u, null, Constant.ERR_CODE_ICN_INVALIDATE);
                        }
                    }
                }
                catch (Exception e) {
                    // 记录日志 并记录错误表 处理下一条记录
                    addEmpiError(e, u, null, Constant.ERR_CODE_OTHER);
                    continue;
                }
                
            }
            
            // 处理EmpiCard对象
            List<EmpiCard> cardList = null;
            for (EmpiUser u : userList) {
                cardList = u.getCards();
                if (!CollectionUtils.isEmpty(cardList)) {
                    for (EmpiCard c : cardList) {
                        
                        try {
                            
                            int cardExist = empiCardMapper.queryEmpiCardCount(c);
                            
                            // 注册Card 若已经注册则查看配置是否更新
                            if (cardExist > 0) {
                                
                                if (Constant.UPDATE_CARD_FLAG.equals(ec.getUpdateCardFlag())) {
                                    
                                    empiCardMapper.updateEmpiCard(c);
                                }
                                
                                // 记录错误表：卡已经注册
                                addEmpiError(null, u, c, Constant.ERR_CODE_CARD_EXISTS);
                            }
                            else {
                                
                                empiCardMapper.addEmpiCard(c);
                                
                            }
                        }
                        catch (Exception e) {
                            // 记录日志 并记录错误表 处理下一条记录
                            addEmpiError(e, u, c, Constant.ERR_CODE_OTHER);
                            continue;
                        }
                    }
                }
            }
            
            flag = true;
            
        }
        return flag;
    }
    
    /**
     * 记录错误日志信息 并记录错误表 <功能详细描述>
     * 
     * @param e
     * @param user
     * @param card
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addEmpiError(Exception e, EmpiUser user, EmpiCard card, String type) {
        
        // 新建EmpiError
        EmpiError ee = new EmpiError();
        ee.setErrId(UUIDUtil.getUuid());
        ee.setEmpiId(user.getEmpiId());
        ee.setUserName(user.getUserName());
        ee.setErrType(type);
        ee.setErrCreateDate(EmpiUtil.getTimeNow());
        
        if (card != null) {
            ee.setErrCardNo(card.getCardNo());
            ee.setErrCardType(card.getCardType());
        }
        
        // 入库
        try {
            empiErrorMapper.addEmpiError(ee);
        }
        catch (Exception ex) {
            log.error(user, ex);
        }
        
        // 记录错误日志
        log.error(user, e);
        
    }
    
    public ProcessFileUtils checkFileType(ImportData data) {
        ProcessFileUtils pfu = null;
        if (null != data) {
            if ("excel".equals(data.getFileType())) {
                pfu = new ProcessExcel();
            }
        }
        
        return pfu;
    }
}
