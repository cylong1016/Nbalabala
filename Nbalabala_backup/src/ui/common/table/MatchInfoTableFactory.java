package ui.common.table;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.common.UserMouseAdapter;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import utility.Constants;
import vo.MatchProfileVO;

/**
 * 将通过比赛数据生成比赛信息表格的逻辑封装以便复用
 * @author Issac Ding
 * @version 2015年3月30日  下午9:08:41
 */
public class MatchInfoTableFactory {
	
	private MatchInfoTable table = null;
	private ArrayList<MatchProfileVO> matchProfile;
	
	public MatchInfoTableFactory(final ArrayList<MatchProfileVO> matchProfile,
			final BottomPanel panel) {
		this.matchProfile = matchProfile;
		loadData(matchProfile);
		setAction(panel);
		setLook();
	}
	
	public BottomScrollPane getTableScrollPanel(){
		BottomScrollPane scroll = new BottomScrollPane(table);
		scroll.setLocation(57, 240);
		return scroll;
	}
	
	public void loadData(final ArrayList<MatchProfileVO> matchProfile) {
		int gameSum = matchProfile.size();
		String[][]rowData = new String[2 * gameSum][MatchInfoTable.COLUMN_NAMES.length];
		for(int j = 0; j < gameSum * 2 ; j = j + 2) {
			MatchProfileVO pro = matchProfile.get(j / 2);
			String [] homeScore = new String[]{"0", "0", "0", "0", "0", "0", "0"};
			String [] roadScore = new String[]{"0", "0", "0", "0", "0", "0", "0"};
			
			String [] teamFullName = new String[2];
			String [] teamAbbr = pro.getTeam().split("-");
			teamFullName[0] = Constants.translateTeamAbbr(teamAbbr[0]);
			teamFullName[1] = Constants.translateTeamAbbr(teamAbbr[1]);
			
			String [] totalScore = pro.getScore().split("-");// 两支球队比赛总分
			String [] eachScore = pro.getEachSectionScore().split(";");
			
			int sectionCount = eachScore.length;
			for(int i = 0; i < sectionCount; i++) {
				String[] scoreTemp = eachScore[i].split("-");
				homeScore[i] = scoreTemp[0];
				roadScore[i] = scoreTemp[1];
			}
			
			rowData[j][0] = pro.getSeason() + "賽季";
			rowData[j+1][0] = pro.getTime();
			rowData[j][1] = teamFullName[0];
			rowData[j+1][1] = teamFullName[1];
			rowData[j][9] = totalScore[0];
			rowData[j + 1][9] = totalScore[1];
			rowData[j][10]="数据统计";
			
			int line = j / 2;
			for(int i = 0; i < 7; i++) {
				rowData[2 * line ][i + 2] = homeScore[i];
				rowData[2 * line + 1][i + 2] = roadScore[i];
			}
		}
		table = new MatchInfoTable(rowData);
	}
	
	/** 双击查看具体比赛 */
	private void setAction(final BottomPanel panel) {
		try{
			table.addMouseListener(new UserMouseAdapter(){
				public void mouseClicked(MouseEvent e){
					if (e.getClickCount() < 2) return;
					int rowI  = table.rowAtPoint(e.getPoint());// 得到table的行号
					if (rowI > -1){
//						MainController.toOneGamePanel(matchProfile.get(rowI/2), panel);
					}
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 设置宽度 */
	private void setLook() {
		int scoresWidth = 70;
		int addsWidth = 80;
		table.setWidth(new int[]{111,scoresWidth, scoresWidth, scoresWidth, scoresWidth, addsWidth, addsWidth, addsWidth, scoresWidth, 85, 85});
	}
	
}
