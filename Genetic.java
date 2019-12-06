import java.util.*;
import java.lang.*;

public class Genetic
{
	
	
	public static int[] generateIndividual(int n)
	{
		//make an array [8] using random numbers 0-(n-1)
		int[] queens = new int[n];
		Random rand = new Random();
		int random = 0;
		for(int i = 0; i<n; i++)
		{
			random = rand.nextInt(n);
			queens[i] =random;
		}
		return queens;
	}
	
	public static int[][] generatePopulation(int r, int c)
	{
		//create multiple individuals
		int[][] population = new int[r][c];
		int[] individual = new int[c];
		
		for(int i = 0; i<r; i++)
		{
			individual = generateIndividual(c);
			for(int j = 0; j<c; j++)
			{
				population[i][j] = individual[j];
			}
		}
		
		return population;
	}
	
	static boolean solution(int[] board)
	{
		//all solutions to 8-queens problem
		int[] solution1 = {0,6,4,7,1,3,5,2};
		int[] solution2 = {0,6,3,5,7,1,4,2};
		int[] solution3 = {5,0,4,1,7,2,6,3};
		int[] solution4 = {3,0,4,7,1,6,2,5};
		int[] solution5 = {4,0,7,3,1,6,2,5};
		int[] solution6 = {2,0,6,4,7,1,3,5};
		int[] solution7 = {4,0,3,5,7,1,6,2};
		int[] solution8 = {4,0,7,5,2,6,1,3};
		int[] solution9 = {4,6,0,3,1,7,5,2};
		int[] solution10 = {6,0,2,7,5,3,1,4};
		int[] solution11 = {5,2,0,7,3,1,6,4};
		int[] solution12 = {4,2,0,6,1,7,5,3};
		
		//check if given board is a solution
		if(board==solution1)
			return true;
		else if(board==solution2)
			return true;
		else if(board==solution3)
			return true;
		else if(board==solution4)
			return true;
		else if(board==solution5)
			return true;
		else if(board==solution6)
			return true;
		else if(board==solution7)
			return true;
		else if(board==solution8)
			return true;
		else if(board==solution9)
			return true;
		else if(board==solution10)
			return true;
		else if(board==solution11)
			return true;
		if(board==solution12)
			return true;
		
		return false;
	}
	
	public static void main(String[] args)
	{
		int pop = 10;
		Random random = new Random();
		int parent = random.nextInt(10);
		int[] parent1 = new int[8];
		int[] parent2 = new int[8];
		int[] child1 = new int[8];
		int[] child2 = new int[8];
		int crossover = 0;
		int mutation = 0;
		int mutationVal = 0;
		int[][] population = new int[10][8];
		int counter = 0;
		while(counter<20)
		{
			population = generatePopulation(10,8);
			crossover = random.nextInt(8);
			mutation = random.nextInt(8);
			mutationVal = random.nextInt(8);
			//get parents
			for(int i = 0; i<8; i++)
				parent1[i] = population[parent][i];
			parent = random.nextInt(10);
			for(int i = 0; i<8; i++)
				parent2[i] = population[parent][i];
			
			//create children
			for(int i = 0; i<crossover; i++)
				child1[i] = parent1[i];
			
			for(int i = crossover; i<8; i++)
				child1[i] = parent2[i];
			
			for(int i = crossover; i<8; i++)
				child2[i] = parent1[i];
			
			for(int i =0; i<crossover; i++)
				child2[i] = parent2[i];
			
			//mutate children at index mutation
			child1[mutation] = mutationVal;
			mutationVal = random.nextInt(8);
			child2[mutation] = mutationVal;
			
			//check if child is a solution
			if(solution(child1))
			{
				System.out.println("Solution: " + Arrays.toString(child1));
				break;
			}
			if(solution(child2))
			{
				System.out.println("Solution: " + Arrays.toString(child2));
				break;
			}
			counter++;
		}
		
		System.out.println("No solutions found");
	
	}
}