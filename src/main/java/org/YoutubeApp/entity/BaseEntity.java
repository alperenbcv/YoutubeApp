package org.YoutubeApp.entity;

import java.time.LocalDateTime;

public class BaseEntity {
	
	private Integer state;
	private Long createdAt;
	private Long updatedAt;
	
	public BaseEntity() {
	}
	
	public BaseEntity(Integer state, Long createdAt, Long updatedAt) {
		this.state = state;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public Integer getState() {
		return state;
	}
	
	public void setState(Integer state) {
		this.state = state;
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