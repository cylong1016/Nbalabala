package ui.panel.allplayers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;

import po.MatchPlayerPO;
import po.PlayerProfilePO;
import po.PlayerSeasonPO;
import ui.Images;
import ui.UIConfig;
import ui.common.SeasonInputPanel;
import ui.common.button.ImgButton;
import ui.common.button.TabButton;
import ui.common.frame.Frame;
import ui.common.label.ImgLabel;
import ui.common.label.MyLabel;
import ui.common.panel.BottomPanel;
import ui.common.panel.Panel;
import ui.controller.MainController;
import utility.Constants;
import vo.PlayerDetailVO;
import bl.playerquerybl.PlayerQuery;
import blservice.PlayerQueryBLService;
import data.playerdata.PlayerImageCache;

/**
 * 具体球员信息界面
 * @author lsy
 * @version 2015年3月24日 上午10:26:48
 */
@SuppressWarnings("serial")
public class PlayerInfoBottomPanel extends BottomPanel {

	private static final String IMG_URL = UIConfig.IMG_PATH + "players/";
	private static final String BACK_BUTTON_OFF = IMG_URL + "back.png";
	private static final String BACK_BUTTON_ON = IMG_URL + "backOn.png";
	private static final String BACK_BUTTON_CLICK = IMG_URL + "back.png";

	private String name;
	private PlayerProfilePO profileVO;
	private PlayerQueryBLService playerQuery = new PlayerQuery();
	private PlayerDetailVO detailVO;
	
	private ImgButton backButton;

	private BottomPanel lastPanel;
	private MyLabel profileLabel[] = new MyLabel[4];

	/** 赛季选择器 */
	private SeasonInputPanel seasonInput;
	
	private PlayerScoreReboundAssistLabel scoreLabel;
	private PlayerScoreReboundAssistLabel reboundLabel;
	private PlayerScoreReboundAssistLabel assistLabel;
	
	/** 选项卡按钮,分为当前和非当前状态 */
	private TabButton briefTab = new TabButton(Constants.briefText, 
			Images.PLAYER_TAB_MOVE_ON, Images.PLAYER_TAB_CHOSEN);
	private TabButton seasonDataTab = new TabButton(Constants.seasonDataText, 
			Images.PLAYER_TAB_MOVE_ON, Images.PLAYER_TAB_CHOSEN);
	private TabButton matchesDataTab = new TabButton(Constants.matchesDataText, 
			Images.PLAYER_TAB_MOVE_ON, Images.PLAYER_TAB_CHOSEN);
	
	/** 所属球队 */
	private JLabel teamLabel;
	
	// 子面板。通过切换子面板实现三个页面的切换，公共部分不变
	private Panel currentPanel;
	private PlayerInfoBriefPanel briefPanel;
	private PlayerInfoSeasonDataPanel seasonDataPanel;
	private PlayerInfoMatchesDataPanel matchesDataPanel;
	
	public PlayerInfoBottomPanel(String name, BottomPanel lastPanel) {
		super(Images.PLAYER_INFO_BG);
		seasonInput = new SeasonInputPanel(this);
		seasonInput.setLocation(515, 255);	//TODO 赛季选择器还没定位置，这里是随便写的
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
		
		int matchCount = detailVO.getMatchRecords().size();
		ArrayList<MatchPlayerPO> latestTwoMatches = new ArrayList<MatchPlayerPO>();
		latestTwoMatches.add(detailVO.getMatchRecords().get(matchCount - 2));
		latestTwoMatches.add(detailVO.getMatchRecords().get(matchCount - 1));
				
		// 刚进入的时候显示的是柱状对比图的那个页面
		briefPanel = new PlayerInfoBriefPanel(detailVO.getSeasonRecord(), 
				playerQuery.getFiveArgsAvg(seasonInput.getSeason()), 
				playerQuery.getHighestScoreReboundAssist(seasonInput.getSeason()),
				latestTwoMatches);
		currentPanel = briefPanel;
		addCurrentPanel();
	}
	
	private void addTabButtons() {
		briefTab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(currentPanel);
				String season = seasonInput.getSeason();
				PlayerSeasonPO seasonVO = detailVO.getSeasonRecord();
				
				currentPanel = briefPanel;
				addCurrentPanel();
				briefPanel.updateContent(seasonVO, playerQuery.getFiveArgsAvg(season), 
						playerQuery.getHighestScoreReboundAssist(season));
				
				briefTab.setOn();
				seasonDataTab.setOff();
				matchesDataTab.setOff();
				repaint();
			}
		});
		briefTab.setLocation(24,198);
		this.add(briefTab);
		briefTab.setOn();	//一开始本选项卡是当前选项卡
		
		seasonDataTab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(currentPanel);
				if (seasonDataPanel == null) {
					seasonDataPanel = new PlayerInfoSeasonDataPanel();
					seasonDataPanel.update(seasonInput.getSeason(), detailVO.getSeasonRecord());
				}
				currentPanel = seasonDataPanel;
				addCurrentPanel();
				
				briefTab.setOff();
				seasonDataTab.setOn();
				matchesDataTab.setOff();
				
				repaint();
			}
		});
		seasonDataTab.setLocation(341,198);
		this.add(seasonDataTab);
		
		matchesDataTab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(currentPanel);
				if (matchesDataPanel == null) {
					matchesDataPanel = new PlayerInfoMatchesDataPanel();
					matchesDataPanel.updateContent(detailVO.getMatchRecords());
				}
				currentPanel = matchesDataPanel;
				addCurrentPanel();
				
				briefTab.setOff();
				seasonDataTab.setOff();
				matchesDataTab.setOn();
				repaint();
			}
		});
		matchesDataTab.setLocation(657,198);
		this.add(matchesDataTab);
	}
	
	// 标题信息，包括球衣号码、名字、位置、球队
	// TODO 这里的字体字号不造都是啥
	// TODO 没有号码的数据怎么办
	private void addTitles() {
		PlayerSeasonPO seasonPO = detailVO.getSeasonRecord();
		
//		JLabel numLabel = new JLabel(profileVO.getNumber());
//		numLabel.setForeground(UIConfig.ORANGE_TEXT_COLOR);
//		numLabel.setHorizontalAlignment(JLabel.RIGHT);
//		//TODO 球衣号码，那个硕大的橙色的文字的字体和bounds
//		numLabel.setBounds(280, 23, 50, 50);
//		this.add(numLabel);
		
		JLabel nameLabel = new JLabel(name);
		nameLabel.setOpaque(false);
		nameLabel.setHorizontalAlignment(JLabel.LEFT);
		//TODO 球员名字的字体和bounds
		nameLabel.setBounds(352, 22, 350, 50);
		this.add(nameLabel);
		
		// 位置有可能是 前锋-中锋 所以宽度不一定，所以队名的位置也不一定
		JLabel positionLabel = new JLabel(Constants.translatePosition(profileVO.getPosition())
				+ " / ");
		Dimension preferred = positionLabel.getPreferredSize();
		positionLabel.setFont(UIConfig.LABEL_PLAIN_FONT);
		positionLabel.setBounds(351, 63, (int)preferred.getWidth(), (int)preferred.getHeight());
		this.add(positionLabel);
		
		// 改变赛季以后teamLabel可能会改变
		teamLabel = new JLabel(Constants.translateTeamAbbrToLocation(seasonPO.getTeamAbbr()) + 
				Constants.translateTeamAbbr(seasonPO.getTeamAbbr()));
		teamLabel.setFont(UIConfig.LABEL_PLAIN_FONT);
		teamLabel.setForeground(UIConfig.BLUE_TEXT_COLOR);
		teamLabel.setBounds((int)preferred.getWidth() + 351, 63, 120, 25);
		this.add(teamLabel);
	}
	
	private void addScoreReboundAssistLabels() {
		int[] ranks = playerQuery.getScoreReboundAssistRank(name, seasonInput.getSeason());
		PlayerSeasonPO seasonVO = detailVO.getSeasonRecord();
		
		scoreLabel = new PlayerScoreReboundAssistLabel(Constants.scoreAvgText, seasonVO.scoreAvg, ranks[0]);
		scoreLabel.setLocation(277, 102);
		this.add(scoreLabel);
		
		reboundLabel = new PlayerScoreReboundAssistLabel(Constants.reboundAvgText, 
				seasonVO.totalReboundAvg, ranks[1]);
		reboundLabel.setLocation(385, 102);
		this.add(reboundLabel);
		
		assistLabel = new PlayerScoreReboundAssistLabel(Constants.assistAvgText, 
				seasonVO.assistAvg, ranks[2]);
		assistLabel.setLocation(493, 102);
		this.add(assistLabel);
	}

	private void addProfileLabel() {
		String[] profileLabelStr = new String[]{Constants.translateHeight(profileVO.getHeightFoot() + "-" + profileVO.getHeightInch()) + " / " + Constants.translateWeight(profileVO.getWeight()),
				Constants.birthdayText + "：" + Constants.translateDate(profileVO.getBirthDate()),
				Constants.veteranText + "：" + (profileVO.getToYear() - profileVO.getFromYear()),
				Constants.schoolText + "：" + profileVO.getSchool()};
		for(int i = 0; i < 4; i++) {
			profileLabel[i] = new MyLabel(profileLabelStr[i]);
			profileLabel[i].setBounds(588, 20 + i * UIConfig.PROFILE_LABEL_INTER_Y, 250, 16);
			profileLabel[i].setFont(UIConfig.LABEL_SMALL_FONT);
			this.add(profileLabel[i]);
		}
	}

	// title（主要是所属球队）和三大数据以及排名 不随赛季变化（只关注现在的）
	public void refresh() {
		String season = seasonInput.getSeason();
		detailVO = playerQuery.getPlayerDetailByName(name, season);
		PlayerSeasonPO seasonVO = detailVO.getSeasonRecord();
//		int [] ranks = playerQuery.getScoreReboundAssistRank(name, season);
//		scoreLabel.update(seasonVO.scoreAvg, ranks[0]);
//		reboundLabel.update(seasonVO.totalReboundAvg, ranks[1]);
//		assistLabel.update(seasonVO.assistAvg, ranks[2]);
		
		briefPanel.updateContent(seasonVO, playerQuery.getFiveArgsAvg(season), 
				playerQuery.getHighestScoreReboundAssist(season));
		if (seasonDataPanel != null) {
			seasonDataPanel.update(season, seasonVO);
		}
		if (matchesDataPanel != null) {
			matchesDataPanel.updateContent(detailVO.getMatchRecords());
		}
		
//		teamLabel.setText(Constants.translateTeamAbbr(seasonVO.getTeam()));
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
		ImgLabel label = new ImgLabel(53, 0, 200, 160, PlayerImageCache.getPortraitByName(name));
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
	
	//TODO 测试代码
	public static void main(String[]args) {
		Frame frame = new Frame();
		MainController.frame = frame;
		new PlayerImageCache().loadPortrait();;
		frame.setPanel(new PlayerInfoBottomPanel("Kobe Bryant", null));
		frame.start();
	}
}
