#include <algorithm>
#include <iostream>
#include <unordered_map>
#include <vector>
using namespace std;


vector<string> anagrams(vector<string> &strs) {
    unordered_map<string, vector<int> > anagrams;
    vector<string> result;

    for (int i = 0; i < strs.size(); ++i)
    {
        string key = strs[i];
        sort(key.begin(), key.end());
        anagrams[key].push_back(i); //This is correct even if key does not exist in the map
    }

    for (auto it = anagrams.begin(); it != anagrams.end(); ++it)
    {
        if (it->second.size() > 1) {//Be careful that anagrams refer to multiple strings(more than 1)!
            
            for (int i = 0; i < it->second.size(); ++i)
                result.push_back(strs[(it->second).at(i)]);
        }
    }
    return result;
}

int main(int argc, char** argv)
{
    return 0;
}


