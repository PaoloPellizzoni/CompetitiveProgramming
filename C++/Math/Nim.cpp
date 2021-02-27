/*
We have a game which fulfills the following requirements:
    - There are two players who move alternately.
    - The game consists of states, and the possible moves in a state do not depend on whose turn it is.
    - The game ends when a player cannot make a move.
    - The game surely ends sooner or later.
    - The players have complete information about the states and allowed moves, and there is no randomness in the game.
The idea is to calculate Grundy numbers for each game state. It is calculated like so: mex({g_1, g_2, ..., g_n}),
where g_1, g_2, ..., g_n are the Grundy numbers of the states which are reachable from the current state. $mex$ gives the smallest nonnegative number that
is not in the set (mex(\{0, 1, 3\}) = 2, mex(\emptyset) = 0). If the Grundy number of a state is 0, then this state is a losing state. Otherwise it's a winning
state.
Sometimes a move in a game divides the game into subgames that are independent of each other. In this case, the Grundy number of a game state is
$mex({g_1, g_2, ..., g_n}), g_k = a_{k,1} xor a_{k,2} xor ... xor a_{k,m}$ meaning that move $k$ divides the game into $m$ subgames whose Grundy numbers are $a_{i,j}$.

Example: We have a heap with $n$ sticks. On each turn, the player chooses a heap and divides it into two nonempty heaps such that the heaps are of different size. The player
who makes the last move wins the game. Let $g(n)$ denote the Grundy number of a heap of size $n$. The Grundy number can be calculated by going though all possible ways to divide the heap into
two parts. E.g. $g(8) = mex(\{g(1) \oplus g(7), g(2) \oplus g(6), g(3) \oplus g(5)\}).$ Base case: $g(1) = g(2) = 0$, because these are losing states
*/


map<set<int>,int> grundy;
map<ll,set<int>> mp;

int getGrundy(set<int> x){
    // base case
    if( sz(x) == 0 ) return 0;
    if( grundy.find(x) != grundy.end() ) return grundy[x];
    
    set<int> S;
    int res = 0;

    auto iter = x.end(); iter--;
    int mx = *iter;

    // transition : which k to select
    for(int i=1;i<=mx;i++){
        set<int> nxt;
        for(auto e : x){
            if( e < i ) nxt.insert(e);
            else if( e == i ) continue;
            else nxt.insert(e-i); 
        }
        S.insert(get_grundy(nxt));
    }

    // find mex and return
    while( S.find(res) != S.end() ) res++;
    grundy[x] = res;
    return res;
}