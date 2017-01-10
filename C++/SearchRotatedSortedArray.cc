#include <iostream>
using namespace std;

int search0(int A[], int n, int target) {
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (A[mid] == target)
                return mid;
            else if (A[mid] < target) {
                if ((A[mid] <= A[right]) && (target > A[right])) {
                    right = mid - 1;
                } else
                    left = mid + 1;
            } else {
                if ((A[mid] >= A[left]) && (target < A[left])) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            
        }
        return -1;
    }

//Better algorithm
int search(int A[], int n, int target) {
    int start = 0;
    int end = n - 1;
    while (start <= end) {
        int mid = start + (end - start) / 2;
        if (target == A[mid])
            return mid;
            
        if (A[start] <= A[mid]) {
            if ((A[start] <= target) && (target <= A[mid]))
                end = mid;
            else
                start = mid + 1;
        } else {
            if ((A[mid] < target) && (target <= A[end]))
                start = mid + 1;
            else
                end = mid;
        }
    }
    
    return -1;
}

int main(int argc, char** argv)
{
    int test[] = {1, 3};
    cout << search(test, 2, 3) << endl;
    return 0;
}
