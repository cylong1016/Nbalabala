
package autotest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import utility.Constants;
import utility.Utility;
import data.seasondata.MatchesAccumulator;
import enums.Position;
import enums.ScreenDivision;

/**
 * 读取并累加赛季数据。数据结构是两层map。外层是赛季到map的映射。内层是球员/球队名到其该赛季数据的映射
 * @author Issac Ding
 * @version 2015年3月14日  下午4:04:31
 */

public class SeasonSimpleData{
	
	public SeasonSimpleData() {
		if (playerRecords.size() == 0 || teamRecords.size() == 0) loadMatches();
	}
	
	/** 存储所有球员的所有赛季数据记录 */
	private static HashMap<String, PlayerSimpleSeasonVO> playerRecords =
			new HashMap<String,PlayerSimpleSeasonVO>();
	
	/** 存储所有球队的所有赛季数据记录 */
	private static HashMap<String, TeamSimpleSeasonVO> teamRecords = 
			new HashMap<String, TeamSimpleSeasonVO>();
	
	public ArrayList<PlayerSimpleSeasonVO> getScreenedPlayerSeasonData(Position position,
			ScreenDivision division) {
		
		if (playerRecords == null) {
			return new ArrayList<PlayerSimpleSeasonVO>();
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
		
		ArrayList<PlayerSimpleSeasonVO> result = new ArrayList<PlayerSimpleSeasonVO>();
		
		Iterator<Map.Entry<String, PlayerSimpleSeasonVO>> itr = playerRecords.entrySet().iterator();
		
		if (position == Position.ALL && division == ScreenDivision.ALL) {
			return getAllPlayerSeasonData();
		}else if (position == Position.ALL && 
				(division == ScreenDivision.WEST || division == ScreenDivision.EAST)){
			while (itr.hasNext()) {
				PlayerSimpleSeasonVO record = itr.next().getValue();
				if (Constants.getAreaByAbbr(record.teamName) == division) {
					result.add(record);
				}
			}
		}else if (position == Position.ALL && 
				(division != ScreenDivision.WEST && division != ScreenDivision.EAST)){
			while (itr.hasNext()) {
				PlayerSimpleSeasonVO record = itr.next().getValue();
				if (Constants.getDivisionByAbbr(record.teamName) == division) {
					result.add(record);
				}
			}
		}else if (position != Position.ALL && division == ScreenDivision.ALL) {
			while (itr.hasNext()) {
				PlayerSimpleSeasonVO record = itr.next().getValue();
				if (record.getPosition() == posChar) {
					result.add(record);
				}
			}
		}else if (position != Position.ALL && 
				(division == ScreenDivision.WEST || division == ScreenDivision.EAST)){
			while (itr.hasNext()) {
				PlayerSimpleSeasonVO record = itr.next().getValue();
				if (record.getPosition() == posChar && 
						Constants.getAreaByAbbr(record.teamName) == division) {
					result.add(record);
				}
			}
		}else{
			while (itr.hasNext()) {
				PlayerSimpleSeasonVO record = itr.next().getValue();
				if (record.getPosition() == posChar &&
						Constants.getDivisionByAbbr(record.teamName) == division) {
					result.add(record);
				}
			}
		}
		return result;
	}
	
	public ArrayList<TeamSimpleSeasonVO> getScreenedTeamSeasonData(ScreenDivision division) {
		if (division == ScreenDivision.ALL){
			return new ArrayList<TeamSimpleSeasonVO>(teamRecords.values());
		}
		
		Iterator<Map.Entry<String, TeamSimpleSeasonVO>> itr = teamRecords.entrySet().iterator();
		ArrayList<TeamSimpleSeasonVO> result = new ArrayList<TeamSimpleSeasonVO>();
		
		if (division == ScreenDivision.EAST || division == ScreenDivision.WEST) {
			while (itr.hasNext()) {
				TeamSimpleSeasonVO record = itr.next().getValue();
				if (Constants.getAreaByAbbr(record.getTeamName()) == division){
					result.add(record);
				}
			}
		}else {
			while (itr.hasNext()) {
				TeamSimpleSeasonVO record = itr.next().getValue();
				if (Constants.getDivisionByAbbr(record.getTeamName()) == division){
					result.add(record);
				}
			}
		}
		return result;
	}
	
	public String getTeamAbbrByPlayer(String playerName, String season) {
		PlayerSimpleSeasonVO record = playerRecords.get(playerName);
		if (record != null) return record.getTeam(); 
		else return Constants.UNKNOWN;
	}
	
	public PlayerSimpleSeasonVO getPlayerSeasonDataByName(String playerName) {
		PlayerSimpleSeasonVO record = playerRecords.get(playerName);
		if (record == null) return new PlayerSimpleSeasonVO(playerName); 
		else return record;
	}
	
	public TeamSimpleSeasonVO getTeamDataByAbbr(String abbr) {
		TeamSimpleSeasonVO record = teamRecords.get(abbr);
		if (record != null) return record;
		else return new TeamSimpleSeasonVO(abbr);
	}
	
	private void loadMatches() {
		File[] files = Utility.getSortedMatchFiles();
		
		SimpleMatchesAccumulator accumulator = new SimpleMatchesAccumulator(playerRecords, teamRecords);
		accumulator.accumulate(files, false);
		
		Iterator<Entry<String, PlayerSimpleSeasonVO>> playerItr = playerRecords.entrySet().iterator();
		while(playerItr.hasNext()) {
			playerItr.next().getValue().update();
		}
		
		Iterator<Entry<String, TeamSimpleSeasonVO>> teamItr = teamRecords.entrySet().iterator();
		while(teamItr.hasNext()) {
			teamItr.next().getValue().update();
		}
	}
	
	public static void clear() {
		playerRecords.clear();
		teamRecords.clear();
	}
	
	/** 发现新文件加入时调用此方法 */
	public void appendMatches(ArrayList<File> files) {
		int size = files.size();
		File[] newFiles = new File[size];
		for (int i=0;i<size;i++) {
			newFiles[i] = files.get(i);
		}
		SimpleMatchesAccumulator accumulator = 
				new SimpleMatchesAccumulator(playerRecords, teamRecords);
		accumulator.accumulate(newFiles, true);
		
		HashSet<PlayerSimpleSeasonVO> playerUpdated = accumulator.getUpdatedPlayers();
		HashSet<TeamSimpleSeasonVO> teamUpdated = accumulator.getUpdatedTeams();
		
		for (PlayerSimpleSeasonVO vo : playerUpdated) {
			vo.update();
		}
		for (TeamSimpleSeasonVO vo : teamUpdated) {
			vo.update();
		}
	}
	
	/** 发现有文件被删除时，重新读取所有文件 */
	public void reloadMatches() {
		playerRecords.clear();
		teamRecords.clear();
		new SimpleMatchesAccumulator(playerRecords, teamRecords).accumulate(Utility.getSortedMatchFiles(), false);
	}

	public ArrayList<PlayerSimpleSeasonVO> getAllPlayerSeasonData() {
		
			return new ArrayList<PlayerSimpleSeasonVO>(playerRecords.values());
		}
	
	
	public ArrayList<TeamSimpleSeasonVO> getAllTeamSeasonData() {
		
			return new ArrayList<TeamSimpleSeasonVO>(teamRecords.values());
		
	}
	
}
