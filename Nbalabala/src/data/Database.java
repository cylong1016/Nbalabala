/**
 * 
 */
package data;

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

    // URL指向要访问的数据库名scutcs
    private static String url = "jdbc:mysql://127.0.0.1:3306/nbalabala";

    // MySQL配置时的用户名
    public static String user = "root"; 

    // MySQL配置时的密码cyl941016
    public static String password = "cyl941016";
    
    public static Connection conn;
    
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
