package org.YoutubeApp.repository;

import org.YoutubeApp.entity.ERole;
import org.YoutubeApp.entity.User;
import org.YoutubeApp.utility.ConnectionProvider;
import org.YoutubeApp.utility.ICRUD;
import org.YoutubeApp.utility.SQLQueryBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements ICRUD<User> {
	private String sql = "";
	private final ConnectionProvider connectionProvider;
	private final PreparedStatement ps;
	private final Connection connection;
	
	public UserRepository() {
		this.connectionProvider = new ConnectionProvider();
		this.ps = connectionProvider.getPreparedStatement();
		this.connection=connectionProvider.getConn();
	}
	
	@Override
	public Optional<User> save(User user) {
		connectionProvider.executeUpdate(SQLQueryBuilder.generateInsert(user,"tbluser"));
		return Optional.of(user);
	}
	
	@Override
	public Optional<User> update(User user) {
		connectionProvider.executeUpdate(SQLQueryBuilder.generateUpdate(user,"tbluser"));
		return Optional.of(user);
	}
	
	@Override
	public boolean delete(Long id) {
		connectionProvider.executeUpdate(SQLQueryBuilder.generateDelete(User.class,"tbluser",id));
		return true;
	}
	
	@Override
	public List<User> findAll() {
		Optional<ResultSet> resultSet=connectionProvider.executeQuery("SELECT * FROM tbluser ORDER BY id");
		if(resultSet.isPresent()){
			ResultSet rs = resultSet.get();
			return SQLQueryBuilder.generateList(User.class,"tbluser", rs);
		}
		return new ArrayList<>();
	}
	
	@Override
	public Optional<User> findById(Long id) {
		sql="SELECT * FROM tbluser WHERE id=" + id;
		Optional<ResultSet> resultSet=connectionProvider.executeQuery(sql);
		if(resultSet.isPresent()){
			ResultSet rs = resultSet.get();
			return SQLQueryBuilder.findById(User.class,"tbluser",id,rs);
		}
		return Optional.empty();
	}
}