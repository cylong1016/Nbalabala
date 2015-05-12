
package autotest;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import autotest.playertest.PlayerSimpleSeasonVO;
import autotest.playertest.PlayerSimpleVO;
import autotest.teamtest.TeamSimpleSeasonVO;

/**
 * @author Issac Ding
 * @version 2015年3月14日  下午4:04:31
 */

public class SeasonSimpleData{
	
	public static boolean isReading = false;
	
	public SeasonSimpleData() {
		if (playerRecords.size() == 0 || teamRecords.size() == 0) loadMatches();
//		System.out.println(playerRecords.get("DeMarcus Cousins").doubleDoubleAvg);
//		System.out.println(playerRecords.get("DeMarcus Cousins").doubleDoubleCount);
//		System.out.println(playerRecords.get("Andre Drummond").doubleDoubleAvg);
	}
	
	/** 存储所有球员的所有赛季数据记录 */
	public static HashMap<String, PlayerSimpleSeasonVO> playerRecords =
			new HashMap<String,PlayerSimpleSeasonVO>();
	
	/** 存储所有球队的所有赛季数据记录 */
	private static HashMap<String, TeamSimpleSeasonVO> teamRecords = 
			new HashMap<String, TeamSimpleSeasonVO>();
	
	public static void loadMatches() {
		
		isReading = true;
		
		File[] files = SimpleUtility.getSortedMatchFiles();
		
		SimpleUtility.fileCount += files.length;
		
		if (PlayerSimpleData.players==null) {
			PlayerSimpleData.loadPlayers();
		}
		
		SimpleMatchesAccumulator accumulator = new SimpleMatchesAccumulator(playerRecords, teamRecords);
		accumulator.accumulate(files, false);	//第一次读取，不需要记录哪些球员和球队发生了更新
		
		Iterator<Entry<String, PlayerSimpleSeasonVO>> playerItr = playerRecords.entrySet().iterator();
		while(playerItr.hasNext()) {
			PlayerSimpleSeasonVO vo = playerItr.next().getValue();
			vo.update();
			
			PlayerSimpleVO playerVO = PlayerSimpleData.players.get(vo.name);
			
			if (playerVO != null) {
				vo.age = playerVO.age;
				vo.position = playerVO.position;
			}
		}
		
		Iterator<Entry<String, TeamSimpleSeasonVO>> teamItr = teamRecords.entrySet().iterator();
		while(teamItr.hasNext()) {
			teamItr.next().getValue().update();
		}
		
		isReading = false;
	}
	
	public static void clear() {
		playerRecords.clear();
		teamRecords.clear();
	}
	
	/** 发现新文件加入时调用此方法 */
	public void appendMatches(ArrayList<File> files) {	
		isReading = true;
		int size = files.size();
		File[] newFiles = new File[size];
		for (int i=0;i<size;i++) {
			newFiles[i] = files.get(i);
		}
		Arrays.sort(newFiles, new FileComparator());
		
		SimpleUtility.fileCount += newFiles.length;
		
		SimpleMatchesAccumulator accumulator = 
				new SimpleMatchesAccumulator(playerRecords, teamRecords);
		accumulator.accumulate(newFiles, true);
		
		HashSet<PlayerSimpleSeasonVO> playerUpdated = accumulator.getUpdatedPlayers();
		HashSet<TeamSimpleSeasonVO> teamUpdated = accumulator.getUpdatedTeams();
		
		if (PlayerSimpleData.players==null) {
			PlayerSimpleData.loadPlayers();
		}
		
		for (PlayerSimpleSeasonVO vo : playerUpdated) {
			vo.update();
			
			PlayerSimpleVO playerVO = PlayerSimpleData.players.get(vo.name);
			if (playerVO != null) {
				vo.age = playerVO.age;
				vo.position = playerVO.position;
			}
		}
		for (TeamSimpleSeasonVO vo : teamUpdated) {
			vo.update();
		}
		
		isReading = false;
	}
	

	public ArrayList<PlayerSimpleSeasonVO> getAllPlayerSeasonData() {
		return new ArrayList<PlayerSimpleSeasonVO>(playerRecords.values());
	}
	
	public ArrayList<TeamSimpleSeasonVO> getAllTeamSeasonData() {	
		return new ArrayList<TeamSimpleSeasonVO>(teamRecords.values());
	}
	
	public static void reload() {
		playerRecords.clear();
		teamRecords.clear();
		loadMatches();
	}
}
