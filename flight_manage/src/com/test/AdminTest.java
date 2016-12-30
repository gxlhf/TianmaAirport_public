package com.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.management.relation.RoleInfoNotFoundException;
import javax.security.auth.callback.TextOutputCallback;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.dao.LoginDao;
import com.dao.loggingDao;
import com.entity.Admin;
import com.entity.AirportResource;
import com.entity.ArrivalFlightInfo;
import com.entity.DepartureFlightInfo;
import com.entity.FlightCourse;
import com.entity.News;
import com.entity.PropertyFacility;
import com.entity.Role;
import com.entity.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminTest {
	
	Admin admin;
	@Before
	public void setUp() throws Exception {
		admin=new Admin("9999", "testuser", 1, "111111111@qq.com", new Role("最高权限管理员"), "11111111111", "073122222222", "测试部", "管理员", "123123");
		admin.setRole(admin.searchRole("最高权限管理员"));
	}

	@Test
	public void testGetEmpno() {
		String empno=admin.getEmpno();
		assertNotNull(empno);
			
	}

	@Test
	public void testSetEmpno() {
		String preempno=admin.getEmpno();
		String toempno="9998";
		admin.setEmpno(toempno);
		assertEquals(admin.getEmpno(), toempno);
		admin.setEmpno(preempno);
	}

	@Test
	public void testGetName() {
		String name=admin.getName();
		assertNotNull(name);
	}

	@Test
	public void testSetName() {
		String prename=admin.getName();
		String toname="testuser1";
		admin.setName(toname);
		assertEquals(toname, admin.getName());
		admin.setName(prename);
	}

	@Test
	public void testGetSex() {
		int sex=admin.getSex();
		assertNotNull(sex);
	}

	@Test
	public void testSetSex() {
		int presex=admin.getSex();
		int tosex=(presex==1?0:1);
		admin.setSex(tosex);
		assertEquals(tosex, admin.getSex());
		admin.setSex(presex);
	}

	@Test
	public void testGetEmail() {
		String email=admin.getEmail();
		assertNotNull(email);
	}

	@Test
	public void testSetEmail() {
		String preemial=admin.getEmail();
		String toemail="test@abcd.com";
		admin.setEmail(toemail);
		assertEquals(toemail, admin.getEmail());
		admin.setEmail(preemial);
	}

	@Test
	public void testGetRole() {
		Role role=admin.getRole();
		assertNotNull(role);
	}

	@Test
	public void testSetRole() {
		Role prerole=admin.getRole();
		Role torole=new Role("testrole");
		admin.setRole(torole);
		assertEquals(torole, admin.getRole());
		admin.setRole(prerole);
	}

	@Test
	public void testGetMobile() {
		String mobile=admin.getMobile();
		assertNotNull(mobile);
	}

	@Test
	public void testSetMobile() {
		String premo=admin.getMobile();
		String tomo="testmobile";
		admin.setMobile(tomo);
		assertEquals(tomo, admin.getMobile());
		admin.setMobile(premo);
	}

	@Test
	public void testGetPhone() {
		String phone=admin.getPhone();
		assertNotNull(phone);
	}

	@Test
	public void testSetPhone() {
		String prephone=admin.getPhone();
		String tophone="testphone";
		admin.setPhone(tophone);
		assertEquals(tophone, admin.getPhone());
		admin.setPhone(prephone);
	}

	@Test
	public void testGetDepartment() {
		String de=admin.getDepartment();
		assertNotNull(de);
	}

	@Test
	public void testSetDepartment() {
		String prede=admin.getDepartment();
		String tode="test depa";
		admin.setDepartment(tode);
		assertEquals(tode, admin.getDepartment());
		admin.setDepartment(prede);
	}

	@Test
	public void testGetPosition() {
		String position=admin.getPosition();
		assertNotNull(position);
	}

	@Test
	public void testSetPosition() {
		String preposition=admin.getPosition();
		String toposition="test position";
		admin.setPosition(toposition);
		assertEquals(toposition, admin.getPosition());
		admin.setPosition(preposition);
	}

	@Test
	public void testGetPassword() {
		String pass=admin.getPassword();
		assertNotNull(pass);
	}

	@Test
	public void testSetPassword() {
		String prepassword=admin.getPassword();
		String topassword="test password";
		admin.setPassword(topassword);
		assertEquals(topassword, admin.getPassword());
		admin.setPassword(prepassword);
	}


	@Test
	public void test000SearchRole() {
		Role role=admin.searchRole(admin.getRole().getName());
		assertEquals(role.getName(), admin.getRole().getName());
	}

	@Test
	public void testReturnAllRole() {
		Role[] roles=admin.returnAllRole();
		assertNotNull(roles);
	}

	@Test
	public void test001AddRole() {
		Map <String,Boolean> map = new HashMap<>();
		map.put("新闻管理", true);
		Role role=new Role("testrole", "test role",map );
		assertEquals(1, admin.addRole(role));
	}

	@Test
	public void test002ModifyRole() {
		Role role=admin.searchRole("testrole");
		role.setDescription("role test002");
		assertEquals(1, admin.modifyRole(role));
	}

	@Test
	public void test003DeleteRole() {
		Role role=admin.searchRole("testrole");
		assertEquals(1, admin.deleteRole(role));
	}

	@Test
	public void test000SearchAdmin() {
		Admin[] admin1=admin.searchAdmin("9999", null, 1, null,null);
		assertNotNull(admin1);
	}

	@Test
	public void test001AddAdmin() {
		Admin admin1=new Admin("9998", "testuser1", 1, "111111111@qq.com", admin.searchRole("1243"), "11111111111", "073122222222", "测试部", "管理员", "123123");
		assertEquals(1,admin.addAdmin(admin1));
		
	}

	@Test
	public void test002ModifyAdmin() {
		Admin[] admin1=admin.searchAdmin("9998", null, 1, null, null);
		admin1[0].setPhone("12312312312");
		assertEquals(1, admin.modifyAdmin(admin1[0]));
		
	}

	@Test
	public void test003DeleteAdmin() {
		Admin[] admin1=admin.searchAdmin("9998", null, 1, null, null);
		assertEquals(1, admin.deleteAdmin(admin1[0]));
	}

	@Test
	public void test001AddAirportResource() {
		AirportResource airportResource=new AirportResource("test", "test", "test", "登机门");
		assertEquals(1, admin.addAirportResource(airportResource));
	}

	@Test
	public void test002ModifyAirportResource() {
		AirportResource[] airportResources=admin.searchAirportResource("test", "登机门");
		airportResources[0].setRemark("test1");
		assertEquals(1, admin.modifyAirportResource(airportResources[0]));
	}

	@Test
	public void test003DeleteAirportResource() {
		AirportResource[] airportResources=admin.searchAirportResource("test", "登机门");
		assertEquals(1, admin.deleteAirportResource(airportResources[0]));
	}

	@Test
	public void test001AddPropertyFacility() {
		PropertyFacility propertyFacility=new PropertyFacility("test", "test", "test", "test", "test");
		assertEquals(1, admin.addPropertyFacility(propertyFacility));
	}

	@Test
	public void test002ModifyPropertyFacility() {
		PropertyFacility[] propertyFacilities=admin.searchPropertyFacility("test","",1);
		propertyFacilities[0].setLocation("test1");
		assertEquals(1, admin.modifyPropertyFacility(propertyFacilities[0]));
	}

	@Test
	public void test003DeletePropertyFacility() {
		PropertyFacility[] propertyFacilities=admin.searchPropertyFacility("test","",1);
		assertEquals(1, admin.deletePropertyFacility(propertyFacilities[0]));
	}
/*
	@Test
	public void test001AddDepartureFlightInfo() {
		FlightCourse fli=new FlightCourse(true, true, "TS9999", "test", "test", "test", "test");
		String[] couter={"值机柜台01","值机柜台02"};
		DepartureFlightInfo departureFlightInfo=new DepartureFlightInfo(fli, couter, "登机门23", "2014-10-10 12:40:10");
		assertEquals(1, admin.addDepartureFlightInfo(departureFlightInfo));
	}

	@Test
	public void test002ModifyDepartureFlightInfo() {
		DepartureFlightInfo[] departureFlightInfo=admin.searchDepartureFlightInfo(null, "TS9999", null, null);
		System.out.println(departureFlightInfo[0].getCheckinCounter());
		departureFlightInfo[0].setTime("2014-10-20 12:10:10");
		assertEquals(1, admin.modifyDepartureFlightInfo(departureFlightInfo[0]));
	}

	@Test
	public void test003DeleteDepartureFlightInfo() {
		DepartureFlightInfo[] departureFlightInfos=admin.searchDepartureFlightInfo(null, "TS9999", null, null);
		assertEquals(1, admin.deleteDepartureFlightInfo(departureFlightInfos[0]));
	}

	@Test
	public void test001AddArrivalFlightInfo() {
		FlightCourse fli=new FlightCourse(true, true, "TS9998", "test", "test", "test", "test");
		ArrivalFlightInfo arrivalFlightInfo=new ArrivalFlightInfo(fli, "行李转盘01", "2014-10-10 10:10:10");
		assertEquals(1, admin.addArrivalFlightInfo(arrivalFlightInfo));
	}

	@Test
	public void test002ModifyArrivalFlightInfo() {
		ArrivalFlightInfo[] arrivalFlightInfos=admin.searchArrivalFlightInfo(null, "TS9998", null, null);
		arrivalFlightInfos[0].setTime("2014-10-10 10:10:12");
		assertEquals(1, admin.modifyArrivalFlightInfo(arrivalFlightInfos[0]));
	}

	@Test
	public void test003DeleteArrivalFlightInfo() {
		ArrivalFlightInfo[] arrivalFlightInfos=admin.searchArrivalFlightInfo(null, "TS9998", null, null);
		assertEquals(1, admin.deleteArrivalFlightInfo(arrivalFlightInfos[0]));
	}
*/
	@Test
	public void test001AddNews() {
		News news=new News("test", "2014-10-20", "test", "航班信息", "", "1003");
		assertEquals(1, admin.addNews(news));
	}

	@Test
	public void test002ModifyNews() {
		News[] news=admin.searchNews("test", "2014-10-20");
		news[0].setContent("test1");
		assertEquals(1, admin.modifyNews(news[0]));
	}

	@Test
	public void test003DeleteNews() {
		News[] news=admin.searchNews("test", "2014-10-20");
		assertEquals(1, admin.deleteNews(news[0]));
	}

	@Test
	public void testReturnAllPosition() {
		String[] po=admin.returnAllPosition();
		assertNotNull(po);
	}

	@Test
	public void testReturnAllDepartment() {
		String[] de=admin.returnAllDepartment();
		assertNotNull(de);
	}

}
