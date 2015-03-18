
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

import utility.Utility;
import data.teamdata.TeamData;
import enums.Position;
import enums.ScreenDivision;
import enums.TeamState;

/**
 * 读取并累加赛季数据
 * @author Issac Ding
 * @version 2015年3月14日  下午4:04:31
 */

public class SeasonData {
	
	public SeasonData(){
		if (playerRecords.size() == 0 || teamRecords.size() == 0) loadMatches();
	}
	
	/** 存储所有球员的赛季数据记录 */
	private static HashMap<String, PlayerSeasonRecord> playerRecords = new HashMap<String, PlayerSeasonRecord>();
	
	/** 存储所有球队的赛季数据记录 */
	private static HashMap<String, TeamSeasonRecord> teamRecords = new HashMap<String, TeamSeasonRecord>();
	
	/** 根据位置、赛区来返回符合条件的球员 */
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
		TeamData teamData = new TeamData();
		
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
	
	/** 返回全部球员赛季数据 */
	public ArrayList<PlayerSeasonRecord> getAllPlayerSeasonData() {
		return new ArrayList<PlayerSeasonRecord>(playerRecords.values());
	}
	
	/** 根据赛区返回符合条件的记录 */
	public ArrayList<TeamSeasonRecord> getScreenedTeamSeasonData(ScreenDivision division) {
		if (division == ScreenDivision.ALL){
			return new ArrayList<TeamSeasonRecord>(teamRecords.values());
		}
		
		Iterator<Map.Entry<String, TeamSeasonRecord>> itr = teamRecords.entrySet().iterator();
		TeamData teamData = new TeamData();
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
	
	/** 根据球员名字返回其最后一次比赛的球队 */
	public String getTeamAbbrByPlayer(String playerName) {
		return playerRecords.get(playerName).getTeam();
	}
	
	public void loadMatches() {
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
				
				//该数组存储本场比赛主场队的总数据，0,1位置不用，2为所有球员上场秒数，3之后是s的对应位置转化为整数的和
				int [] homeTeamData = new int [18];
				//客场队总数据
				int [] roadTeamData = new int [18];
				
				for (int i=0; i< 18;i++){
					homeTeamData[i] = 0;
					roadTeamData[i] = 0;
				}
				
				while(true){
					line = br.readLine();
					String[] s = line.split(";");
					if (s.length == 1) break; 	//读到客场队队名时说明主场队读完了
					if ( !playerRecords.containsKey(s[0])) playerRecords.put(s[0], 
							new PlayerSeasonRecord());
					PlayerSeasonRecord playerRecord = playerRecords.get(s[0]);
					
					// 如果是首发，更新其位置信息
					if (s[1].length() != 0) playerRecord.position = s[1].charAt(0);
					playerRecord.teamName = homeTeam;
					
					homePlayers.add(playerRecord);
					
					dataAccumulate(homeTeamData, playerRecord, s, file, TeamState.HOME);
				}
				
				//下面读取客场队
				String roadTeam = line;
				while ((line = br.readLine()) != null) {
					String[] s = line.split(";");
					if ( !playerRecords.containsKey(s[0])) playerRecords.put(s[0], 
							new PlayerSeasonRecord());
					PlayerSeasonRecord playerRecord = playerRecords.get(s[0]);
					
					// 如果是首发，更新其位置信息
					if (s[1].length() != 0) playerRecord.position = s[1].charAt(0);
					playerRecord.teamName = roadTeam;
					
					roadPlayers.add(playerRecord);
					
					dataAccumulate(roadTeamData, playerRecord, s, file, TeamState.ROAD);
				}
				
				for (PlayerSeasonRecord record : homePlayers) {
					playerAccumulate(record, homeTeamData, roadTeamData);
				}
				for (PlayerSeasonRecord record : roadPlayers) {
					playerAccumulate(record, roadTeamData, homeTeamData);
				}
				
				if (!teamRecords.containsKey(homeTeam)) {
					teamRecords.put(homeTeam, new TeamSeasonRecord());
				}
				if (!teamRecords.containsKey(roadTeam)) {
					teamRecords.put(roadTeam, new TeamSeasonRecord());
				}
				
				TeamSeasonRecord homeTeamRecord = teamRecords.get(homeTeam);
				TeamSeasonRecord roadTeamRecord = teamRecords.get(roadTeam);
				
				teamAccumulate(homeTeamRecord, homeTeamData, roadTeamData);
				teamAccumulate(roadTeamRecord, roadTeamData, homeTeamData);
				
				if (homePoints > roadPoints) {
					homeTeamRecord.wins ++;
					roadTeamRecord.loses ++;
				}else {
					homeTeamRecord.loses ++;
					roadTeamRecord.wins ++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 数据累加，包括一队的各种数据累加和球员的数据累加 */
	public void dataAccumulate(int[] teamData, PlayerSeasonRecord playerRecord, String[] s,
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
		
		for (int i=2;i<18;i++){
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
		playerRecord.personalGoal += lineInt[17];
		playerRecord.matchCount ++;
	}
	
	/** 把球队本场比赛的数据累加到球员赛季数据中 */
	public void playerAccumulate(PlayerSeasonRecord playerRecord, int [] teamData, int[] oppoData){
		playerRecord.oppoDefensiveRebound += oppoData[10];
		playerRecord.oppoFieldAttempt += oppoData[4];
		playerRecord.oppoFieldGoal += oppoData[3];
		playerRecord.oppoFoul += oppoData[16];
		playerRecord.oppoFreethrowAttempt += oppoData[8];
		playerRecord.oppoOffensiveRebound += oppoData[9];
		playerRecord.oppoThreePointAttempt += oppoData[6];
		playerRecord.oppoTotalRebound += oppoData[11];
		
		playerRecord.teamDefensiveRebound += teamData[10];
		playerRecord.teamFieldAttempt += teamData[4];
		playerRecord.teamFieldGoal += teamData[3];
		playerRecord.teamFoul += teamData[16];
		playerRecord.teamFreethrowAttempt += teamData[8];
		playerRecord.teamOffensiveRebound += teamData[9];
		playerRecord.teamTime += teamData[2];
		playerRecord.teamTotalRebound += teamData[11];
	}
	
	public void teamAccumulate(TeamSeasonRecord teamRecord, int [] teamData, int [] oppoData){
		teamRecord.matchCount ++;
		teamRecord.teamGoal += teamData[17];
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
		teamRecord.matchCount ++;
		
		teamRecord.oppoDefensiveRebound += oppoData[10];
		teamRecord.oppoFieldAttempt += oppoData[4];
		teamRecord.oppoFieldGoal += oppoData[3];
		teamRecord.oppoFreethrowAttempt += oppoData[8];
		teamRecord.oppoOffensiveRebound += oppoData[9];
		teamRecord.oppoGoal += oppoData[17];
		teamRecord.oppoTurnover += oppoData[15];
	}
	
}
