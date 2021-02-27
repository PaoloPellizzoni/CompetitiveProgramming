public class MaxRangeSum
{
	public int Max1DSum (int[] array)
	{
		int sum = 0;
		int ans = 0;
		for(int i =0; i<n; i++)
		{
			sum += array[i];
			if(sum>ans)
				ans = sum;
			if(sum<0)
				sum = 0;
		}
		return ans;
	}
	
	public int Max2DSum (int[][] a)
	{
		for(int i=0; i<a.length; i++)
		{
			for(int j=0; j<a[0].length; j++)
			{
				a[i][j] = in.nextInt();
				if(j>0)
					a[i][j] += a[i][j-1];
			}
		}
		
		int max = 0;
		for(int l=0; l<n; l++)
		{
			for(int r=l; r<n; r++)
			{
				int sum = 0;
				for(int row=0; row<n; row++)
				{
					if(l>0)
						sum += a[row][r] - a[row][l-1];
					else
						sum += a[row][r];
					
					// Kadane’s algorithm on rows
					if (sum < 0)
						sum = 0;
					
					if(sum > max)
						max = sum;
				}
			}
		}
		System.out.println(max);
	}
}