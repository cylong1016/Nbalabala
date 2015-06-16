package data.playerdata;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import utility.Constants;
import utility.Utility;
import vo.PlayerProfileVO;
import data.seasondata.SeasonData;
import dataservice.PlayerDataService;

/**
 * @see dataservice.playerdataservice.PlayerDataService
 * @author cylong
 * @version 2015年3月13日 下午7:01:57
 */
public class PlayerData implements PlayerDataService{

	/** 全部球员基本信息 */
	private static HashMap<String, PlayerProfileVO> players = new HashMap<String, PlayerProfileVO>();

	public PlayerData() {
		if (players.size() == 0) loadPlayers();
	}
	
	/**
	 * 加载全部球员信息
	 * 这个全部加载肯能效率不太好，暂时先不要用这个方法
	 * @author cylong
	 * @version 2015年3月13日 下午7:33:17
	 */
	private static void loadPlayers() {
		File dir = new File(Constants.dataSourcePath + "players/info/");
		SeasonData seasonData = new SeasonData();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File[] files = dir.listFiles();
		BufferedReader br = null;
		
		String season = Utility.getDefaultSeason();
		
		try {
			for(File file : files) {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
				ArrayList<String> playerInfo = new ArrayList<String>(9);
				String line = null;
				int lineNum = 0;
				
				while((line = br.readLine()) != null) { // 一次读取每行的球员信息
					lineNum++;
					if (lineNum % 2 != 0) {
						continue; // 文件中只有双数行有数据
					}
					line = line.replace("║", "");
					playerInfo.add(line.split("│")[1].trim());
				}
				
				String name = playerInfo.get(0);
				Image portrait = PlayerImageCache.getPortraitByName(name);
				PlayerProfileVO player = new PlayerProfileVO(portrait, playerInfo.get(0), 
						seasonData.getTeamAbbrByPlayer(name, season), 
						playerInfo.get(1), playerInfo.get(2), playerInfo.get(3), 
						playerInfo.get(4), playerInfo.get(5), playerInfo.get(6), 
						playerInfo.get(7), playerInfo.get(8));
				players.put(name, player);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//有些球员参加过比赛却没有球员资料，也应该将其加入
		ArrayList<String> names = seasonData.getPlayerNames();
		for (String name : names){
			if (!players.containsKey(name)) {
				players.put(name, new PlayerProfileVO(PlayerImageCache.getNullPortrait(), name));
			}
		}
	}
	
	/**
	 * @see dataservice.PlayerDataService#getPlayerProfileByInitial(char)
	 */
	@Override
	public ArrayList<PlayerProfileVO> getPlayerProfileByInitial(char initial) {
		ArrayList<PlayerProfileVO> result = new ArrayList<PlayerProfileVO>();
		Iterator<Entry<String, PlayerProfileVO>> itr = players.entrySet().iterator();
		while (itr.hasNext()) {
			PlayerProfileVO po = itr.next().getValue();
			if (po.getName().charAt(0) == initial) result.add(po);
		}
		Comparator<PlayerProfileVO> comparator = new Comparator<PlayerProfileVO>() {
			public int compare(PlayerProfileVO p1, PlayerProfileVO p2) {
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
	public PlayerProfileVO getPlayerProfileByName(String name) {
		if (players.get(name) != null)
			return players.get(name);
		else 
			return new PlayerProfileVO(PlayerImageCache.getNullPortrait(), name);
	}
	
	/**
	 * @see dataservice.PlayerDataService#getPlayerProfileByNames(java.util.ArrayList)
	 */
	public ArrayList<PlayerProfileVO> getPlayerProfileByNames(ArrayList<String> names) {
		ArrayList<PlayerProfileVO> result = new ArrayList<PlayerProfileVO>();
		for (String name : names) {
			result.add(getPlayerProfileByName(name));
		}
		return result;
	}
	
	/**
	 * @see dataservice.PlayerDataService#searchPlayers(java.lang.String)
	 */
	@Override
	public ArrayList<PlayerProfileVO> searchPlayers(String keyword) {
		ArrayList<PlayerProfileVO> result = new ArrayList<PlayerProfileVO>();
		Iterator<Entry<String, PlayerProfileVO>> itr = players.entrySet().iterator();
		keyword = keyword.toLowerCase();
		while (itr.hasNext()) {
			PlayerProfileVO po = itr.next().getValue();
			if (po.getName().toLowerCase().contains(keyword)) result.add(po);
		}
		Comparator<PlayerProfileVO> comparator = new Comparator<PlayerProfileVO>() {
			public int compare(PlayerProfileVO p1, PlayerProfileVO p2) {
				return p1.getName().compareTo(p2.getName());
			}
		};
		Collections.sort(result, comparator);
		return result;
	}
	
	public static void clear() {
		players.clear();
	}
	
	public static void reloadPlayers() {
		players.clear();
		loadPlayers();
	}

}
