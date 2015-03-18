package vo;

import java.io.File;

import enums.TeamState;
import utility.Utility;

/**
 * 记录一场比赛中一个球员的发挥
 * @author Issac Ding
 * @version 2015年3月18日  上午10:32:04
 */
public class MatchPlayerVO {
	
	/** 球员名字 */
	private String name;
	
	/** 位置，以""代表后发 */
	private String position;
	
	/** 时间，格式是 分钟数：秒数 */
	private String time;
	
	/** 投篮命中 */
	private int fieldGoal;
	
	/** 投篮出手 */
	private int fieldAttempt;
	
	/** 三分命中 */
	private int threePointGoal;
	
	/** 三分出手 */
	private int threePointAttempt;
	
	/** 罚球命中 */
	private int freethrowGoal;
	
	/** 罚球出手 */
	private int freethrowAttempt;
	
	/** 进攻篮板 */
	private int offensiveRebound;
	
	/** 防守篮板 */
	private int defensiveRebound;
	
	/** 总篮板 */
	private int totalRebound;
	
	/** 助攻 */
	private int assist;
	
	/** 抢断 */
	private int steal;
	
	/** 盖帽 */
	private int block;
	
	/** 失误 */
	private int turnover;
	
	/** 犯规 */
	private int foul;
	
	/** 个人得分 */
	private int personalGoal;
	
	//将该比赛的文件也传进来，如果有脏数据，可以处理修正之
	public MatchPlayerVO(String record, File file, TeamState teamState){
		String[]s = record.split(";");
		name = s[0];
		position = s[1];
		//上场时间为null的情况
		if (s[2].split(":").length < 2) {
			int totalSeconds = Utility.getModifiedTime(file, teamState);
			int minutes = totalSeconds / 60;
			int seconds = totalSeconds - minutes * 60;
			time = minutes + "-" + seconds;
		}else {
			time = s[2];
		}
		fieldGoal = Integer.parseInt(s[3]);
		fieldAttempt = Integer.parseInt(s[4]);
		threePointGoal = Integer.parseInt(s[5]);
		threePointAttempt = Integer.parseInt(s[6]);
		freethrowGoal = Integer.parseInt(s[7]);
		freethrowAttempt = Integer.parseInt(s[8]);
		offensiveRebound = Integer.parseInt(s[9]);
		defensiveRebound = Integer.parseInt(s[10]);
		totalRebound = Integer.parseInt(s[11]);
		assist = Integer.parseInt(s[12]);
		steal = Integer.parseInt(s[13]);
		block = Integer.parseInt(s[14]);
		turnover = Integer.parseInt(s[15]);
		foul = Integer.parseInt(s[16]);
		try{
			personalGoal = Integer.parseInt(s[17]);
		}catch(NumberFormatException e){
			personalGoal = 2 * fieldGoal + threePointGoal + freethrowGoal;
		}
		
	}

	public String getName() {
		return name;
	}

	public String getPosition() {
		return position;
	}

	public String getTime() {
		return time;
	}

	public int getFieldGoal() {
		return fieldGoal;
	}

	public int getFieldAttempt() {
		return fieldAttempt;
	}

	public int getThreePointGoal() {
		return threePointGoal;
	}

	public int getThreePointAttempt() {
		return threePointAttempt;
	}

	public int getFreethrowGoal() {
		return freethrowGoal;
	}

	public int getFreethrowAttempt() {
		return freethrowAttempt;
	}

	public int getOffensiveRebound() {
		return offensiveRebound;
	}

	public int getDefensiveRebound() {
		return defensiveRebound;
	}

	public int getTotalRebound() {
		return totalRebound;
	}

	public int getAssist() {
		return assist;
	}

	public int getSteal() {
		return steal;
	}

	public int getBlock() {
		return block;
	}

	public int getTurnover() {
		return turnover;
	}

	public int getFoul() {
		return foul;
	}

	public int getPersonalGoal() {
		return personalGoal;
	}


}
