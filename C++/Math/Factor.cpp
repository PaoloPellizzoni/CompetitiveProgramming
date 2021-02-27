#define ll long long
int MAXN = 10000000;
vector<int> spf(MAXN); 
void smallestPrimeFactor(){ //O(nloglogn) sieve 
	spf[1] = 1; spf[2] = 2;
	for (int i=3; i<MAXN; i+=2) 
		spf[i] = i;
	for (int i=4; i<MAXN; i+=2) 
		spf[i] = 2; 
	for (int i=3; i*i<MAXN; i++) { 
		if (spf[i] == i){ // checking if i is prime 
			for (int j=i*i; j<MAXN; j+=i) // marking SPF for all numbers divisible by i 
				if (spf[j]==j) 
					spf[j] = i; 
		} 
	} 
}
vector<ll> fastFactorize(ll x){ // run spf first
	vector<ll> ret;
	while (x != 1) { 
		ret.push_back(spf[x]); 
		x = x / spf[x]; 
	} 
	return ret;
}

vector<ll> factorize(ll x){ //O(sqrtN)
	vector<ll> ret;
	while(x%2==0){
		ret.push_back(2);
		x = x>>1;
	}
	for(ll i = 3; i*i <= x; i = i+2) { 
		while(x%i == 0) { 
			ret.push_back(i);
			x = x/i; 
		} 
	}
	return ret; 
} 

vector<ll> getDivisors(int n) { // O(sqrtN)
	vector<ll> sol;
	for (int i=1; i*i<=n; i++) { 
		if(n%i==0) { 
			if(n/i == i) // check if divisors are equal 
				sol.push_back(i);
			else{ 
				sol.push_back(i);
				sol.push_back(n/i); // push the second divisor in the vector
			} 
		} 
	} 
	return sol;
} 