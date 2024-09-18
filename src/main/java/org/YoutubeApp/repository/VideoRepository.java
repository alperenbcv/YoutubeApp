package org.YoutubeApp.repository;

import org.YoutubeApp.entity.Comment;
import org.YoutubeApp.entity.ECategory;
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
	
	public List<Video> findByTitle(String title) {
		Optional<ResultSet> resultSet = connectionProvider.executeQuery("SELECT * FROM tblvideo WHERE title ILIKE '%" + title + "%'");
		if (resultSet.isPresent()) {
			ResultSet rs = resultSet.get();
			return SQLQueryBuilder.findByTitle(Video.class, rs);
		}
		return new ArrayList<>();
	}
	
	public List<Video> findByCategory(ECategory category) {
		Optional<ResultSet> resultSet=connectionProvider.executeQuery("SELECT * FROM tblvideo WHERE category = '" + category+ "'");
		if(resultSet.isPresent()){
			ResultSet rs = resultSet.get();
			return SQLQueryBuilder.findByTitle(Video.class,rs);
		}
		return new ArrayList<>();
	}
	
	public List<Video> findByDate(String startDate, String endDate) {
		String sql = "SELECT * FROM tblvideo WHERE createdat BETWEEN '" + startDate + "' AND '" + endDate + "'";
		Optional<ResultSet> resultSet = connectionProvider.executeQuery(sql);
		if(resultSet.isPresent()){
			ResultSet rs = resultSet.get();
			return SQLQueryBuilder.findByTitle(Video.class,rs);
		}
		return new ArrayList<>();
	}
	
	public List<Video> getVideosByUserId(Long userId) {
		String sql="SELECT * FROM tblvideo WHERE uploaderid=" + userId;
		Optional<ResultSet> resultSet=connectionProvider.executeQuery(sql);
		if(resultSet.isPresent()){
			ResultSet rs = resultSet.get();
			return SQLQueryBuilder.generateList(Video.class,"tblvideo", rs);
		}
		return new ArrayList<>();
	}
	
	public List<Video> findAllByTitle(){
		String sql="SELECT * FROM tblvideo ORDER BY title LIMIT 10";
        Optional<ResultSet> resultSet=connectionProvider.executeQuery(sql);
        if(resultSet.isPresent()){
            ResultSet rs = resultSet.get();
            return SQLQueryBuilder.generateList(Video.class,"tblvideo", rs);
        }
        return new ArrayList<>();
	}
	
	public List<Video> findAllByViewCount(){
		String sql="SELECT * FROM tblvideo ORDER BY viewcount DESC LIMIT 10";
        Optional<ResultSet> resultSet=connectionProvider.executeQuery(sql);
        if(resultSet.isPresent()){
            ResultSet rs = resultSet.get();
            return SQLQueryBuilder.generateList(Video.class,"tblvideo", rs);
        }
        return new ArrayList<>();
	}
	
	public List<Video> findAllByLikeCount(){
		String sql="SELECT * FROM tblvideo ORDER BY likecount DESC LIMIT 10";
        Optional<ResultSet> resultSet=connectionProvider.executeQuery(sql);
        if(resultSet.isPresent()){
            ResultSet rs = resultSet.get();
            return SQLQueryBuilder.generateList(Video.class,"tblvideo", rs);
        }
        return new ArrayList<>();
	}
	
	public List<Video> findAllByDislikeCount(){
		String sql="SELECT * FROM tblvideo ORDER BY dislikecount DESC LIMIT 10";
        Optional<ResultSet> resultSet=connectionProvider.executeQuery(sql);
        if(resultSet.isPresent()){
            ResultSet rs = resultSet.get();
            return SQLQueryBuilder.generateList(Video.class,"tblvideo", rs);
        }
        return new ArrayList<>();
	}
}