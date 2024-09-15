package org.YoutubeApp.modules;

import org.YoutubeApp.controller.UserController;
import org.YoutubeApp.entity.ERole;
import org.YoutubeApp.entity.User;
import org.YoutubeApp.service.UserService;

import java.util.Scanner;

public class RegisterModule {
	static Scanner scanner = new Scanner(System.in);
	static UserController userController = new UserController();
	static UserService userService = new UserService();
	
	public static void register() {
		System.out.println("---Register---0:Back");
		System.out.println("Name: ");
		String name = scanner.nextLine();
		if(name.equalsIgnoreCase("0")) {
			MenuModule.firstMenu();
            return;
		}
		System.out.println("Surname: ");
		String surname = scanner.nextLine();
		System.out.println("Email: ");
		String email = scanner.nextLine();
		while (userService.checkEmail(email)) {
			System.out.println("This email is already taken. Please enter a different one.");
			System.out.println("Email: ");
			email = scanner.nextLine();
		}
		System.out.println("Username: ");
		String username = scanner.nextLine();
		while (userService.checkUsername(username)) {
			System.out.println("This username is already taken. Please enter a different one.");
			System.out.println("Username: ");
			username = scanner.nextLine();
		}
		System.out.println("Password: ");
		String password = scanner.nextLine();
		userController.save(new User(name, surname, email, username, password, ERole.USER));
	}
}