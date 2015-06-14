package bl.livebl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
		ArrayList<String> textLive = live.getTextLive();
		for(int i = 0; i < textLive.size(); i++) {
			System.out.println(textLive.get(i));
		}
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
	/** 文字直播 */
	private ArrayList<String> textLive = new ArrayList<String>();
	/** 客场球员数据 */
	private ArrayList<LivePlayerVO> roadPlayerRecords = new ArrayList<LivePlayerVO>();
	/** 主场球员数据 */
	private ArrayList<LivePlayerVO> homePlayerRecords = new ArrayList<LivePlayerVO>();
	private double[] roadFiveArgs = new double[5];
	private double[] homeFiveArgs = new double[5];
	
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
//		if(!hasMatchStarted) {
//			return 0;
//		}
		return roadPlayerRecords;
	}

	/**
	 * @see blservice.LiveBLService#getHomePlayerRecords()
	 */
	@Override
	public ArrayList<LivePlayerVO> getHomePlayerRecords() {
//		if(!hasMatchStarted) {
//			return 0;
//		}
		return homePlayerRecords;
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
		return homeFiveArgs;
	}

	/**
	 * @see blservice.LiveBLService#getroadFiveArgs()
	 */
	@Override
	public double[] getroadFiveArgs() {
//		if(!hasMatchStarted) {
//			return null;
//		}
		return roadFiveArgs;
	}
	
	/**
	 * @see blservice.LiveBLService#getTextLive()
	 */
	@Override
	public ArrayList<String> getTextLive() {
//		if(!hasMatchStarted) {
//			return null;
//		}
		return textLive;
	}
	
	/**
	 * @param source
	 * @author cylong
	 * @version 2015年6月12日  上午9:27:14
	 */
	private void captureTextLive(String source) {
		textLive.clear();
		String eventReg = "<tr sid=\"(?<sid>\\d+)\">.*?<td width=\"70\">(?<time>.*?)</td>.*?<td width=\"70\">(?<team>.*?)</td>.*?<td>(?<event>.*?(<b>)?.*?(</b>)?.*?)</td>.*?<td width=\"139\" class=\"center\">(?<score>\\d+.\\d+)</td>.*?</tr>";
		String pauseReg = "<tr sid=\"(?<sid>\\d+)\" class=\"pause\">.*?<td colspan=\"4\" style=\"text-align:center\"><b>(?<pause>.*?)</b></td>.*?</tr>";
		Pattern eventPattern = Pattern.compile(eventReg);
		Pattern pausePattern = Pattern.compile(pauseReg);
		Matcher eventMatcher = eventPattern.matcher(source);
		Matcher pauseMatcher = pausePattern.matcher(source);
		int maxSID = 0; // 目前的最大事件数
		while(eventMatcher.find()) {
			int sid = Integer.parseInt(eventMatcher.group("sid"));
			if(sid > maxSID) {
				maxSID = sid;
			}
			String time = eventMatcher.group("time");
			String team = eventMatcher.group("team");
			String event = eventMatcher.group("event").replaceAll("<b>|</b>| ", "");
			String score = eventMatcher.group("score");
			String str = time + ";" + team + ";" + event + ";" + score;
			textLive.add(str);
		}
		boolean isOver = false;
		while(pauseMatcher.find()) {
			int sid = Integer.parseInt(pauseMatcher.group("sid"));
			if(sid > maxSID) {
				maxSID = sid;
			}
			String pause = pauseMatcher.group("pause");
			isOver = Pattern.matches(".+?(比赛结束)+.+?", source);
			if(isOver) {
				textLive.add(sid - 2, pause);
			} else {
				textLive.add(maxSID - sid, pause);
			}
		}
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
		HttpURLConnection urlConn = getConn(liveURL);
		InputStream input = null;
		BufferedReader reader = null;
		ArrayList<String> scoreList = new ArrayList<String>(); // 小结分数，包含总分
		try {
//			input = urlConn.getInputStream();
//			reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			FileReader fr = new FileReader("C:\\Users\\cylong\\Downloads\\虎扑\\06月05日勇士vs骑士文字直播－虎扑NBA原创报道14.html");
			reader = new BufferedReader(fr);
			String scoreReg = "<td>(?<score>\\d+)</td>";
			Pattern scorePattern = Pattern.compile(scoreReg);
			String dataLiveReg = "<a.*? href=\"(?<dataLiveURL>)\".*?><s></s>数据直播</a>";
			Pattern dataLivePattern = Pattern.compile(dataLiveReg);
			String dataLiveURL = null;
			String source = "";
			String temp = null;
			while((temp = reader.readLine()) != null) {
				source += temp;
				Matcher scoreMatcher = scorePattern.matcher(temp);
				if(scoreMatcher.find()) {
					String score = scoreMatcher.group("score");
					scoreList.add(score);
				}
				Matcher dataLiveMatcher = dataLivePattern.matcher(temp);
				if(dataLiveMatcher.find()) {
					dataLiveURL = dataLiveMatcher.group("dataLiveURL");
				}
			}
		//	captureTextLive(source); // 获得文字直路
			captureDataLive(dataLiveURL); // 获得数据直播
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
					if(!tempBool_1) {
						tempBool_1 = currentDate.equals(date.trim());
					}
				}
				Matcher timeMatcher = timePattern.matcher(temp);
				if(timeMatcher.find()) {
					String time = timeMatcher.group("time");
					SimpleDateFormat sdfTime = new SimpleDateFormat("HH：mm");
					String currentTime = sdfTime.format(current);
					if(!tempBool_2) {
						tempBool_2 = currentTime.compareTo(time.trim()) > 0 ? true : false;
					}
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

	private void captureDataLive(String url) {
		HttpURLConnection urlConn = getConn(LIVE_LIST_URL);
		InputStream input = null;
    	BufferedReader reader = null;
		try {
			input = urlConn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			String source = "";
			String temp = null;
			while((temp = reader.readLine()) != null) {
				source += temp;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

