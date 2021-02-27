import java.util.*;
import java.io.*;

class Dijkstra
{
	static IO io = new IO(System.in, System.out);
	static ArrayList<IntegerPair>[] adjList;
	static ArrayList<Integer> dist;
	static int n, m, s, t;
	public static void main(String[] args)
	{
		n = io.getInt();
		m = io.getInt();

		adjList = new ArrayList[n];
		for(int i=0; i<n; i++)
		{
			adjList[i] = new ArrayList<IntegerPair>();
		}
		
		for(int i=0; i<m; i++)
		{
			int u = io.getInt(); //from
			int v = io.getInt(); // to
			int we = io.getInt(); //weight
			adjList[u].add(new IntegerPair(v, we));
		}
		
		s = io.getInt(); //source
		t = io.getInt(); //target
		
		dijkstra();
		io.println(dist.get(t));
		
		io.close();
	}
	
	public static void dijkstra()
	{
		// -----  DIJKSTRA  -----
		dist = new ArrayList<Integer>(Collections.nCopies(n, 1000000000));//set all dist to +inf
		dist.set(s, 0); //dist from s
		PriorityQueue<IntegerPair> pq = new PriorityQueue<IntegerPair>();
		pq.offer(new IntegerPair(0, s));
		
		while (!pq.isEmpty()) { // main loop
			IntegerPair top = pq.poll(); //pick shortest unvisited vertex
			int d = top.first(); 
			int u = top.second();
			if (d > dist.get(u)) 
				continue; //We want to process vertex u only once 
			for(int i=0; i< adjList[u].size(); i++)
			{ 
				IntegerPair p = adjList[u].get(i);
				int v = p.first();
				int weight_u_v = p.second();
				if (dist.get(u) + weight_u_v < dist.get(v)) 
				{ 
					dist.set(v, dist.get(u) + weight_u_v); // relax              
					pq.offer(new IntegerPair(dist.get(v), v)); 
				} 
			}
		}
		
		//  ----- END -----
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
