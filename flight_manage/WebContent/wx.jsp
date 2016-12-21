<%@ page language="java" import="java.util.*,java.io.*,java.lang.reflect.*,javax.servlet.*,org.dom4j.*,com.entity.*" pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	System.out.println("");

	/* 读取接收到的xml消息 */
	StringBuffer sb = new StringBuffer();
	InputStream is = request.getInputStream();
	InputStreamReader isr = new InputStreamReader(is, "UTF-8");
	BufferedReader br = new BufferedReader(isr);
	String s = "";
	while ((s = br.readLine()) != null) {
		sb.append(s);
	}
	String xml = sb.toString();	//接收到微信端发送过来的xml数据
	System.out.println(xml);

	/* xml实体 */
	class ReceiveXmlEntity {
		private String ToUserName="";
		private String FromUserName="";
		private String CreateTime="";
		private String MsgType="";
		private String MsgId="";
		private String Event="";
		private String EventKey="";
		private String Ticket="";
		private String Latitude="";
		private String Longitude="";
		private String Precision="";
		private String PicUrl="";
		private String MediaId="";
		private String Title="";
		private String Description="";
		private String Url="";
		private String Location_X="";
		private String Location_Y="";
		private String Scale="";
		private String Label="";
		private String Content="";
		private String Format="";
		private String Recognition="";
		
		public String getRecognition() {
			return Recognition;
		}
		public void setRecognition(String recognition) {
			Recognition = recognition;
		}
		public String getFormat() {
			return Format;
		}
		public void setFormat(String format) {
			Format = format;
		}
		public String getContent() {
			return Content;
		}
		public void setContent(String content) {
			Content = content;
		}
		public String getLocation_X() {
			return Location_X;
		}
		public void setLocation_X(String locationX) {
			Location_X = locationX;
		}
		public String getLocation_Y() {
			return Location_Y;
		}
		public void setLocation_Y(String locationY) {
			Location_Y = locationY;
		}
		public String getScale() {
			return Scale;
		}
		public void setScale(String scale) {
			Scale = scale;
		}
		public String getLabel() {
			return Label;
		}
		public void setLabel(String label) {
			Label = label;
		}
		public String getTitle() {
			return Title;
		}
		public void setTitle(String title) {
			Title = title;
		}
		public String getDescription() {
			return Description;
		}
		public void setDescription(String description) {
			Description = description;
		}
		public String getUrl() {
			return Url;
		}
		public void setUrl(String url) {
			Url = url;
		}
		public String getPicUrl() {
			return PicUrl;
		}
		public void setPicUrl(String picUrl) {
			PicUrl = picUrl;
		}
		public String getMediaId() {
			return MediaId;
		}
		public void setMediaId(String mediaId) {
			MediaId = mediaId;
		}
		public String getEventKey() {
			return EventKey;
		}
		public void setEventKey(String eventKey) {
			EventKey = eventKey;
		}
		public String getTicket() {
			return Ticket;
		}
		public void setTicket(String ticket) {
			Ticket = ticket;
		}
		public String getLatitude() {
			return Latitude;
		}
		public void setLatitude(String latitude) {
			Latitude = latitude;
		}
		public String getLongitude() {
			return Longitude;
		}
		public void setLongitude(String longitude) {
			Longitude = longitude;
		}
		public String getPrecision() {
			return Precision;
		}
		public void setPrecision(String precision) {
			Precision = precision;
		}
		public String getEvent() {
			return Event;
		}
		public void setEvent(String event) {
			Event = event;
		}
		public String getMsgId() {
			return MsgId;
		}
		public void setMsgId(String msgId) {
			MsgId = msgId;
		}
		public String getToUserName() {
			return ToUserName;
		}
		public void setToUserName(String toUserName) {
			ToUserName = toUserName;
		}
		public String getFromUserName() {
			return FromUserName;
		}
		public void setFromUserName(String fromUserName) {
			FromUserName = fromUserName;
		}
		public String getCreateTime() {
			return CreateTime;
		}
		public void setCreateTime(String createTime) {
			CreateTime = createTime;
		}
		public String getMsgType() {
			return MsgType;
		}
		public void setMsgType(String msgType) {
			MsgType = msgType;
		}
	}
	
	/* 解析器 */
	class ReceiveXmlProcess {
	/**
	 * 解析微信xml消息
	 * @param strXml
	 * @return
	 */
	public ReceiveXmlEntity getMsgEntity(String strXml){
			ReceiveXmlEntity msg = null;
			try {
				if (strXml.length() <= 0 || strXml == null)
					return null;
				 
				// 将字符串转化为XML文档对象
				Document document = DocumentHelper.parseText(strXml);
				// 获得文档的根节点
				Element root = document.getRootElement();
				// 遍历根节点下所有子节点
				Iterator<?> iter = root.elementIterator();
				
				// 遍历所有结点
				msg = new ReceiveXmlEntity();
				//利用反射机制，调用set方法
				//获取该实体的元类型
				Class<?> c = Class.forName("demo.entity.ReceiveXmlEntity");
				msg = (ReceiveXmlEntity)c.newInstance();//创建这个实体的对象
				
				while(iter.hasNext()){
					Element ele = (Element)iter.next();
					//获取set方法中的参数字段（实体类的属性）
					Field field = c.getDeclaredField(ele.getName());
					//获取set方法，field.getType())获取它的参数数据类型
					Method method = c.getDeclaredMethod("set"+ele.getName(), field.getType());
					//调用set方法
					method.invoke(msg, ele.getText());
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("xml 格式异常: "+ strXml);
				e.printStackTrace();
			}
			return msg;
		}
	}	
	
	ReceiveXmlProcess receiveXmlProcess = new ReceiveXmlProcess();
	ReceiveXmlEntity receiveXmlEntity = receiveXmlProcess.getMsgEntity(xml);
	String fromName = receiveXmlEntity.getFromUserName();
	System.out.println("fromName:" + fromName);
	
	response.getWriter().print("123");
	response.getWriter().flush();
	response.getWriter().close();
%>