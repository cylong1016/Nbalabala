package ui.common.button;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * 用图片填充的button
 * @author lsy
 * @version 2015年3月18日 下午10:03:07
 */
public class ImgButton extends JButton {

	/** serialVersionUID */
	private static final long serialVersionUID = -4616010940707699592L;

	public ImgButton(String imgPath, int x, int y, String stopImgPath, String pressOnImgPath) {
		ImageIcon imageIcon = new ImageIcon(imgPath);
		ImageIcon imageIconStop = new ImageIcon(stopImgPath);
		ImageIcon imageIconPressOn = new ImageIcon(pressOnImgPath);

		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		this.setIcon(imageIcon);
		this.setBounds(x, y, imageIcon.getImage().getWidth(null), imageIcon.getImage().getHeight(null));
		
		this.setRolloverIcon(imageIconStop);
		this.setPressedIcon(imageIconPressOn);
	}
}
