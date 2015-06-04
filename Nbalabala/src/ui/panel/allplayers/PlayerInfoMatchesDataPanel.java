package ui.panel.allplayers;

import java.util.ArrayList;

import po.MatchPlayerPO;
import ui.common.panel.Panel;
import ui.common.table.BottomScrollPane;

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
		scrollPane = new OnePlayerMatchTableFactory(playerMatch).getTableScrollPane();
		scrollPane.setBounds(25, 0, 888, 270); // 表格的位置 
		this.add(scrollPane);
	}

}
