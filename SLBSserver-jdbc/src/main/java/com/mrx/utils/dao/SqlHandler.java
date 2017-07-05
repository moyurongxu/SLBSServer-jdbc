package com.mrx.utils.dao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlHandler {

	public final static Pattern SQL_REMOVE_GROUP = Pattern.compile("\\s+group\\s+by[a-z0-9,\\s*]*",Pattern.CASE_INSENSITIVE);
	public final static Pattern SQL_REMOVE_ORDER = Pattern.compile("\\s+order\\s+by[a-z0-9,\\s*]*",Pattern.CASE_INSENSITIVE);
	public final static Pattern SQL_REPLACE_FROM = Pattern.compile("from\\s*",Pattern.CASE_INSENSITIVE);
	public final static Pattern SQL_REMOVE_LIMIT = Pattern.compile("\\s+LIMIT[a-z0-9,\\s*]*",Pattern.CASE_INSENSITIVE);
	
	
	 public static String getCountSql(String sql) {
		Matcher matcher = SQL_REMOVE_GROUP.matcher(sql); //先去掉group by
		if(matcher.find()){
			System.out.println(matcher.group());
			sql = sql.replace(matcher.group(), "");
			System.out.println(sql);
		}
		
		matcher = SQL_REMOVE_ORDER.matcher(sql); // 再去掉order by
		if(matcher.find()){
			System.out.println(matcher.group());
			sql = sql.replace(matcher.group(), "");
			System.out.println(sql);
		}

		matcher = SQL_REMOVE_LIMIT.matcher(sql); // 再去掉LIMIT
		if(matcher.find()){
			System.out.println(matcher.group());
			sql = sql.replace(matcher.group(), "");
			System.out.println(sql);
		}
		
		matcher = SQL_REPLACE_FROM.matcher(sql); // 再把一个from 之前的字符串转成 select count（*）
        if(matcher.find()){
        	sql =  "SELECT COUNT(*) "+sql.substring(matcher.start(), sql.length());
        }
		return sql;
	 }
	 
	 public static void main(String[] args) {
			String sql = "SELECT * FROM user WHERE a=1 GROUP BY a LIMIT 0,10";
			String sql1 = "select * FROM (select * from user where a=1 GROUP by a, bcd)C";
			String sql2 = "select * FROM (select * from user where a=1 ORDER BY a, bcd)C";
			
//			Matcher matcher = SQL_REMOVE_GROUP.matcher(sql1); // 操作的字符串 
//			Matcher matcher = SQL_REMOVE_ORDER.matcher(sql2); // 操作的字符串 
//			if(matcher.find()){
//				System.out.println(matcher.group());
//				System.out.println(sql2.replace(matcher.group(), ""));
//			}
			
			System.out.println(getCountSql(sql));
//			System.out.println(getCountSql(sql1));
//			System.out.println(getCountSql(sql2));
			System.exit(0);
		}
}
