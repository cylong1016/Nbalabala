package ui.panel.analyse.panel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.UIConfig;
import ui.common.jfreechart.BoxChart;
import ui.common.panel.Panel;
import ui.panel.analyse.button.SeasonSelectButton;
import vo.AnalysisCareerVO;
import bl.analysisbl.ValueAnalysis;
import blservice.AnalysisBLService;
import enums.CareerData;

/**
 * 生涯数据
 * @author lsy
 * @version 2015年6月8日  下午8:40:18
 */
public class AllSeasonPanel extends Panel{

	/** serialVersionUID */
	private static final long serialVersionUID = -1220441293727390539L;
	private String name;
	private BoxChart chart;
	private SeasonSelectButton[] button;
	private int bt_x = 31, bt_y = 16, inter_x = 100, width = 80, height = 30;
	private AnalysisBLService service = new ValueAnalysis();
	private ArrayList<AnalysisCareerVO> vo;
	
	public AllSeasonPanel(String name){
		this.name = name;
		vo = service.getCareerData(name, CareerData.SCORE);
		if (vo == null) {
			//TODO 显示没有走向分析
		} else {
			chart = new BoxChart(vo);
			this.add(chart);
			button = new SeasonSelectButton[5];
			addButton();
			setEffect();
		}
		this.setBounds(0, 100, 1000, 490);
		this.repaint();
	}
	
	public void addButton() {
		for (int i = 0; i < 5; i++) {
			button[i] = new SeasonSelectButton(bt_x + i * inter_x, bt_y, width, height, utility.Constants.ANY_SELECT[i]);
			button[i].setInferenceData(CareerData.values()[i]);
			this.add(button[i]);
		}
		SeasonSelectButton.current = button[0];
		addListener();
	}
	
	public void addListener() {
		for (int i = 0; i < button.length; i++) {
			button[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.getSource() == SeasonSelectButton.current) {
						return;
					}
					SeasonSelectButton.current.back();
					SeasonSelectButton.current = (SeasonSelectButton) e.getSource();
					AllSeasonPanel.this.remove(chart);
					vo = service.getCareerData(name,SeasonSelectButton.current.getInferenceData());
					chart = new BoxChart(vo);
					AllSeasonPanel.this.add(chart);
					AllSeasonPanel.this.repaint();
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