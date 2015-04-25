package ui;

import java.util.ArrayList;

import ui.common.frame.Frame;
import ui.common.table.BottomScrollPane;
import ui.panel.playerData.AllPlayerSeasonTable;
import ui.panel.playerData.PlayerDataPanel;
import ui.panel.teamdata.AllTeamSeasonTable;
import vo.PlayerSeasonVO;
import bl.playerseasonbl.PlayerSeasonAnalysis;
import bl.teamseasonbl.TeamAllSorter;
import bl.teamseasonbl.TeamSeasonAnalysis;
import enums.AllPlayerSeasonTableCategory;
import enums.AllTeamSeasonTableCategory;
import enums.TotalOrAvg;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月22日  下午6:47:27
 */
public class ExpPlayerData extends PlayerDataPanel{
	
	Frame frame;

	public ExpPlayerData(){
		super(UIConfig.IMG_PATH + "playerData/playerDataBG.png");
		frame = new Frame();
		frame.setPanel(this);
		frame.start();
	}
	
	public void createTable(ArrayList<PlayerSeasonVO> playerRecords){
		TeamSeasonAnalysis seasonAnalysis = new TeamSeasonAnalysis();
		AllTeamSeasonTable table = new AllTeamSeasonTable(seasonAnalysis, seasonAnalysis.getTeamDataSortedByName("13-14"),
				AllTeamSeasonTableCategory.DEFENSIVE,
				TotalOrAvg.TOTAL);
		BottomScrollPane pane = new BottomScrollPane(table);	//原来的
		pane.setLocation(57, 260);
		//可以直接在此处用setBounds修改表格的位置大小
		this.add(pane);
	}
	
	
	
	public static void main(String[]args){
		
		new ExpPlayerData();
	}

}
