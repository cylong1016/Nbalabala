package vo;

import java.util.ArrayList;

/**
 * 比赛详细信息
 * @author lsy
 * @version 2015年3月16日  下午8:05:43
 */
public class MatchDetailVO {
	
	private MatchProfileVO profile;
	
	private ArrayList<MatchPlayerVO> homePlayers;
	
	private ArrayList<MatchPlayerVO> roadPlayers;
	
	public MatchDetailVO(MatchProfileVO profile,
			ArrayList<MatchPlayerVO> homePlayers,
			ArrayList<MatchPlayerVO> roadPlayers) {
		super();
		this.profile = profile;
		this.homePlayers = homePlayers;
		this.roadPlayers = roadPlayers;
	}

	public MatchProfileVO getProfile() {
		return profile;
	}

	public ArrayList<MatchPlayerVO> getHomePlayers() {
		return homePlayers;
	}

	public ArrayList<MatchPlayerVO> getRoadPlayers() {
		return roadPlayers;
	}
}
