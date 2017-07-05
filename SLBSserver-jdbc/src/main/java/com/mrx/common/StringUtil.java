package com.mrx.common;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String 工具�?
 */
public class StringUtil {

	public static final String  FS_STR="";
	public static final String  IMG_CACHE = "C:/word/upimg/";//上传图片的缓存路径，如果图片�?��之内没有操作的话，将被删�?
	public static final String IMG_SRC="C:/word/img/";//图片的保存路�?
	public static final String IMG_WEB_SRC = "http://localhost:8080/images/";
	public static final String IMG_WEB_CACHE = "http://localhost:8080/cache/";
	
	public static boolean isEmpty(Object value) {
		return (value == null || "".equals(value));
	}

	public static boolean isNotEmpty(Object value) {
		return (!isEmpty(value));
	}

	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.toUpperCase().replace("-", "");
	}

	public static String getMD5(String source) {
		if (source == null || source == "") {
			return null;
		}
		String str = null;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(source.getBytes());
			byte tmp[] = md.digest();
			char chstr[] = new char[16 * 2];
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				chstr[k++] = hexDigits[byte0 >>> 4 & 0xf];
				chstr[k++] = hexDigits[byte0 & 0xf];
			}
			str = new String(chstr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;
	}
	
	/**
	 * 获取名称后缀
	 * @param name
	 * @return
	 */
	public static String getExt(String name){
		if(name == null || "".equals(name) || !name.contains("."))
			return "";
		return name.substring(name.lastIndexOf(".")+1);
	}
	
	
	/**
	 * 
	 * @param s
	 * @return 获得图片
	 */
	public static List<String> getImg(String s) {
		String regex;
		String img;
		List<String> list = new ArrayList<String>();
		regex = "<img[^<>]*?\\ssrc=\"(.*?)\"";
		Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		Matcher ma = pa.matcher(s);
		while (ma.find()) {
			img = ma.group();
			int begin = img.indexOf("\"") + 1;
			int end = img.lastIndexOf("\"");
			list.add(img.substring(begin, end));
		}
		return list;
	}
}
