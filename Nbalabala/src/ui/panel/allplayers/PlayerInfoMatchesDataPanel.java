package ui.panel.allplayers;

import java.util.ArrayList;

import po.MatchPlayerPO;
import ui.UIConfig;
import ui.common.label.NavLabel;
import ui.common.panel.Panel;
import ui.common.table.BottomScrollPane;
import utility.Constants;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月27日  下午6:16:55
 */
public class PlayerInfoMatchesDataPanel extends Panel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3026360195428120633L;
	private BottomScrollPane scrollPane;
	
	public PlayerInfoMatchesDataPanel() {
		
	}
	
	public void updateContent(ArrayList<MatchPlayerPO> playerMatch) {
		
		if (scrollPane != null) {
			remove(scrollPane);
		}
		
		NavLabel navLabel = new NavLabel(Constants.gameLog);
		navLabel.setLocation(UIConfig.RELA_X, 16);
		this.add(navLabel);
		
		scrollPane = new OnePlayerMatchTableFactory(playerMatch).getTableScrollPane();
		scrollPane.setBounds(UIConfig.RELA_X, 16+navLabel.getHeight(), UIConfig.TABLE_WID, 286); // 表格的位置 
		this.add(scrollPane);
	}

}
