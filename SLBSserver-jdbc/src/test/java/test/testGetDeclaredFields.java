package test;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mrx.entity.user.UserInfo;
import com.mrx.utils.entity.annotation.Column;
import com.mrx.utils.entity.annotation.Table;

public class testGetDeclaredFields {
	
	@SuppressWarnings("unchecked")
	public void testGetDeclaredFields(List<UserInfo> list){
		for (int i = 0; i < list.size(); i++) {
			UserInfo t =list.get(i);
			//用于拼接values中的'?'个数
			StringBuffer sql = new StringBuffer("INSERT INTO ");
			Class <UserInfo>  entityClazz  =  (Class<UserInfo>) t.getClass();
			/*
	         * 根据类注解添加拼接表名
	         * 1.如果Table注解中无参数，那么表名是类名的全小写
	         * 2.如果Table注解中含有参数，那么表名是参数值
	         */
			 if(!entityClazz.isAnnotationPresent(Table.class)){
				 sql.append(entityClazz.getSimpleName().toLowerCase()+"( ");
	        }else{
	        	sql.append(entityClazz.getAnnotation(Table.class).table()+"( ");
	        }
		     StringBuffer value = new StringBuffer(" values( ");
			for(Field field : entityClazz.getDeclaredFields()){
				 if(field.isAnnotationPresent(Column.class)){
					field.setAccessible(true);
				   	Column column = field.getAnnotation(Column.class);
	                if(!column.isAutoIncrement()){
	                    /*
	                     * 拼接字段名，以','隔开
	                     * 拼接结果 insert into employee( emp_code , emp_name , 
	                     */
	                	sql.append(column.name()+", ");

	                    /*
	                     * 拼接values中的'?'个数，以','隔开
	                     * 拼接结果 values(  ? , ? ,  
	                     */
	                    try {
	                    	value.append(field.get(t) +", ");
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
	                }
	            }
			}
			//去掉最后一个“,”
		 	sql.delete(sql.toString().lastIndexOf(","),sql.length());
		 	value.delete(value.toString().lastIndexOf(","),value.length());
		 	//拼接sql语句
		 	sql.append(" )"+value+" ); ");
		 	System.out.println(sql);
		}
	}
	@SuppressWarnings("unchecked")
	public void testGetDeclaredFields1(List<UserInfo> list){
		for (int i = 0; i < list.size(); i++) {
			UserInfo t =list.get(i);
			//用于拼接values中的'?'个数
			StringBuffer sql = new StringBuffer("INSERT INTO ");
			Class <UserInfo>  entityClazz  =  (Class<UserInfo>) t.getClass();
			/*
			 * 根据类注解添加拼接表名
			 * 1.如果Table注解中无参数，那么表名是类名的全小写
			 * 2.如果Table注解中含有参数，那么表名是参数值
			 */
			if(!entityClazz.isAnnotationPresent(Table.class)){
				sql.append(entityClazz.getSimpleName().toLowerCase()+"( ");
			}else{
				sql.append(entityClazz.getAnnotation(Table.class).table()+"( ");
			}
			List<String> name = new ArrayList<String>();
			StringBuffer value = new StringBuffer(" values( ");
			for(Field field : entityClazz.getDeclaredFields()){
				if(field.isAnnotationPresent(Column.class)){
					field.setAccessible(true);
					Column column = field.getAnnotation(Column.class);
					if(!column.isAutoIncrement()){
						sql.append(column.name()+", ");
						name.add(field.getName());
					}
				}
			}
			
			for (UserInfo entity : list) {
				entityClazz  =  (Class<UserInfo>) entity.getClass();
			}
			
			
			//去掉最后一个“,”
			sql.delete(sql.toString().lastIndexOf(","),sql.length());
			value.delete(value.toString().lastIndexOf(","),value.length());
			//拼接sql语句
			sql.append(" )"+value+" ); ");
			System.out.println(sql);
		}
	}
	
	
	
	@Test  
    public void testSave() throws SQLException {
		List<UserInfo> list =new ArrayList<UserInfo>();
		for (int i = 0; i < 1; i++) {
			UserInfo userInfo = new UserInfo();  
			userInfo.setName("name"+i);
			userInfo.setPassword("password"+i);
			list.add(userInfo);
		}
        testGetDeclaredFields1(list);
	}
}
