/**
 * 
 */
package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Issac Ding
 * @since 2015年6月1日 下午3:51:35
 * @version 1.0
 */
public class Database {
	
    private static String driver = "com.mysql.jdbc.Driver";

    /** URL指向要访问的数据库名 */
    private static String url = "jdbc:mysql://127.0.0.1:3306/";

    /** MySQL配置时的用户名 */
    public static String user = "root";
    /** MySQL配置时的密码 */
    public static String password = "root";
    /** 数据库名 */
    public static String databaseName = "nbalabala";
    /** 数据库配置文件路径 */
    public static String configPath = "config/database.conf";
    
    public static Connection conn;
    static {
    	try {
    		// 从文件中读取数据库帐户名，密码和数据库名称
			FileReader fr = new FileReader(configPath);
			BufferedReader reader = new BufferedReader(fr);
			user = reader.readLine();
			password = reader.readLine();
			databaseName = reader.readLine();
			url += databaseName; // URL指向要访问的数据库名
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    static{
    	try { 
            // 加载驱动程序
            Class.forName(driver);
            // 连续数据库
            conn = DriverManager.getConnection(url, user, password);
            if(!conn.isClosed()) 
             System.out.println("Succeeded connecting to the Database!");
    	} catch(ClassNotFoundException e) {
            System.out.println("Sorry,can`t find the Driver!"); 
            e.printStackTrace();
           } catch(SQLException e) {
            e.printStackTrace();
           } catch(Exception e) {
            e.printStackTrace();
           } 
    }
	

}
