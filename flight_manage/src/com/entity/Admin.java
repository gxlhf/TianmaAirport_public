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
		boolean result = false;
		switch(op)
		{
			case "searchRole":
			case "addRole":
			case "modifyRole":
			case "deleteRole":result = role.authorityMap.get("角色管理");
			case "searchAdmin":
			case "addAdmin":
			case "modifyAdmin":
			case "deleteAdmin":result = role.authorityMap.get("用户管理");
			case "addAirportResource":
			case "modifyAirportResource":
			case "deleteAirportResource":
			case "addPropertyFacility":
			case "modifyPropertyFacility":
			case "deletePropertyFacility":result = role.authorityMap.get("机场设施管理");
			case "addDepartureFlightInfo":
			case "modifyDepartureFlightInfo":
			case "deleteDepartureFlightInfo":
			case "addArrivalFlightInfo":
			case "modifyArrivalFlightInfo":
			case "deleteArrivalFlightInfo":result = role.authorityMap.get("航班信息管理");
			case "addNews":
			case "modifyNews":
			case "deleteNews":result = role.authorityMap.get("新闻管理");
			default:result = false;
		}
		return result;
	}
	
	//查询角色函数，输入参数为角色名称，返回一个Role对象数组；若无权限操作，则返回含有一个Role对象的对象数组，其name成员值为-1；
	Role[] searchRole(String name)
	{
		Role[] role = null;
		if(!authorityValidate("searchRole")){  //无权限操作
			role = new Role[1];
			role[0].name = "-1";
		}
		/*
		 * Role[] searchR(String name);
		 * 数据库操作：查询角色名称为该name的角色信息
		 * 形参为角色名称，返回类型为Role对象数组
		 */
		//role=searchR(name);		
		return role;
	}
	
	//新增角色函数：输入参数为角色对象；返回整数值，成功为1，失败为0，无权限操作为-1
	int addRole(Role role){
		if(!authorityValidate("addRole"))
			return -1;
		/*
		 * boolean add(Role role);
		 * 数据库操作：新增角色，形参为角色对象，返回值为布尔值
		 */
		//return add(role);
		return 0;
	}
	
	//修改角色函数：输入参数为角色对象；返回整数值，成功为1，失败为0，无权限操作为-1
	int modifyRole(Role role){
		if(!authorityValidate("modifyRole"))
			return -1;
		/*
		 * boolean add(Role role);
		 * 数据库操作：修改角色，形参为角色对象，返回值为布尔值
		 */
		//return add(role);
		return 0;
	}
	
	//删除角色函数：输入参数为角色对象；返回整数值，成功为1，失败为0，无权限操作为-1
	int deleteRole(Role role){
		if(!authorityValidate("deleteRole"))
			return -1;
		/*
		 * boolean add(Role role);
		 * 数据库操作：删除角色，形参为角色对象，返回值为布尔值
		 */
		//return add(role);
		return 0;
	}

	//查询管理员函数，输入参数为员工号、姓名、性别、职位、角色，返回一个Admin对象数组;若无权限操作，则返回含有一个Admin对象的对象数组，其empno成员值为-1；
	Admin[] searchAdmin(String empno,String name,int sex,String position,String roleName)
	{
		Admin[] admin = null;
		if(!authorityValidate("searchAdmin")){
			admin = new Admin[1];
			admin[0].empno = "-1";
		}
		if(empno!=""){
			/*
			 * Admin[] searchAdmin0(String empno);
			 * 数据库操作：查询员工号为该empno的管理员信息
			 * 形参为员工号，返回类型为Admin对象数组
			 */
			//return searchAdmin0(empno);
		}
		else{
			/*
			 * Admin[] searchAdmin1(String name,int sex,String position,String roleName);
			 * 数据库操作：查询姓名为name，性别为sex，职位为position，角色为roleName的管理员信息，若其中有形参值为空字符串，则表示无该条件限制
			 * 形参为姓名，性别，职位，角色，返回类型为Admin对象数组
			 */
			//return searchAdmin1(empno);
		}
		
		return admin;
	}
	
	//新增管理员函数：输入参数为管理员对象；返回整数值，成功为1，失败为0，无权限操作为-1
	int addAdmin(Admin admin){
		if(!authorityValidate("addAdmin"))
			return -1;
		/*
		 * boolean add(Admin admin);
		 * 数据库操作：新增管理员，形参为管理员对象，返回值为布尔值
		 */
		//return add(admin);
		return 0;
	}
	
	//修改管理员函数：输入参数为管理员对象；返回整数值，成功为1，失败为0，无权限操作为-1
	int modifyAdmin(Admin admin){
		if(!authorityValidate("modifyAdmin"))
			return -1;
		/*
		 * boolean modify(Admin admin);
		 * 数据库操作：修改管理员，形参为管理员对象，返回值为布尔值
		 */
		//return modify(admin);
		return 0;
	}
	
	//删除管理员函数：输入参数为管理员对象；返回整数值，成功为1，失败为0，无权限操作为-1
	int deleteAdmin(Admin admin){
		if(!authorityValidate("deleteAdmin"))
			return -1;
		/*
		 * boolean delete(Admin admin);
		 * 数据库操作：删除管理员，形参为管理员对象，返回值为布尔值
		 */
		//return delete(admin);
		return 0;
	}
	
	//新增机场资源函数：输入参数为机场资源对象；返回整数值，成功为1，失败为0，无权限操作为-1
	int addAirportResource(AirportResource airportResource){	
		/*
		 * boolean add(AirportResource airportResource);
		 * 数据库操作：新增机场资源，形参为机场资源对象，返回值为布尔值
		 */
		//return add(airportResource);
		return 0;
	}

	//修改机场资源函数：输入参数为机场资源对象；返回整数值，成功为1，失败为0，无权限操作为-1
	int modifyAirportResource(AirportResource airportResource){
		if(!authorityValidate("modifyAirportResource"))
			return -1;
		/*
		 * boolean modify(AirportResource airportResource);
		 * 数据库操作：修改机场资源，形参为机场资源对象，返回值为布尔值
		 */
		//return modify(airportResource);
		return 0;
	}

	//删除机场资源函数：输入参数为机场资源对象；返回整数值，成功为1，失败为0，无权限操作为-1
	int deleteAirportResource(AirportResource airportResource){
		if(!authorityValidate("deleteAirportResource"))
			return -1;
		/*
		 * boolean delete(AirportResource airportResource);
		 * 数据库操作：删除机场资源，形参为机场资源对象，返回值为布尔值
		 */
		//return delete(airportResource);
		return 0;
	}
	
	//新增物业设施函数：输入参数为物业设施对象；返回整数值，成功为1，失败为0，无权限操作为-1
	int addPropertyFacility(PropertyFacility propertyFacility){
		if(!authorityValidate("addPropertyFacility"))
			return -1;
		/*
		 * boolean add(PropertyFacility propertyFacility);
		 * 数据库操作：新增物业设施，形参为物业设施对象，返回值为布尔值
		 */
		//return add(propertyFacility);
		return 0;
	}

	//修改物业设施函数：输入参数为物业设施对象；返回整数值，成功为1，失败为0，无权限操作为-1
	int modifyPropertyFacility(PropertyFacility propertyFacility){
		if(!authorityValidate("modifyPropertyFacility"))
			return -1;
		/*
		 * boolean modify(PropertyFacility propertyFacility);
		 * 数据库操作：修改物业设施，形参为物业设施对象，返回值为布尔值
		 */
		//return modify(propertyFacility);
		return 0;
	}

	//删除物业设施函数：输入参数为物业设施对象；返回整数值，成功为1，失败为0，无权限操作为-1
	int deletePropertyFacility(PropertyFacility propertyFacility){
		if(!authorityValidate("deletePropertyFacility"))
			return -1;
		/*
		 * boolean delete(PropertyFacility propertyFacility);
		 * 数据库操作：删除物业设施，形参为物业设施对象，返回值为布尔值
		 */
		//return delete(propertyFacility);
		return 0;
	}

	//新增离港航班信息函数：输入参数为离港航班信息对象；返回整数值，成功为1，失败为0，无权限操作为-1
	int addDepartureFlightInfo(DepartureFlightInfo departureFlightInfo){
		if(!authorityValidate("addDepartureFlightInfo"))
			return -1;
		/*
		 * boolean add(DepartureFlightInfo departureFlightInfo);
		 * 数据库操作：新增离港航班信息，形参为离港航班信息对象，返回值为布尔值
		 */
		//return add(DepartureFlightInfo departureFlightInfo);
		return 0;
	}
	
	//修改离港航班信息函数：输入参数为离港航班信息对象；返回整数值，成功为1，失败为0，无权限操作为-1
	int modifyDepartureFlightInfo(DepartureFlightInfo departureFlightInfo){
		if(!authorityValidate("modifyDepartureFlightInfo"))
			return -1;
		/*
		 * boolean modify(DepartureFlightInfo departureFlightInfo);
		 * 数据库操作：修改离港航班信息，形参为离港航班信息对象，返回值为布尔值
		 */
		//return modify(DepartureFlightInfo departureFlightInfo);
		return 0;
	}
	
	//删除离港航班信息函数：输入参数为离港航班信息对象；返回整数值，成功为1，失败为0，无权限操作为-1
	int deleteDepartureFlightInfo(DepartureFlightInfo departureFlightInfo){
		if(!authorityValidate("deleteDepartureFlightInfo"))
			return -1;
		/*
		 * boolean delete(DepartureFlightInfo departureFlightInfo);
		 * 数据库操作：删除离港航班信息，形参为离港航班信息对象，返回值为布尔值
		 */
		//return delete(DepartureFlightInfo departureFlightInfo);
		return 0;
	}
	
	//新增到港航班信息函数：输入参数为到港航班信息对象；返回整数值，成功为1，失败为0，无权限操作为-1
	int addArrivalFlightInfo(ArrivalFlightInfo arrivalFlightInfo){
		if(!authorityValidate("addArrivalFlightInfo"))
			return -1;
		/*
		 * boolean add(ArrivalFlightInfo arrivalFlightInfo);
		 * 数据库操作：新增到港航班信息，形参为到港航班信息对象，返回值为布尔值
		 */
		//return add(ArrivalFlightInfo arrivalFlightInfo);
		return 0;
	}
	
	//修改到港航班信息函数：输入参数为到港航班信息对象；返回整数值，成功为1，失败为0，无权限操作为-1
	int modifyArrivalFlightInfo(ArrivalFlightInfo arrivalFlightInfo){
		if(!authorityValidate("modifyArrivalFlightInfo"))
			return -1;
		/*
		 * boolean modify(ArrivalFlightInfo arrivalFlightInfo);
		 * 数据库操作：修改到港航班信息，形参为到港航班信息对象，返回值为布尔值
		 */
		//return modify(ArrivalFlightInfo arrivalFlightInfo);
		return 0;
	}
	
	//删除到港航班信息函数：输入参数为到港航班信息对象；返回整数值，成功为1，失败为0，无权限操作为-1
	int deleteArrivalFlightInfo(ArrivalFlightInfo arrivalFlightInfo){
		if(!authorityValidate("deleteArrivalFlightInfo"))
			return -1;
		/*
		 * boolean delete(ArrivalFlightInfo arrivalFlightInfo);
		 * 数据库操作：删除到港航班信息，形参为到港航班信息对象，返回值为布尔值
		 */
		//return delete(ArrivalFlightInfo arrivalFlightInfo);
		return 0;
	}
	
	//新增新闻函数：输入参数为新闻对象；返回整数值，成功为1，失败为0，无权限操作为-1
	int addNews(News news){
		if(!authorityValidate("addNews"))
			return -1;
		/*
		 * boolean add(News news);
		 * 数据库操作：新增新闻，形参为新闻对象，返回值为布尔值
		 */
		//return add(News news);
		return 0;
	}

	//修改新闻函数：输入参数为新闻对象；返回整数值，成功为1，失败为0，无权限操作为-1
	int modifyNews(News news){
		if(!authorityValidate("modifyNews"))
			return -1;
		/*
		 * boolean modify(News news);
		 * 数据库操作：修改新闻，形参为新闻对象，返回值为布尔值
		 */
		//return modify(News news);
		return 0;
	}

	//删除新闻函数：输入参数为新闻对象；返回整数值，成功为1，失败为0，无权限操作为-1
	int deleteNews(News news){
		if(!authorityValidate("deleteNews"))
			return -1;
		/*
		 * boolean delete(News news);
		 * 数据库操作：删除新闻，形参为新闻对象，返回值为布尔值
		 */
		//return delete(News news);
		return 0;
	}
	
}
