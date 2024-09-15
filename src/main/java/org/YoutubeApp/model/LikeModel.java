package org.YoutubeApp.model;

import org.YoutubeApp.entity.ELikeStatus;
import org.YoutubeApp.entity.User;
import org.YoutubeApp.entity.Video;

public class LikeModel extends BaseEntity {
	private Long id;
	private Video video;
	private User user;
	private ELikeStatus likeStatus;
	
	public LikeModel() {
	}
	
	public LikeModel(Long id, Video video, User user, ELikeStatus likeStatus) {
		this.id = id;
		this.video = video;
		this.user = user;
		this.likeStatus = likeStatus;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public ELikeStatus getLikeStatus() {
		return likeStatus;
	}
	
	public void setLikeStatus(ELikeStatus likeStatus) {
		this.likeStatus = likeStatus;
	}
	
	@Override
	public String toString() {
		return "LikeModel{" + "id=" + getId() + ", video=" + getVideo() + ", user=" + getUser() + ", likeStatus=" + getLikeStatus() + '}';
	}
}