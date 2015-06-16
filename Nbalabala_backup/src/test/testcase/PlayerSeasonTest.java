package test.testcase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import test.players.Console;

public class PlayerSeasonTest {
	
	static ArrayList<String> valueOfPosition = new ArrayList<String>();
	static ArrayList<String> valueOfLeague = new ArrayList<String>();
	static ArrayList<String> valueOfAge = new ArrayList<String>();

static Console c = new Console();
	
	@BeforeClass
	public  static void setUP() {
		c.execute(null, new String[] {"--datasource", "NBAdata//"});
	}
	static {
		valueOfPosition.add("G");
		valueOfPosition.add("F");
		valueOfPosition.add("C");
		
		valueOfLeague.add("West");
		valueOfLeague.add("East");
		
		valueOfAge.add("<=22");
		valueOfAge.add("22< X <=25");
		valueOfAge.add("25< X <=30");
		valueOfAge.add(">30");
	}
	@Test
	public void testPosition() {
			
		PrintStream p = null;
		for(int j=0; j<valueOfPosition.size();j++)
		{	
			String filterCondition = valueOfPosition.get(j);
			try {
				p = new PrintStream(new File("-player-Filter-Pos-"+filterCondition+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-player","-filter","position."+filterCondition};
			c.execute(p, ss);
		}
	}
	@Test
	public void testLeague() {
		
		PrintStream p = null;
		for(int j=0; j<valueOfLeague.size();j++)
		{	
			System.out.println(1);
			String filterCondition = valueOfLeague.get(j);
			try {
				p = new PrintStream(new File("-player-Filter-League-"+filterCondition+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-player","-filter","league."+filterCondition};
			c.execute(p, ss);
		}
	}
	
	@Test
	public void testAge() {
		
		PrintStream p = null;
		for(int j=0; j<valueOfAge.size();j++)
		{	
			String filterCondition = valueOfAge.get(j);
			try {
				p = new PrintStream(new File("-player-Filter-Age-"+j+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-player","-filter","age."+filterCondition};
			c.execute(p, ss);
		}
	}
	
	@Test
	public void testMultiCon() {
		
		PrintStream p = null;
		for(int j=0; j<valueOfAge.size();j++)
		{	
			String filterCondition = valueOfAge.get(j);
			for(int i=0;i<valueOfLeague.size();i++) {
				String filterCondition2 = valueOfLeague.get(i);
				for(int k=0;k<valueOfPosition.size();k++) {
					String filterCondition3 = valueOfPosition.get(k);
					try {
						p = new PrintStream(new File("-player-Filter-"+j+i+k+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-player","-filter","age."+filterCondition+",league."+filterCondition2+
							",position."+filterCondition3};
					c.execute(p, ss);
				}
			}
		}
	}
}
