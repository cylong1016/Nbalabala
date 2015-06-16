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

		set();

		this.setIcon(imageIcon);
		this.setBounds(x, y, imageIcon.getImage().getWidth(null), imageIcon.getImage().getHeight(null));
		this.setRolloverIcon(imageIconStop);
		this.setPressedIcon(imageIconPressOn);
		
	}
	
	public ImgButton(String imgPath, int x, int y, String stopImgPath) {
		this(imgPath, x, y, stopImgPath, stopImgPath);
		
	}
	
	public ImgButton(String imgPath, String pressOnImgPath) {
		this(imgPath, 0, 0, pressOnImgPath, pressOnImgPath);
	}

	public ImgButton() {

	}
	
	public ImgButton(ImageIcon normal, ImageIcon on) {
		set();
		this.setIcon(normal);
		this.setRolloverIcon(on);
		this.setPressedIcon(on);
		this.setSize(normal.getIconWidth(), normal.getIconHeight());
	}

	private void set() {
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
	}

}
