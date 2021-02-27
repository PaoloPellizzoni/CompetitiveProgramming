vector<vector<int>> edgeMat; 
// edgeMat[i][j]==1 means there's an edge from appl i to job j
vector<int> visited, matchR;
static int n, m;

bool bpm(int u) {
	for (int v = 0; v < m; v++) { 
		if ((edgeMat[u][v]==1) && (visited[v]==0)) { 
			visited[v] = 1;  
			if (matchR[v] < 0 || bpm(matchR[v])) { 
				matchR[v] = u; 
				return true; 
			} 
		} 
	} 
	return false; 
}
// Returns maximum number of matching from n app to m jobs, O(VE)
int maxBipartiteMatching() { 
	// The value of matchR[i] is the app assigned to job i
	matchR.assign(m, -1); 
	int result = 0;  
	for (int u = 0; u < n; u++) { 
		visited = vector<int>(m);
		// Find if the applicant 'u' can get a job 
		if (bpm(u)) 
			result++; 
	} 
	return result; 
}  
