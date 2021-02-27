import java.util.*;

class PrimeReduction
{
	//invoke smallestPrimeFactor() once and then 
	//(logN) getFactorization(N) for every query
	static int MAXN = 10000000;
	static int spf[] = new int[MAXN]; 
       
    // Calculating SPF (Smallest Prime Factor) for every 
    // number till MAXN. 
    // Time Complexity : O(nloglogn) 
    static void smallestPrimeFactor() 
    { 
        spf[1] = 1; 
		spf[2] = 2;
        for (int i=3; i<MAXN; i+=2) 
       
            // marking smallest prime factor for every 
            // number to be itself. 
            spf[i] = i; 
       
        // separately marking spf for every even 
        // number as 2 
        for (int i=4; i<MAXN; i+=2) 
            spf[i] = 2; 
       
        for (int i=3; i*i<MAXN; i++) 
        { 
            // checking if i is prime 
            if (spf[i] == i) 
            { 
                // marking SPF for all numbers divisible by i 
                for (int j=i*i; j<MAXN; j+=i) 
       
                    // marking spf[j] if it is not  
                    // previously marked 
                    if (spf[j]==j) 
                        spf[j] = i; 
            } 
        } 
    } 
       
    // A O(log n) function returning primefactorization 
    // by dividing by smallest prime factor at every step 
    static ArrayList<Integer> getFactorization(int x) 
    { 
        ArrayList<Integer> ret = new ArrayList<>(); 
		while(x>MAXN && x%2==0)
		{
			ret.add(2);
			x = x>>1;
		}
		for (int i = 3; x>MAXN && i <= Math.sqrt(x); i = i+2) 
		{ 
        // While i divides n, print i and divide n 
			while (x>MAXN && x%i == 0) 
			{ 
				ret.add(i);
				x = x/i; 
			} 
		}
		if(x>MAXN)
		{
			ret.add(x);
			return ret;
		}
		
        while (x != 1) 
        { 
            ret.add(spf[x]); 
            x = x / spf[x]; 
        } 
        return ret; 
    } 
	
	
	
	//classic erathostenes sieve
	static BitSet isPrime;
	static void primeSieve()
	{
		if(n<2)
			return;
		
		isPrime.set(2, true);
		count = 1;
		for(int i=3; i<=n; i+=2)
			isPrime.set(i, true);
		
		for(int i=3; i<=n; i+=2)
		{
			if(isPrime.get(i))
			{
				count++;
				if(i> Math.sqrt(n+1))
					continue;
				
				for(int j = i*i; j>0 && j<=n; j+=i)
					isPrime.set(j, false);
			}
		}
	}
}