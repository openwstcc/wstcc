package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	private final static String JDBC_CLASS = "com.mysql.jdbc.Driver";
	private final static String JDBC_URL = "jdbc:mysql://localhost:3306/TCCSERVER";
	private final static String JDBC_USERID = "root";
	private final static String JDBC_PASSWORD = "root";
	private Connection con;
	private static JDBCUtil jdbcutil;

	private JDBCUtil() {
	}

	public Connection getConnection() throws SQLException {
		if (con == null) {
			try {
				Class.forName(JDBC_CLASS);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		if (con == null || (con != null && !con.isValid(3))) {
			con = DriverManager.getConnection(JDBC_URL, JDBC_USERID, JDBC_PASSWORD);
		}
		return con;
	}

	public static JDBCUtil getInstance() {
		if (jdbcutil == null) {
			jdbcutil = new JDBCUtil();
		}
		return jdbcutil;
	}
}