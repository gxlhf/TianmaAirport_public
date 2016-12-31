package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class loggingDao {
	static String sql = null;
    static db_connection db1 = null;
    static ResultSet ret = null; 
    public Boolean LogAdd(String ip,String userId,String action,String time){
    	sql = "INSERT INTO logging(ip,action,userId,time) VALUES (?,?,?,?)";
    	db1= new db_connection(sql);//创建db_connection对象  
        try {  
        	// db1.conn.setAutoCommit(false);
             db1.pst.setString(1, ip);
             db1.pst.setString(2, action);
             db1.pst.setString(3, userId);
             db1.pst.setString(4, time);
             db1.pst.executeUpdate();//执行语句
           //  db1.conn.commit();
        	
        } catch (SQLException e) {  
//         	try{
//    			db1.conn.rollback();
//    		}catch(SQLException e1){
//    			e1.printStackTrace();
//    			return false;
//    		}
    		e.printStackTrace();
    		return false;
       }finally
       {   
    	   db1.close();//关闭连接
//    	  try{
//   			//  db1.conn.setAutoCommit(true);
//   			  
//   	    	}catch(SQLException e){
//   			   e.printStackTrace();
//   		    }
       }
          return true;   	
    }
}
