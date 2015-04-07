package data.teamdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import utility.Constants;
import vo.TeamProfileVO;
import dataservice.TeamDataService;
import enums.ScreenDivision;

/**
 *	
 * @author cylong
 * @version 2015年3月13日 下午8:36:13
 */
public class TeamData implements TeamDataService{

	/** 全部球队信息 */
	private static HashMap<String, TeamProfileVO> teams = new HashMap<String, TeamProfileVO>();

	/** 存储球队信息的文件 */
	private static final String PATH = Constants.dataSourcePath + "teams/teams";

	public TeamData() {
		if (teams.size() == 0) {
			loadTeams();
		}
	}

	/**
	 * 加载全部球队信息
	 * @author cylong
	 * @version 2015年3月13日 下午9:05:33
	 */
	private void loadTeams() {
		File file = new File(PATH);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			String line = null;
			while((line = br.readLine()) != null) {
				line = line.replace("║", "");
				if (!Character.isLetterOrDigit(line.charAt(0))) {
					continue;
				}
				String[] info = line.split("│");
				TeamProfileVO team = new TeamProfileVO(info[0].trim(), info[1], info[2].trim(),  
						info[4].trim(), info[5].trim(), info[6]);
				teams.put(team.getAbbr(), team);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}


	/**
	 * @see dataservice.TeamDataService#getTeamProfileByAbbr(java.lang.String)
	 */
	@Override
	public TeamProfileVO getTeamProfileByAbbr(String abbr) {
		return teams.get(abbr);
	}
	
	// 以下两个方法主要用来根据球队缩写返回东/西或者具体赛区，主要用于球员的条件筛选
	
	/**
	 * @see dataservice.TeamDataService#getAreaByAbbr(java.lang.String)
	 */
	@Override
	public ScreenDivision getAreaByAbbr(String abbr) {
		return teams.get(abbr).getArea();
	}
	
	/**
	 * @see dataservice.TeamDataService#getDivisionByAbbr(java.lang.String)
	 */
	@Override
	public ScreenDivision getDivisionByAbbr(String abbr) {
		return teams.get(abbr).getDivision();
	}
	
	public static void clear() {
		teams.clear();
	}
}
