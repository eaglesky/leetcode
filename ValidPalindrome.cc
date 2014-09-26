#include <cstdlib>
#include <iostream>
using namespace std;

bool isPalindrome(string s) {

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

int main(int argc, char** argv)
{
    return 0;
}
