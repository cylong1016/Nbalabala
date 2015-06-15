package ui.panel.analyse;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;

import ui.UIConfig;
import ui.common.jfreechart.LineChart;
import ui.common.label.MyLabel;
import ui.common.panel.Panel;
import ui.panel.hot.ThreeButton;
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
	private SelectButton[] button;
	private int bt_x = 20, bt_y = 20, inter_x = 120, inter_y = 33, width = 80, height = 25;
	private String name;
	private AnalysisBLService service = new ValueAnalysis();
	private JTextArea area;

	public TurnPanel(String name) {
		this.name = name;
		AnalysisTransferVO vo = service.getTransferData(name, InferenceData.ASSIST);
		if(vo == null) {
			//TODO 提示该球员没有转会
		}else{
			chart = new LineChart(vo);
			this.add(chart);
			button = new SelectButton[10];
			area = new JTextArea();
			this.add(strLabel);
			addButton();
			setEffect();
		}
		this.setBounds(0, 100, 1000, 490);
		this.repaint();
	}

	public void addButton() {
		for (int i = 0; i < 5; i++) {
			button[i] = new SelectButton(bt_x + i * inter_x, bt_y, width, height, utility.Constants.ANY_SELECT[i]);
			button[i].setInferenceData(InferenceData.values()[i]);
			this.add(button[i]);
		}
		for (int i = 5; i < 10; i++) {
			button[i] = new SelectButton(bt_x + (i - 5) * inter_x, bt_y + inter_y, width, height,
					utility.Constants.ANY_SELECT[i]);
			button[i].setInferenceData(InferenceData.values()[i]);
			this.add(button[i]);
		}
		SelectButton.current = button[0];
		addListener();
	}
	
	public void addListener() {
		for (int i = 0; i < button.length; i++) {
			button[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.getSource() == ThreeButton.current) {
						return;
					}
					SelectButton.current.back();
					SelectButton.current = (SelectButton) e.getSource();
					TurnPanel.this.remove(chart);
					chart = new LineChart(service.getTransferData(name,SelectButton.current.getInferenceData()));
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

}
