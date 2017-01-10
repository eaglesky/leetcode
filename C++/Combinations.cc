#include <cstdlib>
#include <iostream>
#include <vector>
using namespace std;
int timeCount = 0;
//Recursive solution
void combineRec(int n, int k, int start, vector<int>& curCombine, vector<vector<int> >& result)
{
    int curSz = curCombine.size();
    if (n - start + 1 < k - curSz)
        return;
    if (curSz == k) {
        result.push_back(curCombine);
        return;
    }

    curCombine.push_back(start);
    combineRec(n, k, start+1, curCombine, result);
    curCombine.pop_back();
    combineRec(n, k, start+1, curCombine, result);
}

vector<vector<int> > combine0(int n, int k) {
   vector<vector<int> > result;
   vector<int> curCombine;
   combineRec(n, k, 1, curCombine, result);
   return result;
}

//Recursive solution 2
void combineRec2(int n, int k, int start, vector<int>& curCombine, vector<vector<int> >& result)
{
    int curSz = curCombine.size();
    if (n - start + 1 < k - curSz)
        return;
    if (curSz == k) {
        result.push_back(curCombine);
        return;
    }

    for (int i = start; i <= n; ++i)
    {
        curCombine.push_back(i);
        combineRec2(n, k, i+1, curCombine, result);
        curCombine.pop_back();
    }
}

vector<vector<int> > combine1(int n, int k) {
   vector<vector<int> > result;
   vector<int> curCombine;
   combineRec2(n, k, 1, curCombine, result);
   return result;
}

//Iterative solution (Backtracking)
//O(k^2* Cnk)time and O(k) extra space(not including output)
vector<vector<int> > combine(int n, int k) {
    vector<vector<int> > result;
    vector<int> cur;
    if (k > n || k < 0)
        return result;
    else if (k == 0) {
        result.push_back(cur);
        return result;
    }

    for (int i = 0; i < k; ++i)
        cur.push_back(i+1);

    while (!cur.empty()) {
        timeCount++;
        int tail = cur.back();
        int sz = cur.size();
        if (tail > n - k + sz) {
            cur.pop_back();
            if (cur.empty())
                break;
            else
                cur[cur.size()-1]++;
        } else if (cur.size() == k) {
            result.push_back(cur);
            cur[k-1]++;
        } else
            cur.push_back(tail+1);
    }
    return result;
}

//Another iterative using permutation on bit vector takes O(n*Cnk) time and O(n) space(See the book)  
int main(int argc, char** argv)
{
    int n = atoi(argv[1]);
    int k = atoi(argv[2]);
    vector<vector<int> > result = combine(n, k);
    for (int i = 0; i < result.size(); ++i)
    {
        for (int j = 0; j < result[i].size(); ++j)
        {
            cout << result[i][j] << ", ";
        }
        cout << endl;
    }
    cout << "C" << n << "(" << k << ") = " << result.size() << endl;
    cout << "Time Count = " << timeCount << endl;
    return 0;
}
