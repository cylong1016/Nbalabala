package ui.panel.gamedata;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import po.MatchPlayerPO;
import ui.UIConfig;
import ui.common.button.TextButton;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import utility.Constants;
import vo.MatchDetailVO;

/**
 * 
 * @author lsy
 * @version 2015年6月16日  下午5:47:06
 */
public class TechPanel extends BottomPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = -7521043366433799138L;
	private TextButton team1,team2;
	private static String teamAbbr1,teamAbbr2;
	private TechTable table;
	private ArrayList<MatchPlayerPO> homeplayers,roadplayers;

	public TechPanel(MatchDetailVO vo,BottomPanel panel){
		super("images2.0/games/techBG.png");
		homeplayers = vo.getHomePlayers();
		roadplayers = vo.getRoadPlayers();
		String[] team = vo.getProfile().getTeam().split("-");
		teamAbbr1 = team[0];
		teamAbbr2 =team[1];
		addButton();
		table = new TechTable(homeplayers,panel);
		setTable(homeplayers);
		this.setBounds(25, 249, 948, 350);
		this.repaint();
	}
	
	public void addButton(){
		team1 = new TextButton(794 - 26,5,74,31,Constants.translateTeamAbbr(teamAbbr1));
		team2 = new TextButton(868 - 25,5,74,31,Constants.translateTeamAbbr(teamAbbr2));
		this.add(team1);
		this.add(team2);
		setEffect(team1);
		team1.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				setEffect(team1);
				team2.back();
				updateTable(homeplayers);
			}
		});
		team2.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				setEffect(team2);
				team1.back();
				updateTable(roadplayers);
			}
		});
	}

	public void setEffect(TextButton button) {
		button.setOpaque(true);
		button.setBackground(UIConfig.BUTTON_COLOR);
		button.setForeground(Color.white);
	}
	
	private BottomScrollPane scroll;

	public void setTable(ArrayList<MatchPlayerPO> players) {
		scroll = new BottomScrollPane(table);
		table.setHeaderColorAndFont();
		table.setHeaderHeight(UIConfig.TABLE_HEADER_HEIGHT);
		table.setWidth(new int[] {33, 130, 51, 51, 62, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51,33});
		scroll.setBounds(UIConfig.RELA_X,41,table.setTableWidth(300, table.getRowCount()),300);
		this.add(scroll);
	}
	
	public void updateTable(ArrayList<MatchPlayerPO> players){
		table.setTable(players);
	}
}
