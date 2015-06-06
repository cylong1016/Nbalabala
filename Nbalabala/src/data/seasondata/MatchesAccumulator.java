package data.seasondata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import ui.panel.playerData.AllPlayerSeasonTable;
import utility.Constants;
import data.Database;


/**
 * 通过对比赛数据的累加计算，生成球员和球队数据，写入数据库
 * @author Issac Ding
 * @version 2015年4月1日  下午10:14:21
 */
public class MatchesAccumulator {
	
	public static void main(String[]args) {
	//writeTeamsToDatabase();
		MatchesAccumulator accumulator = new MatchesAccumulator();
		accumulator.accumulate();
		accumulator.update();
		System.out.println(accumulator.allPlayerRecords.size());
		Iterator<HashMap<String, PlayerSeasonVOForCompute>> itr = accumulator.allPlayerRecords.values().iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next().size());
		}
//		accumulator.writeToDatabase();
	}
	
	private static Connection conn = Database.conn;
	
	//读取文件并将球队数据写入数据库（跟这个类的名字无关）
	public static void writeTeamsToDatabase() {
		File file = new File(Constants.dataSourcePath + "teams/teams");
		if ( !file.exists()){
			return;
		}
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			String line = null;
			while((line = br.readLine()) != null) {
				line = line.replace("║", "");
				if (!Character.isLetterOrDigit(line.charAt(0))) {
					continue;
				}
				String[] info = line.split("│");
				
				try {
						java.sql.PreparedStatement ps = Database.conn.prepareStatement("insert into team_profile values(?,?,?,?,?,?,?)");
						ps.setString(1, info[1].trim());
						ps.setString(2, info[0]);
						ps.setString(3, info[2].trim());
						ps.setString(4, info[3]);
						ps.setString(5, info[4].trim());
						ps.setString(6, info[5].trim());
						ps.setInt(7, Integer.parseInt(info[6]));
						ps.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		Iterator<HashMap<String, PlayerSeasonVOForCompute>> itr = allPlayerRecords.values().iterator();
		while(itr.hasNext()) {
			Iterator<PlayerSeasonVOForCompute> subItr = itr.next().values().iterator();
			while(subItr.hasNext()) {
				subItr.next().update();
			}
		}
		Iterator<HashMap<String, TeamSeasonVOForCompute>> itr2 = allTeamRecords.values().iterator();
		while(itr2.hasNext()) {
			Iterator<TeamSeasonVOForCompute> subItr2 = itr2.next().values().iterator();
			while(subItr2.hasNext()) {
				subItr2.next().update();
			}
		}
	}
	
	public void writeToDatabase() {
		
		Iterator<HashMap<String, PlayerSeasonVOForCompute>> itr = allPlayerRecords.values().iterator();
		while(itr.hasNext()) {
			Iterator<PlayerSeasonVOForCompute> subItr = itr.next().values().iterator();
			while(subItr.hasNext()) {
				PlayerSeasonVOForCompute vo = subItr.next();
				PreparedStatement ps = null;  
				String insertSql = "INSERT INTO player_season " +
			             "VALUES (?, ?, ?,?,?,?, ?, ?,?,?,?, ?, ?,?,?,?, ?, ?,?,?,"
			             + "?, ?, ?,?,?,?, ?, ?,?,?,?, ?, ?,?,?,?, ?, ?,?,?,"
			             + "?, ?, ?,?,?,?, ?, ?,?,?,?, ?, ?,?,?,?, ?, ?,?,?,?,?,?)";
				try {
					ps = conn.prepareStatement(insertSql);
					ps.setString(1, vo.name);
				    ps.setString(2, vo.season);
				    ps.setInt(3, vo.latestMatchID);
				    ps.setDate(4, vo.latestMatchDate);
				    ps.setString(5, vo.teamName);
				    ps.setInt(6, vo.matchCount);
				    ps.setInt(7, vo.firstCount);
				    ps.setFloat(8, vo.firstCountAvg);
				    ps.setFloat(9, vo.minutes);
				    ps.setFloat(10, vo.minutesAvg);
				    ps.setInt(11, vo.fieldMade);
				    ps.setFloat(12, vo.fieldMadeAvg);
				    ps.setInt(13, vo.fieldAttempt);
				    ps.setFloat(14, vo.fieldAttemptAvg);
				    ps.setFloat(15, vo.fieldPercent);//15OK
				    
				    ps.setInt(16, vo.threePointMade);
				    ps.setFloat(17, vo.threePointMadeAvg);
				    ps.setInt(18, vo.threePointAttempt);
				    ps.setFloat(19, vo.threePointAttemptAvg);
				    ps.setFloat(20, vo.threePointPercent);
				    
				    ps.setInt(21, vo.freethrowMade);
				    ps.setFloat(22, vo.freethrowMadeAvg);
				    ps.setInt(23, vo.freethrowAttempt);
				    ps.setFloat(24, vo.freethrowAttemptAvg);
				    ps.setFloat(25, vo.freethrowPercent);
				    
				    ps.setInt(26, vo.offensiveRebound);
				    ps.setFloat(27, vo.offensiveReboundAvg);
				    ps.setInt(28, vo.defensiveRebound);
				    ps.setFloat(29, vo.defensiveReboundAvg);
				    ps.setInt(30, vo.totalRebound);
				    ps.setFloat(31, vo.totalReboundAvg); //31OK
				    
				    ps.setInt(32, vo.assist);
				    ps.setFloat(33, vo.assistAvg);
				    ps.setInt(34, vo.steal);
				    ps.setFloat(35, vo.stealAvg);
				    ps.setInt(36, vo.block);
				    ps.setFloat(37, vo.blockAvg);
				    ps.setInt(38, vo.turnover);
				    ps.setFloat(39, vo.turnoverAvg);
				    ps.setInt(40, vo.foul);
				    ps.setFloat(41, vo.foulAvg);
				    ps.setInt(42, vo.score);
				    ps.setFloat(43, vo.scoreAvg);
				    ps.setInt(44, vo.doubleDoubleCount);
				    ps.setFloat(45, vo.doubleDoubleAvg);
				    
				    ps.setInt(46, vo.efficiency);
				    ps.setFloat(47, vo.efficiencyAvg);
				    ps.setInt(48, vo.scoreReboundAssist);
				    ps.setFloat(49, vo.scoreReboundAssistAvg);
				    ps.setFloat(50, vo.gmsc);
				    ps.setFloat(51, vo.gmscAvg);
				    ps.setFloat(52, vo.realFieldPercent);
				    ps.setFloat(53, vo.fieldEff);
				    ps.setFloat(54, vo.offensiveReboundPercent);
				    ps.setFloat(55, vo.defensiveReboundPercent);
				    ps.setFloat(56, vo.totalReboundPercent);
				    ps.setFloat(57, vo.stealPercent);
				    ps.setFloat(58, vo.blockPercent);
				    ps.setFloat(59, vo.turnOverPercent);
				    ps.setFloat(60, vo.foulPercent);
				    ps.setFloat(61, vo.usePercent);
				    ps.setFloat(62, vo.assistPercent);
				    ps.setString(63, "C-F");	//TODO 因为位置还没爬下来，先这么写着
				    
				    ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
		}
		
		Iterator<HashMap<String, TeamSeasonVOForCompute>> itr2 = allTeamRecords.values().iterator();
		while(itr2.hasNext()) {
			Iterator<TeamSeasonVOForCompute> subItr2 = itr2.next().values().iterator();
			while(subItr2.hasNext()) {
				TeamSeasonVOForCompute vo = subItr2.next();
				PreparedStatement ps = null;  
				String insertSql = "INSERT INTO team_season " +
			             "VALUES (?, ?, ?,?,?,?, ?, ?, ?,?,?,?,?, ?, ?,?,?,?,?, ?, ?,?,?,?,?, ?, ?,?,?,?,?, ?, ?,?,?,?,?, ?, ?,?,?,?,?, ?, ?,?,?,?)";
				try {
					ps = conn.prepareStatement(insertSql);
					ps.setString(1, vo.teamName);
				    ps.setString(2, vo.season);
				    ps.setInt(3, vo.matchCount);
				    ps.setInt(4, vo.wins);
				    ps.setFloat(5, vo.winning);
				    ps.setInt(6, vo.fieldMade);
				    ps.setFloat(7, vo.fieldMadeAvg);
				    ps.setInt(8, vo.fieldAttempt);
				    ps.setFloat(9, vo.fieldAttemptAvg);
				    ps.setFloat(10, vo.fieldPercent);
				    ps.setInt(11, vo.threePointMade);
				    ps.setFloat(12, vo.threePointMadeAvg);
				    ps.setInt(13, vo.threePointAttempt);
				    ps.setFloat(14, vo.threePointAttemptAvg);
				    ps.setFloat(15, vo.threePointPercent);
				    ps.setInt(16, vo.freethrowMade);
				    ps.setFloat(17, vo.freethrowMadeAvg);
				    ps.setInt(18, vo.freethrowAttempt);
				    ps.setFloat(19, vo.freethrowAttemptAvg);
				    ps.setFloat(20, vo.freethrowPercent);
				    ps.setInt(21, vo.offensiveRebound);
				    ps.setFloat(22, vo.offensiveReboundAvg);
				    ps.setInt(23, vo.defensiveRebound);
				    ps.setFloat(24, vo.defensiveReboundAvg);
				    ps.setInt(25, vo.totalRebound);
				    ps.setFloat(26, vo.totalReboundAvg); //31OK
				    ps.setInt(27, vo.assist);
				    ps.setFloat(28, vo.assistAvg);
				    ps.setInt(29, vo.steal);
				    ps.setFloat(30, vo.stealAvg);
				    ps.setInt(31, vo.block);
				    ps.setFloat(32, vo.blockAvg);
				    ps.setInt(33, vo.turnover);
				    ps.setFloat(34, vo.turnoverAvg);
				    ps.setInt(35, vo.foul);
				    ps.setFloat(36, vo.foulAvg);
				    ps.setInt(37, vo.score);
				    ps.setFloat(38, vo.scoreAvg);
				    ps.setFloat(39, vo.offensiveRound);
				    ps.setFloat(40, vo.offensiveRoundAvg);
				    ps.setFloat(41, vo.defensiveRound);
				    ps.setFloat(42, vo.defensiveRoundAvg);
				    ps.setFloat(43, vo.offensiveEff);
				    ps.setFloat(44, vo.defensiveEff);
				    ps.setFloat(45, vo.assistEff);
				    ps.setFloat(46, vo.stealEff);
				    ps.setFloat(47, vo.oppoFieldPercent);
				    ps.setFloat(48, vo.oppoScoreAvg);
				    ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
		}
	}
	

	private HashMap<String, HashMap<String, PlayerSeasonVOForCompute>> allPlayerRecords = new HashMap<String, HashMap<String,PlayerSeasonVOForCompute>>();
	private HashMap<String, HashMap<String, TeamSeasonVOForCompute>> allTeamRecords = new HashMap<String, HashMap<String,TeamSeasonVOForCompute>>();
	
	private double oppoAttack;
	
//	private HashSet<PlayerSeasonVOForCompute> playerUpdated = new HashSet<PlayerSeasonVOForCompute>();
//	private HashSet<TeamSeasonVOForCompute> teamUpdated = new HashSet<TeamSeasonVOForCompute>(); 
	
//	public MatchesAccumulator(HashMap<String, HashMap<String, PlayerSeasonVOForCompute>> players,
//			HashMap<String, HashMap<String, TeamSeasonVOForCompute>> teams) {
//		super();
//		this.allPlayerRecords = players;
//		this.allTeamRecords = teams;
//	}
//	
//	public HashSet<PlayerSeasonVOForCompute> getUpdatedPlayers() {
//		return playerUpdated;
//	}
//	
//	public HashSet<TeamSeasonVOForCompute> getUpdatedTeams() {
//		return teamUpdated;
//	}
	
	/**
	 * @param files 按时间排序好的比赛文件
	 * @author Issac Ding
	 * @version 2015年4月1日  下午10:16:53
	 * needRecord表示是否需要把发生更新的球员名和球队缩写记录到HashSet中
	 */
	public void accumulate() {
		
		 // statement用来执行SQL语句
	    Statement statement = null;
	    String sql = "select * from match_profile";
	    ResultSet rs = null;
	    
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(sql);

			while(rs.next()) {
				
				//对于每一场比赛：
				
				int matchID = rs.getInt(1);
				String season = rs.getString(2);
				Date matchDate = rs.getDate(3);
				String roadTeam = rs.getString(4);
				String homeTeam = rs.getString(5);
				int homePoints = rs.getInt(8);
				int roadPoints = rs.getInt(7);
				
				PreparedStatement ps = null;
				String subSql = "select * from match_player where match_id=?";
				ps = conn.prepareStatement(subSql);
				ps.setInt(1, matchID);
			    ResultSet players = ps.executeQuery();	//players里是这场比赛所有球员数据了
			    
				HashMap<String, PlayerSeasonVOForCompute> playerRecords = allPlayerRecords
						.get(season);
				if (playerRecords == null) {
					allPlayerRecords.put(season,
							new HashMap<String, PlayerSeasonVOForCompute>());
					playerRecords = allPlayerRecords.get(season);
				}

				HashMap<String, TeamSeasonVOForCompute> teamRecords = allTeamRecords
						.get(season);
				if (teamRecords == null) {
					allTeamRecords.put(season,
							new HashMap<String, TeamSeasonVOForCompute>());
					teamRecords = allTeamRecords.get(season);
				}
				
				ArrayList<PlayerSeasonVOForCompute> homePlayers = new ArrayList<PlayerSeasonVOForCompute>();
				ArrayList<PlayerSeasonVOForCompute> roadPlayers = new ArrayList<PlayerSeasonVOForCompute>();
				
				// 该数组存储本场比赛主场队的总数据，0,1位置不用，2为所有球员上场秒数，3之后是s的对应位置转化为整数的和，
				int[] homeTeamData = new int[18];
				// 客场队总数据
				int[] roadTeamData = new int[18];
				
				for (int i = 0; i < 17; i++) {
					homeTeamData[i] = 0;
					roadTeamData[i] = 0;
				}
				
				homeTeamData[17] = homePoints;
				roadTeamData[17] = roadPoints;
				
				while (players.next()) {
					String name = players.getString(4);
					if (!playerRecords.containsKey(name))
						playerRecords.put(name, new PlayerSeasonVOForCompute(name));
			
					PlayerSeasonVOForCompute playerRecord = playerRecords.get(name);
					playerRecord.latestMatchID = matchID;
					playerRecord.season = season;
					
					if ((playerRecord.latestMatchDate == null) || (matchDate.after(playerRecord.latestMatchDate))) {
						playerRecord.latestMatchDate = matchDate;
						playerRecord.teamName = players.getString(3);
					}
					
					if (players.getString(2).charAt(0) == 'H') {		//客场0 主场1
						homePlayers.add(playerRecord);
						lineAccumulate(homeTeamData, playerRecord, players);
					}else {
						roadPlayers.add(playerRecord);
						lineAccumulate(roadTeamData, playerRecord, players);
					}
					
					if (players.getInt(5) == 1){			//首发
						playerRecord.firstCount ++;
					}
					
				}
				
				if (!teamRecords.containsKey(homeTeam)) {
					teamRecords.put(homeTeam, new TeamSeasonVOForCompute(homeTeam));
				}
				if (!teamRecords.containsKey(roadTeam)) {
					teamRecords.put(roadTeam, new TeamSeasonVOForCompute(roadTeam));
				}
				
				TeamSeasonVOForCompute homeTeamRecord = teamRecords.get(homeTeam);
				TeamSeasonVOForCompute roadTeamRecord = teamRecords.get(roadTeam);
				
				homeTeamRecord.season = season;
				roadTeamRecord.season = season;

				teamAccumulate(homeTeamRecord, homeTeamData, roadTeamData);
				for (PlayerSeasonVOForCompute record : homePlayers) {
					playerAccumulate(record, homeTeamData, roadTeamData);
				}
				
				teamAccumulate(roadTeamRecord, roadTeamData, homeTeamData);
				for (PlayerSeasonVOForCompute record : roadPlayers) {
					playerAccumulate(record, roadTeamData, homeTeamData);
				}

				if (homePoints > roadPoints) {
					homeTeamRecord.wins++;
				} else {
					roadTeamRecord.wins++;
				}
				
				players.close();
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** 数据累加，包括一队的各种数据累加和球员的数据累加 */
	private void lineAccumulate(int[] teamData, PlayerSeasonVOForCompute playerRecord, ResultSet rs) {
		//该数组0，1位置不用， 2位置为该球员的上场秒数，3之后是s的对应转化为整数
		int[] lineInt = new int[18];
		
		String[] timeString;
		try {

			timeString = rs.getString(6).split(":");
			if (timeString.length > 1)
				lineInt[2] = 60 * Integer.parseInt(timeString[0]) + 
					Integer.parseInt(timeString[1]);
			else
				lineInt[2] = 0;
			
			lineInt[3] = rs.getInt(7);
			lineInt[4] = rs.getInt(8);
			lineInt[5] = rs.getInt(10);
			lineInt[6] = rs.getInt(11);
			lineInt[7] = rs.getInt(13);
			lineInt[8] = rs.getInt(14);
			lineInt[9] = rs.getInt(16);
			lineInt[10] = rs.getInt(17);
			lineInt[11] = rs.getInt(18);
			lineInt[12] = rs.getInt(19);
			lineInt[13] = rs.getInt(20);
			lineInt[14] = rs.getInt(21);
			lineInt[15] = rs.getInt(22);
			lineInt[16] = rs.getInt(23);
			lineInt[17] = rs.getInt(24);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		// 球队的总得分在之前已经写好，故不参与累加
		for (int i=2;i<17;i++){
			teamData[i] += lineInt[i];
		}
		
		playerRecord.time += lineInt[2];
		playerRecord.fieldMade += lineInt[3];
		playerRecord.fieldMadeQueue.enqueue(lineInt[3]);
		playerRecord.fieldAttempt += lineInt[4];
		playerRecord.fieldAttemptQueue.enqueue(lineInt[4]);
		playerRecord.threePointMade += lineInt[5];
		playerRecord.threePointMadeQueue.enqueue(lineInt[5]);
		playerRecord.threePointAttempt += lineInt[6];
		playerRecord.threePointAttemptQueue.enqueue(lineInt[6]);
		playerRecord.freethrowMade += lineInt[7];
		playerRecord.freethrowMadeQueue.enqueue(lineInt[7]);
		playerRecord.freethrowAttempt += lineInt[8];
		playerRecord.freethrowAttemptQueue.enqueue(lineInt[8]);
		playerRecord.offensiveRebound += lineInt[9];
		playerRecord.defensiveRebound += lineInt[10];
		playerRecord.totalRebound += lineInt[11];
		playerRecord.reboundQueue.enqueue(lineInt[11]);
		playerRecord.assist += lineInt[12];
		playerRecord.assistQueue.enqueue(lineInt[12]);
		playerRecord.steal += lineInt[13];
		playerRecord.stealQueue.enqueue(lineInt[13]);
		playerRecord.block += lineInt[14];
		playerRecord.blockQueue.enqueue(lineInt[14]);
		playerRecord.turnover += lineInt[15];
		playerRecord.foul += lineInt[16];
		playerRecord.score += lineInt[17];
		playerRecord.scoreQueue.enqueue(lineInt[17]);
		playerRecord.matchCount ++;
		
		if (isDoubleDouble(lineInt)) playerRecord.doubleDoubleCount++;
	}

	private boolean isDoubleDouble(int[]lineInt) {
		int doubleCount = 0;
		if (lineInt[17] > 9) doubleCount ++;
		if (lineInt[14] > 9) doubleCount ++;
		if (lineInt[13] > 9) doubleCount ++;
		if (lineInt[12] > 9) doubleCount ++;
		if (lineInt[11] > 9) doubleCount ++;
		return doubleCount > 1;
	}
	
	/** 把球队本场比赛的数据累加到球员赛季数据中 */
	private void playerAccumulate(PlayerSeasonVOForCompute playerRecord, int [] teamData, int[] oppoData){
		playerRecord.oppoDefensiveRebound += oppoData[10];
		playerRecord.oppoFieldAttempt += oppoData[4];
		playerRecord.oppoFieldMade += oppoData[3];
		playerRecord.oppoTurnover += oppoData[15];
		playerRecord.oppoFreethrowAttempt += oppoData[8];
		playerRecord.oppoOffensiveRebound += oppoData[9];
		playerRecord.oppoThreePointAttempt += oppoData[6];
		playerRecord.oppoTotalRebound += oppoData[11];
		
		playerRecord.teamDefensiveRebound += teamData[10];
		playerRecord.teamFieldAttempt += teamData[4];
		playerRecord.teamFieldMade += teamData[3];
		playerRecord.teamTurnover += teamData[15];
		playerRecord.teamFreethrowAttempt += teamData[8];
		playerRecord.teamOffensiveRebound += teamData[9];
		playerRecord.teamTime += teamData[2];
		playerRecord.teamTotalRebound += teamData[11];
		
		playerRecord.oppoAttack += oppoAttack;
	}
		
	private void teamAccumulate(TeamSeasonVOForCompute teamRecord, int [] teamData, int [] oppoData){
		
		teamRecord.matchCount ++;
		teamRecord.score += teamData[17];
		teamRecord.fieldMade += teamData[3];
		teamRecord.fieldAttempt += teamData[4];
		teamRecord.threePointMade += teamData[5];
		teamRecord.threePointAttempt += teamData[6];
		teamRecord.freethrowMade += teamData[7];
		teamRecord.freethrowAttempt += teamData[8];
		teamRecord.offensiveRebound += teamData[9];
		teamRecord.defensiveRebound += teamData[10];
		teamRecord.totalRebound += teamData[11];
		teamRecord.assist += teamData[12];
		teamRecord.steal += teamData[13];
		teamRecord.block += teamData[14];
		teamRecord.turnover += teamData[15];
		teamRecord.foul += teamData[16];
		
		teamRecord.oppoDefensiveRebound += oppoData[10];
		teamRecord.oppoFieldAttempt += oppoData[4];
		teamRecord.oppoFieldMade += oppoData[3];
		teamRecord.oppoFreethrowAttempt += oppoData[8];
		teamRecord.oppoOffensiveRebound += oppoData[9];
		teamRecord.oppoScore += oppoData[17];
		teamRecord.oppoTurnover += oppoData[15];
		
		int tmp = teamData[9] + oppoData[10];
		if (tmp != 0)
			teamRecord.offensiveRound += teamData[4] + 0.4 * (teamData[8]) - 1.07
				* ((double)teamData[9] / (tmp) * 
						(teamData[4] - teamData[3])) + 1.07 * teamData[15];
		
		tmp = teamData[10] + oppoData[9];
		if (tmp != 0) {
			oppoAttack = oppoData[4] + 0.4 * (oppoData[8]) - 1.07 
					* ((double)oppoData[9] / (tmp) * 
							(oppoData[4] - oppoData[3])) + 1.07 * oppoData[15];
			teamRecord.defensiveRound += oppoAttack;
		}
	}
}
