package vo;

/**
 * 数据王
 * @author Issac Ding
 * @version 2015年4月29日  下午6:52:42
 */
public class KingVO {
	
	public KingVO(int top, String name, double data, int overallRank, String position) {
		super();
		this.top = top;
		this.name = name;
		this.data = data;
		this.overallRank = overallRank;
		this.position = position;
	}
	
	/** 第几名 */
	private int top;

	private String name;
	
	/** 数据值 */
	private double data;
	
	/** 该数据在所有球员中的排名 */
	private int overallRank;
	
	private String position;
	
	public int getTop() {
		return top;
	}

	public String getName() {
		return name;
	}

	public double getData() {
		return data;
	}

	public int getOverallRank() {
		return overallRank;
	}

	public String getPosition() {
		return position;
	}

}
