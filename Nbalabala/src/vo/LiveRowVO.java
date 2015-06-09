/**
 * 
 */
package vo;

/**
 * 对应于文字直播中的一行，对应于直播Web界面表格中的一行
 * @author Issac Ding
 * @since 2015年6月2日 下午10:02:13
 * @version 1.0
 */
public class LiveRowVO {
	
	
	/** 时间 */
	public String time;
	
	/** 如果这一行是“第一节开始”、“勇士队请求暂停”之类的，这个属性不为null。这时候合并Jtable
	 * 的后三列，显示这一事件 */
	public String event;
	
	/** 在event为null的时候，后面这三个属性才会被显示。
	 * 	当event不为null时，这三个属性都是null */

	/** 客场发生了什么 */	
	public String roadEvent;
	/** 两队当前比分 */
	public String currentScores;
	/** 主场发生了什么 */
	public String homeEvent;
	

}
