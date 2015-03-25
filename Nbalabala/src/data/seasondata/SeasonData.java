
package data.seasondata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import utility.Constants;
import utility.Utility;
import data.teamdata.TeamData;
import dataservice.SeasonDataService;
import dataservice.TeamDataService;
import enums.Position;
import enums.ScreenDivision;
import enums.TeamState;

/**
 * 读取并累加赛季数据
 * @author Issac Ding
 * @version 2015年3月14日  下午4:04:31
 */

public class SeasonData implements SeasonDataService {
	
	public SeasonData(){
		if (playerRecords.size() == 0 || teamRecords.size() == 0) loadMatches();
	}
	
	/** 存储所有球员的赛季数据记录 */
	private static HashMap<String, PlayerSeasonRecord> playerRecords = new HashMap<String, PlayerSeasonRecord>();
	
	/** 存储所有球队的赛季数据记录 */
	private static HashMap<String, TeamSeasonRecord> teamRecords = new HashMap<String, TeamSeasonRecord>();
	
	/**
	 * @see dataservice.SeasonDataService#getScreenedPlayerSeasonData(enums.Position, enums.ScreenDivision)
	 */
	@Override
	public ArrayList<PlayerSeasonRecord> getScreenedPlayerSeasonData(Position position,
			ScreenDivision division) {
		
		if (playerRecords.size() == 0) {
			loadMatches();
		}
		
		char posChar = '\0';
		switch (position) {
		case F:
			posChar = 'F';
			break;
		case C:
			posChar = 'C';
			break;
		case G:
			posChar = 'G';
		default:
			break;
		}
		
		ArrayList<PlayerSeasonRecord> result = new ArrayList<PlayerSeasonRecord>();
		
		Iterator<Map.Entry<String, PlayerSeasonRecord>> itr = playerRecords.entrySet().iterator();
		
		//需要队伍信息来查询球员属于哪个分区
		TeamDataService teamData = new TeamData();
		
		if (position == Position.ALL && division == ScreenDivision.ALL) {
			return getAllPlayerSeasonData();
		}else if (position == Position.ALL && 
				(division == ScreenDivision.WEST || division == ScreenDivision.EAST)){
			while (itr.hasNext()) {
				PlayerSeasonRecord record = itr.next().getValue();
				if (teamData.getAreaByAbbr(record.teamName) == division) {
					result.add(record);
				}
			}
		}else if (position == Position.ALL && 
				(division != ScreenDivision.WEST && division != ScreenDivision.EAST)){
			while (itr.hasNext()) {
				PlayerSeasonRecord record = itr.next().getValue();
				if (teamData.getDivisionByAbbr(record.teamName) == division) {
					result.add(record);
				}
			}
		}else if (position != Position.ALL && division == ScreenDivision.ALL) {
			while (itr.hasNext()) {
				PlayerSeasonRecord record = itr.next().getValue();
				if (record.getPosition() == posChar) {
					result.add(record);
				}
			}
		}else if (position != Position.ALL && 
				(division == ScreenDivision.WEST || division == ScreenDivision.EAST)){
			while (itr.hasNext()) {
				PlayerSeasonRecord record = itr.next().getValue();
				if (record.getPosition() == posChar && 
						teamData.getAreaByAbbr(record.teamName) == division) {
					result.add(record);
				}
			}
		}else{
			while (itr.hasNext()) {
				PlayerSeasonRecord record = itr.next().getValue();
				if (record.getPosition() == posChar &&
						teamData.getDivisionByAbbr(record.teamName) == division) {
					result.add(record);
				}
			}
		}

		return result;
	}
	
	/**
	 * @see dataservice.SeasonDataService#getAllPlayerSeasonData()
	 */
	@Override
	public ArrayList<PlayerSeasonRecord> getAllPlayerSeasonData() {
		return new ArrayList<PlayerSeasonRecord>(playerRecords.values());
	}
	
	/**
	 * @see dataservice.SeasonDataService#getScreenedTeamSeasonData(enums.ScreenDivision)
	 */
	@Override
	public ArrayList<TeamSeasonRecord> getScreenedTeamSeasonData(ScreenDivision division) {
		if (division == ScreenDivision.ALL){
			return new ArrayList<TeamSeasonRecord>(teamRecords.values());
		}
		
		Iterator<Map.Entry<String, TeamSeasonRecord>> itr = teamRecords.entrySet().iterator();
		TeamDataService teamData = new TeamData();
		ArrayList<TeamSeasonRecord> result = new ArrayList<TeamSeasonRecord>();
		
		if (division == ScreenDivision.EAST || division == ScreenDivision.WEST) {
			while (itr.hasNext()) {
				TeamSeasonRecord record = itr.next().getValue();
				if (teamData.getAreaByAbbr(record.getTeamName()) == division){
					result.add(record);
				}
			}
		}else {
			while (itr.hasNext()) {
				TeamSeasonRecord record = itr.next().getValue();
				if (teamData.getDivisionByAbbr(record.getTeamName()) == division){
					result.add(record);
				}
			}
		}
		return result;
	}
	
	/**
	 * @see dataservice.SeasonDataService#getTeamAbbrByPlayer(java.lang.String)
	 */
	@Override
	public String getTeamAbbrByPlayer(String playerName) {
		PlayerSeasonRecord record = playerRecords.get(playerName);
		if (record != null) return record.getTeam(); 
		else return Constants.UNKNOWN;
	}
	
	/**
	 * @see dataservice.SeasonDataService#getPlayerSeasonDataByName(java.lang.String)
	 */
	@Override
	public PlayerSeasonRecord getPlayerSeasonDataByName(String playerName) {
		PlayerSeasonRecord record = playerRecords.get(playerName);
		if (record == null) return new PlayerSeasonRecord(playerName); 
		else return record;
	}
	
	/**
	 * @see dataservice.SeasonDataService#getTeamDataByAbbr(java.lang.String)
	 */
	@Override
	public TeamSeasonRecord getTeamDataByAbbr(String abbr) {
		return teamRecords.get(abbr);
	}
	
	private void loadMatches() {
		File[] files = Utility.getSortedMatchFiles();
		
		BufferedReader br = null;

		try {
			for(File file : files) {
				br = new BufferedReader(new FileReader(file));
				String description = br.readLine();
				String[] points = description.split(";")[2].split("-");
				int homePoints = Integer.parseInt(points[0]);
				int roadPoints = Integer.parseInt(points[1]);
				
				br.readLine();
				String homeTeam = br.readLine();
				String line = null;
				
				ArrayList<PlayerSeasonRecord> homePlayers = new ArrayList<PlayerSeasonRecord>();
				ArrayList<PlayerSeasonRecord> roadPlayers = new ArrayList<PlayerSeasonRecord>();
				
				//该数组存储本场比赛主场队的总数据，0,1位置不用，2为所有球员上场秒数，3之后是s的对应位置转化为整数的和，
				int [] homeTeamData = new int [18];
				//客场队总数据
				int [] roadTeamData = new int [18];
				
				for (int i=0; i< 17;i++){
					homeTeamData[i] = 0;
					roadTeamData[i] = 0;
				}
				
				homeTeamData[17] = homePoints;
				roadTeamData[17] = roadPoints;
				
				while(true){
					line = br.readLine();
					
					if (line.length() < 5) break; 	//读到客场队队名时说明主场队读完了
					String[] s = line.split(";");
					if ( !playerRecords.containsKey(s[0])) playerRecords.put(s[0], 
							new PlayerSeasonRecord());
					PlayerSeasonRecord playerRecord = playerRecords.get(s[0]);
					
					playerRecord.name = s[0];
					// 如果是首发，更新其位置信息
					if (s[1].length() != 0) playerRecord.position = s[1].charAt(0);
					playerRecord.teamName = homeTeam;
					
					homePlayers.add(playerRecord);
					
					lineAccumulate(homeTeamData, playerRecord, s, file, TeamState.HOME);
				}
				
				//下面读取客场队
				String roadTeam = line;
				while ((line = br.readLine()) != null) {
					String[] s = line.split(";");
					if ( !playerRecords.containsKey(s[0])) playerRecords.put(s[0], 
							new PlayerSeasonRecord());
					PlayerSeasonRecord playerRecord = playerRecords.get(s[0]);
					
					playerRecord.name = s[0];
					// 如果是首发，更新其位置信息
					if (s[1].length() != 0) playerRecord.position = s[1].charAt(0);
					playerRecord.teamName = roadTeam;
					
					roadPlayers.add(playerRecord);
					
					lineAccumulate(roadTeamData, playerRecord, s, file, TeamState.ROAD);
				}
				
				for (PlayerSeasonRecord record : homePlayers) {
					playerAccumulate(record, homeTeamData, roadTeamData);
				}
				for (PlayerSeasonRecord record : roadPlayers) {
					playerAccumulate(record, roadTeamData, homeTeamData);
				}
				
				if (!teamRecords.containsKey(homeTeam)) {
					teamRecords.put(homeTeam, new TeamSeasonRecord(homeTeam));
				}
				if (!teamRecords.containsKey(roadTeam)) {
					teamRecords.put(roadTeam, new TeamSeasonRecord(roadTeam));
				}
				
				TeamSeasonRecord homeTeamRecord = teamRecords.get(homeTeam);
				TeamSeasonRecord roadTeamRecord = teamRecords.get(roadTeam);
				
				teamAccumulate(homeTeamRecord, homeTeamData, roadTeamData);
				teamAccumulate(roadTeamRecord, roadTeamData, homeTeamData);
				
				if (homePoints > roadPoints) {
					homeTeamRecord.wins ++;
				}else {
					roadTeamRecord.wins ++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @see dataservice.SeasonDataService#getPlayerNamesByTeamAbbr(java.lang.String)
	 */
	@Override
	public ArrayList<String> getPlayerNamesByTeamAbbr(String abbr) {
		ArrayList<String> result = new ArrayList<String>();
		Iterator<Entry<String, PlayerSeasonRecord>> itr = playerRecords.entrySet().iterator();
		while (itr.hasNext()) {
			PlayerSeasonRecord record = itr.next().getValue();
			if (record.getTeam().equals(abbr)) {
				result.add(record.getName());
			}
		}
		return result;
	}
	
	/** 数据累加，包括一队的各种数据累加和球员的数据累加 */
	private void lineAccumulate(int[] teamData, PlayerSeasonRecord playerRecord, String[] s,
			File file, TeamState teamState) {
		//该数组0，1位置不用， 2位置为该球员的上场秒数，3之后是s的对应转化为整数
		int[] lineInt = new int[18];
		
		//识别上场时间为null的情况：
		try{
			String[] timeString = s[2].split(":");
			lineInt[2] = 60 * Integer.parseInt(timeString[0]) + 
					Integer.parseInt(timeString[1]);
		}catch(Exception e){
			lineInt[2] = Utility.getModifiedTime(file, teamState);
		}
		
		for (int i = 3;i < 17;i++){
			lineInt[i] = Integer.parseInt(s[i]);
		}
		
		//识别总分为null的情况：
		try{
			lineInt[17] = Integer.parseInt(s[17]);
		}catch(Exception e){
			lineInt[17] = Utility.getModifiedScore(lineInt);
		}
		
		// 球队的总得分在之前已经写好，故不参与累加
		for (int i=2;i<17;i++){
			teamData[i] += lineInt[i];
		}
		
		if (s[1].length() != 0){
			playerRecord.firstCount ++;
		}
		playerRecord.time += lineInt[2];
		playerRecord.fieldGoal += lineInt[3];
		playerRecord.fieldAttempt += lineInt[4];
		playerRecord.threePointGoal += lineInt[5];
		playerRecord.threePointAttempt += lineInt[6];
		playerRecord.freethrowGoal += lineInt[7];
		playerRecord.freethrowAttempt += lineInt[8];
		playerRecord.offensiveRebound += lineInt[9];
		playerRecord.defensiveRebound += lineInt[10];
		playerRecord.totalRebound += lineInt[11];
		playerRecord.assist += lineInt[12];
		playerRecord.steal += lineInt[13];
		playerRecord.block += lineInt[14];
		playerRecord.turnover += lineInt[15];
		playerRecord.foul += lineInt[16];
		playerRecord.score += lineInt[17];
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
	private void playerAccumulate(PlayerSeasonRecord playerRecord, int [] teamData, int[] oppoData){
		playerRecord.oppoDefensiveRebound += oppoData[10];
		playerRecord.oppoFieldAttempt += oppoData[4];
		playerRecord.oppoFieldGoal += oppoData[3];
		playerRecord.oppoTurnover += oppoData[15];
		playerRecord.oppoFreethrowAttempt += oppoData[8];
		playerRecord.oppoOffensiveRebound += oppoData[9];
		playerRecord.oppoThreePointAttempt += oppoData[6];
		playerRecord.oppoTotalRebound += oppoData[11];
		
		playerRecord.teamDefensiveRebound += teamData[10];
		playerRecord.teamFieldAttempt += teamData[4];
		playerRecord.teamFieldGoal += teamData[3];
		playerRecord.teamTurnover += teamData[15];
		playerRecord.teamFreethrowAttempt += teamData[8];
		playerRecord.teamOffensiveRebound += teamData[9];
		playerRecord.teamTime += teamData[2];
		playerRecord.teamTotalRebound += teamData[11];
		
		/**
		 * PlayerVO [name=Al Horfor, appearance=29, starting=29, playTime=57464, hit=238, shot=420, 
		 * thirdHit=4, thirdshot=11, freeHit=58, freeshot=85, offensiveRebound=66, defensiveRebound=178, 
		 * totalRebound=244, assist=76, steal=27, block=44, miss=64, foul=56, score=538, 
		 * hitRate=0.5666666666666667, thirdHitRate=0.36363636363636365, freeHitRate=0.6823529411764706,
		 *  efficiency=656.0, gmScEfficiency=452.6, realHitRate=0.5881066899868824, 
		 *  shotEfficiency=0.5714285714285714, reboundRate=0.14631181900575907, 
		 *  offensiveReboundRate=0.17100804589319138, defensiveReboundRate=0.1388754086790601, 
		 *  assistRate=0.14363234424966495, stealRate=0.014177970173000183, blockRate=0.035265161673555014,
		 *   missRate=0.12539184952978058, useRate=0.24769325338861903, towPairs=9.0]
		 * */
		
//		if (playerRecord.name.equals("Al-Farouq Aminu")) {
//			System.out.println("___________________________");
//			System.out.println(playerRecord.getTeam() + "  " +playerRecord.getPosition() + "  " + playerRecord.getFirstCount() +" "+
//					playerRecord.getTime()+ "  " +playerRecord.getFieldGoal()+ "  " +playerRecord.getFieldAttempt());
//			System.out.println(playerRecord.getThreePointGoal()+ "  " + playerRecord.getThreePointAttempt()+ "  " +
//					playerRecord.getFreethrowGoal()+ "  " +playerRecord.getFreethrowAttempt()+ "  " );
//			
//			System.out.println(playerRecord.getOffensiveRebound()+ "  " + playerRecord.getDefensiveRebound()+ "  " +
//					playerRecord.getTotalRebound()+ "  " + playerRecord.getAssist()+ "  " + playerRecord.getSteal());
//			
//			System.out.println(playerRecord.getBlock()+ "  " + playerRecord.getTurnover()+ "  " +playerRecord.getFoul()+ "  " +
//					playerRecord.getPersonalGoal()+ "  " + playerRecord.getFieldPercent());
//			
//			System.out.println(playerRecord.getThreePointPercent()+ "  " + playerRecord.getFreethrowPercent()+ "  " +
//					playerRecord.getEfficiency()+ "  " + playerRecord.getGmSc()+ "  " + playerRecord.getRealFieldPercent());
//			
//			System.out.println(playerRecord.getFieldEff()+ "  " + playerRecord.getTotalReboundPercent()+ "  " +
//					playerRecord.getOffensiveReboundPercent()+ "  " + playerRecord.getDefensiveReboundPercent());
//			
//			System.out.println(playerRecord.getAssistPercent()+ "  " + playerRecord.getStealPercent()+ "  " +playerRecord.getBlockPercent()+ "  " +
//					playerRecord.getTurnOverPercent()+ "  " + playerRecord.getUsePercent()+ "  " +playerRecord.getDoubleDouble());}
	}
	
//		TeamSeasonRecord record = SeasonData.teamRecords.get("CHI");
//		System.out.println(record.getMatchCount() + "  " + record.getFieldGoal() + "  " + record.getFieldAttempt() + "  " + 
//		record.getThreePointGoal() + "  " + record.getThreePointAttempt() + "  " + record.getFreethrowGoal() + "  " + record.getFreethrowAttempt());
//		System.out.println(record.getOffensiveRebound() + "  " + record.getDefensiveRebound() + "  " + record.getTotalRebound() + "  " + 
//		record.getAssist() + "  " + record.getSteal() + "  " + record.getBlock() + "  " + record.getTurnover() + "  " + record.getFoul() + "  " + record.getTeamGoal());
//		System.out.println(record.getFieldPercent() + "  " + record.getThreePointPercent() + "  " + record.getFreeThrowPercent() + "  " );
//		System.out.println(record.getWinning() + "  " + record.getOffensiveRound() + "  " + record.getOffensiveEff() + "  " + 
//		record.getDefensiveRound() + "  " +record.getDefensiveEff());
//		System.out.println(record.getOffensiveReboundEff()+ "  " +record.getDefensiveReboundEff()+ "  " +record.getStealEff()+ "  " +record.getAssistEff());
	/**
	 * TeamVO [fullName=Bulls, abbreviation=CHI, location=Chicago, division=E, 
	 * zone=Central, home=United Center, setupTime=1966, appearance=82, hit=2829, 
	 * shot=6544, thirdHit=506, thirdshot=1450, freeHit=1480, freeshot=1900, 
	 * offensiveRebound=933, defensiveRebound=2671, totalRebound=3604, assist=1851, 
	 * steal=592, block=421, miss=1144, foul=1556, score=7680, hitRate=0.4323044009779951, 
	 * thirdHitRate=0.3489655172413793, freeHitRate=0.7789473684210526, 
	 * 
	 * winRate=0.5853658536585366, offensiveRound=7447.135187991839, 
	 * offensiveEfficiency=103.1269045898837, defensiveRound=7496.849347826086, 
	 * defensiveEfficiency=100.41551658208188, offensiveReboundEfficiency=0.2719323812299621, 
	 * defensiveReboundEfficiency=0.7540937323546019, stealEfficiency=7.896650613256173, 
	 * assistEfficiency=24.855195364046185]
	 * 
	 * */
	/**
	 * PlayerVO [teamFullName=Pelicans, teamAbbreviation=NOP, division=W, zone=Southwest,
	 *  name=Al-Farouq Aminu, number=0, position=F, height=6英尺9英寸, weight=215, 
	 *   age=23, exp=3, school=Wake Forest, appearance=80, starting=65, 
	 *  playTime=122127, hit=231, shot=490, thirdHit=12, thirdshot=47, freeHit=91, freeshot=137,
	 *   
	 *  offensiveRebound=129, defensiveRebound=366, totalRebound=495, assist=114, steal=81, 
	 *  block=38, miss=87, foul=147, score=565, hitRate=0.4714285714285714, 
	 *  thirdHitRate=0.2553191489361702, freeHitRate=0.6642335766423357, efficiency=901.0, 
	 *  gmScEfficiency=537.7, realHitRate=0.5133750090862834, shotEfficiency=0.48367346938775513, 
	 *  reboundRate=0.1414083149604877, offensiveReboundRate=0.1389417693336824,
	 *   defensiveReboundRate=0.14229867530857482, assistRate=0.08408738734094613, 
	 *   stealRate=0.18031481670854213, blockRate=0.015645510018927236,
	 *    missRate=0.1473876804228502, useRate=0.14351428056608692, towPairs=5.0]
	 * */
	
	private void teamAccumulate(TeamSeasonRecord teamRecord, int [] teamData, int [] oppoData){
		
		teamRecord.matchCount ++;
		teamRecord.score += teamData[17];
		teamRecord.fieldGoal += teamData[3];
		teamRecord.fieldAttempt += teamData[4];
		teamRecord.threePointGoal += teamData[5];
		teamRecord.threePointAttempt += teamData[6];
		teamRecord.freethrowGoal += teamData[7];
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
		teamRecord.oppoFieldGoal += oppoData[3];
		teamRecord.oppoFreethrowAttempt += oppoData[8];
		teamRecord.oppoOffensiveRebound += oppoData[9];
		teamRecord.oppoGoal += oppoData[17];
		teamRecord.oppoTurnover += oppoData[15];
	}
	
	/** 向playerdata提供所有参加过比赛的球员的名字 */
	public ArrayList<String> getPlayerNames() {
		ArrayList<String> result = new ArrayList<String>();
		Iterator<String> itr = playerRecords.keySet().iterator();
		while (itr.hasNext()){
			result.add(itr.next());
		}
		return result;
	}
}
