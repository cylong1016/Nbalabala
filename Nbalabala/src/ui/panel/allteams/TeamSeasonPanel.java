package ui.panel.allteams;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.UIConfig;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import vo.PlayerProfileVO;
import vo.TeamDetailVO;
import bl.matchquerybl.MatchQuery;
import bl.teamquerybl.TeamQuery;
import blservice.MatchQueryBLService;
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
	MatchQueryBLService matchQuery = new MatchQuery();
	
	//用x来判断此时是赛季数据还是阵容 0代表赛季 1代表阵容
	public TeamSeasonPanel(MainController controller, String url,TeamButton teamButton,int x) {
		super(controller,url);
		this.controller = controller;
		this.teamButton = teamButton;
		setButton();
		addButton();
		setEffect(x);
		addListener();
//		TeamDetailVO teamDetail = teamQuery.getTeamDetailByAbbr(teamButton.team);
		//TODO 设置表格
	}
	
	
	public void setButton(){
		button[0] = new TeamSeasonButton(seasonX,y,width1,height,"赛季数据");
		button[1] = new TeamSeasonButton(lineupX,y,width2,height,"阵容");
		button[2] = new TeamSeasonButton(gameX,y,width1,height,"赛程数据");
		TeamSeasonButton.current = button[0];
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
				if (e.getSource() == TeamSeasonButton.current) {
					return;
				}
				TeamSeasonButton.current.back();
				TeamSeasonButton.current = (TeamSeasonButton) e.getSource();
			 if(e.getSource() == button[0]){
				 return;
			 }else if(e.getSource() == button[1]){
				 TeamDetailVO teamDetail = teamQuery.getTeamDetailByAbbr(teamButton.team);
					ArrayList<PlayerProfileVO> players = teamDetail.getPlayers();
					//TODO 把球员信息分解下放到表格里~
			 }else if(e.getSource() == button[2]){
				 controller.toTeamGamePanel(TeamSeasonPanel.this, teamButton);
			 }
		 }
	}

	public void setEffect(int i) {
		button[i].setOpaque(true);
		button[i].setBackground(UIConfig.BUTTON_COLOR);
		button[i].setForeground(Color.white);
	}
}
