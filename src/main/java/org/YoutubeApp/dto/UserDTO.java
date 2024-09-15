package org.YoutubeApp.dto;

import org.YoutubeApp.entity.ERole;

public class UserDTO{
	
	private String name;
	private String surname;
	private String email;
	private String username;
	private ERole role;
	
	public UserDTO() {
	}
	
	
	public UserDTO(String name, String surname, String email, String username, ERole role) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.username = username;
		this.role = role;
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
	
	public ERole getRole() {
		return role;
	}
	
	public void setRole(ERole role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "User{name='" + getName() + '\'' + ", surname='" + getSurname() + '\'' + ", email='" + getEmail() + '\'' + ", username='" + getUsername() + '\'' + ", role=" + getRole()+'}';
	}
}