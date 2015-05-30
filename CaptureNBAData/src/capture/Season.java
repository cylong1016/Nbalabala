package capture;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mysql.MySQL;

/**
 * 抓取赛季数据
 * http://www.basketball-reference.com/leagues
 * @author cylong
 * @version 2015年5月12日  下午7:54:54
 */
public class Season implements NBAData {
	
	public static void main(String[] args) {
		Season s = new Season();
		s.capture();
	}
	
	/** 网站链接 */ 
	private String root = "http://www.basketball-reference.com";
	/** 赛季数据的url */
    private String seasonUrl;
    private MySQL mysql;
    private Connection mysqlConn;
    /** 最近多少年的数据 */
    private int maxYear = 40;
    /** 比赛ID */
    private int gameID = 1;
    
    
    public Season() {
    	this.seasonUrl = root + "/leagues";
    	mysql = new MySQL();
    	mysqlConn = mysql.getConn(); // 获得数据库连接
    }
    
    /**
     * 抓取html网页赛季数据
     * @author cylong
     * @version 2015年5月18日  上午2:17:26
     */
    public void capture() {
    	new Capture().start();
    }
    
    /**
     * 抓取赛季日期和详细信息的url
     * @author cylong
     * @version 2015年5月19日  下午8:20:07
     */
    private void captureSeasonData() {
    	InputStream input = null;
    	BufferedReader reader = null;
    	HttpURLConnection urlConn = getConn(seasonUrl);
    	int i = 1; // 记录最近多少年
    	try {
    		input = urlConn.getInputStream();
    		reader = new BufferedReader(new InputStreamReader(input, urlConn.getContentType().equals("text-html; charset=gb2312")?"UTF-8":"gb2312"));
    		String temp = null;
    		while((temp = reader.readLine()) != null) {
    			// 匹配赛季日期和数据的链接
    			String reg = "(<td.*?>(<a href=\"(?<href>.*)\">))(?<season>\\d{4}-\\d{2})(</a></td>)";
    			Pattern pattern = Pattern.compile(reg);
    			Matcher matcher = pattern.matcher(temp);
    			if(matcher.find()) {
    				String href = root + matcher.group("href").replaceAll(".html", "_games.html");
    				String season = matcher.group("season");
    				captureNameAndScore(season, href);
    				i++;
    			}
    			if(i > maxYear) {
    				break;
    			}
    		}
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
	 * 抓取某个赛季的球队名称和总比分
	 * @param season 赛季年份
	 * @param url 详细信息的url
	 * @author cylong
	 * @version 2015年5月19日  下午8:28:39
	 */
	private void captureNameAndScore(String season, String url) {
		InputStream input = null;
		BufferedReader reader = null;
		HttpURLConnection urlConn = getConn(url);
		String regularOrPlayoff = season + "R"; // 常规赛或者季后赛，默认常规赛
		int culumnNum = 8; // 表格有8列数据
		int i = culumnNum;
		boolean hasDetalData = true; // 有些行没有球队的比赛信息
		ArrayList<String> cellData = new ArrayList<String>();
		try {
			input = urlConn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(input, urlConn.getContentType().equals("text-html; charset=gb2312")?"UTF-8":"gb2312"));
			String temp = null;
			while((temp = reader.readLine()) != null) {
				if(Pattern.matches("<h2.*[^>]>Playoffs</h2>", temp)) { // 检测到季后赛的表
					regularOrPlayoff = season + "P";
				}
				String cellReg = "<td.*?>(<a href=\"(?<href>.*)\">)*(?<info>[^<]*)(</a>)*</td>";
				Pattern patternCell = Pattern.compile(cellReg);
				Matcher matcherCell = patternCell.matcher(temp);
				if(matcherCell.find()) {
					String info = matcherCell.group("info");
					String href = root + matcherCell.group("href");
					if(i == 7) { // Box Score所在的那一行
						if(!info.equals("Box Score")) { // 这一行没有球队的比赛信息
							hasDetalData = false;
						}
						cellData.add(href);
						System.out.println(href + "， gameID: " + gameID);
					} else {
						cellData.add(info);
					}
					i--;
					if(i == 0) { // 一行数据读取完毕
						// 存入数据库
						if(hasDetalData) { // 有详细信息再存入数据库
							insertIntoTeamSeason(cellData, regularOrPlayoff); // 存储球队比赛数据
							gameID++;
						}
						hasDetalData = true;
						i = culumnNum;
						cellData.clear();
					}
				}
			}
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
	 * 抓取每小节的比分和球员的比赛信息
	 * @param url
	 * @author cylong
	 * @version 2015年5月19日  下午10:31:49
	 */
	private ArrayList<String> captureSectionAndPlayerData(String url) {
		InputStream input = null;
		BufferedReader reader = null;
		HttpURLConnection urlConn = getConn(url);
		// 将要返回的数据，包括球队缩写，小节数和四个小节的比分
		ArrayList<String> returnList = new ArrayList<String>();
		// 读取的小节分数，第0位保存着球队缩写
		ArrayList<String> sectionList = new ArrayList<String>();
		// 两只球队的加时赛信息
		ArrayList<String> extraTime = new ArrayList<String>();
		int k = 0; // 记录是哪个球队，0是第一行的球队，1是第二行的球队
		try {
			input = urlConn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(input, urlConn.getContentType().equals("text-html; charset=gb2312")?"UTF-8":"gb2312"));
			String temp = null;
			while((temp = reader.readLine()) != null) {
				if(Pattern.matches("<th.*[^>]>Scoring</th>", temp)) { // 找到Scoring表
					while((temp = reader.readLine()) != null) {
						String teamNameReg = "<td><a.*[^>]>(?<teamName>[A-Z]*)</a></td>";
						Pattern patternTeamName = Pattern.compile(teamNameReg);
						Matcher matcherTeamName = patternTeamName.matcher(temp);
						if(matcherTeamName.find()) {
							String teamName = matcherTeamName.group("teamName");
							sectionList.add(teamName); // 球队缩写
							while((temp = reader.readLine()) != null) {
								String sectionScoreReg = "<td.*[^>]>(?<score>[0-9]*)</td>";
								Pattern patternSectionScore = Pattern.compile(sectionScoreReg);
								Matcher matcherSectionScore = patternSectionScore.matcher(temp);
								if(matcherSectionScore.find()) {
									String score = matcherSectionScore.group("score");
									sectionList.add(score);
								}
								if(Pattern.matches("</tr>", temp)) { // 一行数据的结束
									if(k == 0) {
										returnList.add(String.valueOf(sectionList.size() - 2)); // 小节数
									}
									for(int i = 0; i < 5; i++) { // 球队名加上4个小节的分数
										returnList.add(sectionList.get(i));
									}
									if(sectionList.size() > 6) { // 对名、总分、加上4小节是6,如果大于6说明有加时赛
										for(int i = 5; i < sectionList.size() - 1; i++) {
											extraTime.add(sectionList.get(i));
										}
									}
									sectionList.clear();
									break;
								}
							}
							k++;
							if(k == 2) { // 两个球队的小节比分全部读取完毕
								insertIntoExtraTime(extraTime); // 保存加时赛信息
								break;
							}
						}
					}
					break;
				}
			}
			capturePlayerSeason(reader, returnList.get(1), returnList.get(6)); // 继续读取球员的比赛信息
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
		return returnList;
	}

	/**
	 * 读取每个球员的比赛信息
	 * @param reader 
	 * @param visitor 客场球队名缩写
	 * @param home 主场球队名缩写
     * @version 2015年5月28日  下午3:45:12
     */
	private void capturePlayerSeason(BufferedReader reader, String visitor, String home) {
    	String BASIC_BOX_SCORE_STATS = "Basic Box Score Stats"; // 球员比赛数据表格的表头
    	int homeOrVisitor = 0; // 0:客场、1:主场
    	String reserves = "Reserves"; // 候补球员的表头
    	try {
			String temp = null;
			while((temp = reader.readLine()) != null) {
				// 球员的比赛数据
				ArrayList<String> playerSeason = new ArrayList<String>();
				// 找到球员比赛信息的第一个表【客场】
				if(Pattern.matches(".*<th.*>" + BASIC_BOX_SCORE_STATS + "</th>", temp)) {
					int isStarter = 1; // 是否先发：1是、0不是
					while((temp = reader.readLine()) != null) {
						if(Pattern.matches(".*<th.*>" + reserves + "</th>", temp)) { // 读取到后发球员
							isStarter = 0;
						}
						String playerDataReg = "<td.*?>(<a href=.*?>)?(?<playerData>.*?)(</a>)?</td>";
						Pattern patternPlayerData = Pattern.compile(playerDataReg);
						Matcher matcherPlayerData = patternPlayerData.matcher(temp);
						if(matcherPlayerData.find()) {
							String playerData = matcherPlayerData.group("playerData");
							playerSeason.add(playerData);
						}
						if(Pattern.matches("</tr>", temp)) { // 一个球员的数据读取完毕
							playerSeason.add(String.valueOf(homeOrVisitor)); // 主场或者客场
							playerSeason.add(String.valueOf(isStarter)); // 是否先发
							if(homeOrVisitor == 0) {
								playerSeason.add(visitor);
							} else {
								playerSeason.add(home);
							}
							// 球员数据为24个，少于24个数据的就是没有记录
							// 表格最后一行是Team Totals，不记录
							if(playerSeason.size() == 24) {
								// 球员比赛信息表的最后一行，接着读取下一个Basic Box Score Stats表
								if(playerSeason.get(0).equals("Team Totals")) {
									homeOrVisitor = 1; // 下一个表是主场
									break; // 内层的while循环
								}
								insertIntoPlayerSeason(playerSeason);
							}
							playerSeason.clear();
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
    			reader.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
		}
	}

	/**
	 * 将球员比赛数据插入到数据库中
	 * @param playerSeason
	 * @author cylong
	 * @version 2015年5月28日  下午5:00:06
	 */
	private void insertIntoPlayerSeason(ArrayList<String> playerSeason) {
		for(int i = 0; i < playerSeason.size(); i++) {
			// 有些数据网站上是空的，存到数据库中为null
			if(playerSeason.get(i).equals("")) {
				playerSeason.set(i, "0");
			}
		}
		String sql = "INSERT INTO player_season ("
				+ "gameID, "
				+ "home_or_visitor, "
				+ "team, "
				+ "player_name, "
				+ "is_starter, "
				+ "minutes_played, "
				+ "field_goals, "
				+ "field_goals_attempts, "
				+ "field_goals_percentage, "
				+ "three_field_goals, "
				+ "three_field_goals_attempts, "
				+ "three_field_goals_percentage, "
				+ "free_throws, "
				+ "free_throws_attempts, "
				+ "free_throws_percentage, "
				+ "offensive_rebounds, "
				+ "defensive_rebounds, "
				+ "total_rebounds, "
				+ "assists, "
				+ "steals, "
				+ "blocks, "
				+ "turnovers, "
				+ "personal_fouls, "
				+ "points, "
				+ "plus_minus"
				+ ") VALUES ("
					+ gameID + ", "
					+ playerSeason.get(21) + ", "
					+ "'" + playerSeason.get(23) + "', "
					+ "'" + playerSeason.get(0).replaceAll("'", "\\\\'") + "', " // 名字中有单引号
					+ playerSeason.get(22) + ", "
					+ "'" + playerSeason.get(1) + "', "
					+ playerSeason.get(2) + ", "
					+ playerSeason.get(3) + ", "
					+ playerSeason.get(4) + ", "
					+ playerSeason.get(5) + ", "
					+ playerSeason.get(6) + ", "
					+ playerSeason.get(7) + ", " 
					+ playerSeason.get(8) + ", "
					+ playerSeason.get(9) + ", "
					+ playerSeason.get(10) + ", "
					+ playerSeason.get(11) + ", "
					+ playerSeason.get(12) + ", "
					+ playerSeason.get(13) + ", "
					+ playerSeason.get(14) + ", "
					+ playerSeason.get(15) + ", "
					+ playerSeason.get(16) + ", "
					+ playerSeason.get(17) + ", "
					+ playerSeason.get(18) + ", "
					+ playerSeason.get(19) + ", "
					+ playerSeason.get(20)
					+ ")";
		System.out.println(playerSeason);
		try {
			Statement statement = mysqlConn.createStatement();
			statement.execute(sql); // 插入
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * 将球队比赛信息保存到team_season表中
	 * @param cellData
	 * @param regularOrPlayoff 常规赛或者季后赛
	 * @author cylong
	 * @version 2015年5月25日  下午3:59:41
	 */
	private void insertIntoTeamSeason(ArrayList<String> cellData, String regularOrPlayoff) {
		ArrayList<String> sectionScore = captureSectionAndPlayerData(cellData.get(1)); // 读取每小节分数和球员数据
		String sql = "INSERT INTO team_season ("
				+ "gameID, "
				+ "date, "
				+ "vistor_total_score, "
				+ "home_total_score, "
				+ "section, "
				+ "visitor, "
				+ "vistor_section_1, "
				+ "vistor_section_2, "
				+ "vistor_section_3, "
				+ "vistor_section_4, "
				+ "home, "
				+ "home_section_1, "
				+ "home_section_2, "
				+ "home_section_3, "
				+ "home_section_4, "
				+ "regular_or_playoff"
				+ ") VALUES ("
				+ gameID + ", "
				+ "'" + cellData.get(0) + "', "
				+ cellData.get(3) + ", "
				+ cellData.get(5) + ", "
				+ sectionScore.get(0) + ", "
				+ "'" + sectionScore.get(1) + "', "
				+ sectionScore.get(2) + ", "
				+ sectionScore.get(3) + ", "
				+ sectionScore.get(4) + ", "
				+ sectionScore.get(5) + ", "
				+ "'" + sectionScore.get(6) + "', "
				+ sectionScore.get(7) + ", "
				+ sectionScore.get(8) + ", "
				+ sectionScore.get(9) + ", "
				+ sectionScore.get(10) + ", "
				+ "'" + regularOrPlayoff + "'"
				+ ")";
		try {
			// statement用来执行SQL语句            
			Statement statement = mysqlConn.createStatement();
			statement.execute(sql); // 插入
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 将加时赛信息存入表extra_time中
	 * @param extraTime
	 * @author cylong
	 * @version 2015年5月24日  上午12:07:41
	 */
	private void insertIntoExtraTime(ArrayList<String> extraTime) {
		int otNum = extraTime.size() / 2; // 加时赛数
		for(int i = 0; i < otNum; i++) {
			String sql = "INSERT INTO extra_time ("
					+ "gameID, "
					+ "num, "
					+ "vistor_score, "
					+ "home_score"
					+ ") VALUES (" 
						+ gameID + ", "
						+ (i + 1) + ", "
						+ extraTime.get(i) + ", "
						+ extraTime.get(i + otNum)
					+ ")";
			try {
				Statement statement = mysqlConn.createStatement();
				statement.execute(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

	/**
     * 得到某个url的连接
     * @param url
     * @return HttpURLConnection
     * @author cylong
     * @version 2015年5月21日  下午2:33:03
     */
    private HttpURLConnection getConn(String url) {
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

    class Capture extends Thread {
    	@Override
    	public void run() {
    		long start = System.currentTimeMillis();
    		captureSeasonData();
    		long stop = System.currentTimeMillis();
    		long millisecond = stop - start;
    		long hour = millisecond / 1000 / 60 / 60;
    		long minute = millisecond / 1000 / 60 % 60;
    		long second = millisecond / 1000 % 60;
    		System.out.println("最近" + maxYear + "年数据抓取完毕！");
    		System.out.println("共有" + (gameID--) + "场比赛！");
    		System.out.println("耗时：" + hour + "小时" + minute + "分钟" + second + "秒");
    	}
    }
    
    class CaptureDetal extends Thread {
    	String season;
    	String url;
    	
		public CaptureDetal(String season, String url) {
			this.season = season;
			this.url = url;
		}

		@Override
    	public void run() {
			captureNameAndScore(season, url);
    	}
    }
    
    class InsertInto extends Thread {
    	
    	ArrayList<String> cellData = new ArrayList<String>();
    	String regularOrPlayoff;
    	
		public InsertInto(ArrayList<String> cellData, String regularOrPlayoff) {
			this.cellData.addAll(cellData);
			this.regularOrPlayoff = regularOrPlayoff;
		}

		@Override
    	public void run() {
			insertIntoTeamSeason(cellData, regularOrPlayoff);
    	}
    }
    
}
