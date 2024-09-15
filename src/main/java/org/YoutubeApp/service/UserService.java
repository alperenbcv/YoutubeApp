package org.YoutubeApp.service;

import org.YoutubeApp.dto.UserDTO;
import org.YoutubeApp.dto.VideoDTO;
import org.YoutubeApp.entity.ERole;
import org.YoutubeApp.entity.User;
import org.YoutubeApp.entity.Video;
import org.YoutubeApp.repository.UserRepository;
import org.YoutubeApp.utility.ConnectionProvider;
import org.YoutubeApp.utility.ICRUDService;
import org.YoutubeApp.utility.SQLQueryBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
			if(!checkEmail(user.getEmail())) {
				if(!checkUsername(user.getUsername())) {
					userRepository.save(user);
					System.out.println("User saved successfully!");
				}
				else {
                    System.out.println("Username already exists!");
                    return Optional.empty();
                }
			}
			else {
                System.out.println("Email already exists!");
                return Optional.empty();
            }
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
	
	public List<UserDTO> findAllAsDTO() {
		List<User> all = userRepository.findAll();
		List<UserDTO> userDTOS = new ArrayList<>();
		for(User user : all) {
			UserDTO userDTO = new UserDTO(user.getName(), user.getSurname(), user.getEmail(), user.getUsername(), user.getRole());
            userDTOS.add(userDTO);
		}
		return userDTOS;
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}
	
	public Optional<UserDTO> findByUsernameAsDTO(String username) {
		Optional<User> byUsername = userRepository.findByUsername(username);
		if(byUsername.isPresent()) {
			User user = byUsername.get();
            UserDTO userDTO = new UserDTO(user.getName(), user.getSurname(), user.getEmail(), user.getUsername(), user.getRole());
            return Optional.of(userDTO);
		}
		return Optional.empty();
	}
	public boolean checkEmail(String email) {
		ConnectionProvider connectionProvider = new ConnectionProvider();
		Optional<ResultSet> resultSet =
				connectionProvider.executeQuery("SELECT * FROM tbluser WHERE email ILIKE '" + email + "'");
		if(resultSet.isPresent()) {
			ResultSet rs = resultSet.get();
            try {
                if (rs.next()) {
                    return true;
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
		}
		return false;
	}
	
	public boolean checkUsername(String username) {
		ConnectionProvider connectionProvider = new ConnectionProvider();
		Optional<ResultSet> resultSet =
				connectionProvider.executeQuery("SELECT * FROM tbluser WHERE username ILIKE '" + username + "'");
		if (resultSet.isPresent()) {
			ResultSet rs = resultSet.get();
			try {
				if (rs.next()) {
					return true;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}