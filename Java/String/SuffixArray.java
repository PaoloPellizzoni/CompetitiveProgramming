class SuffixArray
{
	char T[];                        
	int n, m;           
	int[] RA, tempRA; 
	Integer[] SuffArr, tempSA; 
	int[] Phi;  
	int[] PLCP;
	int[] LCP;    // LCP[i] stores the LCP between previous suffix "T + SuffArr[i-1]" and current suffix "T + SuffArr[i]"

	private void countingSort(int k)
	{
		int i, sum, maxi = Math.max(300, n);   // up to 255 ASCII chars or length of n
		int[] c = new int[maxi];
		for (i = 0; i < n; i++)
			c[i + k < n ? RA[i + k] : 0]++;
		for (i = sum = 0; i < maxi; i++) {
			int t = c[i]; c[i] = sum; sum += t;
		}
		for (i = 0; i < n; i++)               
			tempSA[c[SuffArr[i] + k < n ? RA[SuffArr[i] + k] : 0]++] = SuffArr[i];
		for (i = 0; i < n; i++)                    
			SuffArr[i] = tempSA[i];
	}

	private void constructSA() 
	{              
		int i, k, r;
		for (i = 0; i < n; i++) RA[i] = T[i];          //initial rank           
		for (i = 0; i < n; i++) SuffArr[i] = i;        
		for (k = 1; k < n; k <<= 1) {            
			countingSort(k);       
			countingSort(0);              
			tempRA[SuffArr[0]] = r = 0;                 
			for (i = 1; i < n; i++)                      
				tempRA[SuffArr[i]] = (RA[SuffArr[i]] == RA[SuffArr[i-1]] && RA[SuffArr[i]+k] == RA[SuffArr[i-1]+k]) ? r : ++r;
			for (i = 0; i < n; i++)                          // update the rank array RA
				RA[i] = tempRA[i];
		}		
	}

	private void computeLCP() 
	{
		int i, L;
		Phi[SuffArr[0]] = -1;
		for (i = 1; i < n; i++)
			Phi[SuffArr[i]] = SuffArr[i-1];         // remember which suffix is behind this suffix
		for (i = L = 0; i < n; i++) {                  // compute Permuted LCP in O(n)
			if (Phi[i] == -1) { PLCP[i] = 0; continue; }                 // special case
			while (i + L < T.length && Phi[i] + L < T.length && T[i + L] == T[Phi[i] + L]) L++;
			PLCP[i] = L;
			L = Math.max(L-1, 0);
		}
		for (i = 1; i < n; i++) 
			LCP[i] = PLCP[SuffArr[i]];   // put the permuted LCP back to the correct position
	}

	private int strncmp(char[] a, int i, char[] b, int j, int n){
		for (int k=0; i+k < a.length && j+k < b.length; k++){
			if (a[i+k] != b[j+k]) return a[i+k] - b[j+k];
		}
		return 0;
	}
	

	public int[] stringMatching(String str) // string matching in O(m log n)
	{
		char P[] = str.toCharArray();
		m = P.length;
		int lo = 0, hi = n-1, mid = lo;                 
		while (lo < hi) {                                          // find lower bound
			mid = (lo + hi) / 2;                                   
			int res = strncmp(T, SuffArr[mid], P, 0, m);
			if (res >= 0) hi = mid;            
			else          lo = mid + 1; 
		}
		if (strncmp(T,SuffArr[lo], P,0, m) != 0) return new int[]{-1, -1};         // if not found
		int[] ans = new int[]{ lo, 0} ;

		lo = 0; hi = n - 1; mid = lo;
		while (lo < hi) {                 // if lower bound is found, find upper bound
			mid = (lo + hi) / 2;
			int res = strncmp(T, SuffArr[mid], P,0, m);
			if (res > 0) hi = mid;
			else         lo = mid + 1;
		}
		if (strncmp(T, SuffArr[hi], P,0, m) != 0) hi--;                      // special case
		ans[1] = hi;
		return ans;// return lower/upper bound of the range where the pattern is found
	} 

	public String LRS() //longest repeated substring O(n)
	{
		int i, idx = 0, maxLCP = 0;

		for (i = 1; i < n; i++)
		if (LCP[i] > maxLCP) {
			maxLCP = LCP[i];
			idx = i;
		}
		return (new String(T)).substring(SuffArr[idx], SuffArr[idx]+maxLCP); // maxLCP is the length
	}

	private int owner(int idx) { return (idx < n-m-1) ? 1 : 2; }

	public String LCS(String str) 
	{
		int i, j, maxLCP = 0, idx = 0;
		m = str.length();
		T = (new String(T) + str + "#").toCharArray();   // append P and '#'
		n = T.length; // update n
		RA = new int[n];
		tempRA = new int[n];
		SuffArr = new Integer[n];
		tempSA = new Integer[n];
		Phi = new int[n];
		PLCP = new int[n];
		LCP = new int[n];
		constructSA();
		computeLCP();

		for (i = 1, maxLCP = -1; i < n; i++){
			if (LCP[i] > maxLCP && owner(SuffArr[i]) != owner(SuffArr[i-1])) {  // different owner
				maxLCP = LCP[i];
				idx = i;
			}
		}
		return (new String(T)).substring(SuffArr[idx], SuffArr[idx] + maxLCP);
	}
	
	public SuffixArray(String str)
	{
		T = str.toCharArray();
		n = T.length;
		RA = new int[n];
		tempRA = new int[n];
		SuffArr = new Integer[n];
		tempSA = new Integer[n];
		Phi = new int[n];
		PLCP = new int[n];
		LCP = new int[n];
		constructSA();
		computeLCP(); 
	}
}