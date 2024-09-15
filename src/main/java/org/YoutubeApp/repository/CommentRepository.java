package org.YoutubeApp.repository;

import org.YoutubeApp.entity.Comment;
import org.YoutubeApp.entity.Like;
import org.YoutubeApp.utility.ConnectionProvider;
import org.YoutubeApp.utility.ICRUD;
import org.YoutubeApp.utility.SQLQueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentRepository implements ICRUD<Comment> {
	private final ConnectionProvider connectionProvider;
	private final PreparedStatement ps;
	private final Connection connection;
	
	public CommentRepository() {
		this.connectionProvider = new ConnectionProvider();
		this.ps = connectionProvider.getPreparedStatement();
		this.connection=connectionProvider.getConn();
	}
	
	@Override
	public Optional<Comment> save(Comment comment) {
		connectionProvider.executeUpdate(SQLQueryBuilder.generateInsert(comment, "tblcomment"));
		return Optional.of(comment);
	}
	
	@Override
	public Optional<Comment> update(Comment comment) {
		connectionProvider.executeUpdate(SQLQueryBuilder.generateUpdate(comment,"tblcomment"));
		return Optional.of(comment);
	}
	
	@Override
	public boolean delete(Long id) {
		connectionProvider.executeUpdate(SQLQueryBuilder.generateDelete(Comment.class,"tblcomment",id));
		return true;
	}
	
	@Override
	public List<Comment> findAll() {
		Optional<ResultSet> resultSet=connectionProvider.executeQuery("SELECT * FROM tblcomment ORDER BY id");
		if(resultSet.isPresent()){
			ResultSet rs = resultSet.get();
			return SQLQueryBuilder.generateList(Comment.class,"tblcomment", rs);
		}
		return new ArrayList<>();
	}
	
	@Override
	public Optional<Comment> findById(Long id) {
		Optional<ResultSet> resultSet=connectionProvider.executeQuery("SELECT * FROM tblcomment WHERE id=" + id);
		if(resultSet.isPresent()){
			ResultSet rs = resultSet.get();
			return SQLQueryBuilder.findById(Comment.class,"tblcomment",id,rs);
		}
		return Optional.empty();
	}
	
	public List<Comment> findCommentsByVideoId(Long videoId) {
		Optional<ResultSet> resultSet=connectionProvider.executeQuery("SELECT * FROM tblcomment WHERE videoid=" + videoId);
        if(resultSet.isPresent()){
            ResultSet rs = resultSet.get();
            return SQLQueryBuilder.generateList(Comment.class,"tblcomment", rs);
        }
        return new ArrayList<>();
	}
}