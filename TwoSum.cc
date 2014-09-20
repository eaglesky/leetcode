#include <iostream>
#include <unordered_map>
#include <vector>

using namespace std;

vector<int> twoSum0(vector<int> &numbers, int target) {
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

vector<int> twoSum1(vector<int> &numbers, int target) {
        unordered_map<int, vector<int> > map;
        vector<int> result;
        for (int i = 0; i < numbers.size(); ++i)
        {
            if (map.find(numbers[i]) == map.end()) {
                vector<int> newIndex{i};
                map[numbers[i]] = newIndex;
            } else
                map[numbers[i]].push_back(i);
        }
        
        for (int i = 0; i < numbers.size(); ++i)
        {
            int comp = target - numbers[i];
            if ((comp != numbers[i]) && (map.find(comp) != map.end())) {
                result.push_back(i+1);
                result.push_back(map[comp][0]+1);
                return result;
            } else if ((comp == numbers[i]) && (map[comp].size() > 1)) {
                result.push_back(map[comp][0]+1);
                result.push_back(map[comp][1]+1);
                return result;
            }
        }
        
        return result;
    }
    
//Best version!
//O(n) time and O(n) space
    vector<int> twoSum(vector<int> &numbers, int target) {
        unordered_map<int, int> map;
        vector<int> result;
        for (int i = 0; i < numbers.size(); ++i)
        {
            if (map.find(target-numbers[i]) != map.end()) {
                result.push_back(map[target-numbers[i]]+1);
                result.push_back(i+1);
            }
            map[numbers[i]] = i;
        }
        return result;
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
