package bl.teamseasonbl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import data.seasondata.TeamSeasonRecord;
import enums.SortOrder;
import enums.TeamAllSortBasis;

/**
 * 球队总数据根据不同字段的排序
 * @author Issac Ding
 * @version 2015年3月21日 下午1:50:28
 */
public class TeamAllSorter {

	private static int factor = 1;

	public static void sort(ArrayList<TeamSeasonRecord> teams, TeamAllSortBasis basis, SortOrder order) {
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

		case MATCH_COUNT:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getMatchCount() - t2.getMatchCount());
				}
			};
			break;

		case FIELD_GOAL:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFieldGoal() - t2.getFieldGoal());
				}
			};
			break;

		case FIELD_ATTEMPT:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFieldAttempt() - t2.getFieldAttempt());
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

		case THREE_POINT_ATTEMPT:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getThreePointAttempt() - t2.getThreePointAttempt());
				}
			};
			break;

		case THREE_POINT_GOAL:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getThreePointGoal() - t2.getThreePointGoal());
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

		case FREETHROW_GOAL:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFreethrowGoal() - t2.getFreethrowGoal());
				}
			};
			break;

		case FREETHROW_ATTEMPT:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFreethrowAttempt() - t2.getFreethrowAttempt());
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

		case OFFENSIVE_REBOUND:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getOffensiveRebound() - t2.getOffensiveRebound());
				}
			};
			break;

		case DEFENSIVE_REBOUND:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getDefensiveRebound() - t2.getDefensiveRebound());
				}
			};
			break;

		case TOTAL_REBOUND:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getTotalRebound() - t2.getTotalRebound());
				}
			};
			break;

		case ASSIST:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getAssist() - t2.getAssist());
				}
			};
			break;

		case STEAL:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getSteal() - t2.getSteal());
				}
			};
			break;

		case BLOCK:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getBlock() - t2.getBlock());
				}
			};
			break;

		case TURNOVER:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getTurnover() - t2.getTurnover());
				}
			};
			break;

		case FOUL:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getFoul() - t2.getFoul());
				}
			};
			break;

		case SCORE:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getScore() - t2.getScore());
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

		case OFFENSIVE_ROUND:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getOffensiveRound() - t2.getOffensiveRound()) < 0 ? -1 : 1;
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

		case DEFENSIVE_ROUND:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getDefensiveRound() - t2.getDefensiveRound()) < 0 ? -1 : 1;
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

		case OFFENSIVE_REBOUND_EFF:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getOffensiveReboundEff() - t2.getOffensiveReboundEff()) < 0 ? -1 : 1;
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

		case STEAL_EFF:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getStealEff() - t2.getStealEff()) < 0 ? -1 : 1;
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

		case WINS:
			comparator = new Comparator<TeamSeasonRecord>() {

				public int compare(TeamSeasonRecord t1, TeamSeasonRecord t2) {
					return factor * (t1.getWins() - t2.getWins());
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

		default:
			break;
		}

		Collections.sort(teams, comparator);
	}

}
