package ui.common.jfreechart;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

import ui.common.panel.Panel;

/** @see http://stackoverflow.com/questions/6844759 */
public class BoxChart extends Panel{

    /** serialVersionUID */
	private static final long serialVersionUID = -5806566237485794421L;
	private static final int COLS = 20;
    private static final int VISIBLE = 4;
    private static final int ROWS = 5;
    private static final int VALUES = 10;
    private static final Random rnd = new Random();
    private List<String> columns;
    private List<List<List<Double>>> data;
    private DefaultBoxAndWhiskerCategoryDataset dataset;
    private CategoryPlot plot;
    private ChartPanel chartPanel;
    private JPanel controlPanel;
    private int start = 0;

    public BoxChart() {
        createData();
        createDataset(start);
        createChartPanel();
        createControlPanel();
		chartPanel.setSize(800, 400);
		this.add(chartPanel); // 将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
		this.repaint();
    }

    private void createData() {
        columns = new ArrayList<String>(COLS);
        data = new ArrayList<List<List<Double>>>();
        for (int i = 0; i < COLS; i++) {
            String name = "Category" + String.valueOf(i + 1);
            columns.add(name);
            List<List<Double>> list = new ArrayList<List<Double>>();
            for (int j = 0; j < ROWS; j++) {
                list.add(createValues());
            }
            data.add(list);
        }
    }

    private List<Double> createValues() {
        List<Double> list = new ArrayList<Double>();
        for (int i = 0; i < VALUES; i++) {
            list.add(rnd.nextGaussian());
        }
        return list;
    }

    private void createDataset(int start) {
        dataset = new DefaultBoxAndWhiskerCategoryDataset();
        for (int i = start; i < start + VISIBLE; i++) {
            List<List<Double>> list = data.get(i);
            int row = 0;
            for (List<Double> values : list) {
                String category = columns.get(i);
                dataset.add(values, "s" + row++, category);
            }
        }
    }

    private void createChartPanel() {
        CategoryAxis xAxis = new CategoryAxis("Category");
        NumberAxis yAxis = new NumberAxis("Value");
        BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
        plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);
        JFreeChart chart = new JFreeChart("BoxAndWhiskerDemo", plot);
        chartPanel = new ChartPanel(chart);
    }

    
    /**
     * 添加下面的两个button,暂时不用
     * @author lsy
     * @version 2015年6月10日  下午1:24:07
     */
    private void createControlPanel() {
        controlPanel = new JPanel();
        controlPanel.add(new JButton(new AbstractAction("u22b2Prev") {

            /** serialVersionUID */
			private static final long serialVersionUID = 4522011730470666102L;

			@Override
            public void actionPerformed(ActionEvent e) {
                start -= VISIBLE;
                if (start < 0) {
                    start = 0;
                    return;
                }
                createDataset(start);
                plot.setDataset(dataset);
            }
        }));
        controlPanel.add(new JButton(new AbstractAction("Nextu22b3") {

            /** serialVersionUID */
			private static final long serialVersionUID = -3479435161409432954L;

			@Override
            public void actionPerformed(ActionEvent e) {
                start += VISIBLE;
                if (start > COLS - VISIBLE) {
                    start = COLS - VISIBLE;
                    return;
                }
                createDataset(start);
                plot.setDataset(dataset);
            }
        }));
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    public JPanel getControlPanel() {
        return controlPanel;
    }
}