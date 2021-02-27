class StringMatching
{
	static int[] failFunc;
	
	public static void kmpComputeFail(String p)
	{
		failFunc = new int[p.length()];
		int i=1, j=0;
		failFunc[0] = 0;
		while(i<p.length())
		{
			if(p.charAt(i)==p.charAt(j))
			{
				failFunc[i] = j+1;
				i++;
				j++;
			}
			else
			{
				if(j>0)
					j = failFunc[j-1];
				else
				{
					failFunc[i]=0;
					i++;
				}
			}
		}
	}
	
	public static ArrayList<Integer> kmpSearch(String t, String p)
	{
		ArrayList<Integer> index = new ArrayList<Integer>();
		int i=0, j=0;
		while(i<t.length())
		{
			if(t.charAt(i)==p.charAt(j))
			{
				i++;
				j++;
				if(j==p.length())
				{
					index.add((i-p.length()));
					j = failFunc[j-1];
				}
			}
			else
			{
				if(j>0)
					j = failFunc[j-1];
				else
					i++;
			}
		}
		return index;
	}
	}
}