class SlidingWindow
{
	static int[] slidingWindowMin(int arr[], int n, int k) 
    { 
		int[] sol = new int[n-k+1];
		int c=0;
        Deque<Integer> Qi = new LinkedList<Integer>(); 
  
        /* Process first k (or first window) elements of array */
        int i; 
        for (i = 0; i < k; ++i) { 
            // For every element, the previous smaller elements are useless so 
            // remove them from Qi 
            while (!Qi.isEmpty() && arr[i] <= arr[Qi.peekLast()]) 
                Qi.removeLast(); // Remove from rear 

            Qi.addLast(i); 
        } 
  
        // Process rest of the elements, i.e., from arr[k] to arr[n-1] 
        for (; i < n; ++i) { 
            // The element at the front of the queue is the largest element of previous window, so add it 
			sol[c++] = arr[Qi.peek()]; 
  
            // Remove the elements which are out of this window 
            while ((!Qi.isEmpty()) && Qi.peek() <= i - k) 
                Qi.removeFirst(); 
  
            // Remove all elements smaller than the currently being added element
            while ((!Qi.isEmpty()) && arr[i] <= arr[Qi.peekLast()]) 
                Qi.removeLast(); 
            // Add current element at the rear of Qi 
            Qi.addLast(i); 
        } 
		//add max element of last window
		sol[c] = arr[Qi.peek()];
		return sol;
    } 
}