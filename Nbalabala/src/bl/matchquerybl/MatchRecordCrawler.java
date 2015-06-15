package bl.matchquerybl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utility.Utility;

/**
 * 获得历史的文字直播实录
 * @author cylong
 * @version 2015年6月15日  下午5:59:23
 */
public class MatchRecordCrawler {
	
	public static void main(String[] args) {
		String url = "http://www.basketball-reference.com/boxscores/pbp/201410280LAL.html#pbp";
		MatchRecordCrawler mrc = new MatchRecordCrawler();
		ArrayList<String> textLives = mrc.getLives(url);
		for(String event : textLives) {
			System.out.println(event);
		}
	}
	
	/**
	 * @param url 爬比赛实录的网页
	 * @return 如果网络不通或者该网页不存在，返回null。否则，返回文字表格中每一行，就像直播做的那样，从上到下。
	 * 返回格式，xxx;xxx;xxx;xxx就是用分号隔开一行的每一列
	 * @author cylong
	 * @version 2015年6月15日  下午4:53:04
	 */
	public ArrayList<String> getLives(String url) {
		HttpURLConnection urlConn = Utility.getConn(url);
		InputStream input = null;
		ArrayList<String> textLives = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			input = urlConn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			String timeReg = "<td class=.*?>(?<time>\\d{1,2}:\\d{2}.\\d)</td>";
			Pattern timePattern = Pattern.compile(timeReg);
			String eventReg = "<td.*?>(?<roadEvent>.*?)</td>(<td.*?>.*?</td><td.*?>(?<score>.*?)</td><td.*?>.*?</td><td.*?>(?<homeEvent>.*?)</td>)?";
			Pattern eventPattern = Pattern.compile(eventReg);
			String temp = null;
			while((temp = reader.readLine()) != null) {
				if(Pattern.matches("<th.*?>1st Quarter</td>", temp)) { // 直播实录的表格开始
					while((temp = reader.readLine()) != null) {
						Matcher timeMatcher = timePattern.matcher(temp);
						if(timeMatcher.find()) {
							String time = timeMatcher.group("time");
							// 下一行就是此时间对应的直播数据
							Matcher eventMatcher = eventPattern.matcher(reader.readLine());
							if(eventMatcher.find()) {
								String roadEvent = eventMatcher.group("roadEvent");
								String score = eventMatcher.group("score");
								String homeEvent = eventMatcher.group("homeEvent");
								String column = null;
								if(score == null && homeEvent == null) { // 只有一列数据
									column = roadEvent;
								} else {
									column = time + ";" + roadEvent + ";" + score + ";" + homeEvent;
								}
								column = column.replaceAll("<a href=.*?>", "");
								column = column.replaceAll("</a>", "");
								column = column.replaceAll("&nbsp;", " ");
								textLives.add(column);
							}
							
						}
					}
				}
			}
			return textLives;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
