#include <iostream>
#include <vector>
using namespace std;

//Note that although the words could have empty strings, they are treated as
//normal strings. If you want to make the result unaffected by the empty
//strings, you can add pre-process at the beginning of fullJustify function,
//which removes all the empty strings in words if there is at least one
//non-empty string, otherwise don't remove anything.
void justifyOneLine(vector<string>& words, int start, int end, int numSpaces, vector<string>& result)
{
    string curStr = "";

    if (end < words.size()-1) {
        int numParts = (end > start) ? (end - start) : 1;
        
        int spaceLen = numSpaces / numParts;
        int extraSpaceNum = numSpaces % numParts;
        string spaces = "";
        for (int i = 0; i < spaceLen; ++i)
            spaces += " ";

        for (int i = start; i < end; ++i )
        {
                curStr += words[i];
                curStr += spaces;
                if (i < start + extraSpaceNum)
                    curStr += " ";
        }
        curStr += words[end];

        if (start == end) {
            curStr += spaces;            
        }

    } else {
        for (int i = start; i < end; ++i)
        {
            curStr += words[i];
            curStr += " ";
            numSpaces--;
        }
        curStr += words[end];
        for (int i = 0; i < numSpaces; ++i)
            curStr += " ";

    }

    result.push_back(curStr);
}

vector<string> fullJustify0(vector<string> &words, int L) {

    vector<string> result;
    if (words.size() == 0)
        return result;

    int start = 0;
    int numChars = 0;
    for (int i = 0; i < words.size(); ++i)
    {

        if (numChars + (i-start) + words[i].size()> L) {
            justifyOneLine(words, start, i-1, L-numChars,  result);            
            numChars = 0;
            start = i;
        } 

        numChars += words[i].size();
    }
    justifyOneLine(words, start, words.size()-1,  L-numChars, result);

    return result;
}


//Another implementation
vector<string> fullJustify1(vector<string> &words, int L) {
    vector<string> result;

    int end = 0;
    for (int i = 0; i < words.size(); i = end)
    {
        string curStr = "";
        int count = words[i].size();
        end = i + 1;
        for (; (end < words.size()) && (count+1+words[end].size() <= L); count += 1 + words[end].size(), ++end);
        int numWords = end - i;
        int spaceLeft = L - count;
        curStr += words[i];
        if ((end == words.size()) || (end == i + 1)) {
            int j = i+1;
            for (; j < end; ++j)
            {
                curStr += ' ';
                curStr += words[j];
            }
            for (; spaceLeft > 0; spaceLeft--)
                curStr += ' ';
        } else {
            int spaceBlock = numWords - 1;
            int spaceLeftOneBlock = spaceLeft / spaceBlock;
            int remain = spaceLeft % spaceBlock;
            for (int j = i + 1; j < end; ++j)
            {
                int curSpaceNum = (j - i <= remain) ? (2 + spaceLeftOneBlock) : (1 + spaceLeftOneBlock);
                string spaces = "";
                for (int s = 0; s < curSpaceNum; ++s)
                    spaces += ' ';
                curStr += spaces;
                curStr += words[j];
            }
        }
        
        result.push_back(curStr);
    }
    
    return result;
}

//Improved version of the above solution
vector<string> fullJustify(vector<string> &words, int L) {
    vector<string> result;

    int end = 0;
    for (int i = 0; i < words.size(); i = end)
    {
        string curStr = "";
        int count = words[i].size();
        end = i + 1;
        for (; (end < words.size()) && (count+1+words[end].size() <= L); count += 1 + words[end].size(), ++end);
        int numWords = end - i;
        int spaceLeft = L - count;
        curStr += words[i];
        
       
        for (int j = i + 1; j < end; ++j)
        {
            int curSpaceNum = 1;
            if (end < words.size()) {
                 int spaceBlock = numWords - 1;
                 int spaceLeftOneBlock = (L-count) / spaceBlock;
                 int remain = (L-count) % spaceBlock;
                curSpaceNum = (j - i <= remain) ? (2 + spaceLeftOneBlock) : (1 + spaceLeftOneBlock);
            }
            spaceLeft -= curSpaceNum-1;
            curStr += string(curSpaceNum, ' ');
            curStr += words[j];
        }
        curStr += string(spaceLeft, ' ');

        result.push_back(curStr);
    }
    
    return result;
}


int main(int argc, char** argv)
{
/*    vector<string> words = {
        "This", "is", "an", "example", "of", "text", "justification."
    };
    
    int L = 16;*/
/*    vector<string> words = {
        ""
    };
    int L = 0;*/
  /*  vector<string> words = {
        "a"
    };
    int L = 1;*/

 /*   vector<string> words = {
        "a","b","c","d","e"
    };
    int L = 1;*/

    vector<string> words = {
        "Listen","to","many,","speak","to","a","few."
    };
    int L = 6;

    vector<string> result = fullJustify(words, L);
    for (int i = 0; i < result.size(); ++i)
        cout << '"' << result[i] << '"' << endl;

    return 0;
}
