package ui.panel.gamedata.live;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.Images;
import ui.MyFont;
import ui.UIConfig;
import ui.common.button.TabButton;
import ui.common.label.MyLabel;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import utility.Constants;
import bl.livebl.Live;
import blservice.LiveBLService;

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
	private LiveDetailTable table ;
	private ArrayList<String> text;
	private int currentI;
	private LiveBLService liveService;
	
	public LiveBelowPanel(String teamAbbr1,String teamAbbr2,ArrayList<String> text,Image bg) {
		super(bg);
		this.text = text;
		this.setBounds(UIConfig.RELA_X-4, 250, 948, 309);
		liveService = new LiveMock();
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
		table.setHeaderColorAndFont();
		table.setHeaderHeight(UIConfig.TABLE_HEADER_HEIGHT);
		table.setWidth(new int[] {60, 300, 90, 315});
		scroll = new BottomScrollPane(table);
		scroll.setBounds(162-26, 10, 788, 300);
		this.add(scroll);
	}
	
	public void updateTable(ArrayList<String> text) {
		this.text = text;
		if(currentI == 0) {
			table.setTable(text);
		} else if(currentI == liveService.getCurrentSectionCount()){
			findSection();
			int begin = 0, end = 0;
			switch(currentI){
			case 1:
				begin = section[0];
				end = text.size();
				break;
			case 2:
				begin = section[1];
				end = section[0];
				break;
			case 3:
				begin = section[2];
				end = section[1];
				break;
			case 4:
				begin = section[3];
				end = section[2];
				break;
			}
			 subArray = new ArrayList<String>();
			 for(int i = begin ;i < end; i++) {
				 subArray.add(text.get(i));
			 }
			 seeSectionTable(subArray);	
		}
	}
	
	public void seeSectionTable(ArrayList<String> text){
		table.setTable(text);
	}

	private int[] section;
	public void findSection(){
		section = new int[4];
		for(int i = 0; i < text.size(); i++) {
			if(text.get(i).equals("第1节结束")) {
				section[0] = i;
			}else if(text.get(i).equals("第2节结束")) {
				section[1] = i;
			}else if(text.get(i).equals("第3节结束")) {
				section[2] = i;
			}else if(text.get(i).equals("第4节结束")) {
				section[3] = i;
			}
			
		}
//		System.out.println(section[0]+" "+section[1]+" "+section[2]+" "+section[3]);
	}
	
	private ArrayList<String> subArray;
	public void addButton() {
		tab = new TabButton[5];
		for (int i = 0; i < 5; i++) {
			tab[i] = new TabButton(Constants.LIVE_TEXT[i], Images.SECTION_ON, Images.SECTION_CLICK);
			tab[i].setLocation(20, 18 + i * 34);
			tab[i].setFont(MyFont.YH_S);
			this.add(tab[i]);
		}
		tab[0].setLocation(20, 10);
		tab[0].setOn();
		
		tab[0].addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 currentI = 0;
				 tab[0].setOn();
				 tab[1].setOff();
				 tab[2].setOff();
				 tab[3].setOff();
				 tab[4].setOff();
				 findSection();
				 seeSectionTable(text);
			 }
		});
		tab[1].addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 currentI = 1;
				 tab[1].setOn();
				 tab[0].setOff();
				 tab[2].setOff();
				 tab[3].setOff();
				 tab[4].setOff();
				 findSection();
				 subArray = new ArrayList<String>();
				 for(int i = section[0] ;i < text.size(); i++) {
					 subArray.add(text.get(i));
				 }
				 seeSectionTable(subArray);
			 }
		});
		tab[2].addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 currentI = 2;
				 tab[2].setOn();
				 tab[1].setOff();
				 tab[0].setOff();
				 tab[3].setOff();
				 tab[4].setOff();
				 findSection();
				 subArray = new ArrayList<String>();
				 for(int i = section[1] ;i < section[0]; i++) {
					 subArray.add(text.get(i));
				 }
				 seeSectionTable(subArray);
			 }
		});
		tab[3].addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 currentI = 3;
				 tab[3].setOn();
				 tab[1].setOff();
				 tab[2].setOff();
				 tab[0].setOff();
				 tab[4].setOff();
				 subArray = new ArrayList<String>();
				 for(int i = section[2] ;i < section[1]; i++) {
					 subArray.add(text.get(i));
				 }
				 seeSectionTable(subArray);
			 }
		});
		tab[4].addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e) {
				 currentI = 4;
				 tab[4].setOn();
				 tab[1].setOff();
				 tab[2].setOff();
				 tab[0].setOff();
				 tab[3].setOff();
				 findSection();
				 subArray = new ArrayList<String>();
				 for(int i = section[3] ;i < section[2]; i++) {
					 subArray.add(text.get(i));
				 }
				 seeSectionTable(subArray); 
			 }
		});
	}
}
