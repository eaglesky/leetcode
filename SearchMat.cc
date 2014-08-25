#include <iostream>
#include <vector>
using namespace std;

bool searchMatrix0(vector<vector<int> > &matrix, int target) {
    int start = 0;
    int end = matrix.size() - 1;
    int row = -1;
    while (start <= end) {
        int mid = start + (end - start) / 2;
        if (target >= matrix[mid][0]) {
            if (start == mid) {
                if (target < matrix[end][0])
                    row = start;
                else
                    row = end;
                break;
            }
            start = mid;
        }
        else
            end = mid - 1;
    }
    if ((row == -1) || (start > end))
        return false;
    start = 0;
    end = matrix[row].size() - 1;
    while (start <= end) {
        int mid = start + (end - start) / 2;
        if (target < matrix[row][mid])
            end = mid - 1;
        else if (target > matrix[row][mid])
            start = mid + 1;
        else 
            return true;
    }
    return false;
}

//Improved solution
bool searchMatrix(vector<vector<int> > &matrix, int target) {
    int rows = matrix.size();
    if (rows == 0)
        return false;
    int cols = matrix[0].size();
    int start = 0;
    int end = rows * cols - 1;
    while (start <= end) {
        int mid = start + (end - start) / 2;
        int curRow = mid / cols;
        int curCol = mid % cols;
        if (target < matrix[curRow][curCol])
            end = mid - 1;
        else if (target > matrix[curRow][curCol])
            start = mid + 1;
        else
            return true;
    }
    return false;
}


int main(int argc, char** argv)
{
    vector<vector<int> > mat = {
        {1, 3, 5, 7},
        {10, 11, 16, 20},
        {23, 30, 34, 50}
    };
    int target = 50;
    cout << searchMatrix(mat, target) << endl;

    return 0;
}
