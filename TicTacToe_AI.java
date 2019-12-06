import java.util.*;
/**
Name: TicTacToe_AI
Author: Krutika Patel
Description: This program creates a player vs. computer tic tac toe with
an intelligent computer that find the best move based on an implementation
of the Minimax algorithm
*/
public class TicTacToe_AI{
	//class attributes
	public static int NONE = 0;//empty cell
	public static int PLAYER = 1; //x
	public static int COMPUTER = 2; //o
	public static int[][] grid = new int[3][3];//game board
	public static Point computerMove = new Point(0,0);// computer's move set to (0,0) initially
	
	/**
	The Point class is a class in class TicTacToe_AI to create a coordinate value for a move
	*/
	public static class Point
	{
		//class attributes
		int x, y;
		
		/**
		Constructor for class Point, it takes an integer x and y that are coordinates for a point
		@param x Point's x coordinate
		@param y Point's y coodinate
		*/
		public Point(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
		/**
		The toString method overrides the toString method of Object class and return a string form of the point
		@return String toString version of point
		*/
		@Override
		public String toString()
		{
			return "("+ this.x + ", " + this.y +")";
		}
	}
	
	/**
	The printBoard method takes the internal integer array board, maps its values to the corresponding symbol
	and print the resulting character array.
	@param grid the internal integer array
	*/
	public static void printBoard(int[][] grid)
	{
		char sym;
		char[][] board = new char[3][3];
		for(int i = 0; i<3; i++)
		{
			for(int j = 0; j<3; j++)
			{
				if(grid[i][j] == 0)
				{
					sym = '-';
					board[i][j] = sym;
				}
				else if(grid[i][j] == 1)
				{
					sym = 'x';
					board[i][j] = sym;
				}
				else
				{
					sym = 'o';
					board[i][j] = sym;
				}
			}
		}
		for(int i = 0; i<3; i++)
		{
			System.out.println(board[i][0] + " " + board[i][1] + " " + board[i][2]);
		}
	}
	
	/**
	The resetBoard method resets the board if the player choose to play again
	*/
	public static void resetBoard()
	{
		for(int i =0; i<3; i++)
		{
			for(int j = 0; j<3; j++)
			{
				grid[i][j] = 0;
			}
		}
	}
	
	/**
	The win method checks the internal integer array and checks for a win for the given player
	@param player the corresponding number of the player whose win we are checking
	@return true if win has occured else false
	*/
	public static boolean win(int player)
	{
		//checks win in row and column
		for(int i = 0; i<3; i++)
		{
			if((grid[i][0]== grid[i][1] && grid[i][1]==grid[i][2] && grid[i][1] == player) || (grid[0][i]==grid[1][i] && grid[1][i]==grid[2][i] && grid[1][i] == player))
			{
				return true;
			}
		}
		//checks win in both diagonals
		if(((grid[0][0] == grid[1][1]) && (grid[1][1] == grid[2][2]) && (grid[1][1] == player)) || (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[1][1] == player))
		{
			return true;
		}
		return false;
	}
	
	/**
	The gameOver method checks if  either player has won, or if there are no available cells: resulting in a draw
	@return true if any there is a win for wither player or a draw, false otherwise
	*/
	public static boolean gameOver()
	{
		if( win(PLAYER) || win(COMPUTER) || (allAvailableCells().size()==0))
			return true;
		return false;
	}
	
	/**
	The method allAvailableCells return a list of points cooresponding to empty cells on the board
	@ return availableCells list of points for available points
	*/
	public static ArrayList<Point> allAvailableCells()
	{
		ArrayList<Point> availableCells = new ArrayList<Point>();
		
		for(int i = 0; i<3; i++)
		{
			for(int j =0; j<3; j++)
			{
				if(grid[i][j] == 0)
					availableCells.add( new Point(i,j));
			}
		}
		return availableCells;
	}
	
	/**
	The move method places a move for the given player on the given point
	@param player player for whom the move is being placed
	@param point the point at which the move is placed
	@return true if move was succesfully placed false otherwise
	*/
	public static boolean move(int player, Point point)
	{
		if(grid[point.x][point.y] != NONE)
		{
			return false;
		}
		grid[point.x][point.y] = player;
		return true;
	}
	
	/**
	The minimax method implements the minimax algorithm and stores the best move for the computer in variable computerMove
	by comparing the scores returned by its recursive call.
	@param player the player for whom we are finding the best move
	@param depth the number of levels we are checking
	@return value scoring value based on the player
	*/
	public static int minimax(int player, int depth)
	{
		//check base cases
		
		//player won
		if(win(COMPUTER))
			return 1;
		//computer won
		if(win(PLAYER))
			return -1;
		//draw
		if(allAvailableCells().size() == 0)
			return 0;
		
		int maxVal = -10;
		int minVal = 10;
		
		ArrayList<Point> points = allAvailableCells();
		
		//loop through and recursivly find the best score
		//for the breanches below this level
		
		for(int i = 0; i< points.size(); i++)
		{
			//get point
			Point p = points.get(i);
			if(player == COMPUTER)
			{
				
				move(COMPUTER, p);
				int cost = minimax(PLAYER, depth+1);
				maxVal = Math.max(cost, maxVal);
				
				/*just to check the scores
				if(depth == 0)
					System.out.println("Computer Score for Position " + p + "= " + cost);
				*/
				
				//assign computerMove
				if(cost >= 0)
					if(depth == 0)
					{	computerMove.x = p.x;
						computerMove.y = p.y;
					}
				if(cost == 1)
				{
					resetMove(p);
					break;
				}
				//assign computerMove
				if(i == points.size() -1 && maxVal < 0)
					if(depth == 0)
					{	computerMove.x = p.x;
						computerMove.y = p.y;
					}
			}
			else if(player == PLAYER)
			{
				//place o at point
				move(PLAYER, p);
				
				int cost = minimax(depth + 1, COMPUTER);
				minVal = Math.min(cost, minVal);
				
				if(minVal == -1)
				{
					resetMove(p);
					break;
				}
				
			}
			resetMove(p);
		}
		return (player == PLAYER) ? maxVal:minVal;
	}
	
	/**
	The method resetMove sets the given point to 0 (empty)
	@param point point to be set to empty
	*/
	public static void resetMove(Point point)
	{
		grid[point.x][point.y] = NONE;
	}
	
	/**
	Prints message to get player to input point coordinate
	*/
	public static void prompt()
	{
		System.out.println("Please enter the coordinate where you would like to move in the form 'x y'");
		System.out.println("");
	}
	
	/**
	Lets the player know their given point was already filled
	*/
	public static void invalid()
	{
		System.out.println("This point is already full");
	}
	
	/**
	Asks player if they would like to play again
	*/
	public static void playAgain()
	{
		System.out.println("Would you like to play again?");
		System.out.println("Enter 0 for yes, or 1 for no");
	}
	
	/**
	Driver method for class TicTacToe_AI
	*/
	public static void main(String[] args)
	{
		boolean play = true;
		boolean win = false;
		Scanner input = new Scanner(System.in);
		System.out.println("");
		System.out.println("Welcome to TicTacToe");
		System.out.println("");
		System.out.println("Your player symbol is: x");
		System.out.println("The board goes from 1-3 for x and y");
		System.out.println("");
		printBoard(grid);
		System.out.println("");
		while(play)
		{
			while(!gameOver())
			{
				boolean validPlay = true;
				do
				{
					if(!validPlay)
					{
						invalid();
					}
					
					prompt();
					validPlay = move(PLAYER, new Point(input.nextInt()-1, input.nextInt()-1));

				}while(validPlay == false);
				
				printBoard(grid);
				System.out.println("");
				System.out.println("--------------");
				System.out.println("");
				
				//check if PLAYER Won or if Draw and break if true
				if(gameOver())
				{
					break;
				}
				
				minimax(COMPUTER, 0);
				move(COMPUTER, computerMove); 
				System.out.println("Computer's move");
				printBoard(grid);
				System.out.println("");
				System.out.println("--------------");
				System.out.println("");
			}
			if(win(PLAYER))
			{
				System.out.println("You Won!!");
			}
			else if(win(COMPUTER))
			{
				System.out.println("COmputer Won!!");
			}
			else
			{
				System.out.println("Its a Draw");
			}
			
			playAgain();
			
			if(input.nextInt() == 1)
			{
				play = false;
			}
			
			resetBoard();
		}
		System.out.println("Thanks for playing, Goodbye.");
	}
}