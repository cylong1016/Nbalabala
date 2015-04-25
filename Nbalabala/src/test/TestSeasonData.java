package test;

import static org.junit.Assert.*;

import org.junit.Test;

import autotest.SeasonSimpleData;
import data.seasondata.SeasonData;

/**
 * 
 * @author Issac Ding
 * @version 2015年4月25日  下午4:34:17
 */
public class TestSeasonData {

	@Test
	public void test() {
		
		for (int i=0;i<100;i++) {
			new SeasonSimpleData().clear();
		}
	}

}
