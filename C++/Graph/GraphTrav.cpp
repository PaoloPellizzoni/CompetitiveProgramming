vector<int> vis;
bool hasCycle = false;
void cycle_dfs(int v){
	vis[v] = 1;
	for(int i : adj[v]){
		if(vis[i] == 0)
			cycle_dfs(i);
		else if(vis[i] == 1)
			hasCycle = true;
	}
	vis[v] = 2;
}


bool k_partite(int v){
	vector<int> colOk(k+1);
	for(int u : adj[v]){
		colOk[color[u]] = 1;
	}
	for(int i=1; i<=k; i++){
		if(colOk[i] == 1)
			continue;
		color[v] = i;
		bool flg = false;
		for(int u : adj[v]){
			if(color[u] == 0){
				if(!k_partite(u)){
					flg = true;
					break;
				}
			}
		}
		if(flg)
			continue;
		return true;
	}
	color[v] = 0;
	return false;
}