#include <iostream>
#include <vector>
using namespace std;

//Greedy algorithm
//O(n) time and O(1) space
int maxProfit(vector<int> &prices) {
    int maxProfit = 0;
    int n = prices.size();
    if (n < 2)
        return maxProfit;
    for (int i = 1; i < n; ++i)
    {
        int diff = prices[i] - prices[i-1];
        if (diff > 0)
            maxProfit += diff;
    }
    return maxProfit;
}

int main(int argc, char** argv)
{
    return 0;
}
