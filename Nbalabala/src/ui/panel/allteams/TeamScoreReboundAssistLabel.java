package ui.panel.allteams;

import javax.swing.JLabel;

import ui.UIConfig;
import utility.Utility;

/**
 * 封装了得分篮板助攻的  名称、数据、排名 的label
 * @author Issac Ding
 * @version 2015年4月27日  下午3:42:37
 */
@SuppressWarnings("serial")
public class TeamScoreReboundAssistLabel extends JLabel{
	
	private JLabel promptLabel;
	private JLabel rankLabel;
	private JLabel endLabel;	//右上角的那个st nd rd th
	
	public TeamScoreReboundAssistLabel(String prompt, int rank) {
		setLayout(null);
		setSize(71, 53);
		setOpaque(false);
		
		promptLabel = new JLabel(prompt);
		promptLabel.setOpaque(false);
		promptLabel.setFont(UIConfig.HiraginoFont);
		promptLabel.setHorizontalAlignment(LEFT);
		promptLabel.setBounds(0,0,58,20);
		this.add(promptLabel);
		
		rankLabel = new JLabel(String.valueOf(rank));
		rankLabel.setOpaque(false);
		rankLabel.setFont(UIConfig.FONT);
		rankLabel.setHorizontalAlignment(LEFT);
		rankLabel.setBounds(15, 26, 35, 25);
		this.add(rankLabel);
		
		endLabel = new JLabel(Utility.getRankEnd(rank));
		endLabel.setOpaque(false);
		endLabel.setFont(UIConfig.FONT);
		endLabel.setHorizontalAlignment(LEFT);
		endLabel.setBounds(15 + (int)rankLabel.getPreferredSize().getWidth(), 22, 20, 20);
		this.add(endLabel);
	}
	
	public void update(int rank) {
		rankLabel.setText(Utility.getRankStr(rank));
		endLabel.setText(Utility.getRankEnd(rank));
		endLabel.setBounds(15 + (int)rankLabel.getPreferredSize().getWidth(), 22, 20, 20);
	}
	
}
