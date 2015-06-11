package blservice;

import java.util.ArrayList;
import java.util.HashMap;

import vo.LivePlayerVO;

/**
 * 直播界面需要的接口
 * @author Issac Ding
 * @since 2015年6月2日 下午9:29:07
 * @version 1.0
 */
public interface LiveBLService {
	
	/**
	 * 每次调用其它方法的时候就要调用这个方法刷新数据
	 * @author cylong
	 * @version 2015年6月12日  上午12:30:04
	 */
	public void refresh();
	
	/**
	 * 返回将要或者正在直播的比赛列	表
	 * @return 时间=>比赛球队
	 * @author cylong
	 * @version 2015年6月11日  下午7:57:16
	 */
	public HashMap<String, String> getLiveList();
	
	/** 对于上面这个方法的1 3 4 三种情况返回true; 2 返回false 
	 *  因为只有在这两种情况下，才能点某个按钮进入下一级界面，显示直播界面
	 *  （当然了，对于情况4，界面将不会再动了）*/
	public boolean hasMatchStarted();
	
	/** 检查页面从上一次检查更新到现在有无更新 */
	public boolean isAnythingNew();
	
	/** 得到客场球队名 */
	public String getRoadName();
	/** 得到客场球缩写 */
	public String getRoadAbbr();
	/** 得到主场球队名 */
	public String getHomeName();
	/** 得到主场球缩写 */
	public String getHomeAbbr();
	
	/** 获取客场球员的记录，PO里面要有所有页面上能爬到的属性 */
	public ArrayList<LivePlayerVO> getRoadPlayerRecords();
	
	/** 获取主场球员的记录，PO里面要有所有页面上能爬到的属性 ，这个网页上没有的属性不用管。注意“篮板”是totalRebounds*/
	public ArrayList<LivePlayerVO> getHomePlayerRecords();
	
	/** 获取客场各节比分。
	 * 比如当前进行到第二节，第一节是20，第二节目前15，那么List就是size为2，两个元素是20和15 */
	/** 修改：最后还加上总比分
	 * @author cylong
	 * @version 2015年6月12日  上午12:05:30
	 */
	public ArrayList<Integer> getRoadScores();

	/** 获取主场各节比分。
	 * 比如当前进行到第二节，第一节是20，第二节目前15，那么List就是size为2，两个元素是20和15 */
	/**
	/** 修改：最后还加上总比分 
	 * @author cylong
	 * @version 2015年6月12日  上午12:05:00
	 */
	public ArrayList<Integer> getHomeScores();
	
	/** 获取当前小节数，如当前第三小节正在进行，返回3 */
	public int getCurrentSectionCount();
	
	/** 长度为5数组，分别是主队 投篮命中率、三分命中率、罚球命中率、篮板、助攻 */
	public double [] getHomeFiveArgs();
	
	/** 长度为5数组，分别是客队 投篮命中率、三分命中率、罚球命中率、篮板、助攻 */
	public double [] getroadFiveArgs();
}
