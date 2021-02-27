int par[maxn][logn], d[maxn];
vector<int> adj[maxn];

void dfs0(int v, int f, int dd){
    d[v] = dd;;
    int curr = par[v][0] = f;
    for(int i = 1; (1 << i) <= dd; i++){
        par[v][i] = par[curr][i - 1];
        curr = par[curr][i - 1];
    }
    for(auto u : adj[v])
        if(u != f)
            dfs0(u, v, dd + 1);
}

int lca(int a, int b){
    if(d[a] > d[b]) swap(a, b);
    int diff = d[b] - d[a];
    for(int i = 0; i < logn; i++)
        if(diff & (1 << i))
            b = par[b][i];
    if(a == b) return a;
    for(int i = logn - 1; i >= 0; i--)
        if(par[a][i] != par[b][i])
            a = par[a][i], b = par[b][i];
    return par[a][0];
}
