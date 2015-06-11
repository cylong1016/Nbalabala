package bl.livebl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vo.LivePlayerVO;
import blservice.LiveBLService;

/**
 * @author cylong
 * @version 2015年6月11日 下午4:36:28
 */
public class Live implements LiveBLService {
	
	public static void main(String[] args) {
		Live live = new Live();
		HashMap<String, String> liveList = live.getLiveList();
		System.out.println(liveList);
	}

	/** 直播列表的URL */
	private static final String LIVE_LIST_URL = "http://v.opahnet.com/nba/tv/";
	private boolean hasMatchStarted = false;
	
	/**
	 * @see blservice.LiveBLService#getLiveList()
	 */
	@Override
	public HashMap<String, String> getLiveList() {
		HashMap<String, String> liveList = new HashMap<String, String>(); // 直播列表
		HttpURLConnection urlConn = getConn(LIVE_LIST_URL);
		InputStream input = null;
    	BufferedReader reader = null;
    	try {
			input = urlConn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			Date current = new Date();
			String dateReg = "<span class=\"date\">(?<date>[0-9]{2}月[0-9]{2}日)";
			String timeReg = "<span>(?<time>[0-9]{2}：[0-9]{2})</span>";
			String teamReg = "<span.*?>(?<team>.*?-.*?)</span>";
			Pattern datePattern = Pattern.compile(dateReg);
			Pattern timePattern = Pattern.compile(timeReg);
			Pattern teamPattern = Pattern.compile(teamReg);
			String temp = null;
			String date = null; // 在网站上抓取的直播日期
			while((temp = reader.readLine()) != null) {
				Matcher dateMatcher = datePattern.matcher(temp);
				if(dateMatcher.find()) {
					date = dateMatcher.group("date");
					SimpleDateFormat sdfDate = new SimpleDateFormat("MM月dd日");
					String currentDate = sdfDate.format(current);
					hasMatchStarted = currentDate.equals(date);
				}
				Matcher timeMatcher = timePattern.matcher(temp);
				if(timeMatcher.find()) {
					String time = timeMatcher.group("time");
					SimpleDateFormat sdfTime = new SimpleDateFormat("HH：mm");
					String currentTime = sdfTime.format(current);
					hasMatchStarted = currentTime.equals(time);
					Matcher teamMatcher = teamPattern.matcher(reader.readLine()); // 下一行就是球队信息
					if(teamMatcher.find()) {
						String team = teamMatcher.group("team");
						liveList.put(date + " " + time, team);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return liveList;
	}

	/**
	 * @see blservice.LiveBLService#hasMatchStarted()
	 */
	@Override
	public boolean hasMatchStarted() {
		getLiveList(); // 怕不调用这个方法
		return hasMatchStarted;
	}

	/**
	 * @see blservice.LiveBLService#isAnythingNew()
	 */
	@Override
	public boolean isAnythingNew() {
		return false;
	}

	/**
	 * @see blservice.LiveBLService#getRoadName()
	 */
	@Override
	public String getRoadName() {
		return null;
	}

	/**
	 * @see blservice.LiveBLService#getRoadAbbr()
	 */
	@Override
	public String getRoadAbbr() {
		return null;
	}

	/**
	 * @see blservice.LiveBLService#getHomeName()
	 */
	@Override
	public String getHomeName() {
		return null;
	}

	/**
	 * @see blservice.LiveBLService#getHomeAbbr()
	 */
	@Override
	public String getHomeAbbr() {
		return null;
	}

	/**
	 * @see blservice.LiveBLService#getRoadPlayerRecords()
	 */
	@Override
	public ArrayList<LivePlayerVO> getRoadPlayerRecords() {
		return null;
	}

	/**
	 * @see blservice.LiveBLService#getHomePlayerRecords()
	 */
	@Override
	public ArrayList<LivePlayerVO> getHomePlayerRecords() {
		return null;
	}

	/**
	 * @see blservice.LiveBLService#getRoadScores()
	 */
	@Override
	public ArrayList<Integer> getRoadScores() {
		return null;
	}

	/**
	 * @see blservice.LiveBLService#getHomeScores()
	 */
	@Override
	public ArrayList<Integer> getHomeScores() {
		return null;
	}

	/**
	 * @see blservice.LiveBLService#getCurrentSectionCount()
	 */
	@Override
	public int getCurrentSectionCount() {
		return 0;
	}

	/**
	 * @see blservice.LiveBLService#getHomeFiveArgs()
	 */
	@Override
	public double[] getHomeFiveArgs() {
		return null;
	}

	/**
	 * @see blservice.LiveBLService#getroadFiveArgs()
	 */
	@Override
	public double[] getroadFiveArgs() {
		return null;
	}
	
	/**
	 * 得到某个url的连接
	 * @param url
	 * @return HttpURLConnection
	 * @author cylong
	 * @version 2015年5月21日 下午2:33:03
	 */
	protected HttpURLConnection getConn(String url) {
		HttpURLConnection urlConn = null;
		try {
			urlConn = (HttpURLConnection)new URL(url).openConnection();
			urlConn.setRequestMethod("GET");
			urlConn.setUseCaches(true);
			urlConn.connect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return urlConn;
	}

}
