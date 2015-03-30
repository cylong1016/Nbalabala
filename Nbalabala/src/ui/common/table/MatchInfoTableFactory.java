package ui.common.table;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.common.UserMouseAdapter;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import utility.Constants;
import vo.MatchProfileVO;

/**
 * 
 * @author Issac Ding
 * @version 2015年3月30日  下午9:08:41
 */
public class MatchInfoTableFactory {
	
	/** 两个队伍每节的比分 */
	String[] score1 = {"0", "0", "0", "0", "0", "0", "0"};
	String[] score2 = {"0", "0", "0", "0", "0", "0", "0"};
	
	/** 两支球队缩写 */
	String[] teamShort;
	/** 两支球队比赛总分 */
	String[] scoreAll;
	/** 每节比分 */
	String[] eachScore;
	/** 球队全称 */
	String[] teamLong;
	
	MatchInfoTable table = null;
	BottomScrollPane scroll = null;
	ArrayList<MatchProfileVO> matchProfile;
	
	public MatchInfoTableFactory(final ArrayList<MatchProfileVO> matchProfile,
			final BottomPanel panel,final MainController controller) {
		this.matchProfile = matchProfile;
		loadData(matchProfile);
		setAction(panel, controller);
		setLook();
	}
	
	public BottomScrollPane getTableScrollPanel(){
		return scroll;
	}
	
	public void loadData(final ArrayList<MatchProfileVO> matchProfile) {
		int gameSum = matchProfile.size();
		String[][]rowData = new String[2 * gameSum][MatchInfoTable.columnNames.length];
		for(int j = 0; j < gameSum * 2 ; j = j + 2) {
			MatchProfileVO pro = matchProfile.get(j / 2);
			score1 = new String[]{"0", "0", "0", "0", "0", "0", "0"};
			score2 = new String[]{"0", "0", "0", "0", "0", "0", "0"};
			analyzeVO(pro);
			rowData[j][0] = pro.getSeason() + "賽季";
			rowData[j+1][0] = pro.getTime();
			rowData[j][1] = teamLong[0];
			rowData[j+1][1] = teamLong[1];
			rowData[j][9] = scoreAll[0];
			rowData[j + 1][9] = scoreAll[1];
			rowData[j][10]="数据统计";
			addScore(rowData,j / 2);
		}
		table = new MatchInfoTable(rowData);
	}
	
	public void setAction(final BottomPanel panel,final MainController controller) {
		try{
			table.addMouseListener(new UserMouseAdapter(){
				public void mouseClicked(MouseEvent e){
					if (e.getClickCount() < 2) return;
					int rowI  = table.rowAtPoint(e.getPoint());// 得到table的行号
					if ( rowI > -1){
						controller.toOneGamePanel(panel, matchProfile.get(rowI/2), panel);
					}
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void setLook() {
		int scores = 70;
		int adds = 80;
		table.setWidth(new int[]{120,scores, scores, scores, scores, adds, adds, adds, scores, 89, 89});
		scroll = new BottomScrollPane(table);
		scroll.setLocation(57, 240);
	}
	
	/**
	 * 拆解传回来的vo
	 * @author lsy
	 * @version 2015年3月22日 上午12:04:52
	 */
	private void analyzeVO(MatchProfileVO proVOArray) {
		teamLong = new String[2];
		teamShort = proVOArray.getTeam().split("-");
		teamLong[0] = Constants.translateTeamAbbr(teamShort[0]);
		teamLong[1] = Constants.translateTeamAbbr(teamShort[1]);
		scoreAll = proVOArray.getScore().split("-");// 两支球队比赛总分
		eachScore = proVOArray.getEachSectionScore().split(";");
		int eachlth = eachScore.length;
		for(int i = 0; i < eachlth; i++) {
			String[] scoreTemp = eachScore[i].split("-");
			score1[i] = scoreTemp[0];
			score2[i] = scoreTemp[1];
		}
	}
	
	private void addScore(String[][] rowData,int line) {
		for(int i = 0; i < 7; i++) {
			rowData[2 * line ][i + 2] = score1[i];
			rowData[2 * line + 1][i + 2] = score2[i];
		}
	}
	

}
