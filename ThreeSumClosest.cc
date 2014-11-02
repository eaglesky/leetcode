#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;



int threeSumClosest(vector<int> &num, int target) {
    int n = num.size();
    if (n < 3)
        return 0;
    int result = num[0] + num[1] + num[2];
    sort(num.begin(), num.end());
    for (int i = 0; i < n - 2; ++i)
    {
        for (;(i > 0) && (num[i] == num[i-1]); ++i);
        int start = i + 1;
        int end = n - 1;
      
        while (start < end) {
            int curSum = num[i] + num[start] + num[end];
            if (abs(curSum-target) < abs(result-target))
                result = curSum;
            if (curSum <= target) {
                if (curSum == target)
                    return curSum;
                for(start++; num[start] == num[start-1]; ++start);
            } else {
                for (end--; num[end] == num[end+1]; --end);
            }
        }
    }
    
    return result;
}

int main(int argc, char** argv)
{
    return 0;
}