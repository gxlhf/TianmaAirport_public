/* 权限类
 * name:权限名称
 * remark:权限备注
 */
package com.entity;

public class Authority {
	String name;
	String remark;
	public Authority(String name,String remark) {
		this.name = name;
		this.remark = remark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
