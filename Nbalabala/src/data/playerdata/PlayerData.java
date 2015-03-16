package data.playerdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import po.PlayerPO;
import dataservice.playerdataservice.PlayerDataService;

/**
 * @see dataservice.playerdataservice.PlayerDataService
 * @author cylong
 * @version 2015年3月13日 下午7:01:57
 */
public class PlayerData implements PlayerDataService {

	/** 全部球员基本信息（name， PlayerPO） */
	private HashMap<String, PlayerPO> players = new HashMap<String, PlayerPO>();

	/** 存储球员信息的文件夹 */
	private String path = "NBAdata/players/info/";

	public PlayerData() {
		loadPlayers();
	}

	/**
	 * 加载全部球员信息
	 * 这个全部加载肯能效率不太好，暂时先不要用这个方法
	 * @author cylong
	 * @version 2015年3月13日 下午7:33:17
	 */
	public void loadPlayers() {
		File dir = new File(path);
		File[] files = dir.listFiles();
		BufferedReader br = null;

		try {
			for(File file : files) {
				br = new BufferedReader(new FileReader(file));
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
				PlayerPO player = new PlayerPO(playerInfo);
				players.put(player.getName(), player);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see dataservice.playerdataservice.PlayerDataService#findPlayer(java.lang.String)
	 */
	@SuppressWarnings("resource")
	@Override
	public PlayerPO findPlayer(String name) {
		PlayerPO po = players.get(name);
		if(po != null) { // 如果以前已经加载进内存就直接读取
			return po;
		}
		
		File file = new File(path + name);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
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
			PlayerPO player = new PlayerPO(playerInfo);
			players.put(player.getName(), player);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null; // 球员不存在
		} catch (IOException e) {
			e.printStackTrace();
		}
		return players.get(name);
	}

}
