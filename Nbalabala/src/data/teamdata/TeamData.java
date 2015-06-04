package data.teamdata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import po.TeamProfilePO;
import data.Database;
import dataservice.TeamDataService;

/**
 *	
 * @author cylong
 * @version 2015年3月13日 下午8:36:13
 */
public class TeamData implements TeamDataService{

	/** 全部球队信息 */
	private static HashMap<String, TeamProfilePO> teams = new HashMap<String, TeamProfilePO>();

	public TeamData() {
		if (teams.size() == 0) {
			loadTeams();
		}
	}
	
	/**
	 * 加载全部球队信息
	 * @author cylong
	 * @version 2015年3月13日 下午9:05:33
	 */
	private void loadTeams() {
		try {
			Statement statement = Database.conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from team_profile");
			while (rs.next()) {
				TeamProfilePO po = new TeamProfilePO();
				po.abbr = rs.getString(1);
				po.name = rs.getString(2);
				po.location = rs.getString(3);
				po.league = rs.getString(4).charAt(0);
				po.division = rs.getString(5);
				po.home = rs.getString(6);
				po.since = rs.getInt(7);
				teams.put(po.abbr, po);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see dataservice.TeamDataService#getTeamProfileByAbbr(java.lang.String)
	 */
	@Override
	public TeamProfilePO getTeamProfileByAbbr(String abbr) {
		return teams.get(abbr);
	}
}
