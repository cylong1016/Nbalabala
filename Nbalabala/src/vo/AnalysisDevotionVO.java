/**
 * 
 */
package vo;

/**
 * 分析球员对球队贡献的VO
 * @author Issac Ding
 * @since 2015年6月9日 上午10:31:00
 * @version 1.0
 */
public class AnalysisDevotionVO {
	
	/** 球员名 */
	private String name;
	/** 球员对球队贡献值 */
	private double devotion;
	
	
	public AnalysisDevotionVO(String name, double devotion) {
		super();
		this.name = name;
		this.devotion = devotion;
	}

	public String getName() {
		return name;
	}

	public double getDevotion() {
		return devotion;
	}
	
	

}
