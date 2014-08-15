#include <iostream>
#include <vector>
using namespace std;

int compute(int a, int b, const string& op)
{
    if (op == "+")
        return a + b;
    else if (op == "-")
        return a - b;
    else if (op == "*")
        return a * b;
    else if (op == "/")
        return a / b;
    else
        return 0;
}

int evalRPN(vector<string>& tokens) {
    vector<int> results;
    for (int i = 0; i < tokens.size(); ++i)
    {
        if((tokens[i] != "+") && (tokens[i] != "-") && (tokens[i] != "*")
                && (tokens[i] != "/"))
            results.push_back(stoi(tokens[i]));
        else {
            int result = compute(results[results.size() - 2], results.back(), tokens[i]);
            results.pop_back();
            results.pop_back();
            results.push_back(result);
        }
    }

    if (!results.empty())
        return results.back();
    else 
        return 0;
}

int main (int argc, char** argv)
{
    vector<string> test = {"2", "1", "+", "3", "*"};
    vector<string> test2 = {"4", "13", "5", "/", "+"};
    cout << evalRPN(test) << endl;
    cout << evalRPN(test2) << endl;
    return 0;
}
