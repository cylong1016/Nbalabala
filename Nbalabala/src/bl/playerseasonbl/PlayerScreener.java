package bl.playerseasonbl;

import java.util.ArrayList;

import data.seasondata.PlayerSeasonRecord;
import enums.PlayerAllSortBasis;
import enums.ScreenBasis;
import enums.SortOrder;

/**
 * 负责筛选球员的类
 * @author Issac Ding
 * @version 2015年3月21日  下午1:44:31
 */
public class PlayerScreener {
	
	public ArrayList<PlayerSeasonRecord> screen(ArrayList<PlayerSeasonRecord> players, 
			ScreenBasis basis) {
		PlayerAllSorter sorter = new PlayerAllSorter();
		switch(basis){
		case SCORE_REBOUND_ASSIST:
			sorter.sort(players, PlayerAllSortBasis.SCORE_REBOUND_ASSIST, SortOrder.DE);
			break;
		case REBOUND:
			sorter.sort(players, PlayerAllSortBasis.TOTAL_REBOUND, SortOrder.DE);
			break;
		case ASSIST:
			sorter.sort(players, PlayerAllSortBasis.ASSIST, SortOrder.DE);
			break;
		case BLOCK:
			sorter.sort(players, PlayerAllSortBasis.BLOCK, SortOrder.DE);
			break;
		case STEAL:
			sorter.sort(players, PlayerAllSortBasis.STEAL, SortOrder.DE);
			break;
		case FOUL:
			sorter.sort(players, PlayerAllSortBasis.FOUL, SortOrder.DE);
			break;
		case TURNOVER:
			sorter.sort(players, PlayerAllSortBasis.TURNOVER, SortOrder.DE);
			break;
		case TIME:
			sorter.sort(players, PlayerAllSortBasis.TIME, SortOrder.DE);
			break;
		case EFFICIENCY:
			sorter.sort(players, PlayerAllSortBasis.EFFICIENCY, SortOrder.DE);
			break;
		case FIELD:
			sorter.sort(players, PlayerAllSortBasis.FIELD_GOAL, SortOrder.DE);
			break;
		case THREE_POINT:
			sorter.sort(players, PlayerAllSortBasis.THREE_POINT_GOAL, SortOrder.DE);
			break;
		case FREE_THROW:
			sorter.sort(players, PlayerAllSortBasis.FREETHROW_GOAL, SortOrder.DE);
			break;
		case SCORE:
			sorter.sort(players, PlayerAllSortBasis.SCORE, SortOrder.DE);
			break;
		case DOUBLE_DOUBLE:
			sorter.sort(players, PlayerAllSortBasis.DOUBLE_DOUBLE, SortOrder.DE);
			break;
		default:
			break;
		}
		
		ArrayList<PlayerSeasonRecord> result = new ArrayList<PlayerSeasonRecord>();
		int maxIndex = players.size() > 50 ? 50 : players.size();
		for (int i=0; i< maxIndex;i++) {
			result.add(players.get(i));
		}
		
		return result;
	}

}
