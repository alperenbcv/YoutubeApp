package org.YoutubeApp.service;

import org.YoutubeApp.entity.User;
import org.YoutubeApp.repository.UserRepository;
import org.YoutubeApp.utility.ICRUDService;

import java.util.List;
import java.util.Optional;

public class UserService implements ICRUDService<User> {
	
	private final UserRepository userRepository;
	
	public UserService() {
		this.userRepository = new UserRepository();
	}
	
	@Override
	public Optional<User> save(User user) {
		try {
			userRepository.save(user);
			System.out.println("User saved successfully!");
		}
		catch (Exception e) {
			System.err.println("Service save Hata!");
		}
		return Optional.ofNullable(user);
	}
	
	@Override
	public Optional<User> update(User user) {
		if (findById(user.getId()).isPresent()) {
			try {
				userRepository.update(user);
				System.out.println("User updated successfully!");
			}
			catch (Exception e) {
				System.err.println("Service update Hata!");
				return Optional.empty();
			}
		}
		else {
			System.out.println("User not found! Update failed!");
			return Optional.empty();
		}
		return Optional.of(user);
	}
	
	@Override
	public boolean delete(Long id) {
		if (findById(id).isPresent()) {
			try {
				userRepository.delete(id);
				System.out.println("User deleted successfully!");
				return true;
			}
			catch (Exception e) {
				System.err.println("Service delete Error!");
			}
		}
		else {
			System.out.println("User not found! Deletion failed!");
		}
		return false;
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
}