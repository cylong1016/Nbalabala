package test;

import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.print.Printable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberInputStream;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import po.PlayerProfileVO;
import po.PlayerSeasonPO;
import autotest.playertest.PlayerSimpleVO;
import bl.playerseasonbl.PlayerSeasonAnalysis;
import utility.Constants;
import data.playerdata.PlayerImageCache;
import data.seasondata.SeasonData;
import enums.PlayerAllSortBasis;
import enums.PlayerAvgSortBasis;
import enums.SortOrder;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月6日  下午10:08:32
 */
public class PerformanceTest {
	
	/** 全部球员基本信息 */
	private static HashMap<String, PlayerSimpleVO> players = new HashMap<String, PlayerSimpleVO>();

	/** 存储球员信息的文件夹 */
	private static final String INFO_Path = Constants.dataSourcePath + "players/info/";
	
	@Pie
	public void loadPlayers() {
		PlayerSeasonAnalysis analysis = new PlayerSeasonAnalysis();
		ArrayList<PlayerSeasonPO> vos = analysis.getResortedPlayersAllData(PlayerAllSortBasis.USE_PERCENT, SortOrder.AS);
		for (int i=0;i<2;i++) {
			analysis.getResortedPlayersAllData(PlayerAllSortBasis.USE_PERCENT, SortOrder.AS);
			analysis.getResortedPlayersAllData(PlayerAllSortBasis.GMSC, SortOrder.DE);
			analysis.getResortedPlayersAvgData(PlayerAvgSortBasis.ASSIST_PERCENT, SortOrder.AS);
			analysis.getResortedPlayersAvgData(PlayerAvgSortBasis.OFFENSIVE_REBOUND_PERCENT, SortOrder.DE);
			for (PlayerSeasonPO vo : vos) {
				vo.update();
			}
		}
	}
		
		
		
}
