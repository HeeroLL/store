package com.zebone.empi.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jws.WebService;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.zebone.empi.dao.DictionaryMapper;
import com.zebone.empi.dao.EmpiMatchLogMapper;
import com.zebone.empi.dao.EmpiUnbindApplyMapper;
import com.zebone.empi.dao.PullFailLogMapper;
import com.zebone.empi.dao.ResidentCardMapper;
import com.zebone.empi.dao.ResidentInfoLogMapper;
import com.zebone.empi.dao.ResidentInfoMapper;
import com.zebone.empi.dao.ResidentUpdateApplyMapper;
import com.zebone.empi.oneCard.CancelRequest;
import com.zebone.empi.oneCard.CancelResult;
import com.zebone.empi.oneCard.LevelChangeRequest;
import com.zebone.empi.oneCard.LevelChangeResult;
import com.zebone.empi.oneCard.PullFailLog;
import com.zebone.empi.oneCard.SearchRequest;
import com.zebone.empi.oneCard.SearchResult;
import com.zebone.empi.oneCard.SearchResult.CardInfo;
import com.zebone.empi.oneCard.SearchResult.PersonalInfo;
import com.zebone.empi.service.BizLogService;
import com.zebone.empi.service.CheckerService;
import com.zebone.empi.service.EmpiService;
import com.zebone.empi.service.ResidentInfoOperationState;
import com.zebone.empi.util.CheckCard;
import com.zebone.empi.util.IDCardValidate;
import com.zebone.empi.util.ObjectReflect;
import com.zebone.empi.util.UUIDUtil;
import com.zebone.empi.util.XmlManage;
import com.zebone.empi.vo.ApplyUnbindRequest;
import com.zebone.empi.vo.ApplyUnbindResult;
import com.zebone.empi.vo.ApplyUpdateReq;
import com.zebone.empi.vo.ApplyUpdateRes;
import com.zebone.empi.vo.CheckResult;
import com.zebone.empi.vo.EmpiLog;
import com.zebone.empi.vo.EmpiMatchLog;
import com.zebone.empi.vo.EmpiUnbindApply;
import com.zebone.empi.vo.ResidentCard;
import com.zebone.empi.vo.ResidentInfo;
import com.zebone.empi.vo.ResidentUpdateApply;

/**
 * EMPI接口实现类
 * 
 * @author YinCm
 * @version 2013-7-31 下午10:10:20
 */
@Service("EmpiServiceImpl")
@WebService
public class EmpiServiceImpl implements EmpiService {

	private static final Log log = LogFactory.getLog(EmpiServiceImpl.class);

	@Resource
	private ResidentCardMapper residentCardMapper;

	@Resource
	private ResidentInfoMapper residentInfoMapper;

	@Resource
	private EmpiMatchLogMapper empiMatchLogMapper;

	@Resource
	private PullFailLogMapper pullFailLogMapper;

	@Resource
	private DictionaryMapper dictionaryMapper;

	@Resource
	private ResidentInfoLogMapper residentInfoLogMapper;

	@Resource
	private ResidentUpdateApplyMapper updateApplyMapper;

	@Resource
	private EmpiUnbindApplyMapper unbindApplyMapper;

	@Resource
	private BizLogService bizLogService;

	@Resource
	private CheckerService checkerService;

	// 队列名称
	@Value("${queueName}")
	private String queueName;

	// 机构编码列表
	@Value("${queueOrg}")
	private String queueOrg;

	@Value("${brokerURL}")
	private String brokerURL;

	@Value("${iswmq}")
	private String isWmq;

	@Value("${wmq.host}")
	private String wmqHost;

	@Value("${wmq.port}")
	private int wmqPort;

	@Value("${wmq.channel}")
	private String wmqChannel;

	@Value("${wmq.queuemanager}")
	private String wmqQueueManager;

	@Value("${wmq.transporttype}")
	private String wmqTransportType;

	/**
	 * 
	 * @see com.zebone.empi.service.EmpiService#registerOrUpdate(java.lang.String)
	 */
	@Override
	public String registerOrUpdate(String empiInfo) {
		// 新建log
		EmpiLog empiLog = new EmpiLog();
		empiLog.setTarget("1");
		empiLog.setEventNameCode("1");
		empiLog.setSource("1");
		empiLog.setEventTime(new Date());
		ResidentInfo residentInfo = null;
		String orgName = ""; // 机构名称，用于注册或更新成功后的返回信息
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			residentInfo = XmlManage
					.xmlToResidentInfo(empiInfo.trim(), empiLog);
		} catch (Exception e) {
			empiLog.setErrorCategory("4");
			empiLog.setResultCode("2");
			empiLog.setErrorType("1");
			empiLog.setErrorPosition("xml参数格式错误");
			empiLog.setInfo_XMLDoc(empiInfo);
			bizLogService.log(empiLog);
			return "01|xml格式错误";
		}
		// 补全empilog基本信息
		List<ResidentCard> firstClassCards = residentInfo.getFirstClassCards();
		if (firstClassCards != null && firstClassCards.size() > 0) {
			ResidentCard firstCard = firstClassCards.get(0);
			if (firstCard != null) {
				empiLog.setResidentName(residentInfo.getName());
				empiLog.setCardNo(firstCard.getCardNo());
				empiLog.setCardType(firstCard.getCardType());
				empiLog.setOrgCode(firstCard.getCardOrg());
			}
		}
		// 如果证件为身份证，则通过身份证获取年龄和性别

		// 格式校验通过，获取部分日志字段
		empiLog.setOrgCode(residentInfo.getDeptCode());
		empiLog.setResidentName(residentInfo.getName());
		for (ResidentCard rc : residentInfo.getCardList()) {
			if (rc.getCardType().trim().equals("1")
					|| rc.getCardType().trim().equals("2")
					|| rc.getCardType().trim().equals("3")) {
				empiLog.setCardNo(rc.getCardNo());
				empiLog.setCardType(rc.getCardType());
				if (rc.getCardType() != null) {
					rc.setCardOrg("A1");
				}
			}
			if (rc.getCardType() != null) {
				if (rc.getCardType().trim().equals("4")) {
					rc.setCardOrg("A2");
				}
				if (rc.getCardType().trim().equals("6")) {
					rc.setCardOrg("B3");
				}
				if (rc.getCardType().trim().equals("7")) {
					rc.setCardOrg("B4");
				}
				if (rc.getCardType().trim().equals("8")) {
					rc.setCardOrg("B5");
				}
				// 如果是院内主索引或院内就诊卡，则必须填写发卡机构代码
				if ("9".equals(rc.getCardType().trim())
						|| "10".equals(rc.getCardType().trim())) {
					String cardOrg = rc.getCardOrg();
					if (cardOrg == null || "".equals(cardOrg)) {
						String errorPosition = rc.getCardNo() + ":发卡机构为空";
						empiLog.setErrorCategory("2");
						empiLog.setResultCode("2");
						empiLog.setErrorPosition(errorPosition);
						bizLogService.log(empiLog);
						return "02|校验时出现错误|" + errorPosition;
					}
				}
			}
		}

		CheckResult checkResult = null;

		try {
			checkResult = checkerService.doCheck(residentInfo, empiLog,
					empiInfo);
		} catch (Exception e) {
			empiLog.setErrorCategory("2");
			empiLog.setResultCode("2");
			e.printStackTrace();
			return "02|校验时出现错误";
		}

		if (checkResult.getResultCode() == 1) {
			try {
				String residentInfoImpi = registerEmpi(residentInfo);
				residentInfo.setEmpi(residentInfoImpi);
				orgName = dictionaryMapper.getOrgName(residentInfo
						.getDeptCode());
			} catch (Exception e) {
				empiLog.setErrorCategory("2");
				empiLog.setResultCode("2");
				empiLog.setEmpi(residentInfo.getEmpi());
				empiLog.setErrorPosition("插入数据库时错误！");
				bizLogService.log(empiLog);
				log.error(e.getMessage(), e);
				return "03|注册过程中产生错误";
			}
			empiLog.setResultCode("1");
			empiLog.setEmpi(residentInfo.getEmpi());
			bizLogService.log(empiLog);
			bizLogService.log(residentInfo, ResidentInfoOperationState.Add);
			return "1|" + residentInfo.getEmpi() + "|" + orgName + "于"
					+ sdf.format(new Date()) + "注册成功";
		} else if (checkResult.getResultCode() == 2) {
			try {
				updateEmpiN(residentInfo, checkResult);
				orgName = dictionaryMapper.getOrgName(residentInfo
						.getDeptCode());
			} catch (Exception e) {
				empiLog.setErrorCategory("2");
				empiLog.setResultCode("2");
				bizLogService.log(empiLog);
				log.error(e.getMessage(), e);
				return "03|注册过程中产生错误";
			}
			empiLog.setResultCode("1");

			bizLogService.log(empiLog);
			bizLogService.log(residentInfo, ResidentInfoOperationState.Update);

			return "1|" + residentInfo.getEmpi() + "|" + orgName + "于"
					+ sdf.format(new Date()) + "更新成功";
		} else {
			empiLog.setErrorCategory("1");
			empiLog.setErrorType("2");
			empiLog.setResultCode("2");
			empiLog.setEmpi(residentInfo.getEmpi());
			bizLogService.log(empiLog);
			String p = empiLog.getErrorPosition();
			return "02|校验时出现错误|" + p;
		}

	}

	@Override
	public String searchEmpiId(String paramXml) throws Exception {
		ResidentInfo ri = null;
		try {
			ri = searchEmpiInfoObj(paramXml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if ("01|未注册".equals(e.getMessage())) {
				return "01|未注册";
			} else if ("02|未匹配".equals(e.getMessage())) {
				return "02|未匹配";
			} else if ("03|参数错误".equals(e.getMessage())) {
				return "03|参数错误";
			} else {
				return "04|未知错误";
			}
		}
		return ri.getEmpi();
	}

	@Override
	public String searchEmpiInfo(String paramXml) throws Exception {
		ResidentInfo ri = null;
		try {
			ri = searchEmpiInfoObj(paramXml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if ("01|未注册".equals(e.getMessage())) {
				return "01|未注册";
			} else if ("02|未匹配".equals(e.getMessage())) {
				return "02|未匹配";
			} else if ("03|参数错误".equals(e.getMessage())) {
				return "03|参数错误";
			} else {
				return "04|未知错误";
			}
		}
		List<ResidentCard> cardList = residentCardMapper.selectByEmpi(ri
				.getEmpi());
		ri.setCardList(cardList);
		String riMxl = XmlManage.ResidentInfoToXml(ri);
		return riMxl;
	}

	@Override
	public String getEmpiInfo(String paramXml) throws Exception {
		String empiInfo = null;
		XStream xs = new XStream(new DomDriver("UTF-8", new NoNameCoder()));
		SearchRequest searchRequest = (SearchRequest) getRequestParObj(xs,
				"卡查询", paramXml);
		SearchResult searchResult = new SearchResult();
		ResidentInfo residentInfo = null;
		PersonalInfo personalInfo = null;
		List<ResidentCard> residentCardLic = null;
		List<CardInfo> cardInfoLic = null;
		boolean isSuccess = true;
		try {
			if (searchRequest == null) {
				isSuccess = false;
				searchResult.setErrorCode("2");
				searchResult.setErrorMsg("xml参数格式错误");
			} else if (searchRequest.getCardNo() == null
					|| "".equals(searchRequest.getCardNo())
					|| searchRequest.getCardType() == null
					|| "".equals(searchRequest.getCardType())) {
				isSuccess = false;
				searchResult.setErrorCode("2");
				searchResult.setErrorMsg("卡号和卡类型都不能为空");
			} else {
				Map<String, String> parMap = new HashMap<String, String>();
				parMap.put("cardType", searchRequest.getCardType());
				parMap.put("cardNo", searchRequest.getCardNo());
				residentCardLic = residentCardMapper
						.getResidentCardInfo(parMap);
				if (residentCardLic == null || residentCardLic.size() == 0) {
					isSuccess = false;
					searchResult.setErrorCode("1"); // 1 表示未注册
					searchResult.setErrorMsg("卡未注册");
				} else {
					String empiNo = residentCardLic.get(0).getEmpi();
					String cardType = residentCardLic.get(0).getCardType();
					cardInfoLic = new ArrayList<CardInfo>();

					// 如果是通过一级标识进行卡查询，必须返回所有的卡信息
					if ("1".equals(cardType) || "2".equals(cardType)
							|| "3".equals(cardType)) {
						// 根据empi号获取所有的卡信息
						residentCardLic = residentCardMapper
								.selectByEmpi(empiNo);
					} else { // 如果是通过其他标识进行卡查询，返回该卡信息和所对应的一级标识信息
						String[] cardTypes = new String[] { "1", "2", "3" }; // 一级标识卡类型列表
						Map<String, Object> oMap = new HashMap<String, Object>();
						oMap.put("empi", empiNo);
						oMap.put("cardTypes", Arrays.asList(cardTypes));
						// 获取相对应的一级标识卡信息
						List<ResidentCard> firstLevelCards = residentCardMapper
								.getFirstLevelCards(oMap);
						residentCardLic.addAll(firstLevelCards);
					}

					for (ResidentCard residentCard : residentCardLic) {
						CardInfo cardInfo = new CardInfo();
						BeanUtils.copyProperties(cardInfo, residentCard);
						cardInfo.setCreateDate(formatDate(residentCard
								.getCreateDate()));
						cardInfoLic.add(cardInfo);
					}

					// 获取主索引注册信息
					residentInfo = residentInfoMapper
							.selectResidentInfoByEmpi(empiNo);
					personalInfo = new PersonalInfo();
					BeanUtils.copyProperties(personalInfo, residentInfo);
					if (residentInfo.getPhoto() != null) {
						personalInfo.setPhoto(new String(residentInfo
								.getPhoto()));
					}
					personalInfo.setBirthday(formatDate(residentInfo
							.getBirthday()));
				}
			}
		} catch (Exception e) {
			isSuccess = false;
			searchResult.setErrorCode("2"); // 2 表示系统错误
			searchResult.setErrorMsg("系统出现异常");
			log.error(e.getMessage(), e);
		} finally {
			if (isSuccess) {
				searchResult.setSuccess("1"); // 1 表示查询成功
				searchResult.getItem().getCard().setCardInfoLic(cardInfoLic);
				searchResult.getItem().setPersonalInfo(personalInfo);
			} else {
				searchResult.setSuccess("2"); // 2 表示未查询成功
				searchResult.getItem().setPersonalInfo(null);
			}
			xs.processAnnotations(SearchResult.class);
			empiInfo = xs.toXML(searchResult);
		}
		return empiInfo;
	}

	@Override
	public String cancelCard(String paramXml) throws Exception {
		String returnInfo = null;
		XStream xs = new XStream(new DomDriver("UTF-8", new NoNameCoder()));
		CancelRequest cancelRequest = (CancelRequest) getRequestParObj(xs,
				"卡注销", paramXml);
		CancelResult cancelResult = new CancelResult();
		ResidentCard cancelCard = new ResidentCard();
		String cardNo = "";
		boolean isSuccess = true;
		List<ResidentCard> cancelCardLic = new ArrayList<ResidentCard>();
		String empiNo = "";
		ResidentInfo residentInfo = new ResidentInfo();
		try {
			if (cancelRequest == null) {
				isSuccess = false;
				cancelResult.setErrorCode("2");
				cancelResult.setErrorMsg("xml参数格式错误");
			} else if (cancelRequest.getCardNo() == null
					|| "".equals(cancelRequest.getCardNo())) {
				isSuccess = false;
				cancelResult.setErrorCode("2");
				cancelResult.setErrorMsg("卡号不能为空");
			} else {
				cardNo = cancelRequest.getCardNo();
				List<ResidentCard> residentCards = residentCardMapper
						.selectByCardNo(cardNo);
				if (residentCards == null || residentCards.size() == 0) {
					isSuccess = false;
					cancelResult.setErrorCode("1");
					cancelResult.setErrorMsg("卡信息不存在");
				} else {
					for (ResidentCard residentCard : residentCards) {
						if ("10".equals(residentCard.getCardType())) { // 10
																		// 表示院内就诊卡，可注销
							cancelCardLic.add(residentCard);
						}
					}
					if (cancelCardLic.size() == 0) {
						isSuccess = false;
						cancelResult.setErrorCode("1");
						cancelResult.setErrorMsg("非就诊卡,不可注销");
					} else {
						cancelCard = cancelCardLic.get(0);
						empiNo = cancelCard.getEmpi();
						residentInfo = residentInfoMapper
								.selectResidentInfoByEmpi(empiNo);
						if ("2".equals(cancelCard.getCardStatus())) { // 2
																		// 表示该卡已是注销状态
							isSuccess = false;
							cancelResult.setErrorCode("1");
							cancelResult.setErrorMsg("不可重复注销");
						} else {
							cancelCard.setCancelDate(new Date());
							residentCardMapper.updateCardStatus(cancelCard);
							cancelCardLic.get(0).setCardStatus("2");
						}
					}
				}
			}
		} catch (Exception e) {
			isSuccess = false;
			cancelResult.setErrorCode("2"); // 2 表示系统错误
			cancelResult.setErrorMsg("系统出现异常");
			log.error(e.getMessage(), e);
		} finally {
			// 根据empi号获取相对应的一级标识卡信息
			String[] cardTypes = new String[] { "1", "2", "3" }; // 一级标识卡类型列表
			Map<String, Object> oMap = new HashMap<String, Object>();
			oMap.put("empi", empiNo);
			oMap.put("cardTypes", Arrays.asList(cardTypes));
			List<ResidentCard> firstLevelCards = residentCardMapper
					.getFirstLevelCards(oMap);

			if (isSuccess) {
				cancelResult.setSuccess("1"); // 注销成功
				cancelResult.getCancelItem().setCardNo(cardNo);
				insertCancelLog(isSuccess, empiNo, residentInfo.getName(),
						firstLevelCards, cancelResult);
				// 获取推送信息
				SearchResult pushInfo = new SearchResult();
				pushInfo.setSuccess("1"); // 注销成功
				pushInfo.setType("cardCancel");
				// 为pushInfo填充卡信息
				List<CardInfo> cardInfoLic = new ArrayList<CardInfo>();

				// 将一级标识加入到推送信息卡列表中
				cancelCardLic.addAll(firstLevelCards);

				for (ResidentCard residentCard : cancelCardLic) {
					CardInfo cardInfo = new CardInfo();
					BeanUtils.copyProperties(cardInfo, residentCard);
					cardInfo.setCreateDate(formatDate(residentCard
							.getCreateDate()));
					cardInfoLic.add(cardInfo);
				}
				pushInfo.getItem().getCard().setCardInfoLic(cardInfoLic);

				// 为pushInfo填充主索引信息
				PersonalInfo personalInfo = new PersonalInfo();
				BeanUtils.copyProperties(personalInfo, residentInfo);
				if (residentInfo.getPhoto() != null) {
					personalInfo.setPhoto(new String(residentInfo.getPhoto()));
				}
				personalInfo
						.setBirthday(formatDate(residentInfo.getBirthday()));
				pushInfo.getItem().setPersonalInfo(personalInfo);
				xs.processAnnotations(SearchResult.class);
				String pushXml = xs.toXML(pushInfo);
				String[] pushOrgLic = getPushOrgList();
				if (pushOrgLic != null && pushOrgLic.length > 0) {
					for (String orgCode : pushOrgLic) {
						String queue = queueName + "." + orgCode;
						try {
							pushInfo(pushXml, queue);
						} catch (Exception e) {
							// 插入推送失败日志信息
							PullFailLog pullFailLog = new PullFailLog();
							pullFailLog.setId(UUIDUtil.getUuid());
							pullFailLog.setPullContent(pushXml);
							pullFailLog.setPullQueue(queue);
							pullFailLog.setPullType("0");// 卡注销
							pullFailLogMapper.insertPullFailLog(pullFailLog);
							log.error(e.getMessage(), e);
						}
					}
				}
			} else {
				cancelResult.setSuccess("2"); // 注销失败
				cancelResult.getCancelItem().setCardNo(cardNo);
				insertCancelLog(isSuccess, empiNo, residentInfo.getName(),
						firstLevelCards, cancelResult);
			}
			xs.processAnnotations(CancelResult.class);
			returnInfo = xs.toXML(cancelResult);
		}
		return returnInfo;
	}

	@Override
	public String changeCardLevel(String paramXml) throws Exception {
		String returnInfo = null;
		XStream xs = new XStream(new DomDriver("UTF-8", new NoNameCoder()));
		LevelChangeRequest levelRequest = (LevelChangeRequest) getRequestParObj(
				xs, "卡变更", paramXml);
		LevelChangeResult levelResult = new LevelChangeResult();
		String cardNo = "";
		String cardType = "";
		String starLevel = "";
		String empiNo = "";
		ResidentInfo residentInfo = new ResidentInfo();
		String name = "";
		boolean isSuccess = true;
		List<ResidentCard> changeCardLic;
		List<ResidentCard> firstCardLic;
		try {
			if (levelRequest == null) {
				isSuccess = false;
				levelResult.setErrorCode("2");
				levelResult.setErrorMsg("xml参数格式错误");
			} else if (levelRequest.getCardNo() == null
					|| "".equals(levelRequest.getCardNo())
					|| levelRequest.getCardType() == null
					|| "".equals(levelRequest.getCardType())
					|| levelRequest.getStarLevel() == null
					|| "".equals(levelRequest.getStarLevel())) {
				isSuccess = false;
				levelResult.setErrorCode("2");
				levelResult.setErrorMsg("卡号、卡类型和提升等级值都不能为空");
			} else {
				cardNo = levelRequest.getCardNo();
				cardType = levelRequest.getCardType();
				starLevel = levelRequest.getStarLevel();
				if ("1".equals(cardType) || "2".equals(cardType)
						|| "3".equals(cardType)) { // 1 2 3 代表一级标识卡类型
					if ("0".equals(starLevel) || "1".equals(starLevel)
							|| "2".equals(starLevel)) { // 居民等级 1:vip 2:dsc
						Map<String, String> parMap = new HashMap<String, String>();
						parMap.put("cardType", cardType);
						parMap.put("cardNo", cardNo);
						changeCardLic = residentCardMapper
								.getResidentCardInfo(parMap);
						if (changeCardLic == null || changeCardLic.size() == 0) {
							isSuccess = false;
							levelResult.setErrorCode("1");
							levelResult.setErrorMsg("卡信息不存在");
						} else {
							Map<String, Object> updatePar = new HashMap<String, Object>();
							empiNo = changeCardLic.get(0).getEmpi();
							updatePar.put("starLevel", starLevel);
							updatePar.put("empiNo", empiNo);
							updatePar.put("modifiedTime", new Date());
							residentInfoMapper.updateStarLevel(updatePar);
							residentInfo = residentInfoMapper
									.selectResidentInfoByEmpi(empiNo);
							name = residentInfo.getName();
						}
					} else {
						isSuccess = false;
						levelResult.setErrorCode("2");
						levelResult.setErrorMsg("人员等级值错误");
					}
				} else {
					isSuccess = false;
					levelResult.setErrorCode("2");
					levelResult.setErrorMsg("卡类型非一级标识类型");
				}
			}
		} catch (Exception e) {
			isSuccess = false;
			levelResult.setErrorCode("2"); // 2 表示系统错误
			levelResult.setErrorMsg("系统出现异常");
			log.error(e.getMessage(), e);
		} finally {
			// 根据empi号获取相对应的一级标识卡信息
			String[] cardTypes = new String[] { "1", "2", "3" }; // 一级标识卡类型列表
			Map<String, Object> oMap = new HashMap<String, Object>();
			oMap.put("empi", empiNo);
			oMap.put("cardTypes", Arrays.asList(cardTypes));
			firstCardLic = residentCardMapper.getFirstLevelCards(oMap);

			if (isSuccess) {
				levelResult.setSuccess("1"); // 卡等变更成功
				levelResult.getLevelChangeItem().setEmpi(empiNo);
				levelResult.getLevelChangeItem().setName(name);
				levelResult.getLevelChangeItem().setStarLevel(starLevel);
				insertLevelChangeLog(isSuccess, empiNo, name, firstCardLic,
						levelResult);
				// 获取推送信息
				SearchResult pushInfo = new SearchResult();
				pushInfo.setSuccess("1"); // 卡等变更成功
				pushInfo.setType("cardLevelChange");

				// 为pushInfo填充卡信息,卡等变更，需要推送所有卡信息
				List<CardInfo> cardInfoLic = new ArrayList<CardInfo>();
				changeCardLic = residentCardMapper.selectByEmpi(empiNo);
				for (ResidentCard residentCard : changeCardLic) {
					CardInfo cardInfo = new CardInfo();
					BeanUtils.copyProperties(cardInfo, residentCard);
					cardInfo.setCreateDate(formatDate(residentCard
							.getCreateDate()));
					cardInfoLic.add(cardInfo);
				}
				pushInfo.getItem().getCard().setCardInfoLic(cardInfoLic);

				// 为pushInfo填充主索引信息

				PersonalInfo personalInfo = new PersonalInfo();
				BeanUtils.copyProperties(personalInfo, residentInfo);
				if (residentInfo.getPhoto() != null) {
					personalInfo.setPhoto(new String(residentInfo.getPhoto()));
				}
				personalInfo
						.setBirthday(formatDate(residentInfo.getBirthday()));
				pushInfo.getItem().setPersonalInfo(personalInfo);
				xs.processAnnotations(SearchResult.class);
				String pushXml = xs.toXML(pushInfo);
				String[] pushOrgLic = getPushOrgList();
				if (pushOrgLic != null && pushOrgLic.length > 0) {
					for (String orgCode : pushOrgLic) {
						String queue = queueName + "." + orgCode;
						try {
							pushInfo(pushXml, queue);
						} catch (Exception e) {
							// 插入推送失败日志信息
							PullFailLog pullFailLog = new PullFailLog();
							pullFailLog.setId(UUIDUtil.getUuid());
							pullFailLog.setPullContent(pushXml);
							pullFailLog.setPullQueue(queue);
							pullFailLog.setPullType("1");// 卡等变更
							pullFailLogMapper.insertPullFailLog(pullFailLog);
							log.error(e.getMessage(), e);
						}
					}
				}
			} else {
				levelResult.setSuccess("2"); // 卡等变更失败
				insertLevelChangeLog(isSuccess, empiNo, name, firstCardLic,
						levelResult);
			}
			xs.processAnnotations(LevelChangeResult.class);
			returnInfo = xs.toXML(levelResult);
		}
		return returnInfo;
	}

	@Override
	public String applyForUpdate(String paramXml) throws Exception {
		String returnInfo;
		EmpiLog empiLog = new EmpiLog();
		empiLog.setInfo_XMLDoc(paramXml);
		XStream xs = new XStream(new DomDriver("UTF-8", new NoNameCoder()));
		ApplyUpdateReq applyRequest;
		ApplyUpdateRes applyResult = new ApplyUpdateRes();
		boolean isSuccess = true;
		String cardNo;
		try {
			boolean xsdCheck = checkerService.xsdCheckForApply(paramXml,
					empiLog, "实名变更");
			if (xsdCheck) { // 参数结构校验通过
				applyRequest = (ApplyUpdateReq) getRequestParObj(xs, "实名变更申请",
						paramXml);
				cardNo = applyRequest.getCardNo();

				// 填充返回对象
				applyResult.getUpdateApplyItem().setCardNo(cardNo);

				String strIdValidate = "";
				// 身份证有效性的校验
				strIdValidate = IDCardValidate.IDCardValidate(applyRequest
						.getCardNo());
				if (!"".equals(strIdValidate)) { // 身份证不合法
					isSuccess = false;
					applyResult.setErrorCode("04"); // 参数值域错误
					applyResult.setErrorMsg(strIdValidate);
				} else {
					// 填充日志信息
					empiLog.setCardNo(cardNo);
					empiLog.setOrgCode(applyRequest.getOrgCode());
					empiLog.setCardType("1");

					List<ResidentCard> residentCards = residentCardMapper
							.selectByCardNo(cardNo);
					if (residentCards == null || residentCards.size() == 0) {
						isSuccess = false;
						applyResult.setErrorCode("02");
						applyResult.setErrorMsg("一级标识" + cardNo + "未注册");
					} else {
						String empiNo = residentCards.get(0).getEmpi();
						empiLog.setEmpi(empiNo);
						ResidentInfo residentInfo = residentInfoMapper
								.selectResidentInfoByEmpi(empiNo);
						ResidentUpdateApply updateApplyRec = updateApplyMapper
								.getUpdateApplyRecord(empiNo);
						empiLog.setResidentName(residentInfo.getName());
						if (updateApplyRec != null) {
							isSuccess = false;
							applyResult.setErrorCode("01");
							applyResult.setErrorMsg("该请求卡存在未处理的申请");
						} else {
							ResidentUpdateApply updateApplyInfo = new ResidentUpdateApply();
							BeanUtils.copyProperties(updateApplyInfo,
									applyRequest);
							updateApplyInfo.setId(UUIDUtil.getUuid());
							updateApplyInfo.setEmpi(empiNo);
							updateApplyInfo.setPhoto(applyRequest.getStrPhoto()
									.getBytes());
							SimpleDateFormat df = new SimpleDateFormat(
									"yyyyMMdd");
							String strBirthday = applyRequest.getStrBirthday();
							if (strBirthday != null && !"".equals(strBirthday)) {
								Date birthday = df.parse(strBirthday);
								updateApplyInfo.setBirthday(birthday);
							}
							updateApplyInfo.setApplyTime(new Date());
							updateApplyMapper
									.saveUpdateApplyInfo(updateApplyInfo);
						}
					}
				}
			} else {
				isSuccess = false;
				applyResult.setErrorCode("03"); // 参数结构错误
				applyResult.setErrorMsg(empiLog.getErrorPosition());
			}
		} catch (Exception e) {
			isSuccess = false;
			applyResult.setErrorCode("05"); // 05表示系统错误
			applyResult.setErrorMsg("系统出现异常");
			log.error(e.getMessage(), e);
		} finally {
			if (isSuccess) {
				applyResult.setSuccess("1"); // 1 表示申请成功
				applyResult.setSuccessMsg("你的申请已经接收,该申请会在两个工作日内处理完毕");
			} else {
				applyResult.setSuccess("2"); // 2 表示申请失败
			}
			insertUpdateApplyLog(isSuccess, empiLog, applyResult); // 插入日志记录
			xs.processAnnotations(ApplyUpdateRes.class);
			returnInfo = xs.toXML(applyResult);
		}
		return returnInfo;
	}

	@Override
	public String applyForUnbind(String paramXml) throws Exception {
		String returnInfo;
		EmpiLog empiLog = new EmpiLog();
		empiLog.setInfo_XMLDoc(paramXml);
		XStream xs = new XStream(new DomDriver("UTF-8", new NoNameCoder()));
		ApplyUnbindRequest applyUnbindRequest;
		ApplyUnbindResult applyUnbindResult = new ApplyUnbindResult(); // 申请返回消息对象
		boolean isSuccess = true;
		try {
			boolean xsdCheck = checkerService.xsdCheckForApply(paramXml,
					empiLog, "解绑");
			if (xsdCheck) { // 参数结构校验通过
				applyUnbindRequest = (ApplyUnbindRequest) getRequestParObj(xs,
						"解绑申请", paramXml);
				String firstCardType = applyUnbindRequest.getFirstCardType();
				String strIdValidate = "";
				if ("1".equals(firstCardType)) { // 身份证有效性的校验
					strIdValidate = IDCardValidate
							.IDCardValidate(applyUnbindRequest.getFirstCardNo());
				}
				if (!"".equals(strIdValidate)) { // 身份证不合法
					isSuccess = false;
					applyUnbindResult.setErrorCode("05"); // 参数值域错误
					applyUnbindResult.setErrorMsg(strIdValidate);
				} else {
					String secCardNo = applyUnbindRequest.getSecCardNo(); // 需要解绑的二级标识号
					String secCardType = applyUnbindRequest.getSecCardType();
					String secCardOrg = applyUnbindRequest.getSecCardOrg();

					ResidentCard residentCard = new ResidentCard();
					residentCard.setCardNo(secCardNo);
					residentCard.setCardType(secCardType);
					residentCard.setCardOrg(secCardOrg);

					// 填充返回对象
					applyUnbindResult.getUnbindItem().setCardType(secCardType);
					applyUnbindResult.getUnbindItem().setCardNo(secCardNo);
					applyUnbindResult.getUnbindItem().setCardOrg(secCardOrg);

					empiLog.setOrgCode(applyUnbindRequest.getOrgCode());
					// 根据卡号，卡类型和发卡机构获取二级标识卡列表
					List<ResidentCard> residentCards = residentCardMapper
							.selectByCodeAndTypeAndDept(residentCard);
					if (residentCards == null || residentCards.size() == 0) {
						isSuccess = false;
						applyUnbindResult.setErrorCode("02");
						applyUnbindResult.setErrorMsg("二级标识" + secCardNo
								+ "未注册");
					} else {
						String empiNo = residentCards.get(0).getEmpi(); // 二级标识已绑定的主索引号
						empiLog.setEmpi(empiNo);

						// 根据提供的新的一级标识号获取主索引
						// String newEmpiNo =
						// residentCardMapper.selectByFirstLevelCardId(applyUnbindRequest.getFirstCardNo());
						EmpiUnbindApply unbindApplyRec = unbindApplyMapper
								.getUnbindApplyRecord(residentCard);
						if (unbindApplyRec != null) {
							isSuccess = false;
							applyUnbindResult.setErrorCode("01");
							applyUnbindResult.setErrorMsg("该请求卡存在未处理的申请");
						}
						/**
						 * else if (newEmpiNo != null &&
						 * newEmpiNo.equals(empiNo)) { isSuccess = false;
						 * applyUnbindResult.setErrorCode("03");
						 * applyUnbindResult
						 * .setErrorMsg("新的一级标识与系统内已绑定的一级标识相同"); }
						 **/
						else {
							EmpiUnbindApply empiUnbindApply = new EmpiUnbindApply();
							BeanUtils.copyProperties(empiUnbindApply,
									applyUnbindRequest);
							empiUnbindApply.setId(UUIDUtil.getUuid());
							empiUnbindApply.setEmpi(empiNo);
							empiUnbindApply.setPhoto(applyUnbindRequest
									.getStrPhoto().getBytes());
							SimpleDateFormat df = new SimpleDateFormat(
									"yyyyMMdd");
							String strBirthday = applyUnbindRequest
									.getStrBirthday();
							if (strBirthday != null && !"".equals(strBirthday)) {
								Date birthday = df.parse(strBirthday);
								empiUnbindApply.setBirthday(birthday);
							}
							empiUnbindApply.setApplyTime(new Date());
							unbindApplyMapper
									.saveUnbindApplyInfo(empiUnbindApply);
							applyUnbindResult.setUnbindApplyId(empiUnbindApply
									.getId());
						}
					}
				}
			} else {
				isSuccess = false;
				applyUnbindResult.setErrorCode("04"); // 参数结构错误
				applyUnbindResult.setErrorMsg(empiLog.getErrorPosition());
			}
		} catch (Exception e) {
			isSuccess = false;
			applyUnbindResult.setErrorCode("06");
			applyUnbindResult.setErrorMsg("系统出现异常");
			log.error(e.getMessage(), e);
		} finally {
			if (isSuccess) {
				applyUnbindResult.setSuccess("1"); // 1 表示申请成功
				applyUnbindResult.setSuccessMsg("你的申请已经接收,该申请会在两个工作日内处理完毕");
			} else {
				applyUnbindResult.setSuccess("2"); // 2 表示申请失败
			}
			insertUnbindApplyLog(isSuccess, empiLog, applyUnbindResult); // 插入日志记录
			xs.processAnnotations(ApplyUnbindResult.class);
			returnInfo = xs.toXML(applyUnbindResult);
		}
		return returnInfo;
	}

	/**
	 * 插入卡注销日志
	 * 
	 * @param
	 * @throws Exception
	 */
	public void insertCancelLog(boolean flag, String empiNo, String name,
			List firstLevelCards, CancelResult cancelResult) throws Exception {
		EmpiLog empiLog = new EmpiLog();
		empiLog.setSource("3"); // 卡注销事件源
		empiLog.setTarget("3");// 事件目标 卡注销
		empiLog.setEventNameCode("10");// 就诊卡注销
		empiLog.setEmpi(empiNo);
		empiLog.setResidentName(name);
		empiLog.setEventTime(new Date());
		// 将对应的一级标识信息填充到日志信息中
		if (firstLevelCards != null && firstLevelCards.size() > 0) {
			ResidentCard firstCard = (ResidentCard) firstLevelCards.get(0);
			if (firstCard != null) {
				empiLog.setCardNo(firstCard.getCardNo());
				empiLog.setCardType(firstCard.getCardType());
				empiLog.setOrgCode(firstCard.getCardOrg());
			}
		}
		if (flag) {// 注销成功
			empiLog.setResultCode("1");
		} else {// 注销失败
			empiLog.setResultCode("2");
			empiLog.setErrorCategory("5"); // 就诊卡注销错误
			empiLog.setErrorPosition(cancelResult.getErrorMsg());
		}
		try {
			bizLogService.log(empiLog);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new Exception(e);
		}
	}

	/**
	 * 插入卡等变更日志
	 * 
	 * @param
	 * @throws Exception
	 */
	public void insertLevelChangeLog(boolean flag, String empiNo, String name,
			List firstLevelCards, LevelChangeResult levelResult)
			throws Exception {
		EmpiLog empiLog = new EmpiLog();
		empiLog.setSource("4"); // 卡等变更事件源
		empiLog.setTarget("4");// 事件目标 卡等变更
		empiLog.setEventNameCode("11");// 卡等变更
		empiLog.setEmpi(empiNo);
		empiLog.setEventTime(new Date());
		empiLog.setResidentName(name);
		// 将对应的一级标识信息填充到日志信息中
		if (firstLevelCards != null && firstLevelCards.size() > 0) {
			ResidentCard firstCard = (ResidentCard) firstLevelCards.get(0);
			if (firstCard != null) {
				empiLog.setCardNo(firstCard.getCardNo());
				empiLog.setCardType(firstCard.getCardType());
				empiLog.setOrgCode(firstCard.getCardOrg());
			}
		}
		if (flag) {// 变更成功
			empiLog.setResultCode("1");
		} else {// 变更失败
			empiLog.setResultCode("2");
			empiLog.setErrorCategory("6"); // 卡等变更错误
			empiLog.setErrorPosition(levelResult.getErrorMsg());
		}
		try {
			bizLogService.log(empiLog);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new Exception(e);
		}
	}

	/**
	 * 插入实名信息更新申请日志
	 * 
	 * @param
	 * @throws Exception
	 */
	public void insertUpdateApplyLog(boolean flag, EmpiLog empiLog,
			ApplyUpdateRes applyUpdateRes) throws Exception {
		empiLog.setSource("5"); // 实名信息更新申请事件源
		empiLog.setTarget("5");// 事件目标 实名信息变更申请
		empiLog.setEventNameCode("12");// 实名信息变更申请
		empiLog.setEventTime(new Date());
		if (flag) {// 申请成功
			empiLog.setResultCode("1");
		} else {// 申请失败
			empiLog.setResultCode("2");
			empiLog.setErrorCategory("7"); // 实名信息变更申请错误
			empiLog.setErrorPosition(applyUpdateRes.getErrorMsg());
		}
		try {
			bizLogService.log(empiLog);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new Exception(e);
		}
	}

	/**
	 * 插入主索引解绑申请日志
	 * 
	 * @param
	 * @throws Exception
	 */
	public void insertUnbindApplyLog(boolean flag, EmpiLog empiLog,
			ApplyUnbindResult unbindResult) throws Exception {
		empiLog.setSource("6"); // 主索引解绑申请事件源
		empiLog.setTarget("6");// 事件目标 主索引解绑申请
		empiLog.setEventNameCode("13");// 主索引解绑申请
		empiLog.setEventTime(new Date());

		// 要求解绑的二级标识对应的主索引号
		String empi = empiLog.getEmpi();
		if (empi != null && !"".equals(empi)) {
			// 获取要求解绑的二级标识所对应的一级标识卡信息
			String[] cardTypes = new String[] { "1", "2", "3" }; // 一级标识卡类型列表
			Map<String, Object> oMap = new HashMap<String, Object>();
			oMap.put("empi", empi);
			oMap.put("cardTypes", Arrays.asList(cardTypes));
			ResidentInfo residentInfo = residentInfoMapper
					.selectResidentInfoByEmpi(empi);
			List<ResidentCard> firstCardLic = residentCardMapper
					.getFirstLevelCards(oMap);
			if (firstCardLic != null && firstCardLic.size() > 0) {
				ResidentCard firstCard = (ResidentCard) firstCardLic.get(0);
				// 填充日志信息
				empiLog.setCardType(firstCard.getCardType());
				empiLog.setCardNo(firstCard.getCardNo());
				empiLog.setResidentName(residentInfo.getName());
			}
		}

		if (flag) {// 申请成功
			empiLog.setResultCode("1");
		} else {// 申请失败
			empiLog.setResultCode("2");
			empiLog.setErrorCategory("8"); // 主索引解绑申请错误
			empiLog.setErrorPosition(unbindResult.getErrorMsg());
		}
		try {
			bizLogService.log(empiLog);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new Exception(e);
		}
	}

	/**
	 * 信息推送
	 * 
	 * @param pushXml
	 */
	public void pushInfo(String pushXml, String queue) {
		if (pushXml == null || pushXml.equals("")) {
			throw new RuntimeException("不能发送空信息！");
		}
		ConnectionFactory connectionFactory = null;
		Connection connection = null;
		Session session = null;
		Destination destination = null;
		MessageProducer producer = null;
		boolean useTransaction = false;

		try {

			connectionFactory = new ActiveMQConnectionFactory(brokerURL);
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(useTransaction,
					Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue(queue);
			producer = session.createProducer(destination);
			TextMessage msg = session.createTextMessage();
			msg.setText(pushXml);
			producer.send(msg);

		} catch (Exception e) {
			log.error("", e);
			throw new RuntimeException(e);
		} finally {
			try {
				if (producer != null) {
					producer.close();
				}
				if (session != null) {
					session.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 查询empi用户信息对象
	 * 
	 * @param paramXml
	 * @return
	 * @throws Exception
	 */
	public ResidentInfo searchEmpiInfoObj(String paramXml) throws Exception {
		ResidentInfo residentInfo = null;
		try {
			residentInfo = XmlManage.xmlToResidentInfo(paramXml, null);
		} catch (Exception e) {
			throw new Exception("03|参数错误");
		}
		// 获取empiId
		String empiId = searchEmpiIdByCard(paramXml);

		// 此处name必须填入，查询时必须考虑姓名一致
		String system_code = residentInfo.getSystem_code();
		ResidentInfo ri = residentInfoMapper.selectResidentInfoByEmpi(empiId);

		// 如果注册中心的查询查到对应的记录，则插入一条状态为1的日志记录
		EmpiMatchLog record = ObjectReflect
				.convertResidentInfoToEmpiMatchLog(residentInfo);

		// 姓名未匹配
		if (record.getName().trim().equals(ri.getName().trim())) {
			record.setMatchState("1");
		} else {
			record.setMatchState("2");
		}
		record.setId(UUIDUtil.getUuid());
		empiMatchLogMapper.insertEmpiMatchLog(record);
		if (record.getMatchState().equals("2")) {
			throw new Exception("02|未匹配");
		}

		return ri;
	}

	/**
	 * 按照卡号卡类别查找empi号，
	 * 
	 * @param paramXml
	 * @return empiID
	 * @throws Exception
	 */
	public String searchEmpiIdByCard(String paramXml) throws Exception {
		ResidentInfo residentInfo = null;
		try {
			residentInfo = XmlManage.xmlToResidentInfo(paramXml, null);
		} catch (Exception e) {
			throw new Exception("03|参数错误");
		}
		String system_code = residentInfo.getSystem_code();
		for (ResidentCard rc : residentInfo.getCardList()) {
			if (rc.getCardType().trim().equals("1")
					|| rc.getCardType().trim().equals("2")
					|| rc.getCardType().trim().equals("3")) {
				rc.setCardOrg("A1");
			}
			if (rc.getCardType().trim().equals("4")) {
				rc.setCardOrg("A2");
			}
			if (rc.getCardType().trim().equals("6")) {
				rc.setCardOrg("B3");
			}
			if (rc.getCardType().trim().equals("7")) {
				rc.setCardOrg("B4");
			}
			if (rc.getCardType().trim().equals("8")) {
				rc.setCardOrg("B5");
			}
		}

		// 校验数据合法性
		boolean validatePassed = checkerService.validateFieldValues(
				residentInfo, null);
		boolean idCardPassed = CheckCard.CheckIDCard(residentInfo, null);

		if (!validatePassed || !idCardPassed) {
			throw new Exception("字段有效性校验未通过！");
		}

		List<ResidentCard> residentCardList = residentInfo.getCardList();
		Set<String> empiIdSet = new HashSet<String>();
		for (ResidentCard rc : residentCardList) {
			List<ResidentCard> rcListTemp = residentCardMapper
					.selectByCodeAndTypeAndDept(rc);
			/*
			 * if(residentInfo.getName()==null ||
			 * residentInfo.getName().isEmpty()){ throw new
			 * Exception("错误的查询条件，需要查询条件'姓名'!"); }
			 */
			if ((rc.getCardOrg().equals("5") || rc.getCardOrg().equals("9") || rc
					.getCardOrg().equals("10"))
					&& (rc.getCardOrg() == null || rc.getCardOrg().isEmpty())) {
				throw new Exception("错误的查询条件，二级标识类型为5,9或10时，需要查询条件'发卡机构代码'!");
			}
			if (rcListTemp.size() > 0) {
				empiIdSet.add(rcListTemp.get(0).getEmpi());
			}
		}

		if (empiIdSet.size() > 1) {
			throw new Exception("返回了多个查询结果，查询条件有误!");
		}
		if (empiIdSet.size() == 0) {
			// 如果查不到对应的记录，则插入一条状态为0的日志记录
			EmpiMatchLog record = ObjectReflect
					.convertResidentInfoToEmpiMatchLog(residentInfo);
			record.setMatchState("0");
			record.setId(UUIDUtil.getUuid());
			empiMatchLogMapper.insertEmpiMatchLog(record);
			throw new Exception("01|未注册");
		}
		String empiId = empiIdSet.toArray()[0].toString();
		return empiId;
	}

	/**
	 * Empi注册
	 * 
	 * @param residentInfo
	 * @return 新生成empi的id
	 */
	private String registerEmpi(ResidentInfo residentInfo) throws Exception {
		String empiId = UUIDUtil.getUuid();
		residentInfo.setEmpi(empiId);
		residentInfo.setModifiedTime(new Date());
		residentInfoMapper.insertResidentInfo(residentInfo);

		List<ResidentCard> cardList = residentInfo.getCardList();
		for (ResidentCard rc : cardList) {
			rc.setEmpi(empiId);
			if (residentCardMapper.countSelectByCodeAndTypeAndEmpi(rc) == 1) {
				ResidentCard rsd = residentCardMapper
						.selectByCodeAndTypeAndEmpi(rc);
				ObjectReflect.updateEmpty(rsd, rc);
				residentCardMapper.updateByCodeAndTypeAndEmpi(rsd);
				continue;
			}
			String cardId = UUIDUtil.getUuid();
			rc.setId(cardId);
			if (rc.getCardType().trim().equals("1")
					|| rc.getCardType().trim().equals("2")
					|| rc.getCardType().trim().equals("3")) {
				rc.setCardLevel("1");
			} else {
				rc.setCardLevel("2");
			}
			rc.setCreateDate(new Date());
			rc.setDeptCode(residentInfo.getDeptCode());
			rc.setOperState("1"); // empi新增操作
			residentCardMapper.insertResidentCard(rc);
		}
		return empiId;
	}

	/**
	 * Empi更新（新流程）
	 * 
	 * @param residentInfo
	 * @param checkResult
	 * @throws Exception
	 */
	public void updateEmpiN(ResidentInfo residentInfo, CheckResult checkResult)
			throws Exception {
		String empiId = residentInfo.getEmpi();
		if (empiId == null) {
			// 寻找一级标示
			List<ResidentCard> rcList = residentInfo.getCardList();
			List<ResidentCard> rcFirstLic = new ArrayList<ResidentCard>();
			for (ResidentCard rc : rcList) {
				if (rc.getCardType().trim().equals("1")
						|| rc.getCardType().trim().equals("2")
						|| rc.getCardType().trim().equals("3")) {
					rc.setCardLevel("1");
					rcFirstLic.add(rc);
				}
			}

			// 1一级标识一定存在，通过查找到一级标识对应的empiID来确定empiid
			List<ResidentCard> firstRcList = new ArrayList<ResidentCard>();
			for (ResidentCard rcFirst : rcFirstLic) {
				firstRcList = residentCardMapper
						.selectByCodeAndTypeAndDept(rcFirst);
				if (firstRcList.size() > 0) {
					break;
				}
			}
			empiId = firstRcList.get(0).getEmpi();
			residentInfo.setEmpi(empiId);
		}

		for (ResidentCard rc : checkResult.getResidentCardListInsert()) {
			rc.setId(UUIDUtil.getUuid());
			if (rc.getCardType().trim().equals("1")
					|| rc.getCardType().trim().equals("2")
					|| rc.getCardType().trim().equals("3")) {
				rc.setCardLevel("1");
			} else {
				rc.setCardLevel("2");
			}
			residentCardMapper.insertResidentCard(rc);
		}

		for (ResidentCard rc : checkResult.getResidentCardListUpdate()) {
			rc.setEmpi(empiId);
			// 填充数据库中的空值
			ResidentCard rsd = residentCardMapper
					.selectByCodeAndTypeAndEmpi(rc);
			ObjectReflect.updateEmpty(rsd, rc);
			residentCardMapper.updateResidentCardById(rsd);
		}
		// 填充数据库中的空值
		ResidentInfo ri = residentInfoMapper.selectResidentInfoByEmpi(empiId);
		ri.setEmpi(empiId);
		// ObjectReflect.updateEmpty(ri, residentInfo); //信息补空操作
		residentInfo.setModifiedTime(new Date());
		byte[] photo = residentInfo.getPhoto();
		if (photo == null || photo.length == 0) {
			residentInfo.setPhoto(null);
		}
		residentInfoMapper.updateResidentInfoByEmpi(residentInfo);
	}

	/**
	 * Empi更新 废弃的方法，老的验证流程使用此方法。目前不在使用。
	 * 
	 * @param residentInfo
	 */
	@Deprecated
	public void updateEmpi(ResidentInfo residentInfo) throws Exception {
		String empiId = residentInfo.getEmpi();
		if (empiId == null) {
			// 寻找一级标示
			List<ResidentCard> rcList = residentInfo.getCardList();
			ResidentCard rcFirst = null;
			for (ResidentCard rc : rcList) {
				if (rc.getCardType().trim().equals("1")
						|| rc.getCardType().trim().equals("2")
						|| rc.getCardType().trim().equals("3")) {
					rc.setCardLevel("1");
					rcFirst = rc;
				}
			}

			// 1一级标识一定存在，通过查找到一级标识对应的empiID来确定empiid
			List<ResidentCard> firstRcList = residentCardMapper
					.selectByCodeAndTypeAndDept(rcFirst);
			empiId = firstRcList.get(0).getEmpi();
		}

		ResidentInfo ri = residentInfoMapper.selectResidentInfoByEmpi(empiId);
		ObjectReflect.updateEmpty(ri, residentInfo);
		ri.setModifiedTime(new Date());
		residentInfoMapper.updateResidentInfoByEmpi(ri);

		List<ResidentCard> cardList = residentInfo.getCardList();
		for (ResidentCard rc : cardList) {
			rc.setEmpi(empiId);
			// 数据库不存在，则插入该条card
			if (residentCardMapper.countSelectByCodeAndTypeAndEmpi(rc) == 0) {
				rc.setEmpi(empiId);
				rc.setId(UUIDUtil.getUuid());
				if (rc.getCardType().trim().equals("1")
						|| rc.getCardType().trim().equals("2")
						|| rc.getCardType().trim().equals("3")) {
					rc.setCardLevel("1");
				} else {
					rc.setCardLevel("2");
				}
				residentCardMapper.insertResidentCard(rc);
			} else {
				if (rc.getId() == null || rc.getId().equals("")) {
					ResidentCard rsd = residentCardMapper
							.selectByCodeAndTypeAndEmpi(rc);
					ObjectReflect.updateEmpty(rsd, rc);
					residentCardMapper.updateByCodeAndTypeAndEmpi(rsd);
					continue;
				}
				ResidentCard rcd = residentCardMapper.selectResidentCardById(rc
						.getId());
				ObjectReflect.updateEmpty(rcd, rc);
				residentCardMapper.updateResidentCardById(rcd);
			}
		}
		residentInfo.setModifiedTime(new Date());
		residentInfoMapper.updateResidentInfoByEmpi(residentInfo);
	}

	/**
	 * 获取请求参数参数对象
	 * 
	 * @param documentXML
	 * @return
	 */
	private Object getRequestParObj(XStream xs, String methodName,
			String documentXML) {
		try {
			if ("卡查询".equals(methodName)) {
				xs.processAnnotations(SearchRequest.class);
				SearchRequest searchRequest = (SearchRequest) xs
						.fromXML(documentXML);
				return searchRequest;
			} else if ("卡注销".equals(methodName)) {
				xs.processAnnotations(CancelRequest.class);
				CancelRequest cancelRequest = (CancelRequest) xs
						.fromXML(documentXML);
				return cancelRequest;
			} else if ("卡变更".equals(methodName)) {
				xs.processAnnotations(LevelChangeRequest.class);
				LevelChangeRequest levelChangeRequest = (LevelChangeRequest) xs
						.fromXML(documentXML);
				return levelChangeRequest;
			} else if ("实名变更申请".equals(methodName)) {
				xs.processAnnotations(ApplyUpdateReq.class);
				ApplyUpdateReq applyUpdateReq = (ApplyUpdateReq) xs
						.fromXML(documentXML);
				return applyUpdateReq;
			} else if ("解绑申请".equals(methodName)) {
				xs.processAnnotations(ApplyUnbindRequest.class);
				ApplyUnbindRequest applyUnbindRequest = (ApplyUnbindRequest) xs
						.fromXML(documentXML);
				return applyUnbindRequest;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	private String formatDate(Date dt) {
		String strDt = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (dt != null) {
			strDt = sdf.format(dt);
		}
		return strDt;
	}

	private String[] getPushOrgList() {
		String[] pushOrgList = new String[0];
		if (queueOrg != null && !"".equals(queueOrg)) {
			pushOrgList = queueOrg.split(",");
		}
		return pushOrgList;
	}

}
