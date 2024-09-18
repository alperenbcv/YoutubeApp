package org.YoutubeApp.model;

import org.YoutubeApp.entity.User;
import org.YoutubeApp.entity.Video;

import java.time.LocalDate;

public class CommentModel extends BaseEntity {
	private Long id;
	private String content;
	private Video video;
	private User user;
	
	public CommentModel() {
	}
	
	public CommentModel(Long id,String content, Video video, User user) {
		this.id = id;
		this.content = content;
		this.video = video;
		this.user = user;
	}
	
	public CommentModel(Long id, String content, Video video, User user, Integer state, LocalDate createdAt, LocalDate updatedAt) {
		super(state, createdAt, updatedAt);
		this.id = id;
		this.content = content;
		this.video = video;
		this.user = user;
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
	
	public Video getVideo() {
		return video;
	}
	
	public void setVideo(Video video) {
		this.video = video;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Comment{" + "id=" + getId() + ", content='" + getContent() + '\'' + ", video=" + getVideo() + ", user=" + getUser() + '}';
	}
}