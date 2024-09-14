package org.YoutubeApp.utility;

import java.sql.*;
import java.util.Optional;

import static org.YoutubeApp.utility.Constants.*;

public class ConnectionProvider {
	private Connection conn;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	private boolean openConnection(){
		try{
			conn = DriverManager.getConnection("jdbc:postgresql://"+ DB_HOSTNAME+":"+DB_PORT+"/"+DB_NAME, DB_USERNAME, DB_PASSWORD);
			return true;
		}
		catch (SQLException e) {
			System.out.println("Connection error..."+e.getMessage());
			return false;
		}
	}
	
	private boolean closeConnection(){
		try{
			if(!conn.isClosed()) {
				conn.close();
				return true;
			}
			else{
                System.out.println("Connection has already closed...");
                return false;
            }
		}
		catch (SQLException e) {
			System.out.println("Connection close error..."+e.getMessage());
            return false;
		}
	}
	
	public boolean executeUpdate(String sql){
		try{
			if(openConnection()){
				conn.prepareStatement(sql).executeUpdate();
                return closeConnection();
			} else{
				System.out.println("Connection open error...");
				return false;
			}
		}
		catch (SQLException e) {
			System.out.println("SQL execution error..."+e.getMessage());
			return false;
		}
	}
	
	public Optional<ResultSet> executeQuery(String sql){
		try{
			if(openConnection()){
				ResultSet rs = conn.prepareStatement(sql).executeQuery();
				return Optional.ofNullable(rs);
			}else{
				System.out.println("Connection open error...");
                return Optional.empty();
			}
		}
		catch (SQLException e) {
			System.out.println("SQL execution error..."+e.getMessage());
            return Optional.empty();
		}
	}
	
	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}
	
	public void setPreparedStatement(PreparedStatement preparedStatement) {
		this.preparedStatement = preparedStatement;
	}
	
	public Connection getConn() {
		return conn;
	}
	
	public void setConn(Connection conn) {
		this.conn = conn;
	}
}