package com.mrx.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间处理
 */

public class DateFormart {

	private static String YYYYMMDD = "yyyyMMdd";
	private static String YYYY_MM_DD = "yyyy-MM-dd";
	private static String YYYYXMMXDD = "yyyy/MM/dd";
	private static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	private static String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
	private static String YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
	private static String YYYYXMMXDDXHHMMSS = "yyyy/MM/dd HH:mm:ss";

	public DateFormart(String string) {
		// TODO Auto-generated constructor stub
	}

	public static String convertToString(Date date, String str) {
		if (StringUtil.isEmpty(date) || StringUtil.isEmpty(str)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(str);
		return sdf.format(date);
	}

	public static Date pareStrToDate(String strDate, String formatStr) {
		if (StringUtil.isEmpty(strDate) || StringUtil.isEmpty(formatStr)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	// 时间（“2012-4-5”）转成时间戳（1460044800000）
	public static long DateTopare(String strDate) {

		SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
		Date date = null;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long timeStemp = date.getTime();
		return timeStemp;

	}

	public static String convertToString(Long dates, String str) {
		// if (StringUtil.isEmpty(date) || StringUtil.isEmpty(str)) {
		// return null;
		// }
		SimpleDateFormat sdf = new SimpleDateFormat(str);
		// return sdf.format(new Date(Long.parseLong(date)));
		Date date = new Date(dates);
		return sdf.format(date);
	}

	public static String convertToYYYYMMDD(Date date) {
		return convertToString(date, YYYYMMDD);
	}

	public static String convertToYYYY_MM_DD(Date date) {
		return convertToString(date, YYYY_MM_DD);
	}

	public static String convertToYYYY_MM_DD(Long date) {
		return convertToString(date, YYYY_MM_DD);
	}

	public static String convertToYYYYXMMXDD(Date date) {
		return convertToString(date, YYYYXMMXDD);
	}

	public static String convertToYYYYMMDDHHMMSS(Date date) {
		return convertToString(date, YYYYMMDDHHMMSS);
	}

	public static String convertToYYYYMMDDHHMMSSSSS(Date date) {
		return convertToString(date, YYYYMMDDHHMMSSSSS);
	}

	public static String convertToYYYY_MM_DD_HHMMSS(Date date) {
		return convertToString(date, YYYY_MM_DD_HHMMSS);
	}

	public static String convertToYYYYXMMXDDXHHMMSS(Date date) {
		return convertToString(date, YYYYXMMXDDXHHMMSS);
	}

	public static Date paserYYYY_MM_DDToDate(String strDate) {
		return pareStrToDate(strDate, YYYY_MM_DD);
	}

	public static Date paserYYYY_MM_DD_HHMMSSToDate(String strDate) {
		return pareStrToDate(strDate, YYYY_MM_DD_HHMMSS);
	}

	public static Date paserYYYY_MM_DD_HHMMSSToDate(long longTime) {
		Date date = new Date(longTime * 1000);
		String dt = convertToYYYY_MM_DD_HHMMSS(date);
		return paserYYYY_MM_DD_HHMMSSToDate(dt);
	}
	
	public static String getNowDate(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}
	
	public static void main(String[] args) {
		// for(int i=0;i<10;i++){
		// // System.out.println(UUID.randomUUID());
//		System.out.println(DateFormart.convertToYYYY_MM_DD(new Date()));
//		System.out.println(DateFormart.DateTopare(DateFormart
//				.convertToYYYY_MM_DD(new Date())));
		// }
			System.out.println(getNowDate());
		// String a="每日精选";
		// String b = "每日精选";
		//
		// if(a.equals(b)){
		// System.out.println("相同");
		// }else{
		// System.out.println("不同");
		// }
		//
				System.out.println(0*10);
		// 1460044800000
		// Date a = new Date(YYYY_MM_DD);
		// long end_time = DateTopare("2016-4-25");
		// System.out.println(end_time);
		// Long time = DateTopare("2016-4-8");
		// System.out.println(time);
		// int data= (int) ((end_time - time)/(24*60*60*1000));
		// System.out.println(data);
		// System.out.println(convertToYYYY_MM_DD(end_time - time) );
		// System.out.println(DateTopare("2016-4-8"));

	}
}
