
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
import dataservice.SeasonDataService;
import enums.Position;
import enums.ScreenDivision;

/**
 * 读取并累加赛季数据。数据结构是两层map。外层是赛季到map的映射。内层是球员/球队名到其该赛季数据的映射
 * @author Issac Ding
 * @version 2015年3月14日  下午4:04:31
 */

public class SeasonData implements SeasonDataService {
	
	public SeasonData() {
		if (allPlayerRecords.size() == 0 || allTeamRecords.size() == 0) loadMatches();
	}
	
	/** 存储所有球员的所有赛季数据记录 */
	private static HashMap<String, HashMap<String, PlayerSeasonVO>> allPlayerRecords =
			new HashMap<String, HashMap<String,PlayerSeasonVO>>();
	
	/** 存储所有球队的所有赛季数据记录 */
	private static HashMap<String, HashMap<String, TeamSeasonVO>> allTeamRecords = 
			new HashMap<String, HashMap<String, TeamSeasonVO>>();
	
	/**
	 * @see dataservice.SeasonDataService#getScreenedPlayerSeasonData(enums.Position, enums.ScreenDivision)
	 */
	@Override
	public ArrayList<PlayerSeasonVO> getScreenedPlayerSeasonData(Position position,
			ScreenDivision division, String season) {
		
		HashMap<String, PlayerSeasonVO> playerRecords = allPlayerRecords.get(season);
		
		if (playerRecords == null) {
			return new ArrayList<PlayerSeasonVO>();
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
		
		if (position == Position.ALL && division == ScreenDivision.ALL) {
			return getAllPlayerSeasonData(season);
		}else if (position == Position.ALL && 
				(division == ScreenDivision.WEST || division == ScreenDivision.EAST)){
			while (itr.hasNext()) {
				PlayerSeasonVO record = itr.next().getValue();
				if (Constants.getAreaByAbbr(record.teamName) == division) {
					result.add(record);
				}
			}
		}else if (position == Position.ALL && 
				(division != ScreenDivision.WEST && division != ScreenDivision.EAST)){
			while (itr.hasNext()) {
				PlayerSeasonVO record = itr.next().getValue();
				if (Constants.getDivisionByAbbr(record.teamName) == division) {
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
						Constants.getAreaByAbbr(record.teamName) == division) {
					result.add(record);
				}
			}
		}else{
			while (itr.hasNext()) {
				PlayerSeasonVO record = itr.next().getValue();
				if (record.getPosition() == posChar &&
						Constants.getDivisionByAbbr(record.teamName) == division) {
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
	public ArrayList<PlayerSeasonVO> getAllPlayerSeasonData(String season) {
		HashMap<String, PlayerSeasonVO> playerRecords = allPlayerRecords.get(season);
		if (playerRecords == null) {
			return new ArrayList<PlayerSeasonVO>();
		}
		return new ArrayList<PlayerSeasonVO>(playerRecords.values());
	}
	
	/**
	 * @see dataservice.SeasonDataService#getScreenedTeamSeasonData(enums.ScreenDivision)
	 */
	@Override
	public ArrayList<TeamSeasonVO> getScreenedTeamSeasonData(ScreenDivision division, String season) {
		
		HashMap<String, TeamSeasonVO> teamRecords = allTeamRecords.get(season);
		if (teamRecords == null) {
			return new ArrayList<TeamSeasonVO>();
		}
		
		if (division == ScreenDivision.ALL){
			return new ArrayList<TeamSeasonVO>(teamRecords.values());
		}
		
		Iterator<Map.Entry<String, TeamSeasonVO>> itr = teamRecords.entrySet().iterator();
		ArrayList<TeamSeasonVO> result = new ArrayList<TeamSeasonVO>();
		
		if (division == ScreenDivision.EAST || division == ScreenDivision.WEST) {
			while (itr.hasNext()) {
				TeamSeasonVO record = itr.next().getValue();
				if (Constants.getAreaByAbbr(record.getTeamName()) == division){
					result.add(record);
				}
			}
		}else {
			while (itr.hasNext()) {
				TeamSeasonVO record = itr.next().getValue();
				if (Constants.getDivisionByAbbr(record.getTeamName()) == division){
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
	public String getTeamAbbrByPlayer(String playerName, String season) {
		HashMap<String, PlayerSeasonVO> playerRecords = allPlayerRecords.get(season);
		if (playerRecords == null) {
			return Constants.UNKNOWN;
		}
		PlayerSeasonVO record = playerRecords.get(playerName);
		if (record != null) return record.getTeam(); 
		else return Constants.UNKNOWN;
	}
	
	/**
	 * @see dataservice.SeasonDataService#getPlayerSeasonDataByName(java.lang.String)
	 */
	@Override
	public PlayerSeasonVO getPlayerSeasonDataByName(String playerName, String season) {
		HashMap<String, PlayerSeasonVO> playerRecords = allPlayerRecords.get(season);
		if (playerRecords == null) {
			return new PlayerSeasonVO(playerName);
		}
		PlayerSeasonVO record = playerRecords.get(playerName);
		if (record == null) return new PlayerSeasonVO(playerName); 
		else return record;
	}
	
	/**
	 * @see dataservice.SeasonDataService#getTeamDataByAbbr(java.lang.String)
	 */
	@Override
	public TeamSeasonVO getTeamDataByAbbr(String abbr, String season) {
		HashMap<String, TeamSeasonVO> teamRecords = allTeamRecords.get(season);
		if (teamRecords == null) {
			return new TeamSeasonVO(abbr);
		}
		TeamSeasonVO record = teamRecords.get(abbr);
		if (record != null) return record;
		else return new TeamSeasonVO(abbr);
	}
	
	private void loadMatches() {
		File[] files = Utility.getSortedMatchFiles();
		MatchesAccumulator accumulator = new MatchesAccumulator(allPlayerRecords, allTeamRecords);
		accumulator.accumulate(files);
		Iterator<Entry<String, HashMap<String, PlayerSeasonVO>>> itr = allPlayerRecords.entrySet().iterator();
		while(itr.hasNext()) {
			HashMap<String, PlayerSeasonVO> vos = itr.next().getValue();
			Iterator<Entry<String, PlayerSeasonVO>> iterator = vos.entrySet().iterator();
			while (iterator.hasNext()) {
				iterator.next().getValue().update();
			}
		}
	}
	
	/**
	 * @see dataservice.SeasonDataService#getPlayerNamesByTeamAbbr(java.lang.String)
	 */
	@Override
	public ArrayList<String> getPlayerNamesByTeamAbbr(String abbr) {
		ArrayList<String> result = new ArrayList<String>();
		Iterator<Entry<String, HashMap<String, PlayerSeasonVO>>> itr = allPlayerRecords.entrySet().iterator();
		while(itr.hasNext()) {
			HashMap<String, PlayerSeasonVO> vos = itr.next().getValue();
			Iterator<Entry<String, PlayerSeasonVO>> iterator = vos.entrySet().iterator();
			while (iterator.hasNext()) {
				PlayerSeasonVO vo = iterator.next().getValue();
				if (vo.getTeam().equals(abbr)) {
					result.add(vo.getName());
				}
				iterator.next().getValue().update();
			}
		}
		return result;
	}
	
	/** 向playerdata提供所有参加过比赛的球员的名字 */
	public ArrayList<String> getPlayerNames() {
		ArrayList<ArrayList<String>> listOfNameLists = new ArrayList<ArrayList<String>>();
		Iterator<HashMap<String, PlayerSeasonVO>> itr = allPlayerRecords.values().iterator();
		while(itr.hasNext()) {
			listOfNameLists.add(new ArrayList<String>(itr.next().keySet()));
		}
		ArrayList<String> result = new ArrayList<String>();
		for (ArrayList<String> list : listOfNameLists) {
			list.removeAll(result);
			result.addAll(list);
		}
		return result;
	}
	
	public static void clear() {
		allPlayerRecords.clear();
		allTeamRecords.clear();
	}
	
}
