#define MAXN 1001
#define MAXM 1001
int V[MAXN][MAXM];
int path[MAXN][MAXM];
int stringAlignment(string A, string B) //Needleman-Wunsch
{   // change scores accordingly
	int matchScore = 2, misScore = -1, spaceAScore = -1, spaceBScore = -1;
	memset(V, 0, sizeof(V));
	memset(path, -1, sizeof(path));
	for(int i=1; i<=A.size(); i++){
		V[i][0] = i * spaceBScore;
		path[i][0] = 1;
	}
	for(int i=1; i<=B.size(); i++){
		V[0][i] = i * spaceAScore;
		path[0][i] = 2;
	}
	for(int i=1; i<=A.size(); i++){
		for(int j=1; j<=B.size(); j++){
			int tmp[3];
			tmp[0] = V[i-1][j-1] + (A[i-1]==B[j-1] ? matchScore : misScore);
			tmp[1] = V[i-1][j] + spaceBScore;
			tmp[2] = V[i][j-1] + spaceAScore;
			int max = 0;
			if(tmp[1]>tmp[max])	max = 1;
			if(tmp[2]>tmp[max])	max = 2;
			path[i][j] = max;
			V[i][j] = tmp[max];
		}
	}
	return V[A.size()][B.size()];
}

