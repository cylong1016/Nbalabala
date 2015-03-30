package ui.panel.allteams;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ui.DateChooser;
import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.label.MyLabel;
import ui.common.table.BottomScrollPane;
import ui.common.table.MatchInfoTableFactory;
import ui.controller.MainController;
import ui.panel.gamedata.GameDataPanel;
import vo.MatchProfileVO;
import vo.TeamDetailVO;
import bl.teamquerybl.TeamQuery;
import blservice.TeamQueryBLService;
import data.seasondata.TeamSeasonRecord;

/**
 * 球队赛程数据界面
 * 
 * @author lsy
 * @version 2015年3月21日 上午12:28:47
 */
public class TeamGamePanel extends TeamSeasonPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 5247981003029326464L;
	ImgButton imgButton;
	MainController controller;
	DateChooser dateChooser;
	TeamQueryBLService teamQuery = new TeamQuery();
	TeamButton teamButton;
	TeamDetailVO teamDetail;
	GameDataPanel gameData;
	ArrayList<MatchProfileVO> matchProfile;
	BottomScrollPane pane;

	public TeamGamePanel(AllTeamsPanel allteams, MainController controller, String url, TeamButton teamButton,
			int x) {
		super(allteams, controller, url, teamButton, x);
		this.controller = controller;
		this.teamButton = teamButton;
		addFindButton();
		addDateChooser();
		teamDetail = teamQuery.getTeamDetailByAbbr(teamButton.team);
		matchProfile = teamDetail.getMatchRecords();
		gameData = new GameDataPanel(controller,"",1); 
		if (pane != null) {
			remove(pane);
		}
		pane = new MatchInfoTableFactory(matchProfile,this,controller).getTableScrollPanel();
		pane.setBounds(55, 285, 905, 250);
		add(pane);
		iniTable(x);
	}
	
	/**
	 * 覆盖父类的方法
	 * @see ui.panel.allteams.TeamSeasonPanel#iniTable(int)
	 */
	public void iniTable(int X){
		
	}

	DecimalFormat df = UIConfig.format;
	
	/**
	 * 覆盖父类的方法，让父类的表格不显示
	 */
	public void addSeasonTable(TeamSeasonRecord record) {
	}

	public void addBack() {
		ImgButton back = new ImgButton(UIConfig.IMG_PATH + "back.png", 50, 50, UIConfig.IMG_PATH + "back.png",
				UIConfig.IMG_PATH + "back.png");
		this.add(back);
		back.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				controller.backToGameDataPanel(TeamGamePanel.this, allteams);
			}

		});
	}

	public void addDateChooser() {
		dateChooser = new DateChooser();
		controller.addDateChooserPanel(this, dateChooser, 722, 248);
	}

	MyLabel label = new MyLabel(770,280,100,50,"当天没有比赛");
	public void addFindButton() {
		imgButton = new ImgButton(url + "search.png", 893, 250, url + "searchOn.png", url + "searchClick.png");
		this.add(imgButton);
		imgButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				TeamGamePanel.this.remove(pane);
				label.setForeground(UIConfig.BUTTON_COLOR);
				TeamGamePanel.this.add(label);
				Date date = dateChooser.getDate();
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH) + 1;
				int seasonStart;
				int seasonEnd;
				if (month < 8) {
					seasonStart = year -1;
					seasonEnd = year;
				}else {
					seasonStart = year;
					seasonEnd = year +1;
				}
				String seasonString = String.valueOf(seasonStart).substring(2) + "-" + 
						String.valueOf(seasonEnd).substring(2);
				
				SimpleDateFormat  sd = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
				String str = sd.format(date);
				String[] dateArr = str.split("  ");
				String[] dateTemp = dateArr[0].split("-");
				String dateStr = dateTemp[1]+"-"+dateTemp[2];
				for (int i = 0; i < matchProfile.size(); i++) {
					if(dateStr.equals(matchProfile.get(i).getTime())&&seasonString.equals(matchProfile.get(i).getSeason())){
						TeamGamePanel.this.remove(label);
						ArrayList<MatchProfileVO> pro = new ArrayList<MatchProfileVO>();
						pro.add(matchProfile.get(i));
						if (pane != null){
							remove(pane);
						}
						pane = new MatchInfoTableFactory(pro,TeamGamePanel.this,controller).getTableScrollPanel();
						add(pane);
					}
				}
				TeamGamePanel.this.repaint();
			}
		});
	}

	public void getGame() {

	}

	public void setEffect() {
		button[2].setOpaque(true);
		button[2].setBackground(UIConfig.BUTTON_COLOR);
		button[2].setForeground(Color.white);
	}

	public void addListener() {
		MouListener mou1 = new MouListener();
		for (int i = 0; i < 3; i++) {
			button[i].addMouseListener(mou1);
		}
	}

	class MouListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if (e.getSource() == button[0]) {
				controller.toTeamSeasonPanel(allteams, TeamGamePanel.this, teamButton, 0);
			} else if (e.getSource() == button[1]) {
				controller.toTeamSeasonPanel(allteams, TeamGamePanel.this, teamButton, 1);
			} else if (e.getSource() == button[2]) {
				return;
			}
		}
	}

}
