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
		System.out.println(live.getLiveList());
		System.out.println(live.getHomeName());
		System.out.println(live.getHomeAbbr());
		System.out.println(live.getRoadName());
		System.out.println(live.getRoadAbbr());
		System.out.println(live.hasMatchStarted());
		System.out.println(live.getCurrentSectionCount());
		System.out.println(live.getHomeScores());
		System.out.println(live.getRoadScores());
		double[] homeFiveArgs = live.getHomeFiveArgs();
		double[] roadFiveArgs = live.getRoadFiveArgs();
		System.out.println("主队5项数据");
		for(int i = 0; i < homeFiveArgs.length; i++) {
			System.out.println(homeFiveArgs[i]);
		}
		System.out.println("客队5项数据");
		for(int i = 0; i < roadFiveArgs.length; i++) {
			System.out.println(roadFiveArgs[i]);
		}
		ArrayList<LivePlayerVO> homePlayerRecords = live.getHomePlayerRecords();
		ArrayList<LivePlayerVO> roadPlayerRecords = live.getRoadPlayerRecords();
		System.out.println("主队球员");
		for(int i = 0; i < homePlayerRecords.size(); i++) {
			System.out.println(homePlayerRecords.get(i));
		}
		System.out.println("客队球员");
		for(int i = 0; i < roadPlayerRecords.size(); i++) {
			System.out.println(roadPlayerRecords.get(i));
		}
		ArrayList<String> textLive = live.getTextLive();
		for(int i = 0; i < textLive.size(); i++) {
			System.out.println(textLive.get(i));
		}
	}

	/** 直播列表的URL */
	private static final String LIVE_LIST_URL = "http://v.opahnet.com/nba/tv/";
	/** 直播的URL */
	private static final String LIVE_URL = "http://g.hupu.com/";
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
		return roadPlayerRecords;
	}

	/**
	 * @see blservice.LiveBLService#getHomePlayerRecords()
	 */
	@Override
	public ArrayList<LivePlayerVO> getHomePlayerRecords() {
		return homePlayerRecords;
	}

	/**
	 * @see blservice.LiveBLService#getRoadScores()
	 */
	@Override
	public ArrayList<Integer> getRoadScores() {
		return roadScores;
	}

	/**
	 * @see blservice.LiveBLService#getHomeScores()
	 */
	@Override
	public ArrayList<Integer> getHomeScores() {
		return homeScores;
	}

	/**
	 * @see blservice.LiveBLService#getCurrentSectionCount()
	 */
	@Override
	public int getCurrentSectionCount() {
		return currentSectionCount;
	}

	/**
	 * @see blservice.LiveBLService#getHomeFiveArgs()
	 */
	@Override
	public double[] getHomeFiveArgs() {
		for(int i = 0; i < homePlayerRecords.size(); i++) {
			LivePlayerVO vo = homePlayerRecords.get(i);
			homeFiveArgs[3] += vo.totalRebound;
			homeFiveArgs[4] += vo.assist;
		}
		return homeFiveArgs;
	}

	/**
	 * @see blservice.LiveBLService#getRoadFiveArgs()
	 */
	@Override
	public double[] getRoadFiveArgs() {
		for(int i = 0; i < roadPlayerRecords.size(); i++) {
			LivePlayerVO vo = roadPlayerRecords.get(i);
			roadFiveArgs[3] += vo.totalRebound;
			roadFiveArgs[4] += vo.assist;
		}
		return roadFiveArgs;
	}

	/**
	 * @see blservice.LiveBLService#getTextLive()
	 */
	@Override
	public ArrayList<String> getTextLive() {
		return textLive;
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
	 * 刷新有多少个直播的列表
	 * @author cylong
	 * @version 2015年6月12日 上午12:34:12
	 */
	private void refreshLiveList() {
		liveList.clear();
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
				if (dateMatcher.find()) {
					date = dateMatcher.group("date");
					SimpleDateFormat sdfDate = new SimpleDateFormat("MM月dd日");
					String currentDate = sdfDate.format(current);
					if (!tempBool_1) {
						tempBool_1 = currentDate.equals(date.trim());
					}
				}
				Matcher timeMatcher = timePattern.matcher(temp);
				if (timeMatcher.find()) {
					String time = timeMatcher.group("time");
					SimpleDateFormat sdfTime = new SimpleDateFormat("HH：mm");
					String currentTime = sdfTime.format(current);
					if (!tempBool_2) {
						tempBool_2 = currentTime.compareTo(time.trim()) > 0 ? true : false;
					}
					Matcher teamMatcher = teamPattern.matcher(reader.readLine()); // 下一行就是球队名
					if (teamMatcher.find()) {
						String teamStr = teamMatcher.group("team");
						liveList.put(date + " " + time, teamStr);
						if (homeName == null && teamStr != null) {
							String[] teams = teamStr.split(" |-");
							homeName = teams[1];
							homeAbbr = Constants.getAbbrByName(homeName);
							roadName = teams[2];
							roadAbbr = Constants.getAbbrByName(roadName);
						}
						reader.readLine();
						Matcher liveURLMatcher = detailURLPattern.matcher(reader.readLine());
						if (liveURLMatcher.find()) {
							if (liveURL == null) {
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

	/**
	 * 刷新正在直播的界面
	 * @author cylong
	 * @version 2015年6月12日 上午12:37:11
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
//			input = urlConn.getInputStream();
//			reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			FileReader fr = new FileReader("直播\\文字1.html");
			reader = new BufferedReader(fr);
			String scoreReg = "<td>(?<score>\\d+)</td>";
			Pattern scorePattern = Pattern.compile(scoreReg);
//			String dataLiveURLReg = "<a  target=.*? href=\"(?<dataLiveURL>.*?)\" class=\".*?><s></s>数据直播</a>";
			String dataLiveURLReg = "<a  target=.*? href=\"(?<dataLiveURL>.*?)\" class=\"d \"><s></s>.*?</a>";
			Pattern dataLiveurlPattern = Pattern.compile(dataLiveURLReg);
			String dataLiveURL = null;
			String source = "";
			String temp = null;
			while((temp = reader.readLine()) != null) {
				source += temp;
				Matcher scoreMatcher = scorePattern.matcher(temp);
				if (scoreMatcher.find()) {
					String score = scoreMatcher.group("score");
					scoreList.add(score);
				}
				Matcher dataLiveURLMatcher = dataLiveurlPattern.matcher(temp);
				if (dataLiveURLMatcher.find()) {
					dataLiveURL = dataLiveURLMatcher.group("dataLiveURL");
				}
			}
			captureTextLive(source); // 获得文字直播
			captureDataLive(LIVE_URL + dataLiveURL); // 获得数据直播
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
	 * @param source
	 * @author cylong
	 * @version 2015年6月12日 上午9:27:14
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
			if (sid > maxSID) {
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
			if (sid > maxSID) {
				maxSID = sid;
			}
			String pause = pauseMatcher.group("pause");
			isOver = Pattern.matches(".+?(比赛结束)+.+?", source);
			if (isOver) {
				textLive.add(sid - 2, pause);
			} else {
				textLive.add(maxSID - sid, pause);
			}
		}
	}

	private void captureDataLive(String url) {
		homePlayerRecords.clear();
		roadPlayerRecords.clear();
		HttpURLConnection urlConn = getConn(url);
		InputStream input = null;
		BufferedReader reader = null;
		String homeOrRoad = "H"; // 主场还是客场
		boolean isStart = true; // 是否首发 
		try {
			input = urlConn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			String playerNameURLReg = "<a href=\"(?<playerNameURL>.*?)\" target=\"_blank\">";
			String playerNameReg = "(?<playerName>.*?)</a>";
			String playerDataReg = "<td>(<span.*?>)*(?<playerData>.*?)(</span>.*?)*</td>";
			Pattern playerDataPattern = Pattern.compile(playerDataReg);
			Pattern playerNameURLPattern = Pattern.compile(playerNameURLReg);
			Pattern playerNamePattern = Pattern.compile(playerNameReg);
			String source = "";
			String temp = null;
			while((temp = reader.readLine()) != null) {
				source += temp;
				Matcher playerNameURLMatcher = playerNameURLPattern.matcher(temp);
				if (Pattern.matches(".*?<td.*?><b>首发</b></td>", temp)) {
					isStart = true;
				} else if (Pattern.matches(".*?<td.*?><b>替补</b></td>", temp)) {
					isStart = false;
				}
				if (Pattern.matches(".*?<div.*?><h2>.*?（主队）</h2>", temp)) {
					homeOrRoad = "H";
				} else if (Pattern.matches(".*?<div.*?><h2>.*?（客队）</h2>", temp)) {
					homeOrRoad = "R";
				}
				if (playerNameURLMatcher.find()) {
					ArrayList<String> playerData = new ArrayList<String>();
					Matcher playerNameMatcher = playerNamePattern.matcher(reader.readLine()); // 下一行是球员名
					if (playerNameMatcher.find()) {
						String playerChnName = playerNameMatcher.group("playerName");
						playerData.add(playerChnName);
//						String playerNameURL = playerNameURLMatcher.group("playerNameURL");
//						playerData.add(getPlayerEngName(playerChnName, playerNameURL));
						playerData.add("");
					}
					while((temp = reader.readLine()) != null) { // 继续读取球员的数据
						if (Pattern.matches(".*?</tr>", temp)) {
							playerData.add(homeOrRoad);
							playerData.add(String.valueOf(isStart));
							if (playerData.size() >= 16) {
								formatPlayerData(playerData); // 整理球员数据
							}
							break; // 继续读下一个球员
						}
						Matcher playerDataMatcher = playerDataPattern.matcher(temp);
						if (playerDataMatcher.find()) {
							String data = playerDataMatcher.group("playerData");
							playerData.add(data);
						}
					}
				}
			}
//			String statReg = "<tr .*?>.*?<td.*?>统计</td>(.*?<td>.*?</td>){7}<td>(?<rebound>.*?)</td>.*?<td>(?<assist>.*?)</td>(.*?<td>.*?</td>){6}.*?</tr>";
//			Pattern statPattern = Pattern.compile(statReg);
//			Matcher statMatcher = statPattern.matcher(source);
//			if(statMatcher.find()) {
//				homeFiveArgs[3] = Double.parseDouble(statMatcher.group("rebound"));
//				homeFiveArgs[4] = Double.parseDouble(statMatcher.group("assist"));
//			}
//			if(statMatcher.find()) {
//				roadFiveArgs[3] = Double.parseDouble(statMatcher.group("rebound"));
//				roadFiveArgs[4] = Double.parseDouble(statMatcher.group("assist"));
//			}
			String fieldPercentReg = "<tr.*?>.*?<td.*?>命中率</td>(.*?<td>.*?</td>){2}.*?<td>(?<fieldMadPercent>.*?)</td>.*?<td>(?<threePointPercent>.*?)</td>.*?<td>(?<freePercent>.*?)</td>(.*?<td>.*?</td>){10}.*?</tr>";
			Pattern fieldPercent = Pattern.compile(fieldPercentReg);
			Matcher fieldPercentMatcher = fieldPercent.matcher(source);
			if (fieldPercentMatcher.find()) {
				String fieldMadPercent = fieldPercentMatcher.group("fieldMadPercent");
				String threePointPercent = fieldPercentMatcher.group("threePointPercent");
				String freePercent = fieldPercentMatcher.group("freePercent");
				homeFiveArgs[0] = percentToDouble(fieldMadPercent);
				homeFiveArgs[1] = percentToDouble(threePointPercent);
				homeFiveArgs[2] = percentToDouble(freePercent);
			}
			if (fieldPercentMatcher.find()) {
				String fieldMadPercent = fieldPercentMatcher.group("fieldMadPercent");
				String threePointPercent = fieldPercentMatcher.group("threePointPercent");
				String freePercent = fieldPercentMatcher.group("freePercent");
				roadFiveArgs[0] = percentToDouble(fieldMadPercent);
				roadFiveArgs[1] = percentToDouble(threePointPercent);
				roadFiveArgs[2] = percentToDouble(freePercent);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得球员英文名
	 * @param playerChnName 球员中文名
	 * @param playerNameURL 英文名所在url
	 * @return 球员英文名
	 * @author cylong
	 * @version 2015年6月15日  上午12:26:01
	 */
	public String getPlayerEngName(String playerChnName, String playerNameURL) {
		HttpURLConnection urlConn = getConn(playerNameURL);
		InputStream input = null;
		BufferedReader reader = null;
		try {
			input = urlConn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			String temp = null;
			String engNameReg = "<h2>" + playerChnName.trim() + "（(?<engName>).*?）</h2>";
			Pattern engNamePattern = Pattern.compile(engNameReg);
			while((temp = reader.readLine()) != null) {
				Matcher engNameMatcher = engNamePattern.matcher(temp);
				if(engNameMatcher.find()) {
					String engName = engNameMatcher.group("engName");
					return engName;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 整理球员数据，用来返回
	 * @param playerData
	 * @author cylong
	 * @version 2015年6月14日 下午11:18:27
	 */
	private void formatPlayerData(ArrayList<String> playerData) {
		for(int i = 0; i < playerData.size(); i++) {
			playerData.set(i, playerData.get(i).trim());
		}
		String[] fieldMadeArr = playerData.get(4).split("-");
		String[] threePointMadeArr = playerData.get(5).split("-");
		String[] freeThrowArr = playerData.get(6).split("-");
		String nameChn = playerData.get(0);
		String nameEng = playerData.get(1);
		String position = playerData.get(2);
		boolean isStarter = Boolean.parseBoolean(playerData.get(18));
		String timePlayed = playerData.get(3);
		int fieldMade = Integer.parseInt(fieldMadeArr[0]);
		int fieldAttempt = Integer.parseInt(fieldMadeArr[1]);
		int threepointMade = Integer.parseInt(threePointMadeArr[0]);
		int threepointAttempt = Integer.parseInt(threePointMadeArr[1]);
		int freethrowMade = Integer.parseInt(freeThrowArr[0]);
		int freethrowAttempt = Integer.parseInt(freeThrowArr[1]);
		int totalRebound = Integer.parseInt(playerData.get(9));
		int assist = Integer.parseInt(playerData.get(10));
		int steal = Integer.parseInt(playerData.get(12));
		int block = Integer.parseInt(playerData.get(14));
		int turnover = Integer.parseInt(playerData.get(13));
		int foul = Integer.parseInt(playerData.get(11));
		int score = Integer.parseInt(playerData.get(15));
		int plusMinus = Integer.parseInt(playerData.get(16));
		LivePlayerVO playerVO = new LivePlayerVO(nameChn, nameEng, position, isStarter, timePlayed, fieldMade, fieldAttempt, threepointMade, threepointAttempt, freethrowMade, freethrowAttempt, totalRebound, assist, steal, block, turnover, foul, score, plusMinus);
		String homeOrRoad = playerData.get(17);
		if (homeOrRoad.equals("H")) {
			homePlayerRecords.add(playerVO);
		} else if (homeOrRoad.equals("R")) {
			roadPlayerRecords.add(playerVO);
		}
	}

	/**
	 * 将 49%的形式改成0.49格式
	 * @author cylong
	 * @version 2015年6月15日  上午12:15:31
	 */
	private double percentToDouble(String num) {
		String douStr = num.replace("%", "");
		double dou = Double.parseDouble(douStr) / 100;
		return dou;
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
