package org.YoutubeApp.model;

import org.YoutubeApp.entity.Comment;
import org.YoutubeApp.entity.ECategory;
import org.YoutubeApp.entity.Like;
import org.YoutubeApp.entity.User;

import java.util.List;

public class VideoModel extends BaseEntity {
	private Long id;
	private Long uploaderId;
	private User uploader;
	private String title;
	private String description;
	private ECategory category;
	private List<Comment> comments;
	private Integer viewCount;
	private Integer likeCount;
	private Integer dislikeCount;
	
	public VideoModel() {
	}
	
	public VideoModel(Long id, Long uploaderId, User user,String title, String description, ECategory category, List<Comment> comments, Integer viewCount, Integer likeCount, Integer dislikeCount) {
		this.id = id;
		this.uploaderId = uploaderId;
		this.uploader = user;
		this.title = title;
		this.description = description;
		this.category = category;
		this.comments = comments;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.dislikeCount = dislikeCount;
	}
	
	public Long getId() {
		return id;
	}
	
	public User getUploader() {
		return uploader;
	}
	
	public void setUploader(User uploader) {
		this.uploader = uploader;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public ECategory getCategory() {
		return category;
	}
	
	public void setCategory(ECategory category) {
		this.category = category;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
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
	
	public Integer getDislikeCount() {
		return dislikeCount;
	}
	
	public void setDislikeCount(Integer dislikeCount) {
		this.dislikeCount = dislikeCount;
	}
}