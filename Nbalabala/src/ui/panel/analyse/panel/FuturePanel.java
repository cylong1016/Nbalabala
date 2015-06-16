package ui.panel.analyse.panel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;

import ui.UIConfig;
import ui.common.jfreechart.ScatterChart;
import ui.common.panel.Panel;
import ui.panel.analyse.button.FutureSelectButton;
import ui.panel.hot.ThreeButton;
import vo.ForecastVO;
import bl.analysisbl.ValueAnalysis;
import blservice.AnalysisBLService;
import enums.InferenceData;

/**
 * 球员走向
 * 
 * @author lsy
 * @version 2015年6月8日 下午8:41:11
 */
public class FuturePanel extends Panel {

	/** serialVersionUID */
	private static final long serialVersionUID = -5585803631916572420L;
	private AnalysisBLService service = new ValueAnalysis();
	private JTextArea area;
	private ScatterChart chart;
	private int bt_x = 20, bt_y = 20, inter_x = 120, inter_y = 33, width = 80, height = 25;
	private String name;
	private FutureSelectButton[] button;
	private ForecastVO vo;

	public FuturePanel(String name) {
		this.setBounds(0, 100, 1000, 490);
		this.name = name;
		vo = service.getForecastData(name, InferenceData.ASSIST);
		if (vo == null) {
			//TODO 显示没有走向分析
		} else {
			chart = new ScatterChart(vo);
			this.add(chart);
			button = new FutureSelectButton[10];
			area = new JTextArea(vo.getConclusion());
			area.setLineWrap(true);
			area.setEditable(false);
			area.setBounds(750, 220, 200, 200);
			area.setOpaque(false);
			this.add(area);
			addButton();
			setEffect();
		}
	}

	public void addButton() {
		for (int i = 0; i < 5; i++) {
			button[i] = new FutureSelectButton(bt_x + i * inter_x, bt_y, width, height,
					utility.Constants.ANY_SELECT[i]);
			button[i].setInferenceData(InferenceData.values()[i]);
			this.add(button[i]);
		}
		for (int i = 5; i < 10; i++) {
			button[i] = new FutureSelectButton(bt_x + (i - 5) * inter_x, bt_y + inter_y, width, height,
					utility.Constants.ANY_SELECT[i]);
			button[i].setInferenceData(InferenceData.values()[i]);
			this.add(button[i]);
		}
		FutureSelectButton.current = button[0];
		addListener();
	}

	public void addListener() {
		for (int i = 0; i < button.length; i++) {
			button[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.getSource() == ThreeButton.current) {
						return;
					}
					FutureSelectButton.current.back();
					FutureSelectButton.current = (FutureSelectButton) e.getSource();
					FuturePanel.this.remove(chart);
					vo = service.getForecastData(name, FutureSelectButton.current.getInferenceData());
					chart = new ScatterChart(vo);
					area.setText(vo.getConclusion());
					FuturePanel.this.add(chart);
					FuturePanel.this.repaint();
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
