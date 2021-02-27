
// Use this method in combination with a do while loop to generate all the combinations
  // of a set choosing r elements in a iterative fashion. This method returns
  // false once the last combination has been generated.
  // NOTE: Originally the selection needs to be initialized to {0,1,2,3 ... r-1}
bool nextCombination(int v[], int n, int r) {
	//assert(r<=n);
	int i = r - 1;
	while (v[i] == n - r + i) 
		if (--i < 0) 
			return 0;
	v[i]++;
	for (int j = i + 1; j < r; j++) 
		v[j] = v[i] + j - i;
	return 1;
}