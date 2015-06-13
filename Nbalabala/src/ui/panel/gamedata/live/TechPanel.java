package ui.panel.gamedata.live;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.UIConfig;
import ui.common.button.TextButton;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import utility.Constants;
import vo.LivePlayerVO;

/**
 * 技术统计界面
 * @author lsy
 * @version 2015年6月9日  上午12:04:04
 */
public class TechPanel extends BottomPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = -5091667442846836452L;
	private ArrayList<LivePlayerVO> homeplayers,roadplayers;
	private LiveTechTable table;
	private TextButton team1,team2;
	private String teamAbbr1,teamAbbr2;
	public static int CURRENTI;
	
	public TechPanel(String teamAbbr1,String teamAbbr2,ArrayList<LivePlayerVO> homeplayers,ArrayList<LivePlayerVO> roadplayers,BottomPanel panel){
		super("images2.0/games/techBG.png");
		this.setBounds(27, 249, 948, 351);
		this.homeplayers = homeplayers;
		this.roadplayers = roadplayers;
		this.teamAbbr1 = teamAbbr1;
		this.teamAbbr2 = teamAbbr2;
		addButton();
		table = new LiveTechTable(homeplayers,panel);
		setTable(homeplayers);
	}
	
	public void addButton(){
		team1 = new TextButton(845,5,50,30,Constants.translateTeamAbbr(teamAbbr1));
		team2 = new TextButton(899,5,50,30,Constants.translateTeamAbbr(teamAbbr2));
		this.add(team1);
		this.add(team2);
		setEffect(team1);
		team1.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				setEffect(team1);
				team2.back();
				updateTable(homeplayers);
				CURRENTI = 0;
			}
		});
		team2.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				setEffect(team2);
				team1.back();
				updateTable(roadplayers);
				CURRENTI = 1;
			}
		});
	}

	public void setEffect(TextButton button) {
		button.setOpaque(true);
		button.setBackground(UIConfig.BUTTON_COLOR);
		button.setForeground(Color.white);
	}
	
	private BottomScrollPane scroll;

	public void setTable(ArrayList<LivePlayerVO> players) {
		scroll = new BottomScrollPane(table);
		scroll.setBounds(5,35,940,280);
		this.add(scroll);
	}
	
	public void updateTable(ArrayList<LivePlayerVO> players){
		table.setTable(players);
	}
	
	public void updateHomeTable(ArrayList<LivePlayerVO> homeplayers){
		this.homeplayers = homeplayers;
		table.setTable(homeplayers);
	}
	
	public void updateRoadTable(ArrayList<LivePlayerVO> roadplayers){
		this.roadplayers = roadplayers;
		table.setTable(roadplayers);
	}
	
}
