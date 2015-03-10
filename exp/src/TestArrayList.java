import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**  
 * 创建时间：2015年3月10日 下午12:32:37  
 * 项目名称：PerformanceTest  
 * @author Xiaohan Ding
 * @version 1.0   
 * @since JDK 1.7 
 * 文件名称：TestArrayList.java  
 * 类说明：  
 */
public class TestArrayList {
	
	ArrayList<Record> list = new ArrayList<Record>(4000);
	
	public void run(){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("source.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = null;
		try {
			while((line = reader.readLine())!=null){
				String[] s = line.split(" ");
				list.add(new Record(s[0], Integer.parseInt(s[1]), 
						Integer.parseInt(s[2]), Integer.parseInt(s[3]),
						Integer.parseInt(s[4]),Integer.parseInt(s[5])));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		int sum = 0;
		int count = 0;
		
//		Iterator<Record> itr = list.iterator();
//		while (itr.hasNext()){
//			Record record = itr.next();
//			if (record.getName().equals("cylong")){
//				sum += record.getD2();
//			}
//		}
		
		for (Record record : list){
			if (record.name.equals("cylong")){
				sum += record.d2;
			}
			count++;
		}
		
//		for (int i=0;i<3780;i++){
//			Record record = list.get(i);
//			if (record.name.equals("cylong")){
//				sum += record.d2;
//			}
//		}
//		
		list.clear();
		
	}
	
	public static void main(String []args){
		
		TestArrayList testCase = new TestArrayList();
		Date d1 = Calendar.getInstance().getTime();
		for (int i=0;i<10000;i++){
			testCase.run();
		}
		Date d2 = Calendar.getInstance().getTime();
		System.out.println(d2.getTime() - d1.getTime());
		
	}
	
	
}
