vector<ld> linearSolver(vector<vector<ld>> a) {
    int n = a.size();
    int m = a[0].size() - 1;
    vector<int> where(m,-1);
    for (int col=0, row=0; col<m && row<n; ++col) {
        int sel = row;
        for (int i=row; i<n; ++i)
            if (abs(a[i][col]) > abs(a[sel][col]))
                sel = i;
        if (abs(a[sel][col]) < eps)
          continue;
        for (int i=col; i<=m; ++i){
            ld tmp = a[sel][i];
            a[sel][i] = a[row][i];
            a[row][i] = tmp;
        }
        where[col] = row;
        for (int i=0; i<n; ++i)
            if (i != row) {
                ld c = a[i][col] / a[row][col];
                for (int j=col; j<=m; ++j)
                a[i][j] -= a[row][j] * c;
            }
        ++row;
    }
    vector<ld> ans(m);
    for (int i=0; i<m; ++i)
        if (where[i] != -1)
            ans[i] = a[where[i]][m] / a[where[i]][i];
    for (int i=0; i<n; ++i) {
        double sum = 0;
        for (int j=0; j<m; ++j)
            sum += ans[j] * a[i][j];
        if (abs(sum - a[i][m]) > eps)
        return vector<long double>(); // no solution
    }
    for (int i=0; i<m; ++i)
      if (where[i] == -1)
          return vector<long double>{1.0/0.0}; // infinite solutions
    return ans; // one solution
}
