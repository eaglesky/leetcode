#include <algorithm>
#include <iostream>
#include <unordered_set>
#include <vector>
using namespace std;

//Recursive solution. O(n*n!) time and O(n) space 
void permuteRec(vector<int>& num, int start, vector<vector<int> >& result)
{
    if (start == num.size() - 1) {
        result.push_back(num);
        return ;
    }

    unordered_set<int> set;
    for (int i = start; i < num.size(); ++i)
    {
        auto it = set.find(num[i]);
        if (it != set.end())
            continue;
        swap(num[start], num[i]);
        permuteRec(num, start+1, result);
        swap(num[start], num[i]);
        set.insert(num[i]);
    }
}

vector<vector<int> > permuteUnique(vector<int> &num) {
    vector<vector<int> > result;
    permuteRec(num, 0, result);
    return result;
}

//Iterative solution is exactly same as Permutations1
int main(int argc, char** argv)
{
    vector<int> test = {4, 4, 5, 5, 5, 7};
    vector<vector<int> > result = permuteUnique(test);
    for (int i = 0; i < result.size(); ++i)
    {
        for (int j = 0; j < result[i].size(); ++j)
            cout << result[i][j] << ", ";
        cout << endl;
    }
    cout << "Total = " << result.size() << endl; //Should be 60 for this test case
    cout << "Test array after finished: " << endl;
    for (int i = 0; i < test.size(); ++i)
        cout << test[i] << ",";
    cout << endl;
    return 0;
}


