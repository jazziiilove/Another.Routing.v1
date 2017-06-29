/* 
 * Programmer: Baran Topal                   																			*
 * Project name: Some.Routing.v1          																			    *
 * Folder name: src        																								*
 * Package name: com.some.application  																					*
 * File name: RoutingMain.java                     																	    *
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

import java.util.Scanner;

public class RoutingMain {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Routing r = new Routing();		

		// user input
		System.out.println("Enter the characters to be searched: ");
		Scanner scanIn = new Scanner(System.in);
		String sWhatever = scanIn.nextLine();	    
		scanIn.close();

		// preprocess and strip out delimiters
		r.preprocess(sWhatever);

		// read file content
		r.read("List.txt");

		// display all matching
		r.display();	    
	}
}
