#include <iostream>
#include <vector>
using namespace std;

//Be careful about the last value of i/j when the for loop is finished
//Also pay attention to the matrix of one row/column
vector<int> spiralOrder(vector<vector<int> > &matrix) {

    vector<int> result;
    int w = matrix.size();
    if (w == 0)
        return result;
    int l = matrix[0].size();
    if (l == 0)
        return result;

    int i = 0;
    int j = 0;
    for (int c = 0; result.size() < w*l;  ++c)
    {
        int dir = c % 4;

        switch (dir) {
            case 0:

                for (; j < l-c/4; ++j)
                    result.push_back(matrix[i][j]);
                break;

            case 1:

                for (--j, ++i; i < w-c/4; ++i)
                    result.push_back(matrix[i][j]);
                break;

            case 2:

                for (--i, --j; j >= c/4; --j)
                    result.push_back(matrix[i][j]);

                break;

            case 3:

                for (++j, --i; i >= c/4+1; --i)
                    result.push_back(matrix[i][j]);

                ++i;
                ++j;
                break;

        }

    }

    return result;
}

int main(int argc, char** argv)
{
    vector<vector<int> > mat = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };

    vector<int> result = spiralOrder(mat);
    for (int i = 0; i < result.size(); ++i)
        cout << result[i] << ", " ;
    cout << endl;
    return 0;
}
