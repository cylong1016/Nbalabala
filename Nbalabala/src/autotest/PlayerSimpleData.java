package autotest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import autotest.playertest.PlayerSimpleVO;
import utility.Constants;

/**
 * 供测试用的球员数据模块，不涉及球员头像和全身像的读取
 * @author Issac Ding
 * @version 2015年4月6日  下午11:02:38
 */
public class PlayerSimpleData {
	/** 全部球员基本信息 */
	private static HashMap<String, PlayerSimpleVO> players = new HashMap<String, PlayerSimpleVO>();

	/** 存储球员信息的文件夹 */
	private static final String INFO_Path = Constants.dataSourcePath + "players/info/";
	
	public PlayerSimpleData() {
		if (players.size() == 0) loadPlayers();
	}
	
	private void loadPlayers() {
		File dir = new File(INFO_Path);
		File[] files = dir.listFiles();
		BufferedReader br = null;
		
		try {
			for(File file : files) {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
				br.readLine();
				String name = br.readLine().split("║|│")[1].trim();
				br.readLine();
				br.readLine();
				br.readLine();
				String position = br.readLine().split("║|│")[1].trim();
				br.readLine();
				br.readLine();
				br.readLine();
				br.readLine();
				br.readLine();
				br.readLine();
				br.readLine();
				String age = br.readLine().split("║|│")[1].trim();
				players.put(name, new PlayerSimpleVO(name, position, Integer.parseInt(age)));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
