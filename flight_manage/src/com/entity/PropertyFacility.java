/* 物业设施类
 * name:物业设施名称
 * location:物业设施位置
 * remark:备注
 * type:分类
 * phone:电话
 */
package com.entity;

public class PropertyFacility {
	String name;
	String location;
	String remark;
	String type;
	String phone;
	public PropertyFacility(String name, String location, String remark, String type, String phone) {
		this.name = name;
		this.location = location;
		this.remark = remark;
		this.type = type;
		this.phone = phone;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
