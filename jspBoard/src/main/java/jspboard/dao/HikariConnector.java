package jspboard.dao;
import java.sql.Connection;
import java.sql.SQLException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
public class HikariConnector {
	
	HikariDataSource dataSource;
	
	public HikariConnector(String drivername, String jdbcUrl, String username, String password) {
		HikariConfig config = new HikariConfig();
		
		config.setDriverClassName(drivername);
		config.setJdbcUrl(jdbcUrl);
		config.setUsername(username);
		config.setPassword(password);
		
		dataSource = new HikariDataSource(config);
		
	}
	
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	public void close() {
		dataSource.close();
	}
}
