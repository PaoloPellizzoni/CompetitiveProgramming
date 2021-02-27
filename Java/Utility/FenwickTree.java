public class FenwickTree
{
	public FenwickTree(int n)
	{
		ft = new int[n+1];
	}
	
	public int rangeSumQuery(int b)
	{
		int sum = 0;
		for(; b!=0; b-=LSOne(b))
			sum += ft[b];
		return sum;
	}
	
	public int rangeSumQuery(int a, int b)
	{
		return rangeSumQuery(b) - (a == 1 ? 0 : rangeSumQuery(a-1));
	}
	
	public void adjust(int i, int val)
	{
		for(; i<ft.length; i+= LSOne(i))
			ft[i] += val;
	}
	
	
	private int LSOne(int i)
	{
		return (i & (-i));
	}
	
	private int[] ft;
}