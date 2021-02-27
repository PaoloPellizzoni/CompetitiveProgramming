const int INF = 1000000000;
vector<vector<int>> adjMat;
void floydWarshall(){
	//  classic floyd-warshall algorithm...
	for (int i = 0; i < n; i++){
		adjMat[i][i] = 0;
	}
	for (int k = 0; k < n; k++) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(adjMat[i][k]==INF || adjMat[k][j]==INF)
					continue;
				adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
			}
		}
	}
	// ...ends here
	for (int i = 0; i < n; i++){
		for (int j = 0; j < n; j++){
			for (int k = 0; adjMat[i][j] != -INF && k < n; k++) {
				if (adjMat[i][k] != INF && adjMat[k][j] != INF && adjMat[k][k] < 0)
				{
					adjMat[i][j] = -INF;
				}
			}
		}
	}
}
