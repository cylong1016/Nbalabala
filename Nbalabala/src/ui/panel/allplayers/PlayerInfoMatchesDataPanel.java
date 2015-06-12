package ui.panel.allplayers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import po.MatchPlayerPO;
import ui.UIConfig;
import ui.common.label.NavLabel;
import ui.common.panel.Panel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
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
	private ArrayList<MatchPlayerPO> playerMatch;
	private BottomTable table;
	
	
	public PlayerInfoMatchesDataPanel() {
		
	}
	
	public void updateContent(ArrayList<MatchPlayerPO> playerMatch) {
		this.playerMatch = playerMatch;
		if (scrollPane != null) {
			remove(scrollPane);
		}
		
		NavLabel navLabel = new NavLabel(Constants.gameLog);
		navLabel.setLocation(UIConfig.RELA_X, 16);
		this.add(navLabel);
		
		OnePlayerMatchTableFactory one = new OnePlayerMatchTableFactory(playerMatch);
		scrollPane = one.getTableScrollPane();
		table = one.getTable();
		scrollPane.setBounds(UIConfig.RELA_X, 16+navLabel.getHeight(),one.setTableWidth(UIConfig.TABLE_H), UIConfig.TABLE_H); // 表格的位置
		this.add(scrollPane);
		
		one.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() < 2) return;
				int row = table.rowAtPoint(e.getPoint());
				if (row >= 0) {
					int matchID = PlayerInfoMatchesDataPanel.this.playerMatch.get(row).getMatchID();
					MainController.toGameDetailPanel(matchID, (Panel)(getParent()));
				}
			}
		});
	}
}
