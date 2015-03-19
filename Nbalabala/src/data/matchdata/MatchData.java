package data.matchdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import dataservice.MatchDataService;
import enums.TeamState;
import utility.Utility;
import vo.MatchDetailVO;
import vo.MatchPlayerVO;
import vo.MatchProfileVO;

/**
 * 读取比赛信息，检索并返回符合条件的比赛信息的类
 * @author Issac Ding
 * @version 2015年3月18日  上午10:34:35
 */
public class MatchData implements MatchDataService {
	
	/**
	 * @see dataservice.MatchDataService#getMatchProfileBySeasonAndDate(java.lang.String, java.lang.String)
	 */
	@Override
	public ArrayList<MatchProfileVO> getMatchProfileBySeasonAndDate(String season, String date) {
		
		File[] files = Utility.getSortedMatchFiles();
		
		ArrayList<MatchProfileVO> result = new ArrayList<MatchProfileVO>();
		String keyword = season + "_" + date;

		try {
			for(File file : files) {
				
				if ( ! file.getName().contains(keyword)) continue;
				
				BufferedReader br = new BufferedReader(new FileReader(file));
				String [] profile = br.readLine().split(";");
				MatchProfileVO matchProfileVO = new MatchProfileVO(season, profile[0], profile[1], 
						profile[2], br.readLine());
				result.add(matchProfileVO);
				br.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @see dataservice.MatchDataService#getMatchProfileByTeam(java.lang.String)
	 */
	@Override
	public ArrayList<MatchProfileVO> getMatchProfileByTeam(String team) {
		
		File[] files = Utility.getSortedMatchFiles();
		
		ArrayList<MatchProfileVO> result = new ArrayList<MatchProfileVO>();

		try {
			for(File file : files) {
				
				if ( ! file.getName().contains(team)) continue;
				
				String season = file.getName().split("_")[0];
				
				BufferedReader br = new BufferedReader(new FileReader(file));
				String [] profile = br.readLine().split(";");
				MatchProfileVO matchProfileVO = new MatchProfileVO(season, profile[0], profile[1],
						profile[2], br.readLine());
				result.add(matchProfileVO);
				br.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @see dataservice.MatchDataService#getMatchDetailByFileName(java.lang.String)
	 */
	@Override
	public MatchDetailVO getMatchDetailByFileName(String fileName) {
		
		String season = fileName.split("_")[0];
		File file = new File(Utility.matchPath + fileName);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String [] profile = br.readLine().split(";");
			MatchProfileVO matchProfileVO = new MatchProfileVO(season, profile[0], 
					profile[1], profile[2], br.readLine());
			
			ArrayList<MatchPlayerVO> homePlayers = new ArrayList<MatchPlayerVO>();
			br.readLine();
			String s = null;
			while (true) {
				s = br.readLine();
				if (s.length() < 5) break;
				MatchPlayerVO homeVo = new MatchPlayerVO(s, file, TeamState.HOME);
				homePlayers.add(homeVo);
			}
			
			ArrayList<MatchPlayerVO> roadPlayers = new ArrayList<MatchPlayerVO>();
			while ((s = br.readLine()) != null) {
				MatchPlayerVO roadVo = new MatchPlayerVO(s, file, TeamState.ROAD);
				roadPlayers.add(roadVo);
			}
			
			br.close();
			return new MatchDetailVO(matchProfileVO, homePlayers, roadPlayers);
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * @see dataservice.MatchDataService#getMatchRecordByPlayerName(java.lang.String)
	 */
	@Override
	public ArrayList<MatchPlayerVO> getMatchRecordByPlayerName(String playerName) {
		File [] files = Utility.getSortedMatchFiles();
		
		ArrayList<MatchPlayerVO> result = new ArrayList<MatchPlayerVO>();
		
		FILELOOP: for (File file : files) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				br.readLine();
				br.readLine();
				br.readLine();
				String line;
				while (true) {
					line = br.readLine();
					if (line.length() < 5) break;
					if (line.contains(playerName)) {
						result.add(new MatchPlayerVO(line, file, TeamState.HOME));
						br.close();
						continue FILELOOP;
					}
				}
				while ((line = br.readLine()) != null) {
					if (line.contains(playerName)) {
						result.add(new MatchPlayerVO(line, file, TeamState.ROAD));
						break;
					}
				}
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
