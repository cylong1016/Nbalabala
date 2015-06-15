package capture;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * URL = http://www.82games.com/
 * @author cylong
 * @version 2015年6月15日 下午10:26:47
 */
public class PlayerClutch extends NBAData {
	
	/** 表中的ID */
	private int ID = 1;
	
	public PlayerClutch() {
		this.captureUrl = "http://www.82games.com";
	}

	/**
	 * @see capture.NBAData#capture()
	 */
	@Override
	public void capture() {
		new Capture().start();
	}

	private void capturePlayerClutch() {
		ArrayList<String> teamURLs = getTeamURLs();
		for(String teamURL : teamURLs) {
			ArrayList<String> playerURLs = getPlayersURL(teamURL);
			for(String playerURL : playerURLs) {
				System.out.println(playerURL);
				ArrayList<String> playerClutch = new ArrayList<String>();
				BufferedReader reader = getReader(playerURL);
				String nameReg = "<font.*?>(?<name>.*?)</font><br>";
				Pattern namePattern = Pattern.compile(nameReg);
				String minReg = "<td align=center><font size=-1>&nbsp; (?<min>\\d+%)</font></center></td>";
				Pattern minPattern = Pattern.compile(minReg);
				try {
					String temp = null;
					while((temp = reader.readLine()) != null) {
						Matcher nameMatcher = namePattern.matcher(temp);
						if(nameMatcher.find()) {
							String name = nameMatcher.group("name");
							playerClutch.add(name);
						}
						if(Pattern.matches("<h3>Clutch Statistics</h3>", temp)) { // 找到Clutch Statistics表
							while((temp = reader.readLine()) != null) {
								Matcher minMatcher = minPattern.matcher(temp);
								if(minMatcher.find()) {
									String min = minMatcher.group("min");
									playerClutch.add(min);
								}
								// 找到Pts表头
								if(Pattern.matches("<td width=.*?><center><b><font size.*?>Pts</font></b></center></td>", temp)) {
									for(int i = 0; i < 11; i++) {
										temp = reader.readLine();
									}
									String ptsReg = "<td align=right><font size.*?>(?<pts>.*?)&nbsp;</font></td>";
									Pattern ptsPattern = Pattern.compile(ptsReg);
									Matcher ptsMatcher = ptsPattern.matcher(temp);
									if(ptsMatcher.find()) {
										String pts = ptsMatcher.group("pts");
										if(Pattern.matches("\\d+\\.\\d+", pts)) {
											playerClutch.add(pts);
										} else {
											playerClutch.add("0.0");
										}
									}
								}
							}
						}
					}
					if(playerClutch.size() < 3) {
						playerClutch.add("0.0");
					}
					insertIntoPlayerClutch(playerClutch); // 插入到player_clutch表
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 插入player_clutch表
	 * @param playerClutch
	 * @author cylong
	 * @version 2015年6月16日  上午12:10:49
	 */
	private void insertIntoPlayerClutch(ArrayList<String> playerClutch) {
		String sql = "INSERT INTO player_clutch VALUES ("
				+ ID + ", "
				+ "'" + playerClutch.get(0).replaceAll("'", "\\\\'") + "', "
				+ percentToDouble(playerClutch.get(1)) + ", "
				+ playerClutch.get(2)
				+ ")";
		ID++;
		System.out.println(sql);
		try {
			Statement statement = mysqlConn.createStatement();
			statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据球队链接得到球员链接
	 * @param teamURL 球队URL
	 * @return ArrayList 该球队所有球员URL
	 * @author cylong
	 * @version 2015年6月15日  下午11:03:38
	 */
	private ArrayList<String> getPlayersURL(String teamURL) {
		ArrayList<String> playerURLs = new ArrayList<String>();
		BufferedReader reader = getReader(teamURL);
		String playerURLReg = "<td align=left>&nbsp;<a href=\"(?<playerURL>.*?)\"><u><font size=-1>.*?</font></u></a></td>";
		Pattern playerURLPattern = Pattern.compile(playerURLReg);
		try {
			String temp = null;
			while((temp = reader.readLine()) != null) {
				Matcher teamURLMatcher = playerURLPattern.matcher(temp);
				if(teamURLMatcher.find()) {
					String playerURL = teamURLMatcher.group("playerURL");
					playerURLs.add(captureUrl + "/1415/" +playerURL);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return playerURLs;
	}
	
	/**
	 * 获得全部球队的URL
	 * @return ArrayList 所有球队URL
	 * @author cylong
	 * @version 2015年6月15日  下午10:45:59
	 */
	private ArrayList<String> getTeamURLs() {
		ArrayList<String> teamURLs = new ArrayList<String>();
		BufferedReader reader = getReader(captureUrl);
		String teamURLReg = "<A href=\"(?<teamURL>.*?)\">[A-Za-z ]*</a><br>";
		Pattern teamURLPattern = Pattern.compile(teamURLReg);
		try {
			String temp = null;
			while((temp = reader.readLine()) != null) {
				Matcher teamURLMatcher = teamURLPattern.matcher(temp);
				if(teamURLMatcher.find()) {
					String teamURL = teamURLMatcher.group("teamURL");
					teamURLs.add(teamURL);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return teamURLs;
	}

	class Capture extends Thread {

		@Override
		public void run() {
			capturePlayerClutch();
		}

	}
}
