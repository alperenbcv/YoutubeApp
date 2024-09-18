package org.YoutubeApp.modules;

import org.YoutubeApp.controller.CommentController;
import org.YoutubeApp.controller.LikeController;
import org.YoutubeApp.controller.UserController;
import org.YoutubeApp.controller.VideoController;
import org.YoutubeApp.entity.*;
import org.YoutubeApp.model.CommentModel;
import org.YoutubeApp.model.LikeModel;
import org.YoutubeApp.model.VideoModel;
import org.YoutubeApp.service.UserService;

import java.util.List;
import java.util.Scanner;

public class UserModule {
	static Scanner scanner = new Scanner(System.in);
	static UserController usercontroller = new UserController();
	static UserService userservice = new UserService();
	static VideoController videocontroller = new VideoController();
	static LikeController likecontroller = new LikeController();
	static CommentController commentcontroller = new CommentController();
	
	public static Integer myProfile() {
		System.out.println("\nMy Profile");
		System.out.println("1-Update Profile");
		System.out.println("2-Display My Videos");
		System.out.println("3-Display My Likes");
		System.out.println("4-Display My Comments");
		System.out.println("5-Back to Home");
		System.out.println("Enter your choice: ");
		int choice = scanner.nextInt();
		scanner.nextLine();
		return choice;
	}
	
	public static void myProfileSelection(Integer choice) {
		switch (choice) {
            case 1:
	            Integer i = updateProfile();
				updateProfileSelection(i);
	            break;
            case 2:
                displayMyVideos();
                break;
            case 3:
                displayMyLikes();
                break;
            case 4:
                displayMyComments();
                break;
            case 5:
                MenuModule.homeMenuOpt();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                myProfileSelection(myProfile());
                break;
        }
	}
	
	public static Integer updateProfile() {
		System.out.println("\nProfile Update");
		System.out.println("1-Change Username");
		System.out.println("2-Change Password");
		System.out.println("3-Change Email");
		System.out.println("4-Back to Home");
		System.out.println("Enter your choice: ");
		int choice = scanner.nextInt();
		scanner.nextLine();
		return choice;
    }
	
	public static void updateProfileSelection(Integer choice) {
		switch (choice) {
            case 1:
                changeUsername();
                break;
            case 2:
                changePassword();
                break;
            case 3:
                changeEmail();
                break;
            case 4:
                MenuModule.homeMenuOpt();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                updateProfileSelection(updateProfile());
                break;
        }
	}
	
	public static void displayMyVideos() {
		List<Video> videosByUserId = videocontroller.getVideosByUserId(MenuModule.user.getId());
		askUserForDisplay(videosByUserId);
		
    }
	
	public static void displayMyLikes() {
		List<Like> likesByUserId = likecontroller.getLikesByUserId(MenuModule.user.getId());
		for(Like like : likesByUserId) {
			Long id = like.getId();
			Long videoId = like.getVideoId();
			Video video = videocontroller.findById(videoId).orElse(null);
			Long userId = like.getUserId();
			User user = usercontroller.findById(userId).orElse(null);
			ELikeStatus likeStatus = like.getLikeStatus();
			LikeModel likeModel = new LikeModel(id, video, user, likeStatus);
			likeModel.setCreatedAt(like.getCreatedAt());
			System.out.println("Video: "+likeModel.getVideo().getTitle() + " - Status: " + likeModel.getLikeStatus() + " - Date: " +likeModel.getCreatedAt());
		}
	}
	
    public static void displayMyComments() {
		List<Comment> commentsByUserId = commentcontroller.getCommentsByUserId(MenuModule.user.getId());
		for(Comment comment : commentsByUserId) {
			Long id = comment.getId();
            Long videoId = comment.getVideoId();
            Video video = videocontroller.findById(videoId).orElse(null);
            Long userId = comment.getUserId();
            User user = usercontroller.findById(userId).orElse(null);
            String commentText = comment.getContent();
            CommentModel commentModel = new CommentModel(id,commentText, video, user);
			commentModel.setCreatedAt(comment.getCreatedAt());
            System.out.println("Video: "+commentModel.getVideo().getTitle() + " - Comment: " + commentModel.getContent() + " - Date: " +commentModel.getCreatedAt());
		}
    }
	
	public static void changeUsername() {
		System.out.print("Enter new username: ");
        String newUsername = scanner.nextLine();
		if(!userservice.checkUsername(newUsername)) {
			System.out.println("This username is already taken. Please choose a different one.");
			System.out.print("Enter new username: ");
			newUsername = scanner.nextLine();
		}
		MenuModule.user.setUsername(newUsername);
		usercontroller.update(MenuModule.user);
		System.out.println("Username updated successfully!");
	}
	
    public static void changePassword() {
		System.out.print("Enter new password: ");
		String newPassword = scanner.nextLine();
		MenuModule.user.setPassword(newPassword);
		usercontroller.update(MenuModule.user);
		System.out.println("Password updated successfully!");
    }
	
	public static void changeEmail() {
		System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();
		if(!userservice.checkEmail(newEmail)) {
			System.out.println("This email is already taken. Please choose a different one.");
            System.out.print("Enter new email: ");
            newEmail = scanner.nextLine();
		}
        MenuModule.user.setEmail(newEmail);
        usercontroller.update(MenuModule.user);
        System.out.println("Email updated successfully!");
	}
	
	private static void askUserForDisplay(List<Video> videos) {
		System.out.println("Search Results: " + videos.size() + " videos found!");
		for (int i = 0; i < videos.size(); i++) {
			System.out.println((i + 1) + "- " + videos.get(i).getTitle());
		}
		
		System.out.println("Do you want to display any of the results?");
		System.out.println("Enter the number of the video to display, or 0 to exit:");
		
		int choice = scanner.nextInt();
		scanner.nextLine();
		
		if (choice > 0 && choice <= videos.size()) {
			Video selectedVideo = videos.get(choice - 1);
			System.out.println(selectedVideo.getTitle() + " by " + MenuModule.user.getUsername());
			usersVideoInteraction(selectedVideo);
		}
	}
	
	private static void usersVideoInteraction(Video selectedVideo) {
		System.out.println("1-Display interactions");
		System.out.println("2-Update Video");
		System.out.println("3-Delete Video");
		System.out.println("4-Back to Home");
		System.out.println("Enter your choice: ");
		int choice = scanner.nextInt();
		interactionSelection(choice, selectedVideo);
	}
	
	private static void interactionSelection(Integer choice, Video selectedVideo) {
		switch (choice) {
            case 1:
                displayMyInteractions(selectedVideo);
                break;
            case 2:
                updateVideo(selectedVideo);
                break;
            case 3:
                deleteVideo(selectedVideo);
                break;
            case 4:
                MenuModule.homeMenuOpt();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
	}
	
	public static void displayMyInteractions(Video video) {
		System.out.println("Interactions for " + video.getTitle() + " by " + MenuModule.user.getUsername());
		System.out.println("Views: " + video.getViewCount());
		System.out.println("Likes: " + video.getLikeCount());
		System.out.println("Dislikes: " + video.getDislikeCount());
		usersVideoInteraction(video);
	}
	
	public static void updateVideo(Video video) {
		System.out.println("Enter new title: ");
        String newTitle = scanner.nextLine();
        video.setTitle(newTitle);
        
        System.out.println("Enter new description: ");
        String newDescription = scanner.nextLine();
        video.setDescription(newDescription);
        
        videocontroller.update(video);
        System.out.println("Video updated successfully!");
		usersVideoInteraction(video);
	}
	
	public static void deleteVideo(Video video) {
        videocontroller.delete(video.getId());
        System.out.println("Video deleted successfully!");
		usersVideoInteraction(video);
    }
}