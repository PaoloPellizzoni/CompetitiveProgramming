import java.util.*;
import java.io.*;

class Knapsack
{
	static IO io = new IO(System.in, System.out);
	static int[] weight;
	static int[] value;
	static int n, C;
	static ArrayList<Integer> sol;
	static int[][] memo; // = new int[maxId+1][maxWeight+1];
	
	
	public static void bottom_up_Knap()
	{
		for(int i=1;i<=n;i++)
		{
			int w = weight[i-1] ;
			int v = value[i-1];
			for(int sz=1;sz<=C;sz++)
			{
				memo[i][sz] = memo[i-1][sz];
				if(sz >= w && memo[i-1][sz-w]+v>memo[i][sz])
				{
					memo[i][sz] = memo[i-1][sz-w] + v;
				}
			}
		}
		int sz = C;
		ArrayList<Integer> items = new ArrayList<Integer>();
		for(int i=n;i>0;i--)
		{
			if(memo[i][sz]!=memo[i-1][sz]){
				int selected = i-1;
				items.add(selected);
				sz -= weight[selected];
			}
		}
		io.println(items.size());
		if(items.size()>0){
			for(int i=0; i<items.size()-1; i++)
				io.print(items.get(i)+" ");
			io.println(items.get(items.size()-1));
		}
	}
	
	//invoke with knap(0, maxWeight)
	public static int knap(int id, int remW)
	{
		if(memo[id][remW]!=-1000000000)
			return memo[id][remW];
		
		if(remW == 0)
		{
			memo[id][remW] = 0;
			return 0;
		}
			
		if(id == n)
		{
			memo[id][remW] = 0;
			return 0;
		}
		
		if(weight[id] > remW)
		{
			memo[id][remW] = knap(id+1, remW);
			return memo[id][remW];
		}
		else
		{
			int noTake = knap(id+1, remW);
			int take = knap(id+1, remW - weight[id]) + value[id];
			if(take > noTake)
				memo[id][remW] = take;
			else
				memo[id][remW] = noTake;
			
			return memo[id][remW];
		}
	}
	public static void printPath(int id, int thisW)
	{
		if(id == n)
			return;
		

		if(thisW-weight[id]>=0 && memo[id+1][thisW-weight[id]] == memo[id][thisW]-value[id])
		{
			//io.println(w+ "  "+ (thisW-weight[id]));
			sol.add(id);
			printPath(id+1, thisW-weight[id]);
			return;
		}
		printPath(id+1, thisW);
	}
	
	
	
	
	
	//  subset sum problem
	public static int subsetSum(int id, int remW)
	{
		if(memo[id][remW]!=-1)
			return memo[id][remW];
		
		if(remW == 0)
		{
			memo[id][remW] = 1;
			return 1;
		}
			
		if(id == n)
		{
			memo[id][remW] = 0;
			return 0;
		}
		
		if(a[id] > remW)
		{
			memo[id][remW] = subsetSum(id+1, remW);
			return memo[id][remW];
		}
		else
		{
			int noTake = subsetSum(id+1, remW);
			int take = subsetSum(id+1, remW - a[id]);
			if(take > noTake)
				memo[id][remW] = take;
			else
				memo[id][remW] = noTake;
			
			return memo[id][remW];
		}
	}
}