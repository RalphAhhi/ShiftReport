package com.fv.shiftreport.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.codec.binary.Base64;

public class DatabaseUtil {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/fv?autoReconnect=true&amp;useSSL=false";
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

	private static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}

}
