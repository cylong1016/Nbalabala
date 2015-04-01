package blservice;

import java.util.ArrayList;

import vo.TeamSeasonVO;
import enums.ScreenDivision;
import enums.SortOrder;
import enums.TeamAllSortBasis;
import enums.TeamAvgSortBasis;

/**
 * 球队赛季数据的接口
 * @author Issac Ding
 * @version 2015年3月18日 上午8:46:35
 */
public interface TeamSeasonBLService {

	/** 刚进入界面时调用此方法，得到按名字排序的球队数据 */
	public ArrayList<TeamSeasonVO> getTeamDataSortedByName();

	/** 返回按地区筛选的 */
	public ArrayList<TeamSeasonVO> getScreenedTeamData(ScreenDivision division);

	/** 总数据排序时调用此方法，order的AS为升序，DE为降序 */
	public ArrayList<TeamSeasonVO> getResortedTeamAllData(TeamAllSortBasis basis, SortOrder order);

	/**
	 * 平均排序时调用此方法，order的AS为升序，DE为降序
	 * @author cylong
	 * @version 2015年3月30日 上午12:49:58
	 */
	public ArrayList<TeamSeasonVO> getResortedTeamAvgData(TeamAvgSortBasis basis, SortOrder order);
}
