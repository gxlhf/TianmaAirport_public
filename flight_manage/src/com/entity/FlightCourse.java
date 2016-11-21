/* 航线信息类
 * internationalOrLocal:国际航班/国内航班，国际航班为true，国内航班为false
 * arrivalOrDeparture:到港航班/离港航班，到港航班为true，离港航班为false
 * flightNumber:航班号
 * airline:航空公司
 * from:始发地
 * to:到达地
 * stop:经停地，数组
 */
package com.entity;

public class FlightCourse {
	boolean internationalOrLocal;
	boolean arrivalOrDeparture;
	String flightNumber;
	String airline;
	String from;
	String to;
	String[] stop;
}
