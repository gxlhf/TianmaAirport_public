/* 新闻类
 * title:新闻标题
 * time:发布时间
 * content:新闻内容
 * kind:新闻分类
 * attachment:附件
 * publisher_id:发布人ID
 * newsId:新闻ID
 */
package com.entity;

import com.dao.AdminDao;

public class News {
	String title;
	String time;
	String content;
	String kind;
	String attachment;
	String publisher_id;
	String publisher_name;
	String newsId;
	public News(String title, String time, String content, String kind, String attachment, String publisher_id, String newsId) {
		this.title = title;
		this.time = time;
		this.content = content;
		this.kind = kind;
		this.attachment = attachment;
		this.publisher_id = publisher_id;
		AdminDao adminDao = new AdminDao();
		Admin[] publisher = adminDao.searchAdmin0(publisher_id);
		this.publisher_name = publisher[0].getName();
		this.newsId = newsId;
	}
	public News(String title, String time, String content, String kind, String attachment, String publisher_id) {
		this.title = title;
		this.time = time;
		this.content = content;
		this.kind = kind;
		this.attachment = attachment;
		this.publisher_id = publisher_id;
		AdminDao adminDao = new AdminDao();
		Admin[] publisher = adminDao.searchAdmin0(publisher_id);
		this.publisher_name = publisher[0].getName();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getPublisher_id() {
		return publisher_id;
	}
	public void setPublisher_id(String publisher_id) {
		this.publisher_id = publisher_id;
	}
	public String getPublisher_name() {
		return publisher_name;
	}
	public void setPublisher_name(String publisher_name) {
		this.publisher_name = publisher_name;
	}
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	
}
