import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 创建时间：2015年3月10日 下午1:09:57 项目名称：PerformanceTest
 * 
 * @author Xiaohan Ding
 * @version 1.0
 * @since JDK 1.7 文件名称：TestDB.java 类说明：
 */
public class TestDB {

	// 驱动程序名
	static String driver = "com.mysql.jdbc.Driver";

	static String url = "jdbc:mysql://127.0.0.1:3306/test";

	// MySQL配置时的用户名
	static String user = "root";

	// MySQL配置时的密码
	static String password = "root";

	static Connection conn;

	static {
		try {
			// 加载驱动程序
			Class.forName(driver);

			conn = DriverManager.getConnection(url, user, password);

			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");
		} catch (ClassNotFoundException e) {

			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Statement statement = null;
		String sql1 = "drop table if exists t_user";
		String sql2 = "create table if not exists t_user(name varchar(10),d1 integer,d2 integer,d3 integer,d4 integer,d5 integer)";

		try {
			statement = conn.createStatement();
			statement.executeUpdate(sql1);
			statement.executeUpdate(sql2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		new TestDB().run();
	}

	public void run() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("small.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				String[] s = line.split(" ");
				Statement statement = conn.createStatement();
				String sql = "insert into t_user() values(" + "'" + s[0] + "'"
						+ ',' + s[1] + ',' + s[2] + ',' + s[3] + ',' + s[4]
						+ ',' + s[5] + ')';
				statement.executeUpdate(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
