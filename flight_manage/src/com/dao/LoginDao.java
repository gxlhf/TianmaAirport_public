package com.dao;

import java.sql.*;

import java.util.Map;

import com.entity.*;

public class LoginDao {
	  static String sql = null;
      static db_connection db1 = null;
      static ResultSet ret = null;
      static RoleDao rD=null;
      
      Map<String,Boolean> authorityMap;
      public Admin loginCheck(String userName,String Password){
    	 
    	  Admin admin= null;
    	  sql = "SELECT user_info.Em_No,user_info.Name,user_info.Sex,user_info.PhoneNo,user_info.Tel,user_info.Email," +
				"user_info.Dept,user_info.Position,user_actor.A_name,user_actor.passward FROM user_info INNER JOIN user_actor ON user_info.Em_No = user_actor.Em_No" +
				" WHERE user_info.Em_No = '"+userName+"' AND passward='"+Password+"'";//SQL语句  
    	  db1= new db_connection(sql);//创建db_connection对象  
    	  try {  
    		  ret = db1.pst.executeQuery();//执行语句，得到结果集  
    		  String Name = null;
    		  String Em_No = null;
    		  int Sex = 0 ;         	 
    		  String Email = null;
    		  String Tel = null;
    		  String Dept = null;
    		  String Position = null;
    		  String PhoneNo = null;
    		  String A_name = null;
    		  Role role=null;
    		  if(!ret.next())
    		  {
    			  return admin=null;
    		  }
              else{
                  ret.beforeFirst();
          	      while(ret.next()){
                       Name=ret.getString("Name");
                       Em_No=ret.getString("Em_No");
                       Sex=ret.getInt("Sex");         	 
                       Email=ret.getString("Email");
                       Tel= ret.getString("Tel");
                       Dept=ret.getString("Dept");
                       Position=ret.getString("Position");
                       PhoneNo=ret.getString("PhoneNo");
                       A_name=ret.getString("A_name");
                       role=new Role(A_name);   	
                  }
            }
            admin=new Admin(Em_No,Name,Sex,Email,role,PhoneNo,Tel,Dept,Position);         
                ret.close();  
                db1.close();//关闭连接  
        } catch (SQLException e) {  
        	e.printStackTrace();  
        }
		return admin;   	 
     }
     
}
