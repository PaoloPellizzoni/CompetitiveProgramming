import java.util.*;
import java.io.*;

class Dijkstra
{
	static IO io = new IO(System.in, System.out);
	static ArrayList<IntegerPair>[] adjList;
	static ArrayList<IntegerPair>[] path;
	static ArrayList<Integer> dist;
	static int[] memo;
	static int n, m, s, t;
	public static void main(String[] args)
	{
		n = io.getInt();
		m = io.getInt();
		
		adjList = new ArrayList[n];
		path = new ArrayList[n];
		memo = new int[n];
		for(int i=0; i<n; i++)
		{
			adjList[i] = new ArrayList<IntegerPair>();
			path[i] = new ArrayList<IntegerPair>();
			memo[i] = -1;
		}
		
		for(int i=0; i<m; i++)
		{
			int u = io.getInt();
			int v = io.getInt();
			int we = io.getInt();
			adjList[u].add(new IntegerPair(v, we));
		}
		
		s = io.getInt();
		t = io.getInt();
		
		dijkstra();
		io.println(dist.get(t));
		io.println(countPath(t));
		
		io.close();
	}
	
	public static void dijkstra()
	{
		// -----  DIJKSTRA  -----
		dist = new ArrayList<Integer>(Collections.nCopies(n, 1000000000));
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
					path[v] = new ArrayList<IntegerPair>();
				} 
				if (dist.get(u) + weight_u_v == dist.get(v))
				{
					path[v].add(new IntegerPair(u, weight_u_v));
				}
			}
		}
		
		//  ----- END -----
	}
	
	static int countPath(int u)
	{
		if(memo[u] > -1)
			return memo[u];
		
		if(u == s)
		{
			return 1;
		}
		int tmp = 0;
		for(int i=0;i<path[u].size(); i++)
		{
			tmp += countPath(path[u].get(i).first());
		}
		memo[u] = tmp;
		return tmp;
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

class IO extends PrintWriter {
    public IO(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public IO(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public IO(String fileName) {
        super(new BufferedOutputStream(System.out));
        try{
            r = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            this.println("File Not Found");
        }

    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}