package ui.panel.gamedata.live;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import ui.common.label.MyLabel;
import ui.common.panel.BottomPanel;
import ui.controller.MainController;
import utility.Constants;
import bl.livebl.Live;
import blservice.LiveBLService;

/**
 * 直播初始界面
 * @author lsy
 * @version 2015年6月13日  上午10:59:47
 */
public class LiveInPanel extends BottomPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = -4213351061854735529L;
	private LiveBLService live;
	private MyLabel date,time;
	private MyLabel[]gameDetail;
	private int index = 0;
	private int[] gameDetail_x = {205,325,405,480,620,750};
	
	//06月15日 08：00=季后赛 勇士-骑士, 06月17日 09：00=季后赛 骑士-勇士
	public LiveInPanel(String url){
		super(url);
		live = new Live();
		gameDetail = new MyLabel[6];
		live.refresh();
		readHash();
	}
	
	public void readHash() {
		HashMap<String, String>  liveList = live.getLiveList();
		Set<String> keySet = liveList.keySet();
		Iterator<String> iter = keySet.iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			String value = liveList.get(key);
			String[] keyArr = key.split(" ");
			String[] valueArr = value.split(" ");
			final String[] team = valueArr[1].split("-");
			String[] line2 = {valueArr[0],team[0],"VS",team[1],Constants.history,Constants
					.waitlive};
			for(int i = 0; i < 6; i++) {
				gameDetail[i] = new MyLabel(gameDetail_x[i],140+81*index,100,50,line2[i]);
				this.add(gameDetail[i]);
				gameDetail[i].setFont(new Font("微软雅黑",0,16));
			}
			gameDetail[1].setForeground(Color.blue);
			gameDetail[3].setForeground(Color.blue);
			gameDetail[4].setForeground(Color.orange);
			gameDetail[5].setForeground(Color.gray);
			gameDetail[1].addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					 MainController.toTeamBottomPanel(LiveInPanel.this, Constants.getAbbrByName(team[0]));;
				 }
			});
			gameDetail[3].addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					 MainController.toTeamBottomPanel(LiveInPanel.this, Constants.getAbbrByName(team[1]));
				 }
			});
			
			gameDetail[4].addMouseListener(new MouseAdapter(){
				 public void mouseClicked(MouseEvent e) {
					 MainController.toGameLivePanel(LiveInPanel.this,Constants.getAbbrByName(team[0]),Constants.getAbbrByName(team[1]));
				 }
			});
			
			date = new MyLabel(25,110 + 81 * index,100,30,keyArr[0]);
			time = new MyLabel(25,140+81*index,100,50,keyArr[1]);
			this.add(date);
			this.add(time);
			index ++;
			
			if(live.hasMatchStarted() && index == 1) {
				gameDetail[5].setlbText(Constants.tolive);
				gameDetail[5].setForeground(Color.orange);
				gameDetail[5].addMouseListener(new MouseAdapter(){
					 public void mouseClicked(MouseEvent e) {
						 MainController.toLivePanel();
					 }
				});
			}
		}
	}

}
