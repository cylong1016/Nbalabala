package ui.panel.analyse.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import ui.Images;
import ui.MyFont;
import ui.UIConfig;
import ui.common.jfreechart.ScatterChart;
import ui.common.label.MyLabel;
import ui.common.panel.Panel;
import ui.panel.analyse.button.FutureSelectButton;
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
	private int bt_x = 31, bt_y = 16, inter_x = 100, width = 80, height = 30;
	private String name;
	private FutureSelectButton[] button;
	private ForecastVO vo;
	
	DecimalFormat format = UIConfig.FORMAT;
	
	private Image bgImg = Images.FUTURE_BG;
	
	private MyLabel startSeason, endSeason, widthSeason;
	
	private static final int CONCLUSION_Y = UIConfig.CONCLUSION_Y;
	private static final int CONCLUSION_X = UIConfig.CONCLUSION_X;
	
	private static final int SEASON_WID = 140;
	private static final int SEASON_HEIGHT = 92;

	public FuturePanel(String name) {
		this.setBounds(0, 100, 1000, 490);
		this.name = name;
		vo = service.getForecastData(name, InferenceData.SCORE);
		if (vo == null) {
			bgImg = Images.NO_DATA;
		} else {
			chart = new ScatterChart(vo);
			this.add(chart);
			button = new FutureSelectButton[7];
			addButton();
			addLabel();
			addConclusion();
			bgImg = Images.FUTURE_BG;
			setEffect();
		}
	}

	private void addLabel() {
		JTextArea seasonText = new JTextArea("自" + '\n' + '\n' + "至");
		seasonText.setLineWrap(true);
		seasonText.setEditable(false);
		seasonText.setOpaque(false);
		seasonText.setBounds(CONCLUSION_X + 5, CONCLUSION_Y + 12, 100, SEASON_HEIGHT);
		seasonText.setFont(MyFont.YH_S);
		this.add(seasonText);
		
		startSeason = new MyLabel(vo.getFromseason());
		startSeason.setBounds(CONCLUSION_X + 25, CONCLUSION_Y - 25, SEASON_WID, SEASON_HEIGHT);
		startSeason.setFont(MyFont.YT_M);
		this.add(startSeason);
		
		endSeason = new MyLabel(vo.getToseason());
		endSeason.setBounds(CONCLUSION_X + 25, CONCLUSION_Y + 13, SEASON_WID, SEASON_HEIGHT);
		endSeason.setFont(MyFont.YT_M);
		this.add(endSeason);
		
		JLabel widthText = new JLabel( vo.getWidth() + "比赛/组");
		widthText.setBounds(CONCLUSION_X + 50, CONCLUSION_Y + 33, SEASON_WID, SEASON_HEIGHT);
		widthText.setFont(MyFont.YH_S);
//		widthText.setForeground(Color.WHITE);
		this.add(widthText);
		
		widthSeason = new MyLabel(format.format(vo.getNextY()));
		widthSeason.setBounds(CONCLUSION_X + SEASON_WID, CONCLUSION_Y, SEASON_WID, SEASON_HEIGHT);
		widthSeason.setFont(MyFont.YT_XXL);
		widthSeason.setForeground(Color.WHITE);
		widthSeason.setCenter();
		this.add(widthSeason);

		
	}

	/**
	 * 结论
	 */
	private void addConclusion() {
		area = new JTextArea(vo.getConclusion());
		area.setLineWrap(true);
		area.setEditable(false);
		area.setBounds(CONCLUSION_X, CONCLUSION_Y + 150, 290, 200);
		area.setForeground(UIConfig.BLUE_TEXT_COLOR);
		area.setFont(MyFont.YH_L);
		area.setOpaque(false);
		this.add(area);
	}

	public void addButton() {
		for (int i = 0; i < 3; i++) {
			button[i] = new FutureSelectButton(bt_x + i * inter_x, bt_y, width, height,
					utility.Constants.ANY_SELECT[i]);
			button[i].setInferenceData(InferenceData.values()[i]);
			this.add(button[i]);
		}
		for (int i = 3; i < button.length; i++) {
			button[i] = new FutureSelectButton(bt_x + i * inter_x, bt_y, width, height,
					utility.Constants.ANY_SELECT[i+3]);
			button[i].setInferenceData(InferenceData.values()[i+3]);
			this.add(button[i]);
		}
		FutureSelectButton.current = button[0];
		addListener();
	}

	public void addListener() {
		for (int i = 0; i < button.length; i++) {
			button[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.getSource() == FutureSelectButton.current) {
						return;
					}
					FutureSelectButton.current.back();
					FutureSelectButton.current = (FutureSelectButton) e.getSource();
					FuturePanel.this.remove(chart);
					vo = service.getForecastData(name, FutureSelectButton.current.getInferenceData());
					chart = new ScatterChart(vo);
					area.setText(vo.getConclusion());
					widthSeason.setText(format.format(vo.getNextY()));
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
	
	public void paint(Graphics g) {
		g.drawImage(bgImg, 0, 0, this);
		// g2d.drawImage(slider, 0, 0, this);
		super.paint(g);
	}

}
