package ui.panel.allplayers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.common.button.TextButton;

/**
 * 字母button
 * @author lsy
 * @version 2015年3月20日  下午4:43:16
 */
public class LetterButton extends TextButton{

	/** serialVersionUID */
	private static final long serialVersionUID = -372944070418167926L;
	
	private static final Font LETTER_FONT = new Font("微软雅黑", 1, 15);

	/** 当前按钮代表哪个字母 */
	char letter;
	static final Color LETTER_BG = new Color(51, 66, 84);
	static final Color LETTER_COLOR = new Color(38, 41, 46);
	
	private boolean isPressed = false;
	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param text
	 * @author lsy
	 * @version 2015年3月20日  下午4:43:30
	 */
	public LetterButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
		this.setFont(LETTER_FONT);
		this.setMargin(new Insets(0,0,0,0));
	}

	
	public void addListener(){
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				isPressed = true;
				LetterButton.this.setOpaque(true);
				LetterButton.this.setBackground(LETTER_BG);
			}
			public void mouseEntered(MouseEvent e) {
				LetterButton.this.setOpaque(true);
				LetterButton.this.setBackground(LETTER_BG);
			}
			public void mouseExited(MouseEvent e){
				if (isPressed) {
					return;
				}
				LetterButton.this.setOpaque(false);
			}
		});
		
	}
	public void back() {
		isPressed = false;
		this.setOpaque(false);
		this.setBackground(null);
	}
	
	public void setCurrent(){
		isPressed = true;
		this.setOpaque(true);
		this.setBackground(LETTER_BG);
	}
}
