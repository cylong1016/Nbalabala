
package data.seasondata;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import utility.Constants;
import utility.Utility;
import vo.PlayerSeasonVO;
import vo.TeamSeasonVO;
import data.teamdata.TeamData;
import dataservice.SeasonDataService;
import dataservice.TeamDataService;
import enums.Position;
import enums.ScreenDivision;

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
	private static HashMap<String, PlayerSeasonVO> playerRecords = new HashMap<String, PlayerSeasonVO>();
	
	/** 存储所有球队的赛季数据记录 */
	private static HashMap<String, TeamSeasonVO> teamRecords = new HashMap<String, TeamSeasonVO>();
	
	/**
	 * @see dataservice.SeasonDataService#getScreenedPlayerSeasonData(enums.Position, enums.ScreenDivision)
	 */
	@Override
	public ArrayList<PlayerSeasonVO> getScreenedPlayerSeasonData(Position position,
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
		
		ArrayList<PlayerSeasonVO> result = new ArrayList<PlayerSeasonVO>();
		
		Iterator<Map.Entry<String, PlayerSeasonVO>> itr = playerRecords.entrySet().iterator();
		
		//需要队伍信息来查询球员属于哪个分区
		TeamDataService teamData = new TeamData();
		
		if (position == Position.ALL && division == ScreenDivision.ALL) {
			return getAllPlayerSeasonData();
		}else if (position == Position.ALL && 
				(division == ScreenDivision.WEST || division == ScreenDivision.EAST)){
			while (itr.hasNext()) {
				PlayerSeasonVO record = itr.next().getValue();
				if (teamData.getAreaByAbbr(record.teamName) == division) {
					result.add(record);
				}
			}
		}else if (position == Position.ALL && 
				(division != ScreenDivision.WEST && division != ScreenDivision.EAST)){
			while (itr.hasNext()) {
				PlayerSeasonVO record = itr.next().getValue();
				if (teamData.getDivisionByAbbr(record.teamName) == division) {
					result.add(record);
				}
			}
		}else if (position != Position.ALL && division == ScreenDivision.ALL) {
			while (itr.hasNext()) {
				PlayerSeasonVO record = itr.next().getValue();
				if (record.getPosition() == posChar) {
					result.add(record);
				}
			}
		}else if (position != Position.ALL && 
				(division == ScreenDivision.WEST || division == ScreenDivision.EAST)){
			while (itr.hasNext()) {
				PlayerSeasonVO record = itr.next().getValue();
				if (record.getPosition() == posChar && 
						teamData.getAreaByAbbr(record.teamName) == division) {
					result.add(record);
				}
			}
		}else{
			while (itr.hasNext()) {
				PlayerSeasonVO record = itr.next().getValue();
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
	public ArrayList<PlayerSeasonVO> getAllPlayerSeasonData() {
		return new ArrayList<PlayerSeasonVO>(playerRecords.values());
	}
	
	/**
	 * @see dataservice.SeasonDataService#getScreenedTeamSeasonData(enums.ScreenDivision)
	 */
	@Override
	public ArrayList<TeamSeasonVO> getScreenedTeamSeasonData(ScreenDivision division) {
		if (division == ScreenDivision.ALL){
			return new ArrayList<TeamSeasonVO>(teamRecords.values());
		}
		
		Iterator<Map.Entry<String, TeamSeasonVO>> itr = teamRecords.entrySet().iterator();
		TeamDataService teamData = new TeamData();
		ArrayList<TeamSeasonVO> result = new ArrayList<TeamSeasonVO>();
		
		if (division == ScreenDivision.EAST || division == ScreenDivision.WEST) {
			while (itr.hasNext()) {
				TeamSeasonVO record = itr.next().getValue();
				if (teamData.getAreaByAbbr(record.getTeamName()) == division){
					result.add(record);
				}
			}
		}else {
			while (itr.hasNext()) {
				TeamSeasonVO record = itr.next().getValue();
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
		PlayerSeasonVO record = playerRecords.get(playerName);
		if (record != null) return record.getTeam(); 
		else return Constants.UNKNOWN;
	}
	
	/**
	 * @see dataservice.SeasonDataService#getPlayerSeasonDataByName(java.lang.String)
	 */
	@Override
	public PlayerSeasonVO getPlayerSeasonDataByName(String playerName) {
		PlayerSeasonVO record = playerRecords.get(playerName);
		if (record == null) return new PlayerSeasonVO(playerName); 
		else return record;
	}
	
	/**
	 * @see dataservice.SeasonDataService#getTeamDataByAbbr(java.lang.String)
	 */
	@Override
	public TeamSeasonVO getTeamDataByAbbr(String abbr) {
		return teamRecords.get(abbr);
	}
	
	private void loadMatches() {
		File[] files = Utility.getSortedMatchFiles();
		MatchesAccumulator accumulator = new MatchesAccumulator(playerRecords, teamRecords);
		accumulator.accumulate(files);
	}
	
	/**
	 * @see dataservice.SeasonDataService#getPlayerNamesByTeamAbbr(java.lang.String)
	 */
	@Override
	public ArrayList<String> getPlayerNamesByTeamAbbr(String abbr) {
		ArrayList<String> result = new ArrayList<String>();
		Iterator<Entry<String, PlayerSeasonVO>> itr = playerRecords.entrySet().iterator();
		while (itr.hasNext()) {
			PlayerSeasonVO record = itr.next().getValue();
			if (record.getTeam().equals(abbr)) {
				result.add(record.getName());
			}
		}
		return result;
	}
	
	/** 向playerdata提供所有参加过比赛的球员的名字 */
	public ArrayList<String> getPlayerNames() {
		return new ArrayList<String>(playerRecords.keySet());
	}
	
}
