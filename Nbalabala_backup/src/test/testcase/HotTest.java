package test.testcase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import test.players.Console;

public class HotTest {
	static ArrayList<String> playerHot = new ArrayList<String>();
	static ArrayList<String> teamHot = new ArrayList<String>();
static Console c = new Console();
	
	@BeforeClass
	public  static void setUP() {
		c.execute(null, new String[] {"--datasource", "NBAdata//"});
	}
	static {
		
		//playerNormal information
		playerHot.add("score");
		playerHot.add("rebound");
		playerHot.add("assist");
		
		//teamNormal information
		teamHot.add("score");
		teamHot.add("rebound");
		teamHot.add("assist");
		teamHot.add("blockShot");
		teamHot.add("steal");
		teamHot.add("foul");
		teamHot.add("fault");
		teamHot.add("shot");
		teamHot.add("three");
		teamHot.add("penalty");
		teamHot.add("defendRebound");
		teamHot.add("offendRebound");
		teamHot.add("rebound");
		
	}
	
	@Test
	public void testHotPlayer()
	{
		for(int i=0; i<playerHot.size();i++)
		{
			String sortCondition = playerHot.get(i);
			PrintStream p = null;
			try {
				p = new PrintStream(new File("-player-hot"+sortCondition+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-player","-hot",sortCondition};
			c.execute(p, ss);
		}
	}
	
	@Test
	public void testHotTeam()
	{
		for(int i=0; i<teamHot.size();i++)
		{
			String sortCondition = teamHot.get(i);
			PrintStream p = null;
			try {
				p = new PrintStream(new File("-team-hot"+sortCondition+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-team","-hot",sortCondition};
			c.execute(p, ss);
		}
	}
	@Test
	public void testHotPlayerWithNormalN()
	{
		n=35;
		testHotPlayerWithN();
	}
	
	
	@Test
	public void testHotTeamWithNormalN()
	{
		n=25;
		testHotTeamWithN();
	}
	
	@Test
	public void testHotPlayerWithAbnormalN()
	{
		n=900;
		testHotPlayerWithN();
	}
	
	
	@Test
	public void testHotTeamWithAbnormalN()
	{
		n=40;
		testHotTeamWithN();
	}
	
	int n=20;
	private void testHotPlayerWithN() {
		for(int i=0; i<playerHot.size();i++)
		{
			String sortCondition = playerHot.get(i);
			PrintStream p = null;
			try {
				p = new PrintStream(new File("-player-hot"+sortCondition+"-n"+n+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-player","-hot",sortCondition,"-n",Integer.toString(n)};
			c.execute(p, ss);
		}
	}
	private void testHotTeamWithN() {
		for(int i=0; i<teamHot.size();i++)
		{
			String sortCondition = teamHot.get(i);
			PrintStream p = null;
			try {
				p = new PrintStream(new File("-team-hot"+sortCondition+"-n"+n+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-team","-hot",sortCondition,"-n",Integer.toString(n)};
			c.execute(p, ss);
		}
	}
}
