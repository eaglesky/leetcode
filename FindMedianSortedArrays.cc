#include <iostream>
using namespace std;

int findKSortedArrays(int A[], int m, int B[], int n, int k);

double findMedianSortedArrays(int A[], int m, int B[], int n) {
        
        int mid = (m + n) / 2;
        if ((m + n) & 0x01) {
            return findKSortedArrays(A, m, B, n, mid+1);
        } else
            return (findKSortedArrays(A, m, B, n, mid) + findKSortedArrays(A, m, B, n, mid+1))/2.0;
        
    }
    
int findKSortedArrays(int A[], int m, int B[], int n, int k) {
        if (n < m)
            return findKSortedArrays(B, n, A, m, k);
        if (m == 0) {
            int id = (n >= k) ? (k-1) : (n-1);
            return B[id];
        }
        if (k == 1) {
             return (A[0] <= B[0]) ? A[0] : B[0];
        }
        
        int ia = (m >= k/2) ? k/2 : m;
        int ib = k - ia;
        
        if (ib > n) {
            return B[n-1];
        }
        
        if (A[ia-1] == B[ib-1]) {
            return A[ia-1];
        } else if (A[ia-1] < B[ib-1]) {
            return findKSortedArrays(A+ia, m-ia, B, n, k-ia);
        } else {
            return findKSortedArrays(A, m, B+ib, n-ib, k-ib);
        }
    }

int main(int argc, char** argv)
{
    int A[] = {1};
    int B[] = {1};

    cout << findMedianSortedArrays(A, 1, B, 1) << endl; 

    return 0;
}
