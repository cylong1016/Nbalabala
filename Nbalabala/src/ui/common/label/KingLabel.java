package ui.common.label;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import ui.Images;
import ui.UIConfig;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import ui.panel.allplayers.ActionPhotoPanel;
import utility.Constants;
import utility.Utility;
import vo.KingVO;
import data.playerdata.PlayerImageCache;

/**
 * 数据王的5块板子，使用时只需要传进vo并且add到panel上即可，不需要setBounds
 * @author Issac Ding
 * @version 2015年4月9日  下午11:27:56
 */
public class KingLabel extends JLabel{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -5477070050894403050L;
	private static final Color RANK_Color = new Color(92, 147, 201);
	private static final int TOP_ONE_LAST_LINE_Y = 118;
	private static final int TOP_ONE_LABEL_X = 211;
	private static final int ELSE_LABEL_X = 85;
	private static final int ELSE_LAST_LINE_Y = 46;
	private static final Font DATA_FONT = new Font("微软雅黑", Font.BOLD, 27);
	private static final Font NAME_FONT = new Font("微软雅黑", Font.BOLD, 27);
	private static final Font SMALL_FONT = new Font("微软雅黑", Font.BOLD, 15);
	
	private String playerName;
	private ImgLabel arrowImgLabel = new ImgLabel(Images.ARROW_ICON);
	
	// 只属于第一名的
	private ActionPhotoPanel actionPhotoPanel;
	private MyLabel textLabel;
	
	// 只属于2345的
	private ImgLabel portraitLabel;
		
	//共有的
	private MyLabel nameLabel;
	private MyLabel propertyLabel;
	private MyLabel positionLabel;
	private MyLabel rankLabel;
	
	public KingLabel(KingVO vo) {
		playerName = vo.getName();
		setOpaque(false);
		setLayout(null);
		int top = vo.getTop();
		switch (top) {
		case 1:
			setBounds(29, 36, 444, 267);
			break;
		case 2:
			setBounds(563, 36, 380, 70);
			break;
		case 3:
			setBounds(563, 104, 380, 70);
			break;
		case 4:
			setBounds(563, 172, 380, 70);
			break;
		default:
			setBounds(563, 244, 380, 70);
			break;
		}
		setContent(vo);
	}
	
	public void updateContent(KingVO vo) {
		
		playerName = vo.getName();
		
		if (portraitLabel == null) {	//第一名的Label
			actionPhotoPanel.setImage(PlayerImageCache.getActionImageByName(vo.getName()));
			
			propertyLabel.setText(UIConfig.FORMAT.format(vo.getData()));
			nameLabel.setText(vo.getName());
			positionLabel.setText(vo.getPosition() + " / ");
			rankLabel.setText(String.valueOf(vo.getOverallRank()));
			
			int textX = (int)(TOP_ONE_LABEL_X + positionLabel.getPreferredSize().getWidth());
			textLabel.setLocation(textX, TOP_ONE_LAST_LINE_Y);
			
			int arrowX = (int)(textX + textLabel.getPreferredSize().getWidth());
			arrowImgLabel.setLocation(arrowX, TOP_ONE_LAST_LINE_Y);
			
			int rankX = arrowX + Images.ARROW_ICON.getIconWidth();
			rankLabel.setLocation(rankX, TOP_ONE_LAST_LINE_Y);
		}else {	// 2 3 4 5 名的情况
			portraitLabel.setImage(PlayerImageCache.getPortraitByName(playerName));
			propertyLabel.setText(UIConfig.FORMAT.format(vo.getData()));
			nameLabel.setText(playerName);
			positionLabel.setText(vo.getPosition());
			rankLabel.setText(String.valueOf(vo.getOverallRank()));
			
			int arrowX = ELSE_LABEL_X + (int)(positionLabel.getPreferredSize().getWidth());
			arrowImgLabel.setLocation(arrowX, ELSE_LAST_LINE_Y);
			
			int rankX = arrowX + Images.ARROW_ICON.getIconWidth();
			rankLabel.setLocation(rankX, ELSE_LAST_LINE_Y);
		}
	}
	
	private void setContent(KingVO vo) {
		if (vo.getTop() == 1) {
			Image actionImage = PlayerImageCache.getActionImageByName(vo.getName());
			actionPhotoPanel = new ActionPhotoPanel(actionImage);
			actionPhotoPanel.setBounds(33,5,200,300);
			this.add(actionPhotoPanel);
			
			String propertyStr = UIConfig.FORMAT.format(vo.getData());
			propertyLabel = new MyLabel(TOP_ONE_LABEL_X, 32, 100, 50, propertyStr);
			propertyLabel.setFont(DATA_FONT);
			propertyLabel.setForeground(new Color(235,148,24));
			this.add(propertyLabel);
			
			nameLabel = new MyLabel(TOP_ONE_LABEL_X, 82, 300, 50, vo.getName());
			nameLabel.setFont(NAME_FONT);
			this.add(nameLabel);
			
			positionLabel = new MyLabel(TOP_ONE_LABEL_X, TOP_ONE_LAST_LINE_Y, 80, 50, vo.getPosition() + " / ");
			positionLabel.setLeft();
			this.add(positionLabel);
			
			int textX = (int)(TOP_ONE_LABEL_X + positionLabel.getPreferredSize().getWidth());
			textLabel = new MyLabel(textX, TOP_ONE_LAST_LINE_Y, 80, 50, Constants.overallRankText);
			textLabel.setFont(SMALL_FONT);
			textLabel.setForeground(RANK_Color);
			this.add(textLabel);
			
			int arrowX = (int)(textX + textLabel.getPreferredSize().getWidth());
			arrowImgLabel.setBounds(arrowX, TOP_ONE_LAST_LINE_Y, 20, 20);
			this.add(arrowImgLabel);
			
			int rankX = arrowX + Images.ARROW_ICON.getIconWidth();
			rankLabel = new MyLabel(rankX, TOP_ONE_LAST_LINE_Y, 50, 50, 
					vo.getOverallRank() + Utility.getRankEnd(vo.getOverallRank()));
			rankLabel.setFont(SMALL_FONT);
			rankLabel.setForeground(RANK_Color);
			this.add(rankLabel);
			
			
		}else{
			Image portrait = PlayerImageCache.getPortraitByName(playerName);
			portraitLabel = new ImgLabel(6, 8, 74, 60, portrait);	
			this.add(portraitLabel);
			
			propertyLabel = new MyLabel(300, 25, 70, 30, UIConfig.FORMAT.format(vo.getData()));
			propertyLabel.setFont(DATA_FONT);
			propertyLabel.setForeground(new Color(115, 72, 12));
			this.add(propertyLabel);
			
			nameLabel = new MyLabel(ELSE_LABEL_X,20,300,30,playerName);
			nameLabel.setFont(NAME_FONT);
			this.add(nameLabel);
			
			positionLabel = new MyLabel(ELSE_LABEL_X, ELSE_LAST_LINE_Y, 100, 30, 
					vo.getPosition() + " / ");
			positionLabel.setFont(SMALL_FONT);
			this.add(positionLabel);
			
			int arrowX = ELSE_LABEL_X + (int)(positionLabel.getPreferredSize().getWidth());
			arrowImgLabel.setBounds(arrowX, ELSE_LAST_LINE_Y, 20, 20);
			this.add(arrowImgLabel);
			
			int rankX = arrowX + Images.ARROW_ICON.getIconWidth();
			rankLabel = new MyLabel(rankX, ELSE_LAST_LINE_Y, 80, 30, 
					vo.getOverallRank() + Utility.getRankEnd(vo.getOverallRank()));
			rankLabel.setForeground(RANK_Color);
			this.add(rankLabel);
		}
		
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() < 2) return;
				MainController.toPlayerInfoPanel(playerName, (BottomPanel)KingLabel.this.getParent());
			}
		});
	}
}
