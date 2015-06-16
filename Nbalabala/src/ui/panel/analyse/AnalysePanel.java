package ui.panel.analyse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.Images;
import ui.UIConfig;
import ui.common.button.TabButton;
import ui.common.comboBox.MyComboBox;
import ui.common.panel.BottomPanel;
import ui.common.panel.Panel;
import ui.panel.analyse.panel.AllSeasonPanel;
import ui.panel.analyse.panel.ContriPanel;
import ui.panel.analyse.panel.FuturePanel;
import ui.panel.analyse.panel.LastFivePanel;
import ui.panel.analyse.panel.TurnPanel;
import utility.Constants;
import bl.analysisbl.ValueAnalysis;
import blservice.AnalysisBLService;

/**
 * 球员分析界面
 * @author lsy
 * @version 2015年6月8日  下午2:04:39
 */
public class AnalysePanel extends BottomPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = 560926515968497035L;
//	private String teamAbbr,team,player;
	private MyComboBox teamCom,playerCom;
	private TabButton[] select;
	private Panel currentPanel;
	private LastFivePanel lastFive;
	private AllSeasonPanel allSeason;
	private ContriPanel contri;
	private FuturePanel future;
	private TurnPanel turn;
	private AnalysisBLService service;
	private static int currentI;
	
	public AnalysePanel(String url){
		super(url);
		service = new ValueAnalysis();
//		this.team = Constants.translateTeamAbbr(teamAbbr);
		str = changeArray(service.getLineupNamesByAbbr("BOS"));
		addComboBox();
		lastFive = new LastFivePanel();
		allSeason = new AllSeasonPanel(str[0]);
		contri = new ContriPanel();
		future =new FuturePanel(str[0]);
		turn = new TurnPanel(str[0]);
		currentPanel = lastFive;
		addLabel();
	}
	
	public void addLabel(){
		select = new TabButton[5];
		for(int i = 0 ;i < 5; i++) {
			select[i] = new TabButton(Constants.ANALYSE[i],Images.ANA_ON,Images.ANA_CLICK);
			select[i].setLocation(19+116*i, 60);
			this.add(select[i]);
			this.add(lastFive);
			select[0].setOn();
		}
		
		for (int i = 0; i < 5; i++) {
			select[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					AnalysePanel.this.remove(currentPanel);
					if(e.getSource() == select[0]){
						currentI = 0;
						currentPanel = lastFive;
						for(int i = 0 ;i < 5; i++) {
							select[i].setOff();
						}
						select[0].setOn();
						AnalysePanel.this.add(currentPanel);
					}else if(e.getSource() == select[1]){
						currentI = 1;
						currentPanel = allSeason;
						for(int i = 0 ;i < 5; i++) {
							select[i].setOff();
						}
						select[1].setOn();
						AnalysePanel.this.add(currentPanel);
					}else if(e.getSource() == select[2]){
						currentI = 2;
						currentPanel = contri;
						for(int i = 0 ;i < 5; i++) {
							select[i].setOff();
						}
						select[2].setOn();
						AnalysePanel.this.add(currentPanel);
					}else if(e.getSource() == select[3]){
						currentI = 3;
						currentPanel = future;
						for(int i = 0 ;i < 5; i++) {
							select[i].setOff();
						}
						select[3].setOn();
						AnalysePanel.this.add(currentPanel);
					}else if(e.getSource() == select[4]){
						currentI = 4;
						currentPanel = turn;
						for(int i = 0 ;i < 5; i++) {
							select[i].setOff();
						}
						select[4].setOn();
						AnalysePanel.this.add(currentPanel);
					}
					AnalysePanel.this.repaint();
				}
			});
			
		}
	}
	private String[] str;
	public void addComboBox(){
		teamCom = new MyComboBox(Constants.TEAM_NAMES,599,10,120,30);
		String[] strCom = changeArray(service.getLineupNamesByAbbr("BOS"));
		String[] showStr = new String[strCom.length];
		for(int i = 0 ; i < strCom.length; i++) {
			String[] name = strCom[i].split("\\$");
			showStr[i] = name[0];
		}
		playerCom = new MyComboBox(showStr,770,10,180,30);
		this.add(teamCom);
		teamCom.addActionListener(new ActionListener(){

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				playerCom.removeAllItems();
				str = changeArray(service.getLineupNamesByAbbr(Constants.TEAM_ABBR[teamCom.getSelectedIndex()]));
				for(int i = 0 ;i < str.length; i++) {
					String[] name = str[i].split("\\$");
					playerCom.addItem(name[0]);
				}
			}
			
		});
		
		playerCom.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = playerCom.getSelectedIndex();
				if(index == -1) {
					index = 0;
				}
				switch(currentI) {
				case 0:
					break;
				case 1:
					AnalysePanel.this.remove(allSeason);
					allSeason = new AllSeasonPanel(str[index]); 
					AnalysePanel.this.add(allSeason);
					AnalysePanel.this.repaint();
					break;
				case 2:
					break;
				case 3:
					AnalysePanel.this.remove(future);
					future = new FuturePanel(str[index]); 
					AnalysePanel.this.add(future);
					AnalysePanel.this.repaint();
					break;
				case 4:
					AnalysePanel.this.remove(turn);
					turn = new TurnPanel(str[index]); 
					AnalysePanel.this.add(turn);
					AnalysePanel.this.repaint();
					break;
				}
			}
			
		});
		teamCom.setBGColor(UIConfig.DARK_BUTTON_COLOR);
		playerCom.setBGColor(UIConfig.DARK_BUTTON_COLOR);
		this.add(playerCom);
	}
	
	/**
	 * 将arraylist转换成string数组
	 * @author lsy
	 * @version 2015年6月8日  下午2:33:59
	 */
	public String[] changeArray(ArrayList<String> arrayList){
		int lth = arrayList.size();
		String[] str = new String[lth];
		for(int i = 0 ;i < lth; i++){
			str[i] = arrayList.get(i);
		}
		return str;
	}
}
