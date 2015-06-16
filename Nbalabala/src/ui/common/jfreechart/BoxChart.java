package ui.common.jfreechart;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

import ui.UIConfig;
import ui.common.panel.Panel;
import vo.AnalysisCareerVO;

/** @see http://stackoverflow.com/questions/6844759 */
public class BoxChart extends Panel{

    /** serialVersionUID */
	private static final long serialVersionUID = -5806566237485794421L;
    private List<String> columns;
    private List<List<List<Double>>> data;
    private DefaultBoxAndWhiskerCategoryDataset dataset;
    private CategoryPlot plot;
    private ChartPanel chartPanel;
    private JPanel controlPanel;
    private int start = 0;
    private int size;
    
    private ArrayList<AnalysisCareerVO> vo;

    public BoxChart(ArrayList<AnalysisCareerVO> vo) {
    	this.vo = vo;
        createData();
        createDataset(start);
        createChartPanel();
		chartPanel.setSize(700, 400);
		this.add(chartPanel); // 将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
		this.setBounds(130, 90, 700, 400);
		this.repaint();
    }

    private void createData() {
    	size = vo.size();
        columns = new ArrayList<String>(vo.size());
        data = new ArrayList<List<List<Double>>>();
        for (int i = 0; i < size; i++) {
            columns.add(vo.get(i).getSeason().substring(0,4));
            List<List<Double>> list = new ArrayList<List<Double>>();
            for (int j = 0; j < size; j++) {
                list.add(vo.get(i).getDatas());
            }
            data.add(list);
        }
    }

    private void createDataset(int start) {
        dataset = new DefaultBoxAndWhiskerCategoryDataset();
        for (int i = start; i < size; i++) {
            List<List<Double>> list = data.get(i);
            String category = columns.get(i);
            dataset.add(list.get(i),"season", category);
        }
    }

    private void createChartPanel() {
        CategoryAxis xAxis = new CategoryAxis("赛季");
        NumberAxis yAxis = new NumberAxis("数值");
        BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
//        renderer.setFillBox(false); // 设置不填充颜色
        renderer.setFillBox(true);
        renderer.setBaseCreateEntities(false);
        renderer.setSeriesPaint(0, UIConfig.CHART_ORANGE);
        renderer.setSeriesOutlinePaint(0, Color.yellow);      
//        renderer.setFaroutPaint(Color.LIGHT_GRAY);
        plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);
//        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setForegroundAlpha((float) 0.9);
        plot.setWeight(5);
        JFreeChart chart = new JFreeChart("", plot);
        chartPanel = new ChartPanel(chart);
        
        chart.setBackgroundPaint(UIConfig.CHAR_BG_COLOR);
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    public JPanel getControlPanel() {
        return controlPanel;
    }
}