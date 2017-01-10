#include <climits>
#include <iostream>
#include <vector>
using namespace std;

//Convert it to max subarray problem and solve it using DP.
//O(n) time and O(n) space
int maxProfit0(vector<int> &prices) {
    int n = prices.size();
    if (n < 2)
        return 0;

    vector<int> diffs(n-1);
    vector<int> maxSums(n-1);
    for (int i = 0; i < n-1; ++i)
        diffs[i] = prices[i+1] - prices[i];

    maxSums[0] = diffs[0];
    for (int i = 1; i < n-1; ++i)
        maxSums[i] = (maxSums[i-1] < 0) ? diffs[i] : (diffs[i] + maxSums[i-1]);

    int maxProfit = maxSums[0];
    for (int i = 1; i < n-1; ++i)
    {
       if (maxSums[i] > maxProfit)
           maxProfit = maxSums[i];
    }

    return (maxProfit > 0) ? maxProfit : 0;

}

//Convert it to max subarray problem and solve it using greedy algorithm.
//O(n) time and O(n) space
int maxProfit1(vector<int> &prices) {
    int n = prices.size();
    if (n < 2)
        return 0;

    vector<int> diffs(n-1);
    for (int i = 0; i < n-1; ++i)
        diffs[i] = prices[i+1] - prices[i];

    int curSum = 0;
    int maxSum = INT_MIN;
    for (int i = 0; i < n-1; ++i)
    {
        if (curSum < 0)
            curSum = diffs[i];
        else
            curSum += diffs[i];
        if (curSum > maxSum)
            maxSum = curSum;
    }

    return (maxSum > 0) ? maxSum : 0;
}

//Convert it to max subarray problem and solve it using greedy algorithm.
//This is just a slight change of the above solution
//O(n) time and O(1) space
int maxProfit2(vector<int> &prices) {
    int n = prices.size();
    if (n < 2)
        return 0;

    int curSum = 0;
    int maxSum = INT_MIN;
    for (int i = 0; i < n-1; ++i)
    {
        if (curSum < 0)
            curSum = prices[i+1] - prices[i];
        else
            curSum += prices[i+1] - prices[i];
        if (curSum > maxSum)
            maxSum = curSum;
    }

    return (maxSum > 0) ? maxSum : 0;

}

// Use greedy algorithm directly
int maxProfit(vector<int> &prices) {
    int n = prices.size();
    if (n < 2)
        return 0;

    int profit = 0;
    int curMin = prices[0];
    for (int i = 1; i < n; ++i)
    {
        profit = max(profit, prices[i]-curMin);
        curMin = min(curMin, prices[i]);
    }
    return profit;
}

int main(int argc, char** argv)
{
    return 0;
}
