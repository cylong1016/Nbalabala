package utility;

import java.sql.Date;

import enums.ScreenDivision;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月26日  下午11:55:03
 */
public interface Translater {
	
	String translateHeight(String height);
	String translateWeight(int weight);
	String translateDate(Date date);
	String translateVeteran(int fromYear);
	String translatePosition(String position);
	String translateTeamAbbr(String abbr);
	String translateTeamAbbrToLocation(String abbr);
	String translateTeamDivision(ScreenDivision division);
	String translateTeamLeague(ScreenDivision league);

}
