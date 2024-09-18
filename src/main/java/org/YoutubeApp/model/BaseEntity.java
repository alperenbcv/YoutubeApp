package org.YoutubeApp.model;

import java.time.LocalDate;

public class BaseEntity {
	
	private Integer state;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	
	public BaseEntity() {
	}
	
	public BaseEntity(Integer state, LocalDate createdAt, LocalDate updatedAt) {
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
	
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	
	public LocalDate getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}
}