package ui;

import java.awt.AWTError;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import ui.common.table.BottomTable;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月22日  下午8:02:08
 */
public class ExpPlayerBasicTable extends BottomTable{
//	序号	名字	助攻率	盖帽率	犯规率	效率	Gmsc	真实命中率	投篮效率	进攻篮板率	防守篮板率	总篮板率	抢断率	失误率	使用率

	private static final String []header = {"序号","球员名称","效率","GmSc","使用率","进攻篮板率","防守篮板率","总篮板率","助攻率","真实命中率","投篮效率","盖帽率","抢断率","犯规率","失误率"};
//	String header = {"","","","","","","","","","","","","","","","","",};
	
	private static final Object[][]content=
		{	{333,"Kentavious Caldwell-Pope",5555,5555,77.7,77.7,77.7,77.7,77.7,77.7,77.7,77.7,77.7,77.7,77.7}	};
	
	public ExpPlayerBasicTable(){
		super(content,header);
	}
	
//	public static void main(String[]args){
//		JFrame frame = new JFrame();
//		frame.setSize(UIConfig.WIDTH, UIConfig.HEIGHT);
//		frame.setLocationRelativeTo(null); // 居中，要在设置大小之后 
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setUndecorated(true);
//		frame.setVisible(true);
//		ExpPlayerBasicTable expPlayerBasicTable = new ExpPlayerBasicTable();
//		JScrollPane scrollPane = new JScrollPane(expPlayerBasicTable);
//		scrollPane.setSize(UIConfig.TABLE_DIMEN);
//		scrollPane.setLocation(0,0);
//		frame.getContentPane().setLayout(null);
//		frame.getContentPane().add(scrollPane);
//	}

}
