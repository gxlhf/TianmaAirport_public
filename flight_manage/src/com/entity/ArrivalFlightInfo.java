/* 到港航班信息类
 * flightCourse:航线信息
 * luggageCarousel:行李转盘
 * time:时间
 */
package com.entity;

public class ArrivalFlightInfo {
	FlightCourse flightCourse;
	String luggageCarousel;
	String time;
	public ArrivalFlightInfo(FlightCourse flightCourse, String luggageCarousel, String time) {
		this.flightCourse = flightCourse;
		this.luggageCarousel = luggageCarousel;
		this.time = time;
	}
	public FlightCourse getFlightCourse() {
		return flightCourse;
	}
	public void setFlightCourse(FlightCourse flightCourse) {
		this.flightCourse = flightCourse;
	}
	public String getLuggageCarousel() {
		return luggageCarousel;
	}
	public void setLuggageCarousel(String luggageCarousel) {
		this.luggageCarousel = luggageCarousel;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
