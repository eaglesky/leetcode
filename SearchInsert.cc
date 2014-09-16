#include <iostream>
using namespace std;

 int searchInsert0(int A[], int n, int target) {
        int left = 0;
        int right = n-1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target <= A[mid])
                right = mid;
            else
                left = mid + 1;
        }
        
        if ((A[left] == target) || (target < A[left]))
            return left;
        else if (target > A[left])
            return left + 1;
    }

int searchInsert(int A[], int n, int target) {
    int start = 0;
    int end = n-1;
    while (start <= end) {
        int mid = start + (end - start) / 2;
        if (target <= A[mid]) {
            if (start == end)
                break;
            end = mid;
        } else
            start = mid + 1;
    }
    return start;
}

int main(int argc, char** argv)
{
    int test[] = {1, 3, 3, 5, 5, 6};
    int target = 3;
    cout << searchInsert(test, sizeof(test)/sizeof(int), target) << endl;
    return 0;
}
