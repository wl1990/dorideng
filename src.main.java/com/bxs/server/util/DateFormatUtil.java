package com.bxs.server.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
	private static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * this date to string
	 * @param pattern 时间格式 yyyy-MM-dd HH:mm:ss|yyyy-MM-dd
	 * @param date 日期  
	 */
	public static String dateToString(Date date,String pattern){
		if (pattern != null && !pattern.trim().isEmpty())
			sdf.applyPattern(pattern);
		if (date != null)
			return sdf.format(date);
		else
			return sdf.format(new Date());
	}
	
	/**
	 * current date to String
	 * @param pattern 时间格式 yyyy-MM-dd HH:mm:ss|yyyy-MM-dd
	 * @return
	 */
	public static String dateToString(String pattern){
		if (pattern != null && !pattern.trim().isEmpty())
			sdf.applyPattern(pattern);
		return sdf.format(new Date());
	}
	/**
	 * current date to String yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String dateToString(){
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
  
}
