package ui.FatherPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

/**
 * 带有左边边框和底层的panel，其他具体功能panel的父类
 * @author lsy
 * @version 2015年3月17日  下午6:21:08
 */
public class FatherPanel extends BottomPanel{
	private static final long serialVersionUID = 7108387309547483359L;
	private JButton button;
	private LeftPanel leftPanel;
	
	public FatherPanel(String url){
		super(url);
		setButton();
		this.add(button);
		this.addLeftPanel();
		
	}
	
	public void addLeftPanel(){
		leftPanel = new LeftPanel("Image/2.png");
		this.add(leftPanel);
	}
	
	public void toLeftPanel(){
		leftPanel.moveIn();
		this.repaint();
	}
	
	public void outLeftPanel(){
		leftPanel.moveOut();
		this.repaint();
	}
	
	public void setButton(){
		
		button = new JButton();
		button.setContentAreaFilled(false);
		button.setBounds(0,0, 189, 600);
		button.setBorder(null);
		button.addMouseListener(new ButtonListener());
	}
	
	class ButtonListener implements MouseListener{

		public void mouseClicked(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			
		}

		public void mouseReleased(MouseEvent e) {
			
		}

		public void mouseEntered(MouseEvent e) {
			toLeftPanel();
		}

		public void mouseExited(MouseEvent e) {
			outLeftPanel();
		}
		
	}
}
