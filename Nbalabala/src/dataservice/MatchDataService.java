package dataservice;

import java.util.ArrayList;

import vo.MatchDetailVO;
import vo.MatchPlayerVO;
import vo.MatchProfileVO;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月19日  下午6:16:00
 */
public interface MatchDataService {

	/** 根据赛季以及日期返回符合的比赛简报，season形如13-14,date形如01-01和12-12 */
	public abstract ArrayList<MatchProfileVO> getMatchProfileBySeasonAndDate(
			String season, String date);

	/** 根据参赛队伍返回比赛简报，team形如ABC-DEF */
	public abstract ArrayList<MatchProfileVO> getMatchProfileByTeam(String team);

	/** 通过比赛文件名返回详情 */
	public abstract MatchDetailVO getMatchDetailByFileName(String fileName);

	/** 通过运动员名字返回其全部比赛记录 */
	public abstract ArrayList<MatchPlayerVO> getMatchRecordByPlayerName(
			String playerName);

}