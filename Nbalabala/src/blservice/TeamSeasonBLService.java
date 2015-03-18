package blservice;

import java.util.ArrayList;

import data.seasondata.TeamSeasonRecord;
import enums.SortOrder;
import enums.TeamSortBasis;

/**
 * 球队赛季数据的接口
 * @author Issac Ding
 * @version 2015年3月18日  上午8:46:35
 */
public interface TeamSeasonBLService {
	
	/** 刚进入界面时调用此方法，得到按名字排序的球队数据 */
	public ArrayList<TeamSeasonRecord> getTeamDataSortedByName();
	
	/** 排序时调用此方法，order的AS为升序，DE为降序 */
	public ArrayList<TeamSeasonRecord> getResortedTeamData(TeamSortBasis basis, SortOrder order);
}
