#include <iostream>
#include <vector>
using namespace std;

int findPeakElement(const vector<int> &num) {
    int n = num.size();
    int start = 0;
    int end = n - 1;
    while (start <= end) {
        int mid = start + (end - start) / 2;
        if (((mid == 0) || (num[mid] > num[mid-1])) &&
        ((mid == n-1) || (num[mid] > num[mid+1])))
            return mid;
        else if ((mid > 0) && (num[mid] < num[mid-1])) {
            end = mid - 1;
        } else {
            start = mid + 1;
        }
    }
    
    return -1;
}

int main(int argc, char** argv)
{
    vector<int> num{1, 2, 3, 1};
    cout << findPeakElement(num) << endl;
    return 0;
}