package ui.common.label;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import ui.MyFont;
import ui.UIConfig;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import ui.panel.allplayers.ActionPhotoPanel;
import utility.Constants;
import utility.Utility;
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
	private DecimalFormat df = UIConfig.FORMAT;
	
	
	public void updateContent(HotSeasonPlayerVO vo, HotSeasonPlayerProperty property) {
		if (actionPhotoPanel != null)
			actionPhotoPanel.setImage(PlayerImageCache.getActionImageByName(vo.getName()));
		if (portraitLabel != null)
			portraitLabel.setImage(PlayerImageCache.getPortraitByName(vo.getName()));
		nameLabel.setText(Utility.trimName(vo.getName()));
		String propertyName = Constants.getPropertyName(property);
		if (property == HotSeasonPlayerProperty.FIELD_PERCENT || property == HotSeasonPlayerProperty.THREE_POINT_PERCENT
				|| property == HotSeasonPlayerProperty.FREETHROW_PERCENT) {
			df = UIConfig.PERCENT_FORMAT;
		}else {
			df = UIConfig.FORMAT;
		}
		String propertyStr = df.format(vo.getProperty());
		propertyLabel.setText(propertyName+"："+propertyStr);
		teamLabel.setText(Constants.teamShortText + Constants.translateTeamAbbr(vo.getTeamAbbr()));
		positionLabel.setText(Constants.positionShortText + vo.getPosition());
	}
	
	public HotSeasonPlayerLabel(HotSeasonPlayerVO vo, HotSeasonPlayerProperty property,int index) {
		super(vo.getTop());
		playerName = Utility.trimName(vo.getName());
		String propertyName = Constants.getPropertyName(property);
		if (property == HotSeasonPlayerProperty.FIELD_PERCENT || property == HotSeasonPlayerProperty.THREE_POINT_PERCENT
				|| property == HotSeasonPlayerProperty.FREETHROW_PERCENT) {
			df = UIConfig.PERCENT_FORMAT;
		}else {
			df = UIConfig.FORMAT;
		}
		if (vo.getTop() == 1) {
			Image actionImage = PlayerImageCache.getActionImageByName(vo.getName());
			actionPhotoPanel = new ActionPhotoPanel(actionImage);
			actionPhotoPanel.setSize(176, 280);
			actionPhotoPanel.setBounds(242,10,176,280);
			this.add(actionPhotoPanel);
			
			int labelX = 90;
			nameLabel = new MyLabel(labelX, 40, 300, 40, playerName);
			nameLabel.setFont(MyFont.YT_L);
			nameLabel.setLeft();
			this.add(nameLabel);
			
			String propertyStr = df.format(vo.getProperty());
			if(index < 5){
				propertyStr = df.format(vo.getProperty());
			}
			propertyLabel = new MyLabel(labelX, 80, 200, 40, propertyStr);
			propertyLabel.setFont(MyFont.YT_L);
			propertyLabel.setForeground(UIConfig.RED_WIN_COLOR);
			propertyLabel.setLeft();
			this.add(propertyLabel);
			
			String team = Constants.translateTeamAbbr(vo.getTeamAbbr());
			teamLabel = new MyLabel(labelX, 140, 180, 26, Constants.teamShortText + team);
			teamLabel.setForeground(MyFont.LIGHT_GRAY);
			teamLabel.setLeft();
			this.add(teamLabel);
			
			positionLabel = new MyLabel(labelX, 160, 146, 26, Constants.positionShortText + vo.getPosition());
			positionLabel.setLeft();
			positionLabel.setForeground(MyFont.LIGHT_GRAY);
			this.add(positionLabel);
		}else{
			Image portrait = PlayerImageCache.getPortraitByName(vo.getName());
			portraitLabel = new ImgLabel(0, 37, 92, 75, portrait);
			this.add(portraitLabel);
			
			int labelWid = 175;
			
			nameLabel = new MyLabel(0,3,labelWid,30,Utility.trimName(vo.getName()));
			nameLabel.setFont(MyFont.YT_S);
			
			String propertyStr = df.format(vo.getProperty());
			propertyLabel = new MyLabel(0, 37, labelWid, 20, propertyName+"："+propertyStr);
			
			String team = Constants.translateTeamAbbr(vo.getTeamAbbr());
			teamLabel = new MyLabel(0, 75, labelWid, 20, Constants.teamShortText + team);
			teamLabel.setForeground(MyFont.LIGHT_GRAY);
			
			positionLabel = new MyLabel(0, 97, labelWid, 20, Constants.positionShortText + vo.getPosition());
			positionLabel.setForeground(MyFont.LIGHT_GRAY);
			
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
				MainController.toPlayerInfoPanel(playerName, (BottomPanel)HotSeasonPlayerLabel.this.getParent());
			}
		});
	}
}
