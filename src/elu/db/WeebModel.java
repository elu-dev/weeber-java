package elu.db;

import java.sql.Date;

public class WeebModel {
	private int id, user_id, likes;
	private String content, username;
	private Date post_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getPost_date() {
		return post_date;
	}
	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}
	public WeebModel(int id, int user_id, String username, String content, int likes, Date post_date) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.username = username;
		this.likes = likes;
		this.content = content;
		this.post_date = post_date;
	}
	
	
}
