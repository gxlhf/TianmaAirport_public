package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RoleDao {
	static String sql = null;
    static db_connection db1 = null;
    static ResultSet ret = null;
	public String roleDescriptionSearch(String name){
		String description = null;
		sql = "SELECT * FROM `actor` WHERE actor.A_name=?";//SQL语句  
        db1= new db_connection(sql);//创建db_connection对象  
        try {  
        	db1.pst.setString(1,name);
        	ret = db1.pst.executeQuery();//执行语句，得到结果集  
            while(ret.next()){
            	description=ret.getString("A_describe");
//            	System.out.println(description);
            }
            ret.close();  
            db1.close();//关闭连接  
        } catch (SQLException e) {  
        	e.printStackTrace();  
        }
        return description;
	}
	public Map<String,Boolean> roleAuthorityMapSearch(String name){
		Map<String, Boolean> authorityMap = new HashMap<String, Boolean>();
		sql = "SELECT * FROM actor_privilege WHERE actor_privilege.A_name=?";//SQL语句  
        db1= new db_connection(sql);//创建db_connection对象  
        try {  
        	db1.pst.setString(1,name);
        	ret = db1.pst.executeQuery();//执行语句，得到结果集  
        	while(ret.next()){
//        		System.out.println(ret.getString("P_name"));
        		authorityMap.put(ret.getString("P_name"), true);
        	}
        	ret.close();  
        
        } catch (SQLException e) {  
        	e.printStackTrace();  
        }finally{
        	db1.close();//关闭连接  
        }	
		return authorityMap;		
	}
}
