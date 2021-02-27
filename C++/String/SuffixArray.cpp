struct SuffixArray{
	string s;                        
	int n, m;           
	vector<int> ra, tra, phi, plcp, lcp; 
	vector<int> sarr, tsa; 
	// LCP[i] stores the LCP between previous suffix "s + SuffArr[i-1]" and current suffix "s + SuffArr[i]"

	void csort(int k){
		int i, sum, maxi = max(300, n);   // up to 255 ASCII chars or length of n
		vector<int> c(maxi,0);
		for (i = 0; i < n; i++)
			c[i + k < n ? ra[i + k] : 0]++;
		for (i = sum = 0; i < maxi; i++){
			int t = c[i]; c[i] = sum; sum += t;
		}
		for (i = 0; i < n; i++)               
			tsa[c[sarr[i] + k < n ? ra[sarr[i] + k] : 0]++] = sarr[i];
		for (i = 0; i < n; i++)                    
			sarr[i] = tsa[i];
	}
	void constructSA() {              
		int i, k, r;
		for (i = 0; i < n; i++) ra[i] = s[i]; //initial rank           
		for (i = 0; i < n; i++) sarr[i] = i;        
		for (k = 1; k < n; k <<= 1) {            
			csort(k);       
			csort(0);              
			tra[sarr[0]] = r = 0;                 
			for (i = 1; i < n; i++)                      
				tra[sarr[i]] = (ra[sarr[i]] == ra[sarr[i-1]] && ra[sarr[i]+k] == ra[sarr[i-1]+k]) ? r : ++r;
			for (i = 0; i < n; i++) // update the rank array RA
				ra[i] = tra[i];
		}		
	}
	void computeLCP() {
		int i, l;
		phi[sarr[0]] = -1;
		for (i = 1; i < n; i++)
			phi[sarr[i]] = sarr[i-1];         // remember which suffix is behind this suffix
		for (i = l = 0; i < n; i++) {                  // compute Permuted LCP in O(n)
			if (phi[i] == -1) { plcp[i] = 0; continue; }                 // special case
			while (i + l < (int)s.size() && phi[i] + l < (int)s.size() && s[i + l] == s[phi[i] + l]) l++;
			plcp[i] = l;
			l = max(l-1, 0);
		}
		for (i = 1; i < n; i++) 
			lcp[i] = plcp[sarr[i]];   // put the permuted LCP back to the correct position
	}
	int strncmp(string a, int i, string b, int j, int n){
		for (int k=0; i+k < (int)a.size() && j+k < (int)b.size(); k++)
			if (a[i+k] != b[j+k]) return a[i+k] - b[j+k];
		return 0;
	}
	vector<int> stringMatching(string str){ // string matching in O(m log n)
		//char P[] = str.toCharArray();
		m = str.size();
		int lo = 0, hi = n-1, mid = lo;                 
		while (lo < hi) {                                          // find lower bound
			mid = (lo + hi) / 2;                                   
			int res = strncmp(s, sarr[mid], str, 0, m);
			if (res >= 0) hi = mid;            
			else          lo = mid + 1; 
		}
		if (strncmp(s,sarr[lo], str,0, m) != 0) return {-1, -1};         // if not found
		vector<int> ans = { lo, 0} ;
		lo = 0; hi = n - 1; mid = lo;
		while (lo < hi) {                 // if lower bound is found, find upper bound
			mid = (lo + hi) / 2;
			int res = strncmp(s, sarr[mid], str,0, m);
			if (res > 0) hi = mid;
			else         lo = mid + 1;
		}
		if (strncmp(s, sarr[hi], str,0, m) != 0) hi--;// special case
		ans[1] = hi;
		return ans;// return lower/upper bound of the range where the pattern is found
	} 
    string LongestRpeatedSubstring(){ //longest repeated substring O(n)
		int i, idx = 0, mam = 0;
		for (i = 1; i < n; i++)
		if (lcp[i] > mam) {
			mam = lcp[i];
			idx = i;
		}
		return s.substr(sarr[idx], mam); // maxLCP is the length
	}
	int owner(int idx) { return (idx < n-m-1) ? 1 : 2; }
	string LongestCommonSubstring(string str0, string str1) {
		int i, mam = 0, idx = 0;
		m = str1.length();
		s = str0 + str1 + "#";   // append P and '#'
		n = s.size(); // update n
		ra = vector<int>(n,0);
		tra = vector<int>(n,0);
		sarr = vector<int>(n,0);
		tsa = vector<int>(n,0);
		phi = vector<int>(n,0);
		plcp = vector<int>(n,0);
		lcp = vector<int>(n,0);
		constructSA();
		computeLCP();
		for (i = 1, mam = -1; i < n; i++){
			if (lcp[i] > mam && owner(sarr[i]) != owner(sarr[i-1])) {  // different owner
				mam = lcp[i];
				idx = i;
			}
		}
		return s.substr(sarr[idx], mam);
	}
	void suffixarray(string str){
		s = str+"$";
		n = s.size();
		ra = vector<int>(n,0);
		tra = vector<int>(n,0);
		sarr = vector<int>(n,0);
		tsa = vector<int>(n,0);
		phi = vector<int>(n,0);
		plcp = vector<int>(n,0);
		lcp = vector<int>(n,0);
		constructSA();
		computeLCP(); 
	}
};