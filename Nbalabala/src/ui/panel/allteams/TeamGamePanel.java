package ui.panel.allteams;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ui.common.button.ImgButton;
import ui.common.date.DateChooser;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.common.table.MatchInfoTableFactory;
import ui.controller.MainController;
import ui.panel.gamedata.GameDataPanel;
import vo.MatchProfileVO;
import bl.teamquerybl.TeamQuery;
import blservice.TeamQueryBLService;


/**
 * 球队赛程数据界面
 * @author lsy
 * @version 2015年4月13日  下午8:32:09
 */
public class TeamGamePanel extends TeamBottomPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = -6721546009416370456L;
	ImgButton imgButton;
	DateChooser dateChooser;
	TeamQueryBLService teamQuery = new TeamQuery();
	String abbr;
	GameDataPanel gameData;
	ArrayList<MatchProfileVO> matchProfile;
	BottomScrollPane pane;
	
	public TeamGamePanel(BottomPanel panelFrom,String url, String abbr) {
		super(panelFrom,url, abbr);
		this.abbr = abbr;
		setEffect(2);
		addDateChooser();
		addFindButton();
		matchProfile = teamDetail.getMatchRecords();
		gameData = new GameDataPanel("",1); 
		pane = new MatchInfoTableFactory(matchProfile,this).getTableScrollPanel();
		pane.setBounds(57, 285, 888, 245);
		add(pane);
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
				TeamGamePanel.this.remove(pane);
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
				pane = new MatchInfoTableFactory(pro,TeamGamePanel.this).getTableScrollPanel();
				pane.setBounds(57, 285, 888, 250);
				add(pane);
				TeamGamePanel.this.repaint();
			}
		});
	}

}
