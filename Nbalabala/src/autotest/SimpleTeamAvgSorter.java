package autotest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import enums.SortOrder;
import enums.TeamAvgSortBasis;

/**
 * 球队平均数据根据不同字段的排序
 * @author cylong
 * @version 2015年3月29日 下午11:26:57
 */
public class SimpleTeamAvgSorter {

	private int factor = 1;

	public void sort(ArrayList<TeamSimpleSeasonVO> teams, TeamAvgSortBasis basis, SortOrder order) {
		Comparator<TeamSimpleSeasonVO> comparator = null;

		if (order == SortOrder.DE)
			factor = -1;
		else
			factor = 1;

		switch(basis) {

		case NAME:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return factor * t1.getTeamName().compareTo(t2.getTeamName());
				}
			};
			break;

		case OFFENSIVE_REBOUND_AVG:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getOffensiveReboundAvg() - t2.getOffensiveReboundAvg()) * 10000);
				}
			};
			break;

		case DEFENSIVE_REBOUND_AVG:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getDefensiveReboundAvg() - t2.getDefensiveReboundAvg()) * 10000);
				}
			};
			break;

		case TOTAL_REBOUND_AVG:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getTotalReboundAvg() - t2.getTotalReboundAvg()) * 10000);
				}
			};
			break;

		case ASSIST_AVG:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getAssistAvg() - t2.getAssistAvg()) * 10000);
				}
			};
			break;

		case STEAL_AVG:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getStealAvg() - t2.getStealAvg()) * 10000);
				}
			};
			break;

		case BLOCK_AVG:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getBlockAvg() - t2.getBlockAvg()) * 10000);
				}
			};
			break;

		case TURNOVER_AVG:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getTurnoverAvg() - t2.getTurnoverAvg()) * 10000);
				}
			};
			break;

		case FOUL_AVG:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getFoulAvg() - t2.getFoulAvg()) * 10000);
				}
			};
			break;

		case SCORE_AVG:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getScoreAvg() - t2.getScoreAvg()) * 10000);
				}
			};
			break;

		case ASSIST_EFF:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getAssistEff() - t2.getAssistEff()) * 10000);
				}
			};
			break;

		case DEFENSIVE_EFF:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getDefensiveEff() - t2.getDefensiveEff()) * 10000);
				}
			};
			break;

		case DEFENSIVE_REBOUND_EFF:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getDefensiveReboundEff() - t2.getDefensiveReboundEff()) * 10000);
				}
			};
			break;

		case FIELD_PERCENT:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getFieldPercent() - t2.getFieldPercent()) * 10000);
				}
			};
			break;

		case FREETHROW_PERCENT:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getFreeThrowPercent() - t2.getFreeThrowPercent()) * 10000);
				}
			};
			break;

		case LOSES:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return factor * (t1.getLoses() - t2.getLoses());
				}
			};
			break;

		case MATCH_COUNT:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return factor * (t1.getMatchCount() - t2.getMatchCount());
				}
			};
			break;

		case OFFENSIVE_EFF:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getOffensiveEff() - t2.getOffensiveEff()) * 10000);
				}
			};
			break;

		case OFFENSIVE_REBOUND_EFF:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getOffensiveReboundEff() - t2.getOffensiveReboundEff()) * 10000);
				}
			};
			break;

		case STEAL_EFF:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getStealEff() - t2.getStealEff()) * 10000);
				}
			};
			break;

		case THREE_POINT_PERCENT:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getThreePointPercent() - t2.getThreePointPercent()) * 10000);
				}
			};
			break;

		case WINNING:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getWinning() - t2.getWinning()) * 10000);
				}
			};
			break;

		case WINS:
			comparator = new Comparator<TeamSimpleSeasonVO>() {

				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
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
