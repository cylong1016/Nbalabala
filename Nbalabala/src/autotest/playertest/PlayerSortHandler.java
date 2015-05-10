package autotest.playertest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import autotest.MyComparatorChain;
import autotest.TwoComparatorChain;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月11日  下午1:33:51
 */
public class PlayerSortHandler {
	
	public static void playerSortHandle(String[] args, int state, String firstArg, String secondArg, ArrayList<PlayerSimpleSeasonVO> playerVOs) {
		
		//只有一个 -player 的情况 在console里面处理
		if(state == 3) {
			Collections.sort(playerVOs, PlayerComparatorFactory.getHotComparator(firstArg));
		}else if(state == 4) {
			Collections.sort(playerVOs, PlayerComparatorFactory.getKingComparator(firstArg, secondArg));
		}else {	// 高阶数据和普通数据有可能有sort参数，可能存在复杂情况
			
			for (int i = 1; i< args.length ; i++) {
				
				if (args[i].equals("-sort")) {
					int index = i+1;	// sort指令的参数就是args[index]
					
					//多重排序的情况
					if (args[index].contains(",")) {
						ArrayList<Comparator<PlayerSimpleSeasonVO>> comparators = new ArrayList<Comparator<PlayerSimpleSeasonVO>>();
						String [] basises = args[index].split(",");	//TODO 这个效率可以优化一下？
						for (String basis : basises) {
							String [] s = basis.split("\\.");
							if (state == 1) {
								comparators.add(PlayerComparatorFactory.getPlayerTotalComparator(s[0], s[1]));
							}else {
								comparators.add(PlayerComparatorFactory.getPlayerAvgAndHighComparator(s[0], s[1]));
							}
						}
						Collections.sort(playerVOs, new MyComparatorChain<PlayerSimpleSeasonVO>(comparators));
						return;
					}
					//单重排序
					else {
						String [] s = args[index].split("\\.");
						Comparator<PlayerSimpleSeasonVO> comparator = null;
						if (state == 1) {
							comparator = PlayerComparatorFactory.getPlayerTotalComparator(s[0], s[1]);
						}else {
							comparator = PlayerComparatorFactory.getPlayerAvgAndHighComparator(s[0], s[1]);
						}
						Collections.sort(playerVOs, 
								new TwoComparatorChain<PlayerSimpleSeasonVO>(PlayerComparatorFactory.PLAYER_NAME_COMPARATOR, 
										comparator));
						return;
					}
				}
			}
			
			// 默认排序
			switch (state) {
			case 0:
				Collections.sort(playerVOs, PlayerComparatorFactory.getPlayerAvgAndHighComparator("score", "desc"));
				break;
			case 1:
				Collections.sort(playerVOs, PlayerComparatorFactory.getPlayerTotalComparator("score", "desc"));
				break;
			default:
				Collections.sort(playerVOs, PlayerComparatorFactory.getPlayerAvgAndHighComparator("realShot", "desc"));
			}
		}
	}
}
