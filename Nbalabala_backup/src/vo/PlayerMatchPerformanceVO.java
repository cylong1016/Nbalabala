package vo;

import utility.Constants;

/**
 * 记录球员在一场比赛中的发挥，包括赛季、日期、两队
 * @author Issac Ding
 * @version 2015年3月19日  下午8:25:09
 */
public class PlayerMatchPerformanceVO {
	
	/**
	 * 注意：这个类与PlayerMatchPerformance的区别：这个类记录的是，在已知一场比赛简况的情况下，
	 * 每一位参与的球员具体的数据是怎样的，用在查询比赛信息的界面上，所以不需要记录这场比赛的赛季、日期、对阵队伍。
	 * 而PlayerMatchPerformance用在查询球员的比赛详细记录，所以需要记录赛季、日期、对针队伍*/
	
	public PlayerMatchPerformanceVO(MatchPlayerVO matchPlayerRecord,
			String season, String date, String twoTeams) {
		super();
		this.matchPlayerRecord = matchPlayerRecord;
		this.season = season;
		this.date = date;
		this.twoTeams = twoTeams;
	}
	
	public PlayerMatchPerformanceVO(String name) {
		this.matchPlayerRecord = new MatchPlayerVO(name);
		this.season = Constants.UNKNOWN;
		this.date = Constants.UNKNOWN;
		this.twoTeams = Constants.UNKNOWN;
	}
	
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
		String[] strings = twoTeams.split("-");
		if (strings.length > 1)
			return Constants.translateTeamAbbr(strings[0]) + "-" + 
				Constants.translateTeamAbbr(strings[1]);
		return twoTeams;
	}
}
