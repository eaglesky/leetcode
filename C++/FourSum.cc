#include <algorithm>
#include <iostream>
#include <set>
#include <unordered_map>
#include <vector>
using namespace std;

//O(n^3) time and O(1) space
vector<vector<int> > fourSum0(vector<int> &num, int target) {
   int n = num.size();
   vector<vector<int> > result;
   if (n < 4)
       return result;

   sort(num.begin(), num.end());

   for (int first = 0; first < n - 3; ++first)
   {
       if ((first > 0) && (num[first] == num[first-1]))
           continue;
   
   //  for (int last = first + 3; last < n; ++last) is OK too!
       for (int last = n - 1; last >= first + 3; --last)
       {
           if ((last < n-1) && (num[last] == num[last+1]))
               continue;

           int curTarget = target - num[first] - num[last];
           int s = first + 1;
           int e = last - 1;
           while (s < e) {
               if (num[s] + num[e] <= curTarget) {
                   if (num[s] + num[e] == curTarget) {
                       vector<int> quadruplet{num[first], num[s], num[e], num[last]};
                       result.push_back(quadruplet);
                   }
                   while ((++s < e) && (num[s] == num[s-1]));
               } else {
                   while ((s < --e) && (num[e] == num[e+1]));
               }
           }
       }
   }

   return result;

}

//Improved solution
vector<vector<int> > fourSum(vector<int> &num, int target) {
        sort(num.begin(), num.end());
        unordered_map<int, set<pair<int, int>>> hash;
        set<vector<int>> ans;
        int n = num.size();
        for (int i = 0; i < n; i ++) {
            for (int j = i + 1; j < n; j ++) {
                int a = num[i] + num[j];
                if (hash.count(target - a)) {
                    for (auto &p: hash[target - a]) {
                        vector<int> b = {p.first, p.second, num[i], num[j]};
                        ans.insert(b);
                    }
                }
            }
            for (int j = 0; j < i; j ++) {
                int a = num[j], b = num[i];
                hash[a + b].insert(make_pair(a, b));
            }
        }
        return vector<vector<int>>(ans.begin(), ans.end());
    }

int main(int argc, char** argv)
{
    vector<int> test = {1, 0, -1, 0, -2, 2};
    int target = 0;
    vector<vector<int> > result = fourSum(test, target);
    for (int i = 0; i < result.size(); ++i)
    {
        for (int j = 0; j < result[i].size(); ++j)
            cout << result[i][j] << ", ";
        cout << endl;
    }
    return 0;
}
