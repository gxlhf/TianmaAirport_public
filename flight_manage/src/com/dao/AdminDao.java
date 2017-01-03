package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.entity.*;

public class AdminDao {
	static String sql = null;
    static db_connection db1 = null;
    static ResultSet ret = null; 
    public Role searchRole(String name)
    {//数据库操作：查询角色名称为该name的角色信息
    	Role role=null;
    	sql = "SELECT * FROM actor WHERE actor.A_name=?";//SQL语句  
        db1= new db_connection(sql);//创建db_connection对象  
         try {  
        	  db1.pst.setString(1, name);
              ret = db1.pst.executeQuery();//执行语句，得到结果集  
              if(!ret.next())
              {
            	  ret.close();  
                  db1.close();//关闭连接
                  return role;
              }
              ret.close();  
              db1.close();//关闭连接  
       } catch (SQLException e) {  
            e.printStackTrace();
            return role;
       }
    	role = new Role(name);
		return role;   	
    }
    public Role[] returnAllRole()
    {//返回所有角色信息，返回Role对象数组
    	Role[] allRole=null;
    	String name=null;
    	sql = "SELECT * FROM actor";//SQL语句  
        db1= new db_connection(sql);//创建db_connection对象  
         try {  
              ret = db1.pst.executeQuery();//执行语句，得到结果集 
              int i=0;
             
              ret.last();
              int roleNumber=ret.getRow();
              allRole=new Role[roleNumber];
              ret.beforeFirst();
              while(ret.next()){
                 name=ret.getString("A_name");	  
                 allRole[i] = new Role(name);
                 i++;
             }         
              ret.close();         
       } catch (SQLException e) {  
            e.printStackTrace();
            return allRole;
       }finally{
    	      db1.close();//关闭连接
       }
		return allRole;
    }
    public boolean addRole(Role role)
    {//数据库操作：新增角色，形参为角色对象，返回值为布尔值    
    	     sql = "INSERT INTO actor (A_name,A_describe) "+
    		"VALUES (?,?)";//SQL语句  
    	     db1= new db_connection(sql);//创建db_connection对象  
        try {        	
            
            db1.conn.setAutoCommit(false);
            db1.pst.setString(1, role.getName());
            db1.pst.setString(2, role.getDescription());
            db1.pst.executeUpdate();//执行语句
            //Savepoint savePoint=db1.conn.setSavepoint();
        	sql = "INSERT INTO actor_privilege (A_name,P_name ) " +
		    "VALUES (?,?)";//SQL语句  
            //db1= new db_connection(sql);//创建db_connection对象      
        	db1.pst=db1.conn.prepareStatement(sql);
        	for (Map.Entry<String,Boolean> entry : role.getAuthorityMap().entrySet()) {
        	    db1.pst.setString(1, role.getName());
        	    db1.pst.setString(2, entry.getKey());
        	    db1.pst.addBatch();
        	}
        	db1.pst.executeBatch();
        	//db1.conn.rollback(savePoint);
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
           }
           finally{
        	 try {
                 db1.conn.setAutoCommit(true);
                 db1.close();//关闭连接   
             } catch (SQLException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }         
        }
         
        return true;   	   	
    }
    public boolean modifyRole(Role role)
    {//数据库操作：修改角色，形参为角色对象，返回值为布尔值
	     sql = "UPDATE actor SET A_describe= ? WHERE A_name= ?";//SQL语句  
 	     db1= new db_connection(sql);//创建db_connection对象  
     try {        	
         
         db1.conn.setAutoCommit(false);
         db1.pst.setString(1, role.getDescription());
  	     db1.pst.setString(2, role.getName());
         db1.pst.executeUpdate();//执行语句
         //Savepoint savePoint=db1.conn.setSavepoint();
        sql = "DELETE FROM actor_privilege WHERE A_name=?";//SQL语句  
        db1.pst=db1.conn.prepareStatement(sql);
        db1.pst.setString(1, role.getName());
        db1.pst.executeUpdate();
        sql = "INSERT INTO actor_privilege (A_name,P_name ) " +
	    "VALUES (?,?)";//SQL语句
         //db1= new db_connection(sql);//创建db_connection对象      
     	db1.pst=db1.conn.prepareStatement(sql);
     	for (Map.Entry<String,Boolean> entry : role.getAuthorityMap().entrySet()) {
     	    db1.pst.setString(1, role.getName());
     	    db1.pst.setString(2, entry.getKey());
     	    db1.pst.addBatch();
     	}
     	db1.pst.executeBatch();
     	//db1.conn.rollback(savePoint);
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
        }
        finally{
     	 try {
              db1.conn.setAutoCommit(true);
              db1.close();//关闭连接   
          } catch (SQLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }         
     }
      
     return true;   	   	   	
    }
    public boolean deleteRole(Role role)
    {// 数据库操作：删除角色，形参为角色对象，返回值为布尔值
    	sql = "SELECT * FROM `user_actor` WHERE A_name = ?";
    	db1= new db_connection(sql);//创建db_connection对象
    	try {
			db1.pst = db1.conn.prepareStatement(sql);
			db1.pst.setString(1, role.getName());
			ret = db1.pst.executeQuery();
			if(ret.next())
				return false;
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    	
    	sql = "DELETE FROM actor_privilege WHERE A_name= ?";//SQL语句  
    	db1= new db_connection(sql);//创建db_connection对象
        try {     
    	    db1.conn.setAutoCommit(false);
    	    db1.pst.setString(1, role.getName());
            db1.pst.executeUpdate();//执行语句          
        	sql = "DELETE FROM actor WHERE A_name=?";//SQL语句          	
    	    db1.pst=db1.conn.prepareStatement(sql);
    	    db1.pst.setString(1, role.getName());
            db1.pst.executeUpdate();
        	db1.conn.commit();            
        } catch (SQLException e) {  
        	try{
         		db1.conn.rollback();
         	}catch(SQLException e1){
         		//e1.printStackTrace();
         		return false;
         	}
             //e.printStackTrace(); 
             return false;
       }
        finally{
        	 try {
                 db1.conn.setAutoCommit(true);
                 db1.close();//关闭连接   
             } catch (SQLException e) {
                 // TODO Auto-generated catch block
                 //e.printStackTrace();
             }         
        }
        return true;
    	
    }
    public Admin[] searchAdmin0(String empno)
    {//数据库操作：查询员工号为该empno的管理员信息
    	Admin[] admin= null;
		sql = "SELECT user_info.Em_No,user_info.Name,user_info.Sex,user_info.PhoneNo,user_info.Tel,user_info.Email," +
			"user_info.Dept,user_info.Position,user_actor.A_name,user_actor.password FROM user_info INNER JOIN user_actor ON user_info.Em_No = user_actor.Em_No" +
			" WHERE user_info.Em_No = ?";//SQL语句  
        db1= new db_connection(sql);//创建db_connection对象  
        try {  
        	db1.pst.setString(1, empno);
            ret = db1.pst.executeQuery();//执行语句，得到结果集  
            ret.last();
	        int rowNumber=ret.getRow();
	        ret.beforeFirst();
	        admin=new Admin[rowNumber];
            String Name = null;
          	String Em_No = null;
         	int Sex = 0 ;         	 
         	String Email = null;
         	String Tel = null;
          	String Dept = null;
          	String Position = null;
          	String PhoneNo = null;
          	String A_name = null;
          	String password = null;
          	Role role=null;
            int i=0;
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
                       password=ret.getString("password");
                       role=new Role(A_name); 
                       admin[i]=new Admin(Em_No,Name,Sex,Email,role,PhoneNo,Tel,Dept,Position,password);   	
                       i++;
          	}                          
                ret.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }finally
        {    	       
            db1.close();//关闭连接     
        }
		return admin;   	 
    }   	
    
    public Admin[] searchAdmin1(String name,int sex,String position,String roleName)
    {//数据库操作：查询姓名为name，性别为sex，职位为position，角色为roleName的管理员信息，若其中有形参值为空字符串，则表示无该条件限制
    	Admin[] admin= null;
		sql = "SELECT user_info.Em_No,user_info.Name,user_info.Sex,user_info.PhoneNo,user_info.Tel,user_info.Email," +
			"user_info.Dept,user_info.Position,user_actor.A_name,user_actor.password FROM user_info LEFT OUTER JOIN user_actor ON user_info.Em_No = user_actor.Em_No" +
			" WHERE 1=1";//SQL语句  
        db1= new db_connection(sql);//创建db_connection对象  
        try {  
        	if((name!=null&&!name.equals(""))&&(sex!=1&&sex!=0)&&(position==null||position.equals(""))&&(roleName==null||roleName.equals("")))
        	{	
        		sql=sql+" AND user_info.Name=?";
        		db1.pst = db1.conn.prepareStatement(sql);
        		db1.pst.setString(1, name);
        	}else if((name==null||name.equals(""))&&(sex==1||sex==0)&&(position==null||position.equals(""))&&(roleName==null||roleName.equals("")))
            	{	
            		sql=sql+" AND user_info.Sex=?";
            		db1.pst = db1.conn.prepareStatement(sql);
            		db1.pst.setInt(1, sex);
            	}else if((name==null||name.equals(""))&&(sex!=1&&sex!=0)&&(position!=null&&!position.equals(""))&&(roleName==null||roleName.equals("")))
            	{	
            		sql=sql+" AND user_info.Position=?";
            		db1.pst = db1.conn.prepareStatement(sql);
            		db1.pst.setString(1, position);
            	}else if((name==null||name.equals(""))&&(sex!=1&&sex!=0)&&(position==null||position.equals(""))&&(roleName!=null&&!roleName.equals("")))
            	{	
            		sql=sql+" AND user_actor.A_name=?";
            		db1.pst = db1.conn.prepareStatement(sql);
            		db1.pst.setString(1, roleName);
            	}else if((name!=null&&!name.equals(""))&&(sex==1||sex==0)&&(position==null||position.equals(""))&&(roleName==null||roleName.equals("")))
            	{	
            		sql=sql+" AND user_info.Name=? AND user_info.Sex=?";
            		db1.pst = db1.conn.prepareStatement(sql);
            		db1.pst.setString(1, name);
            		db1.pst.setInt(2, sex);
            	}else if((name!=null&&!name.equals(""))&&(sex!=1&&sex!=0)&&(position!=null&&!position.equals(""))&&(roleName==null||roleName.equals("")))
            	{	
            		sql=sql+" AND user_info.Name=? AND user_info.Position=?";
            		db1.pst = db1.conn.prepareStatement(sql);
            		db1.pst.setString(1, name);
            		db1.pst.setString(2, position);
            	}else if((name!=null&&!name.equals(""))&&(sex!=1&&sex!=0)&&(position==null||position.equals(""))&&(roleName!=null&&!roleName.equals("")))
            	{	
            		sql=sql+" AND user_info.Name=? AND user_actor.A_name";
            		db1.pst = db1.conn.prepareStatement(sql);
            		db1.pst.setString(1, name);
            		db1.pst.setString(2, roleName);
            	}else if((name==null||name.equals(""))&&(sex==1||sex==0)&&(position!=null&&!position.equals(""))&&(roleName==null||roleName.equals("")))
            	{	
            		sql=sql+" AND user_info.Sex=? AND user_info.Position=?";
            		db1.pst = db1.conn.prepareStatement(sql);
            		db1.pst.setInt(1, sex);
            		db1.pst.setString(2, position);
            	}else if((name==null||name.equals(""))&&(sex==1||sex==0)&&(position==null||position.equals(""))&&(roleName!=null&&!roleName.equals("")))
            	{	
            		sql=sql+" AND user_info.Sex=? AND user_actor.A_name=?";
            		db1.pst = db1.conn.prepareStatement(sql);
            		db1.pst.setInt(1, sex);
            		db1.pst.setString(2, roleName);
            	}else if((name==null||name.equals(""))&&(sex!=1&&sex!=0)&&(position!=null&&!position.equals(""))&&(roleName!=null&&!roleName.equals("")))
            	{	
            		sql=sql+" AND user_info.Position=? AND user_actor.A_name=?";
            		db1.pst = db1.conn.prepareStatement(sql);
            		db1.pst.setString(1, position);
            		db1.pst.setString(2, roleName);
            	}else if((name!=null&&!name.equals(""))&&(sex==1||sex==0)&&(position!=null&&!position.equals(""))&&(roleName==null||roleName.equals("")))
            	{	
            		sql=sql+" AND user_info.Name=? AND user_info.Sex=? AND user_info.Position=?";
            		db1.pst = db1.conn.prepareStatement(sql);
            		db1.pst.setString(1, name);
            		db1.pst.setInt(2, sex);
            		db1.pst.setString(3, position);
            	}else if((name!=null&&!name.equals(""))&&(sex==1||sex==0)&&(position==null||position.equals(""))&&(roleName!=null&&!roleName.equals("")))
            	{	
            		sql=sql+" AND user_info.Name=? AND user_info.Sex=? AND user_actor.A_name=?";
            		db1.pst = db1.conn.prepareStatement(sql);
            		db1.pst.setString(1, name);
            		db1.pst.setInt(2, sex);
            		db1.pst.setString(3, roleName);
            	}else if((name!=null&&!name.equals(""))&&(sex!=1&&sex!=0)&&(position!=null&&!position.equals(""))&&(roleName!=null&&!roleName.equals("")))
            	{	
            		sql=sql+" AND user_info.Name=? AND user_info.Position=? AND user_actor.A_name=?";
            		db1.pst = db1.conn.prepareStatement(sql);
            		db1.pst.setString(1, name);
            		db1.pst.setString(2, position);
            		db1.pst.setString(3, roleName);
            	}else if((name==null||name.equals(""))&&(sex==1||sex==0)&&(position!=null&&!position.equals(""))&&(roleName!=null&&!roleName.equals("")))
            	{	
            		sql=sql+" AND user_info.Sex=? AND user_info.Position=? AND user_actor.A_name=?";
            		db1.pst = db1.conn.prepareStatement(sql);
            		db1.pst.setInt(1, sex);
            		db1.pst.setString(2, position);
            		db1.pst.setString(3, roleName);
            	}else if((name!=null&&!name.equals(""))&&(sex==1||sex==0)&&(position!=null&&!position.equals(""))&&(roleName!=null&&!roleName.equals("")))
            	{	
            		sql=sql+" AND user_info.Name=? AND user_info.Sex=? AND user_info.Position=? AND user_actor.A_name=?";
            		db1.pst = db1.conn.prepareStatement(sql);
            		db1.pst.setString(1, name);
            		db1.pst.setInt(2, sex);
            		db1.pst.setString(3, position);
            		db1.pst.setString(4, roleName);
            	}
        	    
            ret = db1.pst.executeQuery();//执行语句，得到结果集  
            ret.last();
	        int rowNumber=ret.getRow();
	        ret.beforeFirst();
	        admin=new Admin[rowNumber];
            String Name = null;
          	String Em_No = null;
         	int Sex = 0 ;         	 
         	String Email = null;
         	String Tel = null;
          	String Dept = null;
          	String Position = null;
          	String PhoneNo = null;
          	String A_name = null;
          	String password= null;
          	Role role=null;
            int i=0;
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
                       password=ret.getString("password");
                       role=new Role(A_name); 
                       admin[i]=new Admin(Em_No,Name,Sex,Email,role,PhoneNo,Tel,Dept,Position,password);   	
                       i++;
          	}                          
                ret.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }finally
        {    	       
            db1.close();//关闭连接     
        }
		return admin; 
    	
    }
    public  boolean addAdmin(Admin admin)
    {//数据库操作：新增管理员，形参为管理员对象，返回值为布尔值
    	sql = "INSERT INTO user_info(Em_No,`Name`,Sex,PhoneNo,Tel,Email,Dept,Position) VALUES(?,?,?,?,?,?,?,?)";//SQL语句  
        db1= new db_connection(sql);//创建db_connection对象  
        try {  
          db1.conn.setAutoCommit(false);
          db1.pst.setString(1, admin.getEmpno());
          db1.pst.setString(2, admin.getName());
          db1.pst.setInt(3, admin.getSex());
          db1.pst.setString(4, admin.getMobile());
          db1.pst.setString(5, admin.getPhone());
          db1.pst.setString(6, admin.getEmail());
          db1.pst.setString(7, admin.getDepartment());
          db1.pst.setString(8, admin.getPosition());
          db1.pst.executeUpdate();//执行语句
          sql="INSERT INTO user_actor(Em_No,A_name,password) VALUES(?,?,?)";
          db1.pst = db1.conn.prepareStatement(sql);
          db1.pst.setString(1, admin.getEmpno());
          db1.pst.setString(2, admin.getRole().getName());
          db1.pst.setString(3, admin.getPassword());
          db1.pst.executeUpdate();
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
    public boolean modifyAdmin(Admin admin)
    {//数据库操作：修改管理员，形参为管理员对象，返回值为布尔值
    	sql = "UPDATE user_info LEFT OUTER JOIN user_actor on user_info.Em_No=user_actor.Em_No SET user_info.`Name`=?," +
    			"user_info.Sex=?,user_info.PhoneNo=?,user_info.Tel=?,user_info.Email=?,user_info.Dept=?,user_info.Position=?," +
    			"user_actor.A_name=?,user_actor.password=? WHERE user_info.Em_No=?";//SQL语句  
        db1= new db_connection(sql);//创建db_connection对象  
        try {  
          db1.pst.setString(1, admin.getName());
          db1.pst.setInt(2, admin.getSex());
          db1.pst.setString(3, admin.getMobile());
          db1.pst.setString(4, admin.getPhone());
          db1.pst.setString(5, admin.getEmail());
          db1.pst.setString(6, admin.getDepartment());
          db1.pst.setString(7, admin.getPosition());
          db1.pst.setString(8, admin.getRole().getName());
          db1.pst.setString(9, admin.getPassword());
          db1.pst.setString(10, admin.getEmpno());
          db1.pst.executeUpdate();//执行语句
        } catch (SQLException e) {  
           e.printStackTrace();  
           return false;
       }finally
       {   
   			  db1.close();//关闭连接
       }
          return true;    	
    }
    public boolean deleteAdmin(Admin admin)
    {//数据库操作：删除管理员，形参为管理员对象，返回值为布尔值
    	sql = "DELETE user_actor,user_info FROM  user_actor LEFT OUTER JOIN user_info on user_actor.Em_No=user_info.Em_No" +
    		  " WHERE  user_actor.Em_No=?";//SQL语句  
        db1= new db_connection(sql);//创建db_connection对象  
        try {          	
          db1.pst.setString(1, admin.getEmpno());
          db1.pst.executeUpdate();
        } catch (SQLException e) {  
           e.printStackTrace();  
           return false;
       }finally
       {
   			db1.close();//关闭连接
       }
          return true;   	    	 	
    }
    public boolean addAirportResource(AirportResource airportResource)
    {//数据库操作：新增机场资源，形参为机场资源对象，返回值为布尔值
    	if(airportResource.getType().equals("登机门"))
    	{
    	     sql = "INSERT INTO boardinggate (Bname,Blocation,Bremarks) " +
		     "VALUES (?,?,?);";//SQL语句  
    	}
    	else if(airportResource.getType().equals("值机柜台"))
    	{
    		sql = "INSERT INTO checkincounter (Cname,Clocation,Cremarks) " +
    		"VALUES (?,?,?);";//SQL语句 
    	}
    	else if(airportResource.getType().equals("行李转盘"))
    	{
    		sql = "INSERT INTO luggagecarousel (Lname,Llocation,Lremarks) " +
    		"VALUES (?,?,?);";//SQL语句 
    	}
    	db1= new db_connection(sql);//创建db_connection对象  
        try { 
        	 db1.pst.setString(1, airportResource.getName());
        	 db1.pst.setString(2, airportResource.getLocation());
        	 db1.pst.setString(3, airportResource.getRemark());
             db1.pst.executeUpdate();//执行语句，得到结果集  
        } catch (SQLException e) {  
           e.printStackTrace();  
           return false;
        }finally
        {    	       
            db1.close();//关闭连接     
        }
        return true;  	
    }
    public boolean modifyAirportResource(AirportResource airportResource)
    {//数据库操作：修改机场资源，形参为机场资源对象，返回值为布尔值
    	if(airportResource.getType().equals("登机门"))
    	{
    	     sql = "UPDATE boardinggate set Blocation=?,Bremarks=? WHERE boardinggate.Bname=?";//SQL语句  
    	}
    	else if(airportResource.getType().equals("值机柜台"))
    	{
    		sql = "UPDATE checkincounter set Clocation=?,Cremarks=? WHERE checkincounter.Cname=?";//SQL语句
    	}
    	else if(airportResource.getType().equals("行李转盘"))
    	{
    		sql = "UPDATE luggagecarousel set Llocation=?,Lremarks=? WHERE luggagecarousel.Lname=?";//SQL语句
    	}
    	db1= new db_connection(sql);//创建db_connection对象  
        try {  
        	
        	 db1.pst.setString(1, airportResource.getLocation());
        	 db1.pst.setString(2, airportResource.getRemark());
        	 db1.pst.setString(3, airportResource.getName());
             db1.pst.executeUpdate();//执行语句，得到结果集  

        } catch (SQLException e) {  
           e.printStackTrace();  
           return false;
        }finally
        {    	       
            db1.close();//关闭连接     
        }
        return true;
    }
    public boolean deleteAirportResource(AirportResource airportResource)
    {//数据库操作：删除机场资源，形参为机场资源对象，返回值为布尔值
    	if(airportResource.getType().equals("登机门"))
    	{
    	    sql = "SELECT * FROM bc_allocation WHERE Bname=?";
    	    db1= new db_connection(sql);//创建db_connection对象
        	try {
    			db1.pst = db1.conn.prepareStatement(sql);
    			db1.pst.setString(1, airportResource.getName());
    			ret = db1.pst.executeQuery();
    			if(ret.next())
    				return false;
    		} catch (SQLException e2) {
    			// TODO Auto-generated catch block
    			e2.printStackTrace();
    		}
    		
    		sql = "DELETE FROM boardinggate WHERE Bname=?";//SQL语句  
    	}
    	else if(airportResource.getType().equals("值机柜台"))
    	{
    		sql = "SELECT * FROM bc_allocation WHERE Cname=?";
    	    db1= new db_connection(sql);//创建db_connection对象
        	try {
    			db1.pst = db1.conn.prepareStatement(sql);
    			db1.pst.setString(1, airportResource.getName());
    			ret = db1.pst.executeQuery();
    			if(ret.next())
    				return false;
    		} catch (SQLException e2) {
    			// TODO Auto-generated catch block
    			e2.printStackTrace();
    		}
    		
    		sql = "DELETE FROM checkincounter WHERE Cname=?";//SQL语句
    	}
    	else if(airportResource.getType().equals("行李转盘"))
    	{
    		sql = "SELECT * FROM lc_allocation WHERE Lname=?";
    	    db1= new db_connection(sql);//创建db_connection对象
        	try {
    			db1.pst = db1.conn.prepareStatement(sql);
    			db1.pst.setString(1, airportResource.getName());
    			ret = db1.pst.executeQuery();
    			if(ret.next())
    				return false;
    		} catch (SQLException e2) {
    			// TODO Auto-generated catch block
    			e2.printStackTrace();
    		}
    		
    		sql = "DELETE FROM luggagecarousel WHERE Lname=?";//SQL语句
    	}
    	db1= new db_connection(sql);//创建db_connection对象  
        try {  
        	 db1.pst.setString(1, airportResource.getName());
             db1.pst.executeUpdate();//执行语句，得到结果集  
          
        } catch (SQLException e) {  
           e.printStackTrace();  
           return false;
        }finally
        {    	       
            db1.close();//关闭连接     
        }
        return true;
    	
    }
    public boolean addPropertyFacility(PropertyFacility propertyFacility)
    {//数据库操作：新增物业设施，形参为物业设施对象，返回值为布尔值
    	sql = "INSERT INTO facility (Fname,F_category,F_tel,Location,Remarks) " +
        "VALUES (?,?,?,?,?);";//SQL语句  
        db1= new db_connection(sql);//创建db_connection对象  
         try {  
        	 db1.pst.setString(1, propertyFacility.getName());
        	 db1.pst.setString(2, propertyFacility.getType());
        	 db1.pst.setString(3, propertyFacility.getPhone());
        	 db1.pst.setString(4, propertyFacility.getLocation());
        	 db1.pst.setString(5, propertyFacility.getRemark());
             db1.pst.executeUpdate();//执行语句，得到结果集              
       } catch (SQLException e) {  
            e.printStackTrace();  
            return false;
       }finally
       {    	       
           db1.close();//关闭连接     
       }
       return true;     	
    }
    public boolean modifyPropertyFacility(PropertyFacility propertyFacility)
    {//数据库操作：修改物业设施，形参为物业设施对象，返回值为布尔值
    	sql = "UPDATE facility SET F_category = ?,F_tel = ?,Location = ?, Remarks = ? WHERE Fname = ?";
        db1 = new db_connection(sql);
        try{
        	 db1.pst.setString(1, propertyFacility.getType());
        	 db1.pst.setString(2, propertyFacility.getPhone());
        	 db1.pst.setString(3, propertyFacility.getLocation());
        	 db1.pst.setString(4, propertyFacility.getRemark());
        	 db1.pst.setString(5, propertyFacility.getName());
	           db1.pst.executeUpdate();
            }catch(SQLException e){
	              e.printStackTrace();
	              return false;
            }finally
            {    	       
                db1.close();//关闭连接     
            }
            return true;
   	
    }
    public boolean deletePropertyFacility(PropertyFacility propertyFacility)
    {//数据库操作：删除物业设施，形参为物业设施对象，返回值为布尔值
    	sql = "DELETE FROM facility WHERE Fname = ?";
		db1 = new db_connection(sql);
		try{
			 db1.pst.setString(1, propertyFacility.getName());
			 db1.pst.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}finally
	       {    	       
	           db1.close();//关闭连接     
	       }
		return true;
    	
    }
    public boolean addDepartureFlightInfo(DepartureFlightInfo departureFlightInfo)
    {//数据库操作：新增离港航班信息，形参为离港航班信息对象，返回值为布尔值 	
    	String[] checkincounter=departureFlightInfo.getCheckinCounter();
    	int i=0;
    	sql="SELECT COUNT(*) FROM flight_off WHERE flight_off.Flight_No2=?";
        db1= new db_connection(sql);//创建db_connection对象
    	try {
    		int countFlightInfo=0;
    		db1.conn.setAutoCommit(false);
    		db1.pst.setString(1, departureFlightInfo.getFlightCourse().getFlightNumber());
    		ret = db1.pst.executeQuery();
    		while(ret.next()){
    			countFlightInfo=ret.getInt("COUNT(*)");
    		}
    		ret.close();
    		if(countFlightInfo==1){
    			sql = "INSERT INTO bc_allocation (Flight_No2,Bname,Cname,Time) " +
    		    "VALUES (?,?,?,?)";//SQL语句  
    			db1.conn.setAutoCommit(false);
    			i=0;
    			while(i<checkincounter.length){
             	db1.pst=db1.conn.prepareStatement(sql);
            	db1.pst.setString(1, departureFlightInfo.getFlightCourse().getFlightNumber());
             	db1.pst.setString(2, departureFlightInfo.getBoardingGate());
            	db1.pst.setString(3, checkincounter[i]);
            	db1.pst.setString(4, departureFlightInfo.getTime());
            	db1.pst.executeUpdate();//执行语句
            	i++;
    			}
            	db1.conn.commit(); 
    		}
    		else{
    			
            sql = "INSERT INTO flight_off (Flight_No2,InternationalOrLocal,Starting_station,Destination,Staging_post,Airline) "+
 	           	  "VALUES (?,?,?,?,?,?)";//SQL语句   	          
            db1.conn.setAutoCommit(false);
            db1.pst=db1.conn.prepareStatement(sql);
            db1.pst.setString(1,departureFlightInfo.getFlightCourse().getFlightNumber() );
            db1.pst.setBoolean(2, departureFlightInfo.getFlightCourse().isInternationalOrLocal());
            db1.pst.setString(3, departureFlightInfo.getFlightCourse().getFrom());
            db1.pst.setString(4, departureFlightInfo.getFlightCourse().getTo());
            db1.pst.setString(5, departureFlightInfo.getFlightCourse().getStop());
            db1.pst.setString(6, departureFlightInfo.getFlightCourse().getAirline());
            db1.pst.executeUpdate();//执行语句            
        	sql = "INSERT INTO bc_allocation (Flight_No2,Bname,Cname,Time) " +
		    "VALUES (?,?,?,?)";//SQL语句  
        	i=0;
        	while(i<checkincounter.length){
         	db1.pst=db1.conn.prepareStatement(sql);
        	db1.pst.setString(1, departureFlightInfo.getFlightCourse().getFlightNumber());
         	db1.pst.setString(2, departureFlightInfo.getBoardingGate());
        	db1.pst.setString(3, checkincounter[i]);
        	db1.pst.setString(4, departureFlightInfo.getTime());
        	db1.pst.executeUpdate();//执行语句
        	i++;
        	}
        	db1.conn.commit(); 
    		}
           } catch (SQLException e) {  
     	        try{
             		db1.conn.rollback();
            	}catch(SQLException e1){
     	        	e1.printStackTrace();
     	        	return false;
             	}
                   e.printStackTrace(); 
                  return false;
        }
        finally{
     	 try {
              db1.conn.setAutoCommit(true);
              db1.close();//关闭连接   
          } catch (SQLException e) {
              e.printStackTrace();
          }         
     }    
     return true;  
    	
    }
    public boolean modifyDepartureFlightInfo(DepartureFlightInfo departureFlightInfo)
    {//数据库操作：修改离港航班信息，形参为离港航班信息对象，返回值为布尔值
    	String[] checkincounter=departureFlightInfo.getCheckinCounter();
    	int i=0;
    	sql = "UPDATE flight_off SET flight_off.InternationalOrLocal=?," +
	 		"flight_off.Starting_station=?,flight_off.Destination=?,flight_off.Staging_post=?,flight_off.Airline=? where flight_off.Flight_No2=?";//SQL语句  
 	     db1= new db_connection(sql);//创建db_connection对象  
        try {
        	db1.conn.setAutoCommit(false);
        	db1.pst.setBoolean(1, departureFlightInfo.getFlightCourse().isInternationalOrLocal());
         	db1.pst.setString(2, departureFlightInfo.getFlightCourse().getFrom());
        	db1.pst.setString(3, departureFlightInfo.getFlightCourse().getTo());
        	db1.pst.setString(4, departureFlightInfo.getFlightCourse().getStop());
     	    db1.pst.setString(5, departureFlightInfo.getFlightCourse().getAirline());  
     	    db1.pst.setString(6, departureFlightInfo.getFlightCourse().getFlightNumber());
     	    db1.pst.executeUpdate();//更新flight_off表
     	    
     	    sql = "delete FROM bc_allocation WHERE bc_allocation.Flight_No2=? and bc_allocation.Time=?";//SQL语句
     	    db1.pst=db1.conn.prepareStatement(sql);
     	    db1.pst.setString(1, departureFlightInfo.getFlightCourse().getFlightNumber());
         	db1.pst.setString(2, departureFlightInfo.getTime());
         	 db1.pst.executeUpdate();//删除bc_location表里航班号为？和航班时间为？的数据
         	 
         	sql = "INSERT INTO bc_allocation (Flight_No2,Bname,Cname,Time) " +
		    "VALUES (?,?,?,?)";//SQL语句  
        	i=0;
        	while(i<checkincounter.length){
         	db1.pst=db1.conn.prepareStatement(sql);
        	db1.pst.setString(1, departureFlightInfo.getFlightCourse().getFlightNumber());
         	db1.pst.setString(2, departureFlightInfo.getBoardingGate());
        	db1.pst.setString(3, checkincounter[i]);
        	db1.pst.setString(4, departureFlightInfo.getTime());
        	db1.pst.executeUpdate();//执行语句 ，向bc_location表插入新的航班号为？和航班时间为？的数据
        	i++;
        	}
        	db1.conn.commit();
           } catch (SQLException e) {      	       
                   e.printStackTrace(); 
                  return false;
        }
        finally{    	 
        	 try {
                 db1.conn.setAutoCommit(true);
                 db1.close();//关闭连接   
             } catch (SQLException e) {
                 e.printStackTrace();
             }   
     }    
     return true; 
    	
    }
    public boolean deleteDepartureFlightInfo(DepartureFlightInfo departureFlightInfo)
    {//数据库操作：删除离港航班信息，形参为离港航班信息对象，返回值为布尔值
    	sql = "SELECT count( DISTINCT bc_allocation.Time) FROM bc_allocation WHERE bc_allocation.Flight_No2=?";//SQL语句  
	     db1= new db_connection(sql);//创建db_connection对象  
       try {  
    	   db1.conn.setAutoCommit(false);
    	   int countFlightInfo=0;
    	   db1.pst.setString(1, departureFlightInfo.getFlightCourse().getFlightNumber());
           ret = db1.pst.executeQuery();
           while(ret.next()){
        	   countFlightInfo=ret.getInt("count( DISTINCT bc_allocation.Time)");
       		}
           ret.close();
           sql = "delete FROM bc_allocation WHERE bc_allocation.Flight_No2=? and bc_allocation.Time=?";//SQL语句
           db1.pst=db1.conn.prepareStatement(sql);
           db1.pst.setString(1, departureFlightInfo.getFlightCourse().getFlightNumber());
           db1.pst.setString(2, departureFlightInfo.getTime());
           db1.pst.executeUpdate();//执行语句
           if(countFlightInfo<=1)
           {
        	   sql = "delete  FROM  flight_off WHERE flight_off.Flight_No2=?";//SQL语句
               db1.pst=db1.conn.prepareStatement(sql);
               db1.pst.setString(1, departureFlightInfo.getFlightCourse().getFlightNumber());
       	       db1.pst.executeUpdate();//执行语句
       	       
       		}
	        db1.conn.commit();
          } catch (SQLException e) {  
                e.printStackTrace(); 
                return false;
       }
       finally{
    	   try {
               db1.conn.setAutoCommit(true);
               db1.close();//关闭连接   
           } catch (SQLException e) {
               e.printStackTrace();
           }   
    }    
    return true;  	
    }
    public boolean addArrivalFlightInfo(ArrivalFlightInfo arrivalFlightInfo)
    {//数据库操作：新增到港航班信息，形参为到港航班信息对象，返回值为布尔值
    	sql = "SELECT COUNT(*) FROM flight_arrival WHERE flight_arrival.Flight_No= ?";
    	db1 = new db_connection(sql);//创建db_connection对象
    	try{
    		int countFilghtInfo = 0;
    		db1.conn.setAutoCommit(false);
    		 db1.pst.setString(1, arrivalFlightInfo.getFlightCourse().getFlightNumber());
    		ret = db1.pst.executeQuery();
    		while(ret.next()){
    			countFilghtInfo = ret.getInt("COUNT(*)");
    		}
    		ret.close();
    	    if(countFilghtInfo==1){
    	    	sql = "INSERT INTO lc_allocation(Flight_No, Time, Lname)" +
    	      "VALUES(?,?,?)";//SQL语句
    	    	db1.conn.setAutoCommit(false);
             	db1.pst=db1.conn.prepareStatement(sql);
             	db1.pst.setString(1, arrivalFlightInfo.getFlightCourse().getFlightNumber());
             	db1.pst.setString(2, arrivalFlightInfo.getTime());
             	db1.pst.setString(3,arrivalFlightInfo.getLuggageCarousel());
             	db1.pst.executeUpdate();
             	db1.conn.commit();
    	    }
    	    else{
    	    	sql = "INSERT INTO flight_arrival (Flight_No, InternationalOrLocal,Starting_station,Destination,Staging_post,Airline) "
    	    			+ "VALUES (?,?,?,?,?,?)";//SQL语句
    	    	db1.conn.setAutoCommit(false);
    	    	db1.pst = db1.conn.prepareStatement(sql);
    	    	 db1.pst.setString(1, arrivalFlightInfo.getFlightCourse().getFlightNumber());
    	    	 db1.pst.setBoolean(2, arrivalFlightInfo.getFlightCourse().isInternationalOrLocal());
    	    	 db1.pst.setString(3, arrivalFlightInfo.getFlightCourse().getFrom());
    	    	 db1.pst.setString(4, arrivalFlightInfo.getFlightCourse().getTo());
    	    	 db1.pst.setString(5, arrivalFlightInfo.getFlightCourse().getStop());
    	    	 db1.pst.setString(6, arrivalFlightInfo.getFlightCourse().getAirline());
    	    	db1.pst.executeUpdate();
    	    	sql = "INSERT INTO lc_allocation(Flight_No, Time, Lname)" +
    	      	      "VALUES(?,?,?)";//SQL语句
    	    	db1.pst = db1.conn.prepareStatement(sql);
    	      	db1.pst.setString(1, arrivalFlightInfo.getFlightCourse().getFlightNumber());
    	        db1.pst.setString(2, arrivalFlightInfo.getTime());
    	      	db1.pst.setString(3, arrivalFlightInfo.getLuggageCarousel());
    	       	db1.pst.executeUpdate();
              	db1.conn.commit();
    	    }
    	}catch(SQLException e){
    		try{
    			db1.conn.rollback();
    		}catch(SQLException e1){
    			e1.printStackTrace();
    			return false;
    		}
    		e.printStackTrace();
    		return false;
    	}finally{
    		try{
    			db1.conn.setAutoCommit(true);
    			db1.close();//关闭连接
    		}catch(SQLException e){
    			e.printStackTrace();
    		}
    	}
		return true;    	
    }
    public boolean modifyArrivalFlightInfo(ArrivalFlightInfo arrivalFlightInfo)
    {//数据库操作：修改到港航班信息，形参为到港航班信息对象，返回值为布尔值
		sql = "UPDATE lc_allocation INNER JOIN flight_arrival ON lc_allocation.Flight_No =flight_arrival.Flight_No " +
				"SET flight_arrival.InternationalOrLocal =?,flight_arrival.Starting_station =?," +
				"flight_arrival.Destination =?,flight_arrival.Staging_post =?,flight_arrival.Airline =?," +
				"lc_allocation.Lname =? WHERE lc_allocation.Flight_No =? AND lc_allocation.Time =?";//SQL语句
        db1= new db_connection(sql);//创建db_connection对象 

        try {           
            db1.pst.setBoolean(1, arrivalFlightInfo.getFlightCourse().isInternationalOrLocal());
            db1.pst.setString(2, arrivalFlightInfo.getFlightCourse().getFrom());
            db1.pst.setString(3, arrivalFlightInfo.getFlightCourse().getTo());
            db1.pst.setString(4, arrivalFlightInfo.getFlightCourse().getStop());
            db1.pst.setString(5, arrivalFlightInfo.getFlightCourse().getAirline());
            db1.pst.setString(6, arrivalFlightInfo.getLuggageCarousel());
            db1.pst.setString(7, arrivalFlightInfo.getFlightCourse().getFlightNumber());
            db1.pst.setString(8, arrivalFlightInfo.getTime());
            db1.pst.executeUpdate();//执行语句 
        } catch (SQLException e) {              
           e.printStackTrace(); 
           return false;
        }
         finally{         
              db1.close();//关闭连接   
        }    
        return true; 	
    }
    public boolean deleteArrivalFlightInfo(ArrivalFlightInfo arrivalFlightInfo)
    {//数据库操作：删除到港航班信息，形参为到港航班信息对象，返回值为布尔值
        	sql = "SELECT COUNT(*) FROM lc_allocation WHERE lc_allocation.Flight_No= ?";
        	db1= new db_connection(sql);//创建db_connection对象 
        	try{
        		int countFlightInfo = 0;
        		db1.pst.setString(1, arrivalFlightInfo.getFlightCourse().getFlightNumber());
        		ret = db1.pst.executeQuery();
        		while(ret.next()){
        			countFlightInfo = ret.getInt("COUNT(*)");
        		}
        		ret.close();
        		if(countFlightInfo > 1)
        			{
        			sql = "DELETE FROM lc_allocation WHERE lc_allocation.Flight_No = ? and lc_allocation.Lname = ? and lc_allocation.Time = ?";//SQL语句
        			 db1.pst=db1.conn.prepareStatement(sql);
        			 db1.pst.setString(1, arrivalFlightInfo.getFlightCourse().getFlightNumber());
        			 db1.pst.setString(2, arrivalFlightInfo.getLuggageCarousel());
        			 db1.pst.setString(3, arrivalFlightInfo.getTime());
                     db1.pst.executeUpdate();//执行语句
        			}
        		else
        			{
        			      sql = "DELETE lc_allocation,flight_arrival FROM lc_allocation INNER JOIN flight_arrival on lc_allocation.Flight_No = flight_arrival.Flight_No WHERE lc_allocation.Flight_No = ?" +
        			      		" and lc_allocation.Lname = ? and lc_allocation.Time=?";//SQL语句
        			      db1.pst=db1.conn.prepareStatement(sql);
             			  db1.pst.setString(1, arrivalFlightInfo.getFlightCourse().getFlightNumber());
             			  db1.pst.setString(2, arrivalFlightInfo.getLuggageCarousel());
             			  db1.pst.setString(3, arrivalFlightInfo.getTime());
                          db1.pst.executeUpdate();//执行语句 
        			}
        	}catch(SQLException e){
        		e.printStackTrace();
        		return false;
        	}finally{
        		db1.close();
        	}
        	return true;
   	
    }
    public boolean addNews(News news)  
    {//数据库操作：新增新闻，形参为新闻对象，返回值为布尔值
		sql = "INSERT INTO newscenter (Em_No,NCname,Title,Edit_time,Attachments,Content) " +
				"VALUES (?,?,?,?,?,?);";//SQL语句  
        db1= new db_connection(sql);//创建db_connection对象  
         try {
//        	db1.pst.setString(1, news.getNewsId());
        	db1.pst.setString(1, news.getPublisher_id());
         	db1.pst.setString(2, news.getKind());
         	db1.pst.setString(3, news.getTitle());
         	db1.pst.setString(4, news.getTime());
         	db1.pst.setString(5, news.getAttachment());
         	db1.pst.setString(6, news.getContent());      	
            db1.pst.executeUpdate();//执行语句，得到结果集  
       } catch (SQLException e) {  
            e.printStackTrace();  
            return false;
       }finally
       {    	       
           db1.close();//关闭连接     
       }
		return true;   	
    }
    public boolean modifyNews(News news)
    {//数据库操作：修改新闻，形参为新闻对象，返回值为布尔值
    	sql = "UPDATE newscenter SET Em_No=?,NCname=?,Title=?,Edit_time=?,Attachments=?,Content=?" +
		"WHERE News_id=?";//SQL语句  
    	db1= new db_connection(sql);//创建db_connection对象  
        try {  
        	db1.pst.setString(1, news.getPublisher_id());
        	db1.pst.setString(2, news.getKind());
        	db1.pst.setString(3, news.getTitle());
        	db1.pst.setString(4, news.getTime());
        	db1.pst.setString(5, news.getAttachment());
        	db1.pst.setString(6, news.getContent()); 
        	db1.pst.setString(7, news.getNewsId());
            db1.pst.executeUpdate();//执行语句，得到结果集  
        } catch (SQLException e) {  
                e.printStackTrace();  
                 return false;
       }finally
       {    	       
           db1.close();//关闭连接     
       }
          return true;     	
    }
    public boolean deleteNews(News news)
    {//数据库操作：删除新闻，形参为新闻对象，返回值为布尔值
    	sql = "DELETE FROM newscenter WHERE News_id=?";//SQL语句  
    	db1= new db_connection(sql);//创建db_connection对象  
        try {  
        	 db1.pst.setString(1, news.getNewsId());
             db1.pst.executeUpdate();//执行语句，得到结果集  
        } catch (SQLException e) {  
                e.printStackTrace();  
                 return false;
       }finally
       {    	       
           db1.close();//关闭连接     
       }
          return true;
    	
    }
    public News[] searchNews(String title,String time,String publisher){
   	 
      /*
       * News[] searchN(String title,String time,String publisher);
       * 数据库操作：查询新闻标题为title，发布时间为time，发布人为publisher的新闻信息；若其中一形参值为空字符串，则表示无此限制条件
       * 形参为新闻标题、发布时间，返回类型为News对象数组
       */
   	 News[] news =null;
   	 int i=0;
     	sql = "SELECT * FROM newscenter INNER JOIN user_info ON newscenter.Em_No = user_info.Em_No ORDER BY newscenter.Edit_time DESC";//SQL语句
   	 db1= new db_connection(sql);//创建db_connection对象 
   	 String newsId;
   	 String content;
   	 String kind;
   	 String publisher_id;
   	 String attachment;
   	 try {  
   		 if((!title.equals("")&&title!=null)&&(!time.equals("")&&time!=null)&&(publisher!=null&&!publisher.equals("")))
   		 {
   			 sql = "SELECT * FROM newscenter INNER JOIN user_info ON newscenter.Em_No = user_info.Em_No WHERE newscenter.title like ? AND newscenter.Edit_time=? AND user_info.Name = ? ORDER BY newscenter.Edit_time DESC";//SQL语句   			  
   			 db1.pst=db1.conn.prepareStatement(sql);
   			 db1.pst.setString(1, "%"+title+"%");
   			 db1.pst.setString(2, time);
   			 db1.pst.setString(3, publisher);
   		 }
   	   	 else if((!title.equals("")&&title!=null)&&(time.equals("")||time==null)&&(publisher==null||publisher.equals("")))
   	   	 {
   	   		 sql = "SELECT * FROM newscenter INNER JOIN user_info ON newscenter.Em_No = user_info.Em_No WHERE newscenter.title like ? ORDER BY newscenter.Edit_time DESC";//SQL语句
   	       	 db1.pst=db1.conn.prepareStatement(sql);
			 db1.pst.setString(1, "%"+title+"%");
   	   	 }
   	   	 else if((title.equals("")||title==null)&&(!time.equals("")&&time!=null)&&(publisher==null||publisher.equals("")))
   	   	 {
   	   		 sql = "SELECT * FROM newscenter INNER JOIN user_info ON newscenter.Em_No = user_info.Em_No WHERE newscenter.Edit_time=? ORDER BY newscenter.Edit_time DESC";//SQL语句
   	   	db1.pst=db1.conn.prepareStatement(sql);
			 db1.pst.setString(1, time);
   	   	 }
   	   	 else if((title.equals("")||title==null)&&(time.equals("")||time==null)&&(publisher!=null&&!publisher.equals("")))
   	   	 {
   	   		 sql = "SELECT * FROM newscenter INNER JOIN user_info ON newscenter.Em_No = user_info.Em_No WHERE user_info.Name = ? ORDER BY newscenter.Edit_time DESC";//SQL语句
   	     	db1.pst=db1.conn.prepareStatement(sql);
			 db1.pst.setString(1, publisher);
   	   	 }
   	   	 else if((!title.equals("")&&title!=null)&&(!time.equals("")&&time!=null)&&(publisher==null||publisher.equals("")))
   	   	 {
   	   		 sql = "SELECT * FROM newscenter INNER JOIN user_info ON newscenter.Em_No = user_info.Em_No WHERE newscenter.title like ? AND newscenter.Edit_time=? ORDER BY newscenter.Edit_time DESC";//SQL语句
   	    	db1.pst=db1.conn.prepareStatement(sql);
			 db1.pst.setString(1, "%"+title+"%");
			 db1.pst.setString(2, time);
   	   	 }
   	   	 else if((!title.equals("")&&title!=null)&&(time.equals("")||time==null)&&(publisher!=null&&!publisher.equals("")))
   	   	 {
   	   		 sql = "SELECT * FROM newscenter INNER JOIN user_info ON newscenter.Em_No = user_info.Em_No WHERE newscenter.title like ? AND user_info.Name = ? ORDER BY newscenter.Edit_time DESC";//SQL语句
   	    	db1.pst=db1.conn.prepareStatement(sql);
			 db1.pst.setString(1, "%"+title+"%");
			 db1.pst.setString(2, publisher);
   	   	 }
   	   	 else  if((title.equals("")||title==null)&&(!time.equals("")&&time!=null)&&(publisher!=null&&!publisher.equals("")))
   	   	 {
   	   		 sql = "SELECT * FROM newscenter INNER JOIN user_info ON newscenter.Em_No = user_info.Em_No WHERE newscenter.Edit_time=? AND user_info.Name = ? ORDER BY newscenter.Edit_time DESC";//SQL语句
   	    	db1.pst=db1.conn.prepareStatement(sql);
			 db1.pst.setString(1, time);
			 db1.pst.setString(2, publisher);
   	   	 }
   	   	 else 
   	   		 {
   	   		     sql = "SELECT * FROM newscenter INNER JOIN user_info ON newscenter.Em_No = user_info.Em_No ORDER BY newscenter.Edit_time DESC";//SQL语句
   	   		 }

   		 	ret = db1.pst.executeQuery();
   		 	ret.last();
	            int rowNumber=ret.getRow();
	            ret.beforeFirst();
	            news = new News[rowNumber];
   		 	while (ret.next()) { 
   	
   		 		newsId=ret.getString("News_id");
   		 		title=ret.getString("Title");
   		 		time=ret.getString("Edit_time");
   		 		content=ret.getString("Content");
   		 		kind=ret.getString("NCname");
   		 		publisher_id=ret.getString("Em_No");
   		 		attachment=ret.getString("Attachments");
   		 		news[i] = new News(title, time, content, kind,  attachment, publisher_id,newsId);
   		 		i++;
   		 	}
   		 	ret.close();    		 
   	 } catch (SQLException e) {  
   		 e.printStackTrace();  
   	 } finally{
   		 	db1.close();//关闭连接  
   	 }
      return news;
   }
    public String[] returnAllPosition()
    {//返回所有职位名称，返回String数组
    	String[] allPosition=null;
    	 int i=0;
         sql = "SELECT DISTINCT Position FROM user_info";//SQL语句  
         db1= new db_connection(sql);//创建db_connection对象         
         try {  
             ret = db1.pst.executeQuery();//执行语句，得到结果集 
             ret.last();
             int rowNumber=ret.getRow();
             ret.beforeFirst();
             allPosition=new String[rowNumber];
             while (ret.next()) {           	 
             	allPosition[i]=ret.getString("Position");   	  	         	 
                   i++;
             }
             ret.close();  
            
         } catch (SQLException e) {  
             e.printStackTrace();  
         } 
         finally{
         	 db1.close();//关闭连接  
         }
    	return allPosition;
    }
    public String[] returnAllDepartment()
    {//返回所有部门名称 ，返回String数组
    	String[] allDepartment=null;
    	 int i=0;
         sql = "SELECT DISTINCT Dept FROM user_info";//SQL语句  
         db1= new db_connection(sql);//创建db_connection对象         
         try {  
             ret = db1.pst.executeQuery();//执行语句，得到结果集 
             ret.last();
             int rowNumber=ret.getRow();
             ret.beforeFirst();
             allDepartment=new String[rowNumber];
             while (ret.next()) {           	 
             	allDepartment[i]=ret.getString("Dept");   	  	         	 
                   i++;
             }
             ret.close();  
            
         } catch (SQLException e) {  
             e.printStackTrace();  
         } 
         finally{
         	 db1.close();//关闭连接  
         }
    	return allDepartment;
    }
}
