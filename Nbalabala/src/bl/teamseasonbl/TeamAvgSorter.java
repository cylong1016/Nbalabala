package bl.teamseasonbl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import data.seasondata.TeamSeasonRecord;
import enums.SortOrder;
import enums.TeamAvgSortBasis;

/**
 * 球队平均数据根据不同字段的排序
 * @author cylong
 * @version 2015年3月29日 下午11:26:57
 */
public class TeamAvgSorter {

	private static int factor = 1;

	public static void sort(ArrayList<TeamSeasonRecord> teams, TeamAvgSortBasis basis, SortOrder order) {
		Comparator<TeamSeasonRecord> comparator = null;

		if (order == SortOrder.DE)
			factor = -1;
		else
			factor = 1;

		switch(basis) {

		case NAME:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * t1.getTeamName().compareTo(t2.getTeamName());
				}
			};
			break;

		case FIELD_GOAL_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFieldGoalAvg() - t2.getFieldGoalAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case FIELD_ATTEMPT_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFieldAttemptAvg() - t2.getFieldAttemptAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case THREE_POINT_ATTEMPT_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getThreePointAttemptAvg() - t2.getThreePointAttemptAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case THREE_POINT_GOAL_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getThreePointGoalAvg() - t2.getThreePointGoalAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case FREETHROW_GOAL_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFreethrowGoalAvg() - t2.getFreethrowGoalAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case FREETHROW_ATTEMPT_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFreethrowAttemptAvg() - t2.getFreethrowAttemptAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case OFFENSIVE_REBOUND_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getOffensiveReboundAvg() - t2.getOffensiveReboundAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case DEFENSIVE_REBOUND_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getDefensiveReboundAvg() - t2.getDefensiveReboundAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case TOTAL_REBOUND_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getTotalReboundAvg() - t2.getTotalReboundAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case ASSIST_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getAssistAvg() - t2.getAssistAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case STEAL_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getStealAvg() - t2.getStealAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case BLOCK_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getBlockAvg() - t2.getBlockAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case TURNOVER_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getTurnoverAvg() - t2.getTurnoverAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case FOUL_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFoulAvg() - t2.getFoulAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case SCORE_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getScoreAvg() - t2.getScoreAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case OFFENSIVE_ROUND_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getOffensiveRoundAvg() - t2.getOffensiveRoundAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case DEFENSIVE_ROUND_AVG:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getDefensiveRoundAvg() - t2.getDefensiveRoundAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case ASSIST_EFF:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getAssistEff() - t2.getAssistEff()) < 0 ? -1 : 1;
				}
			};
			break;

		case DEFENSIVE_EFF:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getDefensiveEff() - t2.getDefensiveEff()) < 0 ? -1 : 1;
				}
			};
			break;

		case DEFENSIVE_REBOUND_EFF:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getDefensiveReboundEff() - t2.getDefensiveReboundEff()) < 0 ? -1 : 1;
				}
			};
			break;

		case FIELD_PERCENT:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFieldPercent() - t2.getFieldPercent()) < 0 ? -1 : 1;
				}
			};
			break;

		case FREETHROW_PERCENT:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFreeThrowPercent() - t2.getFreeThrowPercent()) < 0 ? -1 : 1;
				}
			};
			break;

		case LOSES:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getLoses() - t2.getLoses());
				}
			};
			break;

		case MATCH_COUNT:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getMatchCount() - t2.getMatchCount());
				}
			};
			break;

		case OFFENSIVE_EFF:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getOffensiveEff() - t2.getOffensiveEff()) < 0 ? -1 : 1;
				}
			};
			break;

		case OFFENSIVE_REBOUND_EFF:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getOffensiveReboundEff() - t2.getOffensiveReboundEff()) < 0 ? -1 : 1;
				}
			};
			break;

		case STEAL_EFF:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getStealEff() - t2.getStealEff()) < 0 ? -1 : 1;
				}
			};
			break;

		case THREE_POINT_PERCENT:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getThreePointPercent() - t2.getThreePointPercent()) < 0 ? -1 : 1;
				}
			};
			break;

		case WINNING:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getWinning() - t2.getWinning()) < 0 ? -1 : 1;
				}
			};
			break;

		case WINS:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getWins() - t2.getWins());
				}
			};
			break;

		default:
			break;

		}

		Collections.sort(teams, comparator);
	}
}
