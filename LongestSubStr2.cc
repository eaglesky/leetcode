#include <iostream>
#include <unordered_map>
using namespace std;

//O(n) time and O(1) space
int lengthOfLongestSubstringTwoDistinct0(string s)
{
    int n = s.size();
    if (n <= 2)
        return n;

    int i = 0;
    int j = 0;
    int result = 0;
    int k = 1;
    for (; k < n; ++k)
    {
        if (s[k] == s[k-1])
            continue;

        result = max(result, k - i);

        if (s[k] != s[i]) {
            i = j;
        }

        j = k;
    }

    return max(result, k - i);
}

// Another implementation in O(n) time and uses O(n) space which can be easily generalized
// to the case when the maximum number of distinct characters is more than 2
int lengthOfLongestSubstringTwoDistinct(string s)
{
    int diffNum = 4;
    int n = s.size();
    if (n <= diffNum)
        return n;
    int result = 0;
    unordered_map<char, int> counts;
    int distinctNum = 0;
    int start = 0;
    int i = 0;
    for (; i < n; ++i)
    {
        if (counts.find(s[i]) == counts.end()) {
            counts[s[i]]++;
            distinctNum++;
            if (distinctNum > diffNum) {
                result = max(result, i - start);
                for (; start < i; ++start)
                {
                    counts[s[start]]--;

                    if (counts[s[start]] == 0) {
                        counts.erase(s[start++]);
                        distinctNum--;
                        break;
                    }
                    
                }
            }

        } else
            counts[s[i]]++;
    }

    return max(result, i - start);
}

int main(int argc, char** argv)
{
    string test(argv[1]);
    cout << test << ": " << lengthOfLongestSubstringTwoDistinct(test) << endl;
    return 0;
}
