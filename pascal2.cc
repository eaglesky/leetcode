#include <iostream>
#include <vector>
using namespace std;

//Can be improved to O(n) solution using the recurrence formula:
//C(n, m) = C(n, m-1) * ((n-m+1)/m)
vector<int> getRow(int rowIndex) {
    vector<int> result(rowIndex+1, 0);

    for (int i = 0; i <= rowIndex; ++i)
    {
        for (int j = rowIndex; j > 0; --j)
        {
            result[j] += result[j-1];
        }
        result[0] = 1;
    }

    return result;
}

int main(int argc, char** argv)
{
    
    int row = atoi(argv[1]);
    cout << "Row id = " << row << endl;

    vector<int> result = getRow(row);
    for (int i = 0; i < result.size(); ++i)
        cout << result[i] << ", ";
    cout << endl;

    return 0;
}
