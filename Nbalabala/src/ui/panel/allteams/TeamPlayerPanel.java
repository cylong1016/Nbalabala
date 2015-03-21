package ui.panel.allteams;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.UIConfig;
import ui.controller.MainController;
import vo.PlayerProfileVO;
import vo.TeamDetailVO;
import bl.teamquerybl.TeamQuery;
import blservice.TeamQueryBLService;

/**
 * 球队阵容界面
 * @author lsy
 * @version 2015年3月21日  上午12:28:35
 */
public class TeamPlayerPanel extends TeamSeasonPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = 5997192605829491904L;
	TeamQueryBLService teamQuery = new TeamQuery();
	TeamButton teamButton;

	public TeamPlayerPanel(MainController controller, String url,TeamButton teamButton) {
		super(controller, url,teamButton);
		this.teamButton = teamButton;
		TeamDetailVO teamDetail = teamQuery.getTeamDetailByAbbr(teamButton.team);
		ArrayList<PlayerProfileVO> players = teamDetail.getPlayers();
		//TODO 把球员信息分解下放到表格里~
	}
	
	public void addListener(){
		MouListener mou1 = new MouListener();
		for(int i = 0; i < 3; i++) {
			button[i].addMouseListener(mou1);
		}
	}
	
	class MouListener extends MouseAdapter{
		 public void mousePressed(MouseEvent e) {
			 if(e.getSource() == button[0]){
				 controller.toTeamSeasonPanel(TeamPlayerPanel.this,teamButton);
			 }else if(e.getSource() == button[1]){
				 return;
			 }else if(e.getSource() == button[2]){
				 controller.toTeamGamePanel(TeamPlayerPanel.this,teamButton);
			 }
		 }
	}
	
	public void setEffect() {
		button[1].setOpaque(true);
		button[1].setBackground(UIConfig.BUTTON_COLOR);
		button[1].setForeground(Color.white);
}

}
