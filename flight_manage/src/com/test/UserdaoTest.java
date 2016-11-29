package com.test;

import com.dao.LoginDao;
import com.entity.Admin;

public class UserdaoTest {
	public static void main(String[] args){
	   	 Admin ad= null;
	        LoginDao lD=new LoginDao();
	        ad=lD.loginCheck("1001", "888888");
	        if(ad==null)
	        {
	        	System.out.println("null！");
	        }
	     System.out.println(ad.getEmpno()+ad.getName()+ad.getSex()+ad.getEmail()+ad.getDepartment()+ad.getPhone()+ad.getPosition()+ad.getMobile()+ad.getRole().getName()+ad.getRole().getDescription()+ad.getRole().getAuthorityMap());
	        
	    }
}
