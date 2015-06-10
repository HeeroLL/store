package com.zebone.btp.empi.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zebone.btp.Constant;
import com.zebone.btp.empi.EmpiUtil;
import com.zebone.btp.empi.vo.EmpiCard;
import com.zebone.btp.empi.vo.EmpiUser;
import com.zebone.btp.empi.vo.ImportData;
import com.zebone.util.UUIDUtil;

public class ProcessExcel implements ProcessFileUtils {

	/**
	 * 从excel文档中读取数据
	 * 
	 * @param fileName
	 * @return
	 */
	public List<EmpiUser> readFromFile(ImportData data) throws IOException {

		// 根据excel的后缀选取不同的对象
		Workbook wb = null;

		// 带路径的文件名
		String fileName = data.getAbsolutePath();

		if (fileName.endsWith(SUFFIX_EXCEL_XLS)) {
			wb = new HSSFWorkbook(new FileInputStream(fileName));
		} else if (fileName.endsWith(SUFFIX_EXCEL_XLSX)) {
			wb = new XSSFWorkbook(new FileInputStream(fileName));
		}

		// 结果集
		List<EmpiUser> userList = null;

		// 计算记录的条数 第一行标题头除外
		int index = wb.getNumberOfSheets();

		if (index > 0) {

			userList = new ArrayList<EmpiUser>();
			Sheet sheet;

			// 工作簿
			for (int i = 0; i < index; i++) {
				sheet = wb.getSheetAt(i);
				EmpiUser user;
				Cell cell = null;
				for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {

					Row row = sheet.getRow(rowNum);
					// 剔除空行和标题（第0行）
					if (row == null || rowNum == 0) {
						continue;
					}
					user = new EmpiUser();// 一个row对应一个user对象
					user.setUserId(UUIDUtil.getUuid()); // userid
					user.setCreateDate(EmpiUtil.getTimeNow()); // 创建时间
					user.setCreator(data.getLoginName()); // 创建人
					user.setState(Constant.STATE_NORMAL); // 设置默认状态1
					user.setDelFlag(Constant.DEL_FLAG_NORMAL);// 设置默认删除标识1

					// 根据不同的模板导入不同的数据
					getTemplate(data, row, cell, user);

					// EMPI 是身份证 则赋值icn
					if (Constant.IDENTIFY_CARD_NO.equals(data.getEmpiType())) {
						user.setIcn(user.getEmpiId());// 将EMPIID的值赋给身份证
						user.setBirthday(EmpiUtil.getBirthday(user.getIcn()));
						user.setSex(EmpiUtil.getSex(user.getIcn()));
					}

					userList.add(user);
				}
			}
		}

		return userList;
	}

	/**
	 * 读取xlsx文档单元格内容 根据单元格不同属性返回字符串数值
	 * 
	 * @param cell
	 * @return
	 */
	private String getCellValue(Cell cell) {

		String cellvalue = "";

		if (cell != null) {

			switch (cell.getCellType()) {

			case Cell.CELL_TYPE_STRING:
				cellvalue = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellvalue = String.valueOf(cell.getBooleanCellValue());
				break;

			case Cell.CELL_TYPE_NUMERIC:
				cellvalue = String.valueOf(cell.getNumericCellValue());
				break;

			case Cell.CELL_TYPE_BLANK:
				break;

			case Cell.CELL_TYPE_ERROR:
				break;

			case Cell.CELL_TYPE_FORMULA:
				break;

			default:
				cellvalue = cell.getStringCellValue();
				break;
			}

		}
		return cellvalue;
	}

	/**
	 * 
	 * 根据不同模板 导入数据信息
	 * 
	 * @param data
	 * @param row
	 * @param cell
	 * @param user
	 */
	private void getTemplate(ImportData data, Row row, Cell cell, EmpiUser user) {
		if (null != data) {
			// Integer templateType = Integer.parseInt(data.getTemplate());
			String templateType = data.getTemplate();
			// 根据不同的证件类型 调用不同的处理方法
			if (Constant.IMPORT_DATA_TYPE_PERSON.equals(templateType)) {
				processPersonTemplate(row, cell, user);
			} else if (Constant.IMPORT_DATA_TYPE_SOCIAL.equals(templateType)) {
				processSocialInsuranceTemplate(row, cell, user);
			} else{
				processSocialInsuranceTemplate(row, cell, user);
			}
		}

	}

	/**
	 * 处理 人口数据
	 * 
	 * @param row
	 * @param cell
	 * @param user
	 */
	private void processPersonTemplate(Row row, Cell cell, EmpiUser user) {

		if (row != null) {
			for (int k = 0; k <= row.getLastCellNum(); k++) {

				cell = row.getCell(k);
				if (cell == null) {
					continue;
				}

				switch (k) {
				case 0:
					user.setEmpiId(getCellValue(cell));// empiId
					break;
				case 1:
					user.setUserName(getCellValue(cell));// 姓名
					break;
				case 2:
					user.setRegaddress(getCellValue(cell));// 户籍地址
					break;
				case 3:
					user.setTel(getCellValue(cell));// 联系电话
					break;
				case 4:
					user.setNation(getCellValue(cell));// 民族
					break;
				default:
					break;
				}
			}
		}
	}

	/**
	 * 
	 * 处理 社保数据
	 * 
	 * @param row
	 * @param cell
	 * @param user
	 */
	private void processSocialInsuranceTemplate(Row row, Cell cell,
			EmpiUser user) {

		if (row != null) {

			EmpiCard card = new EmpiCard();
			for (int k = 0; k <= row.getLastCellNum(); k++) {

				cell = row.getCell(k);
				if (cell == null) {
					continue;
				}

				switch (k) {
				case 0:
					card.setCardType(getCellValue(cell));// 介质代码
					break;
				case 1:
					card.setCardNo(getCellValue(cell));// 证件号码
					break;
				case 2:
					card.setCardSid(getCellValue(cell));// 序列号
					break;
				case 3:
					user.setEmpiId(getCellValue(cell));// 身份证码号
					break;
				case 4:
					user.setUserName(getCellValue(cell));// 姓名
					break;
				case 5:
					user.setPreaddress(getCellValue(cell));// 现居住地址
					break;
				case 6:
					user.setTel(getCellValue(cell));// 联系电话
					break;
				case 7:
					user.setNation(getCellValue(cell));// 民族
					break;
				default:
					break;
				}
			}
			card.setCardId(UUIDUtil.getUuid());
			card.setCardState(Constant.STATE_NORMAL);
			card.setEmpiId(user.getEmpiId());

			List<EmpiCard> cards = new ArrayList<EmpiCard>();
			cards.add(card);

			user.setCards(cards);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ProcessExcel p = new ProcessExcel();

		ImportData data = new ImportData();
		data.setAbsolutePath("WebContent/file/excel/2007/card_2007.xlsx");
		// data.setAbsolutePath("WebContent/file/excel/2007/card_2003.xls");
		try {
			p.readFromFile(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
