package data.teamdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import po.TeamPO;
import dataservice.teamdataservice.TeamDataService;

/**
 * @see dataservice.teamdataservice.TeamDataService
 * @author cylong
 * @version 2015年3月13日 下午8:36:13
 */
public class TeamData implements TeamDataService {


	/** 全部球队信息 */
	private HashMap<String, TeamPO> teams = new HashMap<String, TeamPO>();

	/** 存储球队信息的文件 */
	private String path = "NBAdata/teams/teams";

	public TeamData() {
		loadTeams();
		System.out.println(teams.size());
	}
	
	public static void main(String[]args){
		new TeamData();
	}

	/**
	 * 加载全部球队信息
	 * @author cylong
	 * @version 2015年3月13日  下午9:05:33
	 */
	private void loadTeams() {
		File file = new File(path);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = br.readLine()) != null) {
				line = line.replace("║", "");
				if (!Character.isLetter(line.charAt(0))) {
					continue;
				}
				String[] info = line.split("│");
				TeamPO team = new TeamPO(info[0], info[1], info[2], info[3], info[4], info[5], info[6]);
				teams.put(team.getAbbr(), team);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see dataservice.teamdataservice.TeamDataService#findTeam(java.lang.String)
	 */
	@Override
	public TeamPO findTeam(String name) {
		return teams.get(name);
	}

}
