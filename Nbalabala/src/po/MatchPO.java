package po;

import java.util.ArrayList;

/**
 * 记录一场比赛的所有信息
 * 因为比分只供展示，不供计算，所以用STRING存储就行
 * @author Issac Ding
 * @version 2015年3月18日  上午9:20:22
 */
public class MatchPO {
	
	int year;
	int month;
	int day;
	
	String homeAbbr;
	String roadAbbr;
	
	String totalHomeScore;
	String totalRoadScore;
	
	//存放小节比分，从0位置开始依次是第一小节主场，第一小节客场，第二小节主场……
	ArrayList<String> sectionScores = new ArrayList<String>();
	
	//记录每个主场球员的表现
	ArrayList<MatchPlayerPO> homePlayers = new ArrayList<MatchPlayerPO>();
	
	ArrayList<MatchPlayerPO> roadPlayers = new ArrayList<MatchPlayerPO>();
	

}
