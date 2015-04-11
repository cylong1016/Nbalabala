package autotest.playertest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import autotest.ComparatorChain;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月11日  下午1:33:51
 */
public class PlayerTestSortHandler {
	
	public static void playerSortHandle(String[]args, ArrayList<PlayerSimpleSeasonVO> vos) {
		
		boolean isHigh = false;
		
		for (int i = 1; i< args.length ; i++) {
			
			if (args[i].equals("-high"))
				isHigh = true;
			
			if (args[i].equals("-sort")) {
				int index = i+1;
				
				//多重排序的情况
				if (args[index].contains(",")) {
					ArrayList<Comparator<PlayerSimpleSeasonVO>> comparators = new ArrayList<Comparator<PlayerSimpleSeasonVO>>();
					String [] basises = args[index].split(",");
					for (String basis : basises) {
						String [] s = basis.split("\\.");
						comparators.add(SimplePlayerAvgSorter.getPlayerAvgAndHighComparator(s[0], s[1]));
					}
					Collections.sort(vos, new ComparatorChain<PlayerSimpleSeasonVO>(comparators));
					return;
				}else {
					
					//单重排序
					
					String [] s = args[index].split("\\.");
					Collections.sort(vos, SimplePlayerAvgSorter.getPlayerAvgAndHighComparator(s[0], s[1]));
					return;
				}
				
			}
		}
		
		// 默认排序
		if (isHigh) {
			Collections.sort(vos, SimplePlayerAvgSorter.getPlayerAvgAndHighComparator("score", "desc"));
		}else {
			Collections.sort(vos, SimplePlayerAvgSorter.getPlayerAvgAndHighComparator("realShot", "desc"));
		}
	}

}
