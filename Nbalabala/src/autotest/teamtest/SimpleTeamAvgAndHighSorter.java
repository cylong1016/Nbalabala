package autotest.teamtest;

import java.util.Comparator;

/**
 * 球队平均数据根据不同字段的排序
 * @author cylong
 * @version 2015年3月29日 下午11:26:57
 */
public class SimpleTeamAvgAndHighSorter {

	private static int factor = 1;
	
	public static Comparator<TeamSimpleSeasonVO> getTeamAvgAndHighComparator(String basis, String order) {
		Comparator<TeamSimpleSeasonVO> comparator = null;

		if (order.equals("desc"))
			factor = -1;
		else
			factor = 1;
		
		switch(basis) {

		case "score":
		case "point":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamAvgAndHighSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.scoreAvg - t2.scoreAvg) * 10000);
				}
			};
			break;
			
		case "rebound":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamAvgAndHighSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.totalReboundAvg - t2.totalReboundAvg) * 10000);
				}
			};
			break;
			
		case "assist":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamAvgAndHighSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.assistAvg - t2.assistAvg) * 10000);
				}
			};
			break;
			
		case "blockShot":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamAvgAndHighSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.blockAvg - t2.blockAvg) * 10000);
				}
			};
			break;

		case "steal":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamAvgAndHighSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.stealAvg - t2.stealAvg) * 10000);
				}
			};
			break;
			
		case "foul":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamAvgAndHighSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.foulAvg - t2.foulAvg) * 10000);
				}
			};
			break;
			
		case "fault":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamAvgAndHighSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getTurnoverAvg() - t2.getTurnoverAvg()) * 10000);
				}
			};
			break;
		
		case "shot":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamAvgAndHighSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getFieldPercent() - t2.getFieldPercent()) * 10000);
				}
			};
			break;
		
		case "three":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamAvgAndHighSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getThreePointPercent() - t2.getThreePointPercent()) * 10000);
				}
			};
			break;
			
		case "penalty":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamAvgAndHighSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getFreeThrowPercent() - t2.getFreeThrowPercent()) * 10000);
				}
			};
			break;
			
		case "defendRebound":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamAvgAndHighSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getDefensiveReboundAvg() - t2.getDefensiveReboundAvg()) * 10000);
				}
			};
			break;

		case "offendRebound":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamAvgAndHighSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getOffensiveReboundAvg() - t2.getOffensiveReboundAvg()) * 10000);
				}
			};
			break;
			
		case "winRate":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamAvgAndHighSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.winning - t2.winning) * 10000);
				}
			};
			break;
			
		case "offendRound":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamAvgAndHighSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.offensiveRound - t2.offensiveRound) * 10000);
				}
			};
			break;
			//TODO 进攻回合的含义是什么
		case "offendEfficient":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamAvgAndHighSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.offensiveEff - t2.offensiveEff) * 10000);
				}
			};
			break;			
			
		case "defendEfficient":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamAvgAndHighSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.defensiveEff - t2.defensiveEff) * 10000);
				}
			};
			break;

		case "offendReboundEfficient":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamAvgAndHighSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.offensiveReboundEff - t2.offensiveReboundEff) * 10000);
				}
			};
			break;
			
		case "defendReboundEfficient":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamAvgAndHighSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.defensiveReboundEff - t2.defensiveReboundEff) * 10000);
				}
			};
			break;
			
		case "assistEfficient":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamAvgAndHighSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.assistEff - t2.assistEff) * 10000);
				}
			};
			break;
			
		case "stealEfficient":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = SimpleTeamAvgAndHighSorter.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.stealEff - t2.stealEff) * 10000);
				}
			};
			break;
		default:
			break;

		}
		return comparator;
	}

}
