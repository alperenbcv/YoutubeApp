package org.YoutubeApp.modules;

import org.YoutubeApp.controller.UserController;
import org.YoutubeApp.entity.ERole;
import org.YoutubeApp.entity.User;

import java.util.Scanner;

public class RegisterModule {
	static Scanner scanner = new Scanner(System.in);
	static UserController userController = new UserController();
	
	public void register() {
		boolean invalidMail = true;
		boolean invalidUsername = true;
        System.out.println("---Register---");
		System.out.println("Name: ");
		String name = scanner.nextLine();
		System.out.println("Surname: ");
		String surname = scanner.nextLine();
		while(invalidMail) {
			System.out.println("Email: ");
			String email = scanner.nextLine();
			if(userService.checkEmail(email)){
				System.out.println("This email is already taken. Please enter a different one.");
			}
			else{
				while(invalidUsername) {
					System.out.println("Username: ");
					String username = scanner.nextLine();
					if(userService.checkUsername(username)){
                        System.out.println("This username is already taken. Please enter a different one.");
                    }
					else {
						System.out.println("Password: ");
						String password = scanner.nextLine();
						userController.save(new User(name, surname, email, username, password, ERole.USER));
					}
				}
            }
			
		}
		
		
	}
}