/**
 * 
 */
package bl.matchquerybl;

import java.sql.Date;
import java.util.ArrayList;

import vo.LiveRowVO;

/**
 * 
 * @author Issac Ding
 * @since 2015年6月9日 下午8:23:17
 * @version 1.0
 */
public class MatchRecordCrawler {
	
	/**
	 * @param url 爬比赛实录的网页
	 * @return 如果网络不通或者该网页不存在，返回null。否则，返回表格中每一行。
	 * 注意这里的每一行指的是每一个有四个单元格的行。
	 * 因为那些以12:00或者以5:00为开头的行可以标志着一小节的开始，所以界面层可以在
	 * 顺序读取你返回的ArrayList的过程中知道那是第几小节，所以不需要抓取那些
	 * "第x小节开始"的行。
	 */
	public ArrayList<LiveRowVO> getLives(String url) {
		return null;
	}

}
