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
			return departureFlightInfo;
		}
		else{
			if(city!=null&&airline!=null){//查询目的地为该city，航空公司为该airline的离港航班信息
				return departureFlightInfo;
			}
			if(city==null&&airline!=null){//查询航空公司为该airline的离港航班信息
				return departureFlightInfo;
			}
			if(city!=null&&airline==null){//查询目的地为该city的离港航班信息
				return departureFlightInfo;
			}
		}
		return departureFlightInfo;
	}
	ArrivalFlightInfo[] searchArrivalFlightInfo(String city, String flightNumber, String airline)
	{
		ArrivalFlightInfo[] arrivalFlightInfos = null;
		return arrivalFlightInfos;
	}
}
