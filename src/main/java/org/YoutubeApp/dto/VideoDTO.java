package org.YoutubeApp.dto;

import org.YoutubeApp.entity.ECategory;

public class VideoDTO extends BaseEntityDTO {
	
	private Long uploaderId;
	private String title;
	private String description;
	private ECategory category;
	private Integer viewCount=0;
	private Integer likeCount=0;
	private Integer dislikeCount=0;
	
	public VideoDTO() {
	}
	
	public VideoDTO(Long uploaderId, String title, String description, ECategory category, Integer viewCount, Integer likeCount, Integer dislikeCount) {
		this.uploaderId = uploaderId;
		this.title = title;
		this.description = description;
		this.category = category;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
	}
	
	public ECategory getCategory() {
		return category;
	}
	
	public void setCategory(ECategory category) {
		this.category = category;
	}
	
	public Integer getDislikeCount() {
		return dislikeCount;
	}
	
	public void setDislikeCount(Integer dislikeCount) {
		this.dislikeCount = dislikeCount;
	}
	
	public Long getUploaderId() {
		return uploaderId;
	}
	
	public void setUploaderId(Long uploaderId) {
		this.uploaderId = uploaderId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getViewCount() {
		return viewCount;
	}
	
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	
	public Integer getLikeCount() {
		return likeCount;
	}
	
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	
	@Override
	public String toString() {
		return "Video{title='" + getTitle() + '\'' + ", description='" + getDescription() + '\'' + ", category=" + getCategory() + ", viewCount=" + getViewCount() + ", likeCount=" + getLikeCount() + ", dislikeCount=" + getDislikeCount() + '}';
	}
}