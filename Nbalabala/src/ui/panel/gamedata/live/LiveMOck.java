/**
 * 
 */
package ui.panel.gamedata.live;

import java.util.ArrayList;
import java.util.HashMap;

import vo.LivePlayerVO;
import blservice.LiveBLService;

/**
 *
 * @author Issac Ding
 * @since 2015年6月12日 上午7:40:11
 * @version 1.0
 */
public class LiveMock implements LiveBLService{

	/* (non-Javadoc)
	 * @see blservice.LiveBLService#refresh()
	 */
	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see blservice.LiveBLService#getLiveList()
	 */
	@Override
	public HashMap<String, String> getLiveList() {
		HashMap<String, String> list = new HashMap<String, String>();
		list.put("06月15日 09：00","季后赛 勇士-骑士");
		list.put("06月17日 08：00","季后赛 勇士-骑士");
		return list;
	}

	/* (non-Javadoc)
	 * @see blservice.LiveBLService#hasMatchStarted()
	 */
	@Override
	public boolean hasMatchStarted() {
		return true;
	}

	/* (non-Javadoc)
	 * @see blservice.LiveBLService#getRoadName()
	 */
	@Override
	public String getRoadName() {
		return "勇士";
	}

	/* (non-Javadoc)
	 * @see blservice.LiveBLService#getRoadAbbr()
	 */
	@Override
	public String getRoadAbbr() {
		return "GSW";
	}

	/* (non-Javadoc)
	 * @see blservice.LiveBLService#getHomeName()
	 */
	@Override
	public String getHomeName() {
		return "骑士";
	}

	/* (non-Javadoc)
	 * @see blservice.LiveBLService#getHomeAbbr()
	 */
	@Override
	public String getHomeAbbr() {
		return "CLE";
	}

	/* (non-Javadoc)
	 * @see blservice.LiveBLService#getTextLive()
	 */
	@Override
	public ArrayList<String> getTextLive() {
		ArrayList<String>text = new ArrayList<String>();
		text.add("第四小节结束");
		text.add("48:00;勇士;艾弗森扣篮球进;0-4");
		text.add("第三小节结束");
		text.add("33:00;骑士;詹姆斯扣篮球进;0-4");
		text.add("骑士换人");
		text.add("第二小节结束");
		text.add("20:00;勇士;詹姆斯扣篮球进;0-4");
		text.add("18:00;勇士;詹姆斯扣篮球进;0-4");
		text.add("第一小节结束");
		text.add("08:00;勇士;詹姆斯扣篮球进;0-4");
		text.add("01:36;骑士;詹姆斯罚球不中;0-2");
		return text;
	}

	/* (non-Javadoc)
	 * @see blservice.LiveBLService#getRoadPlayerRecords()
	 */
	@Override
	public ArrayList<LivePlayerVO> getRoadPlayerRecords() {
		ArrayList<LivePlayerVO> vos = new ArrayList<LivePlayerVO>();
		vos.add(new LivePlayerVO("张三", "Kobe Byrant", "F", true, "35:4", 4, 5, 4, 5, 4, 5, 6, 7, 8, 9, 1, 1, 2, 3));
		return vos;
	}

	/* (non-Javadoc)
	 * @see blservice.LiveBLService#getHomePlayerRecords()
	 */
	@Override
	public ArrayList<LivePlayerVO> getHomePlayerRecords() {
		ArrayList<LivePlayerVO> vos = new ArrayList<LivePlayerVO>();
		vos.add(new LivePlayerVO("李四", "LeBron James", "F", true, "35:4", 4, 15, 14, 15, 14, 15, 16, 17, 8, 9, 1, 1, 2, 3));
		return vos;
	}

	/* (non-Javadoc)
	 * @see blservice.LiveBLService#getRoadScores()
	 */
	@Override
	public ArrayList<Integer> getRoadScores() {
		ArrayList<Integer> integers = new ArrayList<Integer>();
		integers.add(20);
		integers.add(11);
		integers.add(23);
//		integers.add(54);
//		integers.add(107);
		return integers;
	}

	/* (non-Javadoc)
	 * @see blservice.LiveBLService#getHomeScores()
	 */
	@Override
	public ArrayList<Integer> getHomeScores() {
		ArrayList<Integer> integers = new ArrayList<Integer>();
		integers.add(15);
		integers.add(22);
		integers.add(21);
//		integers.add(58);
//		integers.add(116);
		return integers;
	}

	/* (non-Javadoc)
	 * @see blservice.LiveBLService#getCurrentSectionCount()
	 */
	@Override
	public int getCurrentSectionCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	/* (non-Javadoc)
	 * @see blservice.LiveBLService#getHomeFiveArgs()
	 */
	@Override
	public double[] getHomeFiveArgs() {
		return new double[] {5,6,7,0.5,0.7};
	}

	/* (non-Javadoc)
	 * @see blservice.LiveBLService#getroadFiveArgs()
	 */
	@Override
	public double[] getRoadFiveArgs() {
		return new double[] {7,8,9,0.5,0.7};
	}

}
