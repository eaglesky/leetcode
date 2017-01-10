#include <cstdlib>
#include <iostream>
using namespace std;

bool isPalindrome0(string s) {

        int end = s.size() - 1;
        int start = 0;
        while (start < end) {
            while ((start < end) && (!isalnum(s[start])))
                start++;
            while ((start < end) && !isalnum(s[end]))
                end--;
            if ((s[start] == s[end]) || (isalpha(s[start]) && isalpha(s[end]) && (abs(s[start] - s[end]) == abs('a' - 'A')))) {
                start++;
                end--;
                continue;
            } else
                return false;
    
        }
    
        return true;

}

//Another implementation
bool isPalindrome(string s) {
    int start = 0;
    int end = s.size() - 1;
    while (start < end) {
        if (isalnum(s[start]) && isalnum(s[end])) {
            if ((isalpha(s[start]) && isalpha(s[end]) && tolower(s[start]) != tolower(s[end]))
            || ((isdigit(s[start]) || isdigit(s[end])) && (s[start] != s[end])))
                return false;
            else {
                start++;
                end--;
            }
        } else if (!isalnum(s[start]))
            start++;
        else
            end--;
    }
    return true;
}


int main(int argc, char** argv)
{
    return 0;
}
