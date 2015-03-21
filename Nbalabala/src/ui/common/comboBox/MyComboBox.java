package ui.common.comboBox;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicComboBoxUI;

import ui.UIConfig;

/**
 * 下拉框
 * @author lsy
 * @version 2015年3月21日  下午2:09:53
 */
@SuppressWarnings("rawtypes")
public class MyComboBox extends JComboBox{
	/** serialVersionUID */
	private static final long serialVersionUID = -102182186566866213L;

	@SuppressWarnings("unchecked")
	public MyComboBox(String [] roleList,int x,int y,int width,int height) {
		super(roleList);
		this.setBounds(x, y, width, height);
		this.setSelectedItem(roleList[0]);
		this.setOpaque(false);

		this.setFont(UIConfig.FONT);
		
		this.setBackground(UIConfig.BUTTON_COLOR);
		this.setForeground(Color.white);
		
		this.setUI(new BasicComboBoxUI() {
	            public void installUI(JComponent comboBox) {
	                super.installUI(comboBox);
	                listBox.setOpaque(false);
	                listBox.setForeground(Color.white);
	                
	                listBox.setSelectionBackground(Color.white);
	                listBox.setSelectionForeground(UIConfig.BUTTON_COLOR);
	                
	            }
	             
	            /**
	             * 该方法返回右边的按钮
	             */
	        });
		this.repaint();
	}
}
