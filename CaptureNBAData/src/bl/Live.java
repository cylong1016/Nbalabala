package bl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
		new Live().getLiveInfo();
	}

	/** 直播列表的URL */
	private String liveListURL = "http://v.opahnet.com/nba/tv/";
	private boolean hasMatchStarted = false;
	
	/**
	 * @see blservice.LiveBLService#getLiveInfo()
	 */
	@Override
	public String getLiveInfo() {
		HttpURLConnection urlConn = getConn(liveListURL);
		InputStream input = null;
    	BufferedReader reader = null;
    	try {
			input = urlConn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			String temp = null;
			String dateReg = "(<span class=\"date\">(?<date>[0-9]{2}月[0-9]{2}日))|(<span>(?<time>[0-9]{2}：[0-9]{2})</span>)";
			Pattern datePattern = Pattern.compile(dateReg);
			while((temp = reader.readLine()) != null) {
				Matcher dateMatcher = datePattern.matcher(temp);
				if(dateMatcher.find()) {
					String date = dateMatcher.group("date");
					String time = dateMatcher.group("time");
					System.out.println(date);
					System.out.println(time);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @see blservice.LiveBLService#hasMatchStarted()
	 */
	@Override
	public boolean hasMatchStarted() {
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
