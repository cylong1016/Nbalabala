package ui.common.jfreechart;

import javax.swing.JFrame;

import ui.UIConfig;

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
		String[] name = {"Allen Iverson","James Peter","Kobe Byrant","CYL"};
		double[] num = {35.4,18.3,26,30.4};
//		BarChart chart = new BarChart(name,num);
//		LineChart chart = new LineChart(name,name,num);
//		ScatterChart chart = new ScatterChart(num,num);
//		LineChart chart = new LineChart(name,name,num);
//		ScatterChart chart = new ScatterChart(num,num);
//		MixChart chart = new MixChart();
		BoxChart chart = new BoxChart();
		Test frame = new Test();
		frame.add(chart);
		frame.setVisible(true);
		frame.repaint();
	}
}
