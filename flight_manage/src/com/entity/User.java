/*
 * 普通用户类
 */
package com.entity;

public class User {
	//离港航班信息查询函数，输入三个参数依次为目的地、航班号、航空公司，返回一个DepartureFlightInfo对象数组
	DepartureFlightInfo[] searchDepartureFlightInfo(String city, String flightNumber, String airline)
	{
		DepartureFlightInfo[] departureFlightInfo = null;
		if(flightNumber!=null){//查询该航班号的离港航班信息
			/*
			 * DepartureFlightInfo[] searchDepartureFlightInfo0(String flightnumber);
			 * 数据库操作：查询航班号为flightnumber的离港航班信息
			 * 形参为航班号，返回类型为DepartureFlightInfo对象数组
			 * 
			 */
			//departureFlightInfo=searchDepartureFlightInfo0(flightnumber); 
			return departureFlightInfo;
		}
		else{
			if(city!=null&&airline!=null){//查询目的地为该city，航空公司为该airline的离港航班信息
				/*
				 * DepartureFlightInfo[] searchDepartureFlightInfo1(String city,String airline);
				 * 数据库操作：查询 目的地为该city，航空公司为该airline的离港航班信息
				 * 形参为目的地和航空公司，返回类型为DepartureFlightInfo对象数组
				 * 
				 */	
				//departureFlightInfo=searchDepartureFlightInfo1(city,airline); 
				return departureFlightInfo;
			}
			if(city==null&&airline!=null){//查询航空公司为该airline的离港航班信息
				/*
				 * DepartureFlightInfo[] searchDepartureFlightInfo2(String airline); 
				 * 数据库操作：查询航空公司为该airline的离港航班信息
				 * 形参为航空公司，返回类型为DepartureFlightInfo对象数组
				 *  
				 */
				//departureFlightInfo=searchDepartureFlightInfo2(airline);
				return departureFlightInfo;
			}
			if(city!=null&&airline==null){//查询目的地为该city的离港航班信息
				/*
				 * DepartureFlightInfo[] searchDepartureFlightInfo3(String city); 
				 * 数据库操作：查询目的地为该city的离港航班信息
				 * 形参为目的地，返回类型为DepartureFlightInfo对象数组
				 * 
				 */
				//departureFlightInfo=searchDepartureFlightInfo3(city); 
				return departureFlightInfo;
			}
		}
		return departureFlightInfo;
	}
	//到港航班信息查询函数，输入三个参数依次为出发地、航班号、航空公司，返回一个ArrivalFlightInfo对象数组
	ArrivalFlightInfo[] searchArrivalFlightInfo(String city, String flightNumber, String airline)
	{
		ArrivalFlightInfo[] arrivalFlightInfo = null;
		if(flightNumber!=null){//查询该航班号的到港航班信息
			/*
			 * ArrivalFlightInfo[] searchArrivalFlightInfo0(String flightnumber); 
			 * 数据库操作：查询航班号为flightnumber的到港航班信息
			 * 形参为航班号，返回类型为ArrivalFlightInfo对象数组
			 * 
			 */
			//arrivalFlightInfo=searchArrivalFlightInfo0(flightnumber);
			return arrivalFlightInfo;
		}
		else{
			if(city!=null&&airline!=null){//查询出发地为该city，航空公司为该airline的到港航班信息
				/*
				 * ArrivalFlightInfo[] searchArrivalFlightInfo1(String city,String airline); 
				 * 数据库操作：查询 出发地为该city，航空公司为该airline的到港航班信息
				 * 形参为出发地和航空公司，返回类型为ArrivalFlightInfo对象数组
				 * 
				 */		
				//arrivalFlightInfo=searchArrivalFlightInfo1(city,airline);
				return arrivalFlightInfo;
			}
			if(city==null&&airline!=null){//查询航空公司为该airline的到港航班信息
				/*
				 * ArrivalFlightInfo[] searchArrivalFlightInfo2(String airline); 
				 * 数据库操作：查询航空公司为该airline的到港航班信息
				 * 形参为航空公司，返回类型为ArrivalFlightInfo对象数组
				 * 
				 */
				//arrivalFlightInfo=searchArrivalFlightInfo2(airline); 
				return arrivalFlightInfo;
			}
			if(city!=null&&airline==null){//查询出发地为该city的到港航班信息
				/*
				 * ArrivalFlightInfo[] searchArrivalFlightInfo3(String city); 
				 * 数据库操作：查询出发地为该city的到港航班信息
				 * 形参为出发地，返回类型为ArrivalFlightInfo对象数组
				 * 
				 */
				//arrivalFlightInfo=searchArrivalFlightInfo3(city); 
				return arrivalFlightInfo;
			}
		}
		return arrivalFlightInfo;
	}
	
	
}
