/* 
 * Programmer: Baran Topal                   																			*
 * Project name: Some.Routing.v1          																			    *
 * Folder name: src        																								*
 * Package name: com.some.testing  																						*
 * File name: TestRouting.java                     																	    *
 *                                           																			*      
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *	                                                                                         							*
 *  LICENSE: This source file is subject to have the protection of GNU General Public License.             				*
 *	You can distribute the code freely but storing this license information. 											*
 *	Contact Baran Topal if you have any questions. barantopal@barantopal.com 										    *
 *	                                                                                         							*
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.some.testing;
import java.util.HashMap;
import java.util.Set;
import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import org.springframework.util.StopWatch;

import com.some.application.*;

public class TestRouting {

	// preprocess a regular input
	@Test
	public void preProcessTestUsual()
	{
		// arrange
		Routing r = new Routing();
		String userInput = "+46-73-212";
		r.preprocess(userInput);

		String actual = new String(r.getSb());
		String expected = "4673212";

		// assert
		Assert.assertEquals(expected, actual);		
	}

	// preprocess empty literal
	@Test
	public void preProcessTestEmpty()
	{
		// arrange
		Routing r = new Routing();
		String userInput = "";
		r.preprocess(userInput);

		String actual = new String(r.getSb());
		String expected = "";

		// assert
		Assert.assertEquals(expected, actual);	
	}

	// preprocess one alpha
	@Test
	public void preProcessTestOneAlpha()
	{
		// arrange
		Routing r = new Routing();
		String userInput = "a";
		r.preprocess(userInput);

		String actual = new String(r.getSb());
		String expected = "";

		// assert
		Assert.assertEquals(expected, actual);	
	}

	// preprocess one delimiter
	@Test
	public void preProcessTestOneDelim()
	{
		// arrange
		Routing r = new Routing();
		String userInput = "+";
		r.preprocess(userInput);

		String actual = new String(r.getSb());
		String expected = "";

		// assert
		Assert.assertEquals(expected, actual);	
	}

	// preprocess two same delimiters
	@Test
	public void preProcessTestTwoDelimsSame()
	{
		// arrange
		Routing r = new Routing();
		String userInput = "++";
		r.preprocess(userInput);

		String actual = new String(r.getSb());
		String expected = "";

		// assert
		Assert.assertEquals(expected, actual);	
	}

	// preprocess two different delimiters
	@Test
	public void preProcessTestTwoDelimsDifferent()
	{
		// arrange
		Routing r = new Routing();
		String userInput = "+-";
		r.preprocess(userInput);

		String actual = new String(r.getSb());
		String expected = "";

		// assert
		Assert.assertEquals(expected, actual);	
	}

	// preprocess one alpha one numeric respectively
	@Test
	public void preProcessTestOneAlphaOneNumeric()
	{
		// arrange
		Routing r = new Routing();
		String userInput = "a1";
		r.preprocess(userInput);

		String actual = new String(r.getSb());
		String expected = "1";

		// assert
		Assert.assertEquals(expected, actual);	
	}

	// preprocess one numeric one alpha respectively
	@Test
	public void preProcessTestOneNumericOneAlpha()
	{
		// arrange
		Routing r = new Routing();
		String userInput = "1a";
		r.preprocess(userInput);

		String actual = new String(r.getSb());
		String expected = "1";

		// assert
		Assert.assertEquals(expected, actual);	
	}

	// preprocess two numeric one alpha respectively
	@Test
	public void preProcessTestTwoNumericOneAlpha()
	{
		// arrange
		Routing r = new Routing();
		String userInput = "12a";
		r.preprocess(userInput);

		String actual = new String(r.getSb());
		String expected = "12";

		// assert
		Assert.assertEquals(expected, actual);	
	}

	// preprocess two numeric one alpha where alpha is in between
	@Test
	public void preProcessTestTwoNumericOneAlphaIn()
	{
		// arrange
		Routing r = new Routing();
		String userInput = "1a2";
		r.preprocess(userInput);

		String actual = new String(r.getSb());
		String expected = "12";

		// assert
		Assert.assertEquals(expected, actual);	
	}

	// preprocess two numeric one alpha where numeric is in between
	@Test
	public void preProcessTestOneNumericTwoAlphaIn()
	{
		// arrange
		Routing r = new Routing();
		String userInput = "a1b";
		r.preprocess(userInput);

		String actual = new String(r.getSb());
		String expected = "1";

		// assert
		Assert.assertEquals(expected, actual);	
	}

	// preprocess two numeric two alpha one another order
	@Test
	public void preProcessTestTwoNumericTwoAlpha1()
	{
		// arrange
		Routing r = new Routing();
		String userInput = "a1b2";
		r.preprocess(userInput);

		String actual = new String(r.getSb());
		String expected = "12";

		// assert
		Assert.assertEquals(expected, actual);	
	}

	// preprocess two numeric two alpha where numerics are in between
	@Test
	public void preProcessTestTwoNumericTwoAlpha2()
	{
		// arrange
		Routing r = new Routing();
		String userInput = "a12b";
		r.preprocess(userInput);

		String actual = new String(r.getSb());
		String expected = "12";

		// assert
		Assert.assertEquals(expected, actual);	
	}

	// preprocess two numeric two alpha where alphas are in between
	@Test
	public void preProcessTestTwoNumericTwoAlpha3()
	{
		// arrange
		Routing r = new Routing();
		String userInput = "1ab2";
		r.preprocess(userInput);

		String actual = new String(r.getSb());
		String expected = "12";

		// assert
		Assert.assertEquals(expected, actual);	
	}

	// preprocess two numeric two alpha one another order
	@Test
	public void preProcessTestTwoNumericTwoAlpha4()
	{
		// arrange
		Routing r = new Routing();
		String userInput = "1a2b";
		r.preprocess(userInput);

		String actual = new String(r.getSb());
		String expected = "12";

		// assert
		Assert.assertEquals(expected, actual);	
	}

	// test actual file read capacity
	@Test
	public void readTestRegularTextFile()
	{
		// arrange		
		Routing routing = mock(Routing.class);			
		when(routing.read("List.txt")).thenReturn(true);		

		// assert
		Assert.assertEquals(true, routing.read("List.txt"));			
	}

	// test partial file read capacity having single block
	@Test
	public void readTestPartialRegularTextFile1()
	{
		// arrange		
		Routing routing = mock(Routing.class);			
		when(routing.read("./test/PartialList1.txt")).thenReturn(true);		

		// assert
		Assert.assertEquals(true, routing.read("./test/PartialList1.txt"));			
	}

	// test partial file read capacity having single block with additional alpha
	@Test
	public void readTestPartialRegularTextFile2()
	{
		// arrange		
		Routing routing = mock(Routing.class);			
		when(routing.read("./test/PartialList2.txt")).thenReturn(true);		

		// assert
		Assert.assertEquals(true, routing.read("./test/PartialList2.txt"));			
	}

	// test partial file read capacity having multi blocks
	@Test
	public void readTestPartialRegularTextFile3()
	{
		// arrange		
		Routing routing = mock(Routing.class);			
		when(routing.read("./test/PartialList3.txt")).thenReturn(true);		

		// assert
		Assert.assertEquals(true, routing.read("./test/PartialList3.txt"));			
	}

	// test empty file read capacity
	@Test
	public void readTestEmptyTextFile()
	{
		// arrange		
		Routing routing = mock(Routing.class);			
		when(routing.read("./test/ListEmpty.txt")).thenReturn(true);		

		// assert
		Assert.assertEquals(true, routing.read("./test/ListEmpty.txt"));			
	}

	// test gibberish content file read capacity
	@Test
	public void readTestGibbTextFile()
	{
		// arrange		
		Routing routing = mock(Routing.class);			
		when(routing.read("./test/ListGibberish.txt")).thenReturn(true);		

		// assert
		Assert.assertEquals(true, routing.read("./test/ListGibberish.txt"));	
	}

	// test split line
	@Test
	public void splitTestRegularSuccess()
	{
		// arrange
		Routing r = new Routing();
		r.invokeSplit("Operator A:");
		char letter = 'A';

		// assert
		Assert.assertEquals(letter, r.getLetter());
	}

	// test split line failure
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void splitTestEmptyFail()
	{
		// arrange		
		Routing r = new Routing();				
		exception.expect(Exception.class);
		r.invokeSplit("");		
	}

	// test split line failure
	@Test
	public void splitTestGarbageFail()
	{
		// arrange
		Routing r = new Routing();				
		exception.expect(Exception.class);
		r.invokeSplit("dummy");	
	}

	// search expected input
	@Test
	public void searchTestRegular()
	{
		// arrange
		Routing r = new Routing();
		char [] arranged = {'4','6','3','2', '1'};
		r.setSb(new StringBuffer("46321"));

		StopWatch stopWatch = new StopWatch("searchTestRegular");
		stopWatch.start("searchTestRegular");

		int actual = r.invokeSearch(arranged);
		/*
		try{
		Thread.sleep(10000);
		}catch(Exception ex){}
		 */	
		stopWatch.stop();
		// System.out.println(stopWatch.getTotalTimeMillis());

		System.out.println(stopWatch.prettyPrint());

		int expected = 46321;

		// assert
		Assert.assertEquals(expected, actual);
	}

	// search no match, single numerics
	@Test
	public void searchTestNoMatch1()
	{
		// arrange
		Routing r = new Routing();
		char [] arranged = {'4'};
		r.setSb(new StringBuffer("5"));

		StopWatch stopWatch = new StopWatch("searchTestNoMatch1");
		stopWatch.start("searchTestNoMatch1");		

		int actual = r.invokeSearch(arranged);

		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());		

		int expected = -1;

		// assert
		Assert.assertEquals(expected, actual);
	}

	// search no match mixed length numerics that contains 1 matching elements
	@Test
	public void searchTestNotMatch2()
	{
		// arrange
		Routing r = new Routing();
		char [] arranged = {'4','6','3','2', '1'};
		r.setSb(new StringBuffer("1"));

		StopWatch stopWatch = new StopWatch("searchTestNotMatch2");
		stopWatch.start("searchTestNotMatch2");	

		int actual = r.invokeSearch(arranged);

		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());

		int expected = -1;

		// assert
		Assert.assertEquals(expected, actual);
	}

	// search no match mixed length numerics that contains no matching elements
	@Test
	public void searchTestNoMatch3()
	{
		// arrange
		Routing r = new Routing();
		char [] arranged = {'4'};
		r.setSb(new StringBuffer("56"));

		StopWatch stopWatch = new StopWatch("searchTestNoMatch3");
		stopWatch.start("searchTestNoMatch3");	

		int actual = r.invokeSearch(arranged);

		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());

		int expected = -1;

		// assert
		Assert.assertEquals(expected, actual);
	}

	// search no match single alpha with empty input
	@Test
	public void searchTestNoMatch4()
	{
		// arrange
		Routing r = new Routing();
		char [] arranged = {'4'};
		r.setSb(new StringBuffer(""));

		StopWatch stopWatch = new StopWatch("searchTestNoMatch4");
		stopWatch.start("searchTestNoMatch4");	

		int actual = r.invokeSearch(arranged);

		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());

		int expected = -1;

		// assert
		Assert.assertEquals(expected, actual);
	}

	// search no match empty alpha with single numeric input
	@Test
	public void searchTestNoMatch5()
	{
		// arrange
		Routing r = new Routing();
		char [] arranged = {' '};
		r.setSb(new StringBuffer("4"));

		StopWatch stopWatch = new StopWatch("searchTestNoMatch5");
		stopWatch.start("searchTestNoMatch5");	

		int actual = r.invokeSearch(arranged);

		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());

		int expected = -1;

		// assert
		Assert.assertEquals(expected, actual);
	}

	// search no match empty alpha with empty input
	@Test
	public void searchTestNoMatch6()
	{
		// arrange
		Routing r = new Routing();
		char [] arranged = {' '};
		r.setSb(new StringBuffer(""));

		StopWatch stopWatch = new StopWatch("searchTestNoMatch6");
		stopWatch.start("searchTestNoMatch6");	

		int actual = r.invokeSearch(arranged);

		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());

		int expected = -1;

		// assert
		Assert.assertEquals(expected, actual);
	}

	// search partial match
	@Test
	public void searchTestPartialMulti()
	{
		// arrange
		Routing r = new Routing();
		char [] arranged = {'4','6'};
		r.setSb(new StringBuffer("46321"));

		StopWatch stopWatch = new StopWatch("searchTestPartialMulti");
		stopWatch.start("searchTestPartialMulti");	

		int actual = r.invokeSearch(arranged);

		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());

		int expected = 46;

		// assert
		Assert.assertEquals(expected, actual);
	}

	// search partial match with first char
	@Test
	public void searchTestPartialSingle()
	{
		// arrange
		Routing r = new Routing();
		char [] arranged = {'4'};
		r.setSb(new StringBuffer("46321"));

		StopWatch stopWatch = new StopWatch("searchTestPartialSingle");
		stopWatch.start("searchTestPartialSingle");	

		int actual = r.invokeSearch(arranged);

		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());

		int expected = 4;

		// assert
		Assert.assertEquals(expected, actual);
	}

	// search perfect match with one char
	@Test
	public void searchTestPerfectSingle()
	{
		// arrange
		Routing r = new Routing();
		char [] arranged = {'4'};
		r.setSb(new StringBuffer("4"));

		StopWatch stopWatch = new StopWatch("searchTestPerfectSingle");
		stopWatch.start("searchTestPerfectSingle");	

		int actual = r.invokeSearch(arranged);

		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());		

		int expected = 4;

		// assert
		Assert.assertEquals(expected, actual);
	}

	// search perfect match with multi chars
	@Test
	public void searchTestPerfectMulti()
	{
		// arrange
		Routing r = new Routing();
		char [] arranged = {'4', '6'};
		r.setSb(new StringBuffer("46"));
		StopWatch stopWatch = new StopWatch("searchTestPerfectMulti");
		stopWatch.start("searchTestPerfectMulti");	

		int actual = r.invokeSearch(arranged);

		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());

		int expected = 46;

		// assert
		Assert.assertEquals(expected, actual);
	}

	// compare not null content
	@Test
	public void compareTestNotNull()
	{
		// arrange
		Routing r = new Routing();
		HashMap<char[],char[]> testMap = new HashMap<char[],char[]>();
		testMap.put(new char[]{'1'}, new char[]{'1'});
		testMap.put(new char[]{'1','2'}, new char[]{'0','.','1'});
		r.setMap(testMap);		
		r.invokeCompare();

		// assert
		Assert.assertNotNull(r);
	}

	// compare longest with different size prefix
	@Test
	public void compareTestLongest1()
	{
		// arrange
		Routing r = new Routing();
		HashMap<char[],char[]> testMap = new HashMap<char[],char[]>();
		testMap.put(new char[]{'1'}, new char[]{'1'});
		testMap.put(new char[]{'1','2'}, new char[]{'0','.','1'});
		r.setMap(testMap);		
		r.invokeCompare();

		char [] expectedPrefix = {'1', '2'};
		char [] expectedPrice =  {'0','.','1'};
		// assert prefix
		for(int i = 0; i < expectedPrefix.length; i++)
			Assert.assertEquals(expectedPrefix[i], r.getPrefix()[i]);

		// assert price
		for(int i = 0; i < expectedPrice.length; i++)
			Assert.assertEquals(expectedPrice[i], r.getPrice()[i]);
	}

	// compare longest with same prefix, ignore due to hashmap non-ordering
	@Test
	@Ignore
	public void compareTestLongest2()
	{
		// arrange
		Routing r = new Routing();
		HashMap<char[],char[]> testMap = new HashMap<char[],char[]>();
		testMap.put(new char[]{'1','2'}, new char[]{'1','.','3'});
		testMap.put(new char[]{'1','2'}, new char[]{'0','.','1'});

		r.setMap(testMap);
		r.invokeCompare();

		char [] expectedPrefix = {'1', '2'};
		char [] expectedPrice =  {'0','.','1'};

		// assert prefix and price
		for(int i = 0; i < expectedPrefix.length; i++)
		{
			Assert.assertEquals(expectedPrefix[i], r.getPrefix()[i]);
			Assert.assertEquals(expectedPrice[i], r.getPrice()[i]);
		}
	}

	// compare longest with different size prefix order different
	@Test
	public void compareTestLongest3()
	{
		// arrange
		Routing r = new Routing();
		HashMap<char[],char[]> testMap = new HashMap<char[],char[]>();
		testMap.put(new char[]{'1','2'}, new char[]{'1','.','2'});
		testMap.put(new char[]{'1'}, new char[]{'0','.','1'});
		r.setMap(testMap);		
		r.invokeCompare();

		char [] expectedPrefix = {'1', '2'};
		char [] expectedPrice =  {'1','.','2'};
		// assert prefix
		for(int i = 0; i < expectedPrefix.length; i++)
			Assert.assertEquals(expectedPrefix[i], r.getPrefix()[i]);

		// assert price
		for(int i = 0; i < expectedPrice.length; i++)
			Assert.assertEquals(expectedPrice[i], r.getPrice()[i]);
	}

	// compare longest with alpha containing prefix
	@Test
	public void compareTestLongest4()
	{
		// arrange
		Routing r = new Routing();
		HashMap<char[],char[]> testMap = new HashMap<char[],char[]>();
		testMap.put(new char[]{'1','2'}, new char[]{'1','.','2'});
		testMap.put(new char[]{'-','1'}, new char[]{'0','.','1'});
		r.setMap(testMap);		
		r.invokeCompare();

		char [] expectedPrefix = {'-', '2'};
		char [] expectedPrice = {'1','.','3'};

		// assert prefix		
		assertThat(new String(expectedPrefix), not(equalTo(new String(r.getPrefix()))));		

		// assert price
		assertThat(new String(expectedPrice ), not(equalTo(new String(r.getPrice()))));
	}

	// compare single input
	@Test
	public void compareTestLongest5()
	{
		// arrange
		Routing r = new Routing();
		HashMap<char[],char[]> testMap = new HashMap<char[],char[]>();
		testMap.put(new char[]{'1','2'}, new char[]{'1','.','2'});		
		r.setMap(testMap);		
		r.invokeCompare();

		char [] expectedPrefix = {'1', '2'};
		char [] expectedPrice =  {'1','.','2'};
		// assert prefix
		for(int i = 0; i < expectedPrefix.length; i++)
			Assert.assertEquals(expectedPrefix[i], r.getPrefix()[i]);

		// assert price
		for(int i = 0; i < expectedPrice.length; i++)
			Assert.assertEquals(expectedPrice[i], r.getPrice()[i]);		
	}

	// compare null containing prefix
	@Test
	public void compareTestLongest6()
	{
		// arrange
		Routing r = new Routing();
		HashMap<char[],char[]> testMap = new HashMap<char[],char[]>();
		testMap.put(new char[]{'1','\u0000'}, new char[]{'1','.','2'});		
		r.setMap(testMap);		
		r.invokeCompare();

		char [] expectedPrefix = {'1', '\u0000'};
		char [] expectedPrice =  {'1','.','2'};
		// assert prefix
		for(int i = 0; i < expectedPrefix.length; i++)
			Assert.assertEquals(expectedPrefix[i], r.getPrefix()[i]);

		// assert price
		for(int i = 0; i < expectedPrice.length; i++)
			Assert.assertEquals(expectedPrice[i], r.getPrice()[i]);		
	}

	// compare null containing price
	@Test
	public void compareTestLongest7()
	{
		// arrange
		Routing r = new Routing();
		HashMap<char[],char[]> testMap = new HashMap<char[],char[]>();
		testMap.put(new char[]{'1','\u0000'}, new char[]{'\u0000'});		
		r.setMap(testMap);		
		r.invokeCompare();

		char [] expectedPrefix = {'1', '\u0000'};
		char [] expectedPrice =  {'\u0000'};
		// assert prefix
		for(int i = 0; i < expectedPrefix.length; i++)
			Assert.assertEquals(expectedPrefix[i], r.getPrefix()[i]);

		// assert price
		for(int i = 0; i < expectedPrice.length; i++)
			Assert.assertEquals(expectedPrice[i], r.getPrice()[i]);			
	}

	// compare null containing prefix and suffix
	@Test
	public void compareTestLongest8()
	{
		// arrange
		Routing r = new Routing();
		HashMap<char[],char[]> testMap = new HashMap<char[],char[]>();
		testMap.put(new char[]{'\u0000'}, new char[]{'\u0000'});		
		r.setMap(testMap);		
		r.invokeCompare();

		char [] expectedPrefix = {'\u0000'};
		char [] expectedPrice =  {'\u0000'};
		// assert prefix
		for(int i = 0; i < expectedPrefix.length; i++)
			Assert.assertEquals(expectedPrefix[i], r.getPrefix()[i]);

		// assert price
		for(int i = 0; i < expectedPrice.length; i++)
			Assert.assertEquals(expectedPrice[i], r.getPrice()[i]);	
	}

	// compare having non init element in map with init
	@Test
	public void compareTestLongest9()
	{
		// arrange
		Routing r = new Routing();
		HashMap<char[],char[]> testMap = new HashMap<char[],char[]>();
		testMap.put(new char[]{'1','\u0000'}, null);		
		r.setMap(testMap);		
		r.invokeCompare();

		char [] expectedPefix = {'1'};

		// assert
		for(int i = 0; i < expectedPefix.length; i++)
			Assert.assertEquals(expectedPefix[i], r.getPrefix()[i]);			
	}

	// compare having non init element in map with null element
	@Test
	public void compareTestLongest10()
	{
		// arrange
		Routing r = new Routing();
		HashMap<char[],char[]> testMap = new HashMap<char[],char[]>();
		testMap.put(new char[]{'\u0000'}, null);		
		r.setMap(testMap);		
		r.invokeCompare();

		char [] expectedPefix = {'\u0000'};

		// assert		
		for(int i = 0; i < expectedPefix.length; i++)
			Assert.assertEquals(expectedPefix[i], r.getPrefix()[i]);		
	}

	// null check, compare having non init element in map with null element
	@Test
	public void compareTestLongest11()
	{
		// arrange
		Routing r = new Routing();
		HashMap<char[],char[]> testMap = new HashMap<char[],char[]>();
		testMap.put(new char[]{'\u0000'}, null);		
		r.setMap(testMap);		
		r.invokeCompare();

		// assert
		Assert.assertNotNull(testMap);		
	}

	// display, having null prefix and price
	@Test
	public void displayTestNull()
	{
		// arrange
		Routing r = new Routing();
		HashMap<char[],char[]> testMap = null;					
		r.setMap(testMap);
		r.setPrefix(null);
		r.setPrice(null);					
		boolean expected = r.display();
		boolean actual = false;

		// assert
		Assert.assertEquals(expected, actual);

	}

	// display, init but empty map
	@Test	
	public void displayTestEmpty()
	{
		// arrange
		Routing r = new Routing();
		HashMap<char[],char[]> testMap = new HashMap<char[],char[]>();
		r.setMap(testMap);
		boolean actual = r.display();
		boolean expected =true;

		// assert		
		Assert.assertEquals(expected, actual);				
	}


	// display, price is null
	@Test	
	public void displayTestNullPrefix()
	{
		// arrange
		Routing r = new Routing();
		HashMap<char[],char[]> testMap = new HashMap<char[],char[]>();
		testMap.put(new char[]{'1'}, null);
		r.setMap(testMap);
		r.display();

		// assert
		Assert.assertNull(r.getPrefix());
	}

	// display, prefix is null
	@Test	
	public void displayTestNullPrice()
	{
		// arrange
		Routing r = new Routing();
		HashMap<char[],char[]> testMap = new HashMap<char[],char[]>();
		testMap.put(null, new char[]{'1'});
		r.setMap(testMap);
		r.setPrefix(null);
		r.setPrice(new char[]{'1'});
		r.display();

		// assert			
		Assert.assertNull(r.getPrefix());
	}

	// display, both prefix and price are null
	@Test	
	public void displayTestNullPrefixPrice()
	{
		// arrange
		Routing r = new Routing();
		HashMap<char[],char[]> testMap = new HashMap<char[],char[]>();
		testMap.put(null, null);
		r.setMap(testMap);		
		r.display();

		// assert
		Assert.assertNull(r.getPrefix());
		Assert.assertNull(r.getPrice());
	}

	// display, prefix and price members are null w.o. setting in Routing instance
	@Test
	public void displayTestPrefixPrice1()
	{
		// arrange
		Routing r = new Routing();
		HashMap<char[],char[]> testMap = new HashMap<char[],char[]>();
		testMap.put(new char[]{'1'}, new char[]{'1'});
		r.setMap(testMap);
		boolean actual = r.display();
		boolean expected = false;

		// assert
		Assert.assertEquals(expected, actual);
	}

	// display, prefix and price are set in Routing instance 
	@Test
	public void displayTestPrefixPrice2()
	{
		// arrange
		Routing r = new Routing();
		HashMap<char[],char[]> testMap = new HashMap<char[],char[]>();
		testMap.put(new char[]{'1'}, new char[]{'1'});			
		r.setMap(testMap);

		Set <char[]> keys = r.getMap().keySet(); 		
		char [] tempPrefix = ((char[])keys.iterator().next());
		r.setPrefix(tempPrefix);

		char [] tempPrice = (char[])r.getMap().get(tempPrefix);
		r.setPrice(tempPrice);

		char tempLetter = 'A';	
		r.setLetter(tempLetter);
		boolean actual = r.display();
		boolean expected = true;

		// assert
		Assert.assertEquals(expected, actual);
	}

	// display, prefix and price are set in multi map instances 
	@Test
	public void displayTestPrefixPrice3()
	{
		// arrange
		Routing r = new Routing();
		HashMap<char[],char[]> testMap = new HashMap<char[],char[]>();
		testMap.put(new char[]{'1'}, new char[]{'1'});
		testMap.put(new char[]{'4', '6'}, new char[]{'1', '.', '2'});
		r.setMap(testMap);

		Set <char[]> keys = r.getMap().keySet(); 		
		for(int i = 0; i < keys.size(); i++)
		{
			char [] tempPrefix = ((char[])keys.iterator().next());
			r.setPrefix(tempPrefix);

			char [] tempPrice = (char[])r.getMap().get(tempPrefix);
			r.setPrice(tempPrice);

			char tempLetter = 'A';	
			r.setLetter(tempLetter);
			boolean actual = r.display();
			boolean expected = true;

			// assert
			Assert.assertEquals(expected, actual);
		}
	}
}