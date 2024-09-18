package org.YoutubeApp.repository;

import org.YoutubeApp.entity.ERole;
import org.YoutubeApp.entity.Like;
import org.YoutubeApp.entity.User;
import org.YoutubeApp.utility.ConnectionProvider;
import org.YoutubeApp.utility.ICRUD;
import org.YoutubeApp.utility.SQLQueryBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LikeRepository implements ICRUD<Like> {
	private final ConnectionProvider connectionProvider;
	private final PreparedStatement ps;
	private final Connection connection;
	
	public LikeRepository() {
		this.connectionProvider = new ConnectionProvider();
		this.ps = connectionProvider.getPreparedStatement();
		this.connection=connectionProvider.getConn();
	}
	
	@Override
	public Optional<Like> save(Like like) {
		connectionProvider.executeUpdate(SQLQueryBuilder.generateInsert(like,"tbllike"));
		return Optional.of(like);
	}
	
	@Override
	public Optional<Like> update(Like like) {
		connectionProvider.executeUpdate(SQLQueryBuilder.generateUpdate(like,"tbllike"));
		return Optional.of(like);
	}
	
	@Override
	public boolean delete(Long id) {
		connectionProvider.executeUpdate(SQLQueryBuilder.generateDelete(Like.class,"tbllike",id));
		return true;
	}
	
	@Override
	public List<Like> findAll() {
		Optional<ResultSet> resultSet=connectionProvider.executeQuery("SELECT * FROM tbllike ORDER BY id");
		if(resultSet.isPresent()){
			ResultSet rs = resultSet.get();
			return SQLQueryBuilder.generateList(Like.class,"tbllike", rs);
		}
		return new ArrayList<>();
	}
	
	@Override
	public Optional<Like> findById(Long id) {
		Optional<ResultSet> resultSet=connectionProvider.executeQuery("SELECT * FROM tbllike WHERE id=" + id);
		if(resultSet.isPresent()){
			ResultSet rs = resultSet.get();
			return SQLQueryBuilder.findById(Like.class,"tbllike",id,rs);
		}
		return Optional.empty();
	}
	
	public List<Like> findByQuery(String query) {
		Optional<ResultSet> resultSet=connectionProvider.executeQuery(query);
		if(resultSet.isPresent()){
			ResultSet rs = resultSet.get();
			return SQLQueryBuilder.generateList(Like.class,"tbllike",rs);
		}
		return new ArrayList<>();
	}
	
	public Integer findCountByQuery(String query) {
		Optional<ResultSet> resultSet = connectionProvider.executeQuery(query);
		if (resultSet.isPresent()) {
			ResultSet resultSet1 = resultSet.get();
			
			try {
				if (resultSet1.next()) {
					int count = resultSet1.getInt(1);
					return count;
				} else {
					System.out.println("No results found.");
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Query failed or returned no results.");
		}
		return 0;
	}
	
	public List<Like> getLikesByUserId(Long userId) {
		String sql="SELECT * FROM tbllike WHERE userid=" + userId;
		Optional<ResultSet> resultSet=connectionProvider.executeQuery(sql);
		if(resultSet.isPresent()){
			ResultSet rs = resultSet.get();
			return SQLQueryBuilder.generateList(Like.class,"tbllike", rs);
		}
		return new ArrayList<>();
	}
}