package org.YoutubeApp.repository;

import org.YoutubeApp.entity.Comment;
import org.YoutubeApp.entity.Video;
import org.YoutubeApp.utility.ConnectionProvider;
import org.YoutubeApp.utility.ICRUD;
import org.YoutubeApp.utility.SQLQueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VideoRepository implements ICRUD<Video> {
	private final ConnectionProvider connectionProvider;
	
	public VideoRepository() {
		this.connectionProvider = new ConnectionProvider();
	}
	
	@Override
	public Optional<Video> save(Video video) {
		connectionProvider.executeUpdate(SQLQueryBuilder.generateInsert(video, "tblvideo"));
		return Optional.of(video);
	}
	
	@Override
	public Optional<Video> update(Video video) {
		connectionProvider.executeUpdate(SQLQueryBuilder.generateUpdate(video,"tblvideo"));
		return Optional.of(video);
	}
	
	@Override
	public boolean delete(Long id) {
		connectionProvider.executeUpdate(SQLQueryBuilder.generateDelete(Video.class,"tblvideo",id));
		return true;
	}
	
	@Override
	public List<Video> findAll() {
		Optional<ResultSet> resultSet=connectionProvider.executeQuery("SELECT * FROM tblvideo ORDER BY id");
		if(resultSet.isPresent()){
			ResultSet rs = resultSet.get();
			return SQLQueryBuilder.generateList(Video.class,"tblvideo", rs);
		}
		return new ArrayList<>();
	}
	
	@Override
	public Optional<Video> findById(Long id) {
		Optional<ResultSet> resultSet=connectionProvider.executeQuery("SELECT * FROM tblvideo WHERE id=" + id);
		if(resultSet.isPresent()){
			ResultSet rs = resultSet.get();
			return SQLQueryBuilder.findById(Video.class,"tblvideo",id,rs);
		}
		return Optional.empty();
	}
}