/*
 * 普通用户类
 */
package com.entity;
import java.util.Map;

import com.dao.*;

public class User {
	//返回所有国内离港航班信息
	public DepartureFlightInfo[] returnAllLocalDepartureFlightInfo()
	{
		UserDao userDao = new UserDao();
		return userDao.returnAllLocalDepartureFlightInfo();
	}
	//返回所有国际离港航班信息
	public DepartureFlightInfo[] returnAllInternationalDepartureFlightInfo()
	{
		UserDao userDao = new UserDao();
		return userDao.returnAllInternationalDepartureFlightInfo();
	}
	//返回所有国内到港航班信息
	public ArrivalFlightInfo[] returnAllLocalArrivalFlightInfo()
	{
		UserDao userDao = new UserDao();
		return userDao.returnAllLocalArrivalFlightInfo();
	}
	//返回所有国际到港航班信息
	public ArrivalFlightInfo[] returnAllInternationalArrivalFlightInfo()
	{
		UserDao userDao = new UserDao();
		return userDao.returnAllInternationalArrivalFlightInfo();
	}
	//离港航班信息查询函数，输入三个参数依次为目的地、航班号、航空公司、时间，返回一个DepartureFlightInfo对象数组
	public DepartureFlightInfo[] searchDepartureFlightInfo(String city, String flightNumber, String airline, String time)
	{
		DepartureFlightInfo[] departureFlightInfo = null;
		UserDao userDao = new UserDao();
		if((city==null||city.equals(""))&&(airline==null||airline.equals(""))&&(flightNumber!=null&&!flightNumber.equals(""))&&(time!=null&&!time.equals(""))){
			//用于修改离港航班信息时取出修改前的数据，正常情况下只返回一条结果
			departureFlightInfo = userDao.searchDepartureFlightInfo4(flightNumber, time);
			return departureFlightInfo;
		}
		if(flightNumber!=null&&!flightNumber.equals("")){//查询该航班号的离港航班信息
			/*
			 * DepartureFlightInfo[] searchDepartureFlightInfo0(String flightnumber);
			 * 数据库操作：查询航班号为flightnumber的离港航班信息
			 * 形参为航班号，返回类型为DepartureFlightInfo对象数组
			 * 
			 */
			departureFlightInfo=userDao.searchDepartureFlightInfo0(flightNumber); 
			return departureFlightInfo;
		}
		else{
			if(!(city==null||city.equals(""))&&!(airline==null||airline.equals(""))){//查询目的地为该city，航空公司为该airline的离港航班信息
				/*
				 * DepartureFlightInfo[] searchDepartureFlightInfo1(String city,String airline);
				 * 数据库操作：查询 目的地为该city，航空公司为该airline的离港航班信息
				 * 形参为目的地和航空公司，返回类型为DepartureFlightInfo对象数组
				 * 
				 */	
				departureFlightInfo=userDao.searchDepartureFlightInfo1(city,airline); 
				return departureFlightInfo;
			}
			if((city==null||city.equals(""))&&!(airline==null||airline.equals(""))){//查询航空公司为该airline的离港航班信息
				/*
				 * DepartureFlightInfo[] searchDepartureFlightInfo2(String airline); 
				 * 数据库操作：查询航空公司为该airline的离港航班信息
				 * 形参为航空公司，返回类型为DepartureFlightInfo对象数组
				 *  
				 */
				departureFlightInfo=userDao.searchDepartureFlightInfo2(airline);
				return departureFlightInfo;
			}
			if(!(city==null||city.equals(""))&&(airline==null||airline.equals(""))){//查询目的地为该city的离港航班信息
				/*
				 * DepartureFlightInfo[] searchDepartureFlightInfo3(String city); 
				 * 数据库操作：查询目的地为该city的离港航班信息
				 * 形参为目的地，返回类型为DepartureFlightInfo对象数组
				 * 
				 */
				departureFlightInfo=userDao.searchDepartureFlightInfo3(city); 
				return departureFlightInfo;
			}
		}
		return departureFlightInfo;
	}
	//离港航班信息查询函数，输入两个参数依次为登机门名称、时间，返回符合该条件的航班信息条目数
	public int searchDepartureFlightInfo(String boardingGate, String time)
	{
		UserDao userDao = new UserDao();
		int ret = userDao.searchDepartureFlightInfo5(boardingGate, time);
		return ret;
	}
	//到港航班信息查询函数，输入三个参数依次为出发地、航班号、航空公司、时间，返回一个ArrivalFlightInfo对象数组
	public ArrivalFlightInfo[] searchArrivalFlightInfo(String city, String flightNumber, String airline, String time)
	{
		ArrivalFlightInfo[] arrivalFlightInfo = null;
		UserDao userDao = new UserDao();
		if((city==null||city.equals(""))&&(airline==null||airline.equals(""))&&(flightNumber!=null&&!flightNumber.equals(""))&&(time!=null&&!time.equals(""))){
			//用于修改到港航班信息时取出修改前的数据，正常情况下只返回一条结果
			arrivalFlightInfo=userDao.searchArrivalFlightInfo4(flightNumber, time);
			return arrivalFlightInfo;
		}
		if(flightNumber!=null&&!flightNumber.equals("")){//查询该航班号的到港航班信息
			/*
			 * ArrivalFlightInfo[] searchArrivalFlightInfo0(String flightnumber); 
			 * 数据库操作：查询航班号为flightnumber的到港航班信息
			 * 形参为航班号，返回类型为ArrivalFlightInfo对象数组
			 * 
			 */
			arrivalFlightInfo=userDao.searchArrivalFlightInfo0(flightNumber);
			return arrivalFlightInfo;
		}
		else{
			if(!(city==null||city.equals(""))&&!(airline==null||airline.equals(""))){//查询出发地为该city，航空公司为该airline的到港航班信息
				/*
				 * ArrivalFlightInfo[] searchArrivalFlightInfo1(String city,String airline); 
				 * 数据库操作：查询 出发地为该city，航空公司为该airline的到港航班信息
				 * 形参为出发地和航空公司，返回类型为ArrivalFlightInfo对象数组
				 * 
				 */		
				arrivalFlightInfo=userDao.searchArrivalFlightInfo1(city,airline);
				return arrivalFlightInfo;
			}
			if((city==null||city.equals(""))&&!(airline==null||airline.equals(""))){//查询航空公司为该airline的到港航班信息
				/*
				 * ArrivalFlightInfo[] searchArrivalFlightInfo2(String airline); 
				 * 数据库操作：查询航空公司为该airline的到港航班信息
				 * 形参为航空公司，返回类型为ArrivalFlightInfo对象数组
				 * 
				 */
				arrivalFlightInfo=userDao.searchArrivalFlightInfo2(airline); 
				return arrivalFlightInfo;
			}
			if(!(city==null||city.equals(""))&&(airline==null||airline.equals(""))){//查询出发地为该city的到港航班信息
				/*
				 * ArrivalFlightInfo[] searchArrivalFlightInfo3(String city); 
				 * 数据库操作：查询出发地为该city的到港航班信息
				 * 形参为出发地，返回类型为ArrivalFlightInfo对象数组
				 * 
				 */
				arrivalFlightInfo=userDao.searchArrivalFlightInfo3(city); 
				return arrivalFlightInfo;
			}
		}
		return arrivalFlightInfo;
	}
	
	//机场资源查询函数，输入参数为机场资源名称和类别，返回一个AirportResource对象数组
	public AirportResource[] searchAirportResource(String name, String type)
	{
		
		UserDao userDao = new UserDao();
		AirportResource[] airportResource = null;
		/*
		 * AirportResource[] searchA(String name, String type);
		 * 数据库操作：查询机场资源名称为该name和类别为type的机场资源信息，若其中一项为空表示无此限制条件
		 * 形参为机场资源名称，返回类型为AirportResource对象数组
		 */
		airportResource=userDao.searchAirportResource(name, type);
		return airportResource;
	}
	
	//物业设施查询函数，输入参数为物业设施名称，返回一个PropertyFacility对象数组
	public PropertyFacility[] searchPropertyFacility(String name,String type,int mode)
	{
		UserDao userDao = new UserDao();
		PropertyFacility[] propertyFacility = null;
		/*
		 * PropertyFacility[] searchP(String name);
		 * 数据库操作：查询物业设施名称为该name的物业设施信息
		 * 形参为物业设施名称，返回类型为PropertyFacility对象数组
		 */
		propertyFacility=userDao.searchPropertyFacility(name,type,mode);
		return propertyFacility;
	}
	
	public News[] searchNews(String title,String time)
	{
		UserDao userDao = new UserDao();
		News[] news = null;
		/*
		 * News[] searchN(String title,String time);
		 * 数据库操作：查询新闻标题为title，发布时间为time的新闻信息；若其中一形参值为空字符串，则表示无此限制条件
		 * 形参为新闻标题、发布时间，返回类型为News对象数组
		 */
		news=userDao.searchNews(title,time);
		return news;
	}

	public String[] returnAllInternationalDestination()
    {/*返回数据库中离港航班的所有国际目的地
       allInternationalDestination:国际目的地数组
       return allInternationalDestination;
      */
    	
    	String[] allInternationalDestination=null;
    	UserDao userDao=new UserDao();
    	allInternationalDestination=userDao.returnAllInternationalDestination();
    	return allInternationalDestination;
    }

	public String[] returnAllInternationalFrom()
    {/*返回数据库中到港航班的所有国际始发地
        allInternationalFrom:国际始发地数组
       return allInternationalFrom
       */
    	String[] allInternationalFrom=null;
    	UserDao userDao=new UserDao();
    	allInternationalFrom=userDao.returnAllInternationalFrom();
    	return allInternationalFrom;
    }

	public String[] returnAllLocalDestination()
    {/*返回数据库中离港航班的所有国内目的地
       allLocalDestination:国内目的地数组
       return allLocalDestination;
      */
    	
    	String[] allLocalDestination=null;
    	UserDao userDao=new UserDao();
    	allLocalDestination=userDao.returnAllLocalDestination();
    	return allLocalDestination;
    }

	public String[] returnAllLocalFrom()
    {/*返回数据库中到港航班的所有国内始发地
        allLocalFrom:国内始发地数组
       return allLocalFrom
       */
    	String[] allLocalFrom=null;
    	UserDao userDao=new UserDao();
    	allLocalFrom=userDao.returnAllLocalFrom();
    	return allLocalFrom;
    }
    
	public String[] returnAllArrivalLocalAirline()
    {/*返回数据库中到航班信息的所有国内航空公司
       allArrivalLocalAirline:到港国内航空公司数组
       return allArrivalLocalAirline
       */
    	String[] allArrivalLocalAirline=null;
    	UserDao userDao=new UserDao();
    	allArrivalLocalAirline=userDao.returnAllArrivalLocalAirline();
    	return allArrivalLocalAirline;
    }

    public String[] returnAllDepartureLocalAirline()
    {/*返回数据库中离港航班信息的所有国内航空公司
       allArrivalLocalAirline:国内航空公司数组
       return allArrivalLocalAirline
       */
    	String[] allDepartureLocalAirline=null;
    	UserDao userDao=new UserDao();
    	allDepartureLocalAirline=userDao.returnAllDepartureLocalAirline();
    	return allDepartureLocalAirline;
    }

    public String[] returnAllArrivalInternationalAirline()
    {/*返回数据库中到航班信息的所有国际航空公司
       allArrivalLocalAirline:到港国际航空公司数组
       return allArrivalLocalAirline
       */
    	String[] allArrivalInternationalAirline=null;
    	UserDao userDao=new UserDao();
    	allArrivalInternationalAirline=userDao.returnAllArrivalInternationalAirline();
    	return allArrivalInternationalAirline;
    }
    
    public String[] returnAllDepartureInternationalAirline()
    {/*返回数据库中到离港航班信息的所有国际航空公司
       allArrivalLocalAirline:离港国际航空公司数组
       return allArrivalLocalAirline
       */
    	String[] allDepartureInternationalAirline=null;
    	UserDao userDao=new UserDao();
    	allDepartureInternationalAirline=userDao.returnAllDepartureInternationalAirline();
    	return allDepartureInternationalAirline;
    }

    public AirportResource[] returnAllAirportResource()
    {  /*
    	* AirportResource[] returnAllAirportResoure();
		* 数据库操作：返回所有机场资源信息
		* 返回类型为AirportResource对象数组
		*/
    	UserDao userDao= new UserDao();
		return userDao.returnAllAirportResource();
    	
    }
    public PropertyFacility[] returnAllPropertyFacility()
    {  /*
        * PropertyFacility[] returnAllPropertyFacility();
        * 数据库操作：返回所有物业设施信息
        * 返回类型为PropertyFacility对象数组
        */
    	UserDao userDao= new UserDao();
		return userDao.returnAllPropertyFacility();
    }
    public News[] returnAllNews()
    { 	 
	   /*
		* News[]  returnAllNews();
		* 数据库操作：返回所有新闻信息；
		* 返回类型为News对象数组
		*/
    	UserDao userDao=new UserDao();
    	return userDao.returnAllNews();
    }
    public Map<String,String> returnFlightnumberAirlineMap(){
    	/*
    	 * Map<String,String> returnFlightnumberAirlineMap()
    	 * 取航空公司的二字代码和航空公司名字的对应关系
    	 * 返回Map
    	 */
		UserDao userDao=new UserDao();
    	return userDao.returnFlightnumberAirlineMap(); 	
    }
    public String[] returnAllPropertyFacilityType()
    {
		
    	/*
         * String[] returnAllPropertyFacilityType();
         * 数据库操作：返回所有物业设施类别
         * 返回类型为String对象数组
         */
    	UserDao userDao=new UserDao();
    	return userDao.returnAllPropertyFacilityType();
    }
}
