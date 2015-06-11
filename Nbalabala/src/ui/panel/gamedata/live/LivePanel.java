package ui.panel.gamedata.live;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.Images;
import ui.common.button.TabButton;
import ui.common.panel.ScorePanel;
import ui.panel.gamedata.ConPanel;
import ui.panel.gamedata.GameFatherPanel;
import utility.Constants;
import vo.LivePlayerVO;
import vo.MatchDetailVO;
import bl.livebl.Live;
import blservice.LiveBLService;

/**
 * 直播界面
 * 
 * @author lsy
 * @version 2015年6月8日 下午9:07:28
 */
public class LivePanel extends GameFatherPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -1777953751279202293L;
	private TabButton tech, live, contrastbt;
	private LiveBelowPanel liveBelow;
	private LiveBLService liveService;
	private ConPanel conPanel;
	private TechPanel techPanel;
	private ArrayList<LivePlayerVO> vo1,vo2;
	private int currentI;
	private ArrayList<String> text;
	private double[] homePlayersArgs,roadPlayersArgs;

	
	public LivePanel(String url) {
		super(url);
		liveService = new Live();
//		testData();
		init();
		text = liveService.getTextLive();
		vo1 = liveService.getHomePlayerRecords();
		vo2 = liveService.getRoadPlayerRecords();
		homePlayersArgs = liveService.getHomeFiveArgs();
		roadPlayersArgs = liveService.getroadFiveArgs();
		techPanel = new TechPanel(vo1,vo2,LivePanel.this);
		liveBelow = new LiveBelowPanel(text,Images.LIVE_BELOW,LivePanel.this);
		conPanel = new ConPanel(homePlayersArgs,roadPlayersArgs);
		this.add(techPanel);
		currentI = 0;
		ThreadDis thread = new ThreadDis();
		thread.start();
	}
	
	public void init(){
		ArrayList<Integer> roadScores = liveService.getRoadScores();
		ArrayList<Integer> homeScores = liveService.getHomeScores();
		int size = homeScores.size();
		while(size < 4) {
			roadScores.add(0);
			homeScores.add(0);
			size++;
		}
		String homeName = liveService.getHomeAbbr();
		String roadName = liveService.getRoadAbbr();
		String total = roadScores.get(size-1).toString() +"-"+homeScores.get(size-1).toString();
		String each = "";
		for(int i = 0; i < size-1;i++){
			each = each + homeScores.get(i)+"-"+roadScores.get(i)+";";
		}
		
		String[] totalScore = new String[]{roadScores.get(size-1).toString(),homeScores.get(size-1).toString()};
		addLabel(homeName,roadName,totalScore);
		scPanel = new ScorePanel(homeName,roadName,total,each);
		this.add(scPanel);
		scPanel.setLocation(188, 110);
		this.repaint();
		addButton();
		
	}
//	double[] homePlayersArgs = {1,2,3,4,5};
//	double[] roadPlayersArgs = {5,4,3,2,1};
//	String[] time = {"HELLO","WORLD","CYL","LSY","ALLEN"};
//	int[] made ={10,13,15,16,18};
//	ArrayList<Integer> roadScores = new ArrayList<Integer>();
//	ArrayList<Integer> homeScores = new ArrayList<Integer>();
//	String homeName = "PHI",roadName = "LAL";
	
//	String timePlayed,int fieldMade,int fieldAttempt,int threepointMade,int threepointAttempt
//	,int freethrowMade,int freethrowAttempt,int totalRebound,int assist,int steal,int block,int turnover,
//	int foul,int score,int plusMinus
//	ArrayList<String> text;
//	public void testData(){
//		vo1 = new ArrayList<LivePlayerVO>();
//		vo2 = new ArrayList<LivePlayerVO>();
//		text = new ArrayList<String>();
//		text.add("10:36;骑士;詹姆斯罚球不中;0-2");
//		text.add("第一小节结束");
//		text.add("11:00;骑士;詹姆斯扣篮球进;0-4");
//		text.add("骑士换人");
//		for(int i = 0;i<5;i++){
//			vo1.add(new LivePlayerVO(Constants.TEAM_ABBR[0],Constants.TEAM_ABBR[0],"P",
//					true,time[i],made[i],0,0,0,0,0,0,0,0,0,0,0,0,0)) ;
//			vo2.add(new LivePlayerVO(Constants.TEAM_ABBR[1],Constants.TEAM_ABBR[1],"R",
//					true,time[i],made[i],1,1,1,1,1,1,1,1,1,1,1,1,1));
//			roadScores.add(i);
//			homeScores.add(4-i);
//		}
//		roadScores.add(20);
//		homeScores.add(30);
//	}
//	

	public void addButton() {
		tech = new TabButton(Constants.LIVE[0], Images.PLAYER_TAB_MOVE_ON, Images.PLAYER_TAB_CHOSEN);
		live = new TabButton(Constants.LIVE[1], Images.PLAYER_TAB_MOVE_ON, Images.PLAYER_TAB_CHOSEN);
		contrastbt = new TabButton(Constants.LIVE[2], Images.PLAYER_TAB_MOVE_ON, Images.PLAYER_TAB_CHOSEN);
		tech.setLocation(btx, bty);
		live.setLocation(btx + inter, bty);
		contrastbt.setLocation(btx + 2 * inter, bty);
		tech.setOn();
		this.add(tech);
		this.add(live);
		this.add(contrastbt);
		tech.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				tech.setOn();
				live.setOff();
				contrastbt.setOff();
				currentI = 0;
				LivePanel.this.remove(liveBelow);
				LivePanel.this.remove(conPanel);
				LivePanel.this.add(techPanel);
				LivePanel.this.repaint();
			}
		});
		live.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				tech.setOff();
				live.setOn();
				contrastbt.setOff();
				currentI = 1;
				LivePanel.this.remove(conPanel);
				LivePanel.this.remove(techPanel);
				LivePanel.this.add(liveBelow);
				LivePanel.this.repaint();
			}
		});

		contrastbt.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				tech.setOff();
				live.setOff();
				contrastbt.setOn();
				currentI = 2;
				LivePanel.this.remove(liveBelow);
				LivePanel.this.remove(techPanel);
				LivePanel.this.add(conPanel);
				LivePanel.this.repaint();
			}
		});
	}
	
	int index = 0;
	public void refresh(){
			if(currentI == 0) {
				this.remove(techPanel);
			} else if(currentI == 1) {
				this.remove(liveBelow);
			} else{
				this.remove(conPanel);
			}
		index ++;
		conPanel = new ConPanel(liveService.getHomeFiveArgs(),liveService.getroadFiveArgs());
		techPanel = new TechPanel(liveService.getHomePlayerRecords(),liveService.getRoadPlayerRecords(),
				LivePanel.this);
//		if(index % 2 == 0){
//			techPanel = new TechPanel(vo1,vo2,LivePanel.this);
//			conPanel = new ConPanel(homePlayersArgs,roadPlayersArgs);
//		}else{
//			techPanel = new TechPanel(vo2,vo1,LivePanel.this);
//			conPanel = new ConPanel(roadPlayersArgs,homePlayersArgs);
//		}
//		text.add("10:36;骑士;詹姆斯罚球不中;0-2");
		liveBelow = new LiveBelowPanel(liveService.getTextLive(),Images.LIVE_BELOW,LivePanel.this);
		if(currentI == 0) {
			this.add(techPanel);
		} else if(currentI == 1) {
			this.add(liveBelow);
		} else{
			this.add(conPanel);
		}
		this.repaint();
	}

	class ThreadDis extends Thread {

		public void run() {
			while (true) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
					liveService.refresh();
					refresh();
			}
		}
	}

}
