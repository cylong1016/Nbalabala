/**
 * 
 * @author Issac Ding
 * @version 下午3:08:56
 */
package bl.seasonbl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.xml.parsers.FactoryConfigurationError;

import data.seasondata.PlayerSeasonRecord;
import data.seasondata.SeasonData;
import data.seasondata.TeamSeasonRecord;
import enums.SortOrder;
import enums.TeamSortBasis;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月15日  下午3:08:56
 */
public class TeamSeasonAnalysis {
	
	private SeasonData seasonData = new SeasonData();
	
	private int factor = 1;
	
	public ArrayList<TeamSeasonRecord> getTeamSeasonData(TeamSortBasis basis, SortOrder order){
		
		ArrayList<TeamSeasonRecord> teams = seasonData.getTeamSeasonData();
		
		Comparator<TeamSeasonRecord> comparator = null;
		
		if (order == SortOrder.DE) factor = -1;
		else factor = 1;
		
		switch (basis) {
		case MATCH_COUNT:
			comparator = new Comparator<TeamSeasonRecord>() {
				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2){
					return factor * (t1.getMatchCount() - t2.getMatchCount());
				}
			};
			break;

		default:
			break;
		}
		
		//上面还有40个case！
		
		Collections.sort(teams, comparator);
		return teams;
	}
}
