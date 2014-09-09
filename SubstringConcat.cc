#include <iostream>
#include <unordered_map>
#include <vector>
using namespace std;

//Original solution, TLE
vector<int> findSubstring0(string S, vector<string> &L) {
    vector<int> result;

    if (L.size() == 0)
        return result;

    int wlen = L[0].size();
    int totalChars = wlen * L.size();
    unordered_map<string, int> Lcounts;
    for (int i = 0; i < L.size(); ++i)
    {
        if (Lcounts.find(L[i]) == Lcounts.end())
            Lcounts[L[i]] = 1;
        else
            Lcounts[L[i]]++;
    }

    
    unordered_map<string, int> Scounts = Lcounts;
    int i, j;
    for (i = 0, j = 0; i < S.size(); )
    {
       string curStr = S.substr(i, wlen);
       if (Scounts.find(curStr) == Scounts.end()) {
            j++;
            i = j;
            Scounts = Lcounts;
       } else {
           Scounts[curStr]--;
           if (Scounts[curStr] == 0)
               Scounts.erase(curStr);

           if (Scounts.empty()) {
               result.push_back(j);
               Scounts[S.substr(j, wlen)] = 1;
               j += wlen;
           }
           i += wlen;
       }
    }

    return result;

}

//Modified solution
//Let the length of S be n and total characters in L be m.
//O(n*m) time and O(m) space
vector<int> findSubstring1(string S, vector<string> &L) {
    
    vector<int> result;

    if (L.size() == 0)
        return result;

    int wlen = L[0].size();
    int totalChars = wlen * L.size();
    unordered_map<string, int> Lcounts;
    for (auto word : L)
        Lcounts[word]++;

    for (int i = 0; i < S.size()-totalChars+1; ++i)
    {
        unordered_map<string, int> unusedCounts(Lcounts);
        for (int j = i; j <= i + totalChars - wlen; j += wlen)
        {
            string curStr = S.substr(j, wlen);
            auto it = unusedCounts.find(curStr);
            if (it != unusedCounts.end()) {
                it->second--;
                if (it->second == 0)
                    unusedCounts.erase(it);
            } else
                break;

        }

        if (unusedCounts.empty())
            result.push_back(i);

    }

    return result;
}

//Improved solution
vector<int> findSubstring(string S, vector<string> &L) {
    vector<int> result;

    if (L.size() == 0)
        return result;

    int wlen = L[0].size();
    int totalChars = wlen * L.size();
    unordered_map<string, int> Lcounts;
    for (auto word : L)
        Lcounts[word]++;

    unordered_map<string, int> usedCounts;

    for (int i = 0; i < wlen; ++i)
    {
        int start = i;
        usedCounts.clear();
        for (int j = i; j < S.size(); j += wlen)
        {
            string curStr = S.substr(j, wlen);
            if ((Lcounts.find(curStr) != Lcounts.end())) {
                usedCounts[curStr]++;
                if (usedCounts[curStr] > Lcounts[curStr]) {
                    string removedStr;
                    do {
                        removedStr = S.substr(start, wlen);
                        usedCounts[removedStr]--;
                        start += wlen;
                    } while(removedStr != curStr);
                } else if (j - start  == totalChars - wlen) {
                    result.push_back(start);
                    usedCounts[S.substr(start, wlen)]--;
                    start += wlen;
                }
      
            } else {
                usedCounts.clear();
                start = j + wlen;
            } 
        }
    }
}

int main(int argc, char** argv)
{
//    string S = "barfoothefoobarman";
//    string S = "foobarfoobarfoo";
//    string S = "abababab";

/*    vector<string> L = {
        "foo", "bar"
    };*/
/*    vector<string> L = {
        "a", "b", "a"
    };*/


    string S = "abaababbaba";
    vector<string> L = {
        "ab", "ba", "ab", "ba"
    };
    vector<int> result = findSubstring(S, L);
    for (int i = 0; i < result.size(); ++i)
        cout << result[i] << endl;

    return 0;
}

