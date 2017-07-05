//package com.slbs.dao.implement;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//
//import com.slbs.dao.interfaces.IBaseDao;
//
//public class IBaseDaoImpl<T> implements IBaseDao<T> {
//	
//	@Autowired
//	protected JdbcTemplate jdbcTemplate; 
//	
//	 @Autowired  
//	 protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//	
//	public void traceSql(String sql) {
//		System.out.println("JDBC:" + sql);
//	}
//
//	@Override
//	public void save(String sql ,  MapSqlParameterSource paramSource) {
//		int resutl = namedParameterJdbcTemplate.update(sql, paramSource);  
//        if(resutl>0){
//        	traceSql(sql);
//        }
//	}
//
//	@Override
//	public T find(String sql) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<T> findAll(String sql) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void update(T entity) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void delete(String sql) {
//		// TODO Auto-generated method stub
//
//	}
//}
