#include <iostream>
using namespace std;

char *strStr0(char *haystack, char *needle) {
        
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

 char *strStr(char *haystack, char *needle) {
        
        char* s = haystack;
        for (; ; s++)
        {
            char* s2 = s;
            char* p = needle;
            for (; *s2 && *p && (*s2 == *p); s2++, p++);
            
            if (!*p)
                return s;
                
            if (!*s2)
                break;   
        }
        return NULL;
}

int main(int argc, char** argv)
{
    cout << strStr("a", "") << endl;
    return 0;
}
