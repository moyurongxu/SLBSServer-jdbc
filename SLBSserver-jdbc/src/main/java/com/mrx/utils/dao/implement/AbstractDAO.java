package com.mrx.utils.dao.implement;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONException;
import com.mrx.common.StringUtil;
import com.mrx.utils.dao.JdbcUtil;
import com.mrx.utils.dao.ResultSetToBean;
import com.mrx.utils.dao.interfaces.DAO;
import com.mrx.utils.entity.annotation.Column;
import com.mrx.utils.entity.annotation.Table;


public class AbstractDAO<T> extends JdbcUtil implements DAO<T>  {
	
	public static final String WHERE = " WHERE ";
	
	@SuppressWarnings("unchecked")
	public Class<T> getClazz(){
		return (Class < T > ) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[ 0 ];
	}
	
	public T getT(){
		 T t = null;
			try {
				t = getClazz().newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		return  t;
	}
	
	//获取所有的字段
	public List<Field> getField(){
		List<Field> list = new ArrayList<Field>();
		for(Field field : getClazz().getDeclaredFields()){
			if(field.getName() != "serialVersionUID"){
				list.add(field);
			}
		}
		return list;
	}
	
	//获取表名
	public String getEntityName(){
		Class < T >  entityClazz  = getClazz();
		/*
         * 根据类注解添加拼接表名
         * 1.如果Table注解中无参数，那么表名是类名的全小写
         * 2.如果Table注解中含有参数，那么表名是参数值
         */
		 if(entityClazz.isAnnotationPresent(Table.class)){
			 return  entityClazz.getAnnotation(Table.class).table();
        }
		return  entityClazz.getSimpleName().toLowerCase();
	}
	
	//获取字段名
	public Object getColumn(Field field){
		if(field.isAnnotationPresent(Column.class)){
			 Column column = field.getAnnotation(Column.class);
             if(!column.isAutoIncrement()){
            	 return column.name();
             }
          }
		return field.getName();
	}
	
	public Object getValue( Field field, T entity){
		Object value = StringUtil.FS_STR;
		field.setAccessible(true);
		try {
			value = field.get(entity);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public String getWhere(T entity){
		StringBuffer where = new StringBuffer(WHERE);
		for (Field field : getField()) {
			field.setAccessible(true);
			Column column = field.getAnnotation(Column.class);
			if(field.isAnnotationPresent(Column.class) && column.isPrimary()){
				if(field.getType()==String.class 
						&& !StringUtil.isEmpty(getValue(field, entity))){
					where.append(column.name()+"=");
					where.append("'"+getValue(field, entity)+"', ");
				}else if (Integer.parseInt(getValue(field, entity).toString())>0) {
					where.append(column.name()+"=");
					where.append(getValue(field, entity)+", ");
				}
			}
		}
		return where.delete(where.toString().lastIndexOf(","),where.length()).toString();
	}
	
	public void setField(Field field , T t , ResultSet resultSet){
		if(field.isAnnotationPresent(Column.class)){
			Column column = field.getAnnotation(Column.class);
			field.setAccessible(true);
			try {
				field.set(t, resultSet.getObject(column.name()));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<T> rsToObject(ResultSet resultSet){
		List<T> list= new ArrayList<T>();
		T t ;
		try {
			while(rs.next()){
			   t = getT();
			   for(Field field : getField()){
				   setField(field, t, resultSet);
				}
			   list.add(t);
		   }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
	    return list;
    }
	
	@Override
	public void save(T entity){
		StringBuffer sql = new StringBuffer("INSERT INTO ");
		sql.append(getEntityName()+"( ");
		//用于拼接values中的'?'个数
	     StringBuffer value = new StringBuffer(" values( ");
	     // 获取Method对象
		 for(Field field : getField()){
            /*
             * 拼接字段名，以','隔开
             * 拼接结果 insert into employee( emp_code , emp_name , 
             */
        	sql.append(getColumn(field)+", ");
            /*
             * 拼接values中的'?'个数，以','隔开
             * 拼接结果 values(  ? , ? ,  
             */
        	if(field.getType()==String.class){
        		value.append("'"+getValue(field, entity) +"', ");
        	}else{
        		value.append(getValue(field, entity) +", ");
        	}
	      }
	 	//去掉最后一个“,”
	 	sql.delete(sql.toString().lastIndexOf(","),sql.length());
	 	value.delete(value.toString().lastIndexOf(","),value.length());
	 	//拼接sql语句
	 	sql.append(" )"+value+" ); ");
	 	executeUpdate(sql.toString());
	}
	
	public void merge(String sql, String... params){
		executeUpdate(sql, params);
    }
	
	@Override
	public void saveAll(List<T> list) {
		//TODO
	}
	
	@Override
	public void merge(T entity){
		StringBuffer sql = new StringBuffer("UPDATE ");
		//获取表名
		sql.append(" "+getEntityName()+" SET ");
		//循环获取字段、到值
		for (Field field : getField()) {
			//存在注解
			field.setAccessible(true);
			if(field.isAnnotationPresent(Column.class)){
			   	Column column = field.getAnnotation(Column.class);
                if(!column.isPrimary()){//如果不是主键
                	sql.append(column.name()+"=");
                	if(field.getType()==String.class){
                		sql.append("'"+getValue(field, entity)+"', ");
                	}else{
                		sql.append(getValue(field, entity)+", ");
                	}
                }
			}else{
				if(field.getType()==String.class){
            		sql.append("'"+getValue(field, entity)+"', ");
            	}else{
            		sql.append(getValue(field, entity)+", ");
            	}
			}
		}
		//去掉最后一个“,”
	 	sql.delete(sql.toString().lastIndexOf(","),sql.length());
	 	sql.append(getWhere(entity));
	 	System.out.println(sql.toString());
	 	executeUpdate(sql.toString());
	}

	@Override
	public void remove(T entity){
		StringBuffer sql = new StringBuffer("UPDATE ");
		//获取表名
		sql.append(" "+getEntityName());
		sql.append(" "+getWhere(entity));
		executeUpdate(sql.toString());
	}
	
	public T getEntity(String sql ,String... params){
        rs = executeQuery(sql,params);
        List<T> list = rsToObject(rs);
        if(null != list && list.size()>0){
        	return list.get(0);
        }
        return getT();
	}
	
	@Override
	public List<T> getList(String sql, String... params) {
		rs = executeQuery(sql,params);
        return rsToObject(rs);
	}
	
	@Override
	public T find(String sql){
		 rs = executeQuery(sql);
		 T t = null;
		try {
			t = ResultSetToBean.getEntity(rs, getClazz());
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return t;
	}

	@Override
	public List<T> findAll(String sql){
		rs = executeQuery(sql);
		List<T> list = null;
		try {
			list = ResultSetToBean.getEntityList(rs, getClazz());
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return list;
	}
}
