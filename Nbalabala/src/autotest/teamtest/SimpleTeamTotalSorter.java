package autotest.teamtest;

import java.util.Comparator;

/**
 * 球队总数据根据不同字段的排序
 * @author Issac Ding
 * @version 2015年3月21日 下午1:50:28
 */
public class SimpleTeamTotalSorter {

	private static int factor = 1;
	
	public static Comparator<TeamSimpleSeasonVO> getTeamTotalComparator(String basis, String order) {
		Comparator<TeamSimpleSeasonVO> comparator = null;

		if (order.equals("desc"))
			factor = -1;
		else
			factor = 1;
		
		switch(basis) {
		case "score":
		case "point":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamTotalSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return factor * (t1.score - t2.score);
				}
			};
			break;
			
		case "rebound":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamTotalSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return factor * (t1.totalRebound - t2.totalRebound);
				}
			};
			break;
			
		case "assist":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamTotalSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return factor * (t1.assist - t2.assist);
				}
			};
			break;
			
		case "blockShot":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamTotalSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return factor * (t1.block - t2.block);
				}
			};
			break;

		case "steal":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamTotalSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return factor * (t1.steal - t2.steal);
				}
			};
			break;
			
		case "foul":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamTotalSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return factor * (t1.foul - t2.foul);
				}
			};
			break;
			
		case "fault":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamTotalSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return factor * (t1.getTurnover() - t2.getTurnover());
				}
			};
			break;
		
		case "shot":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamTotalSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(10000 * factor * (t1.getFieldPercent() - t2.getFieldPercent()));
				}
			};
			break;
		
		case "three":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamTotalSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(10000 * factor * (t1.getThreePointPercent() - t2.getThreePointPercent()));
				}
			};
			break;
			
		case "penalty":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamTotalSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(10000*factor * (t1.getFreeThrowPercent() - t2.getFreeThrowPercent()));
				}
			};
			break;
			
		case "defendRebound":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamTotalSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return factor * (t1.getDefensiveRebound() - t2.getDefensiveRebound());
				}
			};
			break;

		case "offendRebound":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamTotalSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return factor * (t1.getOffensiveRebound() - t2.getOffensiveRebound());
				}
			};
			break;
		
		default:
			break;
		}
		return comparator;
	}

}
