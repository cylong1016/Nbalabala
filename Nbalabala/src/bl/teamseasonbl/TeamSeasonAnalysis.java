/**
 * 
 * @author Issac Ding
 * @version 下午3:08:56
 */
package bl.teamseasonbl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import blservice.TeamSeasonBLService;
import data.seasondata.SeasonData;
import data.seasondata.TeamSeasonRecord;
import dataservice.SeasonDataService;
import enums.ScreenDivision;
import enums.SortOrder;
import enums.TeamSortBasis;

/**
 * 分析球队数据的类
 * @author Issac Ding
 * @version 2015年3月15日 下午3:08:56
 */
public class TeamSeasonAnalysis implements TeamSeasonBLService{
	
	//记录上一次返回的，也就是界面正在展示的表
	private ArrayList<TeamSeasonRecord> currentList = new ArrayList<TeamSeasonRecord>();

	private SeasonDataService seasonData = new SeasonData();
	
	/** 刚进入界面时调用此方法，得到按名字排序的球队数据 */
	public ArrayList<TeamSeasonRecord> getTeamDataSortedByName() {
		currentList = seasonData.getScreenedTeamSeasonData(ScreenDivision.ALL);
		sortTeamDataByName(currentList);
		return currentList;
	}
	
	/** 返回按地区筛选的 */
	public ArrayList<TeamSeasonRecord> getScreenedTeamData(ScreenDivision division) {
		currentList = seasonData.getScreenedTeamSeasonData(division);
		return currentList;
	}
	
	/** 排序时调用此方法，order的AS为升序，DE为降序 */
	public ArrayList<TeamSeasonRecord> getResortedTeamData(TeamSortBasis basis, SortOrder order) {
		sortTeamSeasonData(currentList, basis, order);
		return currentList;
	}
	
	/** 根据球队缩写返回其赛季数据 */
	public TeamSeasonRecord getTeamDataByAbbr(String abbr) {
		return seasonData.getTeamDataByAbbr(abbr);
	}
	
	/** 按球队名排序 */
	private void sortTeamDataByName(ArrayList<TeamSeasonRecord> teams) {
		Comparator<TeamSeasonRecord> comparator = new Comparator<TeamSeasonRecord>() {
			public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
				return t1.getTeamName().compareTo(t2.getTeamName());
			}
		};
		Collections.sort(teams, comparator);
	}

	/** 按排序指标排序 */
	private void sortTeamSeasonData(ArrayList<TeamSeasonRecord> teams,
			TeamSortBasis basis, SortOrder order) {
		new TeamSorter().sort(teams, basis, order);
	}
}
