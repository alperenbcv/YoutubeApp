package org.YoutubeApp.dto;

import org.YoutubeApp.entity.ELikeStatus;

public class LikeDTO {
	
	private Long videoId;
	private Long userId;
	private ELikeStatus likeStatus;
	
	public LikeDTO() {
	}
	
	public LikeDTO(Long videoId, Long userId, ELikeStatus likeStatus) {
		this.videoId = videoId;
		this.userId = userId;
		this.likeStatus = likeStatus;
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
		return "Like{" + ", videoId=" + getVideoId() + ", userId=" + getUserId() + ", likeStatus=" + getLikeStatus() + '}';
	}
}