package bl.teamseasonbl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import po.TeamSeasonPO;
import enums.SortOrder;
import enums.TeamAllSortBasis;

/**
 * 球队总数据根据不同字段的排序
 * @author Issac Ding
 * @version 2015年3月21日 下午1:50:28
 */
public class TeamAllSorter {

	private static int factor = 1;

	public static void sort(ArrayList<TeamSeasonPO> teams, TeamAllSortBasis basis, SortOrder order) {
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

		case MATCH_COUNT:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return factor * (t1.getMatchCount() - t2.getMatchCount());
				}
			};
			break;

		case FIELD_MADE:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return factor * (t1.getFieldMade() - t2.getFieldMade());
				}
			};
			break;

		case FIELD_ATTEMPT:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return factor * (t1.getFieldAttempt() - t2.getFieldAttempt());
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

		case THREE_POINT_ATTEMPT:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return factor * (t1.getThreePointAttempt() - t2.getThreePointAttempt());
				}
			};
			break;

		case THREE_POINT_MADE:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return factor * (t1.getThreePointMade() - t2.getThreePointMade());
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

		case FREETHROW_MADE:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return factor * (t1.getFreethrowMade() - t2.getFreethrowMade());
				}
			};
			break;

		case FREETHROW_ATTEMPT:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return factor * (t1.getFreethrowAttempt() - t2.getFreethrowAttempt());
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

		case OFFENSIVE_REBOUND:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return factor * (t1.getOffensiveRebound() - t2.getOffensiveRebound());
				}
			};
			break;

		case DEFENSIVE_REBOUND:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return factor * (t1.getDefensiveRebound() - t2.getDefensiveRebound());
				}
			};
			break;

		case TOTAL_REBOUND:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return factor * (t1.getTotalRebound() - t2.getTotalRebound());
				}
			};
			break;

		case ASSIST:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return factor * (t1.getAssist() - t2.getAssist());
				}
			};
			break;

		case STEAL:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return factor * (t1.getSteal() - t2.getSteal());
				}
			};
			break;

		case BLOCK:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return factor * (t1.getBlock() - t2.getBlock());
				}
			};
			break;

		case TURNOVER:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return factor * (t1.getTurnover() - t2.getTurnover());
				}
			};
			break;

		case FOUL:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return factor * (t1.getFoul() - t2.getFoul());
				}
			};
			break;

		case SCORE:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return factor * (t1.getScore() - t2.getScore());
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

		case OFFENSIVE_ROUND:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getOffensiveRound() - t2.getOffensiveRound()) * 10000);
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

		case DEFENSIVE_ROUND:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getDefensiveRound() - t2.getDefensiveRound()) * 10000);
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

		case OFFENSIVE_REBOUND_EFF:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getOffensiveReboundEff() - t2.getOffensiveReboundEff()) * 10000);
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

		case STEAL_EFF:
			comparator = new Comparator<TeamSeasonPO>() {

				public int compare(TeamSeasonPO t1, TeamSeasonPO t2) {
					return (int)(factor * (t1.getStealEff() - t2.getStealEff()) * 10000);
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
			
		case OPPO_SCORE:
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
