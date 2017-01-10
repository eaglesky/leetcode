#include <iostream>
#include <vector>
using namespace std;

//More simple solution is on the book(note that it is square matrix)
vector<vector<int> > generateMatrix(int n) {
    vector<vector<int> > result(n, vector<int>(n, 0));
    if (n == 0)
        return result;

    int i = 0;
    int j = 0;
    int num = 1;
    for (int c = 0; num <= n*n; ++c)
    {
        int dir = c % 4;

        switch (dir) {
            case 0:
                for (; j < n - c/4; ++j, num++) 
                    result[i][j] = num;
                break;

            case 1:
                for (--j, ++i; i < n - c/4; ++i, num++)
                    result[i][j] = num;
                break;

            case 2:
                for (--i, --j; j >= c/4; --j, num++)
                    result[i][j] = num;
                break;

            case 3:
                for (++j, --i; i >= c/4 + 1; --i, num++)
                    result[i][j] = num;
                ++i;
                ++j;
                break;
        }
    }

    return result;
}

int main(int argc, char** argv)
{
    int n = atoi(argv[1]);
    vector<vector<int> > mat = generateMatrix(n) ;
    cout << "n = " << n << endl;
    for (int i = 0; i < mat.size(); ++i)
    {
        for (int j = 0; j < mat[i].size(); ++j)
            cout << mat[i][j] << ", " ;
        cout << endl;
    }
    return 0;
}
