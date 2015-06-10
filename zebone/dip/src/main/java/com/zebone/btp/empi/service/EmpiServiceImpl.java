package com.zebone.btp.empi.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.zebone.btp.Constant;
import com.zebone.btp.core.util.Pagination;
import com.zebone.btp.empi.EmpiUtil;
import com.zebone.btp.empi.mapper.EmpiCardMapper;
import com.zebone.btp.empi.mapper.EmpiConfigMapper;
import com.zebone.btp.empi.mapper.EmpiErrorMapper;
import com.zebone.btp.empi.mapper.EmpiUserMapper;
import com.zebone.btp.empi.vo.EmpiCard;
import com.zebone.btp.empi.vo.EmpiConfig;
import com.zebone.btp.empi.vo.EmpiError;
import com.zebone.btp.empi.vo.EmpiUser;
import com.zebone.btp.empi.vo.QueryInfo;
import com.zebone.util.UUIDUtil;

@Service("empiService")
public class EmpiServiceImpl implements EmpiService {
    
    // private static Logger log = Logger.getLogger(EmpiServiceImpl.class);
    
    @Autowired
    private EmpiUserMapper empiUserMapper;
    
    @Autowired
    private EmpiErrorMapper empiErrorMapper;
    
    @Autowired
    private EmpiConfigMapper empiConfigMapper;
    
    @Autowired
    private EmpiCardMapper empiCardMapper;
    
    @Autowired
    private EmpiDataService empiDataService;
    
    /* **********************EmpiUser对象 处理 start************* */
    
    @Override
    public Pagination<EmpiUser> queryEmpiUserList(Pagination<EmpiUser> page, EmpiUser empiUser) {
        
        // 查询结果
        List<EmpiUser> tempList = empiUserMapper.queryEmpiUserList(page.getRowBounds(), empiUser);
        
        int count = empiUserMapper.queryEmpiUserCount(empiUser);
        
        // 封装到对应的Page对象
        page.setData(tempList);
        page.setTotalCount(count);
        return page;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean operateEmpiUser(EmpiUser empiUser) {
        
        // 处理EmpiUser 判断ID是否有值 无新建 有更新
        try {
            if (EmpiUtil.isEmpty(empiUser.getUserId())) {
                
                empiUser.setUserId(UUIDUtil.getUuid()); // 用户ID
                empiUser.setState(Constant.STATE_NORMAL);// 状态
                empiUser.setCreateDate(EmpiUtil.getTimeNow());// 创建时间（yyyyMMddhhmmss）
                empiUser.setDelFlag(Constant.DEL_FLAG_NORMAL); // 删除标识
                
                if (Constant.IDENTIFY_CARD_NO.equals(empiUser.getEmpiType())) {// 如果EMPI是身份证
                    // 则设置icn为EMPI值
                    empiUser.setIcn(empiUser.getEmpiId());
                }
                
                empiUserMapper.addEmpiUser(empiUser);
                
            }
            else {
                
                empiUserMapper.updateEmpiUser(empiUser);
            }
        }
        catch (Exception e) {
            empiDataService.addEmpiError(e, empiUser, null, "其他错误");
            return false;
        }
        
        // 处理EmpiCard
        proEmpiCards(empiUser);
        List<EmpiCard> cardList = empiUser.getCards();
        if (!CollectionUtils.isEmpty(cardList)) {
            
            // 配置
            EmpiConfig ec = empiConfigMapper.getEmpiConfig();
            
            for (EmpiCard c : cardList) {
                try {
                    
                    int cardExist = empiCardMapper.queryEmpiCardCount(c);
                    
                    // 注册Card 若已经注册则查看配置是否更新
                    if (cardExist > 0) {
                        
                        if (Constant.UPDATE_CARD_FLAG.equals(ec.getUpdateCardFlag())) {
                            
                            empiCardMapper.updateEmpiCard(c);
                            
                        }
                    }
                    else {
                        
                        empiCardMapper.addEmpiCard(c);
                        
                    }
                }
                catch (Exception e) {
                    // 记录日志 并记录错误表 处理下一条记录
                    empiDataService.addEmpiError(e, empiUser, c, "");
                    continue;
                }
            }
        }
        
        return true;
    }
    
    @Override
    public boolean removeEmpiUser(QueryInfo info) {
        
        // 将ID字符传转化为list
        String[] idArray = info.getId().split(",");
        for (String id : idArray) {
            info.addId(id.trim());
        }
        return (empiUserMapper.removeEmpiUser(info) > 0) ? true : false;
    }
    
    @Override
    public EmpiUser loadEmpiUser(EmpiUser empiUser) {
        
        return empiUserMapper.loadEmpiUser(empiUser);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean updateEmpiId(EmpiUser empiUser) {
        
        try {
            // 用新的newEmpiId 替换 empiId
            empiUserMapper.updateSynEmpiUser(empiUser);
            
            // 同步更新对应注册的Cards
            empiCardMapper.updateSynEmpiCards(empiUser);
        }
        catch (Exception e) {
            empiDataService.addEmpiError(e, empiUser, null, "");
            return false;
        }
        
        return true;
    }
    
    /* **********************EmpiUser对象 处理 end*************** */
    
    /* **********************EmpiCard对象 处理 start************* */
    @Override
    public Pagination<EmpiCard> queryEmpiCardList(Pagination<EmpiCard> page, EmpiCard empiCard) {
        
        // 查询结果
        List<EmpiCard> tempList = empiCardMapper.queryEmpiCardList(page.getRowBounds(), empiCard);
        
        int count = empiCardMapper.queryEmpiCardCount(empiCard);
        
        // 封装到对应的Page对象
        page.setData(tempList);
        page.setTotalCount(count);
        return page;
    }
    
    @Override
    public boolean operateEmpiCard(EmpiCard empiCard) {
        
        int result = 0;
        
        if (null != empiCard) {
            // id为空 则是新增 否则是更新 注销
            if (StringUtils.isEmpty(empiCard.getCardId())) {
                
                int exists = empiCardMapper.queryEmpiCardCount(empiCard);
                if (exists <= 0) {
                    empiCard.setCardId(UUIDUtil.getUuid());
                    empiCard.setCardState(Constant.STATE_NORMAL);
                    
                    result = empiCardMapper.addEmpiCard(empiCard);
                }
                
            }
            else {
                result = empiCardMapper.updateEmpiCard(empiCard);
            }
        }
        
        return result > 0 ? true : false;
    }
    
    @Override
    public EmpiCard loadEmpiCard(EmpiCard empiCard) {
        
        return empiCardMapper.loadEmpiCard(empiCard);
    }
    
    @Override
    public boolean removeEmpiCard(QueryInfo info) {
        
        // 将ID字符传转化为list
        String[] idArray = info.getId().split(",");
        for (String id : idArray) {
            info.addId(id.trim());
        }
        return (empiCardMapper.removeEmpiCard(info) > 0) ? true : false;
    }
    
    /* **********************EmpiCard对象 处理 end*************** */
    
    /* **********************EmpiError对象 处理 start************* */
    public boolean addEmpiError(EmpiError empiError) {
        return empiErrorMapper.addEmpiError(empiError) > 0 ? true : false;
    }
    
    /* **********************EmpiError对象 处理 end*************** */
    
    /* **********************EmpiConfig对象 处理 start************* */
    @Override
    public EmpiConfig getEmpiConfig() {
        
        return empiConfigMapper.getEmpiConfig();
    }
    
    @Override
    public boolean operateEmpiConfig(EmpiConfig empiConfig) {
        
        return empiConfigMapper.updateEmpiConfig(empiConfig) > 0 ? true : false;
    }
    
    /* **********************EmpiConfig对象 处理 end*************** */
    
    /* **********************公共方法*************** */
    
    /**
     * 
     * 处理传参
     * 
     * @param empiUser
     */
    public static void proEmpiCards(EmpiUser empiUser) {
        String[] cardNo = empiUser.getCardNo();
        String[] cardSid = empiUser.getCardSid();
        String[] cardType = empiUser.getCardType();
        
        if (null != cardNo && null != cardSid && null != cardType) {
            // TODO 校验Card
            if (cardNo.length == cardSid.length && cardNo.length == cardType.length) {
                List<EmpiCard> list = new ArrayList<EmpiCard>();
                EmpiCard ec;
                for (int k = 0; k < cardNo.length; k++) {
                    ec = new EmpiCard();
                    ec.setCardId(UUIDUtil.getUuid());
                    ec.setCardNo(cardNo[k]);
                    ec.setCardSid(cardSid[k]);
                    ec.setCardType(cardType[k]);
                    ec.setCardState(Constant.STATE_NORMAL);
                    ec.setEmpiId(empiUser.getEmpiId());
                    
                    list.add(ec);
                }
                empiUser.setCards(list);
            }
        }
    }
    
}
