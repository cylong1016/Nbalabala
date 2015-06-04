package ui;

import java.awt.Font;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JLabel;

import po.PlayerSeasonPO;
import ui.common.frame.Frame;
import ui.common.table.BottomScrollPane;
import ui.panel.playerData.PlayerDataPanel;
import ui.panel.teamdata.AllTeamSeasonTable;
import bl.teamseasonbl.TeamSeasonAnalysis;
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
	
	public void createTable(ArrayList<PlayerSeasonPO> playerRecords){
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
//		
//		new ExpPlayerData();
//		try {
//	        java.io.FileInputStream fi = new java.io.FileInputStream(new File("Hiragino.otf"));
//	        java.io.BufferedInputStream fb = new java.io.BufferedInputStream(fi);
//	        Font nf = Font.createFont(Font.TRUETYPE_FONT, fb);
//	        nf = nf.deriveFont(Font.BOLD, 10);
//	        System.out.println(nf.getFontName());
//	        System.out.println(nf.getSize());
//	      }
//	      catch (Exception e) {
//	        System.out.println(e.getMessage());
//	      }
		
		JLabel label = new JLabel("hkasjdflkaj");
		System.out.println(label.getPreferredSize());
	    
	}

}
