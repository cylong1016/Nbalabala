/**
 * 
 */
package po;

/**
 *
 * @author Issac Ding
 * @since 2015年6月15日 下午9:28:55
 * @version 1.0
 */
public class ClutchPO {
	
	public String getName() {
		return name;
	}

	public String getSeason() {
		return season;
	}

	public double getClutchTime() {
		return clutchTime;
	}

	public double getClutchScore() {
		return clutchScore;
	}

	public String name;
	
	public String season;
	
	public double clutchTime;
	
	public double clutchScore;
	
	public ClutchPO(String name,String season) {
		this.name = name;
		this.season = season;
	}
}
