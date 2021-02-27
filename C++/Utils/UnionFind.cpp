struct UnionFind {
	vector<int> p, size;
	int numSets;
	UnionFind(int n) {
		p = vector<int>(n); 
		size = vector<int>(n);
		numSets = n;
		for (int i = 0; i < N; ++i){
			p[i] = i; size[i] = 1;
		}
	}
	int findSet(int i) {
		return (p[i] == i) ? i : (p[i] = findSet(p[i]));
	}
	bool isSameSet(int i, int j) { 
		return findSet(i) == findSet(j); 
	}
	void uniteSets(int i, int j){
		i = findSet(i);
		j = findSet(j);
		if(i==j)
			return;
		numSets--;
		if(size[i] > size[j]){
			int tmp = j;
			j = i; i = tmp;
		}
		size[j] += size[i];
		p[i] = j
    }
};