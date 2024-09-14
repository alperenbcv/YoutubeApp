package org.YoutubeApp;

import org.YoutubeApp.controller.CommentController;
import org.YoutubeApp.controller.LikeController;
import org.YoutubeApp.controller.UserController;
import org.YoutubeApp.controller.VideoController;
import org.YoutubeApp.entity.ECategory;
import org.YoutubeApp.entity.ERole;
import org.YoutubeApp.entity.User;
import org.YoutubeApp.entity.Video;
import org.YoutubeApp.repository.UserRepository;

public class Main {
	public static void main(String[] args) {
		//public User(String name, String surname, String email, String username, String password, ERole role, Integer state, Long createdAt, Long updatedAt)
//		User user=new User("Alperen", "Bicav", "alperen@gmail.com", "alperen", "123456", ERole.USER,1, System.currentTimeMillis(), System.currentTimeMillis());
//		User user1=new User("Alperen", "Bicav", "alpe@gmail.com", "alper", "123456", ERole.USER,1,
//		                    System.currentTimeMillis(), System.currentTimeMillis());
//
		UserController userController=new UserController();
		VideoController videoController=new VideoController();
		LikeController likeController=new LikeController();
		CommentController commentController=new CommentController();
		
		Video video = new Video(1L,"Video 1", "Description 1", ECategory.TECHNOLOGY);
		videoController.save(video);
//		userController.save(user1);
//		userController.findAll().forEach(System.out::println);
//		userController.findById(1L);
	
	}
}