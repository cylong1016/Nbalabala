package bl.playerseasonbl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import data.seasondata.PlayerSeasonRecord;
import enums.PlayerAvgSortBasis;
import enums.SortOrder;

/**
 * 将输入的球员平均数据排序的类
 * @author cylong
 * @version 2015年3月30日 上午1:49:34
 */
public class PlayerAvgSorter {

	private int factor = 1;

	public void sort(ArrayList<PlayerSeasonRecord> players, PlayerAvgSortBasis basis, SortOrder order) {
		Comparator<PlayerSeasonRecord> comparator = null;

		if (order == SortOrder.DE)
			factor = -1;
		else
			factor = 1;

		switch(basis) {
		case FIRST_COUNT_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFirstCountAvg() - p2.getFirstCountAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case FIELD_GOAL_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFieldGoalAvg() - p2.getFieldGoalAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case FIELD_ATTEMPT_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFieldAttemptAvg() - p2.getFieldAttemptAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case FREETHROW_GOAL_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFreethrowGoalAvg() - p2.getFreethrowGoalAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case THREE_POINT_ATTEMPT_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getThreePointAttemptAvg() - p2.getThreePointAttemptAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case THREE_POINT_GOAL_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getThreePointGoalAvg() - p2.getThreePointGoalAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case FREETHROW_ATTEMPT_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFreethrowAttemptAvg() - p2.getFreethrowAttemptAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case OFFENSIVE_REBOUND_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getOffensiveReboundAvg() - p2.getOffensiveReboundAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case DEFENSIVE_REBOUND_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getDefensiveReboundAvg() - p2.getDefensiveReboundAvg()) < 0 ? -1 : 1;
				}
			};
			break;
		case TOTAL_REBOUND_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getTotalReboundAvg() - p2.getTotalReboundAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case ASSIST_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getAssistAvg() - p2.getAssistAvg()) < 0 ? -1 : 1;
				}
			};
			break;
		case STEAL_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getStealAvg() - p2.getStealAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case BLOCK_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getBlockAvg() - p2.getBlockAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case TURNOVER_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getTurnoverAvg() - p2.getTurnoverAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case FOUL_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFoulAvg() - p2.getFoulAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case SCORE_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getScoreAvg() - p2.getScoreAvg()) < 0 ? -1 : 1;
				}
			};
			break;

		case TIME_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getTimeDoubleAvg() - p2.getTimeDoubleAvg()) < 0 ? -1 : 1;
				}
			};
			break;
		case DOUBLE_DOUBLE_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getDoubleDoubleAvg() - p2.getDoubleDoubleAvg()) < 0 ? -1 : 1;
				}
			};
			break;
		case SCORE_REBOUND_ASSIST_AVG:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getScoreReboundAssistAvg() - p2.getScoreReboundAssistAvg()) < 0 ? -1 : 1;
				}
			};
			break;
		case ASSIST_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getAssistPercent() - p2.getAssistPercent()) < 0 ? -1 : 1;
				}
			};
			break;
		case BLOCK_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getBlockPercent() - p2.getBlockPercent()) < 0 ? -1 : 1;
				}
			};
			break;
		case DEFENSIVE_REBOUND_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getDefensiveReboundPercent() - p2.getDefensiveReboundPercent()) < 0 ? -1 : 1;
				}
			};
			break;
		case EFFICIENCY:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getEfficiency() - p2.getEfficiency());
				}
			};
			break;
		case FIELD_EFF:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFieldEff() - p2.getFieldEff()) < 0 ? -1 : 1;
				}
			};
			break;
		case FIELD_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFieldPercent() - p2.getFieldPercent()) < 0 ? -1 : 1;
				}
			};
			break;
		case FOUL_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFoulPercent() - p2.getFoulPercent()) < 0 ? -1 : 1;
				}
			};
			break;
		case FREETHROW_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFreeThrowPercent() - p2.getFreeThrowPercent()) < 0 ? -1 : 1;
				}
			};
			break;
		case GMSC:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getGmSc() - p2.getGmSc()) < 0 ? -1 : 1;
				}
			};
			break;
		case MATCH_COUNT:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getMatchCount() - p2.getMatchCount());
				}
			};
			break;
		case NAME:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getName().compareTo(p2.getName()));
				}
			};
			break;
		case OFFENSIVE_REBOUND_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getOffensiveReboundPercent() - p2.getOffensiveReboundPercent()) < 0 ? -1 : 1;
				}
			};
			break;
		case REAL_FIELD_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getRealFieldPercent() - p2.getRealFieldPercent()) < 0 ? -1 : 1;
				}
			};
			break;
		case STEAL_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getStealPercent() - p2.getStealPercent()) < 0 ? -1 : 1;
				}
			};
			break;
		case TEAM_NAME:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getTeam().compareTo(p2.getTeam()));
				}
			};
			break;
		case THREE_POINT_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getThreePointPercent() - p2.getThreePointPercent()) < 0 ? -1 : 1;
				}
			};
			break;
		case TOTAL_REBOUND_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getTotalReboundPercent() - p2.getTotalReboundPercent()) < 0 ? -1 : 1;
				}
			};
			break;
		case TURNOVER_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getTurnOverPercent() - p2.getTurnOverPercent()) < 0 ? -1 : 1;
				}
			};
			break;
		case USE_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getUsePercent() - p2.getUsePercent()) < 0 ? -1 : 1;
				}
			};
			break;
		default:
			break;
		}

		Collections.sort(players, comparator);
	}
}
