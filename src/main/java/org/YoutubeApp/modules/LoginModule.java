package org.YoutubeApp.modules;

import org.YoutubeApp.entity.ERole;
import org.YoutubeApp.entity.User;
import org.YoutubeApp.utility.ConnectionProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class LoginModule {
	private static Scanner scanner = new Scanner(System.in);
	private static ConnectionProvider connectionProvider = new ConnectionProvider(); // Static initialization
	
	public void login() {
		System.out.println("---Login---");
		System.out.println("Username: ");
		String username = scanner.nextLine();
		System.out.println("Password: ");
		String password = scanner.nextLine();
		
		Optional<User> user = doLogin(username, password);
		if (user.isPresent()) {
			System.out.println("Login successful.");
			MenuInitializer.user=user.get();
		}
		else {
			System.out.println("Invalid username or password.");
		}
	}
	
	public static Optional<User> doLogin(String username, String password) {
		String sql = "SELECT * FROM tbluser WHERE username = '" + username + "' AND password = '" + password + "'";
			Optional<ResultSet> resultSet = connectionProvider.executeQuery(sql);
			if (resultSet.isPresent()) {
				try {
					ResultSet rs = resultSet.get();
					if (rs.next()) {
						Long rsId = rs.getLong("id");
						String rsUsername = rs.getString("username");
						String rsPassword = rs.getString("password");
						String rsEmail = rs.getString("email");
						String rsName = rs.getString("name");
						String rsSurname = rs.getString("surname");
						ERole role = ERole.valueOf(rs.getString("role"));
						return Optional.of(new User(rsId, rsName, rsSurname, rsEmail, rsUsername, rsPassword, role));
					}
				}
				catch (SQLException e) {
					System.out.println("SQL error: " + e.getMessage());
				}
			}
		return Optional.empty();
	}
}