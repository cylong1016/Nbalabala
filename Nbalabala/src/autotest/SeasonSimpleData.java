
package autotest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import utility.Utility;

/**
 * @author Issac Ding
 * @version 2015年3月14日  下午4:04:31
 */

public class SeasonSimpleData{
	
	public SeasonSimpleData() {
		if (playerRecords.size() == 0 || teamRecords.size() == 0) loadMatches();
	}
	
	/** 存储所有球员的所有赛季数据记录 */
	private static HashMap<String, PlayerSimpleSeasonVO> playerRecords =
			new HashMap<String,PlayerSimpleSeasonVO>();
	
	/** 存储所有球队的所有赛季数据记录 */
	private static HashMap<String, TeamSimpleSeasonVO> teamRecords = 
			new HashMap<String, TeamSimpleSeasonVO>();
	
	/**  position = F G C表示按位置筛选，""表示无要求。 league为"East或West"*/
	public ArrayList<PlayerSimpleSeasonVO> getFilteredPlayerSeasonData(String position,
			String league, String ageLimit) {
		
		Iterator<Map.Entry<String, PlayerSimpleSeasonVO>> itr = playerRecords.entrySet().iterator();
		
		ArrayList<PlayerSimpleSeasonVO> result = new ArrayList<PlayerSimpleSeasonVO>();
		
		while (itr.hasNext()) {
			PlayerSimpleSeasonVO vo = itr.next().getValue();
			int ageMin;
			int ageMax;
			switch (ageLimit) {
			case "<=22":
				ageMin = 0;
				ageMax = 22;
				break;
			case "22< X <=25":
				ageMin = 22;
				ageMax = 25;
				break;
			case "25< X <=30":
				ageMin = 25;
				ageMax = 30;
				break;
			case ">30":
				ageMin = 30;
				ageMax = 128;
				break;
			default:
				ageMin = 0;
				ageMax = 128;
			}
			if (vo.position.contains(position) && 
					SimpleConstants.getLeagueByAbbr(vo.teamName).contains(league) && 
					vo.age > ageMin && vo.age < ageMax) {
				result.add(vo);
			}
		}
		return result;
	}
	
	private void loadMatches() {
		File[] files = Utility.getSortedMatchFiles();
		
		SimpleMatchesAccumulator accumulator = new SimpleMatchesAccumulator(playerRecords, teamRecords);
		accumulator.accumulate(files, false);
		
		Iterator<Entry<String, PlayerSimpleSeasonVO>> playerItr = playerRecords.entrySet().iterator();
		while(playerItr.hasNext()) {
			playerItr.next().getValue().update();
		}
		
		Iterator<Entry<String, TeamSimpleSeasonVO>> teamItr = teamRecords.entrySet().iterator();
		while(teamItr.hasNext()) {
			teamItr.next().getValue().update();
		}
	}
	
	public static void clear() {
		playerRecords.clear();
		teamRecords.clear();
	}
	
	/** 发现新文件加入时调用此方法 */
	public void appendMatches(ArrayList<File> files) {
		int size = files.size();
		File[] newFiles = new File[size];
		for (int i=0;i<size;i++) {
			newFiles[i] = files.get(i);
		}
		SimpleMatchesAccumulator accumulator = 
				new SimpleMatchesAccumulator(playerRecords, teamRecords);
		accumulator.accumulate(newFiles, true);
		
		HashSet<PlayerSimpleSeasonVO> playerUpdated = accumulator.getUpdatedPlayers();
		HashSet<TeamSimpleSeasonVO> teamUpdated = accumulator.getUpdatedTeams();
		
		for (PlayerSimpleSeasonVO vo : playerUpdated) {
			vo.update();
		}
		for (TeamSimpleSeasonVO vo : teamUpdated) {
			vo.update();
		}
	}
	
	/** 发现有文件被删除时，重新读取所有文件 */
	public void reloadMatches() {
		playerRecords.clear();
		teamRecords.clear();
		new SimpleMatchesAccumulator(playerRecords, teamRecords).accumulate(Utility.getSortedMatchFiles(), false);
	}

	public ArrayList<PlayerSimpleSeasonVO> getAllPlayerSeasonData() {
		return new ArrayList<PlayerSimpleSeasonVO>(playerRecords.values());
	}
	
	public ArrayList<TeamSimpleSeasonVO> getAllTeamSeasonData() {	
		return new ArrayList<TeamSimpleSeasonVO>(teamRecords.values());
	}
	
}
