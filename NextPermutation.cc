#include <iostream>
#include <vector>
using namespace std;

void reverse(vector<int> &num, int s, int e)
{
    while (s < e) {
        swap(num[s++], num[e--]);
    }
}

void nextPermutation(vector<int> &num) {
    int n = num.size();
    if (n == 0)
        return;

    int increasedId = n - 2;
    for (; increasedId >= 0; --increasedId)
    {
        if (num[increasedId] < num[increasedId+1])
            break;
    }

    if (increasedId >= 0) {
        int i = n - 1;
        for (; i >= 0; --i)
        {
            if (num[i] > num[increasedId])
                break;
        }
        swap(num[i], num[increasedId]);
    }
    reverse(num, increasedId+1, n-1);
}

int main(int argc, char** argv)
{
    vector<int> test = {1};
    nextPermutation(test);
    for (int i = 0; i < test.size(); ++i)
        cout << test[i] << ", ";
    cout << endl;
    return 0;
}
