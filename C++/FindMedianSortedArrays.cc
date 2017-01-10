#include <iostream>
using namespace std;

int findKSortedArrays0(int A[], int m, int B[], int n, int k);

double findMedianSortedArrays0(int A[], int m, int B[], int n) {
        
        int mid = (m + n) / 2;
        if ((m + n) & 0x01) {
            return findKSortedArrays(A, m, B, n, mid+1);
        } else
            return (findKSortedArrays(A, m, B, n, mid) + findKSortedArrays(A, m, B, n, mid+1))/2.0;
        
    }
    
int findKSortedArrays0(int A[], int m, int B[], int n, int k) {
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

//Iterative solution
 int findKSortedArrays(int A[], int m, int B[], int n, int k)
    {
        
        if (m > n)
            return findKSortedArrays(B, n, A, m, k);
        if (k > (m+n))
            k = m + n;
        
        int* pa = A;
        int* pb = B;
        
        while (1) {
            if (m > n) {
                swap(pa, pb);
                swap(m, n);
            }
                
            if (m == 0)
                return pb[k-1];
                
            if (k == 1)
                return min(pa[0], pb[0]);
                
            int ia = min(m, k/2);
            int ib = k - ia;
        
            
            if (pa[ia-1] == pb[ib-1])
                return pa[ia-1];
            else if (pa[ia-1] < pb[ib-1]) {
                pa += ia;
                m -= ia;
                n = ib;
                k -= ia;
            } else {
                pb += ib;
                n -= ib;
                m = ia;
                k -= ib;
            }
        }
  
    }
    
    double findMedianSortedArrays(int A[], int m, int B[], int n) {
        if ((m+n) & 1)
            return findKSortedArrays(A, m, B, n, (m+n)/2+1);
        else
            return ((findKSortedArrays(A, m, B, n, (m+n)/2) + findKSortedArrays(A, m, B, n, (m+n)/2+1)) / 2.0);
            
    }

int main(int argc, char** argv)
{
    int A[] = {1};
    int B[] = {1};

    cout << findMedianSortedArrays(A, 1, B, 1) << endl; 

    return 0;
}
