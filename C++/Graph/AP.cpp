int V,disc[MAXV],low[MAXV];
bool used[MAXV];
vector<int> mam[MAXV],AP;

void apDfs(int n, int par = -1, bool isRoot = true){
    static int time = 0;

    used[n] = true;
    low[n] = disc[n] = ++time;

    int nChild = 0;
    bool added = false;

    for(int x:mam[n])
        if(!used[x]){
            apDfs(x,n,false);
            ++nChild;
            low[n] = min(low[n],low[x]);
            if(!added && !isRoot && low[x]>disc[n]){
                added = true;
                AP.push_back(n);
            }
        }
        else if(x != par)
            low[n] = min(low[n],disc[x]);

    if(isRoot){
        if(nChild >= 2)
            AP.push_back(n);
    }
}

vector<int> articulationPoints(){
    for(int i=0;i<V;++i)
        if(!used[i])
            apDfs(i);

    return AP;
}