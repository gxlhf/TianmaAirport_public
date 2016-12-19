package com.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dao.AdminDao;
import com.dao.LoginDao;
import com.dao.RoleDao;
import com.entity.Admin;
import com.entity.Role;

public class UserdaoTest {
	public static void main(String[] args){
	   	 /*Admin ad= null;
	   	 RoleDao roleDao = new RoleDao();
	     LoginDao lD=new LoginDao();
	     ad=lD.loginCheck("9527", "19971004");
	     if(ad==null)
	     {
	    	 System.out.println("null！");
	     }*/
	     //roleDao.roleDescriptionSearch(ad.getRole().getName());
//	     System.out.println(ad.getRole().getName());
//	     System.out.println(roleDao.roleDescriptionSearch("系统管理员"));
//	     System.out.println(ad.getEmpno()+ad.getName()+ad.getSex()+ad.getEmail()+ad.getDepartment()+ad.getPhone()+ad.getPosition()+ad.getMobile()+ad.getRole().getName()+ad.getRole().getDescription()+ad.getRole().getAuthorityMap());
	     AdminDao adminDao=new AdminDao();
	     Role role=adminDao.searchRole("ass");
	     if(role==null)
	    	 System.out.println("null!");
	     /*Admin[] admins = adminDao.searchAdmin1("", 1, "人力主管", "");
	     if(admins.length==0)
	    	 System.out.println("null!");*/
	     /*for(Admin output:admins)
	    	 System.out.println(output.getEmpno());*/
	     String[]  string;
	     
	    
	     
	     
	     
	     
	     
	}
}
