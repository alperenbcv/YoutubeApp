package org.YoutubeApp.controller;

import org.YoutubeApp.dto.UserDTO;
import org.YoutubeApp.entity.Comment;
import org.YoutubeApp.entity.Like;
import org.YoutubeApp.entity.User;
import org.YoutubeApp.entity.Video;
import org.YoutubeApp.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserController {
	
	private final UserService userService;
	
	public UserController() {
        this.userService = new UserService();
    }
	
	public Optional<User> save(User user) {
		try{
			return userService.save(user);
		} catch (Exception e) {
			System.out.println("Controller Save Hata!");
		}
		return Optional.empty();
	}
	
	public Optional<User> update(User user) {
		try{
			return userService.update(user);
		} catch (Exception e) {
			System.err.println("Controller Update Hata!");
		}
		return Optional.empty();
	}
	
	public boolean delete(Long id) {
		try{
            return userService.delete(id);
        } catch (Exception e) {
            System.err.println("Controller Delete Hata!");
        }
        return false;
	}
	
	public List<UserDTO> findAllAsDTO() {
		try{
            return userService.findAllAsDTO();
        } catch (Exception e) {
            System.err.println("Controller FindAllAsDTO Hata!");
        }
        return new ArrayList<>();
	}
	
	public List<User> findAll() {
		try{
            return userService.findAll();
        } catch (Exception e) {
            System.err.println("Controller FindAll Hata!");
        }
        return new ArrayList<>();
	}
	
	public Optional<User> findById(Long id) {
		Optional<User> user = userService.findById(id);
		user.ifPresentOrElse(
				u -> System.out.println("Controller User bulundu: " + u.getUsername()),
				() -> System.out.println("Controller Böyle bir user bulunamadı.")
		);
		return user;
	}
	
	public Optional<UserDTO> findByUsernameAsDTO(String username) {
		Optional<UserDTO> userDTO = userService.findByUsernameAsDTO(username);
        userDTO.ifPresentOrElse(
                u -> System.out.println("Controller UserDTO bulundu: " + u.getUsername()),
                () -> System.out.println("Controller Böyle bir userDTO bulunamadı.")
        );
        return userDTO;
	}
	
}