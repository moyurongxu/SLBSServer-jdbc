package com.mrx.dao.interfaces;

import java.util.List;

//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

 public interface IBaseDao<T> {
	
	public T find(String sql);  
	  
	public List<T> findAll(String sql);  
  
	//public void save(String sql ,  MapSqlParameterSource paramSource);
    
    public void update(T entity);
    
    public void delete(String  sql);
    
}
