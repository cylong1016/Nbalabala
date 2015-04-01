/**
 * @author Issac Ding
 * @version 下午3:08:56
 */
package bl.teamseasonbl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import vo.TeamSeasonVO;
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
	private ArrayList<TeamSeasonVO> currentList = new ArrayList<TeamSeasonVO>();

	private SeasonDataService seasonData = new SeasonData();

	/** 刚进入界面时调用此方法，得到按名字排序的球队数据 */
	public ArrayList<TeamSeasonVO> getTeamDataSortedByName() {
		currentList = seasonData.getScreenedTeamSeasonData(ScreenDivision.ALL);
		sortTeamDataByName(currentList);
		return currentList;
	}

	public static void main(String[] args) {
		ArrayList<TeamSeasonVO> records = new TeamSeasonAnalysis().getScreenedTeamData(ScreenDivision.ALL);
		for(TeamSeasonVO record : records) {
			System.out.println(record.getTeamName());
		}
	}

	/** 返回按地区筛选的 */
	public ArrayList<TeamSeasonVO> getScreenedTeamData(ScreenDivision division) {
		currentList = seasonData.getScreenedTeamSeasonData(division);
		return currentList;
	}

	/** 总数据排序时调用此方法，order的AS为升序，DE为降序 */
	public ArrayList<TeamSeasonVO> getResortedTeamAllData(TeamAllSortBasis basis, SortOrder order) {
		TeamAllSorter.sort(currentList, basis, order);
		return currentList;
	}

	/** 平均数据排序时调用此方法，order的AS为升序，DE为降序 */
	@Override
	public ArrayList<TeamSeasonVO> getResortedTeamAvgData(TeamAvgSortBasis basis, SortOrder order) {
		TeamAvgSorter.sort(currentList, basis, order);
		return currentList;
	}

	/** 根据球队缩写返回其赛季数据 */
	public TeamSeasonVO getTeamDataByAbbr(String abbr) {
		return seasonData.getTeamDataByAbbr(abbr);
	}

	/** 按球队名排序 */
	private void sortTeamDataByName(ArrayList<TeamSeasonVO> teams) {
		Comparator<TeamSeasonVO> comparator = new Comparator<TeamSeasonVO>() {

			public int compare(TeamSeasonVO t1, TeamSeasonVO t2) {
				return t1.getTeamName().compareTo(t2.getTeamName());
			}
		};
		Collections.sort(teams, comparator);
	}

}
