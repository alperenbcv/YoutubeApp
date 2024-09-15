package org.YoutubeApp.modules;

import org.YoutubeApp.controller.CommentController;
import org.YoutubeApp.controller.LikeController;
import org.YoutubeApp.controller.UserController;
import org.YoutubeApp.controller.VideoController;
import org.YoutubeApp.entity.*;
import org.YoutubeApp.model.VideoModel;
import org.YoutubeApp.service.VideoService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static java.lang.StringTemplate.STR;

public class VideoModule {
	static Scanner scanner = new Scanner(System.in);
	static VideoController videoController = new VideoController();
	static UserController userController = new UserController();
	static CommentController commentController = new CommentController();
	static LikeController likeController = new LikeController();
	
	public static Integer videoSearch() {
		System.out.println("1-Search by Title");
		System.out.println("2-Search by Category");
		System.out.println("3-Search by Date");
		System.out.println("Back to Home");
		System.out.println("Enter your choice: ");
		int choice = scanner.nextInt();
		scanner.nextLine();
		return choice;
	}
	
	public static void videoSearchSelection(Integer choice) {
		switch (choice) {
			case 1:
				searchByTitle();
				break;
			case 2:
				searchByCategory();
				break;
//			case 3:
//				searchByDate();
//				break;
			case 4:
				break;
			default:
				System.out.println("Invalid choice! Please try again");
		}
	}
	
//	private static void searchByDate() {
//		System.out.println("Enter start date (YYYY-MM-DD): ");
//        String startDate = scanner.nextLine();
//        System.out.println("Enter end date (YYYY-MM-DD): ");
//        String endDate = scanner.nextLine();
//
//        List<Video> videoList = videoController.findByDate(startDate, endDate);
//        if (videoList.size() == 0) {
//            System.out.println("No videos found in the given date range. Please try again. ");
//        }
//        else {
//            askUserForDisplay(videoList);
//        }
//	}
	
	private static void searchByCategory() {
		System.out.println("1-Music");
		System.out.println("2-Sports");
		System.out.println("3-Technology");
		System.out.println("4-Games");
		System.out.println("5-Entertainment");
		System.out.println("Enter your choice: ");
		int categoryChoice = scanner.nextInt();
		
		switch (categoryChoice) {
			case 1:
				List<Video> musicVideos = videoController.findByCategory(ECategory.MUSIC);
				if (musicVideos.size() == 0) {
					System.out.println("No videos found in the Music category. Please try again. ");
					break;
				}
				else {
					askUserForDisplay(musicVideos);
				}
				break;
			case 2:
				List<Video> sportVideos = videoController.findByCategory(ECategory.SPORTS);
				if (sportVideos.size() == 0) {
					System.out.println("No videos found in the Sport category. Please try again. ");
					break;
				}else {
				askUserForDisplay(sportVideos);
				}
				break;
			case 3:
				List<Video> technologyVideos = videoController.findByCategory(ECategory.TECHNOLOGY);
				if (technologyVideos.size() == 0) {
					System.out.println("No videos found in the Technology category. Please try again. ");
					break;
				}
				else {
					askUserForDisplay(technologyVideos);
				}
				break;
			case 4:
				List<Video> gameVideos = videoController.findByCategory(ECategory.GAMES);
				if (gameVideos.size() == 0) {
					System.out.println("No videos found in the Game category. Please try again. ");
					break;
				}
				else {
					askUserForDisplay(gameVideos);
					}
				break;
			case 5:
				List<Video> entertainmentVideos = videoController.findByCategory(ECategory.ENTERTAINMENT);
				if (entertainmentVideos.size() == 0) {
					System.out.println("No videos found in the Entertainment category. Please try again. ");
					break;
				}
				else {
					askUserForDisplay(entertainmentVideos);
				}
				break;
			default:
				System.out.println("Invalid category choice! Please try again.");
                break;
			
		}
	}
	
	
	private static void searchByTitle() {
		System.out.println("Enter the title of the video: ");
		String title = scanner.nextLine();
		
		List<Video> byTitle = videoController.findByTitle(title);
		if (byTitle.size() == 0) {
			System.out.println("No videos found with the given title. Please try again. ");
			return;
		}
		else {
			System.out.println("Search Results: " + byTitle.size() + " videos found!");
			for (int i = 0; i < byTitle.size(); i++) {
				System.out.println((i + 1) + "- " + byTitle.get(i).getTitle());
			}
			
			System.out.println("Do you want to display any of the results?");
			System.out.println("Enter the number of the video to display, or 0 to exit:");
			
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			if (choice > 0 && choice <= byTitle.size()) {
				Video selectedVideo = byTitle.get(choice - 1);
				incrementViewCount(selectedVideo);
				VideoModel videoModel = videoToModel(selectedVideo);
				displayVideoDetails(videoModel);
				videoInteractionMenu(selectedVideo);
			}
			else {
				System.out.println("Exiting without displaying any video.");
			}
		}
	}
	
	public static void videoInteractionMenu(Video video) {
		while (true) {
			System.out.println("Choose an option:");
			System.out.println("1- Like the video");
			System.out.println("2- Dislike the video");
			System.out.println("3- Add a comment");
			System.out.println("4- Exit to main menu");
			
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			switch (choice) {
				case 1:
					Like like=new Like(video.getId(), MenuModule.user.getId(), ELikeStatus.LIKE);
					likeController.save(like);
					videoController.update(video);
					System.out.println("You liked the video.");
					break;
				case 2:
					Like dislike=new Like(video.getId(), MenuModule.user.getId(), ELikeStatus.DISLIKE);
					video.setDislikeCount(video.getDislikeCount() + 1);
					likeController.save(dislike);
					videoController.update(video);
					System.out.println("You disliked the video.");
					break;
				case 3:
					System.out.println("Enter your comment:");
					String commentContent = scanner.nextLine();
					Comment newComment = new Comment(commentContent, video.getId(),MenuModule.user.getId()); // Mevcut
					commentController.save(newComment);
					System.out.println("Your comment has been added.");
					break;
				case 4:
					System.out.println("Exiting to the main menu...");
					return;
				default:
					System.out.println("Invalid option. Please try again.");
					break;
			}
		}
	}
	
	private static void incrementViewCount(Video video) {
		video.setViewCount(video.getViewCount() + 1);
		videoController.update(video);
	}
	
	public static VideoModel videoToModel(Video video) {
		
		Long id = video.getId();
		Long uploaderId = video.getUploaderId();
		Optional<User> byId = userController.findById(uploaderId);
		User user=new User();
		if(byId.isPresent()) {
			user=byId.get();
		}
		String title = video.getTitle();
		String description = video.getDescription();
		ECategory category = video.getCategory();
		List<Comment> commentsByVideoId = commentController.findCommentsByVideoId(id);
		Long viewCount = video.getViewCount();
		Integer likeCount = likeController.getLikeCountByVideoId(id);
		Integer dislikeCount = likeController.getDislikeCountByVideoId(id);
		VideoModel videoModel = new VideoModel(id, uploaderId, user, title, description, category, commentsByVideoId, viewCount.intValue(), likeCount, dislikeCount);
		return videoModel;
	}
	public static void displayVideoDetails(VideoModel videoModel) {
		int width = 60;
		
		printLine(width);
		printCenteredText("Title: " + videoModel.getTitle(), width);
		printCenteredText("Uploader: " + videoModel.getUploader().getName(), width);
		printCenteredText("Category: " + videoModel.getCategory().name(), width);
		printCenteredText("Description: " + videoModel.getDescription(), width);
		printCenteredText("Views: " + videoModel.getViewCount(), width);
		printCenteredText("Likes: " + videoModel.getLikeCount() + " | Dislikes: " + videoModel.getDislikeCount(), width);
		printLine(width);
		
		System.out.println("Comments:");
		for (Comment comment : videoModel.getComments()) {
			printLeftAlignedText(comment.getUserId() + ": " + comment.getContent(), width);
		}
		
		printLine(width);
	}
	
	private static void printLine(int width) {
		for (int i = 0; i < width; i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	private static void printCenteredText(String text, int width) {
		int padding = (width - text.length()) / 2;
		System.out.print("-");
		for (int i = 1; i < padding; i++) {
			System.out.print(" ");
		}
		System.out.print(text);
		for (int i = text.length() + padding; i < width - 1; i++) {
			System.out.print(" ");
		}
		System.out.println("-");
	}
	
	private static void printLeftAlignedText(String text, int width) {
		System.out.print("- ");
		System.out.print(text);
		for (int i = text.length() + 2; i < width - 1; i++) {
			System.out.print(" ");
		}
		System.out.println("-");
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
			incrementViewCount(selectedVideo);
			VideoModel videoModel = videoToModel(selectedVideo);
			displayVideoDetails(videoModel);
			videoInteractionMenu(selectedVideo);
		}
	}
	
	public static void videoUpload() {
		System.out.println("Do you want to upload a video? (y/n)");
		String uploadChoice = scanner.nextLine();
        if (uploadChoice.equalsIgnoreCase("y")) {
			System.out.println("Enter the title of the video:");
			String title = scanner.nextLine();
			
            System.out.println("Enter the description of the video:");
			String description = scanner.nextLine();
			
			List<ECategory> categories = Arrays.asList(ECategory.MUSIC, ECategory.SPORTS, ECategory.TECHNOLOGY,
			                                     ECategory.GAMES, ECategory.ENTERTAINMENT);
            System.out.println("Choose a category (1-Music 2-Sports 3-Technology 4-Games 5-Entertainment):");
			int categoryChoice = scanner.nextInt();
			if(categoryChoice < 1 || categoryChoice > categories.size()) {
				System.out.println("Invalid category. Please try again.");
				System.out.println("1-Music 2-Sports 3-Technology 4-Games 5-Entertainment :");
				categoryChoice = scanner.nextInt();
			}
			ECategory category = categories.get(categoryChoice - 1);
			videoController.save(new Video(MenuModule.user.getId(), title, description, category));
			System.out.println("Video uploaded successfully. Returning to the main menu...");
        }
	}
}