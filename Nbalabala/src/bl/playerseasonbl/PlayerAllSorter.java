package bl.playerseasonbl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import data.seasondata.PlayerSeasonRecord;
import enums.PlayerAllSortBasis;
import enums.SortOrder;

/**
 * 将输入的球员总数据排序的类
 * @author Issac Ding
 * @version 2015年3月21日 下午1:40:00
 */
public class PlayerAllSorter {

	private int factor = 1;

	public void sort(ArrayList<PlayerSeasonRecord> players, PlayerAllSortBasis basis, SortOrder order) {
		Comparator<PlayerSeasonRecord> comparator = null;

		if (order == SortOrder.DE)
			factor = -1;
		else
			factor = 1;

		switch(basis) {
		case MATCH_COUNT:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getMatchCount() - p2.getMatchCount());
				}
			};
			break;

		case FIRST_COUNT:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFirstCount() - p2.getFirstCount());
				}
			};
			break;

		case TIME:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getTimeDouble() - p2.getTimeDouble()) < 0 ? -1 : 1;
				}
			};
			break;

		case FIELD_GOAL:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFieldGoal() - p2.getFieldGoal());
				}
			};
			break;

		case FIELD_ATTEMPT:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFieldAttempt() - p2.getFieldAttempt());
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

		case THREE_POINT_ATTEMPT:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getThreePointAttempt() - p2.getThreePointAttempt());
				}
			};
			break;

		case THREE_POINT_GOAL:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getThreePointGoal() - p2.getThreePointGoal());
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

		case FREETHROW_GOAL:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFreeThrowGoal() - p2.getFreeThrowGoal());
				}
			};
			break;

		case FREETHROW_ATTEMPT:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFreeThrowAttempt() - p2.getFreeThrowAttempt());
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

		case OFFENSIVE_REBOUND:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getOffensiveRebound() - p2.getOffensiveRebound());
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

		case DEFENSIVE_REBOUND:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getDefensiveRebound() - p2.getDefensiveRebound());
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

		case TOTAL_REBOUND:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getTotalRebound() - p2.getTotalRebound());
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

		case ASSIST:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getAssist() - p2.getAssist());
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

		case STEAL:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getSteal() - p2.getSteal());
				}
			};
			break;

		case STEAL_PERCENT:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					// 这块为什么后面要×1000， 主要原因是使排序的时候不会报错，参考下面的错误
					// Comparison method violates its general contract!
					return (int)(factor * (p1.getStealPercent() - p2.getStealPercent()) * 10000);
				}
			};
			break;

		case BLOCK:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getBlock() - p2.getBlock());
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

		case TURNOVER:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getTurnover() - p2.getTurnover());
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

		case FOUL:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFoul() - p2.getFoul());
				}
			};
			break;

		case SCORE:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getScore() - p2.getScore());
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

		case GMSC:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getGmSc() - p2.getGmSc()) < 0 ? -1 : 1;
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

		case FIELD_EFF:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getFieldEff() - p2.getFieldEff()) < 0 ? -1 : 1;
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

		case DOUBLE_DOUBLE:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getDoubleDouble() - p2.getDoubleDouble());
				}
			};
			break;

		case SCORE_REBOUND_ASSIST:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getScoreReboundAssist() - p2.getScoreReboundAssist());
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
		case NAME:
			comparator = new Comparator<PlayerSeasonRecord>() {

				public int compare(PlayerSeasonRecord p1, PlayerSeasonRecord p2) {
					return factor * (p1.getName().compareTo(p2.getName()));
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
		default:
			break;

		}

		Collections.sort(players, comparator);
	}

}
