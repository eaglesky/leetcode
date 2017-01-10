#include <iostream>
#include <vector>
using namespace std;

char matchChar(char c)
{
    switch(c) {
        case ')': return '(';
        case '}': return '{';
        case ']': return '[';
        default: return '\0';
    }
}
bool isValid(string const& s) {
    vector<char> stack;
    for (int i = 0; i < s.size(); ++i)
    {
        if ((s[i] == '(') || (s[i] == '{') || (s[i] == '['))
            stack.push_back(s[i]);
        else if (!stack.empty()) {
            if (stack.back() == matchChar(s[i]))
                stack.pop_back();
            else
                return false;
        } else
            return false;
    }
    return stack.empty();
}

int main(int argc, char** argv)
{
    return 0;
}
