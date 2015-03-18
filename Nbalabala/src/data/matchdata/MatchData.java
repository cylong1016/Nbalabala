package data.matchdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import vo.MatchDetailVO;
import vo.MatchPlayerVO;
import vo.MatchProfileVO;

/**
 * 读取比赛信息，检索并返回符合条件的比赛信息的类
 * @author Issac Ding
 * @version 2015年3月18日  上午10:34:35
 */
public class MatchData {
	
	/** 存储比赛信息的文件夹 */
	private String path = "NBAdata/matches/";

	/** 根据日期或参赛队伍返回符合的比赛简报，date形如01-01和12-12，队伍形如ABC-DEF */
	public ArrayList<MatchProfileVO> getMatchByKeyword(String keyword) {
		File dir = new File(path);
		File[] files = dir.listFiles();
		
		ArrayList<MatchProfileVO> result = new ArrayList<MatchProfileVO>();

		try {
			for(File file : files) {
				
				if ( ! file.getName().contains(keyword)) continue;
				
				BufferedReader br = new BufferedReader(new FileReader(file));
				String [] profile = br.readLine().split(";");
				MatchProfileVO matchProfileVO = new MatchProfileVO(profile[0], profile[1], profile[2], 
						br.readLine());
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

	/** 通过比赛文件名返回详情 */
	public MatchDetailVO getMatchDetailByFileName(String fileName) {
		File file = new File(path+fileName);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String [] profile = br.readLine().split(";");
			MatchProfileVO matchProfileVO = new MatchProfileVO(profile[0], profile[1], profile[2], 
					br.readLine());
			
			ArrayList<MatchPlayerVO> homePlayers = new ArrayList<MatchPlayerVO>();
			br.readLine();
			String s = null;
			while (true) {
				s = br.readLine();
				if (s.length() < 5) break;
				MatchPlayerVO homeVo = new MatchPlayerVO(s);
				homePlayers.add(homeVo);
			}
			
			ArrayList<MatchPlayerVO> roadPlayers = new ArrayList<MatchPlayerVO>();
			while ((s = br.readLine()) != null) {
				MatchPlayerVO roadVo = new MatchPlayerVO(s);
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
}
