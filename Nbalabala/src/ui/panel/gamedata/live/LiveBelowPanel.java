package ui.panel.gamedata.live;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.Images;
import ui.common.button.TabButton;
import ui.common.label.MyLabel;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import utility.Constants;

/**
 * 文字直播下半部分的panel
 * 
 * @author lsy
 * @version 2015年6月8日 下午9:23:42
 */
public class LiveBelowPanel extends BottomPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 3575553054783466861L;
	private TabButton[] tab;
	private MyLabel playerlb_1,playerlb_2;
	private String teamAbbr1,teamAbbr2;
	private LiveDetailTable table ;
	
	public LiveBelowPanel(String teamAbbr1,String teamAbbr2,ArrayList<String> text,Image bg) {
		super(bg);
		this.setBounds(22, 292, 952, 309);
		this.teamAbbr1 = teamAbbr1;
		this.teamAbbr2 = teamAbbr2;
	    table = new LiveDetailTable(teamAbbr1,teamAbbr2,text);
		addButton();
		setTable(text);
	}
	
	public void addLabel(){
		playerlb_1 = new MyLabel(137,48,100,34,Constants.playerOnCourt);
		playerlb_2 = new MyLabel(825,48,100,34,Constants.playerOnCourt);
		playerlb_1.setForeground(Color.white);
		playerlb_2.setForeground(Color.white);
		this.add(playerlb_1);
		this.add(playerlb_2);
	}
	
	private BottomScrollPane scroll;

	public void setTable(ArrayList<String> text) {
		scroll = new BottomScrollPane(table);
		scroll.setBounds(160,10,770,300);
		this.add(scroll);
	}
	
	public void updateTable(ArrayList<String> text) {
		table.setTable(text);
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
