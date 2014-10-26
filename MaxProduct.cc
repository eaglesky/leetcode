#include <iostream>
using namespace std;

//DP solution. O(n) time and O(1) space
int maxProduct(int A[], int n) {
    if (n == 0)
        return 0;
        
    int maxProduct = A[0];
    int prevMax = A[0];
    int prevMin = A[0];
    for (int i = 1; i < n; ++i)
    {
        int curProduct1 = prevMax * A[i];
        int curProduct2 = prevMin * A[i];
        prevMax = max(max(curProduct1, curProduct2), A[i]);
        prevMin = min(min(curProduct1, curProduct2), A[i]);
        maxProduct = max(maxProduct, prevMax);
    }
    
    return maxProduct;
}

int main(int argc, char** argv)
{
    return 0;
}