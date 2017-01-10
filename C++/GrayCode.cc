#include <iostream>
#include <vector>
using namespace std;

//Expected solution.
//O(2^n) time and O(1) space
vector<int> grayCode(int n) {
	vector<int> result{0};
    int add = 1;
    for (int i = 0; i < n; ++i)
    {
        int prevNum = result.size();
        for (int j = prevNum - 1; j >= 0; j--)
        {
            result.push_back(result[j] | add);
        }
        add = add << 1;
    }
    
    return result;
}

int main(int argc, char** argv)
{
    int n = atoi(argv[1]);
    cout << "n = " << n << endl;
    vector<int> result = grayCode(n);
    for (int i = 0; i < result.size(); ++i)
        cout << result[i] << endl;
    return 0;
}
