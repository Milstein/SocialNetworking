package socialnetworking.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	public Connection Get_Connection() throws Exception {
		try {
			// String connectionURL =
			// "jdbc:mysql://132.178.128.200:3306/db_socialnetwork";
			String connectionURL = "jdbc:mysql://localhost:3306/db_socialnetwork";
			java.sql.Connection connection = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// connection = DriverManager.getConnection(connectionURL,
			// "remoteuser", "root");
			connection = DriverManager.getConnection(connectionURL, "root",
					"123456789");
			return connection;
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}
}
