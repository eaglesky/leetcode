#include <iostream>
#include <unordered_map>
#include <vector>

using namespace std;

vector<int> twoSum(vector<int> &numbers, int target) {
        unordered_map<int, vector<int> > map;
        vector<int> res;
        
        for (int i = 0; i < numbers.size(); ++i)
        {
            //unordered_map<int, vector<int> >::iterator it;
            auto it = map.find(numbers[i]);
            if (it != map.end()) {
                (it->second).push_back(i);
            } else {
                vector<int> posVec {i};
                map[numbers[i]] = posVec;
            } 
            
        }
        
        for (int i = 0; i < numbers.size(); ++i)
        {
            unordered_map<int, vector<int> >::iterator it;
            int comp = target - numbers[i];
            if ((it = map.find(comp)) != map.end()) {
                if (comp == numbers[i]) {
                    if ((it->second).size() > 1) {
                        for (auto j : (it->second)) {
                            if (j != i) {
                                res.push_back(i+1);
                                res.push_back(j+1);
                                return res;
                            }
                        }
                    }
                } else {
                    res.push_back(i+1);
                    res.push_back((it->second).at(0)+1);
                    return res;
                }
            }
        }
        
        return res;
    
}

int main(int argc, char** argv)
{
    vector<int> test{2, 7, 7, 7,   11, 15};
    int target = 14;

    vector<int> res = twoSum(test, target);

    cout << "Result: ";
    for (auto i : res) {
        cout << i << " ";
    }
    cout << endl;

    return 0;
}
