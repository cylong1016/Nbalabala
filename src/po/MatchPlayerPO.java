package po;
/**
 * 一场比赛中一个球员的表现记录，对应txt中的一行
 * @author Issac Ding
 * @version 2015年3月14日  下午2:28:47
 */
public class MatchPlayerPO {
	
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
	
	public MatchPlayerPO(String record){
		String[]s = record.split(";");
		name = s[0];
		position = s[1];
		time = s[2];
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
		personalGoal = Integer.parseInt(s[17]);
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
