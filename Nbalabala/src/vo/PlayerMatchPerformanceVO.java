package vo;

/**
 * 记录球员在一场比赛中的发挥，包括赛季、日期、两队
 * @author Issac Ding
 * @version 2015年3月19日  下午8:25:09
 */
public class PlayerMatchPerformanceVO {
	
	private MatchPlayerVO matchPlayerRecord;
	
	private String season;
	
	private String date;
	
	private String twoTeams;

	public MatchPlayerVO getMatchPlayerRecord() {
		return matchPlayerRecord;
	}

	public String getSeason() {
		return season;
	}

	public String getDate() {
		return date;
	}

	public String getTwoTeams() {
		return twoTeams;
	}
	
	

}
