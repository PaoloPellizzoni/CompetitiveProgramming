static vector<int> slidingWindowMin(vector<int> arr, int n, int k){ 
	vector<int> sol(n-k+1);
	int c=0;
	deque<int> q(0); 
	int i; 
	for (i = 0; i < k; ++i) { 
		// previous smaller elements are useless so remove from q 
		while (!q.empty() && arr[i] <= arr[q.back()]) 
			q.pop_back(); // Remove from rear 
		q.push_back(i); 
	} 
	// Process rest of the elements, i.e., from arr[k] to arr[n-1] 
	for (; i < n; ++i) { 
		// element at the front is the largest of prev window, so add it 
		sol[c++] = arr[q.front()]; 

		// Remove the elements which are out of this window 
		while ((!q.empty()) && q.front() <= i - k) 
			q.pop_front(); 
		// Remove elements smaller than currently being added element
		while ((!q.empty()) && arr[i] <= arr[q.back()]) 
			q.pop_back(); 
		// Add current element at the rear of q 
		q.push_back(i); 
	} 
	//add max element of last window
	sol[c] = arr[q.front()];
	return sol;
} 
