package bl.playerseasonbl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import vo.PlayerSeasonVO;
import enums.PlayerAvgSortBasis;
import enums.SortOrder;

/**
 * 将输入的球员平均数据排序的类
 * @author cylong
 * @version 2015年3月30日 上午1:49:34
 */
public class PlayerAvgSorter {

	private int factor = 1;

	public void sort(ArrayList<PlayerSeasonVO> players, PlayerAvgSortBasis basis, SortOrder order) {
		Comparator<PlayerSeasonVO> comparator = null;
		
		if (order == SortOrder.DE)
			factor = -1;
		else
			factor = 1;

		switch(basis) {
		case FIRST_COUNT_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					// 排序时候的错误，参见以下的错误
					return (int)(factor * (p1.getFirstCountAvg() - p2.getFirstCountAvg()) * 10000);
				}
			};
			break;

		case FIELD_GOAL_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getFieldGoalAvg() - p2.getFieldGoalAvg()) * 10000);
				}
			};
			break;

		case FIELD_ATTEMPT_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getFieldAttemptAvg() - p2.getFieldAttemptAvg()) * 10000);
				}
			};
			break;

		case FREETHROW_GOAL_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getFreethrowGoalAvg() - p2.getFreethrowGoalAvg()) * 10000);
				}
			};
			break;

		case THREE_POINT_ATTEMPT_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getThreePointAttemptAvg() - p2.getThreePointAttemptAvg()) * 10000);
				}
			};
			break;

		case THREE_POINT_GOAL_AVG:
			System.out.println("_________");
			comparator = new Comparator<PlayerSeasonVO>() {
				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getThreePointGoalAvg() - p2.getThreePointGoalAvg()) * 10000);
				}
			};
			break;

		case FREETHROW_ATTEMPT_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getFreethrowAttemptAvg() - p2.getFreethrowAttemptAvg()) * 10000);
				}
			};
			break;

		case OFFENSIVE_REBOUND_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getOffensiveReboundAvg() - p2.getOffensiveReboundAvg()) * 10000);
				}
			};
			break;

		case DEFENSIVE_REBOUND_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getDefensiveReboundAvg() - p2.getDefensiveReboundAvg()) * 10000);
				}
			};
			break;
		case TOTAL_REBOUND_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getTotalReboundAvg() - p2.getTotalReboundAvg()) * 10000);
				}
			};
			break;

		case ASSIST_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getAssistAvg() - p2.getAssistAvg()) * 10000);
				}
			};
			break;
		case STEAL_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getStealAvg() - p2.getStealAvg()) * 10000);
				}
			};
			break;

		case BLOCK_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getBlockAvg() - p2.getBlockAvg()) * 10000);
				}
			};
			break;

		case TURNOVER_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getTurnoverAvg() - p2.getTurnoverAvg()) * 10000);
				}
			};
			break;

		case FOUL_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getFoulAvg() - p2.getFoulAvg()) * 10000);
				}
			};
			break;

		case SCORE_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getScoreAvg() - p2.getScoreAvg()) * 10000);
				}
			};
			break;

		case TIME_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getMinutesAvg() - p2.getMinutesAvg()) * 10000);
				}
			};
			break;
		case DOUBLE_DOUBLE_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getDoubleDoubleAvg() - p2.getDoubleDoubleAvg()) * 10000);
				}
			};
			break;
		case SCORE_REBOUND_ASSIST_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getScoreReboundAssistAvg() - p2.getScoreReboundAssistAvg()) * 10000);
				}
			};
			break;
		case ASSIST_PERCENT:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getAssistPercent() - p2.getAssistPercent()) * 10000);
				}
			};
			break;
		case BLOCK_PERCENT:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getBlockPercent() - p2.getBlockPercent()) * 10000);
				}
			};
			break;
		case DEFENSIVE_REBOUND_PERCENT:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getDefensiveReboundPercent() - p2.getDefensiveReboundPercent()) * 10000);
				}
			};
			break;
		case EFFICIENCY_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.efficiencyAvg - p2.efficiencyAvg) * 10000);
				}
			};
			break;
		case FIELD_EFF:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getFieldEff() - p2.getFieldEff()) * 10000);
				}
			};
			break;
		case FIELD_PERCENT:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getFieldPercent() - p2.getFieldPercent()) * 10000);
				}
			};
			break;
		case FOUL_PERCENT:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getFoulPercent() - p2.getFoulPercent()) * 10000);
				}
			};
			break;
		case FREETHROW_PERCENT:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getFreeThrowPercent() - p2.getFreeThrowPercent()) * 10000);
				}
			};
			break;
		case GMSC_AVG:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.gmscAvg - p2.gmscAvg) * 10000);
				}
			};
			break;
		case MATCH_COUNT:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return factor * (p1.getMatchCount() - p2.getMatchCount());
				}
			};
			break;
		case NAME:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return factor * (p1.getName().compareTo(p2.getName()));
				}
			};
			break;
		case OFFENSIVE_REBOUND_PERCENT:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getOffensiveReboundPercent() - p2.getOffensiveReboundPercent()) * 10000);
				}
			};
			break;
		case REAL_FIELD_PERCENT:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getRealFieldPercent() - p2.getRealFieldPercent()) * 10000);
				}
			};
			break;
		case STEAL_PERCENT:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getStealPercent() - p2.getStealPercent()) * 10000);
				}
			};
			break;
		case TEAM_NAME:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return factor * (p1.getTeam().compareTo(p2.getTeam()));
				}
			};
			break;
		case THREE_POINT_PERCENT:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getThreePointPercent() - p2.getThreePointPercent()) * 10000);
				}
			};
			break;
		case TOTAL_REBOUND_PERCENT:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getTotalReboundPercent() - p2.getTotalReboundPercent()) * 10000);
				}
			};
			break;
		case TURNOVER_PERCENT:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getTurnOverPercent() - p2.getTurnOverPercent()) * 10000);
				}
			};
			break;
		case USE_PERCENT:
			comparator = new Comparator<PlayerSeasonVO>() {

				public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
					return (int)(factor * (p1.getUsePercent() - p2.getUsePercent()) * 10000);
				}
			};
			break;
		default:
			break;
		}

		Collections.sort(players, comparator);
	}
}
