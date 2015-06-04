package bl.teamseasonbl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import po.TeamSeasonPO;
import enums.SortOrder;
import enums.TeamAvgSortBasis;

/**
 * 球队平均数据根据不同字段的排序
 * @author cylong
 * @version 2015年3月29日 下午11:26:57
 */
public class TeamAvgSorter {

	private int factor = 1;

	public void sort(ArrayList<TeamSeasonPO> teams, TeamAvgSortBasis basis, SortOrder order) {
		Comparator<TeamSeasonPO> comparator = null;

		if (order == SortOrder.DE)
			factor = -1;
		else
			factor = 1;

		switch(basis) {

		case NAME:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return factor * t1.getAbbr().compareTo(t2.getAbbr());
				}
			};
			break;

		case FIELD_MADE_AVG:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getFieldMadeAvg() - t2.getFieldMadeAvg()) * 10000);
				}
			};
			break;

		case FIELD_ATTEMPT_AVG:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getFieldAttemptAvg() - t2.getFieldAttemptAvg()) * 10000);
				}
			};
			break;

		case THREE_POINT_ATTEMPT_AVG:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getThreePointAttemptAvg() - t2.getThreePointAttemptAvg()) * 10000);
				}
			};
			break;

		case THREE_POINT_MADE_AVG:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getThreePointMadeAvg() - t2.getThreePointMadeAvg()) * 10000);
				}
			};
			break;

		case FREETHROW_MADE_AVG:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getFreethrowMadeAvg() - t2.getFreethrowMadeAvg()) * 10000);
				}
			};
			break;

		case FREETHROW_ATTEMPT_AVG:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getFreethrowAttemptAvg() - t2.getFreethrowAttemptAvg()) * 10000);
				}
			};
			break;

		case OFFENSIVE_REBOUND_AVG:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getOffensiveReboundAvg() - t2.getOffensiveReboundAvg()) * 10000);
				}
			};
			break;

		case DEFENSIVE_REBOUND_AVG:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getDefensiveReboundAvg() - t2.getDefensiveReboundAvg()) * 10000);
				}
			};
			break;

		case TOTAL_REBOUND_AVG:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getTotalReboundAvg() - t2.getTotalReboundAvg()) * 10000);
				}
			};
			break;

		case ASSIST_AVG:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getAssistAvg() - t2.getAssistAvg()) * 10000);
				}
			};
			break;

		case STEAL_AVG:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getStealAvg() - t2.getStealAvg()) * 10000);
				}
			};
			break;

		case BLOCK_AVG:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getBlockAvg() - t2.getBlockAvg()) * 10000);
				}
			};
			break;

		case TURNOVER_AVG:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getTurnoverAvg() - t2.getTurnoverAvg()) * 10000);
				}
			};
			break;

		case FOUL_AVG:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getFoulAvg() - t2.getFoulAvg()) * 10000);
				}
			};
			break;

		case SCORE_AVG:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getScoreAvg() - t2.getScoreAvg()) * 10000);
				}
			};
			break;

		case OFFENSIVE_ROUND_AVG:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getOffensiveRoundAvg() - t2.getOffensiveRoundAvg()) * 10000);
				}
			};
			break;

		case DEFENSIVE_ROUND_AVG:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getDefensiveRoundAvg() - t2.getDefensiveRoundAvg()) * 10000);
				}
			};
			break;

		case ASSIST_EFF:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getAssistEff() - t2.getAssistEff()) * 10000);
				}
			};
			break;

		case DEFENSIVE_EFF:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getDefensiveEff() - t2.getDefensiveEff()) * 10000);
				}
			};
			break;

		case DEFENSIVE_REBOUND_EFF:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getDefensiveReboundEff() - t2.getDefensiveReboundEff()) * 10000);
				}
			};
			break;

		case FIELD_PERCENT:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getFieldPercent() - t2.getFieldPercent()) * 10000);
				}
			};
			break;

		case FREETHROW_PERCENT:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getFreethrowPercent() - t2.getFreethrowPercent()) * 10000);
				}
			};
			break;

		case MATCH_COUNT:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return factor * (t1.getMatchCount() - t2.getMatchCount());
				}
			};
			break;

		case OFFENSIVE_EFF:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getOffensiveEff() - t2.getOffensiveEff()) * 10000);
				}
			};
			break;

		case OFFENSIVE_REBOUND_EFF:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getOffensiveReboundEff() - t2.getOffensiveReboundEff()) * 10000);
				}
			};
			break;

		case STEAL_EFF:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getStealEff() - t2.getStealEff()) * 10000);
				}
			};
			break;

		case THREE_POINT_PERCENT:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getThreePointPercent() - t2.getThreePointPercent()) * 10000);
				}
			};
			break;

		case WINNING:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getWinning() - t2.getWinning()) * 10000);
				}
			};
			break;

		case WINS:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return factor * (t1.getWins() - t2.getWins());
				}
			};
			break;
			
		case OPPO_FIELD_PERCENT:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.oppoFieldPercent - t2.oppoFieldPercent) * 10000);
				}
			};
			break;
			
		case OPPO_SCORE_AVG:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.oppoScoreAvg - t2.oppoScoreAvg) * 10000);
				}
			};
			break;

		default:
			break;

		}

		Collections.sort(teams, comparator);
	}
}
