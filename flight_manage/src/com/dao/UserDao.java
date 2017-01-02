package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.entity.AirportResource;
import com.entity.ArrivalFlightInfo;
import com.entity.DepartureFlightInfo;
import com.entity.FlightCourse;
import com.entity.News;
import com.entity.PropertyFacility;

public class UserDao {
    static String sql = null;
    static db_connection db1 = null;
    static ResultSet ret = null;
    public DepartureFlightInfo[] returnAllInternationalDepartureFlightInfo()
    {
    	DepartureFlightInfo[] allInternationalDepartureFlightInfo = null;
		//返回国际离港航班信息
            /*
             * DepartureFlightInfo[] returnAllInternationalDepartureFlightInfo();
             * 数据库操作
             * 返回类型为DepartureFlightInfo对象数组
             * 
             */
                int i=-1;
				sql = "SELECT flight_off.Flight_No2,flight_off.InternationalOrLocal,flight_off.Starting_station,flight_off.Destination," +
						"flight_off.Staging_post,flight_off.Airline,bc_allocation.Bname,bc_allocation.Cname,bc_allocation.Time " +
						"FROM flight_off INNER JOIN bc_allocation ON flight_off.Flight_No2 = bc_allocation.Flight_No2 " +
						"WHERE flight_off.InternationalOrLocal=true ORDER BY Time,Flight_No2";//SQL语句  
		        db1 = new db_connection(sql);//创建db_connection对象  		       
		        boolean internationalOrLocal;
		        String from=null;
		        String to=null;
		        String stop=null;
		        String airline=null;
		        String boardingGate=null;
		        String[] checkinCounter=null;
		        String flightnumber=null;
		        String time=null;
		        try {  
		            ret = db1.pst.executeQuery();//执行语句，得到结果集  
		            ret.last();
		            int rowNumber=ret.getRow();
		            ret.beforeFirst();
		            allInternationalDepartureFlightInfo = new DepartureFlightInfo[rowNumber];
		            int j=0;
		            while (ret.next()) { 
		            	if(ret.getString("Flight_No2").equals(flightnumber) && ret.getString("Time").equals(time))
                        {  
         	        	   j++;
         	            }else
         	               {
	            	           checkinCounter=new String[10];
         	        	       i++;
         	        	       j=0;
         	               }
		            	           flightnumber=ret.getString("Flight_No2");
		                           internationalOrLocal=ret.getBoolean("InternationalOrLocal");
		            	           from=ret.getString("Starting_station");
		            	           to=ret.getString("Destination");
		            	           stop=ret.getString("Staging_post");
		            	           airline=ret.getString("Airline");
		            	           boardingGate=ret.getString("Bname");
		            	           checkinCounter[j]=ret.getString("Cname");
		            	           time=ret.getString("Time");
		            	           FlightCourse flightCourse=new FlightCourse(internationalOrLocal,false,flightnumber,airline,from,to,stop);
		            	           allInternationalDepartureFlightInfo[i]=new DepartureFlightInfo(flightCourse,checkinCounter,boardingGate,time);		            	           
		            }
		            ret.close();  		          
		        } catch (SQLException e) {  
		            e.printStackTrace();  
		        } finally{
		        	  db1.close();//关闭连接  
		        }
		return allInternationalDepartureFlightInfo;
    	
    }
    public DepartureFlightInfo[] returnAllLocalDepartureFlightInfo()
    {
       	DepartureFlightInfo[] allLocalDepartureFlightInfo = null;
		//返回所有国内离港航班信息
            /*
             * DepartureFlightInfo[] returnAllLocalDepartureFlightInfo();
             * 数据库操作：
             * 返回类型为DepartureFlightInfo对象数组
             * 
             */
                int i=-1;
				sql = "SELECT flight_off.Flight_No2,flight_off.InternationalOrLocal,flight_off.Starting_station,flight_off.Destination," +
						"flight_off.Staging_post,flight_off.Airline,bc_allocation.Bname,bc_allocation.Cname,bc_allocation.Time " +
						"FROM flight_off INNER JOIN bc_allocation ON flight_off.Flight_No2 = bc_allocation.Flight_No2 " +
						"WHERE flight_off.InternationalOrLocal=false ORDER BY Time,Flight_No2";//SQL语句  
		        db1 = new db_connection(sql);//创建db_connection对象  		       
		        boolean internationalOrLocal;
		        String from=null;
		        String to=null;
		        String stop=null;
		        String airline=null;
		        String boardingGate=null;
		        String[] checkinCounter=null;
		        String flightnumber=null;
		        String time=null;
		        try {  
		            ret = db1.pst.executeQuery();//执行语句，得到结果集  
		            ret.last();
		            int rowNumber=ret.getRow();
		            ret.beforeFirst();
		            allLocalDepartureFlightInfo = new DepartureFlightInfo[rowNumber];
		            int j=0;
		            while (ret.next()) { 
		            	if(ret.getString("Flight_No2").equals(flightnumber) && ret.getString("Time").equals(time))
                        {  
         	        	   j++;
         	            }else
         	               {
	            	           checkinCounter=new String[10];
         	        	       i++;
         	        	       j=0;
         	               }
		            	flightnumber=ret.getString("Flight_No2");
		                internationalOrLocal=ret.getBoolean("InternationalOrLocal");
		            	from=ret.getString("Starting_station");
		            	to=ret.getString("Destination");
		            	stop=ret.getString("Staging_post");
		            	airline=ret.getString("Airline");
		            	boardingGate=ret.getString("Bname");
		            	checkinCounter[j]=ret.getString("Cname");
		            	time=ret.getString("Time");
		            	FlightCourse flightCourse=new FlightCourse(internationalOrLocal,false,flightnumber,airline,from,to,stop);
		            	allLocalDepartureFlightInfo[i]=new DepartureFlightInfo(flightCourse,checkinCounter,boardingGate,time);
		            }
		            ret.close();  
		          
		        } catch (SQLException e) {  
		            e.printStackTrace();  
		        } finally{
		        	  db1.close();//关闭连接  
		        }
		return allLocalDepartureFlightInfo;
    	
    }
    public ArrivalFlightInfo[] returnAllInternationalArrivalFlightInfo()
    {//返回国际到港航班信息
        /*
         * ArrivalFlightInfo[] returnAllInternationalArrivalFlightInfo();
         * 数据库操作
         * 返回类型为ArrivalFlightInfo对象数组
         * 
         */
    	ArrivalFlightInfo[] allInternationalArrivalFlightInfo=null;
    	 int i=0;
         sql = "SELECT flight_arrival.Flight_No,flight_arrival.InternationalOrLocal,flight_arrival.Starting_station,flight_arrival.Destination," +
			"flight_arrival.Staging_post,flight_arrival.Airline,lc_allocation.Time,lc_allocation.Lname FROM (flight_arrival " +
			"INNER JOIN lc_allocation ON flight_arrival.Flight_No = lc_allocation.Flight_No) " +
			"WHERE flight_arrival.InternationalOrLocal=true ";//SQL语句  
	        db1 = new db_connection(sql);//创建db_connection对象  
	        boolean internationalOrLocal;
	        String from=null;
	        String to=null;
	        String stop=null;
	        String luggageCarousel=null;
	        String time=null;
	        String flightnumber=null;
	        String airline=null;
	        try {  
	            ret = db1.pst.executeQuery();//执行语句，得到结果集  
	            ret.last();
	            int rowNumber=ret.getRow();
	            ret.beforeFirst();
	            allInternationalArrivalFlightInfo = new ArrivalFlightInfo[rowNumber];
	            while (ret.next()) { 
	            	flightnumber = ret.getString("Flight_No");
	                internationalOrLocal=ret.getBoolean("InternationalOrLocal");
	            	from=ret.getString("Starting_station");
	            	to=ret.getString("Destination");
	            	stop=ret.getString("Staging_post");
	            	airline=ret.getString("Airline");
	            	luggageCarousel=ret.getString("Lname");
	            	time=ret.getString("Time");
	            	FlightCourse flightCourse=new FlightCourse(internationalOrLocal,true,flightnumber,airline,from,to,stop);
	            	allInternationalArrivalFlightInfo[i]=new ArrivalFlightInfo(flightCourse,luggageCarousel,time);
	                i++;
	            }
	            ret.close();             
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }finally{
	        	 db1.close();//关闭连接  
	        }
	        return allInternationalArrivalFlightInfo;
    	
    }
    public ArrivalFlightInfo[] returnAllLocalArrivalFlightInfo()
    {//返回国内到港航班信息
        /*
         * ArrivalFlightInfo[] returnAllLocalArrivalFlightInfo();
         * 数据库操作
         * 返回类型为ArrivalFlightInfo对象数组
         * 
         */
    	ArrivalFlightInfo[] allLocalArrivalFlightInfo=null;
   	    int i=0;
        sql = "SELECT flight_arrival.Flight_No,flight_arrival.InternationalOrLocal,flight_arrival.Starting_station,flight_arrival.Destination," +
			"flight_arrival.Staging_post,flight_arrival.Airline,lc_allocation.Time,lc_allocation.Lname FROM (flight_arrival " +
			"INNER JOIN lc_allocation ON flight_arrival.Flight_No = lc_allocation.Flight_No) " +
			"WHERE flight_arrival.InternationalOrLocal=false";//SQL语句  
	        db1 = new db_connection(sql);//创建db_connection对象  
	        boolean internationalOrLocal;
	        String from=null;
	        String to=null;
	        String stop=null;
	        String luggageCarousel=null;
	        String time=null;
	        String flightnumber=null;
	        String airline=null;
	        try {  
	            ret = db1.pst.executeQuery();//执行语句，得到结果集  
	            ret.last();
	            int rowNumber=ret.getRow();
	            ret.beforeFirst();
	            allLocalArrivalFlightInfo = new ArrivalFlightInfo[rowNumber];
	            while (ret.next()) { 
	            	flightnumber = ret.getString("Flight_No");
	                internationalOrLocal=ret.getBoolean("InternationalOrLocal");
	            	from=ret.getString("Starting_station");
	            	to=ret.getString("Destination");
	            	stop=ret.getString("Staging_post");
	            	airline=ret.getString("Airline");
	            	luggageCarousel=ret.getString("Lname");
	            	time=ret.getString("Time");
	            	FlightCourse flightCourse=new FlightCourse(internationalOrLocal,true,flightnumber,airline,from,to,stop);
	            	allLocalArrivalFlightInfo[i]=new ArrivalFlightInfo(flightCourse,luggageCarousel,time);
	                i++;
	            }
	            ret.close();             
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }finally{
	        	 db1.close();//关闭连接  
	        }
	        return allLocalArrivalFlightInfo;
    	
    }
    public DepartureFlightInfo[] searchDepartureFlightInfo0(String flightnumber)
    {
		DepartureFlightInfo[] departureFlightInfo = null;
		//查询该航班号的离港航班信息
            /*
             * DepartureFlightInfo[] searchDepartureFlightInfo0(String flightnumber);
             * 数据库操作：查询航班号为flightnumber的离港航班信息
             * 形参为航班号，返回类型为DepartureFlightInfo对象数组
             * 
             */
            //departureFlightInfo=searchDepartureFlightInfo0(flightnumber); 
                int i=-1;
				sql = "SELECT flight_off.Flight_No2,flight_off.InternationalOrLocal,flight_off.Starting_station,flight_off.Destination," +
						"flight_off.Staging_post,flight_off.Airline,bc_allocation.Bname,bc_allocation.Cname,bc_allocation.Time " +
						"FROM flight_off INNER JOIN bc_allocation ON flight_off.Flight_No2 = bc_allocation.Flight_No2 " +
						"WHERE flight_off.Flight_No2 = ? ORDER BY Time,Flight_No2";//SQL语句  
		        db1 = new db_connection(sql);//创建db_connection对象  		       
		        boolean internationalOrLocal;
		        String from=null;
		        String to=null;
		        String stop=null;
		        String airline=null;
		        String boardingGate=null;
		        String[] checkinCounter=null;
		        String time=null;
		        try {  
		        	db1.pst.setString(1, flightnumber);
		            ret = db1.pst.executeQuery();//执行语句，得到结果集  
		            ret.last();
		            int rowNumber=ret.getRow();
		            ret.beforeFirst();
		            departureFlightInfo = new DepartureFlightInfo[rowNumber];
		            int j=0;
		            while (ret.next()) { 
		            	if(ret.getString("Flight_No2").equals(flightnumber) && ret.getString("Time").equals(time))
                        {  
         	        	   j++;
         	            }else
         	               {
	            	           checkinCounter=new String[10]; 
         	        	       i++;
         	        	       j=0;
         	               }
		            	flightnumber=ret.getString("Flight_No2");
		                internationalOrLocal=ret.getBoolean("InternationalOrLocal");
		            	from=ret.getString("Starting_station");
		            	to=ret.getString("Destination");
		            	stop=ret.getString("Staging_post");
		            	airline=ret.getString("Airline");
		            	boardingGate=ret.getString("Bname");
		            	checkinCounter[j]=ret.getString("Cname");
		            	time=ret.getString("Time");
		            	FlightCourse flightCourse=new FlightCourse(internationalOrLocal,false,flightnumber,airline,from,to,stop);
		            	departureFlightInfo[i]=new DepartureFlightInfo(flightCourse,checkinCounter,boardingGate,time);
		            }
		            ret.close();  
		            
		        } catch (SQLException e) {  
		            e.printStackTrace();  
		        } finally{
		        	db1.close();//关闭连接  
		        }
		return departureFlightInfo;
    	
    }
    public DepartureFlightInfo[] searchDepartureFlightInfo1(String city,String airline)
    {
		DepartureFlightInfo[] departureFlightInfo = null;
		//查询该航班号的离港航班信息
            /*
             * DepartureFlightInfo[] searchDepartureFlightInfo1(String flightnumber);
             * 数据库操作：查询航班号为flightnumber的离港航班信息
             * 形参为航班号，返回类型为DepartureFlightInfo对象数组
             * 
             */
            //departureFlightInfo=searchDepartureFlightInfo1(flightnumber); 
                int i=-1;
                sql = "SELECT flight_off.Flight_No2,flight_off.InternationalOrLocal,flight_off.Starting_station,flight_off.Destination," +
				"flight_off.Staging_post,flight_off.Airline,bc_allocation.Bname,bc_allocation.Cname,bc_allocation.Time " +
				"FROM flight_off INNER JOIN bc_allocation ON flight_off.Flight_No2 = bc_allocation.Flight_No2 " +
				"WHERE (flight_off.Destination like ? OR flight_off.Staging_post like ?)  AND flight_off.Airline = ? ORDER BY Time,Flight_No2";//SQL语句  
		        db1 = new db_connection(sql);//创建db_connection对象  
		        String flightNumber=null;
		        boolean internationalOrLocal;
		        String from=null;
		        String to=null;
		        String stop=null;
		        String boardingGate=null;
		        String[] checkinCounter=null;
		        String time=null;
		        try { 
		        	db1.pst.setString(1, "%"+city+"%");
		        	db1.pst.setString(2, "%"+city+"%");
		        	db1.pst.setString(3, airline);
		            ret = db1.pst.executeQuery();//执行语句，得到结果集  
		            ret.last();
		            int rowNumber=ret.getRow();
		            ret.beforeFirst();
		            departureFlightInfo = new DepartureFlightInfo[rowNumber];
		            int j=0;
		            while (ret.next()) { 
		            	if(ret.getString("Flight_No2").equals(flightNumber) && ret.getString("Time").equals(time))
                        {  
         	        	   j++;
         	            }else
         	               {
	            	           checkinCounter=new String[10];
         	        	       i++;
         	        	       j=0;
         	               }
		            	flightNumber=ret.getString("Flight_No2");
		                internationalOrLocal=ret.getBoolean("InternationalOrLocal");
		            	from=ret.getString("Starting_station");
		            	to=ret.getString("Destination");
		            	stop=ret.getString("Staging_post");
		            	airline=ret.getString("Airline");
		            	boardingGate=ret.getString("Bname");
		            	checkinCounter[j]=ret.getString("Cname");
		            	time=ret.getString("Time");
		            	FlightCourse flightCourse=new FlightCourse(internationalOrLocal,false,flightNumber,airline,from,to,stop);
		            	departureFlightInfo[i]=new DepartureFlightInfo(flightCourse,checkinCounter,boardingGate,time);
		            }
		            ret.close();  		           
		        } catch (SQLException e) {  
		            e.printStackTrace();  
		        } finally{
		        	 db1.close();//关闭连接  
		        }
		return departureFlightInfo;
    	
    }
    public DepartureFlightInfo[] searchDepartureFlightInfo2(String airline)
    {
		DepartureFlightInfo[] departureFlightInfo = null;
		//查询该航班号的离港航班信息
            /*
             * DepartureFlightInfo[] searchDepartureFlightInfo1(String flightnumber);
             * 数据库操作：查询航班号为flightnumber的离港航班信息
             * 形参为航班号，返回类型为DepartureFlightInfo对象数组
             * 
             */
            //departureFlightInfo=searchDepartureFlightInfo1(flightnumber); 
                int i=-1;
                sql = "SELECT flight_off.Flight_No2,flight_off.InternationalOrLocal,flight_off.Starting_station,flight_off.Destination," +
				"flight_off.Staging_post,flight_off.Airline,bc_allocation.Bname,bc_allocation.Cname,bc_allocation.Time " +
				"FROM flight_off INNER JOIN bc_allocation ON flight_off.Flight_No2 = bc_allocation.Flight_No2 " +
				"WHERE flight_off.Airline = ? ORDER BY Time,Flight_No2";//SQL语句  
		        db1 = new db_connection(sql);//创建db_connection对象  
		        String flightNumber=null;
		        boolean internationalOrLocal;
		        String from=null;
		        String to=null;
		        String stop=null;
		        String boardingGate=null;
		        String[] checkinCounter=null;
		        String time=null;
		        try {  
		        	db1.pst.setString(1,airline);
		            ret = db1.pst.executeQuery();//执行语句，得到结果集  
		            ret.last();
		            int rowNumber=ret.getRow();
		            ret.beforeFirst();
		            departureFlightInfo = new DepartureFlightInfo[rowNumber];
		            int j=0;
		            while (ret.next()) { 
		            	if(ret.getString("Flight_No2").equals(flightNumber) && ret.getString("Time").equals(time))
                        {  
         	        	   j++;
         	            }else
         	               {
	            	           checkinCounter=new String[10];
         	        	       i++;
         	        	       j=0;
         	               }
		            	flightNumber=ret.getString("Flight_No2");
		                internationalOrLocal=ret.getBoolean("InternationalOrLocal");
		            	from=ret.getString("Starting_station");
		            	to=ret.getString("Destination");
		            	stop=ret.getString("Staging_post");
		            	airline=ret.getString("Airline");
		            	boardingGate=ret.getString("Bname");
		            	checkinCounter[j]=ret.getString("Cname");
		            	time=ret.getString("Time");
		            	FlightCourse flightCourse=new FlightCourse(internationalOrLocal,false,flightNumber,airline,from,to,stop);
		            	departureFlightInfo[i]=new DepartureFlightInfo(flightCourse,checkinCounter,boardingGate,time);
		            }
		            ret.close();  
		          
		        } catch (SQLException e) {  
		            e.printStackTrace();  
		        } finally{
		        	  db1.close();//关闭连接  
		        }
		return departureFlightInfo;
    	
    }
    public DepartureFlightInfo[] searchDepartureFlightInfo3(String city)
    {
		DepartureFlightInfo[] departureFlightInfo = null;
		//查询该航班号的离港航班信息
            /*
             * DepartureFlightInfo[] searchDepartureFlightInfo1(String flightnumber);
             * 数据库操作：查询航班号为flightnumber的离港航班信息
             * 形参为航班号，返回类型为DepartureFlightInfo对象数组
             * 
             */
            //departureFlightInfo=searchDepartureFlightInfo1(flightnumber); 
                int i=-1;
                sql = "SELECT flight_off.Flight_No2,flight_off.InternationalOrLocal,flight_off.Starting_station,flight_off.Destination," +
				"flight_off.Staging_post,flight_off.Airline,bc_allocation.Bname,bc_allocation.Cname,bc_allocation.Time " +
				"FROM flight_off INNER JOIN bc_allocation ON flight_off.Flight_No2 = bc_allocation.Flight_No2 " +
				"WHERE flight_off.Destination like ? OR flight_off.Staging_post like ? ORDER BY Time,Flight_No2" ;//SQL语句  
		        db1 = new db_connection(sql);//创建db_connection对象  
		        String flightNumber=null;
		        boolean internationalOrLocal;
		        String from=null;
		        String to=null;
		        String stop=null;
		        String boardingGate=null;
		        String[] checkinCounter=null;
		        String airline=null;
		        String time=null;
		        try {  
		        	db1.pst.setString(1, "%"+city+"%");
		        	db1.pst.setString(2, "%"+city+"%");
		            ret = db1.pst.executeQuery();//执行语句，得到结果集  
		            ret.last();
		            int rowNumber=ret.getRow();
		            ret.beforeFirst();
		            departureFlightInfo = new DepartureFlightInfo[rowNumber];
		            int j=0;
		            while (ret.next()) { 
		            	if(ret.getString("Flight_No2").equals(flightNumber) && ret.getString("Time").equals(time))
                        {  
         	        	   j++;
         	            }else
         	               {
	            	           checkinCounter=new String[10];
         	        	       i++;
         	        	       j=0;
         	               }
		            	flightNumber=ret.getString("Flight_No2");
		                internationalOrLocal=ret.getBoolean("InternationalOrLocal");
		            	from=ret.getString("Starting_station");
		            	to=ret.getString("Destination");
		            	stop=ret.getString("Staging_post");
		            	airline=ret.getString("Airline");
		            	boardingGate=ret.getString("Bname");
		            	checkinCounter[j]=ret.getString("Cname");
		            	time=ret.getString("Time");
		            	FlightCourse flightCourse=new FlightCourse(internationalOrLocal,false,flightNumber,airline,from,to,stop);
		            	departureFlightInfo[i]=new DepartureFlightInfo(flightCourse,checkinCounter,boardingGate,time);

		            }
		            ret.close();  
		            
		        } catch (SQLException e) {  
		            e.printStackTrace();  
		        } finally{
		        	 db1.close();//关闭连接 
		        }
		return departureFlightInfo;
    	
    }
    public DepartureFlightInfo[] searchDepartureFlightInfo4(String flightNumber,String time)
    {
		DepartureFlightInfo[] departureFlightInfo = null;
		
                int i=-1;
                sql = "SELECT flight_off.Flight_No2,flight_off.InternationalOrLocal,flight_off.Starting_station,flight_off.Destination," +
				"flight_off.Staging_post,flight_off.Airline,bc_allocation.Bname,bc_allocation.Cname,bc_allocation.Time " +
				"FROM flight_off INNER JOIN bc_allocation ON flight_off.Flight_No2 = bc_allocation.Flight_No2 " +
				"WHERE flight_off.Flight_No2 = ? AND bc_allocation.Time = ? ORDER BY Time,Flight_No2";//SQL语句  
		        db1 = new db_connection(sql);//创建db_connection对象  
//		        String flightNumber=null;
		        boolean internationalOrLocal;
		        String from=null;
		        String to=null;
		        String stop=null;
		        String boardingGate=null;
		        String[] checkinCounter=null;
		        String airline = null;
//		        String time=null;
		        try { 
		        	db1.pst.setString(1, flightNumber);
		        	db1.pst.setString(2, time);
		            ret = db1.pst.executeQuery();//执行语句，得到结果集  
		            ret.last();
		            int rowNumber=ret.getRow();
		            ret.beforeFirst();
		            departureFlightInfo = new DepartureFlightInfo[rowNumber];
		            int j=0;
		            time=null;
		            while (ret.next()) { 
		            	if(ret.getString("Flight_No2").equals(flightNumber) && ret.getString("Time").equals(time))
                        {  
         	        	   j++;
         	            }else
         	               {
	            	           checkinCounter=new String[10];
         	        	       i++;
         	        	       j=0;
         	               }
		            	flightNumber=ret.getString("Flight_No2");
		                internationalOrLocal=ret.getBoolean("InternationalOrLocal");
		            	from=ret.getString("Starting_station");
		            	to=ret.getString("Destination");
		            	stop=ret.getString("Staging_post");
		            	airline=ret.getString("Airline");
		            	boardingGate=ret.getString("Bname");
		            	checkinCounter[j]=ret.getString("Cname");
		            	time=ret.getString("Time");
		            	FlightCourse flightCourse=new FlightCourse(internationalOrLocal,false,flightNumber,airline,from,to,stop);
		            	departureFlightInfo[i]=new DepartureFlightInfo(flightCourse,checkinCounter,boardingGate,time);
		            }
		            ret.close();  		           
		        } catch (SQLException e) {  
		            e.printStackTrace();  
		        } finally{
		        	 db1.close();//关闭连接  
		        }
		return departureFlightInfo;
    	
    }
    /*
     * 传入登机门名称boardingGate和时间time，查询符合该条件的航班分配条目数量
     * 返回条目数量，当返回-1时表示查询失败
     */
    public int searchDepartureFlightInfo5(String boardingGate,String time)//
    {
                int result = -1;
                sql = "SELECT COUNT(*) count FROM bc_allocation WHERE Bname = ? AND Time = ?";//SQL语句  
		        db1 = new db_connection(sql);//创建db_connection对象  
		        try { 
		        	db1.pst.setString(1, boardingGate);
		        	db1.pst.setString(2, time);
		            ret = db1.pst.executeQuery();//执行语句，得到结果集  
		            ret.beforeFirst();
		            ret.next();
		            result = ret.getInt("count");
		            ret.close();  		           
		        } catch (SQLException e) {  
		            e.printStackTrace();  
		        } finally{
		        	 db1.close();//关闭连接  
		        }
		return result;
    }
    public  ArrivalFlightInfo[] searchArrivalFlightInfo0(String flightnumber)
    {
    	ArrivalFlightInfo[] arrivalFlightInfo = null;
		//查询该航班号的离港航班信息
            /*
             * DepartureFlightInfo[] searchDepartureFlightInfo0(String flightnumber);
             * 数据库操作：查询航班号为flightnumber的离港航班信息
             * 形参为航班号，返回类型为DepartureFlightInfo对象数组
             * 
             */
            //departureFlightInfo=searchDepartureFlightInfo0(flightnumber); 
                int i=0;
                sql = "SELECT flight_arrival.Flight_No,flight_arrival.InternationalOrLocal,flight_arrival.Starting_station,flight_arrival.Destination," +
				"flight_arrival.Staging_post,flight_arrival.Airline,lc_allocation.Time,lc_allocation.Lname FROM (flight_arrival " +
				"INNER JOIN lc_allocation ON flight_arrival.Flight_No = lc_allocation.Flight_No) WHERE flight_arrival.Flight_No = ?";//SQL语句  
		        db1 = new db_connection(sql);//创建db_connection对象  
		        boolean internationalOrLocal;
		        String from=null;
		        String to=null;
		        String stop=null;
		        String airline=null;
		        String luggageCarousel=null;
		        String time=null;
		        try {  
		        	db1.pst.setString(1, flightnumber);
		            ret = db1.pst.executeQuery();//执行语句，得到结果集  
		            ret.last();
		            int rowNumber=ret.getRow();
		            ret.beforeFirst();
		            arrivalFlightInfo = new ArrivalFlightInfo[rowNumber];
		            while (ret.next()) { 
		            	flightnumber=ret.getString("Flight_No");
		                internationalOrLocal=ret.getBoolean("InternationalOrLocal");
		            	from=ret.getString("Starting_station");
		            	to=ret.getString("Destination");
		            	stop=ret.getString("Staging_post");
		            	airline=ret.getString("Airline");
		            	luggageCarousel=ret.getString("Lname");
		            	time=ret.getString("Time");
		            	FlightCourse flightCourse=new FlightCourse(internationalOrLocal,true,flightnumber,airline,from,to,stop);
		            	arrivalFlightInfo[i]=new ArrivalFlightInfo(flightCourse,luggageCarousel,time);
		                i++;
		            }
		            ret.close();  
		            
		        } catch (SQLException e) {  
		            e.printStackTrace();  
		        } finally{
		        	db1.close();//关闭连接  
		        }
		return arrivalFlightInfo;
    	
    }
    public  ArrivalFlightInfo[] searchArrivalFlightInfo1(String city,String airline)
    {
    	ArrivalFlightInfo[] arrivalFlightInfo = null;
    	//查询出发地为该city，航空公司为该airline的到港航班信息
		/*
		 * ArrivalFlightInfo[] arrivalFlightInfo=search1(String city,String airline); 
		 * 数据库操作：查询 出发地为该city，航空公司为该airline的到港航班信息
		 * 形参为出发地和航空公司，返回类型为ArrivalFlightInfo对象数组
		 */
                int i=0;
                sql = "SELECT flight_arrival.Flight_No,flight_arrival.InternationalOrLocal,flight_arrival.Starting_station,flight_arrival.Destination," +
				"flight_arrival.Staging_post,flight_arrival.Airline,lc_allocation.Time,lc_allocation.Lname FROM (flight_arrival " +
				"INNER JOIN lc_allocation ON flight_arrival.Flight_No = lc_allocation.Flight_No) " +
				"WHERE (flight_arrival.Starting_station like ? OR flight_arrival.Staging_post like ?) AND flight_arrival.Airline= ?";//SQL语句  
		        db1 = new db_connection(sql);//创建db_connection对象  
		        boolean internationalOrLocal;
		        String from=null;
		        String to=null;
		        String stop=null;
		        String luggageCarousel=null;
		        String time=null;
		        String flightnumber=null;
		        try {
		        	db1.pst.setString(1, "%"+city+"%");
		        	db1.pst.setString(2, "%"+city+"%");
		        	db1.pst.setString(3, airline);
		            ret = db1.pst.executeQuery();//执行语句，得到结果集  
		            ret.last();
		            int rowNumber=ret.getRow();
		            ret.beforeFirst();
		            arrivalFlightInfo = new ArrivalFlightInfo[rowNumber];
		            while (ret.next()) { 
		            	flightnumber = ret.getString("Flight_No");
		                internationalOrLocal=ret.getBoolean("InternationalOrLocal");
		            	from=ret.getString("Starting_station");
		            	to=ret.getString("Destination");
		            	stop=ret.getString("Staging_post");
		            	airline=ret.getString("Airline");
		            	luggageCarousel=ret.getString("Lname");
		            	time=ret.getString("Time");
		            	FlightCourse flightCourse=new FlightCourse(internationalOrLocal,true,flightnumber,airline,from,to,stop);
		            	arrivalFlightInfo[i]=new ArrivalFlightInfo(flightCourse,luggageCarousel,time);
		                i++;
		            }
		            ret.close();  
		         
		        } catch (SQLException e) {  
		            e.printStackTrace();  
		        } finally{
		        	   db1.close();//关闭连接  
		        }
		     
		return arrivalFlightInfo;
    	
    }
    public  ArrivalFlightInfo[] searchArrivalFlightInfo2(String airline)
    {
    	ArrivalFlightInfo[] arrivalFlightInfo = null;
    	//查询航空公司为该airline的到港航班信息
        /*
         * ArrivalFlightInfo[] searchArrivalFlightInfo2(String airline); 
         * 数据库操作：查询航空公司为该airline的到港航班信息
         * 形参为航空公司，返回类型为ArrivalFlightInfo对象数组
         * 
         */ 
                int i=0;
                sql = "SELECT flight_arrival.Flight_No,flight_arrival.InternationalOrLocal,flight_arrival.Starting_station,flight_arrival.Destination," +
				"flight_arrival.Staging_post,flight_arrival.Airline,lc_allocation.Time,lc_allocation.Lname FROM (flight_arrival " +
				"INNER JOIN lc_allocation ON flight_arrival.Flight_No = lc_allocation.Flight_No) WHERE flight_arrival.Airline = ?";//SQL语句 
		        db1 = new db_connection(sql);//创建db_connection对象  
		        boolean internationalOrLocal;
		        String from=null;
		        String to=null;
		        String stop=null;
		        String luggageCarousel=null;
		        String time=null;
		        String flightnumber=null;
		        try {  
		        	db1.pst.setString(1, airline);
		            ret = db1.pst.executeQuery();//执行语句，得到结果集  
		            ret.last();
		            int rowNumber=ret.getRow();
		            ret.beforeFirst();
		            arrivalFlightInfo = new ArrivalFlightInfo[rowNumber];
		            while (ret.next()) { 
		            	flightnumber = ret.getString("Flight_No");
		                internationalOrLocal=ret.getBoolean("InternationalOrLocal");
		            	from=ret.getString("Starting_station");
		            	to=ret.getString("Destination");
		            	stop=ret.getString("Staging_post");
		            	airline=ret.getString("Airline");
		            	luggageCarousel=ret.getString("Lname");
		            	time=ret.getString("Time");
		            	FlightCourse flightCourse=new FlightCourse(internationalOrLocal,true,flightnumber,airline,from,to,stop);
		            	arrivalFlightInfo[i]=new ArrivalFlightInfo(flightCourse,luggageCarousel,time);
		                i++;
		            }
		            ret.close();  
		           
		        } catch (SQLException e) {  
		            e.printStackTrace();  
		        } finally{
		        	 db1.close();//关闭连接  
		        }
		return arrivalFlightInfo;
    	
    }
    public  ArrivalFlightInfo[] searchArrivalFlightInfo3(String city)
    {
    	ArrivalFlightInfo[] arrivalFlightInfo = null;
    	//查询出发地为该city的到港航班信息
        /*
         * ArrivalFlightInfo[] searchArrivalFlightInfo3(String city); 
         * 数据库操作：查询出发地为该city的到港航班信息
         * 形参为出发地，返回类型为ArrivalFlightInfo对象数组
         * 
         */        
                int i=0;
                sql = "SELECT flight_arrival.Flight_No,flight_arrival.InternationalOrLocal,flight_arrival.Starting_station,flight_arrival.Destination," +
				"flight_arrival.Staging_post,flight_arrival.Airline,lc_allocation.Time,lc_allocation.Lname FROM (flight_arrival " +
				"INNER JOIN lc_allocation ON flight_arrival.Flight_No = lc_allocation.Flight_No) WHERE flight_arrival.Starting_station like ? OR flight_arrival.Staging_post like ?";//SQL语句   
		        db1 = new db_connection(sql);//创建db_connection对象  
		        boolean internationalOrLocal;
		        String from=null;
		        String to=null;
		        String stop=null;
		        String luggageCarousel=null;
		        String time=null;
		        String flightnumber=null;
		        String airline=null;
		        try {  
		        	db1.pst.setString(1, "%"+city+"%");
		        	db1.pst.setString(2, "%"+city+"%");
		            ret = db1.pst.executeQuery();//执行语句，得到结果集  
		            ret.last();
		            int rowNumber=ret.getRow();
		            ret.beforeFirst();
		            arrivalFlightInfo = new ArrivalFlightInfo[rowNumber];
		            while (ret.next()) { 
		            	flightnumber = ret.getString("Flight_No");
		                internationalOrLocal=ret.getBoolean("InternationalOrLocal");
		            	from=ret.getString("Starting_station");
		            	to=ret.getString("Destination");
		            	stop=ret.getString("Staging_post");
		            	airline=ret.getString("Airline");
		            	luggageCarousel=ret.getString("Lname");
		            	time=ret.getString("Time");
		            	FlightCourse flightCourse=new FlightCourse(internationalOrLocal,true,flightnumber,airline,from,to,stop);
		            	arrivalFlightInfo[i]=new ArrivalFlightInfo(flightCourse,luggageCarousel,time);
		                i++;
		            }
		            ret.close();  
		         
		        } catch (SQLException e) {  
		            e.printStackTrace();  
		        } finally{
		        	   db1.close();//关闭连接  
		        }
		return arrivalFlightInfo;
    	
    }
    public  ArrivalFlightInfo[] searchArrivalFlightInfo4(String flightNumber,String time)
    {
    	ArrivalFlightInfo[] arrivalFlightInfo = null;
    	
                int i=0;
                sql = "SELECT flight_arrival.Flight_No,flight_arrival.InternationalOrLocal,flight_arrival.Starting_station,flight_arrival.Destination," +
				"flight_arrival.Staging_post,flight_arrival.Airline,lc_allocation.Time,lc_allocation.Lname FROM (flight_arrival " +
				"INNER JOIN lc_allocation ON flight_arrival.Flight_No = lc_allocation.Flight_No) " +
				"WHERE flight_arrival.Flight_No = ? AND lc_allocation.Time= ?";//SQL语句  
		        db1 = new db_connection(sql);//创建db_connection对象  
		        boolean internationalOrLocal;
		        String from=null;
		        String to=null;
		        String stop=null;
		        String luggageCarousel=null;
//		        String time=null;
		        String flightnumber=null;
		        String airline=null;
		        try {
		        	db1.pst.setString(1, flightNumber);
		        	db1.pst.setString(2, time);
		            ret = db1.pst.executeQuery();//执行语句，得到结果集  
		            ret.last();
		            int rowNumber=ret.getRow();
		            ret.beforeFirst();
		            arrivalFlightInfo = new ArrivalFlightInfo[rowNumber];
		            while (ret.next()) { 
		            	flightnumber = ret.getString("Flight_No");
		                internationalOrLocal=ret.getBoolean("InternationalOrLocal");
		            	from=ret.getString("Starting_station");
		            	to=ret.getString("Destination");
		            	stop=ret.getString("Staging_post");
		            	airline=ret.getString("Airline");
		            	luggageCarousel=ret.getString("Lname");
		            	time=ret.getString("Time");
		            	FlightCourse flightCourse=new FlightCourse(internationalOrLocal,true,flightnumber,airline,from,to,stop);
		            	arrivalFlightInfo[i]=new ArrivalFlightInfo(flightCourse,luggageCarousel,time);
		                i++;
		            }
		            ret.close();  
		           
		        } catch (SQLException e) {  
		            e.printStackTrace();  
		        } finally{
		        	 db1.close();//关闭连接  
		        }
		return arrivalFlightInfo;
    	
    }
    public  AirportResource[] searchAirportResource(String name,String type){
    	AirportResource[] airportResource = null;
  /*
  * AirportResource[] searchA(String name);
  * 数据库操作：查询机场资源名称为该name的机场资源信息
  * 形参为机场资源名称，返回类型为AirportResource对象数组
  */
    	 int i=0;
    	 String location;
         String remark;
         int rowNumber=0;
         try {  
        	 if(name!=null&&!name.equals("")){
 		     sql= "SELECT * FROM boardinggate WHERE boardinggate.Bname=?";
             db1= new db_connection(sql);//创建db_connection对象                  
             db1.pst.setString(1, name);
             ret = db1.pst.executeQuery();//执行语句，得到结果集  
             type="登机门";
             ret.last();
	         rowNumber=ret.getRow();
	         ret.beforeFirst();
	         airportResource = new AirportResource[rowNumber];
             while (ret.next()) {             	
//          	name=ret.getString("SourseName");
//          	location=ret.getString("SourseL");
//          	remark=ret.getString("SourseR");
//          	type=ret.getString("SourseR");
          	name=ret.getString("Bname");
          	location=ret.getString("Blocation");
          	remark=ret.getString("Bremarks");
          	airportResource[i]=new AirportResource(name,location,remark,type);         	
              i++;
          }  
             if(!ret.first())
             {   sql= "SELECT * FROM checkincounter WHERE checkincounter.Cname=?";
                 db1= new db_connection(sql);//创建db_connection对象 
                 db1.pst.setString(1, name);
                 ret = db1.pst.executeQuery();//执行语句，得到结果集  
                 ret.last();
 	             rowNumber=ret.getRow();
 	             ret.beforeFirst();
 	             airportResource = new AirportResource[rowNumber];	
                 type="值机柜台";
                 i=0;
                 while (ret.next()) {             	
                  	name=ret.getString("Cname");
                  	location=ret.getString("Clocation");
                  	remark=ret.getString("Cremarks");
                  	airportResource[i]=new AirportResource(name,location,remark,type);
                    i++;
                  }
             }
              if(!ret.first())
             {   sql= "SELECT * FROM luggagecarousel WHERE luggagecarousel.Lname=?";
                 db1= new db_connection(sql);//创建db_connection对象  
                 db1.pst.setString(1, name);
                 ret = db1.pst.executeQuery();//执行语句，得到结果集  
                 ret.last();
 	             rowNumber=ret.getRow();
 	             ret.beforeFirst();
 	             airportResource = new AirportResource[rowNumber];	             
                 type="行李转盘";
                 i=0;
                 while (ret.next()) {             	
                  	name=ret.getString("Lname");
                  	location=ret.getString("Llocation");
                  	remark=ret.getString("Lremarks");
                  	airportResource[i]=new AirportResource(name,location,remark,type);
                    i++;
                  }
             }
        	 }else if(name.equals("")||name==null){
        		 if(type.equals("登机门"))
        	    	{
        	    	     sql = "SELECT * FROM boardinggate";
        		         //SQL语句  
        	    	     db1= new db_connection(sql);//创建db_connection对象
        	             ret = db1.pst.executeQuery();//执行语句，得到结果集  
        	             type="登机门";
        	             ret.last();
        		         rowNumber=ret.getRow();
        		         ret.beforeFirst();
        		         airportResource = new AirportResource[rowNumber];
        	             while (ret.next()) {             	
        	             	name=ret.getString("Bname");
        	            	location=ret.getString("Blocation");
        	            	remark=ret.getString("Bremarks");
        	             	airportResource[i]=new AirportResource(name,location,remark,type);         	
        	                i++;
        	          }
        	    	}
        	    	else if(type.equals("值机柜台"))
        	    	{
        	    		sql = "SELECT * FROM checkincounter";
        	    		//SQL语句 
        	    		db1= new db_connection(sql);//创建db_connection对象
        	    		ret = db1.pst.executeQuery();//执行语句，得到结果集  
                        ret.last();
        	             rowNumber=ret.getRow();
        	             ret.beforeFirst();
        	             airportResource = new AirportResource[rowNumber];	
                        type="值机柜台";
                        i=0;
                        while (ret.next()) {             	
                         	name=ret.getString("Cname");
                         	location=ret.getString("Clocation");
                         	remark=ret.getString("Cremarks");
                         	airportResource[i]=new AirportResource(name,location,remark,type);
                           i++;
                         }
        	    	}
        	    	else if(type.equals("行李转盘"))
        	    	{
        	    		sql = "SELECT * FROM luggagecarousel";
        	    		//SQL语句 
        	    		db1= new db_connection(sql);//创建db_connection对象
        	    		 ret = db1.pst.executeQuery();//执行语句，得到结果集  
                         ret.last();
         	             rowNumber=ret.getRow();
         	             ret.beforeFirst();
         	             airportResource = new AirportResource[rowNumber];	             
                         type="行李转盘";
                         i=0;
                         while (ret.next()) {             	
                          	name=ret.getString("Lname");
                          	location=ret.getString("Llocation");
                          	remark=ret.getString("Lremarks");
                          	airportResource[i]=new AirportResource(name,location,remark,type);
                            i++;
                          }
        	    	}
        	 }
              ret.close();               
         } catch (SQLException e) {  
             e.printStackTrace();  
         }finally{
        	  db1.close();//关闭连接 
         }
         return airportResource;
    }
    public PropertyFacility[] searchPropertyFacility(String name,String type,int mode){
    	/*
//       * PropertyFacility[] searchPropertyFacility(String name,String type,int mode);
//       * 数据库操作：查询物业设施名称为该name,类型为该type的物业设施信息；mode=1表示精确查询，mode=0表示模糊查询
//       * 形参为物业设施名称、物业设施类型、查询模式，返回类型为PropertyFacility对象数组
//       */
    	 PropertyFacility[] propertyFacility = null;
      
      int i=0;
      sql = "SELECT* FROM facility";//SQL语句  
      db1= new db_connection(sql);//创建db_connection对象  
      String location;
      String remark;
      String phone;
      try {  
    	  if((name!=null&&!name.equals(""))&&(type.equals("")||type==null))
    	  {
    		  if(mode==1)
    			  sql = "SELECT* FROM facility WHERE facility.Fname = ?";//SQL语句
    		  else
    			  sql = "SELECT* FROM facility WHERE facility.Fname like ?";//SQL语句
    		  db1.pst=db1.conn.prepareStatement(sql);
    		  if(mode==1)
    			  db1.pst.setString(1, name);
    		  else
    			  db1.pst.setString(1, "%"+name+"%");
    	  }
    	  else if((name==null||name.equals(""))&&(!type.equals("")&&type!=null))
    	  {
    		  sql = "SELECT* FROM facility WHERE facility.F_category = ?";//SQL语句
    		  db1.pst=db1.conn.prepareStatement(sql);
    		  db1.pst.setString(1, type);
    	  }else if((name!=null&&!name.equals(""))&&(!type.equals("")&&type!=null))
    	  {
    		  sql = "SELECT* FROM facility WHERE facility.F_category = ? and facility.Fname like ?";//SQL语句
    		  db1.pst=db1.conn.prepareStatement(sql);
    		  db1.pst.setString(1, type);
    		  db1.pst.setString(2, "%"+name+"%");
    	  }
          ret = db1.pst.executeQuery();//执行语句，得到结果集 
          ret.last();
          int rowNumber=ret.getRow();
          ret.beforeFirst();
          propertyFacility = new PropertyFacility[rowNumber]; 
          while (ret.next()) { 
        	 
        	  	name=ret.getString("Fname");
        	  	location=ret.getString("Location");
        	  	remark=ret.getString("Remarks");
        	  	type=ret.getString("F_category");
        	  	phone=ret.getString("F_tel");       	  	
        	  	propertyFacility[i] = new PropertyFacility(name, location, remark, type, phone);
                i++;
          }
          ret.close();  
          db1.close();//关闭连接  
      } catch (SQLException e) {  
          e.printStackTrace();  
      } 
    	 return propertyFacility;
    }
    
    public News[] searchNews(String title,String time){
    	 
//       /*
//        * News[] searchN(String title,String time);
//        * 数据库操作：查询新闻标题为title，发布时间为time的新闻信息；若其中一形参值为空字符串，则表示无此限制条件
//        * 形参为新闻标题、发布时间，返回类型为News对象数组
//        */
    	 News[] news =null;
    	 int i=0;
    	 sql="SELECT* FROM newscenter";
    	 db1= new db_connection(sql);//创建db_connection对象  
    	 String newsId;
    	 String content;
    	 String kind;
    	 String publisher_id;
    	 String attachment;
    	 try {  
    		 
    		 if(!title.equals("")&&title!=null&&!time.equals("")&&time!=null)
    	     {
    		       sql = "SELECT* FROM newscenter WHERE newscenter.title like ? AND newscenter.Edit_time=?";//SQL语句
    		       db1.pst=db1.conn.prepareStatement(sql);
    		       db1.pst.setString(1, "%"+title+"%");
       		       db1.pst.setString(2, time);
    	     }
    	     else if((title.equals("")||title==null)&&(!time.equals("")&&time!=null))
    		 {
    		       sql = "SELECT* FROM newscenter WHERE  newscenter.Edit_time=?";//SQL语句
    		       db1.pst=db1.conn.prepareStatement(sql);
    		       db1.pst.setString(1, time);
    		 }
    	     else if((!title.equals("")&&title!=null)&&(time.equals("")||time==null))
		     {
		           sql = "SELECT* FROM newscenter WHERE  newscenter.title like ?";//SQL语句
		           db1.pst=db1.conn.prepareStatement(sql);
		           db1.pst.setString(1, "%"+title+"%");
		      }   		    
    		 	ret = db1.pst.executeQuery();//执行语句，得到结果集  
    		 	ret.last();
	            int rowNumber=ret.getRow();
	            ret.beforeFirst();
	            news = new News[rowNumber];
    		 	while (ret.next()) { 
    	
    		 		newsId=ret.getString("News_id");
    		 		title=ret.getString("Title");
    		 		time=ret.getString("Edit_time");
    		 		content=ret.getString("Content");
    		 		kind=ret.getString("NCname");
    		 		publisher_id=ret.getString("Em_No");
    		 		attachment=ret.getString("Attachments");
    		 		news[i] = new News(title, time, content, kind,  attachment, publisher_id,newsId);
    		 		i++;
    		 	}
    		 	ret.close();  
    		 	db1.close();//关闭连接  
    	 } catch (SQLException e) {  
    		 e.printStackTrace();  
    	 } 
       return news;
    }
    public News[] returnAllNews(){
   	 
//      /*
//       * News[]  returnAllNews();
//       * 数据库操作：返回所有新闻信息；
//       * 返回类型为News对象数组
//       */
   	 News[] allNews =null;
   	 int i=0;
   	 sql = "SELECT* FROM newscenter";//SQL语句
   	 db1= new db_connection(sql);//创建db_connection对象  
   	 String newsId;
   	 String title;
   	 String time;
   	 String content;
   	 String kind;
   	 String publisher_id;
   	 String attachment;
   	 try {  
   		 	ret = db1.pst.executeQuery();//执行语句，得到结果集  
   		 	ret.last();
	            int rowNumber=ret.getRow();
	            ret.beforeFirst();
	            allNews  = new News[rowNumber];
   		 	while (ret.next()) { 
   	
   		 		newsId=ret.getString("News_id");
   		 		title=ret.getString("Title");
   		 		time=ret.getString("Edit_time");
   		 		content=ret.getString("Content");
   		 		kind=ret.getString("NCname");
   		 		publisher_id=ret.getString("Em_No");
   		 		attachment=ret.getString("Attachments");
   		     	allNews[i] = new News(title, time, content, kind,  attachment, publisher_id,newsId);
   		 		i++;
   		 	}
   		 	ret.close();    		 	  
   	 } catch (SQLException e) {  
   		 e.printStackTrace();  
   	 } finally{
   		 db1.close();//关闭连接
   	 }
      return allNews ;
   }
    public String[] returnAllLocalDestination()
    {/*返回数据库中离港航班的所有国内目的地
        allLocalDestination:国内目的地数组
        return allLocalDestination;
       */
    	String[] allLocalDestination=null;
        int i=0;
        sql = "SELECT DISTINCT flight_off.Destination FROM flight_off WHERE flight_off.InternationalOrLocal=false";//SQL语句  
        db1= new db_connection(sql);//创建db_connection对象         
        try {  
            ret = db1.pst.executeQuery();//执行语句，得到结果集 
            ret.last();
            int rowNumber=ret.getRow();
            ret.beforeFirst();
            allLocalDestination=new String[rowNumber];
            while (ret.next()) {           	 
            	allLocalDestination[i]=ret.getString("Destination");   	  	         	 
                  i++;
            }
            ret.close();  
           
        } catch (SQLException e) {  
            e.printStackTrace();  
        } 
        finally{
        	 db1.close();//关闭连接  
        }
		return allLocalDestination;
    	
    }
    public String[] returnAllLocalFrom()
    {/*返回数据库中到港航班的所有国内始发地
        allLocalFrom:始发地数组
        return allLocalFrom
        */
    	String[] allLocalFrom=null;
        int i=0;
        sql = "SELECT DISTINCT flight_arrival.Starting_station FROM flight_arrival WHERE flight_arrival.InternationalOrLocal=false";//SQL语句  
        db1= new db_connection(sql);//创建db_connection对象         
        try {  
            ret = db1.pst.executeQuery();//执行语句，得到结果集 
            ret.last();
            int rowNumber=ret.getRow();
            ret.beforeFirst();
            allLocalFrom=new String[rowNumber];
            while (ret.next()) {           	 
            	allLocalFrom[i]=ret.getString("Starting_station");   	  	         	 
                  i++;
            }
            ret.close();  
           
        } catch (SQLException e) {  
            e.printStackTrace();  
        } 
        finally{
        	 db1.close();//关闭连接  
        }
		return allLocalFrom;
    }
    public String[] returnAllInternationalDestination()
    {/*返回数据库中离港航班的所有国际目的地
        allInternationalDestination:国际目的地数组
        return allInternationalDestination;
       */
    	String[] allInternationalDestination=null;
        int i=0;
        sql = "SELECT DISTINCT flight_off.Destination FROM flight_off WHERE flight_off.InternationalOrLocal=true";//SQL语句  
        db1= new db_connection(sql);//创建db_connection对象         
        try {  
            ret = db1.pst.executeQuery();//执行语句，得到结果集 
            ret.last();
            int rowNumber=ret.getRow();
            ret.beforeFirst();
            allInternationalDestination=new String[rowNumber];
            while (ret.next()) {           	 
            	allInternationalDestination[i]=ret.getString("Destination");   	  	         	 
                  i++;
            }
            ret.close();  
           
        } catch (SQLException e) {  
            e.printStackTrace();  
        } 
        finally{
        	 db1.close();//关闭连接  
        }
		return allInternationalDestination;
    	
    }
    public String[] returnAllInternationalFrom()
    {/*返回数据库中到港航班的所有国际始发地
        allInternationalFrom:国际始发地数组
        return allInternationalFrom
        */
    	String[] allInternationalFrom=null;
        int i=0;
        sql = "SELECT DISTINCT flight_arrival.Starting_station FROM flight_arrival WHERE flight_arrival.InternationalOrLocal=true";//SQL语句  
        db1= new db_connection(sql);//创建db_connection对象         
        try {  
            ret = db1.pst.executeQuery();//执行语句，得到结果集 
            ret.last();
            int rowNumber=ret.getRow();
            ret.beforeFirst();
            allInternationalFrom=new String[rowNumber];
            while (ret.next()) {           	 
            	allInternationalFrom[i]=ret.getString("Starting_station");   	  	         	 
                  i++;
            }
            ret.close();  
           
        } catch (SQLException e) {  
            e.printStackTrace();  
        } 
        finally{
        	 db1.close();//关闭连接  
        }
		return allInternationalFrom;
    }
    public String[] returnAllArrivalLocalAirline()
    {/*返回数据库中的所有到港国内航空公司
        allArrivalLocalAirline:到港国内航空公司数组
        return allArrivalLocalAirline
        */
    	String[] allArrivalLocalAirline=null;
        int i=0;
        sql = "SELECT  DISTINCT Airline FROM flight_arrival WHERE flight_arrival.InternationalOrLocal=false";//SQL语句  
        db1= new db_connection(sql);//创建db_connection对象         
        try {  
            ret = db1.pst.executeQuery();//执行语句，得到结果集 
            ret.last();
            int rowNumber=ret.getRow();
            ret.beforeFirst();
            allArrivalLocalAirline=new String[rowNumber];
            while (ret.next()) {           	 
            	allArrivalLocalAirline[i]=ret.getString("Airline");   	  	         	 
                  i++;
            }
            ret.close();  
           
        } catch (SQLException e) {  
            e.printStackTrace();  
        } 
        finally{
        	 db1.close();//关闭连接  
        }
		return allArrivalLocalAirline;
    }
    public String[] returnAllArrivalInternationalAirline()
    {/*返回数据库中的所有到港国际航空公司
        allArrivalInternationalAirline:到港国际航空公司数组
        return allArrivalInternationalAirline
        */
    	String[] allArrivalInternationalAirline=null;
        int i=0;
        sql = "SELECT  DISTINCT Airline FROM flight_arrival WHERE flight_arrival.InternationalOrLocal=true";//SQL语句  
        db1= new db_connection(sql);//创建db_connection对象         
        try {  
            ret = db1.pst.executeQuery();//执行语句，得到结果集 
            ret.last();
            int rowNumber=ret.getRow();
            ret.beforeFirst();
            allArrivalInternationalAirline=new String[rowNumber];
            while (ret.next()) {           	 
            	allArrivalInternationalAirline[i]=ret.getString("Airline");   	  	         	 
                  i++;
            }
            ret.close();  
           
        } catch (SQLException e) {  
            e.printStackTrace();  
        } 
        finally{
        	 db1.close();//关闭连接  
        }
		return allArrivalInternationalAirline;
    }
    public String[] returnAllDepartureInternationalAirline()
    {/*返回数据库中的所有离港国际航空公司
        allDepartureInternationalAirline:离港国际航空公司数组
        return allDepartureInternationalAirline
        */
    	String[] allDepartureInternationalAirline=null;
        int i=0;
        sql = "SELECT  DISTINCT Airline FROM flight_off WHERE flight_off.InternationalOrLocal=true";//SQL语句  
        db1= new db_connection(sql);//创建db_connection对象         
        try {  
            ret = db1.pst.executeQuery();//执行语句，得到结果集 
            ret.last();
            int rowNumber=ret.getRow();
            ret.beforeFirst();
            allDepartureInternationalAirline=new String[rowNumber];
            while (ret.next()) {           	 
            	allDepartureInternationalAirline[i]=ret.getString("Airline");   	  	         	 
                  i++;
            }
            ret.close();  
           
        } catch (SQLException e) {  
            e.printStackTrace();  
        } 
        finally{
        	 db1.close();//关闭连接  
        }
		return allDepartureInternationalAirline;
    }
    public String[] returnAllDepartureLocalAirline()
    {/*返回数据库中的所有离港国内航空公司
        allDepartureLocalAirline:离港国内航空公司数组
        return allDepartureLocalAirline
        */
    	String[] allDepartureLocalAirline=null;
        int i=0;
        sql = "SELECT  DISTINCT Airline FROM flight_off WHERE flight_off.InternationalOrLocal=false";//SQL语句  
        db1= new db_connection(sql);//创建db_connection对象         
        try {  
            ret = db1.pst.executeQuery();//执行语句，得到结果集 
            ret.last();
            int rowNumber=ret.getRow();
            ret.beforeFirst();
            allDepartureLocalAirline=new String[rowNumber];
            while (ret.next()) {           	 
            	allDepartureLocalAirline[i]=ret.getString("Airline");   	  	         	 
                  i++;
            }
            ret.close();  
           
        } catch (SQLException e) {  
            e.printStackTrace();  
        } 
        finally{
        	 db1.close();//关闭连接  
        }
		return allDepartureLocalAirline;
    }
    public  AirportResource[] returnAllAirportResource(){
    	AirportResource[] allAirportResource = null;
  /*
  * AirportResource[] returnAllAirportResoure();
  * 数据库操作：返回所有机场资源信息
  * 返回类型为AirportResource对象数组
  */
    	 int i=0;
    	 int k=0;
         int j=0;
         int AirportResourceNumber=0;
    	 String name;
    	 String location;
         String remark;
         String type;
         AirportResource[] allBoardinggate=null;
         AirportResource[] allCheckincounter=null;
         AirportResource[] allLuggagecarousel=null;
         try {  
 		     sql= "SELECT * FROM boardinggate";
             db1= new db_connection(sql);//创建db_connection对象                  
             ret = db1.pst.executeQuery();//执行语句，得到结果集  
             type="登机门";
             ret.last();
	         int rowNumber=ret.getRow();
	         ret.beforeFirst();
             allBoardinggate = new AirportResource[rowNumber];
             while (ret.next()) {             	
//          	name=ret.getString("SourseName");
//          	location=ret.getString("SourseL");
//          	remark=ret.getString("SourseR");
//          	type=ret.getString("SourseR");
          	name=ret.getString("Bname");
          	location=ret.getString("Blocation");
          	remark=ret.getString("Bremarks");
          	allBoardinggate[i]=new AirportResource(name,location,remark,type);         	
              i++;
          }  

                 sql= "SELECT * FROM checkincounter";
                 db1.pst=db1.conn.prepareStatement(sql);
                 ret = db1.pst.executeQuery();//执行语句，得到结果集  
                 ret.last();
 	             rowNumber=ret.getRow();
 	             ret.beforeFirst();
 	            allCheckincounter = new AirportResource[rowNumber];	
                 type="值机柜台";
           
                 while (ret.next()) {             	
//                  	name=ret.getString("SourseName");
//                  	location=ret.getString("SourseL");
//                  	remark=ret.getString("SourseR");
//                  	type=ret.getString("SourseR");
                  	name=ret.getString("Cname");
                  	location=ret.getString("Clocation");
                  	remark=ret.getString("Cremarks");
                  	allCheckincounter[j]=new AirportResource(name,location,remark,type);
                    j++;
                  }
                 sql= "SELECT * FROM luggagecarousel";
                 db1.pst=db1.conn.prepareStatement(sql);
                 ret = db1.pst.executeQuery();//执行语句，得到结果集  
                 ret.last();
 	             rowNumber=ret.getRow();
 	             ret.beforeFirst();
 	            allLuggagecarousel = new AirportResource[rowNumber];	             
                 type="行李转盘";
                 
                 while (ret.next()) {             	
//                  	name=ret.getString("SourseName");
//                  	location=ret.getString("SourseL");
//                  	remark=ret.getString("SourseR");
//                  	type=ret.getString("SourseR");
                  	name=ret.getString("Lname");
                  	location=ret.getString("Llocation");
                  	remark=ret.getString("Lremarks");
                  	allLuggagecarousel[k]=new AirportResource(name,location,remark,type);
                    k++;
                  }
                 AirportResourceNumber=i+j+k;
              ret.close();  
                  
         } catch (SQLException e) {  
             e.printStackTrace();  
         }finally{
        	 db1.close();//关闭连接
         }
         allAirportResource = new AirportResource[AirportResourceNumber];
         int x=0;
         int jj=0;
         int kk=0;
         while(x<i){
        	 allAirportResource[x]=allBoardinggate[x];
        	 x++;
         }
         while(jj<j){
        	 allAirportResource[x]=allCheckincounter[jj];
        	 x++;
        	 jj++;
         }
         while(kk<k){
        	 allAirportResource[x]=allLuggagecarousel[kk];
        	 x++;
        	 kk++;
         }
         return allAirportResource;
    }
    public PropertyFacility[] returnAllPropertyFacility()
    {
    	/*
//       * PropertyFacility[] returnAllPropertyFacility();
//       * 数据库操作：返回所有物业设施信息
//       * 返回类型为PropertyFacility对象数组
//       */
    	 PropertyFacility[] propertyFacility = null;
    
      int i=0;
      sql = "SELECT* FROM facility";//SQL语句  
      db1= new db_connection(sql);//创建db_connection对象        
      String location;
      String remark;
      String type;
      String phone;
      String name;
      try {  
          ret = db1.pst.executeQuery();//执行语句，得到结果集 
          ret.last();
          int rowNumber=ret.getRow();
          ret.beforeFirst();
          propertyFacility = new PropertyFacility[rowNumber]; 
          while (ret.next()) {        	 
        	  	name=ret.getString("Fname");
        	  	location=ret.getString("Location");
        	  	remark=ret.getString("Remarks");
        	  	type=ret.getString("F_category");
        	  	phone=ret.getString("F_tel");       	  	
        	  	propertyFacility[i] = new PropertyFacility(name, location, remark, type, phone);
                i++;
          }
          ret.close();  
           
      } catch (SQLException e) {  
          e.printStackTrace();  
      } finally{
    	   db1.close();//关闭连接
      }
    	 return propertyFacility;
    }
    public Map<String,String> returnFlightnumberAirlineMap(){
    	/*
    	 * Map<String,String> returnFlightnumberAirlineMap()
    	 * 取航空公司的二字代码和航空公司名字的对应关系
    	 * 返回Map
    	 */
    	Map<String,String> allFlightnumberAirlineMap = new HashMap<String, String>();
    	    
         sql = "SELECT SUBSTR(Flight_No,1,2) sub,Airline A FROM `flight_arrival` GROUP BY Airline" +
         		" UNION SELECT SUBSTR(Flight_No2,1,2) sub,Airline A FROM `flight_off` GROUP BY Airline";//SQL语句  
         db1= new db_connection(sql);//创建db_connection对象        
         try {  
             ret = db1.pst.executeQuery();//执行语句，得到结果集 
             while (ret.next()) {  
               allFlightnumberAirlineMap.put(ret.getString("A"),ret.getString("sub") );
             }
             ret.close();  
              
         } catch (SQLException e) {  
             e.printStackTrace();  
         } finally{
       	   db1.close();//关闭连接
         }
      	return allFlightnumberAirlineMap; 	
      }
    public String[] returnAllPropertyFacilityType()
    {
    	/*
       * String[] returnAllPropertyFacilityType();
       * 数据库操作：返回所有物业设施类别
       * 返回类型为String对象数组
       */
    	 String[] allPropertyFacilityType = null;
    
      int i=0;
      sql = "SELECT DISTINCT(facility.F_category) FROM facility";//SQL语句  
      db1= new db_connection(sql);//创建db_connection对象        
      try {  
          ret = db1.pst.executeQuery();//执行语句，得到结果集 
          ret.last();
          int rowNumber=ret.getRow();
          ret.beforeFirst();
          allPropertyFacilityType = new String[rowNumber]; 
          while (ret.next()) {        	       	  	
        	  	allPropertyFacilityType[i] =ret.getString("F_category");
                i++;
          }
          ret.close();  
           
      } catch (SQLException e) {  
          e.printStackTrace();  
      } finally{
    	   db1.close();//关闭连接
      }
    	 return allPropertyFacilityType;
    }
    
  }