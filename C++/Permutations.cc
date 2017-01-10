#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

//Recursive solution. O(n*n!) time and O(n) space 
void permuteRec(vector<int>& num, int start, vector<vector<int> >& result)
{
    if (start == num.size() - 1) {
        result.push_back(num);
        return;
    }

    for (int i = start; i < num.size(); ++i)
    {
        swap(num[start], num[i]);
        permuteRec(num, start+1, result);
        swap(num[start], num[i]);
    }

}

vector<vector<int> > permute(vector<int> &num) {
    vector<vector<int> > result;
    permuteRec(num, 0, result);
    return result;
}

//Iterative solution using Next Permutation is on the book.
int main(int argc, char** argv)
{
    vector<int> test = {1, 3, 4, 2};
    vector<vector<int> > result = permute(test);
    for (int i = 0; i < result.size(); ++i)
    {
        for (int j = 0; j < result[i].size(); ++j)
            cout << result[i][j] << ", ";
        cout << endl;
    }
    cout << "Total = " << result.size() << endl;
    cout << "Test array after finished: " << endl;
    for (int i = 0; i < test.size(); ++i)
        cout << test[i] << ",";
    cout << endl;
    return 0;
}


