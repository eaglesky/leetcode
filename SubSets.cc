#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

//Iterative solution using bit operations
vector<vector<int> > subsets0(vector<int> &S) {
    vector<int> sortedS = S;
    sort(sortedS.begin(), sortedS.end());
    int digNum = sortedS.size();
    vector<vector<int> > result;

    for (int i = 0;i < (1 << digNum) ; ++i)
    {
        result.push_back(vector<int>());
        for (int j = digNum-1; j >= 0 ; --j)
        {
            if ((i >> j) & 1)
                result.back().push_back(sortedS[digNum-1-j]);
        }
    }
    return result;
}

//Build subsets incrementally and iteratively
vector<vector<int> > subsets1(vector<int> &S) {
    vector<int> sortedS = S;
    sort(sortedS.begin(), sortedS.end());
    vector<vector<int> > result;
    result.push_back(vector<int>());
    for (int i = 0; i < sortedS.size(); ++i)
    {
        int prevSize = result.size();
        for (int j = 0; j < prevSize; ++j)
        {
            result.push_back(result[j]);
            result.back().push_back(sortedS[i]);
        }
    }
    return result;

}

//Recursive solution
void findSubsets(vector<int>& S, int i,  vector<int>& curSubset, vector<vector<int> >& result)
{
    if (i == S.size()) {
        result.push_back(curSubset);
        return;
    }

    curSubset.push_back(S[i]);
    findSubsets(S, i+1, curSubset, result);
    curSubset.pop_back();
    findSubsets(S, i+1, curSubset, result);
}

vector<vector<int> > subsets2(vector<int> &S) {
    vector<int> sortedS = S;
    sort(sortedS.begin(), sortedS.end());
    vector<int> curSubset;
    vector<vector<int> > result;
    findSubsets(sortedS, 0, curSubset, result);
    return result;
}

//Recursive solution 2 (Tricky!)
void  findSubSets2(vector<int>& S, int start, vector<int>& curSet, vector<vector<int> >& result)
{
    result.push_back(curSet);

    for (int i = start; i < S.size(); ++i)
    {
        curSet.push_back(S[i]);
        findSubSets2(S, i+1, curSet, result);
        curSet.pop_back();
    }
}

vector<vector<int> > subsets(vector<int> &S) {
    vector<int> curSet;
    vector<vector<int> > result;
    vector<int> sortedS = S;
    sort(sortedS.begin(), sortedS.end());
    findSubSets2(sortedS, 0, curSet, result);
    return result;
}


int main(int argc, char** argv)
{
    vector<int> test = {4, 1, 0};
    vector<vector<int> > result = subsets(test);
    for (int i = 0; i < result.size(); ++i)
    {
        for (int j = 0; j < result[i].size(); ++j)
            cout << result[i][j] << ", ";
        cout << endl;
    }
    return 0;
}
