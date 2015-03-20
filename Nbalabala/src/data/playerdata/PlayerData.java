package data.playerdata;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import vo.PlayerProfileVO;
import data.seasondata.SeasonData;
import dataservice.PlayerDataService;
import dataservice.SeasonDataService;

/**
 * @see dataservice.playerdataservice.PlayerDataService
 * @author cylong
 * @version 2015年3月13日 下午7:01:57
 */
public class PlayerData implements PlayerDataService{

	/** 全部球员基本信息 */
	private static HashMap<String, PlayerProfileVO> players = new HashMap<String, PlayerProfileVO>();

	/** 存储球员信息的文件夹 */
	private static final String INFO_Path = "NBAdata/players/info/";
	
	/** 存储球员头像的文件夹 */
	private static final String PORTRAIT_PATH = "NBAdata/players/portrait/";
	
	/** 存储全身像的文件夹 */
	private static final String ACTION_Path = "NBAdata/players/action/";
	
	public PlayerData() {
		if (players.size() == 0) loadPlayers();
	}
	
	/**
	 * 加载全部球员信息
	 * 这个全部加载肯能效率不太好，暂时先不要用这个方法
	 * @author cylong
	 * @version 2015年3月13日 下午7:33:17
	 */
	private void loadPlayers() {
		File dir = new File(INFO_Path);
		File[] files = dir.listFiles();
		BufferedReader br = null;
		
		SeasonDataService seasonData = new SeasonData();
		
		try {
			for(File file : files) {
				
				String name = file.getName();
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
				
				Image portrait = getPotraitImageByName(name);
				PlayerProfileVO player = new PlayerProfileVO(portrait, playerInfo.get(0), 
						seasonData.getTeamAbbrByPlayer(name), playerInfo.get(1), playerInfo.get(2), 
						playerInfo.get(3), playerInfo.get(4), playerInfo.get(5), playerInfo.get(6), 
						playerInfo.get(7), playerInfo.get(8));
				players.put(name, player);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see dataservice.PlayerDataService#getActionImageByName(java.lang.String)
	 */
	@Override
	public Image getActionImageByName(String name) {
		try {
			return  ImageIO.read(new File(ACTION_Path + name + ".png"));
		} catch (IOException e) {
			try {
				return ImageIO.read(new File("images/nullAction.png"));
			} catch (IOException e1) {
				return null;
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
		return players.get(name);
	}
	
	/**
	 * @see dataservice.PlayerDataService#getPlayerProfileByNames(java.util.ArrayList)
	 */
	public ArrayList<PlayerProfileVO> getPlayerProfileByNames(ArrayList<String> names) {
		ArrayList<PlayerProfileVO> result = new ArrayList<PlayerProfileVO>();
		for (String name : names) {
			result.add(players.get(name));
		}
		return result;
	}
	
	private Image getPotraitImageByName(String name) {
		try {
			return  ImageIO.read(new File(PORTRAIT_PATH + name + ".png"));
		} catch (IOException e) {
			try {
				return ImageIO.read(new File("images/nullPortrait.png"));
			} catch (IOException e1) {
				return null;
			}
		}
	}
	
}
