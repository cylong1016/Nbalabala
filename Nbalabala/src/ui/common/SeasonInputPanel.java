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
	private JLabel rpLabel;
	private MyComboBox box;
	private int index;// 季后赛还是常规赛

	/** 有些界面需要改变赛季的同时刷新，则调用该方法 */
	private BottomPanel bottomPanel = null;

	public SeasonInputPanel(BottomPanel panel) {
		this.bottomPanel = panel;
		setLook();
		addCombobox();

		rightDownButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				index = 1 - index;
				rpLabel.setText(Constants.GAME_SORT_RP[index]);
				bottomPanel.refresh();
			}
		});
		rightUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				index = 1 - index;
				rpLabel.setText(Constants.GAME_SORT_RP[index]);
				bottomPanel.refresh();
			}
		});
		box.addActionListener(new ActionListener() {
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
				index = 1 - index;
				rpLabel.setText(Constants.GAME_SORT_RP[index]);
			}
		});
		rightDownButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				index = 1 - index;
				rpLabel.setText(Constants.GAME_SORT_RP[index]);
			}
		});
	}

	private void setLook() {
		this.setLayout(null);
		this.setBackground(UIConfig.BUTTON_COLOR);
		rpLabel = new JLabel(Constants.GAME_SORT_RP[0]);
		rpLabel.setForeground(Color.white);
		rightUpButton = new ImgButton("images/SeasonInputUpOff.png", "images/SeasonInputUpOn.png");
		rightDownButton = new ImgButton("images/SeasonInputDownOff.png", "images/SeasonInputDownOn.png");
		this.setSize(120, 26);

		rpLabel.setBounds(120, 0, 80, 26);
		this.add(rpLabel);

		rightUpButton.setBounds(175, 0, 22, 13);
		this.add(rightUpButton);

		rightDownButton.setBounds(175, 14, 22, 13);
		this.add(rightDownButton);
	}

	public String getSeason() {
		return box.getSelectedItem() + rpLabel.getText();
	}

	private void addCombobox() {
		box = new MyComboBox(Constants.GAME_YEAR, 0, 0, 100, 26);
		this.add(box);
	}

	public static SeasonInputPanel panel = new SeasonInputPanel();

}
