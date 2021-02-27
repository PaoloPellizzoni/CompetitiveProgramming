int max2DSum (vector<vector<int>> a){
	for(int i=0; i<a.size(); i++){
		for(int j=0; j<a[0].size(); j++){
			if(j>0)	a[i][j] += a[i][j-1];
		}
	}
	int mx = 0;
	for(int l=0; l<a[0].size(); l++){
		for(int r=l; r<a[0].size(); r++){
			int sum = 0;
			for(int row=0; row<a.size(); row++){
				if(l>0)	sum += a[row][r] - a[row][l-1];
				else	sum += a[row][r];
				// Kadane’s algorithm on rows
				if (sum < 0)	sum = 0;
				mx = max(mx, sum);
			}
		}
	}
	return mx;
