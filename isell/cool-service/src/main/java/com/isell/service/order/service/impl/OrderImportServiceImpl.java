package com.isell.service.order.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.isell.core.config.BisConfig;
import com.isell.core.util.DateUtil;
import com.isell.core.util.Record;
import com.isell.service.bi.dao.BiDateMapper;
import com.isell.service.bi.vo.BiDate;
import com.isell.service.member.dao.CoolMemberMapper;
import com.isell.service.member.dao.CoolUserMapper;
import com.isell.service.member.vo.CoolMember;
import com.isell.service.member.vo.CoolUser;
import com.isell.service.order.dao.CoolOrderItemMapper;
import com.isell.service.order.dao.CoolOrderMapper;
import com.isell.service.order.service.OrderImportService;
import com.isell.service.order.util.OrderUtil;
import com.isell.service.order.vo.CoolOrder;
import com.isell.service.order.vo.CoolOrderItem;
import com.isell.service.product.dao.CoolProductGgMapper;
import com.isell.service.product.dao.CoolProductMapper;
import com.isell.service.product.vo.CoolProduct;
import com.isell.service.product.vo.CoolProductGg;

/**
 * 订单导入导出接口实现类
 * 
 * @author wangpeng
 * @version [版本号, 2015-12-08]
 */
@Service("orderImportService")
public class OrderImportServiceImpl implements OrderImportService {
    
    @Resource
    private BiDateMapper biDateMapper;
    
    /**
     * log
     */
    private static final Logger log = Logger.getLogger(OrderServiceImpl.class);
    
    /**
     * 订单查询mapper
     */
    @Resource
    private CoolOrderMapper coolOrderMapper;
    
    /**
     * 订单详情Mapper
     */
    @Resource
    private CoolOrderItemMapper coolOrderItemMapper;
    
    /**
     * 商品mapper
     */
    @Resource
    private CoolProductMapper coolProductMapper;
    
    /**
     * 商品规格mapper
     */
    @Resource
    private CoolProductGgMapper coolProductGgMapper;
    
    /**
     * 用户表mapper
     */
    @Resource
    private CoolUserMapper coolUserMapper;
    
    /**
     * 会员表mapper
     */
    @Resource
    private CoolMemberMapper coolMemberMapper;
    
    /**
     * 配置信息
     */
    @Resource
    private BisConfig config;
    
    /**
     * coolJdbcTemplate
     */
    @Resource
    private JdbcTemplate jdbcTemplate;
    
    /**
     * 导入订单
     */
    @Override
    public Record saveCoolOrderForImport(Map<String, Object> param) {
        Record record = new Record();
        String filePath = param.get("filePath").toString();
        String importType = param.get("importType").toString();
        String arrears = param.get("arrears").toString();
        String state = param.get("state").toString();
        filePath = config.getImgLocal() + filePath;
        // filePath = "z:/"+filePath;
        
        /*
         * if ("1".equals(importType)) { // 拼多多 record = savePdd(filePath, arrears); record.set("success", true); } else
         * if ("2".equals(importType)) { // 金箍棒 record = saveJgb(filePath, arrears); record.set("success", true); }
         */
        String shopId = "";
        Integer mId = null;
        String shopName = "";
        if ("2".equals(importType)) { // 金箍棒
            record = saveJgb(filePath, arrears, state);
            record.set("success", true);
        } else if ("4".equals(importType)) { // 美丽说
            record = saveMls(filePath, arrears, state);
            record.set("success", true);
        } else if ("7".equals(importType)) { // 瓜藤网
            shopId = "bc58f014499e44448d744bdf0aff0071";
            mId = 4730;
            shopName = "瓜藤网";
            record = saveGtw(filePath, arrears, state, shopId, mId, shopName);
            record.set("success", true);
        } else if ("9".equals(importType)) { // 红唇丽影
            shopId = "01dca851e2ea4a67806f5f33ce20792b";
            mId = 7380;
            shopName = "";
            record = saveGtw(filePath, arrears, state, shopId, mId, shopName);
            record.set("success", true);
        } else {
            // 拼多多
            if ("1".equals(importType)) {
                shopId = "7b54557199554ee8bc392c514b8377ec";
                mId = 883;
                shopName = "拼多多商城";
            } else if ("3".equals(importType)) { // 保税国际
                shopId = "cb67eac1e9364a09929d4179023d4a25";
                mId = 1169;
                shopName = "保税国际";
            } else if ("5".equals(importType)) {
                shopId = "0d49230fd9504330918ab1b743224abd";
                mId = 6414;
                shopName = "卡乐猫";
            } else if ("6".equals(importType)) {
                shopId = "ce930f7ecf6946b389dd244d42a3c987";
                mId = 6688;
                shopName = "街蜜";
            } else if ("8".equals(importType)) {
                shopId = "d281f70bc48249adaab4cefe45cf8136";
                mId = 57;
                shopName = "你猜";
            } else if ("10".equals(importType)) {
                shopId = "b4124c7271094479ae2c20cce611b5f4";
                mId = 1215;
                shopName = "KKGUAN";
            } else if ("11".equals(importType)) {
                shopId = "42d167e22c1d4df4975bdd79b3cc0923";
                mId = 7411;
                shopName = "小小怪";
            } else if ("12".equals(importType)) {
                shopId = "cb42274a64064882b6a8b2c1c03fe162";
                mId = 1142;
                shopName = "南京汇银";
            }
            record = savePdd(filePath, arrears, shopId, mId, shopName, state);
            record.set("success", true);
        }
        return record;
    }
    
    /**
     * 拼多多订单导入
     * 
     * @param filePath
     * @return
     */
    private Record savePdd(String filePath, String arrears, String shopId, Integer mId, String shopName, String state) {
        Record record = new Record();
        File file = new File(filePath);
        // File file = new File("G:/2.xls");
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
            List<CoolMember> memberList = new ArrayList<CoolMember>();
            for (int i = 1; i <= rowNum; i++) {
                CoolMember member = new CoolMember();
                success = false;
                // 获取到的行数可能会存在问题，所以添加判断标志，都为空时结束循环
                int flag = 0;
                CoolOrder order = new CoolOrder();
                if ("0".equals(arrears)) { // 正常订单
                    order.setArrears(0);
                } else {
                    order.setArrears(1); // 欠费订单
                }
                order.setoType(new Byte("1")); // pc 订单
                order.setOrderType(new Byte("1")); // 一件代发
                if ("0".equals(state)) { // 待支付
                    order.setState(CoolOrder.ORDER_STATE_0);
                } else {
                    order.setState(CoolOrder.ORDER_STATE_1); // 待发货
                    order.setZffs(3); // 微信支付
                }
                
                // order.setState(CoolOrder.ORDER_STATE_1); // 待发货
                // order.setState(CoolOrder.ORDER_STATE_0); // 待支付
                order.setSupplier(shopId);
                order.setmId(mId);
                order.setSupName(shopName);
                CoolOrderItem item = new CoolOrderItem();
                
                row = sheet.getRow(i);
                
                cell = row.getCell(3); // 订单号
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        String orderOldNo = cell.getStringCellValue().trim();
                        List<CoolOrder> oList = coolOrderMapper.getCoolOrderByOrderOldNoList(orderOldNo, shopId);
                        if (CollectionUtils.isNotEmpty(oList)) { // 重复不导入
                            log.info("时间：" + DateUtil.dateToStr(DateUtil.yyyy_MM_dd_HH_mm_ss, new Date()) + shopName
                                + "订单号为" + orderOldNo + "的订单因为存在相同的订单号而终止导入");
                            continue;
                        } else {
                            // System.out.println("====================="+ i + "===" + orderOldNo);
                            order.setOrderOldno(orderOldNo);
                        }
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                
                cell = row.getCell(0); // 商品id
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        int gId = Integer.valueOf(cell.getStringCellValue().trim().toString());
                        CoolProduct product = coolProductMapper.getCoolProductById(gId);
                        if (product == null) {
                            log.info("时间：" + DateUtil.dateToStr(DateUtil.yyyy_MM_dd_HH_mm_ss, new Date()) + shopName
                                + "订单号为" + order.getOrderOldno() + "的订单因为无法根据商品主键" + gId + "获取商品信息而终止导入");
                            continue;
                        }
                        // 生成订单号
                        String orderNo = OrderUtil.generateOrderNo(gId);
                        order.setOrderNo(orderNo);
                        item.setOrderNo(orderNo);
                        
                        CoolProductGg gg = coolProductGgMapper.findCoolProductGgList(gId).get(0);
                        item.setgId(product.getId());
                        item.setName(product.getNameEn());
                        if (StringUtils.isNotEmpty(gg.getLogo())) {
                            item.setLogo(gg.getLogo());
                        } else {
                            item.setLogo(product.getLogo());
                        }
                        item.setGg(gg.getGg());
                        item.setbId(product.getbId());
                        item.setGid(gg.getId());
                        
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(1); // 数量
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        item.setCount(Integer.valueOf(cell.getStringCellValue().trim().toString()));
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                
                cell = row.getCell(6); // 订单金额
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setTotal(new BigDecimal(cell.getStringCellValue().trim().toString()));
                        item.setPrice(new BigDecimal(cell.getStringCellValue().trim().toString()).divide(new BigDecimal(
                            item.getCount()),
                            2));
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(7); // 身份证姓名
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setLinkman(cell.getStringCellValue().trim().toString());
                        member.setRealname(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(8); // 身份证号码
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setIdcard(cell.getStringCellValue().trim().toString());
                        member.setIdcard(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(9); // 收货人
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        // order.setLinkman(cell.getStringCellValue().trim().toString());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(10); // 手机
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setMobile(cell.getStringCellValue().trim());
                        member.setMobile(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(11); // 省
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setLocationP(cell.getStringCellValue().trim());
                        member.setLocationP(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(12); // 市
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setLocationC(cell.getStringCellValue().trim());
                        member.setLocationC(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(13); // 区
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setLocationA(cell.getStringCellValue().trim());
                        member.setLocationA(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(14); // 确认时间
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        // order.setCreatetime(DateUtil.parseDate(cell.getStringCellValue().toString(),DateUtil.yyyy_MM_dd_HH_mm));
                        order.setCreatetime(new Date());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(15); // 街道
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setAddress(cell.getStringCellValue());
                        member.setAddress(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(22); // 支付单号
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setTradeNo(cell.getStringCellValue());
                        // order.setZffs(3); // 微信支付
                        order.setPayTime(new Date(order.getCreatetime().getTime() + 60000));
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                
                cell = row.getCell(23); // 支付方式
                if (cell != null) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        if ("支付宝".equals(cell.getStringCellValue().trim())) {
                            order.setZffs(2);
                        } else if ("微信支付".equals(cell.getStringCellValue().trim())) {
                            order.setZffs(3);
                        }
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                
                cell = row.getCell(24); // 批发价格
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        item.setPfPrice(new BigDecimal(cell.getStringCellValue().trim().toString()));
                    } else {
                        item.setPfPrice(item.getPrice());
                        flag = flag + 1;
                    }
                } else {
                    item.setPfPrice(item.getPrice());
                    flag = flag + 1;
                }
                
                if (flag == 16) {
                    break;
                } else {
                    // orderList.add(order);
                    // orderItemList.add(item);
                    
                    memberList.add(member);
                    coolOrderMapper.saveCoolOrder(order);
                    coolOrderItemMapper.saveCoolOrderItem(item);
                    
                    // 处理商品库存和销量
                    jdbcTemplate.update("update cool_product_gg set stock=stock-" + item.getCount() + ",sales=sales+"
                        + item.getCount() + " where id=?", item.getGid());
                    jdbcTemplate.update("update cool_product set sales=sales+" + item.getCount() + " where id=?",
                        item.getgId());
                    
                    success = true;
                }
                // coolOrderMapper.insertBatch(orderList);
                
            }
            // 注册会员
            // saveUserAfterImportOrder(memberList);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } finally {
            if (file.isFile() && file.exists()) {
                file.delete();
            }
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
     * 金箍棒订单导入
     * 
     * @param filePath
     * @return
     */
    private Record saveJgb(String filePath, String arrears, String state) {
        Record record = new Record();
        File file = new File(filePath);
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
            
            int rowNum = sheet.getLastRowNum();
            List<CoolMember> memberList = new ArrayList<CoolMember>();
            for (int i = 1; i <= rowNum; i++) {
                CoolMember member = new CoolMember();
                success = false;
                // 获取到的行数可能会存在问题，所以添加判断标志，都为空时结束循环
                int flag = 0;
                CoolOrder order = new CoolOrder();
                if ("0".equals(arrears)) { // 正常订单
                    order.setArrears(0);
                } else {
                    order.setArrears(1); // 欠费订单
                }
                order.setoType(new Byte("1")); // pc 订单
                order.setOrderType(new Byte("1")); // 一件代发
                if ("0".equals(state)) { // 待支付
                    order.setState(CoolOrder.ORDER_STATE_0);
                } else {
                    order.setState(CoolOrder.ORDER_STATE_1); // 待发货
                }
                // order.setState(CoolOrder.ORDER_STATE_1); // 待发货
                String shopId = "23e9afc123014ab9950078887a353521";
                order.setSupplier(shopId);
                order.setmId(912);
                order.setSupName("金箍棒网站");
                CoolOrderItem item = new CoolOrderItem();
                
                row = sheet.getRow(i);
                
                cell = row.getCell(1); // 订单号
                if (cell != null) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        String orderOldNo = cell.getStringCellValue().trim();
                        List<CoolOrder> oList = coolOrderMapper.getCoolOrderByOrderOldNoList(orderOldNo, shopId);
                        if (CollectionUtils.isNotEmpty(oList)) { // 重复不导入
                            continue;
                        } else {
                            // order.setOrderNo(cell.getStringCellValue().trim().toString());
                            // item.setOrderNo(cell.getStringCellValue().trim().toString());
                            order.setOrderOldno(cell.getStringCellValue().trim());
                        }
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                
                cell = row.getCell(0); // 商品id
                if (cell != null) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        CoolProduct product =
                            coolProductMapper.getCoolProductById(Integer.valueOf(cell.getStringCellValue()
                                .trim()
                                .toString()));
                        int gId = Integer.valueOf(cell.getStringCellValue().trim().toString());
                        // 生成订单号
                        String orderNo = OrderUtil.generateOrderNo(gId);
                        order.setOrderNo(orderNo);
                        item.setOrderNo(orderNo);
                        
                        CoolProductGg gg = coolProductGgMapper.findCoolProductGgList(gId).get(0);
                        item.setgId(product.getId());
                        item.setName(product.getNameEn());
                        if (StringUtils.isNotEmpty(gg.getLogo())) {
                            item.setLogo(gg.getLogo());
                        } else {
                            item.setLogo(product.getLogo());
                        }
                        item.setGg(gg.getGg());
                        item.setbId(product.getbId());
                        item.setGid(gg.getId());
                        
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(4); // 数量
                if (cell != null) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        item.setCount(Integer.valueOf(cell.getStringCellValue().trim().toString()));
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(5); // 省
                if (cell != null) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setLocationP(cell.getStringCellValue().trim());
                        member.setLocationP(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(6); // 市
                if (cell != null) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setLocationC(cell.getStringCellValue().trim());
                        member.setLocationC(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(7); // 区
                if (cell != null) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setLocationA(cell.getStringCellValue().trim());
                        member.setLocationA(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(8); // 街道
                if (cell != null) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setAddress(cell.getStringCellValue());
                        member.setAddress(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(10); // 收货人
                if (cell != null) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setLinkman(cell.getStringCellValue().trim().toString());
                        member.setRealname(cell.getStringCellValue().trim().toString());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(11); // 手机
                if (cell != null) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setMobile(cell.getStringCellValue().trim());
                        member.setMobile(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(12); // 身份证号码
                if (cell != null) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setIdcard(cell.getStringCellValue().trim());
                        member.setIdcard(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(13); // 收货人邮政编码
                if (cell != null) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setZipcode(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                // cell = row.getCell(13); // 运费
                // if (cell != null) {
                // cell.setCellType(Cell.CELL_TYPE_STRING);
                // if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                // if (isContainsChinese(cell.getStringCellValue())) {
                // order.setPsPrice(new BigDecimal(0));
                // } else {
                // order.setPsPrice(new BigDecimal(cell.getStringCellValue().trim()));
                // }
                // } else {
                // order.setPsPrice(new BigDecimal(0));
                // flag = flag + 1;
                // }
                // } else {
                // flag = flag + 1;
                // }
                cell = row.getCell(15); // 价格
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        item.setPrice(new BigDecimal(cell.getStringCellValue().trim()));
                        order.setTotal(new BigDecimal(cell.getStringCellValue().trim()).multiply(new BigDecimal(
                            item.getCount())).add(order.getPsPrice()));
                    }
                } else {
                    flag = flag + 1;
                }
                
                cell = row.getCell(16); // 批发价格 没有文件，该单元格待定
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        item.setPfPrice(new BigDecimal(cell.getStringCellValue().trim().toString()));
                    } else {
                        item.setPfPrice(item.getPrice());
                        flag = flag + 1;
                    }
                } else {
                    item.setPfPrice(item.getPrice());
                    flag = flag + 1;
                }
                order.setCreatetime(new Date());
                
                if (flag == 13) {
                    break;
                } else {
                    // orderList.add(order);
                    // orderItemList.add(item);
                    coolOrderMapper.saveCoolOrder(order);
                    coolOrderItemMapper.saveCoolOrderItem(item);
                    
                    memberList.add(member);
                    
                    // 处理商品库存和销量
                    jdbcTemplate.update("update cool_product_gg set stock=stock-" + item.getCount() + ",sales=sales+"
                        + item.getCount() + " where id=?", item.getGid());
                    jdbcTemplate.update("update cool_product set sales=sales+" + item.getCount() + " where id=?",
                        item.getgId());
                    
                    // 自动注册会员
                    saveUserAfterImportOrder(memberList);
                    
                    success = true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } finally {
            if (file.isFile() && file.exists()) {
                file.delete();
            }
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
     * 美丽说订单导入
     * 
     * @param filePath
     * @return
     */
    private Record saveMls(String filePath, String arrears, String state) {
        Record record = new Record();
        File file = new File(filePath);
        // File file = new File("G:/2.xls");
        InputStream is = null;
        boolean success = false;
        String msg = "";
        try {
            is = new FileInputStream(file);
            BufferedInputStream binput = new BufferedInputStream(is);
            Workbook hwb;
            hwb = WorkbookFactory.create(binput);
            
            Sheet sheet = hwb.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            
            int rowNum = sheet.getLastRowNum();
            List<CoolMember> memberList = new ArrayList<CoolMember>();
            for (int i = 1; i <= rowNum; i++) {
                CoolMember member = new CoolMember();
                success = false;
                // 获取到的行数可能会存在问题，所以添加判断标志，都为空时结束循环
                int flag = 0;
                CoolOrder order = new CoolOrder();
                if ("0".equals(arrears)) { // 正常订单
                    order.setArrears(0);
                } else {
                    order.setArrears(1); // 欠费订单
                }
                order.setoType(new Byte("1")); // pc 订单
                order.setOrderType(new Byte("1")); // 一件代发
                if ("0".equals(state)) { // 待支付
                    order.setState(CoolOrder.ORDER_STATE_0);
                } else {
                    order.setState(CoolOrder.ORDER_STATE_1); // 待发货
                }
                // order.setState(CoolOrder.ORDER_STATE_1); // 待发货
                String shopId = "9d2346b10b5e49289c6c8ac7dba93d5e";
                order.setSupplier(shopId);
                order.setmId(5274);
                order.setSupName("美丽说");
                CoolOrderItem item = new CoolOrderItem();
                
                row = sheet.getRow(i);
                if (row != null) {
                    cell = row.getCell(0); // 订单号
                    if (cell != null) {
                        // 将单元格格式设为string，方便判断是否为空
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                            String orderOldNo = cell.getStringCellValue().trim();
                            List<CoolOrder> oList = coolOrderMapper.getCoolOrderByOrderOldNoList(orderOldNo, shopId);
                            if (CollectionUtils.isNotEmpty(oList)) { // 重复不导入
                                log.info("时间：" + DateUtil.dateToStr(DateUtil.yyyy_MM_dd_HH_mm_ss, new Date())
                                    + "美丽说订单号为" + orderOldNo + "的订单因为存在相同的订单号而终止导入");
                                continue;
                            } else {
                                order.setOrderOldno(orderOldNo);
                            }
                        } else {
                            flag = flag + 1;
                        }
                    } else {
                        flag = flag + 1;
                    }
                    cell = row.getCell(1); // 商品id
                    if (cell != null) {
                        // 将单元格格式设为string，方便判断是否为空
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                            int gId = Integer.valueOf(cell.getStringCellValue().trim().toString());
                            CoolProduct product = coolProductMapper.getCoolProductById(gId);
                            if (product == null) {
                                msg += "外部订单号为" + order.getOrderOldno() + "的订单因为无法根据商品主键" + gId + "获取商品信息而终止导入;";
                                continue;
                            }
                            // 生成订单号
                            String orderNo = OrderUtil.generateOrderNo(gId);
                            order.setOrderNo(orderNo);
                            item.setOrderNo(orderNo);
                            
                            List<CoolProductGg> ggList = coolProductGgMapper.findCoolProductGgList(gId);
                            CoolProductGg gg = new CoolProductGg();
                            if (CollectionUtils.isEmpty(ggList)) {
                                msg += "外部订单号为" + order.getOrderOldno() + "的订单因为无法根据商品主键" + gId + "获取商品规格信息而终止导入;";
                                continue;
                            } else {
                                gg = ggList.get(0);
                            }
                            
                            item.setgId(product.getId());
                            item.setName(product.getNameEn());
                            if (StringUtils.isNotEmpty(gg.getLogo())) {
                                item.setLogo(gg.getLogo());
                            } else {
                                item.setLogo(product.getLogo());
                            }
                            item.setGg(gg.getGg());
                            item.setbId(product.getbId());
                            item.setGid(gg.getId());
                            
                        } else {
                            flag = flag + 1;
                        }
                    } else {
                        flag = flag + 1;
                    }
                    cell = row.getCell(2); // 支付单号
                    if (cell != null) {
                        // 将单元格格式设为string，方便判断是否为空
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                            order.setTradeNo(cell.getStringCellValue());
                            // order.setZffs(2); // 支付宝
                        } else {
                            flag = flag + 1;
                        }
                    } else {
                        flag = flag + 1;
                    }
                    cell = row.getCell(3); // 支付方式
                    if (cell != null) {
                        // 将单元格格式设为string，方便判断是否为空
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                            if ("支付宝支付".equals(cell.getStringCellValue())) {
                                order.setZffs(2); // 支付宝
                            } else if ("微信支付".equals(cell.getStringCellValue())) {
                                order.setZffs(3); // 微信
                            }
                        } else {
                            flag = flag + 1;
                        }
                    } else {
                        flag = flag + 1;
                    }
                    cell = row.getCell(5); // 报关交易号
                    if (cell != null) {
                        // 将单元格格式设为string，方便判断是否为空
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                            order.setTradeNo(cell.getStringCellValue());
                        } else {
                            flag = flag + 1;
                        }
                    } else {
                        flag = flag + 1;
                    }
                    cell = row.getCell(9); // 数量
                    if (cell != null) {
                        // 将单元格格式设为string，方便判断是否为空
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                            item.setCount(Integer.valueOf(cell.getStringCellValue().trim().toString()));
                        } else {
                            flag = flag + 1;
                        }
                    } else {
                        flag = flag + 1;
                    }
                    cell = row.getCell(6); // 总价
                    if (cell != null) {
                        // 将单元格格式设为string，方便判断是否为空
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                            order.setTotal(new BigDecimal(cell.getStringCellValue().trim().toString()));
                            item.setPrice(new BigDecimal(cell.getStringCellValue().trim().toString()).divide(new BigDecimal(
                                item.getCount()),
                                2,
                                BigDecimal.ROUND_HALF_UP));
                            // item.setPrice(new BigDecimal(cell.getStringCellValue().trim().toString()));
                            // order.setTotal(new BigDecimal(cell.getStringCellValue().trim().toString()).multiply(new
                            // BigDecimal(item.getCount())));
                        } else {
                            flag = flag + 1;
                        }
                    } else {
                        flag = flag + 1;
                    }
                    // cell = row.getCell(10); // 收货人
                    // if (cell != null) {
                    // // 将单元格格式设为string，方便判断是否为空
                    // cell.setCellType(Cell.CELL_TYPE_STRING);
                    // if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                    // order.setLinkman(cell.getStringCellValue().trim().toString());
                    // } else {
                    // flag = flag + 1;
                    // }
                    // } else {
                    // flag = flag + 1;
                    // }
                    cell = row.getCell(14); // 身份证姓名
                    if (cell != null) {
                        // 将单元格格式设为string，方便判断是否为空
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                            order.setLinkman(cell.getStringCellValue().trim().toString());
                            member.setRealname(cell.getStringCellValue().trim());
                        } else {
                            flag = flag + 1;
                        }
                    } else {
                        flag = flag + 1;
                    }
                    cell = row.getCell(15); // 手机
                    if (cell != null) {
                        // 将单元格格式设为string，方便判断是否为空
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                            order.setMobile(cell.getStringCellValue().trim());
                            member.setMobile(cell.getStringCellValue().trim());
                        } else {
                            flag = flag + 1;
                        }
                    } else {
                        flag = flag + 1;
                    }
                    cell = row.getCell(16); // 地址
                    if (cell != null) {
                        // 将单元格格式设为string，方便判断是否为空
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                            String[] dz = cell.getStringCellValue().trim().split(",");
                            // if(dz.length < 4){
                            // record.set("msg", "订单号为" + order.getOrderOldno() + "的记录地址格式错误");
                            // break;
                            // }
                            order.setLocationP(dz[0]);
                            member.setLocationP(dz[0]);
                            if ("市辖区".equals(dz[1])) {
                                dz[1] = dz[0];
                            }
                            order.setLocationC(dz[1]);
                            member.setLocationC(dz[1]);
                            order.setLocationA(dz[2]);
                            member.setLocationA(dz[2]);
                            int length = dz.length;
                            String address = dz[3];
                            if (length > 4) {
                                for (int k = 4; k < length; k++) {
                                    address += dz[k];
                                }
                            }
                            order.setAddress(address);
                            member.setAddress(address);
                        } else {
                            flag = flag + 1;
                        }
                    } else {
                        flag = flag + 1;
                    }
                    cell = row.getCell(25); // 确认时间
                    if (cell != null) {
                        // 将单元格格式设为string，方便判断是否为空
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                            order.setCreatetime(DateUtil.parseDate(cell.getStringCellValue().toString(),
                                DateUtil.yyyy_MM_dd_HH_mm));
                        } else {
                            flag = flag + 1;
                        }
                    } else {
                        flag = flag + 1;
                    }
                    cell = row.getCell(27); // 支付时间
                    if (cell != null) {
                        // 将单元格格式设为string，方便判断是否为空
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                            order.setPayTime(DateUtil.parseDate(cell.getStringCellValue().toString(),
                                DateUtil.yyyy_MM_dd_HH_mm));
                        } else {
                            flag = flag + 1;
                        }
                    } else {
                        flag = flag + 1;
                    }
                    cell = row.getCell(29); // 身份证号码
                    if (cell != null) {
                        // 将单元格格式设为string，方便判断是否为空
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                            order.setIdcard(cell.getStringCellValue().trim().toString());
                            member.setIdcard(cell.getStringCellValue().trim());
                        } else {
                            flag = flag + 1;
                        }
                    } else {
                        flag = flag + 1;
                    }
                    
                    cell = row.getCell(32); // 批发价格
                    if (cell != null) {
                        // 将单元格格式设为string，方便判断是否为空
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                            item.setPfPrice(new BigDecimal(cell.getStringCellValue().trim().toString()));
                        } else {
                            item.setPfPrice(item.getPrice());
                            flag = flag + 1;
                        }
                    } else {
                        item.setPfPrice(item.getPrice());
                        flag = flag + 1;
                    }
                    
                    if (flag == 14) {
                        break;
                    } else {
                        // orderList.add(order);
                        // orderItemList.add(item);
                        
                        memberList.add(member);
                        coolOrderMapper.saveCoolOrder(order);
                        coolOrderItemMapper.saveCoolOrderItem(item);
                        
                        // 处理商品库存和销量
                        jdbcTemplate.update("update cool_product_gg set stock=stock-" + item.getCount()
                            + ",sales=sales+" + item.getCount() + " where id=?", item.getGid());
                        jdbcTemplate.update("update cool_product set sales=sales+" + item.getCount() + " where id=?",
                            item.getgId());
                        
                        success = true;
                    }
                }
            }
            // 注册会员
            // saveUserAfterImportOrder(memberList);
        } catch (FileNotFoundException e) {
            msg += "未找到指定文件";
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } finally {
            if (file.isFile() && file.exists()) {
                file.delete();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        record.set("msg", msg);
        record.set("success", success);
        return record;
    }
    
    /**
     * 判断是否存在中文
     * 
     * @param str
     * @return
     */
    public static boolean isContainsChinese(String str) {
        String regEx = "[\u4e00-\u9fa5]";
        Pattern pat = Pattern.compile(regEx);
        Matcher matcher = pat.matcher(str);
        boolean flg = false;
        if (matcher.find()) {
            flg = true;
        }
        return flg;
    }
    
    /**
     * 订单导入之后注册会员
     * 
     * @param memberList
     */
    private void saveUserAfterImportOrder(List<CoolMember> memberList) {
        for (CoolMember m : memberList) {
            String mobile = m.getMobile();
            if (StringUtils.isNotEmpty(mobile)) {
                CoolUser u = coolUserMapper.getCoolUserByUserName(mobile);
                if (u == null) {
                    CoolUser user = new CoolUser();
                    user.setUsername(mobile);
                    String password = mobile.substring(mobile.length() - 6, mobile.length());
                    user.setPassword(DigestUtils.md5Hex(password));
                    user.setState(1);
                    user.setMobile(mobile);
                    coolUserMapper.saveCoolUser(user);
                    
                    String maxNo = coolMemberMapper.getMaxCoolMemberNo();
                    String no = null;
                    if (StringUtils.isEmpty(maxNo)) {
                        no = "10000000";
                    } else {
                        no = (Integer.parseInt(maxNo) + 1) + "";
                    }
                    CoolUser u2 = coolUserMapper.getCoolUserByUserName(mobile);
                    m.setUserId(u2.getId());
                    m.setNo(no);
                    coolMemberMapper.saveCoolMember(m);
                }
            }
        }
    }
    
    @Override
    public Record CreateDate() {
        String date = "";
        String year = "";
        String quarter = "";
        String month = "";
        String day = "";
        String week = "";
        String longTime = "";
        Calendar cal = Calendar.getInstance();
        cal.set(2015, 6, 1);
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        for (int i = 0; i < 162; i++) {
            cal.set(2015, 6, 1);
            cal.add(Calendar.DAY_OF_MONTH, i);
            year = cal.get(Calendar.YEAR) + "";
            int m = cal.get(Calendar.MONTH) + 1;
            if (m < 10) {
                month = "0" + m;
            } else {
                month = cal.get(Calendar.MONTH) + 1 + "";
            }
            int d = cal.get(Calendar.DAY_OF_MONTH);
            if (d < 10) {
                day = "0" + d;
            } else {
                day = d + "";
            }
            if (m <= 3) {
                quarter = "第一季度";
            } else if (m >= 4 && m <= 6) {
                quarter = "第二季度";
            } else if (m >= 7 && m <= 9) {
                quarter = "第三季度";
            } else if (m >= 10 && m <= 12) {
                quarter = "第四季度";
            }
            int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0) {
                w = 0;
            }
            week = weekDays[w];
            date = year + "-" + month + "-" + day;
            DateUtil.strToDate(DateUtil.yyyy_MM_dd_HH_mm_ss, date + " 00:00:00");
            // longTime = String.valueOf(cal.getTime().getTime());
            longTime = String.valueOf(DateUtil.strToDate(DateUtil.yyyy_MM_dd_HH_mm_ss, date + " 00:00:00").getTime());
            
            BiDate bd = new BiDate();
            bd.setDate(date);
            bd.setYear(year);
            bd.setQuarter(quarter);
            bd.setMonth(month);
            bd.setDay(day);
            bd.setWeek(week);
            bd.setLongtime(longTime);
            biDateMapper.saveBiDate(bd);
        }
        
        return null;
    }
    
    /**
     * 瓜藤网订单导入
     * 
     * @param filePath 文件路径
     * @param arrears 正常或者欠费订单
     * @param state 订单状态
     * @param shopId 酷店主键
     * @param mId 会员主键（配合新后台查询，需要用userId）
     * @param shopName 酷店名称
     * @return
     */
    private Record saveGtw(String filePath, String arrears, String state, String shopId, Integer mId, String shopName) {
        Record record = new Record();
        boolean success = false;
        String msg = "";
        File file = new File(filePath);
        // File file = new File("G:/2.xlsx");
        InputStream is = null;
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
            List<CoolMember> memberList = new ArrayList<CoolMember>();
            for (int i = 1; i <= rowNum; i++) {
                CoolMember member = new CoolMember();
                success = false;
                // 获取到的行数可能会存在问题，所以添加判断标志，都为空时结束循环
                int flag = 0;
                CoolOrder order = new CoolOrder();
                if ("0".equals(arrears)) { // 正常订单
                    order.setArrears(0);
                } else {
                    order.setArrears(1); // 欠费订单
                }
                order.setoType(new Byte("1")); // pc 订单
                order.setOrderType(new Byte("1")); // 一件代发
                if ("0".equals(state)) { // 待支付
                    order.setState(CoolOrder.ORDER_STATE_0);
                } else {
                    order.setState(CoolOrder.ORDER_STATE_1); // 待发货
                }
                
                // order.setState(CoolOrder.ORDER_STATE_1); // 待发货
                // order.setState(CoolOrder.ORDER_STATE_0); // 待支付
                // String shopId = "bc58f014499e44448d744bdf0aff0071";
                order.setSupplier(shopId);
                order.setmId(mId);
                order.setSupName(shopName);
                order.setCreatetime(new Date());
                CoolOrderItem item = new CoolOrderItem();
                
                row = sheet.getRow(i);
                
                cell = row.getCell(18); // 订单号
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        String orderOldNo = cell.getStringCellValue().trim();
                        List<CoolOrder> oList = coolOrderMapper.getCoolOrderByOrderOldNoList(orderOldNo, shopId);
                        if (CollectionUtils.isNotEmpty(oList)) { // 重复不导入
                            msg += "订单号为" + orderOldNo + "的订单因为存在相同的订单号而终止导入;";
                            log.info("时间：" + DateUtil.dateToStr(DateUtil.yyyy_MM_dd_HH_mm_ss, new Date()) + shopName
                                + "订单号为" + orderOldNo + "的订单因为存在相同的订单号而终止导入");
                            continue;
                        } else {
                            order.setOrderOldno(orderOldNo);
                        }
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                
                cell = row.getCell(5); // 商品规格id
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        // CoolProduct product =
                        // coolProductMapper.getCoolProductById(Integer.valueOf(cell.getStringCellValue().trim().toString()));
                        int ggId = Integer.valueOf(cell.getStringCellValue().trim().toString());
                        // 生成订单号
                        String orderNo = OrderUtil.generateOrderNo(ggId);
                        order.setOrderNo(orderNo);
                        item.setOrderNo(orderNo);
                        
                        CoolProductGg gg = coolProductGgMapper.getCoolProductGgById(ggId);
                        if (gg == null) {
                            msg += "订单号为" + order.getOrderOldno() + "的订单因为无法根据规格主键" + ggId + "获取商品信息而终止导入;";
                            continue;
                        }
                        CoolProduct product = coolProductMapper.getCoolProductById(gg.getGoodsId());
                        item.setgId(product.getId());
                        item.setName(product.getNameEn());
                        if (StringUtils.isNotEmpty(gg.getLogo())) {
                            item.setLogo(gg.getLogo());
                        } else {
                            item.setLogo(product.getLogo());
                        }
                        item.setGg(gg.getGg());
                        item.setbId(product.getbId());
                        item.setGid(gg.getId());
                        
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(8); // 数量
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        item.setCount(Integer.valueOf(cell.getStringCellValue().trim().toString()));
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(11); // 收货人
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setLinkman(cell.getStringCellValue().trim().toString());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(12); // 手机
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setMobile(cell.getStringCellValue().trim());
                        member.setMobile(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(13); // 省
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setLocationP(cell.getStringCellValue().trim());
                        member.setLocationP(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(14); // 市
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setLocationC(cell.getStringCellValue().trim());
                        member.setLocationC(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(15); // 区
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setLocationA(cell.getStringCellValue().trim());
                        member.setLocationA(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(16); // 街道
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setAddress(cell.getStringCellValue());
                        member.setAddress(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(22); // 身份证号码
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setIdcard(cell.getStringCellValue().trim().toString());
                        member.setIdcard(cell.getStringCellValue().trim());
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                /*
                 * cell = row.getCell(23); // 订单金额 if (cell != null) { // 将单元格格式设为string，方便判断是否为空
                 * cell.setCellType(Cell.CELL_TYPE_STRING); if
                 * (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) { order.setTotal(new
                 * BigDecimal(cell.getStringCellValue().trim().toString())); item.setPrice(new
                 * BigDecimal(cell.getStringCellValue().trim().toString()).divide(new BigDecimal( item.getCount()), 2));
                 * } else { flag = flag + 1; } } else { flag = flag + 1; }
                 */
                cell = row.getCell(24); // 支付方式
                if (cell != null) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        if ("支付宝".equals(cell.getStringCellValue().trim())) {
                            order.setZffs(2);
                        } else if ("微信支付".equals(cell.getStringCellValue().trim())) {
                            order.setZffs(3);
                        }
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(25); // 支付单号
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        order.setTradeNo(cell.getStringCellValue());
                        // order.setZffs(3); // 微信支付
                        order.setPayTime(new Date(order.getCreatetime().getTime() + 60000));
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                cell = row.getCell(26); // 供货价（同时也是批发价）
                if (cell != null) {
                    // 将单元格格式设为string，方便判断是否为空
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    if (StringUtils.isNotEmpty(cell.getStringCellValue().trim())) {
                        item.setPfPrice(new BigDecimal(cell.getStringCellValue().trim().toString()));
                        item.setPrice(new BigDecimal(cell.getStringCellValue().trim().toString()));
                        order.setTotal(new BigDecimal(cell.getStringCellValue().trim().toString()).multiply(new BigDecimal(
                            item.getCount())));
                    } else {
                        flag = flag + 1;
                    }
                } else {
                    flag = flag + 1;
                }
                
                if (flag == 14) {
                    break;
                } else {
                    // orderList.add(order);
                    // orderItemList.add(item);
                    
                    memberList.add(member);
                    coolOrderMapper.saveCoolOrder(order);
                    coolOrderItemMapper.saveCoolOrderItem(item);
                    
                    // 处理商品库存和销量
                    jdbcTemplate.update("update cool_product_gg set stock=stock-" + item.getCount() + ",sales=sales+"
                        + item.getCount() + " where id=?", item.getGid());
                    jdbcTemplate.update("update cool_product set sales=sales+" + item.getCount() + " where id=?",
                        item.getgId());
                    
                    success = true;
                }
                // coolOrderMapper.insertBatch(orderList);
                
            }
            // 注册会员
            // saveUserAfterImportOrder(memberList);
            
        } catch (FileNotFoundException e) {
            msg += "没有找到指定路径的文件；";
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } finally {
            if (file.isFile() && file.exists()) {
                file.delete();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        record.set("msg", msg);
        record.set("success", success);
        return record;
    }
    
}
