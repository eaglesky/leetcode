#include <iostream>
#include <vector>
using namespace std;

string simplifyPath0(string path) {
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

string simplifyPath(string path) {
        vector<string> stack;
        string result;
        for (int i = 0; i < path.size();)
        {
            for (; (i < path.size()) && (path[i] == '/'); ++i);
            int nextSlash = path.find("/", i);
            if (nextSlash == -1)
                nextSlash = path.size();
                
            string curStr = path.substr(i, nextSlash-i);
            if (curStr == "..") {
                if (!stack.empty())
                    stack.pop_back();
            } else if ((curStr != ".") && (curStr != ""))
                stack.push_back(curStr);
            
            i = nextSlash;
        }
        
        for (auto s : stack)
        {
            result += "/";
            result += s;
        }
        
        return result.empty() ? "/" : result;
    }


int main(int argc, char** argv)
{
    string test(argv[1]);
    cout << simplifyPath(test) << endl;
    return 0;
}
