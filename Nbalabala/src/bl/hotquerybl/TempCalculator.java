/**
 * 
 */
package bl.hotquerybl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import po.MatchDetailPO;
import po.MatchPlayerPO;

/**
 *
 * @author Issac Ding
 * @since 2015年6月4日 上午8:56:59
 * @version 1.0
 */
public class TempCalculator {
	
	public void load(ArrayList<TempVO> list, ArrayList<MatchDetailPO> matches) {
		
		HashMap<String, TempVO> players  = new HashMap<String, TempVO>();
		
		Comparator<MatchDetailPO> comparator = new Comparator<MatchDetailPO>() {
			public int compare(MatchDetailPO p1, MatchDetailPO p2) {
				return p1.matchProfile.date.compareTo(p2.matchProfile.date);
			}
		};
		Collections.sort(matches, comparator);
		
		for (MatchDetailPO match : matches) {
			String homeTeam = match.matchProfile.homeAbbr;
			String roadTeam = match.matchProfile.roadAbbr;
			Date date = match.matchProfile.date;
			for (MatchPlayerPO player : match.matchPlayers) {
				String name = player.playerName;
				TempVO temp = players.get(name);
				if (temp == null) {
					temp = new TempVO();
					temp.name = player.playerName;
					players.put(name, temp);
				}
				if (player.getHomeOrRoad() == 'H') {
					temp.team = homeTeam;
					temp.oppo = roadTeam;
				}else {
					temp.team = roadTeam;
					temp.oppo = homeTeam;
				}
				temp.date = date;
				temp.matchCount ++;
				temp.matchID = player.matchID;
				temp.fieldAttempt += player.fieldAttempt;
				temp.fieldAttemptQueue.enqueue(player.fieldAttempt);
				temp.fieldGoal += player.fieldMade;
				temp.fieldGoalQueue.enqueue(player.fieldMade);
				temp.threePointAttempt += player.threepointAttempt;
				temp.threePointAttemptQueue.enqueue(player.threepointAttempt);
				temp.threePointGoal += player.threepointMade;
				temp.threePointGoalQueue.enqueue(player.threepointMade);
				temp.freethrowAttempt += player.freethrowAttempt;
				temp.freethrowAttemptQueue.enqueue(player.freethrowAttempt);
				temp.freethrowGoal += player.freethrowMade;
				temp.freethrowGoalQueue.enqueue(player.freethrowMade);
				temp.totalRebound += player.totalRebound;
				temp.reboundQueue.enqueue(player.totalRebound);
				temp.assist += player.assist;
				temp.assistQueue.enqueue(player.assist);
				temp.steal += player.steal;
				temp.stealQueue.enqueue(player.steal);
				temp.block += player.block;
				temp.blockQueue.enqueue(player.block);
				temp.score += player.score;
				temp.scoreQueue.enqueue(player.score);
			}
		}
		
		Iterator<TempVO> itr = players.values().iterator();
		while(itr.hasNext()) {
			itr.next().update();
		}
		
		list.addAll(players.values());
	}

}
