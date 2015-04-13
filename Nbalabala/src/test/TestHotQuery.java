package test;

import java.util.ArrayList;

import org.junit.Test;

import vo.HotFastestPlayerVO;
import vo.HotSeasonPlayerVO;
import vo.HotSeasonTeamVO;
import bl.hotquerybl.HotQuery;
import enums.HotFastestPlayerProperty;
import enums.HotSeasonPlayerProperty;
import enums.HotSeasonTeamProperty;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月8日  下午10:48:20
 */
public class TestHotQuery {

	@Test
	public void testHotFastestPlayers() {
		HotQuery query = new HotQuery();
		ArrayList<HotFastestPlayerVO> list = query.getHotFastestPlayers(HotFastestPlayerProperty.ASSIST_AVG);
		for (HotFastestPlayerVO  vo: list) {
			System.out.println(vo.getName());
			System.out.println(vo.getFormerFiveAvg());
		}
	}
	
	@Test
	public void testHotSeasonPlayers() {
		HotQuery query = new HotQuery();
		ArrayList<HotSeasonPlayerVO> list = query.getHotSeasonPlayers(HotSeasonPlayerProperty.SCORE_AVG);
		for (HotSeasonPlayerVO  vo: list) {
			System.out.println(vo.getName());
			System.out.println(vo.getProperty());
		}
	}
	
	@Test
	public void testHotSeasonTeams() {
		HotQuery query = new HotQuery();
		ArrayList<HotSeasonTeamVO> list = query.getHotSeasonTeams(HotSeasonTeamProperty.REBOUND_AVG);
		for (HotSeasonTeamVO  vo: list) {
			System.out.println(vo.getAbbr());
			System.out.println(vo.getProperty());
		}
	}
}
