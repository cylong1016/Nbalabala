/**
 * 
 */
package vo;

/**
 *
 * @author Issac Ding
 * @since 2015年6月12日 上午11:08:14
 * @version 1.0
 */
public class ForecastVO {
	
	public double width;	//组距
	public int size;	//组数
	public double a;	//y = bx + a
	public double b;
	public double r;	//相关系数
	public int fromYear;	//起始年份
	public int toYear;
	public String conclusion;
	
	public double getWidth() {
		return width;
	}
	public int getSize() {
		return size;
	}
	public double getA() {
		return a;
	}
	public double getB() {
		return b;
	}
	public double getR() {
		return r;
	}
	public int getFromYear() {
		return fromYear;
	}
	public int getToYear() {
		return toYear;
	}
	public String getConclusion() {
		return conclusion;
	}

}
