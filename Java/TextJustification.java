public class TextJustification {
    
    private String spaces(int numSpaces) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numSpaces; ++i) {
            sb.append(" ");
        }
        return sb.toString();
    }
    
    private String leftJustifyLine(List<String> words, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size(); ++i) {
            sb.append(words.get(i));
            if (i < words.size() - 1) {
                sb.append(" ");
            }
        }
        int numSpacesLeft = maxWidth - sb.length();
        sb.append(spaces(numSpacesLeft));
        return sb.toString();
    }
    
    private String justifyLine(List<String> words, int maxWidth, int wordsLen) {
        if (words.size() == 1) {
            return leftJustifyLine(words, maxWidth);
        }
        int numSpaces = maxWidth - wordsLen;
        int numSpacesBetweenWords = numSpaces / (words.size() - 1);
        int numExtraSpacesBetweenWords = numSpaces % (words.size() - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size(); ++i) {
            sb.append(words.get(i));
            if (i < words.size() - 1) {
                sb.append(spaces(numSpacesBetweenWords));
                if (i < numExtraSpacesBetweenWords) {
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }
    
    //We can assume that words array doesn't contain empty string, since
    //words array is usually obtained from the oringinal array using split.
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        if (words == null) {
            return result;
        } else if (words.length == 0) {
            result.add("");
            return result;
        }
        for (int i = 0; i < words.length;) {
            List<String> curLineWords = new ArrayList<>();
            int sumLen = 0;
            for (; i < words.length; ++i) {
                int curSumLen = sumLen + words[i].length();
                if (curSumLen + curLineWords.size() <= maxWidth) {
                    curLineWords.add(words[i]);
                    sumLen = curSumLen;
                } else {
                    break;
                }
            }
            if (i == words.length) {
                result.add(leftJustifyLine(curLineWords, maxWidth));
            } else {
                result.add(justifyLine(curLineWords, maxWidth, sumLen));
            }
        }
        return result;
    }
}