package org.YoutubeApp.dto;

public class BaseEntityDTO {
	
	private Long createdAt;
	private Long updatedAt;
	
	public BaseEntityDTO() {
	}
	
	public BaseEntityDTO(Long createdAt, Long updatedAt) {
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	
	public Long getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	
	public Long getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
}