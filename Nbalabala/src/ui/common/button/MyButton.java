package ui.common.button;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * 用图片填充的button
 * @author lsy
 * @version 2015年3月18日  下午10:03:07
 */
public class MyButton extends JButton{
	/** 横坐标 */
	private int x;
	/** 纵坐标 */
	private int y;
	
	public MyButton(String image,int x,int y,String stopImage,String press_onImage) {
		this.x = x;
		this.y = y;
		
		ImageIcon imageIcon = new ImageIcon(image);
		ImageIcon imageIconstop = new ImageIcon(stopImage);
		ImageIcon imageIconpresson = new ImageIcon(press_onImage);
		
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setIcon(imageIcon);
		this.setBounds(x, y, imageIcon.getImage().getWidth(null),
				imageIcon.getImage().getHeight(null));	
		this.setVisible(true);
		
		this.setRolloverIcon(imageIconstop);
		this.setPressedIcon(imageIconpresson);
		
		
		
	}
}
