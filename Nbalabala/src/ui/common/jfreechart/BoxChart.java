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
		this.setBounds(20, 90, 700, 400);
		this.repaint();
    }

    private void createData() {
    	size = vo.size();
        columns = new ArrayList<String>(vo.size());
        data = new ArrayList<List<List<Double>>>();
        for (int i = 0; i < size; i++) {
            String name = String.valueOf(i + 1);
            columns.add(name);
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
        CategoryAxis xAxis = new CategoryAxis("");
        NumberAxis yAxis = new NumberAxis("Value");
        BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
        renderer.setBaseCreateEntities(false);
        renderer.setBaseItemLabelPaint(Color.yellow);
        plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);
//        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setForegroundAlpha((float) 0.6);
        plot.setWeight(5);
        JFreeChart chart = new JFreeChart("BoxAndWhiskerDemo", plot);
        chartPanel = new ChartPanel(chart);
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    public JPanel getControlPanel() {
        return controlPanel;
    }
}