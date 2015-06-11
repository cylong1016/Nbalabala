package bl.livebl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

import utility.Constants;
import vo.LivePlayerVO;
import blservice.LiveBLService;

/**
 * @author cylong
 * @version 2015年6月11日 下午4:36:28
 */
public class Live implements LiveBLService {
	
	public static void main(String[] args) {
		Live live = new Live();
		live.refresh();
		System.out.println(live.getHomeName());
		System.out.println(live.getHomeAbbr());
		System.out.println(live.getRoadName());
		System.out.println(live.getRoadAbbr());
		System.out.println(live.hasMatchStarted);
		System.out.println(live.getCurrentSectionCount());
		System.out.println(live.getHomeScores());
		System.out.println(live.getRoadScores());
		// HashMap<String, String> liveList = live.getLiveList();
		// System.out.println(liveList);
	}

	/** 直播列表的URL */
	private static final String LIVE_LIST_URL = "http://v.opahnet.com/nba/tv/";
	/** 直播列表 */
	HashMap<String, String> liveList = new HashMap<String, String>();
	/** 直播详情的url，从网站上读取 */
	private String liveURL;
	/** 当前是否有直播 */
	private boolean hasMatchStarted = false;
	/** 主队名 */
	private String homeName;
	/** 主队名缩写 */
	private String homeAbbr;
	/** 客队名 */
	private String roadName;
	/** 客队名缩写 */
	private String roadAbbr;
	/** 主队各节比分 */
	private ArrayList<Integer> homeScores = new ArrayList<Integer>();
	/** 客队各节比分 */
	private ArrayList<Integer> roadScores = new ArrayList<Integer>();
	/** 当前多少小节 */
	private int currentSectionCount = 0;
	
	public Live() {
		refresh();
	}
	
	/**
	 * @see blservice.LiveBLService#getLiveList()
	 */
	@Override
	public HashMap<String, String> getLiveList() {
		return liveList;
	}

	/**
	 * @see blservice.LiveBLService#hasMatchStarted()
	 */
	@Override
	public boolean hasMatchStarted() {
		return hasMatchStarted;
	}

	/**
	 * @see blservice.LiveBLService#getRoadName()
	 */
	@Override
	public String getRoadName() {
		return roadName;
	}

	/**
	 * @see blservice.LiveBLService#getRoadAbbr()
	 */
	@Override
	public String getRoadAbbr() {
		return roadAbbr;
	}

	/**
	 * @see blservice.LiveBLService#getHomeName()
	 */
	@Override
	public String getHomeName() {
		return homeName;
	}

	/**
	 * @see blservice.LiveBLService#getHomeAbbr()
	 */
	@Override
	public String getHomeAbbr() {
		return homeAbbr;
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
//		if(!hasMatchStarted) {
//			return null;
//		}
		return roadScores;
	}

	/**
	 * @see blservice.LiveBLService#getHomeScores()
	 */
	@Override
	public ArrayList<Integer> getHomeScores() {
//		if(!hasMatchStarted) {
//			return null;
//		}
		return homeScores;
	}

	/**
	 * @see blservice.LiveBLService#getCurrentSectionCount()
	 */
	@Override
	public int getCurrentSectionCount() {
//		if(!hasMatchStarted) {
//			return 0;
//		}
		return currentSectionCount;
	}

	/**
	 * @see blservice.LiveBLService#getHomeFiveArgs()
	 */
	@Override
	public double[] getHomeFiveArgs() {
//		if(!hasMatchStarted) {
//			return null;
//		}
		return null;
	}

	/**
	 * @see blservice.LiveBLService#getroadFiveArgs()
	 */
	@Override
	public double[] getroadFiveArgs() {
//		if(!hasMatchStarted) {
//			return null;
//		}
		return null;
	}
	
	/**
	 * @see blservice.LiveBLService#getTextLive()
	 */
	@Override
	public ArrayList<String> getTextLive() {
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

	/**
	 * @see blservice.LiveBLService#refresh()
	 */
	@Override
	public void refresh() {
		refreshLiveList();
		refreshLive();
	}
	
	/**
	 * 刷新正在直播的界面
	 * @author cylong
	 * @version 2015年6月12日  上午12:37:11
	 */
	private void refreshLive() {
//		if(!hasMatchStarted) {
//			return;
//		}
//		HttpURLConnection urlConn = getConn(liveURL);
//		InputStream input = null;
		BufferedReader reader = null;
		ArrayList<String> scoreList = new ArrayList<String>(); // 小结分数，包含总分
		try {
			FileReader fr = new FileReader("C:\\Users\\cylong\\Downloads\\虎扑\\06月05日勇士vs骑士文字直播－虎扑NBA原创报道14.html");
			reader = new BufferedReader(fr);
			String scoreReg = "<td>(?<score>\\d+)</td>";
			Pattern scorePattern = Pattern.compile(scoreReg);
			String temp = null;
			while((temp = reader.readLine()) != null) {
				Matcher scoreMatcher = scorePattern.matcher(temp);
				if(scoreMatcher.find()) {
					String score = scoreMatcher.group("score");
					scoreList.add(score);
				}
			}
			homeScores.clear();
			roadScores.clear();
			int num = scoreList.size();
			for(int i = 0; i < num / 2; i++) {
				homeScores.add(Integer.parseInt(scoreList.get(i)));
			}
			for(int i = num / 2; i < num; i++) {
				roadScores.add(Integer.parseInt(scoreList.get(i)));
			}
			currentSectionCount = num / 2 - 1;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 刷新有多少个直播的列表
	 * @author cylong
	 * @version 2015年6月12日  上午12:34:12
	 */
	private void refreshLiveList() {
		HttpURLConnection urlConn = getConn(LIVE_LIST_URL);
		InputStream input = null;
    	BufferedReader reader = null;
    	try {
			input = urlConn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			Date current = new Date();
			String dateReg = "<span class=\"date\">(?<date>\\d{2}月\\d{2}日)";
			String timeReg = "<span>(?<time>\\d{2}：\\d{2})</span>";
			String teamReg = "<span.*?>(?<team>.*?-.*?)</span>";
			String liveURLReg = "<a href=\"(?<liveURL>http://.*?html)\".*?>文字直播</a>";
			Pattern datePattern = Pattern.compile(dateReg);
			Pattern timePattern = Pattern.compile(timeReg);
			Pattern teamPattern = Pattern.compile(teamReg);
			Pattern detailURLPattern = Pattern.compile(liveURLReg);
			boolean tempBool_1 = false;
			boolean tempBool_2 = false;
			String date = null; // 在网站上抓取的直播日期
			String temp = null;
			while((temp = reader.readLine()) != null) {
				Matcher dateMatcher = datePattern.matcher(temp);
				if(dateMatcher.find()) {
					date = dateMatcher.group("date");
					SimpleDateFormat sdfDate = new SimpleDateFormat("MM月dd日");
					String currentDate = sdfDate.format(current);
					tempBool_1 = currentDate.equals(date);
				}
				Matcher timeMatcher = timePattern.matcher(temp);
				if(timeMatcher.find()) {
					String time = timeMatcher.group("time");
					SimpleDateFormat sdfTime = new SimpleDateFormat("HH：mm");
					String currentTime = sdfTime.format(current);
					tempBool_2 = currentTime.compareTo(time) > 0 ? true : false;
					Matcher teamMatcher = teamPattern.matcher(reader.readLine()); // 下一行就是球队名
					if(teamMatcher.find()) {
						String teamStr = teamMatcher.group("team");
						liveList.put(date + " " + time, teamStr);
						if(homeName == null && teamStr != null) {
							String[] teams = teamStr.split(" |-");
							homeName = teams[1];
							homeAbbr = Constants.getAbbrByName(homeName);
							roadName = teams[2];
							roadAbbr = Constants.getAbbrByName(roadName);
						}
						reader.readLine();
						Matcher liveURLMatcher = detailURLPattern.matcher(reader.readLine());
						if(liveURLMatcher.find()) {
							if(liveURL == null) {
								liveURL = liveURLMatcher.group("liveURL");
							}
						}
					}
				}
			}
			hasMatchStarted = tempBool_1 && tempBool_2; // 当前是否有直播
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
    		try {
    			reader.close();
    			input.close();
    			urlConn.disconnect();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
	}

}
