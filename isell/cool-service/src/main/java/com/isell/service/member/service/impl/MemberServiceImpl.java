package com.isell.service.member.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import com.isell.cache.service.JVMCacheService;
import com.isell.core.config.BisConfig;
import com.isell.core.pojo.TemplateSMS;
import com.isell.core.util.CommonUtils;
import com.isell.core.util.DateUtil;
import com.isell.core.util.HttpUtils;
import com.isell.core.util.JsonUtil;
import com.isell.core.util.Record;
import com.isell.service.member.dao.CoolIdentityAuthMapper;
import com.isell.service.member.dao.CoolMemberMapper;
import com.isell.service.member.dao.CoolMemberReceiverMapper;
import com.isell.service.member.dao.CoolUserMapper;
import com.isell.service.member.dao.CoonShopFocusMapper;
import com.isell.service.member.service.MemberService;
import com.isell.service.member.vo.CoolIdentityAuth;
import com.isell.service.member.vo.CoolMember;
import com.isell.service.member.vo.CoolMemberReceiver;
import com.isell.service.member.vo.CoolUser;
import com.isell.service.member.vo.CoonShopFocus;
import com.isell.service.member.vo.UserInfo;
import com.isell.service.shop.dao.CoonShopApplyMapper;
import com.isell.service.shop.dao.CoonShopMapper;
import com.isell.service.shop.vo.CoonShop;
import com.isell.service.shop.vo.CoonShopApply;

/**
 * 会员接口实现层
 *
 * @author wangpeng
 * @version [版本号, 2015-10-14]
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {

	/**
	 * 用户mapper
	 */
	@Resource
	private CoolUserMapper coolUserMapper;

	/**
	 * 会员mapper
	 */
	@Resource
	private CoolMemberMapper coolMemberMapper;

	/**
	 * 会员地址mapper
	 */
	@Resource
	private CoolMemberReceiverMapper coolMemberReceiverMapper;

	/**
	 * 酷店mapper
	 */
	@Resource
	private CoonShopMapper coonShopMapper;

	/**
	 * 酷店申请表
	 */
	@Resource
	private CoonShopApplyMapper coonShopApplyMapper;

	/**
	 * 实名认证表mapper
	 */
	@Resource
	private CoolIdentityAuthMapper coolIdentityAuthMapper;
	
	/**
	 * 酷店关注表mapper
	 */
	@Resource
	private CoonShopFocusMapper coonShopFocusMapper;

	/**
	 * JVM缓存服务接口
	 */
	@Resource
	private JVMCacheService jvmCacheService;

	/**
     * 配置信息
     */
    @Resource
    private BisConfig config;

	/**
	 * 登录
	 */
	@Override
	public Record login(CoolUser user) {
		Record record = new Record();
		boolean success = false;
		String username = user.getUsername();
		String password = user.getPassword();
		if(StringUtils.isNotEmpty(username)){
			user = coolUserMapper.getCoolUserByUserName(username);
			if(user != null){
				CoolMember member = coolMemberMapper.getCoolMemberByUserId(user.getId());
				if(user != null && member != null){
					if(user.getPassword().equals(password)){
					    UserInfo userInfo = new UserInfo();
					    userInfo.setId(user.getId());
					    userInfo.setNo(member.getNo());
					    userInfo.setUsername(user.getUsername());
					    userInfo.setPetname(member.getPetname());
					    userInfo.setRealname(member.getRealname());
					    userInfo.setEmail(user.getEmail());
					    userInfo.setMobile(user.getMobile());
					    userInfo.setLogo(user.getLogo());
					    userInfo.setQq(member.getQq());
					    userInfo.setLevel(member.getLevel());
					    // 店铺信息
					    CoonShop shop = coonShopMapper.getCoonShopByUserId(user.getId() + "");
					    if(shop != null){
					    	record.set("shop", shop);
					    	userInfo.setShopId(shop.getId());
					    }
					    record.set("user", userInfo);				    
						success = true;
					}else{
						record.set("msg", "您输入的密码不正确");
					}
				}else{
					record.set("msg", "您输入的用户不存在");
				}
			}else{
				record.set("msg", "参数错误，无法获取用户信息");
			}
		}else{
			record.set("msg", "用户名不能为空");
		}
		record.set("success", success);
		return record;
	}

	/**
	 * 注销
	 */
	@Override
	public Record loginout(CoolUser user) {
		Record record = new Record();
		return record;
	}

	/**
	 * 保存会员注册信息
	 */
	@Override
	public Record saveCoolMember(Map<String, Object> param) {
		Record record = new Record();

		String username = param.get("username").toString();
        String password = param.get("password").toString();
        boolean success = false;
        int result = 0;
        if (param.get("sms").equals(param.get("sms_" + username))) {
        	int count = coolUserMapper.getRegisterNumberByUserName(username);
        	if(count > 0){
        		record.set("msg", "手机号码已被注册");
        	}else{
        		// 保存用户注册信息
                password = DigestUtils.md5Hex(password);
                CoolUser user = new CoolUser();
                user.setUsername(username);
                user.setPassword(password);
                user.setState(1);
                user.setMobile(username);
                coolUserMapper.saveCoolUser(user);

                String maxMember = coolMemberMapper.getMaxCoolMemberNo();
                String no = null;
                if (StringUtils.isNotEmpty(maxMember)) {
                    no = "10000000";
                } else {
                    no = (Integer.parseInt(maxMember) + 1) + "";
                }
                CoolMember member = new CoolMember();
                member.setUserId(user.getId());
                member.setMobile(username);
                member.setNo(no);
                result = coolMemberMapper.saveCoolMember(member);
                success = result>0?true:false;

                if(!success){
                	record.set("mag", "用户注册失败");
                }
        	}
        }

        record.set("success", success);

        return record;
	}

	/**
	 * 验证email
	 */
	@Override
	public Record updateEmailVerify(Map<String, Object> param) {
		Record record = new Record();

		String verifyCode = param.get("key").toString();
		CoolMember member = new CoolMember();
		member.setVerifyCode(verifyCode);
		List<CoolMember> mList = coolMemberMapper.findCoolMember(member);
		if(CollectionUtils.isNotEmpty(mList)){
			member = mList.get(0);
		}
		if(member != null){
			// 修改邮箱
            String email = member.getVerifyEmail();
            member.setEmail(email);
            member.setVerifyCode("");
            member.setVerifyEmail("");
            coolMemberMapper.updateCoolMember(member);

            CoolUser user = coolUserMapper.getCoolUserById(member.getUserId());
            if(user != null){
            	user.setEmail(email);
            	coolUserMapper.updateCoolUser(user);
            }
		}else{
			record.set("msg", "该验证码不存在！");
		}

		return record;
	}

	/**
	 * 修改会员信息
	 */
	@Override
	public Record updateCoolUser(Map<String, Object> param) {
		Record record = new Record();
		int result = 0;
		boolean success = false;
		String msg = "";

		CoolUser user = coolUserMapper.getCoolUserById(Integer.parseInt(param.get("userId").toString()));
		if(user != null){
			//修改密码
			String oldPassword = DigestUtils.md5Hex(param.get("oldPassword").toString()); // 原密码
	        String newPassword = DigestUtils.md5Hex(param.get("password").toString());// 新密码
	        String password = user.getPassword();
	        if(oldPassword.equals(password)){
	        	user.setPassword(newPassword);
	        	result = coolUserMapper.updateCoolUser(user);
	        	if(result > 0){
	        		success = true;
	        	}else{
	        		msg = "密码修改失败！";
	        	}
	        }else {
	        	msg = "旧密码错误，请重新输入!";
	        }
		}else{
			msg = "该会员不存在!";
		}

		record.set("success", success);
		record.set("msg", msg);
		return record;
	}

	/**
	 * 修改个人信息
	 */
	@Override
	public Record updateCoolMember(Map<String, Object> param) {
		Record record = new Record();
		int result = 0;
		boolean success = false;
		String msg = "";

		CoolMember member = coolMemberMapper.getCoolMemberById(Integer.parseInt(param.get("id").toString()));
		if(member != null){
			member.setPetname(param.get("petname").toString());
			member.setSex(param.get("sex").toString());
			member.setBirthday(DateUtil.strToDate("yyyyMMdd", param.get("date").toString()));
			member.setSkin(Integer.valueOf(param.get("skin").toString()));
			member.setHair(Integer.valueOf(param.get("hair").toString()));
			member.setCostPerYear(Integer.valueOf(param.get("cost_per_year").toString()));
			member.setDescription(param.get("sex").toString());
			member.setSex(param.get("cardId").toString());
			result = coolMemberMapper.updateCoolMember(member);

			CoolUser user = coolUserMapper.getCoolUserById(Integer.parseInt(param.get("id").toString()));
			user.setPetname(param.get("petname").toString());
			result = coolUserMapper.updateCoolUser(user);
			if(result > 0){
	            success = true;
	        } else {
	            msg = "个人资料保存失败";
	        }
		}else{
			msg = "该用户不存在！";
		}

		record.set("success", success);
		record.set("msg", msg);
		return record;
	}

	/**
	 * 根据主键获取
	 */
	@Override
	public Record getCoolMemberById(Map<String, Object> param) {
		Record record = new Record();
		CoolMember member = coolMemberMapper.getCoolMemberById(Integer.valueOf(param.get("id").toString()));
		record.set("email", member.getEmail());
		record.set("idcard", member.getIdcard());
		record.set("petname", member.getPetname());
		record.set("mobile", member.getMobile());
		record.set("logo", member.getLogo());
		return record;
	}

	/**
	 * 根据用户id获取
	 */
	@Override
	public Record getCoolMemberByUserId(Map<String, Object> param) {
		Record record = new Record();
		CoolMember member = coolMemberMapper.getCoolMemberByUserId(Integer.valueOf(param.get("userId").toString()));
		record.set("email", member.getEmail());
		record.set("idcard", member.getIdcard());
		record.set("petname", member.getPetname());
		record.set("mobile", member.getMobile());
		record.set("logo", member.getLogo());
		return record;
	}

	/**
     * 修改CoolMember（通用）
     *
     */
	@Override
	public Record updateCoolMemberCommen(Map<String, Object> param) {
		CoolMember member = new CoolMember();
		if(param.get("id") != null){
			member = coolMemberMapper.getCoolMemberById(Integer.valueOf(param.get("id").toString()));
		}else{
			member = coolMemberMapper.getCoolMemberByUserId(Integer.valueOf(param.get("userId").toString()));
		}

		if(param.get("username") != null){
			member.setMobile(param.get("username").toString());
		}
		if(param.get("verify_code") != null){
			member.setVerifyCode(param.get("verify_code").toString());
		}
		if(param.get("verify_email") != null){
			member.setVerifyEmail(param.get("verify_email").toString());
		}

		int result = coolMemberMapper.updateCoolMember(member);

		Record record = new Record();
		record.set("success", result>0?true:false);
		return record;
	}

	/**
     * 修改CoolUser（通用）
     *
     */
	@Override
	public Record updateCoolUserCommen(Map<String, Object> param) {
		Record record = new Record();
		CoolUser user = coolUserMapper.getCoolUserById(Integer.parseInt(param.get("id").toString()));
		if(param.get("username") != null){
			user.setUsername(param.get("username").toString());
		}
		if(param.get("mobile") != null){
			user.setMobile(param.get("mobile").toString());
		}
		if(param.get("logo") != null){
			user.setLogo(param.get("logo").toString());
		}

		int result = coolUserMapper.updateCoolUser(user);

		record.set("success", result>0?true:false);
		return record;
	}

	/**
     * 平台帐户检查
     *
     */
	@Override
	public Record getNumberForCheckUser(Map<String, Object> param) {
		Record record = new Record();
		int count = coolUserMapper.getNumberForCheckUser(Integer.valueOf(param.get("id").toString()), (param.get("email").toString()));
		record.set("count",count);
		return record;
	}

	/**
	 * 检查身份证是否正确
	 *
	 */
	@Override
	public Record getNumberForCheckIdCard(Map<String, Object> param) {
		Record record = new Record();
		int count = coolMemberMapper.getNumberForCheckIdCard(Integer.valueOf(param.get("userId").toString()), (param.get("idcard").toString()));
		record.set("count",count);
		return record;
	}

	/**
	 * 获取收货地址
	 */
	@Override
	public Record getcoolMemberReceiverList(Map<String, Object> param) {
		Record record = new Record();
		List<CoolMemberReceiver> list = new ArrayList<CoolMemberReceiver>();
		if(param.get("mId") != null){
			list = coolMemberReceiverMapper.getCoolMemberReceiver(Integer.valueOf(param.get("mId").toString()),0);
		}else if(param.get("id") != null){
			list = coolMemberReceiverMapper.getCoolMemberReceiver(0,Integer.valueOf(param.get("id").toString()));
		}

		record.set("receivers", list);
		return record;
	}

	/**
	 * 删除收货地址
	 */
	@Override
	public Record deleteCoolMemberReceiver(Map<String, Object> param) {
		Record record = new Record();
		int count = coolMemberReceiverMapper.deleteCoolMemberReceiver(Integer.valueOf(param.get("id").toString()));

		record.set("count", count);
		return record;
	}

	/**
	 * 保存收货地址
	 */
	@Override
	public Record saveCoolMemberReceiver(Map<String, Object> param) {
		Record record = new Record();
		CoolMemberReceiver receiver = coolMemberReceiverMapper.getCoolMemberReceiverById(Integer.valueOf(param.get("id").toString()));
		if(param.get("location_p") != null){
			receiver.setLocationP(param.get("location_p").toString());
		}
		if(param.get("location_c") != null){
			receiver.setLocationC(param.get("location_c").toString());
		}
		if(param.get("location_a") != null){
			receiver.setLocationA(param.get("location_a").toString());
		}
		if(param.get("address") != null){
			receiver.setAddress(param.get("address").toString());
		}
		if(param.get("name") != null){
			receiver.setName(param.get("name").toString());
		}
		if(param.get("mobile") != null){
			receiver.setMobile(param.get("mobile").toString());
		}
		if(param.get("tel") != null){
			receiver.setTel(param.get("tel").toString());
		}
		if(param.get("m_id") != null){
			receiver.setmId(Integer.valueOf(param.get("m_id").toString()));
		}
		if(param.get("def") != null){
			receiver.setDef(Boolean.valueOf(param.get("def").toString()));
		}

		int result = coolMemberReceiverMapper.updateCoolMemberReceiver(receiver);

		record.set("result", result);
		return record;
	}

	 /**
     * 导入用户信息自动注册会员及酷店<br>
     * 导入文件格式具有不确定性，此方法暂不可被其他地方调用
     *
     * @param param
     * @return Record
     */
	@Override
	public Record saveCoolMemberAndShopForImport(Map<String, Object> param) {
		Record record = new Record();
        File file = new File(param.get("filePath").toString());
        InputStream is = null;
        boolean success = false;
        try {
            is = new FileInputStream(file);
            BufferedInputStream binput = new BufferedInputStream(is);
            Workbook hwb;
            hwb = WorkbookFactory.create(binput);

            Sheet sheet = hwb.getSheetAt(0);
            Row row = null;
            Cell cell = null;

            // List<CoolOrder> orderList = new ArrayList<CoolOrder>();
            // List<CoolOrderItem> orderItemList = new ArrayList<CoolOrderItem>();
            int rowNum = sheet.getLastRowNum();
            for(int i = 1; i <= rowNum; i++){
            	String name = "";
            	String username = "";
            	String idcard = "";
            	String linkname = "";
            	String password = "";

            	row = sheet.getRow(i);

            	//获取到的行数可能会存在问题，所以添加判断标志，都为空时结束循环
            	int flag = 0;
            	cell = row.getCell(0); //身份证姓名
            	if(cell != null){
            		//将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                    	name = cell.getStringCellValue().trim();
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
            	cell = row.getCell(1); //身份证号码
            	if(cell != null){
            		//将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                    	idcard = cell.getStringCellValue().trim();
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
            	cell = row.getCell(1); //身份证号码
            	if(cell != null){
            		//将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                    	idcard = cell.getStringCellValue().trim();
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
            	cell = row.getCell(2); //收货人
            	if(cell != null){
            		//将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                    	linkname = cell.getStringCellValue().trim();
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
            	cell = row.getCell(2); //手机
            	if(cell != null){
            		//将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                    	username = cell.getStringCellValue().trim();
                    	int length = username.length();
                    	password = username.substring(length-6, length);
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }

            	 if (flag == 4) {
                     break;
                 } else {
                	 //业务处理
                	 String no = null;
                	 CoolMember member = coolMemberMapper.getCoolMemberByMobile(username);
                	 CoolUser user = coolUserMapper.getCoolUserByUserName(username);
                	 //先判断是否会员
                	 if(member == null ){
                		 member = new CoolMember();
                		 user = new CoolUser();

                		 password = DigestUtils.md5Hex(password);
                		 user.setUsername(username);
                		 user.setPassword(password);
                		 user.setState(1);
                		 user.setMobile(username);
                		 user.setCreatetime(new Date());
                		 coolUserMapper.saveCoolUser(user);

                		 String maxMember = coolMemberMapper.getMaxCoolMemberNo();
                		 if(StringUtils.isNotEmpty(maxMember)){
                			 no = (Integer.parseInt(maxMember) + 1) + "";
                		 }else{
                			 no = "10000000";
                		 }
                		 member.setUserId(user.getId());
                		 member.setMobile(username);
                		 member.setNo(no);
                		 member.setRealname(name);
                		 member.setIdcard(idcard);
                		 member.setPetname(linkname);
                		 coolMemberMapper.saveCoolMember(member);
                	 }

                	 //再判断是否有酷店
                	 CoonShop shop = coonShopMapper.getCoonShopByUserId(user.getId().toString());
                	 if(shop == null){
                		 //判断是否正在申请
                		 CoonShopApply apply = coonShopApplyMapper.getCoonShopApplyByUserId(user.getId().toString());
                		 if(apply != null){
                			 apply.setState(new Byte("1"));
                			 apply.setAuditTime(new Date());
                			 coonShopApplyMapper.updateCoonShopApply(apply);
                		 }else{
                			 apply = new CoonShopApply();
                			 apply.setId(CommonUtils.uuid());
                			 apply.setUserId(user.getId().toString());
                			 apply.setName(name);
                			 apply.setMobile(username);
                			 apply.setState(new Byte("1"));
                			 apply.setIdcard(idcard);
                			 apply.setAudit("0");
                			 apply.setAuditTime(new Date());
                			 coonShopApplyMapper.saveCoonShopApply(apply);
                		 }
                		 //注册店铺
                		 shop = new CoonShop();
                		 String  uuid = CommonUtils.uuid();
                		 shop.setId(uuid);
                		 shop.setUserId(user.getId().toString());
                		 shop.setCode(member.getNo());
                		 shop.setName(name);
                		 String dirPath = "/shop/" + uuid + "/";
                		 // 生成酷店二维码和网址
                         // String webSite = "http://" + member.getNo() + ".m." +  param.get("baseDomain").toString(); //AppConfig.baseDomain;

                         String dir = param.get("img_local").toString(); //AppConfig.IMG_LOCAL;
                         String qrCode = dirPath + "qr_" + member.getNo() + ".png";
                         new File(dir + dirPath).mkdirs();
                         File filesite = new File(dir + qrCode);
                         if (filesite.exists()) {
                             FileUtils.deleteQuietly(filesite);
                         }
                         //CommonUtils.createQRCode(webSite, dir + qrCode, param.get("realPath").toString()  //getRequest().getSession().getServletContext().getRealPath("")
                          //   + "/wap/images/xiaocoon1.png", 10);

                         shop.setShowWay(new Byte("1"));
                         shop.setQrCode(qrCode);
                         coonShopMapper.saveCoonShop(shop);
                		 //注册成功发送短信


                		 success = true;
                	 }else{
                		 //已有店铺，不处理
                		 success = true;
                	 }
                 }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		record.set("success", success);
		return record;
	}

	/**
     * 校验手机号是否注册
     *
     * @param user
     * @return 手机号是否注册
     */
	@Override
	public Record checkMobile(CoolUser user) {
		Record record = new Record();
		String userName = user.getMobile();
		if(StringUtils.isNotEmpty(userName)){
			CoolUser coolUser = coolUserMapper.getCoolUserByUserName(userName);
			if(coolUser == null){
				record.set("register", false); //未注册
			}else{
				record.set("register", true);
			}
		}else{
			throw new RuntimeException("exception.access.param-invalidate");
		}

		return record;
	}

	/**
     * 发送手机验证码
     *
     * @param user
     * @return 手机验证码
     */
	@Override
	public Record sendMessage(CoolUser user) {
		Record record = new Record();
		String mobile = user.getMobile();
		Boolean success = false;
		if(StringUtils.isNotEmpty(mobile)){
			int sms = CommonUtils.randomFour();
			TemplateSMS templateSMS = new TemplateSMS();
            templateSMS.setTemplateId("13560");
            templateSMS.setTo(mobile);
            templateSMS.setParam(sms + "");

            String result = HttpUtils.httpPost(config.getServiceDomain()+ config.getSendSmsUrl(), JsonUtil.writeValueAsString(templateSMS));

            if (StringUtils.isEmpty(result)) {
            	record.set("msg", "短信发送失败");
            } else {
            	record.set("securityCode", sms);
            	success = true;
            	System.out.println(sms);
            }
		}else{
			throw new RuntimeException("exception.access.param-invalidate");
		}

		record.set("success", success);
		return record;
	}

	/**
     * 注册用户
     *
     * @param user
     * @return 是否注册成功
     */
	@Override
	public Record saveRegisterMember(CoolUser user) {
		Record record = new Record();
		Boolean success = false;
		String username = user.getUsername();
		String password = user.getPassword();
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
			throw new RuntimeException("exception.access.param-invalidate");
		}else{
			int number = coolUserMapper.getRegisterNumberByUserName(username);
			if(number > 0){
				record.set("msg", "手机号已注册");
			}else{
				int result = 0;
				CoolUser cUser = new CoolUser();
				cUser.setUsername(username);
				cUser.setPassword(password);
				cUser.setState(CoolUser.USER_STATE_1);
				result = coolUserMapper.saveCoolUser(cUser);
				if(result > 0){
					CoolMember cMember = new CoolMember();
					String maxMember = coolMemberMapper.getMaxCoolMemberNo();
					 String no = null;
	                    if (maxMember == null) {
	                        no = "10000000";
	                    } else {
	                        no = (Integer.parseInt(maxMember) + 1) + "";
	                    }
	                    CoolUser u = coolUserMapper.getCoolUserByUserName(username);
	                    cMember.setUserId(u.getId());
	                    cMember.setMobile(username);
	                    cMember.setNo(no);
	                    result = coolMemberMapper.saveCoolMember(cMember);
	                    if(result > 0){
	                    	success = true;
	                    }else{
	                    	record.set("msg", "用户注册失败");
	                    }
				}else{
					record.set("msg", "用户注册失败");
				}
			}
		}
		record.set("success", success);
		return record;
	}

	/**
     * 更新用户密码
     *
     * @param user
     * @return
     */
	@Override
	public Record updatePassword(CoolUser user) {
		Record record = new Record();
		boolean success = false;
		String username = user.getUsername();
		String sms = user.getSms();
		if(StringUtils.isNotEmpty(username)){
			CoolUser cUser = coolUserMapper.getCoolUserByUserName(username);
			if(cUser != null){
				String password = user.getPassword();
				String oldPssword = user.getOldPssword();
				if(StringUtils.isNotEmpty(password)){
					if(StringUtils.isEmpty(sms)){ //修改密码，需要验证旧密码
						if(StringUtils.isNotEmpty(oldPssword)){
							if(oldPssword.equals(cUser.getPassword())){
								cUser.setPassword(password);
							}else{
								record.set("msg", "旧密码跟原密码不一致");
							}
						}else{
							record.set("msg", "旧密码不能为空");
						}
					}else{ //密码找回，不需要验证旧密码
						cUser.setPassword(password);
					}
				}else{
					record.set("msg", "密码不能为空");
				}
				int result = coolUserMapper.updateCoolUser(cUser);
				if(result > 0 ){
					success = true;
				}else{
					record.set("msg", "密码更新错误");
				}
			}else{
				record.set("msg", "用户名输入错误");
			}
		}else{
			record.set("msg", "用户名不能为空");
		}
		record.set("success", success);
		return record;
	}

	/**
     * 用户实名认证
     *
     * @param user
     * @return
     */
	@Override
	public Record saveIdcardAuth(CoolIdentityAuth coolIdentityAuth) {
		Record record = new Record();
		Boolean success = false;
		Integer userId = coolIdentityAuth.getUserId();
		if(userId != null){
			CoolIdentityAuth cauth = coolIdentityAuthMapper.getCoolIdentityAuthByUserId(userId);
			String name = coolIdentityAuth.getName();
			if(StringUtils.isNotEmpty(name)){
				String idcard = coolIdentityAuth.getIdcard();
				if(StringUtils.isNotEmpty(idcard)){
					String tel = coolIdentityAuth.getTel();
					if(StringUtils.isNotEmpty(tel)){
						String thirdParty = coolIdentityAuth.getThirdParty();
						if(StringUtils.isNotEmpty(thirdParty)){
							String imgUrl = coolIdentityAuth.getImgUrl();
							if(StringUtils.isNotEmpty(imgUrl)){
								String backImgUrl = coolIdentityAuth.getBackImgUrl();
								CoolIdentityAuth auth = new CoolIdentityAuth();
								auth.setUserId(userId);
								auth.setImgUrl(config.getImgLocal()+imgUrl);
								auth.setName(name);
								auth.setIdcard(idcard);
								auth.setThirdParty(thirdParty);
								auth.setTel(tel);
								auth.setAuthTime(new Date());
								auth.setBackImgUrl(backImgUrl);
								int result = 0;
								if(cauth == null){
									auth.setIspass(CoolIdentityAuth.IS_PASS_0);
									result = coolIdentityAuthMapper.saveCoolIdentityAuth(auth);
								}else{
									auth.setId(cauth.getId());
									auth.setIspass(CoolIdentityAuth.IS_PASS_0);
									result = coolIdentityAuthMapper.updateCoolIdentityAuth(auth);
								}
								if(result > 0){
									success = true;
								}else{
									record.set("msg", "提交实名认证申请失败");
								}
							}else{
								record.set("msg", "身份证正面照不能为空");
							}
						}else{
							record.set("msg", "联系地址不能为空");
						}
					}else{
						record.set("msg", "手机号不能为空");
					}
				}else{
					record.set("msg", "身份证号不能为空");
				}
			}else{
				record.set("msg", "真实姓名不能为空");
			}
		}else{
			record.set("msg", "用户主键不能为空");
		}
		record.set("success", success);
		return record;
	}

	/**
     * 获取收货地址列表
     *
     * @param coolMemberReceiver 查询参数
     * @return 收货地址列表
     */
	@Override
	public Record getReceiverList(CoolMemberReceiver coolMemberReceiver) {
		Record record = new Record();
		Boolean success = false;
		Integer mId = coolMemberReceiver.getmId();
		if(mId != null){
			List<CoolMemberReceiver> receiverList = coolMemberReceiverMapper.findCoolMemberReceiverByMId(mId);
			if(CollectionUtils.isNotEmpty(receiverList)){
				record.set("receiverList", receiverList);
				success = true;
			}else{
				record.set("msg", "无数据");
			}
		}else{
			record.set("msg", "参数错误，会员主键不能为空");
		}
		record.set("success", success);
		return record;
	}

	/**
     * 保存收货地址
     * 
     * @param coolMemberReceiver 参数
     * @return 是否保存成功
     */
	@Override
	public Record saveMemberReceiver(CoolMemberReceiver coolMemberReceiver) {
		Record record = new Record();
		Boolean success = false;
		Integer mId = coolMemberReceiver.getmId();
		if(mId != null){
			String locationP = coolMemberReceiver.getLocationP();
			if(StringUtils.isNotEmpty(locationP)){
				String locationC = coolMemberReceiver.getLocationC();
				if(StringUtils.isNotEmpty(locationC)){
					String locationA = coolMemberReceiver.getLocationA();
					if(StringUtils.isNotEmpty(locationA)){
						String address = coolMemberReceiver.getAddress();
						if(StringUtils.isNotEmpty(address)){
							String name = coolMemberReceiver.getName();
							if(StringUtils.isNotEmpty(name)){
								String mobile = coolMemberReceiver.getMobile();
								if(StringUtils.isNotEmpty(mobile)){
									String idcard = coolMemberReceiver.getIdcard();
									if(StringUtils.isNotEmpty(idcard)){
										if(coolMemberReceiver.getDef() != null){
											boolean def = coolMemberReceiver.getDef();
											if(def){  //需要把其他的收货地址改为非默认
												coolMemberReceiverMapper.updateCoolMemberReceiverDef(mId);											
											}
										}										
										int result = 0;										
										Integer id = coolMemberReceiver.getId();
										if(id != null){ //更新
											result = coolMemberReceiverMapper.updateCoolMemberReceiver(coolMemberReceiver);
											if(result > 0){
												success = true;
											}else{
												record.set("msg", "更新收货地址失败");
											}
										}else{
											coolMemberReceiver.setCreatetime(new Date());
											result = coolMemberReceiverMapper.saveCoolMemberReceiver(coolMemberReceiver);
											if(result > 0){
												success = true;
											}else{
												record.set("msg", "保存收货地址失败");
											}
										}
									}else{
										record.set("msg", "参数错误，身份证号不能为空");
									}
								}else{
									record.set("msg", "参数错误，手机号不能为空");
								}
							}else{
								record.set("msg", "参数错误，收货人姓名不能为空");
							}
						}else{
							record.set("msg", "参数错误，参数地址不能为空");
						}
					}else{
						record.set("msg", "参数错误，参数区不能为空");
					}
				}else{
					record.set("msg", "参数错误，参数市不能为空");
				}
			}else{
				record.set("msg", "参数错误，参数省不能为空");
			}		
		}else{
			record.set("msg", "参数错误，会员主键不能为空");
		}		
		record.set("success", success);
		return record;
	}

	/**
     * 更新关注店铺
     * 
     * @param coonShopFocus 参数
     * @return 是否保存成功
     */
	@Override
	public Record updateFocusShop(CoonShopFocus coonShopFocus) {
		Record record = new Record();
		Boolean success = false;
		Integer mId = coonShopFocus.getmId();
		String sId = coonShopFocus.getsId();
		String state = coonShopFocus.getState();
		if(mId != null){
			if(StringUtils.isNotEmpty(sId)){
				if(StringUtils.isNotEmpty(state)){
					int result = 0;
					if("0".equals(state) ){ //取消关注
						CoonShopFocus focuse = coonShopFocusMapper.getCoonShopFocusBymId(mId);
						if(focuse != null){
							result = coonShopFocusMapper.deleteCoonShopFocus(focuse.getId());	
							if(result > 0){
								success = true;
							}else{
								record.set("msg", "取消关注失败"); 
							}
						}else{
							success = true;
						}
					}else if("1".equals(state)){ //关注
						CoonShopFocus focuse = coonShopFocusMapper.getCoonShopFocusBymId(mId);
						if(focuse == null){
							focuse = new CoonShopFocus();
							focuse.setmId(mId);
							focuse.setsId(sId);
							result = coonShopFocusMapper.saveCoonShopFocus(focuse);
							if(result > 0){
								success = true;
							}else{
								record.set("msg", "店铺关注失败"); 
							}
						}else{
							focuse.setsId(sId);
							result = coonShopFocusMapper.updateCoonShopFocus(focuse);
							if(result > 0){
								success = true;
							}else{
								record.set("msg", "店铺关注失败"); 
							}
						}
					}else{
						record.set("msg", "参数错误，状态值只能是0或者1"); 
					}
					
				}else{
					record.set("msg", "参数错误，状态不能为空"); 
				}
			}else{
				
			}
		}else{
			record.set("msg", "参数错误，会员主键不能为空"); 
		}
		
		record.set("success", success);
		return record;
	}

}
