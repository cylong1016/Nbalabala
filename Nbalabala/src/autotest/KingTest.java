package autotest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

public class KingTest {
	static ArrayList<String> playerKing = new ArrayList<String>();
	static ArrayList<String> sd = new ArrayList<String>();
static Console c = new Console();
	
	@BeforeClass
	public  static void setUP() {
		c.execute(null, new String[] {"--datasource", "E:\\autotest\\nba"});
	}
	static {
		playerKing.add("score");
		playerKing.add("rebound");
		playerKing.add("assist");
		sd.add("-daily");
		sd.add("-season");
	}
	@Test
	public void testDailyKing() {
		
	for(int j=0;j<sd.size();j++)	
		for(int i=0; i<playerKing.size();i++)
		{
			String sortCondition = playerKing.get(i);
			PrintStream p = null;
			try {
				p = new PrintStream(new File("-player-king"+sortCondition+sd.get(j)+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-player","-king",sortCondition,sd.get(j)};
			c.execute(p, ss);
		}
	}
	
	@Test
	public void testDailyNumberKing() {
		
	for(int j=0;j<sd.size();j++)	
		for(int i=0; i<playerKing.size();i++)
		{
			String sortCondition = playerKing.get(i);
			PrintStream p = null;
			try {
				p = new PrintStream(new File("-player-kingnumber"+sortCondition+sd.get(j)+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-player","-king",sortCondition,"-n","10",sd.get(j)};
			c.execute(p, ss);
		}
	}
}
