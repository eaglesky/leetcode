#include <iostream>
#include <vector>
using namespace std;

int searchBoundary(int A[], int n, int target, bool isLower)
{
    int start = 0;
    int end = n-1;
    if (n == 0)
        return -1;
    while (start < end) {
        int mid = start + (end - start) / 2;
        if (target < A[mid])
            end = mid - 1;
        else if (target > A[mid])
            start = mid + 1;
        else if (isLower) {
            end = mid;
        } else {
            if (mid == start) {
                return (A[end] == target) ? end : start;
            }
            start = mid;
        }

     }

    if (A[start] == target)
        return start;
    else
        return -1;
}

vector<int> searchRange(int A[], int n, int target) {
    vector<int> result{-1, -1};
    int lower = searchBoundary(A, n, target, true);
    if (lower != -1) {
        result.clear();
        result.push_back(lower);
        result.push_back(searchBoundary(A, n, target, false));
    }
    return result;
}

int main(int argc, char** argv)
{
    int test[] = {7, 9 };
    int target = 8;
    vector<int> result = searchRange(test, sizeof(test)/sizeof(int), target);
    for (int i = 0; i < result.size(); ++i)
        cout << result[i] << ", ";
    cout << endl;
    return 0;
}
