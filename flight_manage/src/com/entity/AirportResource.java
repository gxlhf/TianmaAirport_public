/* 机场资源类
 * name:机场资源名称
 * location:机场资源位置
 * remark:备注
 * type:分类
 * 
 */
package com.entity;

public class AirportResource {
	String name;
	String location;
	String remark;
	String type;
	public AirportResource(String name, String location, String remark, String type) {
		this.name = name;
		this.location = location;
		this.remark = remark;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
