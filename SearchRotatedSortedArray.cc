#include <iostream>
using namespace std;

int search(int A[], int n, int target) {
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

int main(int argc, char** argv)
{
    int test[] = {1, 3};
    cout << search(test, 2, 3) << endl;
    return 0;
}
