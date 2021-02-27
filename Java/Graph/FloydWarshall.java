import java.io.*;
import java.util.*;

public class FloydWarshall
{
	static int[][] adjMatrix;
	static int n;
	
	public static void floydWarshall()
	{
		//  classic floyd-warshall algorithm...
		for (int i = 0; i < n; i++)
		{
			adjMatrix[i][i] = 0;
		}
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(adjMatrix[i][k]==1000000000 || adjMatrix[k][j]==1000000000)
						continue;
					adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
				}
			}
		}
		// ...ends here
		
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				for (int k = 0; adjMatrix[i][j] != -1000000000 && k < n; k++) {
					if (adjMatrix[i][k] != 1000000000 && adjMatrix[k][j] != 1000000000 && adjMatrix[k][k] < 0)
					{
						adjMatrix[i][j] = -1000000000;
					}
				}
			}
		}
	}
}