#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

 vector<vector<int> > threeSum0(vector<int> &num) {

        vector<vector<int> > results;
        
        if (num.size() < 3)
            return results;

        sort(num.begin(), num.end());

        

        for (int ia = 0; ia < num.size() - 2; ++ia) {

            if ((ia > 0) && (num[ia] == num[ia-1]))

                continue;

                

            int target = -num[ia];

            

            int ib = ia + 1;

            int ic = num.size() - 1;

            

            while (ib < ic) {

                if (num[ib] + num[ic] == target) {

                    vector<int> res{num[ia], num[ib], num[ic]};

                    results.push_back(res);

                    

                    ib++;

                    while ((ib < ic) && (num[ib] == num[ib-1]))

                        ib++;

                    

                    ic--;

                } else if (num[ib] + num[ic] < target) {

                    ib++;

                } else

                    ic--;

            }

            

        }

        

        return results;

    }

//O(n^2) time and O(1) space
 vector<vector<int> > threeSum(vector<int> &num) {
        int n = num.size();
        vector<vector<int> > result;
   
        sort(num.begin(), num.end());
        for (int i = 2; i < n; ++i)
        {
            if ((i < n-1) && (num[i] == num[i+1]))
                continue;
                
            int start = 0;
            int end = i-1;
            int curTarget = -num[i];
            while (start < end) {
                if (num[start] + num[end] <= curTarget) {
                    
                    if (num[start] + num[end] == curTarget) {
                        vector<int> triplet{num[start], num[end], num[i]};
                        result.push_back(triplet);
                    }
                    while ((++start < end) && (num[start] == num[start-1]));
                } else {
                    while ((start < --end) && (num[end] == num[end+1]));
                }
            }
        }
        
        return result;
    }


int main(int argc, char** argv)
{
    vector<int> test;
    vector<vector<int> > results = threeSum(test);
    for (auto res :results)
    {
        for (auto num: res)
        {
            cout << num << " " ;
        }
        cout << endl;
    }
    return 0;
}
