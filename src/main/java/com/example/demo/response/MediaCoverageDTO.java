package com.example.demo.response;

public class MediaCoverageDTO {

	private String userId;
	private String title;
	private String id;
	private String body;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "MediaCoverageDTO [userId=" + userId + ", title=" + title + ", id=" + id + ", body=" + body + "]";
	}
	
	
}
