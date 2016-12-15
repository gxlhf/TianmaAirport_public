package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class loggingDao {
	static String sql = null;
    static db_connection db1 = null;
    static ResultSet ret = null; 
    public Boolean LogAdd(String ip,String userId,String action){
    	sql = "INSERT INTO logging(ip,action,userId) VALUES (?,?,?)";
    	db1= new db_connection(sql);//创建db_connection对象  
        try {  
        	 db1.conn.setAutoCommit(false);
             db1.pst.setString(1, ip);
             db1.pst.setString(2, action);
             db1.pst.setString(3, userId);

             db1.pst.executeUpdate();//执行语句
             db1.conn.commit();
        	
        } catch (SQLException e) {  
         	try{
    			db1.conn.rollback();
    		}catch(SQLException e1){
    			e1.printStackTrace();
    			return false;
    		}
    		e.printStackTrace();
    		return false;
       }finally
       {   
    	  try{
   			  db1.conn.setAutoCommit(true);
   			  db1.close();//关闭连接
   	    	}catch(SQLException e){
   			   e.printStackTrace();
   		    }
       }
          return true;   	
    }
}
