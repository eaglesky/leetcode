#include <iostream>
using namespace std;

char *strStr(char *haystack, char *needle) {
        
        for (char* p = haystack; *p != '\0'; ++p)
        {
            char* ph = p;
            char* pn;
            for (pn = needle; *pn != '\0'; ++pn, ++ph)
            {
                if ((*ph != *pn) || (*ph == '\0'))
                    break;
            }
            if (*pn == '\0')
                return (ph-1);
            if (*ph == '\0')
                break;
        }
        
        return (*needle == '\0') ? haystack : NULL;
        
    }

int main(int argc, char** argv)
{
    cout << strStr("a", "") << endl;
    return 0;
}
