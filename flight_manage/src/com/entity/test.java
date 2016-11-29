package com.entity;

import com.dao.loginDao;

public class test {
	public static void main(String[] args){
   	 Admin ad= null;
        loginDao lD=new loginDao();
        ad=lD.loginCheck("1001", "888888");
        if(ad==null)
        {
       	 System.out.println("null！");
        }
     System.out.println(ad.empno+ad.name+ad.sex+ad.email+ad.department+ad.phone+ad.position+ad.mobile+ad.role.name+ad.role.description+ad.role.authorityMap);
        
    }
}
