#include <iostream>
using namespace std;

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
    int test[] = {3, 3, 5, 6};
    int target = 3;
    cout << searchInsert(test, sizeof(test)/sizeof(int), target) << endl;
    return 0;
}
