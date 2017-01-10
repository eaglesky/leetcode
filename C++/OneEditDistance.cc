#include <iostream>
#include <cmath>
using namespace std;

bool isOneEditDistance(string s, string t)
{
    int ns = s.size();
    int nt = t.size();
    if (abs(ns - nt) > 1)
        return false;
    if (ns > nt) {
        swap(s, t);
        swap(ns, nt);
    }

    int i = 0;
    for (; (i < ns) && (s[i] == t[i]); ++i);
    if (i == ns) {
        if (nt > ns)
            return true;
        else
            return false;
    }

    int j = i + 1;
    if (ns == nt)
        i++;
    for (; j < nt; ++j, ++i)
    {
        if (t[j] != s[i])
            return false;
    }

    return true;
}

int main(int argc, char** argv)
{
    string s(argv[1]);
    string t(argv[2]);
    cout << "s = " << s << endl;
    cout << "t = " << t << endl;
    cout << isOneEditDistance(s, t) << endl;
    return 0;
}

