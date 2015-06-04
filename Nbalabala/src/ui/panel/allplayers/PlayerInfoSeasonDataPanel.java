package ui.panel.allplayers;

import po.PlayerSeasonPO;
import ui.common.panel.Panel;
import ui.common.table.BottomScrollPane;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月27日  下午6:15:35
 */
public class PlayerInfoSeasonDataPanel extends Panel{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -131390925768195246L;
	//TODO 贡献图还没做出来
	
	private OnePlayerDataTable seasonDataTable = new OnePlayerDataTable();
	
	public PlayerInfoSeasonDataPanel() {
		BottomScrollPane scrollPane = new BottomScrollPane(seasonDataTable);
		scrollPane.setBounds(25, 25, 888, 160);
		this.add(scrollPane);
	}
	
	public void update(String season, PlayerSeasonPO seasonVO) {
		seasonDataTable.setVO(season, seasonVO);
	}
}
