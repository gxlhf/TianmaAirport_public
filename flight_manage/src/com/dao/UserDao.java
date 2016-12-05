package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

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
                int i=0;
				sql = "SELECT flight_off.Flight_No2,flight_off.InternationalOrLocal,flight_off.Starting_station,flight_off.Destination," +
						"flight_off.Staging_post,flight_off.Airline,bc_allocation.Bname,bc_allocation.Cname,bc_allocation.Time " +
						"FROM flight_off INNER JOIN bc_allocation ON flight_off.Flight_No2 = bc_allocation.Flight_No2 " +
						"WHERE flight_off.Flight_No2 = '"+flightnumber+"'";//SQL语句  
		        db1 = new db_connection(sql);//创建db_connection对象  		       
		        boolean internationalOrLocal;
		        String from=null;
		        String to=null;
		        String stop=null;
		        String airline=null;
		        String boardingGate=null;
		        String checkinCounter=null;
		        String time=null;
		        try {  
		            ret = db1.pst.executeQuery();//执行语句，得到结果集  
		            ret.last();
		            int rowNumber=ret.getRow();
		            ret.beforeFirst();
		            departureFlightInfo = new DepartureFlightInfo[rowNumber];
		            ret.last();
		            while (ret.next()) { 
		            	flightnumber=ret.getString("Flight_No2");
		                internationalOrLocal=ret.getBoolean("InternationalOrLocal");
		            	from=ret.getString("Starting_station");
		            	to=ret.getString("Destination");
		            	stop=ret.getString("Staging_post");
		            	airline=ret.getString("Airline");
		            	boardingGate=ret.getString("Bname");
		            	checkinCounter=ret.getString("Cname");
		            	time=ret.getString("Time");
		            	FlightCourse flightCourse=new FlightCourse(internationalOrLocal,false,flightnumber,airline,from,to,stop);
		            	departureFlightInfo[i]=new DepartureFlightInfo(flightCourse,checkinCounter,boardingGate,time);
		                i++;
		            }
		            ret.close();  
		            db1.close();//关闭连接  
		        } catch (SQLException e) {  
		            e.printStackTrace();  
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
                int i=0;
                sql = "SELECT flight_off.Flight_No2,flight_off.InternationalOrLocal,flight_off.Starting_station,flight_off.Destination," +
				"flight_off.Staging_post,flight_off.Airline,bc_allocation.Bname,bc_allocation.Cname,bc_allocation.Time " +
				"FROM flight_off INNER JOIN bc_allocation ON flight_off.Flight_No2 = bc_allocation.Flight_No2 " +
				"WHERE flight_off.Destination = '"+city+"' AND flight_off.Airline = '"+airline+"'";//SQL语句  
		        db1 = new db_connection(sql);//创建db_connection对象  
		        String flightNumber=null;
		        boolean internationalOrLocal;
		        String from=null;
		        String to=null;
		        String stop=null;
		        String boardingGate=null;
		        String checkinCounter=null;
		        String time=null;
		        try {  
		            ret = db1.pst.executeQuery();//执行语句，得到结果集  
		            ret.last();
		            int rowNumber=ret.getRow();
		            ret.beforeFirst();
		            departureFlightInfo = new DepartureFlightInfo[rowNumber];
		            while (ret.next()) { 
		            	flightNumber=ret.getString("Flight_No2");
		                internationalOrLocal=ret.getBoolean("InternationalOrLocal");
		            	from=ret.getString("Starting_station");
		            	to=ret.getString("Destination");
		            	stop=ret.getString("Staging_post");
		            	airline=ret.getString("Airline");
		            	boardingGate=ret.getString("Bname");
		            	checkinCounter=ret.getString("Cname");
		            	time=ret.getString("Time");
		            	FlightCourse flightCourse=new FlightCourse(internationalOrLocal,false,flightNumber,airline,from,to,stop);
		            	departureFlightInfo[i]=new DepartureFlightInfo(flightCourse,checkinCounter,boardingGate,time);
		                i++;
		            }
		            ret.close();  
		            db1.close();//关闭连接  
		        } catch (SQLException e) {  
		            e.printStackTrace();  
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
                int i=0;
                sql = "SELECT flight_off.Flight_No2,flight_off.InternationalOrLocal,flight_off.Starting_station,flight_off.Destination," +
				"flight_off.Staging_post,flight_off.Airline,bc_allocation.Bname,bc_allocation.Cname,bc_allocation.Time " +
				"FROM flight_off INNER JOIN bc_allocation ON flight_off.Flight_No2 = bc_allocation.Flight_No2 " +
				"WHERE flight_off.Airline = '"+airline+"'";//SQL语句  
		        db1 = new db_connection(sql);//创建db_connection对象  
		        String flightNumber=null;
		        boolean internationalOrLocal;
		        String from=null;
		        String to=null;
		        String stop=null;
		        String boardingGate=null;
		        String checkinCounter=null;
		        String time=null;
		        try {  
		            ret = db1.pst.executeQuery();//执行语句，得到结果集  
		            ret.last();
		            int rowNumber=ret.getRow();
		            ret.beforeFirst();
		            departureFlightInfo = new DepartureFlightInfo[rowNumber];
		            while (ret.next()) { 
		            	flightNumber=ret.getString("Flight_No2");
		                internationalOrLocal=ret.getBoolean("InternationalOrLocal");
		            	from=ret.getString("Starting_station");
		            	to=ret.getString("Destination");
		            	stop=ret.getString("Staging_post");
		            	airline=ret.getString("Airline");
		            	boardingGate=ret.getString("Bname");
		            	checkinCounter=ret.getString("Cname");
		            	time=ret.getString("Time");
		            	FlightCourse flightCourse=new FlightCourse(internationalOrLocal,false,flightNumber,airline,from,to,stop);
		            	departureFlightInfo[i]=new DepartureFlightInfo(flightCourse,checkinCounter,boardingGate,time);
		                i++;
		            }
		            ret.close();  
		            db1.close();//关闭连接  
		        } catch (SQLException e) {  
		            e.printStackTrace();  
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
                int i=0;
                sql = "SELECT flight_off.Flight_No2,flight_off.InternationalOrLocal,flight_off.Starting_station,flight_off.Destination," +
				"flight_off.Staging_post,flight_off.Airline,bc_allocation.Bname,bc_allocation.Cname,bc_allocation.Time " +
				"FROM flight_off INNER JOIN bc_allocation ON flight_off.Flight_No2 = bc_allocation.Flight_No2 " +
				"WHERE flight_off.Destination = '"+city+"'";//SQL语句  
		        db1 = new db_connection(sql);//创建db_connection对象  
		        String flightNumber=null;
		        boolean internationalOrLocal;
		        String from=null;
		        String to=null;
		        String stop=null;
		        String boardingGate=null;
		        String checkinCounter=null;
		        String airline=null;
		        String time=null;
		        try {  
		            ret = db1.pst.executeQuery();//执行语句，得到结果集  
		            ret.last();
		            int rowNumber=ret.getRow();
		            ret.beforeFirst();
		            departureFlightInfo = new DepartureFlightInfo[rowNumber];
		            while (ret.next()) { 
		            	flightNumber=ret.getString("Flight_No2");
		                internationalOrLocal=ret.getBoolean("InternationalOrLocal");
		            	from=ret.getString("Starting_station");
		            	to=ret.getString("Destination");
		            	stop=ret.getString("Staging_post");
		            	airline=ret.getString("Airline");
		            	boardingGate=ret.getString("Bname");
		            	checkinCounter=ret.getString("Cname");
		            	time=ret.getString("Time");
		            	FlightCourse flightCourse=new FlightCourse(internationalOrLocal,false,flightNumber,airline,from,to,stop);
		            	departureFlightInfo[i]=new DepartureFlightInfo(flightCourse,checkinCounter,boardingGate,time);
		                i++;
		            }
		            ret.close();  
		            db1.close();//关闭连接  
		        } catch (SQLException e) {  
		            e.printStackTrace();  
		        } 
		return departureFlightInfo;
    	
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
				"INNER JOIN lc_allocation ON flight_arrival.Flight_No = lc_allocation.Flight_No) WHERE flight_arrival.Flight_No = '"+flightnumber+"'";//SQL语句  
		        db1 = new db_connection(sql);//创建db_connection对象  
		        boolean internationalOrLocal;
		        String from=null;
		        String to=null;
		        String stop=null;
		        String airline=null;
		        String luggageCarousel=null;
		        String time=null;
		        try {  
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
		            db1.close();//关闭连接  
		        } catch (SQLException e) {  
		            e.printStackTrace();  
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
				"WHERE flight_arrival.Starting_station = '"+city+"' AND flight_arrival.Airline= '"+airline+"'";//SQL语句  
		        db1 = new db_connection(sql);//创建db_connection对象  
		        boolean internationalOrLocal;
		        String from=null;
		        String to=null;
		        String stop=null;
		        String luggageCarousel=null;
		        String time=null;
		        String flightnumber=null;
		        try {  
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
		            db1.close();//关闭连接  
		        } catch (SQLException e) {  
		            e.printStackTrace();  
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
				"INNER JOIN lc_allocation ON flight_arrival.Flight_No = lc_allocation.Flight_No) WHERE flight_arrival.Airline = '"+airline+"'";//SQL语句 
		        db1 = new db_connection(sql);//创建db_connection对象  
		        boolean internationalOrLocal;
		        String from=null;
		        String to=null;
		        String stop=null;
		        String luggageCarousel=null;
		        String time=null;
		        String flightnumber=null;
		        try {  
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
		            db1.close();//关闭连接  
		        } catch (SQLException e) {  
		            e.printStackTrace();  
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
				"INNER JOIN lc_allocation ON flight_arrival.Flight_No = lc_allocation.Flight_No) WHERE flight_arrival.Starting_station = '"+city+"'";//SQL语句   
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
		            db1.close();//关闭连接  
		        } catch (SQLException e) {  
		            e.printStackTrace();  
		        } 
		return arrivalFlightInfo;
    	
    }
    public  AirportResource[] searchAirportResource(String name){
    	AirportResource[] airportResource = null;
  /*
  * AirportResource[] searchA(String name);
  * 数据库操作：查询机场资源名称为该name的机场资源信息
  * 形参为机场资源名称，返回类型为AirportResource对象数组
  */
    	 int i=0;
    	 String location;
         String remark;
         String type;

         try {  
 		     sql= "SELECT * FROM boardinggate WHERE boardinggate.Bname='"+name+"'";
             db1= new db_connection(sql);//创建db_connection对象                  
             ret = db1.pst.executeQuery();//执行语句，得到结果集  
             type="登机门";
             ret.last();
	         int rowNumber=ret.getRow();
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
             {   sql= "SELECT * FROM checkincounter WHERE checkincounter.Cname='"+name+"'";
                 db1= new db_connection(sql);//创建db_connection对象 
                 ret = db1.pst.executeQuery();//执行语句，得到结果集  
                 ret.last();
 	             rowNumber=ret.getRow();
 	             ret.beforeFirst();
 	             airportResource = new AirportResource[rowNumber];	
                 type="值机柜台";
                 i=0;
                 while (ret.next()) {             	
//                  	name=ret.getString("SourseName");
//                  	location=ret.getString("SourseL");
//                  	remark=ret.getString("SourseR");
//                  	type=ret.getString("SourseR");
                  	name=ret.getString("Cname");
                  	location=ret.getString("Clocation");
                  	remark=ret.getString("Cremarks");
                  	airportResource[i]=new AirportResource(name,location,remark,type);
                    i++;
                  }
             }
              if(!ret.first())
             {   sql= "SELECT * FROM luggagecarousel WHERE luggagecarousel.Lname='"+name+"'";
                 db1= new db_connection(sql);//创建db_connection对象  
                 ret = db1.pst.executeQuery();//执行语句，得到结果集  
                 ret.last();
 	             rowNumber=ret.getRow();
 	             ret.beforeFirst();
 	             airportResource = new AirportResource[rowNumber];	             
                 type="行李转盘";
                 i=0;
                 while (ret.next()) {             	
//                  	name=ret.getString("SourseName");
//                  	location=ret.getString("SourseL");
//                  	remark=ret.getString("SourseR");
//                  	type=ret.getString("SourseR");
                  	name=ret.getString("Lname");
                  	location=ret.getString("Llocation");
                  	remark=ret.getString("Lremarks");
                  	airportResource[i]=new AirportResource(name,location,remark,type);
                    i++;
                  }
             }
              ret.close();  
              db1.close();//关闭连接  
         } catch (SQLException e) {  
             e.printStackTrace();  
         }
         return airportResource;
    }
    public PropertyFacility[] searchPropertyFacility(String name){
    	/*
//       * PropertyFacility[] searchP(String name);
//       * 数据库操作：查询物业设施名称为该name的物业设施信息
//       * 形参为物业设施名称，返回类型为PropertyFacility对象数组
//       */
    	 PropertyFacility[] propertyFacility = null;
    
      int i=0;
      sql = "SELECT* FROM facility WHERE facility.Fname = '"+name+"'";//SQL语句  
      db1= new db_connection(sql);//创建db_connection对象  
      
      String location;
      String remark;
      String type;
      String phone;
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
    	 if(title!=""&&title!=null&&time!=""&&time!=null)
    	     sql = "SELECT* FROM newscenter WHERE newscenter.title = '"+title+"' AND newscenter.Edit_time='"+time+"'";//SQL语句
    	 else
    		 sql = "SELECT* FROM newscenter WHERE newscenter.title = '"+title+"' OR newscenter.Edit_time='"+time+"'";//SQL语句
    	 db1= new db_connection(sql);//创建db_connection对象  
    	 String newsId;
    	 String content;
    	 String kind;
    	 String publisher_id;
    	 String attachment;
    	 try {  
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
}