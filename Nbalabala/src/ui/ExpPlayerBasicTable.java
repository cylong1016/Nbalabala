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

	private static final String []advancedHeader = {"序号","球员名称","效率","GmSc","使用率","进攻篮板率","防守篮板率","总篮板率","助攻率","真实命中率","投篮效率","盖帽率","抢断率","犯规率","失误率"};
	private static final String []offendHeader = {"序号","球员名称","投篮命中","投篮出手","投篮%","罚球命中","罚球出手","罚球%","三分命中","三分出手","三分%","助攻","助攻率","真实命中率","投篮效率"};
	private static final String []basicHeader = {"序号","球员名称","所属球队","参赛","先发","在场时间","得分","篮板","助攻","盖帽","抢断","两双","得分篮板助攻","犯规","失误"};
	private static final String []defendHeader = {"序号","球员名称","所属球队","参赛","先发","进攻篮板","防守篮板","总篮板","盖帽","抢断","进攻篮板率","防守篮板率","总篮板率","盖帽率","抢断率"};
//	String header = {"","","","","","","","","","","","","","","","","",};

	
	private static final Object[][]advancedContent=
		{	{333,"Kentavious Caldwell-Pope",5555,5555,77.7,77.7,77.7,77.7,77.7,77.7,77.7,77.7,77.7,77.7,77.7}	};
	private static final Object[][]offendContent=
		{	{333,"Kentavious Caldwell-Pope",5555,5555,77.7,5555,5555,77.7,5555,5555,77.7,5555,77.7,77.7,77.7}	};
	private static final Object[][]basicContent=
		{	{333,"Kentavious Caldwell-Pope","凯尔特人",55,55,"66:66",5555,5555,5555,5555,5555,5555,5555,5555,5555}	};
	private static final Object[][]defendContent=
		{	{333,"Kentavious Caldwell-Pope",5555,5555,5555,5555,5555,77.7,77.7,77.7,77.7,77.7,77.7,77.7,77.7}	};
	
	public ExpPlayerBasicTable(){
		super(defendContent,defendHeader);
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
