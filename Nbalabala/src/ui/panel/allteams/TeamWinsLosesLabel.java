package ui.panel.allteams;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import ui.UIConfig;
import utility.Constants;

/**
 * 在球队信息界面中显示球队的胜负，形如60胜-22负，本Label不包括中间那道横线
 * @author Issac Ding
 * @version 2015年4月28日  上午11:03:30
 */
public class TeamWinsLosesLabel extends JLabel{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -1833510881213891733L;
	private JLabel winsLabel;
	private JLabel losesLabel;
	private static final Font BIG_FONT = UIConfig.FONT; //TODO几胜几负的大字体
	
	public TeamWinsLosesLabel(int wins, int loses) {
		winsLabel = new JLabel(String.valueOf(wins));
		winsLabel.setOpaque(false);
		winsLabel.setFont(BIG_FONT);
		winsLabel.setForeground(new Color(190,45,45));
		winsLabel.setBounds(0,3, 42,35);
		winsLabel.setHorizontalAlignment(RIGHT);
		this.add(winsLabel);
		
		JLabel winTextLabel = new JLabel(Constants.winsText);
		winTextLabel.setBounds(43,19,25,25);
		winTextLabel.setOpaque(false);
		winTextLabel.setFont(UIConfig.LABEL_SMALL_FONT);
		this.add(winTextLabel);
		
		losesLabel = new JLabel(String.valueOf(loses));
		losesLabel.setOpaque(false);
		losesLabel.setFont(BIG_FONT);
		losesLabel.setBounds(82, 3, 42, 35);
		this.add(losesLabel);
		
		JLabel losesTextLabel = new JLabel(Constants.losesText);
		losesTextLabel.setBounds(115, 19, 25, 25);
		losesTextLabel.setOpaque(false);
		losesLabel.setFont(UIConfig.LABEL_SMALL_FONT);
		this.add(losesTextLabel);
	}
	
	// 胜负数可能发生变化
	public void update(int wins, int loses) {
		winsLabel.setText(String.valueOf(wins));
		losesLabel.setText(String.valueOf(loses));
	}

}
