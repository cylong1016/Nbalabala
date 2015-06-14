package ui.panel.analyse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import po.PlayerProfilePO;
import ui.Images;
import ui.UIConfig;
import ui.common.button.TabButton;
import ui.common.comboBox.MyComboBox;
import ui.common.panel.BottomPanel;
import ui.common.panel.Panel;
import utility.Constants;
import bl.teamquerybl.TeamQuery;
import blservice.TeamQueryBLService;

/**
 * 球员分析界面
 * @author lsy
 * @version 2015年6月8日  下午2:04:39
 */
public class AnalysePanel extends BottomPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = 560926515968497035L;
	private String teamAbbr,team,player;
	private MyComboBox teamCom,playerCom;
	private TeamQueryBLService teamQuery = new TeamQuery();
	private TabButton[] select;
	private Panel currentPanel;
	private LastFivePanel lastFive;
	private AllSeasonPanel allSeason;
	private ContriPanel contri;
	private FuturePanel future;
	private TurnPanel turn;
	
	public AnalysePanel(String url){
		super(url);
		this.team = Constants.translateTeamAbbr(teamAbbr);
		lastFive = new LastFivePanel();
		allSeason = new AllSeasonPanel();
		contri = new ContriPanel();
		future =new FuturePanel();
		turn = new TurnPanel();
		currentPanel = lastFive;
		addLabel();
		addComboBox();
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
						currentPanel = lastFive;
						for(int i = 0 ;i < 5; i++) {
							select[i].setOff();
						}
						select[0].setOn();
						AnalysePanel.this.add(currentPanel);
					}else if(e.getSource() == select[1]){
						currentPanel = allSeason;
						for(int i = 0 ;i < 5; i++) {
							select[i].setOff();
						}
						select[1].setOn();
						AnalysePanel.this.add(currentPanel);
					}else if(e.getSource() == select[2]){
						currentPanel = contri;
						for(int i = 0 ;i < 5; i++) {
							select[i].setOff();
						}
						select[2].setOn();
						AnalysePanel.this.add(currentPanel);
					}else if(e.getSource() == select[3]){
						currentPanel = future;
						for(int i = 0 ;i < 5; i++) {
							select[i].setOff();
						}
						select[3].setOn();
						AnalysePanel.this.add(currentPanel);
					}else if(e.getSource() == select[4]){
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
	
	public void addComboBox(){
		teamCom = new MyComboBox(Constants.TEAM_NAMES,599,10,120,30);
		String[] str = changeArray(teamQuery.getTeamDetailByAbbr("BOS", "2014-15R").getPlayers());
		playerCom = new MyComboBox(str,770,10,180,30);
		this.add(teamCom);
		teamCom.addActionListener(new ActionListener(){

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				playerCom.removeAllItems();
				String[] str = changeArray(teamQuery.getTeamDetailByAbbr(Constants.TEAM_ABBR[teamCom.getSelectedIndex()],
						"2014-15R").getPlayers());
				for(int i = 0 ;i < str.length; i++) {
					playerCom.addItem(str[i]);
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
	public String[] changeArray(ArrayList<PlayerProfilePO> arrayList){
		int lth = arrayList.size();
		String[] str = new String[lth];
		for(int i = 0 ;i < lth; i++){
			str[i] = arrayList.get(i).getName();
		}
		return str;
	}
}
