/* 离港航班信息类
 * flightCourse:航线信息
 * checkinCounter:值机柜台
 * boardingGate:登机门
 * time:时间
 * 
 */
package com.entity;

public class DepartureFlightInfo {
	FlightCourse flightCourse;
	String[] checkinCounter;
	String boardingGate;
	String time;
	public DepartureFlightInfo(FlightCourse flightCourse, String[] checkinCounter, String boardingGate, String time) {
		this.flightCourse = flightCourse;
		this.checkinCounter = checkinCounter;
		this.boardingGate = boardingGate;
		this.time = time;
	}
	public FlightCourse getFlightCourse() {
		return flightCourse;
	}
	public void setFlightCourse(FlightCourse flightCourse) {
		this.flightCourse = flightCourse;
	}
	public String[] getCheckinCounter() {
		return checkinCounter;
	}
	public void setCheckinCounter(String[] checkinCounter) {
		this.checkinCounter = checkinCounter;
	}
	public String getBoardingGate() {
		return boardingGate;
	}
	public void setBoardingGate(String boardingGate) {
		this.boardingGate = boardingGate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
