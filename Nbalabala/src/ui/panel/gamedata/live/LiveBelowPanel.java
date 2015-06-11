package ui.panel.gamedata.live;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.Images;
import ui.common.button.TabButton;
import ui.common.label.MyLabel;
import ui.common.panel.BottomPanel;
import utility.Constants;
import vo.MatchDetailVO;

/**
 * 文字直播下半部分的panel
 * 
 * @author lsy
 * @version 2015年6月8日 下午9:23:42
 */
public class LiveBelowPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 3575553054783466861L;
	private MatchDetailVO vo;
	private TabButton[] tab;
	private MyLabel playerlb_1,playerlb_2;

	public LiveBelowPanel(Image bg) {
		super(bg);
		this.setBounds(22, 292, 952, 309);
		addButton();
//		addLabel();
	}
	
	public void addLabel(){
		playerlb_1 = new MyLabel(137,48,100,34,Constants.playerOnCourt);
		playerlb_2 = new MyLabel(825,48,100,34,Constants.playerOnCourt);
		playerlb_1.setForeground(Color.white);
		playerlb_2.setForeground(Color.white);
		this.add(playerlb_1);
		this.add(playerlb_2);
	}

	public void addButton() {
		tab = new TabButton[5];
		for (int i = 0; i < 5; i++) {
			tab[i] = new TabButton(Constants.LIVE_TEXT[i], Images.SECTION_ON, Images.SECTION_CLICK);
			tab[i].setLocation(20, 18 + i * 34);
			this.add(tab[i]);
		}
		tab[0].setLocation(20, 10);
		tab[0].setOn();
		
		tab[0].addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 tab[0].setOn();
				 tab[1].setOff();
				 tab[2].setOff();
				 tab[3].setOff();
				 tab[4].setOff();
			 }
		});
		tab[1].addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 tab[1].setOn();
				 tab[0].setOff();
				 tab[2].setOff();
				 tab[3].setOff();
				 tab[4].setOff();
			 }
		});
		tab[2].addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 tab[2].setOn();
				 tab[1].setOff();
				 tab[0].setOff();
				 tab[3].setOff();
				 tab[4].setOff();
			 }
		});
		tab[3].addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 tab[3].setOn();
				 tab[1].setOff();
				 tab[2].setOff();
				 tab[0].setOff();
				 tab[4].setOff();
			 }
		});
		tab[4].addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 tab[4].setOn();
				 tab[1].setOff();
				 tab[2].setOff();
				 tab[0].setOff();
				 tab[3].setOff();
			 }
		});
	}
}
