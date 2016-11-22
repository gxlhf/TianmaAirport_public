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
	Admin(String empno, String name, int sex, String email, Role role, String mobile, String phone,
			String department, String position) {
		this.empno = empno;
		this.name = name;
		this.sex = sex;
		this.email = email;
		this.role = role;
		this.mobile = mobile;
		this.phone = phone;
		this.department = department;
		this.position = position;
	}
	//权限验证函数，输入形参为操作函数名；返回布尔值，若该管理员有该操作的权限则返回true，否则返回false
	private boolean authorityValidate(String op)
	{
		return false;
	}

	//新增机场资源函数：输入参数为机场资源对象；返回布尔值，成功为true，失败为false
	boolean addAirportResource(AirportResource airportResource){	
		/*
		 * boolean add(AirportResource airportResource);
		 * 数据库操作：新增机场资源，形参为机场资源对象，返回值为布尔值
		 */
		//return add(airportResource);
		return false;
	}

	//修改机场资源函数：输入参数为机场资源对象；返回布尔值，成功为true，失败为false
	boolean modifyAirportResource(AirportResource airportResource){
		/*
		 * boolean modify(AirportResource airportResource);
		 * 数据库操作：修改机场资源，形参为机场资源对象，返回值为布尔值
		 */
		//return modify(airportResource);
		return false;
	}

	//删除机场资源函数：输入参数为机场资源对象；返回布尔值，成功为true，失败为false
	boolean deleteAirportResource(AirportResource airportResource){
		/*
		 * boolean delete(AirportResource airportResource);
		 * 数据库操作：删除机场资源，形参为机场资源对象，返回值为布尔值
		 */
		//return delete(airportResource);
		return false;
	}
	
	//新增物业设施函数：输入参数为物业设施对象；返回布尔值，成功为true，失败为false
	boolean addPropertyFacility(PropertyFacility propertyFacility){
		/*
		 * boolean add(PropertyFacility propertyFacility);
		 * 数据库操作：新增物业设施，形参为物业设施对象，返回值为布尔值
		 */
		//return add(propertyFacility);
		return false;
	}

	//修改物业设施函数：输入参数为物业设施对象；返回布尔值，成功为true，失败为false
	boolean modifyPropertyFacility(PropertyFacility propertyFacility){
		/*
		 * boolean modify(PropertyFacility propertyFacility);
		 * 数据库操作：修改物业设施，形参为物业设施对象，返回值为布尔值
		 */
		//return modify(propertyFacility);
		return false;
	}

	//删除物业设施函数：输入参数为物业设施对象；返回布尔值，成功为true，失败为false
	boolean deletePropertyFacility(PropertyFacility propertyFacility){
		/*
		 * boolean delete(PropertyFacility propertyFacility);
		 * 数据库操作：删除物业设施，形参为物业设施对象，返回值为布尔值
		 */
		//return delete(propertyFacility);
		return false;
	}
	//新增新闻函数：输入参数为新闻对象；返回布尔值，成功为1，失败为0
	boolean addNews(News news){
		/*
		 * boolean add(News news);
		 * 数据库操作：新增新闻，形参为新闻对象，返回值为布尔值
		 */
		//return add(News news);
		return false;
	}

	//修改新闻函数：输入参数为新闻对象；返回布尔值，成功为1，失败为0
	boolean modifyNews(News news){
		/*
		 * boolean modify(News news);
		 * 数据库操作：修改新闻，形参为新闻对象，返回值为布尔值
		 */
		//return modify(News news);
		return false;
	}

	//删除新闻函数：输入参数为新闻对象；返回布尔值，成功为1，失败为0
	boolean deleteNews(News news){
		/*
		 * boolean delete(News news);
		 * 数据库操作：删除新闻，形参为新闻对象，返回值为布尔值
		 */
		//return delete(News news);
		return false;
	}
}
