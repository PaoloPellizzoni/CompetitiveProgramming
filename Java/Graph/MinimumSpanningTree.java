import java.util.*;
import java.io.*;

class MinimumSpanningTree
{
	static ArrayList<IntegerTriple> edgeList = new ArrayList<IntegerTriple>();
	static int n; // # of vertexes
	public static int kruskal()
	{
		/* ###how to add edges###
		for(int i=0; i<n; i++)
			for(int j=i+1; j<n; j++)
				edgeList.add(new IntegerTriple(dist, i, j));
		*/
		
		PriorityQueue<IntegerTriple> q = new PriorityQueue<IntegerTriple>(edgeList);
		int cost = 0;
		int count = 0;
		UnionFind uf = new UnionFind(n);
		while(!q.isEmpty() && (count < (n-1)))
        {
			IntegerTriple front = q.poll();
			if(!uf.isSameSet(front.second(), front.third()))
			{
				cost += front.first();
				count++;
				uf.unionSets(front.second(), front.third());
			}
		}
		return cost;
	}

}



class IntegerTriple implements Comparable<IntegerTriple> {
  Integer _first, _second, _third;

  public IntegerTriple(Integer f, Integer s, Integer t) {
    _first = f;
    _second = s;
    _third = t;
  }

  public int compareTo(IntegerTriple o) {
    if (!this.first().equals((o).first()))
      return this.first() - (o).first();
    else if (!this.second().equals((o).second()))
      return this.second() - (o).second();
    else
      return this.third() - (o).third();
  }

  Integer first() { return _first; }
  Integer second() { return _second; }
  Integer third() { return _third; }

}

class UnionFind {
    public UnionFind(int N)
    {
        p = new ArrayList<Integer>(N);
        rank = new ArrayList<Integer>(N);
        setSize = new ArrayList<Integer>(N);
        numSets = N;
        for(int i=0; i<N; i++)
        {
            p.add(i);
            rank.add(0);
            setSize.add(1);
        }
    }
    
    public int findSet(int i)
    {
        if(p.get(i)==i)
            return i;
        else
        {
            int ret = findSet(p.get(i));
            p.set(i, ret);
            return ret;
        }
    }
    
    public boolean isSameSet(int i, int j)
    {
        return findSet(i)==findSet(j);
    }
    
    public void unionSets(int i, int j)
    {
        int x = findSet(i);
        int y = findSet(j);
        if(x!=y)
        {
            numSets--;
            if(rank.get(x) > rank.get(y))
            {
                p.set(y, x);
                setSize.set(x, setSize.get(x)+setSize.get(y));
            }
            else
            {
                p.set(x, y);
                setSize.set(y, setSize.get(y)+setSize.get(x));
                if(rank.get(x)==rank.get(y))
                    rank.set(y, rank.get(y)+1);
            }
        }
    }
    
    public int numDisjointSets()
    {
        return numSets;
    }
    
    public int sizeOfSet(int i)
    {
        return setSize.get(findSet(i));
    }
    
    private ArrayList<Integer> p, rank, setSize;
    private int numSets;
}