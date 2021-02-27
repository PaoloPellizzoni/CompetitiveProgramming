int V,used[MAXV],disc[MAXV],low[MAXV];
stack<int> st;
vector<int> mam[MAXV];
vector<vector<int>> SCC;

void tarjanDfs(int n){
    static int time = 0;

    used[n] = 1;
    low[n] = disc[n] = ++time;
    st.push(n);

    for(int x:mam[n])
        if(used[x] == 0){
            tarjanDfs(x);
            low[n] = min(low[n],low[x]);
        }
        else if(used[x] == 1)
            low[n] = min(low[n],disc[x]);

    if(disc[n] == low[n]){
        SCC.push_back(vector<int>());
        while(st.top() != n){
            SCC.back().push_back(st.top());
            used[st.top()] = 2;
            st.pop();
        }
        SCC.back().push_back(n);
        used[n] = 2;
        st.pop();
    }
}

vector<vector<int>> tarjan(){
    for(int i=0;i<V;++i)
        if(!used[i])
            tarjanDfs(i);

    return SCC;
}