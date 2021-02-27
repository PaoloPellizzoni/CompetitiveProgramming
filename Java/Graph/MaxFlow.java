import java.util.*;

public class MaxFlow {
	
	//----DINIC----
	static ArrayList<Edge>[] adjList; //edges are directed
	//addEdge(u, v, cap);
	public static void addEdge(int s, int t, int cap) {
		adjList[s].add(new Edge(t, adjList[t].size(), cap));
		adjList[t].add(new Edge(s, adjList[s].size() - 1, 0));
	}
	
	static boolean dinicBfs(int src, int dest, int[] level) {
		Arrays.fill(level, -1);
		level[src] = 0;
		int[] Q = new int[adjList.length];
		int sizeQ = 0;
		Q[sizeQ++] = src;
		for (int i = 0; i < sizeQ; i++) {
			int u = Q[i];
			for (Edge e : adjList[u]) {
				if (level[e.to] < 0 && e.f < e.cap) {
					level[e.to] = level[u] + 1;
					Q[sizeQ++] = e.to;
				}
			}
		}
		return level[dest] >= 0;
	}

	//ptr[] to keep track of next edge to explore, ptr[i] = #edges explored from i
	static int dinicDfs(int[] ptr, int[] level, int dest, int u, int f) {
		if (u == dest)
			return f;
		for (; ptr[u] < adjList[u].size(); ++ptr[u]) {
			Edge e = adjList[u].get(ptr[u]);
			if (level[e.to] == level[u] + 1 && e.f < e.cap) {
				int currFlow = Math.min(f, e.cap - e.f);
				int df = dinicDfs(ptr, level, dest, e.to, currFlow);
				if (df > 0) {
					e.f += df;
					adjList[e.to].get(e.rev).f -= df;
					return df;
				}
			}
		}
		return 0;
	}

	public static int maxFlow(int src, int dest) {
		int flow = 0;
		int[] level = new int[adjList.length];
		while (dinicBfs(src, dest, level)) {
			int[] ptr = new int[adjList.length];
			while (true) {
				int df = dinicDfs(ptr, level, dest, src, Integer.MAX_VALUE);
				if (df == 0)
					break;
				flow += df;
			}
		}
		return flow;
	}
	//------ END DINIC -----
	
	
	
	//------- MIN CUT -------
	//dont't run maxFlow first as is't already in minCut()
	public static int minCut(int s, int t)
	{
		maxFlow(s, t);
		reachable = new int[adjList.length];
		dfsCut(s);
		int sol = 0;
		for(int v=0; v<adjList.length; v++)
		{
			if(reachable[v]==1)
			{
				for(Edge e : adjList[v])
				{
					if(e.cap>0 && reachable[e.to]==0)
						sol += e.cap;
				}
			}
		}
		return sol;
	}
	
	static int[] reachable;
	private static void dfsCut(int v)
	{
		reachable[v] = 1;
		for(Edge e : adjList[v])
		{
			if(e.f<e.cap && reachable[e.to]==0)
				dfsCut(e.to);
				
		}
	}
	
	
}

class Edge 
{
	public Edge(int t, int rev, int cap) 
	{
		this.to = t; // vertex v in directed edge  u -> v
		this.rev = rev; //index of reverse edge O(1)
		this.cap = cap; // capacity
    }
	
	int to, rev, cap, f;
}