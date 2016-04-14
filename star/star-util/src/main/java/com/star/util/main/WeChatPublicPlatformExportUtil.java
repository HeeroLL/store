package com.star.util.main;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.star.util.ConfigInfo;
import com.star.util.wechat.service.WeChatPublicPlatformService;

/**
 * 
 * 微信公众号平台导出工具类
 * 
 * @author lilin
 * @version [版本号, 2016年3月30日]
 */
public final class WeChatPublicPlatformExportUtil {
    
    private static ApplicationContext context;
    
    /**
     * 导出入口
     * 
     * @param args 参数
     */
    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("classpath:ApplicationContext.xml");
        WeChatPublicPlatformService wxService = context.getBean(WeChatPublicPlatformService.class);
        ConfigInfo config = context.getBean(ConfigInfo.class);
        
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar cal = Calendar.getInstance();
        if (StringUtils.isNotEmpty(config.getEndDate()) && NumberUtils.isDigits(config.getEndDate()) && config.getEndDate().length() == 8) {
            int year = Integer.parseInt(config.getEndDate().substring(0, 4));
            int month = Integer.parseInt(config.getEndDate().substring(4, 6)) - 1;
            int date = Integer.parseInt(config.getEndDate().substring(6));
            cal.set(year, month, date);
        }
        
        if (config.getHour() != null) {
            cal.set(Calendar.HOUR_OF_DAY, config.getHour());
        } else {
            cal.set(Calendar.HOUR_OF_DAY, 16);
        }
        if (config.getMinute() != null) {
            cal.set(Calendar.MINUTE, config.getMinute());
        } else {
            cal.set(Calendar.MINUTE, 0);
        }
        if (config.getSecond() != null) {
            cal.set(Calendar.SECOND, config.getSecond());
        } else {
            cal.set(Calendar.SECOND, 0);
        }
        // 导出哪一天的
        Date endDate = cal.getTime();
        
        if (StringUtils.isNotEmpty(config.getBeginDate()) && NumberUtils.isDigits(config.getBeginDate()) && config.getBeginDate().length() == 8) {
            int year = Integer.parseInt(config.getBeginDate().substring(0, 4));
            int month = Integer.parseInt(config.getBeginDate().substring(4, 6)) - 1;
            int date = Integer.parseInt(config.getBeginDate().substring(6));
            cal.set(year, month, date);
        } else {
            cal.add(Calendar.DATE, -1); // 没有配置就导昨天的
        }
        
        Date beginDate = cal.getTime();
        String beginTime = df.format(beginDate);
        String endTime = df.format(endDate);
        
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(beginTime + "-" + endTime + "数据");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        // HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        // HSSFCellStyle style = wb.createCellStyle();
        // style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        
        // 获取“我有场地”数据
        List<Map<String, Object>> resultList = wxService.getSiteInfoListByTime(beginTime, endTime);
        int rowIndex = 0;
        if (CollectionUtils.isNotEmpty(resultList)) {
            for (Map<String, Object> map : resultList) {
                HSSFRow row = sheet.createRow(rowIndex++);
                int cellIndex = 0;
                for (Object value : map.values()) {
                    row.createCell(cellIndex++).setCellValue(String.valueOf(value));
                }
            }
        }
        
        // 获取“我要代理”数据
        resultList = wxService.getAgentInfoListByTime(beginTime, endTime);
        
        if (CollectionUtils.isNotEmpty(resultList)) {
            for (Map<String, Object> map : resultList) {
                HSSFRow row = sheet.createRow(rowIndex++);
                int cellIndex = 0;
                for (Object value : map.values()) {
                    row.createCell(cellIndex++).setCellValue(String.valueOf(value));
                }
            }
        }
        try {
            FileOutputStream fout =
                new FileOutputStream(new SimpleDateFormat("yyyyMMdd").format(beginDate) + '-' + new SimpleDateFormat("yyyyMMdd").format(endDate) + ".xls");
            wb.write(fout);
            fout.close();
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
