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

vector<string> generateParenthesis(int n) {
    vector<string> result;
    vector<char> curParen;
    dfsGenParen(n, n, curParen, result);
    return result;
}

// Iterative solution is simply a simultation of the above solution using two
// stacks
int main(int argc, char** argv)
{
    int n = atoi(argv[1]);
    cout << "n = " << n << endl;
    vector<string> result = generateParenthesis(n);
    for (int i = 0; i < result.size(); ++i)
        cout << result[i] << endl;

}
