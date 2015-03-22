package bl.playerseasonbl;

import java.util.ArrayList;

import data.seasondata.PlayerSeasonRecord;
import enums.PlayerSortBasis;
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
		PlayerSorter sorter = new PlayerSorter();
		switch(basis){
		case SCORE_REBOUND_ASSIST:
			sorter.sort(players, PlayerSortBasis.SCORE_REBOUND_ASSIST, SortOrder.DE);
			break;
		case REBOUND:
			sorter.sort(players, PlayerSortBasis.TOTAL_REBOUND, SortOrder.DE);
			break;
		case ASSIST:
			sorter.sort(players, PlayerSortBasis.ASSIST, SortOrder.DE);
			break;
		case BLOCK:
			sorter.sort(players, PlayerSortBasis.BLOCK, SortOrder.DE);
			break;
		case STEAL:
			sorter.sort(players, PlayerSortBasis.STEAL, SortOrder.DE);
			break;
		case FOUL:
			sorter.sort(players, PlayerSortBasis.FOUL, SortOrder.DE);
			break;
		case TURNOVER:
			sorter.sort(players, PlayerSortBasis.TURNOVER, SortOrder.DE);
			break;
		case TIME:
			sorter.sort(players, PlayerSortBasis.TIME, SortOrder.DE);
			break;
		case EFFICIENCY:
			sorter.sort(players, PlayerSortBasis.EFFICIENCY, SortOrder.DE);
			break;
		case FIELD:
			sorter.sort(players, PlayerSortBasis.FIELD_GOAL, SortOrder.DE);
			break;
		case THREE_POINT:
			sorter.sort(players, PlayerSortBasis.THREE_POINT_GOAL, SortOrder.DE);
			break;
		case FREE_THROW:
			sorter.sort(players, PlayerSortBasis.FREETHROW_GOAL, SortOrder.DE);
			break;
		case SCORE:
			sorter.sort(players, PlayerSortBasis.SCORE, SortOrder.DE);
			break;
		case DOUBLE_DOUBLE:
			sorter.sort(players, PlayerSortBasis.DOUBLE_DOUBLE, SortOrder.DE);
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
