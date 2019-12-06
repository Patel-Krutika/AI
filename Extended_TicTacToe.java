import java.util.*;

public class Extended_TicTacToe{
	//class attributes
	static int NONE = 0;
	static int PLAYER_X = 1;
	static int COMPUTER_O = 2;
	static int USED = 3;
	static ArrayList<HashMap> hashMap = new ArrayList<>();
	static int grid_Width = 0;
	static int grid_Lenght = 0;
	//score counters
	static int xCounter = 0;
	static int oCounter = 0;
	//keeps track of the computer's last point
	static int lastPoint_x=0;
	static int lastPoint_y=0;
	
	//crea
	public class HashMap
	{
		Key key;
		int element;
		
		public HashMap(Key key, int element)
		{
			this.key = key;
			this.element = element;
		}
	}
	
	//returns the element corresponding to the key in the hashMap
	public static int get(Key key)
	{
			int element = -1;
			for(int i = 0; i < hashMap.size(); i++)
			{
				if(hashMap.get(i).key.row == key.row && hashMap.get(i).key.col == key.col)
					return hashMap.get(i).element;
			}
			return -1;
		}
	

	//adds HashMap entry to hashMap
	public void push(int x, int y, int element)
	{
		Key key = new Key(x, y);
		HashMap entry = new HashMap(key, element);
		hashMap.add(entry);
		return;
	}
	
	//create key
	public class Key
	{
		int row;
		int col;
		
		public Key(int row, int col)
		{
			this.row = row;
			this.col = col;
		}
		
		@Override
		public String toString()
		{
			return "(" + this.row + "," + this.row + ")";
		}
	}
	
	//prints board for user, using the hashMap and the board size
	void printBoard(int x, int y)
	{
		Character[][] grid = new Character[x][y];
		Key key;
		for(int i = 0; i< x; i++)
		{
			for(int j = 0; j < y; j++)
			{
				key = new Key(i, j);
				if(get(key)== PLAYER_X)
				{
					grid[i][j] = 'X';
				}
				else if(get(key)== COMPUTER_O)
				{
					grid[i][j] = 'O';
				}
				else if(get(key) == USED)
					grid[i][j] = '*';
				else
				{
					grid[i][j] = '-';
				}
			}
		}
		
		for(Character[] row: grid)
			System.out.println(Arrays.toString(row));
		
	}
	
	//checks if there exist a given x andy as row and col respectively
	boolean keyExists(int x, int y)
	{
		Key key = new Key(x,y);
		for(int i = 0; i<hashMap.size(); i++)
		{
			if(hashMap.get(i).key.row == x && hashMap.get(i).key.col == y)
				return true;
		}
		return false;
	}
	
	//chagnes the element to a corresponding key with the new given element
	void setElement(Key key, int element)
	{
		for(int i = 0; i<hashMap.size(); i++)
		{
			if(hashMap.get(i).key.row == key.row && hashMap.get(i).key.col == key.col)
				hashMap.get(i).element = element;
		}
		return;
	}
	
	//checks if there are five marks in a line, calls the next four methods
	public boolean checkWin(int x, int y, int element)
	{
		if(horizontal(x, y, element))
			return horizontal(x,y,element);
		else if(vertical(x,y,element))
			return vertical(x,y,element);
		else if(upward_Diagonal(x,y,element))
			return upward_Diagonal(x,y,element);
		else if(downward_Diagonal(x,y,element))
			return downward_Diagonal(x,y,element);
		else
			return false;
	}
	
	//checks for 5 in a line on the horizontal
	public boolean horizontal(int x, int y, int element)
	{
		boolean match = true;
		int counter = 0;
		Key key = new Key(x, y+1);
		do
		{
			if(keyExists(key.row, key.col))
			{
				if(get(key) == element)
				{
					key.col = key.col+1;
					counter++;
				}
				else match = false;
			}
			else
				break;
		}while(match == true && counter <4);
			
		if(counter == 4)
		{
			setElement(new Key(x, y), USED);
			setElement(new Key(x, y+1), USED);
			setElement(new Key(x, y+2), USED);
			setElement(new Key(x, y+3), USED);
			setElement(new Key(x, y+4), USED);
			
			if(element == 1)
				xCounter++;
			else
				oCounter++;
		}
		match = true;
		counter =0;
		Key key2 = new Key(x, y-1);
		do
		{
			if(keyExists(key2.row, key2.col))
			{
				if(get(key2) == element)
				{
					key2.col = key2.col-1;
					counter++;
				}
				else match = false;
			}
			else
				break;
		}while(match == true && counter <4);
			
		if(counter == 4)
		{
			setElement(new Key(x, y), USED);
			setElement(new Key(x, y-1), USED);
			setElement(new Key(x, y-2), USED);
			setElement(new Key(x, y-3), USED);
			setElement(new Key(x, y-4), USED);
			
			if(element == 1)
				xCounter++;
			else
				oCounter++;
		}
		return false;		
	}
	
	//checks for 5 in a line on the vertical
	public boolean vertical(int x, int y, int element)
	{
	boolean match = true;
		int counter = 0;
		Key key = new Key(x=1, y);
		do
		{
			if(keyExists(key.row, key.col))
			{
				if(get(key) == element)
				{
					key.row = key.row+1;
					counter++;
				}
				else match = false;
			}
			else
				break;
		}while(match == true && counter <4);
			
		if(counter == 4)
		{
			setElement(new Key(x, y), USED);
			setElement(new Key(x+1, y), USED);
			setElement(new Key(x+2, y), USED);
			setElement(new Key(x+3, y), USED);
			setElement(new Key(x+4, y), USED);
			
			if(element == 1)
				xCounter++;
			else
				oCounter++;
		}
		match = true;
		counter =0;
		Key key2 = new Key(x-1, y);
		do
		{
			if(keyExists(key2.row, key2.col))
			{
				if(get(key2) == element)
				{
					key2.row = key2.row-1;
					counter++;
				}
				else match = false;
			}
			else
				break;
		}while(match == true && counter <4);
			
		if(counter == 4)
		{
			setElement(new Key(x, y), USED);
			setElement(new Key(x-1, y), USED);
			setElement(new Key(x-2, y), USED);
			setElement(new Key(x-3, y), USED);
			setElement(new Key(x-4, y), USED);
			
			if(element == 1)
				xCounter++;
			else
				oCounter++;
		}
		return false;			
	}
	
	//checks for 5 in a line on the upward diagonal
	public boolean upward_Diagonal(int x, int y, int element)
	{
		boolean match = true;
		int counter = 0;
		Key key = new Key(x-1, y+1);
		do
		{
			if(keyExists(key.row, key.col))
			{
				if(get(key) == element)
				{
					key.row = key.row-1;
					key.col = key.col+1;
					counter++;
				}
				else match = false;
			}
			else
				break;
		}while(match == true && counter <4);
			
		if(counter == 4)
		{
			setElement(new Key(x, y), USED);
			setElement(new Key(x-1, y+1), USED);
			setElement(new Key(x-2, y+2), USED);
			setElement(new Key(x-3, y+3), USED);
			setElement(new Key(x-4, y+4), USED);
			
			if(element == 1)
				xCounter++;
			else
				oCounter++;
		}
		match = true;
		counter =0;
		Key key2 = new Key(x+1, y-1);
		do
		{
			if(keyExists(key2.row, key2.col))
			{
				if(get(key2) == element)
				{
					key2.row = key2.row+1;
					key2.col = key2.col-1;
					counter++;
				}
				else match = false;
			}
			else
				break;
		}while(match == true && counter <4);
			
		if(counter == 4)
		{
			setElement(new Key(x, y), USED);
			setElement(new Key(x+1, y-1), USED);
			setElement(new Key(x+2, y-2), USED);
			setElement(new Key(x+3, y-3), USED);
			setElement(new Key(x+4, y-4), USED);
			
			if(element == 1)
				xCounter++;
			else
				oCounter++;
		}
		return false;		
	}
	
	//checks for 5 in a line on the downward diagonal
	public boolean downward_Diagonal(int x, int y, int element)
	{
			boolean match = true;
		int counter = 0;
		Key key = new Key(x-1, y-1);
		do
		{
			if(keyExists(key.row, key.col))
			{
				if(get(key) == element)
				{
					key.row = key.row-1;
					key.col = key.col-1;
					counter++;
				}
				else match = false;
			}
			else
				break;
		}while(match == true && counter <4);
			
		if(counter == 4)
		{
			setElement(new Key(x, y), USED);
			setElement(new Key(x-1, y-1), USED);
			setElement(new Key(x-2, y-2), USED);
			setElement(new Key(x-3, y-3), USED);
			setElement(new Key(x-4, y-4), USED);
			
			if(element == 1)
				xCounter++;
			else
				oCounter++;
		}
		match = true;
		counter =0;
		Key key2 = new Key(x+1, y+1);
		do
		{
			if(keyExists(key2.row, key2.col))
			{
				if(get(key2) == element)
				{
					key2.row = key2.row+1;
					key2.col = key2.col+1;
					counter++;
				}
				else match = false;
			}
			else
				break;
		}while(match == true && counter <4);
			
		if(counter == 4)
		{
			setElement(new Key(x, y), USED);
			setElement(new Key(x+1, y+1), USED);
			setElement(new Key(x+2, y+2), USED);
			setElement(new Key(x+3, y+3), USED);
			setElement(new Key(x+4, y+4), USED);
			
			if(element == 1)
				xCounter++;
			else
				oCounter++;
		}
		return false;	
	}
	
	//picks the computers move by checking all eight directions and if none are available, places random move
	public void compMove()
	{
		Random rand = new Random();
		//checks and places on the horizontal direction
		if(!keyExists(lastPoint_x, lastPoint_y+1))
		{
			if(!keyExists(lastPoint_x, lastPoint_y+2) && lastPoint_y+2<grid_Width )
			{
				push(lastPoint_x, lastPoint_y+1, COMPUTER_O);
				lastPoint_x=lastPoint_x;
				lastPoint_y=lastPoint_y+1;
				return;
			}
		}
		
		if(!keyExists(lastPoint_x, lastPoint_y-1))
		{
			if(!keyExists(lastPoint_x, lastPoint_y-2) && lastPoint_y-2>=0)
			{
				push(lastPoint_x, lastPoint_y-1, COMPUTER_O);
				lastPoint_x=lastPoint_x;
				lastPoint_y=lastPoint_y-1;
				return;
			}
		}
		
		//checks and places on the vertical
		if(!keyExists(lastPoint_x-1, lastPoint_y))
		{
			if(!keyExists(lastPoint_x-2, lastPoint_y) && lastPoint_x-2>=0)
			{
				push(lastPoint_x-1, lastPoint_y, COMPUTER_O);
				lastPoint_x=lastPoint_x-1;
				lastPoint_y=lastPoint_y;
				return;
			}
		}
		
		if(!keyExists(lastPoint_x+1, lastPoint_y))
		{
			if(!keyExists(lastPoint_x+2, lastPoint_y) && lastPoint_x+2<grid_Lenght)
			{
				push(lastPoint_x+1, lastPoint_y, COMPUTER_O);
				lastPoint_x=lastPoint_x+1;
				lastPoint_y=lastPoint_y;
				return;
			}
		}
		
		//checks and places on the upward diagonal
		if(!keyExists(lastPoint_x-1, lastPoint_y+1))
		{
			if(!keyExists(lastPoint_x-2, lastPoint_y+2) && lastPoint_x>=0 && lastPoint_y+2<grid_Width)
			{
				push(lastPoint_x-1, lastPoint_y+1, COMPUTER_O);
				lastPoint_x=lastPoint_x-1;
				lastPoint_y=lastPoint_y+1;
				return;
			}
		}
		
		if(!keyExists(lastPoint_x+1, lastPoint_y-1))
		{
			if(!keyExists(lastPoint_x+2, lastPoint_y-2) && lastPoint_x+2<grid_Lenght && lastPoint_y-2>=0)
			{
				push(lastPoint_x+1, lastPoint_y-1, COMPUTER_O);
				lastPoint_x=lastPoint_x+1;
				lastPoint_y=lastPoint_y-1;
				return;
			}
		}
		
		//checks and places on the downward diagonal
		if(!keyExists(lastPoint_x-1, lastPoint_y-1))
		{
			if(!keyExists(lastPoint_x-2, lastPoint_y-2) && lastPoint_x-2>=0 && lastPoint_y-2>=0)
			{
				push(lastPoint_x-1, lastPoint_y-1, COMPUTER_O);
				lastPoint_x=lastPoint_x-1;
				lastPoint_y=lastPoint_y-1;
				return;
			}
		}
		
		if(!keyExists(lastPoint_x+1, lastPoint_y+1))
		{
			if(!keyExists(lastPoint_x+2, lastPoint_y+2) && lastPoint_x+2<grid_Lenght && lastPoint_y+2<grid_Width)
			{
				push(lastPoint_x+1, lastPoint_y+1, COMPUTER_O);
				lastPoint_x=lastPoint_x+1;
				lastPoint_y=lastPoint_y+1;
				return;
			}
		}
		boolean match = false;
		int r;
		int r2;
		while(!match)
		{
			r = rand.nextInt(4);
			r2 = rand.nextInt(4);
			if(!keyExists(r,r2));
			{
				push(r,r2, COMPUTER_O);
				lastPoint_x = r;
				lastPoint_y = r2;
				match = true;
			}
		}
		return;
	}
	
	//checks if the game board is full
	public static boolean gameOver()
	{
		if(hashMap.size() == (grid_Lenght*grid_Width))
		{
			return true;
		}
		else return false;
	}
	
	//driver class for class Extended_TicTacToe
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		Extended_TicTacToe board = new Extended_TicTacToe();
		Random rand = new Random();
		
		System.out.println("Hello, Welcome to Extended Tic-Tac-Toe");
		System.out.println("Please enter the grid dimensions followed by a space: x y");
		
		grid_Width = scan.nextInt();
		grid_Lenght = scan.nextInt();
		
		lastPoint_x = rand.nextInt(4);
		lastPoint_y = rand.nextInt(4);
		
		board.compMove();
		
		int x = 0;
		int y = 0;
		
		boolean play = true;
		int choice = -1;
		while(play)
		{
			long begin = System.currentTimeMillis();
			long end = begin+(2*60*1000);//2 minute timer
			
			
			while(System.currentTimeMillis()<end)
			{
				System.out.println("Enter row and column where you would like to place an 'x' ");
				System.out.println("Enter the row and column seperated by a space: x y");
				
				x = scan.nextInt()-1;
				y = scan.nextInt()-1;
				board.push(x,y, PLAYER_X);
				//check for a line of 5 for player
				for(int i = 0; i<hashMap.size(); i++)
				{
					board.checkWin(hashMap.get(i).key.row, hashMap.get(i).key.col, PLAYER_X);
				}
				
				board.printBoard(grid_Width, grid_Lenght);
				System.out.println("Player Score: " + xCounter + "      Computer Score: " + oCounter);
				
				System.out.println("----------------------------------------");
				
				//checks if the grid is full, and breaks if so
				if(gameOver())
					break;
				
				board.compMove();
				//check for a line of 5 for computer
				board.checkWin(lastPoint_x, lastPoint_y, COMPUTER_O);
				//checks if the grid is full, and breaks if so
				if(gameOver())
					break;
				
				board.printBoard(grid_Width, grid_Lenght);
				System.out.println("Player Score: " + xCounter + "      Computer Score: " + oCounter);
				
				System.out.println("----------------------------------------");
				
			}
			
			if(xCounter>oCounter)
				Systm.out.println("You won!");
			else if(oCounter>xCounter)
				System.out.println("The computer won");
			else
				System.out.println("Its a Draw")';
			
			System.out.println("Would you like to play again?");
			System.out.println("Enter 1 for yes, 0 for no");
			
			if(scan.nextInt() == 0)
				play = false;
		}
		
		System.out.println("Thanks for playing, goodbye!");
	}
}






