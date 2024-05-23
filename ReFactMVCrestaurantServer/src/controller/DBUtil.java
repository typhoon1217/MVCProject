package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	public Connection getConnection() {

		String filePath = "./db.properties";
		Connection con = null;
		try {
			//db.properties 디비주소 사용자명 암호 가져오기
			Properties properties = new Properties(); // Added '=' to assign a new Properties object
			properties.load(new FileReader(filePath));
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			// Oracle database connection object reference variable

			// Load the Oracle JDBC driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("데이터베이스 드라이버 로드 성공");
			// Establish the database connection
			con = DriverManager.getConnection(url, user, password);
			System.out.println("데이터베이스 접속 성공");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 드라이버 로드 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 연결 실패");
		}
		return con;
	}

}
