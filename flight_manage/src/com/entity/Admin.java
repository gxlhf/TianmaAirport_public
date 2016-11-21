/* 管理员类
 * empno:员工号
 * name:姓名
 * sex:性别，1代表男，0代表女
 * email:电子邮箱 
 * role:角色
 * mobile:手机
 * phone:电话
 * department:部门
 * position:职位
 */
package com.entity;

public class Admin extends User {
	String empno;
	String name;
	int sex;
	String email;
	Role role;
	String mobile;
	String phone;
	String department;
	String position;
}
