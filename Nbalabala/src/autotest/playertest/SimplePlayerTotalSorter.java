package autotest.playertest;

import java.util.Comparator;

/**
 * 将输入的球员平均数据排序的类
 * @author cylong
 * @version 2015年3月30日 上午1:49:34
 */
public class SimplePlayerTotalSorter {

	private static int factor = 1;

	public static Comparator<PlayerSimpleSeasonVO> getPlayerTotalComparator(String basis, String order) {
		Comparator<PlayerSimpleSeasonVO> comparator = null;
		
		if (order.equals("desc"))
			factor = -1;
		else
			factor = 1;

		switch(basis) {
		case "point":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerTotalSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (factor * (p1.score - p2.score));
				}
			};
			break;
			
		case "rebound":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerTotalSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (factor * (p1.totalRebound - p2.totalRebound));
				}
			};
			break;

		case "assist":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerTotalSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (factor * (p1.assist - p2.assist) );
				}
			};
			break;
		case "steal":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerTotalSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (factor * (p1.steal - p2.steal) );
				}
			};
			break;

		case "blockShot":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerTotalSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (factor * (p1.block - p2.block) );
				}
			};
			break;

		case "fault":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerTotalSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (factor * (p1.turnover - p2.turnover) );
				}
			};
			break;

		case "foul":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerTotalSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (factor * (p1.foul - p2.foul) );
				}
			};
			break;

		case "minute":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerTotalSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.minutes - p2.minutes) * 10000);
				}
			};
			break;
			
		case "efficient":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerTotalSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (factor * (p1.efficiency - p2.efficiency) );
				}
			};
			break;
			
		case "shot":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerTotalSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.getFieldPercent() - p2.getFieldPercent())*10000 );
				}
			};
			break;
			
		case "three":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerTotalSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.threePointPercent - p2.threePointPercent) *10000);
				}
			};
			break;
		case "penalty":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerTotalSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (int)(factor * (p1.freethrowPercent - p2.freethrowPercent)*10000 );
				}
			};
			break;
		case "doubleTwo":
			comparator = new Comparator<PlayerSimpleSeasonVO>() {
				int factor = SimplePlayerTotalSorter.factor;
				public int compare(PlayerSimpleSeasonVO p1, PlayerSimpleSeasonVO p2) {
					return (factor * (p1.doubleDoubleCount - p2.doubleDoubleCount) );
				}
			};
			break;
		default:
			break;
		}
		return comparator;
	}
}
