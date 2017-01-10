#include <iostream>
#include <vector>
using namespace std;

vector<vector<int> > generate(int numRows) {
    vector<vector<int> > result;

    for (int i = 0; i < numRows; ++i)
    {
        vector<int> cur{1};
        if (!result.empty()) {

            for (int i = 1; i < result.back().size(); ++i)
            {

                cur.push_back(result.back()[i] + result.back()[i-1]);
            }
            cur.push_back(1);
        }

        result.push_back(cur);

    }
    return result;
}

//Another implementation is on the book, which is better
int main(int argc, char** argv)
{
    int n = atoi(argv[1]);
    cout << "n = " << n << endl;
    vector<vector<int> > result = generate(n);
    for (int i = 0; i < result.size(); ++i)
    {
        for (int j = 0; j < result[i].size(); ++j)
            cout << result[i][j] << ", ";
        cout << endl;
    }


    return 0;
}
