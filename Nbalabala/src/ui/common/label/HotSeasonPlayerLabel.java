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
	public HotSeasonPlayerLabel(HotSeasonPlayerVO vo, HotSeasonPlayerProperty property) {
		super(vo.getTop());
		playerName = vo.getName();
		if (vo.getTop() == 1) {
			Image actionImage = PlayerImageCache.getActionImageByName(playerName);
			ActionPhotoPanel actionPhotoPanel = new ActionPhotoPanel(actionImage);
			actionPhotoPanel.setSize(176, 280);
			actionPhotoPanel.setBounds(242,10,176,280);
//			actionPhotoPanel.setLocation(2625, 50);
			this.add(actionPhotoPanel);
			
			MyLabel nameLabel = new MyLabel(68, 40, 250, 26, playerName);
			nameLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
			this.add(nameLabel);
			
			String propertyName = getPropertyName(property);
			String propertyStr = UIConfig.FORMAT.format(vo.getProperty());
			MyLabel propertyLabel = new MyLabel(108, 110, 146, 26, propertyName+"："+propertyStr);
			this.add(propertyLabel);
			
			String team = Constants.translateTeamAbbr(vo.getTeamAbbr());
			MyLabel teamLabel = new MyLabel(108, 161, 180, 26, "球队：" + team);
			this.add(teamLabel);
			
			MyLabel positionLabel = new MyLabel(108, 212, 146, 26, "位置：" + vo.getPosition());
			this.add(positionLabel);
		}else{
			Image portrait = PlayerImageCache.getPortraitByName(vo.getName());
			ImgLabel portaitLabel = new ImgLabel(0, 37, 92, 75, portrait);
			this.add(portaitLabel);
			
			MyLabel nameLabel = new MyLabel(30,7,132,20,vo.getName());
			this.add(nameLabel);
			
			String propertyName = getPropertyName(property);
			String propertyStr = UIConfig.FORMAT.format(vo.getProperty());
			MyLabel propertyLabel = new MyLabel(70, 37, 120, 20, propertyName+"："+propertyStr);
			this.add(propertyLabel);
			
			String team = Constants.translateTeamAbbr(vo.getTeamAbbr());
			MyLabel teamLabel = new MyLabel(80, 67, 120, 20, "球队：" + team);
			this.add(teamLabel);
			
			MyLabel positionLabel = new MyLabel(109, 97, 68, 20, "位置：" + vo.getPosition());
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
