#include <cstdlib>
#include <iostream>
#include <vector>
using namespace std;

//Solution on the book is shorter!  
void dfsGenParen(int leftRest, int rightRest, vector<char>& curParens, vector<string>& result)
{
    if ((leftRest == 0) && (rightRest == 0)) {
        string cur = "";
        for (int i = 0; i < curParens.size(); ++i)
            cur += curParens[i];
        result.push_back(cur);
        return;
    }

    if (leftRest > rightRest)
        return;

    if (leftRest > 0) {
        curParens.push_back('(');
        dfsGenParen(leftRest-1, rightRest, curParens, result);
        curParens.pop_back();
    }

    if (rightRest > 0) {
        curParens.push_back(')');
        dfsGenParen(leftRest, rightRest-1, curParens, result);
        curParens.pop_back();
    }
    
}

vector<string> generateParenthesis0(int n) {
    vector<string> result;
    vector<char> curParen;
    dfsGenParen(n, n, curParen, result);
    return result;
}

// Iterative solution is simply a simultation of the above solution using two
// stacks, or one stack using a pair as the element
vector<string> generateParenthesis(int n) {
    vector<pair<string, int> > stack{make_pair("(", 1)};
    vector<string> result;
    if (n == 0)
        return result;
        
    while (!stack.empty()) {
        pair<string, int> curPair = stack.back();
        stack.pop_back();
        if (curPair.second < n)
            stack.push_back(make_pair(curPair.first + "(", curPair.second+1));
        
        if (2 * curPair.second > curPair.first.size()) {
            stack.push_back(make_pair(curPair.first + ")", curPair.second));
        }
        
        if (curPair.first.size() == 2*n) {
            result.push_back(curPair.first);
        }
    }
    
    return result;
}

// DP solution can be found here:
// https://oj.leetcode.com/discuss/11509/an-iterative-method

int main(int argc, char** argv)
{
    int n = atoi(argv[1]);
    cout << "n = " << n << endl;
    vector<string> result = generateParenthesis(n);
    for (int i = 0; i < result.size(); ++i)
        cout << result[i] << endl;

}
