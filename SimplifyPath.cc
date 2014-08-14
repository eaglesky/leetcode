#include <iostream>
#include <vector>
using namespace std;

string simplifyPath(string path) {
    vector<string> stack;
    int lastSlash = 0;
    for (int i = 1; i <= path.size(); ++i)
    {
        if ((i == path.size()) || (path[i] == '/')) {
            if ((i - lastSlash > 1)) {
                string seg = path.substr(lastSlash+1, i-lastSlash-1);
                if (seg == "..") {
                    if (!stack.empty())
                        stack.pop_back();
                } else if (seg != ".") {
                    stack.push_back(seg);
                }
            }
            lastSlash = i;
        } 
    }
    string result = "";
    for (int i = 0; i < stack.size(); ++i)
    {
       result += "/";
       result += stack[i];
    }
    return stack.empty() ? "/" : result;
}

int main(int argc, char** argv)
{
    string test(argv[1]);
    cout << simplifyPath(test) << endl;
    return 0;
}
