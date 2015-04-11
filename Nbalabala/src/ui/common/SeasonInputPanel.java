package ui.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.common.button.ImgButton;
import utility.Utility;

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

	public SeasonInputPanel() {
		this.setLayout(null);
		String[] defaultSeason = Utility.getDefaultSeason().split("-");
		leftYearLabel = new JLabel(defaultSeason[0]);
		rightYearLabel = new JLabel(defaultSeason[1]);
		middleLabel = new JLabel("—");
		textLabel = new JLabel("赛季");
		rightUpButton = new ImgButton("images/SeasonInputUpOff.png",
				"images/SeasonInputUpOn.png");
		rightDownButton = new ImgButton("images/SeasonInputDownOff.png",
				"images/SeasonInputDownOn.png");
		this.setSize(115, 26);

		leftYearLabel.setBounds(0, 0, 20, 26);
		this.add(leftYearLabel);

		middleLabel.setBounds(23, 0, 20, 26);
		this.add(middleLabel);

		rightYearLabel.setBounds(44, 0, 20, 26);
		this.add(rightYearLabel);
		
		textLabel.setBounds(68, 0, 31, 26);
		this.add(textLabel);

		rightUpButton.setBounds(95, 0, 22, 13);
		this.add(rightUpButton);

		rightDownButton.setBounds(95, 14, 22, 13);
		this.add(rightDownButton);

		setAction();
	}

	public String getSeason() {
		return leftYearLabel.getText() + "-" + rightYearLabel.getText();
	}

	private void setAction() {
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
