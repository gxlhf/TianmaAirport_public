/* 航线信息类
 * internationalOrLocal:国际航班/国内航班，国际航班为true，国内航班为false
 * arrivalOrDeparture:到港航班/离港航班，到港航班为true，离港航班为false
 * flightNumber:航班号
 * airline:航空公司
 * from:始发地
 * to:到达地
 * stop:经停地
 */
package com.entity;

public class FlightCourse {
	boolean internationalOrLocal;
	boolean arrivalOrDeparture;
	String flightNumber;
	String airline;
	String from;
	String to;
	String stop;
	public FlightCourse(boolean internationalOrLocal, boolean arrivalOrDeparture, String flightNumber, String airline,
			String from, String to, String stop) {
		this.internationalOrLocal = internationalOrLocal;
		this.arrivalOrDeparture = arrivalOrDeparture;
		this.flightNumber = flightNumber;
		this.airline = airline;
		this.from = from;
		this.to = to;
		this.stop = stop;
	}
	public boolean isInternationalOrLocal() {
		return internationalOrLocal;
	}
	public void setInternationalOrLocal(boolean internationalOrLocal) {
		this.internationalOrLocal = internationalOrLocal;
	}
	public boolean isArrivalOrDeparture() {
		return arrivalOrDeparture;
	}
	public void setArrivalOrDeparture(boolean arrivalOrDeparture) {
		this.arrivalOrDeparture = arrivalOrDeparture;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getStop() {
		return stop;
	}
	public void setStop(String stop) {
		this.stop = stop;
	}
	
}
