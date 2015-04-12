package ui.panel.hot.hotFast;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import ui.UIConfig;
import ui.common.button.ImgButton;
import ui.common.chart.Chart;
import ui.common.chart.Column;
import ui.common.label.HotFastestPlayerLabel;
import ui.panel.hot.HotThreeFatherPanel;
import ui.panel.hot.ThreeButton;
import vo.HotFastestPlayerVO;
import bl.hotquerybl.HotQuery;
import blservice.HotBLService;
import enums.HotFastestPlayerProperty;

/**
 * 进步最快球员界面
 * @author lsy
 * @version 2015年4月11日  下午4:04:49
 */
public class HotFastPanel extends HotThreeFatherPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = 2077718178589844072L;

	ArrayList<HotFastestPlayerVO> fastVO;
	private static final HotFastestPlayerProperty[] HOT_FAST_ARRAY = HotFastestPlayerProperty.values();
	HotBLService hot = new HotQuery();
	HotFastestPlayerLabel label[] = new HotFastestPlayerLabel[5];
	Chart chart;
	ImgButton left,right;
	public static int CURRENTI = 0;
	
	public HotFastPanel(String url) {
		super(url);
		addlrButton();
		add_bt_Listener();
		addLabel();
		addChart();
	}
	
	public void addlrButton(){
		left = new ImgButton("images/Hot/left.png",110,194,"images/Hot/leftOn.png","images/Hot/left.png");
		right = new ImgButton("images/Hot/right.png",855,194,"images/Hot/rightOn.png","images/Hot/right.png");
		this.add(left);
		this.add(right);
		left.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				CURRENTI--;
				addChart();
				HotFastPanel.this.repaint();
			}
		});
		right.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				CURRENTI++;
				addChart();
				HotFastPanel.this.repaint();
			}
		});
	}

	private void addChart() {
		if(chart!=null){
			this.remove(chart);
		}
		
		if(CURRENTI == 5){
			CURRENTI = 0;
		}else if(CURRENTI == -1){
			CURRENTI = 4;
		}
		if (fastVO.size() < 5) return;
		ArrayList<Column> columns = new ArrayList<Column>();
		double max = fastVO.get(CURRENTI).getPromotion();
		double promotion = max;
		double formerFiveAvg = fastVO.get(CURRENTI).getProperty();
		columns.add(new Column("前五场平均",formerFiveAvg, UIConfig.HIST_COLORS[CURRENTI]));
		for (int i = 0; i < 5; i++) {
			double[] recentFive = fastVO.get(CURRENTI).getRecentFive();
			promotion = recentFive[i];
			columns.add(new Column("第"+(i+1)+"场", recentFive[i], UIConfig.HIST_COLORS[i]));
			if (max < promotion) {
				max = promotion;
			}
		}
		chart = new Chart((CURRENTI+1)+" "+fastVO.get(CURRENTI).getName()+" "+text, columns, max);
		chart.setBounds(95, 103, 809, 200);
		this.add(chart);
		chart.updateUI();
		chart.repaint();
	}
	
	/**
	 * 添加监听
	 * @author lsy
	 * @version 2015年4月11日  下午6:09:11
	 */
	public void add_bt_Listener() {
		for (int i = 0; i < select.length; i++) {
			hotButton[i].fast = HOT_FAST_ARRAY[i];
			hotButton[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					for(int i = 0;i<label.length;i++){
						if (label[i] != null)
							HotFastPanel.this.remove(label[i]);
					}
					addLabel();
					addChart();
				}

			});
		}
	}

	/**
	 * 添加每个球员显示的label
	 * @author lsy
	 * @version 2015年4月11日  下午6:09:19
	 */
	public void addLabel() {
		fastVO = hot.getHotFastestPlayers(ThreeButton.current.fast);
		if (fastVO.size() < 5) return;
		for (int j = 0; j < 5; j++) {
			label[j] = new HotFastestPlayerLabel(fastVO.get(j));
			this.add(label[j]);
		}
		this.repaint();
	}

}
