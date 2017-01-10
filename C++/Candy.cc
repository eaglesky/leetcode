#include <iostream>
#include <vector>
using namespace std;

//O(n) time and O(n) space
int candy0(vector<int> &ratings) {
    int n = ratings.size();
    vector<int> candies(n, 1);
    for (int i = 1; i < n; ++i)
    {
        if (ratings[i] > ratings[i-1])
            candies[i] = candies[i-1] + 1;
    }

    for (int i = n-2; i >= 0; --i)
    {
        if (ratings[i] > ratings[i+1]) {
            candies[i] = max(candies[i], candies[i+1] + 1);
        }
    }

    int result = 0;
    for (int i = 0; i < n; ++i)
        result += candies[i];

    return result;
}

//Improved solution
//O(n) time and O(1) space
int candy(vector<int> &ratings) {

       int n = ratings.size();
        int total = 0;
        int prev = 0;
        for (int i = 0; i < n;)
        {
            int cur = 1;
            if ((i > 0) && (ratings[i] > ratings[i-1]))
                cur = prev + 1;
            int j = i + 1;
            for (;(j < n) && (ratings[j] < ratings[j-1]); ++j);
    
            cur = max(cur, j-i);
            total += cur + (j-i) * (j-i-1) / 2;
            prev = (j > i + 1) ? 1 : cur;
            i = j;
        }
    
        return total;

}

int main(int argc, char** argv)
{
    vector<int> ratings = {1, 1, 1, 1};
    cout << candy(ratings) << endl;
    return 0;
}
