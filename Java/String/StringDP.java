public class StringDP
{
	
	public static void main(String[] args)
	{
		A = "ACAATCC";
		B = "AGCATGC";
		System.out.println(stringAlignment());
		System.out.println(newA);
		System.out.println(newB);
	}
	
	static int[][] path;
	static String A,B;
	public static int stringAlignment() //Needleman-Wunsch
	{   // change scores accordingly
		int matchScore = 2;
		int misScore = -1;
		int spaceAScore = -1;
		int spaceBScore = -1;
		
		int[][] V = new int[A.length()+1][B.length()+1];
		path = new int[A.length()+1][B.length()+1];
		path[0][0] = -1;
		for(int i=1; i<=A.length(); i++){
			V[i][0] = i * spaceBScore;
			path[i][0] = 1;
		}
		for(int i=1; i<=B.length(); i++){
			V[0][i] = i * spaceAScore;
			path[0][i] = 2;
		}
		for(int i=1; i<=A.length(); i++){
			for(int j=1; j<=B.length(); j++)
			{
				int[] tmp = new int[3];
				tmp[0] = V[i-1][j-1] + (A.charAt(i-1)==B.charAt(j-1) ? matchScore : misScore);
				tmp[1] = V[i-1][j] + spaceBScore;
				tmp[2] = V[i][j-1] + spaceAScore;
				int max = 0;
				if(tmp[1]>tmp[max])
					max = 1;
				if(tmp[2]>tmp[max])
					max = 2;
				
				path[i][j] = max;
				V[i][j] = tmp[max];
			}
		}
		newA = "";
		newB = "";
		printAlignment(A.length(), B.length());
		return V[A.length()][B.length()];
	}
	
	static String newA, newB;
	static void printAlignment(int i, int j)
	{
		
		if(path[i][j]==-1)
		{
			return;
		}
		else if(path[i][j]==0)
		{
			printAlignment(i-1, j-1);
			newA = ""+newA+A.charAt(i-1);
			newB = ""+newB+B.charAt(j-1);
		}
		else if(path[i][j]==1)
		{
			printAlignment(i-1, j);
			newA = ""+newA+A.charAt(i-1);
			newB = ""+newB+"_";
		}
		else 
		{
			printAlignment(i, j-1);
			newA = ""+newA+"_";
			newB = ""+newB+B.charAt(j-1);
		}
	}
}