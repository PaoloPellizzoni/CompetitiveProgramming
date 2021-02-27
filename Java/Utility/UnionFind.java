import java.util.*;

public class UnionFind {
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
