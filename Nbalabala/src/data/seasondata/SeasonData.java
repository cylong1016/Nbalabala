
package data.seasondata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import po.PlayerSeasonPO;
import po.TeamSeasonPO;
import utility.Constants;
import utility.Utility;
import data.Database;
import dataservice.SeasonDataService;
import enums.Position;
import enums.ScreenDivision;

/**
 * 读取并维护赛季数据。数据结构是两层map。外层是赛季到map的映射。内层是球员/球队名到其该赛季数据的映射
 * @author Issac Ding
 * @version 2015年3月14日  下午4:04:31
 */

public class SeasonData implements SeasonDataService {
	
	/** 存储所有球员的所有赛季数据记录 */
	private static HashMap<String, HashMap<String, PlayerSeasonPO>> allPlayerRecords =
			new HashMap<String, HashMap<String,PlayerSeasonPO>>();
	
	/** 存储所有球队的所有赛季数据记录 */
	private static HashMap<String, HashMap<String, TeamSeasonPO>> allTeamRecords = 
			new HashMap<String, HashMap<String, TeamSeasonPO>>();
	
	/** 保留在内存中的赛季数据的数量，增加这个数值会减少访问数据库，但是会增加内存占用量 */
	private static int seasonCacheSize = 5;
	
	/** 记录最近被访问的球员赛季数据。 */
	private static ArrayList<String> recentPlayerSeasons = new ArrayList<String>();
	
	/** 记录最近被访问的球队赛季数据。 */
	private static ArrayList<String> recentTeamSeasons = new ArrayList<String>();
	
	/**
	 * 检查一个赛季的球员数据是否在内存中，如果没有，将该赛季的球员数据从数据库读取进来
	 */
	private void checkAndReadPlayerSeasonData(String season) {
		String seasonToAbandon = checkAndAdjustQueue(recentPlayerSeasons, season);
		if (seasonToAbandon != null) {
			allPlayerRecords.remove(seasonToAbandon);
		}
		if (allPlayerRecords.get(season) != null) {
			return;			// 已经在内存中，不用读了
		}
		try {
			HashMap<String, PlayerSeasonPO> newMap = new HashMap<String, PlayerSeasonPO>();
			Statement statement = Database.conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from player_season where season='" + season + "'");
			while(rs.next()) {
				PlayerSeasonPO po = new PlayerSeasonPO();
				po.name = rs.getString(1);
				po.season = rs.getString(2);
				po.latestMatchID = rs.getInt(3);
				po.latestMatchDate = rs.getDate(4);
				po.teamAbbr = rs.getString(5);
				po.matchCount = rs.getInt(6);
				po.firstCount = rs.getInt(7);
				po.firstCountAvg = rs.getFloat(8);
				po.minutes = rs.getFloat(9);
				po.minutesAvg = rs.getFloat(10);
				po.fieldMade = rs.getInt(11);
				po.fieldMadeAvg = rs.getFloat(12);
				po.fieldAttempt = rs.getInt(13);
				po.fieldAttemptAvg = rs.getFloat(14);
				po.fieldPercent = rs.getFloat(15);
				po.threePointMade = rs.getInt(16);
				po.threePointMadeAvg = rs.getFloat(17);
				po.threePointAttempt = rs.getInt(18);
				po.threePointAttemptAvg = rs.getFloat(19);
				po.threePointPercent = rs.getFloat(20);
				po.freethrowMade = rs.getInt(21);
				po.freethrowMadeAvg = rs.getFloat(22);
				po.freethrowAttempt = rs.getInt(23);
				po.freethrowAttemptAvg = rs.getFloat(24);
				po.freethrowPercent = rs.getFloat(25);
				po.offensiveRebound = rs.getInt(26);
				po.offensiveReboundAvg = rs.getFloat(27);
				po.defensiveRebound = rs.getInt(28);
				po.defensiveReboundAvg = rs.getInt(29);
				po.totalRebound = rs.getInt(30);
				po.totalReboundAvg = rs.getFloat(31);
				po.assist = rs.getInt(32);
				po.assistAvg = rs.getFloat(33);
				po.steal = rs.getInt(34);
				po.stealAvg = rs.getFloat(35);
				po.block = rs.getInt(36);
				po.blockAvg = rs.getFloat(37);
				po.turnover = rs.getInt(38);
				po.turnoverAvg = rs.getFloat(39);
				po.foul = rs.getInt(40);
				po.foulAvg = rs.getFloat(41);
				po.score = rs.getInt(42);
				po.scoreAvg = rs.getFloat(43);
				po.doubleDoubleCount = rs.getInt(44);
				po.doubleDoubleAvg = rs.getFloat(45);
				po.efficiency = rs.getInt(46);
				po.efficiencyAvg = rs.getFloat(47);
				po.scoreReboundAssist = rs.getInt(48);
				po.scoreReboundAssistAvg = rs.getFloat(49);
				po.gmsc = rs.getFloat(50);
				po.gmscAvg = rs.getFloat(51);
				po.realFieldPercent = rs.getFloat(52);
				po.fieldEff = rs.getFloat(53);
				po.offensiveReboundPercent = rs.getFloat(54);
				po.defensiveReboundPercent = rs.getFloat(55);
				po.totalReboundPercent = rs.getFloat(56);
				po.stealPercent = rs.getFloat(57);
				po.blockPercent = rs.getFloat(58);
				po.turnOverPercent = rs.getFloat(59);
				po.foulPercent = rs.getFloat(60);
				po.usePercent = rs.getFloat(61);
				po.assistPercent = rs.getFloat(62);
				po.position = rs.getString(63);
				newMap.put(po.name, po);
			}
			if (newMap.size() > 0) 	allPlayerRecords.put(season, newMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 检查一个赛季的球队数据是否在内存中，如果没有，将该赛季的球队数据从数据库读取进来
	 */
	private void checkAndReadTeamSeasonData(String season) {
		String seasonToAbandon = checkAndAdjustQueue(recentTeamSeasons, season);
		if (seasonToAbandon != null) {
			allTeamRecords.remove(seasonToAbandon);
		}
		if (allTeamRecords.get(season) != null) {
			return;
		}
		try {
			HashMap<String, TeamSeasonPO> newMap = new HashMap<String, TeamSeasonPO>();
			Statement statement = Database.conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from team_season where season='" + season + "'");
			while(rs.next()) {
				TeamSeasonPO po = new TeamSeasonPO();
				po.abbr = rs.getString(1);
				po.season = rs.getString(2);
				po.matchCount = rs.getInt(3);
				po.wins = rs.getInt(4);
				po.winning = rs.getFloat(5);
				po.fieldMade = rs.getInt(6);
				po.fieldMadeAvg = rs.getFloat(7);
				po.fieldAttempt = rs.getInt(8);
				po.fieldAttemptAvg = rs.getFloat(9);
				po.fieldPercent = rs.getFloat(10);
				po.threePointMade = rs.getInt(11);
				po.threePointMadeAvg = rs.getFloat(12);
				po.threePointAttempt = rs.getInt(13);
				po.threePointAttemptAvg = rs.getFloat(14);
				po.threePointPercent = rs.getFloat(15);
				po.freethrowMade = rs.getInt(16);
				po.freethrowMadeAvg = rs.getFloat(17);
				po.freethrowAttempt = rs.getInt(18);
				po.freethrowAttemptAvg = rs.getFloat(19);
				po.freethrowPercent = rs.getFloat(20);
				po.offensiveRebound = rs.getInt(21);
				po.offensiveReboundAvg = rs.getFloat(22);
				po.defensiveRebound = rs.getInt(23);
				po.defensiveReboundAvg = rs.getInt(24);
				po.totalRebound = rs.getInt(25);
				po.totalReboundAvg = rs.getFloat(26);
				po.assist = rs.getInt(27);
				po.assistAvg = rs.getFloat(28);
				po.steal = rs.getInt(29);
				po.stealAvg = rs.getFloat(30);
				po.block = rs.getInt(31);
				po.blockAvg = rs.getFloat(32);
				po.turnover = rs.getInt(33);
				po.turnoverAvg = rs.getFloat(34);
				po.foul = rs.getInt(35);
				po.foulAvg = rs.getFloat(36);
				po.score = rs.getInt(37);
				po.scoreAvg = rs.getFloat(38);
				po.offensiveRound = rs.getFloat(39);
				po.offensiveRoundAvg = rs.getFloat(40);
				po.defensiveRound = rs.getFloat(41);
				po.defensiveRoundAvg = rs.getFloat(42);
				po.offensiveEff = rs.getFloat(43);
				po.defensiveEff = rs.getFloat(44);
				po.assistEff = rs.getFloat(45);
				po.stealEff = rs.getFloat(46);
				po.offensiveReboundEff = rs.getFloat(47);
				po.defensiveReboundEff = rs.getFloat(48);
				po.oppoFieldPercent = rs.getFloat(49);
				po.oppoScoreAvg = rs.getFloat(50);
				newMap.put(po.abbr, po);
			}
			if (newMap.size() > 0) 	allTeamRecords.put(season, newMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @see dataservice.SeasonDataService#getScreenedPlayerSeasonData(enums.Position, enums.ScreenDivision)
	 */
	@Override
	public ArrayList<PlayerSeasonPO> getScreenedPlayerSeasonData(Position position,
			ScreenDivision division, String season) {
		//看有没有本赛季数据，若内存里有则拿出来，没有则去数据库里读。数据库也没有的话只好返回空表
		checkAndReadPlayerSeasonData(season);
		HashMap<String, PlayerSeasonPO> playerRecords = allPlayerRecords.get(season);
		if (playerRecords == null) {
			return new ArrayList<PlayerSeasonPO>();
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
		
		ArrayList<PlayerSeasonPO> result = new ArrayList<PlayerSeasonPO>();
		Iterator<Map.Entry<String, PlayerSeasonPO>> itr = playerRecords.entrySet().iterator();
		
		if (position == Position.ALL && division == ScreenDivision.ALL) {
			return getAllPlayerSeasonData(season);
		}else if (position == Position.ALL && 
				(division == ScreenDivision.WEST || division == ScreenDivision.EAST)){
			while (itr.hasNext()) {
				PlayerSeasonPO record = itr.next().getValue();
				if (Constants.getAreaByAbbr(record.teamAbbr) == division) {
					result.add(record);
				}
			}
		}else if (position == Position.ALL && 
				(division != ScreenDivision.WEST && division != ScreenDivision.EAST)){
			while (itr.hasNext()) {
				PlayerSeasonPO record = itr.next().getValue();
				if (Constants.getDivisionByAbbr(record.teamAbbr) == division) {
					result.add(record);
				}
			}
		}else if (position != Position.ALL && division == ScreenDivision.ALL) {
			while (itr.hasNext()) {
				PlayerSeasonPO record = itr.next().getValue();
				if (record.position.lastIndexOf(posChar) >= 0) {
					result.add(record);
				}
			}
		}else if (position != Position.ALL && 
				(division == ScreenDivision.WEST || division == ScreenDivision.EAST)){
			while (itr.hasNext()) {
				PlayerSeasonPO record = itr.next().getValue();
				if (record.position.lastIndexOf(posChar) >= 0 && 
						Constants.getAreaByAbbr(record.teamAbbr) == division) {
					result.add(record);
				}
			}
		}else{
			while (itr.hasNext()) {
				PlayerSeasonPO record = itr.next().getValue();
				if (record.position.lastIndexOf(posChar) >= 0 &&
						Constants.getDivisionByAbbr(record.teamAbbr) == division) {
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
	public ArrayList<PlayerSeasonPO> getAllPlayerSeasonData(String season) {
		checkAndReadPlayerSeasonData(season);
		HashMap<String, PlayerSeasonPO> playerRecords = allPlayerRecords.get(season);
		if (playerRecords == null) {
			return new ArrayList<PlayerSeasonPO>();
		}
		return new ArrayList<PlayerSeasonPO>(playerRecords.values());
	}
	
	/**
	 * @see dataservice.SeasonDataService#getScreenedTeamSeasonData(enums.ScreenDivision)
	 */
	@Override
	public ArrayList<TeamSeasonPO> getScreenedTeamSeasonData(ScreenDivision division, String season) {
		checkAndReadTeamSeasonData(season);
		HashMap<String, TeamSeasonPO> teamRecords = allTeamRecords.get(season);
		if (teamRecords == null) {
			return new ArrayList<TeamSeasonPO>();
		}
		
		if (division == ScreenDivision.ALL){
			return new ArrayList<TeamSeasonPO>(teamRecords.values());
		}
		
		Iterator<Map.Entry<String, TeamSeasonPO>> itr = teamRecords.entrySet().iterator();
		ArrayList<TeamSeasonPO> result = new ArrayList<TeamSeasonPO>();
		
		if (division == ScreenDivision.EAST || division == ScreenDivision.WEST) {
			while (itr.hasNext()) {
				TeamSeasonPO record = itr.next().getValue();
				if (Constants.getAreaByAbbr(record.abbr) == division){
					result.add(record);
				}
			}
		}else {
			while (itr.hasNext()) {
				TeamSeasonPO record = itr.next().getValue();
				if (Constants.getDivisionByAbbr(record.abbr) == division){
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
		checkAndReadTeamSeasonData(season);
		HashMap<String, PlayerSeasonPO> playerRecords = allPlayerRecords.get(season);
		if (playerRecords == null) {
			return Constants.UNKNOWN;
		}
		PlayerSeasonPO record = playerRecords.get(playerName);
		if (record != null) return record.teamAbbr; 
		else return Constants.UNKNOWN;
	}
	
	/**
	 * @see dataservice.SeasonDataService#getPlayerSeasonDataByName(java.lang.String)
	 */
	@Override
	public PlayerSeasonPO getPlayerSeasonDataByName(String playerName, String season) {
		checkAndReadTeamSeasonData(season);
		HashMap<String, PlayerSeasonPO> playerRecords = allPlayerRecords.get(season);
		if (playerRecords == null) {
			return new PlayerSeasonPO(playerName, season);
		}
		PlayerSeasonPO record = playerRecords.get(playerName);
		if (record == null) return new PlayerSeasonPO(playerName, season); 
		else return record;
	}
	
	/**
	 * @see dataservice.SeasonDataService#getTeamDataByAbbr(java.lang.String)
	 */
	@Override
	public TeamSeasonPO getTeamDataByAbbr(String abbr, String season) {
		abbr = Utility.getOldAbbr(season, abbr);
		checkAndReadTeamSeasonData(season);
		HashMap<String, TeamSeasonPO> teamRecords = allTeamRecords.get(season);
		if (teamRecords == null || teamRecords.get(abbr) == null) {
			return new TeamSeasonPO(abbr, season);
		}
		return teamRecords.get(abbr);
	}
	
	/**
	 * @see dataservice.SeasonDataService#getPlayerNamesByTeamAbbr(java.lang.String)
	 */
	@Override
	public ArrayList<String> getPlayerNamesByTeamAbbr(String abbr, String season) {
		abbr = Utility.getOldAbbr(season, abbr);
		checkAndReadPlayerSeasonData(season);
		ArrayList<String> result = new ArrayList<String>();
		HashMap<String, PlayerSeasonPO> seasonPlayers = allPlayerRecords.get(season);
		if (seasonPlayers == null) return result;
		Iterator<Entry<String, PlayerSeasonPO>> itr = seasonPlayers.entrySet().iterator();
		while(itr.hasNext()) {
			PlayerSeasonPO vo = itr.next().getValue();
			if (vo.teamAbbr.equals(abbr))
				result.add(vo.name);
		}
		return result;
	}

	/**
	 * @see dataservice.SeasonDataService#getAllPlayerRecentSeasonData()
	 */
	@Override
	public ArrayList<PlayerSeasonPO> getAllPlayerRecentSeasonData() {
		checkAndReadPlayerSeasonData(Constants.LATEST_SEASON);
		HashMap<String, PlayerSeasonPO> map = allPlayerRecords.get(Constants.LATEST_SEASON);
		if (map == null) {
			return new ArrayList<PlayerSeasonPO>();
		}else {
			return new ArrayList<PlayerSeasonPO>(map.values());
		}
	}
	
	/**
	 * @see dataservice.SeasonDataService#getAllTeamRecentSeasonData()
	 */
	@Override
	public ArrayList<TeamSeasonPO> getAllTeamRecentSeasonData() {
		return getAllTeamSeasonData(Constants.LATEST_SEASON);
	}
	
	public ArrayList<TeamSeasonPO> getAllTeamSeasonData(String season) {
		checkAndReadTeamSeasonData(season);
		HashMap<String, TeamSeasonPO> map = allTeamRecords.get(season);
		if (map == null) {
			return new ArrayList<TeamSeasonPO>();
		}else {
			return new ArrayList<TeamSeasonPO>(map.values());
		}
	}
	
	/** 返回最近赛季某球队的球员名单 */
	public ArrayList<String> getRecentPlayerNamesByTeamAbbr(String abbr) {
		return getPlayerNamesByTeamAbbr(abbr, Constants.LATEST_SEASON);
	}
	
	/** 返回与某缩写的球队在同一联盟内的球队的最近赛季数据 */
	public ArrayList<TeamSeasonPO> getTeamSeasonDataInSameLeague(String abbr, String season) {
		abbr = Utility.getOldAbbr(season, abbr);
		checkAndReadTeamSeasonData(season);
		ArrayList<TeamSeasonPO> result = new ArrayList<TeamSeasonPO>();
		ScreenDivision league = Constants.getAreaByAbbr(abbr);
		if (allTeamRecords.get(season) == null) return result;
		Iterator<TeamSeasonPO> itr = allTeamRecords.get(season).values().iterator();
		while(itr.hasNext()) {
			TeamSeasonPO vo = itr.next();
			if (Constants.getAreaByAbbr(vo.abbr) == league) {
				result.add(vo);
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see dataservice.SeasonDataService#setSeasonBufferCount(int)
	 */
	@Override
	public void setSeasonCacheSize(int size) {
		seasonCacheSize = size;
	}
	
	/** 检查最近访问的赛季队列，返回值为需抛弃的已有赛季， */
	private String checkAndAdjustQueue(ArrayList<String> queue, String season) {
		int index = queue.lastIndexOf(season);
		int size = queue.size();
		int i,k = 0;
		//该赛季已经在内存中，更新其队列位置，提高其被保留的优先级
		if (index >= 0) {
			String[]tmp = new String[size - 1];
			for (i = 0; i < size; i++) {
				if (i != index) {
					tmp[k] = queue.get(i);
					k++;
				}
			}
			queue.clear();
			for (i = 0; i < size - 1; i++) {
				queue.add(tmp[i]);
			}
			queue.add(season);
			return null;
		}
		//规定的缓冲区没满，该赛季不在内存中，加入队列即可
		else if (index < 0 && size < seasonCacheSize) {
			queue.add(season);
			return null;
		}
		//规定的缓冲区满了。而且该赛季不在内存中，淘汰最低优先级的赛季，将该赛季加入队列
		else {
			String seasonToAbandon = queue.remove(0);
			queue.add(season);
			return seasonToAbandon;
		}
	}
	
}
