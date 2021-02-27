int bound(int[] data, int element){	
	int first = 0, last = data.length;
	int mid;
	while (first < last) {
		mid = first + ((last - first) >> 1); 
		if (data[mid] < element) // <= for upper
			first = mid + 1; 
		else 
			last = mid;
	}
	return first;
}