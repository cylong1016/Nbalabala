package ui.panel.allplayers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import data.playerdata.PlayerImageCache;
import ui.Images;
import ui.UIConfig;
import ui.common.SeasonInputPanel;
import ui.common.button.ImgButton;
import ui.common.button.TabButton;
import ui.common.button.TextButton;
import ui.common.frame.Frame;
import ui.common.label.ImgLabel;
import ui.common.label.MyLabel;
import ui.common.panel.BottomPanel;
import ui.common.panel.Panel;
import ui.common.table.BottomScrollPane;
import ui.common.table.BottomTable;
import ui.controller.MainController;
import ui.panel.gamedata.GameDetailButton;
import utility.Constants;
import vo.MatchPlayerVO;
import vo.PlayerDetailVO;
import vo.PlayerMatchPerformanceVO;
import vo.PlayerProfileVO;
import vo.PlayerSeasonVO;
import bl.playerquerybl.PlayerQuery;
import blservice.PlayerQueryBLService;

/**
 * 具体球员信息界面
 * @author lsy
 * @version 2015年3月24日 上午10:26:48
 */
public class PlayerInfoBottomPanel extends BottomPanel {

	private static final String IMG_URL = UIConfig.IMG_PATH + "players/";
	private static final String BACK_BUTTON_OFF = IMG_URL + "back.png";
	private static final String BACK_BUTTON_ON = IMG_URL + "backOn.png";
	private static final String BACK_BUTTON_CLICK = IMG_URL + "back.png";

	private String name;
	private PlayerProfileVO profileVO;
	private PlayerQueryBLService playerQuery = new PlayerQuery();
	private PlayerDetailVO detailVO;
	
	private ImgButton backButton;

	private BottomPanel lastPanel;
	private MyLabel profileLabel[] = new MyLabel[4];

	/** 赛季选择器 */
	private SeasonInputPanel seasonInput;
	
	private ScoreReboundAssistLabel scoreLabel;
	private ScoreReboundAssistLabel reboundLabel;
	private ScoreReboundAssistLabel assistLabel;
	
	private TabButton briefTab = new TabButton(Constants.briefText, 
			UIConfig.FIRST_LEVEL_TAB_OFF_COLOR, UIConfig.FIRST_LEVEL_TAB_ON_COLOR);
	private TabButton seasonDataTab = new TabButton(Constants.seasonDataText, 
			UIConfig.FIRST_LEVEL_TAB_OFF_COLOR, UIConfig.FIRST_LEVEL_TAB_ON_COLOR);
	private TabButton matchesDataTab = new TabButton(Constants.matchesDataText, 
			UIConfig.FIRST_LEVEL_TAB_OFF_COLOR, UIConfig.FIRST_LEVEL_TAB_ON_COLOR);
	
	private JLabel teamLabel;
	
	private static final int PROFILE_LABEL_INTER_Y = 20;
	
	// 子面板。通过切换子面板实现三个页面的切换，其他部分不变
	private Panel currentPanel;
	private PlayerInfoBriefPanel briefPanel;
	private PlayerInfoSeasonDataPanel seasonDataPanel;
	private PlayerInfoMatchesDataPanel matchesDataPanel;
	
	public PlayerInfoBottomPanel(String name, BottomPanel lastPanel) {
		super(Images.PLAYER_INFO_BG);
		
		seasonInput = new SeasonInputPanel(this);
		seasonInput.setLocation(515, 400);	//TODO 赛季选择器还没定位置
		this.add(seasonInput);
		
		this.name = name;
		this.detailVO = playerQuery.getPlayerDetailByName(name, seasonInput.getSeason());
		this.profileVO = detailVO.getProfile();
		this.lastPanel = lastPanel;
		
		
		
		addTitles();
		addScoreReboundAssistLabels();
		addProfileLabel();
		addPortrait();
		addActionImg();
		addBackButton();
		addTabButtons();
		
		// 刚进入的时候显示的是柱状对比图的那个页面
		briefPanel = new PlayerInfoBriefPanel(detailVO.getSeasonRecord(), 
				playerQuery.getFiveArgsAvg(seasonInput.getSeason()), 
						playerQuery.getHighestScoreReboundAssist(seasonInput.getSeason()));
		currentPanel = briefPanel;
		addCurrentPanel();
	}
	
	private void addTabButtons() {
		briefTab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(currentPanel);
				briefPanel = new PlayerInfoBriefPanel(detailVO.getSeasonRecord(), 
						playerQuery.getFiveArgsAvg(seasonInput.getSeason()), 
								playerQuery.getHighestScoreReboundAssist(seasonInput.getSeason()));
				currentPanel = briefPanel;
				addCurrentPanel();
				briefTab.setOn();
				seasonDataTab.setOff();
				matchesDataTab.setOff();
				repaint();
			}
		});
		briefTab.setBounds(24,197,315,39);
		this.add(briefTab);
		briefTab.setEnabled(false);
		
		
		seasonDataTab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(currentPanel);
				seasonDataPanel = new PlayerInfoSeasonDataPanel();
				seasonDataPanel.update(seasonInput.getSeason(), detailVO.getSeasonRecord());
				currentPanel = seasonDataPanel;
				addCurrentPanel();
				briefTab.setOff();
				seasonDataTab.setOn();
				matchesDataTab.setOff();
				repaint();
			}
		});
		seasonDataTab.setBounds(341,197,315,39);
		this.add(seasonDataTab);
		
		matchesDataTab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				remove(currentPanel);
//				matchesDataPanel = new playerinfoma//TODO
				briefTab.setOff();
				seasonDataTab.setOff();
				matchesDataTab.setOn();
				repaint();
			}
		});
		matchesDataTab.setBounds(657,197,315,39);
		this.add(matchesDataTab);
	}
	
	// 标题信息，包括球衣号码、名字、位置、球队
	// TODO 这里的字体字号不造都是啥
	private void addTitles() {
		JLabel numLabel = new JLabel(profileVO.getNumber());
		numLabel.setForeground(new Color(235,148,24));
		numLabel.setHorizontalAlignment(JLabel.RIGHT);
		numLabel.setBounds(280, 23, 50, 50);
		this.add(numLabel);
		
		JLabel nameLabel = new JLabel(name);
		nameLabel.setOpaque(false);
		nameLabel.setHorizontalAlignment(JLabel.LEFT);
		nameLabel.setBounds(352, 22, 350, 50);
		this.add(nameLabel);
		
		// 位置有可能是 前锋-中锋 所以宽度不一定，所以队名的位置也不一定
		JLabel positionLabel = new JLabel(Constants.translatePosition(profileVO.getPosition())
				+ " / ");
		Dimension preferred = positionLabel.getPreferredSize();
		positionLabel.setBounds(351, 63, (int)preferred.getWidth(), (int)preferred.getHeight());
		this.add(positionLabel);
		
		// 改变赛季以后teamLabel可能会改变
		teamLabel = new JLabel(Constants.translateTeamAbbr(profileVO.getTeam()));
		teamLabel.setForeground(new Color(50, 126, 196));
		teamLabel.setBounds((int)preferred.getWidth() + 351, 63, 120, 25);
		this.add(teamLabel);
	}
	
	private void addScoreReboundAssistLabels() {
		int[] ranks = playerQuery.getScoreReboundAssistRank(name, seasonInput.getSeason());
		PlayerSeasonVO seasonVO = detailVO.getSeasonRecord();
		
		scoreLabel = new ScoreReboundAssistLabel(Constants.scoreAvgText, seasonVO.scoreAvg, ranks[0]);
		scoreLabel.setLocation(277, 102);
		this.add(scoreLabel);
		
		reboundLabel = new ScoreReboundAssistLabel(Constants.reboundAvgText, 
				seasonVO.totalReboundAvg, ranks[1]);
		reboundLabel.setLocation(385, 102);
		this.add(reboundLabel);
		
		assistLabel = new ScoreReboundAssistLabel(Constants.assistAvgText, 
				seasonVO.assistAvg, ranks[2]);
		assistLabel.setLocation(493, 102);
		this.add(assistLabel);
	}

	private void addProfileLabel() {
		String[] profileLabelStr = new String[]{profileVO.getHeight() + " / " + profileVO.getWeight(),
				Constants.birthdayText + "：" + profileVO.getBirth(),
				Constants.veteranText + "：" + profileVO.getExp(),
				Constants.schoolText + "：" + profileVO.getSchool()};
		for(int i = 0; i < 4; i++) {
			profileLabel[i] = new MyLabel(profileLabelStr[i]);
			profileLabel[i].setBounds(588, 20 + i * PROFILE_LABEL_INTER_Y, 250, 16);
			this.add(profileLabel[i]);
		}
	}

	// 需要刷新的，也就是会随赛季变化的，有title（主要是所属球队）和三大数据以及排名
	public void refresh() {
		String season = seasonInput.getSeason();
		detailVO = playerQuery.getPlayerDetailByName(name, season);
		PlayerSeasonVO seasonVO = detailVO.getSeasonRecord();
		int [] ranks = playerQuery.getScoreReboundAssistRank(name, season);
		scoreLabel.update(seasonVO.scoreAvg, ranks[0]);
		reboundLabel.update(seasonVO.totalReboundAvg, ranks[1]);
		assistLabel.update(seasonVO.assistAvg, ranks[2]);
		
		if (currentPanel == briefPanel) {
			briefPanel.update(seasonVO, playerQuery.getFiveArgsAvg(season), 
					playerQuery.getHighestScoreReboundAssist(season));
		}else if (currentPanel == seasonDataPanel) {
			seasonDataPanel.update(season, seasonVO);
		}else {
			//TODO
		}
		
		teamLabel.setText(Constants.translateTeamAbbr(seasonVO.getTeam()));
		repaint();
	}

	/**
	 * 返回按钮
	 * @author lsy
	 * @version 2015年3月24日 下午4:20:16
	 */
	private void addBackButton() {
		backButton = new ImgButton(BACK_BUTTON_OFF, 50, 50, BACK_BUTTON_ON, BACK_BUTTON_CLICK);
		this.add(backButton);
		backButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				MainController.backToOnePanel(lastPanel);
			}
		});
	}

	/**
	 * 添加头像
	 * @author lsy
	 * @version 2015年3月24日 上午11:17:35
	 */
	private void addPortrait() {
		ImgLabel label = new ImgLabel(53, 0, 200, 160, profileVO.getPortrait());
		this.add(label);
	}

	/**
	 * 添加全身像
	 * @author lsy
	 * @version 2015年3月24日 上午11:17:42
	 */
	private void addActionImg() {
		ActionPhotoPanel actionPhotoPanel = new ActionPhotoPanel(detailVO.getAction());
		actionPhotoPanel.setOpaque(true);
		actionPhotoPanel.setBounds(880, 6, 1000, 1000);
		this.add(actionPhotoPanel);
	}
	
	private void addCurrentPanel() {
		currentPanel.setBounds(25,236,946,363);
		this.add(currentPanel);
	}
	
	public static void main(String[]args) {
		Frame frame = new Frame();
		MainController.frame = frame;
		new PlayerImageCache();
		frame.setPanel(new PlayerInfoBottomPanel("Kobe Bryant", null));
		frame.start();
	}
}
