package mysql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author cylong
 * @version 2015年5月19日 上午12:25:17
 */
public class MySQL {

	private Connection conn;

	public MySQL() {
		connectMySQL();
	}

	/**
	 * 连接数据库
	 * @author cylong
	 * @version 2015年5月21日 下午8:36:57
	 */
	private void connectMySQL() {
		try {
			//加载驱动，这一句也可写为：Class.forName("com.mysql.jdbc.Driver");
			Class.forName(MySQLConf.DRIVER);
			//建立到MySQL的连接
			conn = DriverManager.getConnection(MySQLConf.URL, MySQLConf.USER, MySQLConf.PASSWORD);
			if (!conn.isClosed()) {
				System.out.println("Succeeded connecting to the Database!");
			}
			DatabaseMetaData metaData = conn.getMetaData();
			ResultSet res = metaData.getTables(null, null, null, null);
			System.out.println("init...");
			while(res.next()) {
				String sql = "TRUNCATE " + res.getString("TABLE_NAME");
				System.out.println(sql);
				Statement statement = conn.createStatement();
				statement.execute(sql);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConn() {
		return this.conn;
	}

}
