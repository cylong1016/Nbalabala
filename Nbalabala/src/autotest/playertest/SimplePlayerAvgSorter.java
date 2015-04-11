package autotest.playertest;

import java.util.Comparator;

/**
 * 将输入的球员平均数据排序的类
 * @author cylong
 * @version 2015年3月30日 上午1:49:34
 */
public class SimplePlayerAvgSorter {

	private static int factor = 1;

	public static Comparator<PlayerSimpleSeasonVO> getPlayerAvgAndHighComparator(String basis, String order) {
		Comparator<PlayerSimpleSeasonVO> comparator = null;
		
		if (order.equals("desc"))
			factor = -1;
		else
			factor = 1;

		switch(basis) {
		case "point":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getScoreAvg() - p2.getScoreAvg()) * 10000);
				}
			};
			break;
			
		case "rebound":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getTotalReboundAvg() - p2.getTotalReboundAvg()) * 10000);
				}
			};
			break;

		case "assist":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getAssistAvg() - p2.getAssistAvg()) * 10000);
				}
			};
			break;
		case "steal":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getStealAvg() - p2.getStealAvg()) * 10000);
				}
			};
			break;

		case "blockShot":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getBlockAvg() - p2.getBlockAvg()) * 10000);
				}
			};
			break;

		case "fault":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getTurnoverAvg() - p2.getTurnoverAvg()) * 10000);
				}
			};
			break;

		case "foul":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getFoulAvg() - p2.getFoulAvg()) * 10000);
				}
			};
			break;

		case "minute":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getMinutesAvg() - p2.getMinutesAvg()) * 10000);
				}
			};
			break;
			
		case "efficient":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.efficiencyAvg - p2.efficiencyAvg) * 10000);
				}
			};
			break;
			
		case "shot":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getFieldPercent() - p2.getFieldPercent()) * 10000);
				}
			};
			break;
			
		case "three":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.threePointPercent - p2.threePointPercent) * 10000);
				}
			};
			break;
		case "penalty":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.freethrowPercent - p2.freethrowPercent) * 10000);
				}
			};
			break;
		case "doubleTwo":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.doubleDoubleAvg - p2.doubleDoubleAvg) * 10000);
				}
			};
			break;
			
			
			// 以下为高阶:
			
		case "realShot":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getRealFieldPercent() - p2.getRealFieldPercent()) * 10000);
				}
			};
			break;
			
		case "GmSc":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getGmSc() - p2.getGmSc()) * 10000);
				}
			};
			break;
			
		case "shotEfficient":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getFieldEff() - p2.getFieldEff()) * 10000);
				}
			};
			break;
			
		case "reboundEfficient":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getTotalReboundPercent() - p2.getTotalReboundPercent()) * 10000);
				}
			};
			break;
			
		case "offendReboundEfficient":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getOffensiveReboundPercent() - p2.getOffensiveReboundPercent()) * 10000);
				}
			};
			break;
			
		case "defendReboundEfficient":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getDefensiveReboundPercent() - p2.getDefensiveReboundPercent()) * 10000);
				}
			};
			break;
			
		case "assistEfficient":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getAssistPercent() - p2.getAssistPercent()) * 10000);
				}
			};
			break;
			
		case "stealEfficient":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getStealPercent() - p2.getStealPercent()) * 10000);
				}
			};
			break;
			
		case "blockShotEfficient":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getBlockPercent() - p2.getBlockPercent()) * 10000);
				}
			};
			break;
			
		case "faultEfficient":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getTurnOverPercent() - p2.getTurnOverPercent()) * 10000);
				}
			};
			break;

		case "frequency":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerAvgSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getUsePercent() - p2.getUsePercent()) * 10000);
				}
			};
			break;
		default:
			break;
		}
		
		return comparator;

	}
}
