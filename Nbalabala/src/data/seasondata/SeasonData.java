
package data.seasondata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 读取并累加赛季数据
 * @author Issac Ding
 * @version 2015年3月14日  下午4:04:31
 */
/**
 * 
 * @author Issac Ding
 * @version 2015年3月16日  下午6:39:27
 */
public class SeasonData {
	
	private final String path = "NBAdata/matches";
	private static int HOME = 0;
	private static int ROAD = 0;
	
	public SeasonData(){
		loadMatches();
	}
	
	/** 存储所有球员的赛季数据记录 */
	private HashMap<String, PlayerSeasonRecord> playerRecords = new HashMap<String, PlayerSeasonRecord>();
	
	/** 存储所有球队的赛季数据记录 */
	private HashMap<String, TeamSeasonRecord> teamRecords = new HashMap<String, TeamSeasonRecord>();
	
	public ArrayList<PlayerSeasonRecord> getPlayerSeasonData() {
		return new ArrayList<PlayerSeasonRecord>(playerRecords.values());
	}
	
	public ArrayList<TeamSeasonRecord> getTeamSeasonData() {
		return new ArrayList<TeamSeasonRecord>(teamRecords.values());
	}
	
	/** 将文件按照比赛时间排序 */
	public void sortFiles(File [] files) {
		Arrays.sort(files, new Comparator<File>() {
			public int compare(File f1, File f2) {
				String name1 = f1.getName();
				String name2 = f2.getName();
				String[]s1 = name1.split("_|-");
				String[]s2 = name2.split("_|-"); 
				int month1 = Integer.parseInt(s1[2]);
				int month2 = Integer.parseInt(s2[2]);
				if (month1 < 8) month1 += 12;
				if (month2 < 8) month2 += 12;
				int deltaMonth = month1 - month2;
				if (deltaMonth != 0) return deltaMonth;
				else {
					int day1 = Integer.parseInt(s1[3]);
					int day2 = Integer.parseInt(s2[3]);
					return day1 - day2;
				}
			}
		});
	}
	
	public static void main(String[]args){
		SeasonData data = new SeasonData();
		PlayerSeasonRecord record = data.playerRecords.get("LeBron James");
		System.out.println(record.getSteal()/ (record.getMatchCount() + 0.0));
	}
	
	public void loadMatches() {
		File dir = new File(path);
		File[] files = dir.listFiles();
		
		//将文件按比赛时间排序
		sortFiles(files);
		
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
					
					homePlayers.add(playerRecord);
					
					dataAccumulate(homeTeamData, playerRecord, s, file, HOME);
				}
				
				//下面读取客场队
				String roadTeam = line;
				while ((line = br.readLine()) != null) {
					String[] s = line.split(";");
					if ( !playerRecords.containsKey(s[0])) playerRecords.put(s[0], 
							new PlayerSeasonRecord());
					PlayerSeasonRecord playerRecord = playerRecords.get(s[0]);
					
					roadPlayers.add(playerRecord);
					
					dataAccumulate(roadTeamData, playerRecord, s, file, ROAD);
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
			File file, int teamState) {
		//该数组0，1位置不用， 2位置为该球员的上场秒数，3之后是s的对应转化为整数
		int[] lineInt = new int[18];
		
		//识别上场时间为null的情况：
		try{
			String[] timeString = s[2].split(":");
			lineInt[2] = 60 * Integer.parseInt(timeString[0]) + 
					Integer.parseInt(timeString[1]);
		}catch(Exception e){
			modifyTime(lineInt, file, teamState);
		}
		
		for (int i = 3;i < 17;i++){
			lineInt[i] = Integer.parseInt(s[i]);
		}
		
		//识别总分为null的情况：
		try{
			lineInt[17] = Integer.parseInt(s[17]);
		}catch(Exception e){
			modifyScore(lineInt);
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
	
	/** 修正时间为null的脏数据，方法是总时间减去其他球员上场时间，总时间为48分钟+5分钟*加时赛场数*/
	private void modifyTime(int [] lineInt, File file, int teamState) {
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(file));
			br.readLine();
			int totalSeconds = ((br.readLine().split(";").length - 4) * 5 + 48) * 60;
			br.readLine();
			//有记录的总分钟数和秒数之和
			int minutes = 0;
			int seconds = 0;
			String [] line;
			
			//如果是主场队，读取文件的上半部分
			if (teamState == HOME) {
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
			lineInt[2] = totalSeconds - seconds - minutes * 60;
		}catch (Exception e){
			
		}
	}
	
	/** 修正得分，得分=（投篮数3-三分球数5）*2 + 三分球数 *3 + 罚球7 * 1 ，整理后即为下式 */
	private void modifyScore(int [] lineInt) {
		lineInt[17] = 2 * lineInt[3] + lineInt[5] + lineInt[7];
	}
}
