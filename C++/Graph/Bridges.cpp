int V,disc[MAXV],low[MAXV];
bool used[MAXV];
vector<int> mam[MAXV];
vector<pair<int,int>> bridges;

void bridgesDfs(int n, int par = -1){
    static int time = 0;

    used[n] = true;
    low[n] = disc[n] = ++time;

    for(int x:mam[n])
        if(!used[x]){
            bridgesDfs(x,n);
            low[n] = min(low[n],low[x]);
            if(low[x]>disc[n])
                bridges.push_back({n,x});
        }
        else if(x != par)
            low[n] = min(low[n],disc[x]);
}

vector<pair<int,int>> findBridges(){
    for(int i=0;i<V;++i)
        if(!used[i])
            bridgesDfs(i);

    return bridges;
}