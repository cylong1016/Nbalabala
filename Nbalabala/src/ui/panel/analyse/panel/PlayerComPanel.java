package ui.panel.analyse.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextArea;

import ui.Images;
import ui.MyFont;
import ui.UIConfig;
import ui.common.comboBox.MyComboBox;
import ui.common.jfreechart.NewLineChart;
import ui.common.label.MyLabel;
import ui.common.panel.Panel;
import ui.panel.analyse.button.CompareButton;
import utility.Constants;
import vo.AnalysisCompareVO;
import bl.analysisbl.ValueAnalysis;
import blservice.AnalysisBLService;
import enums.InferenceData;

/**
 * 球员对比界面
 * @author lsy
 * @version 2015年6月16日  下午8:59:39
 */
public class PlayerComPanel extends Panel{

	/** serialVersionUID */
	private static final long serialVersionUID = 5065142245317348882L;
	private String name,name2;
	private AnalysisBLService service = new ValueAnalysis();
	private AnalysisCompareVO  vo;
	private Image bgImg;
	private NewLineChart chart;
	private CompareButton button[];
	private int bt_x = 31, bt_y = 16, inter_x = 100, width = 80, height = 30;
	private JTextArea area;
	private MyLabel formerTeam, currentTeam;
	private MyComboBox teamCom,playerCom;
	/** 结论的纵坐标 */
	private static final int CONCLUSION_Y = UIConfig.CONCLUSION_Y;
	private static final int CONCLUSION_X = UIConfig.CONCLUSION_X;
	
	private static final int TEAM_WID = 100;
	private static final int TEAM_HEIGHT = 44;
	private String[] str;
	
	public PlayerComPanel(String name) {
		this.name = name;
		str = changeArray(service.getLineupNamesByAbbr("BOS"));
		name2 = str[1];
		addComboBox();
		try {
			vo = service.getCompareData(name, name2,InferenceData.SCORE);
			if (vo == null) {
//				bgImg = Images.NO_DATA;
				//TODO 显示自己比赛太少无法显示
			} else {
				addConclusion();
				chart = new NewLineChart(vo);
				this.add(chart);
				button = new CompareButton[7];
				addButton();
				setEffect();
				addLabel();
			}
		} catch (Exception e) {
			// TODO 显示对方球员参加比赛太少无法比较
		}
		this.setOpaque(false);
		this.setBounds(0, 100, 1000, 490);
		this.repaint();
	}
	
	public void addComboBox(){
		teamCom = new MyComboBox(Constants.TEAM_NAMES,599,45,120,30);
		String[] strCom = changeArray(service.getLineupNamesByAbbr("BOS"));
		String[] showStr = new String[strCom.length];
		for(int i = 0 ; i < strCom.length; i++) {
			String[] name = strCom[i].split("\\$");
			showStr[i] = name[0];
		}
		playerCom = new MyComboBox(showStr,770,45,180,30);
		playerCom.setSelectedIndex(1);
		this.add(teamCom);
		teamCom.addActionListener(new ActionListener(){

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				playerCom.removeAllItems();
				str = changeArray(service.getLineupNamesByAbbr(Constants.TEAM_ABBR[teamCom.getSelectedIndex()]));
				for(int i = 0 ;i < str.length; i++) {
					String[] name = str[i].split("\\$");
					playerCom.addItem(name[0]);
				}
			}
			
		});
		
		playerCom.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = playerCom.getSelectedIndex();
				if(index == -1){
					index = 0;
				}
				name2 = str[index];
				System.out.println(name+name2+CompareButton.current.getInferenceData());
				try {
					vo = service.getCompareData(name, name2,CompareButton.current.getInferenceData());
					area.setText(vo.getConclusion());
					PlayerComPanel.this.remove(chart);
					chart = new NewLineChart(vo);
					PlayerComPanel.this.add(chart);
				} catch (Exception e) {
					// TODO 显示对方比赛太少无法比较
				}
				PlayerComPanel.this.repaint();
			}
			
		});
		teamCom.setBGColor(UIConfig.DARK_BUTTON_COLOR);
		playerCom.setBGColor(UIConfig.DARK_BUTTON_COLOR);
		this.add(playerCom);
	}
	
	private void addConclusion() {
		area = new JTextArea(vo.getConclusion());
		area.setLineWrap(true);
		area.setEditable(false);
		area.setBounds(CONCLUSION_X, CONCLUSION_Y + 200 ,200,200);
		area.setFont(MyFont.YH_B);
		area.setOpaque(false);
		this.add(area);
		
	}

	private void addLabel() {
		formerTeam = new MyLabel(vo.getThisName());
		formerTeam.setBounds(CONCLUSION_X, CONCLUSION_Y, TEAM_WID, TEAM_HEIGHT);
		formerTeam.setFont(MyFont.YT_M);
		formerTeam.setForeground(Color.WHITE);
		formerTeam.setCenter();
		this.add(formerTeam);
		
		currentTeam = new MyLabel(vo.getThatName());
		currentTeam.setBounds(CONCLUSION_X, CONCLUSION_Y + TEAM_HEIGHT + 4, TEAM_WID, TEAM_HEIGHT);
		currentTeam.setFont(MyFont.YT_M);
		currentTeam.setForeground(Color.WHITE);
		currentTeam.setCenter();
		this.add(currentTeam);
		
//		startSeason = new MyLabel(vo.startSeason + "  ~  " + vo.transferSeason);
//		startSeason.setBounds(CONCLUSION_X + TEAM_WID, CONCLUSION_Y, SEASON_WID, TEAM_HEIGHT);
//		startSeason.setCenter();
//		this.add(startSeason);
//		
//		transSeason = new MyLabel(vo.transferSeason + "  ~  " + Constants.today);
//		transSeason.setBounds(CONCLUSION_X + TEAM_WID, CONCLUSION_Y + TEAM_HEIGHT + 4, SEASON_WID, TEAM_HEIGHT);
//		transSeason.setCenter();
//		this.add(transSeason);
	}

	public void addButton() {
		for (int i = 0; i < 3; i++) {
			button[i] = new CompareButton(bt_x + i * inter_x, bt_y, width, height, utility.Constants.ANY_SELECT[i]);
			button[i].setInferenceData(InferenceData.values()[i]);
			this.add(button[i]);
		}
		for (int i = 3; i < button.length; i++) {
			button[i] = new CompareButton(bt_x + i * inter_x, bt_y, width, height, utility.Constants.ANY_SELECT[i + 3]);
			button[i].setInferenceData(InferenceData.values()[i + 3]);
			this.add(button[i]);
		}

		CompareButton.current = button[0];
		addListener();
	}
	
	public void addListener() {
		for (int i = 0; i < button.length; i++) {
			button[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.getSource() == CompareButton.current) {
						return;
					}
					CompareButton.current.back();
					CompareButton.current = (CompareButton) e.getSource();
					PlayerComPanel.this.remove(chart);
					try {
						vo = service.getCompareData(name,name2,CompareButton.current.getInferenceData());
						chart = new NewLineChart(vo);
						area.setText(vo.getConclusion());
						PlayerComPanel.this.add(chart);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
//						e1.printStackTrace();
						//TODO 显示对方比赛太少无法比较
					}
					PlayerComPanel.this.repaint();
				}

			});
		}
	}

	public String[] changeArray(ArrayList<String> arrayList){
		int lth = arrayList.size();
		String[] str = new String[lth];
		for(int i = 0 ;i < lth; i++){
			str[i] = arrayList.get(i);
		}
		return str;
	}
	
	public void setEffect() {
		button[0].setOpaque(true);
		button[0].setBackground(UIConfig.BUTTON_COLOR);
		button[0].setForeground(Color.white);
	}
	
	public void paint(Graphics g) {
		g.drawImage(bgImg, 0, 0, this);
		// g2d.drawImage(slider, 0, 0, this);
		super.paint(g);
	}

}
