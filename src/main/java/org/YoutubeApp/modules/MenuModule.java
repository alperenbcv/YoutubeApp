package org.YoutubeApp.modules;

import org.YoutubeApp.entity.User;
import org.YoutubeApp.entity.Video;

import java.util.Scanner;

public class MenuModule {
	public static User user;
	public static Scanner scanner = new Scanner(System.in);
	
	public static void firstMenu() {
		while(user==null) {
			Integer selection = firstMenuOpt();
			firstMenuSelection(selection);
		}
		while(user!=null) {
			Integer selection = homeMenuOpt();
			homeMenuSelection(selection);
		}
	}
	
	public static Integer firstMenuOpt() {
		boolean isValid = false;
		System.out.println("---Youtube App---");
		System.out.println("1-Register");
		System.out.println("2-Login");
		System.out.println("3-Exit");
		System.out.println("Selection: ");
		int selection = scanner.nextInt();
		return selection;
	}
	
	public static void firstMenuSelection(Integer selection) {
		switch (selection) {
            case 1:
                RegisterModule.register();
                break;
            case 2:
                LoginModule.login();
                break;
            case 3:
                System.out.println("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid selection. Please try again.");
        }
	}
	
	public static Integer homeMenuOpt() {
		boolean isValid = false;
		System.out.println("\n---Welcome "+user.getUsername()+" ---");
		System.out.println("1-Video Search");
		System.out.println("2-Video Upload");
		System.out.println("3-User List");
		System.out.println("4-Profile");
		System.out.println("0-Logout");
		System.out.println("Selection: ");
		int selection = scanner.nextInt();
		return selection;
	}
	
	public static void homeMenuSelection(Integer selection) {
		switch (selection) {
			case 1:
				Integer choice = VideoModule.videoSearch();
				VideoModule.videoSearchSelection(choice);
				break;
			case 2:
				VideoModule.videoUpload();
				break;
//			case 3:
//				UserModule.listAll();
//				break;
//			case 4:
//				UserModule.myProfile();
//				break;
			case 9:
				System.out.println("Logout...");
			default:
				System.out.println("Invalid selection. Please try again.");
		}
	}
}