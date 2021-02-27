ll gcd(ll a, ll b) {
	while(b > 0){
		ll tmp = a%b;
		a = b;
		b = tmp;
	}
	return a;
}

ll lcm(ll a, ll b){
	return (a / gcd(a, b)) * b;
}

ll mod(ll a, ll M){
	return (a%M + M)%M;
}
	
// Find the greatest common factor between two numbers
	ll gcf(ll a, ll b) {
    	return b == 0 ? a : gcf(b, a % b);
	}

ll* extendedEuclid(ll a, ll b){ //finds x,y so that ax+by=gcd(a,b)
	ll xx = 0, y = 0;
	ll yy = 1, x = 1;
	while(b > 0){
		ll q = a / b;
		ll tmp = b;  b = a%b;  a = tmp;
		tmp = xx; xx = x - q*xx; x = tmp;
		tmp = yy; yy = y - q*yy; y = tmp;
	}
	return new ll[3]{a, x, y};
}

vector<ll> modularLinearSolver(ll a, ll b, ll M){ //solution to ax = b (mod M)
    	vector<ll> sol;
	ll *tmp = extendedEuclid(a, M);
	ll g = tmp[0], x = tmp[1], y = tmp[2];
	if(b%g == 0){
		x = mod(x*(b/g), M);
		for(ll i=0; i<g; i++)
			sol.push_back(mod(x + i*(M/g), M));
	}
	return sol;
}

ll modInverse(ll a, ll M){ // returns b so that ab = 1 (mod n)
	ll *tmp = extendedEuclid(a, M);
	ll g = tmp[0], x = tmp[1], y = tmp[2];
	if(g > 1) return -1;
	return mod(x, M);
}

ll* linearDiophantine(ll a, ll b, ll c){ // computes x,y so that ax+by=c
	if(a==0 && b==0){
		if(c==0) return NULL;
		return new ll[2]{0,0};
	}
	if(a==0){
		if(c%b == 0) return NULL;
		return new ll[2]{0, c/b};
	}
	if(b==0){
		if(c%a == 0) return NULL;
		return new ll[2]{c/a, 0};
	}
	ll g = gcd(a, b);
	if(c % g != 0) return NULL;
	ll x = c / g * modInverse(a/g, b/g);
	ll y = (c - a*x) / b;
	return new ll[2]{x, y};
}

ll modMul(ll a, ll b,  ll M) {
	ll res = 0;
	a = a % M;
	while (b > 0) { 
	    if (b % 2 == 1)  {
		res = (res + a) % M;
	    }
	    a = (a * 2) % M;
	    b /= 2;
	}
	return res % M;
}