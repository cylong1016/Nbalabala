package ui;

import java.awt.Dimension;
import java.util.ArrayList;

import ui.common.frame.Frame;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.panel.main.MainPanel;
import ui.panel.playerData.PlayerDataPanel;
import vo.PlayerSeasonVO;

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
		ExpPlayerBasicTable table = new ExpPlayerBasicTable();
//		table.getColumnModel().getColumn(1).setHeaderValue("hahaha"); 就是想试试 能不能改变表头名字
		BottomScrollPane pane = new BottomScrollPane(table);	//原来的
		pane.setLocation(57, 260);
		//可以直接在此处用setBounds修改表格的位置大小
		this.add(pane);
	}
	
	
	
	public static void main(String[]args){
		new ExpPlayerData();
	}

}
