package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.Test;

import autotest.Console;


public class ConsoleTest {
	@Test
	public void executableTest()
	{
		Console c = new Console();
		String ss[] = {"-player","-hot","assist","-n","5"};
		c.execute(System.out, ss);
	}
	
	@Test
	public void testTeam1()
	{
		Console c = new Console();
		String ss[] = {"-team"};
		PrintStream p = null;
		try {
			p = new PrintStream(new File("test1.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.execute(p, ss);
	}
	
	@Test
	public void testTeam2()
	{
		Console c = new Console();
		String ss[] = {"-team","hot","assist","-n","5"};
		PrintStream p = null;
		try {
			p = new PrintStream(new File("test2.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.execute(p, ss);
	}
	
	@Test
	public void testTeam3()
	{
		Console c = new Console();
		String ss[] = {"-team","-total","-all","-n","10","-sort","shot.desc"};
		PrintStream p = null;
		try {
			p = new PrintStream(new File("test3.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.execute(p, ss);
	}
	
	@Test
	public void testTeam4()
	{
		Console c = new Console();
		String ss[] = {"-team","-high","-n","5","-sort","stealEfficient.asc"};
		PrintStream p = null;
		try {
			p = new PrintStream(new File("test4.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.execute(p, ss);
	}
}
