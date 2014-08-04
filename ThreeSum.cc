#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

 vector<vector<int> > threeSum(vector<int> &num) {
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
