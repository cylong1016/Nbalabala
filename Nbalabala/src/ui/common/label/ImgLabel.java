package ui.common.label;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 
 * @author lsy
 * @version 2015年3月22日  下午6:06:56
 */
public class ImgLabel extends JLabel{

	
	public ImgLabel(int x, int y, String url) {
		ImageIcon imageIcon = new ImageIcon(url);
		this.setOpaque(false);
		this.setIcon(imageIcon);
		this.setBounds(x, y, imageIcon.getImage().getWidth(null), imageIcon.getImage().getHeight(null));
	}
}
