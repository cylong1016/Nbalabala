package bl.playerseasonbl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import vo.PlayerSeasonVO;
import blservice.PlayerSeasonBLService;
import data.seasondata.SeasonData;
import dataservice.SeasonDataService;
import enums.PlayerAllSortBasis;
import enums.PlayerAvgSortBasis;
import enums.Position;
import enums.ScreenBasis;
import enums.ScreenDivision;
import enums.SortOrder;

/**
 * 分析球员数据的类
 * @author Issac Ding
 * @version 2015年3月15日 下午2:46:44
 */
public class PlayerSeasonAnalysis implements PlayerSeasonBLService {

	private SeasonDataService seasonData = new SeasonData();

	/** 记录上一次返回给UI层，即UI层正在显示的球员列表 */
	private ArrayList<PlayerSeasonVO> currentList = seasonData.getAllPlayerSeasonData();

	/** 刚进入界面时调用此方法，得到的是全部的以名字排序的球员数据 */
	public ArrayList<PlayerSeasonVO> getAllPlayersSortedByName() {
		sortPlayersByName(currentList);
		return currentList;
	}

	/** 得到对当前总数据表重新排序后的表 */
	public ArrayList<PlayerSeasonVO> getResortedPlayersAllData(PlayerAllSortBasis basis, SortOrder order) {
		new PlayerAllSorter().sort(currentList, basis, order);
		return currentList;
	}

	/** 得到对当前总数据表重新排序后的表 */
	@Override
	public ArrayList<PlayerSeasonVO> getResortedPlayersAvgData(PlayerAvgSortBasis basis, SortOrder order) {
		new PlayerAvgSorter().sort(currentList, basis, order);
		return currentList;
	}

	/** 根据位置、地区、筛选依据，返回含有前50个记录的表 */
	public ArrayList<PlayerSeasonVO> getScreenedPlayers(Position position, ScreenDivision division, ScreenBasis basis) {

		ArrayList<PlayerSeasonVO> players = seasonData.getScreenedPlayerSeasonData(position, division);

		// 如果没有排序指标依据，那么返回所有符合位置和分区的球员，按名字为序
		if (basis == ScreenBasis.ALL) {
			sortPlayersByName(players);
			currentList = players;
			return players;
		}

		// 否则，就按指标选出前50个人
		PlayerScreener screener = new PlayerScreener();
		currentList = screener.screen(players, basis);
		return currentList;
	}

	/** 根据球员名字返回所属球队缩写 */
	public String getTeamAbbrByPlayer(String playerName) {
		return seasonData.getTeamAbbrByPlayer(playerName);
	}

	/** 根据球员名字返回其赛季数据 */
	public PlayerSeasonVO getPlayerSeasonDataByName(String playerName) {
		return seasonData.getPlayerSeasonDataByName(playerName);
	}

	/** 根据球队缩写返回其当前阵容包含的球员名单 */
	public ArrayList<String> getPlayerNamesByTeamAbbr(String abbr) {
		return seasonData.getPlayerNamesByTeamAbbr(abbr);
	}

	/** 根据名字字典顺序为球员排序 */
	private void sortPlayersByName(ArrayList<PlayerSeasonVO> players) {
		Comparator<PlayerSeasonVO> comparator = new Comparator<PlayerSeasonVO>() {

			public int compare(PlayerSeasonVO p1, PlayerSeasonVO p2) {
				return p1.getName().compareTo(p2.getName());
			}
		};
		Collections.sort(players, comparator);
	}

}
