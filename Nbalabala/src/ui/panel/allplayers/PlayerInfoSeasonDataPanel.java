package ui.panel.allplayers;

import po.PlayerSeasonPO;
import po.TeamSeasonPO;
import ui.UIConfig;
import ui.common.label.NavLabel;
import ui.common.panel.Panel;
import ui.common.table.BottomScrollPane;
import utility.Constants;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月27日  下午6:15:35
 */
public class PlayerInfoSeasonDataPanel extends Panel{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -131390925768195246L;
	
	private OnePlayerDataTable seasonDataTable = new OnePlayerDataTable();
	
	public PlayerInfoSeasonDataPanel() {
		
		NavLabel navLabel = new NavLabel(Constants.titleAvgData);
		navLabel.setLocation(UIConfig.RELA_X, 16);
		this.add(navLabel);
		
		BottomScrollPane scrollPane = new BottomScrollPane(seasonDataTable);
		seasonDataTable.setHeaderColorAndFont();
		seasonDataTable.setHeaderHeight(UIConfig.TABLE_HEADER_HEIGHT);
		seasonDataTable.setRowHeight(0, 25);
		seasonDataTable.setRowHeight(2, 25);
		seasonDataTable.setRowHeight(4, 25);
		
		seasonDataTable.setWidth(new int[] {78, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54});
		
		scrollPane.setBounds(UIConfig.RELA_X, navLabel.getHeight()+16, UIConfig.TABLE_WID, UIConfig.TABLE_H);
		this.add(scrollPane);
	}
	
	public void update(String season, PlayerSeasonPO seasonVO, TeamSeasonPO teamPO) {
		seasonDataTable.setVO(season, seasonVO, teamPO);
	}
}
