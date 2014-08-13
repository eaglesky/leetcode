#include <iostream>
#include <cstring>
using namespace std;

  bool isNumber0(const char *s) {
        const char* prevd = NULL;
        const char* prevE = NULL;
        const char* prevDot = NULL;
        bool hasDigit = false;
        while(*s) {
            if (*s == ' ') {
                s++;
                continue;
            } else if ((*s == '+') || (*s == '-')) {
                if (prevd && (*prevd != 'e'))
                    return false;
                 while (*s == ' ')
                    s++;
                if (isdigit(*s))
                    return false;
                    
            } else if (*s == 'e') {
                if ((prevE) || (!prevd) || (!isdigit(*prevd)))
                    return false;
                else
                    prevE = s;
                
                while (*s == ' ')
                    s++;
                    
                if ((*s != '+') && (*s != '-') && !isdigit(*s))
                    return false;
                    
            } else if (*s == '.') {
                if ((prevDot) || (prevE))
                    return false;
                else
                    prevDot = s;
            } 
            
            if (isdigit(*s))
                hasDigit = true;
            prevd = s;
            s++;
        }
        
        if ((!prevd) || !hasDigit)
            return false;
        
        return true;
    }


// Rules:
// ' ': Leading and trailing spaces are valid, however spaces in between
// characters are not.
// 'e': No more than 1. If exists, its left must be a valid number and right
// must be a valid integer(without dot).
// '.': On each side of 'e', no more than 1. Either its left side or right side
// must be a digit.
// '+'/'-': On each side of 'e', no more than 1. Must be located before digits.
// '0'--'9': Digits can be anywhere.
 void mytrim(const char* str, const char** pstart, const char** pend)
    {
        while(*str == ' ')
            str++;
        *pstart = str;
        *pend = str;
        const char* p = str;
        while(*p) {
            if (*p != ' ')
                *pend = p;
            p++;
        }
    }
    
    bool checkValidNum(const char* start, const char* end, bool isLeft)
    {
        const char* p = start;
        const char* prev = NULL;
        const char* prevDot = NULL;
        
        bool hasDigit = false;
        while (*p && (p < end)) {
            if (*p == ' ') {
                    return false;
            } else if (((*p == '+') || (*p == '-'))) {
                if (prev)
                    return false;
            } else if (*p == '.') {
                if (prevDot || !isLeft || ((!prev || !isdigit(*prev)) && !isdigit(*(p+1)) ))
                    return false;
                prevDot = p;
            } else if (isdigit(*p))
                hasDigit = true;
            else
                return false;
        
            prev = p;
            p++;
        }
        return hasDigit;
    }
    
    
    bool isNumber(const char *s) {
        const char* ePos = strchr(s, 'e');
        const char* start = s;
        const char* end = s;
        mytrim(s, &start, &end);
        if (!ePos)
            return checkValidNum(start, end+1, true);
  
        return checkValidNum(start, ePos, true) && checkValidNum(ePos+1, end+1, false);
    }


int main(int argc, char** argv)
{
    const char* test = "-1.";
    cout << isNumber(test) << endl;
    return 0;
}

