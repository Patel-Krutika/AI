import java.util.*;

public class HillClimbing
{
	//stand in value for min number of conflicts
	static int min = 1000;
	
	//create a 2D array representing a board based on the given queens array
	static int[][] createBoard(int[] board)
	{
		int[][] newBoard = new int[board.length][board.length];
		for(int j = 0; j<board.length; j++)
		{
			for(int i = 0; i<board.length; i++)
			{
				if(board[j] == i)
					newBoard[i][j] = 1;
				else
					newBoard[i][j] = 0;
			}
		}
		return newBoard;
	}
	
	//print 2D int array into a board with Queens
	static void printBoard(int[][] board)
	{
		char[][] printableBoard = new char[board.length][board.length];
		for(int i = 0; i<board.length; i++)
		{
			for(int j = 0; j<board.length; j++)
			{
				if(board[i][j] == 1)
					printableBoard[i][j] = 'Q';
				else
					printableBoard[i][j] = '-';
			}
		}
		System.out.println(Arrays.deepToString(printableBoard).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
	}
	
	//get the number of conflicts
	static int getConflicts(int n)
	{
		int sum = 0;
		for(int i = 1; i<n; i++)
		{
			sum = sum + (n-i);
		}
		return sum;
	}
	
	//get the 2D-array that represent the number of conflicts
	//arising when a queen is placed in a respected cell
	static int getHueristic(int[][] board)
	{
		int sum = 0;
		int qCounter=0;
		
		//check row conflict
		for(int i = 0; i<board.length; i++)
		{
			for(int j = 0; j<board.length; j++)
			{
				if(board[i][j]==1)
					qCounter++;
			}
			if(qCounter>1)
				sum = sum + getConflicts(qCounter);
			qCounter=0;
		}
		
		
		int counter = 0;
		int n = 1;
		int nR = 0;
		int nC = 0;
		int queenCounter = 0;
		while(n<=7)
		{
			nR = n;
			nC = 0;
			while(nR!=-1 && nC!=n+1)
			{
				if(board[nR][nC] == 1)
				{
					qCounter++;
				}
				nR--;
				nC++;
			}
			if(qCounter>1)
				sum = sum +getConflicts(qCounter);
			qCounter = 0;
			n++;
		}
		//check all diagonal conflicts
		int[][] d8 = {{7,6,5,4,3,2,1},{1,2,3,4,5,6,7}};
		int[][] d9 = {{7,6,5,4,3,2},{2,3,4,5,6,7}};
		int[][] d10 = {{7,6,5,4,3},{3,4,5,6,7}};
		int[][] d11 = {{7,6,5,4},{4,5,6,7}};
		int[][] d12 = {{7,6,5},{5,6,7}};
		int[][] d13 = {{7,6},{6,7}};
		int[][] d14 = {{6,7},{0,1}};
		int[][] d15 = {{5,6,7},{0,1,2}};
		int[][] d16 = {{4,5,6,7},{0,1,2,3}};
		int[][] d17 = {{3,4,5,6,7},{0,1,2,3,4}};
		int[][] d18 = {{2,3,4,5,6,7},{0,1,2,3,4,5}};
		int[][] d19 = {{1,2,3,4,5,6,7},{0,1,2,3,4,5,6}};
		int[][] d20 = {{0,1,2,3,4,5,6,7},{0,1,2,3,4,5,6,7}};
		int[][] d21 = {{0,1,2,3,4,5,6},{1,2,3,4,5,6,7}};
		int[][] d22 = {{0,1,2,3,4,5},{2,3,4,5,6,7}};
		int[][] d23 = {{0,1,2,3,4},{3,4,5,6,7}};
		int[][] d24 = {{0,1,2,3},{4,5,6,7}};
		int[][] d25 = {{0,1,2},{5,6,7}};
		int[][] d26 = {{0,1},{6,7}};

		for(int i = 0; i<7; i++)
		{
				if(board[d8[0][i]][d8[1][i]]==1)
				{
					qCounter++;
				}
		}
		if(qCounter>1)
			sum = sum+getConflicts(qCounter);
		qCounter=0;
		
		for(int i = 0; i<6; i++)
		{
			
				if(board[d9[0][i]][d9[1][i]]==1)
					qCounter++;
			
		}			
		if(qCounter>1)
			sum = sum+getConflicts(qCounter);
		qCounter=0;
		
		for(int i = 0; i<5; i++)
		{
			
				if(board[d10[0][i]][d10[1][i]]==1)
					qCounter++;
			
		}			
		if(qCounter>1)
			sum = sum+getConflicts(qCounter);
		qCounter=0;
		
		for(int i = 0; i<4; i++)
		{
				if(board[d11[0][i]][d11[1][i]]==1)
					qCounter++;
			
		}			
		if(qCounter>1)
			sum = sum+getConflicts(qCounter);
		qCounter=0;
		
		for(int i = 0; i<3; i++)
		{
			
				if(board[d12[0][i]][d12[1][i]]==1)
					qCounter++;
			
		}			
		if(qCounter>1)
			sum = sum+getConflicts(qCounter);
		qCounter=0;
		
		for(int i = 0; i<2; i++)
		{
			
				if(board[d13[0][i]][d13[1][i]]==1)
					qCounter++;
			
		}			
		if(qCounter>1)
			sum = sum+getConflicts(qCounter);
		qCounter=0;
		
		for(int i = 0; i<2; i++)
		{
			
				if(board[d14[0][i]][d14[1][i]]==1)
					qCounter++;
			
		}			
		if(qCounter>1)
			sum = sum+getConflicts(qCounter);
		qCounter=0;
		
		for(int i = 0; i<3; i++)
		{
			
				if(board[d15[0][i]][d15[1][i]]==1)
					qCounter++;
			
		}			
		if(qCounter>1)
			sum = sum+getConflicts(qCounter);
		qCounter=0;
		
		for(int i = 0; i<4; i++)
		{
			
				if(board[d16[0][i]][d16[1][i]]==1)
					qCounter++;
			
		}			
		if(qCounter>1)
			sum = sum+getConflicts(qCounter);
		qCounter=0;
		
		for(int i = 0; i<5; i++)
		{
			
				if(board[d17[0][i]][d17[1][i]]==1)
					qCounter++;
			
		}			
		if(qCounter>1)
			sum = sum+getConflicts(qCounter);
		qCounter=0;
		
		for(int i = 0; i<6; i++)
		{
				if(board[d18[0][i]][d18[1][i]]==1)
					qCounter++;
			
		}			
		if(qCounter>1)
			sum = sum+getConflicts(qCounter);
		qCounter=0;
		
		for(int i = 0; i<7; i++)
		{
				if(board[d19[0][i]][d19[1][i]]==1)
					qCounter++;
			
		}			
		if(qCounter>1)
			sum = sum+getConflicts(qCounter);
		qCounter=0;
		
		for(int i = 0; i<8; i++)
		{
				if(board[d20[0][i]][d20[1][i]]==1)
					qCounter++;
			
		}			
		if(qCounter>1)
			sum = sum+getConflicts(qCounter);
		qCounter=0;
		
		for(int i = 0; i<7; i++)
		{
				if(board[d21[0][i]][d21[1][i]]==1)
					qCounter++;
			
		}			
		if(qCounter>1)
			sum = sum+getConflicts(qCounter);
		qCounter=0;
		
		for(int i = 0; i<6; i++)
		{
			
				if(board[d22[0][i]][d22[1][i]]==1)
					qCounter++;
			
		}			
		if(qCounter>1)
			sum = sum+getConflicts(qCounter);
		qCounter=0;
		
		for(int i = 0; i<5; i++)
		{
			
				if(board[d23[0][i]][d23[1][i]]==1)
					qCounter++;
			
		}			
		if(qCounter>1)
			sum = sum+getConflicts(qCounter);
		qCounter=0;
		
		for(int i = 0; i<4; i++)
		{
			
				if(board[d24[0][i]][d24[1][i]]==1)
					qCounter++;
			
		}			
		if(qCounter>1)
			sum = sum+getConflicts(qCounter);
		qCounter=0;
		
		for(int i = 0; i<3; i++)
		{
				if(board[d25[0][i]][d25[1][i]]==1)
					qCounter++;
		}			
		if(qCounter>1)
			sum = sum+getConflicts(qCounter);
		qCounter=0;
		
		for(int i = 0; i<2; i++)
		{
			
				if(board[d26[0][i]][d26[1][i]]==1)
					qCounter++;
		}			
		if(qCounter>1)
			sum = sum+getConflicts(qCounter);
		qCounter=0;
		
		return sum;
	}
	
	static int[][] makeHueristicBoard(int[][] board, int[] queens)
	{
		int[][] heuristicBoard = new int[board.length][board.length];
		
		for(int j = 0; j<board.length; j++)
		{
			int queenPosition = queens[j];
			for(int i = 0; i<board.length; i++)
			{
				if(i!=queenPosition)
				{
					board[i][j] = 1;
					board[queenPosition][j] = 0;
					heuristicBoard[i][j]=getHueristic(board);
					board[queenPosition][j] = 1;
					board[i][j] = 0;
				}
				else
				{
					heuristicBoard[i][j] = 100000;
				}
			}
		}
		return heuristicBoard;
	}
	//choose the cell with the lowest conflicts, move the queen there and return the new board
	public static int[][] getNextBoard(int[][] heuristicBoard, int[][] board,int[] queens)
	{
		for(int i = 0; i<heuristicBoard.length; i++)
		{
			for(int j = 0; j<heuristicBoard.length; j++)
			{
				if(heuristicBoard[i][j]<min)
					min = heuristicBoard[i][j];
			}
		}
		
		for(int j = 0; j<board.length; j++)
		{
			for(int i = 0; i<board.length; i++)
			{
				if(heuristicBoard[i][j]==min)
				{
					board[queens[j]][j] = 0;
					board[i][j] = 1;
					return board;
				}
			}
		}
		return board;
	}
	
	//convert the 2D board into an int[] depicting
	//the row positions for a queen in each column
	static int[] getQueensArray(int[][] board)
	{
		int[] queens = new int[board.length];
		for(int j = 0; j<board.length; j++)
		{
			for(int i = 0; i<board.length; i++)
			{
				if(board[i][j] == 1)
				{
					queens[j] = i;
					break;
				}
			}
		}
		return queens;
	}
	
	public static void main(String[] args)
	{
		int[] queens = {4,5,6,3,4,5,6,5};
		int[][] board = createBoard(queens);
		int count = 0;
		while(count<8)
		{
			board = getNextBoard(makeHueristicBoard(board,queens),board,queens);
			queens = getQueensArray(board);
			printBoard(board);
			System.out.println(min);
			count++;
		}
	}
}