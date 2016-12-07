/*
 * 普通用户类
 */
package com.entity;
import com.dao.*;

public class User {
	//离港航班信息查询函数，输入三个参数依次为目的地、航班号、航空公司，返回一个DepartureFlightInfo对象数组
	public DepartureFlightInfo[] searchDepartureFlightInfo(String city, String flightNumber, String airline)
	{
		DepartureFlightInfo[] departureFlightInfo = null;
		UserDao userDao = new UserDao();
		if(flightNumber!=null&&flightNumber!=""){//查询该航班号的离港航班信息
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
			if(!(city==null||city=="")&&!(airline==null||airline=="")){//查询目的地为该city，航空公司为该airline的离港航班信息
				/*
				 * DepartureFlightInfo[] searchDepartureFlightInfo1(String city,String airline);
				 * 数据库操作：查询 目的地为该city，航空公司为该airline的离港航班信息
				 * 形参为目的地和航空公司，返回类型为DepartureFlightInfo对象数组
				 * 
				 */	
				departureFlightInfo=userDao.searchDepartureFlightInfo1(city,airline); 
				return departureFlightInfo;
			}
			if((city==null||city=="")&&!(airline==null||airline=="")){//查询航空公司为该airline的离港航班信息
				/*
				 * DepartureFlightInfo[] searchDepartureFlightInfo2(String airline); 
				 * 数据库操作：查询航空公司为该airline的离港航班信息
				 * 形参为航空公司，返回类型为DepartureFlightInfo对象数组
				 *  
				 */
				departureFlightInfo=userDao.searchDepartureFlightInfo2(airline);
				return departureFlightInfo;
			}
			if(!(city==null||city=="")&&(airline==null||airline=="")){//查询目的地为该city的离港航班信息
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
	//到港航班信息查询函数，输入三个参数依次为出发地、航班号、航空公司，返回一个ArrivalFlightInfo对象数组
	public ArrivalFlightInfo[] searchArrivalFlightInfo(String city, String flightNumber, String airline)
	{
		ArrivalFlightInfo[] arrivalFlightInfo = null;
		UserDao userDao = new UserDao();
		if(flightNumber!=null&&flightNumber!=""){//查询该航班号的到港航班信息
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
			if(!(city==null||city=="")&&!(airline==null||airline=="")){//查询出发地为该city，航空公司为该airline的到港航班信息
				/*
				 * ArrivalFlightInfo[] searchArrivalFlightInfo1(String city,String airline); 
				 * 数据库操作：查询 出发地为该city，航空公司为该airline的到港航班信息
				 * 形参为出发地和航空公司，返回类型为ArrivalFlightInfo对象数组
				 * 
				 */		
				arrivalFlightInfo=userDao.searchArrivalFlightInfo1(city,airline);
				return arrivalFlightInfo;
			}
			if((city==null||city=="")&&!(airline==null||airline=="")){//查询航空公司为该airline的到港航班信息
				/*
				 * ArrivalFlightInfo[] searchArrivalFlightInfo2(String airline); 
				 * 数据库操作：查询航空公司为该airline的到港航班信息
				 * 形参为航空公司，返回类型为ArrivalFlightInfo对象数组
				 * 
				 */
				arrivalFlightInfo=userDao.searchArrivalFlightInfo2(airline); 
				return arrivalFlightInfo;
			}
			if(!(city==null||city=="")&&(airline==null||airline=="")){//查询出发地为该city的到港航班信息
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
	
	//机场资源查询函数，输入参数为机场资源名称，返回一个AirportResource对象数组
	public AirportResource[] searchAirportResource(String name)
	{
		
		UserDao userDao = new UserDao();
		AirportResource[] airportResource = null;
		/*
		 * AirportResource[] searchA(String name);
		 * 数据库操作：查询机场资源名称为该name的机场资源信息
		 * 形参为机场资源名称，返回类型为AirportResource对象数组
		 */
		airportResource=userDao.searchAirportResource(name);
		return airportResource;
	}
	
	//物业设施查询函数，输入参数为物业设施名称，返回一个PropertyFacility对象数组
	public PropertyFacility[] searchPropertyFacility(String name)
	{
		UserDao userDao = new UserDao();
		PropertyFacility[] propertyFacility = null;
		/*
		 * PropertyFacility[] searchP(String name);
		 * 数据库操作：查询物业设施名称为该name的物业设施信息
		 * 形参为物业设施名称，返回类型为PropertyFacility对象数组
		 */
		propertyFacility=userDao.searchPropertyFacility(name);
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
    
	public String[] returnAllAirline()
    {/*返回数据库中到航班信息的所有航空公司
       allAirline:航空公司数组
       return allAirline
       */
    	String[] allAirline=null;
    	UserDao userDao=new UserDao();
    	allAirline=userDao.returnAllAirline();
    	return allAirline;
    }
}
