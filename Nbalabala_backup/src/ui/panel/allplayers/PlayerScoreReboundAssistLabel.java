package ui.panel.allplayers;

import javax.swing.JLabel;

import ui.UIConfig;
import utility.Utility;

/**
 * 封装了得分篮板助攻的  名称、数据、排名 的label
 * @author Issac Ding
 * @version 2015年4月27日  下午3:42:37
 */
@SuppressWarnings("serial")
public class PlayerScoreReboundAssistLabel extends JLabel{
	
	private JLabel promptLabel;
	private JLabel dataLabel;
	private JLabel rankLabel;
	
	public PlayerScoreReboundAssistLabel(String prompt, double data, int rank) {
		setLayout(null);
		setSize(107, 50);
		setOpaque(false);
		
		promptLabel = new JLabel(prompt);
		promptLabel.setOpaque(false);
		promptLabel.setFont(UIConfig.HiraginoFont);
		promptLabel.setHorizontalAlignment(LEFT);
		promptLabel.setBounds(0,0,58,26);
		this.add(promptLabel);
		
		dataLabel = new JLabel(UIConfig.FORMAT.format(data));
		dataLabel.setOpaque(false);
		dataLabel.setFont(UIConfig.FONT);
		dataLabel.setHorizontalAlignment(RIGHT);
		dataLabel.setBounds(0, 25, 53, 26);
		this.add(dataLabel);

		rankLabel = new JLabel(Utility.getRankStr(rank));
		rankLabel.setOpaque(false);
		rankLabel.setFont(UIConfig.FONT);
		rankLabel.setHorizontalAlignment(LEFT);
		rankLabel.setBounds(64, 36, 43, 15);
		this.add(rankLabel);
	}
	
	public void update(double data, int rank) {
		rankLabel.setText(Utility.getRankStr(rank));
		dataLabel.setText(UIConfig.FORMAT.format(data));
	}
	
}
