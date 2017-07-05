package com.mrx.utils.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.mrx.common.exception.BusinessException;
import com.mrx.utils.entity.page.Page;

public class JdbcUtil {
	
	 private static final Logger log = Logger .getLogger(JdbcUtil.class);  
	
	protected DataSource dataSource;
	protected Connection conn;
	protected PreparedStatement pst;
	protected ResultSet  rs; 
	
	
	public DataSource getDataSource() {
		return dataSource;
	}

	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Connection getConnection() {
        	try {
        		if (null == conn||conn.isClosed()) {
        			conn = dataSource.getConnection();
        		} 
        	} catch (SQLException e) {
        		log.error("Connection 获取失败！！！" + e);
        		new BusinessException("Connection 获取失败！！！" + e);
        	}
        return conn;
    }
	
	public PreparedStatement getPrepareStatement(String sql , String... params ){
		try {
			pst = getConnection().prepareStatement(sql);
			if(null != params && params.length>0){
				for (int i = 0; i < params.length; i++) {
					pst.setObject((i + 1), params[i]);
				}
			}
		} catch (SQLException e) {
			log.error("prepareStatement 创建失败！！！"+e);
			
			e.printStackTrace();
		}
		return pst;
	}
	
	//关闭Connection
	public void connClose(){
		if (conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				log.error("Connection 关闭失败！！！" + e);
				e.printStackTrace();
			}
		}
	}
	
	//关闭PreparedStatement
	public void pstClose(){
		if (pst != null){
			try {
				pst.close();
			} catch (SQLException e) {
				log.error("PreparedStatement 关闭失败！！！" + e);
				e.printStackTrace();
			}
		}
	}
	
	//关闭ResultSet
	public void rsClose(){
		if (rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				log.error("ResultSet 关闭失败！！！" + e);
				e.printStackTrace();
			}
		}
	}
	
	//关闭资源
	public void close(){
		rsClose();	
		pstClose();
		connClose();
    }
	
	public int executeUpdate(String sql , String... params){
		int rvalue = 0;
		pst =  getPrepareStatement(sql , params);
		try {
			rvalue = pst.executeUpdate();
		} catch (SQLException e) {
			log.error("executeUpdate执行失败!!! "+e);
			e.printStackTrace();
		}finally{
			close();
		}
		return rvalue;
	}
	
	//带参数的查询
	public ResultSet executeQuery(String sql, String... params) {
		pst =  getPrepareStatement(sql , params);
		try {
        	rs = pst.executeQuery();
		} catch (SQLException e) {
			log.error("executeQuery执行失败！！！ "+e);
			e.printStackTrace();
		}
		return rs;
	}
	
	public int findCount(String sql){
		pst =  getPrepareStatement(sql);
		int count =0;
		try {
        	rs = pst.executeQuery(sql);
        	while (rs.next()) {
    			count = rs.getInt(1);
    			
    		}
		} catch (SQLException e) {
			log.error("findCount执行失败！！！ "+e);
			e.printStackTrace();
		}finally{
			close();
		}
		return count;
	}
	
	public ResultSet executeQueryPage(String sql ,Page page ){
		page.setRowTotal(findCount(SqlHandler.getCountSql(sql)));
		sql = sql+"LIMIT" + page.getCount() +" , " +page.getPageSize();
		pst =  getPrepareStatement(sql);
		try {
			rs = pst.executeQuery(sql);
		} catch (SQLException e) {
			log.error("executeQuery执行失败！！！ "+e);
			e.printStackTrace();
		}finally{
			connClose();
			pstClose();
		}
		return rs;
	}
}
