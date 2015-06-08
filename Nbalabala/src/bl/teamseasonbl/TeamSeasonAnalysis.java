/**
 * @author Issac Ding
 * @version 下午3:08:56
 */
package bl.teamseasonbl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import po.TeamSeasonPO;
import utility.Utility;
import blservice.TeamSeasonBLService;
import data.seasondata.SeasonData;
import dataservice.SeasonDataService;
import enums.ScreenDivision;
import enums.SortOrder;
import enums.TeamAllSortBasis;
import enums.TeamAvgSortBasis;

/**
 * 分析球队数据的类
 * @author Issac Ding
 * @version 2015年3月15日 下午3:08:56
 */
public class TeamSeasonAnalysis implements TeamSeasonBLService {

	//记录上一次返回的，也就是界面正在展示的表
	private ArrayList<TeamSeasonPO> currentList = new ArrayList<TeamSeasonPO>();

	private SeasonDataService seasonData = new SeasonData();

	/** 刚进入界面时调用此方法，得到按名字排序的球队数据 */
	public ArrayList<TeamSeasonPO> getTeamDataSortedByName(String season) {
		currentList = seasonData.getScreenedTeamSeasonData(ScreenDivision.ALL, season);
		sortTeamDataByName(currentList);
		return currentList;
	}

	/** 返回按地区筛选的 */
	public ArrayList<TeamSeasonPO> getScreenedTeamData(ScreenDivision division, String season) {
		currentList = seasonData.getScreenedTeamSeasonData(division, season);
		return currentList;
	}

	/** 总数据排序时调用此方法，order的AS为升序，DE为降序 */
	public ArrayList<TeamSeasonPO> getResortedTeamAllData(TeamAllSortBasis basis, SortOrder order) {
		TeamAllSorter.sort(currentList, basis, order);
		return currentList;
	}

	/** 平均数据排序时调用此方法，order的AS为升序，DE为降序 */
	@Override
	public ArrayList<TeamSeasonPO> getResortedTeamAvgData(TeamAvgSortBasis basis, SortOrder order) {
		new TeamAvgSorter().sort(currentList, basis, order);
		return currentList;
	}

	/** 根据球队缩写返回其赛季数据 */
	public TeamSeasonPO getTeamDataByAbbr(String abbr, String season) {
		return seasonData.getTeamDataByAbbr(Utility.getOldAbbr(season, abbr), season);
	}

	/** 按球队名排序 */
	private void sortTeamDataByName(ArrayList<TeamSeasonPO> teams) {
		Comparator<TeamSeasonPO> comparator = new Comparator<TeamSeasonPO>() {

			public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
				return t1.getAbbr().compareTo(t2.getAbbr());
			}
		};
		Collections.sort(teams, comparator);
	}

}
