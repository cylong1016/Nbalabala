package ui.panel.allteams;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.UIConfig;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import bl.teamquerybl.TeamQuery;
import blservice.TeamQueryBLService;

/**
 * 球队赛季数据
 * @author lsy
 * @version 2015年3月20日  下午11:32:02
 */
public class TeamSeasonPanel extends BottomPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = 4901174711583565164L;
	int seasonX = 672,lineupX=782,gameX=857,y = 190;
	int width1=83,width2=48,height=25;
	TeamSeasonButton[] button = new TeamSeasonButton[3];
	TeamQueryBLService teamQuery = new TeamQuery();
	MainController controller;
	TeamButton teamButton;
	
	public TeamSeasonPanel(MainController controller, String url,TeamButton teamButton) {
		super(controller,url);
		setButton();
		addButton();
		setEffect();
		addListener();
		this.controller = controller;
		this.teamButton = teamButton;
//		TeamDetailVO teamDetail = teamQuery.getTeamDetailByAbbr(teamButton.team);
		//TODO 设置表格
	}
	
	public void setButton(){
		button[0] = new TeamSeasonButton(seasonX,y,width1,height,"赛季数据");
		button[1] = new TeamSeasonButton(lineupX,y,width2,height,"阵容");
		button[2] = new TeamSeasonButton(gameX,y,width1,height,"赛程数据");
	}
	
	public void addButton(){
		for(int i = 0;i<3;i++){
			this.add(button[i]);
		}
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
				 return;
			 }else if(e.getSource() == button[1]){
				 controller.toTeamPlayerPanel(TeamSeasonPanel.this,teamButton);
			 }else if(e.getSource() == button[2]){
				 controller.toTeamGamePanel(TeamSeasonPanel.this,teamButton);
			 }
		 }
	}

	public void setEffect() {
		button[0].setOpaque(true);
		button[0].setBackground(UIConfig.BUTTON_COLOR);
		button[0].setForeground(Color.white);
}
}
