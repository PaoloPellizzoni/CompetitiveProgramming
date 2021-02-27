public class LIS
{
	// n^2 solution
	// DP[i] is the lis ending with array[i]
	public static int lis_dp(int[] array)  
	{
		int N = array.length;
		int[] DP = new int[N];
		int[] prev = new int[N];
		int maxLength = 1, bestEnd = 0;
		DP[0] = 1;
		prev[0] = -1;

		for (int i = 1; i < N; i++)
		{
		   DP[i] = 1;
		   prev[i] = -1;

		   for (int j = i - 1; j >= 0; j--)
			  if (DP[j] + 1 > DP[i] && array[j] < array[i])
			  {
				 DP[i] = DP[j] + 1;
				 prev[i] = j;
			  }

		   if (DP[i] > maxLength)
		   {
			  bestEnd = i;
			  maxLength = DP[i];
		   }
		}
		return maxLength;
	}
	
	public static ArrayList<Integer> reconstruct_lis_dp(int[] array, int[] prev, int bestEnd)
	{
		ArrayList<Integer> l = new ArrayList<Integer>();
		int i = bestEnd;
		while(prev[i]>=0)
		{
			l.add(array[prev[i]]);
			i = prev[i];
		}
		
		return l;
	}
	
	
	
	// NlogN solution
	
	public static int nlogn_lis()
	{
		int[] l_id = new int[n+1];
		int[] p = new int[n+1];
		ArrayList<Integer> l = new ArrayList<Integer>();
		
		int lis = 0, lis_end = 0;
		for(int i=0; i<n; i++)
		{
			int pos = Collections.binarySearch(l, a[i]);
			if(pos < 0)
				pos = -(pos +1);
			if(pos >= l.size())
				l.add(a[i]);
			else
				l.set(pos, a[i]);
			
			l_id[pos] = i;
			p[i] = pos > 0 ? l_id[pos-1] : -1;
			if(pos+1 > lis)
			{
				lis = pos + 1;
				lis_end = i;
			}
		}
		
		io.println(lis);
		print_nlogn_lis(lis_end, p);
		io.println();
		
		return lis;
	}
	
	public static void print_nlogn_lis(int end, int[] p)
	{
		int x = end;
		Stack<Integer> s = new Stack<Integer>();
		for(; p[x] >= 0; x = p[x])
			s.push(x); //prints index, s.push(a[x]) to print value
		
		io.print(x);
		while(!s.isEmpty())
		{
			io.print(" "+s.pop());
		}
	}
	
	
	//Simplified version that cannot print the LIS
	public static int nlogn_lis(ArrayList<Integer> array)
	{
		ArrayList<Integer> l = new ArrayList<Integer>();
		int lis = 0;
		for(int i=0; i<array.size(); i++)
		{
			int pos = Collections.binarySearch(l, array.get(i));
			if(pos < 0)
				pos = -(pos +1);
			if(pos >= l.size())
				l.add(array.get(i));
			else
				l.set(pos, array.get(i));
			
			if(pos+1 > lis)
				lis = pos + 1;
		}
		return lis;
	}
}