package ui.common.label;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.UIConfig;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import ui.panel.allplayers.ActionPhotoPanel;
import utility.Constants;
import vo.HotSeasonPlayerVO;
import data.playerdata.PlayerImageCache;
import enums.HotSeasonPlayerProperty;

/**
 *  * 使用时只需要传入参数并且add到panel上即可
 * @author Issac Ding
 * @version 2015年4月9日  下午5:11:39
 */
public class HotSeasonPlayerLabel extends HotSeasonLabel{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -5702031664115925534L;
	
	private String playerName;

	/**
	 * @param vo 从BL层得到的VO 
	 * @param property 热点的类型，如得分、篮板、助攻等
	 * @author Issac Ding
	 * @version 2015年4月9日  下午6:24:16
	 */
	
	private ActionPhotoPanel actionPhotoPanel;
	private ImgLabel portraitLabel;
	private MyLabel nameLabel;
	private MyLabel propertyLabel;
	private MyLabel teamLabel;
	private MyLabel positionLabel;
	
	public void updateContent(HotSeasonPlayerVO vo, HotSeasonPlayerProperty property) {
		if (actionPhotoPanel != null)
			actionPhotoPanel.setImage(PlayerImageCache.getActionImageByName(playerName));
		if (portraitLabel != null)
			portraitLabel.setImage(PlayerImageCache.getPortraitByName(vo.getName()));
		nameLabel.setText(vo.getName());
		String propertyName = getPropertyName(property);
		String propertyStr = UIConfig.FORMAT.format(vo.getProperty());
		propertyLabel.setText(propertyName+"："+propertyStr);
		teamLabel.setText("球队：" + Constants.translateTeamAbbr(vo.getTeamAbbr()));
		positionLabel.setText("位置：" + vo.getPosition());
	}
	
	public HotSeasonPlayerLabel(HotSeasonPlayerVO vo, HotSeasonPlayerProperty property) {
		super(vo.getTop());
		playerName = vo.getName();
		if (vo.getTop() == 1) {
			Image actionImage = PlayerImageCache.getActionImageByName(playerName);
			actionPhotoPanel = new ActionPhotoPanel(actionImage);
			actionPhotoPanel.setSize(176, 280);
			actionPhotoPanel.setBounds(242,10,176,280);
			this.add(actionPhotoPanel);
			
			int labelX = 90;
			nameLabel = new MyLabel(labelX, 40, 300, 26, playerName);
			nameLabel.setFont(new Font("微软雅黑", Font.BOLD, 27));
			nameLabel.setLeft();
			this.add(nameLabel);
			
			String propertyName = getPropertyName(property);
			String propertyStr = UIConfig.FORMAT.format(vo.getProperty());
			propertyLabel = new MyLabel(labelX, 80, 146, 26, propertyName+"："+propertyStr);
			propertyLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
			propertyLabel.setForeground(UIConfig.HIST_FIRST_COLOR);
			propertyLabel.setLeft();
			this.add(propertyLabel);
			
			String team = Constants.translateTeamAbbr(vo.getTeamAbbr());
			teamLabel = new MyLabel(labelX, 130, 180, 26, "球队：" + team);
			teamLabel.setLeft();
			this.add(teamLabel);
			
			positionLabel = new MyLabel(labelX, 160, 146, 26, "位置：" + vo.getPosition());
			positionLabel.setLeft();
			this.add(positionLabel);
		}else{
			Image portrait = PlayerImageCache.getPortraitByName(vo.getName());
			portraitLabel = new ImgLabel(0, 37, 92, 75, portrait);
			this.add(portraitLabel);
			
			int labelWid = 175;
			
			nameLabel = new MyLabel(0,7,labelWid,20,vo.getName());
			
			String propertyName = getPropertyName(property);
			String propertyStr = UIConfig.FORMAT.format(vo.getProperty());
			propertyLabel = new MyLabel(0, 37, labelWid, 20, propertyName+"："+propertyStr);
			
			String team = Constants.translateTeamAbbr(vo.getTeamAbbr());
			teamLabel = new MyLabel(0, 67, labelWid, 20, "球队：" + team);
			
			positionLabel = new MyLabel(0, 97, labelWid, 20, "位置：" + vo.getPosition());
			
			MyLabel labels[] = {propertyLabel, teamLabel, nameLabel, positionLabel};
			for (int i = 0; i < labels.length; i++) {
				labels[i].setRight();
			}
			
			this.add(nameLabel);
			this.add(propertyLabel);
			this.add(teamLabel);
			this.add(positionLabel);
		}
		
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() < 2) return;
				MainController.toPlayerInfoPanel((BottomPanel)HotSeasonPlayerLabel.this.getParent(), 
						playerName, (BottomPanel)HotSeasonPlayerLabel.this.getParent());
			}
		});
	}
	
	private String getPropertyName(HotSeasonPlayerProperty property) {
		switch (property) {
		case SCORE_AVG:
			return "得分";
		case REBOUND_AVG:
			return "篮板";
		case ASSIST_AVG:
			return "助攻";
		case BLOCK_AVG:
			return "盖帽";
		case STEAL_AVG:
			return "抢断";
		case FIELD_PERCENT:
			return "投篮%";
		case THREE_POINT_PERCENT:
			return "三分%";
		default:
			return "罚球%";
		}
	}
}
