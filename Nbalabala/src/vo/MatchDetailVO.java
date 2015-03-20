package vo;

import java.awt.Image;
import java.util.ArrayList;

/**
 * 比赛详细信息，包括简报，主场球员表现，客场球员表现，两队LOGO
 * @author lsy
 * @version 2015年3月16日  下午8:05:43
 */
public class MatchDetailVO {
	
	private MatchProfileVO profile;
	
	private ArrayList<MatchPlayerVO> homePlayers;
	
	private ArrayList<MatchPlayerVO> roadPlayers;
	
	private Image homeLogo;
	
	private Image roadLogo;
	
	/** 标记本场比赛数据是否有效，指的是有无所有球员上场时间加起来是否明显大于总时间，以及所有球员得分相加是否等于总分 */
	private boolean isValid;
	
	public MatchDetailVO(MatchProfileVO profile,
			ArrayList<MatchPlayerVO> homePlayers,
			ArrayList<MatchPlayerVO> roadPlayers, Image homeLogo, Image roadLogo) {
		super();
		this.profile = profile;
		this.homePlayers = homePlayers;
		this.roadPlayers = roadPlayers;
		this.homeLogo = homeLogo;
		this.roadLogo = roadLogo;
		
		// 总的秒数,用double是为了后面的除法
		double totalSeconds = (((profile.getEachSectionScore().split(";").length - 4) * 5) + 48.0) 
				* 60.0 * 5;
		
		int homeSeconds = 0;
		for (MatchPlayerVO matchPlayerVO : homePlayers) {
			homeSeconds += matchPlayerVO.getSeconds();
		}
		
		int roadSeconds = 0;
		for (MatchPlayerVO matchPlayerVO : roadPlayers) {
			roadSeconds += matchPlayerVO.getSeconds();
		}
		
		//认为总的时间和所有球员上场时间之和相差5%以内为合理的
		boolean homeTimeValid = Math.abs((totalSeconds - homeSeconds)/totalSeconds) < 0.05;
		boolean roadTimeValid = Math.abs((totalSeconds - roadSeconds)/totalSeconds) < 0.05;
		
		//认为所有球员得分相加等于球队得分为合理的
		String[]strings = profile.getScore().split("-");
		int homeScore = Integer.parseInt(strings[0]);
		int roadScore = Integer.parseInt(strings[1]);
		int homePlayersScore = 0;
		for (MatchPlayerVO matchPlayerVO : homePlayers) {
			homePlayersScore += matchPlayerVO.getPersonalGoal();
		}
		int roadPlayersScore = 0;
		for (MatchPlayerVO matchPlayerVO : roadPlayers) {
			roadPlayersScore += matchPlayerVO.getPersonalGoal();
		}
		boolean homeScoreValid = homeScore == homePlayersScore;
		boolean roadScoreValid = roadScore == roadPlayersScore;
		
		isValid = homeTimeValid && roadTimeValid && homeScoreValid && roadScoreValid;
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
	
	public boolean isMatchValid() {
		return isValid;
	}
	
	public Image getHomeLogo() {
		return homeLogo;
	}
	
	public Image getRoadLogo() {
		return roadLogo;
	}
}
