/**
 * 
 */
package ui.panel.allteams;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Thread.State;

import ui.Images;
import ui.common.button.TabButton;
import ui.common.label.KingLabel;
import ui.common.panel.BottomPanel;
import utility.Constants;
import vo.KingVO;
import blservice.TeamQueryBLService;

/**
 * 球队数据王界面
 * @author Issac Ding
 * @since 2015年5月13日 下午7:04:22
 * @version 1.0
 */
@SuppressWarnings("serial")
public class TeamKingPanel extends BottomPanel{
	
	private TeamQueryBLService service;
	private TabButton scoreTab;
	private TabButton reboundTab;
	private TabButton assistTab;
	private String abbr;
	private KingLabel[] kingLabels = new KingLabel[5];
	private String season;
	private int state = 0;	//0 1 2分别为得分篮板助攻
	
	
	public TeamKingPanel(TeamQueryBLService service, String abbr) {
		super(Images.KING_BG);
		this.service = service;
		this.abbr = abbr;
		this.setLayout(null);
		this.season = Constants.LATEST_SEASON_REGULAR;
		
		addSecondLevelTabs();
		KingVO [] kings = service.getScoreKings(abbr, season);
		for (int i=0;i<5;i++) {
			if (kings[i] == null) return;
			kingLabels[i] = new KingLabel(kings[i]);
			this.add(kingLabels[i]);
		}
		repaint();
	}
	
	public void updateContent(String season) {
		this.season = season;
		if (state == 0) {
			changeKingLabel(service.getScoreKings(abbr, season));
		}else if (state == 1) {
			changeKingLabel(service.getReboundKings(abbr, season));
		}else {
			changeKingLabel(service.getAssistKings(abbr, season));
		}
	}
	
	private void changeKingLabel(KingVO[] kings) {
		for (int i=0;i<5;i++) {
			if (kings[i] == null) return;
			if (this.kingLabels[i] == null) {
				kingLabels[i] = new KingLabel(kings[i]);
			}else {
				kingLabels[i].updateContent(kings[i]);
			}
		}
	}
	
	private void addSecondLevelTabs() {
		scoreTab = new TabButton(Constants.scoreAvgText, Images.TEAM_SECOND_LEVEL_TAB_MOVE_ON, Images.TEAM_SECOND_LEVEL_TAB_CHOSEN);
		scoreTab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scoreTab.setOn();
				reboundTab.setOff();
				assistTab.setOff();
				state = 1;
				KingVO [] kings = service.getScoreKings(abbr, season);
				changeKingLabel(kings);
			}
		});
		scoreTab.setLocation(29, 3);
		this.add(scoreTab);
		
		reboundTab = new TabButton(Constants.reboundAvgText, Images.TEAM_SECOND_LEVEL_TAB_MOVE_ON, Images.TEAM_SECOND_LEVEL_TAB_CHOSEN);
		reboundTab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scoreTab.setOff();
				reboundTab.setOn();
				assistTab.setOff();
				state = 2;
				KingVO [] kings = service.getReboundKings(abbr, season);
				changeKingLabel(kings);
			}
		});
		reboundTab.setLocation(325, 3);
		this.add(reboundTab);
		
		assistTab = new TabButton(Constants.assistAvgText, Images.TEAM_SECOND_LEVEL_TAB_MOVE_ON, Images.TEAM_SECOND_LEVEL_TAB_CHOSEN);
		assistTab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scoreTab.setOff();
				reboundTab.setOff();
				assistTab.setOn();
				state = 3;
				KingVO [] kings = service.getAssistKings(abbr, season);
				changeKingLabel(kings);
			}
		});
		assistTab.setLocation(622, 3);
		this.add(assistTab);
	}
}
