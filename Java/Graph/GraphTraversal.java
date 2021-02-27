public class GraphTraversal
{
	
	static int[] visited;
	public static void dfs(int v)
	{
		visited[v] = 1; //visits the vertex
		for(int i=0; i< (int)adjList[v].size(); i++)
		{
			if(visited[(int)adjList[v].get(i)]==0)
				dfs((int)adjList[v].get(i));
		}
	}
	
	
	static int[] dist;
	static int n; // # of vertexes
	public static void bfs(int s) //no recursion here
	{
		dist = new int[n]; // distances from s
		
		for(int i=0; i<n; i++)
			dist[i] = 1000000000; //inf
		dist[s] = 0; //distance from s to s = 0
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		q.addLast(s);
		
		while(!q.isEmpty())
		{
			int v = (int)q.poll();
			for(int i=0; i < adjList[v].size(); i++)
			{
				int u = (int)adjList[v].get(i);
				if(dist[u] == 1000000000)
				{
					dist[u] = dist[v] + 1;
					q.addLast(u);
				}
			}
		}
	}
	
	public static boolean isBipartite(int s) //no recursion here
	{
		int[] color = new int[n]; // colors
		color[s] = 0; 
		boolean flag = true;
		for(int i=1; i<n; i++)
			color[i] = -1; //not visited
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();;
		q.addLast(s);
		
		while(!q.isEmpty() && flag)
		{
			int v = (int)q.poll();
			for(int i=0; i < adjList[v].size(); i++)
			{
				int u = (int)adjList[v].get(i);
				if(color[u] == -1)
				{
					color[u] = 1- color[v];
					q.addLast(u);
				}
				else if(color[u] == color[v])
				{
					flag = false;
					break;
				}
			}
		}
		return flag;
	}
	
	// returns false if the graph isn't k-partite, else colors the graph and returns true (n < 100)
	static boolean k_partite(int v)
	{
		int[] colOk = new int[k+1];
		for(Integer u : adjList[v])
		{
			colOk[color[u]] = 1;
		}
		
		for(int i=1; i<=k; i++)
		{
			if(colOk[i]==1)
				continue;
			
			color[v] = i;
			boolean flag = false;
			
			for(Integer u : adjList[v])
			{
				if(color[u]==0)
				{
					if(!k_partite(u)){
						flag = true;
						break;
					}
				}
			}
			
			if(flag)
				continue;
			
			return true;
		}
		color[v] = 0;
		return false;
	}
	
	
	
	static int dr[] = {1,1,0,-1,-1,-1, 0, 1}; 
	static int dc[] = {0,1,1, 1, 0,-1,-1,-1}; // S,SE,E,NE,N,NW,W,SW neighbors
	public static int floodfill(int r, int c, char c1, char c2) // returns the size of CC
	{ 
		if (r < 0 || r >= R || c < 0 || c >= C) 
			return 0; // outside grid
		if (grid[r][c] != c1) 
			return 0; // does not have color c1
		int ans = 1; // adds 1 to ans because vertex (r, c) has c1 as its color
		grid[r][c] = c2; // now recolors vertex (r, c) to c2 to avoid cycling!
		for (int d = 0; d < 8; d++)
			ans += floodfill(r + dr[d], c + dc[d], c1, c2);
		return ans;
	}
	
	
	static Stack topList;
	public static void topOrder(int v) //modified dfs
	{
		visited[v]=1;
		for(int i=0; i<adjList[v].size(); i++)
		{
			if(visited[(int)adjList[v].get(i)]==0)
				topOrder((int)adjList[v].get(i));
		}
			
		
		topList.push(v); // push to stack
	}
}