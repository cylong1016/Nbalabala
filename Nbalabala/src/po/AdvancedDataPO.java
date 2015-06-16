/**
 * 
 */
package po;

/**
 *
 * @author Issac Ding
 * @since 2015年6月15日 下午9:19:31
 * @version 1.0
 */
public class AdvancedDataPO {
	
	public String name;
	
	public String season;
	
	public int gp;
	
	public double mpg;
	
	public double orpm;
	
	public double drpm;
	
	public double rpm;
	
	public double war;
	
	public AdvancedDataPO(String name, String season) {
		this.name = name;
		this.season = season;
	}

	public String getName() {
		return name;
	}

	public String getSeason() {
		return season;
	}

	public int getGp() {
		return gp;
	}

	public double getMpg() {
		return mpg;
	}

	public double getOrpm() {
		return orpm;
	}

	public double getDrpm() {
		return drpm;
	}

	public double getRpm() {
		return rpm;
	}

	public double getWar() {
		return war;
	}

}
