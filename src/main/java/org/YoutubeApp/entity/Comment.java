package org.YoutubeApp.entity;

import java.time.LocalDateTime;

public class Comment extends BaseEntity {
	private Long id;
	private String content;
	private Long videoId;
	private Long userId;
	
	public Comment() {
	}
	
	public Comment(String content, Long videoId, Long userId,Integer state, Long createdAt, Long updatedAt) {
		super(state, createdAt, updatedAt);
		this.content = content;
		this.videoId = videoId;
		this.userId = userId;
	}
	
	public Comment(Long id, String content, Long videoId, Long userId,Integer state, Long createdAt, Long updatedAt) {
		super(state, createdAt, updatedAt);
		this.id = id;
		this.content = content;
		this.videoId = videoId;
		this.userId = userId;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Long getVideoId() {
		return videoId;
	}
	
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "Comment{" + "id=" + getId() + ", content='" + getContent() + '\'' + ", videoId=" + getVideoId() + ", userId=" + getUserId() + '}';
	}
}