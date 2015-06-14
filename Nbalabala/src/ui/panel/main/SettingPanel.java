/**
 * 
 */
package ui.panel.main;

import java.awt.Point;

import javax.swing.JDialog;
import javax.swing.JPasswordField;

import data.Database;
import ui.Images;
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
		
		
		
	}
	

}
