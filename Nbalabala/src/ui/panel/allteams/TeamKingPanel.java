/**
 * 
 */
package ui.panel.allteams;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import blservice.TeamQueryBLService;
import ui.Images;
import ui.common.button.TabButton;
import ui.common.label.KingLabel;
import ui.common.panel.BottomPanel;
import ui.common.panel.Panel;
import utility.Constants;
import vo.KingVO;

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
	
	public TeamKingPanel(TeamQueryBLService service, String abbr) {
		super(Images.KING_BG);
		this.service = service;
		this.abbr = abbr;
		this.setLayout(null);
		
		addSecondLevelTabs();
		KingVO [] kings = service.getScoreKings(abbr);
		for (int i=0;i<5;i++) {
			if (kings[i] == null) return;
			kingLabels[i] = new KingLabel(kings[i]);
			this.add(kingLabels[i]);
		}
		repaint();
	}
	
	private void addKingLabel(KingVO[] kings) {
		for (int i=0;i<5;i++) {
			if (this.kingLabels[i] != null) {
				this.remove(kingLabels[i]);
			}
			if (kings[i] == null) continue;
			kingLabels[i] = new KingLabel(kings[i]);
			this.add(kingLabels[i]);
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
				KingVO [] kings = service.getScoreKings(abbr);
				addKingLabel(kings);
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
				KingVO [] kings = service.getReboundKings(abbr);
				addKingLabel(kings);
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
				KingVO [] kings = service.getAssistKings(abbr);
				addKingLabel(kings);
			}
		});
		assistTab.setLocation(622, 3);
		this.add(assistTab);
	}
}
