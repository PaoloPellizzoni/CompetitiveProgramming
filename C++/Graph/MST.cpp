typedef tuple<int, int, int> iii;
vector<iii> edgeList;
int n; // # of vertexes
int kruskal(){
	// how to add edges: edgeList.push_back({dist, i, j});
	sort(edgeList.begin(), edgeList.end());
	int cost = 0, count = 0, i = 0;
	UnionFind uf = new UnionFind(n);
	while(i < n && (count < (n-1))){
		auto [w, u, v] = edgeList[i]; 
		if(!uf.isSameSet(u, v)){
			cost += w;
			count++;
			uf.unionSets(u, v);
		}
	}
	return cost;
}
}