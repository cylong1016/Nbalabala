package ui.common.jfreechart;

import javax.swing.JFrame;

import enums.CareerData;
import enums.InferenceData;
import ui.UIConfig;
import bl.analysisbl.ValueAnalysis;
import blservice.AnalysisBLService;

/**
 * 
 * @author lsy
 * @version 2015年6月9日  下午8:10:19
 */
public class Test extends JFrame{
	
	public Test(){
		this.setSize(UIConfig.WIDTH, UIConfig.HEIGHT);
	}
	
	public static void main(String[] args){
		AnalysisBLService service = new ValueAnalysis();
		String[] name = {"Allen Iverson","James Peter","Kobe Byrant","CYL"};
		double[] num = {35.4,18.3,26,30.4};
		BarChart chart = new BarChart(name,num);
//		LineChart chart = new LineChart(name,name,num);
//		ScatterChart chart = new ScatterChart(service.getForecastData("LeBron James", InferenceData.ASSIST));
//		LineChart chart = new LineChart(name,name,num);
//		ScatterChart chart = new ScatterChart(num,num);
//		MixChart chart = new MixChart();
//		BoxChart chart = new BoxChart(service.getCareerData("LeBron James$01", CareerData.ASSIST));
//		XYLine3DRenderer renderer = new XYLine3DRenderer();
		Test frame = new Test();
		
		frame.add(chart);
		frame.setVisible(true);
		frame.repaint();
	}
}
