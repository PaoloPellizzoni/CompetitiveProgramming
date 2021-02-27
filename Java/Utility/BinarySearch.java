class BinarySearch{
	static int bound(int[] data, int element, boolean lower) //true: lower_bound, false: upper_bound
	{	
		int first = 0;
		int last = data.length;
		int mid;
		while (first < last) {
			mid = first + ((last - first) >> 1); 
			if ((lower && data[mid] < element) || (!lower && data[mid] <= element)) 
				first = mid + 1; 
			else 
				last = mid;
		}
		
		return first;
	}
}