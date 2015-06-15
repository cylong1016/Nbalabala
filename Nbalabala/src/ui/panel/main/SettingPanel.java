/**
 * 
 */
package ui.panel.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPasswordField;
import javax.swing.border.Border;

import ui.Images;
import ui.MyFont;
import ui.common.button.ImgButton;
import ui.common.comboBox.MyComboBox;
import ui.common.panel.BottomPanel;
import ui.common.textField.MyTextField;
import utility.Constants;
import data.Database;
import data.seasondata.SeasonData;

/**
 *
 * @author Issac Ding
 * @since 2015年6月14日 下午8:37:58
 * @version 1.0
 */
public class SettingPanel extends BottomPanel{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 7784986737310630718L;

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
		
		userField.setSettingField();
		this.add(userField);
		
		pwField.setBounds(146, 188, 144, 30);
		pwField.setBackground(Color.LIGHT_GRAY);
		pwField.setBorder(null);
		pwField.setForeground(MyFont.DARK_GRAY);
		this.add(pwField);
		
		pathField.setSettingField();
		pathField.setText(Constants.dataSourcePath);
		this.add(pathField);
		
		languageBox.setBGColor(Color.LIGHT_GRAY);
		this.add(languageBox);
		
		String [] cacheSizes = new String[26];
		for (int i=5; i<=30; i++) {
			cacheSizes[i-5] = String.valueOf(i);
		}
		cacheBox = new MyComboBox(cacheSizes, 465, 188, 83, 30);
		cacheBox.setBGColor(Color.LIGHT_GRAY);
		this.add(cacheBox);
		
		browsButton.setLocation(267, 298);
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
		
		noButton.setLocation(474, 285);
		noButton.addActionListener(new ActionListener() {
			JDialog dialog = SettingPanel.this.dialog;
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		this.add(noButton);
		
		yesButton.setLocation(544, 285);
		yesButton.addActionListener(new ActionListener() {
			JDialog dialog = SettingPanel.this.dialog;
			@Override
			public void actionPerformed(ActionEvent e) {
				Constants.changeDataSourcePath(pathField.getText());
				new SeasonData().setSeasonCacheSize(cacheBox.getSelectedIndex() + 5);
				if (languageBox.getSelectedIndex() == 0) {
					Constants.setCHNSimplified();
				}else if (languageBox.getSelectedIndex() == 1){
					Constants.setCHNTraditional();
				}else {
					Constants.setEnglish();
				}
				Database.user = userField.getText();
				Database.password = String.valueOf(userField.getText());
				try {
					Statement statement = Database.conn.createStatement();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				dialog.dispose();
			}
		});
		this.add(yesButton);
		
	}
	

}
