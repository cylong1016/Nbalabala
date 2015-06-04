package autotest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import autotest.playertest.PlayerSimpleSeasonVO;
import autotest.teamtest.TeamSimpleSeasonVO;
import enums.TeamState;

/**
 * 通过对比赛数据的累加计算，生成球员和球队数据
 * @author Issac Ding
 * @version 2015年4月1日  下午10:14:21
 */
public class SimpleMatchesAccumulator {
	
	private HashMap<String, PlayerSimpleSeasonVO> playerRecords;
	private HashMap<String, TeamSimpleSeasonVO> teamRecords;
	
	private HashSet<PlayerSimpleSeasonVO> playerUpdated = new HashSet<PlayerSimpleSeasonVO>();
	private HashSet<TeamSimpleSeasonVO> teamUpdated = new HashSet<TeamSimpleSeasonVO>(); 
	
	private double oppoAttack;
	
	public SimpleMatchesAccumulator(HashMap<String, PlayerSimpleSeasonVO> players,
			HashMap<String, TeamSimpleSeasonVO> teams) {
		super();
		this.playerRecords = players;
		this.teamRecords = teams;
	}
	
	public HashSet<PlayerSimpleSeasonVO> getUpdatedPlayers() {
		return playerUpdated;
	}
	
	public HashSet<TeamSimpleSeasonVO> getUpdatedTeams() {
		return teamUpdated;
	}
	
	/**
	 * @param files 按时间排序好的比赛文件
	 * @author Issac Ding
	 * @version 2015年4月1日  下午10:16:53
	 * needRecord表示是否需要把发生更新的球员名和球队缩写记录到HashSet中
	 */
	public void accumulate(File[] files, boolean needRecordUpdate) {
		playerUpdated.clear();
		teamUpdated.clear();
		BufferedReader br = null;

		for (File file : files) {
			try {
				br = new BufferedReader(new InputStreamReader(
						new FileInputStream(file), "UTF-8"));
				String[] ss = br.readLine().split(";");
				String[] date = ss[0].split("-");
				String month = date[0];
				String day = date[1];
				SimpleConstants.lastDay = day;
				SimpleConstants.lastMonth = month;
				String[] points = ss[2].split("-");
				int homePoints = Integer.parseInt(points[0]);
				int roadPoints = Integer.parseInt(points[1]);

				br.readLine();
				String homeTeam = br.readLine();
				String line = null;

				ArrayList<PlayerSimpleSeasonVO> homePlayers = new ArrayList<PlayerSimpleSeasonVO>();
				ArrayList<PlayerSimpleSeasonVO> roadPlayers = new ArrayList<PlayerSimpleSeasonVO>();

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

				while (true) {
					line = br.readLine();
					
					if (line.length() < 5)
						break; // 读到客场队队名时说明主场队读完了
					String[] s = line.split(";");
					if (!playerRecords.containsKey(s[0]))
						playerRecords.put(s[0], new PlayerSimpleSeasonVO(s[0]));
					PlayerSimpleSeasonVO playerRecord = playerRecords.get(s[0]);
					
					if (needRecordUpdate) playerUpdated.add(playerRecord);

						// 如果是首发，更新其位置信息
						if (s[1].length() != 0)
							playerRecord.position = s[1];
						playerRecord.teamName = homeTeam;

					homePlayers.add(playerRecord);
					playerRecord.lastDay = day;
					playerRecord.lastMonth = month;

					lineAccumulate(homeTeamData, playerRecord, s, file,
							TeamState.HOME);
				}

				// 下面读取客场队
				String roadTeam = line;
				while ((line = br.readLine()) != null) {
					
					String[] s = line.split(";");
					if (!playerRecords.containsKey(s[0]))
						playerRecords.put(s[0], new PlayerSimpleSeasonVO(s[0]));
					PlayerSimpleSeasonVO playerRecord = playerRecords.get(s[0]);
					
					if (needRecordUpdate) playerUpdated.add(playerRecord);

						// 如果是首发，更新其位置信息
						if (s[1].length() != 0)
							playerRecord.position = s[1];
						playerRecord.teamName = roadTeam;
						
						playerRecord.lastDay = day;
						playerRecord.lastMonth = month;

					roadPlayers.add(playerRecord);

					lineAccumulate(roadTeamData, playerRecord, s, file,
							TeamState.ROAD);
				}

				br.close();

				if (!teamRecords.containsKey(homeTeam)) {
					teamRecords.put(homeTeam, new TeamSimpleSeasonVO(homeTeam));
				}
				if (!teamRecords.containsKey(roadTeam)) {
					teamRecords.put(roadTeam, new TeamSimpleSeasonVO(roadTeam));
				}

				TeamSimpleSeasonVO homeTeamRecord = teamRecords.get(homeTeam);
				TeamSimpleSeasonVO roadTeamRecord = teamRecords.get(roadTeam);

				teamAccumulate(homeTeamRecord, homeTeamData, roadTeamData);
				
				for (PlayerSimpleSeasonVO record : homePlayers) {
					playerAccumulate(record, homeTeamData, roadTeamData);
				}
				
				teamAccumulate(roadTeamRecord, roadTeamData, homeTeamData);
				
				for (PlayerSimpleSeasonVO record : roadPlayers) {
					playerAccumulate(record, roadTeamData, homeTeamData);
				}
				
				if (needRecordUpdate) {
					teamUpdated.add(homeTeamRecord);
					teamUpdated.add(roadTeamRecord);
				}

				if (homePoints > roadPoints) {
					homeTeamRecord.wins++;
				} else {
					roadTeamRecord.wins++;
				}
			} catch (Exception e) {
				continue;
			}
		}
	}
	
	/** 数据累加，包括一队的各种数据累加和球员的数据累加 */
	private void lineAccumulate(int[] teamData, PlayerSimpleSeasonVO playerRecord, String[] s,
			File file, TeamState teamState) {
		//该数组0，1位置不用， 2位置为该球员的上场秒数，3之后是s的对应转化为整数
		int[] lineInt = new int[18];
		
		//识别上场时间为null的情况：
		try{
			String[] timeString = s[2].split(":");
			lineInt[2] = 60 * Integer.parseInt(timeString[0]) + 
					Integer.parseInt(timeString[1]);
		}catch(Exception e){
			lineInt[2] = getModifiedTime(file, teamState);
		}
		
		for (int i = 3;i < 17;i++){
			lineInt[i] = Integer.parseInt(s[i]);
		}
		
		//识别总分为null的情况：
		try{
			lineInt[17] = Integer.parseInt(s[17]);
		}catch(Exception e){
			lineInt[17] = 2 * lineInt[3] + lineInt[5] + lineInt[7];
		}
		
		// 球队的总得分在之前已经写好，故不参与累加
		for (int i=2;i<17;i++){
			teamData[i] += lineInt[i];
		}
		
		if (s[1].length() != 0){
			playerRecord.firstCount ++;
		}
		
		playerRecord.time += lineInt[2];
		playerRecord.fieldMade += lineInt[3];
		playerRecord.fieldAttempt += lineInt[4];
		playerRecord.threePointMade += lineInt[5];
		playerRecord.threePointAttempt += lineInt[6];
		playerRecord.freethrowMade += lineInt[7];
		playerRecord.freethrowAttempt += lineInt[8];
		playerRecord.offensiveRebound += lineInt[9];
		playerRecord.defensiveRebound += lineInt[10];
		playerRecord.totalRebound += lineInt[11];
		playerRecord.reboundQueue.enqueue(lineInt[11]);
		playerRecord.assist += lineInt[12];
		playerRecord.assistQueue.enqueue(lineInt[12]);
		playerRecord.steal += lineInt[13];
		playerRecord.block += lineInt[14];
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
	private void playerAccumulate(PlayerSimpleSeasonVO playerRecord, int [] teamData, int[] oppoData){
		playerRecord.oppoDefensiveRebound += oppoData[10];
		playerRecord.oppoFieldAttempt += oppoData[4];
		playerRecord.oppoFieldMade += oppoData[3];
		playerRecord.oppoTurnover += oppoData[15];
		playerRecord.oppoFreethrowAttempt += oppoData[8];
		playerRecord.oppoOffensiveRebound += oppoData[9];
		playerRecord.oppoThreePointAttempt += oppoData[6];
		playerRecord.oppoTotalRebound += oppoData[11];
		playerRecord.oppoAttack += oppoAttack;
		
		playerRecord.teamDefensiveRebound += teamData[10];
		playerRecord.teamFieldAttempt += teamData[4];
		playerRecord.teamFieldMade += teamData[3];
		playerRecord.teamTurnover += teamData[15];
		playerRecord.teamFreethrowAttempt += teamData[8];
		playerRecord.teamOffensiveRebound += teamData[9];
		playerRecord.teamTime += teamData[2];
		playerRecord.teamTotalRebound += teamData[11];
	}
		
	private void teamAccumulate(TeamSimpleSeasonVO teamRecord, int [] teamData, int [] oppoData){
		
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
		
		if (tmp != 0) {
			tmp = teamData[10] + oppoData[9];
			oppoAttack = oppoData[4] + 0.4 * (oppoData[8]) - 1.07 
					* ((double)oppoData[9] / (tmp) * 
							(oppoData[4] - oppoData[3])) + 1.07 * oppoData[15];
			teamRecord.defensiveRound += oppoAttack;
		}
		
	}
	
	/** 修正时间为null的脏数据，方法是总时间减去其他球员上场时间，总时间为48分钟+5分钟*加时赛场数*5*/
	public int getModifiedTime(File file, TeamState teamState) {
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(file));
			br.readLine();
			int totalSeconds = ((br.readLine().split(";").length - 4) * 5 + 48) * 300;
			br.readLine();
			//有记录的总分钟数和秒数之和
			int minutes = 0;
			int seconds = 0;
			String [] line;
			
			//如果是主场队，读取文件的上半部分
			if (teamState == TeamState.HOME) {
				while (true){
					line = br.readLine().split(";|:");
					if (line.length < 2) break;		//读到客场队缩写说明读完了主场队
					try{
						minutes += Integer.parseInt(line[2]);
						seconds += Integer.parseInt(line[3]);
					}catch (Exception e){
						continue;
					}
				}
				
			}else {	//如果要纠正的是客场队
				while (br.readLine().length() > 5) {
					//跳过主场队部分
				}
				String s = null;
				while ((s = br.readLine()) != null){
					line = s.split(";|:");
					try{
						minutes += Integer.parseInt(line[2]);
						seconds += Integer.parseInt(line[3]);
					}catch (Exception e){
						continue;
					}
				}
			}
			br.close();
			return totalSeconds - seconds - minutes * 60;
		}catch (Exception e){
			return 0;
		}
	}
}
