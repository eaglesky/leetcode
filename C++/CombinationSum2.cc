#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

void dfsCombinations2(vector<int>& nums, int start, int gap, vector<int>& curCombine, vector<vector<int> >& result)
{
    if (gap < 0)
        return;
    else if (gap == 0) {
        result.push_back(curCombine);
        return;
    }

    for (int i = start; i < nums.size(); ++i)
    {
        curCombine.push_back(nums[i]);
        dfsCombinations2(nums, i+1, gap-nums[i], curCombine, result);
        curCombine.pop_back();
        while (nums[i] == nums[i+1])
            i++;
    }
}


vector<vector<int> > combinationSum2(vector<int> &num, int target) {
    vector<int> sortedNums = num;
    vector<int> curCombine;
    vector<vector<int> > result;
    sort(sortedNums.begin(), sortedNums.end());
    dfsCombinations2(sortedNums, 0, target, curCombine, result);
    return result;
}

int main(int argc, char** argv)
{
    vector<int> nums = {10, 1, 2, 7, 6, 1, 5};
    int target = 8;
    vector<vector<int> > result = combinationSum2(nums, target);
    for (int i = 0; i < result.size(); ++i)
    {
        for (int j = 0; j < result[i].size(); ++j)
            cout << result[i][j] << ", ";
        cout << endl;
    }
    return 0;
}

