package ui.panel.analyse.panel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.UIConfig;
import ui.common.jfreechart.BarChart;
import ui.common.panel.Panel;
import ui.panel.analyse.button.LastFiveButton;
import ui.panel.analyse.button.LastFiveButton;
import vo.AnalysisCareerVO;
import bl.analysisbl.ValueAnalysis;
import blservice.AnalysisBLService;
import enums.CareerData;

/**
 * 最后五分钟底下的界面
 * @author lsy
 * @version 2015年6月8日  下午8:34:49
 */
public class LastFivePanel extends Panel{

	/** serialVersionUID */
	private static final long serialVersionUID = -5007878982571604781L;
	private String name;
	private BarChart chart;
	private LastFiveButton[] button;
	private int bt_x = 31, bt_y = 16, inter_x = 100, width = 80, height = 30;
	private AnalysisBLService service = new ValueAnalysis();
	private ArrayList<AnalysisCareerVO> vo;
	
	public LastFivePanel(String name){
		this.name = name;
		vo = service.getCareerData(name, CareerData.SCORE);
		if (vo == null) {
			//TODO 显示没有走向分析
		} else {
//			chart = new BarChart(vo);
//			this.add(chart);
			button = new LastFiveButton[6];
			addButton();
			setEffect();
		}
		this.setBounds(0, 100, 1000, 490);
		this.repaint();
	}
	
	public void addButton() {
		for (int i = 0; i < 6; i++) {
			button[i] = new LastFiveButton(bt_x + i * inter_x, bt_y, width, height, utility.Constants.ANY_SELECT[i]);
			this.add(button[i]);
		}
		LastFiveButton.current = button[0];
		addListener();
	}
	
	public void addListener() {
		for (int i = 0; i < button.length; i++) {
			button[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.getSource() == LastFiveButton.current) {
						return;
					}
					LastFiveButton.current.back();
					LastFiveButton.current = (LastFiveButton) e.getSource();
//					LastFivePanel.this.remove(chart);
//					vo = service.getDevotionData(name);
//					chart = new BoxChart(vo);
//					LastFivePanel.this.add(chart);
					LastFivePanel.this.repaint();
				}

			});
		}
	}

	public void setEffect() {
		button[0].setOpaque(true);
		button[0].setBackground(UIConfig.BUTTON_COLOR);
		button[0].setForeground(Color.white);
	}

}
