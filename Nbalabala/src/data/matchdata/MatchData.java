package data.matchdata;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import po.ExtraTimePO;
import po.MatchDetailPO;
import po.MatchPlayerPO;
import po.MatchProfilePO;
import data.Database;
import dataservice.MatchDataService;

/**
 * 读取比赛信息，检索并返回符合条件的比赛信息的类
 * @author Issac Ding
 * @version 2015年3月18日  上午10:34:35
 */
public class MatchData implements MatchDataService {
	
	private static Connection conn = Database.conn;


	
	/** 通过比赛ID得到所有球员的比赛表现记录 */
	private ArrayList<MatchPlayerPO> getMatchPlayersByMatchID(int matchID) {
		String sql = "select * from match_player where match_id = '" + matchID + "'";
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			return getMatchPlayers(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/** 通过比赛ID得到该场比赛的加时赛记录 */
	private ArrayList<ExtraTimePO> getExtraTimesByMatchID(int matchID) {
		String sql = "select * from extra_time where match_id = '" + matchID + "'";
		ArrayList<ExtraTimePO> result = new ArrayList<ExtraTimePO>();
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while(rs.next()) {
				ExtraTimePO po = new ExtraTimePO();
				po.matchID = matchID;
				po.extraOrder = rs.getInt(2);	//TODO 不知道这里顺序是怎样的
				po.roadScore = rs.getInt(3);
				po.homeScore = rs.getInt(4);
				result.add(po);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 从match_profile表的ResultSet中得到MatchProfilePO */
	private MatchProfilePO getMatchProfile(ResultSet rs) {
		MatchProfilePO po = new MatchProfilePO();
		try {
			po.matchID = rs.getInt(1);
			po.season = rs.getString(2);
			po.date = rs.getDate(3);
			po.roadAbbr = rs.getString(4);
			po.homeAbbr = rs.getString(5);
			po.section = rs.getInt(6);
			po.roadTotalScore = rs.getInt(7);
			po.homeTotalScore = rs.getInt(8);
			po.roadSection1 = rs.getInt(9);
			po.roadSection2 = rs.getInt(10);
			po.roadSection3 = rs.getInt(11);
			po.roadSection4 = rs.getInt(12);
			po.homeSection1 = rs.getInt(13);
			po.homeSection2 = rs.getInt(14);
			po.homeSection3 = rs.getInt(15);
			po.homeSection4 = rs.getInt(16);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return po;
	}
	
	/** 通过match_player表的ResultSet得到比赛球员记录的列表 */
	private ArrayList<MatchPlayerPO> getMatchPlayers(ResultSet rs) {
		ArrayList<MatchPlayerPO> result = new ArrayList<MatchPlayerPO>();
		try {
			while(rs.next()) {
				MatchPlayerPO po = new MatchPlayerPO();
				po.matchID = rs.getInt(1);
				po.playerName = rs.getString(2);
				po.homeOrRoad = rs.getString(3).charAt(0);
				po.teamAbbr = rs.getString(4);
				po.isStarter = (rs.getInt(5) == 1);
				po.timePlayed = rs.getString(6);
				po.fieldMade = rs.getInt(7);
				po.fieldAttempt = rs.getInt(8);
				po.fieldPercent = rs.getFloat(9);
				po.threepointMade = rs.getInt(10);
				po.threepointAttempt = rs.getInt(11);
				po.threepointPercent = rs.getFloat(12);
				po.freethrowMade = rs.getInt(13);
				po.freethrowAttempt = rs.getInt(14);
				po.freethrowPercent = rs.getFloat(15);
				po.offensiveRebound = rs.getInt(16);
				po.defensiveRebound = rs.getInt(17);
				po.totalRebound = rs.getInt(18);
				po.assist = rs.getInt(19);
				po.steal = rs.getInt(20);
				po.block  = rs.getInt(21);
				po.turnover = rs.getInt(22);
				po.foul = rs.getInt(23);
				po.score = rs.getInt(24);
				po.plusMinus = rs.getInt(25);
				result.add(po);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/** 通过match_profile表的ResultSet得到MatchDetailPO */
	private MatchDetailPO getMatchDetail(ResultSet rs) {
		MatchDetailPO detailPO = new MatchDetailPO();
		MatchProfilePO profilePO = getMatchProfile(rs);
		detailPO.matchProfile = profilePO;
		detailPO.matchPlayers = getMatchPlayersByMatchID(profilePO.matchID);
		// 如果有加时赛，寻找加时赛记录
		if (profilePO.section > 4) {
			detailPO.extraTimes = getExtraTimesByMatchID(profilePO.matchID);
		}
		return detailPO;
	}

	/* (non-Javadoc)
	 * @see dataservice.MatchDataService#getMatchDetailByDate(java.sql.Date)
	 */
	@Override
	public ArrayList<MatchDetailPO> getMatchDetailByDate(Date date) {
		ArrayList<MatchDetailPO> result = new ArrayList<MatchDetailPO>();
		try {
			PreparedStatement ps = conn.prepareStatement
					("select * from match_profile where date=?");
			ps.setDate(1, date);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				result.add(getMatchDetail(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see dataservice.MatchDataService#getMatchDetailByTeam(java.lang.String)
	 */
	@Override
	public ArrayList<MatchDetailPO> getMatchDetailByTeam(String team) {
		ArrayList<MatchDetailPO> result = new ArrayList<MatchDetailPO>();
		String [] teams = team.split("-");
		String roadAbbr = teams[0];
		String homeAbbr = teams[1];
		try {
			PreparedStatement ps = conn.prepareStatement
					("select * from match_profile where road_abbr=? and home_abbr=?");
			ps.setString(1, roadAbbr);
			ps.setString(2, homeAbbr);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				result.add(getMatchDetail(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see dataservice.MatchDataService#getMatchProfileByTeam(java.lang.String)
	 */
	@Override
	public ArrayList<MatchProfilePO> getMatchProfileByTeamAndSeason(String abbr,String season) {
		ArrayList<MatchProfilePO> result = new ArrayList<MatchProfilePO>();
		try {
			PreparedStatement ps = conn.prepareStatement
					("select * from match_profile where (road_abbr=? or home_abbr=?) and (season=?)");
			ps.setString(1, abbr);
			ps.setString(2, abbr);
			ps.setString(3, season);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				result.add(getMatchProfile(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see dataservice.MatchDataService#getMatchDetailByMatchID(java.lang.String)
	 */
	@Override
	public MatchDetailPO getMatchDetailByMatchID(int matchID) {
		String sql = "select * from match_profile where match_id='" + matchID + "'";
		ResultSet rs;
		try {
			rs = conn.createStatement().executeQuery(sql);
			if (rs.next()) {
				return getMatchDetail(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.MatchDataService#getMatchRecordByPlayerName(java.lang.String, java.lang.String)
	 */
	@Override
	public ArrayList<MatchPlayerPO> getMatchRecordByPlayerName(
			String playerName, String season) {
		ArrayList<MatchPlayerPO> result = new ArrayList<MatchPlayerPO>();
		// 先找到这个赛季的比赛ID区间
		String idSql = "select match_id from match_profile where season='" + season + "' order by match_id asc";
		int startID = 0;
		int endID = 0;
		HashMap<Integer, String> oppoAbbrs = new HashMap<Integer, String>();	// 记录对手的缩写
		HashMap<Integer, Date> dates = new HashMap<Integer, Date>();	// 记录日期
		try {
			ResultSet idResultSet = conn.createStatement().executeQuery(idSql);
			if (idResultSet.next()) {
				startID = idResultSet.getInt(1);	// 本赛季的第一场比赛的ID
				oppoAbbrs.put(startID, idResultSet.getString(4));
				dates.put(startID, idResultSet.getDate(3));
			} else {
				return result;						// 本赛季无比赛，返回空表
			}
			while(idResultSet.next()) {
				endID = idResultSet.getInt(1);
				oppoAbbrs.put(endID, idResultSet.getString(4));
				dates.put(endID, idResultSet.getDate(3));
			}										// 循环结束后找到了最后一场比赛的ID
			if (endID == 0) endID = startID;		// 极端情况：本赛季只有一场比赛的记录
			PreparedStatement ps = conn.prepareStatement("select * from match_player "
					+ "where season>=? and season<=? and player_name=?");
			ps.setInt(1, startID);
			ps.setInt(2, endID);
			ps.setString(3, playerName);
			ResultSet playeResultSet = ps.executeQuery();
			result = getMatchPlayers(playeResultSet);
			int size = result.size();
			for (int i = 0; i < size; i++) {
				MatchPlayerPO po = result.get(i);
				po.date = dates.get(po.matchID);
				po.oppoAbbr = oppoAbbrs.get(po.matchID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see dataservice.MatchDataService#getMatchDetailByDates(java.sql.Date)
	 */
	@Override
	public ArrayList<MatchDetailPO> getMatchDetailByDates(Date start, Date end) {
		ArrayList<MatchDetailPO> result = new ArrayList<MatchDetailPO>();
		try {
			PreparedStatement ps = conn.prepareStatement
					("select * from match_profile where date>=? and date<=?");
			ps.setDate(1, start);
			ps.setDate(2, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				result.add(getMatchDetail(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see dataservice.MatchDataService#getMatchDetailBySeason(java.lang.String)
	 */
	@Override
	public ArrayList<MatchDetailPO> getMatchDetailBySeason(String season) {
		ArrayList<MatchDetailPO> result = new ArrayList<MatchDetailPO>();
		try {
			PreparedStatement ps = conn.prepareStatement
					("select * from match_profile where season=?");
			ps.setString(1, season);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				result.add(getMatchDetail(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
//	
//	/**
//	 * @see dataservice.MatchDataService#getMatchDetailByDate(java.lang.String, java.lang.String)
//	 */
//	@Override
//	public ArrayList<MatchDetailPO> getMatchDetailByDate(Date date) {
//		
//	}
//
//	/**
//	 * @see dataservice.MatchDataService#getMatchProfileByTeam(java.lang.String)
//	 */
//	@Override
//	public ArrayList<MatchDetailVO> getMatchDetailByTeam(String team) {
//		
//		File[] files = Utility.getSortedMatchFiles();
//		
//		ArrayList<MatchDetailVO> result = new ArrayList<MatchDetailVO>();
//
////		try {
//			for(File file : files) {
//				if ( ! file.getName().contains(team)) continue;
//				result.add(getMatchDetailByFile(file));
//				
////				String season = file.getName().split("_")[0];
////				
////				BufferedReader br = new BufferedReader(new FileReader(file));
////				String [] profile = br.readLine().split(";");
////				MatchProfileVO matchProfileVO = new MatchProfileVO(season, profile[0], profile[1],
////						profile[2], br.readLine());
////				result.add(matchProfileVO);
////				br.close();
//			}
////		} catch (FileNotFoundException e) {
////			e.printStackTrace();
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
//		return result;
//	}
//	
//	/**
//	 * @see dataservice.MatchDataService#getMatchDetailByFileName(java.lang.String)
//	 */
//	@Override
//	public MatchDetailVO getMatchDetailByFileName(String fileName) {
//		File file = new File(Constants.dataSourcePath + "matches//" + fileName);
//		return getMatchDetailByFile(file);
//	}
//	
//	private MatchDetailVO getMatchDetailByFile(File file) {
//		String season = file.getName().split("_")[0];
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(file));
//			
//			String [] profile = br.readLine().split(";");
//			MatchProfileVO matchProfileVO = new MatchProfileVO(season, profile[0], 
//					profile[1], profile[2], br.readLine());
//			
//			ArrayList<MatchPlayerVO> homePlayers = new ArrayList<MatchPlayerVO>();
//			br.readLine();
//			String s = null;
//			while (true) {
//				s = br.readLine();
//				if (s.length() < 5) break;
//				MatchPlayerVO homeVo = new MatchPlayerVO(s, file, TeamState.HOME);
//				homePlayers.add(homeVo);
//			}
//			
//			ArrayList<MatchPlayerVO> roadPlayers = new ArrayList<MatchPlayerVO>();
//			while ((s = br.readLine()) != null) {
//				MatchPlayerVO roadVo = new MatchPlayerVO(s, file, TeamState.ROAD);
//				roadPlayers.add(roadVo);
//			}
//			
//			br.close();
//			
//			String[] teams = profile[1].split("-");
//			Image homeLogo = SVGHandler.getTeamLogo(teams[0]);
//			Image roadLogo = SVGHandler.getTeamLogo(teams[1]);
//			
//			return new MatchDetailVO(matchProfileVO, homePlayers, roadPlayers, homeLogo, roadLogo);
//				
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	/**
//	 * @see dataservice.MatchDataService#getMatchRecordByPlayerName(java.lang.String)
//	 */
//	@Override
//	public ArrayList<PlayerMatchPerformanceVO> getMatchRecordByPlayerName(String playerName,String seasonStr) {
//		File [] files = Utility.getSortedMatchFiles();
//		
//		ArrayList<PlayerMatchPerformanceVO> result = new ArrayList<PlayerMatchPerformanceVO>();
//		
//		FILELOOP: for (File file : files) {
//			try {
//				String season = file.getName().split("_")[0];
//				if (!season.equals(seasonStr))
//					continue;
//					
//				BufferedReader br = new BufferedReader(new FileReader(file));
//				
//				String[] profile = br.readLine().split(";");
//				String date = profile[0];
//				String twoTeams = profile[1];
//				
//				br.readLine();
//				br.readLine();
//				String line;
//				while (true) {
//					line = br.readLine();
//					if (line.length() < 5) break;
//					if (line.contains(playerName)) {
//						MatchPlayerVO matchPlayerVO = new MatchPlayerVO(line, file, TeamState.HOME); 
//						result.add(new PlayerMatchPerformanceVO(matchPlayerVO, season, date, twoTeams));
//						br.close();
//						continue FILELOOP;
//					}
//				}
//				while ((line = br.readLine()) != null) {
//					if (line.contains(playerName)) {
//						MatchPlayerVO matchPlayerVO = new MatchPlayerVO(line, file, TeamState.ROAD); 
//						result.add(new PlayerMatchPerformanceVO(matchPlayerVO, season, date, twoTeams));
//						break;
//					}
//				}
//				br.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see dataservice.MatchDataService#getMatchProfileByTeam(java.lang.String)
//	 */
//	@Override	//TODO MatchProfileVO的内容要改
//	public ArrayList<MatchProfileVO> getMatchProfileByTeam(String abbr) {
//		
//		File[] files = Utility.getSortedMatchFiles();
//		
//		ArrayList<MatchProfileVO> result = new ArrayList<MatchProfileVO>();
//
//		try {
//			for(File file : files) {
//				if ( ! file.getName().contains(abbr)) continue;
//				
//				String season = file.getName().split("_")[0];
//				
//				BufferedReader br = new BufferedReader(new FileReader(file));
//				String [] profile = br.readLine().split(";");
//				MatchProfileVO matchProfileVO = new MatchProfileVO(season, profile[0], profile[1],
//						profile[2], br.readLine());
//				result.add(matchProfileVO);
//				br.close();
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
}
