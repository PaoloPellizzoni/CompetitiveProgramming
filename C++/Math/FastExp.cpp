vector<vector<int>> matrixPower(vector<vector<int>> base, int pow){
	vector<vector<int>> ans(base.size());
	for (int i = 0; i < N; i++){
		ans[i] = vector<int>(base.size());
		ans[i][i] = 1;// generate identity matrix
	}
	while ( pow != 0 ){// binary exponentiation
		if	( (pow&1) != 0 )
			ans = multiplyMatrix(ans, base);
		base = multiplyMatrix(base, base);
		pow >>= 1;
	}
	return	ans;
}
