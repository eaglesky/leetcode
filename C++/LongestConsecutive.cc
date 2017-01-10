#include <iostream>
#include <unordered_map>
#include <vector>

using namespace std;

int longestConsecutive0(vector<int> &num) {
        unordered_map<int, bool> used;
        for (auto& number : num)
            used[number] = false;
        
        
        int maxLength = 0;
        for (auto& curMap : used) {
            if (curMap.second)
                continue;
                
            curMap.second = true;
            
            int length = 0;
            for (int i = curMap.first; used.find(i) != used.end(); ++i) {
                used[i] = true;
                length++;
            }
            
            for (int i = curMap.first-1; used.find(i) != used.end(); --i) {
                used[i] = true;
                length++;
            }
            
            if (length > maxLength) {
                maxLength = length;
            }
        }
        
        return maxLength;
    }

int longestConsecutive(vector<int> &num) {
        unordered_map<int, bool> map;
        int maxLen = 0;
        for (int i = 0; i < num.size(); ++i)
        {
           map[num[i]] = false;
        }
        
        for (auto it = map.begin(); it != map.end(); ++it)
        {
            int num = it->first;
          
            int curLen = 0;
            if (!it->second) {
                int maxNum = num;
                
                for (; map.count(maxNum) > 0; maxNum++)
                    map[maxNum] = true;
            
                int minNum = num;
                for (; map.count(minNum) > 0; minNum--)
                    map[minNum] = true;
                
                maxLen = max(maxLen, maxNum - minNum - 1);
            
            }
        }
        
        return maxLen;
    }

int main(int argc, char** argv)
{
    vector<int> test = {100, 4, 200, 1, 3, 2};
    int length = longestConsecutive(test);
    cout << "Length: " << length << endl;

    return 0;
}
