#include <iostream>
using namespace std;

//My version
int lengthOfLastWord(const char* s) {
    const char* post = NULL;
    const char* pre = NULL;
    const char* p = s;
    if (!s)
        return 0;

    while(*p) {
        if (isalpha(*p)) {
            if ((p == s) || *(p-1) == ' ')
                pre = p;
            if (!isalpha(*(p+1)))
                post = p;
        }
        p++;
    }

    return pre && post? post - pre + 1 : 0;
}

// Shortest version I've ever found! Tricky!
int lengthOfLastWord2(const char* s) {
    int len = 0;
    while (*s) {
        if (*s++ != ' ')
            ++len;
        else if (*s && *s != ' ')
            len = 0;

    }
    return len;
}

int main(int argc, char** argv)
{
    cout << lengthOfLastWord2(argv[1]) << endl;
    return 0;
}
