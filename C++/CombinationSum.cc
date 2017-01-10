#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

void dfsCombinations(vector<int>& candidates, int start, int gap, vector<int>& curCombine, vector<vector<int> >& result)
{
    if (gap < 0)
        return;
    else if (gap == 0) {
        result.push_back(curCombine);
        return;
    }

    for (int i = start; i < candidates.size(); ++i)
    {
        curCombine.push_back(candidates[i]);
        dfsCombinations(candidates, i, gap-candidates[i], curCombine, result);
        curCombine.pop_back();
    }
}

vector<vector<int> > combinationSum(vector<int> &candidates, int target) {
    vector<int> combine;
    vector<vector<int> > result;
    vector<int> sortedCandidates = candidates;
    sort(sortedCandidates.begin(), sortedCandidates.end());
    dfsCombinations(sortedCandidates, 0, target, combine, result);
    return result;
}

int main(int argc, char** argv)
{
    vector<int> candidates = {2, 3, 6, 7};
    int target = 7;
    vector<vector<int> > result = combinationSum(candidates, target);
    for (int i = 0; i < result.size(); ++i)
    {
        for (int j = 0; j < result[i].size(); ++j)
            cout << result[i][j] << ", ";
        cout << endl;
    }
    return 0;
}
