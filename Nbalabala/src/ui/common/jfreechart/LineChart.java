package ui.common.jfreechart;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import ui.UIConfig;
import ui.common.panel.Panel;
import vo.AnalysisTransferVO;
import bl.analysisbl.ValueAnalysis;
import blservice.AnalysisBLService;
import enums.InferenceData;

/**
 * 折线图
 * @author lsy
 * @version 2015年6月9日  下午11:31:09
 */
public class LineChart extends Panel{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -238536006047910842L;
	private String name;
	private InferenceData inferenceData;
	private double[] num;
	private AnalysisBLService service;
	private ArrayList<Double> formerData,currentData;
	private AnalysisTransferVO vo;
	
	public LineChart(String name,InferenceData inferenceData){
		this.name = name;
		this.inferenceData = inferenceData;
		service = new ValueAnalysis();
		vo = service.getTransferData(name, inferenceData);
		formerData = vo.getFormerData();
		currentData = vo.getCurrentData();
		JPanel panel = new ChartPanel(createLineChart());
		panel.setSize(800, 400);
		this.add(panel); // 将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
		this.repaint();
	}
	
	public JFreeChart createLineChart() {  
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
        int i = 0;
        
        for(i = 0;i<formerData.size(); i++ ){
        	dataset.addValue(formerData.get(i), vo.getFormerAbbr(), i+"");
        }
        
        for(int j = 0;j<currentData.size(); j++ ){
        	dataset.addValue(currentData.get(j), vo.getCurrentAbbr(), i+j+"");
        }
        
        JFreeChart chart = ChartFactory.createLineChart("球员转会图", "时间", "数据",  
                dataset, PlotOrientation.VERTICAL, true, true, true);  
        
        CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
        LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot.getRenderer();
        lineandshaperenderer.setBaseShapesVisible(true); // series 点（即数据点）可见
        lineandshaperenderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见
 
        categoryplot.setRangeGridlinePaint(Color.white);// 虚线色彩
        categoryplot.setDomainGridlinePaint(Color.white);// 虚线色彩
        categoryplot.setBackgroundPaint(Color.lightGray);//背景颜色
        CategoryAxis domainAxis = categoryplot.getDomainAxis();
        
        domainAxis.setLabelFont(UIConfig.FONT);// 轴标题
 
        domainAxis.setTickLabelFont(UIConfig.FONT);// 轴数值
 
        return chart;
    }  
	
}
