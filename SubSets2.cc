#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;


//Build subsets incrementally and iteratively
vector<vector<int> > subsetsWithDup0(vector<int> &S) {
    vector<int> sortedS = S;
    sort(sortedS.begin(), sortedS.end());
    vector<vector<int> > result;
    result.push_back(vector<int>());
    int preStart = 1;
    for (int i = 0; i < sortedS.size(); ++i)
    {
        int preSize = result.size();
        int j = 0;
        if (i > 0 && (sortedS[i] == sortedS[i-1]))
            j = preStart;
        for (; j < preSize; ++j)
        {
            result.push_back(result[j]);
            result.back().push_back(sortedS[i]);
        }
        preStart = preSize;
    }
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
        while (S[i] == S[i+1])
            i++;
    }
}

vector<vector<int> > subsetsWithDup(vector<int> &S) {
    vector<int> curSet;
    vector<vector<int> > result;
    vector<int> sortedS = S;
    sort(sortedS.begin(), sortedS.end());
    findSubSets2(sortedS, 0, curSet, result);
    return result;
}


int main(int argc, char** argv)
{
    vector<int> test = {1, 2, 3, 2};
    vector<vector<int> > result = subsetsWithDup(test);
    for (int i = 0; i < result.size(); ++i)
    {
        for (int j = 0; j < result[i].size(); ++j)
            cout << result[i][j] << ", ";
        cout << endl;
    }
    return 0;
}
