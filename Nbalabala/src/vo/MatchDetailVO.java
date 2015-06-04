package vo;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import po.ExtraTimePO;
import po.MatchDetailPO;
import po.MatchPlayerPO;
import po.MatchProfilePO;

/**
 * 比赛详细信息，包括简报，主场球员表现，客场球员表现，两队LOGO
 * @author lsy
 * @version 2015年3月16日  下午8:05:43
 */
public class MatchDetailVO {
	
	private MatchProfileVO profile;
	
	/** 每节比分 ，格式为“27-25;29-31;13-25;16-31;”*/
	private String eachSectionScore;
	
	private ArrayList<MatchPlayerPO> homePlayers;
	
	private ArrayList<MatchPlayerPO> roadPlayers;
	
	private Image homeLogo;
	
	private Image roadLogo;
	
	private String homeHighestScoreName;
	
	private String homeHighestReboundName;
	
	private String homeHighestAssistName;
	
	private int homeHighestScoreValue;
	
	private int homeHighestReboundValue;
	
	private int homeHighestAssistValue;
	
	private String roadHighestScoreName;
	
	private String roadHighestReboundName;
	
	private String roadHighestAssistName;
	
	private int roadHighestScoreValue;
	
	private int roadHighestReboundValue;
	
	private int roadHighestAssistValue;
	
	public MatchDetailVO(MatchDetailPO po, Image homeLogo, Image roadLogo) {
		MatchProfilePO profile = po.matchProfile;
		this.profile = new MatchProfileVO(profile);
		this.homePlayers = new ArrayList<MatchPlayerPO>();
		this.roadPlayers = new ArrayList<MatchPlayerPO>();
		for (MatchPlayerPO player : po.matchPlayers) {
			if (player.homeOrRoad == 'H') {
				this.homePlayers.add(player);
			}else {
				this.roadPlayers.add(player);
			}
		}
		this.homeLogo = homeLogo;
		this.roadLogo = roadLogo;
		
		/** 每节比分 ，格式为“27-25;29-31;13-25;16-31;”*/
		StringBuffer sb = new StringBuffer();
		sb.append(profile.roadSection1).append("-").append(profile.homeSection1).append(';')
			.append(profile.roadSection2).append("-").append(profile.homeSection2).append(';')
			.append(profile.roadSection3).append("-").append(profile.homeSection3).append(';')
			.append(profile.roadSection4).append("-").append(profile.homeSection4).append(';');
		if (profile.section == 5 && po.extraTimes != null) {
			ExtraTimePO extraTimePO = po.extraTimes.get(0);
			sb.append(extraTimePO.roadScore).append("-").append(extraTimePO.homeScore).append(';');
		}else if (profile.section > 6 && po.extraTimes != null) {
			Collections.sort(po.extraTimes, new Comparator<ExtraTimePO>() {
				public int compare(ExtraTimePO po1, ExtraTimePO po2) {
					return po1.extraOrder - po2.extraOrder;
				}
			});
			for (int i=0; i<po.extraTimes.size(); i++)  {
				ExtraTimePO extra = po.extraTimes.get(i);
				sb.append(extra.roadScore).append("-").append(extra.homeScore).append(';');
			}
		}
		eachSectionScore = sb.toString();
		
		findKings();
	}
	
	private void findKings() {
		
		//找出得分篮板助攻最多的球员名字和值
		for (MatchPlayerPO MatchPlayerPO : homePlayers) {
			if (MatchPlayerPO.getScore() > homeHighestScoreValue) {
				homeHighestScoreValue = MatchPlayerPO.getScore();
				homeHighestScoreName = MatchPlayerPO.getPlayerName();
			}
			if (MatchPlayerPO.getTotalRebound() > homeHighestReboundValue) {
				homeHighestReboundValue = MatchPlayerPO.getTotalRebound();
				homeHighestReboundName = MatchPlayerPO.getPlayerName();
			}
			if (MatchPlayerPO.getAssist() > homeHighestAssistValue) {
				homeHighestAssistValue = MatchPlayerPO.getAssist();
				homeHighestAssistName = MatchPlayerPO.getPlayerName();
			}
		} 
		for (MatchPlayerPO MatchPlayerPO : roadPlayers) {
			if (MatchPlayerPO.getScore() > roadHighestScoreValue) {
				roadHighestScoreValue = MatchPlayerPO.getScore();
				roadHighestScoreName = MatchPlayerPO.getPlayerName();
			}
			if (MatchPlayerPO.getTotalRebound() > roadHighestReboundValue) {
				roadHighestReboundValue = MatchPlayerPO.getTotalRebound();
				roadHighestReboundName = MatchPlayerPO.getPlayerName();
			}
			if (MatchPlayerPO.getAssist() > roadHighestAssistValue) {
				roadHighestAssistValue = MatchPlayerPO.getAssist();
				roadHighestAssistName = MatchPlayerPO.getPlayerName();
			}
		}
	}

	/** 得到3元数组，分别是客场球队本场比赛最高得分、篮板、助攻的人名 */
	public String[] getRoadHighestNames() {
		return new String[] {roadHighestScoreName, roadHighestReboundName, roadHighestAssistName};
	}
	/** 得到3元数组，分别是客场球队本场比赛最高得分、篮板、助攻的数值 */
	public int[] getRoadHighestValues() {
		return new int[] {roadHighestScoreValue, roadHighestReboundValue, roadHighestAssistValue};
	}
	/** 得到3元数组，分别是客场球队本场比赛最高得分、篮板、助攻的人名 */
	public String[] gethomeHighestNames() {
		return new String[] {homeHighestScoreName, homeHighestReboundName, homeHighestAssistName};
	}
	/** 得到3元数组，分别是主场球队本场比赛最高得分、篮板、助攻的数值 */
	public int[] gethomeHighestValues() {
		return new int[] {homeHighestScoreValue, homeHighestReboundValue, homeHighestAssistValue};
	}

	public MatchProfileVO getProfile() {
		return profile;
	}

	public ArrayList<MatchPlayerPO> getHomePlayers() {
		return homePlayers;
	}

	public ArrayList<MatchPlayerPO> getRoadPlayers() {
		return roadPlayers;
	}
	
	public Image getHomeLogo() {
		return homeLogo;
	}
	
	public Image getRoadLogo() {
		return roadLogo;
	}
	
	public String getEachSectionScore() {
		return eachSectionScore;
	}
	
	/** 获得主场队的命中率、三分命中、罚球命中、篮板、助攻 */
	public double[] getHomeFiveArgs() {
		int fieldAttempt = 0;
		int fieldMade = 0;
		int threeAttempt = 0;
		int threeMade = 0;
		int freethrowAttempt = 0;
		int freethrowMade = 0;
		int assist = 0;
		int rebound = 0;
		for (MatchPlayerPO vo : homePlayers) {
			fieldAttempt += vo.getFieldAttempt();
			fieldMade += vo.getFieldMade();
			threeAttempt += vo.getThreepointAttempt();
			threeMade += vo.getThreepointMade();
			freethrowAttempt += vo.getFreethrowAttempt();
			freethrowMade += vo.getFreethrowMade();
			assist += vo.getAssist();
			rebound += vo.getTotalRebound();
		}
		double [] result = new double[5];
		if (fieldAttempt != 0) result[0] = fieldMade / (double)fieldAttempt * 100;
		else result[0] = 0;
		if (threeAttempt != 0) result[1] = threeMade / (double)threeAttempt * 100;
		else result[1] = 0;
		if (freethrowAttempt != 0) result[2] = freethrowMade / (double)freethrowAttempt * 100;
		else result[2] = 0;
		result[3] = rebound;
		result[4] = assist;
		return result;
	}
	
	/** 获得客场队的命中率、三分命中、罚球命中、篮板、助攻 */
	public double[] getRoadFiveArgs() {
		int fieldAttempt = 0;
		int fieldMade = 0;
		int threeAttempt = 0;
		int threeMade = 0;
		int freethrowAttempt = 0;
		int freethrowMade = 0;
		int assist = 0;
		int rebound = 0;
		for (MatchPlayerPO vo : roadPlayers) {
			fieldAttempt += vo.getFieldAttempt();
			fieldMade += vo.getFieldMade();
			threeAttempt += vo.getThreepointAttempt();
			threeMade += vo.getThreepointMade();
			freethrowAttempt += vo.getFreethrowAttempt();
			freethrowMade += vo.getFreethrowMade();
			assist += vo.getAssist();
			rebound += vo.getTotalRebound();
		}
		double [] result = new double[5];
		if (fieldAttempt != 0) result[0] = fieldMade / (double)fieldAttempt * 100;
		else result[0] = 0;
		if (threeAttempt != 0) result[1] = threeMade / (double)threeAttempt * 100;
		else result[1] = 0;
		if (freethrowAttempt != 0) result[2] = freethrowMade / (double)freethrowAttempt * 100;
		else result[2] = 0;
		result[3] = rebound;
		result[4] = assist;
		return result;
	}
}
