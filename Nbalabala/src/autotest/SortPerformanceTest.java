package autotest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

public class SortPerformanceTest {
	static ArrayList<String> playerNormal = new ArrayList<String>();
	static ArrayList<String> playerHigh = new ArrayList<String>();
	static ArrayList<String> teamNormal = new ArrayList<String>();
	static ArrayList<String> teamHigh = new ArrayList<String>();
	static Console c = new Console();
	
	@BeforeClass
	public  static void setUP() {
		c.execute(null, new String[] {"--datasource", "E:\\autotest\\nba"});
	}
	
	
	static {
		
		//playerNormal information
		playerNormal.add("point");
		playerNormal.add("rebound");
		playerNormal.add("assist");
		playerNormal.add("blockShot");
		playerNormal.add("steal");
		playerNormal.add("foul");
		playerNormal.add("fault");
		playerNormal.add("minute");
		playerNormal.add("efficient");
		playerNormal.add("shot");
		playerNormal.add("three");
		playerNormal.add("penalty");
		playerNormal.add("doubleTwo");
		
		//playerHigh information
		playerHigh.add("realShot");
		playerHigh.add("GmSc");
		playerHigh.add("shotEfficient");
		playerHigh.add("reboundEfficient");
		playerHigh.add("offendReboundEfficient");
		playerHigh.add("defendReboundEfficient");
		playerHigh.add("assistEfficient");
		playerHigh.add("stealEfficient");
		playerHigh.add("blockShotEfficient");
		playerHigh.add("faultEfficient");
		playerHigh.add("frequency");
		
		//teamNormal information
		teamNormal.add("point");
		teamNormal.add("rebound");
		teamNormal.add("assist");
		teamNormal.add("blockShot");
		teamNormal.add("steal");
		teamNormal.add("foul");
		teamNormal.add("fault");
		teamNormal.add("shot");
		teamNormal.add("three");
		teamNormal.add("penalty");
		teamNormal.add("defendRebound");
		teamNormal.add("offendRebound");
		teamNormal.add("rebound");
		
		//teamHigh information
		teamHigh.add("winRate");
		teamHigh.add("offendRound");
		teamHigh.add("offendEfficient");
		teamHigh.add("defendEfficient");
		teamHigh.add("offendReboundEfficient");
		teamHigh.add("defendReboundEfficient");
		teamHigh.add("stealEfficient");
		teamHigh.add("assistEfficient");
	}
	
	@Pie
	public void testPlayerSort()
	{
		//asc
		for(int i=0; i<playerNormal.size();i++)
		{
			String sortCondition = playerNormal.get(i)+".asc";
			PrintStream p = null;
			try {
				p = new PrintStream(new File("-player-sort"+sortCondition+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-player","-sort",sortCondition};
			c.execute(p, ss);
		}
		//desc
		for(int i=0; i<playerNormal.size();i++)
		{
			String sortCondition = playerNormal.get(i)+".desc";
			PrintStream p = null;
			try {
				p = new PrintStream(new File("-player-sort"+sortCondition+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-player","-sort",sortCondition};
			c.execute(p, ss);
		}	
		
		
		//high information
				//asc
				for(int i=0; i<playerHigh.size();i++)
				{
					String sortCondition = playerHigh.get(i)+".asc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-player-high-sort"+sortCondition+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-player","-high","-sort",sortCondition};
					c.execute(p, ss);
				}
				//desc
				for(int i=0; i<playerHigh.size();i++)
				{
					String sortCondition = playerHigh.get(i)+".desc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-player-high-sort"+sortCondition+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-player","-high","-sort",sortCondition};
					c.execute(p, ss);
				}	
				
				
				
				//avg
				//asc
				for(int i=0; i<playerNormal.size();i++)
				{
					String sortCondition = playerNormal.get(i)+".asc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-player-avg-sort"+sortCondition+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-player","-avg","-sort",sortCondition};
					c.execute(p, ss);
				}
				//desc
				for(int i=0; i<playerNormal.size();i++)
				{
					String sortCondition = playerNormal.get(i)+".desc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-player-avg-sort"+sortCondition+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-player","-avg","-sort",sortCondition};
					c.execute(p, ss);
				}	
				
				//total
				//asc
				for(int i=0; i<playerNormal.size();i++)
				{
					String sortCondition = playerNormal.get(i)+".asc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-player-total-sort"+sortCondition+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-player","-total","-sort",sortCondition};
					c.execute(p, ss);
				}
				//desc
				for(int i=0; i<playerNormal.size();i++)
				{
					String sortCondition = playerNormal.get(i)+".desc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-player-total-sort"+sortCondition+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-player","-total","-sort",sortCondition};
					c.execute(p, ss);
				}	
	}
	
	
	@Pie
	public void testTeamSort()
	{
		//asc
		for(int i=0; i<teamNormal.size();i++)
		{
			String sortCondition = teamNormal.get(i)+".asc";
			PrintStream p = null;
			try {
				p = new PrintStream(new File("-team-sort"+sortCondition+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-team","-sort",sortCondition};
			c.execute(p, ss);
		}
		//desc
		for(int i=0; i<teamNormal.size();i++)
		{
			String sortCondition = teamNormal.get(i)+".desc";
			PrintStream p = null;
			try {
				p = new PrintStream(new File("-team-sort"+sortCondition+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-team","-sort",sortCondition};
			c.execute(p, ss);
		}	
		
		
		//high information
				//asc
				for(int i=0; i<teamHigh.size();i++)
				{
					String sortCondition = teamHigh.get(i)+".asc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-team-high-sort"+sortCondition+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-team","-high","-sort",sortCondition};
					c.execute(p, ss);
				}
				//desc
				for(int i=0; i<teamHigh.size();i++)
				{
					String sortCondition = teamHigh.get(i)+".desc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-team-high-sort"+sortCondition+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-team","-high","-sort",sortCondition};
					c.execute(p, ss);
				}	
				
				
				
				//avg
				//asc
				for(int i=0; i<teamNormal.size();i++)
				{
					String sortCondition = teamNormal.get(i)+".asc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-team-avg-sort"+sortCondition+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-team","-avg","-sort",sortCondition};
					c.execute(p, ss);
				}
				//desc
				for(int i=0; i<teamNormal.size();i++)
				{
					String sortCondition = teamNormal.get(i)+".desc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-team-avg-sort"+sortCondition+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-team","-avg","-sort",sortCondition};
					c.execute(p, ss);
				}	
				
				//total
				//asc
				for(int i=0; i<teamNormal.size();i++)
				{
					String sortCondition = teamNormal.get(i)+".asc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-team-total-sort"+sortCondition+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-team","-total","-sort",sortCondition};
					c.execute(p, ss);
				}
				//desc
				for(int i=0; i<teamNormal.size();i++)
				{
					String sortCondition = teamNormal.get(i)+".desc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-team-total-sort"+sortCondition+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-team","-total","-sort",sortCondition};
					c.execute(p, ss);
				}	
	}
	
	
	int n=20;
	private void testPlayerSortWithN()
	{
		//asc
		for(int i=0; i<playerNormal.size();i++)
		{
			String sortCondition = playerNormal.get(i)+".asc";
			PrintStream p = null;
			try {
				p = new PrintStream(new File("-player-sort"+sortCondition+"-n"+n+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-player","-sort",sortCondition,"-n",Integer.toString(n)};
			c.execute(p, ss);
		}
		//desc
		for(int i=0; i<playerNormal.size();i++)
		{
			String sortCondition = playerNormal.get(i)+".desc";
			PrintStream p = null;
			try {
				p = new PrintStream(new File("-player-sort"+sortCondition+"-n"+n+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-player","-sort",sortCondition,"-n",Integer.toString(n)};
			c.execute(p, ss);
		}	
		
		
		//high information
				//asc
				for(int i=0; i<playerHigh.size();i++)
				{
					String sortCondition = playerHigh.get(i)+".asc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-player-high-sort"+sortCondition+"-n"+n+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-player","-high","-sort",sortCondition,"-n",Integer.toString(n)};
					c.execute(p, ss);
				}
				//desc
				for(int i=0; i<playerHigh.size();i++)
				{
					String sortCondition = playerHigh.get(i)+".desc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-player-high-sort"+sortCondition+"-n"+n+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-player","-high","-sort",sortCondition,"-n",Integer.toString(n)};
					c.execute(p, ss);
				}	
				
				
				
				//avg
				//asc
				for(int i=0; i<playerNormal.size();i++)
				{
					String sortCondition = playerNormal.get(i)+".asc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-player-avg-sort"+sortCondition+"-n"+n+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-player","-avg","-sort",sortCondition,"-n",Integer.toString(n)};
					c.execute(p, ss);
				}
				//desc
				for(int i=0; i<playerNormal.size();i++)
				{
					String sortCondition = playerNormal.get(i)+".desc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-player-avg-sort"+sortCondition+"-n"+n+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-player","-avg","-sort",sortCondition,"-n",Integer.toString(n)};
					c.execute(p, ss);
				}	
				
				//total
				//asc
				for(int i=0; i<playerNormal.size();i++)
				{
					String sortCondition = playerNormal.get(i)+".asc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-player-total-sort"+sortCondition+"-n"+n+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-player","-total","-sort",sortCondition,"-n",Integer.toString(n)};
					c.execute(p, ss);
				}
				//desc
				for(int i=0; i<playerNormal.size();i++)
				{
					String sortCondition = playerNormal.get(i)+".desc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-player-total-sort"+sortCondition+"-n"+n+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-player","-total","-sort",sortCondition,"-n",Integer.toString(n)};
					c.execute(p, ss);
				}	
	}
	
	
	
	private void testTeamSortWithN()
	{
		//asc
		for(int i=0; i<teamNormal.size();i++)
		{
			String sortCondition = teamNormal.get(i)+".asc";
			PrintStream p = null;
			try {
				p = new PrintStream(new File("-team-sort"+sortCondition+"-n"+n+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-team","-sort",sortCondition,"-n",Integer.toString(n)};
			c.execute(p, ss);
		}
		//desc
		for(int i=0; i<teamNormal.size();i++)
		{
			String sortCondition = teamNormal.get(i)+".desc";
			PrintStream p = null;
			try {
				p = new PrintStream(new File("-team-sort"+sortCondition+"-n"+n+".txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String ss[] = {"-team","-sort",sortCondition,"-n",Integer.toString(n)};
			c.execute(p, ss);
		}	
		
		
		//high information
				//asc
				for(int i=0; i<teamHigh.size();i++)
				{
					String sortCondition = teamHigh.get(i)+".asc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-team-high-sort"+sortCondition+"-n"+n+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-team","-high","-sort",sortCondition,"-n",Integer.toString(n)};
					c.execute(p, ss);
				}
				//desc
				for(int i=0; i<teamHigh.size();i++)
				{
					String sortCondition = teamHigh.get(i)+".desc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-team-high-sort"+sortCondition+"-n"+n+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-team","-high","-sort",sortCondition,"-n",Integer.toString(n)};
					c.execute(p, ss);
				}	
				
				
				
				//avg
				//asc
				for(int i=0; i<teamNormal.size();i++)
				{
					String sortCondition = teamNormal.get(i)+".asc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-team-avg-sort"+sortCondition+"-n"+n+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-team","-avg","-sort",sortCondition,"-n",Integer.toString(n)};
					c.execute(p, ss);
				}
				//desc
				for(int i=0; i<teamNormal.size();i++)
				{
					String sortCondition = teamNormal.get(i)+".desc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-team-avg-sort"+sortCondition+"-n"+n+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-team","-avg","-sort",sortCondition,"-n",Integer.toString(n)};
					c.execute(p, ss);
				}	
				
				//total
				//asc
				for(int i=0; i<teamNormal.size();i++)
				{
					String sortCondition = teamNormal.get(i)+".asc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-team-total-sort"+sortCondition+"-n"+n+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-team","-total","-sort",sortCondition,"-n",Integer.toString(n)};
					c.execute(p, ss);
				}
				//desc
				for(int i=0; i<teamNormal.size();i++)
				{
					String sortCondition = teamNormal.get(i)+".desc";
					PrintStream p = null;
					try {
						p = new PrintStream(new File("-team-total-sort"+sortCondition+"-n"+n+".txt"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ss[] = {"-team","-total","-sort",sortCondition,"-n",Integer.toString(n)};
					c.execute(p, ss);
				}	
	}
	@Pie
	public void testPlayerSortWithNormalN()
	{
		n=35;
		testPlayerSortWithN();
	}
	
	
	@Pie
	public void testTeamSortWithNormalN()
	{
		n=25;
		testTeamSortWithN();
	}
	
	@Pie
	public void testPlayerSortWithAbnormalN()
	{
		n=900;
		testPlayerSortWithN();
	}
	
	
	@Pie
	public void testTeamSortWithAbnormalN()
	{
		n=40;
		testTeamSortWithN();
	}
}
