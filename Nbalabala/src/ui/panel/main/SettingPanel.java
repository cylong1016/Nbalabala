/**
 * 
 */
package ui.panel.main;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPasswordField;

import data.Database;
import ui.Images;
import ui.common.button.ImgButton;
import ui.common.comboBox.MyComboBox;
import ui.common.panel.BottomPanel;
import ui.common.textField.MyTextField;
import utility.Constants;

/**
 *
 * @author Issac Ding
 * @since 2015年6月14日 下午8:37:58
 * @version 1.0
 */
public class SettingPanel extends BottomPanel{
	
	private JDialog dialog;
	
	private MyTextField userField = new MyTextField(146, 141, 144, 30);
	private JPasswordField pwField = new JPasswordField(Database.password);
	private MyTextField pathField = new MyTextField(70, 292, 184, 30);
	private String [] languages = {"简体", "繁体", "Eng"};
	private MyComboBox languageBox = new MyComboBox(languages, 465, 141, 83, 30);
	private MyComboBox cacheBox;
	private ImgButton browsButton = new ImgButton(Images.BROWS_BUTTON, Images.BROWS_BUTTON_ON);
	private ImgButton yesButton = new ImgButton(Images.YES_BUTTON, Images.YES_BUTTON_ON);
	private ImgButton noButton = new ImgButton(Images.NO_BUTTON, Images.NO_BUTTON_ON);
	
	public SettingPanel(JDialog dialog) {
		super(Images.SETTING_BG);
		this.dialog = dialog;
		this.setLayout(null);
		setSize(Images.SETTING_BG.getWidth(null), Images.SETTING_BG.getHeight(null));
		this.add(userField);
		pwField.setBounds(146, 188, 144, 30);
		this.add(pwField);
		this.add(pathField);
		this.add(languageBox);
		
		String [] cacheSizes = new String[26];
		for (int i=5; i<=30; i++) {
			cacheSizes[i-5] = String.valueOf(i);
		}
		cacheBox = new MyComboBox(languages, 465, 188, 83, 30);
		this.add(cacheBox);
		
		browsButton.setLocation(267, 291);
		browsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setDialogTitle("选择数据文件夹");
				int result = chooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					String path = chooser.getSelectedFile().getAbsolutePath();
					pathField.setText(path);
				}
			}
		});
		this.add(browsButton);
		
		noButton.setLocation(441, 292);
		noButton.addActionListener(new ActionListener() {
			JDialog dialog = SettingPanel.this.dialog;
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		this.add(noButton);
		
		yesButton.setLocation(544, 292);
		yesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	

}