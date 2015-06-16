package test.teams;

import java.util.Comparator;

import test.players.TwoComparatorChain;

/**
 * 球队平均数据根据不同字段的排序
 * @author cylong
 * @version 2015年3月29日 下午11:26:57
 */
public class TeamComparatorFactory {

	private static int factor = 1;
	
	public static final Comparator<TeamSimpleSeasonVO> TEAM_NAME_COMPARATOR =
			new Comparator<TeamSimpleSeasonVO>() {
				public int compare(TeamSimpleSeasonVO vo1, TeamSimpleSeasonVO vo2) {
					return vo1.teamName.compareTo(vo2.teamName);
				}
			};
	
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
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.scoreAvg - t2.scoreAvg) * 1000000);
				}
			};
			break;
			
		case "rebound":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.totalReboundAvg - t2.totalReboundAvg) * 1000000);
				}
			};
			break;
			
		case "assist":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.assistAvg - t2.assistAvg) * 1000000);
				}
			};
			break;
			
		case "blockShot":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.blockAvg - t2.blockAvg) * 1000000);
				}
			};
			break;

		case "steal":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.stealAvg - t2.stealAvg) * 1000000);
				}
			};
			break;
			
		case "foul":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.foulAvg - t2.foulAvg) * 1000000);
				}
			};
			break;
			
		case "fault":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getTurnoverAvg() - t2.getTurnoverAvg()) * 1000000);
				}
			};
			break;
		
		case "shot":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getFieldPercent() - t2.getFieldPercent()) * 1000000);
				}
			};
			break;
		
		case "three":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getThreePointPercent() - t2.getThreePointPercent()) * 1000000);
				}
			};
			break;
			
		case "penalty":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getFreeThrowPercent() - t2.getFreeThrowPercent()) * 1000000);
				}
			};
			break;
			
		case "defendRebound":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getDefensiveReboundAvg() - t2.getDefensiveReboundAvg()) * 1000000);
				}
			};
			break;

		case "offendRebound":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.getOffensiveReboundAvg() - t2.getOffensiveReboundAvg()) * 1000000);
				}
			};
			break;
			
		case "winRate":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.winning - t2.winning) * 1000000);
				}
			};
			break;
			
		case "offendRound":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.offensiveRound - t2.offensiveRound) * 1000000);
				}
			};
			break;
			//TODO 进攻回合的含义是什么
		case "offendEfficient":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.offensiveEff - t2.offensiveEff) * 1000000);
				}
			};
			break;			
			
		case "defendEfficient":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.defensiveEff - t2.defensiveEff) * 1000000);
				}
			};
			break;

		case "offendReboundEfficient":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.offensiveReboundEff - t2.offensiveReboundEff) * 1000000);
				}
			};
			break;
			
		case "defendReboundEfficient":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.defensiveReboundEff - t2.defensiveReboundEff) * 1000000);
				}
			};
			break;
			
		case "assistEfficient":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.assistEff - t2.assistEff) * 1000000);
				}
			};
			break;
		case "stealEfficient":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(factor * (t1.stealEff - t2.stealEff) * 1000000);
				}
			};
			break;
		default:
			break;

		}
		return new TwoComparatorChain<TeamSimpleSeasonVO>(TEAM_NAME_COMPARATOR, comparator);
	}
	
	public static Comparator<TeamSimpleSeasonVO> getTeamTotalComparator(String basis, String order) {
		Comparator<TeamSimpleSeasonVO> comparator = null;

		if (order.equals("desc"))
			factor = -1;
		else
			factor = 1;
		
		switch(basis) {
		case "score":
			//TODO 到底是score还是point
		case "point":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return factor * (t1.score - t2.score);
				}
			};
			break;
			
		case "rebound":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return factor * (t1.totalRebound - t2.totalRebound);
				}
			};
			break;
			
		case "assist":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return factor * (t1.assist - t2.assist);
				}
			};
			break;
			
		case "blockShot":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return factor * (t1.block - t2.block);
				}
			};
			break;

		case "steal":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return factor * (t1.steal - t2.steal);
				}
			};
			break;
			
		case "foul":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return factor * (t1.foul - t2.foul);
				}
			};
			break;
			
		case "fault":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return factor * (t1.getTurnover() - t2.getTurnover());
				}
			};
			break;
		
		case "shot":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(1000000 * factor * (t1.getFieldPercent() - t2.getFieldPercent()));
				}
			};
			break;
		
		case "three":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(1000000 * factor * (t1.getThreePointPercent() - t2.getThreePointPercent()));
				}
			};
			break;
			
		case "penalty":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return (int)(10000000*factor * (t1.getFreeThrowPercent() - t2.getFreeThrowPercent()));
				}
			};
			break;
			
		case "defendRebound":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return factor * (t1.getDefensiveRebound() - t2.getDefensiveRebound());
				}
			};
			break;

		case "offendRebound":
			comparator = new Comparator<TeamSimpleSeasonVO>() {
				private int factor = TeamComparatorFactory.factor;
				public int compare(TeamSimpleSeasonVO t1, TeamSimpleSeasonVO t2) {
					return factor * (t1.getOffensiveRebound() - t2.getOffensiveRebound());
				}
			};
			break;
		
		default:
			break;
		}
		return new TwoComparatorChain<TeamSimpleSeasonVO>(TEAM_NAME_COMPARATOR, comparator);
	}

}
