package vo;

/**
 * 用于直播中球员技术统计的VO
 * @author Issac Ding
 * @since 2015年6月9日 下午8:29:48
 * @version 1.0
 */
public class LivePlayerVO {
	
	public String nameChn;
	
	public String nameEng;
	
	public String position;
	
	/** 是否为首发 */
	public boolean isStarter;
	/** 在场时间，形如25:59 */
	public String timePlayed;
	
	/** 投篮命中 */
	public int fieldMade;
	/** 投篮出手 */	
	public int fieldAttempt;
	
	public int threepointMade;
	/** 三分出手 */
	public int threepointAttempt;
	
	public int freethrowMade;
	/** 罚球出手 */
	public int freethrowAttempt;
	
	/** 总篮板数 */
	public int totalRebound;
	/** 助攻 */
	public int assist;
	/** 抢断 */
	public int steal;
	/** 盖帽 */
	public int block;
	/** 失误 */
	public int turnover;
	/** 犯规 */
	public int foul;
	/** 得分 */
	public int score;
	/** 该球员在场时球队净得分 */
	public int plusMinus;
	
	public LivePlayerVO(String nameChn,String nameEng,String position,
			boolean isStarter,String timePlayed,int fieldMade,int fieldAttempt,int threepointMade,int threepointAttempt
			,int freethrowMade,int freethrowAttempt,int totalRebound,int assist,int steal,int block,int turnover,
			int foul,int score,int plusMinus){
		this.nameChn = nameChn;
		this.nameEng = nameEng;
		this.position = position;
		this.isStarter = isStarter;
		this.timePlayed = timePlayed;
		this.fieldMade = fieldMade;
		this.fieldAttempt = fieldAttempt;
		this.threepointMade = threepointMade;
		this.threepointAttempt = threepointAttempt;
		this.freethrowMade = freethrowMade;
		this.freethrowAttempt = freethrowAttempt;
		this.totalRebound = totalRebound;
		this.assist = assist;
		this.steal = steal;
		this.block = block;
		this.turnover = turnover;
		this.foul = foul;
		this.score =score;
		this.plusMinus = plusMinus;
	}

}
