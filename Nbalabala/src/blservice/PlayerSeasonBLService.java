package blservice;

import java.util.ArrayList;

import data.seasondata.PlayerSeasonRecord;
import enums.PlayerSortBasis;
import enums.Position;
import enums.ScreenBasis;
import enums.ScreenDivision;
import enums.SortOrder;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月17日  下午11:00:29
 */
public interface PlayerSeasonBLService {
	
	/** 刚进入界面时调用此方法获得按照字典顺序排序的全部球员数据 */
	public ArrayList<PlayerSeasonRecord> getAllPlayersSortedByName();
	
	/** 得到对当前表重新排序后的表，参数是依据和顺序，AS为升序，DE为降序 */
	public ArrayList<PlayerSeasonRecord> getResortedPlayers(PlayerSortBasis basis, SortOrder order);
	
	/** 根据球员位置、地区、筛选依据，返回含有前50个记录的表 */
	public ArrayList<PlayerSeasonRecord> getScreenedPlayers(Position position, ScreenDivision division, 
			ScreenBasis basis);
}
