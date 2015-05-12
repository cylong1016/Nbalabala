package autotest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.BeforeClass;
import org.junit.Test;

public class OneExcuteTest {
	static Console c = new Console();
	@BeforeClass
	public  static void setUP() {
		c.execute(null, new String[] {"--datasource", "E:\\autotest\\nba"});
		try {
			Thread.sleep(90000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void executableTest()
	{
		PrintStream p = null;
		try {
			p = new PrintStream(new File("OneExcutetest0.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ss[] = {"-player","-hot","assist","-n","5"};
		c.execute(p, ss);
	}
	
	@Test
	public void testTeam1()
	{
		
		String ss[] = {"-team"};
		PrintStream p = null;
		try {
			p = new PrintStream(new File("OneExcutetest1.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.execute(p, ss);
	}
	
	@Test
	public void testTeam2()
	{
		
		String ss[] = {"-team","-hot","assist","-n","5"};
		PrintStream p = null;
		try {
			p = new PrintStream(new File("OneExcutetest2.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.execute(p, ss);
	}
	
	@Test
	public void testTeam3()
	{
		
		String ss[] = {"-team","-total","-all","-n","10","-sort","shot.desc"};
		PrintStream p = null;
		try {
			p = new PrintStream(new File("OneExcutetest3.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.execute(p, ss);
	}
	
	@Test
	public void testTeam4()
	{
		
		String ss[] = {"-team","-high","-n","5","-sort","stealEfficient.asc"};
		PrintStream p = null;
		try {
			p = new PrintStream(new File("OneExcutetest4.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.execute(p, ss);
	}
	
	@Test
	public void testAllTeam()
	{
		
		String ss[] = {"-team","-all","-total"};
		PrintStream p = null;
		try {
			p = new PrintStream(new File("OneExcutetestAllTeam.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.execute(p, ss);
	}
	
	@Test
	public void testAllPlayer()
	{
		
		String ss[] = {"-player","-all","n","50","-total"};
		PrintStream p = null;
		try {
			p = new PrintStream(new File("OneExcutetestAllPlayer.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.execute(p, ss);
	}
	
	@Test
	public void test1()
	{
		String ss[] = {"-player","-hot","assist","-n","5"};
		String ss2[] = {"-player"};
		
		PrintStream p = null;
		try {
			p = new PrintStream(new File("OneExcutetest11.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			c.execute(p, ss);
			c.execute(p, ss2);
		
	}
	
	@Test
	public void test2()
	{
		String ss[] = {"-player","-all","-n","10"};
		
		PrintStream p = null;
		try {
			p = new PrintStream(new File("OneExcutetest12.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			c.execute(p, ss);
		
	}
	
	@Test
	public void test3()
	{
		String ss[] = {"-player","-high","-n","10","-sort","frequency.desc"};
		
		PrintStream p = null;
		try {
			p = new PrintStream(new File("OneExcutetest13.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			c.execute(p, ss);
		
	}
	
	@Test
	public void test4()
	{
		String ss[] = {"-player","-king","score","-season"};
		
		PrintStream p = null;
		try {
			p = new PrintStream(new File("OneExcutetest14.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			c.execute(p, ss);
	}
	
	@Test
	public void test5()
	{
		String ss[] = {"-player","-king","score","-season"};
		
		PrintStream p = null;
		try {
			p = new PrintStream(new File("OneExcutetest15.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			c.execute(p, ss);
	}
	
	@Test
	public void test6()
	{
		String ss[] = {"-player","-total","-all","-n","10","-filter","position.F,league.West","-sort","shot.desc"};
		
		PrintStream p = null;
		try {
			p = new PrintStream(new File("OneExcutetest16.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			c.execute(p, ss);
	}
	
	@Test
	public void test7()
	{
		String ss[] = {"-team","-all","-n","10"};
		
		PrintStream p = null;
		try {
			p = new PrintStream(new File("OneExcutetest17.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			c.execute(p, ss);
	}
}
