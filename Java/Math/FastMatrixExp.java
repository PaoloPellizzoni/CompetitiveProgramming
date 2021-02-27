class FastMatrixExp
{
	static long[][] matrixPower(long [][] base, long pow)
	{
		long [][] ans = new long [N][N];
		// generate identity matrix
		for (int i = 0; i < N; i++)	ans[i][i] = 1;
 
		// binary exponentiation
		while ( pow != 0 )
		{
			if	( (pow&1) != 0 )
				ans = multiplyMatrix(ans, base);
 
			base = multiplyMatrix(base, base);
 
			pow >>= 1;
		}
 
		return	ans;
	}
	static long[][] multiplyMatrix(long [][] m, long [][] m2)	
	{
		long [][] ans = new long [N][N];

		for (int i = 0; i < N; i++)	for (int j = 0; j < N; j++)	{
			ans[i][j] = 0;
			for (int k = 0; k < N; k++)	{
				ans[i][j] += m[i][k] * m2[k][j];
				ans[i][j] %= MOD;
			}
		}

		return	ans;
	}
}