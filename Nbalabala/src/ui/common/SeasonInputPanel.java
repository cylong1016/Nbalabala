package ui.common;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.comboBox.MyComboBox;
import ui.common.panel.BottomPanel;
import utility.Constants;

/**
 * 用于选择赛季的组件，外观待美化
 * 
 * @author Issac Ding
 * @version 2015年4月7日 下午10:13:46
 */
public class SeasonInputPanel extends JPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 8335944304725379626L;
	private ImgButton rightUpButton;
	private ImgButton rightDownButton;
	private JLabel leftYearLabel;
	private JLabel rightYearLabel;
	private JLabel middleLabel;
	private JLabel textLabel;
	private MyComboBox box;

	/** 有些界面需要改变赛季的同时刷新，则调用该方法 */
	private BottomPanel bottomPanel = null;

	public SeasonInputPanel(BottomPanel panel) {
		this.bottomPanel = panel;
		setLook();
		addCombobox();

		rightDownButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				leftYearLabel.setText(yearIncrease(leftYearLabel.getText()));
				rightYearLabel.setText(yearIncrease(rightYearLabel.getText()));
				bottomPanel.refresh();
			}
		});
		rightUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				leftYearLabel.setText(yearDecrease(leftYearLabel.getText()));
				rightYearLabel.setText(yearDecrease(rightYearLabel.getText()));
				bottomPanel.refresh();
			}
		});
		box.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bottomPanel.refresh();
			}
		});
	}
	
	public SeasonInputPanel() {
		
		setLook();
		
		rightUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				leftYearLabel.setText(yearIncrease(leftYearLabel.getText()));
				rightYearLabel.setText(yearIncrease(rightYearLabel.getText()));
			}
		});
		rightDownButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				leftYearLabel.setText(yearDecrease(leftYearLabel.getText()));
				rightYearLabel.setText(yearDecrease(rightYearLabel.getText()));
			}
		});
	}
	
	private void setLook() {
		this.setLayout(null);
		this.setBackground(UIConfig.BUTTON_COLOR);
		String[] defaultSeason = Constants.LATEST_SEASON_DATE.split("-");
		leftYearLabel = new JLabel(defaultSeason[0]);
		rightYearLabel = new JLabel(defaultSeason[1]);
		middleLabel = new JLabel("—");
		textLabel = new JLabel("赛季");
		JLabel labels[] = {leftYearLabel, rightYearLabel, middleLabel, textLabel};
		for (int i = 0; i < labels.length; i++) {
			labels[i].setForeground(Color.white);
		}
		rightUpButton = new ImgButton("images/SeasonInputUpOff.png",
				"images/SeasonInputUpOn.png");
		rightDownButton = new ImgButton("images/SeasonInputDownOff.png",
				"images/SeasonInputDownOn.png");
		this.setSize(120, 26);

		leftYearLabel.setBounds(5, 0, 25, 26);
		this.add(leftYearLabel);

		middleLabel.setBounds(30, 0, 20, 26);
		this.add(middleLabel);

		rightYearLabel.setBounds(49, 0, 25, 26);
		this.add(rightYearLabel);

		textLabel.setBounds(73, 0, 31, 26);
		this.add(textLabel);

		rightUpButton.setBounds(100, 0, 22, 13);
		this.add(rightUpButton);

		rightDownButton.setBounds(100, 14, 22, 13);
		this.add(rightDownButton);
	}

	public String getSeason() {
		return leftYearLabel.getText() + "-" + rightYearLabel.getText()+changeToRP(box.getSelectedIndex());
	}
	
	/**
	 * 将常规赛转换成r，季后赛转换成p
	 * @author lsy
	 * @version 2015年6月6日  上午11:54:24
	 */
	public String changeToRP(int i){
		if(i == 0){
			return "R";
		}
		return "P";
	}

	private String yearIncrease(String oldYear) {
		int old = Integer.parseInt(oldYear);
		int newYear = old + 1;
		if (newYear == 100) {
			return "00";
		} else if (newYear < 10) {
			return "0" + newYear;
		} else {
			return Integer.toString(newYear);
		}
	}


	private void addCombobox() {
		box = new MyComboBox(Constants.GAME_SORT_RP,130,0,100,26);
		this.add(box);
	}
	
	private String yearDecrease(String oldYear) {
		int old = Integer.parseInt(oldYear);
		int newYear = old - 1;
		if (newYear == -1) {
			return "99";
		} else if (newYear < 10) {
			return "0" + newYear;
		} else {
			return Integer.toString(newYear);
		}
	}

	public static SeasonInputPanel panel = new SeasonInputPanel();

}
