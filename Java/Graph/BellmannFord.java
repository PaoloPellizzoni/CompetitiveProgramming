import java.io.*;
import java.util.*;

public class BellmannFord
{
	public static IO io = new IO(System.in, System.out);
	static ArrayList<IntegerPair>[] adjList;
	static ArrayList<Integer> dist;
	static int n,s;
	
	public static void bellmannFord()
	{
		dist = new ArrayList<Integer>(Collections.nCopies(n, 1000000000));
		dist.set(s, 0); //dist from s
		
		for(int i=0; i < n-1; i++){
			for(int u = 0; u < n; u++){
				for(int j = 0; j<adjList[u].size(); j++)
				{
					IntegerPair v = adjList[u].get(j);
					dist.set(v.first(), Math.min(dist.get(v.first()), dist.get(u)+v.second()));
				}
			}
		}
		boolean hasNegativeCycle = false;
		ArrayList<Integer> cycleVertex = new ArrayList<Integer>();
		for(int u=0; u<n; u++){
			for(int j=0; j < adjList[u].size(); j++)
			{
				IntegerPair v = adjList[u].get(j);
				if(dist.get(v.first()) > dist.get(u) + v.second())
				{
					hasNegativeCycle = true;
					cycleVertex.add(u);
					break;
				}
			}
		}
		if(hasNegativeCycle)
		{
			for(Integer v : cycleVertex)
				dfs(v);
		}
	}
	
	
	public static void dfs(int v)
	{
		if(dist.get(v)<100000000)
			dist.set(v, -1000000000);
		for(int i=0; i< (int)adjList[v].size(); i++)
		{
			if(dist.get(adjList[v].get(i).first()) > -100000000 && dist.get(adjList[v].get(i).first())<100000000)
				dfs(adjList[v].get(i).first());
		}
	}
}

class IntegerPair implements Comparable {
  Integer _first, _second;

  public IntegerPair(Integer f, Integer s) {
    _first = f;
    _second = s;
  }

  public int compareTo(Object o) {
    if (!this.first().equals(((IntegerPair)o).first()))
      return this.first() - ((IntegerPair)o).first();
    else
      return this.second() - ((IntegerPair)o).second();
  }

  Integer first() { return _first; }
  Integer second() { return _second; }
}