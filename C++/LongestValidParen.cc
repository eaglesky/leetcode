#include <iostream>
#include <vector>
using namespace std;

// DP solution, ugly!
int longestValidParentheses0(string s) {
    int len = s.size();
    if (len < 2)
        return 0;

    int* ids = new int[len];
    int maxLen = 0;
    int lastRightParen = (s[0] == ')') ? 0 : -1;
    ids[0] = 0;
    
    for (int i = 1; i < len; ++i)
    {
        if (s[i] == ')') {
            if (i - lastRightParen == 2) {
                if ((lastRightParen < 0) || (ids[lastRightParen] == lastRightParen))
                        ids[i] = i-1;
                else
                    ids[i] = ids[lastRightParen];
            } else if (i - lastRightParen == 1) {
                int lastLeftParen = ids[lastRightParen];
                if ((lastLeftParen > 0) && (lastLeftParen < lastRightParen) &&  (s[lastLeftParen-1] == '(')) {
                    ids[i] = lastLeftParen-1;
                    if (ids[i] > 0 && ids[ids[i]-1] < ids[i]-1)
                        ids[i] = ids[ids[i]-1];
                }
                else
                    ids[i] = i;
            } else
                ids[i] = i - 1;
            lastRightParen = i;
            if (i - ids[i] + 1 > maxLen)
                maxLen = i - ids[i] + 1;
        } else
            ids[i] = i;
    }

    
    delete[] ids;

    return ((maxLen & 1) == 0)? maxLen : 0;
}

//DP solution improved
int longestValidParentheses1(string s) {
    int n = s.size();
    vector<int> lens(n, 0);
    for (int i = 0; i < n; ++i)
    {
        if ((i > 0) && (s[i] == ')')) {
          
            if (s[i-1] == ')') {
               if ((i-lens[i-1]-1 >= 0) && (s[i-lens[i-1]-1] == '(')) {
                   lens[i] = lens[i-1] + 2;
                   if (i-lens[i-1]-2 >= 0)
                    lens[i] += lens[i-lens[i-1]-2];
               } 
            } else {
                lens[i] = (i >= 2) ? lens[i-2] + 2 : 2;
            }
            
        }
    }
    
    int maxLen = 0;
    for (int i = 0; i < n; ++i)
        maxLen = max(maxLen, lens[i]);
    
    return maxLen;
}

// Using a stack. Two passes
int longestValidParentheses2(string s) {
    vector<int> stack;
    for (int i = 0; i < s.size(); ++i)
    {
        if (!stack.empty() && (s[stack.back()] == '(') && s[i] == ')') {
            stack.pop_back();
        } else
            stack.push_back(i);
    }

    stack.push_back(s.size());
    int maxLen = stack[0];
    for (int i = 1; i < stack.size(); ++i)
    {
       int curLen = stack[i] - stack[i-1] - 1;
       if (curLen > maxLen)
           maxLen = curLen;
    }
    return maxLen;
}

// Using a stack. One pass
int longestValidParentheses3(string s) {
    vector<int> stack;
    int maxLen = 0;
    for (int i = 0; i < s.size(); ++i)
    {
        if (s[i] == '(')
            stack.push_back(i);
        else {
            if (!stack.empty() && s[stack.back()] == '(') {
                stack.pop_back();
                int lastPos = -1;
                if (!stack.empty())
                    lastPos = stack.back();
                int curLen = i - lastPos;
                maxLen = (maxLen < curLen) ? curLen : maxLen;
            } else
                stack.push_back(i);
        }
    }
    return maxLen;
}

//Another similar one stack one pass solution
int longestValidParentheses4(string s) {
    int maxLen = 0;
    vector<int> stack;
    int lastRightSlash = -1;
    for (int i = 0; i < s.size(); ++i)
    {
        if (s[i] == '(') {
            stack.push_back(i);
        } else {
            if (!stack.empty()) {
                stack.pop_back();
                int lastPos = stack.empty() ? lastRightSlash : stack.back();
                maxLen = max(maxLen, i-lastPos);
            } else 
                lastRightSlash = i;
        } 
    }

    return maxLen;
}

//O(n) time and O(1) space in two passes
int longestValidParentheses(string s) {
    int maxLen = 0;
    int start = -1;
    int depth = 0;
    int n = s.size();

    //Find the longest paratheses sequence that the left parentheses before the
    //right matched parentheses are fewer than the right ones
    for (int i = 0; i < n; ++i)
    {
        if (s[i] == '(')
            depth++;
        else {
            depth--;
            if (depth == 0) {
                maxLen = max(maxLen, i-start);
            } else if (depth < 0) {
                start = i;
                depth = 0;
            }
        }
    }

    depth = 0;
    start = n;

    //Find the longest paratheses sequence that the right parentheses after the
    //left matched parentheses are fewer than the left ones
    for (int i = n-1; i >= 0; --i)
    {
        if (s[i] == ')')
            depth++;
        else {
            depth--;
            if (depth == 0) {
                maxLen = max(maxLen, start-i);
            } else if (depth < 0) {
                start = i;
                depth = 0;
            }
        }
    }

    return maxLen;
}


int main(int argc, char** argv)
{
    string test("(())()))");
    cout << longestValidParentheses(test) << endl;
    return 0;
}
