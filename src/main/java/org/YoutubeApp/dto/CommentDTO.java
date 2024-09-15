package org.YoutubeApp.dto;

public class CommentDTO extends BaseEntityDTO {
	private Long id;
	private String content;
	private Long videoId;
	private Long userId;
	
	public CommentDTO() {
	}
	
	public CommentDTO(String content, Long videoId, Long userId,Long createdAt, Long updatedAt) {
		super(createdAt, updatedAt);
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