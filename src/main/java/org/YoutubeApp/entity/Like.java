package org.YoutubeApp.entity;

import java.time.LocalDateTime;

public class Like extends BaseEntity {
	private Long id;
	private Long videoId;
	private Long userId;
	private ELikeStatus likeStatus;
	
	public Like() {
	}
	
	public Like(Long videoId, Long userId, ELikeStatus likeStatus,Integer state, Long createdAt,Long updatedAt) {
		super(state, createdAt, updatedAt);
		this.videoId = videoId;
		this.userId = userId;
		this.likeStatus = likeStatus;
	}
	
	public Like( Long id, Long videoId, Long userId, ELikeStatus likeStatus,Integer state, Long createdAt,Long updatedAt) {
		super(state, createdAt, updatedAt);
		this.id = id;
		this.videoId = videoId;
		this.userId = userId;
		this.likeStatus = likeStatus;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public ELikeStatus getLikeStatus() {
		return likeStatus;
	}
	
	public void setLikeStatus(ELikeStatus likeStatus) {
		this.likeStatus = likeStatus;
	}
	
	@Override
	public String toString() {
		return "Like{" + "id=" + getId() + ", videoId=" + getVideoId() + ", userId=" + getUserId() + ", likeStatus=" + getLikeStatus() + '}';
	}
}