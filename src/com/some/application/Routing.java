/* 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Programmer: Baran Topal                   																			*
 * Project name: Some.Routing.v1          																			    *
 * Folder name: src        																								*
 * Package name: com.some.application  																				    *
 * File name: Routing.java                     																	        *
 *                                           																			*      
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *	                                                                                         							*
 *  LICENSE: This source file is subject to have the protection of GNU General Public License.             				*
 *	You can distribute the code freely but storing this license information. 											*
 *	Contact Baran Topal if you have any questions. barantopal@barantopal.com 										    *
 *	                                                                                         							*
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.some.application;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Routing {

	// member variables
	private char[] prefix;
	private char[] price;
	private char letter;

	private Map<char[],char[]> map = new HashMap<char[],char[]>();
	private StringBuffer sb;

	// accessors and mutators
	public void setPrefix(char[] prefix)
	{
		this.prefix = prefix;
	}

	public void setPrice(char [] price)
	{
		this.price = price;
	}

	public void setLetter(char letter)
	{
		this.letter = letter;
	}

	public char[] getPrefix()
	{
		return prefix;
	}

	public char[] getPrice()
	{
		return price;
	}

	public char getLetter()
	{
		return letter;
	}	

	public StringBuffer getSb()
	{
		return sb;
	}

	public void setSb(StringBuffer sb)
	{
		this.sb = sb;
	}

	public Map<char[],char[]> getMap()
	{
		return map;
	}

	public void setMap(HashMap<char[],char[]> map)
	{
		this.map = map;
	}

	/* <Test helper methods> */
	// To maintain encapsulation
	public void invokeSplit(String strLine)
	{
		split(strLine);
	}

	public int invokeSearch(char []prefixTemp)
	{
		return(search(prefixTemp));
	}

	public void invokeCompare()
	{
		compare();
	}
	/* </Test helper methods> */

	// splits a single line
	private void split(String strLine)
	{
		char []ch = strLine.toCharArray();		
		int i = 0, k = 0, m = 0;

		// O character is expected to be first char
		if(ch[i] == 'O')
		{
			// prefix has a value, compare the content and display per block (not line)
			if(prefix != null)
			{				
				compare();
				display();
			}

			// grab letter of Operator %: where % denotes the char
			letter = ch[ch.length - 2];		
		}
		else
		{
			// flow over the numericals
			// prepare array size
			while(ch[i] != '\t')			
				i++;

			// assign array size
			char [] prefixTemp = new char[i];

			// populate array elements
			while(m < prefixTemp.length)
			{
				prefixTemp[m] = ch[k++];
				m++;
			}
			// jump empty chars btw. prefix and price
			i = i + 2;												

			// if prefix matches to the search
			if(search(prefixTemp) != -1)
			{
				prefix = prefixTemp;
				int n = i, t = 0;

				// prepare price array
				price = new char[ch.length - n];

				// populate array elements
				while(i < ch.length)
				{
					price[t++] = ch[i];
					i++;
				}

				// put the current prefix and price to a map
				map.put(prefix, price);
			}					
		}
	}

	// searches a prefix in BoyerMoore
	private int search(char []prefixTemp)
	{
		List<Integer> matches = BoyerMoore.match(new String(prefixTemp), new String(sb));		
		for (Integer integer : matches)
		{
			// success
			if(integer == 0)
			{			
				// return the matching prefix
				return Integer.valueOf(new String(prefixTemp));
			}
		}
		// failure no match
		return -1;	
	}

	// compare map elements
	private void compare()
	{
		int max = 0;
		String s = "";

		// iterate over prefix values
		for (char [] entry : map.keySet())
		{		
			char [] chInner = entry;
			s = new String(chInner);

			// find the longest prefix
			if(s.length() > max)
			{													
				max = s.length();
				prefix = s.toCharArray();
				price = (char[])map.get(entry);					
			}									
		}		
	}

	// strip out the alpha characters by just accepting numericals
	public void preprocess(String userInput)
	{
		char []userInputs = userInput.toCharArray();
		sb = new StringBuffer();
		for(int i = 0; i < userInputs.length; i++)
			if(userInputs[i] >= 48 && userInputs[i] <= 57)
			{
				// append numericals
				sb.append(userInputs[i]);
			}		
	}

	// read the file and call split function
	public boolean read(String filePath)
	{
		try{
			// open the file that is the first command line parameter
			FileInputStream fstream = new FileInputStream(filePath);

			// get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine;
			// read File Line By Line
			while ((strLine = br.readLine()) != null)   			
				split(strLine);				

			// close the input stream
			in.close();
			return true;

		}catch (Exception e){ System.out.println("Error: " + e.getMessage()); return false; }
	}

	// display the result
	public boolean display()
	{
		try
		{
			// map is empty, so no prefix matching the input
			if(map.isEmpty())
			{
				System.out.println("Operator " + letter + " has no deal!");
				return true;
			}
			else
			{
				// clear the map, so you can process for next block of operator				
				map.clear();
				System.out.println("Operator " + letter + " has the prefix, " + new String(prefix) + " and price: " + new String(price));
				return true;
			}

		}catch(Exception ex) { System.out.println(ex.getMessage()); return false; }
	}	
}
