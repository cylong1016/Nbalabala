/**
 * 
 */
package vo;

/**
 * 分析最后五分钟的图表所用的VO
 * @author Issac Ding
 * @since 2015年6月9日 上午10:20:31
 * @version 1.0
 */
public class AnalysisClutchVO {
	
	
	/** 赛季 */
	private String season;
	/** 持球 */
	private double controlling;
	/** 场均得分 */
	private double scoreAvg;
	
	public AnalysisClutchVO(String season, double controlling, double scoreAvg) {
		super();
		this.season = season;
		this.controlling = controlling;
		this.scoreAvg = scoreAvg;
	}

	
	public String getSeason() {
		return season;
	}
	public double getControlling() {
		return controlling;
	}
	public double getScoreAvg() {
		return scoreAvg;
	}
	
	

}
