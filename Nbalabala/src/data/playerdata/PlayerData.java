package data.playerdata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import po.PlayerProfilePO;
import data.Database;
import dataservice.PlayerDataService;

/**
 * @see dataservice.playerdataservice.PlayerDataService
 * @author cylong
 * @version 2015年3月13日 下午7:01:57
 */
public class PlayerData implements PlayerDataService{

	/** 全部球员基本信息 */
	private static HashMap<String, PlayerProfilePO> players = new HashMap<String, PlayerProfilePO>();

	public PlayerData() {
		if (players.size() == 0) loadPlayers();
	}
	
	/**
	 * 加载全部球员信息
	 * @author cylong
	 * @version 2015年3月13日 下午7:33:17
	 */
	private static void loadPlayers() {
		String sql = "select * from player_profile";
		try {
			ResultSet rs = Database.conn.createStatement().executeQuery(sql);
			while(rs.next()) {
				PlayerProfilePO po = new PlayerProfilePO(rs.getString(1));
				po.fromYear = rs.getInt(2);
				po.toYear = rs.getInt(3);
				po.position = rs.getString(4);
				po.heightFoot = rs.getInt(5);
				po.heightInch = rs.getInt(6);
				po.weight = rs.getInt(7);
				po.birthDate = rs.getDate(8);
				po.school = rs.getString(9);
				players.put(po.name, po);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @see dataservice.PlayerDataService#getPlayerProfileByInitial(char)
	 */
	@Override
	public ArrayList<PlayerProfilePO> getPlayerProfileByInitial(char initial) {
		ArrayList<PlayerProfilePO> result = new ArrayList<PlayerProfilePO>();
		Iterator<Entry<String, PlayerProfilePO>> itr = players.entrySet().iterator();
		while (itr.hasNext()) {
			PlayerProfilePO po = itr.next().getValue();
			if (po.name.charAt(po.name.lastIndexOf(' ') + 1) == initial) {
				result.add(po);
			}
		}
		Comparator<PlayerProfilePO> comparator = new Comparator<PlayerProfilePO>() {
			public int compare(PlayerProfilePO p1, PlayerProfilePO p2) {
				return p1.getName().compareTo(p2.getName());
			}
		};
		Collections.sort(result, comparator);
		return result;
	}

	/**
	 * @see dataservice.PlayerDataService#getPlayerProfileByName(java.lang.String)
	 */
	@Override
	public PlayerProfilePO getPlayerProfileByName(String name) {
		PlayerProfilePO po = players.get(name);
		if (po == null) {
			return new PlayerProfilePO(name);
		}else {
			return po;
		}
	}
	
	/**
	 * @see dataservice.PlayerDataService#getPlayerProfileByNames(java.util.ArrayList)
	 */
	public ArrayList<PlayerProfilePO> getPlayerProfileByNames(ArrayList<String> names) {
		ArrayList<PlayerProfilePO> result = new ArrayList<PlayerProfilePO>();
		for (String name : names) {
			result.add(getPlayerProfileByName(name));
		}
		return result;
	}
	
	/**
	 * @see dataservice.PlayerDataService#searchPlayers(java.lang.String)
	 */
	@Override
	public ArrayList<PlayerProfilePO> searchPlayers(String keyword) {
		ArrayList<PlayerProfilePO> result = new ArrayList<PlayerProfilePO>();
		Iterator<Entry<String, PlayerProfilePO>> itr = players.entrySet().iterator();
		keyword = keyword.toLowerCase();
		while (itr.hasNext()) {
			PlayerProfilePO po = itr.next().getValue();
			if (po.getName().toLowerCase().contains(keyword)) result.add(po);
		}
		Comparator<PlayerProfilePO> comparator = new Comparator<PlayerProfilePO>() {
			public int compare(PlayerProfilePO p1, PlayerProfilePO p2) {
				return p1.getName().compareTo(p2.getName());
			}
		};
		Collections.sort(result, comparator);
		return result;
	}
}
