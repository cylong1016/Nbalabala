package capture;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 从上次抓取完之后继续抓取新的比赛
 * @author cylong
 * @version 2015年6月16日 上午9:02:53
 */
public class UpdateMatch extends Match {
	
	private String currentSeason = "2014-15";
	private String currentSeasonURL = "http://www.basketball-reference.com/leagues/NBA_2015_games.html";

	public void capture() {
		new Capture().start();
	}

	public void update() {
		initMatchID();
		String sql = "SELECT MAX(date) FROM match_profile";
		try {
			Statement state = mysqlConn.createStatement();
			ResultSet resSet = state.executeQuery(sql);
			resSet.next();
			maxDate = resSet.getString(1);
			captureNameAndScore(currentSeason, currentSeasonURL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将match_id改成数据库中最大的 + 1
	 * @author cylong
	 * @version 2015年6月16日  上午9:23:54
	 */
	public void initMatchID() {
		// 获得数据库中最大ID
		String sql = "SELECT MAX(match_id) FROM match_profile";
		try {
			Statement state = mysqlConn.createStatement();
			ResultSet resSet = state.executeQuery(sql);
			resSet.next();
			String maxID = resSet.getString(1);
			matchID = Integer.parseInt(maxID) + 1; // 接下来的match_id
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	class Capture extends Thread {
		
		public void run() {
			update();
		}
	}

}
