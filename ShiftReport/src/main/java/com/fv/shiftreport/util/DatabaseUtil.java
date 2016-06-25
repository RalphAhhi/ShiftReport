package com.fv.shiftreport.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

public class DatabaseUtil {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/?autoReconnect=true&amp;useSSL=false";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD="DBPASS";
	
	
	public static Connection getDBConnection() {

		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {      
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
					new String(Base64.decodeBase64(SettingUtil.getValue(DB_PASSWORD))));
			dbConnection.setAutoCommit(false);
			return dbConnection;

		} catch (SQLException e) {
				e.printStackTrace();
		}

		return dbConnection;

	}

	public static java.sql.Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}
	
	public static java.sql.Date converToJdcbDate(Date date) {
		return new java.sql.Date(date.getTime());
	}
	
	public static long sqlGetLastId(String descrption) throws Exception{
		Connection conn = null;
		try{
			String sql =  "insert into fv.idgenerator (description) values (?)";
			conn = getDBConnection();
			PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, descrption);
			ps.execute() ;
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
		       return generatedKeys.getLong(1);
		    }else {
		       throw new SQLException("Creating user failed, no ID obtained.");
		    }
			
		}catch(Exception e){
			conn.rollback();
			Util.writeToFile("Error", getCurrentTimeStamp()+":::"+e.getMessage());
			throw new Exception(e);
		}finally{
			conn.commit();
			if(null != conn){
				conn.close();
			}
		}
	
	}
	

}
