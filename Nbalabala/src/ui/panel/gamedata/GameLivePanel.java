package ui.panel.gamedata;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.Images;
import ui.MyFont;
import ui.UIConfig;
import ui.common.button.TabButton;
import ui.common.panel.BottomPanel;
import ui.common.table.BottomScrollPane;
import ui.panel.gamedata.live.LiveDetailTable;
import utility.Constants;
import vo.MatchDetailVO;
import bl.matchquerybl.MatchQuery;
import blservice.MatchQueryBLService;

/**
 * 
 * @author lsy
 * @version 2015年6月16日  下午6:16:39
 */
public class GameLivePanel extends BottomPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = 3242514111661642405L;
	private TabButton[] tab;
	private LiveDetailTable table ;
	private ArrayList<String> text;
	private int currentI;
	private MatchQueryBLService service;
	
	public GameLivePanel(MatchDetailVO vo) {
		super(Images.LIVE_BELOW);
		this.setBounds(UIConfig.RELA_X-4, 250, 948, 309);
		service = new MatchQuery();
		String[] team = vo.getProfile().getTeam().split("-");
		Date date = vo.getProfile().getDate();
		text = service.getLives(date, team[1]);
		findSection();
//		System.out.println(team[0]+" "+text.size());
		table = new LiveDetailTable(team[0],team[1],text);
		addButton();
		setTable(text);
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
		} 
//		else if(currentI == liveService.getCurrentSectionCount()){
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
//		}
	}
	
	public void seeSectionTable(ArrayList<String> text){
		table.setTable(text);
	}

	private int[] section;
	public void findSection(){
		section = new int[4];
		for(int i = 0; i < text.size(); i++) {
			if(text.get(i).equals("End of 1st quarter")) {
				section[0] = i;
			}else if(text.get(i).equals("End of 2nd quarter")) {
				section[1] = i;
			}else if(text.get(i).equals("End of 3rd quarter")) {
				section[2] = i;
			}else if(text.get(i).equals("End of 4th quarter")) {
				section[3] = i;
			}
			
		}
		System.out.println(section[0]+" "+section[1]+" "+section[2]+" "+section[3]);
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
				 subArray = new ArrayList<String>();
				 for(int i = 0 ;i < section[0]+1; i++) {
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
				 subArray = new ArrayList<String>();
				 for(int i = section[0]+1 ;i < section[1]+1; i++) {
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
				 for(int i = section[1]+1 ;i < section[2]+1; i++) {
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
				 subArray = new ArrayList<String>();
				 for(int i = section[2]+1 ;i < text.size(); i++) {
					 subArray.add(text.get(i));
				 }
				 seeSectionTable(subArray); 
			 }
		});
	}

}
