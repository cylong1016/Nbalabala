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
		list.put("SDF", "用时");
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
		text.add("10:36;骑士;詹姆斯罚球不中;0-2");
		text.add("第一小节结束");
		text.add("11:00;勇士;詹姆斯扣篮球进;0-4");
		text.add("骑士换人");
		return text;
	}

	/* (non-Javadoc)
	 * @see blservice.LiveBLService#getRoadPlayerRecords()
	 */
	@Override
	public ArrayList<LivePlayerVO> getRoadPlayerRecords() {
		ArrayList<LivePlayerVO> vos = new ArrayList<LivePlayerVO>();
		vos.add(new LivePlayerVO("张三", "san", "F", true, "35:4", 4, 5, 4, 5, 4, 5, 6, 7, 8, 9, 1, 1, 2, 3));
		return vos;
	}

	/* (non-Javadoc)
	 * @see blservice.LiveBLService#getHomePlayerRecords()
	 */
	@Override
	public ArrayList<LivePlayerVO> getHomePlayerRecords() {
		ArrayList<LivePlayerVO> vos = new ArrayList<LivePlayerVO>();
		vos.add(new LivePlayerVO("李四", "si", "F", true, "35:4", 4, 5, 4, 5, 4, 5, 6, 7, 8, 9, 1, 1, 2, 3));
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
		return 3;
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
	public double[] getroadFiveArgs() {
		return new double[] {7,8,9,0.5,0.7};
	}

}
