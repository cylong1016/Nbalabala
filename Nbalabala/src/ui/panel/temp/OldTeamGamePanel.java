package ui.panel.temp;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.date.DateChooser;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.MatchInfoTableFactory;
import ui.controller.MainController;
import ui.panel.gamedata.GameDataPanel;
import vo.MatchProfileVO;
import vo.TeamSeasonVO;
import bl.teamquerybl.TeamQuery;
import blservice.TeamQueryBLService;

/**
 * 球队赛程数据界面
 * 
 * @author lsy
 * @version 2015年3月21日 上午12:28:47
 */
public class OldTeamGamePanel extends OldTeamSeasonPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 5247981003029326464L;
	ImgButton imgButton;
	DateChooser dateChooser;
	TeamQueryBLService teamQuery = new TeamQuery();
	String abbr;
	GameDataPanel gameData;
	ArrayList<MatchProfileVO> matchProfile;
	BottomScrollPane pane;

	public OldTeamGamePanel(BottomPanel allteams, String url,String abbr,
			int x) {
		super(allteams, url, abbr, x);
		this.abbr = abbr;
		addDateChooser();
		addFindButton();
		matchProfile = teamDetail.getMatchRecords();
		gameData = new GameDataPanel("",1); 
		pane = new MatchInfoTableFactory(matchProfile,this).getTableScrollPanel();
		pane.setBounds(57, 285, 888, 245);
		add(pane);
	}
	
	/**
	 * 覆盖父类的方法
	 * @see ui.panel.temp.OldTeamSeasonPanel#iniTable(int)
	 */
	public void iniTable(int X){
		
	}
	
	protected void addContrastDiagram() {
		
	}

	DecimalFormat df = UIConfig.FORMAT;
	
	/**
	 * 覆盖父类的方法，让父类的表格不显示
	 */
	public void addSeasonTable(TeamSeasonVO record) {
	}

	public void addBack() {
		ImgButton back = new ImgButton(UIConfig.IMG_PATH + "back.png", 50, 50, UIConfig.IMG_PATH + "back.png",
				UIConfig.IMG_PATH + "back.png");
		this.add(back);
		back.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				MainController.backToOnePanel(OldTeamGamePanel.this, allteams);
			}

		});
	}

	public void addDateChooser() {
		dateChooser = new DateChooser();
		MainController.addDateChooserPanel(this, dateChooser, 722, 245,153,30);
	}

	public void addFindButton() {
		imgButton = new ImgButton(url + "search.png", 893, 250, url + "searchOn.png", url + "searchClick.png");
		this.add(imgButton);
		imgButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				OldTeamGamePanel.this.remove(pane);
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
				ArrayList<MatchProfileVO> pro = new ArrayList<MatchProfileVO>();
				for (int i = 0; i < matchProfile.size(); i++) {
					if(dateStr.equals(matchProfile.get(i).getTime())&&seasonString.equals(matchProfile.get(i).getSeason())){
						pro.add(matchProfile.get(i));
						if (pane != null){
							remove(pane);
						}
					}
				}
				pane = new MatchInfoTableFactory(pro,OldTeamGamePanel.this).getTableScrollPanel();
				pane.setBounds(57, 285, 888, 250);
				add(pane);
				OldTeamGamePanel.this.repaint();
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
				MainController.toTeamSeasonPanel(allteams, OldTeamGamePanel.this, abbr, 0);
			} else if (e.getSource() == button[1]) {
				MainController.toTeamSeasonPanel(allteams, OldTeamGamePanel.this, abbr, 1);
			} else if (e.getSource() == button[2]) {
				return;
			}
		}
	}

}
