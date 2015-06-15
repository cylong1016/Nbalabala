 package ui.common.comboBox;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicArrowButton;
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
		this.setBorder(BorderFactory.createEmptyBorder());
		
		this.setBackground(UIConfig.BUTTON_COLOR);
		this.setForeground(Color.white);
		this.setFocusable(false);
		
		this.setUI(new BasicComboBoxUI() {
	            public void installUI(JComponent comboBox) {
	                super.installUI(comboBox);
	                listBox.setOpaque(false);
	                
	                listBox.setSelectionBackground(Color.white);
	                listBox.setSelectionForeground(UIConfig.BUTTON_COLOR);
	            }
	            
	            protected JButton createArrowButton() {            
	            	JButton button = new BasicArrowButton(5);   
	            	button.setOpaque(false);
	            	button.setBorderPainted(false);
	            	button.setBorder(BorderFactory.createEmptyBorder());
//	            	button.setBorder(UIConfig.BUTTON_COLOR);
	            	button.setFocusable(false);
	            	button.setBackground(UIConfig.BUTTON_COLOR);
	            	return button;        
	            	}   
	        });
		this.repaint();
	}
	
	public void setBGColor(final Color color){
		this.setBackground(color);
		this.setUI(new BasicComboBoxUI() {
            public void installUI(JComponent comboBox) {
                super.installUI(comboBox);
                listBox.setOpaque(false);
                
                listBox.setSelectionBackground(Color.white);
                listBox.setSelectionForeground(color);
            }
            
            protected JButton createArrowButton() {            
            	JButton button = new BasicArrowButton(5);   
            	button.setOpaque(false);
            	button.setBorder(BorderFactory.createLineBorder(color)); // 设置按钮边框的颜色
//            	button.setBorder(UIConfig.BUTTON_COLOR);
            	button.setFocusable(false);
            	button.setBackground(color);
            	return button;        
            	}   
        });
	}
	
}
