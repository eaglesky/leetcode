#include <iostream>
using namespace std;

 bool search(int A[], int n, int target) {
        
        int left = 0;
        int right = n-1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (target == A[mid])
                return true;
            else if (target < A[mid]) {
                if ((A[left] < A[mid]) && (target < A[left]))
                    left = mid + 1;
                else if (A[left] == A[mid])
                    left++;
                else
                    right = mid - 1;
            } else {
                if ((A[mid] < A[right]) && (target > A[right]))
                    right = mid - 1;
                else if (A[mid] == A[right])
                    right--;
                else
                    left = mid + 1;
            }
        }
        
        return false;
    }

 int searchLowerBound(int A[], int n, int target) {
        
        int left = 0;
        int right = n-1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (target <= A[mid]) {
                if ((A[left] < A[mid]) && (target < A[left]))
                    left = mid + 1;
                else if (A[left] == A[mid]) {
                    if (target != A[mid])
                        left++;
                    else
                        right = left;
                } else
                    right = mid;
            } else {
                if ((A[mid] < A[right]) && (target > A[right]))
                    right = mid - 1;
                else if (A[mid] == A[right])
                    right--;
                else
                    left = mid + 1;
            }
        }
        
        return A[left] == target ? left : -1;
    }


int main(int argc, char** argv)
{
    int test[] = {4, 5, 5, 3, 3, 4};
    cout << searchLowerBound(test, sizeof(test)/sizeof(int), 3) << endl;
    return 0;
}
