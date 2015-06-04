/**
 * 
 */
package blservice;

import java.util.ArrayList;

import po.MatchPlayerPO;

/**
 * 直播界面需要的接口
 * @author Issac Ding
 * @since 2015年6月2日 下午9:29:07
 * @version 1.0
 */
public interface LiveBLService {
	
	
	/** 通过检查网页:
	 *  1 如果今天有比赛，返回一个字符串，比如XX对XX的比赛将在18:00开始 
	 *  2 如果没有比赛，返回“今天没有比赛”之类的提示信息
	 *  3 如果有比赛正在进行，返回“XX对XX的比赛正在进行”这样的字符串
	 *  4 如果今天的比赛已经结束，返回“XX对XX的比赛已经结束”这样的字符串*/
	public String getLiveInfo();
	
	/** 对于上面这个方法的3 4 两种情况返回true 1 2 返回false 
	 *  因为只有在这两种情况下，才能点某个按钮进入下一级界面，显示直播界面
	 *  （当然了，对于情况4，界面将不会再动了）*/
	public boolean hasMatchStarted();
	
	/** 检查页面从上一次检查更新到现在有无更新 */
	public boolean isAnythingNew();
	
	/** 获取客场球员的记录，PO里面要有所有页面上能爬到的属性 */
	public ArrayList<MatchPlayerPO> getRoadPlayerRecords();
	
	/** 获取主场球员的记录，PO里面要有所有页面上能爬到的属性 ，这个网页上没有的属性不用管。注意“篮板”是totalRebounds*/
	public ArrayList<MatchPlayerPO> getHomePlayerRecords();
	
	/** 获取客场各节比分。
	 * 比如当前进行到第二节，第一节是20，第二节目前15，那么List就是size为2，两个元素是20和15 */
	public ArrayList<Integer> getRoadScores();

	/** 获取主场各节比分。
	 * 比如当前进行到第二节，第一节是20，第二节目前15，那么List就是size为2，两个元素是20和15 */
	public ArrayList<Integer> getHomeScores();
	
	/** 获取当前小节数，如当前第三小节正在进行，返回3 */
	public int getCurrentSectionCount();
}
