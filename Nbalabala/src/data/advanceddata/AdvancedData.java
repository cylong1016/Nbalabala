/**
 * 
 */
package data.advanceddata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import po.AdvancedDataPO;
import po.ClutchPO;
import data.Database;
import dataservice.AdvancedDataService;

/**
 * @author Issac Ding
 * @since 2015年6月15日 下午9:19:00
 * @version 1.0
 */
public class AdvancedData implements AdvancedDataService {

	private Connection conn = Database.conn;

	public AdvancedDataPO getAdvancedData(String playerName, String season) {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("select * from player_advance where name=? and season=?");
			ps.setString(1, playerName);
			ps.setString(2, season);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				AdvancedDataPO po = new AdvancedDataPO(rs.getString(1), rs.getString(2));
				po.gp = rs.getInt(3);
				po.mpg = rs.getFloat(4);
				po.orpm = rs.getFloat(5);
				po.drpm = rs.getFloat(6);
				po.rpm = rs.getFloat(7);
				po.war = rs.getFloat(8);
				return po;
			} else {
				return new AdvancedDataPO(playerName, season);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new AdvancedDataPO(playerName, season);
		}
	}

	public ClutchPO getClutchData(String playerName, String season) {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("select * from player_clutch where name=? and season=?");
			ps.setString(1, playerName);
			ps.setString(2, season);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ClutchPO po = new ClutchPO(rs.getString(1), rs.getString(2));
				po.clutchTime = rs.getFloat(3);
				po.clutchScore = rs.getFloat(4);
				return po;
			} else {
				return new ClutchPO(playerName, season);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ClutchPO(playerName, season);
		}
	}
}
