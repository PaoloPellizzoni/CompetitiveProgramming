
#define ll long long 
#define pb push_back

struct FlowEdge {
    int v, u;
    ll cap, flo = 0;
    FlowEdge(int v, int u, ll cap) : v(v), u(u), cap(cap) {}
};

struct Dinic {
    const ll flow_inf = 1e18;
    vector<FlowEdge> edj;
    vector<vector<int>> adj;
    int n, m = 0;
    int s, t;
    vector<int> lvl, ptr;
    queue<int> q;

    Dinic(int n, int s, int t) : n(n), s(s), t(t) {
        adj.resize(n);
        lvl.resize(n);
        ptr.resize(n);
    }

    void add_edge(int v, int u, ll cap) {
        edj.pb({v, u, cap});
        edj.pb({u, v, 0});
        adj[v].pb(m);
        adj[u].pb(m + 1);
        m += 2;
    }

    bool bfs() {
        while (!q.empty()) {
            int v = q.front();
            q.pop();
            for (int id : adj[v]) {
                if (edj[id].cap - edj[id].flo < 1)
                    continue;
                if (lvl[edj[id].u] != -1)
                    continue;
                lvl[edj[id].u] = lvl[v] + 1;
                q.push(edj[id].u);
            }
        }
        return lvl[t] != -1;
    }

    ll dfs(int v, ll pu) {
        if (pu == 0)
            return 0;
        if (v == t)
            return pu;
        for (int& cid = ptr[v]; cid < (int)adj[v].size(); cid++) {
            int id = adj[v][cid];
            int u = edj[id].u;
            if (lvl[v] + 1 != lvl[u] || edj[id].cap - edj[id].flo < 1)
                continue;
            ll tr = dfs(u, min(pu, edj[id].cap - edj[id].flo));
            if (tr == 0)
                continue;
            edj[id].flo += tr;
            edj[id ^ 1].flo -= tr;
            return tr;
        }
        return 0;
    }

    ll flow() {
        ll f = 0;
        while (true) {
            fill(lvl.begin(), lvl.end(), -1);
            lvl[s] = 0;
            q.push(s);
            if (!bfs())
                break;
            fill(ptr.begin(), ptr.end(), 0);
            while (ll pu = dfs(s, flow_inf)) {
                f += pu;
            }
        }
        return f;
    }
};
