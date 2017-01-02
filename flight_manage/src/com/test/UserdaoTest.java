package com.test;

import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dao.AdminDao;
import com.dao.LoginDao;
import com.dao.RoleDao;
import com.dao.UserDao;
import com.entity.Admin;
import com.entity.AirportResource;
import com.entity.DepartureFlightInfo;
import com.entity.Role;
import com.entity.User;
import com.servlet.GetMD5;

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
	     /*AdminDao adminDao=new AdminDao();
	     Role role=adminDao.searchRole("ass");
	     if(role==null)
	    	 System.out.println("null!");*/
	     /*Admin[] admins = adminDao.searchAdmin1("", 1, "人力主管", "");
	     if(admins.length==0)
	    	 System.out.println("null!");*/
	     /*for(Admin output:admins)
	    	 System.out.println(output.getEmpno());*/
	     String[]  string;
	     UserDao userDao = new UserDao();
	     DepartureFlightInfo[] test = userDao.searchDepartureFlightInfo4("MF8601", "2016-12-19 07:45:00");
	     /*System.out.println(test[0].getCheckinCounter()[1]);*/
	     DepartureFlightInfo[] test1 = userDao.searchDepartureFlightInfo0("MF8601");
	     /*System.out.println(test1[0].getCheckinCounter()[1]);*/
	     AirportResource[] resourceInfo = userDao.searchAirportResource("登机门23", "");
	     /*System.out.println(resourceInfo[0].getName());*/
	     User user = new User();
	     Map<String, String> airlineCode = user.returnFlightnumberAirlineMap();
	     /*for (String s : airlineCode.keySet()) 
	     {
	    	 System.out.println("key:" + s);
	    	 System.out.println("values:" + airlineCode.get(s));
	     }*/
	     /*HashMap<String, String> map = new HashMap<String, String>() {
	     {
	    	 put("Name", "June");  
	    	 put("QQ", "4889983");  
	     }
	     };
	     System.out.println(map.get("QQ"));*/
//	     System.out.println(GetMD5.getMd5(""));
	     AdminDao adminDao=new AdminDao();
	     Role role = adminDao.searchRole("test+test");
	     //System.out.println(role.getDescription());
	     
	     int searchDepartureFlightInfo5 = userDao.searchDepartureFlightInfo5("登机门24", "2017-01-01 06:51");
	     System.out.println("searchDepartureFlightInfo5:" + searchDepartureFlightInfo5);
	}
}
