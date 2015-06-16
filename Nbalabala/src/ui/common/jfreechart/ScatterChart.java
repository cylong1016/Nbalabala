package ui.common.jfreechart;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import ui.common.panel.Panel;
import utility.Constants;
import vo.ForecastVO;



/**
 * 散点图
 * @author lsy
 * @version 2015年6月9日  下午11:28:34
 */
public class ScatterChart extends Panel{
	/** serialVersionUID */
	private static final long serialVersionUID = 2633383144443658841L;
	
	private ForecastVO vo;
	private ArrayList<Double> datas;
	private double[] x,y;
	
	public ScatterChart(ForecastVO vo){ 
		this.vo = vo;
		datas = vo.getDatas();
		x = vo.getCurveX();
		y = vo.getCurveY();
		JPanel panel = new ChartPanel(createChart());
		panel.setSize(700, 400);
		this.add(panel); // 将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
		this.setBounds(20, 90, 700, 400);
		this.repaint();
	}
	
	 private XYDataset samplexydataset2() {
		 
		  XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
		  XYSeries series = new XYSeries("Regression");
		  for(int i = 0 ; i < datas.size(); i++) {
			  series.add(i+1, datas.get(i));
		  }
          for(int i = 0 ; i < x.length;i++) {
          	series.add(x[i],y[i]);
          }
          series.add(datas.size()+1,vo.getNextY());
		  xySeriesCollection.addSeries(series);
		  return xySeriesCollection;
	 }
	
	//生成图表对象 
	public JFreeChart createChart() { 
	    JFreeChart scatterChart = ChartFactory.createScatterPlot(Constants.SCATTER_CHART[0],
	    		Constants.SCATTER_CHART[1],Constants.SCATTER_CHART[2],samplexydataset2(), PlotOrientation.VERTICAL, true, false, false);  
	    drawScatterChart(scatterChart, "", "");
	    return scatterChart; 
	} 
	 
	public void drawScatterChart(JFreeChart scatterChart, String title, String noDataMsg) { 
	    //title, legend, plot 三个部分设置字体的方法分别如下:   
	       TextTitle textTitle = scatterChart.getTitle();   
	       textTitle.setFont(new Font("宋体", Font.BOLD, 20));   
	       LegendTitle legend = scatterChart.getLegend();   
	       if (legend != null) 
	       {   
	           legend.setItemFont(new Font("宋体", Font.BOLD, 20)); 
	       } 
	       XYPlot xyplot = scatterChart.getXYPlot();   
	       xyplot.setNoDataMessage(noDataMsg);
	       xyplot.setNoDataMessagePaint(Color.blue);  
	       xyplot.setDomainGridlinePaint(Color.lightGray);//设置垂直网格线的颜色   
	       xyplot.setRangeGridlinePaint(Color.lightGray);//设置水平网格线的颜色 
//	       xyplot.setDomainGridlinesVisible(true);//设置垂直网格线是否显示   
//	       xyplot.setRangeGridlinesVisible(true);//设置水平网格线是否显示
	       
//	       XYBoxAnnotation box = new XYBoxAnnotation(0, 0, datas.size(),100); 
//	       xyplot.addAnnotation(box);
	        xyplot.setBackgroundPaint(new Color(255, 253, 246));  
	        xyplot.setForegroundAlpha((float) 0.5);
//	        ValueAxis vaaxis = xyplot.getDomainAxis();  
	        ValueAxis va = xyplot.getDomainAxis(0);  
	        	        
	        va.setAxisLinePaint(new Color(215, 215, 215)); // 坐标轴颜色  
	        va.setLabelPaint(new Color(10, 10, 10)); // 坐标轴标题颜色  
	        va.setTickLabelPaint(new Color(102, 102, 102)); // 坐标轴标尺值颜色  
  
	} 
}
