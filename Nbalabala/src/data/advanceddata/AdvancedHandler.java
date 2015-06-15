/**
 * 
 */
package data.advanceddata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import po.AdvancedDataPO;
import po.ClutchPO;
import utility.Constants;
import utility.Utility;
import data.Database;
import data.seasondata.SeasonData;

/**
 *
 * @author Issac Ding
 * @since 2015年6月15日 下午9:41:46
 * @version 1.0
 */
public class AdvancedHandler {
	
	static Connection conn = Database.conn;
	
	private static ArrayList<String> active = new ArrayList<String>();
	
	private static ArrayList<AdvancedDataPO> advanced = new ArrayList<AdvancedDataPO>();
	
	private static ArrayList<ClutchPO> clutch = new ArrayList<ClutchPO>();
	
	public static void main(String [] args) {
		SeasonData seasonData = new SeasonData();
		for (String abbr : Constants.TEAM_ABBR) {
			active.addAll(seasonData.getPlayerNamesByTeamAbbr(abbr, Constants.LATEST_SEASON_REGULAR));
		}
		readAdvanced();
		readClutch();
		
		for (String player : active) {
			
			boolean hasSameAdvancePlayer = false;
			for (AdvancedDataPO advancedDataPO : advanced) {
				if (sameAs(player, advancedDataPO.name)) {
					hasSameAdvancePlayer = true;
					insertAdvanced(player, advancedDataPO);
					break;
				}
			}
			if (!hasSameAdvancePlayer) {
				for (AdvancedDataPO advancedDataPO : advanced) {
					if (match(player, advancedDataPO.name)) {
						insertAdvanced(player, advancedDataPO);
						break;
					}
				}
			}
			
			boolean hasSameClutchPlayer = false;
			for (ClutchPO clutchPO : clutch) {
				if (sameAs(player, clutchPO.name)) {
					hasSameClutchPlayer = true;
					insertClutch(player, clutchPO);
					break;
				}
			}
			if (!hasSameClutchPlayer) {
				for (ClutchPO clutchPO : clutch) {
					if (match(player, clutchPO.name)) {
						insertClutch(player, clutchPO);
						break;
					}
				}
			}
		}
	}
	
	public static boolean sameAs(String my, String his) {
		return (Utility.trimName(my)).equals(his);
	}
	
	public static boolean match(String my, String his) {
		String myLastName = my.substring(my.lastIndexOf(' '), my.length() );
		String hisLastName = his.substring(his.lastIndexOf(' '), his.length() );
		if (!myLastName.equals(hisLastName)) return false;
		return my.charAt(0) == his.charAt(0);
	}
	
	public static void insertAdvanced(String id, AdvancedDataPO po) {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("insert into player_advance_t values(?,?,?,?,?,?,?,?)");
			ps.setString(1, id);
			ps.setString(2, Constants.LATEST_SEASON_REGULAR);
			ps.setInt(3, po.gp);
			ps.setFloat(4, (float)po.mpg);
			ps.setFloat(5, (float)po.orpm);
			ps.setFloat(6, (float)po.drpm);
			ps.setFloat(7, (float)po.rpm);
			ps.setFloat(8, (float)po.war);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void insertClutch(String id, ClutchPO po) {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("insert into player_clutch_t values(?,?,?,?)");
			ps.setString(1, id);
			ps.setString(2, Constants.LATEST_SEASON_REGULAR);
			ps.setFloat(3, (float)po.clutchTime);
			ps.setFloat(4, (float)po.clutchScore);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void readClutch() {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("select * from player_clutch");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ClutchPO po = new ClutchPO(rs.getString(1),rs.getString(2));
				po.clutchTime = rs.getFloat(3);
				po.clutchScore = rs.getFloat(4);
				clutch.add(po);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void readAdvanced() {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("select * from player_advance");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AdvancedDataPO po = new AdvancedDataPO(rs.getString(1), rs.getString(2));
				po.gp = rs.getInt(3);
				po.mpg = rs.getFloat(4);
				po.orpm = rs.getFloat(5);
				po.drpm = rs.getFloat(6);
				po.rpm = rs.getFloat(7);
				po.war = rs.getFloat(8);
				advanced.add(po);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
