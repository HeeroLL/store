package com.zebone.btp.empi.utils;

import java.io.IOException;
import java.util.List;

import com.zebone.btp.empi.vo.EmpiUser;
import com.zebone.btp.empi.vo.ImportData;

public interface ProcessFileUtils {

	/* 文件后缀名 */
	public static final String SUFFIX_EXCEL_XLSX = ".xlsx";

	public static final String SUFFIX_EXCEL_XLS = ".xls";
	
	List<EmpiUser> readFromFile(ImportData data) throws IOException;
}
