
package bl.seasonbl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import data.seasondata.PlayerSeasonRecord;
import data.seasondata.SeasonData;
import enums.PlayerSortBasis;
import enums.SortOrder;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月15日  下午2:46:44
 */
public class PlayerSeasonAnalysis {
	
	private SeasonData seasonData = new SeasonData();
	
	private int factor = 1;
	
	public ArrayList<PlayerSeasonRecord> sortPlayers(PlayerSortBasis basis, SortOrder order) {
		ArrayList<PlayerSeasonRecord> players = seasonData.getPlayerSeasonData();
		
		Comparator<PlayerSeasonRecord> comparator = null;
		
		if (order == SortOrder.DE) factor = -1;
		else factor = 1;
		
		switch (basis) {
		case MATCH_COUNT:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2){
					return factor * (p1.getMatchCount() - p2.getMatchCount());
				}
			};
			break;
		case FIRST_COUNT:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2){
					return factor * (p1.getFirstCount() - p2.getFirstCount());
				}
			};
		case FIRST_COUNT_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {
				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2){
					return factor * (p1.getFirstCountAvg() - p2.getFirstCountAvg()) < 0 ? -1 : 1;
				}
			};
		default:
			break;
		}
		
		// 上面还有47个switch！
		
		Collections.sort(players, comparator);
		return players;
	}

}
