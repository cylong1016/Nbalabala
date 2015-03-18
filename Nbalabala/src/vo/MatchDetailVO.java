package vo;

import java.util.ArrayList;

/**
 * 比赛详细信息
 * ArrayList中1，2分别存放两个球队的各种信息，按相同顺序，不同球员信息之间用";"隔开
 * @author lsy
 * @version 2015年3月16日  下午8:05:43
 */
public class MatchDetailVO {
	
	/** 球员名 */
	ArrayList<String> playerName1,playerName2;
	/** 球员位置 */
	ArrayList<String> playerLocation1,playerLocation2;
	/** 在场时间 */
	ArrayList<String> playTime1,playTime2;
	/** 投篮命中数 */
	ArrayList<Integer> fieldGoal1,fieldGoal2;
	/** 投篮出手数 */
	ArrayList<Integer> fieldAttempt1,fieldAttempt2;
	/** 三分命中数 */
	ArrayList<Integer> threePointGoal1,threePointGoal2;
	/** 三分出手数 */
	ArrayList<Integer> threePointAttempt1,threePointAttempt2;
	/** 罚球命中数 */
	ArrayList<Integer> freethrowGoal1,freethrowGoal2;
	/** 罚球出手数 */
	ArrayList<Integer> freethrowAttempt1,freethrowAttempt2;
	/** 进攻篮板数 */
	ArrayList<Integer> offensiveRebound1,offensiveRebound2;
	/** 防守篮板数 */
	ArrayList<Integer> defensiveRebound1,defensiveRebound2;
	/** 总篮板数 */
	ArrayList<Integer> totalRebound1,totalRebound2;
	/** 助攻数 */
	ArrayList<Integer> assist1,assist2;
	/** 抢断数 */
	ArrayList<Integer> steal1,steal2;
	/** 盖帽数 */
	ArrayList<Integer> block1,block2;
	/** 失误数 */
	ArrayList<Integer> turnover1,turnover2;
	/** 犯规数 */
	ArrayList<Integer> foul1,foul2;
	/** 个人得分 */
	ArrayList<Integer> personalGoal1,personalGoal2;
}
