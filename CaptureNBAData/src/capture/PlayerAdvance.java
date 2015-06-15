package capture;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * URL = http://espn.go.com/nba/statistics/rpm/_/page/1/sort/ORPM
 * @author cylong
 * @version 2015年6月15日 下午9:01:50
 */
public class PlayerAdvance extends NBAData {
	
	public PlayerAdvance() {
		this.captureUrl = "http://espn.go.com/nba/statistics/rpm/_/page/";
	}

	/**
	 * @see capture.NBAData#capture()
	 */
	@Override
	public void capture() {
		new Capture().start();
	}
	
	private void capturePlayerAdvance() {
		for(int i = 1; i <= 12; i++) {
			String url = this.captureUrl + i;
			BufferedReader reader = getReader(url);
			try {
				String dataReg = "<tr class=.*?>"
						+ "<td>(?<rk>\\d{1,3})</td>"
						+ "<td><a href=.*?>(?<name>.*?)</a>, [A-Z]+</td>"
						+ "<td>.*?</td>"
						+ "<td style=.*?>(?<gp>\\d+)</td>"
						+ "<td style=.*?>(?<mpg>.*?)</td>"
						+ "<td style=.*?>(?<orpm>.*?)</td>"
						+ "<td style=.*?>(?<drpm>.*?)</td>"
						+ "<td style=.*?>(?<rpm>.*?)</td>"
						+ "<td style=.*?>(?<war>.*?)</td>"
						+ "</tr>";
				Pattern dataPattern = Pattern.compile(dataReg);
				String temp = null;
				while((temp = reader.readLine()) != null) {
					Matcher dataMatcher = dataPattern.matcher(temp);
					while(dataMatcher.find()) {
						String rk = dataMatcher.group("rk");
						String name = dataMatcher.group("name");
						String gp = dataMatcher.group("gp");
						String mpg = dataMatcher.group("mpg");
						String orpm = dataMatcher.group("orpm");
						String drpm = dataMatcher.group("drpm");
						String rpm = dataMatcher.group("rpm");
						String war = dataMatcher.group("war");
						String sql = "INSERT INTO player_advance VALUES ("
								+ rk + ", "
								+ "'" + name.replaceAll("'", "\\\\'") + "', " 
								+ gp + ", "
								+ mpg + ", "
								+ orpm + ", "
								+ drpm + ", "
								+ rpm + ", "
								+ war
								+ ")";
						System.out.println(sql);
						Statement statement = mysqlConn.createStatement();
						statement.execute(sql);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	class Capture extends Thread {
		@Override
		public void run() {
			capturePlayerAdvance();
		}

	}

}
