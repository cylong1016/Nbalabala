/**
 * 
 */
package po;

import java.util.ArrayList;

/**
 * 由比赛简况、加时赛情况、球员表现记录组成的比赛详情
 * @author Issac Ding
 * @since 2015年6月3日 下午12:29:51
 * @version 1.0
 */
public class MatchDetailPO {
	/** 比赛简况 */
	public MatchProfilePO matchProfile;
	/** 加时赛。如果没有加时赛，则为null */	
	public ArrayList<ExtraTimePO> extraTimes;
	/** 每个参赛球员表现记录 */	
	public ArrayList<MatchPlayerPO> matchPlayers;
	public MatchProfilePO getMatchProfile() {
		return matchProfile;
	}
	public ArrayList<ExtraTimePO> getExtraTimes() {
		return extraTimes;
	}
	public ArrayList<MatchPlayerPO> getMatchPlayers() {
		return matchPlayers;
	}

}
