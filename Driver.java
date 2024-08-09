/**
 * 
 */
package edu.ilstu;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 */
public class Driver {
	
	static Scanner keys;
	
	public static void main(String[] args) 
	{
		keys = new Scanner(System.in);
		
		System.out.println("Welcome To Chutes & Ladders!\n");
		
		int loop = -1;
		int numOfPlayers = 0;
		
		while(loop < 0)
		{
			try
			{
				System.out.print("Enter Number Of Players: ");
				numOfPlayers = keys.nextInt();
				
				keys.nextLine();
				
				System.out.println("\n");
				
				if(numOfPlayers <= 0)
				{
					System.out.println("Players Cannot Be Zero Or Less, Try Again\n");
				}
				else
				{
					loop++;
				}
			}
			catch(InputMismatchException ime)
			{
				System.out.println("Invalid Input, Try Again\n");
				keys.nextLine();
			}
		}
		
		Game game = new Game(numOfPlayers);
		game.play();
		
	}

}
