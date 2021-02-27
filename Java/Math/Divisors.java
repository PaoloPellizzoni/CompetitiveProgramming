import java.util.*; 
  
class Divisors 
{ 
    // method to store the divisors 
    static ArrayList<Integer> getDivisors(int n) 
    { 
		ArrayList<Integer> sol = new ArrayList<Integer>(); 
        ArrayList<Integer> v = new ArrayList<Integer>(); 
        for (int i=1; i<=Math.sqrt(n); i++) 
        { 
            if (n%i==0) 
            { 
                if (n/i == i) // check if divisors are equal 
                    sol.add(i);
                else
                { 
                     sol.add(i);
                    // push the second divisor in the vector 
                    v.add(n/i); 
                } 
            } 
        } 
        for (int i=v.size()-1; i>=0; i--) 
			sol.add(v.get(i));
		
		return sol;
    } 
}