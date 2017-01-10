#include <cstdlib>
#include <iostream>

using namespace std;

//Dynamic Programming
int numTrees0(int n) {
    int** cache = new int*[n];
    for (int i = 0; i < n; ++i)
    {
        cache[i] = new int[n];
        fill_n(cache[i], n, 0);
    }

    for (int l = 1; l <= n; ++l)
    {
        for (int i = 0; i <= n-l; ++i)
        {
            int j = i + l - 1;
            if (l == 1)
                cache[i][j] = 1;
            else {
                for (int t = i+1; t < j; ++t)
                {
                    cache[i][j] += cache[i][t-1] * cache[t+1][j];
                }
                cache[i][j] += cache[i+1][j] + cache[i][j-1];
            }
        }
    }

    int result = cache[0][n-1];
    for (int i = 0; i < n; ++i)
        delete[] cache[i];
    delete[] cache;

    return result;
}

//DP solution improved
int numTrees(int n) {
    vector<int> cache(n+1, 0);
    cache[0] = 1;
    for (int i = 1; i <= n; ++i)
    {
        for (int j = 0; j < i; ++j)
        {
            cache[i] += cache[j] * cache[i-1-j];
        }
    }
    
    return cache[n];
}


int main(int argc, char** argv)
{
    int n = atoi(argv[1]);
    
    cout << n << ": " << numTrees(n) <<  endl;
    return 0;
}
