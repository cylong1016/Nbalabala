package ui.panel.analyse.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;

import ui.Images;
import ui.MyFont;
import ui.UIConfig;
import ui.common.jfreechart.LineChart;
import ui.common.label.MyLabel;
import ui.common.panel.Panel;
import ui.panel.analyse.button.TurnSelectButton;
import utility.Constants;
import vo.AnalysisTransferVO;
import bl.analysisbl.ValueAnalysis;
import blservice.AnalysisBLService;
import enums.InferenceData;

/**
 * 转会情况
 * 
 * @author lsy
 * @version 2015年6月8日 下午8:42:45
 */
public class TurnPanel extends Panel {

	/** serialVersionUID */
	private static final long serialVersionUID = 97048605908825431L;
	private LineChart chart;
	private TurnSelectButton[] button;
	private int bt_x = 31, bt_y = 16, inter_x = 100, width = 80, height = 30;
	private String name;
	private AnalysisBLService service = new ValueAnalysis();
	private JTextArea area;
	private MyLabel formerTeam, currentTeam;
	private MyLabel startSeason, transSeason;
	private AnalysisTransferVO vo;
	
	private Image bgImg = Images.TRANS_BG;
	
	/** 结论的纵坐标 */
	private static final int CONCLUSION_Y = UIConfig.CONCLUSION_Y;
	private static final int CONCLUSION_X = UIConfig.CONCLUSION_X;
	
	private static final int TEAM_WID = 100;
	private static final int TEAM_HEIGHT = 44;
	private static final int SEASON_WID = 179;

	public TurnPanel(String name) {
		this.name = name;
		vo = service.getTransferData(name, InferenceData.SCORE);
		if(vo == null) {
			bgImg = Images.NO_TRANS;
			//TODO 提示该球员没有转会
		}else{	
			
			addConclusion();
			
			chart = new LineChart(vo);

			this.add(chart);
			button = new TurnSelectButton[7];
			addButton();
			setEffect();
			
			addLabel();
			

		}
		this.setOpaque(false);
		this.setBounds(0, 100, 1000, 490);
		this.repaint();
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
		formerTeam = new MyLabel(vo.getFormerAbbr());
		formerTeam.setBounds(CONCLUSION_X, CONCLUSION_Y, TEAM_WID, TEAM_HEIGHT);
		formerTeam.setFont(MyFont.YT_M);
		formerTeam.setForeground(Color.WHITE);
		formerTeam.setCenter();
		this.add(formerTeam);
		
		currentTeam = new MyLabel(vo.getCurrentAbbr());
		currentTeam.setBounds(CONCLUSION_X, CONCLUSION_Y + TEAM_HEIGHT + 4, TEAM_WID, TEAM_HEIGHT);
		currentTeam.setFont(MyFont.YT_M);
		currentTeam.setForeground(Color.WHITE);
		currentTeam.setCenter();
		this.add(currentTeam);
		
		startSeason = new MyLabel(vo.startSeason + "  ~  " + vo.transferSeason);
		startSeason.setBounds(CONCLUSION_X + TEAM_WID, CONCLUSION_Y, SEASON_WID, TEAM_HEIGHT);
		startSeason.setCenter();
		this.add(startSeason);
		
		transSeason = new MyLabel(vo.transferSeason + "  ~  " + Constants.today);
		transSeason.setBounds(CONCLUSION_X + TEAM_WID, CONCLUSION_Y + TEAM_HEIGHT + 4, SEASON_WID, TEAM_HEIGHT);
		transSeason.setCenter();
		this.add(transSeason);
	}

	public void addButton() {
		for (int i = 0; i < 3; i++) {
			button[i] = new TurnSelectButton(bt_x + i * inter_x, bt_y, width, height, utility.Constants.ANY_SELECT[i]);
			button[i].setInferenceData(InferenceData.values()[i]);
			this.add(button[i]);
		}
		for (int i = 3; i < button.length; i++) {
			button[i] = new TurnSelectButton(bt_x + i * inter_x, bt_y, width, height, utility.Constants.ANY_SELECT[i + 3]);
			button[i].setInferenceData(InferenceData.values()[i + 3]);
			this.add(button[i]);
		}

		TurnSelectButton.current = button[0];
		addListener();
	}
	
	public void addListener() {
		for (int i = 0; i < button.length; i++) {
			button[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.getSource() == TurnSelectButton.current) {
						return;
					}
					TurnSelectButton.current.back();
					TurnSelectButton.current = (TurnSelectButton) e.getSource();
					TurnPanel.this.remove(chart);
					vo = service.getTransferData(name,TurnSelectButton.current.getInferenceData());
					chart = new LineChart(vo);
					area.setText(vo.getConclusion());
					TurnPanel.this.add(chart);
					TurnPanel.this.repaint();
				}

			});
		}
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
