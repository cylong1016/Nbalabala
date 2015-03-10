/**  
 * 创建时间：2015年3月10日 下午12:32:45  
 * 项目名称：PerformanceTest  
 * @author Xiaohan Ding
 * @version 1.0   
 * @since JDK 1.7 
 * 文件名称：Record.java  
 * 类说明：  
 */
public class Record {
	public Record(String name, int d1, int d2, int d3, int d4, int d5) {
		super();
		this.name = name;
		this.d1 = d1;
		this.d2 = d2;
		this.d3 = d3;
		this.d4 = d4;
		this.d5 = d5;
	}
	
	public String getName(){
		return name;
	}
	public int getD2(){
		return d2;
	}
	String name;
	int d1;
	int d2;
	int d3;
	int d4;
	int d5;

}
