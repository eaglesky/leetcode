#include <iostream>
#include <vector>
using namespace std;

//DP solution
//O(n) time and O(n) space
int maxProfit0(vector<int> &prices) {
    int n = prices.size();
    if (n < 2)
        return 0;

    vector<int> leftMax(n, 0);
    vector<int> rightMax(n, 0);
    
    int curMin = prices[0];
    for (int i = 1; i < n; ++i)
    {
        leftMax[i] = max(leftMax[i-1], prices[i]-curMin);
        curMin = min(curMin, prices[i]);
    }

    int curMax = prices[n-1];
    for (int i = n-2; i >= 0; --i)
    {
        rightMax[i] = max(rightMax[i+1], curMax - prices[i]);
        curMax = max(curMax, prices[i]);
    }

    int maxProfit = 0;
    for (int i = 0; i < n; ++i) {
        maxProfit = max(maxProfit, leftMax[i]+rightMax[i]);
    }

    return maxProfit;
}

// Use algorithm here:
// https://oj.leetcode.com/discuss/2619/dont-need-dp-to-solve-it-within-o-n
// O(n) time and O(1) space
int maxProfitSeg(vector<int> &prices, int start, int end, int& startDay, int& endDay)
{
    if (end == start)
    {
        startDay = endDay = end;
        return 0;
    }

    int maxProfit = 0;
    int curMinDay = start;
    startDay = start;
    endDay = start;
    for (int i = start + 1; i <= end; ++i)
    {
        if (prices[i] - prices[curMinDay] > maxProfit) {
            maxProfit = prices[i] - prices[curMinDay];
            endDay = i;
            startDay = curMinDay;
        }    

        if (prices[i] < prices[curMinDay])
            curMinDay = i;
    }

    return maxProfit;
}

int maxProfit(vector<int> &prices) {
    int n = prices.size();
    if (n < 2)
        return 0;

    int startDayOneTrans, endDayOneTrans;
    int maxOneDay = maxProfitSeg(prices, 0, n-1, startDayOneTrans, endDayOneTrans);
    int startLeft, endLeft, startRight, endRight;
    int maxLeft = maxProfitSeg(prices, 0, startDayOneTrans-1, startLeft, endLeft);
    int maxRight = maxProfitSeg(prices, endDayOneTrans+1, n-1, startRight, endRight);
    int curMaxProfit = (maxLeft > maxRight) ? (maxLeft + maxOneDay) : (maxRight + maxOneDay);

    if (startDayOneTrans < endDayOneTrans) {
        int curMax = prices[startDayOneTrans];
        int maxLoss = 0;
        for (int i = startDayOneTrans; i <= endDayOneTrans; ++i)
        {
           maxLoss = max(maxLoss, curMax-prices[i]);
           curMax = max(curMax, prices[i]);
        }
        curMaxProfit = max(curMaxProfit, maxLoss + maxOneDay);
    }
    return curMaxProfit;
}

int main(int argc, char** argv)
{
    vector<int> test = {2, 4, 1};
    cout << maxProfit(test) << endl;
    return 0;
}
