
package data.seasondata;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
		accumulator.accumulate(files, false);
		
		Iterator<Entry<String, HashMap<String, PlayerSeasonVO>>> playerItr = allPlayerRecords.entrySet().iterator();
		while(playerItr.hasNext()) {
			HashMap<String, PlayerSeasonVO> vos = playerItr.next().getValue();
			Iterator<Entry<String, PlayerSeasonVO>> iterator = vos.entrySet().iterator();
			while (iterator.hasNext()) {
				iterator.next().getValue().update();
			}
		}
		
		Iterator<Entry<String, HashMap<String, TeamSeasonVO>>> teamItr = allTeamRecords.entrySet().iterator();
		while(teamItr.hasNext()) {
			HashMap<String, TeamSeasonVO> vos = teamItr.next().getValue();
			Iterator<Entry<String, TeamSeasonVO>> iterator = vos.entrySet().iterator();
			while (iterator.hasNext()) {
				iterator.next().getValue().update();
			}
		}
	}
	
	/**
	 * @see dataservice.SeasonDataService#getPlayerNamesByTeamAbbr(java.lang.String)
	 */
	@Override
	public ArrayList<String> getPlayerNamesByTeamAbbr(String abbr, String season) {
		ArrayList<String> result = new ArrayList<String>();
		HashMap<String, PlayerSeasonVO> seasonPlayers = allPlayerRecords.get(season);
		if (seasonPlayers == null) return result;
		Iterator<Entry<String, PlayerSeasonVO>> itr = seasonPlayers.entrySet().iterator();
		while(itr.hasNext()) {
			PlayerSeasonVO vo = itr.next().getValue();
			if (vo.getTeam().equals(abbr))
				result.add(vo.name);
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
	
	/** 发现新文件加入时调用此方法 */
	public void appendMatches(ArrayList<File> files) {
		int size = files.size();
		File[] newFiles = new File[size];
		for (int i=0;i<size;i++) {
			newFiles[i] = files.get(i);
		}
		MatchesAccumulator accumulator = 
				new MatchesAccumulator(allPlayerRecords, allTeamRecords);
		accumulator.accumulate(newFiles, true);
		
		HashSet<PlayerSeasonVO> playerUpdated = accumulator.getUpdatedPlayers();
		HashSet<TeamSeasonVO> teamUpdated = accumulator.getUpdatedTeams();
		
		for (PlayerSeasonVO vo : playerUpdated) {
			vo.update();
		}
		for (TeamSeasonVO vo : teamUpdated) {
			vo.update();
		}
	}
	
	/** 发现有文件被删除时，重新读取所有文件 */
	public static void reloadMatches() {
		allPlayerRecords.clear();
		allTeamRecords.clear();
		new MatchesAccumulator(allPlayerRecords, allTeamRecords).accumulate(Utility.getSortedMatchFiles(), false);
		
		updateData();
	}

	/**
	 * @see dataservice.SeasonDataService#getAllPlayerRecentSeasonData()
	 */
	@Override
	public ArrayList<PlayerSeasonVO> getAllPlayerRecentSeasonData() {
		HashMap<String, PlayerSeasonVO> map = allPlayerRecords.get(getRecentSeason());
		if (map == null) {
			return new ArrayList<PlayerSeasonVO>();
		}else {
			return new ArrayList<PlayerSeasonVO>(map.values());
		}
	}
	
	private String getRecentSeason() {
		Iterator<Entry<String, HashMap<String, TeamSeasonVO>>> itr = 
				allTeamRecords.entrySet().iterator();
		int lastStartYear = 0;
		String lastSeason = null;
		while(itr.hasNext()) {
			String season = itr.next().getKey();
			String [] seasonS = season.split("-");
			int startYear = Integer.parseInt(seasonS[0]);
			if (startYear > 20) startYear -= 100;
			if (startYear > lastStartYear) {
				lastSeason = season;
				lastStartYear = startYear;
			}
		}
		return lastSeason == null? "13-14": lastSeason;
	}

	/**
	 * @see dataservice.SeasonDataService#getAllTeamRecentSeasonData()
	 */
	@Override
	public ArrayList<TeamSeasonVO> getAllTeamRecentSeasonData() {
		HashMap<String, TeamSeasonVO> map = allTeamRecords.get(getRecentSeason());
		if (map == null) {
			return new ArrayList<TeamSeasonVO>();
		}else {
			return new ArrayList<TeamSeasonVO>(map.values());
		}
	}
	
	public ArrayList<TeamSeasonVO> getAllTeamSeasonData(String season) {
		HashMap<String, TeamSeasonVO> map = allTeamRecords.get(season);
		if (map == null) {
			return new ArrayList<TeamSeasonVO>();
		}else {
			return new ArrayList<TeamSeasonVO>(map.values());
		}
	}
	
	private static void updateData() {
		Iterator<Entry<String, HashMap<String, PlayerSeasonVO>>> playerItr = allPlayerRecords.entrySet().iterator();
		while(playerItr.hasNext()) {
			HashMap<String, PlayerSeasonVO> vos = playerItr.next().getValue();
			Iterator<Entry<String, PlayerSeasonVO>> iterator = vos.entrySet().iterator();
			while (iterator.hasNext()) {
				iterator.next().getValue().update();
			}
		}
		
		Iterator<Entry<String, HashMap<String, TeamSeasonVO>>> teamItr = allTeamRecords.entrySet().iterator();
		while(teamItr.hasNext()) {
			HashMap<String, TeamSeasonVO> vos = teamItr.next().getValue();
			Iterator<Entry<String, TeamSeasonVO>> iterator = vos.entrySet().iterator();
			while (iterator.hasNext()) {
				iterator.next().getValue().update();
			}
		}
	}

	/**
	 * @see dataservice.SeasonDataService#getAllPlayerRecentSeasonTodayData()
	 */
	@Override
	public ArrayList<PlayerSeasonVO> getAllPlayerRecentSeasonTodayData() {
		HashMap<String, PlayerSeasonVO> map = allPlayerRecords.get(getRecentSeason());
		if (map == null) {
			return new ArrayList<PlayerSeasonVO>();
		}else {
			ArrayList<PlayerSeasonVO> result = new ArrayList<PlayerSeasonVO>();
			Iterator<Entry<String, PlayerSeasonVO>> itr = map.entrySet().iterator();
			while(itr.hasNext()) {
				PlayerSeasonVO vo = itr.next().getValue();
				if (vo.latestMonth == Utility.latestMonth && vo.latestDay == Utility.latestDay) {
					result.add(vo);
				}
			}
			return result;
		}
	}
	
}
