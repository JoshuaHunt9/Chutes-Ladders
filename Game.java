/**
 * 
 */
package edu.ilstu;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 */
public class Game
{
	//MAKE A GOOGLE SPREAD SHEET TESTING OUT EACH METHOD
	
	//FORMAT
	//Expected Result: ...
	//Actual Result: ...
	
	private static class Square
	{		
		private ArrayList<Integer> players; //the players on the specific square
		
		private Square prevSquare;
		private Square nextSquare;
		
		private int squareNum; //square tile number
		private int jumpValue; //jump value
		
		private boolean golden; //if square is golden or not
				
		public Square()
		{			
			prevSquare = null;
			nextSquare = null;
			
			squareNum = 1;
			
			players = new ArrayList<Integer>();
		}
		
		public Square(int squareNum, int jumpValue, Square prev)
		{
			this.squareNum = squareNum;
			this.jumpValue = jumpValue;
			
			prevSquare = prev;
			nextSquare = null;
			
			players = new ArrayList<Integer>();
		}
	}
	
	private Square start; //starting node
	private ArrayList<Square> playerSquares;
	private int player;
	
	private int numPlayers;
	
	private boolean goldSquareChoosen;
	private Scanner keys;
	
	
	public Game(int numPlayers)
	{
		keys = new Scanner(System.in);
		
		this.numPlayers = numPlayers;
		
		goldSquareChoosen = false;
		
		playerSquares = new ArrayList<Square>(numPlayers);
		
		createBoard();
		
	}
	
	public void createBoard()
	{
		int goldenSquare = goldenRandomizer();
		
		for(int i = 1; i < 101; i++)
		{	
			Square square = new Square();
			if(start == null)
			{
				start = square;
				start.squareNum = 1;
			}
			else
			{
				Square temp = start;
				
				while(temp.nextSquare != null)
				{
					temp = temp.nextSquare;
				}
				
				//picks a random golden square
				if(goldSquareChoosen == false && i == goldenSquare)
				{
					temp.nextSquare = new Square(i,0,temp);
					temp.golden = true;
						
					goldSquareChoosen = true;
					
					System.out.println("The Golden Square Is Square #" + (i-1) + "\n");					
				}
				else
				{
					temp.nextSquare = new Square(i,jumpRandomizer(),temp);
				}
			}
		}
	}
	
	
	public void play()
	{
		boolean win = false;
		
		while(win == false)
		{
			for(int i = 0; i < numPlayers; i++)
			{
				System.out.print("Press W To Roll The Die: ");
				
				String key = keys.next();
				
				if(key.equals("w") || key.equals("W"))
				{
					keys.nextLine(); 
					
					System.out.println("\n");
					 
					int dice = diceRoll();
					win = move((i + 1),dice);
					
					//if the player wins, then both the for loop and while ends
					if(win == true)
					{
						i = numPlayers;
					}
				}
				else
				{
					i--;
					System.out.println("\nInvalid Input, Try Again.\n");
				}
			}
		}
		
		
	}
	
	
	public boolean move(int playerNumber, int squaresToMove)
	{
		// Move the player to the square.
		// Make the necessary jump if needed. The player will only jump once.
		// prints the starting and ending locations of that player indicating any jumps taken.
		// returns true if the player won.
		
		//int gold = goldenSquareNum;
		
		
		//move player -> find the player in the array
		//move
		//set 
		
		
		Square square = start;
		
		
		
		
		//check the location of the player by going thru arraylist in each square 
		//(have as a separate method)
		
		//once square.players does not equal null
		//go through the player array to see if player is on the square
		
		//then do the jump from that current player location
		
		int loop = -1;
		int squareNum = 0;
		
		ArrayList<Integer> players = new ArrayList<Integer>();
		
		//while(square.players == null && loop < 0)
		
		while(loop < 0)
		{
			try
			{
				players = square.players;				
				
				
				//finding out if people are on the square
				if(players != null && players.size() > 0)
				{
					//go through the player array
					for(int i = 0; i <= players.size(); i++)
					{
						//if the specified player is not found
						if( i == players.size())
						{
							square = square.nextSquare;
						}
						else
						{
							//if the player is found
							if(players.get(i) == playerNumber)
							{
								//sets the player to jump from it's current location
								//removes player from it's previous location
								players.remove(i);
								
								squareNum = square.squareNum; //current location of player before the jump
								
								//ends the while loop
								loop++;
							}
						}
					}
					
				}
				else
				{
					 square = square.nextSquare;
				}
			}
			catch(NullPointerException npe)
			{
				square = start;
				loop++;
			}
		}
		
		System.out.println("Starting Square For Player " + playerNumber + ": " + square.squareNum);
		
		
		
		//the actual jump to the square
		int moves = 0;
		
		while(moves < squaresToMove)
		{
			if(square == null || square.squareNum == 100)
			{
				System.out.println("Player " + playerNumber + " Has Landed On The Final Square!");
				System.out.println("Player " + playerNumber + " Wins!");
				return true;
			}
			else
			{
				square = square.nextSquare;
				moves++;
			}
		}
		
		
		
		
		
		int jump = 0;		
		int value = square.jumpValue; 		
		
		
		
		
		//additional jump for the jumpValue?
		if(square.jumpValue > 0)
		{
			try
			{
				while(jump != square.jumpValue)
				{
					square = square.nextSquare;
					jump++;
				}
				
				players = square.players; //set array to the square array
				players.add(playerNumber); //add player to the players array within the square
				squareNum = square.squareNum;
								
			}
			catch(NullPointerException npe)
			{
				System.out.println("Player " + playerNumber + " Has Landed On The Final Square!");
				System.out.println("Player " + playerNumber + " Wins!");
			}
		}
		
		if(square.jumpValue < 0)
		{
				while(jump != value)
				{
					if(square == null)
					{
						square = start;
					}
					else
					{
						jump--;
						square = square.prevSquare;
					}
				}
		}
		

			players = square.players;
			players.add(playerNumber);
			
		
		
		
		
		
		System.out.println("Current Square Jump Value: " + value);
		System.out.println("Ending Square For Player " + playerNumber + ": " + square.squareNum + "\n");
		
		//determines if the player wins or not
		if(square.golden == true)
		{
			//This executes when the player lands on the golden square due to the jump value
			
			//PROBLEM: console says the player wins when ever it's one square away from the golden square
			
			System.out.println("Player " + playerNumber + " Has Landed On The Golden Square!");
			System.out.println("Player " + playerNumber + " Wins!");
			return true;
		}
		else if(square.squareNum == 100)
		{
			System.out.println("Player " + playerNumber + " Has Landed On The Final Square!");
			System.out.println("Player " + playerNumber + " Wins!");
			return true;
		}
		else
		{
			return false;
		}
	}

	public int diceRoll()
	{
		int num = (int)(Math.random() * 6) + 1;
		System.out.println("Dice Roll: " + num + "\n");
		return num;
	}

	
	//randomizer for jumpValue
	public int jumpRandomizer()
	{
		int num = (int)(Math.random() * 4) + 0;
		
		if(num == 0)
		{
			return 0;
		}
		else if(num == 1)
		{
			return 0;
		}
		else if(num == 2)
		{
			return 0;
		}
		else if(num == 3)
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}
	
	//randomizer to choose the "golden square"
	public int goldenRandomizer()
	{
		int num = (int)(Math.random() * 100) + 1;
		return num;
	}
	
}
