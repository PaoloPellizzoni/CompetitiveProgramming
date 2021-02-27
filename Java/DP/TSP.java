class TSP
{
	public static int tsp(int pos, int visited)
	{
		if(memo[pos][visited]>-1)
			return memo[pos][visited];
		
		if(visited == (1<<n)-1)
			return adj[0][pos];
		
		int min = 10000000;
		for(int i=0; i<n; i++)
		{
			if((visited&(1<<i))!=0)
				continue;
			
			int tmp = tsp(i, visited|(1<<i)) + adj[pos][i];
			if(tmp<min)
				min = tmp;
		}
		memo[pos][visited] = min;
		return min;
	}
}