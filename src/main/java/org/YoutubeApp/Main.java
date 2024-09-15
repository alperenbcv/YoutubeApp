package org.YoutubeApp;


import org.YoutubeApp.entity.Like;
import org.YoutubeApp.modules.MenuModule;
import org.YoutubeApp.repository.LikeRepository;
import org.YoutubeApp.utility.ConnectionProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class Main {
	public static void main(String[] args) {
		MenuModule.firstMenu();
	
	}
}