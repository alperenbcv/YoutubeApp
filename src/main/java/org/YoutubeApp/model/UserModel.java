package org.YoutubeApp.model;

import org.YoutubeApp.entity.Comment;
import org.YoutubeApp.entity.ERole;
import org.YoutubeApp.entity.Like;
import org.YoutubeApp.entity.Video;

import java.util.List;

public class UserModel extends BaseEntity {
	
	private Long id;
	private String name;
	private String surname;
	private String email;
	private String username;
	private String password;
	private ERole role;
	private List<Video> videos;
	private List<Comment> comments;
	private List<Like> likes;
	
	public UserModel() {
	}
	
	public UserModel(Long id, String name, String surname, String email, String username, String password, ERole role, List<Video> videos, List<Comment> comments, List<Like> likes) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
		this.videos = videos;
		this.comments = comments;
		this.likes = likes;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public ERole getRole() {
		return role;
	}
	
	public void setRole(ERole role) {
		this.role = role;
	}
	
	public List<Video> getVideos() {
		return videos;
	}
	
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public List<Like> getLikes() {
		return likes;
	}
	
	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}
	
	@Override
	public String toString() {
		return "UserModel{" + "id=" + getId() + ", name='" + getName() + '\'' + ", surname='" + getSurname() + '\'' + ", email='" + getEmail() + '\'' + ", username='" + getUsername() + '\'' + ", password='" + getPassword() + '\'' + ", role=" + getRole() + ", videos=" + getVideos() + ", comments=" + getComments() + ", likes=" + getLikes() + '}';
	}
}